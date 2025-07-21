package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import modelo.Usuario;

public class UsuarioDAO {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("KinCaps_Persistence");

}
