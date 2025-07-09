/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import connection.ConexaoBancoMysql;
import model.Medicamento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Yure
 */
public class MedicamentoDAO {

    public void inserir(Medicamento m) throws SQLException {
        String sql = "INSERT INTO MEDICAMENTOS (nome_medicamento, dosagem_medicamento, unidade_dosagem, descricao_medicamento) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConexaoBancoMysql.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, m.getNome());
            stmt.setDouble(2, m.getDosagem());
            stmt.setString(3, m.getUnidade());
            stmt.setString(4, m.getDescricao());

            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar!" + e);
        }
    }

    public List<Medicamento> exibir() {
        List<Medicamento> medicamentos = new ArrayList<>();
        String sql = "SELECT * FROM MEDICAMENTOS";

        try (Connection conn = ConexaoBancoMysql.conectar(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Medicamento m = new Medicamento();
                m.setNome(rs.getString("nome_medicamento"));
                m.setDosagem(rs.getDouble("dosagem_medicamento"));
                m.setDescricao(rs.getString("descricao_medicamento"));
                m.setUnidade(rs.getString("unidade_dosagem"));
                medicamentos.add(m);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar Medicamentos"+ e);
        }
        return medicamentos;
    }
}
