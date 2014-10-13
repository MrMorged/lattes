/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uft.lattes.controller;

import br.edu.uft.graph.Edger;
import br.edu.uft.graph.Node;
import com.google.gson.Gson;
import com.uft.lattes.model.*;
import com.uft.lattes.repository.ApresentacaoTrabalhoRepository;
import com.uft.lattes.repository.AreasAtuacaoRepository;
import com.uft.lattes.repository.ArtigoAceitoRepository;
import com.uft.lattes.repository.ArtigoPeriodicoRepository;
import com.uft.lattes.repository.CapituloLivroPublicadoRepository;
import com.uft.lattes.repository.FormacaoAcademicaRepository;
import com.uft.lattes.repository.LivroPublicadoRepository;
import com.uft.lattes.repository.MembroRepository;
import com.uft.lattes.repository.OrganizacaoEventoRepository;
import com.uft.lattes.repository.OrientacoesAndamentoRepository;
import com.uft.lattes.repository.OrientacoesConcluidasRepository;
import com.uft.lattes.repository.OutroTipoBibliograficoRepository;
import com.uft.lattes.repository.OutroTipoTecnicoRepository;
import com.uft.lattes.repository.ParticipacaoEventoRepository;
import com.uft.lattes.repository.ProcessoTecnicaRepository;
import com.uft.lattes.repository.ProducaoArtisticaRepository;
import com.uft.lattes.repository.ProdutoTecnologicoRepository;
import com.uft.lattes.repository.ProjetoPesquisaRepository;
import com.uft.lattes.repository.ResumoCongressoRepository;
import com.uft.lattes.repository.ResumoExpandidoCongressoRepository;
import com.uft.lattes.repository.SoftwareComPatenteRepository;
import com.uft.lattes.repository.SoftwareSemPatenteRepository;
import com.uft.lattes.repository.TextoJornalNoticiaRepository;
import com.uft.lattes.repository.TrabalhoCompletoCongressoRepository;
import com.uft.lattes.repository.TrabalhoTecnicoRepository;
import com.uft.lattes.util.ApplicationUtilies;
import com.uft.lattes.util.GenericRepository;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.infinispan.config.Configuration;
import org.infinispan.manager.CacheManager;
import org.infinispan.manager.DefaultCacheManager;
import org.primefaces.context.RequestContext;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

/**
 *
 * @author Adelmir
 */
@Named
@ViewScoped
public class IndexingController implements Serializable {

//    Configuration c = new Configuration()
//            .fluent()
//            .indexing()
//            .addProperty(
//                    "hibernate.search.option", "valueForOption")
//            .build();
//    CacheManager manager = new DefaultCacheManager(c);

    private String palavra;
    private String auxPalavra;
    private List<Membro> resultadoBusca = new ArrayList();
    private List<Membro> resultadoBuscaAuxiliar = new ArrayList();
    private List<Membro> auxResultadoBusca = new ArrayList();
    private List<PerfilMembro> perfilMembroEdital = new ArrayList();

    HashMap<String, HashMap<String, List<PerfilMembro>>> resultadoProvenance = new HashMap<String, HashMap<String, List<PerfilMembro>>>();

    TreeNode root = null;
    boolean extend = false;
    TreeNode selectedNode;
    private PerfilMembro membroSelected;

    public IndexingController() {
    }

    public void plotarGrafico(PerfilMembro m) {
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("drawChart('" + m.getIdLattes() + "','"
                + new Gson().toJson(m, PerfilMembro.class) + "')");
    }

//    // recebe uma array json e retorna
//    public static ArrayList<String> campoBusca(JSONArray palavras) throws JSONException {
//
//        //string com informação representada em JSON 
//        String aux = "";
//        
//        ArrayList<String> list = new ArrayList<String>();
//        JSONArray jsonArray = new JSONArray(palavras);
//        
//        return (list);
//    }
//    public void initBusca(String texto) {
//        List<String> lista;
//
//        QuebraTextoSeparado a = new QuebraTextoSeparado();
//        lista = a.QuebraTextoSeparado(texto);
//
//        for (int i = 0; i < lista.size(); i++) {
//            resultadoPesquisa();
//        }
//    }
    public void createIndexes() throws InterruptedException {
        /*
         * Indexando os dados chave
         */

        // Apresentacao Trabalho
        new ApresentacaoTrabalhoRepository(ApplicationUtilies.catchEntityManager()).index();
        // Areas Atuacao
        new AreasAtuacaoRepository(ApplicationUtilies.catchEntityManager()).index();
//        // Artigo Aceito
        new ArtigoAceitoRepository(ApplicationUtilies.catchEntityManager()).index();
//        // Artigo Periodico
        new ArtigoPeriodicoRepository(ApplicationUtilies.catchEntityManager()).index();
//        //Capitulo Livro Publicado
        new CapituloLivroPublicadoRepository(ApplicationUtilies.catchEntityManager()).index();
//        // Formacao Academica
        new FormacaoAcademicaRepository(ApplicationUtilies.catchEntityManager()).index();
//        // Livro Publicado
        new LivroPublicadoRepository(ApplicationUtilies.catchEntityManager()).index();
//        // Membro
        new MembroRepository(ApplicationUtilies.catchEntityManager()).index();
//        // Organizacao Evento
        new OrganizacaoEventoRepository(ApplicationUtilies.catchEntityManager()).index();
//        // Orientacoes Andamento
        new OrientacoesAndamentoRepository(ApplicationUtilies.catchEntityManager()).index();
//        // Orientacoes Concluidas
        new OrientacoesConcluidasRepository(ApplicationUtilies.catchEntityManager()).index();
//        // Outro Tipo Bibliografico
        new OutroTipoBibliograficoRepository(ApplicationUtilies.catchEntityManager()).index();
//        // Outro Tipo Tecnico
        new OutroTipoTecnicoRepository(ApplicationUtilies.catchEntityManager()).index();
//        // Participacao Evento
        new ParticipacaoEventoRepository(ApplicationUtilies.catchEntityManager()).index();
//        // Processo Tecnica
        new ProcessoTecnicaRepository(ApplicationUtilies.catchEntityManager()).index();
//        // Producao Artistica
        new ProducaoArtisticaRepository(ApplicationUtilies.catchEntityManager()).index();
//        // Produto Tecnologico
        new ProdutoTecnologicoRepository(ApplicationUtilies.catchEntityManager()).index();
//        // Projeto Pesquisa
        new ProjetoPesquisaRepository(ApplicationUtilies.catchEntityManager()).index();
//        // Resumo Congresso
        new ResumoCongressoRepository(ApplicationUtilies.catchEntityManager()).index();
//        // Resumo Expandido Congresso
        new ResumoExpandidoCongressoRepository(ApplicationUtilies.catchEntityManager()).index();
//        // Software Com Patente
        new SoftwareComPatenteRepository(ApplicationUtilies.catchEntityManager()).index();
//        // Software Sem Patente
        new SoftwareSemPatenteRepository(ApplicationUtilies.catchEntityManager()).index();
//        // Texto Jornal Noticia
        new TextoJornalNoticiaRepository(ApplicationUtilies.catchEntityManager()).index();
//        // Trabalho Completo Congresso
        new TrabalhoCompletoCongressoRepository(ApplicationUtilies.catchEntityManager()).index();
//        // Trabalho Tecnico
        new TrabalhoTecnicoRepository(ApplicationUtilies.catchEntityManager()).index();
    }

