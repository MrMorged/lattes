/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uft.lattes.repository;

import com.uft.lattes.model.Curso;
import com.uft.lattes.model.Membro;
import com.uft.lattes.util.GenericRepository;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author igor
 */
public class CursoRepository extends GenericRepository<Curso>{

    public CursoRepository(EntityManager anEntityManager) {
        super(anEntityManager);
    }
    
    public List<Curso> getTodosCursos(){
        Query query = this.entityManager.createNamedQuery("Curso.findAll");
        return query.getResultList();
    }
    
    public List<Curso> getTodosCursosPorCampus(Long id){
        Query query = this.entityManager.createQuery("SELECT a from Curso a WHERE a.campus.id = :id");
        query.setParameter("id", id);
        return query.getResultList();
    }

    @Override
    public List<Membro> BuscaMembroPorId(List<Curso> listaIdLattes) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
