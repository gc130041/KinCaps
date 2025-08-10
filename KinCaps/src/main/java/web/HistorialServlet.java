package web;

import dao.CarritoDAO;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.Carrito;
import modelo.Cliente;

@WebServlet(name = "HistorialServlet", urlPatterns = {"/historial"})
public class HistorialServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Cliente clienteLogueado = (Cliente) session.getAttribute("clienteLogueado");

        if (clienteLogueado == null) {
            response.sendRedirect(request.getContextPath() + "/pages/login.jsp");
            return;
        }

        CarritoDAO carritoDAO = new CarritoDAO();
        List<Carrito> listaPedidos = carritoDAO.buscarPedidosPorCliente(clienteLogueado.getIdCliente());

        request.setAttribute("listaPedidos", listaPedidos);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/historial.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Servlet para mostrar el historial de compras del cliente.";
    }
}