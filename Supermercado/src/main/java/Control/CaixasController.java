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
public class CaixasController {
 public boolean cadastrar(String nome, String tipo,
            int status, String observacao) throws SQLException {
        String sql = "INSERT INTO caixas (nome, tipo, status, observacao) VALUES (?, ?, ?, ?)";
        try (Connection con = Conexao.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, nome);
            ps.setString(2, tipo);
            ps.setInt(3, status);
            ps.setString(4, observacao);
            return ps.executeUpdate() > 0;
        }
    }
 
  public List<Map<String, Object>> listar() throws SQLException {
        List<Map<String, Object>> lista = new ArrayList<>();
        String sql = "SELECT * FROM caixas";
        try (Connection con = Conexao.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Map<String, Object> item = new LinkedHashMap<>();
                item.put("idcaixas", rs.getInt("idcaixas"));
                item.put("nome", rs.getString("nome"));
                item.put("tipo", rs.getString("tipo"));
                item.put("status", rs.getInt("status"));
                item.put("observacao", rs.getString("observacao"));
                lista.add(item);
            }
        }
        return lista;
    }

    public boolean atualizar(int id, String nome, String tipo,
            int status, String observacao) throws SQLException {
        String sql = "UPDATE caixas SET nome=?, tipo=?, status=?, observacao=? WHERE idcaixas=?";
        try (Connection con = Conexao.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, nome);
            ps.setString(2, tipo);
            ps.setInt(3, status);
            ps.setString(4, observacao);
            ps.setInt(5, id);
            return ps.executeUpdate() > 0;
        }
    }

    public boolean excluir(int id) throws SQLException {
        String sql = "DELETE FROM caixas WHERE idcaixas=?";
        try (Connection con = Conexao.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }

}
