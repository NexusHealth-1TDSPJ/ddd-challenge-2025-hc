package br.com.fiap.dao;

import br.com.fiap.enums.TipoAtendiEnum;
import br.com.fiap.models.Paciente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PacienteDao {
    private Connection conexao;

    public PacienteDao() {
        this.conexao = ConnectionFactory.obterConexao();
    }

    public void inserir(Paciente paciente) {
        String sql = "INSERT INTO PACIENTE (id_pac, nome_pac, idade_pac, nivel_tec, tipo_atendimento) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setInt(1, paciente.getId_pac());
            ps.setString(2, paciente.getNome_pac());
            ps.setInt(3, paciente.getIdade_pac());
            ps.setInt(4, paciente.getNivel_tec());
            ps.setString(5, paciente.getTipoAtendiEnum().toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir paciente", e);
        }
    }

    public void alterar(Paciente paciente) {
        String sql = "UPDATE PACIENTE SET nome_pac = ?, idade_pac = ?, nivel_tec = ?, tipo_atendimento = ? WHERE id_pac = ?";
        try (PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setString(1, paciente.getNome_pac());
            ps.setInt(2, paciente.getIdade_pac());
            ps.setInt(3, paciente.getNivel_tec());
            ps.setString(4, paciente.getTipoAtendiEnum().toString());
            ps.setInt(5, paciente.getId_pac());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao alterar paciente", e);
        }
    }

    public void excluir(int id) {
        String sql = "DELETE FROM PACIENTE WHERE id_pac = ?";
        try (PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir paciente", e);
        }
    }

    public Paciente buscarPorId(int id) {
        Paciente paciente = null;
        String sql = "SELECT * FROM PACIENTE WHERE id_pac = ?";
        try (PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    paciente = new Paciente();
                    paciente.setId_pac(rs.getInt("id_pac"));
                    paciente.setNome_pac(rs.getString("nome_pac"));
                    paciente.setIdade_pac(rs.getInt("idade_pac"));
                    paciente.setNivel_tec(rs.getInt("nivel_tec"));
                    paciente.setTipoAtendiEnum(TipoAtendiEnum.fromString(rs.getString("tipo_atendimento")));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar paciente por ID", e);
        }
        return paciente;
    }

    public List<Paciente> listar() {
        List<Paciente> pacientes = new ArrayList<>();
        String sql = "SELECT * FROM PACIENTE";
        try (PreparedStatement ps = conexao.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Paciente paciente = new Paciente();
                paciente.setId_pac(rs.getInt("id_pac"));
                paciente.setNome_pac(rs.getString("nome_pac"));
                paciente.setIdade_pac(rs.getInt("idade_pac"));
                paciente.setNivel_tec(rs.getInt("nivel_tec"));
                paciente.setTipoAtendiEnum(TipoAtendiEnum.fromString(rs.getString("tipo_atendimento")));
                pacientes.add(paciente);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar pacientes", e);
        }
        return pacientes;
    }


    public int contarPorTipoAtendimento(TipoAtendiEnum tipo) {
        int total = 0;
        String sql = "SELECT COUNT(*) FROM PACIENTE WHERE tipo_atendimento = ?";
        try (PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setString(1, tipo.toString());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) total = rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao contar pacientes por tipo", e);
        }
        return total;
    }

    public List<Paciente> listarPorFaixaIdade(int min, int max) {
        List<Paciente> pacientes = new ArrayList<>();
        String sql = "SELECT * FROM PACIENTE WHERE idade_pac BETWEEN ? AND ?";
        try (PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setInt(1, min);
            ps.setInt(2, max);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Paciente paciente = new Paciente();
                    paciente.setId_pac(rs.getInt("id_pac"));
                    paciente.setNome_pac(rs.getString("nome_pac"));
                    paciente.setIdade_pac(rs.getInt("idade_pac"));
                    paciente.setNivel_tec(rs.getInt("nivel_tec"));
                    paciente.setTipoAtendiEnum(TipoAtendiEnum.fromString(rs.getString("tipo_atendimento")));
                    pacientes.add(paciente);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar pacientes por faixa de idade", e);
        }
        return pacientes;
    }

    public boolean existePaciente(int id) {
        boolean existe = false;
        String sql = "SELECT 1 FROM PACIENTE WHERE id_pac = ?";
        try (PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) existe = true;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao verificar existência do paciente", e);
        }
        return existe;
    }
}
