package org.iesbelen.streams.dao;

import jakarta.persistence.EntityManager;
import org.iesbelen.streams.entity.Departamento;
import org.iesbelen.streams.util.JPAUtil;

import java.util.List;

public class DepartamentoDAOImpl implements DepartamentoDAO {

    public void beginTransaction() {
        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();
        em.close();
    }

    public void commitTransaction() {
        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().commit();
        em.close();
    }

    public void rollbackTransaction() {
        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().rollback();
        em.close();
    }

    @Override
    public List<Departamento> findAll() {

        EntityManager em = JPAUtil.getEntityManager();
        List<Departamento> departamentos = em.createQuery("SELECT d FROM Departamento d",Departamento.class).getResultList();
        em.close();

        return departamentos;
    }
}
