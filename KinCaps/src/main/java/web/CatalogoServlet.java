package web;

import dao.CarritoDAO;
import dao.DetalleCarritoDAO;
import dao.GorrasDAO;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.Gorras;

@WebServlet(name = "CatalogoServlet", urlPatterns = {"/gorras/catalogo"})
public class CatalogoServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("--- INICIANDO CatalogoServlet ---");

        GorrasDAO dao = new GorrasDAO();
        List<Gorras> listaGorras = dao.listarTodas();
        request.setAttribute("listaGorras", listaGorras);

        HttpSession session = request.getSession(false);
        List<modelo.DetalleCarrito> listaDetalleCarrito = new java.util.ArrayList<>();

        if (session != null && session.getAttribute("usuario") instanceof modelo.Cliente) {
            modelo.Cliente cliente = (modelo.Cliente) session.getAttribute("usuario");

            CarritoDAO carritoDAO = new CarritoDAO();
            modelo.Carrito carritoActivo = carritoDAO.buscarActivoPorCliente(cliente);

            if (carritoActivo != null) {
                DetalleCarritoDAO detalleDAO = new DetalleCarritoDAO();
                listaDetalleCarrito = detalleDAO.buscarPorCarrito(carritoActivo);
            }
        }
        request.setAttribute("listaDetalleCarrito", listaDetalleCarrito);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/catalogo.jsp");
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

    @Override
    public String getServletInfo() {
        return "Servlet para mostrar el catálogo de productos en /gorras/catalogo";
    }
}
