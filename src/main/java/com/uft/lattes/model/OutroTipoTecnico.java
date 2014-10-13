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
import org.hibernate.search.annotations.Analyze;
import org.apache.lucene.analysis.br.BrazilianAnalyzer;
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
@Table(name = "outro_tipo_tecnico")
@Analyzer(impl= BrazilianAnalyzer.class)
@XmlRootElement
//@NamedQueries({
//    @NamedQuery(name = "OutroTipoTecnico.findAll", query = "SELECT o FROM OutroTipoTecnico o"),
//    @NamedQuery(name = "OutroTipoTecnico.findByRelevante", query = "SELECT o FROM OutroTipoTecnico o WHERE o.relevante = :relevante"),
//    @NamedQuery(name = "OutroTipoTecnico.findByAutores", query = "SELECT o FROM OutroTipoTecnico o WHERE o.autores = :autores"),
//    @NamedQuery(name = "OutroTipoTecnico.findByTitulo", query = "SELECT o FROM OutroTipoTecnico o WHERE o.titulo = :titulo"),
//    @NamedQuery(name = "OutroTipoTecnico.findByAno", query = "SELECT o FROM OutroTipoTecnico o WHERE o.ano = :ano"),
//    @NamedQuery(name = "OutroTipoTecnico.findByNatureza", query = "SELECT o FROM OutroTipoTecnico o WHERE o.natureza = :natureza"),
//    @NamedQuery(name = "OutroTipoTecnico.findByMembro", query = "SELECT o FROM OutroTipoTecnico o WHERE o.membro.idLattes = :membroIdLattes")})
public class OutroTipoTecnico implements Serializable {
    //private static final long serialVersionUID = 1L;
    @Column(name = "id_outro_tipo_tecnico", nullable = false)
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
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 512)
    @Column(name = "natureza")
    private String natureza;
//    @ManyToOne
//    @IndexedEmbedded
//    private Membro membro;
    @Column(name = "membro_id_lattes")
    private Long idLattes;

    public OutroTipoTecnico() {
    }

    public OutroTipoTecnico(Long id, String relevante, String autores, String titulo, String ano, String natureza, Long idLattes) {
        this.id = id;
        this.relevante = relevante;
        this.autores = autores;
        this.titulo = titulo;
        this.ano = ano;
        this.natureza = natureza;
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

    public String getNatureza() {
        return natureza;
    }

    public void setNatureza(String natureza) {
        this.natureza = natureza;
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
        hash = 47 * hash + (this.id != null ? this.id.hashCode() : 0);
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
        final OutroTipoTecnico other = (OutroTipoTecnico) obj;
        if (this.idLattes != other.idLattes && (this.idLattes == null || !this.idLattes.equals(other.idLattes))) {
            return false;
        }
        return true;
    }

    
}
