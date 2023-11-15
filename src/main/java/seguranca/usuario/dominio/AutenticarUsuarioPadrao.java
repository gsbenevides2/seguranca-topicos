package seguranca.usuario.dominio;

import java.util.Optional;

public class AutenticarUsuarioPadrao implements AutenticarUsuario {

    private final UsuarioRepositorio usuarioRepositorio;

    public AutenticarUsuarioPadrao(UsuarioRepositorio usuarioRepositorio) {
        this.usuarioRepositorio = usuarioRepositorio;
    }

    @Override
    public boolean executar(String email, String senha) {
        Optional<Usuario> opUsuario = usuarioRepositorio.consultarPorEmail(email);
        if (!opUsuario.isPresent()) {
            return false;
        }

        Usuario usuario = opUsuario.get();
        return usuario.getEmail().equals(email) && usuario.getSenha().equals(senha);
    }

}
