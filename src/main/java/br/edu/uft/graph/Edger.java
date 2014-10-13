/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.uft.graph;

import java.util.HashMap;

/**
 *
 * @author adelmir
 */
public class Edger {
    HashMap<String, HashMap<String, HashMap>> edgers;

    public Edger() {
        this.edgers = new HashMap<String, HashMap<String, HashMap>>();
    }
    
   public void addEdger(String key, String value){
        if(edgers.containsKey(key)){            
            edgers.get(key).put(value, new HashMap());
        }else{            
            HashMap<String, HashMap> hashMap = new HashMap<String, HashMap>();
            hashMap.put(value, new HashMap());
            edgers.put(key, hashMap);            
        }
    }

    public HashMap<String, HashMap<String, HashMap>> getEdgers() {
        return edgers;
    }

    public void setEdgers(HashMap<String, HashMap<String, HashMap>> edgers) {
        this.edgers = edgers;
    }
    
}
