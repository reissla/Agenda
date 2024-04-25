/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author davi4
 */
public class DAO {
    //Módulo de conexão
    //parametros de conexao:
    private String driver = "com.mysql.cj.jdbc.Driver";
    private String url = "jdbc:mysql://127.0.0.1:3306/dbagenda?useTimezone=true&serverTimezone=UTC"; 
    private String user = "root";
    private String password = "7598460123";

    //metodo de conexao:      
    private Connection conectar(){
        Connection con = null;
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, user, password);
            return con;
        } catch (Exception e){
            System.out.println(e);
            return null;
        }
    }

    //CRUD 
    //Create:
    public void inserirContato(JavaBeans contato){
        String create = "insert into contatos(nome, fone, email) values (?,?,?)";
        try {
            //abrir conexao:
            Connection con = conectar();
            //Preparar a querry para execucao no BD:
            PreparedStatement pst = con.prepareStatement(create);
            //Substituir os parametros(?) pelo conteudo das variaveis JavaBeans
            pst.setString(1, contato.getNome());
            pst.setString(2, contato.getFone());
            pst.setString(3, contato.getEmail());
            //Executar Querry
            pst.executeUpdate();
            //Encerrar conexao
            con.close();
        } catch (Exception e){
            System.out.println(e);
        }
    }
    
    //READ:
    public ArrayList<JavaBeans> listarContatos(){

        ArrayList<JavaBeans> contatos = new ArrayList();
        String read = "select *from contatos order by nome";
        try {
            Connection con = conectar();
            PreparedStatement pst = con.prepareStatement(read);
            ResultSet rs = pst.executeQuery();
            while (rs.next()){
                String idcon = rs.getString(1);
                String nome = rs.getString(2);
                String fone = rs.getString(3);
                String email = rs.getString(4);
                contatos.add(new JavaBeans(idcon,nome,fone,email));
            }
            con.close();
            return contatos;
        } catch (Exception e){
            System.out.println(e);
            return null;
        }
    }
    
    //UPDATE:
    //Vai pegar o contato do Banco de dados
    public void selecionarContato(JavaBeans contato){
        String read2 = "select * from contatos where idcon=?";
        try {
            Connection con = conectar();
            PreparedStatement pst = con.prepareStatement(read2);
            pst.setString(1, contato.getIdcon());
            ResultSet rs = pst.executeQuery();
            while (rs.next()){
                contato.setIdcon(rs.getString(1));
                contato.setNome(rs.getString(2));
                contato.setFone(rs.getString(3));
                contato.setEmail(rs.getString(4));
            }
            con.close();
        } catch (Exception e){
            System.out.println(e);
        }
    }
    
    //editar o contato
    public void alterarContato(JavaBeans contato){
        String create = "update contatos set nome=?, fone=?, email=? where idcon=?";
        try{
            Connection con = conectar();
            PreparedStatement pst = con.prepareStatement(create);
            pst.setString(1, contato.getNome());
            pst.setString(2, contato.getFone());
            pst.setString(3, contato.getEmail());
            pst.setString(4, contato.getIdcon());
            pst.executeUpdate();
            con.close();
        } catch (Exception e){
            System.out.println(e);
        }
    }
    
    //Delete:
    public void deletarContato(JavaBeans contato){
        try {
            String deletar = "delete from contatos where idcon=?";
            Connection con = conectar();
            PreparedStatement pst = con.prepareStatement(deletar);
            pst.setString(1, contato.getIdcon());
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
