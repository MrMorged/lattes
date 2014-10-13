/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uft.lattes.repository;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author igor
 */
public class InstituicaoRepository {

    private EntityManager entityManager;

    public InstituicaoRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List getTitulacaoPorCampus(String titulacao, Long id) {
        Query query;
        if (titulacao.equals("graduados")) {
            query = this.entityManager.createNativeQuery("SELECT count(*) as titulacao FROM("
                    + "SELECT distinct on (T.membro_id_lattes) T.membro_id_lattes "
                    + "FROM formacao_academica as T "
                    + "INNER JOIN atuacao_profissional as P on T.membro_id_lattes = P.membro_id_lattes "
                    + "INNER JOIN membro as M on T.membro_id_lattes = M.id_lattes "
                    + "INNER JOIN curso as C on M.id_curso = C.id_curso "
                    + "INNER JOIN campus as D on C.campus_id_campus = D.id_campus "
                    + "WHERE P.sigla = 'UFT' "
                    + "AND T.tipo ilike 'graduação %' "
                    + "AND T.ano_conclusao != '' "
                    + "AND P.ano_saida = 'Atual' "
                    + "AND D.id_campus = :id "
                    + "order by T.membro_id_lattes) as titulacao ;");
            query.setParameter("id", id);
            return query.getResultList();
        }
        if (titulacao.equals("especialistas")) {
            query = this.entityManager.createNativeQuery("SELECT count(*) as titulacao FROM("
                    + "SELECT distinct on (T.membro_id_lattes) T.membro_id_lattes "
                    + "FROM formacao_academica as T "
                    + "INNER JOIN atuacao_profissional as P on T.membro_id_lattes = P.membro_id_lattes "
                    + "INNER JOIN membro as M on T.membro_id_lattes = M.id_lattes "
                    + "INNER JOIN curso as C on M.id_curso = C.id_curso "
                    + "INNER JOIN campus as D on C.campus_id_campus = D.id_campus "
                    + "WHERE P.sigla = 'UFT' "
                    + "AND T.tipo ilike 'especialização %' "
                    + "AND T.ano_conclusao != '' "
                    + "AND P.ano_saida = 'Atual' "
                    + "AND D.id_campus = :id "
                    + "order by T.membro_id_lattes) as titulacao ;");
            query.setParameter("id", id);
            return query.getResultList();
        }
        if (titulacao.equals("mestres")) {
            query = this.entityManager.createNativeQuery("SELECT count(*) as titulacao FROM("
                    + "SELECT distinct on (T.membro_id_lattes) T.membro_id_lattes "
                    + "FROM formacao_academica as T "
                    + "INNER JOIN atuacao_profissional as P on T.membro_id_lattes = P.membro_id_lattes "
                    + "INNER JOIN membro as M on T.membro_id_lattes = M.id_lattes "
                    + "INNER JOIN curso as C on M.id_curso = C.id_curso "
                    + "INNER JOIN campus as D on C.campus_id_campus = D.id_campus "
                    + "WHERE P.sigla = 'UFT' "
                    + "AND T.tipo ilike 'mestrado %' "
                    + "AND T.ano_conclusao != '' "
                    + "AND P.ano_saida = 'Atual' "
                    + "AND D.id_campus = :id "
                    + "order by T.membro_id_lattes) as titulacao ;");
            query.setParameter("id", id);
            return query.getResultList();
        }
        if (titulacao.equals("doutores")) {
            query = this.entityManager.createNativeQuery("SELECT count(*) as titulacao FROM("
                    + "SELECT distinct on (T.membro_id_lattes) T.membro_id_lattes "
                    + "FROM formacao_academica as T "
                    + "INNER JOIN atuacao_profissional as P on T.membro_id_lattes = P.membro_id_lattes "
                    + "INNER JOIN membro as M on T.membro_id_lattes = M.id_lattes "
                    + "INNER JOIN curso as C on M.id_curso = C.id_curso "
                    + "INNER JOIN campus as D on C.campus_id_campus = D.id_campus "
                    + "WHERE P.sigla = 'UFT' "
                    + "AND T.tipo ilike 'doutorado %' "
                    + "AND T.ano_conclusao != '' "
                    + "AND P.ano_saida = 'Atual' "
                    + "AND D.id_campus = :id "
                    + "order by T.membro_id_lattes) as titulacao ;");
            query.setParameter("id", id);
            return query.getResultList();
        }
        return null;
    }

