package com.uft.lattes.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe modelo para ManagedBeans
 * @author Igor Barreto
 * @version 1.0
 */
public abstract class GenericController <Class,RepositoryClass> implements Serializable{
    
    protected List<Class> listOfEntities=new ArrayList<Class>();
    protected RepositoryClass repository;
    protected Class entity;
    //@Inject
    //protected PartialController partialController;

    /**
     * Retorna um Entity Manager
     * @since 1.0
     * @return Uma instância da classe que o bean gerencia.
     */
    public Class getEntity() {
        return entity;
    }

    /**
     * Define um Entity Manager
     * @since 1.0
     * @param anEntity Uma instância da classe que o bean gerencia.
     */
    public void setEntity(Class anEntity) {
        this.entity = anEntity;
    }

    /**
     * Retorna uma collection da classe que o bean gerencia.
     * @since 1.0
     * @return Collection de instâncias da classe gerenciada
     */
    public List<Class> getListOfEntities() {
        return listOfEntities;
    }

    /**
     * Define uma collection da classe que o bean gerencia.
     * @since 1.0
     * @param aListOfEntities Collection de instâncias da classe gerenciada
     */
    public void setListOfEntities(List<Class> aListOfEntities) {
        this.listOfEntities = aListOfEntities;
    }
    
     
    /**
     * Retorna uma instância do  Repository responsável pela classe gerenciada pelo Managed Bean.
     * @since 1.0
     * @return Uma instância do Repository
     */
    public RepositoryClass getRepository() {
        return repository;
    }

    /**
     * Define uma instância do Repository reponsável pela classe gerenciada pelo Managed Bean.
     * @since 1.0
     * @param aRepository Uma instância do Repository
     */
    public void setRepository(RepositoryClass aRepository) {
        this.repository = aRepository;
    }
    
    /**
     * Método abstrato resposável pela requisição de inserção no Banco de Dados.
     */
    abstract public void insert();
    
    /**
     * Método abstrato resposável pela requisição de remoção no Banco de Dados.
     */
    abstract public void remove();
    
    /**
     * Método abstrato reposável pela requisição de atualização no Banco de Dados.
     */
    abstract public void update();        
}
