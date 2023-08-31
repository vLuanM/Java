package controller;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import model.Associado;
import model.Cliente;
import model.Conta;
import model.ContaCorrente;
import model.ContaPoupanca;

public class ContaController {
    private static double auxiliar = 0.0;

    public static void main(String[] args) {
        System.out.println("~1 Opções conta~");

        ContaPoupanca cpoup1 = new ContaPoupanca();
        ContaPoupanca cpoup2 = new ContaPoupanca();
        ContaPoupanca cpoup3 = new ContaPoupanca();
        ContaCorrente ccor1 = new ContaCorrente(100);
        ContaCorrente ccor2 = new ContaCorrente(400);
        ContaCorrente ccor3 = new ContaCorrente(600);

        Cliente assoc1 = new Cliente("Roberto", 200);
        Cliente assoc2 = new Cliente("Ricardo", 400);
        Cliente assoc3 = new Cliente("Julio", 600);

        System.out.println(cpoup1);
        System.out.println(cpoup2);
        System.out.println(cpoup3);
        System.out.println(ccor1);
        System.out.println(ccor2);
        System.out.println(ccor3);
        System.out.println(assoc1);
        System.out.println(assoc2);
        System.out.println(assoc3);

        System.out.println("~2 Opções conta~");
        List<Conta> contas = new ArrayList<>();
        contas.add(cpoup1);
        contas.add(cpoup2);
        contas.add(cpoup3);
        contas.add(ccor1);
        contas.add(ccor2);
        contas.add(ccor3);
        List<Associado> associados = new ArrayList<>();
        associados.add(ccor1);
        associados.add(ccor2);
        associados.add(ccor3);
        associados.add(assoc1);
        associados.add(assoc2);
        associados.add(assoc3);
        System.out.println(contas);
        associados.stream()
                .filter(p -> p instanceof Cliente)
                .forEach(System.out::println);

        System.out.println("\n~3 Opções conta~");
        cpoup1.deposita(1000);
        System.out.println("Deposita:" + cpoup1);
        cpoup1.saca(150);
        System.out.println("Saca:" + cpoup1);
        cpoup1.atualiza(5);
        System.out.println("Atualiza:" + cpoup1);

        System.out.println("\n~4 Opções conta~");
        ccor1.deposita(500);
        System.out.println("Deposita:" + ccor1);
        ccor1.saca(450);
        System.out.println("Saca:" + ccor1);

        System.out.println("\n~5 Opções conta~");
        System.out.println(associados);

        System.out.println("\n~6 Opções conta~");
        System.out.println("Ordem Decrescente Cotas:");
        associados.stream()
                .sorted(Comparator.comparing(Associado::getQdeCotas).reversed())
                .forEach(System.out::println);

        System.out.println("\n~7 Opções contas:");
        auxiliar = associados.stream()
                .mapToDouble(Associado::getQdeCotas)
                .max()
                .orElse(0.0);

        associados.stream()
                .filter(p -> p.getQdeCotas() == auxiliar)
                .forEach(System.out::println);
        System.out.println("\n~8 Opções conta~");
        cpoup2.deposita(200);
        cpoup3.deposita(250);
        ccor2.deposita(300);
        ccor3.deposita(500);

        System.out.println("\nContas em Ordem Decrescente de Saldo:");
        contas.sort(Comparator.comparingDouble(Conta::getSaldo).reversed());
        System.out.println(contas);

        System.out.println("\nAssociados com Conta Corrente:");
        contas.stream()
                .filter(p -> p instanceof ContaCorrente)
                .forEach(System.out::println);

        auxiliar = contas.stream()
                .mapToDouble(Conta::getSaldo)
                .max()
                .orElse(0.0);

        System.out.println("\nContas com maior Saldo:");
        contas.stream()
                .filter(p -> p.getSaldo() == auxiliar)
                .forEach(System.out::println);
    }
}
