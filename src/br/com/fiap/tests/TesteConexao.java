package br.com.fiap.tests;

import br.com.fiap.dao.ConnectionFactory;

public class TesteConexao {
    public TesteConexao() {
    }

    public static void main(String[] args) {
        System.out.println("teste de conexao com o banco de dados");
        if (ConnectionFactory.obterConexao() == null) {
            System.out.println("erro ao estabelecer a conexao");
        } else {
            System.out.println("a conexao foi estabelecida com sucesso");
        }

    }
}
