
package Control;

import Conection.Conexao;
import Model.LotesProdutos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LotesProdutosController {

    public void cadastrar(LotesProdutos lote) throws SQLException {
        String sql = "INSERT INTO lotesProdutos "
                + "(numeroLote, dataFabricacao, dataVencimento, "
                + "quantidadeLote, produtos_idprodutos) "
                + "VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, lote.getNumeroLote());
            stmt.setString(2, lote.getDatafabricacao());
            stmt.setString(3, lote.getDataVencimento());
            stmt.setInt(4, lote.getQuantidadeLote());
            stmt.setInt(5, lote.getProdutos_idprodutos());

            stmt.executeUpdate();
        }
    }

    public List<LotesProdutos> listar() throws SQLException {
        List<LotesProdutos> lista = new ArrayList<>();

        String sql = "SELECT * FROM lotesProdutos "
                + "ORDER BY idlotesProdutos DESC";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                lista.add(mapearLote(rs));
            }
        }

        return lista;
    }

    public LotesProdutos buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM lotesProdutos "
                + "WHERE idlotesProdutos = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapearLote(rs);
                }
            }
        }

        return null;
    }

    public List<LotesProdutos> buscarPorProduto(int idProduto)
            throws SQLException {

        List<LotesProdutos> lista = new ArrayList<>();

        String sql = "SELECT * FROM lotesProdutos "
                + "WHERE produtos_idprodutos = ? "
                + "ORDER BY dataVencimento ASC";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idProduto);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapearLote(rs));
                }
            }
        }

        return lista;
    }

    public LotesProdutos buscarPorNumeroLote(String numeroLote)
            throws SQLException {

        String sql = "SELECT * FROM lotesProdutos WHERE numeroLote = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, numeroLote);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapearLote(rs);
                }
            }
        }

        return null;
    }

    public List<LotesProdutos> listarProximosDoVencimento()
            throws SQLException {

        List<LotesProdutos> lista = new ArrayList<>();

        String sql = "SELECT * FROM lotesProdutos "
                + "WHERE dataVencimento >= CURDATE() "
                + "ORDER BY dataVencimento ASC";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                lista.add(mapearLote(rs));
            }
        }

        return lista;
    }

    public List<LotesProdutos> listarVencidos() throws SQLException {
        List<LotesProdutos> lista = new ArrayList<>();

        String sql = "SELECT * FROM lotesProdutos "
                + "WHERE dataVencimento < CURDATE() "
                + "ORDER BY dataVencimento ASC";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                lista.add(mapearLote(rs));
            }
        }

        return lista;
    }

    public void atualizar(LotesProdutos lote) throws SQLException {
        String sql = "UPDATE lotesProdutos SET "
                + "numeroLote = ?, dataFabricacao = ?, "
                + "dataVencimento = ?, quantidadeLote = ?, "
                + "produtos_idprodutos = ? "
                + "WHERE idlotesProdutos = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, lote.getNumeroLote());
            stmt.setString(2, lote.getDatafabricacao());
            stmt.setString(3, lote.getDataVencimento());
            stmt.setInt(4, lote.getQuantidadeLote());
            stmt.setInt(5, lote.getProdutos_idprodutos());
            stmt.setInt(6, lote.getIdlotesProdutos());

            stmt.executeUpdate();
        }
    }

    public void excluir(int id) throws SQLException {
        String sql = "DELETE FROM lotesProdutos "
                + "WHERE idlotesProdutos = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    private LotesProdutos mapearLote(ResultSet rs) throws SQLException {
        LotesProdutos lote = new LotesProdutos();

        lote.setIdlotesProdutos(rs.getInt("idlotesProdutos"));
        lote.setNumeroLote(rs.getString("numeroLote"));
        lote.setDatafabricacao(rs.getString("dataFabricacao"));
        lote.setDataVencimento(rs.getString("dataVencimento"));
        lote.setQuantidadeLote(rs.getInt("quantidadeLote"));
        lote.setProdutos_idprodutos(rs.getInt("produtos_idprodutos"));

        return lote;
    }
}
