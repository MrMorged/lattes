/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uft.lattes.repository;

import com.uft.lattes.model.Membro;
import com.uft.lattes.model.ProdutoTecnologico;
import com.uft.lattes.util.GenericRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.hibernate.CacheMode;
import org.hibernate.annotations.QueryHints;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;

/**
 *
 * @author igor
 */
public class ProdutoTecnologicoRepository extends GenericRepository<ProdutoTecnologico> {

    public ProdutoTecnologicoRepository(EntityManager entityManager) {
        super(entityManager);
    }

    //buscando palavra da pesquisa
    public List buscaInformacao(String palavra) {
        if (palavra != null) {
            FullTextEntityManager fullTextEntityManager = org.hibernate.search.jpa.Search.getFullTextEntityManager(this.entityManager);
            QueryBuilder b = fullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity(ProdutoTecnologico.class).get();
            org.apache.lucene.search.Query luceneQuery = b.phrase().
                    onField("titulo").
                    boostedTo(3).
                    sentence(palavra).createQuery();
            javax.persistence.Query fullTextQuery = fullTextEntityManager.createFullTextQuery(luceneQuery, ProdutoTecnologico.class);
            fullTextQuery.setHint(QueryHints.FETCH_SIZE, true);
            Map<String, Object> teste = fullTextQuery.getHints();

            
            /*
             for(int i=0; i<teste.size();i++){
             System.err.println("hints >>"+(int)teste.size());
             System.out.println("");
             }
             */
            List<ProdutoTecnologico> resposta = new ArrayList<ProdutoTecnologico>();
            resposta = fullTextQuery.getResultList();

            while (!resposta.isEmpty()) {
                System.err.println("nomes >>" + resposta.remove(0));
            }
            
            System.err.println("full >>" + (int) fullTextQuery.getResultList().size());

            return fullTextQuery.getResultList();

        } else {
            return null;
        }
    }
/*
    public int freq() {
        Random gerador = new Random();
        int numero = gerador.nextInt(1) + 12;
        System.out.println(numero);
        return 0;
    }
*/
    public void index() {
        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(this.entityManager);
        try {
            fullTextEntityManager.createIndexer(ProdutoTecnologico.class)
                    .batchSizeToLoadObjects(25)
                    .cacheMode(CacheMode.NORMAL)
                    .threadsToLoadObjects(5)
                    .idFetchSize(150)
                    .threadsForSubsequentFetching(20)
                    .startAndWait();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
            Logger.getLogger(ProdutoTecnologicoRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Membro> BuscaMembroPorId(List<ProdutoTecnologico> listaIdLattes) {
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
     * Busca todos os produtos tecnológicos da UFT faz a mesma coisa da função
     * logo abaixo, porém com programação ao invés de SQL puro
     */
//    public List<ProdutoTecnologico> BuscaProdutoTecnologicoDaUft(){
//        List<ProdutoTecnologico> selecao = new ArrayList<ProdutoTecnologico>();
//        List<AtuacaoProfissional> resultadoAP;
//        int anoEntrada,anoSaida,anoProdutoTecnologico;
//        Query query = this.entityManager.createQuery("SELECT x FROM ProdutoTecnologico x");
//        List<ProdutoTecnologico> resultadoTT = query.getResultList();
//        for(int i=0; i< resultadoTT.size(); i++){
//            ProdutoTecnologico aux = resultadoTT.get(i);
//            resultadoAP = new AtuacaoProfissionalRepository(ApplicationUtilies.catchEntityManager()).buscaTodasDoMembro(aux.getMembro().getIdLattes());
//            for(int j=0; j< resultadoAP.size(); j++){
//                anoProdutoTecnologico = Integer.parseInt(aux.getAno());
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
//                if(anoProdutoTecnologico >= anoEntrada && anoProdutoTecnologico <= anoSaida){
//                    selecao.add(aux);
//                    break;
//                }
//            }
//        }
//        return selecao;
//    }
    /**
     * Método para buscar os produtos tecnológicos que os professores produziram
     * na UFT faz o mesmo que a função logo acima
     */
    public List<ProdutoTecnologico> BuscaProdutoTecnologicoMaisRapido() {
        Query query = this.entityManager.createNativeQuery("select * "
                + "from produto_tecnologico as T "
                + "inner join atuacao_profissional as P on T.membro_id_lattes = P.membro_id_lattes "
                + "inner join membro as M on T.membro_id_lattes = M.id_lattes "
                + "where P.sigla = 'UFT' and "
                + "cast(T.ano as numeric) >= cast(P.ano_entrada as numeric) "
                + "AND CASE when P.ano_saida = 'Atual' THEN EXTRACT(YEAR FROM CURRENT_TIMESTAMP) >= CAST (T.ano as numeric) "
                + "else CAST(T.ano as numeric) <= CAST(P.ano_saida as numeric) end;", ProdutoTecnologico.class);
        return query.getResultList();
    }

    public List getSeparaPorAno(long id) {
        Query query = this.entityManager.createNativeQuery("select count(ano), T.ano "
                + "from produto_tecnologico as T "
                + "inner join atuacao_profissional as P on T.membro_id_lattes = P.membro_id_lattes "
                + "inner join membro as M on T.membro_id_lattes = M.id_lattes "
                + "where P.sigla = 'UFT' AND M.id_curso = :id AND "
                + "cast(T.ano as numeric) >= cast(P.ano_entrada as numeric) "
                + "AND CASE when P.ano_saida = 'Atual' THEN EXTRACT(YEAR FROM CURRENT_TIMESTAMP) >= CAST (T.ano as numeric) "
                + "else CAST(T.ano as numeric) <= CAST(P.ano_saida as numeric) end "
                + "group by ano order by ano;");
        query.setParameter("id", id);
        return query.getResultList();
    }

    public List getSeparaPorAnoPorCampus(long idCampus) {
        Query query = this.entityManager.createNativeQuery("select count(ano), T.ano "
                + "from produto_tecnologico as T "
                + "inner join atuacao_profissional as P on T.membro_id_lattes = P.membro_id_lattes "
                + "inner join membro as M on T.membro_id_lattes = M.id_lattes "
                + "INNER JOIN curso as C on M.id_curso = C.id_curso "
                + "INNER JOIN campus as D on C.campus_id_campus = D.id_campus "
                + "where P.sigla = 'UFT' AND D.id_campus = :idCampus AND "
                + "cast(T.ano as numeric) >= cast(P.ano_entrada as numeric) "
                + "AND CASE when P.ano_saida = 'Atual' THEN EXTRACT(YEAR FROM CURRENT_TIMESTAMP) >= CAST (T.ano as numeric) "
                + "else CAST(T.ano as numeric) <= CAST(P.ano_saida as numeric) end "
                + "group by ano order by ano;");
        query.setParameter("idCampus", idCampus);
        return query.getResultList();
    }

    public List getSeparaPorAnoTodosCampus() {
        Query query = this.entityManager.createNativeQuery("select count(ano), T.ano "
                + "from produto_tecnologico as T "
                + "inner join atuacao_profissional as P on T.membro_id_lattes = P.membro_id_lattes "
                + "inner join membro as M on T.membro_id_lattes = M.id_lattes "
                + "where P.sigla = 'UFT' AND "
                + "cast(T.ano as numeric) >= cast(P.ano_entrada as numeric) "
                + "AND CASE when P.ano_saida = 'Atual' THEN EXTRACT(YEAR FROM CURRENT_TIMESTAMP) >= CAST (T.ano as numeric) "
                + "else CAST(T.ano as numeric) <= CAST(P.ano_saida as numeric) end "
                + "group by ano order by ano;");
        return query.getResultList();
    }
}
