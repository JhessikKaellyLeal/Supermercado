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
import java.sql.Types;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author CleicianeGomes
 */

   
public class CategoriasController {

    public boolean cadastrar(String nome, String descricao,
                             boolean status) throws SQLException {
        String sql = "INSERT INTO categorias (nome, descricao, status) "
                   + "VALUES (?, ?, ?)";

        try (Connection c = Conexao.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, nome);
            ps.setString(2, descricao);
            ps.setBoolean(3, status);
            return ps.executeUpdate() > 0;
        }
    }

    public List<Map<String, Object>> listar() throws SQLException {
        List<Map<String, Object>> lista = new ArrayList<>();
        String sql = "SELECT * FROM categorias ORDER BY nome";

        try (Connection c = Conexao.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Map<String, Object> m = new LinkedHashMap<>();
                m.put("idcategorias", rs.getInt("idcategorias"));
                m.put("nome", rs.getString("nome"));
                m.put("descricao", rs.getString("descricao"));
                m.put("status", rs.getBoolean("status"));
                lista.add(m);
            }
        }
        return lista;
    }

    public boolean editar(int id, String nome, String descricao,
                          boolean status) throws SQLException {
        String sql = "UPDATE categorias SET nome=?, descricao=?, "
                   + "status=? WHERE idcategorias=?";

        try (Connection c = Conexao.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, nome);
            ps.setString(2, descricao);
            ps.setBoolean(3, status);
            ps.setInt(4, id);
            return ps.executeUpdate() > 0;
        }
    }

    public boolean desativar(int id) throws SQLException {
        String sql = "UPDATE categorias SET status=0 WHERE idcategorias=?";

        try (Connection c = Conexao.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }
}

