package br.com.fiap.models;

import br.com.fiap.enums.TipoAtendiEnum;

public class Paciente {
    private int id_pac;
    private String nome_pac;
    private int idade_pac;
    private int nivel_tec;
    private TipoAtendiEnum tipoAtendiEnum;

    public Paciente() {
    }

    public String toString() {
        int var10000 = this.id_pac;
        return "Paciente{id_pac=" + var10000 + ", nome_pac='" + this.nome_pac + "', idade_pac=" + this.idade_pac + ", nivel_tec=" + this.nivel_tec + ", tipoAtendiEnum=" + String.valueOf(this.tipoAtendiEnum) + "}";
    }

    public int getId_pac() {
        return this.id_pac;
    }

    public void setId_pac(int id_pac) {
        this.id_pac = id_pac;
    }

    public String getNome_pac() {
        return this.nome_pac;
    }

    public void setNome_pac(String nome_pac) {
        this.nome_pac = nome_pac;
    }

    public int getIdade_pac() {
        return this.idade_pac;
    }

    public void setIdade_pac(int idade_pac) {
        this.idade_pac = idade_pac;
    }

    public int getNivel_tec() {
        return this.nivel_tec;
    }

    public void setNivel_tec(int nivel_tec) {
        this.nivel_tec = nivel_tec;
    }

    public TipoAtendiEnum getTipoAtendiEnum() {
        return this.tipoAtendiEnum;
    }

    public void setTipoAtendiEnum(TipoAtendiEnum tipoAtendiEnum) {
        this.tipoAtendiEnum = tipoAtendiEnum;
    }
}
