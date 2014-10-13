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
@Table(name = "capitulo_livro_publicado")
@Analyzer(impl = BrazilianAnalyzer.class)
@XmlRootElement
//@NamedQueries({
//    @NamedQuery(name = "CapituloLivroPublicado.findAll", query = "SELECT c FROM CapituloLivroPublicado c"),
//    @NamedQuery(name = "CapituloLivroPublicado.findByRelevante", query = "SELECT c FROM CapituloLivroPublicado c WHERE c.relevante = :relevante"),
//    @NamedQuery(name = "CapituloLivroPublicado.findByAutores", query = "SELECT c FROM CapituloLivroPublicado c WHERE c.autores = :autores"),
//    @NamedQuery(name = "CapituloLivroPublicado.findByTitulo", query = "SELECT c FROM CapituloLivroPublicado c WHERE c.titulo = :titulo"),
//    @NamedQuery(name = "CapituloLivroPublicado.findByLivro", query = "SELECT c FROM CapituloLivroPublicado c WHERE c.livro = :livro"),
//    @NamedQuery(name = "CapituloLivroPublicado.findByEdicao", query = "SELECT c FROM CapituloLivroPublicado c WHERE c.edicao = :edicao"),
//    @NamedQuery(name = "CapituloLivroPublicado.findByEditora", query = "SELECT c FROM CapituloLivroPublicado c WHERE c.editora = :editora"),
//    @NamedQuery(name = "CapituloLivroPublicado.findByAno", query = "SELECT c FROM CapituloLivroPublicado c WHERE c.ano = :ano"),
//    @NamedQuery(name = "CapituloLivroPublicado.findByVolume", query = "SELECT c FROM CapituloLivroPublicado c WHERE c.volume = :volume"),
//    @NamedQuery(name = "CapituloLivroPublicado.findByPaginas", query = "SELECT c FROM CapituloLivroPublicado c WHERE c.paginas = :paginas"),
//    @NamedQuery(name = "CapituloLivroPublicado.findByMembro", query = "SELECT c FROM CapituloLivroPublicado c WHERE c.membro.idLattes = :membroIdLattes")})
public class CapituloLivroPublicado implements Serializable {

    //private static final long serialVersionUID = 1L;
    @Column(name = "id_capitulo_livro_publicado", nullable = false)
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
    @Field(index = Index.YES, analyze = Analyze.YES, store = Store.YES)
    private String autores;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 12345678)
    @Column(name = "titulo")
    @Field(index = Index.YES, analyze = Analyze.YES, store = Store.YES)
    private String titulo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 512)
    @Column(name = "livro")
    private String livro;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "edicao")
    private String edicao;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "editora")
    private String editora;
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

    public CapituloLivroPublicado() {
    }

    public CapituloLivroPublicado(Long id, String relevante, String autores, String titulo, String livro, String edicao, String editora, String ano, String volume, String paginas, Long idLattes) {
        this.id = id;
        this.relevante = relevante;
        this.autores = autores;
        this.titulo = titulo;
        this.livro = livro;
        this.edicao = edicao;
        this.editora = editora;
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

    public String getLivro() {
        return livro;
    }

    public void setLivro(String livro) {
        this.livro = livro;
    }

    public String getEdicao() {
        return edicao;
    }

    public void setEdicao(String edicao) {
        this.edicao = edicao;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
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
        final CapituloLivroPublicado other = (CapituloLivroPublicado) obj;
        if (this.idLattes != other.idLattes && (this.idLattes == null || !this.idLattes.equals(other.idLattes))) {
            return false;
        }
        return true;
    }

}
