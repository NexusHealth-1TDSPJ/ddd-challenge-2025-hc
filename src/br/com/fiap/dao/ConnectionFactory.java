package br.com.fiap.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    public ConnectionFactory() {
    }

    public static Connection obterConecao() {
        Connection conexao = null;

        try {
            conexao = DriverManager.getConnection("jdbc:oracle:thin:@//oracle.fiap.com.br:1521/ORCL", "rm565771", "031204");
        } catch (SQLException erro) {
            erro.printStackTrace();
        }

        return conexao;
    }
}