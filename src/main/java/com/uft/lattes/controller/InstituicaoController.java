/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uft.lattes.controller;

import com.google.gson.Gson;
import com.uft.lattes.repository.InstituicaoRepository;
import com.uft.lattes.util.ApplicationUtilies;
import java.io.Serializable;
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
public class InstituicaoController implements Serializable {

    String opcaoRadioBox;
    @Inject
    CampusController campus;
    @Inject
    CursoController curso;

    public InstituicaoController() {
    }

    public String getOpcaoRadioBox() {
        return opcaoRadioBox;
    }

    public void setOpcaoRadioBox(String opcaoRadioBox) {
        this.opcaoRadioBox = opcaoRadioBox;
    }

    public boolean checkTitulacao() {
        if (this.opcaoRadioBox != null) {
            if (this.opcaoRadioBox.equals("titulacao")) {
                return true;
            }
        }
        return false;
    }

    public boolean checkTitulacaoParaView() {
        if (this.opcaoRadioBox != null) {
            if (this.opcaoRadioBox.equals("titulacao")) {
                return false;
            }
        }
        return true;
    }

    public boolean checkGrandeArea() {
        if (this.opcaoRadioBox != null) {
            if (this.opcaoRadioBox.equals("grandeArea")) {
                return true;
            }
        }
        return false;
    }

    public boolean checkGrandeAreaParaView() {
        if (this.opcaoRadioBox != null) {
            if (this.opcaoRadioBox.equals("grandeArea")) {
                return false;
            }
        }
        return true;
    }

    public void loadTitulacaoTodosCampus() {
        if (this.curso.getGrupoSelecionado() != null) {
            if (this.curso.getGrupoSelecionado().equals("graduados")) {
                RequestContext context = RequestContext.getCurrentInstance();
                context.execute("novaPizza(" + new Gson().toJson(new InstituicaoRepository(
                        ApplicationUtilies.catchEntityManager()).getTitulacaoTodosCampus(
                        this.curso.getGrupoSelecionado())) + "," + 1 + ")");
            }
            if (this.curso.getGrupoSelecionado().equals("especialistas")) {
                RequestContext context = RequestContext.getCurrentInstance();
                context.execute("novaPizza(" + new Gson().toJson(new InstituicaoRepository(
                        ApplicationUtilies.catchEntityManager()).getTitulacaoTodosCampus(
                        this.curso.getGrupoSelecionado())) + "," + 2 + ")");
            }
            if (this.curso.getGrupoSelecionado().equals("mestres")) {
                RequestContext context = RequestContext.getCurrentInstance();
                context.execute("novaPizza(" + new Gson().toJson(new InstituicaoRepository(
                        ApplicationUtilies.catchEntityManager()).getTitulacaoTodosCampus(
                        this.curso.getGrupoSelecionado())) + "," + 3 + ")");
            }
            if (this.curso.getGrupoSelecionado().equals("doutores")) {
                RequestContext context = RequestContext.getCurrentInstance();
                context.execute("novaPizza(" + new Gson().toJson(new InstituicaoRepository(
                        ApplicationUtilies.catchEntityManager()).getTitulacaoTodosCampus(
                        this.curso.getGrupoSelecionado())) + "," + 4 + ")");
            }
        }
    }

