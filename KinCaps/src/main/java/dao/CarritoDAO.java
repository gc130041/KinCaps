package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import modelo.Carrito;
import modelo.Cliente;
import util.JPAUtil;

public class CarritoDAO {

    public void guardar(Carrito carrito) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            if (carrito.getCliente() != null && !em.contains(carrito.getCliente())) {
                Cliente managedCliente = em.merge(carrito.getCliente());
                carrito.setCliente(managedCliente);
            }
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
    
    public List<Carrito> buscarPedidosPorClienteConDetalles(int clienteId) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            String jpql = "SELECT DISTINCT c FROM Carrito c LEFT JOIN FETCH c.detalles d LEFT JOIN FETCH d.gorra g WHERE c.cliente.idCliente = :clienteId ORDER BY c.fechaCreacion DESC";
            return em.createQuery(jpql, Carrito.class)
                     .setParameter("clienteId", clienteId)
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

    public Carrito buscarActivoPorCliente(Cliente cliente) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            List<Carrito> carritos = em.createQuery("SELECT c FROM Carrito c WHERE c.cliente.idCliente = :clienteId AND c.estado = :estado", Carrito.class)
                    .setParameter("clienteId", cliente.getIdCliente())
                    .setParameter("estado", Carrito.Estado.ACTIVO)
                    .getResultList();
            return carritos.isEmpty() ? null : carritos.get(0);
        } finally {
            em.close();
        }
    }
}