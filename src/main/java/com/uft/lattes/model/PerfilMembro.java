/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uft.lattes.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Adelmir
 */
public class PerfilMembro implements Comparator<PerfilMembro> {

    private Long idLattes;
    private String nome;
    private TipoAtributos atributo;
    private int frequencia;
    private String url;
    private HashMap<String, Integer> atributos = new HashMap<String, Integer>();

    //public List<PerfilMembro> listaPie = new ArrayList<PerfilMembro>();

    public PerfilMembro() {
        this.atributo = new TipoAtributos();
    }

    public PerfilMembro(Long idLattes, String nome, TipoAtributos atributo, int frequencia, String url) {
        this.idLattes = idLattes;
        this.nome = nome;
        this.atributo = atributo;
        this.frequencia = frequencia;
        this.url = url;
    }

    public boolean verificaIgualdade(Membro membro, List<PerfilMembro> perfilMembros) {
        for (int j = 0; j < perfilMembros.size(); j++) {
            if (membro.getIdLattes().equals(perfilMembros.get(j).getIdLattes())) {
                return true;
            }
        }
        return false;
    }

    public List<PerfilMembro> incrementaFrequencia(Membro membro, List<PerfilMembro> perfilMembros, String tipoAtributo) {
        for (int j = 0; j < perfilMembros.size(); j++) {
            if (membro.getIdLattes().equals(perfilMembros.get(j).getIdLattes())) {
                perfilMembros.get(j).setFrequencia(perfilMembros.get(j).getFrequencia() + 1);
                if(perfilMembros.get(j).getAtributos().containsKey(tipoAtributo)){
                    perfilMembros.get(j).getAtributos().put(tipoAtributo, perfilMembros.get(j).getAtributos().get(tipoAtributo) + 1);
                }else{
                    perfilMembros.get(j).getAtributos().put(tipoAtributo, 1);
                }
                if (tipoAtributo.equals("AreasAtuacao")) {
                    perfilMembros.get(j).getAtributo().setAreasAtuacao(true);
                    perfilMembros.get(j).getAtributo().setFrequenciaAreasAtuacao(perfilMembros.get(j).getAtributo().getFrequenciaAreasAtuacao() + 1);
                }
                if (tipoAtributo.equals("ArtigoAceito")) {
                    perfilMembros.get(j).getAtributo().setArtigoAceito(true);
                    perfilMembros.get(j).getAtributo().setFrequenciaArtigoAceito(perfilMembros.get(j).getAtributo().getFrequenciaArtigoAceito() + 1);
                }
                if (tipoAtributo.equals("ArtigoPeriodico")) {
                    perfilMembros.get(j).getAtributo().setArtigoPeriodico(true);
                    perfilMembros.get(j).getAtributo().setFrequenciaArtigoPeriodico(perfilMembros.get(j).getAtributo().getFrequenciaArtigoPeriodico() + 1);
                }
                if (tipoAtributo.equals("CapituloLivroPublicado")) {
                    perfilMembros.get(j).getAtributo().setCapituloLivroPublicado(true);
                    perfilMembros.get(j).getAtributo().setFrequenciaCapituloLivroPublicado(perfilMembros.get(j).getAtributo().getFrequenciaCapituloLivroPublicado() + 1);
                }
                if (tipoAtributo.equals("FormacaoAcademica")) {
                    perfilMembros.get(j).getAtributo().setFormacaoAcademica(true);
                    perfilMembros.get(j).getAtributo().setFrequenciaFormacaoAcademica(perfilMembros.get(j).getAtributo().getFrequenciaFormacaoAcademica() + 1);
                }
                if (tipoAtributo.equals("Idioma")) {
                    perfilMembros.get(j).getAtributo().setIdioma(true);
                    perfilMembros.get(j).getAtributo().setFrequenciaIdioma(perfilMembros.get(j).getAtributo().getFrequenciaIdioma() + 1);
                }
                if (tipoAtributo.equals("LivroPublicado")) {
                    perfilMembros.get(j).getAtributo().setLivroPublicado(true);
                    perfilMembros.get(j).getAtributo().setFrequenciaLivroPublicado(perfilMembros.get(j).getAtributo().getFrequenciaLivroPublicado() + 1);
                }
                if (tipoAtributo.equals("OrganizacaoEvento")) {
                    perfilMembros.get(j).getAtributo().setOrganizacaoEvento(true);
                    perfilMembros.get(j).getAtributo().setFrequenciaOrganizacaoEvento(perfilMembros.get(j).getAtributo().getFrequenciaOrganizacaoEvento() + 1);
                }
                if (tipoAtributo.equals("OrientacaoAndamento")) {
                    perfilMembros.get(j).getAtributo().setOrientacaoAndamento(true);
                    perfilMembros.get(j).getAtributo().setFrequenciaOrientacaoAndamento(perfilMembros.get(j).getAtributo().getFrequenciaOrientacaoAndamento() + 1);
                }
                if (tipoAtributo.equals("OrientacaoConcluidas")) {
                    perfilMembros.get(j).getAtributo().setOrientacaoConcluidas(true);
                    perfilMembros.get(j).getAtributo().setFrequenciaOrientacaoConcluidas(perfilMembros.get(j).getAtributo().getFrequenciaOrientacaoConcluidas() + 1);
                }
                if (tipoAtributo.equals("OutroTipoBibliograficos")) {
                    perfilMembros.get(j).getAtributo().setOutroTiposBibliograficos(true);
                    perfilMembros.get(j).getAtributo().setFrequenciaOutroTiposBibliograficos(perfilMembros.get(j).getAtributo().getFrequenciaOutroTiposBibliograficos() + 1);
                }
                if (tipoAtributo.equals("OutroTipoTecnico")) {
                    perfilMembros.get(j).getAtributo().setOutroTipoTecnico(true);
                    perfilMembros.get(j).getAtributo().setFrequenciaOutroTipoTecnico(perfilMembros.get(j).getAtributo().getFrequenciaOutroTipoTecnico() + 1);
                }
                if (tipoAtributo.equals("ParticipacaoEvento")) {
                    perfilMembros.get(j).getAtributo().setParticipacaoEvento(true);
                    perfilMembros.get(j).getAtributo().setFrequenciaParticipacaoEvento(perfilMembros.get(j).getAtributo().getFrequenciaParticipacaoEvento() + 1);
                }
                if (tipoAtributo.equals("PremioTitulo")) {
                    perfilMembros.get(j).getAtributo().setPremioTitulo(true);
                    perfilMembros.get(j).getAtributo().setFrequenciaPremioTitulo(perfilMembros.get(j).getAtributo().getFrequenciaPremioTitulo() + 1);
                }
                if (tipoAtributo.equals("ProcessoTecnica")) {
                    perfilMembros.get(j).getAtributo().setProcessoTecnica(true);
                    perfilMembros.get(j).getAtributo().setFrequenciaProcessoTecnica(perfilMembros.get(j).getAtributo().getFrequenciaProcessoTecnica() + 1);
                }
                if (tipoAtributo.equals("ProducaoArtistica")) {
                    perfilMembros.get(j).getAtributo().setProducaoArtistica(true);
                    perfilMembros.get(j).getAtributo().setFrequenciaProducaoArtistica(perfilMembros.get(j).getAtributo().getFrequenciaProducaoArtistica() + 1);
                }
                if (tipoAtributo.equals("ProdutoTecnologico")) {
                    perfilMembros.get(j).getAtributo().setProdutoTecnologico(true);
                    perfilMembros.get(j).getAtributo().setFrequenciaProdutoTecnologico(perfilMembros.get(j).getAtributo().getFrequenciaProdutoTecnologico() + 1);
                }
                if (tipoAtributo.equals("ProjetoPesquisa")) {
                    perfilMembros.get(j).getAtributo().setProjetoPesquisa(true);
                    perfilMembros.get(j).getAtributo().setFrequenciaProjetoPesquisa(perfilMembros.get(j).getAtributo().getFrequenciaProjetoPesquisa() + 1);
                }
                if (tipoAtributo.equals("ResumoCongresso")) {
                    perfilMembros.get(j).getAtributo().setResumoCongresso(true);
                    perfilMembros.get(j).getAtributo().setFrequenciaResumoCongresso(perfilMembros.get(j).getAtributo().getFrequenciaResumoCongresso() + 1);
                }
                if (tipoAtributo.equals("ResumoExpandidoCongresso")) {
                    perfilMembros.get(j).getAtributo().setResumoExpandidoCongresso(true);
                    perfilMembros.get(j).getAtributo().setFrequenciaResumoExpandidoCongresso(perfilMembros.get(j).getAtributo().getFrequenciaResumoExpandidoCongresso() + 1);
                }
                if (tipoAtributo.equals("SoftwareComPatente")) {
                    perfilMembros.get(j).getAtributo().setSoftwareComPatente(true);
                    perfilMembros.get(j).getAtributo().setFrequenciaSoftwareComPatente(perfilMembros.get(j).getAtributo().getFrequenciaSoftwareComPatente() + 1);
                }
                if (tipoAtributo.equals("SoftwareSemPatente")) {
                    perfilMembros.get(j).getAtributo().setSoftwareSemPatente(true);
                    perfilMembros.get(j).getAtributo().setFrequenciaSoftwareSemPatente(perfilMembros.get(j).getAtributo().getFrequenciaSoftwareSemPatente() + 1);
                }
                if (tipoAtributo.equals("TextoJornalNoticia")) {
                    perfilMembros.get(j).getAtributo().setTextoJornalNoticia(true);
                    perfilMembros.get(j).getAtributo().setFrequenciaTextoJornalNoticia(perfilMembros.get(j).getAtributo().getFrequenciaTextoJornalNoticia() + 1);
                }
                if (tipoAtributo.equals("TrabalhoCompletoCongresso")) {
                    perfilMembros.get(j).getAtributo().setTrabalhoCompletoCongresso(true);
                    perfilMembros.get(j).getAtributo().setFrequenciaTrabalhoCompletoCongresso(perfilMembros.get(j).getAtributo().getFrequenciaTrabalhoCompletoCongresso() + 1);
                }
                if (tipoAtributo.equals("TrabalhoTecnico")) {
                    perfilMembros.get(j).getAtributo().setTrabalhoTecnico(true);
                    perfilMembros.get(j).getAtributo().setFrequenciaTrabalhoTecnico(perfilMembros.get(j).getAtributo().getFrequenciaTrabalhoTecnico() + 1);
                }
                if (tipoAtributo.equals("AreasAtuacao")) {
                    perfilMembros.get(j).getAtributo().setAreasAtuacao(true);
                    perfilMembros.get(j).getAtributo().setFrequenciaAreasAtuacao(perfilMembros.get(j).getAtributo().getFrequenciaAreasAtuacao() + 1);
                }
                //System.err.println(" Nome: " + perfilMembros.get(j).getNome() + " Freq: " + perfilMembros.get(j).getFrequencia() + "Frequencia AT : " + perfilMembros.get(j).getAtributo().getFrequenciaApresentacaoTrabalho());
//                System.err.println(" Nome: " + perfilMembros.get(j).getNome() + " Freq: " + perfilMembros.get(j).getFrequencia() + "Frequencia AT: " + perfilMembros.get(j).getAtributo().getFrequenciaLivroPublicado());
//                return perfilMembros;
            }

        }
//        listaPie = perfilMembros;
        return perfilMembros;
    }

    public TipoAtributos getAtributo() {
        return atributo;
    }

    public void setAtributo(TipoAtributos atributo) {
        this.atributo = atributo;
    }

    public int getFrequencia() {
        return frequencia;
    }

    public void setFrequencia(int frequencia) {
        this.frequencia = frequencia;
    }

    public Long getIdLattes() {
        return idLattes;
    }

    public void setIdLattes(Long idLattes) {
        this.idLattes = idLattes;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public HashMap<String, Integer> getAtributos() {
        return atributos;
    }

    public void setAtributos(HashMap<String, Integer> atributos) {
        this.atributos = atributos;
    }
        

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PerfilMembro other = (PerfilMembro) obj;
        if (this.idLattes != other.idLattes) {
            return false;
        }
        return true;
    }

    @Override
    public int compare(PerfilMembro o1, PerfilMembro o2) {
        if (o1.getFrequencia() < o2.getFrequencia()) {
            return 1;
        }
        if (o1.getFrequencia() > o2.getFrequencia()) {
            return -1;
        } else {
            return 0;
        }

    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + (this.idLattes != null ? this.idLattes.hashCode() : 0);
        return hash;
    }
}