    public List<PerfilMembro> resultadoPesquisa() {
        resultadoBusca.clear();
        auxResultadoBusca.clear();
        Node node = new Node();
        Edger edger = new Edger();

        int i;
        if (this.palavra != null) {
            String[] split = palavra.split(";");
            for (String word : split) {

                this.perfilMembroEdital.clear();
                resultadoBusca.clear();
                auxResultadoBusca.clear();
//            if (this.resultadoBusca.size() > 0) {
//                if (this.palavra.equals(this.auxPalavra)) {
//                    this.palavra = null;
//                    this.auxPalavra = null;
//                }
//                this.resultadoBusca.clear();
//                return this.resultadoBusca;
//            }
//            this.auxPalavra = this.palavra;
            /*
                 * buscando lista de ids dos membros
                 */
                // Apresentacao Trabalho
                List<ApresentacaoTrabalho> auxApresentacaoTrabalho = new ApresentacaoTrabalhoRepository(ApplicationUtilies.catchEntityManager()).buscaInformacao(this.palavra);
                //Areas Atuacao
                List<AreasAtuacao> auxAreasAtuacao = new AreasAtuacaoRepository(ApplicationUtilies.catchEntityManager()).buscaInformacao(this.palavra);
                //Artigo Aceito
                List<ArtigoAceito> auxArtigoAceito = new ArtigoAceitoRepository(ApplicationUtilies.catchEntityManager()).buscaInformacao(this.palavra);
                //Artigo Periodico
                List<ArtigoPeriodico> auxArtigoPeriodico = new ArtigoPeriodicoRepository(ApplicationUtilies.catchEntityManager()).buscaInformacao(this.palavra);
                //Capitulo Livro Publicado
                List<CapituloLivroPublicado> auxCapituloLivroPublicado = new CapituloLivroPublicadoRepository(ApplicationUtilies.catchEntityManager()).buscaInformacao(this.palavra);
                //Formacao Academica
                List<FormacaoAcademica> auxFormacaoAcademica = new FormacaoAcademicaRepository(ApplicationUtilies.catchEntityManager()).buscaInformacao(this.palavra);
                //Livro Publicado
                List<LivroPublicado> auxLivroPublicado = new LivroPublicadoRepository(ApplicationUtilies.catchEntityManager()).buscaInformacao(this.palavra);
                //Membro
                List<Membro> auxMembro = new MembroRepository(ApplicationUtilies.catchEntityManager()).buscaInformacao(this.palavra);
                //Organizacao Evento
                List<OrganizacaoEvento> auxOrganizacaoEvento = new OrganizacaoEventoRepository(ApplicationUtilies.catchEntityManager()).buscaInformacao(this.palavra);
                //Orientacoes Andamento
                List<OrientacoesAndamento> auxOrientacoesAndamento = new OrientacoesAndamentoRepository(ApplicationUtilies.catchEntityManager()).buscaInformacao(this.palavra);
                //Orientacoes Concluidas
                List<OrientacoesConcluidas> auxOrientacoesConcluidas = new OrientacoesConcluidasRepository(ApplicationUtilies.catchEntityManager()).buscaInformacao(this.palavra);
                //Outro Tipo Bibliografico
                List<OutroTipoBibliografico> auxOutroTipoBibliografico = new OutroTipoBibliograficoRepository(ApplicationUtilies.catchEntityManager()).buscaInformacao(this.palavra);
                //Outro Tipo Tecnico
                List<OutroTipoTecnico> auxOutroTipoTecnico = new OutroTipoTecnicoRepository(ApplicationUtilies.catchEntityManager()).buscaInformacao(this.palavra);
                //Participacao Evento
                List<ParticipacaoEvento> auxParticipacaoEvento = new ParticipacaoEventoRepository(ApplicationUtilies.catchEntityManager()).buscaInformacao(this.palavra);
                //Processo Tecnica
                List<ProcessoTecnica> auxProcessoTecnica = new ProcessoTecnicaRepository(ApplicationUtilies.catchEntityManager()).buscaInformacao(this.palavra);
                //Producao Artistica
                List<ProducaoArtistica> auxProducaoArtistica = new ProducaoArtisticaRepository(ApplicationUtilies.catchEntityManager()).buscaInformacao(this.palavra);
                //Produto Tecnologico
                List<ProdutoTecnologico> auxProdutoTecnologico = new ProdutoTecnologicoRepository(ApplicationUtilies.catchEntityManager()).buscaInformacao(this.palavra);
                //Projeto Pesquisa
                List<ProjetoPesquisa> auxProjetoPesquisa = new ProjetoPesquisaRepository(ApplicationUtilies.catchEntityManager()).buscaInformacao(this.palavra);
                //Resumo Congresso
                List<ResumoCongresso> auxResumoCongresso = new ResumoCongressoRepository(ApplicationUtilies.catchEntityManager()).buscaInformacao(this.palavra);
                //Resumo Congresso
                List<ResumoExpandidoCongresso> auxResumoExpandidoCongresso = new ResumoExpandidoCongressoRepository(ApplicationUtilies.catchEntityManager()).buscaInformacao(this.palavra);
                //Software Com Patente
                List<SoftwareComPatente> auxSoftwareComPatente = new SoftwareComPatenteRepository(ApplicationUtilies.catchEntityManager()).buscaInformacao(this.palavra);
                //Software Sem Patente
                List<SoftwareSemPatente> auxSoftwareSemPatente = new SoftwareSemPatenteRepository(ApplicationUtilies.catchEntityManager()).buscaInformacao(this.palavra);
                //Texto Jornal Noticia
                List<TextoJornalNoticia> auxTextoJornalNoticia = new TextoJornalNoticiaRepository(ApplicationUtilies.catchEntityManager()).buscaInformacao(this.palavra);
                //Trabalho Completo Congresso
                List<TrabalhoCompletoCongresso> auxTrabalhoCompletoCongresso = new TrabalhoCompletoCongressoRepository(ApplicationUtilies.catchEntityManager()).buscaInformacao(this.palavra);
                //Trabalho Tecnico
                List<TrabalhoTecnico> auxTrabalhoTecnico = new TrabalhoTecnicoRepository(ApplicationUtilies.catchEntityManager()).buscaInformacao(this.palavra);

                /*
                 * buscando lista com os nomes dos membros pelo id
                 */
                PerfilMembro auxPerfil = new PerfilMembro();
                // Apresentacao Trabalho
                if (auxApresentacaoTrabalho != null) {
                    auxResultadoBusca = new ApresentacaoTrabalhoRepository(ApplicationUtilies.catchEntityManager()).BuscaMembroPorId(auxApresentacaoTrabalho);
                    for (i = 0; i < auxResultadoBusca.size(); i++) {
                        this.resultadoBusca.add(auxResultadoBusca.get(i));
                        if (auxPerfil.verificaIgualdade(auxResultadoBusca.get(i), this.perfilMembroEdital)) {
                            this.perfilMembroEdital = auxPerfil.incrementaFrequencia(auxResultadoBusca.get(i), this.perfilMembroEdital, "ApresentacaoTrabalho");
                        } else {
                            PerfilMembro auxPerfilMembro = new PerfilMembro();
                            auxPerfilMembro.setIdLattes(auxResultadoBusca.get(i).getIdLattes());
                            auxPerfilMembro.setNome(auxResultadoBusca.get(i).getNome());
                            auxPerfilMembro.setFrequencia(auxPerfilMembro.getFrequencia() + 1);
                            auxPerfilMembro.getAtributo().setApresentacaoTrabalho(true);
                            auxPerfilMembro.getAtributo().setFrequenciaApresentacaoTrabalho(auxPerfilMembro.getAtributo().getFrequenciaApresentacaoTrabalho() + 1);
                            this.perfilMembroEdital.add(auxPerfilMembro);
                        }
                    }
                    auxResultadoBusca.clear();
                }
                // Areas Atuacao 
                if (auxAreasAtuacao != null) {
                    auxResultadoBusca = new AreasAtuacaoRepository(ApplicationUtilies.catchEntityManager()).BuscaMembroPorId(auxAreasAtuacao);
                    for (i = 0; i < auxResultadoBusca.size(); i++) {
                        this.resultadoBusca.add(auxResultadoBusca.get(i));
                        if (auxPerfil.verificaIgualdade(auxResultadoBusca.get(i), this.perfilMembroEdital)) {
                            this.perfilMembroEdital = auxPerfil.incrementaFrequencia(auxResultadoBusca.get(i), this.perfilMembroEdital, "AreasAtuacao");
                        } else {
                            PerfilMembro auxPerfilMembro = new PerfilMembro();
                            auxPerfilMembro.setIdLattes(auxResultadoBusca.get(i).getIdLattes());
                            auxPerfilMembro.setNome(auxResultadoBusca.get(i).getNome());
                            auxPerfilMembro.setFrequencia(auxPerfilMembro.getFrequencia() + 1);
                            auxPerfilMembro.getAtributo().setAreasAtuacao(true);
                            auxPerfilMembro.getAtributo().setFrequenciaAreasAtuacao(auxPerfilMembro.getAtributo().getFrequenciaAreasAtuacao() + 1);
                            this.perfilMembroEdital.add(auxPerfilMembro);
                        }

                    }
                    auxResultadoBusca.clear();
                }
                // Artigo Aceito 
                if (auxArtigoAceito != null) {
                    auxResultadoBusca = new ArtigoAceitoRepository(ApplicationUtilies.catchEntityManager()).BuscaMembroPorId(auxArtigoAceito);
                    for (i = 0; i < auxResultadoBusca.size(); i++) {
                        this.resultadoBusca.add(auxResultadoBusca.get(i));
                        if (auxPerfil.verificaIgualdade(auxResultadoBusca.get(i), this.perfilMembroEdital)) {
                            this.perfilMembroEdital = auxPerfil.incrementaFrequencia(auxResultadoBusca.get(i), this.perfilMembroEdital, "ArtigoAceito");
                        } else {
                            PerfilMembro auxPerfilMembro = new PerfilMembro();
                            auxPerfilMembro.setIdLattes(auxResultadoBusca.get(i).getIdLattes());
                            auxPerfilMembro.setNome(auxResultadoBusca.get(i).getNome());
                            auxPerfilMembro.setFrequencia(auxPerfilMembro.getFrequencia() + 1);
                            auxPerfilMembro.getAtributo().setArtigoAceito(true);
                            auxPerfilMembro.getAtributo().setFrequenciaArtigoAceito(auxPerfilMembro.getAtributo().getFrequenciaArtigoAceito() + 1);
                            this.perfilMembroEdital.add(auxPerfilMembro);
                        }
                    }

                    auxResultadoBusca.clear();
                }
                // Artigo Periodico 
                if (auxArtigoPeriodico != null) {
                    auxResultadoBusca = new ArtigoPeriodicoRepository(ApplicationUtilies.catchEntityManager()).BuscaMembroPorId(auxArtigoPeriodico);
                    for (i = 0; i < auxResultadoBusca.size(); i++) {
                        this.resultadoBusca.add(auxResultadoBusca.get(i));
                        if (auxPerfil.verificaIgualdade(auxResultadoBusca.get(i), this.perfilMembroEdital)) {
                            this.perfilMembroEdital = auxPerfil.incrementaFrequencia(auxResultadoBusca.get(i), this.perfilMembroEdital, "ArtigoPeriodico");
                        } else {
                            PerfilMembro auxPerfilMembro = new PerfilMembro();
                            auxPerfilMembro.setIdLattes(auxResultadoBusca.get(i).getIdLattes());
                            auxPerfilMembro.setNome(auxResultadoBusca.get(i).getNome());
                            auxPerfilMembro.setFrequencia(auxPerfilMembro.getFrequencia() + 1);
                            auxPerfilMembro.getAtributo().setArtigoPeriodico(true);
                            auxPerfilMembro.getAtributo().setFrequenciaArtigoPeriodico(auxPerfilMembro.getAtributo().getFrequenciaArtigoPeriodico() + 1);
                            this.perfilMembroEdital.add(auxPerfilMembro);
                        }
                    }
                    auxResultadoBusca.clear();
                }
                // Capitulo Livro Publicado 
                if (auxCapituloLivroPublicado != null) {
                    auxResultadoBusca = new CapituloLivroPublicadoRepository(ApplicationUtilies.catchEntityManager()).BuscaMembroPorId(auxCapituloLivroPublicado);
                    for (i = 0; i < auxResultadoBusca.size(); i++) {
                        this.resultadoBusca.add(auxResultadoBusca.get(i));
                        if (auxPerfil.verificaIgualdade(auxResultadoBusca.get(i), this.perfilMembroEdital)) {
                            this.perfilMembroEdital = auxPerfil.incrementaFrequencia(auxResultadoBusca.get(i), this.perfilMembroEdital, "CapituloLivroPublicado");
                        } else {
                            PerfilMembro auxPerfilMembro = new PerfilMembro();
                            auxPerfilMembro.setIdLattes(auxResultadoBusca.get(i).getIdLattes());
                            auxPerfilMembro.setNome(auxResultadoBusca.get(i).getNome());
                            auxPerfilMembro.setFrequencia(auxPerfilMembro.getFrequencia() + 1);
                            auxPerfilMembro.getAtributo().setCapituloLivroPublicado(true);
                            auxPerfilMembro.getAtributo().setFrequenciaCapituloLivroPublicado(auxPerfilMembro.getAtributo().getFrequenciaCapituloLivroPublicado() + 1);
                            this.perfilMembroEdital.add(auxPerfilMembro);
                        }
                    }
                    auxResultadoBusca.clear();
                }
                // Formacao Academica
                if (auxFormacaoAcademica != null) {
                    auxResultadoBusca = new FormacaoAcademicaRepository(ApplicationUtilies.catchEntityManager()).BuscaMembroPorId(auxFormacaoAcademica);
                    for (i = 0; i < auxResultadoBusca.size(); i++) {
                        this.resultadoBusca.add(auxResultadoBusca.get(i));
                        if (auxPerfil.verificaIgualdade(auxResultadoBusca.get(i), this.perfilMembroEdital)) {
                            this.perfilMembroEdital = auxPerfil.incrementaFrequencia(auxResultadoBusca.get(i), this.perfilMembroEdital, "FormacaoAcademica");
                        } else {
                            PerfilMembro auxPerfilMembro = new PerfilMembro();
                            auxPerfilMembro.setIdLattes(auxResultadoBusca.get(i).getIdLattes());
                            auxPerfilMembro.setNome(auxResultadoBusca.get(i).getNome());
                            auxPerfilMembro.setFrequencia(auxPerfilMembro.getFrequencia() + 1);
                            auxPerfilMembro.getAtributo().setFormacaoAcademica(true);
                            auxPerfilMembro.getAtributo().setFrequenciaFormacaoAcademica(auxPerfilMembro.getAtributo().getFrequenciaFormacaoAcademica() + 1);
                            this.perfilMembroEdital.add(auxPerfilMembro);
                        }
                    }
                    auxResultadoBusca.clear();
                }
                // Livro Publicado
                if (auxLivroPublicado != null) {
                    auxResultadoBusca = new LivroPublicadoRepository(ApplicationUtilies.catchEntityManager()).BuscaMembroPorId(auxLivroPublicado);
                    for (i = 0; i < auxResultadoBusca.size(); i++) {
                        this.resultadoBusca.add(auxResultadoBusca.get(i));
                        if (auxPerfil.verificaIgualdade(auxResultadoBusca.get(i), this.perfilMembroEdital)) {
                            this.perfilMembroEdital = auxPerfil.incrementaFrequencia(auxResultadoBusca.get(i), this.perfilMembroEdital, "LivroPublicado");
                        } else {
                            PerfilMembro auxPerfilMembro = new PerfilMembro();
                            auxPerfilMembro.setIdLattes(auxResultadoBusca.get(i).getIdLattes());
                            auxPerfilMembro.setNome(auxResultadoBusca.get(i).getNome());
                            auxPerfilMembro.setFrequencia(auxPerfilMembro.getFrequencia() + 1);
                            auxPerfilMembro.getAtributo().setLivroPublicado(true);
                            auxPerfilMembro.getAtributo().setFrequenciaLivroPublicado(auxPerfilMembro.getAtributo().getFrequenciaLivroPublicado() + 1);
                            this.perfilMembroEdital.add(auxPerfilMembro);
                        }
                    }
                    auxResultadoBusca.clear();
                }
                // Membro
//            if (auxMembro != null) {
//                auxResultadoBusca = new MembroRepository(ApplicationUtilies.catchEntityManager()).BuscaMembroPorId(auxMembro);
//                for (i = 0; i < auxResultadoBusca.size(); i++) {
//                    this.resultadoBusca.add(auxResultadoBusca.get(i));
//                    if (auxPerfil.verificaIgualdade(auxResultadoBusca, this.perfilMembroEdital)) {
//                        this.perfilMembroEdital = auxPerfil.incrementaFrequencia(auxResultadoBusca, this.perfilMembroEdital, "Membro");
//                    } else {
//                        PerfilMembro auxPerfilMembro = new PerfilMembro();
//                        auxPerfilMembro.setIdLattes(auxResultadoBusca.get(i).getIdLattes());
//                        auxPerfilMembro.setNome(auxResultadoBusca.get(i).getNome());
//                        auxPerfilMembro.setFrequencia(auxPerfilMembro.getFrequencia() + 1);
//                        auxPerfilMembro.getAtributo().setMembro(true);
//                        this.perfilMembroEdital.add(auxPerfilMembro);
//                    }
//                }
//                auxResultadoBusca.clear();
//            }
                // Organizacao Evento
                if (auxOrganizacaoEvento != null) {
                    auxResultadoBusca = new OrganizacaoEventoRepository(ApplicationUtilies.catchEntityManager()).BuscaMembroPorId(auxOrganizacaoEvento);
                    for (i = 0; i < auxResultadoBusca.size(); i++) {
                        this.resultadoBusca.add(auxResultadoBusca.get(i));
                        if (auxPerfil.verificaIgualdade(auxResultadoBusca.get(i), this.perfilMembroEdital)) {
                            this.perfilMembroEdital = auxPerfil.incrementaFrequencia(auxResultadoBusca.get(i), this.perfilMembroEdital, "OrganizacaoEvento");
                        } else {
                            PerfilMembro auxPerfilMembro = new PerfilMembro();
                            auxPerfilMembro.setIdLattes(auxResultadoBusca.get(i).getIdLattes());
                            auxPerfilMembro.setNome(auxResultadoBusca.get(i).getNome());
                            auxPerfilMembro.setFrequencia(auxPerfilMembro.getFrequencia() + 1);
                            auxPerfilMembro.getAtributo().setOrganizacaoEvento(true);
                            auxPerfilMembro.getAtributo().setFrequenciaOrganizacaoEvento(auxPerfilMembro.getAtributo().getFrequenciaOrganizacaoEvento() + 1);
                            this.perfilMembroEdital.add(auxPerfilMembro);
                        }
                    }
                    auxResultadoBusca.clear();
                }
                // Orientacoes Andamento
                if (auxOrientacoesAndamento != null) {
                    auxResultadoBusca = new OrientacoesAndamentoRepository(ApplicationUtilies.catchEntityManager()).BuscaMembroPorId(auxOrientacoesAndamento);
                    for (i = 0; i < auxResultadoBusca.size(); i++) {
                        this.resultadoBusca.add(auxResultadoBusca.get(i));
                        if (auxPerfil.verificaIgualdade(auxResultadoBusca.get(i), this.perfilMembroEdital)) {
                            this.perfilMembroEdital = auxPerfil.incrementaFrequencia(auxResultadoBusca.get(i), this.perfilMembroEdital, "OrientacoesAndamento");
                        } else {
                            PerfilMembro auxPerfilMembro = new PerfilMembro();
                            auxPerfilMembro.setIdLattes(auxResultadoBusca.get(i).getIdLattes());
                            auxPerfilMembro.setNome(auxResultadoBusca.get(i).getNome());
                            auxPerfilMembro.setFrequencia(auxPerfilMembro.getFrequencia() + 1);
                            auxPerfilMembro.getAtributo().setOrientacaoAndamento(true);
                            auxPerfilMembro.getAtributo().setFrequenciaOrientacaoAndamento(auxPerfilMembro.getAtributo().getFrequenciaOrientacaoAndamento() + 1);
                            this.perfilMembroEdital.add(auxPerfilMembro);
                        }
                    }
                    auxResultadoBusca.clear();
                }
                // Orientacoes Concluidas
                if (auxOrientacoesConcluidas != null) {
                    auxResultadoBusca = new OrientacoesConcluidasRepository(ApplicationUtilies.catchEntityManager()).BuscaMembroPorId(auxOrientacoesConcluidas);
                    for (i = 0; i < auxResultadoBusca.size(); i++) {
                        this.resultadoBusca.add(auxResultadoBusca.get(i));
                        if (auxPerfil.verificaIgualdade(auxResultadoBusca.get(i), this.perfilMembroEdital)) {
                            this.perfilMembroEdital = auxPerfil.incrementaFrequencia(auxResultadoBusca.get(i), this.perfilMembroEdital, "OrientacoesConcluidas");
                        } else {
                            PerfilMembro auxPerfilMembro = new PerfilMembro();
                            auxPerfilMembro.setIdLattes(auxResultadoBusca.get(i).getIdLattes());
                            auxPerfilMembro.setNome(auxResultadoBusca.get(i).getNome());
                            auxPerfilMembro.setFrequencia(auxPerfilMembro.getFrequencia() + 1);
                            auxPerfilMembro.getAtributo().setOrientacaoConcluidas(true);
                            auxPerfilMembro.getAtributo().setFrequenciaOrientacaoConcluidas(auxPerfilMembro.getAtributo().getFrequenciaOrientacaoConcluidas() + 1);
                            this.perfilMembroEdital.add(auxPerfilMembro);
                        }
                    }
                    auxResultadoBusca.clear();
                }
                // Outro Tipo Bibliografico
                if (auxOutroTipoBibliografico != null) {
                    auxResultadoBusca = new OutroTipoBibliograficoRepository(ApplicationUtilies.catchEntityManager()).BuscaMembroPorId(auxOutroTipoBibliografico);
                    for (i = 0; i < auxResultadoBusca.size(); i++) {
                        this.resultadoBusca.add(auxResultadoBusca.get(i));
                        if (auxPerfil.verificaIgualdade(auxResultadoBusca.get(i), this.perfilMembroEdital)) {
                            this.perfilMembroEdital = auxPerfil.incrementaFrequencia(auxResultadoBusca.get(i), this.perfilMembroEdital, "OutroTipoBibliografico");
                        } else {
                            PerfilMembro auxPerfilMembro = new PerfilMembro();
                            auxPerfilMembro.setIdLattes(auxResultadoBusca.get(i).getIdLattes());
                            auxPerfilMembro.setNome(auxResultadoBusca.get(i).getNome());
                            auxPerfilMembro.setFrequencia(auxPerfilMembro.getFrequencia() + 1);
                            auxPerfilMembro.getAtributo().setOutroTiposBibliograficos(true);
                            auxPerfilMembro.getAtributo().setFrequenciaOutroTiposBibliograficos(auxPerfilMembro.getAtributo().getFrequenciaOutroTiposBibliograficos() + 1);
                            this.perfilMembroEdital.add(auxPerfilMembro);
                        }
                    }
                    auxResultadoBusca.clear();
                }
                // Outro Tipo Tecnico
                if (auxOutroTipoTecnico != null) {
                    auxResultadoBusca = new OutroTipoTecnicoRepository(ApplicationUtilies.catchEntityManager()).BuscaMembroPorId(auxOutroTipoTecnico);
                    for (i = 0; i < auxResultadoBusca.size(); i++) {
                        this.resultadoBusca.add(auxResultadoBusca.get(i));
                        if (auxPerfil.verificaIgualdade(auxResultadoBusca.get(i), this.perfilMembroEdital)) {
                            this.perfilMembroEdital = auxPerfil.incrementaFrequencia(auxResultadoBusca.get(i), this.perfilMembroEdital, "OutroTipoTecnico");
                        } else {
                            PerfilMembro auxPerfilMembro = new PerfilMembro();
                            auxPerfilMembro.setIdLattes(auxResultadoBusca.get(i).getIdLattes());
                            auxPerfilMembro.setNome(auxResultadoBusca.get(i).getNome());
                            auxPerfilMembro.setFrequencia(auxPerfilMembro.getFrequencia() + 1);
                            auxPerfilMembro.getAtributo().setOutroTipoTecnico(true);
                            auxPerfilMembro.getAtributo().setFrequenciaOutroTipoTecnico(auxPerfilMembro.getAtributo().getFrequenciaOutroTipoTecnico() + 1);
                            this.perfilMembroEdital.add(auxPerfilMembro);
                        }
                    }
                    auxResultadoBusca.clear();
                }
                // Participacao Evento
                if (auxParticipacaoEvento != null) {
                    auxResultadoBusca = new ParticipacaoEventoRepository(ApplicationUtilies.catchEntityManager()).BuscaMembroPorId(auxParticipacaoEvento);
                    for (i = 0; i < auxResultadoBusca.size(); i++) {
                        this.resultadoBusca.add(auxResultadoBusca.get(i));
                        if (auxPerfil.verificaIgualdade(auxResultadoBusca.get(i), this.perfilMembroEdital)) {
                            this.perfilMembroEdital = auxPerfil.incrementaFrequencia(auxResultadoBusca.get(i), this.perfilMembroEdital, "ParticipacaoEvento");
                        } else {
                            PerfilMembro auxPerfilMembro = new PerfilMembro();
                            auxPerfilMembro.setIdLattes(auxResultadoBusca.get(i).getIdLattes());
                            auxPerfilMembro.setNome(auxResultadoBusca.get(i).getNome());
                            auxPerfilMembro.setFrequencia(auxPerfilMembro.getFrequencia() + 1);
                            auxPerfilMembro.getAtributo().setParticipacaoEvento(true);
                            auxPerfilMembro.getAtributo().setFrequenciaParticipacaoEvento(auxPerfilMembro.getAtributo().getFrequenciaParticipacaoEvento() + 1);
                            this.perfilMembroEdital.add(auxPerfilMembro);
                        }
                    }
                    auxResultadoBusca.clear();
                }
                // Processo Tecnica
                if (auxProcessoTecnica != null) {
                    auxResultadoBusca = new ProcessoTecnicaRepository(ApplicationUtilies.catchEntityManager()).BuscaMembroPorId(auxProcessoTecnica);
                    for (i = 0; i < auxResultadoBusca.size(); i++) {
                        this.resultadoBusca.add(auxResultadoBusca.get(i));
                        if (auxPerfil.verificaIgualdade(auxResultadoBusca.get(i), this.perfilMembroEdital)) {
                            this.perfilMembroEdital = auxPerfil.incrementaFrequencia(auxResultadoBusca.get(i), this.perfilMembroEdital, "ProcessoTecnica");
                        } else {
                            PerfilMembro auxPerfilMembro = new PerfilMembro();
                            auxPerfilMembro.setIdLattes(auxResultadoBusca.get(i).getIdLattes());
                            auxPerfilMembro.setNome(auxResultadoBusca.get(i).getNome());
                            auxPerfilMembro.setFrequencia(auxPerfilMembro.getFrequencia() + 1);
                            auxPerfilMembro.getAtributo().setProcessoTecnica(true);
                            auxPerfilMembro.getAtributo().setFrequenciaProcessoTecnica(auxPerfilMembro.getAtributo().getFrequenciaProcessoTecnica() + 1);
                            this.perfilMembroEdital.add(auxPerfilMembro);
                        }
                    }
                    auxResultadoBusca.clear();
                }
                // Producao Artistica
                if (auxProducaoArtistica != null) {
                    auxResultadoBusca = new ProducaoArtisticaRepository(ApplicationUtilies.catchEntityManager()).BuscaMembroPorId(auxProducaoArtistica);
                    for (i = 0; i < auxResultadoBusca.size(); i++) {
                        this.resultadoBusca.add(auxResultadoBusca.get(i));
                        if (auxPerfil.verificaIgualdade(auxResultadoBusca.get(i), this.perfilMembroEdital)) {
                            this.perfilMembroEdital = auxPerfil.incrementaFrequencia(auxResultadoBusca.get(i), this.perfilMembroEdital, "ProducaoArtistica");
                        } else {
                            PerfilMembro auxPerfilMembro = new PerfilMembro();
                            auxPerfilMembro.setIdLattes(auxResultadoBusca.get(i).getIdLattes());
                            auxPerfilMembro.setNome(auxResultadoBusca.get(i).getNome());
                            auxPerfilMembro.setFrequencia(auxPerfilMembro.getFrequencia() + 1);
                            auxPerfilMembro.getAtributo().setProducaoArtistica(true);
                            auxPerfilMembro.getAtributo().setFrequenciaProducaoArtistica(auxPerfilMembro.getAtributo().getFrequenciaProducaoArtistica() + 1);
                            this.perfilMembroEdital.add(auxPerfilMembro);
                        }
                    }
                    auxResultadoBusca.clear();
                }
                // Produto Tecnologico
                if (auxProdutoTecnologico != null) {
                    auxResultadoBusca = new ProdutoTecnologicoRepository(ApplicationUtilies.catchEntityManager()).BuscaMembroPorId(auxProdutoTecnologico);
                    for (i = 0; i < auxResultadoBusca.size(); i++) {
                        this.resultadoBusca.add(auxResultadoBusca.get(i));
                        if (auxPerfil.verificaIgualdade(auxResultadoBusca.get(i), this.perfilMembroEdital)) {
                            this.perfilMembroEdital = auxPerfil.incrementaFrequencia(auxResultadoBusca.get(i), this.perfilMembroEdital, "ProdutoTecnologico");
                        } else {
                            PerfilMembro auxPerfilMembro = new PerfilMembro();
                            auxPerfilMembro.setIdLattes(auxResultadoBusca.get(i).getIdLattes());
                            auxPerfilMembro.setNome(auxResultadoBusca.get(i).getNome());
                            auxPerfilMembro.setFrequencia(auxPerfilMembro.getFrequencia() + 1);
                            auxPerfilMembro.getAtributo().setProdutoTecnologico(true);
                            auxPerfilMembro.getAtributo().setFrequenciaProdutoTecnologico(auxPerfilMembro.getAtributo().getFrequenciaProdutoTecnologico() + 1);
                            this.perfilMembroEdital.add(auxPerfilMembro);
                        }
                    }
                    auxResultadoBusca.clear();
                }
                // Projeto Pesquisa
                if (auxProjetoPesquisa != null) {
                    auxResultadoBusca = new ProjetoPesquisaRepository(ApplicationUtilies.catchEntityManager()).BuscaMembroPorId(auxProjetoPesquisa);
                    for (i = 0; i < auxResultadoBusca.size(); i++) {
                        this.resultadoBusca.add(auxResultadoBusca.get(i));
                        if (auxPerfil.verificaIgualdade(auxResultadoBusca.get(i), this.perfilMembroEdital)) {
                            this.perfilMembroEdital = auxPerfil.incrementaFrequencia(auxResultadoBusca.get(i), this.perfilMembroEdital, "ProjetoPesquisa");
                        } else {
                            PerfilMembro auxPerfilMembro = new PerfilMembro();
                            auxPerfilMembro.setIdLattes(auxResultadoBusca.get(i).getIdLattes());
                            auxPerfilMembro.setNome(auxResultadoBusca.get(i).getNome());
                            auxPerfilMembro.setFrequencia(auxPerfilMembro.getFrequencia() + 1);
                            auxPerfilMembro.getAtributo().setProjetoPesquisa(true);
                            auxPerfilMembro.getAtributo().setFrequenciaProjetoPesquisa(auxPerfilMembro.getAtributo().getFrequenciaProjetoPesquisa() + 1);
                            this.perfilMembroEdital.add(auxPerfilMembro);
                        }
                    }
                    auxResultadoBusca.clear();
                }
                // Resumo Congresso
                if (auxResumoCongresso != null) {
                    auxResultadoBusca = new ResumoCongressoRepository(ApplicationUtilies.catchEntityManager()).BuscaMembroPorId(auxResumoCongresso);
                    for (i = 0; i < auxResultadoBusca.size(); i++) {
                        this.resultadoBusca.add(auxResultadoBusca.get(i));
                        if (auxPerfil.verificaIgualdade(auxResultadoBusca.get(i), this.perfilMembroEdital)) {
                            this.perfilMembroEdital = auxPerfil.incrementaFrequencia(auxResultadoBusca.get(i), this.perfilMembroEdital, "ResumoCongresso");
                        } else {
                            PerfilMembro auxPerfilMembro = new PerfilMembro();
                            auxPerfilMembro.setIdLattes(auxResultadoBusca.get(i).getIdLattes());
                            auxPerfilMembro.setNome(auxResultadoBusca.get(i).getNome());
                            auxPerfilMembro.setFrequencia(auxPerfilMembro.getFrequencia() + 1);
                            auxPerfilMembro.getAtributo().setResumoCongresso(true);
                            auxPerfilMembro.getAtributo().setFrequenciaProjetoPesquisa(auxPerfilMembro.getAtributo().getFrequenciaProjetoPesquisa() + 1);
                            this.perfilMembroEdital.add(auxPerfilMembro);
                        }
                    }
                    auxResultadoBusca.clear();
                }
                // ResumoExpandidoCongresso
                if (auxResumoExpandidoCongresso != null) {
                    auxResultadoBusca = new ResumoExpandidoCongressoRepository(ApplicationUtilies.catchEntityManager()).BuscaMembroPorId(auxResumoExpandidoCongresso);
                    for (i = 0; i < auxResultadoBusca.size(); i++) {
                        this.resultadoBusca.add(auxResultadoBusca.get(i));
                        if (auxPerfil.verificaIgualdade(auxResultadoBusca.get(i), this.perfilMembroEdital)) {
                            this.perfilMembroEdital = auxPerfil.incrementaFrequencia(auxResultadoBusca.get(i), this.perfilMembroEdital, "ResumoExpandidoCongresso");
                        } else {
                            PerfilMembro auxPerfilMembro = new PerfilMembro();
                            auxPerfilMembro.setIdLattes(auxResultadoBusca.get(i).getIdLattes());
                            auxPerfilMembro.setNome(auxResultadoBusca.get(i).getNome());
                            auxPerfilMembro.setFrequencia(auxPerfilMembro.getFrequencia() + 1);
                            auxPerfilMembro.getAtributo().setResumoExpandidoCongresso(true);
                            auxPerfilMembro.getAtributo().setFrequenciaResumoExpandidoCongresso(auxPerfilMembro.getAtributo().getFrequenciaResumoExpandidoCongresso() + 1);
                            this.perfilMembroEdital.add(auxPerfilMembro);
                        }
                    }
                    auxResultadoBusca.clear();
                }
                // Software Com Patente
                if (auxSoftwareComPatente != null) {
                    auxResultadoBusca = new SoftwareComPatenteRepository(ApplicationUtilies.catchEntityManager()).BuscaMembroPorId(auxSoftwareComPatente);
                    for (i = 0; i < auxResultadoBusca.size(); i++) {
                        this.resultadoBusca.add(auxResultadoBusca.get(i));
                        if (auxPerfil.verificaIgualdade(auxResultadoBusca.get(i), this.perfilMembroEdital)) {
                            this.perfilMembroEdital = auxPerfil.incrementaFrequencia(auxResultadoBusca.get(i), this.perfilMembroEdital, "SoftwareComPatente");
                        } else {
                            PerfilMembro auxPerfilMembro = new PerfilMembro();
                            auxPerfilMembro.setIdLattes(auxResultadoBusca.get(i).getIdLattes());
                            auxPerfilMembro.setNome(auxResultadoBusca.get(i).getNome());
                            auxPerfilMembro.setFrequencia(auxPerfilMembro.getFrequencia() + 1);
                            auxPerfilMembro.getAtributo().setSoftwareComPatente(true);
                            auxPerfilMembro.getAtributo().setFrequenciaSoftwareComPatente(auxPerfilMembro.getAtributo().getFrequenciaSoftwareComPatente() + 1);
                            this.perfilMembroEdital.add(auxPerfilMembro);
                        }
                    }
                    auxResultadoBusca.clear();
                }
                // Software Sem Patente
                if (auxSoftwareSemPatente != null) {
                    auxResultadoBusca = new SoftwareSemPatenteRepository(ApplicationUtilies.catchEntityManager()).BuscaMembroPorId(auxSoftwareSemPatente);
                    for (i = 0; i < auxResultadoBusca.size(); i++) {
                        this.resultadoBusca.add(auxResultadoBusca.get(i));
                        if (auxPerfil.verificaIgualdade(auxResultadoBusca.get(i), this.perfilMembroEdital)) {
                            this.perfilMembroEdital = auxPerfil.incrementaFrequencia(auxResultadoBusca.get(i), this.perfilMembroEdital, "SoftwareSemPatente");
                        } else {
                            PerfilMembro auxPerfilMembro = new PerfilMembro();
                            auxPerfilMembro.setIdLattes(auxResultadoBusca.get(i).getIdLattes());
                            auxPerfilMembro.setNome(auxResultadoBusca.get(i).getNome());
                            auxPerfilMembro.setFrequencia(auxPerfilMembro.getFrequencia() + 1);
                            auxPerfilMembro.getAtributo().setSoftwareSemPatente(true);
                            auxPerfilMembro.getAtributo().setFrequenciaSoftwareSemPatente(auxPerfilMembro.getAtributo().getFrequenciaSoftwareSemPatente() + 1);
                            this.perfilMembroEdital.add(auxPerfilMembro);
                        }
                    }
                    auxResultadoBusca.clear();
                }
                // Texto Jornal Noticia
                if (auxTextoJornalNoticia != null) {
                    auxResultadoBusca = new TextoJornalNoticiaRepository(ApplicationUtilies.catchEntityManager()).BuscaMembroPorId(auxTextoJornalNoticia);
                    for (i = 0; i < auxResultadoBusca.size(); i++) {
                        this.resultadoBusca.add(auxResultadoBusca.get(i));
                        if (auxPerfil.verificaIgualdade(auxResultadoBusca.get(i), this.perfilMembroEdital)) {
                            this.perfilMembroEdital = auxPerfil.incrementaFrequencia(auxResultadoBusca.get(i), this.perfilMembroEdital, "TextoJornalNoticia");
                        } else {
                            PerfilMembro auxPerfilMembro = new PerfilMembro();
                            auxPerfilMembro.setIdLattes(auxResultadoBusca.get(i).getIdLattes());
                            auxPerfilMembro.setNome(auxResultadoBusca.get(i).getNome());
                            auxPerfilMembro.setFrequencia(auxPerfilMembro.getFrequencia() + 1);
                            auxPerfilMembro.getAtributo().setTextoJornalNoticia(true);
                            auxPerfilMembro.getAtributo().setFrequenciaTextoJornalNoticia(auxPerfilMembro.getAtributo().getFrequenciaTextoJornalNoticia() + 1);
                            this.perfilMembroEdital.add(auxPerfilMembro);
                        }
                    }
                    auxResultadoBusca.clear();
                }
                // Trabalho Completo Congresso
                if (auxTrabalhoCompletoCongresso != null) {
                    auxResultadoBusca = new TrabalhoCompletoCongressoRepository(ApplicationUtilies.catchEntityManager()).BuscaMembroPorId(auxTrabalhoCompletoCongresso);
                    for (i = 0; i < auxResultadoBusca.size(); i++) {
                        this.resultadoBusca.add(auxResultadoBusca.get(i));
                        if (auxPerfil.verificaIgualdade(auxResultadoBusca.get(i), this.perfilMembroEdital)) {
                            this.perfilMembroEdital = auxPerfil.incrementaFrequencia(auxResultadoBusca.get(i), this.perfilMembroEdital, "TrabalhoCompletoCongresso");
                        } else {
                            PerfilMembro auxPerfilMembro = new PerfilMembro();
                            auxPerfilMembro.setIdLattes(auxResultadoBusca.get(i).getIdLattes());
                            auxPerfilMembro.setNome(auxResultadoBusca.get(i).getNome());
                            auxPerfilMembro.setFrequencia(auxPerfilMembro.getFrequencia() + 1);
                            auxPerfilMembro.getAtributo().setTrabalhoCompletoCongresso(true);
                            auxPerfilMembro.getAtributo().setFrequenciaTrabalhoCompletoCongresso(auxPerfilMembro.getAtributo().getFrequenciaTrabalhoCompletoCongresso() + 1);
                            this.perfilMembroEdital.add(auxPerfilMembro);
                        }
                    }
                    auxResultadoBusca.clear();
                }
                // Trabalho Tecnico
                if (auxTrabalhoTecnico != null) {
                    auxResultadoBusca = new TrabalhoTecnicoRepository(ApplicationUtilies.catchEntityManager()).BuscaMembroPorId(auxTrabalhoTecnico);
                    for (i = 0; i < auxResultadoBusca.size(); i++) {
                        this.resultadoBusca.add(auxResultadoBusca.get(i));
                        if (auxPerfil.verificaIgualdade(auxResultadoBusca.get(i), this.perfilMembroEdital)) {
                            this.perfilMembroEdital = auxPerfil.incrementaFrequencia(auxResultadoBusca.get(i), this.perfilMembroEdital, "TrabalhoTecnico");
                        } else {
                            PerfilMembro auxPerfilMembro = new PerfilMembro();
                            auxPerfilMembro.setIdLattes(auxResultadoBusca.get(i).getIdLattes());
                            auxPerfilMembro.setNome(auxResultadoBusca.get(i).getNome());
                            auxPerfilMembro.setUrl(auxResultadoBusca.get(i).getUrl());
                            auxPerfilMembro.setFrequencia(auxPerfilMembro.getFrequencia() + 1);
                            auxPerfilMembro.getAtributo().setTrabalhoTecnico(true);
                            auxPerfilMembro.getAtributo().setFrequenciaTrabalhoTecnico(auxPerfilMembro.getAtributo().getFrequenciaTrabalhoTecnico() + 1);
                            this.perfilMembroEdital.add(auxPerfilMembro);
                        }
                    }
                    auxResultadoBusca.clear();
                }
                Collections.sort(this.perfilMembroEdital, new PerfilMembro());

                return this.perfilMembroEdital;

//            if (resultadoBusca.size() > 1) { // existe mais de um elemento no resultado da busca
//                // colocar os que repetem no topo da lista e remover a repetição, para cada repetição de um elemento, ele anda uma posição na lista
//                for (int j = 0; j < resultadoBusca.size(); j++) {
//                    if (resultadoBuscaAuxiliar.isEmpty()) {       // se a lista ta vazia, pega o primeiro elemento
//                        resultadoBuscaAuxiliar.add(resultadoBusca.get(j));
//                    } else {
//                        if (!resultadoBuscaAuxiliar.contains(resultadoBusca.get(j))) { // se o elemento nao eh repetido
//                            resultadoBuscaAuxiliar.add(resultadoBusca.get(j));
//                        } else {       // quando o elemento eh repetido, mover ele uma posição na frente da lista final[auxListaLattes]
//                            for (int k = 0; k < resultadoBuscaAuxiliar.size(); k++) {
//                                for (int z = 0; z < resultadoBusca.size(); z++) {
//                                    if (resultadoBuscaAuxiliar.get(k).getIdLattes() == resultadoBusca.get(z).getIdLattes()) {
//                                        Membro aux = resultadoBuscaAuxiliar.get(k);
//                                        resultadoBuscaAuxiliar.remove(k);
//                                        if (k > 1) {
//                                            resultadoBuscaAuxiliar.add(k - 2, aux);
//                                        }
//                                        if (k == 1) {
//                                            resultadoBuscaAuxiliar.add(k - 1, aux);
//                                        }
//                                        if (k == 0) {
//                                            resultadoBuscaAuxiliar.add(k, aux);
//                                        }
//                                    }
//                                }
//                            }
//                        }
//                    }
//                }
//                resultadoBusca.clear();
//                auxResultadoBusca = resultadoBuscaAuxiliar;
//            }                                         
            }
        }
        return null;
    }

