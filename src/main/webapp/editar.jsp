<%-- 
    Document   : editar
    Created on : 17 de abr. de 2024, 21:45:44
    Author     : davi4
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <title>Agenda de Contatos</title>
        <link rel="icon" href="imagens/favicon.png">
        <link rel="stylesheet" href="style.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>    
        <h1>Editar Contato</h1>
        <form name="frmContato" action="update">
            <table>
                <tr>
                    <td>
                        <input type="text" name="idcon"  id="Caixa3" readonly value="<%out.print(request.getAttribute("idcon"));%>">
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="text" name="nome"  class="Caixa1" value="<%out.print(request.getAttribute("nome"));%>">
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="text" name="fone"  class="Caixa2" value="<%out.print(request.getAttribute("fone"));%>">
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="text" name="email" class="Caixa1" value="<%out.print(request.getAttribute("email"));%>">
                    </td>
                </tr>
            </table>    
            <input type="button" value="Salvar" class="Botao1" onclick="validar()">
        </form>
        <script src="scripts/validador.js"></script>
    </body>
</html>
