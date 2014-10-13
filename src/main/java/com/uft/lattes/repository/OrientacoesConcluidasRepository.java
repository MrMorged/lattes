/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uft.lattes.repository;

import com.uft.lattes.model.Membro;
import com.uft.lattes.model.OrientacoesConcluidas;
import com.uft.lattes.util.GenericRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.hibernate.CacheMode;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;

/**
 *
 * @author igor
 */
public class OrientacoesConcluidasRepository extends GenericRepository<OrientacoesConcluidas> {

    public OrientacoesConcluidasRepository(EntityManager anEntityManager) {
        super(anEntityManager);
    }
    
    //buscando palavra da pesquisa
    public List buscaInformacao(String palavra) {
        if (palavra != null) {
            FullTextEntityManager fullTextEntityManager = org.hibernate.search.jpa.Search.getFullTextEntityManager(this.entityManager);
            QueryBuilder b = fullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity(OrientacoesConcluidas.class).get();
            org.apache.lucene.search.Query luceneQuery = b.phrase().
                    onField("titulo_trabalho").
                    boostedTo(2).
                    sentence(palavra).createQuery();
            javax.persistence.Query fullTextQuery = fullTextEntityManager.createFullTextQuery(luceneQuery, OrientacoesConcluidas.class);
            return fullTextQuery.getResultList();
        } else {
            return null;
        }
    }

