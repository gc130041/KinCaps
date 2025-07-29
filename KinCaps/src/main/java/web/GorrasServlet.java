package web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.Cliente;
import modelo.Usuario;

@WebServlet(name = "GorrasServlet", urlPatterns = {"/gorras"})
public class GorrasServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession(false);

        if (session != null && session.getAttribute("usuario") != null) {
            Usuario usuario = (Usuario) session.getAttribute("usuario");
            
            if (usuario instanceof Cliente) {
                request.getRequestDispatcher("/pages/mainmenu.jsp").forward(request, response);
            } else {
                response.sendRedirect(request.getContextPath() + "/mantenimiento");
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/login");
        }
    }
}