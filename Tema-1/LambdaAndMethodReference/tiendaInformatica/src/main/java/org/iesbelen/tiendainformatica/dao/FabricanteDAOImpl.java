package org.iesbelen.tiendainformatica.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.iesbelen.tiendainformatica.entity.Fabricante;
import org.iesbelen.tiendainformatica.util.JPAUtil;

import java.util.List;

public class FabricanteDAOImpl implements FabricanteDAO {

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
    public List<Fabricante> findAll() {

        EntityManager em = JPAUtil.getEntityManager();
        List<Fabricante> fabricantes = em.createQuery("SELECT f FROM Fabricante f", Fabricante.class).getResultList();
        em.close();

        return fabricantes;
    }

    @Override
    public Fabricante findOne(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        Fabricante fabricante = em.find(Fabricante.class, id);
        em.close();

        return fabricante;
    }

    @Override
    public boolean create(Fabricante fabricante) {
        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();
        em.persist(fabricante);
        em.getTransaction().commit();
        em.close();
        return true;
    }

    @Override
    public boolean update(Fabricante fabricante) {
        return false;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }
}
