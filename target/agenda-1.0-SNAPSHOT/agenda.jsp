<%-- 
    Document   : agenda
    Created on : 15 de abr. de 2024, 10:42:17
    Author     : davi4
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.JavaBeans" %>
<%@page import="java.util.ArrayList" %>
<%
    ArrayList<JavaBeans> lista = (ArrayList<JavaBeans>)request.getAttribute("contatos");
    
%>    
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Agenda de contatos</title>
        <link rel="icon" href="imagens/favicon.png">
        <link rel="stylesheet" href="style.css">
    </head>
    <body>
        <h1>Agenda de Contatos</h1>
        <table id="tabela">
            <thead>
                <tr>
                    <th>Id</th>
                    <th>Nome</th>
                    <th>Fone</th>
                    <th>E-mail</th>
                    <th>Opções</th>
                </tr> 
            </thead>
            <tbody>
                <%for (int i = 0; i < lista.size(); i++){%>
                    <tr>
                        <td><%=lista.get(i).getIdcon()%></td>
                        <td><%=lista.get(i).getNome()%></td>
                        <td><%=lista.get(i).getFone()%></td>
                        <td><%=lista.get(i).getEmail()%></td>
                        <td><a href="select?idcon=<%=lista.get(i).getIdcon()%>" class="Botao1">Editar</a></td>    
                        <td><a href="delete?idcon=<%=lista.get(i).getIdcon()%>" class="Botao2">Excluir</a></td>    
                        
                    </tr>
                <%}%>
            </tbody>
        </table>
            <br>
        <a href="novo.html" class="Botao1">Novo Contato</a>    
    </body>
</html>
