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
@Table(name = "livro_publicado")
@Analyzer(impl= BrazilianAnalyzer.class)
@XmlRootElement
//@NamedQueries({
//    @NamedQuery(name = "LivroPublicado.findAll", query = "SELECT l FROM LivroPublicado l"),
//    @NamedQuery(name = "LivroPublicado.findByRelevante", query = "SELECT l FROM LivroPublicado l WHERE l.relevante = :relevante"),
//    @NamedQuery(name = "LivroPublicado.findByAutores", query = "SELECT l FROM LivroPublicado l WHERE l.autores = :autores"),
//    @NamedQuery(name = "LivroPublicado.findByTitulo", query = "SELECT l FROM LivroPublicado l WHERE l.titulo = :titulo"),
//    @NamedQuery(name = "LivroPublicado.findByEdicao", query = "SELECT l FROM LivroPublicado l WHERE l.edicao = :edicao"),
//    @NamedQuery(name = "LivroPublicado.findByAno", query = "SELECT l FROM LivroPublicado l WHERE l.ano = :ano"),
//    @NamedQuery(name = "LivroPublicado.findByVolume", query = "SELECT l FROM LivroPublicado l WHERE l.volume = :volume"),
//    @NamedQuery(name = "LivroPublicado.findByPaginas", query = "SELECT l FROM LivroPublicado l WHERE l.paginas = :paginas"),
//    @NamedQuery(name = "LivroPublicado.findByMembro", query = "SELECT l FROM LivroPublicado l WHERE l.membro.idLattes = :membroIdLattes")})
public class LivroPublicado implements Serializable {
    //private static final long serialVersionUID = 1L;
    @Column(name = "id_livro_publicado", nullable = false)
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
    @Size(min = 1, max = 45)
    @Column(name = "edicao")
    private String edicao;
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

    public LivroPublicado() {
    }

    public LivroPublicado(Long id, String relevante, String autores, String titulo, String edicao, String ano, String volume, String paginas, Long idLattes) {
        this.id = id;
        this.relevante = relevante;
        this.autores = autores;
        this.titulo = titulo;
        this.edicao = edicao;
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

    public String getEdicao() {
        return edicao;
    }

    public void setEdicao(String edicao) {
        this.edicao = edicao;
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
        final LivroPublicado other = (LivroPublicado) obj;
        if (this.idLattes != other.idLattes && (this.idLattes == null || !this.idLattes.equals(other.idLattes))) {
            return false;
        }
        return true;
    }

}
