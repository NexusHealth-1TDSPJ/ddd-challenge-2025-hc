package br.com.fiap.tests;

import br.com.fiap.dao.ProfissionalDao;
import br.com.fiap.enums.TipoAtendiEnum;
import br.com.fiap.models.Profissional;
import java.util.List;
import java.util.Scanner;

public class TesteProfissional {
    public TesteProfissional() {
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ProfissionalDao dao = new ProfissionalDao();

        int op;
        do {
            System.out.println("\nMENU PROFISSIONAL");
            System.out.println("1 - Inserir");
            System.out.println("2 - Alterar");
            System.out.println("3 - Buscar por ID");
            System.out.println("4 - Listar todos");
            System.out.println("5 - Excluir");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            op = sc.nextInt();
            switch (op) {
                case 0:
                    System.out.println("Programa encerrado.");
                    break;
                case 1:
                    inserirProfissional(dao, sc);
                    break;
                case 2:
                    alterarProfissional(dao, sc);
                    break;
                case 3:
                    buscarPorId(dao, sc);
                    break;
                case 4:
                    listarProfissionais(dao);
                    break;
                case 5:
                    excluirProfissional(dao, sc);
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        } while(op != 0);
    }

    private static void inserirProfissional(ProfissionalDao dao, Scanner sc) {
        Profissional p = new Profissional();
        sc.nextLine();
        System.out.print("ID: ");
        p.setId_profissional(sc.nextInt());
        sc.nextLine();
        System.out.print("Nome: ");
        p.setNome_profissional(sc.nextLine());
        System.out.print("Especialidade: ");
        p.setEspecialidade_profissional(sc.nextLine());
        System.out.println("Tipo de atendimento: 1 - Presencial | 2 - Teleconsulta");
        int tipo = sc.nextInt();
        p.setTipo_atend(tipo == 1 ? TipoAtendiEnum.Presencial : TipoAtendiEnum.Teleconsulta);
        System.out.print("CRM: ");
        p.setCrm_profissional(sc.nextInt());
        dao.inserir(p);
    }

    private static void alterarProfissional(ProfissionalDao dao, Scanner sc) {
        Profissional p = new Profissional();
        sc.nextLine();
        System.out.print("ID do profissional a alterar: ");
        p.setId_profissional(sc.nextInt());
        sc.nextLine();
        System.out.print("Novo nome: ");
        p.setNome_profissional(sc.nextLine());
        System.out.print("Nova especialidade: ");
        p.setEspecialidade_profissional(sc.nextLine());
        System.out.println("Novo tipo de atendimento: 1 - Presencial | 2 - Teleconsulta");
        int tipo = sc.nextInt();
        p.setTipo_atend(tipo == 1 ? TipoAtendiEnum.Presencial : TipoAtendiEnum.Teleconsulta);
        System.out.print("Novo CRM: ");
        p.setCrm_profissional(sc.nextInt());
        dao.alterar(p);
    }

    private static void buscarPorId(ProfissionalDao dao, Scanner sc) {
        System.out.print("Digite o ID do profissional: ");
        int id = sc.nextInt();
        Profissional p = dao.buscarPorId(id);
        if (p != null) {
            System.out.println("Profissional encontrado:");
            System.out.println(p);
        } else {
            System.out.println("Profissional não encontrado.");
        }

    }

    private static void listarProfissionais(ProfissionalDao dao) {
        List<Profissional> lista = dao.listar();
        System.out.println("\n=== Lista de Profissionais ===");
        if (lista.isEmpty()) {
            System.out.println("Nenhum profissional cadastrado.");
        } else {
            for(Profissional p : lista) {
                System.out.println(p);
            }
        }

    }

    private static void excluirProfissional(ProfissionalDao dao, Scanner sc) {
        System.out.print("Digite o ID do profissional a excluir: ");
        int id = sc.nextInt();
        dao.excluirProfissional(id);
    }
}