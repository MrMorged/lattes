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
@Table(name = "texto_jornal_noticia")
@Analyzer(impl= BrazilianAnalyzer.class)
@XmlRootElement
//@NamedQueries({
//    @NamedQuery(name = "TextoJornalNoticia.findAll", query = "SELECT t FROM TextoJornalNoticia t"),
//    @NamedQuery(name = "TextoJornalNoticia.findByRelevante", query = "SELECT t FROM TextoJornalNoticia t WHERE t.relevante = :relevante"),
//    @NamedQuery(name = "TextoJornalNoticia.findByAutores", query = "SELECT t FROM TextoJornalNoticia t WHERE t.autores = :autores"),
//    @NamedQuery(name = "TextoJornalNoticia.findByTitulo", query = "SELECT t FROM TextoJornalNoticia t WHERE t.titulo = :titulo"),
//    @NamedQuery(name = "TextoJornalNoticia.findByNomeJornal", query = "SELECT t FROM TextoJornalNoticia t WHERE t.nomeJornal = :nomeJornal"),
//    @NamedQuery(name = "TextoJornalNoticia.findByData", query = "SELECT t FROM TextoJornalNoticia t WHERE t.data = :data"),
//    @NamedQuery(name = "TextoJornalNoticia.findByAno", query = "SELECT t FROM TextoJornalNoticia t WHERE t.ano = :ano"),
//    @NamedQuery(name = "TextoJornalNoticia.findByVolume", query = "SELECT t FROM TextoJornalNoticia t WHERE t.volume = :volume"),
//    @NamedQuery(name = "TextoJornalNoticia.findByPaginas", query = "SELECT t FROM TextoJornalNoticia t WHERE t.paginas = :paginas"),
//    @NamedQuery(name = "TextoJornalNoticia.findByMembro", query = "SELECT t FROM TextoJornalNoticia t WHERE t.membro.idLattes = :membroIdLattes")})
public class TextoJornalNoticia implements Serializable {
    //private static final long serialVersionUID = 1L;
    @Column(name = "id_texto_jornal_noticia", nullable = false)
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
    @Size(max = 512)
    @Column(name = "nome_jornal")
    private String nomeJornal;
    @Size(max = 12)
    @Column(name = "data")
    private String data;
    @Size(max = 5)
    @Column(name = "ano")
    private String ano;
    @Size(max = 256)
    @Column(name = "volume")
    private String volume;
    @Size(max = 256)
    @Column(name = "paginas")
    private String paginas;
//    @ManyToOne
//    @IndexedEmbedded
//    private Membro membro;
    @Column(name = "membro_id_lattes")
    private Long idLattes;

    public TextoJornalNoticia() {
    }

    public TextoJornalNoticia(Long id, String relevante, String autores, String titulo, String nomeJornal, String data, String ano, String volume, String paginas, Long idLattes) {
        this.id = id;
        this.relevante = relevante;
        this.autores = autores;
        this.titulo = titulo;
        this.nomeJornal = nomeJornal;
        this.data = data;
        this.ano = ano;
        this.volume = volume;
        this.paginas = paginas;
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

    public String getNomeJornal() {
        return nomeJornal;
    }

    public void setNomeJornal(String nomeJornal) {
        this.nomeJornal = nomeJornal;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getPaginas() {
        return paginas;
    }

    public void setPaginas(String paginas) {
        this.paginas = paginas;
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
        int hash = 5;
        hash = 97 * hash + (this.id != null ? this.id.hashCode() : 0);
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
        final TextoJornalNoticia other = (TextoJornalNoticia) obj;
        if (this.idLattes != other.idLattes && (this.idLattes == null || !this.idLattes.equals(other.idLattes))) {
            return false;
        }
        return true;
    }


}
