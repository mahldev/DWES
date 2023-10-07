package org.iesbelen.tiendainformatica.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.iesbelen.tiendainformatica.entity.Producto;
import org.iesbelen.tiendainformatica.util.JPAUtil;

import java.util.List;

public class ProductoDAOImpl implements ProductoDAO{

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
    public List<Producto> findAll() {

        EntityManager em = JPAUtil.getEntityManager();
        List<Producto> productos =
                em.createQuery("SELECT p FROM Producto p",Producto.class).getResultList();
        em.close();

        return productos;
    }

    @Override
    public Producto findOne(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        Producto producto = em.find(Producto.class,id);
        em.close();

        return producto;
    }

    @Override
    public boolean create(Producto producto) {
        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();
        em.persist(producto);
        em.getTransaction().commit();
        em.close();
        return true;
    }

    @Override
    public boolean update(Producto producto) {
        return false;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }
}
