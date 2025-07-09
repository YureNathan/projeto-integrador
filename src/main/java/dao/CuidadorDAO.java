/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import connection.ConexaoBancoMysql;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import model.Cuidador;

/**
 *
 * @author Yure
 */
public class CuidadorDAO {
    public void inserir(Cuidador c ) {
        String sql = "INSERT INTO CUIDADORES (nome_cuidador, telefone_cuidador, endereco_cuidador, email_cuidador, senha_cuidador) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conn = ConexaoBancoMysql.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, c.getNome_cuidador());
            stmt.setString(2, c.getTelefone_cuidador());
            stmt.setString(3, c.getEndereco_cuidador());
            stmt.setString(4, c.getEmail_cuidador());
            stmt.setString(5, c.getSenha_cuidador());
            stmt.executeUpdate();
            
        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(null, "Erro ao inserir cuidadoor!"+e);
        }
        
    }
    public Cuidador autenticar(String email, String senha) {
           String sql = "SELECT * FROM CUIDADORES WHERE email_cuidador = ? AND senha_cuidador = ?";
           
           try (Connection conn = ConexaoBancoMysql.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)){
               stmt.setString(1, email);
               stmt.setString(2, senha);
               
               ResultSet rs = stmt.executeQuery();
               
               if(rs.next()) {
                   Cuidador c = new Cuidador();
                   c.setId_cuidador(rs.getInt("id_cuidador"));
                   c.setNome_cuidador(rs.getString("nome_cuidador"));
                   
                   return c;
               }
           } catch (Exception e) {
               JOptionPane.showMessageDialog(null, "Erro ao encontrar cuidador"+ e);
           }
           return null;
       } 
}
