/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uft.lattes.controller;

import com.google.gson.Gson;
import com.uft.lattes.repository.SoftwareSemPatenteRepository;
import com.uft.lattes.util.ApplicationUtilies;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author igor
 */
@Named
@SessionScoped
public class SoftwareSemPatenteController implements Serializable {

    @Inject
    CursoController curso;

    public SoftwareSemPatenteController() {
    }

    public String toJson() {
        if (this.curso.getSelecionado() != null) {
            return new Gson().toJson(new SoftwareSemPatenteRepository(ApplicationUtilies.catchEntityManager()).getSeparaPorAno(this.curso.getSelecionado().getId()));
        } else {
            return null;
        }
    }

    public CursoController getCurso() {
        return curso;
    }

    public void setCurso(CursoController curso) {
        this.curso = curso;
    }
}
