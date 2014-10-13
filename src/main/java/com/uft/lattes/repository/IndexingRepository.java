/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uft.lattes.repository;

import javax.persistence.EntityManager;
import org.hibernate.CacheMode;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;

/**
 *
 * @author igor
 */
public class IndexingRepository {

    private EntityManager entityManager;

    public IndexingRepository(EntityManager anEntityManager) {
        this.entityManager = anEntityManager;

    }

    public void index() throws InterruptedException {
        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(this.entityManager);
        fullTextEntityManager.createIndexer()
                .batchSizeToLoadObjects(25)
                .cacheMode(CacheMode.NORMAL)
                .threadsToLoadObjects(5)
                .idFetchSize(150)
                .threadsForSubsequentFetching(20)
                .startAndWait();
        //FullTextSession fullTextSession = Search.getFullTextEntityManager(this.entityManager);
        //fullTextSession
//                .createIndexer(ApresentacaoTrabalho.class)
//                .batchSizeToLoadObjects(25)
//                .cacheMode(CacheMode.NORMAL)
//                .threadsToLoadObjects(5)
//                .idFetchSize(150)
//                .threadsForSubsequentFetching(20)
//                .startAndWait();
    }
}
