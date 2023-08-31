package controller;

import java.util.List;
import java.util.Scanner;
import dao.DentistaDAO;
import model.Dentista;


public class DentistaController {

    private static final Scanner input = new Scanner(System.in);

    public static void main(String[] args) {

        int opcao = 0;
        do {
            System.out.print("\n------→  MENU  ←------\n");
            System.out.print(
                    """
                        1. → Cadastrar Dentista
                        2. → Atualizar dados do(a) Dentista
                        3. → Listar Dentistas
                        4. → Consultar dentista pelo código
                        5. → Consultar dentista por nome
                        6. → Consultar dentista pelo CRO
                        Opção (Zero p/sair):\s""");
            opcao = input.nextInt();
            input.nextLine();
            switch (opcao) {
                case 1 -> cadastrarDentista();
                case 2 -> atualizarDadosDentista();
                case 3 -> selecionarDentistas();
                case 4 -> consultarPorCodigo();
                case 5 -> selecionarNomeDentista();
                case 6 -> selecionarCroDentista();
                default -> {
                    if (opcao != 0)
                        System.out.println("Opção inválida.");
                }
            }
        } while (opcao != 0);

    }

    //opção 1 - Cadastra Dentista
    private static void cadastrarDentista() {
        Dentista dentista = new Dentista();
        System.out.println("\n------→  Nome  ←------");
        System.out.print("Digite o nome do(a) Dentista: ");
        dentista.setNome(input.nextLine());
        System.out.println("\n------→  Conselho Regional de Odontologia  ←------");
        System.out.print("Digite o CRO do(a) Dentista ");
        dentista.setCro(input.nextLine());

        if(DentistaDAO.cadastrarDentista(dentista)) {
            System.out.println("\nDentista cadastrado.");
        }else {
            System.out.println("\nHouve um erro ao cadastrar.");
        }
    }

    //opção 2 - Atualiza Dados do Dentista
    private static void atualizarDadosDentista() {
        Dentista dentista = null;
        for (; dentista == null; ) {
            System.out.println("\nDigite o código do Dentista que deseja atualizar: ");
            int cod_dentista;
            while (true) {
                if (input.hasNextInt()) {
                    cod_dentista = input.nextInt();
                    input.nextLine();
                    break;
                } else {
                    System.out.println("\nDigite apenas números inteiros!");
                    input.nextLine();
                }
            }
            dentista = DentistaDAO.selecionarDentistaCodigo(cod_dentista);
            if (dentista == null)
                System.out.println("\nCódigo Inválido!");
            else
                System.out.println("\nDentista Encontrado!");
        }
        int opcao;
        System.out.println("\nNome: " + dentista.getNome() + " | Alterar nome? (1.Sim ou 2.Não)");
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
            System.out.println("\nDigite o novo nome do Dentista: ");
            dentista.setNome(input.nextLine());
        }
        System.out.println("\nCRO: " + dentista.getCro() + " Alterar CRO? (1.Sim ou 2.Não)");
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
            dentista.setCro(input.nextLine());
        }
        if (DentistaDAO.atualizarDadosDentista(dentista)) {
            System.out.println("\nDados alterados");
        } else {
            System.out.println("\nErro ao alterar os dados");
        }
    }

    //opção 3 - Consulta Dentista
    private static void selecionarDentistas() {
        System.out.println("\nDentista cadastrados: \n" + DentistaDAO.selecionarDentista());
    }

    //opção 4 - Código Dentista pelo id
    private static void consultarPorCodigo() {
        int cod_dentista;
        Dentista dentista;
        while (true) {
            System.out.println("\nCódigo dentista: ");
            if (input.hasNextInt()) {
                cod_dentista = input.nextInt();
                input.nextLine();
                dentista = DentistaDAO.selecionarDentistaCodigo(cod_dentista);
                if (dentista == null || dentista.equals("")) {
                    System.out.println("Dentista não encontrado. \nDigite um código válido.");
                } else {
                    break;
                }
            } else {
                System.out.println("Digite apenas números inteiros!");
                input.nextLine();
            }
        }
        System.out.println(dentista);
    }


    //opção 5 - Consulta Dentista pelo nome
    private static void selecionarNomeDentista() {
        while (true) {
            System.out.println("\nDigite o nome: ");
            if (input.hasNext("[a-zA-Z]+")) {
                String nome = input.next();
                input.nextLine();
                List<Dentista> dentistas = DentistaDAO.selecionarNomeDentista(nome);
                if (dentistas.isEmpty()) {
                    System.out.println("Não existem Dentistas com essa letra e/ou nome. DIGITE NOVAMENTE.");
                } else {
                    System.out.println(dentistas);
                    break;
                }
            } else {
                System.out.println("Digite apenas letras!");
                input.nextLine();
            }
        }
    }


    // opção 6 - Consulta dentista pelo CRO
   private static void selecionarCroDentista() {
        System.out.println("\nCRO do Dentista: ");
        String cro = input.next();
        input.nextLine();
        System.out.println(DentistaDAO.selecionarCroDentista(cro));
    }
}