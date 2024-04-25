/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import model.DAO;
import model.JavaBeans;

/**
 *
 * @author davi4
 */

@WebServlet(name = "Controller", urlPatterns = {"/Controller", "/main", "/insert", "/select", "/update", "/delete"})
public class Controller extends HttpServlet {

    DAO dao = new DAO();
    JavaBeans contato = new JavaBeans();
    
    //vai receber a request do usuario 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();
        System.out.println(action);
        if (action.equals("/main")){
            contatos(request, response);
        } else if (action.equals("/insert")){
            novoContato(request, response);
        }else if (action.equals("/select")){
            listarContato(request, response);
        }else if (action.equals("/update")){
            editarContato(request, response);
        }else if (action.equals("/delete")){
            excluirContato(request, response);
        }else {
            response.sendRedirect("index.html");
        }
    }

    //Listar Contatos
    protected void contatos(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Criando um iobjeto que ira receber os dados JavaBeans
        ArrayList<JavaBeans> lista = dao.listarContatos();
        //Encaminhar a lista ao documneto agenda.jsp
        request.setAttribute("contatos", lista);
        RequestDispatcher rd = request.getRequestDispatcher("agenda.jsp");
        rd.forward(request, response);
    }

    //Novo Contato
    protected void novoContato(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Setar variaveis JavaBeans:
        contato.setNome(request.getParameter("nome"));
        contato.setFone(request.getParameter("fone"));
        contato.setEmail(request.getParameter("email"));
        
        dao.inserirContato(contato);
        response.sendRedirect("main");
    }
    
    //Editar contato
    protected void listarContato(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //vai puxar o parametro "idcon" da request /select 
        String idcon = request.getParameter("idcon");
        //setar variavel javabeans
        contato.setIdcon(idcon);
        dao.selecionarContato(contato);
        //Setar os atributos do formulario com o conteudo JavaBeans
        request.setAttribute("idcon", contato.getIdcon());
        request.setAttribute("nome", contato.getNome());
        request.setAttribute("fone", contato.getFone());
        request.setAttribute("email", contato.getEmail());
        //Encaminhar ao documento editar.jsp
        RequestDispatcher rd = request.getRequestDispatcher("editar.jsp");
        rd.forward(request, response);
    }
    
    protected void editarContato(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        contato.setIdcon(request.getParameter("idcon"));
        contato.setNome(request.getParameter("nome"));
        contato.setFone(request.getParameter("fone"));
        contato.setEmail(request.getParameter("email"));
        dao.alterarContato(contato);
        response.sendRedirect("main");
    }
    
    protected void excluirContato(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idcon = request.getParameter("idcon");
        contato.setIdcon(idcon);
        dao.deletarContato(contato);
        response.sendRedirect("main");
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
