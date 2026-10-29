/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control;

import Conection.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ArthurGiuvannucci
 */
public class FeriasController {

    public boolean cadastrar(
            String inicio,
            String fim,
            String situacao) throws SQLException {

        String sql = "INSERT INTO ferias (inicio, final, situacao) "
                + "VALUES (?, ?, ?)";

        try (Connection con = Conexao.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, inicio);
            ps.setString(2, fim);
            ps.setString(3, situacao);

            return ps.executeUpdate() > 0;
        }
    }

    public List<String[]> listar() throws SQLException {
        List<String[]> lista = new ArrayList<>();

        String sql = "SELECT idferias, inicio, final, situacao "
                + "FROM ferias ORDER BY idferias";

        try (Connection con = Conexao.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(new String[]{
                    String.valueOf(rs.getInt("idferias")),
                    rs.getString("inicio"),
                    rs.getString("final"),
                    rs.getString("situacao")
                });
            }
        }

        return lista;
    }

    public boolean atualizar(
            int idFerias,
            String inicio,
            String fim,
            String situacao) throws SQLException {

        String sql = "UPDATE ferias SET inicio=?, final=?, "
                + "situacao=? WHERE idferias=?";

        try (Connection con = Conexao.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, inicio);
            ps.setString(2, fim);
            ps.setString(3, situacao);
            ps.setInt(4, idFerias);

            return ps.executeUpdate() > 0;
        }
    }

    public boolean excluir(int idFerias) throws SQLException {
        String sql = "DELETE FROM ferias WHERE idferias = ?";

        try (Connection con = Conexao.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idFerias);
            return ps.executeUpdate() > 0;
        }
    }
}
