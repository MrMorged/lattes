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
@Table(name = "resumo_expandido_congresso")
@Analyzer(impl= BrazilianAnalyzer.class)
@XmlRootElement
//@NamedQueries({
//    @NamedQuery(name = "ResumoExpandidoCongresso.findAll", query = "SELECT r FROM ResumoExpandidoCongresso r"),
//    @NamedQuery(name = "ResumoExpandidoCongresso.findByRelevante", query = "SELECT r FROM ResumoExpandidoCongresso r WHERE r.relevante = :relevante"),
//    @NamedQuery(name = "ResumoExpandidoCongresso.findByDoi", query = "SELECT r FROM ResumoExpandidoCongresso r WHERE r.doi = :doi"),
//    @NamedQuery(name = "ResumoExpandidoCongresso.findByAutores", query = "SELECT r FROM ResumoExpandidoCongresso r WHERE r.autores = :autores"),
//    @NamedQuery(name = "ResumoExpandidoCongresso.findByTitulo", query = "SELECT r FROM ResumoExpandidoCongresso r WHERE r.titulo = :titulo"),
//    @NamedQuery(name = "ResumoExpandidoCongresso.findByNomeEvento", query = "SELECT r FROM ResumoExpandidoCongresso r WHERE r.nomeEvento = :nomeEvento"),
//    @NamedQuery(name = "ResumoExpandidoCongresso.findByAno", query = "SELECT r FROM ResumoExpandidoCongresso r WHERE r.ano = :ano"),
//    @NamedQuery(name = "ResumoExpandidoCongresso.findByVolume", query = "SELECT r FROM ResumoExpandidoCongresso r WHERE r.volume = :volume"),
//    @NamedQuery(name = "ResumoExpandidoCongresso.findByPaginas", query = "SELECT r FROM ResumoExpandidoCongresso r WHERE r.paginas = :paginas"),
//    @NamedQuery(name = "ResumoExpandidoCongresso.findByMembro", query = "SELECT r FROM ResumoExpandidoCongresso r WHERE r.membro.idLattes = :membroIdLattes")})
public class ResumoExpandidoCongresso implements Serializable {
    //private static final long serialVersionUID = 1L;

    @Column(name = "id_resumo_expandido_congresso", nullable = false)
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
    private String autores;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 512)
    @Column(name = "titulo")
    @Field(index=Index.YES, analyze=Analyze.YES, store=Store.YES)
    private String titulo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 512)
    @Column(name = "nome_evento")
    @Field(index=Index.YES, analyze=Analyze.YES, store=Store.YES)
    private String nomeEvento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "ano")
    private String ano;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "volume")
    private String volume;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "paginas")
    private String paginas;
//    @ManyToOne
//    @IndexedEmbedded
//    private Membro membro;
    @Column(name = "membro_id_lattes")
    private Long idLattes;

    public ResumoExpandidoCongresso() {
    }

    public ResumoExpandidoCongresso(Long id, String relevante, String doi, String autores, String titulo, String nomeEvento, String ano, String volume, String paginas, Long idLattes) {
        this.id = id;
        this.relevante = relevante;
        this.doi = doi;
        this.autores = autores;
        this.titulo = titulo;
        this.nomeEvento = nomeEvento;
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

    public String getNomeEvento() {
        return nomeEvento;
    }

    public void setNomeEvento(String nomeEvento) {
        this.nomeEvento = nomeEvento;
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
        int hash = 7;
        hash = 53 * hash + (this.id != null ? this.id.hashCode() : 0);
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
        final ResumoExpandidoCongresso other = (ResumoExpandidoCongresso) obj;
        if (this.idLattes != other.idLattes && (this.idLattes == null || !this.idLattes.equals(other.idLattes))) {
            return false;
        }
        return true;
    }

}
