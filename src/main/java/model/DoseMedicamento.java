/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import java.time.LocalTime;

/**
 *
 * @author Yure
 */
public class DoseMedicamento {
 private int id;
    private String nomeMedicamento;
    private LocalTime horarioDose;
    private String status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeMedicamento() {
        return nomeMedicamento;
    }

    public void setNomeMedicamento(String nomeMedicamento) {
        this.nomeMedicamento = nomeMedicamento;
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
}