package br.com.fiap.models;

import br.com.fiap.enums.TipoAtendiEnum;

public class Profissional {
    private int id_profissional;
    private String nome_profissional;
    private String especialidade_profissional;
    private TipoAtendiEnum tipo_atend;
    private int crm_profissional;

    public Profissional() {
    }

    public String toString() {
        int var10000 = this.id_profissional;
        return "Profissional{id_profissional=" + var10000 + ", nome_profissional='" + this.nome_profissional + "', especialidade_profissional='" + this.especialidade_profissional + "', tipo_atend=" + String.valueOf(this.tipo_atend) + ", crm_profissional=" + this.crm_profissional + "}";
    }

    public int getId_profissional() {
        return this.id_profissional;
    }

    public void setId_profissional(int id_profissional) {
        this.id_profissional = id_profissional;
    }

    public String getNome_profissional() {
        return this.nome_profissional;
    }

    public void setNome_profissional(String nome_profissional) {
        this.nome_profissional = nome_profissional;
    }

    public String getEspecialidade_profissional() {
        return this.especialidade_profissional;
    }

    public void setEspecialidade_profissional(String especialidade_profissional) {
        this.especialidade_profissional = especialidade_profissional;
    }

    public TipoAtendiEnum getTipo_atend() {
        return this.tipo_atend;
    }

    public void setTipo_atend(TipoAtendiEnum tipo_atend) {
        this.tipo_atend = tipo_atend;
    }

    public int getCrm_profissional() {
        return this.crm_profissional;
    }

    public void setCrm_profissional(int crm_profissional) {
        this.crm_profissional = crm_profissional;
    }
}