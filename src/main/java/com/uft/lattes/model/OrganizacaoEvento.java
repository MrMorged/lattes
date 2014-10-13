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
@Table(name = "organizacao_evento")
@Analyzer(impl= BrazilianAnalyzer.class)
@XmlRootElement
//@NamedQueries({
//    @NamedQuery(name = "OrganizacaoEvento.findAll", query = "SELECT o FROM OrganizacaoEvento o"),
//    @NamedQuery(name = "OrganizacaoEvento.findByAutores", query = "SELECT o FROM OrganizacaoEvento o WHERE o.autores = :autores"),
//    @NamedQuery(name = "OrganizacaoEvento.findByNomeEvento", query = "SELECT o FROM OrganizacaoEvento o WHERE o.nomeEvento = :nomeEvento"),
//    @NamedQuery(name = "OrganizacaoEvento.findByAno", query = "SELECT o FROM OrganizacaoEvento o WHERE o.ano = :ano"),
//    @NamedQuery(name = "OrganizacaoEvento.findByNatureza", query = "SELECT o FROM OrganizacaoEvento o WHERE o.natureza = :natureza"),
//    @NamedQuery(name = "OrganizacaoEvento.findByItem", query = "SELECT o FROM OrganizacaoEvento o WHERE o.item = :item"),
//    @NamedQuery(name = "OrganizacaoEvento.findByMembro", query = "SELECT o FROM OrganizacaoEvento o WHERE o.membro.idLattes = :membroIdLattes")})
public class OrganizacaoEvento implements Serializable {
    //private static final long serialVersionUID = 1L;
    @Column(name = "id_organizacao_evento", nullable = false)
    @Id
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 512)
    @Column(name = "autores")
    private String autores;
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
    @Size(min = 1, max = 512)
    @Column(name = "natureza")
    private String natureza;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "item")
    private String item;
//    @ManyToOne
//    @IndexedEmbedded
//    private Membro membro;
    @Column(name = "membro_id_lattes")
    private Long idLattes;

    public OrganizacaoEvento() {
    }

    public OrganizacaoEvento(Long id, String autores, String nomeEvento, String ano, String natureza, String item, Long idLattes) {
        this.id = id;
        this.autores = autores;
        this.nomeEvento = nomeEvento;
        this.ano = ano;
        this.natureza = natureza;
        this.item = item;
        this.idLattes = idLattes;
    }

    public String getAutores() {
        return autores;
    }

    public void setAutores(String autores) {
        this.autores = autores;
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

    public String getNatureza() {
        return natureza;
    }

    public void setNatureza(String natureza) {
        this.natureza = natureza;
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
        hash = 43 * hash + (this.id != null ? this.id.hashCode() : 0);
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
        final OrganizacaoEvento other = (OrganizacaoEvento) obj;
        if (this.idLattes != other.idLattes && (this.idLattes == null || !this.idLattes.equals(other.idLattes))) {
            return false;
        }
        return true;
    }

}
