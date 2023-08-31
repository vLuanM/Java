package model;

public class Fornecedor {
    private String contato;
    private int cnpj;
    private String nome;

    public boolean manterFornec(int cnpj){
        return true;
    }

    public Fornecedor() {
    }

    public Fornecedor(String contato, String nome, int cnpj) {
        this.contato = contato;
        this.nome = nome;
        this.cnpj = cnpj;
    }

    @Override
    public String toString() {
        return "\nFornecedor{"+
                "contato='" +  contato +'\''+
                ", nome='" +nome +'\''+
                ", cnpj=" + cnpj +
                '}';
    }
}