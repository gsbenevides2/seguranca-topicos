package seguranca.usuario.persistencia;

import java.util.Optional;
import java.util.Objects;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import seguranca.usuario.dominio.Funcionalidade;
import seguranca.usuario.dominio.FuncionalidadeRepositorio;

public class FuncionalidadeRepositorioJPA implements FuncionalidadeRepositorio {

    private final EntityManager entityManager;

    public FuncionalidadeRepositorioJPA(EntityManager daoGenerico) {
        this.entityManager = daoGenerico;
    }

    @Override
    public Optional<Funcionalidade> consultarPorId(String id) {
        Objects.requireNonNull(id);
        id = id.trim().toUpperCase();

        TypedQuery<Funcionalidade> consulta = entityManager.createQuery(
                "SELECT f FROM Funcionalidade f WHERE f.id=:id",
                Funcionalidade.class);
        consulta.setParameter("id", id);

        try {
            return Optional.of(consulta.getSingleResult());
        } catch (Exception e) {
            return Optional.empty();
        }
    }

}
