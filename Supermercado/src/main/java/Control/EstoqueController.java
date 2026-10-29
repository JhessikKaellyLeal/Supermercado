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

public class EstoqueController {
  public boolean cadastrar(
            int quantidadeAtual,
            int quantidadeReservada,
            String dataAtualizacao,
            int idProduto,
            int idCategoria,
            int idMarca,
            int idUnidadeMedida) throws SQLException {

        String sql = "INSERT INTO estoque "
                + "(quantidadeAtual, quantidadeReservada, "
                + "dataAtualizacao, produtos_idprodutos, "
                + "produtos_categorias_idcategorias, "
                + "produtos_marcas_idmarcas, "
                + "produtos_unidadeMedidas_idunidadeMedidas) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = Conexao.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, quantidadeAtual);
            ps.setInt(2, quantidadeReservada);
            ps.setString(3, dataAtualizacao);
            ps.setInt(4, idProduto);
            ps.setInt(5, idCategoria);
            ps.setInt(6, idMarca);
            ps.setInt(7, idUnidadeMedida);

            return ps.executeUpdate() > 0;
        }
    }

    public List<Map<String, Object>> listar() throws SQLException {

        List<Map<String, Object>> lista = new ArrayList<>();

        String sql = "SELECT * FROM estoque";

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
            int quantidadeAtual,
            int quantidadeReservada,
            String dataAtualizacao,
            int idProduto,
            int idCategoria,
            int idMarca,
            int idUnidadeMedida) throws SQLException {

        String sql = "UPDATE estoque SET "
                + "quantidadeAtual=?, quantidadeReservada=?, "
                + "dataAtualizacao=?, produtos_idprodutos=?, "
                + "produtos_categorias_idcategorias=?, "
                + "produtos_marcas_idmarcas=?, "
                + "produtos_unidadeMedidas_idunidadeMedidas=? "
                + "WHERE idestoque=?";

        try (Connection con = Conexao.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, quantidadeAtual);
            ps.setInt(2, quantidadeReservada);
            ps.setString(3, dataAtualizacao);
            ps.setInt(4, idProduto);
            ps.setInt(5, idCategoria);
            ps.setInt(6, idMarca);
            ps.setInt(7, idUnidadeMedida);
            ps.setInt(8, id);

            return ps.executeUpdate() > 0;
        }
    }

    public boolean excluir(int id) throws SQLException {

        String sql = "DELETE FROM estoque WHERE idestoque=?";

        try (Connection con = Conexao.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            return ps.executeUpdate() > 0;
        }
    }   
}
