/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bean;

/**
 *
 * @author Yure
 */
public class Paciente {
    private int id_paciente;
    private long cpf_paciente;
    private String nome_paciente;
    private String telefone_paciente;
    private String endereco_paciente; 
    private String alergias_paciente;

    public int getId_paciente() {
        return id_paciente;
    }

    public void setId_paciente(int id_paciente) {
        this.id_paciente = id_paciente;
    }

    public long getCpf_paciente() {
        return cpf_paciente;
    }

    public void setCpf_paciente(int cpf_paciente) {
        this.cpf_paciente = cpf_paciente;
    }

    public String getNome_paciente() {
        return nome_paciente;
    }

    public void setNome_paciente(String nome_paciente) {
        this.nome_paciente = nome_paciente;
    }

    public String getTelefone_paciente() {
        return telefone_paciente;
    }

    public void setTelefone_paciente(String telefone_paciente) {
        this.telefone_paciente = telefone_paciente;
    }

    public String getEndereco_paciente() {
        return endereco_paciente;
    }

    public void setEndereco_paciente(String endereco_paciente) {
        this.endereco_paciente = endereco_paciente;
    }

    public String getAlergias_paciente() {
        return alergias_paciente;
    }

    public void setAlergias_paciente(String alergias_paciente) {
        this.alergias_paciente = alergias_paciente;
    }
}
