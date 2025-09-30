package br.com.fiap.dao;

import br.com.fiap.enums.TipoAtendiEnum;
import br.com.fiap.models.Consulta;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ConsultaDao {
    private Connection conexao;

    public ConsultaDao() {
        this.conexao = ConnectionFactory.obterConexao();
    }

    // Inserir consulta
    public void inserir(Consulta c) {
        String sql = "INSERT INTO CONSULTA (id_consulta, tipo_consulta, data_consulta, motivo_consulta, fk_historico, fk_profissional) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setInt(1, c.getId_consulta());
            ps.setString(2, c.getTipo_consulta().toString());
            ps.setDate(3, Date.valueOf(c.getData_consulta()));
            ps.setString(4, c.getMotivo_consulta());
            ps.setInt(5, c.getFk_historico());
            ps.setInt(6, c.getFk_profissional());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir consulta", e);
        }
    }

    // Alterar consulta
    public void alterar(Consulta c) {
        String sql = "UPDATE CONSULTA SET tipo_consulta = ?, data_consulta = ?, motivo_consulta = ?, fk_historico = ?, fk_profissional = ? WHERE id_consulta = ?";
        try (PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setString(1, c.getTipo_consulta().toString());
            ps.setDate(2, Date.valueOf(c.getData_consulta()));
            ps.setString(3, c.getMotivo_consulta());
            ps.setInt(4, c.getFk_historico());
            ps.setInt(5, c.getFk_profissional());
            ps.setInt(6, c.getId_consulta());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao alterar consulta", e);
        }
    }

    // Excluir consulta
    public void excluir(int id) {
        String sql = "DELETE FROM CONSULTA WHERE id_consulta = ?";
        try (PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir consulta", e);
        }
    }

    // Buscar por ID
    public Consulta buscarPorId(int id) {
        Consulta c = null;
        String sql = "SELECT * FROM CONSULTA WHERE id_consulta = ?";
        try (PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    c = new Consulta();
                    c.setId_consulta(rs.getInt("id_consulta"));
                    c.setTipo_consulta(TipoAtendiEnum.fromString(rs.getString("tipo_consulta")));
                    c.setData_consulta(rs.getDate("data_consulta").toLocalDate());
                    c.setMotivo_consulta(rs.getString("motivo_consulta"));
                    c.setFk_historico(rs.getInt("fk_historico"));
                    c.setFk_profissional(rs.getInt("fk_profissional"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar consulta por ID", e);
        }
        return c;
    }

    // Listar todas
    public List<Consulta> listar() {
        List<Consulta> consultas = new ArrayList<>();
        String sql = "SELECT * FROM CONSULTA";
        try (PreparedStatement ps = conexao.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Consulta c = new Consulta();
                c.setId_consulta(rs.getInt("id_consulta"));
                c.setTipo_consulta(TipoAtendiEnum.fromString(rs.getString("tipo_consulta")));
                c.setData_consulta(rs.getDate("data_consulta").toLocalDate());
                c.setMotivo_consulta(rs.getString("motivo_consulta"));
                c.setFk_historico(rs.getInt("fk_historico"));
                c.setFk_profissional(rs.getInt("fk_profissional"));
                consultas.add(c);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar consultas", e);
        }
        return consultas;
    }

    // --- MÉTODOS DE REGRAS DE NEGÓCIO ---

    // 1. Contar consultas por profissional
    public int contarConsultasPorProfissional(int idProfissional) {
        int total = 0;
        String sql = "SELECT COUNT(*) FROM CONSULTA WHERE fk_profissional = ?";
        try (PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setInt(1, idProfissional);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) total = rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao contar consultas por profissional", e);
        }
        return total;
    }

    // 2. Listar consultas por data
    public List<Consulta> listarPorData(LocalDate data) {
        List<Consulta> consultas = new ArrayList<>();
        String sql = "SELECT * FROM CONSULTA WHERE data_consulta = ?";
        try (PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setDate(1, Date.valueOf(data));
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Consulta c = new Consulta();
                    c.setId_consulta(rs.getInt("id_consulta"));
                    c.setTipo_consulta(TipoAtendiEnum.fromString(rs.getString("tipo_consulta")));
                    c.setData_consulta(rs.getDate("data_consulta").toLocalDate());
                    c.setMotivo_consulta(rs.getString("motivo_consulta"));
                    c.setFk_historico(rs.getInt("fk_historico"));
                    c.setFk_profissional(rs.getInt("fk_profissional"));
                    consultas.add(c);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar consultas por data", e);
        }
        return consultas;
    }

    // 3. Verificar disponibilidade de profissional em uma data
    public boolean isProfissionalDisponivel(int idProfissional, LocalDate data) {
        String sql = "SELECT 1 FROM CONSULTA WHERE fk_profissional = ? AND data_consulta = ?";
        try (PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setInt(1, idProfissional);
            ps.setDate(2, Date.valueOf(data));
            try (ResultSet rs = ps.executeQuery()) {
                return !rs.next(); // se não houver registros, está disponível
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao verificar disponibilidade do profissional", e);
        }
    }

    // 4. Listar consultas por tipo de atendimento
    public List<Consulta> listarPorTipo(TipoAtendiEnum tipo) {
        List<Consulta> consultas = new ArrayList<>();
        String sql = "SELECT * FROM CONSULTA WHERE tipo_consulta = ?";
        try (PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setString(1, tipo.toString());
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Consulta c = new Consulta();
                    c.setId_consulta(rs.getInt("id_consulta"));
                    c.setTipo_consulta(TipoAtendiEnum.fromString(rs.getString("tipo_consulta")));
                    c.setData_consulta(rs.getDate("data_consulta").toLocalDate());
                    c.setMotivo_consulta(rs.getString("motivo_consulta"));
                    c.setFk_historico(rs.getInt("fk_historico"));
                    c.setFk_profissional(rs.getInt("fk_profissional"));
                    consultas.add(c);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar consultas por tipo", e);
        }
        return consultas;
    }
}
