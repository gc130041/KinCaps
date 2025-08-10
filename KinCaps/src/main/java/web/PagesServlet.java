package web;

import java.io.IOException;
import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.Cliente;
import modelo.Usuario;
import util.JPAUtil;

@WebServlet(name = "PagesServlet", urlPatterns = {"/gorras", "/gorras/*"})
public class PagesServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("usuario") instanceof Cliente) {
            
            Cliente clienteEnSesion = (Cliente) session.getAttribute("usuario");
            EntityManager em = JPAUtil.getEntityManager();
            
            try {
                Cliente usuarioCompleto = em.find(Cliente.class, clienteEnSesion.getIdCliente());
                request.setAttribute("usuarioCompleto", usuarioCompleto);
            } finally {
                if (em != null) {
                    em.close();
                }
            }
        }

        String pathInfo = request.getPathInfo();
        String pageToForward;

        if (pathInfo == null || pathInfo.equals("/")) {
            pageToForward = "/pages/mainmenu.jsp";
        } else {
            switch (pathInfo) {
                case "/mision":
                    pageToForward = "/pages/misionvision.jsp";
                    break;
                case "/contactanos":
                    pageToForward = "/pages/contactanos.jsp";
                    break;
                default:
                    pageToForward = "/pages/mainmenu.jsp"; 
                    break;
            }
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(pageToForward);
        dispatcher.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}