package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import modelo.Carrito;
import modelo.DetalleCarrito;
import modelo.Gorras;
import util.JPAUtil;

public class DetalleCarritoDAO {

    public void guardar(DetalleCarrito detalle) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            if (detalle.getCarrito() != null && !em.contains(detalle.getCarrito())) {
                detalle.setCarrito(em.merge(detalle.getCarrito()));
            }
            if (detalle.getGorra() != null && !em.contains(detalle.getGorra())) {
                detalle.setGorra(em.merge(detalle.getGorra()));
            }
            em.persist(detalle);
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

    public void actualizar(DetalleCarrito detalle) {
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

    public DetalleCarrito buscarPorId(int id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.find(DetalleCarrito.class, id);
        } finally {
            em.close();
        }
    }

    public List<DetalleCarrito> listarTodos() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery("SELECT d FROM DetalleCarrito d", DetalleCarrito.class).getResultList();
        } finally {
            em.close();
        }
    }

    public void eliminar(int id) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            DetalleCarrito detalle = em.find(DetalleCarrito.class, id);
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

    public DetalleCarrito buscarPorCarritoYGorra(Carrito carrito, Gorras gorra) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            List<DetalleCarrito> detalles = em.createQuery("SELECT d FROM DetalleCarrito d WHERE d.carrito.idCarrito = :carritoId AND d.gorra.idGorra = :gorraId", DetalleCarrito.class)
                    .setParameter("carritoId", carrito.getIdCarrito())
                    .setParameter("gorraId", gorra.getIdGorra())
                    .getResultList();
            return detalles.isEmpty() ? null : detalles.get(0);
        } finally {
            em.close();
        }
    }

    public List<DetalleCarrito> buscarPorCarrito(Carrito carrito) {
        EntityManager em = JPAUtil.getEntityManager();
        if (carrito == null) {
            return java.util.Collections.emptyList();
        }
        try {
            return em.createQuery("SELECT d FROM DetalleCarrito d WHERE d.carrito = :carrito", DetalleCarrito.class)
                    .setParameter("carrito", carrito)
                    .getResultList();
        } finally {
            em.close();
        }
    }
}
