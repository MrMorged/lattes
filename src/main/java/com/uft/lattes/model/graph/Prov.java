/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.uft.lattes.model.graph;

import com.uft.lattes.model.PerfilMembro;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Adelmir
 */
public class Prov {
    HashMap<String, List<PerfilMembro>> proveniencias;

    public Prov(HashMap<String, List<PerfilMembro>> proveniencias) {
        this.proveniencias = proveniencias;
    }

    public HashMap<String, List<PerfilMembro>> getProveniencias() {
        return proveniencias;
    }

    public void setProveniencias(HashMap<String, List<PerfilMembro>> proveniencias) {
        this.proveniencias = proveniencias;
    }
        
}
