package br.com.fiap.tests;

import br.com.fiap.dao.PacienteDao;
import br.com.fiap.enums.TipoAtendiEnum;
import br.com.fiap.models.Paciente;
import java.util.Scanner;

public class TestePaciente {
    public TestePaciente() {
    }

    public static void main(String[] args) {
        Scanner leitorNum = new Scanner(System.in);
        Scanner leitorTexto = new Scanner(System.in);
        PacienteDao dao = new PacienteDao();

        int opcao;
        do {
            System.out.println("\n==== MENU PACIENTE ====");
            System.out.println("1 - Cadastrar Paciente");
            System.out.println("2 - Buscar Paciente por ID");
            System.out.println("3 - Alterar Paciente");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = leitorNum.nextInt();
            switch (opcao) {
                case 0:
                    System.out.println("Encerrando o programa...");
                    break;
                case 1:
                    cadastrarPaciente(leitorNum, leitorTexto, dao);
                    break;
                case 2:
                    buscarPaciente(leitorNum, dao);
                    break;
                case 3:
                    alterarPaciente(leitorNum, leitorTexto, dao);
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        } while(opcao != 0);

        leitorNum.close();
        leitorTexto.close();
    }

    private static void cadastrarPaciente(Scanner leitorNum, Scanner leitorTexto, PacienteDao dao) {
        Paciente paciente = new Paciente();
        System.out.println("\n=== CADASTRAR PACIENTE ===");
        System.out.print("Digite o código do paciente: ");
        paciente.setId_pac(leitorNum.nextInt());
        System.out.print("Digite o nome do paciente: ");
        paciente.setNome_pac(leitorTexto.nextLine());
        System.out.print("Digite a idade do paciente: ");
        paciente.setIdade_pac(leitorNum.nextInt());
        System.out.print("Digite o nível técnico do paciente (1 - Baixo, 2 - Médio, 3 - Alto): ");
        paciente.setNivel_tec(leitorNum.nextInt());
        System.out.println("Selecione o tipo de atendimento: ");
        System.out.println("1 - Presencial\t2 - Teleconsulta");
        int tipoOp = leitorNum.nextInt();
        switch (tipoOp) {
            case 1 -> paciente.setTipoAtendiEnum(TipoAtendiEnum.Presencial);
            case 2 -> paciente.setTipoAtendiEnum(TipoAtendiEnum.Teleconsulta);
            default -> System.out.println("Opção inválida. Tipo não alterado.");
        }

        dao.inserir(paciente);
        System.out.println("Paciente cadastrado com sucesso!");
    }

    private static void buscarPaciente(Scanner leitorNum, PacienteDao dao) {
        System.out.println("\n=== BUSCAR PACIENTE ===");
        System.out.print("Digite o ID do paciente: ");
        int id = leitorNum.nextInt();
        Paciente paciente = dao.buscarPorId(id);
        if (paciente != null) {
            System.out.println("Paciente encontrado:");
            System.out.println(paciente);
        } else {
            System.out.println("Nenhum paciente encontrado com esse ID.");
        }

    }

    private static void alterarPaciente(Scanner leitorNum, Scanner leitorTexto, PacienteDao dao) {
        Paciente paciente = new Paciente();
        System.out.println("\n=== ALTERAR PACIENTE ===");
        System.out.print("Digite o ID do paciente que deseja alterar: ");
        paciente.setId_pac(leitorNum.nextInt());
        System.out.print("Digite o novo nome do paciente: ");
        paciente.setNome_pac(leitorTexto.nextLine());
        System.out.print("Digite a nova idade do paciente: ");
        paciente.setIdade_pac(leitorNum.nextInt());
        System.out.print("Digite o novo nível técnico do paciente (0 a 10): ");
        paciente.setNivel_tec(leitorNum.nextInt());
        System.out.println("Selecione o novo tipo de atendimento:");
        System.out.println("1 - Presencial\t2 - Teleconsulta");
        int op = leitorNum.nextInt();
        switch (op) {
            case 1 -> paciente.setTipoAtendiEnum(TipoAtendiEnum.Presencial);
            case 2 -> paciente.setTipoAtendiEnum(TipoAtendiEnum.Teleconsulta);
            default -> System.out.println("Opção inválida. Tipo não alterado.");
        }

        dao.alterar(paciente);
        System.out.println("Paciente alterado com sucesso!");
    }
}
