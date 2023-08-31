package model;

import java.util.ArrayList;
import java.util.List;


public class Produto {
    private int codigo, quantidade;
    private String nome;
    private double preco;
    private List<Item> itens = new ArrayList<>();

    public boolean manterProduto(int codigo){
        return true;
    }

    public Produto() {
    }

    public Produto(int codigo, int quantidade, String nome, double preco) {
        if(quantidade > 0){
            if(codigo > 0){this.codigo = codigo;}
            this.quantidade = quantidade;
            this.nome = nome;
            if(preco >= 0){this.preco = preco;}
        }
    }

    public Produto(int codigo, int quantidade, String nome, double preco, List<Item> itens) {
        if(quantidade > 0){
            if(codigo > 0){this.codigo = codigo;}
            this.quantidade = quantidade;
            this.nome = nome;
            if(preco >= 0){this.preco = preco;}
            this.itens = itens;
        }
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        if(codigo > 0){this.codigo = codigo;}
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        if(quantidade > 0){this.quantidade = quantidade;}
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        if(preco >= 0){this.preco = preco;}
    }

    public List<Item> getItens() {
        return itens;
    }

    public void setItens(List<Item> itens) {
        this.itens = itens;
    }


    public void aumentarEstoque(int quantidade) {
        this.quantidade += quantidade;
    }

    @Override
    public String toString() {
        return "\nProduto{" +
                "codigo=" + codigo +
                ", quantidade=" + quantidade +
                ", nome='" + nome + '\'' +
                ", preco=" + preco +
                ", itens=" + itens +
                '}';
    }
}