    public void index() {
        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(this.entityManager);
        try {
            fullTextEntityManager.createIndexer(OrientacoesConcluidas.class)
                    .batchSizeToLoadObjects(25)
                    .cacheMode(CacheMode.NORMAL)
                    .threadsToLoadObjects(5)
                    .idFetchSize(150)
                    .threadsForSubsequentFetching(20)
                    .startAndWait();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
            Logger.getLogger(OrientacoesConcluidasRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Membro> BuscaMembroPorId(List<OrientacoesConcluidas> listaIdLattes) {
        Long auxId;
        int i;
        List<Membro> auxMembros = new ArrayList();
        List<Membro> novo;
        
        i = 0;
        while (i < listaIdLattes.size()) {
            auxId = listaIdLattes.get(i).getIdLattes();
            Query query = this.entityManager.createQuery("SELECT new Membro(x.nome,x.url,x.idLattes) FROM Membro x WHERE x.idLattes = :auxId");
            query.setParameter("auxId", auxId);
            novo = query.getResultList();
            auxMembros.add(novo.get(0));
            ++i;
        }
        return auxMembros;
    }
    
    public List<OrientacoesConcluidas> BuscaOrientacoesConcluidasMaisRapido(){
        Query query = this.entityManager.createNativeQuery("select * from "
                + "(select distinct on (T.nome,T.titulo_trabalho) T.nome,T.titulo_trabalho,T.ano_conclusao,M.nome,P.sigla,T.tipo_orientacao "
                + "from orientacoes_concluidas as T inner join atuacao_profissional as P "
                + "on T.membro_id_lattes = P.membro_id_lattes inner join membro as M on T.membro_id_lattes = M.id_lattes "
                + "where P.sigla = 'UFT' "
                + "AND T.tipo_orientacao like '%Trabalho de conclusão de curso de graduação%' "
                + "AND cast(T.ano_conclusao as numeric) >= cast(P.ano_entrada as numeric) "
                + "AND CASE when P.ano_saida = 'Atual' "
                + "THEN EXTRACT(YEAR FROM CURRENT_TIMESTAMP) >= CAST (T.ano_conclusao as numeric) "
                + "else CAST(T.ano_conclusao as numeric) <= CAST(P.ano_saida as numeric) end) "
                + "as t order by t.ano_conclusao ASC;", OrientacoesConcluidas.class);
        return query.getResultList();
    }
    
    public List getSeparaPorAno(long id){
        Query queryAux = this.entityManager.createNativeQuery("select C.nome "
                + "from curso C "
                + "where C.id_curso = :id ;");
        queryAux.setParameter("id", id);
        String nomeCurso =  queryAux.getSingleResult().toString();
        Query query = this.entityManager.createNativeQuery("select count(ano_conclusao), T.ano_conclusao "
                + "from orientacoes_concluidas as T "
                + "inner join atuacao_profissional as P on T.membro_id_lattes = P.membro_id_lattes "
                + "inner join membro as M on T.membro_id_lattes = M.id_lattes "
                + "INNER JOIN curso as C on M.id_curso = C.id_curso "
                + "where P.sigla = 'UFT'AND M.id_curso = :id "
                + "AND T.instituicao like '%Graduação%' "
                + "AND T.instituicao like :nomeCurso "
                + "AND T.instituicao like '%Fundação Universidade Federal do Tocantins%' "
                + "AND cast(T.ano_conclusao as numeric) >= cast(P.ano_entrada as numeric) "
                + "AND CASE when P.ano_saida = 'Atual' "
                + "THEN EXTRACT(YEAR FROM CURRENT_TIMESTAMP) >= CAST (T.ano_conclusao as numeric) "
                + "else CAST(T.ano_conclusao as numeric) <= CAST(P.ano_saida as numeric) end "
                + "group by ano_conclusao order by ano_conclusao;");
        query.setParameter("id", id);
        query.setParameter("nomeCurso", "%"+nomeCurso+"%");
        return query.getResultList();
    }
    
    public List getSeparaPorAnoPorCampus(long idCampus){
        Query query = this.entityManager.createNativeQuery("select count(ano_conclusao), T.ano_conclusao "
                + "from orientacoes_concluidas as T "
                + "inner join atuacao_profissional as P on T.membro_id_lattes = P.membro_id_lattes "
                + "inner join membro as M on T.membro_id_lattes = M.id_lattes "
                + "INNER JOIN curso as C on M.id_curso = C.id_curso "
                + "INNER JOIN campus as D on C.campus_id_campus = D.id_campus "
                + "where P.sigla = 'UFT'AND D.id_campus = :idCampus "
                + "AND T.instituicao like '%Graduação%' "
                + "AND T.instituicao like '%Fundação Universidade Federal do Tocantins%' "
                + "AND cast(T.ano_conclusao as numeric) >= cast(P.ano_entrada as numeric) "
                + "AND CASE when P.ano_saida = 'Atual' "
                + "THEN EXTRACT(YEAR FROM CURRENT_TIMESTAMP) >= CAST (T.ano_conclusao as numeric) "
                + "else CAST(T.ano_conclusao as numeric) <= CAST(P.ano_saida as numeric) end "
                + "group by ano_conclusao order by ano_conclusao;");
        query.setParameter("idCampus", idCampus);
        return query.getResultList();
    }
    
    public List getSeparaPorAnoTodosCampus(){
        Query query = this.entityManager.createNativeQuery("select count(ano_conclusao), T.ano_conclusao "
                + "from orientacoes_concluidas as T "
                + "inner join atuacao_profissional as P on T.membro_id_lattes = P.membro_id_lattes "
                + "inner join membro as M on T.membro_id_lattes = M.id_lattes "
                + "where P.sigla = 'UFT' "
                + "AND T.instituicao like '%Graduação%' "
                + "AND T.instituicao like '%Fundação Universidade Federal do Tocantins%' "
                + "AND cast(T.ano_conclusao as numeric) >= cast(P.ano_entrada as numeric) "
                + "AND CASE when P.ano_saida = 'Atual' "
                + "THEN EXTRACT(YEAR FROM CURRENT_TIMESTAMP) >= CAST (T.ano_conclusao as numeric) "
                + "else CAST(T.ano_conclusao as numeric) <= CAST(P.ano_saida as numeric) end "
                + "group by ano_conclusao order by ano_conclusao;");
        return query.getResultList();
    }
    
}
