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
public class EtiquetasController {
       public boolean cadastrar(String tipo, byte[] imagem)
            throws SQLException {

        String sql = "INSERT INTO etiquetas (tipo, imagem) VALUES (?, ?)";

        try (Connection c = Conexao.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, tipo);
            ps.setBytes(2, imagem);

            return ps.executeUpdate() > 0;
        }
    }

    public List<Map<String, Object>> listar() throws SQLException {
        List<Map<String, Object>> lista = new ArrayList<>();

        String sql = "SELECT idetiquetas, tipo, imagem "
                   + "FROM etiquetas ORDER BY idetiquetas";

        try (Connection c = Conexao.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Map<String, Object> m = new LinkedHashMap<>();

                m.put("idetiquetas", rs.getInt("idetiquetas"));
                m.put("tipo", rs.getString("tipo"));
                m.put("imagem", rs.getBytes("imagem"));

                lista.add(m);
            }
        }

        return lista;
    }

    public boolean editar(int id, String tipo, byte[] imagem)
            throws SQLException {

        String sql = "UPDATE etiquetas SET tipo=?, imagem=? "
                   + "WHERE idetiquetas=?";

        try (Connection c = Conexao.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, tipo);
            ps.setBytes(2, imagem);
            ps.setInt(3, id);

            return ps.executeUpdate() > 0;
        }
    }

    public boolean excluir(int id) throws SQLException {
        String sql = "DELETE FROM etiquetas WHERE idetiquetas=?";

        try (Connection c = Conexao.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }
}
