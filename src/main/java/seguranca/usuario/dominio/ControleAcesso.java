package seguranca.usuario.dominio;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
public class ControleAcesso implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @ManyToOne(cascade=CascadeType.ALL)
    private Funcionalidade funcionalidade;
    private boolean permitir;

    public ControleAcesso() {
    }
    
    public ControleAcesso(Funcionalidade funcionalidade) {
        this(funcionalidade, true);
    }

    public ControleAcesso(Funcionalidade funcionalidade, boolean permitir) {
        setFuncionalidade(funcionalidade);
        setPermitir(permitir);
    }

    public Funcionalidade getFuncionalidade() {
        return funcionalidade;
    }

    private void setFuncionalidade(Funcionalidade funcionalidade) {
        this.funcionalidade = Objects.requireNonNull(funcionalidade);
    }

    public boolean isPermitir() {
        return permitir;
    }

    public void setPermitir(boolean permitir) {
        this.permitir = permitir;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ControleAcesso)) {
            return false;
        }
        ControleAcesso other = (ControleAcesso) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
    
}
