package dao;

import model.Dentista;
import model.Paciente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DentistaDAO extends BaseDAO{

    public static boolean cadastrarDentista(Dentista dentista) {
        final String sql = "INSERT INTO dentista (nome, cro) VALUES (?, ?)";
        try (
                Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)
        )
        {
            pstmt.setString(1, dentista.getNome());
            pstmt.setString(2, dentista.getCro());
            int count = pstmt.executeUpdate();
            return count > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean atualizarDadosDentista(Dentista dentista) {
        final String sql = "UPDATE dentista SET nome=?, cro=? WHERE cod_dentista=?";
        try (
                Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)
        )
        {
            pstmt.setString(1, dentista.getNome());
            pstmt.setString(2, dentista.getCro());
            pstmt.setInt(3, dentista.getCod_dentista());
            int count = pstmt.executeUpdate();
            return count > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static List<Dentista> selecionarDentista() {
        final String sql = "SELECT * FROM dentista ORDER BY nome";
        try
                (
                        Connection conn = getConnection();
                        PreparedStatement pstmt = conn.prepareStatement(sql);
                        ResultSet rs = pstmt.executeQuery()
                )
        {
            List<Dentista> dentistas = new ArrayList<>();
            while (rs.next()) {
                dentistas.add(resultsetToDentista(rs));
            }
            return dentistas;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<Dentista> selecionarNomeDentista(String nome) {
        final String sql = "SELECT * FROM dentista WHERE nome LIKE ? ORDER BY nome";
        try
                (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nome.toLowerCase() + "%");
            ResultSet rs = pstmt.executeQuery();
            List<Dentista> dentistas = new ArrayList<>();
            while (rs.next()) {
                dentistas.add(resultsetToDentista(rs));
            }
            return dentistas;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Dentista selecionarCroDentista(String cro) {
        final String sql = "SELECT * FROM dentista WHERE cro=?";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, cro);
            ResultSet rs = pstmt.executeQuery();
            Dentista dentista = null;
            if (rs.next()) {
                dentista = resultsetToDentista(rs);
            }
            rs.close();
            return dentista;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static Dentista selecionarDentistaCodigo(int cod_dentista ) {
        final String sql = "SELECT * FROM dentista WHERE cod_dentista=?";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, cod_dentista);
            ResultSet rs = pstmt.executeQuery();
            Dentista dentista = null;
            if (rs.next()) {
                dentista = resultsetToDentista(rs);
            }
            rs.close();
            return dentista;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static Dentista resultsetToDentista(ResultSet rs) throws SQLException {
        Dentista c = new Dentista();
        c.setCod_dentista(rs.getInt("cod_dentista"));
        c.setNome(rs.getString("nome"));
        c.setCro(rs.getString("cro"));

        return c;
    }
}
