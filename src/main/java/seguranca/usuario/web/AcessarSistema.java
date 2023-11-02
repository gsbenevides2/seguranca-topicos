package seguranca.usuario.web;

import seguranca.usuario.dominio.AutenticarUsuario;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import seguranca.comum.LocalizadorServico;

public class AcessarSistema extends HttpServlet {

    protected void doPost(HttpServletRequest request, 
                        HttpServletResponse response)
            throws ServletException, IOException {
        
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        
        AutenticarUsuario autenticarUsuario = LocalizadorServico.autenticarUsuario();
        if(autenticarUsuario.executar(email, senha)) {
            request.getSession().setAttribute("AUTENTICADO", email);
            response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/restrito/principal.jsp"));
        } else {
            response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/index.jsp?invalido=true"));
        }
        
    }

}

