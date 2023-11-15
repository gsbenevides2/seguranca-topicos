package seguranca.usuario.dominio;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 100, nullable = false)
    private String email;
    @Column(length = 255, nullable = false)
    private String senha;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<ControleAcesso> controlesAcesso = new HashSet<>();

    @ManyToMany(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH,
            CascadeType.PERSIST }, fetch = FetchType.LAZY)
    private Set<Papel> papeis = new HashSet<>();

    public Usuario() {

    }

    public Usuario(String email, String senha) {
        setEmail(email);
        setSenha(senha);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Set<ControleAcesso> getControlesAcesso() {
        return controlesAcesso;
    }

    public void setControlesAcesso(Set<ControleAcesso> controlesAcesso) {
        this.controlesAcesso = controlesAcesso;
    }

    public void addPapel(Papel papel) {
        this.papeis.add(papel);
    }

    public Set<Papel> getPapeis() {
        return papeis;
    }

    private void setPapeis(Set<Papel> papeis) {
        this.papeis = papeis;
    }

    public boolean temAcessoA(Funcionalidade funcionalidade) {
        Acessos acesso = this.controlesAcesso
                .stream()
                .filter(it -> it.getFuncionalidade().equals(funcionalidade))
                .map(it -> it.isPermitir() ? Acessos.PERMITIDO : Acessos.NEGADO)
                .findFirst()
                .orElse(Acessos.NAO_INFORMADO);
        if (acesso == Acessos.NEGADO) {
            return false;
        }

        boolean algumPermitiu = false;
        for (Papel papel : papeis) {
            Acessos temAcessoViaPapel = papel.temAcessoA(funcionalidade);
            if (temAcessoViaPapel == Acessos.NEGADO) {
                return false;
            } else if (temAcessoViaPapel == Acessos.PERMITIDO) {
                algumPermitiu = true;
            }
        }

        return algumPermitiu;
    }

    public void addPermissaoPara(Funcionalidade funcionalidade) {
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
        Usuario other = (Usuario) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "seguranca.usuario.Usuario[ id=" + id + " ]";
    }

}