    public List getTitulacaoTodosCampus(String titulacao) {
        Query query;
        if (titulacao.equals("graduados")) {
            query = this.entityManager.createNativeQuery("SELECT count(*) as titulacao FROM("
                    + "SELECT distinct on (T.membro_id_lattes) T.membro_id_lattes "
                    + "FROM formacao_academica as T "
                    + "INNER JOIN atuacao_profissional as P on T.membro_id_lattes = P.membro_id_lattes "
                    + "INNER JOIN membro as M on T.membro_id_lattes = M.id_lattes "
                    + "WHERE P.sigla = 'UFT' "
                    + "AND T.tipo ilike 'graduação %' "
                    + "AND T.ano_conclusao != '' "
                    + "AND P.ano_saida = 'Atual' "
                    + "order by T.membro_id_lattes) as titulacao ;");
            return query.getResultList();
        }
        if (titulacao.equals("especialistas")) {
            query = this.entityManager.createNativeQuery("SELECT count(*) as titulacao FROM("
                    + "SELECT distinct on (T.membro_id_lattes) T.membro_id_lattes "
                    + "FROM formacao_academica as T "
                    + "INNER JOIN atuacao_profissional as P on T.membro_id_lattes = P.membro_id_lattes "
                    + "INNER JOIN membro as M on T.membro_id_lattes = M.id_lattes "
                    + "WHERE P.sigla = 'UFT' "
                    + "AND T.tipo ilike 'especialização %' "
                    + "AND T.ano_conclusao != '' "
                    + "AND P.ano_saida = 'Atual' "
                    + "order by T.membro_id_lattes) as titulacao ;");
            return query.getResultList();
        }
        if (titulacao.equals("mestres")) {
            query = this.entityManager.createNativeQuery("SELECT count(*) as titulacao FROM("
                    + "SELECT distinct on (T.membro_id_lattes) T.membro_id_lattes "
                    + "FROM formacao_academica as T "
                    + "INNER JOIN atuacao_profissional as P on T.membro_id_lattes = P.membro_id_lattes "
                    + "INNER JOIN membro as M on T.membro_id_lattes = M.id_lattes "
                    + "WHERE P.sigla = 'UFT' "
                    + "AND T.tipo ilike 'mestrado %' "
                    + "AND T.ano_conclusao != '' "
                    + "AND P.ano_saida = 'Atual' "
                    + "order by T.membro_id_lattes) as titulacao ;");
            return query.getResultList();
        }
        if (titulacao.equals("doutores")) {
            query = this.entityManager.createNativeQuery("SELECT count(*) as titulacao FROM("
                    + "SELECT distinct on (T.membro_id_lattes) T.membro_id_lattes "
                    + "FROM formacao_academica as T "
                    + "INNER JOIN atuacao_profissional as P on T.membro_id_lattes = P.membro_id_lattes "
                    + "INNER JOIN membro as M on T.membro_id_lattes = M.id_lattes "
                    + "WHERE P.sigla = 'UFT' "
                    + "AND T.tipo ilike 'doutorado %' "
                    + "AND T.ano_conclusao != '' "
                    + "AND P.ano_saida = 'Atual' "
                    + "order by T.membro_id_lattes) as titulacao ;");
            return query.getResultList();
        }
        return null;
    }

