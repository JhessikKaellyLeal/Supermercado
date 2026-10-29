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
public class MovimentacaoEstoqueController {
    public boolean cadastrar(
            String tipoMovimentacao,
            int quantidade,
            String dataMovimentacao,
            String horaMovimentacao,
            byte[] documentoReferencia,
            int idProduto,
            int idCategoria,
            int idMarca,
            int idUnidadeMedida) throws SQLException {

        String sql = "INSERT INTO movimentacaoEstoque "
                + "(tipoMovimentacao, quantidade, "
                + "dataMovimentacao, horaMovimentacao, "
                + "documentoReferencia, produtos_idprodutos, "
                + "produtos_categorias_idcategorias, "
                + "produtos_marcas_idmarcas, "
                + "produtos_unidadeMedidas_idunidadeMedidas) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = Conexao.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, tipoMovimentacao);
            ps.setInt(2, quantidade);
            ps.setString(3, dataMovimentacao);
            ps.setString(4, horaMovimentacao);
            ps.setBytes(5, documentoReferencia);
            ps.setInt(6, idProduto);
            ps.setInt(7, idCategoria);
            ps.setInt(8, idMarca);
            ps.setInt(9, idUnidadeMedida);

            return ps.executeUpdate() > 0;
        }
    }

    public List<Map<String, Object>> listar() throws SQLException {

        List<Map<String, Object>> lista = new ArrayList<>();

        String sql = "SELECT idmovimentacaoEstoque, "
                + "tipoMovimentacao, quantidade, "
                + "dataMovimentacao, horaMovimentacao, "
                + "produtos_idprodutos, "
                + "produtos_categorias_idcategorias, "
                + "produtos_marcas_idmarcas, "
                + "produtos_unidadeMedidas_idunidadeMedidas "
                + "FROM movimentacaoEstoque";

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
            String tipoMovimentacao,
            int quantidade,
            String dataMovimentacao,
            String horaMovimentacao,
            byte[] documentoReferencia,
            int idProduto,
            int idCategoria,
            int idMarca,
            int idUnidadeMedida) throws SQLException {

        String sql = "UPDATE movimentacaoEstoque SET "
                + "tipoMovimentacao=?, quantidade=?, "
                + "dataMovimentacao=?, horaMovimentacao=?, "
                + "documentoReferencia=?, produtos_idprodutos=?, "
                + "produtos_categorias_idcategorias=?, "
                + "produtos_marcas_idmarcas=?, "
                + "produtos_unidadeMedidas_idunidadeMedidas=? "
                + "WHERE idmovimentacaoEstoque=?";

        try (Connection con = Conexao.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, tipoMovimentacao);
            ps.setInt(2, quantidade);
            ps.setString(3, dataMovimentacao);
            ps.setString(4, horaMovimentacao);
            ps.setBytes(5, documentoReferencia);
            ps.setInt(6, idProduto);
            ps.setInt(7, idCategoria);
            ps.setInt(8, idMarca);
            ps.setInt(9, idUnidadeMedida);
            ps.setInt(10, id);

            return ps.executeUpdate() > 0;
        }
    }

    public boolean excluir(int id) throws SQLException {

        String sql = "DELETE FROM movimentacaoEstoque "
                + "WHERE idmovimentacaoEstoque=?";

        try (Connection con = Conexao.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            return ps.executeUpdate() > 0;
        }
    } 
}
