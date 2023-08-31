package controller;

import java.util.List;
import java.util.Scanner;

import dao.PacienteDAO;
import dao.SecretariaDAO;
import model.Secretaria;


public class SecretariaController {

    private static final Scanner input = new Scanner(System.in);

    public static void main(String[] args) {

        int opcao = 0;
        do {
            System.out.print("\n------→  MENU  ←------\n");
            System.out.print(
                    """
                            1. → Cadastrar secretaria
                            2. → Atualizar dados da secretaria
                            3. → Listar secretarias
                            4. → Consultar secretaria pelo código
                            5. → Consultar secretaria por nome
                            6. → Consultar secretaria pelo RG
                            Opção (Zero p/sair):\s""");
            opcao = input.nextInt();
            input.nextLine();
            switch (opcao) {
                case 1 -> cadastrarSecretaria();
                case 2 -> atualizarDadosSecretaria();
                case 3 -> selecionarSecretaria();
                case 4 -> consultarPorCodigo();
                case 5 -> selecionarNomeSecretaria();
                case 6 -> selecionarRgSecretaria();
                default -> {
                    if (opcao != 0)
                        System.out.println("Opção inválida.");
                }
            }
        } while (opcao != 0);

    }

    //opção 1 - Cadastra Secretaria
    private static void cadastrarSecretaria() {
        Secretaria secretaria = new Secretaria();
        System.out.println("\n------→  Nome  ←------");
        System.out.print("Digite o nome da secretaria: ");
        secretaria.setNome(input.nextLine());
        System.out.println("\n------→  Registro Geral  ←------");
        System.out.print("Digite o RG do secretaria ");
        secretaria.setRg(input.nextLine());

        if (SecretariaDAO.cadastrarSecretaria(secretaria)) {
            System.out.println("\nSecretaria cadastrada.");
        } else {
            System.out.println("\nHouve um erro ao cadastrar");
        }
    }

    //opção 2 - Atualiza Dados da Secretaria
    private static void atualizarDadosSecretaria() {
        Secretaria secretaria = null;
        for (; secretaria == null; ) {
            System.out.println("\nDigite o código da secretaria que deseja atualizar: ");
            int cod_secretaria;
            while (true) {
                if (input.hasNextInt()) {
                    cod_secretaria = input.nextInt();
                    input.nextLine();
                    break;
                } else {
                    System.out.println("\nDigite apenas números inteiros!");
                    input.nextLine();
                }
            }
            secretaria = SecretariaDAO.selecionarSecretariaCodigo(cod_secretaria);
            if (secretaria == null)
                System.out.println("\nCódigo Inválido!");
            else
                System.out.println("\nSecretaria Encontrado!");
        }
        int opcao;
        System.out.println("\nNome: " + secretaria.getNome() + " | Alterar nome? (1.Sim ou 2.Não)");
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
            System.out.println("\nDigite o novo nome do Secretaria: ");
            secretaria.setNome(input.nextLine());
        }
        System.out.println("\nRG: " + secretaria.getRg() + " Alterar RG? (1.Sim ou 2.Não)");
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
            secretaria.setRg(input.nextLine());
        }
        if (SecretariaDAO.atualizarDadosSecretaria(secretaria)) {
            System.out.println("\nDados alterados");
        } else {
            System.out.println("\nErro ao alterar os dados");
        }
    }

    //opção 3 - Consulta Secretaria
    private static void selecionarSecretaria(){
        System.out.println("\nSecretarias cadastradas:\n" + SecretariaDAO.selecionarSecretarias());
    }

    //opção 4 - Código Secretaria por codigo
    private static void consultarPorCodigo(){
        int cod_secretaria;
        Secretaria secretaria;
        while (true) {
            System.out.println("\nCódigo Secretaria: ");
            if (input.hasNextInt()) {
                cod_secretaria = input.nextInt();
                input.nextLine();
                secretaria = SecretariaDAO.selecionarSecretariaCodigo(cod_secretaria);
                if (secretaria == null || secretaria.equals("")) {
                    System.out.println("Secretaria não encontrada. \nDigite um código válido.");
                } else {
                    break;
                }
            } else {
                System.out.println("Digite apenas números inteiros!");
                input.nextLine();
            }
        }
        System.out.println(secretaria);
    }

    //opção 5 - Consulta secretaria pelo nome
    private static void selecionarNomeSecretaria() {
        while (true) {
            System.out.println("\nDigite o nome: ");
            if (input.hasNext("[a-zA-Z]+")) {
                String nome = input.next();
                input.nextLine();
                List<Secretaria> secretarias = SecretariaDAO.selecionarNomeSecretaria(nome);
                if (secretarias.isEmpty()) {
                    System.out.println("Não existem secretarias com essa letra e/ou nome. DIGITE NOVAMENTE.");
                } else {
                    System.out.println(secretarias);
                    break;
                }
            } else {
                System.out.println("Digite apenas letras!");
                input.nextLine();
            }
        }
    }


    // opção 6 - Consulta secretaria pelo RG
    private static void selecionarRgSecretaria() {
        System.out.println("\nRG do secretaria: ");
        String rg = input.next();
        input.nextLine();
        System.out.println(SecretariaDAO.selecionarRgSecretaria(rg));
    }
}