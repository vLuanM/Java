package model;

public class Conta {
    protected double saldo;

    public void deposita(double valor){
        if(valor > 0) {this.saldo += valor;}
    }

    public void saca(double valor){
        if(valor > 0 && valor <= this.saldo){this.saldo -= valor;}
    }

    public void atualiza(double taxa){
        if(taxa > 0 && this.saldo > 0){
            this.saldo += this.saldo * (taxa/100);
        }
    }

    public double getSaldo() {
        return saldo;
    }
}