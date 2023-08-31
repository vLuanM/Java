package dao;

import model.Consulta;
import model.Dentista;
import model.Paciente;
import model.Secretaria;

import java.time.LocalTime;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConsultaDAO  extends BaseDAO {

    public static Consulta SelecionarConsultaCodigo(int codigo){
        final String sql = "SELECT * FROM consulta WHERE cod_consulta=?";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, codigo);
            ResultSet rs = pstmt.executeQuery();
            Consulta consulta = null;
            if (rs.next()) {
                consulta = resultsetToConsulta(rs);
            }
            rs.close();
            return consulta;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<Consulta> SelecionarConsultasPaciente(int codigo ) {
        final String sql = "SELECT consulta.cod_consulta, consulta.cod_paciente, consulta.cod_dentista, consulta.cod_secretaria, consulta.data, consulta.hora, dentista.nome " +
                "FROM consulta JOIN paciente USING (cod_paciente) JOIN dentista USING (cod_dentista) " +
                "WHERE consulta.cod_paciente=? ORDER BY consulta.data";
        try
                (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, codigo);
            ResultSet rs = pstmt.executeQuery();
            List<Consulta> consultas = new ArrayList<>();
            while (rs.next()) {
                consultas.add(resultsetToConsulta(rs));
            }
            return consultas;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<Consulta> SelecionarConsultasDentista(int codigo ) {
        final String sql = "SELECT consulta.nome_paciente, consulta.data, consulta.hora, dentista.nome " +
                "FROM consulta JOIN paciente USING (cod_paciente) JOIN dentista USING (cod_dentista) " +
                "WHERE consulta.cod_dentista=? ORDER BY consulta.data";
        try
                (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, codigo);
            ResultSet rs = pstmt.executeQuery();
            List<Consulta> consultas = new ArrayList<>();
            while (rs.next()) {
                consultas.add(resultsetToConsulta(rs));
            }
            return consultas;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    public static boolean cadastrarConsulta(Consulta consulta) {
        final String sql = "INSERT INTO consulta (data, hora, cod_paciente, nome_paciente, cod_dentista, cod_secretaria) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        try (
                Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {
            pstmt.setDate(1, java.sql.Date.valueOf(consulta.getData_consulta().toString()));
            pstmt.setTime(2, Time.valueOf(consulta.getHora()));
            pstmt.setInt(3, consulta.getPaciente().getCod_paciente());
            pstmt.setString(4, consulta.getPaciente().getNome());
            pstmt.setInt(5, consulta.getDentista().getCod_dentista());
            pstmt.setInt(6, consulta.getSecretaria().getCod_secretaria());
            int count = pstmt.executeUpdate();
            return count > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean cancelarConsulta(int cod) {
        final String sql = "DELETE FROM consulta WHERE cod_consulta=?";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, cod);
            int count = pstmt.executeUpdate();
            return count > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean remarcarConsulta(Consulta consulta) {
        final String sql = "UPDATE consulta SET data=?, hora=? WHERE cod_consulta=?";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDate(1, java.sql.Date.valueOf(consulta.getData_consulta()));
            pstmt.setTime(2, Time.valueOf(consulta.getHora()));
            pstmt.setInt(3, consulta.getCod_consulta());
            int count = pstmt.executeUpdate();
            return count > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static Consulta resultsetToConsulta(ResultSet rs) throws SQLException {
        Consulta c = new Consulta();
        c.setCod_consulta(rs.getInt("cod_consulta"));
        c.setData_consulta(rs.getDate("data").toLocalDate());
        c.setHora(rs.getTime("hora").toLocalTime());


        c.setPaciente(PacienteDAO.selecionarPacienteCodigo(rs.getInt("cod_paciente")));
        c.setDentista(DentistaDAO.selecionarDentistaCodigo(rs.getInt("cod_dentista")));
        c.setSecretaria(SecretariaDAO.selecionarSecretariaCodigo(rs.getInt("cod_secretaria")));


        return c;
    }

    /*public static void main(String[] args) {

       LocalDate data = LocalDate.of(2019, 04, 06);
        LocalTime horarioDeEntrada = LocalTime.now();
        horarioDeEntrada = LocalTime.of(14, 0);

        Paciente paciente = new Paciente();
        paciente.setCod_paciente(4);
        paciente.setNome("Joaquim Silveira");

        Dentista dentista = new Dentista();
        dentista.setCod_dentista(1);

        Secretaria secretaria = new Secretaria();
        secretaria.setCod_secretaria(1);

        Consulta consulta = new Consulta();
        consulta.setPaciente(paciente);
        consulta.setDentista(dentista);
        consulta.setSecretaria(secretaria);

        consulta.setData_consulta(data);
        consulta.setHora(horarioDeEntrada);
        ConsultaDAO.cadastrarConsulta(consulta);

        LocalTime horario = LocalTime.now();
        horario = LocalTime.of(10, 0);
        LocalDate data = LocalDate.of(2023, 06, 22);

        ConsultaDAO.remarcarConsulta(2, data, horario);

    }*/


}

