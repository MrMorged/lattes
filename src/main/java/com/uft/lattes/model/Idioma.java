/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uft.lattes.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Adelmir
 */
@Entity
@Table(name = "idioma")
@XmlRootElement
//@NamedQueries({
//    @NamedQuery(name = "Idioma.findAll", query = "SELECT i FROM Idioma i"),
//    @NamedQuery(name = "Idioma.findByNome", query = "SELECT i FROM Idioma i WHERE i.nome = :nome"),
//    @NamedQuery(name = "Idioma.findByProficiencia", query = "SELECT i FROM Idioma i WHERE i.proficiencia = :proficiencia"),
//    @NamedQuery(name = "Idioma.findByMembro", query = "SELECT i FROM Idioma i WHERE i.membro.idLattes = :membroIdLattes")})
public class Idioma implements Serializable {
    //private static final long serialVersionUID = 1L;
    @Column(name = "id_idioma", nullable = false)
    @Id
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "nome")
    private String nome;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 512)
    @Column(name = "proficiencia")
    private String proficiencia;
//    @ManyToOne
//    private Membro membro;
    @Column(name = "membro_id_lattes")
    private Long idLattes;

    public Idioma() {
    }

    public Idioma(Long id, String nome, String proficiencia, Long idLattes) {
        this.id = id;
        this.nome = nome;
        this.proficiencia = proficiencia;
        this.idLattes = idLattes;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getProficiencia() {
        return proficiencia;
    }

    public void setProficiencia(String proficiencia) {
        this.proficiencia = proficiencia;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdLattes() {
        return idLattes;
    }

    public void setIdLattes(Long idLattes) {
        this.idLattes = idLattes;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + (this.id != null ? this.id.hashCode() : 0);
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
        final Idioma other = (Idioma) obj;
        if (this.idLattes != other.idLattes && (this.idLattes == null || !this.idLattes.equals(other.idLattes))) {
            return false;
        }
        return true;
    }

}
