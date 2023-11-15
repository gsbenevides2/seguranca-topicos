package seguranca.usuario.persistencia;

import javax.persistence.*;
import seguranca.usuario.dominio.Usuario;
import seguranca.usuario.dominio.UsuarioRepositorio;
import java.util.Optional;

public class UsuarioRepositorioJPA implements UsuarioRepositorio {

    private final EntityManager entityManager;

    public UsuarioRepositorioJPA(EntityManager daoGenerico) {
        this.entityManager = daoGenerico;
    }

    @Override
    public void iniciarTransacao() {
        entityManager.getTransaction().begin();
    }

    @Override
    public void confirmarTransacao() {
        entityManager.getTransaction().commit();
    }

    @Override
    public void cancelarTransacao() {
        entityManager.getTransaction().rollback();
    }

    @Override
    public void criar(Usuario usuario) {

        usuario.getPapeis().forEach(papel -> {
            papel.getControlesAcesso().forEach(controleAcesso -> {
                System.out.println(controleAcesso.getFuncionalidade().getId());
            });
        });

        entityManager.persist(usuario);
    }

    @Override
    public void atualizar(Usuario usuario) {
        entityManager.merge(usuario);
    }

    @Override
    public Optional<Usuario> consultarPorEmail(String email) {
        Query consulta = entityManager.createQuery("SELECT us FROM Usuario us WHERE us.email=:email", Usuario.class);
        consulta.setParameter("email", email);
        try {
            return Optional.of((Usuario) consulta.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

}
