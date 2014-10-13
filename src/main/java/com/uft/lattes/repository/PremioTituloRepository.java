/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uft.lattes.repository;

import com.uft.lattes.model.Membro;
import com.uft.lattes.model.PremioTitulo;
import com.uft.lattes.util.GenericRepository;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author igor
 */
public class PremioTituloRepository extends GenericRepository<PremioTitulo>{

    public PremioTituloRepository(EntityManager anEntityManager) {
        super(anEntityManager);
    }
    
    public List<PremioTitulo> buscaPremioTitulo(String membro){
        Query query = this.entityManager.createNativeQuery("select * "
                + "from premio_titulo as P "
                + "inner join membro as M "
                + "on P.membro_id_lattes = M.id_lattes "
                + "and M.nome ilike '%" + membro.trim() + "%';", PremioTitulo.class);
        return query.getResultList();
    }

    @Override
    public List<Membro> BuscaMembroPorId(List<PremioTitulo> listaIdLattes) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
