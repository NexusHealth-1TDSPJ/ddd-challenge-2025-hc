//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package br.com.fiap.dao;

import br.com.fiap.enums.TipoAtendiEnum;
import br.com.fiap.models.Profissional;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProfissionalDao {
    private Connection conexao;

    public ProfissionalDao() {
    }

    public void inserir(Profissional profissional) {
        this.conexao = ConnectionFactory.obterConecao();
        PreparedStatement comandoSql = null;

        try {
            String sql = "INSERT INTO PROFISSIONAL (id_profissional, nome_profissional, especialidade_profissional, tipo_atend, crm_profissional) VALUES (?, ?, ?, ?, ?)";
            comandoSql = this.conexao.prepareStatement(sql);
            comandoSql.setInt(1, profissional.getId_profissional());
            comandoSql.setString(2, profissional.getNome_profissional());
            comandoSql.setString(3, profissional.getEspecialidade_profissional());
            comandoSql.setString(4, profissional.getTipo_atend().toString());
            comandoSql.setInt(5, profissional.getCrm_profissional());
            comandoSql.executeUpdate();
            System.out.println("Profissional inserido com sucesso!");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir profissional", e);
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

    public void alterar(Profissional profissional) {
        this.conexao = ConnectionFactory.obterConecao();
        PreparedStatement comandoSql = null;

        try {
            String sql = "UPDATE PROFISSIONAL SET nome_profissional = ?, especialidade_profissional = ?, tipo_atend = ?, crm_profissional = ? WHERE id_profissional = ?";
            comandoSql = this.conexao.prepareStatement(sql);
            comandoSql.setString(1, profissional.getNome_profissional());
            comandoSql.setString(2, profissional.getEspecialidade_profissional());
            comandoSql.setString(3, profissional.getTipo_atend().toString());
            comandoSql.setInt(4, profissional.getCrm_profissional());
            comandoSql.setInt(5, profissional.getId_profissional());
            comandoSql.executeUpdate();
            System.out.println("Profissional alterado com sucesso!");
        } catch (SQLException e) {
            throw new RuntimeException(" Erro ao alterar profissional", e);
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
            String sql = "DELETE FROM PROFISSIONAL WHERE id_profissional = ?";
            comandoSql = this.conexao.prepareStatement(sql);
            comandoSql.setInt(1, id);
            comandoSql.executeUpdate();
            System.out.println("Profissional excluído com sucesso!");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir profissional", e);
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

    public Profissional buscarPorId(int id) {
        this.conexao = ConnectionFactory.obterConecao();
        Profissional profissional = null;
        PreparedStatement comandoSql = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT * FROM PROFISSIONAL WHERE id_profissional = ?";
            comandoSql = this.conexao.prepareStatement(sql);
            comandoSql.setInt(1, id);
            rs = comandoSql.executeQuery();
            if (rs.next()) {
                profissional = new Profissional();
                profissional.setId_profissional(rs.getInt("id_profissional"));
                profissional.setNome_profissional(rs.getString("nome_profissional"));
                profissional.setEspecialidade_profissional(rs.getString("especialidade_profissional"));
                profissional.setTipo_atend(TipoAtendiEnum.valueOf(rs.getString("tipo_atend")));
                profissional.setCrm_profissional(rs.getInt("crm_profissional"));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar profissional por ID", e);
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

        return profissional;
    }

    public List<Profissional> listar() {
        this.conexao = ConnectionFactory.obterConecao();
        List<Profissional> profissionais = new ArrayList();
        PreparedStatement comandoSql = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT * FROM PROFISSIONAL";
            comandoSql = this.conexao.prepareStatement(sql);
            rs = comandoSql.executeQuery();

            while(rs.next()) {
                Profissional profissional = new Profissional();
                profissional.setId_profissional(rs.getInt("id_profissional"));
                profissional.setNome_profissional(rs.getString("nome_profissional"));
                profissional.setEspecialidade_profissional(rs.getString("especialidade_profissional"));
                profissional.setTipo_atend(TipoAtendiEnum.valueOf(rs.getString("tipo_atend")));
                profissional.setCrm_profissional(rs.getInt("crm_profissional"));
                profissionais.add(profissional);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar profissionais", e);
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

        return profissionais;
    }
}
