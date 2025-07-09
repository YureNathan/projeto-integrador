/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;

/**
 *
 * @author Yure
 */
public class DoseMedicamento {
    private int id;
    private int idPaciente;
    private int idMedicamento;
    private LocalDate dataDose;
    private LocalTime horarioDose;
    private String status;
    private LocalDateTime dataconfirmacao;
    private String nomeMedicamento; 
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }

    public int getIdMedicamento() {
        return idMedicamento;
    }

    public void setIdMedicamento(int idMedicamento) {
        this.idMedicamento = idMedicamento;
    }

    public LocalDate getDataDose() {
        return dataDose;
    }

    public void setDataDose(LocalDate dataDose) {
        this.dataDose = dataDose;
    }

    public LocalTime getHorarioDose() {
        return horarioDose;
    }

    public void setHorarioDose(LocalTime horarioDose) {
        this.horarioDose = horarioDose;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getDataconfirmacao() {
        return dataconfirmacao;
    }

    public void setDataconfirmacao(LocalDateTime dataconfirmacao) {
        this.dataconfirmacao = dataconfirmacao;
    }

    public String getNomeMedicamento() {
        return nomeMedicamento;
    }

    public void setNomeMedicamento(String nomeMedicamento) {
        this.nomeMedicamento = nomeMedicamento;
    }
    
}
