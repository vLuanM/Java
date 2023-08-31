package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Secretaria {
    private int cod_secretaria;
    private String nome;
    private String rg;

    @Override
    public String toString() {
        return "\nSecretaria{" +
                "cod_secretaria=" + cod_secretaria +
                ", nome='" + nome + '\'' +
                ", rg='" + rg + '\'' +
                '}';
    }
}
