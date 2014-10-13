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

package com.uft.lattes.util;

import au.com.bytecode.opencsv.CSVReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author adelmir
 */
public class JavaRuntime {

    public static void main(String[] args) throws InterruptedException {
//        File file = new File("home/adelmir/pdf");
//        int count;
        try {
            //

            Runtime r = Runtime.getRuntime();

            System.out.println("Formatando o namenode");
            r.exec("hadoop namenode -format");
            System.out.println("Iniciando o hadoop");
            r.exec("start-all.sh").waitFor();
            r.exec("hadoop dfs -rmr /home/adelmir/hadoop-1.2.1/src/examples").waitFor();
            // recebe o número de arquivos txt
            System.out.println("Copiando os arquivos de texto para o HDFS");
            //count = contaArquivos(file);
            //for (int i = 0; i < count; i++) {
//            String sufix;
            System.out.println("Transferindo arquivos para o HDFS");
            r.exec("hadoop dfs -copyFromLocal /home/adelmir/pdf/arquivo.txt /home/adelmir/hadoop-1.2.1/src/examples/textinputfile.txt").waitFor();

            System.out.println("Executando o contador de palavras com os arquivos texto");
            r.exec("hadoop jar /home/adelmir/hadoop-1.2.1/hadoop-examples-1.2.1.jar wordcount /home/adelmir/hadoop-1.2.1/src/examples /home/adelmir/hadoop-1.2.1/src/examples/wordcountexample-output").waitFor();
            
            System.out.println("Copiando arquivos de saída para um diretório local");
            r.exec("hadoop dfs -get /home/adelmir/hadoop-1.2.1/src/examples/wordcountexample-output/ /home/adelmir/pdf/").waitFor();
            
            CSVReader reader = new CSVReader(new FileReader("yourfile.csv"), '\t');
            List<String[]> myEntries = reader.readAll();
            for(String[] entry : myEntries){
                
            }
            
        } catch (IOException exc) {
        }
    }

    public static int contaArquivos(File file) {

        File[] arquivos = file.listFiles();
        int aux = 0;

        // Se há arquivos, entra nesta rotina  
        if (arquivos != null) {
            int length = arquivos.length;

            for (int i = 0; i < length; ++i) {
                File f = arquivos[i];
                if (f.isFile()) {
                    aux++;
                }

            }
            System.out.println("Quantidade de Arquivos: " + aux);

        }
        return aux;

    }
}