    public void loadGrandeAreaTodosCampus() {
        if (this.curso.getGrupoSelecionado() != null) {
            if (this.curso.getGrupoSelecionado().equals("agrarias")) {
                RequestContext context = RequestContext.getCurrentInstance();
                context.execute("novaPizza(" + new Gson().toJson(new InstituicaoRepository(
                        ApplicationUtilies.catchEntityManager()).getGrandeAreaTodosCampus(
                        this.curso.getGrupoSelecionado())) + "," + 5 + ")");
            } else if (this.curso.getGrupoSelecionado().equals("biologicas")) {
                RequestContext context = RequestContext.getCurrentInstance();
                context.execute("novaPizza(" + new Gson().toJson(new InstituicaoRepository(
                        ApplicationUtilies.catchEntityManager()).getGrandeAreaTodosCampus(
                        this.curso.getGrupoSelecionado())) + "," + 6 + ")");
            } else if (this.curso.getGrupoSelecionado().equals("saude")) {
                RequestContext context = RequestContext.getCurrentInstance();
                context.execute("novaPizza(" + new Gson().toJson(new InstituicaoRepository(
                        ApplicationUtilies.catchEntityManager()).getGrandeAreaTodosCampus(
                        this.curso.getGrupoSelecionado())) + "," + 7 + ")");
            } else if (this.curso.getGrupoSelecionado().equals("exatas")) {
                RequestContext context = RequestContext.getCurrentInstance();
                context.execute("novaPizza(" + new Gson().toJson(new InstituicaoRepository(
                        ApplicationUtilies.catchEntityManager()).getGrandeAreaTodosCampus(
                        this.curso.getGrupoSelecionado())) + "," + 8 + ")");
            } else if (this.curso.getGrupoSelecionado().equals("humanas")) {
                RequestContext context = RequestContext.getCurrentInstance();
                context.execute("novaPizza(" + new Gson().toJson(new InstituicaoRepository(
                        ApplicationUtilies.catchEntityManager()).getGrandeAreaTodosCampus(
                        this.curso.getGrupoSelecionado())) + "," + 9 + ")");
            } else if (this.curso.getGrupoSelecionado().equals("sociais")) {
                RequestContext context = RequestContext.getCurrentInstance();
                context.execute("novaPizza(" + new Gson().toJson(new InstituicaoRepository(
                        ApplicationUtilies.catchEntityManager()).getGrandeAreaTodosCampus(
                        this.curso.getGrupoSelecionado())) + "," + 10 + ")");
            } else if (this.curso.getGrupoSelecionado().equals("engenharias")) {
                RequestContext context = RequestContext.getCurrentInstance();
                context.execute("novaPizza(" + new Gson().toJson(new InstituicaoRepository(
                        ApplicationUtilies.catchEntityManager()).getGrandeAreaTodosCampus(
                        this.curso.getGrupoSelecionado())) + "," + 11 + ")");
            } else if (this.curso.getGrupoSelecionado().equals("linguistica")) {
                RequestContext context = RequestContext.getCurrentInstance();
                context.execute("novaPizza(" + new Gson().toJson(new InstituicaoRepository(
                        ApplicationUtilies.catchEntityManager()).getGrandeAreaTodosCampus(
                        this.curso.getGrupoSelecionado())) + "," + 12 + ")");
            } else if (this.curso.getGrupoSelecionado().equals("multidisciplinar")) {
                RequestContext context = RequestContext.getCurrentInstance();
                context.execute("novaPizza(" + new Gson().toJson(new InstituicaoRepository(
                        ApplicationUtilies.catchEntityManager()).getGrandeAreaTodosCampus(
                        this.curso.getGrupoSelecionado())) + "," + 13 + ")");
            }
        }
    }

    public void loadTitulacaoPorCampus() {
        if (this.curso.getGrupoSelecionado() != null) {
            if (this.curso.getGrupoSelecionado().equals("graduados")) {
                RequestContext context = RequestContext.getCurrentInstance();
                context.execute("novaPizza(" + new Gson().toJson(new InstituicaoRepository(
                        ApplicationUtilies.catchEntityManager()).getTitulacaoPorCampus(
                        this.curso.getGrupoSelecionado(), this.campus.getSelecionado().getId())) + "," + 1 + ")");
            }
            if (this.curso.getGrupoSelecionado().equals("especialistas")) {
                RequestContext context = RequestContext.getCurrentInstance();
                context.execute("novaPizza(" + new Gson().toJson(new InstituicaoRepository(
                        ApplicationUtilies.catchEntityManager()).getTitulacaoPorCampus(
                        this.curso.getGrupoSelecionado(), this.campus.getSelecionado().getId())) + "," + 2 + ")");
            }
            if (this.curso.getGrupoSelecionado().equals("mestres")) {
                RequestContext context = RequestContext.getCurrentInstance();
                context.execute("novaPizza(" + new Gson().toJson(new InstituicaoRepository(
                        ApplicationUtilies.catchEntityManager()).getTitulacaoPorCampus(
                        this.curso.getGrupoSelecionado(), this.campus.getSelecionado().getId())) + "," + 3 + ")");
            }
            if (this.curso.getGrupoSelecionado().equals("doutores")) {
                RequestContext context = RequestContext.getCurrentInstance();
                context.execute("novaPizza(" + new Gson().toJson(new InstituicaoRepository(
                        ApplicationUtilies.catchEntityManager()).getTitulacaoPorCampus(
                        this.curso.getGrupoSelecionado(), this.campus.getSelecionado().getId())) + "," + 4 + ")");
            }
        }
    }

