package seguranca.usuario.dominio;

import java.util.HashMap;
import java.util.Optional;

public class AutorizarUsuarioPadrao implements AutorizarUsuario {
    private final FuncionalidadeRepositorio funcionalidadeRepositorio;
    private final UsuarioRepositorio usuarioRepositorio;

    private final HashMap<String, HashMap<String, String>> funcionalidadesPorMetodo = new HashMap<>();

    public AutorizarUsuarioPadrao(FuncionalidadeRepositorio funcionalidadeRepositorio,
            UsuarioRepositorio usuarioRepositorio) {
        this.funcionalidadeRepositorio = funcionalidadeRepositorio;
        this.usuarioRepositorio = usuarioRepositorio;

        /*
         * HashMap<String, String> funcionalidadesPorMetodoPost = new HashMap<>();
         * funcionalidadesPorMetodoPost.put("/restrito/notas", "Lançar notas");
         * funcionalidadesPorMetodoPost.put("/restrito/planos-de-ensino",
         * "Revisar planos de ensino");
         * this.funcionalidadesPorMetodo.put("POST", funcionalidadesPorMetodoPost);
         */

        HashMap<String, String> funcionalidadesPorMetodoGet = new HashMap<>();
        funcionalidadesPorMetodoGet.put("/restrito/notas.jsp", "VER_TODAS_NOTAS");
        funcionalidadesPorMetodoGet.put("/restrito/minhas-notas.jsp", "VER_PROPRIAS_NOTAS");
        funcionalidadesPorMetodoGet.put("/restrito/lancar-notas.jsp", "LANCAR_NOTAS");
        funcionalidadesPorMetodoGet.put("/restrito/planos-de-ensino.jsp", "REVISAR_PLANOS_DE_ENSINO");
        funcionalidadesPorMetodoGet.put("/restrito/principal.jsp", "ACESSAR_PAINEL_PRINCIPAL");
        this.funcionalidadesPorMetodo.put("GET", funcionalidadesPorMetodoGet);
    }

    private String getFuncionalidade(String recurso, String metodo) {
        HashMap<String, String> funcionalidadesPorMetodo = this.funcionalidadesPorMetodo.get(metodo);
        if (funcionalidadesPorMetodo == null) {
            return null;
        }

        return funcionalidadesPorMetodo.get(recurso);
    }

    @Override
    public boolean executar(String recurso, String metodo, String email) {
        String funcionalidadeStr = this.getFuncionalidade(recurso, metodo);
        if (funcionalidadeStr == null) {
            return false;
        }

        Optional<Funcionalidade> funcionalidadeObj = this.funcionalidadeRepositorio.consultarPorId(funcionalidadeStr);
        if (!funcionalidadeObj.isPresent()) {
            return false;
        }

        Optional<Usuario> usuarioObj = this.usuarioRepositorio.consultarPorEmail(email);

        if (!usuarioObj.isPresent()) {
            return false;
        }

        Funcionalidade funcionalidade = funcionalidadeObj.get();
        Usuario usuario = usuarioObj.get();

        return usuario.temAcessoA(funcionalidade);

    }

}
