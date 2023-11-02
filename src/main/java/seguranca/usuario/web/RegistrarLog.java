package seguranca.usuario.web;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;

public class RegistrarLog implements Filter {
    
    private static final DateFormat formatadorData = new SimpleDateFormat("ddMMyyyy HH:mm:ss");
    
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        
        System.out.printf("%s (%s): %s%n", 
                formatadorData.format(new Date()),
                httpRequest.getRemoteAddr(),
                httpRequest.getRequestURI());
        
        chain.doFilter(request, response);
    }

    public void destroy() {        
    }

    public void init(FilterConfig filterConfig) {        
    }
    
}
