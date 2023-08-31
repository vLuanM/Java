package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.sql.Time;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Consulta {
    private int cod_consulta;
    private LocalDate data_consulta;
    private LocalTime hora;
    private Paciente paciente = new Paciente();
    private Dentista dentista = new Dentista();
    private Secretaria secretaria = new Secretaria();


    @Override
    public String toString() {
        return "\nConsulta{" +
                "cod_consulta=" + cod_consulta +
                ", data=" + data_consulta +
                ", hora=" + hora +
                ", paciente=" + paciente +
                ", dentista=" + dentista +
                ", secretaria=" + secretaria +
                '}';
    }
}
