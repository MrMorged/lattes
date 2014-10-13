/*
 * Copyright 2014 adelmir.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.uft.lattes.controller;

import au.com.bytecode.opencsv.CSVReader;
import com.uft.lattes.model.LattesResult;
import com.uft.lattes.model.LattesSearchInfor;
import com.uft.lattes.model.PerfilMembro;
import com.uft.lattes.repository.LattesInforRepository;
import com.uft.lattes.util.ApplicationUtilies;
import com.uft.lattes.util.GenericController;
import com.uft.lattes.util.HadoopController;
import com.uft.lattes.util.PDFTextParser;
import static com.uft.lattes.util.PDFTextParser.readFully;
import com.uft.lattes.util.StopWords;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import org.jgroups.protocols.relay.RELAY2;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.RelationshipType;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author adelmir
 */
@Named("lattesInforController")
@SessionScoped
//@ManagedBean(name = "lattesInforController")
public class LattesInforController extends GenericController<LattesSearchInfor, LattesInforRepository> implements Serializable {

    LattesSearchInfor instance;
    int size;
    static String HADOOP_FOLDER = "/home/adelmir/hadoop-1.2.1/src/examples/";
    static String FOLDER_SEARCH = "/home/adelmir/pdf/";
    boolean newPdf = true;
    UploadedFile pdf;
    GraphDatabaseService graphDb;

    @Inject
    IndexingController indexController;

    @PreDestroy
    public void preDestroy() {
        registerShutdownHook(graphDb);
    }

