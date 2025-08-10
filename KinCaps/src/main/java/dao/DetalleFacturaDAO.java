package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import modelo.DetalleFactura;
import util.JPAUtil;

public class DetalleFacturaDAO {

    public void guardar(DetalleFactura detalle) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            if (detalle.getFactura() != null && !em.contains(detalle.getFactura())) {
                detalle.setFactura(em.merge(detalle.getFactura()));
            }
            if (detalle.getGorra() != null && !em.contains(detalle.getGorra())) {
                detalle.setGorra(em.merge(detalle.getGorra()));
            }
            em.persist(detalle);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public DetalleFactura buscarPorId(int id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.find(DetalleFactura.class, id);
        } finally {
            em.close();
        }
    }

    public List<DetalleFactura> listarTodos() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery("SELECT d FROM DetalleFactura d", DetalleFactura.class).getResultList();
        } finally {
            em.close();
        }
    }

    public void actualizar(DetalleFactura detalle) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.merge(detalle);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public void eliminar(int id) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            DetalleFactura detalle = em.find(DetalleFactura.class, id);
            if (detalle != null) {
                em.remove(detalle);
            }
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}