/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import connection.ConexaoBancoMysql;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.DoseMedicamento;

/**
 *
 * @author Yure
 */
public class DoseMedicamentoDAO {
     public void inserir(DoseMedicamento d) throws SQLException {
        String sql = "INSERT INTO DOSE_MEDICAMENTO (id_paciente, cod_medicamento, data_dose, horario_dose, status, data_confirmacao) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConexaoBancoMysql.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setInt(1, d.getIdPaciente());
            stmt.setInt(2, d.getIdMedicamento());
            stmt.setDate(3, Date.valueOf(d.getDataDose()));
            stmt.setTime(4, Time.valueOf(d.getHorarioDose()));
            stmt.setString(5, d.getStatus());
            stmt.setTimestamp(6, d.getDataconfirmacao() != null ? Timestamp.valueOf(d.getDataconfirmacao()) : null);

            stmt.executeUpdate();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "erro ao adicionar medicamento " + e);
        }
        
        
       public List <DoseMedicamento> listarDosesDoDia(int idPaciente){
        List<DoseMedicamento> lista = new ArrayList<>();
        String sql = """
                     
                     SELECT dm.*, nome_medicamento
                     FROM DOSE_MEDICAMENTO dm
                     JOIN MEDICAMENTOS m ON dm.cod_medicamento = m.cod_medicamento
                     WHERE dm.id_paciente = ? 
                     AND dm.data_dose = CURDATE()
                     ORDER BY dm.horario_dose
                     """;

        try (Connection conn = ConexaoBancoMysql.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idPaciente);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                DoseMedicamento d = new DoseMedicamento();
                d.setId(rs.getInt("id_dose"));
                d.setIdPaciente(rs.getInt("id_paciente"));
                d.setIdMedicamento(rs.getInt("cod_medicamento"));
                d.setDataDose(rs.getDate("data_dose").toLocalDate());
                d.setHorarioDose(rs.getTime("horario_dose").toLocalTime());
                d.setStatus(rs.getString("status"));
                d.setNomeMedicamento(rs.getString("nome_medicamento"));
                lista.add(d);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "erro ao listar!" +e);
        }

        return lista;
    }
    }
}
