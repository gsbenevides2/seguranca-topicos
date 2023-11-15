package seguranca.usuario.dominio;

public interface AutorizarUsuario {
    public boolean executar(String recurso, String metodo, String email);
}
