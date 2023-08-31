package controller;

import model.*;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class Loja {
    private static double aux = 0;
    public static void main(String[] args){
        // 1° pedido
        Vendedor vendedor1 = new Vendedor(01, "José M.", "T. Domingos de Almeida",
                "Vila Princesa", "123456789", "Rio de Janeiro", "Rio de Janeiro", "Boteco do Seu Zé");

        Produto prod1 = new Produto(051, 30, "Cerveja", 5);
        Item item1 = new Item(011, 12, 5, prod1);

        Produto prod2 = new Produto(153, 90, "Vodka", 10);
        Item item2 = new Item(071, 5, 6, prod2);

        Produto prod3 = new Produto(033, 20, "Whisky", 20);
        Item item3 = new Item(83, 1, 2, prod3);

        List<Item> itens = new ArrayList<>();
        itens.add(item1);
        itens.add(item2);
        itens.add(item3);


        Pedido pedido1 = new Pedido();
        pedido1.setNumero(1); pedido1.setData(new GregorianCalendar(2023, 3, 7));
        pedido1.setTipo(Tipo.valueOf("ATENDIDO"));
        pedido1.setVendedor(vendedor1);
        pedido1.setItens(itens);

        pedido1.getItens().forEach(p -> {
            p.setTotalItem(p.getQuantidade() * p.getProduto().getPreco() -
                    ((p.getQuantidade() * p.getProduto().getPreco()) * p.getDesconto()/100));
            pedido1.setValor(pedido1.getValor() + p.getTotalItem());
        });

        // 2°
        Vendedor vendedor2 = new Vendedor(02, "Ricardo T.", "R. Teixeirinha",
                "Simões", "987654321", "Rio Grande", "Rio Grande do Sul", "Mercadinho Sul");

        Produto prod4 = new Produto(074, 60, "Farinha", 8);
        Item item4 = new Item(034, 10, 3, prod4);

        Produto prod5 = new Produto(105, 100, "Carvão", 5);
        Item item5 = new Item(107, 2, 5, prod5);

        List<Item> itens2 = new ArrayList<>();
        itens2.add(item4);
        itens2.add(item5);

        Pedido pedido2 = new Pedido();
        pedido2.setNumero(2); pedido2.setData(new GregorianCalendar(2023, 4, 2));
        pedido2.setTipo(Tipo.valueOf("ATENDIDO"));
        pedido2.setVendedor(vendedor2);
        pedido2.setItens(itens2);

        pedido2.getItens().forEach(p -> {
            p.setTotalItem(p.getQuantidade() * p.getProduto().getPreco() -
                    ((p.getQuantidade() * p.getProduto().getPreco()) * p.getDesconto()/100));
            pedido2.setValor(pedido2.getValor() + p.getTotalItem());
        });


        List<Pedido> pedidos = new ArrayList<>();
        pedidos.add(pedido1); pedidos.add(pedido2);
        System.out.println(pedidos);
        System.out.println();

        pedidos.forEach(p -> {
            p.getItens().forEach(f -> {
                f.getProduto().setQuantidade(f.getProduto().getQuantidade() - f.getQuantidade());
                System.out.println("Produto: "+ f.getProduto().getNome() +"\n → Estoque: "+ f.getProduto().getQuantidade());
            });
        });

        Produto prod6 = new Produto(55, 50, "MP3", 300);
        Fornecedor fornecedor1 = new Fornecedor("11990010001", "Samsung", 123987543);
        Fornecimento fornecimento1 = new Fornecimento(new GregorianCalendar(2023, 2, 11),
                prod6.getPreco() * prod6.getQuantidade());
        fornecimento1.setFornecedor(fornecedor1);
        fornecimento1.setProduto(prod6);

        Produto prod7 = new Produto(47, 50, "iPod", 1000);
        Fornecedor fornecedor2 = new Fornecedor("53990020002", "Apple", 123987543);
        Fornecimento fornecimento2 = new Fornecimento(new GregorianCalendar(2023, 3, 1),
                prod7.getPreco() * prod7.getQuantidade());
        fornecimento2.setFornecedor(fornecedor2);
        fornecimento2.setProduto(prod7);


        List<Fornecimento> fornecimentos = new ArrayList<>();
        fornecimentos.add(fornecimento1);
        fornecimentos.add(fornecimento2);

        fornecimentos.forEach(p -> {
            aux += p.getValorTotal();
        });

        System.out.println(fornecimentos);
        System.out.println("Valor Total Fornecimentos: "+ aux);
    }
}