    public void loadGrandeAreaPorCampus() {
        if (this.curso.getGrupoSelecionado() != null) {
            if (this.curso.getGrupoSelecionado().equals("agrarias")) {
                RequestContext context = RequestContext.getCurrentInstance();
                context.execute("novaPizza(" + new Gson().toJson(new InstituicaoRepository(
                        ApplicationUtilies.catchEntityManager()).getGrandeAreaPorCampus(
                        this.curso.getGrupoSelecionado(), this.campus.getSelecionado().getId())) + "," + 5 + ")");
            } else if (this.curso.getGrupoSelecionado().equals("biologicas")) {
                RequestContext context = RequestContext.getCurrentInstance();
                context.execute("novaPizza(" + new Gson().toJson(new InstituicaoRepository(
                        ApplicationUtilies.catchEntityManager()).getGrandeAreaPorCampus(
                        this.curso.getGrupoSelecionado(), this.campus.getSelecionado().getId())) + "," + 6 + ")");
            } else if (this.curso.getGrupoSelecionado().equals("saude")) {
                RequestContext context = RequestContext.getCurrentInstance();
                context.execute("novaPizza(" + new Gson().toJson(new InstituicaoRepository(
                        ApplicationUtilies.catchEntityManager()).getGrandeAreaPorCampus(
                        this.curso.getGrupoSelecionado(), this.campus.getSelecionado().getId())) + "," + 7 + ")");
            } else if (this.curso.getGrupoSelecionado().equals("exatas")) {
                RequestContext context = RequestContext.getCurrentInstance();
                context.execute("novaPizza(" + new Gson().toJson(new InstituicaoRepository(
                        ApplicationUtilies.catchEntityManager()).getGrandeAreaPorCampus(
                        this.curso.getGrupoSelecionado(), this.campus.getSelecionado().getId())) + "," + 8 + ")");
            } else if (this.curso.getGrupoSelecionado().equals("humanas")) {
                RequestContext context = RequestContext.getCurrentInstance();
                context.execute("novaPizza(" + new Gson().toJson(new InstituicaoRepository(
                        ApplicationUtilies.catchEntityManager()).getGrandeAreaPorCampus(
                        this.curso.getGrupoSelecionado(), this.campus.getSelecionado().getId())) + "," + 9 + ")");
            } else if (this.curso.getGrupoSelecionado().equals("sociais")) {
                RequestContext context = RequestContext.getCurrentInstance();
                context.execute("novaPizza(" + new Gson().toJson(new InstituicaoRepository(
                        ApplicationUtilies.catchEntityManager()).getGrandeAreaPorCampus(
                        this.curso.getGrupoSelecionado(), this.campus.getSelecionado().getId())) + "," + 10 + ")");
            } else if (this.curso.getGrupoSelecionado().equals("engenharias")) {
                RequestContext context = RequestContext.getCurrentInstance();
                context.execute("novaPizza(" + new Gson().toJson(new InstituicaoRepository(
                        ApplicationUtilies.catchEntityManager()).getGrandeAreaPorCampus(
                        this.curso.getGrupoSelecionado(), this.campus.getSelecionado().getId())) + "," + 11 + ")");
            } else if (this.curso.getGrupoSelecionado().equals("linguistica")) {
                RequestContext context = RequestContext.getCurrentInstance();
                context.execute("novaPizza(" + new Gson().toJson(new InstituicaoRepository(
                        ApplicationUtilies.catchEntityManager()).getGrandeAreaPorCampus(
                        this.curso.getGrupoSelecionado(), this.campus.getSelecionado().getId())) + "," + 12 + ")");
            } else if (this.curso.getGrupoSelecionado().equals("multidisciplinar")) {
                RequestContext context = RequestContext.getCurrentInstance();
                context.execute("novaPizza(" + new Gson().toJson(new InstituicaoRepository(
                        ApplicationUtilies.catchEntityManager()).getGrandeAreaPorCampus(
                        this.curso.getGrupoSelecionado(), this.campus.getSelecionado().getId())) + "," + 13 + ")");
            }
        }
    }

