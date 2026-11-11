
package Control;

import Conection.Conexao;
import Model.Promocao_has_Produtos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Promocao_has_produtosController {

    public void cadastrar(Promocao_has_Produtos relacao)
            throws SQLException {

        String sql = "INSERT INTO promocao_has_produtos "
                + "(promocao_idpromocao, produtos_idprodutos, "
                + "produtos_categorias_idcategorias, "
                + "produtos_marcas_idmarcas, "
                + "produtos_unidadeMedidas_idunidadeMedidas) "
                + "VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, relacao.getPromocao_idpromocao());
            stmt.setInt(2, relacao.getProdutos_idprodutos());
            stmt.setInt(3, relacao.getProdutos_categorias_idcategorias());
            stmt.setInt(4, relacao.getProdutos_marcas_idmarcas());
            stmt.setInt(5, relacao.getProdutos_unidadeMedidas_idunidadeMedidas());

            stmt.executeUpdate();
        }
    }

    public List<Promocao_has_Produtos> listar() throws SQLException {

        List<Promocao_has_Produtos> lista = new ArrayList<>();

        String sql = "SELECT * FROM promocao_has_produtos "
                + "ORDER BY promocao_idpromocao";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                lista.add(mapearRelacao(rs));
            }
        }

        return lista;
    }

    public List<Promocao_has_Produtos> buscarPorPromocao(int idPromocao)
            throws SQLException {

        List<Promocao_has_Produtos> lista = new ArrayList<>();

        String sql = "SELECT * FROM promocao_has_produtos "
                + "WHERE promocao_idpromocao = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idPromocao);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapearRelacao(rs));
                }
            }
        }

        return lista;
    }

    public List<Promocao_has_Produtos> buscarPorProduto(int idProduto)
            throws SQLException {

        List<Promocao_has_Produtos> lista = new ArrayList<>();

        String sql = "SELECT * FROM promocao_has_produtos "
                + "WHERE produtos_idprodutos = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idProduto);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapearRelacao(rs));
                }
            }
        }

        return lista;
    }

    public boolean existeRelacao(int idPromocao, int idProduto)
            throws SQLException {

        String sql = "SELECT COUNT(*) FROM promocao_has_produtos "
                + "WHERE promocao_idpromocao = ? "
                + "AND produtos_idprodutos = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idPromocao);
            stmt.setInt(2, idProduto);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }

        return false;
    }

    public void excluir(int idPromocao, int idProduto,
            int idCategoria, int idMarca, int idUnidadeMedida)
            throws SQLException {

        String sql = "DELETE FROM promocao_has_produtos "
                + "WHERE promocao_idpromocao = ? "
                + "AND produtos_idprodutos = ? "
                + "AND produtos_categorias_idcategorias = ? "
                + "AND produtos_marcas_idmarcas = ? "
                + "AND produtos_unidadeMedidas_idunidadeMedidas = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idPromocao);
            stmt.setInt(2, idProduto);
            stmt.setInt(3, idCategoria);
            stmt.setInt(4, idMarca);
            stmt.setInt(5, idUnidadeMedida);

            stmt.executeUpdate();
        }
    }

    public void excluirTodosPorPromocao(int idPromocao)
            throws SQLException {

        String sql = "DELETE FROM promocao_has_produtos "
                + "WHERE promocao_idpromocao = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idPromocao);
            stmt.executeUpdate();
        }
    }

    private Promocao_has_Produtos mapearRelacao(ResultSet rs)
            throws SQLException {

        Promocao_has_Produtos relacao = new Promocao_has_Produtos();

        relacao.setPromocao_idpromocao(
                rs.getInt("promocao_idpromocao"));

        relacao.setProdutos_idprodutos(
                rs.getInt("produtos_idprodutos"));

        relacao.setProdutos_categorias_idcategorias(
                rs.getInt("produtos_categorias_idcategorias"));

        relacao.setProdutos_marcas_idmarcas(
                rs.getInt("produtos_marcas_idmarcas"));

        relacao.setProdutos_unidadeMedidas_idunidadeMedidas(
                rs.getInt("produtos_unidadeMedidas_idunidadeMedidas"));

        return relacao;
    }
}
