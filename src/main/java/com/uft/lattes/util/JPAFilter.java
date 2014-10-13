package com.uft.lattes.util;

import java.io.IOException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;

/**
 * Filtro que gerencia o controle de transações do Entity Manager na requisição.
 * Implementa a interface @see Filter
 * @author Marcelo Claudio
 * @version 2.0
 */
@WebFilter(servletNames={"Faces Servlet"})
public class JPAFilter implements Filter {
    
    private static EntityManagerFactory factory;

    /**
     * Cria e retorna um Entity Manager da fábrica de conexões
     * @since 2.0
     * @return Uma instância da classe Entity Manager
     */
    public static EntityManager getEntityManager(){
        return JPAFilter.factory.createEntityManager();
    }
    
    /**
     * Método que define instruções na inicialização do filtro
     * @since 1.0
     * @param aFilterConfig configuração do filtro
     */
    @Override
    public void init(FilterConfig aFilterConfig) throws ServletException {
        JPAFilter.factory = Persistence.createEntityManagerFactory("lattes");
    }

    /**
     * Método que define instruções no encerramento do filtro
     * @since 1.0
     */
    @Override
    public void destroy() {
        JPAFilter.factory.close();
    }
    
    /**
     * Instruções a serem efetuadas a cada requisição do filtro
     * @since 1.0
     */
    @Override
    public void doFilter(ServletRequest aRequest, ServletResponse aResponse,
        FilterChain aChain) throws IOException, ServletException {
        
        EntityManager anEntityManager = JPAFilter.factory.createEntityManager();
        aRequest.setAttribute("entityManager" , anEntityManager);
        anEntityManager.getTransaction().begin();
        aChain.doFilter(aRequest, aResponse);        
        try {
            anEntityManager.getTransaction().commit();
        } catch (Exception e) {
            anEntityManager.getTransaction().rollback();
        } finally {
            anEntityManager.close();
        }
    }
}