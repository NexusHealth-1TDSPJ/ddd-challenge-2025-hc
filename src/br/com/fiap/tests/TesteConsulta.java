package br.com.fiap.tests;

import br.com.fiap.dao.ConsultaDao;
import br.com.fiap.enums.TipoAtendiEnum;
import br.com.fiap.models.Consulta;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class TesteConsulta {
    public TesteConsulta() {
    }

    public static void main(String[] args) {
        Scanner leitorNum = new Scanner(System.in);
        Scanner leitorTexto = new Scanner(System.in);
        ConsultaDao dao = new ConsultaDao();

        int opcao;
        do {
            System.out.println("\n==== MENU CONSULTA ====");
            System.out.println("1 - Cadastrar Consulta");
            System.out.println("2 - Buscar Consulta por ID");
            System.out.println("3 - Alterar Consulta");
            System.out.println("4 - Excluir Consulta");
            System.out.println("5 - Listar Todas as Consultas");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = leitorNum.nextInt();
            switch (opcao) {
                case 0:
                    System.out.println("Encerrando o programa...");
                    break;
                case 1:
                    cadastrarConsulta(leitorNum, leitorTexto, dao);
                    break;
                case 2:
                    buscarConsulta(leitorNum, dao);
                    break;
                case 3:
                    alterarConsulta(leitorNum, leitorTexto, dao);
                    break;
                case 4:
                    excluirConsulta(leitorNum, dao);
                    break;
                case 5:
                    listarConsultas(dao);
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        } while(opcao != 0);

        leitorNum.close();
        leitorTexto.close();
    }

    private static void cadastrarConsulta(Scanner leitorNum, Scanner leitorTexto, ConsultaDao dao) {
        Consulta c = new Consulta();
        System.out.println("\n=== CADASTRAR CONSULTA ===");
        System.out.print("Digite o ID da consulta: ");
        c.setId_consulta(leitorNum.nextInt());
        System.out.println("Selecione o tipo de consulta:");
        System.out.println("1 - Presencial\t2 - Teleconsulta");
        int tipoOp = leitorNum.nextInt();
        switch (tipoOp) {
            case 1:
                c.setTipo_consulta(TipoAtendiEnum.Presencial);
                break;
            case 2:
                c.setTipo_consulta(TipoAtendiEnum.Teleconsulta);
                break;
            default:
                System.out.println("Opção inválida, definido como Presencial.");
                c.setTipo_consulta(TipoAtendiEnum.Presencial);
        }

        System.out.print("Digite a data da consulta (AAAA-MM-DD): ");
        String data = leitorTexto.nextLine();
        c.setData_consulta(LocalDate.parse(data));
        System.out.print("Digite o motivo da consulta: ");
        c.setMotivo_consulta(leitorTexto.nextLine());
        System.out.print("Digite o ID do histórico do paciente: ");
        c.setFk_historico(leitorNum.nextInt());
        System.out.print("Digite o ID do profissional responsável: ");
        c.setFk_profissional(leitorNum.nextInt());
        dao.inserir(c);
        System.out.println("Consulta cadastrada com sucesso!");
    }

    private static void buscarConsulta(Scanner leitorNum, ConsultaDao dao) {
        System.out.println("\n=== BUSCAR CONSULTA ===");
        System.out.print("Digite o ID da consulta: ");
        int id = leitorNum.nextInt();
        Consulta c = dao.buscarPorId(id);
        if (c != null) {
            System.out.println("\nConsulta encontrada:");
            System.out.println(c);
        } else {
            System.out.println("Nenhuma consulta encontrada com esse ID.");
        }

    }

    private static void alterarConsulta(Scanner leitorNum, Scanner leitorTexto, ConsultaDao dao) {
        Consulta c = new Consulta();
        System.out.println("\n=== ALTERAR CONSULTA ===");
        System.out.print("Digite o ID da consulta que deseja alterar: ");
        c.setId_consulta(leitorNum.nextInt());
        System.out.println("Selecione o novo tipo de consulta:");
        System.out.println("1 - Presencial\t2 - Teleconsulta");
        int tipoOp = leitorNum.nextInt();
        switch (tipoOp) {
            case 1:
                c.setTipo_consulta(TipoAtendiEnum.Presencial);
                break;
            case 2:
                c.setTipo_consulta(TipoAtendiEnum.Teleconsulta);
                break;
            default:
                System.out.println("Opção inválida, definido como Presencial.");
                c.setTipo_consulta(TipoAtendiEnum.Presencial);
        }

        System.out.print("Digite a nova data da consulta (AAAA-MM-DD): ");
        leitorTexto.nextLine();
        c.setData_consulta(LocalDate.parse(leitorTexto.nextLine()));
        System.out.print("Digite o novo motivo da consulta: ");
        c.setMotivo_consulta(leitorTexto.nextLine());
        System.out.print("Digite o novo ID do histórico: ");
        c.setFk_historico(leitorNum.nextInt());
        System.out.print("Digite o novo ID do profissional: ");
        c.setFk_profissional(leitorNum.nextInt());
        dao.alterar(c);
        System.out.println("Consulta alterada com sucesso!");
    }

    private static void excluirConsulta(Scanner leitorNum, ConsultaDao dao) {
        System.out.println("\n=== EXCLUIR CONSULTA ===");
        System.out.print("Digite o ID da consulta que deseja excluir: ");
        int id = leitorNum.nextInt();
        dao.excluir(id);
        System.out.println("Consulta excluída com sucesso!");
    }

    private static void listarConsultas(ConsultaDao dao) {
        System.out.println("\n=== LISTA DE CONSULTAS ===");
        List<Consulta> consultas = dao.listar();
        if (consultas.isEmpty()) {
            System.out.println("Nenhuma consulta cadastrada.");
        } else {
            for(Consulta c : consultas) {
                System.out.println(c);
            }
        }

    }
}
