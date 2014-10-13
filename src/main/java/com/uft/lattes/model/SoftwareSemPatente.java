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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;

/**
 *
 * @author Adelmir
 */
@Entity
@Indexed
@Table(name = "software_sem_patente")
@Analyzer(impl= BrazilianAnalyzer.class)
@XmlRootElement
//@NamedQueries({
//    @NamedQuery(name = "SoftwareSemPatente.findAll", query = "SELECT s FROM SoftwareSemPatente s"),
//    @NamedQuery(name = "SoftwareSemPatente.findByRelevante", query = "SELECT s FROM SoftwareSemPatente s WHERE s.relevante = :relevante"),
//    @NamedQuery(name = "SoftwareSemPatente.findByAutores", query = "SELECT s FROM SoftwareSemPatente s WHERE s.autores = :autores"),
//    @NamedQuery(name = "SoftwareSemPatente.findByTitulo", query = "SELECT s FROM SoftwareSemPatente s WHERE s.titulo = :titulo"),
//    @NamedQuery(name = "SoftwareSemPatente.findByAno", query = "SELECT s FROM SoftwareSemPatente s WHERE s.ano = :ano"),
//    @NamedQuery(name = "SoftwareSemPatente.findByMembro", query = "SELECT s FROM SoftwareSemPatente s WHERE s.membro.idLattes = :membroIdLattes")})
public class SoftwareSemPatente implements Serializable {
    //private static final long serialVersionUID = 1L;
    @Column(name = "id_software_sem_patente", nullable = false)
    @Id
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 512)
    @Column(name = "relevante")
    private String relevante;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 512)
    @Column(name = "autores")
    private String autores;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 512)
    @Column(name = "titulo")
    @Field(index=Index.YES, analyze=Analyze.YES, store=Store.YES)
    private String titulo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "ano")
    private String ano;
//    @ManyToOne
//    @IndexedEmbedded
//    private Membro membro;
    @Column(name = "membro_id_lattes")
    private Long idLattes;

    public SoftwareSemPatente() {
    }

    public SoftwareSemPatente(Long id, String relevante, String autores, String titulo, String ano, Long idLattes) {
        this.id = id;
        this.relevante = relevante;
        this.autores = autores;
        this.titulo = titulo;
        this.ano = ano;
        this.idLattes = idLattes;
    }

    public Long getIdLattes() {
        return idLattes;
    }

    public void setIdLattes(Long idLattes) {
        this.idLattes = idLattes;
    }

    public String getRelevante() {
        return relevante;
    }

    public void setRelevante(String relevante) {
        this.relevante = relevante;
    }

    public String getAutores() {
        return autores;
    }

    public void setAutores(String autores) {
        this.autores = autores;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + (this.id != null ? this.id.hashCode() : 0);
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
        final SoftwareSemPatente other = (SoftwareSemPatente) obj;
        if (this.idLattes != other.idLattes && (this.idLattes == null || !this.idLattes.equals(other.idLattes))) {
            return false;
        }
        return true;
    }


}
