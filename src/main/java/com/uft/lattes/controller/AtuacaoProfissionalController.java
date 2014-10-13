/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uft.lattes.controller;

import com.google.gson.Gson;
import com.uft.lattes.repository.AtuacaoProfissionalRepository;
import com.uft.lattes.util.ApplicationUtilies;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author adelmir
 */
@Named
@RequestScoped
public class AtuacaoProfissionalController implements Serializable{

    public AtuacaoProfissionalController() {
    }
    
    public String toJson(String nome){
        return new Gson().toJson(new AtuacaoProfissionalRepository(ApplicationUtilies.catchEntityManager()).buscaAtuacaoProfissional(nome));
    }
}
