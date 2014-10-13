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
@Table(name = "participacao_evento")
@Analyzer(impl= BrazilianAnalyzer.class)
@XmlRootElement
//@NamedQueries({
//    @NamedQuery(name = "ParticipacaoEvento.findAll", query = "SELECT p FROM ParticipacaoEvento p"),
//    @NamedQuery(name = "ParticipacaoEvento.findByAno", query = "SELECT p FROM ParticipacaoEvento p WHERE p.ano = :ano"),
//    @NamedQuery(name = "ParticipacaoEvento.findByItem", query = "SELECT p FROM ParticipacaoEvento p WHERE p.item = :item"),
//    @NamedQuery(name = "ParticipacaoEvento.findByMembro", query = "SELECT p FROM ParticipacaoEvento p WHERE p.membro.idLattes = :membroIdLattes")})
public class ParticipacaoEvento implements Serializable {
    //private static final long serialVersionUID = 1L;
    @Column(name = "id_participacao_evento", nullable = false)
    @Id
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "ano")
    private String ano;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "item")
    @Field(index=Index.YES, analyze=Analyze.YES, store=Store.YES)
    private String item;
//    @ManyToOne
//    @IndexedEmbedded
//    private Membro membro;
    @Column(name = "membro_id_lattes")
    private Long idLattes;

    public ParticipacaoEvento() {
    }

    public ParticipacaoEvento(Long id, String ano, String item, Long idLattes) {
        this.id = id;
        this.ano = ano;
        this.item = item;
        this.idLattes = idLattes;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
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
        hash = 89 * hash + (this.id != null ? this.id.hashCode() : 0);
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
        final ParticipacaoEvento other = (ParticipacaoEvento) obj;
        if (this.idLattes != other.idLattes && (this.idLattes == null || !this.idLattes.equals(other.idLattes))) {
            return false;
        }
        return true;
    }

    
}
