/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.uft.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author adelmir
 */
public class Node {

    HashMap<String, HashMap<String, String>> node;
    List<String> ids = new ArrayList<String>();

    public Node() {
        node = new HashMap<String, HashMap<String, String>>();
    }

    public void addNode(String key, String value, String tipo, String link) {
        String color = "red";
        HashMap<String, String> dados = new HashMap<String, String>();
        if(tipo.equalsIgnoreCase("palavra")){
            dados.put("shape", "dot");
            dados.put("alpha", "1");            
            color = "green";
        }else if(tipo.equalsIgnoreCase("professor")){
            dados.put("alpha", "0");
            color = "gray";
        }else if(tipo.equalsIgnoreCase("lattes")){
            dados.put("shape", "dot");
            dados.put("alpha", "1");
            color = "GreenYellow";
        }
        dados.put("color", color);
        dados.put("label", value);
        if(link != null){
            dados.put("link", link);
        }                
        node.put(key, dados);        
    }

    public HashMap<String, HashMap<String, String>> getNode() {
        return node;
    }

    public void setNode(HashMap<String, HashMap<String, String>> node) {
        this.node = node;
    }

    public List<String> getIds() {
        return ids;
    }

    public void setIds(List<String> ids) {
        this.ids = ids;
    }        

}
