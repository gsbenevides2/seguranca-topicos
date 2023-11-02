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
            
            Funcionalidade f1 = new Funcionalidade("Ver próprias notas");
            Funcionalidade f2 = new Funcionalidade("Ver todas notas");
            Funcionalidade f3 = new Funcionalidade("Lançar notas");
            Funcionalidade f4 = new Funcionalidade("Revisar planos de ensino");

            Papel coordenador = new Papel("Coordenador");
            Papel professor = new Papel("Professor");
            Papel aluno = new Papel("aluno");

            coordenador.addPermissaoPara(f2);
            coordenador.addPermissaoPara(f4);

            professor.addPermissaoPara(f3);

            aluno.addPermissaoPara(f1);
            
            Usuario usAluno = new Usuario("aluno@email.com", "abc12345");
            usAluno.setId(1L);
            usAluno.addPapel(aluno);

            Usuario usProfessor = new Usuario("professor@email.com", "abc12345");
            usProfessor.addPapel(professor);

            Usuario usCoordenador = new Usuario("coordenador@email.com", "abc12345");
            usCoordenador.addPapel(coordenador);
            
            repositorio.iniciarTransacao();
            repositorio.criar(usAluno);
            repositorio.criar(usProfessor);
            repositorio.criar(usCoordenador);
            repositorio.confirmarTransacao();
        %>
        <h1>Banco preparado!</h1>
    </body>
</html>

