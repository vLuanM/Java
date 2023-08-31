package model;

import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Calendar;
import model.Tipo;
public class Pedido {
    private int numero;
    private Calendar data;
    private double valor;
    private Tipo tipo;
    private Vendedor vendedor;
    private List<Item> itens = new ArrayList<>();

    public boolean manterPedido(int numero){
        return true;
    }

    public boolean manterItem(int codItem){
        return true;
    }

    public Pedido() {
    }

    public Pedido(int numero, Calendar  data, double valor, Tipo tipo, Vendedor vendedor, List<Item> itens) {
        if(numero > 0){this.numero = numero;}
        this.data = data;
        if(valor >= 0){this.valor = valor;}
        this.tipo = tipo;
        this.vendedor = vendedor;
        this.itens = itens;
    }

    public Pedido(int numero, Calendar data, double valor, Tipo tipo) {
        if(numero > 0){this.numero = numero;}
        this.data = data;
        if(valor >= 0){this.valor = valor;}
        this.tipo = tipo;
    }

    public Pedido(int numero, Calendar data, double valor) {
        if(numero > 0){this.numero = numero;}
        this.data = data;
        if(valor >= 0){this.valor = valor;}
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        if(numero > 0){this.numero = numero;}
    }

    public Calendar  getData() {
        return data;
    }

    public void setData(Calendar  data) {
        this.data = data;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        if(valor >= 0){this.valor = valor;}
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    public List<Item> getItens() {
        return itens;
    }

    public void setItens(List<Item> itens) {
        this.itens = itens;
    }

    private static String calendarToString(Calendar data) {
        if (data != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("DD/MM/YYYY");
            return sdf.format(data.getTime());
        }
        return "00/00/0000 00:00";
    }
    @Override
    public String toString() {
        return "\nPedido{" +
                "numero=" + numero +
                ", data=" + calendarToString(data) +
                ", valor=" + valor +
                ", tipo='" + tipo + '\'' +
                ", vendedor=" + vendedor +
                ", itens=" + itens +
                '}';
    }

}