    public String getPalavra() {
        return palavra;
    }

    public void setPalavra(String palavra) {
        this.palavra = palavra;
    }

    public String getAuxPalavra() {
        return auxPalavra;
    }

    public void setAuxPalavra(String auxPalavra) {
        this.auxPalavra = auxPalavra;
    }

    public List getResultadoBusca() {
        return resultadoBusca;
    }

    public void setResultadoBusca(List resultadoBusca) {
        this.resultadoBusca = resultadoBusca;
    }

    public PerfilMembro getMembroEdital(List<PerfilMembro> membros, Membro membro) {
        for (PerfilMembro perfilMembro : membros) {
            if (perfilMembro.getIdLattes().equals(membro.getIdLattes())) {
                return perfilMembro;
            }
        }
        return null;
    }

    public GenericRepository getProvenanceRepository(Class clazz) {

        if (clazz.getCanonicalName().equals(ApresentacaoTrabalho.class.getCanonicalName())) {
            return new ApresentacaoTrabalhoRepository(ApplicationUtilies.catchEntityManager());
        }
        if (clazz.getCanonicalName().equals(AreasAtuacao.class.getCanonicalName())) {
            return new AreasAtuacaoRepository(ApplicationUtilies.catchEntityManager());
        }
        if (clazz.getCanonicalName().equals(ArtigoAceito.class.getCanonicalName())) {
            return new ArtigoAceitoRepository(ApplicationUtilies.catchEntityManager());
        }
        if (clazz.getCanonicalName().equals(ArtigoPeriodico.class.getCanonicalName())) {
            return new ArtigoPeriodicoRepository(ApplicationUtilies.catchEntityManager());
        }
        if (clazz.getCanonicalName().equals(CapituloLivroPublicado.class.getCanonicalName())) {
            return new CapituloLivroPublicadoRepository(ApplicationUtilies.catchEntityManager());
        }
        if (clazz.getCanonicalName().equals(FormacaoAcademica.class.getCanonicalName())) {
            return new FormacaoAcademicaRepository(ApplicationUtilies.catchEntityManager());
        }
        if (clazz.getCanonicalName().equals(LivroPublicado.class.getCanonicalName())) {
            return new LivroPublicadoRepository(ApplicationUtilies.catchEntityManager());
        }
        if (clazz.getCanonicalName().equals(OrganizacaoEvento.class.getCanonicalName())) {
            return new OrganizacaoEventoRepository(ApplicationUtilies.catchEntityManager());
        }
        if (clazz.getCanonicalName().equals(OrientacoesAndamento.class.getCanonicalName())) {
            return new OrientacoesAndamentoRepository(ApplicationUtilies.catchEntityManager());
        }
        if (clazz.getCanonicalName().equals(OrientacoesConcluidas.class.getCanonicalName())) {
            return new OrientacoesConcluidasRepository(ApplicationUtilies.catchEntityManager());
        }
        if (clazz.getCanonicalName().equals(OutroTipoBibliografico.class.getCanonicalName())) {
            return new OutroTipoBibliograficoRepository(ApplicationUtilies.catchEntityManager());
        }
        if (clazz.getCanonicalName().equals(OutroTipoTecnico.class.getCanonicalName())) {
            return new OutroTipoTecnicoRepository(ApplicationUtilies.catchEntityManager());
        }
        if (clazz.getCanonicalName().equals(ParticipacaoEvento.class.getCanonicalName())) {
            return new ParticipacaoEventoRepository(ApplicationUtilies.catchEntityManager());
        }
        if (clazz.getCanonicalName().equals(ProcessoTecnica.class.getCanonicalName())) {
            return new ProcessoTecnicaRepository(ApplicationUtilies.catchEntityManager());
        }
        if (clazz.getCanonicalName().equals(ProducaoArtistica.class.getCanonicalName())) {
            return new ProducaoArtisticaRepository(ApplicationUtilies.catchEntityManager());
        }
        if (clazz.getCanonicalName().equals(ProdutoTecnologico.class.getCanonicalName())) {
            return new ProdutoTecnologicoRepository(ApplicationUtilies.catchEntityManager());
        }
        if (clazz.getCanonicalName().equals(ProjetoPesquisa.class.getCanonicalName())) {
            return new ProjetoPesquisaRepository(ApplicationUtilies.catchEntityManager());
        }
        if (clazz.getCanonicalName().equals(ResumoCongresso.class.getCanonicalName())) {
            return new ResumoCongressoRepository(ApplicationUtilies.catchEntityManager());
        }
        if (clazz.getCanonicalName().equals(ResumoExpandidoCongresso.class.getCanonicalName())) {
            return new ResumoExpandidoCongressoRepository(ApplicationUtilies.catchEntityManager());
        }
        if (clazz.getCanonicalName().equals(SoftwareComPatente.class.getCanonicalName())) {
            return new SoftwareComPatenteRepository(ApplicationUtilies.catchEntityManager());
        }
        if (clazz.getCanonicalName().equals(SoftwareSemPatente.class.getCanonicalName())) {
            return new SoftwareSemPatenteRepository(ApplicationUtilies.catchEntityManager());
        }
        if (clazz.getCanonicalName().equals(TextoJornalNoticia.class.getCanonicalName())) {
            return new TextoJornalNoticiaRepository(ApplicationUtilies.catchEntityManager());
        }
        if (clazz.getCanonicalName().equals(TrabalhoCompletoCongresso.class.getCanonicalName())) {
            return new TrabalhoCompletoCongressoRepository(ApplicationUtilies.catchEntityManager());
        }
        if (clazz.getCanonicalName().equals(TrabalhoTecnico.class.getCanonicalName())) {
            return new TrabalhoTecnicoRepository(ApplicationUtilies.catchEntityManager());
        }
        return null;
    }

