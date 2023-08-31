package model;

import java.util.ArrayList;
import java.util.List;

public class Vendedor extends Funcionario{
    private String local;
    private List<Pedido> pedidos = new ArrayList<>();

    public void realizarPedido(){

    }

    public void consultarPreco(){

    }

    public void fornecerPrecos(){

    }

    public Vendedor() {
    }

    public Vendedor(int matricula, String nome, String endereco, String bairro, String cep, String cidade, String estado, String local, List<Pedido> pedidos) {
        super(matricula, nome, endereco, bairro, cep, cidade, estado);
        this.local = local;
        this.pedidos = pedidos;
    }

    public Vendedor(int matricula, String nome, String endereco, String bairro, String cep, String cidade, String estado, String local) {
        super(matricula, nome, endereco, bairro, cep, cidade, estado);
        this.local = local;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    @Override
    public String toString() {
        return "\n Funcionario{" +
                "matricula=" + getMatricula() +
                ", nome='" + getNome() + '\'' +
                ", endereco='" + getEndereco() + '\'' +
                ", bairro='" + getBairro() + '\'' +
                ", cep='" + getCep() + '\'' +
                ", cidade='" + getCidade() + '\'' +
                ", estado='" + getEstado() + '\'' +
                ", local='" + local + '\'' +
                ", Pedido='" + pedidos + '\'' +
                '}';
    }
}