    public void loadTitulacaoPorCurso() {
        if (this.curso.getGrupoSelecionado() != null) {
            if (this.curso.getGrupoSelecionado().equals("graduados")) {
                RequestContext context = RequestContext.getCurrentInstance();
                context.execute("novaPizza(" + new Gson().toJson(new InstituicaoRepository(
                        ApplicationUtilies.catchEntityManager()).getTitulacaoPorCurso(
                        this.curso.getGrupoSelecionado(), this.campus.getSelecionado().getId(), this.curso.getSelecionado().getId())) + "," + 1 + ")");
            }
            if (this.curso.getGrupoSelecionado().equals("especialistas")) {
                RequestContext context = RequestContext.getCurrentInstance();
                context.execute("novaPizza(" + new Gson().toJson(new InstituicaoRepository(
                        ApplicationUtilies.catchEntityManager()).getTitulacaoPorCurso(
                        this.curso.getGrupoSelecionado(), this.campus.getSelecionado().getId(), this.curso.getSelecionado().getId())) + "," + 2 + ")");
            }
            if (this.curso.getGrupoSelecionado().equals("mestres")) {
                RequestContext context = RequestContext.getCurrentInstance();
                context.execute("novaPizza(" + new Gson().toJson(new InstituicaoRepository(
                        ApplicationUtilies.catchEntityManager()).getTitulacaoPorCurso(
                        this.curso.getGrupoSelecionado(), this.campus.getSelecionado().getId(), this.curso.getSelecionado().getId())) + "," + 3 + ")");
            }
            if (this.curso.getGrupoSelecionado().equals("doutores")) {
                RequestContext context = RequestContext.getCurrentInstance();
                context.execute("novaPizza(" + new Gson().toJson(new InstituicaoRepository(
                        ApplicationUtilies.catchEntityManager()).getTitulacaoPorCurso(
                        this.curso.getGrupoSelecionado(), this.campus.getSelecionado().getId(), this.curso.getSelecionado().getId())) + "," + 4 + ")");
            }
        }
    }

