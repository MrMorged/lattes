/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uft.lattes.controller;

import com.google.gson.Gson;
import com.uft.lattes.repository.ProjetoPesquisaRepository;
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
public class ProjetoPesquisaController implements Serializable{

    public ProjetoPesquisaController() {
    }
    
    public String toJson(String nome){
        return new Gson().toJson(new ProjetoPesquisaRepository(ApplicationUtilies.catchEntityManager()).buscaProjetoPesquisa(nome));
    }
    
}
