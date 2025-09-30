package br.com.fiap.tests;

import java.util.Scanner;

public class MenuPrincipal {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcao;

        while (true) {
            System.out.println("\n==== MENU PRINCIPAL ====");
            System.out.println("1 - Testar Conexão");
            System.out.println("2 - Paciente");
            System.out.println("3 - Profissional");
            System.out.println("4 - Consulta");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1 -> TesteConexao.main(null);
                case 2 -> TestePaciente.main(null);
                case 3 -> TesteProfissional.main(null);
                case 4 -> TesteConsulta.main(null);
                case 0 -> {
                    System.out.println("Programa encerrado!");
                    sc.close();
                    return;
                }
                default -> System.out.println("Opção inválida! Tente novamente.");
            }
        }

    }
}
