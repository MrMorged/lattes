/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uft.lattes.repository;

import com.uft.lattes.model.Idioma;
import com.uft.lattes.model.Membro;
import com.uft.lattes.util.GenericRepository;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author igor
 */
public class IdiomaRepository extends GenericRepository<Idioma>{

    public IdiomaRepository(EntityManager anEntityManager) {
        super(anEntityManager);
    }
    
    public List<Idioma> buscaIdioma(String membro){
        Query query = this.entityManager.createNativeQuery("select * "
                + "from idioma as I "
                + "inner join membro as M "
                + "on I.membro_id_lattes = M.id_lattes "
                + "and M.nome ilike '%"+ membro.trim() +"%';", Idioma.class);
        return query.getResultList();
    }

    @Override
    public List<Membro> BuscaMembroPorId(List<Idioma> listaIdLattes) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