    @PostConstruct
    public void postConstruct() {
        this.instance = new LattesSearchInfor();
        try {
            System.out.println("Iniciandooo");
            Runtime r = Runtime.getRuntime();
            r.exec("hadoop namenode -format");
            r.exec("start-all.sh").waitFor();
        } catch (IOException ex) {
            Logger.getLogger(HadoopController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(HadoopController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void handleFileUpload(FileUploadEvent event) {
        pdf = event.getFile();
        newPdf = true;
    }

    public String getWords() {
        if (size > 0) {
            String words = "";
            for (int i = 0; i < size; i++) {
                if (size < instance.getResults().size()) {
                    words += instance.getResults().get(i) + ",";
                } else {
                    break;
                }

            }
            return words.length() > 0 ? words.substring(0, words.length()) : "";
        }
        return "";
    }

    public void search() {
        if (pdf == null) {
            FacesMessage message = new FacesMessage("Error",
                    "Nenhum PDF selecionado!");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return;
        }

        if (newPdf) {
            try {
                byte[] pdfBytes = readFully(pdf.getInputstream());
                this.instance.setPdf(pdfBytes);
                if (this.instance.getId() == null) {
                    this.insert();
                } else {
                    this.update();
                }
                this.instance.setInitSearch(new Date());
                this.generateTxt(instance);
                this.removeStopWords(instance);
                instance.setResults(this.executeHadoop(instance));
                Collections.sort(getInstance().getResults(), new Comparator<LattesResult>() {

                    @Override
                    public int compare(LattesResult o1, LattesResult o2) {
                        if (o1.getCount() == o2.getCount()) {
                            return 0;
                        }
                        if (o1.getCount() > o2.getCount()) {
                            return -1;
                        }
                        return 1;
                    }
                });
                this.instance.setEndSearch(new Date());
                this.update();
                newPdf = false;
            } catch (IOException ex) {
                Logger.getLogger(LattesInforController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public void searchWords() {
        System.out.println("Pesquinsando");
        if (indexController == null) {
            System.out.println("null");
        } else {
            System.out.println("não é null");

            String search = "";
            int index = 0;
            for (LattesResult w : this.instance.getResults()) {
                search += w.getWord() + ";";
                if (index < size) {
                    index++;
                } else {
                    break;
                }
            }
            if (search.length() > 1 && search.charAt(search.length() - 1) == ';') {
                search = search.substring(0, search.length() - 1);
            }
            System.out.println(search);
            indexController.setPalavra(search);
            indexController.resultadoPesquisa2();

            HashMap<String, HashMap<String, List<PerfilMembro>>> r = indexController.getResultadoProvenance();

            ///home/adelmir/Downloads/neo4j-community-2.1.3/data/ 
            //graphDb = new GraphDatabaseFactory().newEmbeddedDatabase("/home/adelmir/pdf/neo4j"); 
            graphDb = new GraphDatabaseFactory().newEmbeddedDatabase("/home/adelmir/Downloads/neo4j-community-2.1.3/data/");

            Transaction tx = graphDb.beginTx();

            Node firstNode = graphDb.createNode();
            neo4JObject obj = new neo4JObject();
            obj.setNomeEdital(pdf.getFileName());
            obj.setPesquisa(instance.getInitSearch());
            firstNode.setProperty("nome", obj.getNomeEdital());
            firstNode.setProperty("data", new SimpleDateFormat("dd/MM/yyyy HH:mm").format(obj.getPesquisa()));
            /*firstNode.setProperty( "message", "Hello, " );
             secondNode = graphDb.createNode();
             secondNode.setProperty( "message", "World!" );

             relationship = firstNode.createRelationshipTo( secondNode, RelTypes.KNOWS );
             relationship.setProperty( "message", "brave Neo4j " );*/

            for (String word : r.keySet()) {
                System.out.println(word);
                Node wordNode = graphDb.createNode();
                wordNode.setProperty("palavra", word);
                HashMap<String, List<PerfilMembro>> wordResult = r.get(word);
                for (String prov : wordResult.keySet()) {
                    Node provNode = graphDb.createNode();
                    if (!wordResult.get(prov).isEmpty()) {
                        for (PerfilMembro membro : wordResult.get(prov)) {
                            Node teacherNode = graphDb.createNode();
                            teacherNode.setProperty("nome", membro.getNome());
                            teacherNode.setProperty("idLattes", membro.getIdLattes());
                            teacherNode.setProperty("frequencia", membro.getFrequencia());
                            provNode.createRelationshipTo(teacherNode, RelTypes.KNOWS);
                        }
                    }
                    wordNode.createRelationshipTo(provNode, RelTypes.KNOWS);
                }
                firstNode.createRelationshipTo(wordNode, RelTypes.KNOWS);
            }
            tx.success();
            graphDb.shutdown();
        }
    }

    private void generateTxt(LattesSearchInfor instance) {
        instance.setTxt(PDFTextParser.pdfToText(instance.getPdf()).getBytes());
    }

    private void removeStopWords(LattesSearchInfor instance) {
        String txt = new String(instance.getTxt());
        instance.setTxt(StopWords.getTextWithoutStopWords(txt).getBytes());
    }

    private String saveTxtHadoop(LattesSearchInfor instance) {
        BufferedWriter output = null;
        try {
            if (new File(FOLDER_SEARCH + "search_" + instance.getId()).exists()) {
                new File(FOLDER_SEARCH + "search_" + instance.getId()).delete();
            }
            new File(FOLDER_SEARCH + "search_" + instance.getId()).mkdir();
            String fileInputUrl = FOLDER_SEARCH + "search_" + instance.getId();
            String txtFileName = "txtInputFile_" + instance.getId() + ".txt";
            String fullPath = fileInputUrl + "/" + txtFileName;
            File inputFile = new File(fullPath);
            output = new BufferedWriter(new FileWriter(inputFile));
            output.write(new String(instance.getTxt()));
            output.close();
            return fullPath;
        } catch (IOException ex) {
            Logger.getLogger(LattesInforController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                output.close();
            } catch (IOException ex) {
                Logger.getLogger(LattesInforController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    private List<LattesResult> executeHadoop(LattesSearchInfor instance) {
        try {
            String fullPath = this.saveTxtHadoop(instance);
            String fileInputUrl = FOLDER_SEARCH + "search_" + instance.getId();
            Runtime r = Runtime.getRuntime();

            r.exec("hadoop namenode -format");

            new File("/home/adelmir/hadoop-1.2.1/src/result" + instance.getId()).mkdir();
            r.exec("hadoop dfs -rmr /home/adelmir/hadoop-1.2.1/src/result" + instance.getId()).waitFor();
            // recebe o número de arquivos txt
            System.out.println("Copiando os arquivos de texto para o HDFS");
            //count = contaArquivos(file);
            //for (int i = 0; i < count; i++) {
//            String sufix;
            System.out.println("Transferindo arquivos para o HDFS");
            r.exec("hadoop dfs -copyFromLocal " + fullPath + " /home/adelmir/hadoop-1.2.1/src/result" + instance.getId() + "/textinputfile.txt").waitFor();

            System.out.println("Executando o contador de palavras com os arquivos texto");
            r.exec("hadoop jar /home/adelmir/hadoop-1.2.1/hadoop-examples-1.2.1.jar wordcount /home/adelmir/hadoop-1.2.1/src/result" + instance.getId() + " /home/adelmir/hadoop-1.2.1/src/result" + instance.getId() + "/wordcountexample-output").waitFor();

            System.out.println("Copiando arquivos de saída para um diretório local");
            r.exec("hadoop dfs -get /home/adelmir/hadoop-1.2.1/src/result" + instance.getId() + "/wordcountexample-output " + fileInputUrl).waitFor();

            System.out.println(fileInputUrl + "/part-r-00000");
            instance.setResult(ApplicationUtilies.readFully(new FileInputStream(new File(fileInputUrl + "/wordcountexample-output/part-r-00000"))));
            FileReader fileReader = new FileReader(fileInputUrl + "/wordcountexample-output/part-r-00000");
            CSVReader reader = new CSVReader(fileReader, '\t');
            List<String[]> myEntries = reader.readAll();
            List<LattesResult> results = new ArrayList<LattesResult>();
            for (String[] entry : myEntries) {
                results.add(new LattesResult(entry[0], entry[1]));
            }
            //System.out.println(new File(fileInputUrl).delete());
            r.exec("rm -rf " + fileInputUrl);

            return results;
        } catch (IOException exc) {
            exc.printStackTrace();
        } catch (InterruptedException ex) {
            Logger.getLogger(LattesInforController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public LattesSearchInfor getInstance() {
        return instance;
    }

    public void setInstance(LattesSearchInfor instance) {
        this.instance = instance;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update() {
        this.repository = new LattesInforRepository(ApplicationUtilies.catchEntityManager());
        this.repository.update(instance);
    }

    @Override
    public void insert() {
        this.repository = new LattesInforRepository(ApplicationUtilies.catchEntityManager());
        this.repository.insert(instance);
    }

    public boolean isRenderPdfText() {
        return pdf != null;
    }

    public String getPdfName() {
        return isRenderPdfText() == true ? pdf.getFileName() : "";
    }

    private static void registerShutdownHook(final GraphDatabaseService graphDb) {
        // Registers a shutdown hook for the Neo4j instance so that it
        // shuts down nicely when the VM exits (even if you "Ctrl-C" the
        // running application).
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                graphDb.shutdown();
            }
        });
    }

    class neo4JObject {

        String nomeEdital;
        Date pesquisa;

        public String getNomeEdital() {
            return nomeEdital;
        }

        public void setNomeEdital(String nomeEdital) {
            this.nomeEdital = nomeEdital;
        }

        public Date getPesquisa() {
            return pesquisa;
        }

        public void setPesquisa(Date pesquisa) {
            this.pesquisa = pesquisa;
        }

    }

    private static enum RelTypes implements RelationshipType {

        KNOWS
    }

}
