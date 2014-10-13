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
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Fields;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;

/**
 *
 * @author Adelmir
 */
@Entity
@Indexed
@Table(name = "artigo_periodico")
@Analyzer(impl = BrazilianAnalyzer.class)
@XmlRootElement
//@NamedQueries({
//    @NamedQuery(name = "ArtigoPeriodico.findAll", query = "SELECT a FROM ArtigoPeriodico a"),
//    @NamedQuery(name = "ArtigoPeriodico.findByRelevante", query = "SELECT a FROM ArtigoPeriodico a WHERE a.relevante = :relevante"),
//    @NamedQuery(name = "ArtigoPeriodico.findByDoi", query = "SELECT a FROM ArtigoPeriodico a WHERE a.doi = :doi"),
//    @NamedQuery(name = "ArtigoPeriodico.findByAutores", query = "SELECT a FROM ArtigoPeriodico a WHERE a.autores = :autores"),
//    @NamedQuery(name = "ArtigoPeriodico.findByTitulo", query = "SELECT a FROM ArtigoPeriodico a WHERE a.titulo = :titulo"),
//    @NamedQuery(name = "ArtigoPeriodico.findByRevista", query = "SELECT a FROM ArtigoPeriodico a WHERE a.revista = :revista"),
//    @NamedQuery(name = "ArtigoPeriodico.findByPaginas", query = "SELECT a FROM ArtigoPeriodico a WHERE a.paginas = :paginas"),
//    @NamedQuery(name = "ArtigoPeriodico.findByVolume", query = "SELECT a FROM ArtigoPeriodico a WHERE a.volume = :volume"),
//    @NamedQuery(name = "ArtigoPeriodico.findByNumero", query = "SELECT a FROM ArtigoPeriodico a WHERE a.numero = :numero"),
//    @NamedQuery(name = "ArtigoPeriodico.findByAno", query = "SELECT a FROM ArtigoPeriodico a WHERE a.ano = :ano"),
//    @NamedQuery(name = "ArtigoPeriodico.findByMembro", query = "SELECT a FROM ArtigoPeriodico a WHERE a.membro.idLattes = :membroIdLattes")})
public class ArtigoPeriodico implements Serializable {

    //private static final long serialVersionUID = 1L;
    @Column(name = "id_artigo_periodico", nullable = false)
    @Id
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 512)
    @Column(name = "relevante")
    private String relevante;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "doi")
    private String doi;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 512)
    @Column(name = "autores")
    @Field(index = Index.YES, analyze = Analyze.YES, store = Store.YES)
    private String autores;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 512)
    @Column(name = "titulo")
    @Field(index = Index.YES, analyze = Analyze.YES, store = Store.YES)
    private String titulo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 512)
    @Column(name = "revista")
    @Field(index = Index.YES, analyze = Analyze.YES, store = Store.YES)
    private String revista;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "paginas")
    private String paginas;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "volume")
    private String volume;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "numero")
    private String numero;
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

    public ArtigoPeriodico() {
    }

    public ArtigoPeriodico(Long id, String relevante, String doi, String autores, String titulo, String revista, String paginas, String volume, String numero, String ano, Long idLattes) {
        this.id = id;
        this.relevante = relevante;
        this.doi = doi;
        this.autores = autores;
        this.titulo = titulo;
        this.revista = revista;
        this.paginas = paginas;
        this.volume = volume;
        this.numero = numero;
        this.ano = ano;
        this.idLattes = idLattes;
    }

    public String getRelevante() {
        return relevante;
    }

    public void setRelevante(String relevante) {
        this.relevante = relevante;
    }

    public String getDoi() {
        return doi;
    }

    public void setDoi(String doi) {
        this.doi = doi;
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

    public String getRevista() {
        return revista;
    }

    public void setRevista(String revista) {
        this.revista = revista;
    }

    public String getPaginas() {
        return paginas;
    }

    public void setPaginas(String paginas) {
        this.paginas = paginas;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
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

    public Long getIdLattes() {
        return idLattes;
    }

    public void setIdLattes(Long idLattes) {
        this.idLattes = idLattes;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + (this.id != null ? this.id.hashCode() : 0);
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
        final ArtigoPeriodico other = (ArtigoPeriodico) obj;
        if (this.idLattes != other.idLattes && (this.idLattes == null || !this.idLattes.equals(other.idLattes))) {
            return false;
        }
        return true;
    }

}
