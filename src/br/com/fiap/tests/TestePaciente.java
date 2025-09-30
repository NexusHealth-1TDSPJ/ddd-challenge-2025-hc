package br.com.fiap.tests;

import br.com.fiap.dao.PacienteDao;
import br.com.fiap.enums.TipoAtendiEnum;
import br.com.fiap.models.Paciente;

import java.util.List;
import java.util.Scanner;

public class TestePaciente {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PacienteDao dao = new PacienteDao();
        int opcao;

        do {
            System.out.println("\n==== MENU PACIENTE ====");
            System.out.println("1 - Cadastrar Paciente");
            System.out.println("2 - Buscar Paciente por ID");
            System.out.println("3 - Alterar Paciente");
            System.out.println("4 - Excluir Paciente");
            System.out.println("5 - Listar Todos os Pacientes");
            System.out.println("6 - Contar Pacientes por Tipo de Atendimento");
            System.out.println("7 - Verificar Existência de Paciente");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 0 -> System.out.println(" Programa encerrado!");
                case 1 -> cadastrarPaciente(sc, dao);
                case 2 -> buscarPaciente(sc, dao);
                case 3 -> alterarPaciente(sc, dao);
                case 4 -> excluirPaciente(sc, dao);
                case 5 -> listarPacientes(dao);
                case 6 -> contarPorTipoAtendimento(sc, dao);
                case 7 -> verificarExistencia(sc, dao);
                default -> System.out.println(" Opção inválida! Tente novamente.");
            }

        } while (opcao != 0);

    }

    private static void cadastrarPaciente(Scanner sc, PacienteDao dao) {
        Paciente p = new Paciente();
        System.out.println("\nCADASTRAR PACIENTE");
        System.out.print("ID: ");
        p.setId_pac(sc.nextInt());
        sc.nextLine();
        System.out.print("Nome: ");
        p.setNome_pac(sc.nextLine());
        System.out.print("Idade: ");
        p.setIdade_pac(sc.nextInt());
        sc.nextLine();
        System.out.print("Nível técnico: ");
        p.setNivel_tec(sc.nextInt());
        sc.nextLine();

        TipoAtendiEnum tipo = null;
        while (tipo == null) {
            System.out.println("Tipo de atendimento: 1-Presencial 2-Teleconsulta");
            int op = sc.nextInt();
            sc.nextLine();
            if (op == 1) tipo = TipoAtendiEnum.Presencial;
            else if (op == 2) tipo = TipoAtendiEnum.Teleconsulta;
            else System.out.println("Opção inválida!");
        }
        p.setTipoAtendiEnum(tipo);

        dao.inserir(p);
        System.out.println(" Paciente cadastrado!");
    }

    private static void buscarPaciente(Scanner sc, PacienteDao dao) {
        System.out.print("Digite o ID do paciente: ");
        int id = sc.nextInt();
        sc.nextLine();
        Paciente p = dao.buscarPorId(id);
        if (p != null) System.out.println(p);
        else System.out.println(" Paciente não encontrado.");
    }

    private static void alterarPaciente(Scanner sc, PacienteDao dao) {
        System.out.print("Digite o ID do paciente a alterar: ");
        int id = sc.nextInt();
        sc.nextLine();
        Paciente p = dao.buscarPorId(id);
        if (p == null) {
            System.out.println(" Paciente não encontrado.");
            return;
        }

        System.out.print("Novo nome: ");
        p.setNome_pac(sc.nextLine());
        System.out.print("Nova idade: ");
        p.setIdade_pac(sc.nextInt());
        sc.nextLine();
        System.out.print("Novo nível técnico: ");
        p.setNivel_tec(sc.nextInt());
        sc.nextLine();

        TipoAtendiEnum tipo = null;
        while (tipo == null) {
            System.out.println("Novo tipo de atendimento: 1-Presencial 2-Teleconsulta");
            int op = sc.nextInt();
            sc.nextLine();
            if (op == 1) tipo = TipoAtendiEnum.Presencial;
            else if (op == 2) tipo = TipoAtendiEnum.Teleconsulta;
            else System.out.println(" Opção inválida!");
        }
        p.setTipoAtendiEnum(tipo);

        dao.alterar(p);
        System.out.println(" Paciente alterado!");
    }

    private static void excluirPaciente(Scanner sc, PacienteDao dao) {
        System.out.print("Digite o ID do paciente a excluir: ");
        int id = sc.nextInt();
        sc.nextLine();
        dao.excluir(id);
        System.out.println(" Paciente excluído!");
    }

    private static void listarPacientes(PacienteDao dao) {
        List<Paciente> lista = dao.listar();
        if (lista.isEmpty()) System.out.println("Nenhum paciente cadastrado.");
        else lista.forEach(System.out::println);
    }

    private static void contarPorTipoAtendimento(Scanner sc, PacienteDao dao) {
        TipoAtendiEnum tipo = null;
        while (tipo == null) {
            System.out.println("Tipo de atendimento: 1-Presencial 2-Teleconsulta");
            int op = sc.nextInt();
            sc.nextLine();
            if (op == 1) tipo = TipoAtendiEnum.Presencial;
            else if (op == 2) tipo = TipoAtendiEnum.Teleconsulta;
            else System.out.println("Opção inválida!");
        }
        int qtd = dao.contarPorTipoAtendimento(tipo);
        System.out.println("Total de pacientes: " + qtd);
    }

    private static void verificarExistencia(Scanner sc, PacienteDao dao) {
        System.out.print("Digite o ID do paciente: ");
        int id = sc.nextInt();
        sc.nextLine();
        boolean existe = dao.existePaciente(id);
        System.out.println(existe ? " Paciente existe." : "Paciente não existe.");
    }
}