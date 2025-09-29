package br.com.fiap.dao;

import br.com.fiap.models.Consulta;
import br.com.fiap.enums.TipoAtendiEnum;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConsultaDao {
    private Connection conexao = ConnectionFactory.obterConecao();

    public void inserir(Consulta c) {
        String sql = "INSERT INTO consulta (id_consulta, tipo_consulta, data_consulta, motivo_consulta, fk_historico, fk_profissional) VALUES (?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement ps = this.conexao.prepareStatement(sql);
            ps.setInt(1, c.getId_consulta());
            ps.setString(2, c.getTipo_consulta().name());
            ps.setDate(3, Date.valueOf(c.getData_consulta()));
            ps.setString(4, c.getMotivo_consulta());
            ps.setInt(5, c.getFk_historico());
            ps.setInt(6, c.getFk_profissional());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void alterar(Consulta c) {
        String sql = "UPDATE consulta SET tipo_consulta = ?, data_consulta = ?, motivo_consulta = ?, fk_historico = ?, fk_profissional = ? WHERE id_consulta = ?";

        try {
            PreparedStatement ps = this.conexao.prepareStatement(sql);
            ps.setString(1, c.getTipo_consulta().name());
            ps.setDate(2, Date.valueOf(c.getData_consulta()));
            ps.setString(3, c.getMotivo_consulta());
            ps.setInt(4, c.getFk_historico());
            ps.setInt(5, c.getFk_profissional());
            ps.setInt(6, c.getId_consulta());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void excluir(int id) {
        String sql = "DELETE FROM consulta WHERE id_consulta = ?";

        try {
            PreparedStatement ps = this.conexao.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public Consulta buscarPorId(int id) {
        Consulta c = null;
        String sql = "SELECT * FROM consulta WHERE id_consulta = ?";

        try {
            PreparedStatement ps = this.conexao.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                c = new Consulta();
                c.setId_consulta(rs.getInt("id_consulta"));
                c.setTipo_consulta(TipoAtendiEnum.valueOf(rs.getString("tipo_consulta").toUpperCase()));
                c.setData_consulta(rs.getDate("data_consulta").toLocalDate());
                c.setMotivo_consulta(rs.getString("motivo_consulta"));
                c.setFk_historico(rs.getInt("fk_historico"));
                c.setFk_profissional(rs.getInt("fk_profissional"));
            }

            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return c;
    }

    public List<Consulta> listar() {
        List<Consulta> consultas = new ArrayList();
        String sql = "SELECT * FROM consulta";

        try {
            PreparedStatement ps = this.conexao.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                Consulta c = new Consulta();
                c.setId_consulta(rs.getInt("id_consulta"));
                c.setTipo_consulta(TipoAtendiEnum.valueOf(rs.getString("tipo_consulta").toUpperCase()));
                c.setData_consulta(rs.getDate("data_consulta").toLocalDate());
                c.setMotivo_consulta(rs.getString("motivo_consulta"));
                c.setFk_historico(rs.getInt("fk_historico"));
                c.setFk_profissional(rs.getInt("fk_profissional"));
                consultas.add(c);
            }

            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return consultas;
    }
}