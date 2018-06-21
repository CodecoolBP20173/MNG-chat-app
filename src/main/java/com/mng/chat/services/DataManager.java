package com.mng.chat.services;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class DataManager {

    private EntityManagerFactory emf;


    public DataManager(String persistenceUnitName) {
        this.emf = Persistence.createEntityManagerFactory(persistenceUnitName);
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void persistEntity(EntityManager em, Object obj) {
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.persist(obj);
        et.commit();
    }

    @Override
    protected void finalize() throws Throwable {
        emf.close();
    }
}