    public List getTitulacaoPorCurso(String titulacao, Long idCampus, Long idCurso) {
        Query query;
        String aux = "graduação";
        if (titulacao.equals("graduados")) {
            query = this.entityManager.createNativeQuery("SELECT count(*) as titulacao FROM("
                    + "SELECT distinct on (T.membro_id_lattes) T.membro_id_lattes "
                    + "FROM formacao_academica as T "
                    + "INNER JOIN atuacao_profissional as P on T.membro_id_lattes = P.membro_id_lattes "
                    + "INNER JOIN membro as M on T.membro_id_lattes = M.id_lattes "
                    + "INNER JOIN curso as C on M.id_curso = C.id_curso "
                    + "INNER JOIN campus as D on C.campus_id_campus = D.id_campus "
                    + "WHERE P.sigla = 'UFT' "
                    + "AND T.tipo ilike :aux "
                    + "AND T.ano_conclusao != '' "
                    + "AND P.ano_saida = 'Atual' "
                    + "AND C.id_curso = :idCurso "
                    + "AND D.id_campus = :idCampus "
                    + "order by T.membro_id_lattes) as titulacao ;");
            query.setParameter("idCurso", idCurso);
            query.setParameter("idCampus", idCampus);
            query.setParameter("aux", aux + " %");
            return query.getResultList();
        }
        if (titulacao.equals("especialistas")) {
            query = this.entityManager.createNativeQuery("SELECT count(*) as titulacao FROM("
                    + "SELECT distinct on (T.membro_id_lattes) T.membro_id_lattes "
                    + "FROM formacao_academica as T "
                    + "INNER JOIN atuacao_profissional as P on T.membro_id_lattes = P.membro_id_lattes "
                    + "INNER JOIN membro as M on T.membro_id_lattes = M.id_lattes "
                    + "INNER JOIN curso as C on M.id_curso = C.id_curso "
                    + "INNER JOIN campus as D on C.campus_id_campus = D.id_campus "
                    + "WHERE P.sigla = 'UFT' "
                    + "AND T.tipo ilike 'especialização %' "
                    + "AND T.ano_conclusao != '' "
                    + "AND P.ano_saida = 'Atual' "
                    + "AND C.id_curso = :idCurso "
                    + "AND D.id_campus = :idCampus "
                    + "order by T.membro_id_lattes) as titulacao ;");
            query.setParameter("idCurso", idCurso);
            query.setParameter("idCampus", idCampus);
            return query.getResultList();
        }
        if (titulacao.equals("mestres")) {
            query = this.entityManager.createNativeQuery("SELECT count(*) as titulacao FROM("
                    + "SELECT distinct on (T.membro_id_lattes) T.membro_id_lattes "
                    + "FROM formacao_academica as T "
                    + "INNER JOIN atuacao_profissional as P on T.membro_id_lattes = P.membro_id_lattes "
                    + "INNER JOIN membro as M on T.membro_id_lattes = M.id_lattes "
                    + "INNER JOIN curso as C on M.id_curso = C.id_curso "
                    + "INNER JOIN campus as D on C.campus_id_campus = D.id_campus "
                    + "WHERE P.sigla = 'UFT' "
                    + "AND T.tipo ilike 'mestrado %' "
                    + "AND T.ano_conclusao != '' "
                    + "AND P.ano_saida = 'Atual' "
                    + "AND C.id_curso = :idCurso "
                    + "AND D.id_campus = :idCampus "
                    + "order by T.membro_id_lattes) as titulacao ;");
            query.setParameter("idCurso", idCurso);
            query.setParameter("idCampus", idCampus);
            return query.getResultList();
        }
        if (titulacao.equals("doutores")) {
            query = this.entityManager.createNativeQuery("SELECT count(*) as titulacao FROM("
                    + "SELECT distinct on (T.membro_id_lattes) T.membro_id_lattes "
                    + "FROM formacao_academica as T "
                    + "INNER JOIN atuacao_profissional as P on T.membro_id_lattes = P.membro_id_lattes "
                    + "INNER JOIN membro as M on T.membro_id_lattes = M.id_lattes "
                    + "INNER JOIN curso as C on M.id_curso = C.id_curso "
                    + "INNER JOIN campus as D on C.campus_id_campus = D.id_campus "
                    + "WHERE P.sigla = 'UFT' "
                    + "AND T.tipo ilike 'doutorado %' "
                    + "AND T.ano_conclusao != '' "
                    + "AND P.ano_saida = 'Atual' "
                    + "AND C.id_curso = :idCurso "
                    + "AND D.id_campus = :idCampus "
                    + "order by T.membro_id_lattes) as titulacao ;");
            query.setParameter("idCurso", idCurso);
            query.setParameter("idCampus", idCampus);
            return query.getResultList();
        }
        return null;
    }

