/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uft.lattes.controller;

import com.google.gson.Gson;
import com.uft.lattes.repository.AreasAtuacaoRepository;
import com.uft.lattes.util.ApplicationUtilies;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author igor
 */
@Named
@RequestScoped
public class AreasAtuacaoController implements Serializable{

    public AreasAtuacaoController() {
    }
    
    public String toJson(String nome){
        return new Gson().toJson(new AreasAtuacaoRepository(ApplicationUtilies.catchEntityManager()).buscaAreasAtuacao(nome));
    }
    
}
