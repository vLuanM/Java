package controller;

import dao.ConsultaDAO;
import dao.DentistaDAO;
import dao.PacienteDAO;
import model.Consulta;
import model.Dentista;
import model.Paciente;
import model.Secretaria;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsultaController {

        private static final Scanner input = new Scanner(System.in);

        public static void main(String[] args) {

            int opcao = 0;
            do {
                System.out.print("\n------→  MENU  ←------\n");
                System.out.print(
                        """
                            1. → Cadastrar consulta
                            2. → Remarcar consulta
                            3. → Cancelar consulta
                            4. → Pesquisar consulta do paciente
                            5. → Pesquisar consulta do dentista
                            Opção (Zero p/sair):\s""");
                opcao = input.nextInt();
                input.nextLine();
                switch (opcao) {
                    case 1 -> cadastrarConsulta();
                    case 2 -> remarcarConsulta();
                    case 3 -> cancelarConsulta();
                    case 4 -> SelecionarConsultasPaciente();
                    case 5 -> SelecionarConsultasDentista();
                    default -> {
                        if (opcao != 0)
                            System.out.println("Opção inválida.");
                    }
                }

            } while (opcao != 0);

        }

    private static void cadastrarConsulta(){
        Consulta consulta = new Consulta();
        Dentista dentista = new Dentista();
        Paciente paciente = new Paciente();
        Secretaria secretaria = new Secretaria();
        int dia, mes, ano, hora, minuto; String rg;

        System.out.println("\nDigite o RG do Paciente: "); //RG: 693
        rg = input.nextLine();
        paciente = PacienteDAO.selecionarRgPaciente(rg);
        System.out.println("\nDados do Paciente: \n"+ paciente);
        consulta.setPaciente(paciente);

        System.out.println("\nDentistas Cadastrados: \n" + DentistaDAO.selecionarDentista());

        System.out.println("\nSelecionar Código do Dentista: ");
        dentista.setCod_dentista(input.nextInt());
        consulta.setDentista(dentista);

        System.out.println("\nDigite o Código de Secretária: "); //Código: 1
        secretaria.setCod_secretaria(input.nextInt());
        consulta.setSecretaria(secretaria);

        System.out.println("\nDigite a Data da Consulta: \nDia: ");
        dia = input.nextInt();
        System.out.println("\nMês: ");
        mes = input.nextInt();
        System.out.println("\nAno: ");
        ano = input.nextInt();
        LocalDate data = LocalDate.of(ano, mes, dia);
        consulta.setData_consulta(data);

        System.out.println("\nDigite o Hoŕario da Consulta: \nHora: ");
        hora = input.nextInt();
        System.out.println("\nMinuto: ");
        minuto = input.nextInt();
        LocalTime horario = LocalTime.of(hora, minuto);
        consulta.setHora(horario);

        if(ConsultaDAO.cadastrarConsulta(consulta)){
            System.out.println("\nConsulta Cadastrada!\n");
        }else{
            System.out.println("\nErro ao Cadastrar.\n");
        }
    }

    public static void remarcarConsulta(){
        Paciente paciente = new Paciente();
        List<Consulta> consultas = new ArrayList<>();
        Consulta consulta = new Consulta(); String rg;
        int codigo, auxiliar, dia, mes, ano, hora, minuto;

        System.out.println("\nDigite o RG do Paciente: "); //RG: 693
        rg = input.nextLine();
        paciente = PacienteDAO.selecionarRgPaciente(rg);
        if(paciente == null){
            System.out.println("\nRG Não Encontrado!");
        }else {
            System.out.println("\nDados Paciente: "+ paciente);
            consultas = ConsultaDAO.SelecionarConsultasPaciente(paciente.getCod_paciente());
            if(consultas == null){
                System.out.println("\nO Paciente NÃO Possui Consultas.");
            }else{
                System.out.println("\nConsultas do Paciente: "+ consultas);
                System.out.println("\nDigite o Código da Consulta p/ Remarcar: ");
                codigo = input.nextInt();
                consulta = ConsultaDAO.SelecionarConsultaCodigo(codigo);
                System.out.println("\nDeseja Remarcar a Consulta? 1-SIM | 2-NÃO ");
                auxiliar = input.nextInt();
                if(auxiliar == 1){
                    System.out.println(consulta.getData_consulta());
                    System.out.println("\nRemarcar a Data? 1-SIM | 2-NÃO");
                    auxiliar = input.nextInt();
                    if(auxiliar == 1){
                        System.out.println("\nDigite a Data da Consulta: \nDia: ");
                        dia = input.nextInt();
                        System.out.println("\nMês: ");
                        mes = input.nextInt();
                        System.out.println("\nAno: ");
                        ano = input.nextInt();
                        LocalDate data = LocalDate.of(ano, mes, dia);
                        consulta.setData_consulta(data);
                    }
                    System.out.println("\nRemarcar o Horário? 1-SIM | 2-NÃO");
                    auxiliar = input.nextInt();
                    if(auxiliar == 1){
                        System.out.println("\nDigite o Hoŕario da Consulta: \nHora: ");
                        hora = input.nextInt();
                        System.out.println("\nMinuto: ");
                        minuto = input.nextInt();
                        LocalTime horario = LocalTime.of(hora, minuto);
                        consulta.setHora(horario);
                    }
                    if(ConsultaDAO.remarcarConsulta(consulta)) {
                        System.out.println("\nConsulta Remarcada c/ Sucesso!");
                    }else{
                        System.out.println("\nErro ao Remarcar.");
                    }
                }
            }
        }
    }

    public static void cancelarConsulta(){
        Paciente paciente = new Paciente();
        String rg; int codigo;

        System.out.println("\nDigite o RG do Paciente: "); //RG: 693
        rg = input.nextLine();
        paciente = PacienteDAO.selecionarRgPaciente(rg);
        if(paciente == null){
            System.out.println("\nRG Não Encontrado!");
        }else {
            System.out.println("\nDados Paciente: "+ paciente);
        }
        System.out.println("\nConsultas do Paciente: "+ ConsultaDAO.SelecionarConsultasPaciente(paciente.getCod_paciente()));
        System.out.println("\nCancelar Consulta: \nCódigo: ");
        codigo = input.nextInt();
        if(ConsultaDAO.cancelarConsulta(codigo)){
            System.out.println("\nCancelamento Efetuado!");
        }else{
            System.out.println("\nERRO: Não Foi Possivel Cancelar.");
        }
    }

    public static void SelecionarConsultasPaciente(){
        Paciente paciente = new Paciente();
        List<Consulta> consultas = new ArrayList<>();
        String rg;

        System.out.println("\nDigite o RG do Paciente: "); //RG: 693
        rg = input.nextLine();
        paciente = PacienteDAO.selecionarRgPaciente(rg);
        if(paciente == null){
            System.out.println("\nRG Não Encontrado!");
        }else {
            System.out.println("\nDados Paciente: "+ paciente);
        }
        consultas = ConsultaDAO.SelecionarConsultasPaciente(paciente.getCod_paciente());
        if(consultas == null){
            System.out.println("\nO Paciente NÃO Possui Consultas.");
        }else{
            System.out.println("\nConsultas do Paciente: "+ consultas);
        }
    }

    public static void SelecionarConsultasDentista(){
        Dentista dentista = new Dentista();
        List<Consulta> consultas = new ArrayList<>();
        String cro;

        System.out.println("\nDigite o CRO do Dentista: "); //005
        cro = input.nextLine();
        dentista = DentistaDAO.selecionarCroDentista(cro);
        if(dentista == null){
            System.out.println("\nCRO Não Encontrado!");
        }else {
            System.out.println("\nDados Dentista: "+ dentista);
        }
        consultas = ConsultaDAO.SelecionarConsultasPaciente(dentista.getCod_dentista());
        if(consultas == null){
            System.out.println("\nO Dentista NÃO Possui Consultas.");
        }else{
            System.out.println("\nConsultas do Dentista: "+ consultas);
        }
    }
}
