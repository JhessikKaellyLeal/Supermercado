
package Control;

import Conection.Conexao;
import Model.Estoque;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EstoqueController {

    // LISTAR ESTOQUE POR NOME DO PRODUTO
public List<Estoque> listarPorNomeProduto(String nomeProduto)
        throws SQLException {

    List<Estoque> lista = new ArrayList<>();

    String sql = "SELECT e.* "
            + "FROM estoque e "
            + "INNER JOIN produtos p "
            + "ON e.produtos_idprodutos = p.idprodutos "
            + "WHERE p.nome LIKE ? "
            + "ORDER BY e.idestoque DESC";

    try (Connection conn = Conexao.conectar();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, "%" + nomeProduto + "%");

        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                lista.add(mapearEstoque(rs));
            }
        }
    }

    return lista;
}
    
    public void cadastrar(Estoque estoque) throws SQLException {
        String sql = "INSERT INTO estoque "
                + "(quantidadeAtual, quantidadeReservada, dataAtualizacao, "
                + "produtos_idprodutos, produtos_categorias_idcategorias, "
                + "produtos_marcas_idmarcas, produtos_unidadeMedidas_idunidadeMedidas) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, estoque.getQuantidadeAtual());
            stmt.setInt(2, estoque.getQuantidadeReservada());
            stmt.setString(3, estoque.getDataAtualizacao());
            stmt.setInt(4, estoque.getProdutos_idprodutos());
            stmt.setInt(5, estoque.getProdutos_categorias_idcategorias());
            stmt.setInt(6, estoque.getProdutos_marcas_idmarcas());
            stmt.setInt(7, estoque.getProdutos_unidadeMedidas_idunidadeMedidas());

            stmt.executeUpdate();
        }
    }

    public List<Estoque> listar() throws SQLException {
        List<Estoque> lista = new ArrayList<>();

        String sql = "SELECT * FROM estoque";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                lista.add(mapearEstoque(rs));
            }
        }

        return lista;
    }

    public Estoque buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM estoque WHERE idestoque = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapearEstoque(rs);
                }
            }
        }

        return null;
    }

    public void atualizar(Estoque estoque) throws SQLException {
        String sql = "UPDATE estoque SET "
                + "quantidadeAtual = ?, "
                + "quantidadeReservada = ?, "
                + "dataAtualizacao = ?, "
                + "produtos_idprodutos = ?, "
                + "produtos_categorias_idcategorias = ?, "
                + "produtos_marcas_idmarcas = ?, "
                + "produtos_unidadeMedidas_idunidadeMedidas = ? "
                + "WHERE idestoque = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, estoque.getQuantidadeAtual());
            stmt.setInt(2, estoque.getQuantidadeReservada());
            stmt.setString(3, estoque.getDataAtualizacao());
            stmt.setInt(4, estoque.getProdutos_idprodutos());
            stmt.setInt(5, estoque.getProdutos_categorias_idcategorias());
            stmt.setInt(6, estoque.getProdutos_marcas_idmarcas());
            stmt.setInt(7, estoque.getProdutos_unidadeMedidas_idunidadeMedidas());
            stmt.setInt(8, estoque.getIdestoque());

            stmt.executeUpdate();
        }
    }

    public void excluir(int id) throws SQLException {
        String sql = "DELETE FROM estoque WHERE idestoque = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public Estoque buscarPorProduto(int idProduto) throws SQLException {
        String sql = "SELECT * FROM estoque WHERE produtos_idprodutos = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idProduto);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapearEstoque(rs);
                }
            }
        }

        return null;
    }

    public void atualizarQuantidade(int idProduto, int quantidadeAtual,
            int quantidadeReservada, String dataAtualizacao) throws SQLException {

        String sql = "UPDATE estoque SET "
                + "quantidadeAtual = ?, "
                + "quantidadeReservada = ?, "
                + "dataAtualizacao = ? "
                + "WHERE produtos_idprodutos = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, quantidadeAtual);
            stmt.setInt(2, quantidadeReservada);
            stmt.setString(3, dataAtualizacao);
            stmt.setInt(4, idProduto);

            stmt.executeUpdate();
        }
    }

    private Estoque mapearEstoque(ResultSet rs) throws SQLException {
        Estoque estoque = new Estoque();

        estoque.setIdestoque(rs.getInt("idestoque"));
        estoque.setQuantidadeAtual(rs.getInt("quantidadeAtual"));
        estoque.setQuantidadeReservada(rs.getInt("quantidadeReservada"));
        estoque.setDataAtualizacao(rs.getString("dataAtualizacao"));
        estoque.setProdutos_idprodutos(rs.getInt("produtos_idprodutos"));
        estoque.setProdutos_categorias_idcategorias(rs.getInt("produtos_categorias_idcategorias"));
        estoque.setProdutos_marcas_idmarcas(rs.getInt("produtos_marcas_idmarcas"));
        estoque.setProdutos_unidadeMedidas_idunidadeMedidas(rs.getInt("produtos_unidadeMedidas_idunidadeMedidas"));

        return estoque;
    }
}
