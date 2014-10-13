/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uft.lattes.controller;

import com.google.gson.Gson;
import com.uft.lattes.model.Curso;
import com.uft.lattes.repository.ApresentacaoTrabalhoRepository;
import com.uft.lattes.repository.ArtigoAceitoRepository;
import com.uft.lattes.repository.ArtigoPeriodicoRepository;
import com.uft.lattes.repository.CapituloLivroPublicadoRepository;
import com.uft.lattes.repository.CursoRepository;
import com.uft.lattes.repository.LivroPublicadoRepository;
import com.uft.lattes.repository.OrganizacaoEventoRepository;
import com.uft.lattes.repository.OrientacoesAndamentoRepository;
import com.uft.lattes.repository.OrientacoesConcluidasRepository;
import com.uft.lattes.repository.OutroTipoBibliograficoRepository;
import com.uft.lattes.repository.OutroTipoTecnicoRepository;
import com.uft.lattes.repository.ParticipacaoEventoRepository;
import com.uft.lattes.repository.ProdutoTecnologicoRepository;
import com.uft.lattes.repository.ResumoCongressoRepository;
import com.uft.lattes.repository.ResumoExpandidoCongressoRepository;
import com.uft.lattes.repository.SoftwareSemPatenteRepository;
import com.uft.lattes.repository.TextoJornalNoticiaRepository;
import com.uft.lattes.repository.TrabalhoCompletoCongressoRepository;
import com.uft.lattes.repository.TrabalhoTecnicoRepository;
import com.uft.lattes.util.ApplicationUtilies;
import com.uft.lattes.util.GenericController;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.context.RequestContext;

/**
 *
 * @author igor
 */
@Named
@SessionScoped
public class CursoController extends GenericController<Curso, CursoRepository> implements Serializable {

    Curso selecionado;
    boolean curso;
    String grupoSelecionado;
    String subGrupoSelecionado;
    List<String> producaoTecnica = new ArrayList<String>();
    List<String> producaoBibliografica = new ArrayList<String>();
    List<String> eventos = new ArrayList<String>();
    List<String> orientacoes = new ArrayList<String>();
    @Inject
    CampusController campus;

    public CursoController() {
        this.entity = new Curso();
        iniciaSubGrupos();
    }

    @Override
    public void insert() {
        this.repository = new CursoRepository(ApplicationUtilies.catchEntityManager());
        this.repository.insert(this.entity);
        restartController();
    }

    @Override
    public void remove() {
        this.repository = new CursoRepository(ApplicationUtilies.catchEntityManager());
        this.repository.remove(this.entity);
        restartController();
    }

    @Override
    public void update() {
        this.repository = new CursoRepository(ApplicationUtilies.catchEntityManager());
        this.repository.update(this.entity);
        restartController();
    }

    public void restartController() {
        this.entity = new Curso();
        this.selecionado = new Curso();
    }

    public List<Curso> loadTodosCursos() {
        this.repository = new CursoRepository(ApplicationUtilies.catchEntityManager());
        return this.repository.getTodosCursos();
    }

    public List<Curso> loadTodosCursosPorCampus() {
        if (this.campus.getSelecionado() != null) {
            this.repository = new CursoRepository(ApplicationUtilies.catchEntityManager());
            return this.repository.getTodosCursosPorCampus(this.campus.getSelecionado().getId());
        }
        return null;
    }

    public Curso getSelecionado() {
        return selecionado;
    }

    public void setSelecionado(Curso selecionado) {
        this.selecionado = selecionado;
    }
      

