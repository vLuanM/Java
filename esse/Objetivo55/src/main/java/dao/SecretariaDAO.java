package dao;


import model.Secretaria;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SecretariaDAO extends BaseDAO{

    public static boolean cadastrarSecretaria(Secretaria secretaria) {
        final String sql = "INSERT INTO secretaria (nome, rg) VALUES (?, ?)";
        try (
                Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)
        )
        {
            pstmt.setString(1, secretaria.getNome());
            pstmt.setString(2, secretaria.getRg());
            int count = pstmt.executeUpdate();
            return count > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean atualizarDadosSecretaria(Secretaria secretaria) {
        final String sql = "UPDATE secretaria SET nome=?, rg=? WHERE cod_secretaria=?";
        try (
                Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)
        )
        {
            pstmt.setString(1, secretaria.getNome());
            pstmt.setString(2, secretaria.getRg());
            pstmt.setInt(3, secretaria.getCod_secretaria());
            int count = pstmt.executeUpdate();
            return count > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static List<Secretaria> selecionarSecretarias() {
        final String sql = "SELECT * FROM secretaria ORDER BY nome";
        try
                (
                        Connection conn = getConnection();
                        PreparedStatement pstmt = conn.prepareStatement(sql);
                        ResultSet rs = pstmt.executeQuery()
                )
        {
            List<Secretaria> secretarias = new ArrayList<>();
            while (rs.next()) {
                secretarias.add(resultsetToSecretaria(rs));
            }
            return secretarias;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<Secretaria> selecionarNomeSecretaria(String nome) {
        final String sql = "SELECT * FROM secretaria WHERE nome LIKE ? ORDER BY nome";
        try
                (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nome.toLowerCase() + "%");
            ResultSet rs = pstmt.executeQuery();
            List<Secretaria> secretarias = new ArrayList<>();
            while (rs.next()) {
                secretarias.add(resultsetToSecretaria(rs));
            }
            return secretarias;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<Secretaria> selecionarRgSecretaria(String rg) {
        final String sql = "SELECT * FROM secretaria WHERE rg like ?";
        try
                (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, rg.toLowerCase() + "%");
            ResultSet rs = pstmt.executeQuery();
            List<Secretaria> secretarias = new ArrayList<>();
            while (rs.next()) {
                secretarias.add(resultsetToSecretaria(rs));
            }
            return secretarias;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static Secretaria selecionarSecretariaCodigo(int cod_secretaria) {
        final String sql = "SELECT * FROM secretaria WHERE cod_secretaria=?";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, cod_secretaria);
            ResultSet rs = pstmt.executeQuery();
            Secretaria secretaria = null;
            if (rs.next()) {
                secretaria = resultsetToSecretaria(rs);
            }
            rs.close();
            return secretaria;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    private static Secretaria resultsetToSecretaria(ResultSet rs) throws SQLException {
        Secretaria c = new Secretaria();
        c.setCod_secretaria(rs.getInt("cod_secretaria"));
        c.setNome(rs.getString("nome"));
        c.setRg(rs.getString("rg"));
        return c;
    }
}
