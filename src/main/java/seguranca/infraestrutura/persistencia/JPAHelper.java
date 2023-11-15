package seguranca.infraestrutura.persistencia;

import javax.persistence.*;

public class JPAHelper {

    public static EntityManager getEntityManager() {
        return Persistence
                .createEntityManagerFactory("AplicacaoBancariaPU")
                .createEntityManager();
    }

}
