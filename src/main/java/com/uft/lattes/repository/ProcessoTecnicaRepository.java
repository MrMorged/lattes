/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uft.lattes.repository;

import com.uft.lattes.model.Membro;
import com.uft.lattes.model.ProcessoTecnica;
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
public class ProcessoTecnicaRepository extends GenericRepository<ProcessoTecnica>{
    
    public ProcessoTecnicaRepository(EntityManager entityManager){
        super(entityManager);
    }
    
    //buscando palavra da pesquisa
    public List buscaInformacao(String palavra) {
        if (palavra != null) {
            FullTextEntityManager fullTextEntityManager = org.hibernate.search.jpa.Search.getFullTextEntityManager(this.entityManager);
            QueryBuilder b = fullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity(ProcessoTecnica.class).get();
            org.apache.lucene.search.Query luceneQuery = b.phrase().
                    onField("titulo").
                    boostedTo(3).
                    sentence(palavra).createQuery();
            javax.persistence.Query fullTextQuery = fullTextEntityManager.createFullTextQuery(luceneQuery, ProcessoTecnica.class);
            return fullTextQuery.getResultList();
        } else {
            return null;
        }
    }

    public void index() {
        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(this.entityManager);
        try {
            fullTextEntityManager.createIndexer(ProcessoTecnica.class)
                    .batchSizeToLoadObjects(25)
                    .cacheMode(CacheMode.NORMAL)
                    .threadsToLoadObjects(5)
                    .idFetchSize(150)
                    .threadsForSubsequentFetching(20)
                    .startAndWait();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
            Logger.getLogger(ProcessoTecnicaRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Membro> BuscaMembroPorId(List<ProcessoTecnica> listaIdLattes) {
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
    
    /** 
     * Busca todos os processos e técnicas da UFT
     * faz a mesma coisa da função logo abaixo, porém com programação ao invés de SQL puro
     */
//    public List<ProcessoTecnica> BuscaProcessoTecnicaDaUft(){
//        List<ProcessoTecnica> selecao = new ArrayList<ProcessoTecnica>();
//        List<AtuacaoProfissional> resultadoAP;
//        int anoEntrada,anoSaida,anoProcessoTecnica;
//        Query query = this.entityManager.createQuery("SELECT x FROM ProcessoTecnica x");
//        List<ProcessoTecnica> resultadoTT = query.getResultList();
//        for(int i=0; i< resultadoTT.size(); i++){
//            ProcessoTecnica aux = resultadoTT.get(i);
//            resultadoAP = new AtuacaoProfissionalRepository(ApplicationUtilies.catchEntityManager()).buscaTodasDoMembro(aux.getMembro().getIdLattes());
//            for(int j=0; j< resultadoAP.size(); j++){
//                anoProcessoTecnica = Integer.parseInt(aux.getAno());
//                anoEntrada = Integer.parseInt(resultadoAP.get(j).getAnoEntrada());
//                
//                if(resultadoAP.get(j).getAnoSaida().equalsIgnoreCase("Atual")){
//                    Calendar calendario = Calendar.getInstance();
//                    anoSaida = calendario.get(Calendar.YEAR); 
//                    //anoSaida = 2012;
//                }
//                else{
//                    anoSaida = Integer.parseInt(resultadoAP.get(j).getAnoSaida());
//                }
//                if(anoProcessoTecnica >= anoEntrada && anoProcessoTecnica <= anoSaida){
//                    selecao.add(aux);
//                    break;
//                }
//            }
//        }
//        return selecao;
//    }
    
     /**
     * Método para buscar os processos e técnicas que os professores produziram na UFT
     * faz o mesmo que a função logo acima
     */
    public List<ProcessoTecnica> BuscaProcessoTecnicaMaisRapido(){
        Query query = this.entityManager.createNativeQuery("select * "
                + "from processo_tecnica as T "
                + "inner join atuacao_profissional as P on T.membro_id_lattes = P.membro_id_lattes "
                + "inner join membro as M on T.membro_id_lattes = M.id_lattes "
                + "where P.sigla = 'UFT' and "
                + "cast(T.ano as numeric) >= cast(P.ano_entrada as numeric) "
                + "AND CASE when P.ano_saida = 'Atual' THEN EXTRACT(YEAR FROM CURRENT_TIMESTAMP) >= CAST (T.ano as numeric) "
                + "else CAST(T.ano as numeric) <= CAST(P.ano_saida as numeric) end;", ProcessoTecnica.class);
        return query.getResultList();
    }
}