    public void loadGrandeAreaPorCurso() {
        if (this.curso.getGrupoSelecionado() != null) {
            if (this.curso.getGrupoSelecionado().equals("agrarias")) {
                RequestContext context = RequestContext.getCurrentInstance();
                context.execute("novaPizza(" + new Gson().toJson(new InstituicaoRepository(
                        ApplicationUtilies.catchEntityManager()).getGrandeAreaPorCurso(
                        this.curso.getGrupoSelecionado(), this.campus.getSelecionado().getId(), this.curso.getSelecionado().getId())) + "," + 5 + ")");
            } else if (this.curso.getGrupoSelecionado().equals("biologicas")) {
                RequestContext context = RequestContext.getCurrentInstance();
                context.execute("novaPizza(" + new Gson().toJson(new InstituicaoRepository(
                        ApplicationUtilies.catchEntityManager()).getGrandeAreaPorCurso(
                        this.curso.getGrupoSelecionado(), this.campus.getSelecionado().getId(), this.curso.getSelecionado().getId())) + "," + 6 + ")");
            } else if (this.curso.getGrupoSelecionado().equals("saude")) {
                RequestContext context = RequestContext.getCurrentInstance();
                context.execute("novaPizza(" + new Gson().toJson(new InstituicaoRepository(
                        ApplicationUtilies.catchEntityManager()).getGrandeAreaPorCurso(
                        this.curso.getGrupoSelecionado(), this.campus.getSelecionado().getId(), this.curso.getSelecionado().getId())) + "," + 7 + ")");
            } else if (this.curso.getGrupoSelecionado().equals("exatas")) {
                RequestContext context = RequestContext.getCurrentInstance();
                context.execute("novaPizza(" + new Gson().toJson(new InstituicaoRepository(
                        ApplicationUtilies.catchEntityManager()).getGrandeAreaPorCurso(
                        this.curso.getGrupoSelecionado(), this.campus.getSelecionado().getId(), this.curso.getSelecionado().getId())) + "," + 8 + ")");
            } else if (this.curso.getGrupoSelecionado().equals("humanas")) {
                RequestContext context = RequestContext.getCurrentInstance();
                context.execute("novaPizza(" + new Gson().toJson(new InstituicaoRepository(
                        ApplicationUtilies.catchEntityManager()).getGrandeAreaPorCurso(
                        this.curso.getGrupoSelecionado(), this.campus.getSelecionado().getId(), this.curso.getSelecionado().getId())) + "," + 9 + ")");
            } else if (this.curso.getGrupoSelecionado().equals("sociais")) {
                RequestContext context = RequestContext.getCurrentInstance();
                context.execute("novaPizza(" + new Gson().toJson(new InstituicaoRepository(
                        ApplicationUtilies.catchEntityManager()).getGrandeAreaPorCurso(
                        this.curso.getGrupoSelecionado(), this.campus.getSelecionado().getId(), this.curso.getSelecionado().getId())) + "," + 10 + ")");
            } else if (this.curso.getGrupoSelecionado().equals("engenharias")) {
                RequestContext context = RequestContext.getCurrentInstance();
                context.execute("novaPizza(" + new Gson().toJson(new InstituicaoRepository(
                        ApplicationUtilies.catchEntityManager()).getGrandeAreaPorCurso(
                        this.curso.getGrupoSelecionado(), this.campus.getSelecionado().getId(), this.curso.getSelecionado().getId())) + "," + 11 + ")");
            } else if (this.curso.getGrupoSelecionado().equals("linguistica")) {
                RequestContext context = RequestContext.getCurrentInstance();
                context.execute("novaPizza(" + new Gson().toJson(new InstituicaoRepository(
                        ApplicationUtilies.catchEntityManager()).getGrandeAreaPorCurso(
                        this.curso.getGrupoSelecionado(), this.campus.getSelecionado().getId(), this.curso.getSelecionado().getId())) + "," + 12 + ")");
            } else if (this.curso.getGrupoSelecionado().equals("multidisciplinar")) {
                RequestContext context = RequestContext.getCurrentInstance();
                context.execute("novaPizza(" + new Gson().toJson(new InstituicaoRepository(
                        ApplicationUtilies.catchEntityManager()).getGrandeAreaPorCurso(
                        this.curso.getGrupoSelecionado(), this.campus.getSelecionado().getId(), this.curso.getSelecionado().getId())) + "," + 13 + ")");
            }
        }
    }

    public void plotarGrafico() {
        if (this.campus.isCampus() && this.curso.isCurso() == false) { // somente campus selecionado
            if (this.checkGrandeArea()) {
                loadGrandeAreaPorCampus();
            } else if (this.checkTitulacao()) {
                loadTitulacaoPorCampus();
            }
        } else if (this.curso.getSelecionado() != null) {
            if (this.checkGrandeArea()) {
                loadGrandeAreaPorCurso();
            } else if (this.checkTitulacao()) {
                loadTitulacaoPorCurso();
            }
        } else if ((!this.campus.isCampus()) && (!this.curso.isCurso())) { // nenhum selecionado
            if (this.checkGrandeArea()) {
                loadGrandeAreaTodosCampus();
            } else if (this.checkTitulacao()) {
                loadTitulacaoTodosCampus();
            }
        }
    }
}