    public <T> void createProvenance(List<T> provenance, Class clazz, HashMap<String, List<PerfilMembro>> proveniencia, String label) {
        PerfilMembro auxPerfil = new PerfilMembro();
        List<PerfilMembro> membros = new ArrayList<PerfilMembro>();
        List<Membro> membrosList = new ArrayList<Membro>();
        if (provenance != null) {
            membrosList = getProvenanceRepository(clazz).BuscaMembroPorId(provenance);
            for (Membro membro : membrosList) {
                PerfilMembro auxPerfilMembro = null;
                if (auxPerfil.verificaIgualdade(membro, membros)) {
                    auxPerfilMembro = this.getMembroEdital(membros, membro);
                    System.out.println(auxPerfilMembro);
                    if (auxPerfilMembro.getAtributos().containsKey(label)) {
                        auxPerfilMembro.getAtributos().put(label, auxPerfilMembro.getAtributos().get(label) + 1);
                    } else {
                        auxPerfilMembro.getAtributos().put(label, 1);
                    }
//                    if (auxPerfil.verificaIgualdade(membro, membros)) {
//                        membros.add(auxPerfilMembro);
//                    }
                } else {
                    auxPerfilMembro = new PerfilMembro();
                    auxPerfilMembro.setIdLattes(membro.getIdLattes());
                    auxPerfilMembro.setNome(membro.getNome());
                    auxPerfilMembro.setFrequencia(1);
                    auxPerfilMembro.getAtributos().put(label, 1);
                    this.perfilMembroEdital.add(auxPerfilMembro);
                    membros.add(auxPerfilMembro);
                }
            }
        }
        proveniencia.put(label, membros);
    }

