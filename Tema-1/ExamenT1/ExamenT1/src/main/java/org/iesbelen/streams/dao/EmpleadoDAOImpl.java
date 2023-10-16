package org.iesbelen.streams.dao;

import jakarta.persistence.EntityManager;
import org.iesbelen.streams.entity.Empleado;
import org.iesbelen.streams.util.JPAUtil;

import java.util.List;

public class EmpleadoDAOImpl implements EmpleadoDAO {

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
    public List<Empleado> findAll() {

        EntityManager em = JPAUtil.getEntityManager();
        List<Empleado> empleados = em.createQuery("SELECT e FROM Empleado e",Empleado.class).getResultList();
        em.close();

        return empleados;
    }
}
