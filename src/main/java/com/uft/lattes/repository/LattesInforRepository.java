/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uft.lattes.repository;

import com.uft.lattes.model.Campus;
import com.uft.lattes.model.LattesSearchInfor;
import com.uft.lattes.model.Membro;
import com.uft.lattes.util.GenericRepository;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Adelmir
 */
public class LattesInforRepository extends GenericRepository<LattesSearchInfor>{

    public LattesInforRepository(EntityManager anEntityManager) {
        super(anEntityManager);
    }
    
    public List<LattesSearchInfor> getTodosCampus(){
        Query query = this.entityManager.createNamedQuery("Campus.findAll");
        return query.getResultList();
    }   

    @Override
    public List<Membro> BuscaMembroPorId(List<LattesSearchInfor> listaIdLattes) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
