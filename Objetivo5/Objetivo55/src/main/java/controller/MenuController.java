package controller;

import java.util.Scanner;

public class MenuController {

    private static final Scanner input = new Scanner(System.in);

    public static void main(String[] args) {

        int opcao = 0;
        do {
            System.out.print("\n------→  Menu Principal  ←------\n");
            System.out.print(
                    """
                        1. Menu Paciente
                        2. Menu Secretaria
                        3. Menu Dentista
                        4. Menu Consulta
                        Opção (Zero p/sair):\s""");
            opcao = input.nextInt();
            input.nextLine();
            switch (opcao) {
                case 1 -> PacienteController.main(null);
                case 2 -> SecretariaController.main(null);
                case 3 -> DentistaController.main(null);
               case 4 -> ConsultaController.main(null);
                default -> {
                    if (opcao != 0) System.out.println("Opção inválida.");
                }
            }
        } while(opcao != 0) ;
        System.out.println("\n\nFim");
        input.close();
    }
}