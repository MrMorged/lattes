/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uft.lattes.controller;

import com.uft.lattes.model.Campus;
import com.uft.lattes.repository.CampusRepository;
import com.uft.lattes.util.ApplicationUtilies;
import com.uft.lattes.util.GenericController;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author adelmir
 */
@Named
@SessionScoped
public class CampusController extends GenericController<Campus, CampusRepository> implements Serializable{
    
    Campus selecionado;
    boolean campus;

    public CampusController() {
        this.entity = new Campus();
    }

    @Override
    public void insert() {
        this.repository = new CampusRepository(ApplicationUtilies.catchEntityManager());
        this.repository.insert(this.entity);
        this.entity = new Campus();
    }

    @Override
    public void remove() {
        this.repository = new CampusRepository(ApplicationUtilies.catchEntityManager());
        this.repository.remove(this.entity);
        this.entity = new Campus();
    }

    @Override
    public void update() {
        this.repository = new CampusRepository(ApplicationUtilies.catchEntityManager());
        this.repository.update(this.entity);
        this.entity = new Campus();
    }
    
    public List<Campus> loadTodosCampi(){
        this.repository = new CampusRepository(ApplicationUtilies.catchEntityManager());
        return this.repository.getTodosCampus();
    }

    public Campus getSelecionado() {
        return selecionado;
    }

    public void setSelecionado(Campus selecionado) {
        this.selecionado = selecionado;
    }

    public boolean isCampus() {
        return campus;
    }

    public void setCampus(boolean campus) {
        this.campus = campus;
    }
    
    
}
