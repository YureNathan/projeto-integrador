package dao;

import connection.ConexaoBancoMysql;
import model.DoseMedicamento;

import java.sql.*;
import java.time.LocalDate;
import java.util.*;

public class DoseMedicamentoDAO {

    public Map<String, Integer> contarPorStatus(int idPaciente) {
        Map<String, Integer> mapa = new HashMap<>();

        String sql = """
            SELECT status, COUNT(*) AS total
            FROM DOSE_MEDICAMENTO
            WHERE id_paciente = ?
              AND data_dose = CURDATE()
            GROUP BY status
        """;

        // Inicializa com 0 para garantir mesmo que não haja registros
        mapa.put("tomado", 0);
        mapa.put("pendente", 0);
        mapa.put("esquecido", 0);

        try (Connection conn = ConexaoBancoMysql.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idPaciente);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String status = rs.getString("status");
                int total = rs.getInt("total");
                mapa.put(status, total);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao contar doses por status: " + e.getMessage());
        }

        return mapa;
    }

    public List<DoseMedicamento> listarDosesDoDia(int idPaciente) {
        List<DoseMedicamento> lista = new ArrayList<>();

        String sql = """
            SELECT dm.id_dose, dm.status, dm.horario_dose, m.nome_medicamento
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
                DoseMedicamento dose = new DoseMedicamento();
                dose.setId(rs.getInt("id_dose"));
                dose.setStatus(rs.getString("status"));
                dose.setHorarioDose(rs.getTime("horario_dose").toLocalTime());
                dose.setNomeMedicamento(rs.getString("nome_medicamento"));
                lista.add(dose);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar doses do dia: " + e.getMessage());
        }

        return lista;
    }

    public void atualizarStatus(int idDose, String novoStatus) {
        String sql = "UPDATE DOSE_MEDICAMENTO SET status = ?, data_confirmacao = NOW() WHERE id_dose = ?";

        try (Connection conn = ConexaoBancoMysql.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, novoStatus);
            stmt.setInt(2, idDose);
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar status da dose: " + e.getMessage());
        }
    }

    // (Opcional) Geração de doses a partir da agenda (executado no início do dia)
    public void gerarDosesDoDia(int idPaciente) {
        String diaSemana = switch (LocalDate.now().getDayOfWeek()) {
            case MONDAY -> "SEG";
            case TUESDAY -> "TER";
            case WEDNESDAY -> "QUA";
            case THURSDAY -> "QUI";
            case FRIDAY -> "SEX";
            case SATURDAY -> "SAB";
            case SUNDAY -> "DOM";
        };

        String sql = """
            INSERT INTO DOSE_MEDICAMENTO (id_paciente, cod_medicamento, data_dose, horario_dose, status)
            SELECT a.id_paciente, a.cod_medicamento, CURDATE(), a.horario_medicamento, 'pendente'
            FROM AGENDA_MEDICAMENTOS a
            WHERE a.id_paciente = ?
              AND a.dia_medicamento = ?
              AND NOT EXISTS (
                  SELECT 1 FROM DOSE_MEDICAMENTO d
                  WHERE d.id_paciente = a.id_paciente
                    AND d.cod_medicamento = a.cod_medicamento
                    AND d.data_dose = CURDATE()
                    AND d.horario_dose = a.horario_medicamento
              )
        """;

        try (Connection conn = ConexaoBancoMysql.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idPaciente);
            stmt.setString(2, diaSemana);
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Erro ao gerar doses do dia: " + e.getMessage());
        }
    }
}
