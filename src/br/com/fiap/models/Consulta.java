package br.com.fiap.models;

import br.com.fiap.enums.TipoAtendiEnum;
import java.time.LocalDate;

public class Consulta {
    private int id_consulta;
    private TipoAtendiEnum tipo_consulta;
    private LocalDate data_consulta;
    private String motivo_consulta;
    private int fk_historico;
    private int fk_profissional;

    public Consulta() {
    }

    public int getId_consulta() {
        return this.id_consulta;
    }

    public void setId_consulta(int id_consulta) {
        this.id_consulta = id_consulta;
    }

    public TipoAtendiEnum getTipo_consulta() {
        return this.tipo_consulta;
    }

    public void setTipo_consulta(TipoAtendiEnum tipo_consulta) {
        this.tipo_consulta = tipo_consulta;
    }

    public LocalDate getData_consulta() {
        return this.data_consulta;
    }

    public void setData_consulta(LocalDate data_consulta) {
        this.data_consulta = data_consulta;
    }

    public String getMotivo_consulta() {
        return this.motivo_consulta;
    }

    public void setMotivo_consulta(String motivo_consulta) {
        this.motivo_consulta = motivo_consulta;
    }

    public int getFk_historico() {
        return this.fk_historico;
    }

    public void setFk_historico(int fk_historico) {
        this.fk_historico = fk_historico;
    }

    public int getFk_profissional() {
        return this.fk_profissional;
    }

    public void setFk_profissional(int fk_profissional) {
        this.fk_profissional = fk_profissional;
    }

    public String toString() {
        int var10000 = this.id_consulta;
        return "Consulta{id_consulta=" + var10000 +
                ", tipo_consulta='" + String.valueOf(this.tipo_consulta) + "', data_consulta=" + String.valueOf(this.data_consulta) +
                ", motivo_consulta='" + this.motivo_consulta + "', fk_historico=" + this.fk_historico +
                ", fk_profissional=" + this.fk_profissional + "}";
    }
}