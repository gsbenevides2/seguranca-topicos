package seguranca.usuario.web;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import seguranca.comum.LocalizadorServico;
import seguranca.usuario.dominio.AutorizarUsuario;

public class GarantirAutorizacao implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String email = (String) httpRequest.getSession().getAttribute("AUTENTICADO");
        String recurso = httpRequest.getRequestURI().substring(httpRequest.getContextPath().length());
        String metodo = httpRequest.getMethod();
        AutorizarUsuario autorizarUsuario = LocalizadorServico.autorizarUsuario();
        if (autorizarUsuario.executar(recurso, metodo, email)) {
            chain.doFilter(request, response);
        } else {
            httpResponse.sendRedirect(
                    httpResponse.encodeRedirectURL(httpRequest.getContextPath() + "/naoAutorizado.jsp"));
        }
    }
}
