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
@Table(name = "membro")
@Analyzer(impl= BrazilianAnalyzer.class)
@XmlRootElement
//@NamedQueries({
//    @NamedQuery(name = "Membro.findAll", query = "SELECT m FROM Membro m"),
//    @NamedQuery(name = "Membro.findByIdLattes", query = "SELECT m FROM Membro m WHERE m.idLattes = :idLattes"),
//    @NamedQuery(name = "Membro.findByIdMembro", query = "SELECT m FROM Membro m WHERE m.idMembro = :idMembro"),
//    @NamedQuery(name = "Membro.findByNome", query = "SELECT m FROM Membro m WHERE m.nome = :nome"),
//    @NamedQuery(name = "Membro.findBySexo", query = "SELECT m FROM Membro m WHERE m.sexo = :sexo"),
//    @NamedQuery(name = "Membro.findByNomeCitacoesBibliograficas", query = "SELECT m FROM Membro m WHERE m.nomeCitacoesBibliograficas = :nomeCitacoesBibliograficas"),
//    @NamedQuery(name = "Membro.findByPeriodo", query = "SELECT m FROM Membro m WHERE m.periodo = :periodo"),
//    @NamedQuery(name = "Membro.findByBolsaProdutividade", query = "SELECT m FROM Membro m WHERE m.bolsaProdutividade = :bolsaProdutividade"),
//    @NamedQuery(name = "Membro.findByEnderecoProfissional", query = "SELECT m FROM Membro m WHERE m.enderecoProfissional = :enderecoProfissional"),
//    @NamedQuery(name = "Membro.findByUrl", query = "SELECT m FROM Membro m WHERE m.url = :url"),
//    @NamedQuery(name = "Membro.findByAtualizacaoCv", query = "SELECT m FROM Membro m WHERE m.atualizacaoCv = :atualizacaoCv"),
//    @NamedQuery(name = "Membro.findByTextoResumo", query = "SELECT m FROM Membro m WHERE m.textoResumo = :textoResumo"),
//    @NamedQuery(name = "Membro.findByIdCurso", query = "SELECT m FROM Membro m WHERE m.idCurso = :idCurso")})
public class Membro implements Serializable {
    //private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id_lattes", unique = true, nullable = false, length = 128)
    @Field(index=Index.YES, analyze=Analyze.YES, store=Store.YES)
    //@NumericField
    private Long idLattes;
    @Column(name = "id_curso", unique = true, nullable = false, length = 128)
    private Long idCurso;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_membro")
    private long idMembro;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 512)
    @Column(name = "nome")
    @Field(index=Index.YES, analyze=Analyze.YES, store=Store.YES)
    private String nome;
    @Size(max = 12)
    @Column(name = "sexo")
    private String sexo;
    @Size(max = 512)
    @Column(name = "nome_citacoes_bibliograficas")
    private String nomeCitacoesBibliograficas;
    @Size(max = 256)
    @Column(name = "periodo")
    private String periodo;
    @Size(max = 512)
    @Column(name = "bolsa_produtividade")
    private String bolsaProdutividade;
    @Size(max = 512)
    @Column(name = "endereco_profissional")
    private String enderecoProfissional;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "url")
    private String url;
    @Size(max = 12)
    @Column(name = "atualizacao_cv")
    private String atualizacaoCv;
    @Size(max = 2147483647)
    @Column(name = "texto_resumo")
    private String textoResumo;
//    @Lob
//    @Column(name = "foto")
//    private byte[] foto;
//    @ContainedIn
//    @OneToMany(mappedBy="membro")
//    private Set<ApresentacaoTrabalho> apresentacaoTrabalho;

    public Membro() {
    }

    public Membro(String nome, String url, Long idLattes) {
        this.idLattes = idLattes;
        this.nome = nome;
        this.url = url;
    }

    public long getIdMembro() {
        return idMembro;
    }

    public void setIdMembro(long idMembro) {
        this.idMembro = idMembro;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getNomeCitacoesBibliograficas() {
        return nomeCitacoesBibliograficas;
    }

    public void setNomeCitacoesBibliograficas(String nomeCitacoesBibliograficas) {
        this.nomeCitacoesBibliograficas = nomeCitacoesBibliograficas;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public String getBolsaProdutividade() {
        return bolsaProdutividade;
    }

    public void setBolsaProdutividade(String bolsaProdutividade) {
        this.bolsaProdutividade = bolsaProdutividade;
    }

    public String getEnderecoProfissional() {
        return enderecoProfissional;
    }

    public void setEnderecoProfissional(String enderecoProfissional) {
        this.enderecoProfissional = enderecoProfissional;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAtualizacaoCv() {
        return atualizacaoCv;
    }

    public void setAtualizacaoCv(String atualizacaoCv) {
        this.atualizacaoCv = atualizacaoCv;
    }

    public String getTextoResumo() {
        return textoResumo;
    }

    public void setTextoResumo(String textoResumo) {
        this.textoResumo = textoResumo;
    }

//    public byte[] getFoto() {
//        return foto;
//    }
//
//    public void setFoto(byte[] foto) {
//        this.foto = foto;
//    }

    public Long getIdLattes() {
        return idLattes;
    }

    public void setIdLattes(Long idLattes) {
        this.idLattes = idLattes;
    }

    public Long getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(Long idCurso) {
        this.idCurso = idCurso;
    }

//    public Set<ApresentacaoTrabalho> getApresentacaoTrabalho() {
//        return apresentacaoTrabalho;
//    }
//
//    public void setApresentacaoTrabalho(Set<ApresentacaoTrabalho> apresentacaoTrabalho) {
//        this.apresentacaoTrabalho = apresentacaoTrabalho;
//    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + (this.idLattes != null ? this.idLattes.hashCode() : 0);
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
        final Membro other = (Membro) obj;
        if (this.idLattes != other.idLattes && (this.idLattes == null || !this.idLattes.equals(other.idLattes))) {
            return false;
        }
        return true;
    }

    
}
