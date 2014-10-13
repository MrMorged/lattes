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
@Table(name = "areas_atuacao")
@Analyzer(impl= BrazilianAnalyzer.class)
@XmlRootElement
//@NamedQueries({
//    @NamedQuery(name = "AreasAtuacao.findAll", query = "SELECT a FROM AreasAtuacao a"),
//    @NamedQuery(name = "AreasAtuacao.findByDescricao", query = "SELECT a FROM AreasAtuacao a WHERE a.descricao = :descricao"),
//    @NamedQuery(name = "AreasAtuacao.findByMembro", query = "SELECT a FROM AreasAtuacao a WHERE a.membro.idLattes = :membroIdLattes")})
public class AreasAtuacao implements Serializable {

    //private static final long serialVersionUID = 1L;
    @Column(name = "id_areas_atuacao", nullable = false)
    @Id
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "descricao")
    @Field(index=Index.YES, analyze=Analyze.YES, store=Store.YES)
    private String descricao;
    @NotNull
    @Size(min = 1, max = 512)
    @Column(name = "membro_id_lattes")
    @Field(index=Index.YES, analyze=Analyze.YES, store=Store.YES)
    private Long idLattes;

    public AreasAtuacao() {
    }

    public AreasAtuacao(Long id, String descricao, Long idLattes) {
        this.id = id;
        this.descricao = descricao;
        this.idLattes = idLattes;
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
        hash = 29 * hash + (this.id != null ? this.id.hashCode() : 0);
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
        final AreasAtuacao other = (AreasAtuacao) obj;
        if (this.idLattes != other.idLattes && (this.idLattes == null || !this.idLattes.equals(other.idLattes))) {
            return false;
        }
        return true;
    }

}
