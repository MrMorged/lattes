/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uft.lattes.repository;

import com.uft.lattes.model.AtuacaoProfissional;
import com.uft.lattes.model.Membro;
import com.uft.lattes.util.GenericRepository;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author igor
 */
public class AtuacaoProfissionalRepository extends GenericRepository<AtuacaoProfissional>{
    
    public AtuacaoProfissionalRepository(EntityManager entityManager){
        super(entityManager);
    }
    
    public List<AtuacaoProfissional> buscaTodasDaUFT(){
        Query query = this.entityManager.createQuery("SELECT x FROM AtuacaoProfissional x WHERE x.instituicao='Fundação Universidade Federal do Tocantins' OR x.sigla='UFT'");
        return query.getResultList();
    }
    
    /** Método que busca uma atuação profissional
     * 
     */
    public List<AtuacaoProfissional> buscaTodasDoMembro(Long membro_id_lattes){
        Query query = this.entityManager.createQuery("SELECT x FROM AtuacaoProfissional x WHERE x.instituicao='Fundação Universidade Federal do Tocantins' AND x.idLattes= :membro_id_lattes OR x.sigla='UFT' AND x.idLattes= :membro_id_lattes");
        query.setParameter("membro_id_lattes", membro_id_lattes);
        return query.getResultList();
        
    }
    
    public List<AtuacaoProfissional> buscaAtuacaoProfissional(String membro){
        Query query = this.entityManager.createNativeQuery("select distinct on (F.sigla) * "
                + "from atuacao_profissional as F "
                + "inner join membro as M "
                + "on F.membro_id_lattes = M.id_lattes "
                + "and M.nome ilike '%"+ membro.trim() +"%'", AtuacaoProfissional.class);
        //query.setParameter();
        return query.getResultList();
    }

    @Override
    public List<Membro> BuscaMembroPorId(List<AtuacaoProfissional> listaIdLattes) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
