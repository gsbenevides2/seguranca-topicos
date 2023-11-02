package seguranca.usuario.dominio;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
public class Funcionalidade  implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String nome;
    private String descricao;

    public Funcionalidade() {
    }
    public Funcionalidade(String nome) {
        setNome(nome);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        Objects.requireNonNull(nome);
        nome = nome.trim();
        if(nome.isEmpty()) {
            throw new IllegalArgumentException("O nome não pode ser vazio");
        }
        this.nome = nome.toUpperCase();
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        if(descricao != null) {
            descricao = descricao.trim();
            if(descricao.isEmpty()) {
                throw new IllegalArgumentException("A descrição não pode ser vazia");
            }
        }

        this.descricao = descricao;
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
        if (!(object instanceof Funcionalidade)) {
            return false;
        }
        Funcionalidade other = (Funcionalidade) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
    
}
