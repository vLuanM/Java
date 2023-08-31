package dao;

import model.Dentista;
import model.Paciente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PacienteDAO extends BaseDAO{

    public static boolean cadastrarPaciente(Paciente paciente) {
        final String sql = "INSERT INTO paciente (nome, rg, senha) VALUES (?, ?, ?)";
        try (
                Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)
        )
        {
            pstmt.setString(1, paciente.getNome());
            pstmt.setString(2, paciente.getRg());
            pstmt.setString(3, paciente.getSenha());
            int count = pstmt.executeUpdate();
            return count > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean atualizarDadosPaciente(Paciente paciente) {
        final String sql = "UPDATE paciente SET nome=?, rg=?, senha=? WHERE cod_paciente=?";
        try (
                Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)
        )
        {
            pstmt.setString(1, paciente.getNome());
            pstmt.setString(2, paciente.getRg());
            pstmt.setString(3, paciente.getSenha());
            pstmt.setInt(4, paciente.getCod_paciente());
            int count = pstmt.executeUpdate();
            return count > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static List<Paciente> selecionarPacientes() {
        final String sql = "SELECT * FROM paciente ORDER BY nome";
        try
                (
                        Connection conn = getConnection();
                        PreparedStatement pstmt = conn.prepareStatement(sql);
                        ResultSet rs = pstmt.executeQuery()
                )
        {
            List<Paciente> pacientes = new ArrayList<>();
            while (rs.next()) {
                pacientes.add(resultsetToPaciente(rs));
            }
            return pacientes;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<Paciente> selecionarNomePaciente(String nome) {
        final String sql = "SELECT * FROM paciente WHERE nome LIKE ? ORDER BY nome";
        try
                (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nome.toLowerCase() + "%");
            ResultSet rs = pstmt.executeQuery();
            List<Paciente> pacientes = new ArrayList<>();
            while (rs.next()) {
                pacientes.add(resultsetToPaciente(rs));
            }
            return pacientes;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Paciente selecionarRgPaciente(String rg) {
        final String sql = "SELECT * FROM paciente WHERE rg=?";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, rg);
            ResultSet rs = pstmt.executeQuery();
            Paciente paciente = null;
            if (rs.next()) {
                paciente = resultsetToPaciente(rs);
            }
            rs.close();
            return paciente;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static Paciente selecionarPacienteCodigo(int cod_paciente ) {
        final String sql = "SELECT * FROM paciente WHERE cod_paciente=?";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, cod_paciente);
            ResultSet rs = pstmt.executeQuery();
            Paciente paciente = null;
            if (rs.next()) {
                paciente = resultsetToPaciente(rs);
            }
            rs.close();
            return paciente;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static Paciente resultsetToPaciente(ResultSet rs) throws SQLException {
        Paciente c = new Paciente();
        c.setCod_paciente(rs.getInt("cod_paciente"));
        c.setNome(rs.getString("nome"));
        c.setRg(rs.getString("rg"));
        c.setSenha(rs.getString("senha"));

        return c;
    }
}
