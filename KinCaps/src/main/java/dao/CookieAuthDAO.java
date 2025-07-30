package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import modelo.CookieAuth;
import util.JPAUtil;

public class CookieAuthDAO {

    public void crearToken(CookieAuth token) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(token);
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

    public CookieAuth buscarPorSelector(String selector) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            TypedQuery<CookieAuth> query = em.createQuery("SELECT c FROM CookieAuth c WHERE c.selector = :selector", CookieAuth.class);
            query.setParameter("selector", selector);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }

    public void eliminarPorSelector(String selector) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            CookieAuth token = buscarPorSelector(selector);
            if (token != null) {
                em.remove(em.merge(token));
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