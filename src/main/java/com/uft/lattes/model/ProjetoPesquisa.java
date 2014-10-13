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
@Table(name = "projeto_pesquisa")
@Analyzer(impl= BrazilianAnalyzer.class)
@XmlRootElement
//@NamedQueries({
//    @NamedQuery(name = "ProjetoPesquisa.findAll", query = "SELECT p FROM ProjetoPesquisa p"),
//    @NamedQuery(name = "ProjetoPesquisa.findByAnoInicio", query = "SELECT p FROM ProjetoPesquisa p WHERE p.anoInicio = :anoInicio"),
//    @NamedQuery(name = "ProjetoPesquisa.findByAnoConclusao", query = "SELECT p FROM ProjetoPesquisa p WHERE p.anoConclusao = :anoConclusao"),
//    @NamedQuery(name = "ProjetoPesquisa.findByNome", query = "SELECT p FROM ProjetoPesquisa p WHERE p.nome = :nome"),
//    @NamedQuery(name = "ProjetoPesquisa.findByDescricao", query = "SELECT p FROM ProjetoPesquisa p WHERE p.descricao = :descricao"),
//    @NamedQuery(name = "ProjetoPesquisa.findByMembro", query = "SELECT p FROM ProjetoPesquisa p WHERE p.membro.idLattes = :membroIdLattes")})
public class ProjetoPesquisa implements Serializable {
    //private static final long serialVersionUID = 1L;
    @Column(name = "id_projeto_pesquisa", nullable = false)
    @Id
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "ano_inicio")
    private String anoInicio;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "ano_conclusao")
    private String anoConclusao;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 512)
    @Column(name = "nome")
    @Field(index=Index.YES, analyze=Analyze.YES, store=Store.YES)
    private String nome;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "descricao")
    @Field(index=Index.YES, analyze=Analyze.YES, store=Store.YES)
    private String descricao;
//    @ManyToOne
//    @IndexedEmbedded
//    private Membro membro;
    @Column(name = "membro_id_lattes")
    private Long idLattes;

    public ProjetoPesquisa() {
    }

    public ProjetoPesquisa(Long id, String anoInicio, String anoConclusao, String nome, String descricao, Long idLattes) {
        this.id = id;
        this.anoInicio = anoInicio;
        this.anoConclusao = anoConclusao;
        this.nome = nome;
        this.descricao = descricao;
        this.idLattes = idLattes;
    }

    public String getAnoInicio() {
        return anoInicio;
    }

    public void setAnoInicio(String anoInicio) {
        this.anoInicio = anoInicio;
    }

    public String getAnoConclusao() {
        return anoConclusao;
    }

    public void setAnoConclusao(String anoConclusao) {
        this.anoConclusao = anoConclusao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
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
        hash = 67 * hash + (this.id != null ? this.id.hashCode() : 0);
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
        final ProjetoPesquisa other = (ProjetoPesquisa) obj;
        if (this.idLattes != other.idLattes && (this.idLattes == null || !this.idLattes.equals(other.idLattes))) {
            return false;
        }
        return true;
    }


}
