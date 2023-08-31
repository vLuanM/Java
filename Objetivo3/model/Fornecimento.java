package model;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Fornecimento {
    private Calendar  data;
    private double valorTotal;
    private Produto produto;
    private Fornecedor fornecedor;

    public Fornecimento() {
    }

    public Fornecimento(Calendar data, double valorTotal, Produto produto, Fornecedor fornecedor) {
        this.data = data;
        this.valorTotal = valorTotal;
        this.produto = produto;
        this.fornecedor = fornecedor;
    }

    public Fornecimento(Calendar  data, double valorTotal) {
        this.data = data;
        this.valorTotal = valorTotal;
    }

    public Calendar getData() {
        return data;
    }

    public void setData(Calendar  data) {
        this.data = data;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    private static String calendarToString(Calendar data) {
        if (data != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY");
            return sdf.format(data.getTime());
        }
        return "00/00/0000 00:00";
    }

    @Override
    public String toString() {
        return "\nFornecimento{" +
                "data=" +  calendarToString(data) +
                ", valorTotal=" + valorTotal +
                ", produto=" + produto +
                ", fornecedor=" + fornecedor +
                '}';
    }

}