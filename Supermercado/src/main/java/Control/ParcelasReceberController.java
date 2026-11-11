
package Control;

import Conection.Conexao;
import Model.ParcelasReceber;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class ParcelasReceberController {

    public void cadastrar(ParcelasReceber parcela) throws SQLException {
        String sql = "INSERT INTO parcelasReceber "
                + "(numeroParcela, valorParcela, dataVencimento, "
                + "dataPagamento, valorRecebido, statusParcela, "
                + "observacao, contasReceber_idcontasReceber) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, parcela.getNumeroParcela());
            stmt.setDouble(2, parcela.getValorParcela());
            stmt.setString(3, parcela.getDataVencimento());

            if (parcela.getDataPagamento() == null) {
                stmt.setNull(4, Types.VARCHAR);
            } else {
                stmt.setString(4, parcela.getDataPagamento());
            }

            if (parcela.getValorRecebido()== null) {
                stmt.setNull(5, Types.DOUBLE);
            } else {
                stmt.setDouble(5, parcela.getValorRecebido());
            }

            stmt.setString(6, parcela.getStatusParcela());
            stmt.setString(7, parcela.getObservacao());
            stmt.setInt(8, parcela.getContasReceber_idcontasReceber());

            stmt.executeUpdate();
        }
    }

    public List<ParcelasReceber> listar() throws SQLException {
        List<ParcelasReceber> lista = new ArrayList<>();

        String sql = "SELECT * FROM parcelasReceber "
                + "ORDER BY dataVencimento ASC";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                lista.add(mapearParcela(rs));
            }
        }

        return lista;
    }

    public ParcelasReceber buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM parcelasReceber "
                + "WHERE idparcelasReceber = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapearParcela(rs);
                }
            }
        }

        return null;
    }

    public List<ParcelasReceber> buscarPorContaReceber(int idConta)
            throws SQLException {

        List<ParcelasReceber> lista = new ArrayList<>();

        String sql = "SELECT * FROM parcelasReceber "
                + "WHERE contasReceber_idcontasReceber = ? "
                + "ORDER BY dataVencimento ASC";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idConta);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapearParcela(rs));
                }
            }
        }

        return lista;
    }

    public List<ParcelasReceber> buscarPorStatus(String status)
            throws SQLException {

        List<ParcelasReceber> lista = new ArrayList<>();

        String sql = "SELECT * FROM parcelasReceber "
                + "WHERE statusParcela = ? "
                + "ORDER BY dataVencimento ASC";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, status);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapearParcela(rs));
                }
            }
        }

        return lista;
    }

    public List<ParcelasReceber> buscarPorVencimento(String data)
            throws SQLException {

        List<ParcelasReceber> lista = new ArrayList<>();

        String sql = "SELECT * FROM parcelasReceber "
                + "WHERE dataVencimento = ? "
                + "ORDER BY dataVencimento ASC";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, data);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapearParcela(rs));
                }
            }
        }

        return lista;
    }

    public List<ParcelasReceber> listarEmAberto() throws SQLException {
        List<ParcelasReceber> lista = new ArrayList<>();

        String sql = "SELECT * FROM parcelasReceber "
                + "WHERE statusParcela = 'Pendente' "
                + "ORDER BY dataVencimento ASC";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                lista.add(mapearParcela(rs));
            }
        }

        return lista;
    }

    public void atualizar(ParcelasReceber parcela) throws SQLException {
        String sql = "UPDATE parcelasReceber SET "
                + "numeroParcela = ?, valorParcela = ?, "
                + "dataVencimento = ?, dataPagamento = ?, "
                + "valorRecebido = ?, statusParcela = ?, "
                + "observacao = ?, contasReceber_idcontasReceber = ? "
                + "WHERE idparcelasReceber = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, parcela.getNumeroParcela());
            stmt.setDouble(2, parcela.getValorParcela());
            stmt.setString(3, parcela.getDataVencimento());

            if (parcela.getDataPagamento() == null) {
                stmt.setNull(4, Types.VARCHAR);
            } else {
                stmt.setString(4, parcela.getDataPagamento());
            }

            if (parcela.getValorRecebido() == null) {
                stmt.setNull(5, Types.DOUBLE);
            } else {
                stmt.setDouble(5, parcela.getValorRecebido());
            }

            stmt.setString(6, parcela.getStatusParcela());
            stmt.setString(7, parcela.getObservacao());
            stmt.setInt(8, parcela.getContasReceber_idcontasReceber());
            stmt.setInt(9, parcela.getIdparcelasReceber());

            stmt.executeUpdate();
        }
    }

    public void registrarRecebimento(int id, String dataPagamento,
            double valorRecebido) throws SQLException {

        String sql = "UPDATE parcelasReceber SET "
                + "dataPagamento = ?, valorRecebido = ?, "
                + "statusParcela = 'Recebida' "
                + "WHERE idparcelasReceber = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, dataPagamento);
            stmt.setDouble(2, valorRecebido);
            stmt.setInt(3, id);

            stmt.executeUpdate();
        }
    }

    public void excluir(int id) throws SQLException {
        String sql = "DELETE FROM parcelasReceber "
                + "WHERE idparcelasReceber = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    private ParcelasReceber mapearParcela(ResultSet rs)
            throws SQLException {

        ParcelasReceber parcela = new ParcelasReceber();

        parcela.setIdparcelasReceber(
                rs.getInt("idparcelasReceber"));

        parcela.setNumeroParcela(
                rs.getInt("numeroParcela"));

        parcela.setValorParcela(
                rs.getDouble("valorParcela"));

        parcela.setDataVencimento(
                rs.getString("dataVencimento"));

        parcela.setDataPagamento(
                rs.getString("dataPagamento"));

        double valorRecebido = rs.getDouble("valorRecebido");
        parcela.setValorRecebido(
                rs.wasNull() ? null : valorRecebido);

        parcela.setStatusParcela(
                rs.getString("statusParcela"));

        parcela.setObservacao(
                rs.getString("observacao"));

        parcela.setContasReceber_idcontasReceber(
                rs.getInt("contasReceber_idcontasReceber"));

        return parcela;
    }
}
