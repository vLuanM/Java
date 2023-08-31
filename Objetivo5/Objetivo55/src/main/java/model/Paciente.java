package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public class Paciente {
    private int cod_paciente;
    private String nome;
    private String rg;
    private String senha;

    public Paciente() {
    }
    public Paciente(String nome, String rg, String senha) {
        this.nome = nome;
        this.rg = rg;
        this.senha = senha;
    }
    public Paciente(int cod_paciente, String nome, String rg, String senha) {
        this.cod_paciente = cod_paciente;
        this.nome = nome;
        this.rg = rg;
        this.senha = senha;
    }

    public int getCod_paciente() {
        return cod_paciente;
    }

    public void setCod_paciente(int cod_paciente) {
        this.cod_paciente = cod_paciente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public String toString() {
        return "\nPaciente{" +
                ", cod_paciente=" + cod_paciente +
                ", nome='" + nome + '\'' +
                ", rg='" + rg + '\'' +
                ", senha='" + senha + '\'' +
                '}';
    }
}