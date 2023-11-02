package seguranca.usuario.dominio;

import java.util.Optional;

public interface UsuarioRepositorio {

    void atualizar(Usuario usuario);

    void cancelarTransacao();

    void confirmarTransacao();

    Optional<Usuario> consultarPorEmail(String email);

    void criar(Usuario usuario);

    void iniciarTransacao();
    
}
