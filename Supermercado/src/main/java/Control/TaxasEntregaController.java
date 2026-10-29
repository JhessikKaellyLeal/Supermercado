/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control;

/**
 *
 * @author AntônioVieira
 */
import Conection.Conexao;
import java.sql.*;
import java.util.*;
public class TaxasEntregaController {
  public boolean cadastrar(String regiao, String tipoTaxa,
            double valorTaxa, String observacao) throws SQLException {

        String sql = "INSERT INTO taxasEntrega "
                + "(regiao, tipoTaxa, valorTaxa, observacao) "
                + "VALUES (?, ?, ?, ?)";

        try (Connection con = Conexao.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, regiao);
            ps.setString(2, tipoTaxa);
            ps.setDouble(3, valorTaxa);
            ps.setString(4, observacao);

            return ps.executeUpdate() > 0;
        }
    }

    public List<Map<String, Object>> listar() throws SQLException {

        List<Map<String, Object>> lista = new ArrayList<>();

        String sql = "SELECT * FROM taxasEntrega";

        try (Connection con = Conexao.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Map<String, Object> item = new LinkedHashMap<>();

                item.put("idtaxasEntrega",
                        rs.getInt("idtaxasEntrega"));
                item.put("regiao", rs.getString("regiao"));
                item.put("tipoTaxa", rs.getString("tipoTaxa"));
                item.put("valorTaxa", rs.getDouble("valorTaxa"));
                item.put("observacao", rs.getString("observacao"));

                lista.add(item);
            }
        }

        return lista;
    }

    public boolean atualizar(int id, String regiao,
            String tipoTaxa, double valorTaxa,
            String observacao) throws SQLException {

        String sql = "UPDATE taxasEntrega SET "
                + "regiao=?, tipoTaxa=?, valorTaxa=?, observacao=? "
                + "WHERE idtaxasEntrega=?";

        try (Connection con = Conexao.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, regiao);
            ps.setString(2, tipoTaxa);
            ps.setDouble(3, valorTaxa);
            ps.setString(4, observacao);
            ps.setInt(5, id);

            return ps.executeUpdate() > 0;
        }
    }

    public boolean excluir(int id) throws SQLException {

        String sql = "DELETE FROM taxasEntrega "
                + "WHERE idtaxasEntrega=?";

        try (Connection con = Conexao.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            return ps.executeUpdate() > 0;
        }
    } 
}
