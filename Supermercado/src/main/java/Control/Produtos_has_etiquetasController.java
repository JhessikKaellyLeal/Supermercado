
package Control;

import Conection.Conexao;
import Model.Produtos_has_Etiquetas;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Produtos_has_etiquetasController {

    public void cadastrar(Produtos_has_Etiquetas relacao)
            throws SQLException {

        String sql = "INSERT INTO produtos_has_etiquetas "
                + "(produtos_idprodutos, "
                + "produtos_categorias_idcategorias, "
                + "produtos_marcas_idmarcas, "
                + "produtos_unidadeMedidas_idunidadeMedidas, "
                + "etiquetas_idetiquetas) "
                + "VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, relacao.getProdutos_idprodutos());
            stmt.setInt(2, relacao.getProdutos_categorias_idcategorias());
            stmt.setInt(3, relacao.getProdutos_marcas_idmarcas());
            stmt.setInt(4, relacao.getProdutos_unidadeMedidas_idunidadeMedidas());
            stmt.setInt(5, relacao.getEtiquetas_idetiquetas());

            stmt.executeUpdate();
        }
    }

    public List<Produtos_has_Etiquetas> listar() throws SQLException {
        List<Produtos_has_Etiquetas> lista = new ArrayList<>();

        String sql = "SELECT * FROM produtos_has_etiquetas";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                lista.add(mapearRelacao(rs));
            }
        }

        return lista;
    }

    public List<Produtos_has_Etiquetas> buscarPorProduto(int idProduto)
            throws SQLException {

        List<Produtos_has_Etiquetas> lista = new ArrayList<>();

        String sql = "SELECT * FROM produtos_has_etiquetas "
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

    public List<Produtos_has_Etiquetas> buscarPorEtiqueta(int idEtiqueta)
            throws SQLException {

        List<Produtos_has_Etiquetas> lista = new ArrayList<>();

        String sql = "SELECT * FROM produtos_has_etiquetas "
                + "WHERE etiquetas_idetiquetas = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idEtiqueta);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapearRelacao(rs));
                }
            }
        }

        return lista;
    }

    public boolean existeRelacao(int idProduto, int idEtiqueta)
            throws SQLException {

        String sql = "SELECT 1 FROM produtos_has_etiquetas "
                + "WHERE produtos_idprodutos = ? "
                + "AND etiquetas_idetiquetas = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idProduto);
            stmt.setInt(2, idEtiqueta);

            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        }
    }

    public void excluir(int idProduto, int idCategoria,
            int idMarca, int idUnidadeMedida, int idEtiqueta)
            throws SQLException {

        String sql = "DELETE FROM produtos_has_etiquetas "
                + "WHERE produtos_idprodutos = ? "
                + "AND produtos_categorias_idcategorias = ? "
                + "AND produtos_marcas_idmarcas = ? "
                + "AND produtos_unidadeMedidas_idunidadeMedidas = ? "
                + "AND etiquetas_idetiquetas = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idProduto);
            stmt.setInt(2, idCategoria);
            stmt.setInt(3, idMarca);
            stmt.setInt(4, idUnidadeMedida);
            stmt.setInt(5, idEtiqueta);

            stmt.executeUpdate();
        }
    }

    public void excluirTodasPorProduto(int idProduto)
            throws SQLException {

        String sql = "DELETE FROM produtos_has_etiquetas "
                + "WHERE produtos_idprodutos = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idProduto);
            stmt.executeUpdate();
        }
    }

    private Produtos_has_Etiquetas mapearRelacao(ResultSet rs)
            throws SQLException {

        Produtos_has_Etiquetas relacao = new Produtos_has_Etiquetas();

        relacao.setProdutos_idprodutos(
                rs.getInt("produtos_idprodutos"));

        relacao.setProdutos_categorias_idcategorias(
                rs.getInt("produtos_categorias_idcategorias"));

        relacao.setProdutos_marcas_idmarcas(
                rs.getInt("produtos_marcas_idmarcas"));

        relacao.setProdutos_unidadeMedidas_idunidadeMedidas(
                rs.getInt("produtos_unidadeMedidas_idunidadeMedidas"));

        relacao.setEtiquetas_idetiquetas(
                rs.getInt("etiquetas_idetiquetas"));

        return relacao;
    }
}
