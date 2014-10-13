/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uft.lattes.controller;

import com.google.gson.Gson;
import com.uft.lattes.repository.PremioTituloRepository;
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
public class PremioTituloController implements Serializable{

    public PremioTituloController() {
    }
    
    public String toJson(String nome){
        return new Gson().toJson(new PremioTituloRepository(ApplicationUtilies.catchEntityManager()).buscaPremioTitulo(nome));
    }
}