    public void resultadoPesquisa2() {
        System.out.println("Entramooos");
        resultadoBusca.clear();
        auxResultadoBusca.clear();
        Node node = new Node();
        Edger edger = new Edger();
        HashMap<String, List<PerfilMembro>> proveniencias = new HashMap<String, List<PerfilMembro>>();
        int i = 0;
        node.getIds().add("n" + i);
        node.addNode("n" + i++, "Lattes", "palavra", null);

        root = new DefaultTreeNode("Resultado", null);

        if (this.palavra != null) {
            String[] split = palavra.split(";");
            for (String word : split) {
                List<ApresentacaoTrabalho> auxApresentacaoTrabalho = new ApresentacaoTrabalhoRepository(ApplicationUtilies.catchEntityManager()).buscaInformacao(word);
                this.createProvenance(auxApresentacaoTrabalho, ApresentacaoTrabalho.class, proveniencias, "Apresentação de Trabalhos");
                //Areas Atuacao
                List<AreasAtuacao> auxAreasAtuacao = new AreasAtuacaoRepository(ApplicationUtilies.catchEntityManager()).buscaInformacao(word);
                this.createProvenance(auxAreasAtuacao, AreasAtuacao.class, proveniencias, "Areas de Atuação");
                //Artigo Aceito
                List<ArtigoAceito> auxArtigoAceito = new ArtigoAceitoRepository(ApplicationUtilies.catchEntityManager()).buscaInformacao(word);
                this.createProvenance(auxArtigoAceito, ArtigoAceito.class, proveniencias, "Artigos Aceitos");
                //Artigo Periodico
                List<ArtigoPeriodico> auxArtigoPeriodico = new ArtigoPeriodicoRepository(ApplicationUtilies.catchEntityManager()).buscaInformacao(word);
                this.createProvenance(auxArtigoPeriodico, ArtigoPeriodico.class, proveniencias, "Artigo Periodico");
                //Capitulo Livro Publicado
                List<CapituloLivroPublicado> auxCapituloLivroPublicado = new CapituloLivroPublicadoRepository(ApplicationUtilies.catchEntityManager()).buscaInformacao(word);
                this.createProvenance(auxCapituloLivroPublicado, CapituloLivroPublicado.class, proveniencias, "Capitulo livro publicado");
                //Formacao Academica
                List<FormacaoAcademica> auxFormacaoAcademica = new FormacaoAcademicaRepository(ApplicationUtilies.catchEntityManager()).buscaInformacao(word);
                this.createProvenance(auxFormacaoAcademica, FormacaoAcademica.class, proveniencias, "Formação Academica");
                //Livro Publicado
                List<LivroPublicado> auxLivroPublicado = new LivroPublicadoRepository(ApplicationUtilies.catchEntityManager()).buscaInformacao(word);
                this.createProvenance(auxLivroPublicado, LivroPublicado.class, proveniencias, "Livro Publicado");
                //Organizacao Evento
                List<OrganizacaoEvento> auxOrganizacaoEvento = new OrganizacaoEventoRepository(ApplicationUtilies.catchEntityManager()).buscaInformacao(word);
                this.createProvenance(auxOrganizacaoEvento, OrganizacaoEvento.class, proveniencias, "Organização de Eventos");
                //Orientacoes Andamento
                List<OrientacoesAndamento> auxOrientacoesAndamento = new OrientacoesAndamentoRepository(ApplicationUtilies.catchEntityManager()).buscaInformacao(word);
                this.createProvenance(auxOrientacoesAndamento, OrientacoesAndamento.class, proveniencias, "Orientações em Andamento");
                //Orientacoes Concluidas
                List<OrientacoesConcluidas> auxOrientacoesConcluidas = new OrientacoesConcluidasRepository(ApplicationUtilies.catchEntityManager()).buscaInformacao(word);
                this.createProvenance(auxOrientacoesConcluidas, OrientacoesConcluidas.class, proveniencias, "Orientações Concluídas");
                //Outro Tipo Bibliografico
                List<OutroTipoBibliografico> auxOutroTipoBibliografico = new OutroTipoBibliograficoRepository(ApplicationUtilies.catchEntityManager()).buscaInformacao(word);
                this.createProvenance(auxOutroTipoBibliografico, OutroTipoBibliografico.class, proveniencias, "Outro tipos bibliograficos");
                //Outro Tipo Tecnico
                List<OutroTipoTecnico> auxOutroTipoTecnico = new OutroTipoTecnicoRepository(ApplicationUtilies.catchEntityManager()).buscaInformacao(word);
                this.createProvenance(auxOutroTipoTecnico, OutroTipoTecnico.class, proveniencias, "Outro tipos tecnicos");
                //Participacao Evento
                List<ParticipacaoEvento> auxParticipacaoEvento = new ParticipacaoEventoRepository(ApplicationUtilies.catchEntityManager()).buscaInformacao(word);
                this.createProvenance(auxParticipacaoEvento, ParticipacaoEvento.class, proveniencias, "Participação de Eventos");
                //Processo Tecnica
                List<ProcessoTecnica> auxProcessoTecnica = new ProcessoTecnicaRepository(ApplicationUtilies.catchEntityManager()).buscaInformacao(word);
                this.createProvenance(auxProcessoTecnica, ProcessoTecnica.class, proveniencias, "Processo Tecnico");
                //Producao Artistica
                List<ProducaoArtistica> auxProducaoArtistica = new ProducaoArtisticaRepository(ApplicationUtilies.catchEntityManager()).buscaInformacao(word);
                this.createProvenance(auxProducaoArtistica, ProducaoArtistica.class, proveniencias, "Produção Artistica");
                //Produto Tecnologico
                List<ProdutoTecnologico> auxProdutoTecnologico = new ProdutoTecnologicoRepository(ApplicationUtilies.catchEntityManager()).buscaInformacao(word);
                this.createProvenance(auxProdutoTecnologico, ProdutoTecnologico.class, proveniencias, "Produto Tecnologico");
                //Projeto Pesquisa
                List<ProjetoPesquisa> auxProjetoPesquisa = new ProjetoPesquisaRepository(ApplicationUtilies.catchEntityManager()).buscaInformacao(word);
                this.createProvenance(auxProjetoPesquisa, ProjetoPesquisa.class, proveniencias, "Projeto Pesquisa");
                //Resumo Congresso
                List<ResumoCongresso> auxResumoCongresso = new ResumoCongressoRepository(ApplicationUtilies.catchEntityManager()).buscaInformacao(word);
                this.createProvenance(auxResumoCongresso, ResumoCongresso.class, proveniencias, "Resumo Congresso");
                //Resumo Congresso
                List<ResumoExpandidoCongresso> auxResumoExpandidoCongresso = new ResumoExpandidoCongressoRepository(ApplicationUtilies.catchEntityManager()).buscaInformacao(word);
                this.createProvenance(auxResumoExpandidoCongresso, ResumoExpandidoCongresso.class, proveniencias, "Resumo Expandido Congresso");
                //Software Com Patente
                List<SoftwareComPatente> auxSoftwareComPatente = new SoftwareComPatenteRepository(ApplicationUtilies.catchEntityManager()).buscaInformacao(word);
                this.createProvenance(auxSoftwareComPatente, SoftwareComPatente.class, proveniencias, "Software Com Patente");
                //Software Sem Patente
                List<SoftwareSemPatente> auxSoftwareSemPatente = new SoftwareSemPatenteRepository(ApplicationUtilies.catchEntityManager()).buscaInformacao(word);
                this.createProvenance(auxSoftwareSemPatente, SoftwareSemPatente.class, proveniencias, "Software Sem Patente");
                //Texto Jornal Noticia
                List<TextoJornalNoticia> auxTextoJornalNoticia = new TextoJornalNoticiaRepository(ApplicationUtilies.catchEntityManager()).buscaInformacao(word);
                this.createProvenance(auxTextoJornalNoticia, TextoJornalNoticia.class, proveniencias, "Texto Jornal Noticia");
                //Trabalho Completo Congresso
                List<TrabalhoCompletoCongresso> auxTrabalhoCompletoCongresso = new TrabalhoCompletoCongressoRepository(ApplicationUtilies.catchEntityManager()).buscaInformacao(word);
                this.createProvenance(auxTrabalhoCompletoCongresso, TrabalhoCompletoCongresso.class, proveniencias, "Trabalho Completo Congresso");
                //Trabalho Tecnico
                List<TrabalhoTecnico> auxTrabalhoTecnico = new TrabalhoTecnicoRepository(ApplicationUtilies.catchEntityManager()).buscaInformacao(word);
                this.createProvenance(auxTrabalhoTecnico, TrabalhoTecnico.class, proveniencias, "Trabalho Tecnico");
                //Collections.sort(this.perfilMembroEdital, new PerfilMembro());
                TreeNode wordNode = new DefaultTreeNode(word, root);
                System.out.println("Testandooo");
                String id = "n" + i++;
                node.getIds().add(id);
                node.addNode(id, word, "palavra", null);
                for (String key : proveniencias.keySet()) {
                    if (proveniencias.get(key).size() > 0) {
                        String id2 = "n" + i++;
                        node.getIds().add(id2);
                        node.addNode(id2, key, "palavra", null);
                        TreeNode rootChildren = new DefaultTreeNode(key, wordNode);
                        List<PerfilMembro> membros = proveniencias.get(key);
                        System.out.println("tamanho = " + membros.size());
                        for (PerfilMembro m : membros) {
                            String id3 = "n" + i++;
                            node.getIds().add(id3);
                            node.addNode(id3, m.getNome(), "professor",
                                    "open");
                            edger.addEdger(id2, id3);
                            rootChildren.getChildren().add(new DefaultTreeNode(m));
                        }
                        edger.addEdger(id, id2);
                    }
                }
                edger.addEdger("n0", id);
                resultadoProvenance.put(word, new HashMap<String, List<PerfilMembro>>(proveniencias));
            }
        }
        System.out.println(new Gson().toJson(node));
        System.out.println(new Gson().toJson(edger));
//        RequestContext context = RequestContext.getCurrentInstance();
//        context.execute("drawGraph('" + new Gson().toJson(node) + "','"
//                + new Gson().toJson(edger) + "')");
    }

