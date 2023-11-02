<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Autenticação</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css">
        <script src="https://www.google.com/recaptcha/api.js" async defer></script>
    </head>
    <body>
        <div class="container">
            <h1>Autenticação</h1>
            <p>Informe o seu usuário e senha.</p>
            <c:if test="${param['invalido'] == true}">
                <span class="text-danger">Usuário e/ou senha inválido(s)!</span>
            </c:if>
            <c:if test="${param['proibido'] == true}">
                <span class="text-danger">Você não está autenticado! Autentique-se para acessar esse recurso.</span>
            </c:if>
            <c:if test="${param['recaptcha_error'] == true}">
                <span class="text-danger">Ocorreu em erro com o recaptcha. Tente novamente.</span>
            </c:if>
            <form action="${pageContext.request.contextPath}/acessar" method="POST">
                <div class="row">
                    <div class="col-12">
                        <label for="email">E-mail:</label>
                        <input type="email" required="required" id="email" name="email" class="form-control" />
                    </div>
                </div>
                <div class="row">
                    <div class="col-12">
                        <label for="password">Senha:</label>
                        <input type="password" required="required" id="password" name="senha" class="form-control" />
                    </div>
                </div>
                <div class="row">
                    <div class="col-12">
                        <div class="g-recaptcha" data-sitekey="6Lc7TuooAAAAADM0ASdrmoyGRnmNIDIn9L9jQto8"></div>
                    </div>
                </div>
                <button type="submit" class="btn btn-primary mt-2">Acessar</button>
            </form>
        </div>
    </body>
</html>
