/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uft.lattes.controller;

import com.google.gson.Gson;
import com.uft.lattes.model.ApresentacaoTrabalho;
import com.uft.lattes.repository.ApresentacaoTrabalhoRepository;
import com.uft.lattes.util.ApplicationUtilies;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author igor
 */
@Named
@SessionScoped
public class ApresentacaoTrabalhoController implements Serializable {

    @Inject
    CursoController curso;

    public ApresentacaoTrabalhoController() {
    }

    public String toJson() {
        if (this.curso.getSelecionado() != null) {
            return new Gson().toJson(new ApresentacaoTrabalhoRepository(ApplicationUtilies.catchEntityManager()).getSeparaPorAno(this.curso.getSelecionado().getId()));
        } else {
            return null;
        }
    }

}
