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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Store;

/**
 *
 * @author Adelmir
 */
@Entity
@Table(name = "atuacao_profissional")
@XmlRootElement
//@NamedQueries({
//    @NamedQuery(name = "AtuacaoProfissional.findAll", query = "SELECT a FROM AtuacaoProfissional a"),
//    @NamedQuery(name = "AtuacaoProfissional.findByInstituicao", query = "SELECT a FROM AtuacaoProfissional a WHERE a.instituicao = :instituicao"),
//    @NamedQuery(name = "AtuacaoProfissional.findBySigla", query = "SELECT a FROM AtuacaoProfissional a WHERE a.sigla = :sigla"),
//    @NamedQuery(name = "AtuacaoProfissional.findByAnoEntrada", query = "SELECT a FROM AtuacaoProfissional a WHERE a.anoEntrada = :anoEntrada"),
//    @NamedQuery(name = "AtuacaoProfissional.findByAnoSaida", query = "SELECT a FROM AtuacaoProfissional a WHERE a.anoSaida = :anoSaida"),
//    @NamedQuery(name = "AtuacaoProfissional.findByVinculo", query = "SELECT a FROM AtuacaoProfissional a WHERE a.vinculo = :vinculo"),
//    @NamedQuery(name = "AtuacaoProfissional.findByEnquadramento", query = "SELECT a FROM AtuacaoProfissional a WHERE a.enquadramento = :enquadramento"),
//    @NamedQuery(name = "AtuacaoProfissional.findByCargaHoraria", query = "SELECT a FROM AtuacaoProfissional a WHERE a.cargaHoraria = :cargaHoraria"),
//    @NamedQuery(name = "AtuacaoProfissional.findByRegime", query = "SELECT a FROM AtuacaoProfissional a WHERE a.regime = :regime")})

public class AtuacaoProfissional implements Serializable {

    //private static final long serialVersionUID = 1L;
    @Column(name = "id_atuacao_profissional", nullable = false)
    @Id
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 512)
    @Column(name = "instituicao")
    private String instituicao;
    @Size(max = 45)
    @Column(name = "sigla")
    private String sigla;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "ano_entrada")
    private String anoEntrada;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "ano_saida")
    private String anoSaida;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "vinculo")
    private String vinculo;
    @Size(max = 128)
    @Column(name = "enquadramento")
    private String enquadramento;
    @Size(max = 4)
    @Column(name = "carga_horaria")
    private String cargaHoraria;
    @Size(max = 128)
    @Column(name = "regime")
    private String regime;
////    @ManyToOne(fetch= FetchType.LAZY)
////    @IndexedEmbedded
////    private Membro membro;
//    @Column(name = "membro_id_lattes")
    @NotNull
    @Size(min = 1, max = 512)
    @Column(name = "membro_id_lattes")
    @Field(index = Index.YES, analyze = Analyze.YES, store = Store.YES)
    private Long idLattes;

    public AtuacaoProfissional() {
    }

    public AtuacaoProfissional(Long id, String instituicao, String sigla, String anoEntrada, String anoSaida, String vinculo, String enquadramento, String cargaHoraria, String regime, Long idLattes) {
        this.id = id;
        this.instituicao = instituicao;
        this.sigla = sigla;
        this.anoEntrada = anoEntrada;
        this.anoSaida = anoSaida;
        this.vinculo = vinculo;
        this.enquadramento = enquadramento;
        this.cargaHoraria = cargaHoraria;
        this.regime = regime;
        this.idLattes = idLattes;
    }

    public String getInstituicao() {
        return instituicao;
    }

    public void setInstituicao(String instituicao) {
        this.instituicao = instituicao;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getAnoEntrada() {
        return anoEntrada;
    }

    public void setAnoEntrada(String anoEntrada) {
        this.anoEntrada = anoEntrada;
    }

    public String getAnoSaida() {
        return anoSaida;
    }

    public void setAnoSaida(String anoSaida) {
        this.anoSaida = anoSaida;
    }

    public String getVinculo() {
        return vinculo;
    }

    public void setVinculo(String vinculo) {
        this.vinculo = vinculo;
    }

    public String getEnquadramento() {
        return enquadramento;
    }

    public void setEnquadramento(String enquadramento) {
        this.enquadramento = enquadramento;
    }

    public String getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(String cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public String getRegime() {
        return regime;
    }

    public void setRegime(String regime) {
        this.regime = regime;
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
        final AtuacaoProfissional other = (AtuacaoProfissional) obj;
        if (this.idLattes != other.idLattes && (this.idLattes == null || !this.idLattes.equals(other.idLattes))) {
            return false;
        }
        return true;
    }

}
