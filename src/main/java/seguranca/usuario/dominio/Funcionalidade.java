package seguranca.usuario.dominio;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
public class Funcionalidade implements Serializable {

    @Id
    private String id;

    private String nome;
    private String descricao;

    public Funcionalidade() {
    }

    public Funcionalidade(String id, String nome, String descricao) {
        setId(id);
        setNome(nome);
        setDescricao(descricao);

    }

    public Funcionalidade(String id, String nome) {
        this(id, nome, null);
    }

    public Funcionalidade(String id) {
        this(id, null);
    }

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    private void setId(String id) {
        Objects.requireNonNull(id);
        id = id.trim();
        if (id.isEmpty()) {
            throw new IllegalArgumentException("O id não pode ser vazio");
        }
        this.id = id.toUpperCase();
    }

    public void setNome(String nome) {
        if (nome != null) {
            nome = nome.trim();
            if (nome.isEmpty()) {
                throw new IllegalArgumentException("O nome não pode ser vazio");
            }
        }
        this.nome = nome;

    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        if (descricao != null) {
            descricao = descricao.trim();
            if (descricao.isEmpty()) {
                throw new IllegalArgumentException("A descrição não pode ser vazia");
            }
        }

        this.descricao = descricao;
    }

    @Override
    public int hashCode() {
        System.out.println(id.hashCode());
        return id.hashCode();
    }

    @Override
    public boolean equals(Object object) {

        if (!(object instanceof Funcionalidade)) {
            return false;
        }
        Funcionalidade other = (Funcionalidade) object;
        return this.id.equals(other.id);
    }

}
