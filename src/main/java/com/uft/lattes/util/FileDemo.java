/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uft.lattes.util;

/**
 *
 * @author Adelmir
 */
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

public class FileDemo  {
    
    private File f;//arquivo temporario

    public File getF() {
        return f;
    }
    public void setF(File f) {
        this.f = f;
    }
    public FileDemo(File f) {
        this.f = f;
    }
    // cira o arquivo.pdf e exibe o caminho onde se encontra
    public File criaArquivoTmp() throws IOException{
        System.out.println("chegou no criaArquivo");
        f = File.createTempFile("tmp", ".pdf", new File("D:\\arqTemp"));
         System.out.println("File path: "+f.getAbsolutePath());
        return null;   
    }
    // deleta o arquivo temporário 
    public File deleteAquivoTmp(){
        f.deleteOnExit();
        return null;
    } 
 
}