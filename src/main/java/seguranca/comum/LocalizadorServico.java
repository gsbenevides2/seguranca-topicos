package seguranca.comum;

import javax.persistence.*;
import seguranca.infraestrutura.persistencia.JPAHelper;
import seguranca.usuario.dominio.AutenticarUsuario;
import seguranca.usuario.dominio.AutenticarUsuarioPadrao;
import seguranca.usuario.dominio.AutorizarUsuario;
import seguranca.usuario.dominio.AutorizarUsuarioPadrao;
import seguranca.usuario.dominio.FuncionalidadeRepositorio;
import seguranca.usuario.dominio.UsuarioRepositorio;
import seguranca.usuario.persistencia.FuncionalidadeRepositorioJPA;
import seguranca.usuario.persistencia.UsuarioRepositorioJPA;

public class LocalizadorServico {

    public static AutenticarUsuario autenticarUsuario() {
        return new AutenticarUsuarioPadrao(usuarioRepositorio());
    }

    public static UsuarioRepositorio usuarioRepositorio() {
        return new UsuarioRepositorioJPA(entityManager());
    }

    public static EntityManager entityManager() {
        return JPAHelper.getEntityManager();
    }

    public static ValidarRecaptcha validarRecaptcha() {
        return new ValidarRecaptchaGoogle();
    }

    public static FuncionalidadeRepositorio funcionalidadeRepositorio() {
        return new FuncionalidadeRepositorioJPA(entityManager());
    }

    public static AutorizarUsuario autorizarUsuario() {
        return new AutorizarUsuarioPadrao(funcionalidadeRepositorio(), usuarioRepositorio());
    }

}
