
package Control;

import Conection.Conexao;
import Model.ItensPedidos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItensPedidosController {

    public void cadastrar(ItensPedidos item) throws SQLException {
        String sql = "INSERT INTO itensPedidos "
                + "(quantidade, precoUnitario, desconto, pedidos_idpedidos, "
                + "produtos_idprodutos, produtos_categorias_idcategorias, "
                + "produtos_marcas_idmarcas, produtos_unidadeMedidas_idunidadeMedidas) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, item.getQuantidade());
            stmt.setDouble(2, item.getPrecoUnitario());
            stmt.setDouble(3, item.getDesconto());
            stmt.setInt(4, item.getPedidos_idpedidos());
            stmt.setInt(5, item.getProdutos_idprodutos());
            stmt.setInt(6, item.getProdutos_categorias_idcategorias());
            stmt.setInt(7, item.getProdutos_marcas_idmarcas());
            stmt.setInt(8, item.getProdutos_unidadeMedidas_idunidadeMedidas());

            stmt.executeUpdate();
        }
    }

    public List<ItensPedidos> listar() throws SQLException {
        List<ItensPedidos> lista = new ArrayList<>();

        String sql = "SELECT * FROM itensPedidos ORDER BY iditensPedidos DESC";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                lista.add(mapearItem(rs));
            }
        }

        return lista;
    }

    public ItensPedidos buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM itensPedidos WHERE iditensPedidos = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapearItem(rs);
                }
            }
        }

        return null;
    }

    public List<ItensPedidos> buscarPorPedido(int idPedido)
            throws SQLException {

        List<ItensPedidos> lista = new ArrayList<>();

        String sql = "SELECT * FROM itensPedidos "
                + "WHERE pedidos_idpedidos = ? "
                + "ORDER BY iditensPedidos DESC";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idPedido);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapearItem(rs));
                }
            }
        }

        return lista;
    }

    public List<ItensPedidos> buscarPorProduto(int idProduto)
            throws SQLException {

        List<ItensPedidos> lista = new ArrayList<>();

        String sql = "SELECT * FROM itensPedidos "
                + "WHERE produtos_idprodutos = ? "
                + "ORDER BY iditensPedidos DESC";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idProduto);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapearItem(rs));
                }
            }
        }

        return lista;
    }

    public void atualizar(ItensPedidos item) throws SQLException {
        String sql = "UPDATE itensPedidos SET "
                + "quantidade = ?, precoUnitario = ?, desconto = ?, "
                + "pedidos_idpedidos = ?, produtos_idprodutos = ?, "
                + "produtos_categorias_idcategorias = ?, "
                + "produtos_marcas_idmarcas = ?, "
                + "produtos_unidadeMedidas_idunidadeMedidas = ? "
                + "WHERE iditensPedidos = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, item.getQuantidade());
            stmt.setDouble(2, item.getPrecoUnitario());
            stmt.setDouble(3, item.getDesconto());
            stmt.setInt(4, item.getPedidos_idpedidos());
            stmt.setInt(5, item.getProdutos_idprodutos());
            stmt.setInt(6, item.getProdutos_categorias_idcategorias());
            stmt.setInt(7, item.getProdutos_marcas_idmarcas());
            stmt.setInt(8, item.getProdutos_unidadeMedidas_idunidadeMedidas());
            stmt.setInt(9, item.getIditensPedidos());

            stmt.executeUpdate();
        }
    }

    public void excluir(int id) throws SQLException {
        String sql = "DELETE FROM itensPedidos WHERE iditensPedidos = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    private ItensPedidos mapearItem(ResultSet rs) throws SQLException {
        ItensPedidos item = new ItensPedidos();

        item.setIditensPedidos(rs.getInt("iditensPedidos"));
        item.setQuantidade(rs.getInt("quantidade"));
        item.setPrecoUnitario(rs.getDouble("precoUnitario"));
        item.setDesconto(rs.getDouble("desconto"));
        item.setPedidos_idpedidos(rs.getInt("pedidos_idpedidos"));
        item.setProdutos_idprodutos(rs.getInt("produtos_idprodutos"));
        item.setProdutos_categorias_idcategorias(
                rs.getInt("produtos_categorias_idcategorias"));
        item.setProdutos_marcas_idmarcas(
                rs.getInt("produtos_marcas_idmarcas"));
        item.setProdutos_unidadeMedidas_idunidadeMedidas(
                rs.getInt("produtos_unidadeMedidas_idunidadeMedidas"));

        return item;
    }
}
