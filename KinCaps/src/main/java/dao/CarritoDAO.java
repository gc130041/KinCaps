package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import modelo.Carrito;
import util.JPAUtil;

public class CarritoDAO {

    public void guardar(Carrito carrito) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(carrito);
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

    public Carrito buscarPorId(int id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.find(Carrito.class, id);
        } finally {
            em.close();
        }
    }

    public List<Carrito> listarTodos() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery("SELECT c FROM Carrito c", Carrito.class).getResultList();
        } finally {
            em.close();
        }
    }

    public List<Carrito> buscarPedidosPorCliente(int idCliente) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery(
                    "SELECT c FROM Carrito c WHERE c.cliente.idCliente = :idCliente AND c.estado = 'PAGADO' ORDER BY c.fechaCreacion DESC",
                    Carrito.class)
                    .setParameter("idCliente", idCliente)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public void actualizar(Carrito carrito) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.merge(carrito);
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
            Carrito carrito = em.find(Carrito.class, id);
            if (carrito != null) {
                em.remove(carrito);
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
