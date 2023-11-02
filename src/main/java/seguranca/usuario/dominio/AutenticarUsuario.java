package seguranca.usuario.dominio;

public interface AutenticarUsuario {
    
    boolean executar(String email, String senha);
    
}