    public void createTree() {

    }

    public TreeNode getRoot() {
        return root;
    }

    public void setRoot(TreeNode root) {
        this.root = root;
    }

    public boolean verificarMembro(Object ob) {
        return ob instanceof PerfilMembro;
    }

    public void onNodeExpand(NodeSelectEvent event) {
        if (this.verificarMembro(event.getTreeNode().getData())) {
            this.selectedNode = event.getTreeNode();
            membroSelected = (PerfilMembro) event.getTreeNode().getData();
            RequestContext.getCurrentInstance().execute("PF('pesquisadorDialog').show()");
        }
    }

    public boolean isExtend() {
        return extend;
    }

    public void setExtend(boolean extend) {
        this.extend = extend;
    }

    public TreeNode getSelectedNode() {
        return selectedNode;
    }

    public void setSelectedNode(TreeNode selectedNode) {
        this.selectedNode = selectedNode;
    }

    public PerfilMembro getMembroSelected() {
        return membroSelected;
    }

    public void setMembroSelected(PerfilMembro membroSelected) {
        this.membroSelected = membroSelected;
    }

    public HashMap<String, HashMap<String, List<PerfilMembro>>> getResultadoProvenance() {
        return resultadoProvenance;
    }

    public void setResultadoProvenance(HashMap<String, HashMap<String, List<PerfilMembro>>> resultadoProvenance) {
        this.resultadoProvenance = resultadoProvenance;
    }

}
