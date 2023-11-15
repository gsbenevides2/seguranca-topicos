<%@page import="seguranca.usuario.dominio.Usuario"%>
<%@page import="seguranca.usuario.dominio.UsuarioRepositorio"%>
<%@page import="seguranca.comum.LocalizadorServico"%>
<%@ page import="seguranca.usuario.dominio.Funcionalidade" %>
<%@ page import="seguranca.usuario.dominio.Papel" %>
<%@ page import="java.io.FileReader" %>
<%@ page import="java.io.BufferedReader" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Preparar banco de dados</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <%

            UsuarioRepositorio repositorio = LocalizadorServico.usuarioRepositorio();
            
            Funcionalidade f1 = new Funcionalidade("VER_PROPRIAS_NOTAS");
            Funcionalidade f2 = new Funcionalidade("VER_TODAS_NOTAS");
            Funcionalidade f3 = new Funcionalidade("LANCAR_NOTAS");
            Funcionalidade f4 = new Funcionalidade("REVISAR_PLANOS_DE_ENSINO");
            Funcionalidade f5 = new Funcionalidade("ACESSAR_PAINEL_PRINCIPAL");
            
            Papel usuario = new Papel("Usuario");
            usuario.addPermissaoPara(f5);
            
            // Coordenador
            Papel coordenador = new Papel("Coordenador");
            coordenador.extendePermissoes(usuario);
            coordenador.addPermissaoPara(f2);
            coordenador.addPermissaoPara(f4);
            
            Usuario usCoordenador = new Usuario("coordenador@email.com", "abc12345");
            usCoordenador.addPapel(coordenador);

            // Professor
            Papel professor = new Papel("Professor");
            professor.extendePermissoes(usuario);
            professor.addPermissaoPara(f3);

            Usuario usProfessor = new Usuario("professor@email.com", "abc12345");
            usProfessor.addPapel(professor);

            // Aluno
            Papel aluno = new Papel("aluno");
            aluno.extendePermissoes(usuario);
            aluno.addPermissaoPara(f1);
            
            Usuario usAluno = new Usuario("aluno@email.com", "abc12345");
            usAluno.setId(1L);
            usAluno.addPapel(aluno);
            
            repositorio.iniciarTransacao();
            repositorio.criar(usAluno);
            repositorio.criar(usProfessor);
            repositorio.criar(usCoordenador);

            repositorio.confirmarTransacao();
        %>
        <h1>Banco preparado!</h1>
    </body>
</html>

