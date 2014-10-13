/*
 * Esta função quebra as palavras da busca por ' , ' virgula
 */
package com.uft.lattes.util;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Adelmir
 */
public class QuebraTextoSeparado {

    public String palavra;

    public List<String> QuebraTextoSeparado(String texto) {
        String[] separado = texto.split(",");
        List <String> lista = new ArrayList<String>();
        for (String separado1 : separado) {
            System.out.print(separado1 + "\n");
            lista.add(separado1);
        }
        return lista;
    }
    

    public String getPalavra() {
        return palavra;
    }

    public void setPalavra(String palavra) {
        this.palavra = palavra;
    }

}
