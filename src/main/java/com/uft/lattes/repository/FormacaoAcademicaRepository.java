/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uft.lattes.repository;

import com.uft.lattes.model.FormacaoAcademica;
import com.uft.lattes.model.FormacaoAcademica;
import com.uft.lattes.model.Membro;
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
public class FormacaoAcademicaRepository extends GenericRepository<FormacaoAcademica>{

    public FormacaoAcademicaRepository(EntityManager anEntityManager) {
        super(anEntityManager);
    }
    
    //buscando palavra da pesquisa
    public List buscaInformacao(String palavra) {
        if (palavra != null) {
            FullTextEntityManager fullTextEntityManager = org.hibernate.search.jpa.Search.getFullTextEntityManager(this.entityManager);
            QueryBuilder b = fullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity(FormacaoAcademica.class).get();
            org.apache.lucene.search.Query luceneQuery = b.phrase().
                    onField("descricao").
                    boostedTo(1).
                    sentence(palavra).createQuery();
            javax.persistence.Query fullTextQuery = fullTextEntityManager.createFullTextQuery(luceneQuery, FormacaoAcademica.class);
            return fullTextQuery.getResultList();
        } else {
            return null;
        }
    }

    public void index() {
        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(this.entityManager);
        try {
            fullTextEntityManager.createIndexer(FormacaoAcademica.class)
                    .batchSizeToLoadObjects(25)
                    .cacheMode(CacheMode.NORMAL)
                    .threadsToLoadObjects(5)
                    .idFetchSize(150)
                    .threadsForSubsequentFetching(20)
                    .startAndWait();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
            Logger.getLogger(FormacaoAcademicaRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Membro> BuscaMembroPorId(List<FormacaoAcademica> listaIdLattes) {
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
    
    public List<FormacaoAcademica> buscaFormacaoAcademica(String membro){
        Query query = this.entityManager.createNativeQuery("select * "
                + "from formacao_academica as F "
                + "inner join membro as M "
                + "on F.membro_id_lattes = M.id_lattes "
                + "and M.nome ilike '%"+ membro.trim() + "%';", FormacaoAcademica.class);
        //query.setParameter();
        return query.getResultList();
    }
    
}
