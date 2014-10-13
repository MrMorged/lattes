/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uft.lattes.controller;

import com.google.gson.Gson;
import com.uft.lattes.repository.ArtigoPeriodicoRepository;
import com.uft.lattes.util.ApplicationUtilies;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author igor
 */
@Named
@RequestScoped
public class ArtigoPeriodicoController implements Serializable {

    @Inject
    CursoController curso;

    public ArtigoPeriodicoController() {
    }

    public String toJson() {
        if (this.curso.getSelecionado() != null) {
            return new Gson().toJson(new ArtigoPeriodicoRepository(ApplicationUtilies.catchEntityManager()).getSeparaPorAno(this.curso.getSelecionado().getId()));
        } else {
            return null;
        }
    }
}
