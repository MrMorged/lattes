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
import org.apache.solr.analysis.BrazilianStemFilterFactory;
import org.apache.solr.analysis.LowerCaseFilterFactory;
import org.apache.solr.analysis.SnowballPorterFilterFactory;
import org.apache.solr.analysis.StandardTokenizerFactory;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.AnalyzerDef;
import org.hibernate.search.annotations.AnalyzerDefs;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Parameter;
import org.hibernate.search.annotations.Store;
import org.hibernate.search.annotations.TokenFilterDef;
import org.hibernate.search.annotations.TokenizerDef;

/**
 *
 * @author Adelmir
 */
@Entity
@Indexed
@Table(name = "formacao_academica")
@Analyzer(impl= BrazilianAnalyzer.class)
//@AnalyzerDefs({
//    @AnalyzerDef(name = "en",
//            tokenizer = @TokenizerDef(factory = StandardTokenizerFactory.class),
//            filters = {
//                @TokenFilterDef(factory = LowerCaseFilterFactory.class),
//                @TokenFilterDef(factory = SnowballPorterFilterFactory.class,
//                        params = {
//                            @Parameter(name = "language", value = "English")}
//                )
//            }
//    ),
//    @AnalyzerDef(name = "pt",
//            tokenizer = @TokenizerDef(factory = StandardTokenizerFactory.class),
//            filters = {
//                @TokenFilterDef(factory = LowerCaseFilterFactory.class),
//                @TokenFilterDef(factory = BrazilianStemFilterFactory.class)
//            })
//})
@XmlRootElement
//@NamedQueries({
//    @NamedQuery(name = "FormacaoAcademica.findAll", query = "SELECT f FROM FormacaoAcademica f"),
//    @NamedQuery(name = "FormacaoAcademica.findByAnoInicio", query = "SELECT f FROM FormacaoAcademica f WHERE f.anoInicio = :anoInicio"),
//    @NamedQuery(name = "FormacaoAcademica.findByAnoConclusao", query = "SELECT f FROM FormacaoAcademica f WHERE f.anoConclusao = :anoConclusao"),
//    @NamedQuery(name = "FormacaoAcademica.findByTipo", query = "SELECT f FROM FormacaoAcademica f WHERE f.tipo = :tipo"),
//    @NamedQuery(name = "FormacaoAcademica.findByNomeInstituicao", query = "SELECT f FROM FormacaoAcademica f WHERE f.nomeInstituicao = :nomeInstituicao"),
//    @NamedQuery(name = "FormacaoAcademica.findByDescricao", query = "SELECT f FROM FormacaoAcademica f WHERE f.descricao = :descricao"),
//    @NamedQuery(name = "FormacaoAcademica.findByMembro", query = "SELECT f FROM FormacaoAcademica f WHERE f.membro.idLattes = :membroIdLattes")})
public class FormacaoAcademica implements Serializable {

    //private static final long serialVersionUID = 1L;
    @Column(name = "id_formacao_academica", nullable = false)
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
    @Size(min = 1, max = 128)
    @Column(name = "tipo")
    private String tipo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 512)
    @Column(name = "nome_instituicao")
    private String nomeInstituicao;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "descricao")
    @Field(index = Index.YES, analyze = Analyze.YES, store = Store.YES)
    private String descricao;
//    @ManyToOne
//    @IndexedEmbedded
//    private Membro membro;
    @Column(name = "membro_id_lattes")
    private Long idLattes;

    public FormacaoAcademica() {
    }

    public FormacaoAcademica(Long id, String anoInicio, String anoConclusao, String tipo, String nomeInstituicao, String descricao, Long idLattes) {
        this.id = id;
        this.anoInicio = anoInicio;
        this.anoConclusao = anoConclusao;
        this.tipo = tipo;
        this.nomeInstituicao = nomeInstituicao;
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNomeInstituicao() {
        return nomeInstituicao;
    }

    public void setNomeInstituicao(String nomeInstituicao) {
        this.nomeInstituicao = nomeInstituicao;
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
        final FormacaoAcademica other = (FormacaoAcademica) obj;
        if (this.idLattes != other.idLattes && (this.idLattes == null || !this.idLattes.equals(other.idLattes))) {
            return false;
        }
        return true;
    }

}
