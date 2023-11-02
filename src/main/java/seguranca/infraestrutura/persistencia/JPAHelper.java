package seguranca.infraestrutura.persistencia;

import javax.persistence.*;

import java.util.HashMap;
import java.util.Map;

public class JPAHelper {

    public static EntityManager getEntityManager() {
        return Persistence
                .createEntityManagerFactory("AplicacaoBancariaPU")
                .createEntityManager();
    }
    
}
