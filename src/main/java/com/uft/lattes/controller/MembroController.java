/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uft.lattes.controller;

import com.google.gson.Gson;
import com.ocpsoft.pretty.faces.annotation.URLAction;
import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLMappings;
import com.uft.lattes.model.Membro;
import com.uft.lattes.repository.MembroRepository;
import com.uft.lattes.util.ApplicationUtilies;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author igor
 */
@Named
@RequestScoped
public class MembroController implements Serializable {

    private String pesquisa;

    public MembroController() {
    }

    public String toJson(String membro) {
        return new Gson().toJson(new MembroRepository(ApplicationUtilies.catchEntityManager()).buscaMembro(membro));
    }

    public List<Membro> pesquisar() {
        if (this.pesquisa == null) {
            return null;
        }
        MembroRepository membro = new MembroRepository(ApplicationUtilies.catchEntityManager());

        return membro.buscaMembro(this.pesquisa);
    }

    public String getPesquisa() {
        return this.pesquisa;
    }

    public void setPesquisa(String pesquisa) {
        this.pesquisa = pesquisa;
    }
}
