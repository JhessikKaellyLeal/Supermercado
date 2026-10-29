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

public class Produtos_has_etiquetasController {
   // VINCULAR ETIQUETA AO PRODUTO
    public boolean cadastrar(
            int idProduto,
            int idCategoria,
            int idMarca,
            int idUnidadeMedida,
            int idEtiqueta) throws SQLException {

        String sql = "INSERT INTO produtos_has_etiquetas "
                + "(produtos_idprodutos, "
                + "produtos_categorias_idcategorias, "
                + "produtos_marcas_idmarcas, "
                + "produtos_unidadeMedidas_idunidadeMedidas, "
                + "etiquetas_idetiquetas) "
                + "VALUES (?, ?, ?, ?, ?)";

        try (Connection con = Conexao.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idProduto);
            ps.setInt(2, idCategoria);
            ps.setInt(3, idMarca);
            ps.setInt(4, idUnidadeMedida);
            ps.setInt(5, idEtiqueta);

            return ps.executeUpdate() > 0;
        }
    }

    // LISTAR TODOS OS VINCULOS
    public List<Map<String, Object>> listar() throws SQLException {

        List<Map<String, Object>> lista = new ArrayList<>();

        String sql = "SELECT * FROM produtos_has_etiquetas";

        try (Connection con = Conexao.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Map<String, Object> item = new LinkedHashMap<>();

                item.put("produtos_idprodutos",
                        rs.getInt("produtos_idprodutos"));

                item.put("produtos_categorias_idcategorias",
                        rs.getInt("produtos_categorias_idcategorias"));

                item.put("produtos_marcas_idmarcas",
                        rs.getInt("produtos_marcas_idmarcas"));

                item.put("produtos_unidadeMedidas_idunidadeMedidas",
                        rs.getInt("produtos_unidadeMedidas_idunidadeMedidas"));

                item.put("etiquetas_idetiquetas",
                        rs.getInt("etiquetas_idetiquetas"));

                lista.add(item);
            }
        }

        return lista;
    }

    // LISTAR ETIQUETAS DE UM PRODUTO
    public List<Map<String, Object>> listarPorProduto(
            int idProduto) throws SQLException {

        List<Map<String, Object>> lista = new ArrayList<>();

        String sql = "SELECT * FROM produtos_has_etiquetas "
                + "WHERE produtos_idprodutos=?";

        try (Connection con = Conexao.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idProduto);

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    Map<String, Object> item = new LinkedHashMap<>();

                    item.put("produtos_idprodutos",
                            rs.getInt("produtos_idprodutos"));

                    item.put("produtos_categorias_idcategorias",
                            rs.getInt("produtos_categorias_idcategorias"));

                    item.put("produtos_marcas_idmarcas",
                            rs.getInt("produtos_marcas_idmarcas"));

                    item.put("produtos_unidadeMedidas_idunidadeMedidas",
                            rs.getInt("produtos_unidadeMedidas_idunidadeMedidas"));

                    item.put("etiquetas_idetiquetas",
                            rs.getInt("etiquetas_idetiquetas"));

                    lista.add(item);
                }
            }
        }

        return lista;
    }

    // REMOVER VINCULO ENTRE PRODUTO E ETIQUETA
    public boolean excluir(
            int idProduto,
            int idCategoria,
            int idMarca,
            int idUnidadeMedida,
            int idEtiqueta) throws SQLException {

        String sql = "DELETE FROM produtos_has_etiquetas "
                + "WHERE produtos_idprodutos=? "
                + "AND produtos_categorias_idcategorias=? "
                + "AND produtos_marcas_idmarcas=? "
                + "AND produtos_unidadeMedidas_idunidadeMedidas=? "
                + "AND etiquetas_idetiquetas=?";

        try (Connection con = Conexao.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idProduto);
            ps.setInt(2, idCategoria);
            ps.setInt(3, idMarca);
            ps.setInt(4, idUnidadeMedida);
            ps.setInt(5, idEtiqueta);

            return ps.executeUpdate() > 0;
        }
    }  
}
