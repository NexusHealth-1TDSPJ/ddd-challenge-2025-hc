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
    }

    public void inserir(Paciente paciente) {
        this.conexao = ConnectionFactory.obterConecao();
        PreparedStatement comandoSql = null;

        try {
            String sql = "INSERT INTO PACIENTE (id_pac, nome_pac, idade_pac, nivel_tec, tipo_atendimento) VALUES (?, ?, ?, ?, ?)";
            comandoSql = this.conexao.prepareStatement(sql);
            comandoSql.setInt(1, paciente.getId_pac());
            comandoSql.setString(2, paciente.getNome_pac());
            comandoSql.setInt(3, paciente.getIdade_pac());
            comandoSql.setInt(4, paciente.getNivel_tec());
            comandoSql.setString(5, paciente.getTipoAtendiEnum().toString());
            comandoSql.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir paciente", e);
        } finally {
            try {
                if (comandoSql != null) {
                    comandoSql.close();
                }

                if (this.conexao != null) {
                    this.conexao.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

    }

    public void alterar(Paciente paciente) {
        this.conexao = ConnectionFactory.obterConecao();
        PreparedStatement comandoSql = null;

        try {
            String sql = "UPDATE PACIENTE SET nome_pac = ?, idade_pac = ?, nivel_tec = ?, tipo_atendimento = ? WHERE id_pac = ?";
            comandoSql = this.conexao.prepareStatement(sql);
            comandoSql.setString(1, paciente.getNome_pac());
            comandoSql.setInt(2, paciente.getIdade_pac());
            comandoSql.setInt(3, paciente.getNivel_tec());
            comandoSql.setString(4, paciente.getTipoAtendiEnum().toString());
            comandoSql.setInt(5, paciente.getId_pac());
            comandoSql.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao alterar paciente", e);
        } finally {
            try {
                if (comandoSql != null) {
                    comandoSql.close();
                }

                if (this.conexao != null) {
                    this.conexao.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

    }

    public void excluir(int id) {
        this.conexao = ConnectionFactory.obterConecao();
        PreparedStatement comandoSql = null;

        try {
            String sql = "DELETE FROM PACIENTE WHERE id_pac = ?";
            comandoSql = this.conexao.prepareStatement(sql);
            comandoSql.setInt(1, id);
            comandoSql.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir paciente", e);
        } finally {
            try {
                if (comandoSql != null) {
                    comandoSql.close();
                }

                if (this.conexao != null) {
                    this.conexao.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

    }

    public Paciente buscarPorId(int id) {
        this.conexao = ConnectionFactory.obterConecao();
        Paciente paciente = null;
        PreparedStatement comandoSql = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT * FROM PACIENTE WHERE id_pac = ?";
            comandoSql = this.conexao.prepareStatement(sql);
            comandoSql.setInt(1, id);
            rs = comandoSql.executeQuery();
            if (rs.next()) {
                paciente = new Paciente();
                paciente.setId_pac(rs.getInt("id_pac"));
                paciente.setNome_pac(rs.getString("nome_pac"));
                paciente.setIdade_pac(rs.getInt("idade_pac"));
                paciente.setNivel_tec(rs.getInt("nivel_tec"));
                paciente.setTipoAtendiEnum(TipoAtendiEnum.valueOf(rs.getString("tipo_atendimento")));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar paciente por ID", e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }

                if (comandoSql != null) {
                    comandoSql.close();
                }

                if (this.conexao != null) {
                    this.conexao.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

        return paciente;
    }

    public List<Paciente> listar() {
        this.conexao = ConnectionFactory.obterConecao();
        List<Paciente> pacientes = new ArrayList();
        PreparedStatement comandoSql = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT * FROM PACIENTE";
            comandoSql = this.conexao.prepareStatement(sql);
            rs = comandoSql.executeQuery();

            while(rs.next()) {
                Paciente paciente = new Paciente();
                paciente.setId_pac(rs.getInt("id_pac"));
                paciente.setNome_pac(rs.getString("nome_pac"));
                paciente.setIdade_pac(rs.getInt("idade_pac"));
                paciente.setNivel_tec(rs.getInt("nivel_tec"));
                paciente.setTipoAtendiEnum(TipoAtendiEnum.valueOf(rs.getString("tipo_atendimento")));
                pacientes.add(paciente);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar pacientes", e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }

                if (comandoSql != null) {
                    comandoSql.close();
                }

                if (this.conexao != null) {
                    this.conexao.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

        return pacientes;
    }
}
