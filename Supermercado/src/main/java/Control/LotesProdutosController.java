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

public class LotesProdutosController {
      public boolean cadastrar(
            String numeroLote,
            String dataFabricacao,
            String dataVencimento,
            int quantidadeLote,
            int idProduto,
            int idCategoria,
            int idMarca,
            int idUnidadeMedida) throws SQLException {

        String sql = "INSERT INTO lotesProdutos "
                + "(numeroLote, datafabricacao, dataVencimento, "
                + "quantidadeLote, produtos_idprodutos, "
                + "produtos_categorias_idcategorias, "
                + "produtos_marcas_idmarcas, "
                + "produtos_unidadeMedidas_idunidadeMedidas) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = Conexao.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, numeroLote);
            ps.setString(2, dataFabricacao);
            ps.setString(3, dataVencimento);
            ps.setInt(4, quantidadeLote);
            ps.setInt(5, idProduto);
            ps.setInt(6, idCategoria);
            ps.setInt(7, idMarca);
            ps.setInt(8, idUnidadeMedida);

            return ps.executeUpdate() > 0;
        }
    }

    public List<Map<String, Object>> listar() throws SQLException {

        List<Map<String, Object>> lista = new ArrayList<>();

        String sql = "SELECT * FROM lotesProdutos";

        try (Connection con = Conexao.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Map<String, Object> item = new LinkedHashMap<>();
                ResultSetMetaData meta = rs.getMetaData();

                for (int i = 1; i <= meta.getColumnCount(); i++) {
                    item.put(meta.getColumnLabel(i), rs.getObject(i));
                }

                lista.add(item);
            }
        }

        return lista;
    }

    public boolean atualizar(
            int id,
            String numeroLote,
            String dataFabricacao,
            String dataVencimento,
            int quantidadeLote,
            int idProduto,
            int idCategoria,
            int idMarca,
            int idUnidadeMedida) throws SQLException {

        String sql = "UPDATE lotesProdutos SET "
                + "numeroLote=?, datafabricacao=?, "
                + "dataVencimento=?, quantidadeLote=?, "
                + "produtos_idprodutos=?, "
                + "produtos_categorias_idcategorias=?, "
                + "produtos_marcas_idmarcas=?, "
                + "produtos_unidadeMedidas_idunidadeMedidas=? "
                + "WHERE idlotesProdutos=?";

        try (Connection con = Conexao.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, numeroLote);
            ps.setString(2, dataFabricacao);
            ps.setString(3, dataVencimento);
            ps.setInt(4, quantidadeLote);
            ps.setInt(5, idProduto);
            ps.setInt(6, idCategoria);
            ps.setInt(7, idMarca);
            ps.setInt(8, idUnidadeMedida);
            ps.setInt(9, id);

            return ps.executeUpdate() > 0;
        }
    }

    public boolean excluir(int id) throws SQLException {

        String sql = "DELETE FROM lotesProdutos "
                + "WHERE idlotesProdutos=?";

        try (Connection con = Conexao.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            return ps.executeUpdate() > 0;
        }
    }
  
}
