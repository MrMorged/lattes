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

import com.uft.lattes.controller.LattesInforController;
import com.uft.lattes.model.LattesResult;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

/**
 *
 * @author adelmir
 */
public class PDFTextParser {

    public static String pdfToText(byte[] byteStream) {
        String parsedText = null;
        PDFTextStripper pdfStripper = null;
        PDDocument pdDoc = null;
        COSDocument cosDoc = null;
        try {
            PDFParser parser = new PDFParser(new ByteArrayInputStream(byteStream));
            parser.parse();
            cosDoc = parser.getDocument();
            pdfStripper = new PDFTextStripper();
            pdDoc = new PDDocument(cosDoc);
            parsedText = pdfStripper.getText(pdDoc);
        } catch (Exception e) {
            System.err
                    .println("Ocorreu um erro na conversão de texto."
                            + e.getMessage());
        } finally {
            try {
                if (cosDoc != null) {
                    cosDoc.close();
                }
                if (pdDoc != null) {
                    pdDoc.close();
                }
            } catch (Exception e) {
            }
        }
        return parsedText;

    }

    // Extrai o texto de um documento PDF
    static String pdftoText(String fileName) {

        PDFParser parser;
        String parsedText = null;
        PDFTextStripper pdfStripper = null;
        PDDocument pdDoc = null;
        COSDocument cosDoc = null;
        File file = new File(fileName);
        if (!file.isFile()) {
            System.err.println("Arquivo " + fileName + " não existe.");
            return null;
        }
        try {
            parser = new PDFParser(new FileInputStream(file));
        } catch (IOException e) {
            System.err.println("Error. " + e.getMessage());
            return null;
        }
        try {
            parser.parse();
            cosDoc = parser.getDocument();
            pdfStripper = new PDFTextStripper();
            pdDoc = new PDDocument(cosDoc);
//            pdfStripper.setStartPage(1);
//            pdfStripper.setEndPage(30);
            parsedText = pdfStripper.getText(pdDoc);
        } catch (Exception e) {
            System.err
                    .println("Ocorreu um erro na conversão de texto."
                            + e.getMessage());
        } finally {
            try {
                if (cosDoc != null) {
                    cosDoc.close();
                }
                if (pdDoc != null) {
                    pdDoc.close();
                }
            } catch (Exception e) {
            }
        }
        return parsedText;
    }

    public static void main(String args[]) {
        try {
//        PrintWriter pw = new PrintWriter("/home/adelmir/pdf/arquivo.txt");
//        System.out.println(pdftoText("/home/adelmir/pdf/teste.pdf"));
//       
//            pw.write(pdftoText("/home/adelmir/pdf/teste.pdf"));
//            pw.close();

            //String txt = pdftoText("/home/adelmir/pdf/teste.pdf");
            File file = new File("/home/adelmir/pdf/teste.pdf");
            byte[] pdfBytes = readFully(new FileInputStream(file));

            LattesInforController hadoopController = new LattesInforController();
            hadoopController.postConstruct();
            hadoopController.getInstance().setId(28L);
            hadoopController.getInstance().setPdf(pdfBytes);
            hadoopController.search();

            Collections.sort(hadoopController.getInstance().getResults(), new Comparator<LattesResult>() {

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

            for (LattesResult r : hadoopController.getInstance().getResults()) {
                System.out.println(r.getWord());
                System.out.println(r.getCount());
            }

        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PDFTextParser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PDFTextParser.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static byte[] readFully(InputStream stream) throws IOException {
        byte[] buffer = new byte[8192];
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        int bytesRead;
        while ((bytesRead = stream.read(buffer)) != -1) {
            baos.write(buffer, 0, bytesRead);
        }
        return baos.toByteArray();
    }
//    public void Gravar(String texto) {
//        String conteudo = texto;
//        try {
//            // o true significa q o arquivo será constante  
//            FileWriter x = new FileWriter("/home/adelmir/pdf/teste.txt", true);
//
//            conteudo += "\n\r"; // criando nova linha e recuo no arquivo              
//            x.write(conteudo); // armazena o texto no objeto x, que aponta para o arquivo             
//            x.close(); // cria o arquivo              
//            JOptionPane.showMessageDialog(null, "Arquivo gravado com sucesso", "Concluído", JOptionPane.INFORMATION_MESSAGE);
//        } // em caso de erro apreenta mensagem abaixo  
//        catch (Exception e) {
//            JOptionPane.showMessageDialog(null, e.getMessage(), "Atenção", JOptionPane.WARNING_MESSAGE);
//        }
//    }
}
