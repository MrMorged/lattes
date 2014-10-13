/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uft.lattes.repository;

import com.uft.lattes.model.Membro;
import com.uft.lattes.model.ParticipacaoEvento;
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
public class ParticipacaoEventoRepository extends GenericRepository<ParticipacaoEvento>{

    public ParticipacaoEventoRepository(EntityManager anEntityManager) {
        super(anEntityManager);
    }
    
    //buscando palavra da pesquisa
    public List buscaInformacao(String palavra) {
        if (palavra != null) {
            FullTextEntityManager fullTextEntityManager = org.hibernate.search.jpa.Search.getFullTextEntityManager(this.entityManager);
            QueryBuilder b = fullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity(ParticipacaoEvento.class).get();
            org.apache.lucene.search.Query luceneQuery = b.phrase().
                    onField("item").
                    boostedTo(2).
                    sentence(palavra).createQuery();
            javax.persistence.Query fullTextQuery = fullTextEntityManager.createFullTextQuery(luceneQuery, ParticipacaoEvento.class);
            return fullTextQuery.getResultList();
        } else {
            return null;
        }
    }

    public void index() {
        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(this.entityManager);
        try {
            fullTextEntityManager.createIndexer(ParticipacaoEvento.class)
                    .batchSizeToLoadObjects(25)
                    .cacheMode(CacheMode.NORMAL)
                    .threadsToLoadObjects(5)
                    .idFetchSize(150)
                    .threadsForSubsequentFetching(20)
                    .startAndWait();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
            Logger.getLogger(ParticipacaoEventoRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Membro> BuscaMembroPorId(List<ParticipacaoEvento> listaIdLattes) {
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
     * Busca todos as participacoes de eventos dos professores da UFT
     * faz a mesma coisa da função logo abaixo, porém com programação ao invés de SQL puro
     */
//    public List<ParticipacaoEvento> BuscaParticipacaoEventoDaUft(){
//        List<ParticipacaoEvento> selecao = new ArrayList<ParticipacaoEvento>();
//        List<AtuacaoProfissional> resultadoAP;
//        int anoEntrada,anoSaida,anoParticipacaoEvento;
//        Query query = this.entityManager.createQuery("SELECT x FROM ParticipacaoEvento x");
//        List<ParticipacaoEvento> resultadoTT = query.getResultList();
//        for(int i=0; i< resultadoTT.size(); i++){
//            ParticipacaoEvento aux = resultadoTT.get(i);
//            resultadoAP = new AtuacaoProfissionalRepository(ApplicationUtilies.catchEntityManager()).buscaTodasDoMembro(aux.getMembro().getIdLattes());
//            for(int j=0; j< resultadoAP.size(); j++){
//                anoParticipacaoEvento = Integer.parseInt(aux.getAno());
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
//                if(anoParticipacaoEvento >= anoEntrada && anoParticipacaoEvento <= anoSaida){
//                    selecao.add(aux);
//                    break;
//                }
//            }
//        }
//        return selecao;
//    }
    
    /**
     * Método para buscar as participacões de eventos que os professores da UFT estiveram
     * faz o mesmo que a função logo acima
     */
    public List<ParticipacaoEvento> BuscaParticipacaoEventoMaisRapido(){
        Query query = this.entityManager.createNativeQuery("select * "
                + "from participacao_evento as T "
                + "inner join atuacao_profissional as P on T.membro_id_lattes = P.membro_id_lattes "
                + "inner join membro as M on T.membro_id_lattes = M.id_lattes "
                + "where P.sigla = 'UFT' and "
                + "cast(T.ano as numeric) >= cast(P.ano_entrada as numeric) "
                + "AND CASE when P.ano_saida = 'Atual' THEN EXTRACT(YEAR FROM CURRENT_TIMESTAMP) >= CAST (T.ano as numeric) "
                + "else CAST(T.ano as numeric) <= CAST(P.ano_saida as numeric) end;", ParticipacaoEvento.class);
        return query.getResultList();
    }
    
    public List getSeparaPorAno(long id){
        Query query = this.entityManager.createNativeQuery("select count(ano), T.ano "
                + "from participacao_evento as T "
                + "inner join atuacao_profissional as P on T.membro_id_lattes = P.membro_id_lattes "
                + "inner join membro as M on T.membro_id_lattes = M.id_lattes "
                + "where P.sigla = 'UFT' AND M.id_curso = :id AND "
                + "cast(T.ano as numeric) >= cast(P.ano_entrada as numeric) "
                + "AND CASE when P.ano_saida = 'Atual' "
                + "THEN EXTRACT(YEAR FROM CURRENT_TIMESTAMP) >= CAST (T.ano as numeric) "
                + "else CAST(T.ano as numeric) <= CAST(P.ano_saida as numeric) end "
                + "group by ano order by ano;");
        query.setParameter("id", id);
        return query.getResultList();
    }
    
    public List getSeparaPorAnoPorCampus(long idCampus){
        Query query = this.entityManager.createNativeQuery("select count(ano), T.ano "
                + "from participacao_evento as T "
                + "inner join atuacao_profissional as P on T.membro_id_lattes = P.membro_id_lattes "
                + "inner join membro as M on T.membro_id_lattes = M.id_lattes "
                + "INNER JOIN curso as C on M.id_curso = C.id_curso "
                + "INNER JOIN campus as D on C.campus_id_campus = D.id_campus "
                + "where P.sigla = 'UFT' AND D.id_campus = :idCampus AND "
                + "cast(T.ano as numeric) >= cast(P.ano_entrada as numeric) "
                + "AND CASE when P.ano_saida = 'Atual' "
                + "THEN EXTRACT(YEAR FROM CURRENT_TIMESTAMP) >= CAST (T.ano as numeric) "
                + "else CAST(T.ano as numeric) <= CAST(P.ano_saida as numeric) end "
                + "group by ano order by ano;");
        query.setParameter("idCampus", idCampus);
        return query.getResultList();
    }
    
    public List getSeparaPorAnoTodosCampus(){
        Query query = this.entityManager.createNativeQuery("select count(ano), T.ano "
                + "from participacao_evento as T "
                + "inner join atuacao_profissional as P on T.membro_id_lattes = P.membro_id_lattes "
                + "inner join membro as M on T.membro_id_lattes = M.id_lattes "
                + "where P.sigla = 'UFT' AND "
                + "cast(T.ano as numeric) >= cast(P.ano_entrada as numeric) "
                + "AND CASE when P.ano_saida = 'Atual' "
                + "THEN EXTRACT(YEAR FROM CURRENT_TIMESTAMP) >= CAST (T.ano as numeric) "
                + "else CAST(T.ano as numeric) <= CAST(P.ano_saida as numeric) end "
                + "group by ano order by ano;");
        return query.getResultList();
    }
}