    public List getGrandeAreaTodosCampus(String area) {
        String aux = checkArea(area);
        Query query = this.entityManager.createNativeQuery("SELECT count(*) as area FROM("
                + "SELECT distinct on (T.membro_id_lattes) T.membro_id_lattes "
                + "FROM areas_atuacao as T "
                + "INNER JOIN atuacao_profissional as P on T.membro_id_lattes = P.membro_id_lattes "
                + "INNER JOIN membro as M on T.membro_id_lattes = M.id_lattes "
                + "WHERE P.sigla = 'UFT' "
                + "AND T.descricao ilike :aux "
                + "AND P.ano_saida = 'Atual' "
                + "order by T.membro_id_lattes) as area ;");
        query.setParameter("aux", "%" + aux + "%");
        return query.getResultList();
    }

    public List getGrandeAreaPorCampus(String area, Long idCampus) {
        String aux = checkArea(area);
        Query query = this.entityManager.createNativeQuery("SELECT count(*) as titulacao FROM("
                + "SELECT distinct on (T.membro_id_lattes) T.membro_id_lattes "
                + "FROM areas_atuacao as T "
                + "INNER JOIN atuacao_profissional as P on T.membro_id_lattes = P.membro_id_lattes "
                + "INNER JOIN membro as M on T.membro_id_lattes = M.id_lattes "
                + "INNER JOIN curso as C on M.id_curso = C.id_curso "
                + "INNER JOIN campus as D on C.campus_id_campus = D.id_campus "
                + "WHERE P.sigla = 'UFT' "
                + "AND T.descricao ilike :aux "
                + "AND P.ano_saida = 'Atual' "
                + "AND D.id_campus = :idCampus "
                + "order by T.membro_id_lattes) as titulacao ;");
        query.setParameter("aux", "%" + aux + "%");
        query.setParameter("idCampus", idCampus);
        return query.getResultList();
    }
    
    public List getGrandeAreaPorCurso(String area, Long idCampus, Long idCurso) {
        String aux = checkArea(area);
        Query query = this.entityManager.createNativeQuery("SELECT count(*) as titulacao FROM("
                + "SELECT distinct on (T.membro_id_lattes) T.membro_id_lattes "
                + "FROM areas_atuacao as T "
                + "INNER JOIN atuacao_profissional as P on T.membro_id_lattes = P.membro_id_lattes "
                + "INNER JOIN membro as M on T.membro_id_lattes = M.id_lattes "
                + "INNER JOIN curso as C on M.id_curso = C.id_curso "
                + "INNER JOIN campus as D on C.campus_id_campus = D.id_campus "
                + "WHERE P.sigla = 'UFT' "
                + "AND T.descricao ilike :aux "
                + "AND P.ano_saida = 'Atual' "
                + "AND C.id_curso = :idCurso "
                + "AND D.id_campus = :idCampus "
                + "order by T.membro_id_lattes) as titulacao ;");
        query.setParameter("aux", "%" + aux + "%");
        query.setParameter("idCampus", idCampus);
        query.setParameter("idCurso", idCurso);
        return query.getResultList();
    }
    
    public String checkArea(String area){
        String aux = null;
        if (area.equals("agrarias")) {
            aux = "Ciências Agrárias";
        }
        else if (area.equals("biologicas")) {
            aux = "Ciências Biológicas";
        }
        else if (area.equals("saude")) {
            aux = "Ciências da Saúde";
        }
        else if (area.equals("exatas")) {
            aux = "Ciências Exatas e da Terra";
        }
        else if (area.equals("humanas")) {
            aux = "Ciências Humanas";
        }
        else if (area.equals("sociais")) {
            aux = "Ciências Sociais Aplicadas";
        }
        else if (area.equals("engenharias")) {
            aux = "Engenharias";
        }
        else if (area.equals("linguistica")) {
            aux = "Lingüística, Letras e Artes";
        }
        else if (area.equals("multidisciplinar")) {
            aux = "Multidisciplinar";
        }
        return aux;
    }
}