    public void plotaSubGrupo() {
        RequestContext context = RequestContext.getCurrentInstance();
        if (this.grupoSelecionado.equals("prodTecnicas")) {
            if (this.subGrupoSelecionado.equals("Softwares Sem Patente")) {
                if (this.campus.getSelecionado() != null) { // campus selecionado
                    if (this.selecionado != null) { // curso selecionado
                        context.execute("pizza(" + new Gson().toJson(new SoftwareSemPatenteRepository(ApplicationUtilies.catchEntityManager()).getSeparaPorAno(this.selecionado.getId())) + ")");
                        context.execute("barra(" + new Gson().toJson(new SoftwareSemPatenteRepository(ApplicationUtilies.catchEntityManager()).getSeparaPorAno(this.selecionado.getId())) + ")");
                    } else { // se nao tiver curso selecionado, ou seja, analise do campus entao
                        context.execute("pizza(" + new Gson().toJson(new SoftwareSemPatenteRepository(ApplicationUtilies.catchEntityManager()).getSeparaPorAnoPorCampus(this.campus.getSelecionado().getId())) + ")");
                        context.execute("barra(" + new Gson().toJson(new SoftwareSemPatenteRepository(ApplicationUtilies.catchEntityManager()).getSeparaPorAnoPorCampus(this.campus.getSelecionado().getId())) + ")");
                    }
                } else { // nem campus nem curso selecionado, ou seja, a instituicao inteira
                    context.execute("pizza(" + new Gson().toJson(new SoftwareSemPatenteRepository(ApplicationUtilies.catchEntityManager()).getSeparaPorAnoTodosCampus()) + ")");
                    context.execute("barra(" + new Gson().toJson(new SoftwareSemPatenteRepository(ApplicationUtilies.catchEntityManager()).getSeparaPorAnoTodosCampus()) + ")");
                }
            }
            if (this.subGrupoSelecionado.equals("Trabalhos Técnicos")) {
                if (this.campus.getSelecionado() != null) {
                    if (this.selecionado != null) { // curso selecionado
                        context.execute("pizza(" + new Gson().toJson(new TrabalhoTecnicoRepository(ApplicationUtilies.catchEntityManager()).getSeparaPorAno(this.selecionado.getId())) + ")");
                        context.execute("barra(" + new Gson().toJson(new TrabalhoTecnicoRepository(ApplicationUtilies.catchEntityManager()).getSeparaPorAno(this.selecionado.getId())) + ")");
                    } else {
                        context.execute("pizza(" + new Gson().toJson(new TrabalhoTecnicoRepository(ApplicationUtilies.catchEntityManager()).getSeparaPorAnoPorCampus(this.campus.getSelecionado().getId())) + ")");
                        context.execute("barra(" + new Gson().toJson(new TrabalhoTecnicoRepository(ApplicationUtilies.catchEntityManager()).getSeparaPorAnoPorCampus(this.campus.getSelecionado().getId())) + ")");
                    }
                } else {
                    context.execute("pizza(" + new Gson().toJson(new TrabalhoTecnicoRepository(ApplicationUtilies.catchEntityManager()).getSeparaPorAnoTodosCampus()) + ")");
                    context.execute("barra(" + new Gson().toJson(new TrabalhoTecnicoRepository(ApplicationUtilies.catchEntityManager()).getSeparaPorAnoTodosCampus()) + ")");
                }
            }
            if (this.subGrupoSelecionado.equals("Produtos Tecnológicos")) {
                if (this.campus.getSelecionado() != null) {
                    if (this.selecionado != null) { // curso selecionado
                        context.execute("pizza(" + new Gson().toJson(new ProdutoTecnologicoRepository(ApplicationUtilies.catchEntityManager()).getSeparaPorAno(this.selecionado.getId())) + ")");
                        context.execute("barra(" + new Gson().toJson(new ProdutoTecnologicoRepository(ApplicationUtilies.catchEntityManager()).getSeparaPorAno(this.selecionado.getId())) + ")");
                    } else {
                        context.execute("pizza(" + new Gson().toJson(new ProdutoTecnologicoRepository(ApplicationUtilies.catchEntityManager()).getSeparaPorAnoPorCampus(this.campus.getSelecionado().getId())) + ")");
                        context.execute("barra(" + new Gson().toJson(new ProdutoTecnologicoRepository(ApplicationUtilies.catchEntityManager()).getSeparaPorAnoPorCampus(this.campus.getSelecionado().getId())) + ")");
                    }
                } else {
                    context.execute("pizza(" + new Gson().toJson(new ProdutoTecnologicoRepository(ApplicationUtilies.catchEntityManager()).getSeparaPorAnoTodosCampus()) + ")");
                    context.execute("barra(" + new Gson().toJson(new ProdutoTecnologicoRepository(ApplicationUtilies.catchEntityManager()).getSeparaPorAnoTodosCampus()) + ")");
                }
            }
            if (this.subGrupoSelecionado.equals("Outros Tipos Técnicos")) {
                if (this.campus.getSelecionado() != null) {
                    if (this.selecionado != null) { // curso selecionado
                        context.execute("pizza(" + new Gson().toJson(new OutroTipoTecnicoRepository(ApplicationUtilies.catchEntityManager()).getSeparaPorAno(this.selecionado.getId())) + ")");
                        context.execute("barra(" + new Gson().toJson(new OutroTipoTecnicoRepository(ApplicationUtilies.catchEntityManager()).getSeparaPorAno(this.selecionado.getId())) + ")");
                    } else {
                        context.execute("pizza(" + new Gson().toJson(new OutroTipoTecnicoRepository(ApplicationUtilies.catchEntityManager()).getSeparaPorAnoPorCampus(this.campus.getSelecionado().getId())) + ")");
                        context.execute("barra(" + new Gson().toJson(new OutroTipoTecnicoRepository(ApplicationUtilies.catchEntityManager()).getSeparaPorAnoPorCampus(this.campus.getSelecionado().getId())) + ")");
                    }
                } else {
                    context.execute("pizza(" + new Gson().toJson(new OutroTipoTecnicoRepository(ApplicationUtilies.catchEntityManager()).getSeparaPorAnoTodosCampus()) + ")");
                    context.execute("barra(" + new Gson().toJson(new OutroTipoTecnicoRepository(ApplicationUtilies.catchEntityManager()).getSeparaPorAnoTodosCampus()) + ")");
                }
            }
        }
        // AKIII
        if (this.grupoSelecionado.equals("prodBiblio")) {
            if (this.subGrupoSelecionado.equals("Apresentação de Trabalho")) {
                if (this.campus.getSelecionado() != null) {
                    if (this.selecionado != null) {
                        context.execute("pizza(" + new Gson().toJson(new ApresentacaoTrabalhoRepository(ApplicationUtilies.catchEntityManager()).getSeparaPorAno(this.selecionado.getId())) + ")");
                        context.execute("barra(" + new Gson().toJson(new ApresentacaoTrabalhoRepository(ApplicationUtilies.catchEntityManager()).getSeparaPorAno(this.selecionado.getId())) + ")");
                    } else {
                        context.execute("pizza(" + new Gson().toJson(new ApresentacaoTrabalhoRepository(ApplicationUtilies.catchEntityManager()).getSeparaPorAnoPorCampus(this.campus.getSelecionado().getId())) + ")");
                        context.execute("barra(" + new Gson().toJson(new ApresentacaoTrabalhoRepository(ApplicationUtilies.catchEntityManager()).getSeparaPorAnoPorCampus(this.campus.getSelecionado().getId())) + ")");
                    }
                } else {
                    context.execute("pizza(" + new Gson().toJson(new ApresentacaoTrabalhoRepository(ApplicationUtilies.catchEntityManager()).getSeparaPorAnoTodosCampus()) + ")");
                    context.execute("barra(" + new Gson().toJson(new ApresentacaoTrabalhoRepository(ApplicationUtilies.catchEntityManager()).getSeparaPorAnoTodosCampus()) + ")");
                }
            }
            if (this.subGrupoSelecionado.equals("Resumo em Congresso")) {
                if (this.campus.getSelecionado() != null) {
                    if (this.selecionado != null) {
                        context.execute("pizza(" + new Gson().toJson(new ResumoCongressoRepository(ApplicationUtilies.catchEntityManager()).getSeparaPorAno(this.selecionado.getId())) + ")");
                        context.execute("barra(" + new Gson().toJson(new ResumoCongressoRepository(ApplicationUtilies.catchEntityManager()).getSeparaPorAno(this.selecionado.getId())) + ")");
                    } else {
                        context.execute("pizza(" + new Gson().toJson(new ResumoCongressoRepository(ApplicationUtilies.catchEntityManager()).getSeparaPorAnoPorCampus(this.campus.getSelecionado().getId())) + ")");
                        context.execute("barra(" + new Gson().toJson(new ResumoCongressoRepository(ApplicationUtilies.catchEntityManager()).getSeparaPorAnoPorCampus(this.campus.getSelecionado().getId())) + ")");
                    }
                } else {
                    context.execute("pizza(" + new Gson().toJson(new ResumoCongressoRepository(ApplicationUtilies.catchEntityManager()).getSeparaPorAnoTodosCampus()) + ")");
                    context.execute("barra(" + new Gson().toJson(new ResumoCongressoRepository(ApplicationUtilies.catchEntityManager()).getSeparaPorAnoTodosCampus()) + ")");
                }
            }
            if (this.subGrupoSelecionado.equals("Resumo Expandido em Congresso")) {
                if (this.campus.getSelecionado() != null) {
                    if (this.selecionado != null) {
                        context.execute("pizza(" + new Gson().toJson(new ResumoExpandidoCongressoRepository(ApplicationUtilies.catchEntityManager()).getSeparaPorAno(this.selecionado.getId())) + ")");
                        context.execute("barra(" + new Gson().toJson(new ResumoExpandidoCongressoRepository(ApplicationUtilies.catchEntityManager()).getSeparaPorAno(this.selecionado.getId())) + ")");
                    } else {
                        context.execute("pizza(" + new Gson().toJson(new ResumoExpandidoCongressoRepository(ApplicationUtilies.catchEntityManager()).getSeparaPorAnoPorCampus(this.campus.getSelecionado().getId())) + ")");
                        context.execute("barra(" + new Gson().toJson(new ResumoExpandidoCongressoRepository(ApplicationUtilies.catchEntityManager()).getSeparaPorAnoPorCampus(this.campus.getSelecionado().getId())) + ")");
                    }
                } else {
                    context.execute("pizza(" + new Gson().toJson(new ResumoExpandidoCongressoRepository(ApplicationUtilies.catchEntityManager()).getSeparaPorAnoTodosCampus()) + ")");
                    context.execute("barra(" + new Gson().toJson(new ResumoExpandidoCongressoRepository(ApplicationUtilies.catchEntityManager()).getSeparaPorAnoTodosCampus()) + ")");
                }
            }
            if (this.subGrupoSelecionado.equals("Artigo Aceito")) {
                if (this.campus.getSelecionado() != null) {
                    if (this.selecionado != null) {
                        context.execute("pizza(" + new Gson().toJson(new ArtigoAceitoRepository(ApplicationUtilies.catchEntityManager()).getSeparaPorAno(this.selecionado.getId())) + ")");
                        context.execute("barra(" + new Gson().toJson(new ArtigoAceitoRepository(ApplicationUtilies.catchEntityManager()).getSeparaPorAno(this.selecionado.getId())) + ")");
                    } else {
                        context.execute("pizza(" + new Gson().toJson(new ArtigoAceitoRepository(ApplicationUtilies.catchEntityManager()).getSeparaPorAnoPorCampus(this.campus.getSelecionado().getId())) + ")");
                        context.execute("barra(" + new Gson().toJson(new ArtigoAceitoRepository(ApplicationUtilies.catchEntityManager()).getSeparaPorAnoPorCampus(this.campus.getSelecionado().getId())) + ")");
                    }
                } else {
                    context.execute("pizza(" + new Gson().toJson(new ArtigoAceitoRepository(ApplicationUtilies.catchEntityManager()).getSeparaPorAnoTodosCampus()) + ")");
                    context.execute("barra(" + new Gson().toJson(new ArtigoAceitoRepository(ApplicationUtilies.catchEntityManager()).getSeparaPorAnoTodosCampus()) + ")");
                }
            }
            if (this.subGrupoSelecionado.equals("Artigo em Periódico")) {
                if (this.campus.getSelecionado() != null) {
                    if (this.selecionado != null) {
                        context.execute("pizza(" + new Gson().toJson(new ArtigoPeriodicoRepository(ApplicationUtilies.catchEntityManager()).getSeparaPorAno(this.selecionado.getId())) + ")");
                        context.execute("barra(" + new Gson().toJson(new ArtigoPeriodicoRepository(ApplicationUtilies.catchEntityManager()).getSeparaPorAno(this.selecionado.getId())) + ")");
                    } else {
                        context.execute("pizza(" + new Gson().toJson(new ArtigoPeriodicoRepository(ApplicationUtilies.catchEntityManager()).getSeparaPorAnoPorCampus(this.campus.getSelecionado().getId())) + ")");
                        context.execute("barra(" + new Gson().toJson(new ArtigoPeriodicoRepository(ApplicationUtilies.catchEntityManager()).getSeparaPorAnoPorCampus(this.campus.getSelecionado().getId())) + ")");
                    }
                } else {
                    context.execute("pizza(" + new Gson().toJson(new ArtigoPeriodicoRepository(ApplicationUtilies.catchEntityManager()).getSeparaPorAnoTodosCampus()) + ")");
                    context.execute("barra(" + new Gson().toJson(new ArtigoPeriodicoRepository(ApplicationUtilies.catchEntityManager()).getSeparaPorAnoTodosCampus()) + ")");
                }
            }
            if (this.subGrupoSelecionado.equals("Capítulo de Livro Publicado")) {
                if (this.campus.getSelecionado() != null) {
                    if (this.selecionado != null) {
                        context.execute("pizza(" + new Gson().toJson(new CapituloLivroPublicadoRepository(ApplicationUtilies.catchEntityManager()).getSeparaPorAno(this.selecionado.getId())) + ")");
                        context.execute("barra(" + new Gson().toJson(new CapituloLivroPublicadoRepository(ApplicationUtilies.catchEntityManager()).getSeparaPorAno(this.selecionado.getId())) + ")");
                    } else {
                        context.execute("pizza(" + new Gson().toJson(new CapituloLivroPublicadoRepository(ApplicationUtilies.catchEntityManager()).getSeparaPorAnoPorCampus(this.campus.getSelecionado().getId())) + ")");
                        context.execute("barra(" + new Gson().toJson(new CapituloLivroPublicadoRepository(ApplicationUtilies.catchEntityManager()).getSeparaPorAnoPorCampus(this.campus.getSelecionado().getId())) + ")");
                    }
                } else {
                    context.execute("pizza(" + new Gson().toJson(new CapituloLivroPublicadoRepository(ApplicationUtilies.catchEntityManager()).getSeparaPorAnoTodosCampus()) + ")");
                    context.execute("barra(" + new Gson().toJson(new CapituloLivroPublicadoRepository(ApplicationUtilies.catchEntityManager()).getSeparaPorAnoTodosCampus()) + ")");
                }
            }

            if (this.subGrupoSelecionado.equals("Texto Em Jornal de Notícia")) {
                if (this.campus.getSelecionado() != null) {
                    if (this.selecionado != null) {
                        context.execute("pizza(" + new Gson().toJson(new TextoJornalNoticiaRepository(ApplicationUtilies.catchEntityManager()).getSeparaPorAno(this.selecionado.getId())) + ")");
                        context.execute("barra(" + new Gson().toJson(new TextoJornalNoticiaRepository(ApplicationUtilies.catchEntityManager()).getSeparaPorAno(this.selecionado.getId())) + ")");
                    } else {
                        context.execute("pizza(" + new Gson().toJson(new TextoJornalNoticiaRepository(ApplicationUtilies.catchEntityManager()).getSeparaPorAnoPorCampus(this.campus.getSelecionado().getId())) + ")");
                        context.execute("barra(" + new Gson().toJson(new TextoJornalNoticiaRepository(ApplicationUtilies.catchEntityManager()).getSeparaPorAnoPorCampus(this.campus.getSelecionado().getId())) + ")");
                    }
                } else {
                    context.execute("pizza(" + new Gson().toJson(new TextoJornalNoticiaRepository(ApplicationUtilies.catchEntityManager()).getSeparaPorAnoTodosCampus()) + ")");
                    context.execute("barra(" + new Gson().toJson(new TextoJornalNoticiaRepository(ApplicationUtilies.catchEntityManager()).getSeparaPorAnoTodosCampus()) + ")");
                }
            }
            if (this.subGrupoSelecionado.equals("Trabalho Completo em Congresso")) {
                if (this.campus.getSelecionado() != null) {
                    if (this.selecionado != null) {
                        context.execute("pizza(" + new Gson().toJson(new TrabalhoCompletoCongressoRepository(ApplicationUtilies.catchEntityManager()).getSeparaPorAno(this.selecionado.getId())) + ")");
                        context.execute("barra(" + new Gson().toJson(new TrabalhoCompletoCongressoRepository(ApplicationUtilies.catchEntityManager()).getSeparaPorAno(this.selecionado.getId())) + ")");
                    } else {
                        context.execute("pizza(" + new Gson().toJson(new TrabalhoCompletoCongressoRepository(ApplicationUtilies.catchEntityManager()).getSeparaPorAnoPorCampus(this.campus.getSelecionado().getId())) + ")");
                        context.execute("barra(" + new Gson().toJson(new TrabalhoCompletoCongressoRepository(ApplicationUtilies.catchEntityManager()).getSeparaPorAnoPorCampus(this.campus.getSelecionado().getId())) + ")");
                    }
                } else {
                    context.execute("pizza(" + new Gson().toJson(new TrabalhoCompletoCongressoRepository(ApplicationUtilies.catchEntityManager()).getSeparaPorAnoTodosCampus()) + ")");
                    context.execute("barra(" + new Gson().toJson(new TrabalhoCompletoCongressoRepository(ApplicationUtilies.catchEntityManager()).getSeparaPorAnoTodosCampus()) + ")");
                }
            }
            if (this.subGrupoSelecionado.equals("Livro Publicado")) {
                if (this.campus.getSelecionado() != null) {
                    if (this.selecionado != null) {
                        context.execute("pizza(" + new Gson().toJson(new LivroPublicadoRepository(ApplicationUtilies.catchEntityManager()).getSeparaPorAno(this.selecionado.getId())) + ")");
                        context.execute("barra(" + new Gson().toJson(new LivroPublicadoRepository(ApplicationUtilies.catchEntityManager()).getSeparaPorAno(this.selecionado.getId())) + ")");
                    } else {
                        context.execute("pizza(" + new Gson().toJson(new LivroPublicadoRepository(ApplicationUtilies.catchEntityManager()).getSeparaPorAnoPorCampus(this.campus.getSelecionado().getId())) + ")");
                        context.execute("barra(" + new Gson().toJson(new LivroPublicadoRepository(ApplicationUtilies.catchEntityManager()).getSeparaPorAnoPorCampus(this.campus.getSelecionado().getId())) + ")");
                    }
                } else {
                    context.execute("pizza(" + new Gson().toJson(new LivroPublicadoRepository(ApplicationUtilies.catchEntityManager()).getSeparaPorAnoTodosCampus()) + ")");
                    context.execute("barra(" + new Gson().toJson(new LivroPublicadoRepository(ApplicationUtilies.catchEntityManager()).getSeparaPorAnoTodosCampus()) + ")");
                }
            }
            if (this.subGrupoSelecionado.equals("Outro Tipo Bibliográfico")) {
                if (this.campus.getSelecionado() != null) {
                    if (this.selecionado != null) {
                        context.execute("pizza(" + new Gson().toJson(new OutroTipoBibliograficoRepository(ApplicationUtilies.catchEntityManager()).getSeparaPorAno(this.selecionado.getId())) + ")");
                        context.execute("barra(" + new Gson().toJson(new OutroTipoBibliograficoRepository(ApplicationUtilies.catchEntityManager()).getSeparaPorAno(this.selecionado.getId())) + ")");
                    } else {
                        context.execute("pizza(" + new Gson().toJson(new OutroTipoBibliograficoRepository(ApplicationUtilies.catchEntityManager()).getSeparaPorAnoPorCampus(this.campus.getSelecionado().getId())) + ")");
                        context.execute("barra(" + new Gson().toJson(new OutroTipoBibliograficoRepository(ApplicationUtilies.catchEntityManager()).getSeparaPorAnoPorCampus(this.campus.getSelecionado().getId())) + ")");
                    }
                } else {
                    context.execute("pizza(" + new Gson().toJson(new OutroTipoBibliograficoRepository(ApplicationUtilies.catchEntityManager()).getSeparaPorAnoTodosCampus()) + ")");
                    context.execute("barra(" + new Gson().toJson(new OutroTipoBibliograficoRepository(ApplicationUtilies.catchEntityManager()).getSeparaPorAnoTodosCampus()) + ")");
                }
            }
        }

        if (this.grupoSelecionado.equals("orientacoes")) {
            if (this.subGrupoSelecionado.equals("Orientações Concluídas")) {
                if (this.campus.getSelecionado() != null) {
                    if (this.selecionado != null) {
                        context.execute("pizza(" + new Gson().toJson(new OrientacoesConcluidasRepository(ApplicationUtilies.catchEntityManager()).getSeparaPorAno(this.selecionado.getId())) + ")");
                        context.execute("barra(" + new Gson().toJson(new OrientacoesConcluidasRepository(ApplicationUtilies.catchEntityManager()).getSeparaPorAno(this.selecionado.getId())) + ")");
                    } else {
                        context.execute("pizza(" + new Gson().toJson(new OrientacoesConcluidasRepository(ApplicationUtilies.catchEntityManager()).getSeparaPorAnoPorCampus(this.campus.getSelecionado().getId())) + ")");
                        context.execute("barra(" + new Gson().toJson(new OrientacoesConcluidasRepository(ApplicationUtilies.catchEntityManager()).getSeparaPorAnoPorCampus(this.campus.getSelecionado().getId())) + ")");
                    }
                } else {
                    context.execute("pizza(" + new Gson().toJson(new OrientacoesConcluidasRepository(ApplicationUtilies.catchEntityManager()).getSeparaPorAnoTodosCampus()) + ")");
                    context.execute("barra(" + new Gson().toJson(new OrientacoesConcluidasRepository(ApplicationUtilies.catchEntityManager()).getSeparaPorAnoTodosCampus()) + ")");
                }
            }
            if (this.subGrupoSelecionado.equals("Orientações em Andamento")) {
                if (this.campus.getSelecionado() != null) {
                    if (this.selecionado != null) {
                        context.execute("pizza(" + new Gson().toJson(new OrientacoesAndamentoRepository(ApplicationUtilies.catchEntityManager()).getSeparaPorAno(this.selecionado.getId())) + ")");
                        context.execute("barra(" + new Gson().toJson(new OrientacoesAndamentoRepository(ApplicationUtilies.catchEntityManager()).getSeparaPorAno(this.selecionado.getId())) + ")");
                    } else {
                        context.execute("pizza(" + new Gson().toJson(new OrientacoesAndamentoRepository(ApplicationUtilies.catchEntityManager()).getSeparaPorAnoPorCampus(this.campus.getSelecionado().getId())) + ")");
                        context.execute("barra(" + new Gson().toJson(new OrientacoesAndamentoRepository(ApplicationUtilies.catchEntityManager()).getSeparaPorAnoPorCampus(this.campus.getSelecionado().getId())) + ")");
                    }
                } else {
                    context.execute("pizza(" + new Gson().toJson(new OrientacoesAndamentoRepository(ApplicationUtilies.catchEntityManager()).getSeparaPorAnoTodosCampus()) + ")");
                    context.execute("barra(" + new Gson().toJson(new OrientacoesAndamentoRepository(ApplicationUtilies.catchEntityManager()).getSeparaPorAnoTodosCampus()) + ")");
                }
            }
        }

        if (this.grupoSelecionado.equals(
                "eventos")) {
            if (this.subGrupoSelecionado.equals("Participação de Eventos")) {
                if (this.campus.getSelecionado() != null) {
                    if (this.selecionado != null) {
                        context.execute("pizza(" + new Gson().toJson(new ParticipacaoEventoRepository(ApplicationUtilies.catchEntityManager()).getSeparaPorAno(this.selecionado.getId())) + ")");
                        context.execute("barra(" + new Gson().toJson(new ParticipacaoEventoRepository(ApplicationUtilies.catchEntityManager()).getSeparaPorAno(this.selecionado.getId())) + ")");
                    } else {
                        context.execute("pizza(" + new Gson().toJson(new ParticipacaoEventoRepository(ApplicationUtilies.catchEntityManager()).getSeparaPorAnoPorCampus(this.campus.getSelecionado().getId())) + ")");
                        context.execute("barra(" + new Gson().toJson(new ParticipacaoEventoRepository(ApplicationUtilies.catchEntityManager()).getSeparaPorAnoPorCampus(this.campus.getSelecionado().getId())) + ")");
                    }
                } else {
                    context.execute("pizza(" + new Gson().toJson(new ParticipacaoEventoRepository(ApplicationUtilies.catchEntityManager()).getSeparaPorAnoTodosCampus()) + ")");
                    context.execute("barra(" + new Gson().toJson(new ParticipacaoEventoRepository(ApplicationUtilies.catchEntityManager()).getSeparaPorAnoTodosCampus()) + ")");
                }
            }
            if (this.subGrupoSelecionado.equals("Organização de Eventos")) {
                if (this.campus.getSelecionado() != null) {
                    if (this.selecionado != null) {
                        context.execute("pizza(" + new Gson().toJson(new OrganizacaoEventoRepository(ApplicationUtilies.catchEntityManager()).getSeparaPorAno(this.selecionado.getId())) + ")");
                        context.execute("barra(" + new Gson().toJson(new OrganizacaoEventoRepository(ApplicationUtilies.catchEntityManager()).getSeparaPorAno(this.selecionado.getId())) + ")");
                    } else {
                        context.execute("pizza(" + new Gson().toJson(new OrganizacaoEventoRepository(ApplicationUtilies.catchEntityManager()).getSeparaPorAnoPorCampus(this.campus.getSelecionado().getId())) + ")");
                        context.execute("barra(" + new Gson().toJson(new OrganizacaoEventoRepository(ApplicationUtilies.catchEntityManager()).getSeparaPorAnoPorCampus(this.campus.getSelecionado().getId())) + ")");
                    }
                } else {
                    context.execute("pizza(" + new Gson().toJson(new OrganizacaoEventoRepository(ApplicationUtilies.catchEntityManager()).getSeparaPorAnoTodosCampus()) + ")");
                    context.execute("barra(" + new Gson().toJson(new OrganizacaoEventoRepository(ApplicationUtilies.catchEntityManager()).getSeparaPorAnoTodosCampus()) + ")");
                }
            }
        }
    }

