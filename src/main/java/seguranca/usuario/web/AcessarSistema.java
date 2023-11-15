package seguranca.usuario.web;

import seguranca.usuario.dominio.AutenticarUsuario;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import seguranca.comum.LocalizadorServico;
import seguranca.comum.ValidarRecaptcha;

public class AcessarSistema extends HttpServlet {

    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        String responseToken = request.getParameter("g-recaptcha-response");

        AutenticarUsuario autenticarUsuario = LocalizadorServico.autenticarUsuario();
        ValidarRecaptcha validarRecaptcha = LocalizadorServico.validarRecaptcha();

        if (!autenticarUsuario.executar(email, senha)) {
            response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/index.jsp?invalido=true"));
            return;
        }

        if (!validarRecaptcha.executar(responseToken)) {
            response.sendRedirect(
                    response.encodeRedirectURL(request.getContextPath() + "/index.jsp?recaptcha_error=true"));
            return;
        }

        response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/restrito/principal.jsp"));
        request.getSession().setAttribute("AUTENTICADO", email);

    }

}
