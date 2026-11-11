
package Control;

import Conection.Conexao;
import Model.ItensCompras;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItensCompraController {
    
    
public List<ItensCompras> listarPorCompra(int idCompra)
        throws SQLException {

    List<ItensCompras> lista = new ArrayList<>();

    String sql = "SELECT * FROM itensCompras "
            + "WHERE compras_idcompras = ?";

    try (Connection conn = Conexao.conectar();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, idCompra);

        try (ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                ItensCompras item = new ItensCompras();

                item.setIditensCompra(
                        rs.getInt("iditensCompras")
                );

                item.setQuantidade(
                        rs.getInt("quantidade")
                );

                item.setPrecoUnitario(
                        rs.getDouble("precoUnitario")
                );

                item.setDesconto(
                        rs.getDouble("desconto")
                );

                item.setProdutos_idprodutos(
                        rs.getInt("Produtos_idprodutos")
                );

                item.setProdutos_categorias_idcategorias(
                        rs.getInt("Produtos_categorias_idcategorias")
                );

                item.setProdutos_marcas_idmarcas(
                        rs.getInt("Produtos_marcas_idmarcas")
                );

                item.setProdutos_unidadeMedidas_idunidadeMedidas(
                        rs.getInt("Produtos_unidadeMedidas_idunidadeMedidas")
                );

                item.setCompras_idcompras(
                        rs.getInt("compras_idcompras")
                );

                lista.add(item);
            }
        }
    }

    return lista;
}


    public void cadastrar(ItensCompras item) throws SQLException {
        String sql = "INSERT INTO itensCompra "
                + "(quantidade, precoUnitario, desconto, compras_idcompras, "
                + "produtos_idprodutos, produtos_categorias_idcategorias, "
                + "produtos_marcas_idmarcas, produtos_unidadeMedidas_idunidadeMedidas) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, item.getQuantidade());
            stmt.setDouble(2, item.getPrecoUnitario());
            stmt.setDouble(3, item.getDesconto());
            stmt.setInt(4, item.getCompras_idcompras());
            stmt.setInt(5, item.getProdutos_idprodutos());
            stmt.setInt(6, item.getProdutos_categorias_idcategorias());
            stmt.setInt(7, item.getProdutos_marcas_idmarcas());
            stmt.setInt(8, item.getProdutos_unidadeMedidas_idunidadeMedidas());

            stmt.executeUpdate();
        }
    }

    public List<ItensCompras> listar() throws SQLException {
        List<ItensCompras> lista = new ArrayList<>();

        String sql = "SELECT * FROM itensCompra ORDER BY iditensCompra DESC";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                lista.add(mapearItem(rs));
            }
        }

        return lista;
    }

    public ItensCompras buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM itensCompra WHERE iditensCompra = ?";

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

    public List<ItensCompras> buscarPorCompra(int idCompra)
            throws SQLException {

        List<ItensCompras> lista = new ArrayList<>();

        String sql = "SELECT * FROM itensCompra "
                + "WHERE compras_idcompras = ? "
                + "ORDER BY iditensCompra DESC";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idCompra);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapearItem(rs));
                }
            }
        }

        return lista;
    }

    public List<ItensCompras> buscarPorProduto(int idProduto)
            throws SQLException {

        List<ItensCompras> lista = new ArrayList<>();

        String sql = "SELECT * FROM itensCompra "
                + "WHERE produtos_idprodutos = ? "
                + "ORDER BY iditensCompra DESC";

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

    public void atualizar(ItensCompras item) throws SQLException {
        String sql = "UPDATE itensCompra SET "
                + "quantidade = ?, precoUnitario = ?, desconto = ?, "
                + "compras_idcompras = ?, produtos_idprodutos = ?, "
                + "produtos_categorias_idcategorias = ?, "
                + "produtos_marcas_idmarcas = ?, "
                + "produtos_unidadeMedidas_idunidadeMedidas = ? "
                + "WHERE iditensCompra = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, item.getQuantidade());
            stmt.setDouble(2, item.getPrecoUnitario());
            stmt.setDouble(3, item.getDesconto());
            stmt.setInt(4, item.getCompras_idcompras());
            stmt.setInt(5, item.getProdutos_idprodutos());
            stmt.setInt(6, item.getProdutos_categorias_idcategorias());
            stmt.setInt(7, item.getProdutos_marcas_idmarcas());
            stmt.setInt(8, item.getProdutos_unidadeMedidas_idunidadeMedidas());
            stmt.setInt(9, item.getIditensCompra());

            stmt.executeUpdate();
        }
    }

    public void excluir(int id) throws SQLException {
        String sql = "DELETE FROM itensCompra WHERE iditensCompra = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    private ItensCompras mapearItem(ResultSet rs) throws SQLException {
        ItensCompras item = new ItensCompras();

        item.setIditensCompra(rs.getInt("iditensCompra"));
        item.setQuantidade(rs.getInt("quantidade"));
        item.setPrecoUnitario(rs.getDouble("precoUnitario"));
        item.setDesconto(rs.getDouble("desconto"));
        item.setCompras_idcompras(rs.getInt("compras_idcompras"));
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
