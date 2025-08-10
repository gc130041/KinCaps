package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import modelo.Gorras;
import util.JPAUtil;

public class GorrasDAO {

    public void guardar(Gorras gorra) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(gorra);
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

    public Gorras buscarPorId(int id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.find(Gorras.class, id);
        } finally {
            em.close();
        }
    }

    public List<Gorras> listarTodas() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery("SELECT g FROM Gorras g", Gorras.class).getResultList();
        } finally {
            em.close();
        }
    }

    public void actualizar(Gorras gorra) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.merge(gorra);
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
            Gorras gorra = em.find(Gorras.class, id);
            if (gorra != null) {
                em.remove(gorra);
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

    public List<Gorras> buscarMasVendidas(int limite) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            String sql = "SELECT g.* FROM gorras g "
                    + "JOIN detalleFactura df ON g.idGorra = df.idGorra "
                    + "GROUP BY g.idGorra "
                    + "ORDER BY SUM(df.cantidad) DESC "
                    + "LIMIT :limite";
            return em.createNativeQuery(sql, Gorras.class)
                    .setParameter("limite", limite)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public List<Gorras> buscarAleatorias(int limite) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            String sql = "SELECT * FROM gorras ORDER BY RAND() LIMIT :limite";
            return em.createNativeQuery(sql, Gorras.class)
                    .setParameter("limite", limite)
                    .getResultList();
        } finally {
            em.close();
        }
    }

}
