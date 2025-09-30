package br.com.fiap.tests;

import br.com.fiap.dao.ConsultaDao;
import br.com.fiap.enums.TipoAtendiEnum;
import br.com.fiap.models.Consulta;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class TesteConsulta {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ConsultaDao dao = new ConsultaDao();
        int opcao;

        do {
            System.out.println("\n MENU CONSULTA ");
            System.out.println("1 - Cadastrar Consulta");
            System.out.println("2 - Buscar Consulta por ID");
            System.out.println("3 - Alterar Consulta");
            System.out.println("4 - Excluir Consulta");
            System.out.println("5 - Listar Todas as Consultas");
            System.out.println("6 - Contar Consultas de um Profissional");
            System.out.println("7 - Verificar Disponibilidade de Profissional");
            System.out.println("8 - Listar Consultas por Tipo");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 0 -> System.out.println("Programa encerrado!");
                case 1 -> cadastrarConsulta(sc, dao);
                case 2 -> buscarConsulta(sc, dao);
                case 3 -> alterarConsulta(sc, dao);
                case 4 -> excluirConsulta(sc, dao);
                case 5 -> listarConsultas(dao);
                case 6 -> contarConsultasProfissional(sc, dao);
                case 7 -> verificarDisponibilidade(sc, dao);
                case 8 -> listarConsultasPorTipo(sc, dao);

                default -> System.out.println("Opção inválida! Tente novamente.");
            }
        } while (opcao != 0);

    }

    private static void cadastrarConsulta(Scanner sc, ConsultaDao dao) {
        Consulta c = new Consulta();
        System.out.println("\nCADASTRAR CONSULTA ");
        System.out.print("Digite o ID da consulta: ");
        c.setId_consulta(sc.nextInt());
        sc.nextLine();

        TipoAtendiEnum tipo = null;
        while (tipo == null) {
            System.out.println("Selecione o tipo de consulta: 1-Presencial 2-Teleconsulta");
            int tipoOp = sc.nextInt();
            sc.nextLine();
            if (tipoOp == 1) tipo = TipoAtendiEnum.Presencial;
            else if (tipoOp == 2) tipo = TipoAtendiEnum.Teleconsulta;
            else System.out.println("Opção inválida.");
        }
        c.setTipo_consulta(tipo);

        boolean dataValida = false;
        while (!dataValida) {
            try {
                System.out.print("Digite a data da consulta (AAAA-MM-DD): ");
                String dataStr = sc.nextLine();
                c.setData_consulta(LocalDate.parse(dataStr));
                dataValida = true;
            } catch (DateTimeParseException e) {
                System.out.println("Data inválida! Use AAAA-MM-DD.");
            }
        }

        System.out.print("Digite o motivo da consulta: ");
        c.setMotivo_consulta(sc.nextLine());

        System.out.print("Digite o ID do paciente: ");
        c.setFk_paciente(sc.nextInt());
        sc.nextLine();

        System.out.print("Digite o ID do profissional responsável: ");
        c.setFk_profissional(sc.nextInt());
        sc.nextLine();

        dao.inserir(c);
        System.out.println("Consulta cadastrada com sucesso!");
    }

    private static void buscarConsulta(Scanner sc, ConsultaDao dao) {
        System.out.println("\n BUSCAR CONSULTA");
        System.out.print("Digite o ID da consulta: ");
        int id = sc.nextInt();
        sc.nextLine();

        Consulta c = dao.buscarPorId(id);
        if (c != null) System.out.println(c);
        else System.out.println("Nenhuma consulta encontrada.");
    }

    private static void alterarConsulta(Scanner sc, ConsultaDao dao) {
        Consulta c = new Consulta();
        System.out.println("\n ALTERAR CONSULTA ");
        System.out.print("Digite o ID da consulta que deseja alterar: ");
        c.setId_consulta(sc.nextInt());
        sc.nextLine();

        TipoAtendiEnum tipo = null;
        while (tipo == null) {
            System.out.println("Selecione o novo tipo de consulta: 1-Presencial 2-Teleconsulta");
            int tipoOp = sc.nextInt();
            sc.nextLine();
            if (tipoOp == 1) tipo = TipoAtendiEnum.Presencial;
            else if (tipoOp == 2) tipo = TipoAtendiEnum.Teleconsulta;
            else System.out.println("Opção inválida.");
        }
        c.setTipo_consulta(tipo);

        boolean dataValida = false;
        while (!dataValida) {
            try {
                System.out.print("Digite a nova data da consulta (AAAA-MM-DD): ");
                String dataStr = sc.nextLine();
                c.setData_consulta(LocalDate.parse(dataStr));
                dataValida = true;
            } catch (DateTimeParseException e) {
                System.out.println("Data inválida! Use AAAA-MM-DD.");
            }
        }

        System.out.print("Digite o novo motivo da consulta: ");
        c.setMotivo_consulta(sc.nextLine());

        System.out.print("Digite o ID do novo paciente: ");
        c.setFk_paciente(sc.nextInt());
        sc.nextLine();

        System.out.print("Digite o novo ID do profissional: ");
        c.setFk_profissional(sc.nextInt());
        sc.nextLine();

        dao.alterar(c);
        System.out.println("Consulta alterada com sucesso!");
    }

    private static void excluirConsulta(Scanner sc, ConsultaDao dao) {
        System.out.println("\n EXCLUIR CONSULTA ");
        System.out.print("Digite o ID da consulta que deseja excluir: ");
        int id = sc.nextInt();
        sc.nextLine();
        dao.excluir(id);
        System.out.println("Consulta excluída com sucesso!");
    }

    private static void listarConsultas(ConsultaDao dao) {
        System.out.println("\n LISTA DE CONSULTAS ");
        List<Consulta> consultas = dao.listar();
        if (consultas.isEmpty()) System.out.println("Nenhuma consulta cadastrada.");
        else consultas.forEach(System.out::println);
    }

    private static void contarConsultasProfissional(Scanner sc, ConsultaDao dao) {
        System.out.print("Digite o ID do profissional: ");
        int id = sc.nextInt();
        sc.nextLine();
        int total = dao.contarConsultasPorProfissional(id);
        System.out.println("Total de consultas do profissional: " + total);
    }

    private static void verificarDisponibilidade(Scanner sc, ConsultaDao dao) {
        System.out.print("Digite o ID do profissional: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Digite a data (AAAA-MM-DD): ");
        LocalDate data = LocalDate.parse(sc.nextLine());
        boolean disponivel = dao.isProfissionalDisponivel(id, data);
        System.out.println(disponivel ? "Profissional disponível." : " Profissional indisponível.");
    }

    private static void listarConsultasPorTipo(Scanner sc, ConsultaDao dao) {
        System.out.println("Selecione o tipo: 1-Presencial 2-Teleconsulta");
        int op = sc.nextInt();
        sc.nextLine();
        TipoAtendiEnum tipo = (op == 1) ? TipoAtendiEnum.Presencial : TipoAtendiEnum.Teleconsulta;
        List<Consulta> consultas = dao.listarPorTipo(tipo);
        if (consultas.isEmpty()) System.out.println("Nenhuma consulta desse tipo.");
        else consultas.forEach(System.out::println);
    }
}