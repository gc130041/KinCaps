package dao;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import modelo.Cliente;
import modelo.Empleado;
import modelo.Usuario;
import util.JPAUtil;

public class UsuarioDAO {

    public Usuario verificarCredenciales(String email, String password) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            StoredProcedureQuery query = em.createStoredProcedureQuery("sp_Login")
                .registerStoredProcedureParameter("p_email", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_contrasena", String.class, ParameterMode.IN)
                .setParameter("p_email", email)
                .setParameter("p_contrasena", password);

            query.execute();

            Object[] result = (Object[]) query.getSingleResult();
            Integer idUsuario = Integer.parseInt(result[0].toString());
            String tipoUsuario = (String) result[1];

            if ("cliente".equals(tipoUsuario)) {
                return em.find(Cliente.class, idUsuario);
            } else if ("empleado".equals(tipoUsuario)) {
                return em.find(Empleado.class, idUsuario);
            } else {
                return null;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            em.close();
        }
    }
}