package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Dentista {
    private int cod_dentista;
    private String cro;
    private String nome;

    @Override
    public String toString() {
        return "\nDentista{" +
                "cod_dentista=" + cod_dentista +
                ", cro='" + cro + '\'' +
                ", nome='" + nome + '\'' +
                '}';
    }
}
