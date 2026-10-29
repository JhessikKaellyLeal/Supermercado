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
public class ProdutosController {
     public boolean cadastrar(
            String nome, String codigo, String descricao,
            double precoCusto, double precoVenda, int situacao,
            int estoque, String validade, byte[] imagem,
            int idCategoria, int idMarca, int idUnidade)
            throws SQLException {

        String sql = "INSERT INTO produtos "
                + "(nome, codigo, descricao, precoCusto, precoVenda, "
                + "situacao, estoque, validade, imagem, "
                + "categorias_idcategorias, marcas_idmarcas, "
                + "unidadeMedidas_idunidadeMedidas) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection c = Conexao.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, nome);
            ps.setString(2, codigo);
            ps.setString(3, descricao);
            ps.setDouble(4, precoCusto);
            ps.setDouble(5, precoVenda);
            ps.setInt(6, situacao);
            ps.setInt(7, estoque);
            ps.setString(8, validade);

            if (imagem == null) {
                ps.setNull(9, Types.BLOB);
            } else {
                ps.setBytes(9, imagem);
            }

            ps.setInt(10, idCategoria);
            ps.setInt(11, idMarca);
            ps.setInt(12, idUnidade);

            return ps.executeUpdate() > 0;
        }
    }

    public List<Map<String, Object>> listar() throws SQLException {
        List<Map<String, Object>> lista = new ArrayList<>();

        String sql = "SELECT p.*, c.nome AS categoria, "
                + "m.nome AS marca, u.nome AS unidade "
                + "FROM produtos p "
                + "JOIN categorias c "
                + "ON p.categorias_idcategorias = c.idcategorias "
                + "JOIN marcas m ON p.marcas_idmarcas = m.idmarcas "
                + "JOIN unidadeMedidas u "
                + "ON p.unidadeMedidas_idunidadeMedidas = "
                + "u.idunidadeMedidas "
                + "ORDER BY p.nome";

        try (Connection c = Conexao.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Map<String, Object> m = new LinkedHashMap<>();

                m.put("idprodutos", rs.getInt("idprodutos"));
                m.put("nome", rs.getString("nome"));
                m.put("codigo", rs.getString("codigo"));
                m.put("descricao", rs.getString("descricao"));
                m.put("precoCusto", rs.getDouble("precoCusto"));
                m.put("precoVenda", rs.getDouble("precoVenda"));
                m.put("situacao", rs.getInt("situacao"));
                m.put("estoque", rs.getInt("estoque"));
                m.put("validade", rs.getString("validade"));
                m.put("imagem", rs.getBytes("imagem"));

                m.put("categorias_idcategorias",
                      rs.getInt("categorias_idcategorias"));
                m.put("marcas_idmarcas",
                      rs.getInt("marcas_idmarcas"));
                m.put("unidadeMedidas_idunidadeMedidas",
                      rs.getInt("unidadeMedidas_idunidadeMedidas"));

                m.put("categoria", rs.getString("categoria"));
                m.put("marca", rs.getString("marca"));
                m.put("unidade", rs.getString("unidade"));

                lista.add(m);
            }
        }

        return lista;
    }

    public boolean editar(
            int id, String nome, String codigo, String descricao,
            double precoCusto, double precoVenda, int situacao,
            int estoque, String validade, byte[] imagem,
            int idCategoria, int idMarca, int idUnidade)
            throws SQLException {

        String sql = "UPDATE produtos SET nome=?, codigo=?, "
                + "descricao=?, precoCusto=?, precoVenda=?, "
                + "situacao=?, estoque=?, validade=?, imagem=?, "
                + "categorias_idcategorias=?, marcas_idmarcas=?, "
                + "unidadeMedidas_idunidadeMedidas=? "
                + "WHERE idprodutos=?";

        try (Connection c = Conexao.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, nome);
            ps.setString(2, codigo);
            ps.setString(3, descricao);
            ps.setDouble(4, precoCusto);
            ps.setDouble(5, precoVenda);
            ps.setInt(6, situacao);
            ps.setInt(7, estoque);
            ps.setString(8, validade);

            if (imagem == null) {
                ps.setNull(9, Types.BLOB);
            } else {
                ps.setBytes(9, imagem);
            }

            ps.setInt(10, idCategoria);
            ps.setInt(11, idMarca);
            ps.setInt(12, idUnidade);
            ps.setInt(13, id);

            return ps.executeUpdate() > 0;
        }
    }

    public boolean desativar(int id) throws SQLException {
        String sql = "UPDATE produtos SET situacao=0 "
                   + "WHERE idprodutos=?";

        try (Connection c = Conexao.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }

    public List<Map<String, Object>> pesquisar(String termo)
            throws SQLException {

        List<Map<String, Object>> lista = new ArrayList<>();

        String sql = "SELECT idprodutos, nome, codigo, precoVenda, "
                + "estoque, situacao FROM produtos "
                + "WHERE nome LIKE ? OR codigo LIKE ? ORDER BY nome";

        try (Connection c = Conexao.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, "%" + termo + "%");
            ps.setString(2, "%" + termo + "%");

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Map<String, Object> m = new LinkedHashMap<>();

                    m.put("idprodutos", rs.getInt("idprodutos"));
                    m.put("nome", rs.getString("nome"));
                    m.put("codigo", rs.getString("codigo"));
                    m.put("precoVenda", rs.getDouble("precoVenda"));
                    m.put("estoque", rs.getInt("estoque"));
                    m.put("situacao", rs.getInt("situacao"));

                    lista.add(m);
                }
            }
        }

        return lista;
    }

}
