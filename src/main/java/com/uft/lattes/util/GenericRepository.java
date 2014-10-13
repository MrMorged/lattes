package com.uft.lattes.util;

import com.uft.lattes.model.Membro;
import com.uft.lattes.model.TrabalhoTecnico;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * Classe modelo para repositórios definidos na aplicação
 * @author Marcelo Claudio
 * @version 2.0
 */
public abstract class GenericRepository<Class>{

    protected EntityManager entityManager;

    /**
     * Construtor da classe
     * @param anEntityManager Entity Manager para realização das transações no banco de dados
     * @since 1.0
     */
    public GenericRepository(EntityManager anEntityManager) {
        this.entityManager = anEntityManager;
    }

    /**
     * Método resposável por inserção no banco de dados.
     * @since 1.0
     * @param anInsert Objeto a ser inserido
     */
    public void insert(Class anInsert) {
        this.entityManager.persist(anInsert);
        this.entityManager.flush();
    }

    /**
     * Método resposável por remoção no banco de dados.
     * @since 1.0
     * @param anRemoval Objeto a ser removido
     */
    public void remove(Class aRemoval) {
        this.entityManager.remove(this.entityManager.merge(aRemoval));
        this.entityManager.flush();
    }

    /**
     * Método resposável por atuazalização no banco de dados.
     * @since 1.0
     * @param anUpdate Objeto a ser atualizado
     */
    public void update(Class anUpdate) {
        this.entityManager.merge(anUpdate);
        this.entityManager.flush();
    }
    
    /**
     * Método responsável por retornar um único resultado de uma busca.
     * @since 2.0
     * @param aQuery
     * @return Primeiro resultado da busca.
     */
    public Class getSingleResult(Query aQuery) {
        aQuery.setMaxResults(1); //limita a busca a um elemento
        List<Class> aListResult = aQuery.getResultList(); //recebe a lista com os resultados
        if (aListResult == null || aListResult.isEmpty()) //verifica se é nulo ou vazia
            return null;
        else
            return (Class) aListResult.get(0); //caso não seja envia o primeiro resultado        
    }
    
    public abstract List<Membro> BuscaMembroPorId(List<Class> listaIdLattes);
}
