package controller;

import java.util.List;
import java.util.Scanner;

import dao.PacienteDAO;
import model.Paciente;

public class PacienteController {

    private static final Scanner input = new Scanner(System.in);

    public static void main(String[] args) {

        int opcao = 0;
        do {
            System.out.print("\n------→  MENU  ←------\n");
            System.out.print(
                    """
                        1. → Cadastrar paciente
                        2. → Atualizar dados do paciente
                        3. → Listar pacientes
                        4. → Consultar paciente pelo código
                        5. → Consultar paciente por nome
                        6. → Consultar paciente pelo RG
                        Opção (Zero p/sair):\s""");
            opcao = input.nextInt();
            input.nextLine();
            switch (opcao) {
                case 1 -> cadastrarPaciente();
                case 2 -> atualizarDadosPaciente();
                case 3 -> selecionarPacientes();
                case 4 -> consultarPorCodigo();
                case 5 -> selecionarNomePaciente();
                case 6 -> selecionarRgPaciente();
                default -> {
                    if (opcao != 0)
                        System.out.println("Opção inválida.");
                }
            }
        } while (opcao != 0);

    }

    //opção 1 - Cadastra Paciente
    private static void cadastrarPaciente() {
        Paciente paciente = new Paciente();
        System.out.println("\n------→  Nome  ←------");
        System.out.print("Digite o nome do paciente: ");
        paciente.setNome(input.nextLine());
        System.out.println("\n------→  Registro Geral  ←------");
        System.out.print("Digite o RG do paciente ");
        paciente.setRg(input.nextLine());
        System.out.println("\n------→  Senha  ←------");
        System.out.print("Digite a senha de consulta do paciente: ");
        paciente.setSenha(input.nextLine());

        if(PacienteDAO.cadastrarPaciente(paciente)) {
            System.out.println("\nPaciente cadastrado.");
        }else {
            System.out.println("\nHouve um erro ao cadastrar.");
        }
    }

    //opção 2 - Atualiza Dados do Paciente
    private static void atualizarDadosPaciente() {
        Paciente paciente = null;
        for (; paciente == null; ) {
            System.out.println("\nDigite o código do paciente que deseja atualizar: ");
            int cod_paciente;
            while (true) {
                if (input.hasNextInt()) {
                    cod_paciente = input.nextInt();
                    input.nextLine();
                    break;
                } else {
                    System.out.println("\nDigite apenas números inteiros!");
                    input.nextLine();
                }
            }
            paciente = PacienteDAO.selecionarPacienteCodigo(cod_paciente);
            if (paciente == null)
                System.out.println("\nCódigo Inválido!");
            else
                System.out.println("\nPaciente Encontrado!");
        }
        int opcao;
        System.out.println("\nNome: " + paciente.getNome() + " | Alterar nome? (1.Sim ou 2.Não)");
        while (true) {
            if (input.hasNextInt()) {
                opcao = input.nextInt();
                input.nextLine();
                if (opcao == 1 || opcao == 2)
                    break;
                else
                    System.out.println("\nDigite apenas 1 para Sim ou 2 para Não!");
            } else {
                System.out.println("\nDigite apenas números inteiros!");
                input.nextLine();
            }
        }
        if (opcao == 1) {
            System.out.println("\nDigite o novo nome do Paciente: ");
            paciente.setNome(input.nextLine());
        }
        System.out.println("\nRG: " + paciente.getRg() + " Alterar RG? (1.Sim ou 2.Não)");
        while (true) {
            if (input.hasNextInt()) {
                opcao = input.nextInt();
                input.nextLine();
                if (opcao == 1 || opcao == 2)
                    break;
                else
                    System.out.println("\nDigite apenas 1 para Sim ou 2 para Não!");
            } else {
                System.out.println("\nDigite apenas números inteiros!");
                input.nextLine();
            }
        }
        if (opcao == 1) {
            System.out.println("\nDigite o novo RG: ");
            paciente.setRg(input.nextLine());
        }
        System.out.println("\nSenha: " + paciente.getSenha() + " Alterar Senha? (1.Sim ou 2.Não)");
        while (true) {
            if (input.hasNextInt()) {
                opcao = input.nextInt();
                input.nextLine();
                if (opcao == 1 || opcao == 2)
                    break;
                else
                    System.out.println("\nDigite apenas 1 para Sim ou 2 para Não!");
            } else {
                System.out.println("\nDigite apenas números inteiros!");
                input.nextLine();
            }
        }
        if (opcao == 1) {
            System.out.println("\nDigite a nova senha: ");
            paciente.setSenha(input.nextLine());
        }
        if (PacienteDAO.atualizarDadosPaciente(paciente)) {
            System.out.println("\nDados alterados");
        } else {
            System.out.println("\nErro ao alterar os dados");
        }
    }

    //opção 3 - Consulta Paciente
    private static void selecionarPacientes() {
        System.out.println("\nPacientes cadastrados:\n" + PacienteDAO.selecionarPacientes());
    }

    //opção 4 - Código paciente pelo id
    private static void consultarPorCodigo() {
        int cod_paciente;
        Paciente paciente;
        while (true) {
            System.out.println("\nCódigo Paciente: ");
            if (input.hasNextInt()) {
                cod_paciente = input.nextInt();
                input.nextLine();
                paciente = PacienteDAO.selecionarPacienteCodigo(cod_paciente);
                if (paciente == null || paciente.equals("")) {
                    System.out.println("Paciente não encontrado. \nDigite um código válido.");
                } else {
                    break;
                }
            } else {
                System.out.println("Digite apenas números inteiros!");
                input.nextLine();
            }
        }
        System.out.println(paciente);
    }


    //opção 5 - Consulta paciente pelo nome
    private static void selecionarNomePaciente() {
        while (true) {
            System.out.println("\nDigite o nome: ");
            if (input.hasNext("[a-zA-Z]+")) {
                String nome = input.next();
                input.nextLine();
                List<Paciente> pacientes = PacienteDAO.selecionarNomePaciente(nome);
                if (pacientes.isEmpty()) {
                    System.out.println("Não existem pacientes com essa letra e/ou nome. DIGITE NOVAMENTE.");
                } else {
                    System.out.println(pacientes);
                    break;
                }
            } else {
                System.out.println("Digite apenas letras!");
                input.nextLine();
            }
        }
    }

    // opção 6 - Consulta paciente pelo RG
    private static void selecionarRgPaciente() {
        System.out.println("\nRG do paciente: ");
        String rg = input.next();
        input.nextLine();
        System.out.println(PacienteDAO.selecionarRgPaciente(rg));
    }
}