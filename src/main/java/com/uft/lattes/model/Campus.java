/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uft.lattes.model;

import com.uft.lattes.util.EntityConverter;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Adelmir
 */
@Entity
@Table(name = "campus")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Campus.findAll", query = "SELECT c FROM Campus c"),
    @NamedQuery(name = "Campus.findByIdCampus", query = "SELECT c FROM Campus c WHERE c.id = :id"),
    @NamedQuery(name = "Campus.findByDescricao", query = "SELECT c FROM Campus c WHERE c.descricao = :descricao")})
public class Campus implements Serializable, EntityConverter {
    //private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_campus")
    private Long id;
    @Size(max = 45)
    @Column(name = "descricao")
    private String descricao;

    public Campus() {
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Campus other = (Campus) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }


    @Override
    public Long getId() {
        return id;
    }

}
