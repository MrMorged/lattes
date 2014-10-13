/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uft.lattes.controller;

import com.google.gson.Gson;
import com.uft.lattes.repository.TrabalhoTecnicoRepository;
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
public class TrabalhoTecnicoController implements Serializable {

    @Inject
    CursoController curso;

    public TrabalhoTecnicoController() {
    }

    public String toJson() {
        if (this.curso.getSelecionado() != null) {
            return new Gson().toJson(new TrabalhoTecnicoRepository(ApplicationUtilies.catchEntityManager()).getSeparaPorAno(this.curso.getSelecionado().getId()));
        }
        else return null;
    }
    /**
     * Busca todos os trabalhos técnicos produzidos em 2011
     *
     * public void getTrabalhosTecnicos2011(){ ArrayList<TrabalhoTecnico>
     * periodo2011 = new ArrayList<TrabalhoTecnico>(); List<TrabalhoTecnico>
     * atuacoes = new
     * TrabalhoTecnicoRepository(ApplicationUtilies.catchEntityManager()).BuscaTrabalhoTecnico();
     * for(int i=0; i< atuacoes.size(); i++){
     * if(atuacoes.get(i).getAno().equals("2011")){
     * periodo2011.add(atuacoes.get(i)); } }
    }
     */
}
