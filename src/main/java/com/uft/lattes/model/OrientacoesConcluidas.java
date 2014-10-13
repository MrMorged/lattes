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
@Table(name = "orientacoes_concluidas")
@Analyzer(impl= BrazilianAnalyzer.class)
@XmlRootElement
//@NamedQueries({
//    @NamedQuery(name = "OrientacoesConcluidas.findAll", query = "SELECT o FROM OrientacoesConcluidas o"),
//    @NamedQuery(name = "OrientacoesConcluidas.findByNome", query = "SELECT o FROM OrientacoesConcluidas o WHERE o.nome = :nome"),
//    @NamedQuery(name = "OrientacoesConcluidas.findByTituloTrabalho", query = "SELECT o FROM OrientacoesConcluidas o WHERE o.tituloTrabalho = :tituloTrabalho"),
//    @NamedQuery(name = "OrientacoesConcluidas.findByAnoConclusao", query = "SELECT o FROM OrientacoesConcluidas o WHERE o.anoConclusao = :anoConclusao"),
//    @NamedQuery(name = "OrientacoesConcluidas.findByInstituicao", query = "SELECT o FROM OrientacoesConcluidas o WHERE o.instituicao = :instituicao"),
//    @NamedQuery(name = "OrientacoesConcluidas.findByAgencia", query = "SELECT o FROM OrientacoesConcluidas o WHERE o.agencia = :agencia"),
//    @NamedQuery(name = "OrientacoesConcluidas.findByTipoOrientacao", query = "SELECT o FROM OrientacoesConcluidas o WHERE o.tipoOrientacao = :tipoOrientacao"),
//    @NamedQuery(name = "OrientacoesConcluidas.findByMembro", query = "SELECT o FROM OrientacoesConcluidas o WHERE o.membro.idLattes = :membroIdLattes")})
public class OrientacoesConcluidas implements Serializable {
    //private static final long serialVersionUID = 1L;
    @Column(name = "id_orientacoes_concluidas", nullable = false)
    @Id
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 512)
    @Column(name = "nome")
    private String nome;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 512)
    @Column(name = "titulo_trabalho")
    @Field(index=Index.YES, analyze=Analyze.YES, store=Store.YES)
    private String tituloTrabalho;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "ano_conclusao")
    private String anoConclusao;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 512)
    @Column(name = "instituicao")
    private String instituicao;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "agencia")
    private String agencia;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "tipo_orientacao")
    private String tipoOrientacao;
//    @ManyToOne
//    @IndexedEmbedded
//    private Membro membro;
    @Column(name = "membro_id_lattes")
    private Long idLattes;

    public OrientacoesConcluidas() {
    }

    public OrientacoesConcluidas(Long id, String nome, String tituloTrabalho, String anoConclusao, String instituicao, String agencia, String tipoOrientacao, Long idLattes) {
        this.id = id;
        this.nome = nome;
        this.tituloTrabalho = tituloTrabalho;
        this.anoConclusao = anoConclusao;
        this.instituicao = instituicao;
        this.agencia = agencia;
        this.tipoOrientacao = tipoOrientacao;
        this.idLattes = idLattes;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTituloTrabalho() {
        return tituloTrabalho;
    }

    public void setTituloTrabalho(String tituloTrabalho) {
        this.tituloTrabalho = tituloTrabalho;
    }

    public String getAnoConclusao() {
        return anoConclusao;
    }

    public void setAnoConclusao(String anoConclusao) {
        this.anoConclusao = anoConclusao;
    }

    public String getInstituicao() {
        return instituicao;
    }

    public void setInstituicao(String instituicao) {
        this.instituicao = instituicao;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public String getTipoOrientacao() {
        return tipoOrientacao;
    }

    public void setTipoOrientacao(String tipoOrientacao) {
        this.tipoOrientacao = tipoOrientacao;
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
        hash = 71 * hash + (this.id != null ? this.id.hashCode() : 0);
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
        final OrientacoesConcluidas other = (OrientacoesConcluidas) obj;
        if (this.idLattes != other.idLattes && (this.idLattes == null || !this.idLattes.equals(other.idLattes))) {
            return false;
        }
        return true;
    }


}
