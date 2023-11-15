package seguranca.usuario.dominio;

import java.util.Optional;

public interface FuncionalidadeRepositorio {

    Optional<Funcionalidade> consultarPorId(String id);
}