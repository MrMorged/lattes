/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uft.lattes.repository;

import com.uft.lattes.model.Membro;
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
public class MembroRepository extends GenericRepository<Membro> {

    public MembroRepository(EntityManager anEntityManager) {
        super(anEntityManager);
    }
    
    //buscando palavra da pesquisa
    public List buscaInformacao(String palavra) {
        if (palavra != null) {
            FullTextEntityManager fullTextEntityManager = org.hibernate.search.jpa.Search.getFullTextEntityManager(this.entityManager);
            QueryBuilder b = fullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity(Membro.class).get();
            org.apache.lucene.search.Query luceneQuery = b.phrase().
                    onField("nome").
                    andField("id_lattes").
                    boostedTo(3).
                    sentence(palavra).createQuery();
            javax.persistence.Query fullTextQuery = fullTextEntityManager.createFullTextQuery(luceneQuery, Membro.class);
            return fullTextQuery.getResultList();
        } else {
            return null;
        }
    }

    public void index() {
        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(this.entityManager);
        try {
            fullTextEntityManager.createIndexer(Membro.class)
                    .batchSizeToLoadObjects(25)
                    .cacheMode(CacheMode.NORMAL)
                    .threadsToLoadObjects(5)
                    .idFetchSize(150)
                    .threadsForSubsequentFetching(20)
                    .startAndWait();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
            Logger.getLogger(MembroRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Membro> BuscaMembroPorId(List<Membro> listaIdLattes) {
        Long auxId;
        int i;
        List<Membro> auxMembros = new ArrayList();
        List<Membro> novo;
        List<Membro> auxListaLattes = new ArrayList();

        if (listaIdLattes.size() > 1) { // existe mais de um elemento no resultado da busca
            // colocar os que repetem no topo da lista e remover a repetição, para cada repetição de um elemento, ele anda uma posição na lista
            for (int j = 0; j < listaIdLattes.size(); j++) {
                if (auxListaLattes.isEmpty()) {       // se a lista ta vazia, pega o primeiro elemento
                    auxListaLattes.add(listaIdLattes.get(j));
                } else {
                    if (auxListaLattes.contains(listaIdLattes.get(j)) == false) { // se o elemento nao eh repetido
                        auxListaLattes.add(listaIdLattes.get(j));
                    } else {       // quando o elemento eh repetido, mover ele uma posição na frente da lista final[auxListaLattes]
                        for (int k = 0; k < auxListaLattes.size(); k++) {
                            for (int z = 0; z < listaIdLattes.size(); z++) {
                                if (auxListaLattes.get(k).getIdLattes() == listaIdLattes.get(z).getIdLattes()) {
                                    Membro aux = auxListaLattes.get(k);
                                    auxListaLattes.remove(k);
                                    if (k > 1) {
                                        auxListaLattes.add(k - 2, aux);
                                    }
                                    if (k == 1) {
                                        auxListaLattes.add(k - 1, aux);
                                    }
                                    if (k == 0) {
                                        auxListaLattes.add(k, aux);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if (listaIdLattes.size() > 1) {
            listaIdLattes.clear();
            listaIdLattes = auxListaLattes;
        }
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

    public List<Membro> buscaMembro(String membro) {

        //System.out.println(membro);

        Query query = this.entityManager.createNativeQuery("select * "
                + "from membro as M "
                + "where M.nome "
                + "ilike '%" + membro + "%';", Membro.class);

        return query.getResultList();
    }

    
}
