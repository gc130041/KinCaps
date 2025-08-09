package web;

import dao.GorrasDAO;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Gorras;

@WebServlet(name = "CatalogoServlet", urlPatterns = {"/gorras/catalogo"})
public class CatalogoServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("--- INICIANDO CatalogoServlet ---");

        GorrasDAO dao = new GorrasDAO();
        List<Gorras> listaGorras = dao.listarTodas();

        // --- LÍNEAS DE DEPURACIÓN CLAVE ---
        if (listaGorras == null) {
            System.out.println("El DAO devolvió una lista NULA.");
        } else {
            System.out.println("Número de gorras encontradas por el DAO: " + listaGorras.size());
            // Opcional: imprimir los detalles de la primera gorra si existe
            if (!listaGorras.isEmpty()) {
                Gorras primeraGorra = listaGorras.get(0);
                System.out.println("Detalles de la primera gorra: ID=" + primeraGorra.getIdGorra() + ", Marca=" + primeraGorra.getMarca());
            }
        }
        // --- FIN DE LÍNEAS DE DEPURACIÓN ---

        request.setAttribute("listaGorras", listaGorras);

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
