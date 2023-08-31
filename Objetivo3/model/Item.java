package model;

public class Item {
    private int codItem, quantidade;
    private double desconto;
    private Produto produto;

    private double totalItem;

    public Item() {
    }

    public Item(int codItem, int quantidade, double desconto) {
        if(quantidade > 0 && codItem > 0){
            this.quantidade = quantidade;
            this.codItem = codItem;
            if(desconto >= 0){this.desconto = desconto;}
        }
    }

    public Item(int codItem, int quantidade, double desconto, Produto produto) {
        if(quantidade > 0 && codItem > 0){
            this.codItem = codItem;
            this.quantidade = quantidade;
            if(desconto >= 0){this.desconto = desconto;}
            this.produto = produto;
        }
    }

    public int getCodItem() {
        return codItem;
    }

    public void setCodItem(int codItem) {
        if(codItem > 0){this.codItem = codItem;}
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        if(quantidade > 0){this.quantidade = quantidade;}
    }

    public double getDesconto() {
        return desconto;
    }

    public void setDesconto(double desconto) {
        if(desconto >= 0){this.desconto = desconto;}
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public double getTotalItem() {
        return totalItem;
    }

    public void setTotalItem(double totalItem) {
        this.totalItem = totalItem;
    }

    @Override
    public String toString() {
        return "\nItem{" +
                "codItem=" + codItem +
                ", quantidade=" + quantidade +
                ", desconto=" + desconto +
                ", produto=" + produto +
                ", Total Item=" + totalItem +
                '}';
    }
}