
package Control;

import Conection.Conexao;
import Model.PerdasEstoque;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PerdasEstoqueController {

// LISTAR PERDAS POR NOME DO PRODUTO
public List<PerdasEstoque> listarPorNomeProduto(String nomeProduto)
        throws SQLException {

    List<PerdasEstoque> lista = new ArrayList<>();

    String sql = "SELECT pe.* "
            + "FROM perdasEstoque pe "
            + "INNER JOIN produtos p "
            + "ON pe.produtos_idprodutos = p.idprodutos "
            + "WHERE p.nome LIKE ? "
            + "ORDER BY pe.idperdasEstoque DESC";

    try (Connection conn = Conexao.conectar();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, "%" + nomeProduto + "%");

        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                lista.add(mapearPerda(rs));
            }
        }
    }

    return lista;
}
    
    public void cadastrar(PerdasEstoque perda) throws SQLException {
        String sql = "INSERT INTO perdasEstoque "
                + "(quantidade, dataRegistro, motivo, "
                + "produtos_idprodutos, produtos_categorias_idcategorias, "
                + "produtos_marcas_idmarcas, "
                + "produtos_unidadeMedidas_idunidadeMedidas, "
                + "lotesProdutos_idlotesProdutos) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, perda.getQuantidade());
            stmt.setString(2, perda.getDataRegistro());
            stmt.setString(3, perda.getMotivo());
            stmt.setInt(4, perda.getProdutos_idprodutos());
            stmt.setInt(5, perda.getProdutos_categorias_idcategorias());
            stmt.setInt(6, perda.getProdutos_marcas_idmarcas());
            stmt.setInt(7, perda.getProdutos_unidadeMedidas_idunidadeMedidas());
            stmt.setInt(8, perda.getLotesProdutos_idlotesProdutos());

            stmt.executeUpdate();
        }
    }

    public List<PerdasEstoque> listar() throws SQLException {
        List<PerdasEstoque> lista = new ArrayList<>();

        String sql = "SELECT * FROM perdasEstoque "
                + "ORDER BY idperdasEstoque DESC";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                lista.add(mapearPerda(rs));
            }
        }

        return lista;
    }

    public PerdasEstoque buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM perdasEstoque "
                + "WHERE idperdasEstoque = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapearPerda(rs);
                }
            }
        }

        return null;
    }

    public List<PerdasEstoque> buscarPorProduto(int idProduto)
            throws SQLException {

        List<PerdasEstoque> lista = new ArrayList<>();

        String sql = "SELECT * FROM perdasEstoque "
                + "WHERE produtos_idprodutos = ? "
                + "ORDER BY idperdasEstoque DESC";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idProduto);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapearPerda(rs));
                }
            }
        }

        return lista;
    }

    public List<PerdasEstoque> buscarPorLote(int idLote)
            throws SQLException {

        List<PerdasEstoque> lista = new ArrayList<>();

        String sql = "SELECT * FROM perdasEstoque "
                + "WHERE lotesProdutos_idlotesProdutos = ? "
                + "ORDER BY idperdasEstoque DESC";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idLote);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapearPerda(rs));
                }
            }
        }

        return lista;
    }

    public List<PerdasEstoque> buscarPorMotivo(String motivo)
            throws SQLException {

        List<PerdasEstoque> lista = new ArrayList<>();

        String sql = "SELECT * FROM perdasEstoque "
                + "WHERE motivo LIKE ? "
                + "ORDER BY idperdasEstoque DESC";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + motivo + "%");

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapearPerda(rs));
                }
            }
        }

        return lista;
    }

    public List<PerdasEstoque> buscarPorData(String data)
            throws SQLException {

        List<PerdasEstoque> lista = new ArrayList<>();

        String sql = "SELECT * FROM perdasEstoque "
                + "WHERE dataRegistro = ? "
                + "ORDER BY idperdasEstoque DESC";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, data);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapearPerda(rs));
                }
            }
        }

        return lista;
    }

    public void atualizar(PerdasEstoque perda) throws SQLException {
        String sql = "UPDATE perdasEstoque SET "
                + "quantidade = ?, dataRegistro = ?, motivo = ?, "
                + "produtos_idprodutos = ?, "
                + "produtos_categorias_idcategorias = ?, "
                + "produtos_marcas_idmarcas = ?, "
                + "produtos_unidadeMedidas_idunidadeMedidas = ?, "
                + "lotesProdutos_idlotesProdutos = ? "
                + "WHERE idperdasEstoque = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, perda.getQuantidade());
            stmt.setString(2, perda.getDataRegistro());
            stmt.setString(3, perda.getMotivo());
            stmt.setInt(4, perda.getProdutos_idprodutos());
            stmt.setInt(5, perda.getProdutos_categorias_idcategorias());
            stmt.setInt(6, perda.getProdutos_marcas_idmarcas());
            stmt.setInt(7, perda.getProdutos_unidadeMedidas_idunidadeMedidas());
            stmt.setInt(8, perda.getLotesProdutos_idlotesProdutos());
            stmt.setInt(9, perda.getIdperdasEstoque());

            stmt.executeUpdate();
        }
    }

    public void excluir(int id) throws SQLException {
        String sql = "DELETE FROM perdasEstoque "
                + "WHERE idperdasEstoque = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    private PerdasEstoque mapearPerda(ResultSet rs)
            throws SQLException {

        PerdasEstoque perda = new PerdasEstoque();

        perda.setIdperdasEstoque(rs.getInt("idperdasEstoque"));
        perda.setQuantidade(rs.getInt("quantidade"));
        perda.setDataRegistro(rs.getString("dataRegistro"));
        perda.setMotivo(rs.getString("motivo"));
        perda.setProdutos_idprodutos(rs.getInt("produtos_idprodutos"));
        perda.setProdutos_categorias_idcategorias(
                rs.getInt("produtos_categorias_idcategorias"));
        perda.setProdutos_marcas_idmarcas(
                rs.getInt("produtos_marcas_idmarcas"));
        perda.setProdutos_unidadeMedidas_idunidadeMedidas(
                rs.getInt("produtos_unidadeMedidas_idunidadeMedidas"));
        perda.setLotesProdutos_idlotesProdutos(
                rs.getInt("lotesProdutos_idlotesProdutos"));

        return perda;
    }
}
