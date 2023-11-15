package seguranca.usuario.dominio;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

@Entity
public class Papel implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nome;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<ControleAcesso> controlesAcesso = new HashSet<>();

    public Papel() {
    }

    public Papel(String nome) {
        setNome(nome);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        Objects.requireNonNull(nome);
        nome = nome.trim();
        if (nome.isEmpty()) {
            throw new IllegalArgumentException("O nome não pode ser vazio");
        }
        this.nome = nome.toUpperCase();
    }

    public void addPermissaoPara(Funcionalidade funcionalidade) {
        this.controlesAcesso.forEach(it -> {
            System.out.println(it.getFuncionalidade().getId());
        });
        this.controlesAcesso.add(new ControleAcesso(funcionalidade));
    }

    public void denyPermissaoPara(Funcionalidade funcionalidade) {
        this.controlesAcesso.add(new ControleAcesso(funcionalidade, false));
    }

    public void addControleAcesso(ControleAcesso controleAcesso) {
        this.controlesAcesso.add(controleAcesso);
    }

    public boolean removeControleAcesso(Funcionalidade funcionalidade) {
        return this.controlesAcesso.removeIf(it -> it.getFuncionalidade().equals(funcionalidade));
    }

    public Acessos temAcessoA(Funcionalidade funcionalidade) {
        return this.controlesAcesso
                .stream()
                .filter(it -> it.getFuncionalidade().equals(funcionalidade))
                .map(it -> it.isPermitir() ? Acessos.PERMITIDO : Acessos.NEGADO)
                .findFirst()
                .orElse(Acessos.NAO_INFORMADO);
    }

    public void extendePermissoes(Papel papel) {
        Iterator<ControleAcesso> cA = papel.controlesAcesso.iterator();

        while (cA.hasNext()) {
            this.addControleAcesso(cA.next());
        }
    }

    public Set<ControleAcesso> getControlesAcesso() {
        return controlesAcesso;
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
        if (!(object instanceof Usuario)) {
            return false;
        }
        Papel other = (Papel) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

}