    public String getGrupoSelecionado() {
        return grupoSelecionado;
    }

    public void setGrupoSelecionado(String grupoSelecionado) {
        this.grupoSelecionado = grupoSelecionado;
    }

    public String getSubGrupoSelecionado() {
        return subGrupoSelecionado;
    }

    public void setSubGrupoSelecionado(String subGrupoSelecionado) {
        this.subGrupoSelecionado = subGrupoSelecionado;
    }

    public List<String> carregaSubGupo() {
        if (this.grupoSelecionado != null) {
            if (this.grupoSelecionado.equals("prodTecnicas")) {
                return this.producaoTecnica;
            }
            if (this.grupoSelecionado.equals("prodBiblio")) {
                return this.producaoBibliografica;
            }
            if (this.grupoSelecionado.equals("orientacoes")) {
                return this.orientacoes;
            }
            return this.eventos;
        } else {
            return null;
        }
    }

    private void iniciaSubGrupos() {
        this.producaoTecnica.add("Softwares Sem Patente");
        this.producaoTecnica.add("Trabalhos Técnicos");
        this.producaoTecnica.add("Produtos Tecnológicos");
        this.producaoTecnica.add("Outros Tipos Técnicos");

        this.producaoBibliografica.add("Apresentação de Trabalho");
        this.producaoBibliografica.add("Resumo em Congresso");
        this.producaoBibliografica.add("Resumo Expandido em Congresso");
        this.producaoBibliografica.add("Artigo Aceito");
        this.producaoBibliografica.add("Artigo em Periódico");
        this.producaoBibliografica.add("Capítulo de Livro Publicado");
        this.producaoBibliografica.add("Texto Em Jornal de Notícia");
        this.producaoBibliografica.add("Trabalho Completo em Congresso");
        this.producaoBibliografica.add("Livro Publicado");
        this.producaoBibliografica.add("Outro Tipo Bibliográfico");

        this.orientacoes.add("Orientações Concluídas");
        this.orientacoes.add("Orientações em Andamento");

        this.eventos.add("Participação de Eventos");
        this.eventos.add("Organização de Eventos");
    }

    public List<String> getProducaoTecnica() {
        return producaoTecnica;
    }

    public void setProducaoTecnica(List<String> producaoTecnica) {
        this.producaoTecnica = producaoTecnica;
    }

    public List<String> getProducaoBibliografica() {
        return producaoBibliografica;
    }

    public void setProducaoBibliografica(List<String> producaoBibliografica) {
        this.producaoBibliografica = producaoBibliografica;
    }

    public List<String> getEventos() {
        return eventos;
    }

    public void setEventos(List<String> eventos) {
        this.eventos = eventos;
    }

    public List<String> getOrientacoes() {
        return orientacoes;
    }

    public void setOrientacoes(List<String> orientacoes) {
        this.orientacoes = orientacoes;
    }

    public boolean isCurso() {
        return curso;
    }

    public void setCurso(boolean curso) {
        this.curso = curso;
    }
}
