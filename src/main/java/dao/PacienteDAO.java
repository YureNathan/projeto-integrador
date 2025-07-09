/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.Paciente;
import connection.ConexaoBancoMysql;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Yure
 */
public class PacienteDAO {

    public void inserir(Paciente p) throws SQLDataException {
        String sql = "INSERT INTO PACIENTES (cpf_paciente, nome_paciente, telefone_paciente, endereco_paciente, alergias_paciente) VALUES (?,?,?,?,?)";

        try (Connection conn = ConexaoBancoMysql.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, p.getCpf_paciente());
            stmt.setString(2, p.getNome_paciente());
            stmt.setString(3, p.getTelefone_paciente());
            stmt.setString(4, p.getEndereco_paciente());
            stmt.setString(5, p.getAlergias_paciente());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Paciente salvo com sucesso!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao inserir dados do Paciente" + e);
        }
    }

    public List<Paciente> exibir() {
        List<Paciente> pacientes = new ArrayList<>();
        String sql = "SELECT * FROM PACIENTES";

        try (Connection conn = ConexaoBancoMysql.conectar(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Paciente p = new Paciente();
                p.setNome_paciente(rs.getString("nome_paciente"));
                p.setCpf_paciente(rs.getString("cpf_paciente"));
                p.setTelefone_paciente(rs.getString("telefone_paciente"));
                p.setEndereco_paciente(rs.getString("Endereco_paciente"));
                p.setAlergias_paciente(rs.getString("alergias_paciente"));
                pacientes.add(p);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar pacientes!" + e);
        }
        return pacientes;
    }

}
