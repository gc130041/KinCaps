package web;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(filterName = "AuthFilter", urlPatterns = {"/gorras/*", "/mantenimiento/*", "/pages/*"})
public class AuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String requestURI = httpRequest.getRequestURI();
        String contextPath = httpRequest.getContextPath();

        if (requestURI.equals(contextPath + "/login.jsp")
                || requestURI.equals(contextPath + "/login")
                || requestURI.equals(contextPath + "/terminos")
                || requestURI.equals(contextPath + "/politica")
                || requestURI.startsWith(contextPath + "/img/")
                || requestURI.startsWith(contextPath + "/style/")
                || requestURI.startsWith(contextPath + "/scripts/")) {

            chain.doFilter(request, response);
            return;
        }

        HttpSession session = httpRequest.getSession(false);

        if (session != null && session.getAttribute("usuario") != null) {
            chain.doFilter(request, response);
        } else {
            System.out.println("Acceso denegado a: " + requestURI + ". Redirigiendo a login.");
            httpResponse.sendRedirect(contextPath + "/login");
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }
}
