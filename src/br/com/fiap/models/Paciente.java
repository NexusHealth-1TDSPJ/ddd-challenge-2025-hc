package br.com.fiap.models;

import br.com.fiap.enums.TipoAtendiEnum;

public class Paciente {

    private int id_pac;
    private String nome_pac;
    private int idade_pac;
    private int nivel_tec;
    private TipoAtendiEnum tipoAtendiEnum;

    // Construtor padrão
    public Paciente() {}

    // Construtor completo
    public Paciente(int id_pac, String nome_pac, int idade_pac, int nivel_tec, TipoAtendiEnum tipoAtendiEnum) {
        this.id_pac = id_pac;
        this.nome_pac = nome_pac;
        this.idade_pac = idade_pac;
        this.nivel_tec = nivel_tec;
        this.tipoAtendiEnum = tipoAtendiEnum;
    }

    // Getters e Setters
    public int getId_pac() {
        return id_pac;
    }

    public void setId_pac(int id_pac) {
        this.id_pac = id_pac;
    }

    public String getNome_pac() {
        return nome_pac;
    }

    public void setNome_pac(String nome_pac) {
        this.nome_pac = nome_pac;
    }

    public int getIdade_pac() {
        return idade_pac;
    }

    public void setIdade_pac(int idade_pac) {
        this.idade_pac = idade_pac;
    }

    public int getNivel_tec() {
        return nivel_tec;
    }

    public void setNivel_tec(int nivel_tec) {
        this.nivel_tec = nivel_tec;
    }

    public TipoAtendiEnum getTipoAtendiEnum() {
        return tipoAtendiEnum;
    }

    public void setTipoAtendiEnum(TipoAtendiEnum tipoAtendiEnum) {
        this.tipoAtendiEnum = tipoAtendiEnum;
    }

    // toString para exibir os dados organizados
    @Override
    public String toString() {
        return "Paciente {" +
                "ID=" + id_pac +
                ", Nome='" + nome_pac + '\'' +
                ", Idade=" + idade_pac +
                ", Nível Técnico=" + nivel_tec +
                ", Tipo Atendimento=" + tipoAtendiEnum +
                '}';
    }
}
