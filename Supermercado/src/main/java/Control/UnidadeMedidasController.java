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
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author CleicianeGomes
 */
public class UnidadeMedidasController {
    public boolean cadastrar(int codigo, String nome, boolean status,
                             String descricao) throws SQLException {
        String sql = "INSERT INTO unidadeMedidas "
                   + "(codigo, nome, status, descricao) VALUES (?, ?, ?, ?)";

        try (Connection c = Conexao.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, codigo);
            ps.setString(2, nome);
            ps.setBoolean(3, status);
            ps.setString(4, descricao);
            return ps.executeUpdate() > 0;
        }
    }

    public List<Map<String, Object>> listar() throws SQLException {
        List<Map<String, Object>> lista = new ArrayList<>();
        String sql = "SELECT * FROM unidadeMedidas ORDER BY nome";

        try (Connection c = Conexao.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Map<String, Object> m = new LinkedHashMap<>();
                m.put("idunidadeMedidas",
                      rs.getInt("idunidadeMedidas"));
                m.put("codigo", rs.getInt("codigo"));
                m.put("nome", rs.getString("nome"));
                m.put("status", rs.getBoolean("status"));
                m.put("descricao", rs.getString("descricao"));
                lista.add(m);
            }
        }
        return lista;
    }

    public boolean editar(int id, int codigo, String nome,
                          boolean status, String descricao)
                          throws SQLException {
        String sql = "UPDATE unidadeMedidas SET codigo=?, nome=?, "
                   + "status=?, descricao=? WHERE idunidadeMedidas=?";

        try (Connection c = Conexao.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, codigo);
            ps.setString(2, nome);
            ps.setBoolean(3, status);
            ps.setString(4, descricao);
            ps.setInt(5, id);
            return ps.executeUpdate() > 0;
        }
    }

    public boolean desativar(int id) throws SQLException {
        String sql = "UPDATE unidadeMedidas SET status=0 "
                   + "WHERE idunidadeMedidas=?";

        try (Connection c = Conexao.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }

}
