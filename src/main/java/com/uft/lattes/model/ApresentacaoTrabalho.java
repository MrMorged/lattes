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
@Table(name = "apresentacao_trabalho")
@Analyzer(impl = BrazilianAnalyzer.class)
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
//    @AnalyzerDef(name = "de",
//            tokenizer = @TokenizerDef(factory = StandardTokenizerFactory.class),
//            filters = {
//                @TokenFilterDef(factory = LowerCaseFilterFactory.class),
//                @TokenFilterDef(factory = BrazilianStemFilterFactory.class)
//            })
//})
@XmlRootElement
//@NamedQueries({
//    @NamedQuery(name = "ApresentacaoTrabalho.findAll", query = "SELECT a FROM ApresentacaoTrabalho a"),
//    @NamedQuery(name = "ApresentacaoTrabalho.findByRelevante", query = "SELECT a FROM ApresentacaoTrabalho a WHERE a.relevante = :relevante"),
//    @NamedQuery(name = "ApresentacaoTrabalho.findByAutores", query = "SELECT a FROM ApresentacaoTrabalho a WHERE a.autores = :autores"),
//    @NamedQuery(name = "ApresentacaoTrabalho.findByTitulo", query = "SELECT a FROM ApresentacaoTrabalho a WHERE a.titulo = :titulo"),
//    @NamedQuery(name = "ApresentacaoTrabalho.findByAno", query = "SELECT a FROM ApresentacaoTrabalho a WHERE a.ano = :ano"),
//    @NamedQuery(name = "ApresentacaoTrabalho.findByNatureza", query = "SELECT a FROM ApresentacaoTrabalho a WHERE a.natureza = :natureza"),
//    @NamedQuery(name = "ApresentacaoTrabalho.findByMembro", query = "SELECT a FROM ApresentacaoTrabalho a WHERE a.membro.idLattes = :membroIdLattes")})
public class ApresentacaoTrabalho implements Serializable {

    //private static final long serialVersionUID = 1L;
    @Column(name = "id_apresentacao_trabalho", nullable = false)
    @Id
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 512)
    @Column(name = "relevante")
    private String relevante;
//    @Basic(optional = false)
//    @NotNull
//    @Size(min = 1, max = 512)
//    @Column(name = "autores")
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 512)
    @Column(name = "autores")
    @Field(index = Index.YES, analyze = Analyze.YES, store = Store.YES)
//    @Fields({
//        @Field(index = Index.YES, store = Store.YES,
//                analyzer = @Analyzer(definition = "en")),
//        @Field(index = Index.YES, store = Store.YES,
//                analyzer = @Analyzer(definition = "pt")),})
//    @Field
//    @AnalyzerDiscriminator(impl = LanguageDiscriminator.class)
    private String autores;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 512)
    @Column(name = "titulo")
    @Field(index = Index.YES, analyze = Analyze.YES, store = Store.YES)  // somente o titulo sera indexado
//    @Field
//    @AnalyzerDiscriminator(impl = LanguageDiscriminator.class)
    private String titulo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "ano")
    private String ano;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 512)
    @Column(name = "natureza")
    @Field(index = Index.YES, analyze = Analyze.YES, store = Store.YES)// somente o titulo sera indexado
//    @Field
//    @AnalyzerDiscriminator(impl = LanguageDiscriminator.class)
    private String natureza;
    //@ManyToOne
    //@Field(index=Index.YES, analyze=Analyze.YES, store=Store.YES) // somente o titulo sera indexado
    //@IndexedEmbedded
    //private Membro membro;
    @Column(name = "membro_id_lattes")
    private Long idLattes;

    public ApresentacaoTrabalho() {
    }

    public ApresentacaoTrabalho(Long id, String relevante, String autores, String titulo, String ano, String natureza, Long idLattes) {
        this.id = id;
        this.relevante = relevante;
        this.autores = autores;
        this.titulo = titulo;
        this.ano = ano;
        this.natureza = natureza;
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

    public Long getIdLattes() {
        return idLattes;
    }

    public void setIdLattes(Long idLattes) {
        this.idLattes = idLattes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 3;
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
        final ApresentacaoTrabalho other = (ApresentacaoTrabalho) obj;
        if (this.idLattes != other.idLattes && (this.idLattes == null || !this.idLattes.equals(other.idLattes))) {
            return false;
        }
        return true;
    }

}
