package seguranca.comum;

import javax.persistence.*;
import seguranca.infraestrutura.persistencia.JPAHelper;
import seguranca.usuario.dominio.AutenticarUsuario;
import seguranca.usuario.dominio.AutenticarUsuarioPadrao;
import seguranca.usuario.dominio.UsuarioRepositorio;
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
    
}
