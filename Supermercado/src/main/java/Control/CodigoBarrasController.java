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
public class CodigoBarrasController {
     public boolean cadastrar(
            String codigo, byte[] imagem, String tipo,
            boolean status, String descricao,
            int idProduto, int idCategoria,
            int idMarca, int idUnidade) throws SQLException {

        String sql = "INSERT INTO codigoBarras "
                + "(codigo, imagem, tipo, status, descricao, "
                + "produtos_idprodutos, "
                + "produtos_categorias_idcategorias, "
                + "produtos_marcas_idmarcas, "
                + "produtos_unidadeMedidas_idunidadeMedidas) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection c = Conexao.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, codigo);

            if (imagem == null) {
                ps.setNull(2, Types.BLOB);
            } else {
                ps.setBytes(2, imagem);
            }

            ps.setString(3, tipo);
            ps.setBoolean(4, status);
            ps.setString(5, descricao);
            ps.setInt(6, idProduto);
            ps.setInt(7, idCategoria);
            ps.setInt(8, idMarca);
            ps.setInt(9, idUnidade);

            return ps.executeUpdate() > 0;
        }
    }

    public List<Map<String, Object>> listar() throws SQLException {
        List<Map<String, Object>> lista = new ArrayList<>();

        String sql = "SELECT cb.*, p.nome AS produto "
                + "FROM codigoBarras cb "
                + "JOIN produtos p "
                + "ON cb.produtos_idprodutos = p.idprodutos "
                + "ORDER BY cb.codigo";

        try (Connection c = Conexao.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Map<String, Object> m = new LinkedHashMap<>();

                m.put("idcodigoBarras", rs.getInt("idcodigoBarras"));
                m.put("codigo", rs.getString("codigo"));
                m.put("imagem", rs.getBytes("imagem"));
                m.put("tipo", rs.getString("tipo"));
                m.put("status", rs.getBoolean("status"));
                m.put("descricao", rs.getString("descricao"));
                m.put("produtos_idprodutos",
                      rs.getInt("produtos_idprodutos"));
                m.put("produtos_categorias_idcategorias",
                      rs.getInt("produtos_categorias_idcategorias"));
                m.put("produtos_marcas_idmarcas",
                      rs.getInt("produtos_marcas_idmarcas"));
                m.put("produtos_unidadeMedidas_idunidadeMedidas",
                      rs.getInt("produtos_unidadeMedidas_idunidadeMedidas"));
                m.put("produto", rs.getString("produto"));

                lista.add(m);
            }
        }

        return lista;
    }

    public boolean editar(
            int id, String codigo, byte[] imagem, String tipo,
            boolean status, String descricao,
            int idProduto, int idCategoria,
            int idMarca, int idUnidade) throws SQLException {

        String sql = "UPDATE codigoBarras SET codigo=?, imagem=?, "
                + "tipo=?, status=?, descricao=?, "
                + "produtos_idprodutos=?, "
                + "produtos_categorias_idcategorias=?, "
                + "produtos_marcas_idmarcas=?, "
                + "produtos_unidadeMedidas_idunidadeMedidas=? "
                + "WHERE idcodigoBarras=?";

        try (Connection c = Conexao.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, codigo);

            if (imagem == null) {
                ps.setNull(2, Types.BLOB);
            } else {
                ps.setBytes(2, imagem);
            }

            ps.setString(3, tipo);
            ps.setBoolean(4, status);
            ps.setString(5, descricao);
            ps.setInt(6, idProduto);
            ps.setInt(7, idCategoria);
            ps.setInt(8, idMarca);
            ps.setInt(9, idUnidade);
            ps.setInt(10, id);

            return ps.executeUpdate() > 0;
        }
    }

    public boolean desativar(int id) throws SQLException {
        String sql = "UPDATE codigoBarras SET status=0 "
                   + "WHERE idcodigoBarras=?";

        try (Connection c = Conexao.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }

}
