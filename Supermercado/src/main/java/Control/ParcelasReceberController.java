/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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

/**
 *
 * @author GeovannaOliviera
 */
public class ParcelasReceberController {
 public boolean cadastrar(ParcelasReceber parcela) {

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

            if (parcela.getValorRecebido() == 0) {
                stmt.setNull(5, Types.DOUBLE);
            } else {
                stmt.setDouble(5, parcela.getValorRecebido());
            }

            stmt.setString(6, parcela.getStatusParcela());
            stmt.setString(7, parcela.getObservacao());
            stmt.setInt(8, parcela.getContasReceber_idcontasReceber());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar parcela a receber: "
                    + e.getMessage());
            return false;
        }
    }

    // LISTAR TODAS AS PARCELAS
    public List<ParcelasReceber> listarTodos() {

        List<ParcelasReceber> lista = new ArrayList<>();

        String sql = "SELECT * FROM parcelasReceber";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                lista.add(mapearParcela(rs));
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar parcelas a receber: "
                    + e.getMessage());
        }

        return lista;
    }

    // BUSCAR PARCELA PELO ID
    public ParcelasReceber buscarPorId(int idparcelasReceber) {

        String sql = "SELECT * FROM parcelasReceber "
                + "WHERE idparcelasReceber = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idparcelasReceber);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapearParcela(rs);
                }
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar parcela: "
                    + e.getMessage());
        }

        return null;
    }

    // LISTAR PARCELAS DE UMA CONTA A RECEBER
    public List<ParcelasReceber> buscarPorContaReceber(
            int idcontasReceber) {

        List<ParcelasReceber> lista = new ArrayList<>();

        String sql = "SELECT * FROM parcelasReceber "
                + "WHERE contasReceber_idcontasReceber = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idcontasReceber);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapearParcela(rs));
                }
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar parcelas da conta: "
                    + e.getMessage());
        }

        return lista;
    }

    // ATUALIZAR PARCELA
    public boolean atualizar(
            int idparcelasReceber,
            ParcelasReceber parcela) {

        String sql = "UPDATE parcelasReceber SET "
                + "numeroParcela = ?, "
                + "valorParcela = ?, "
                + "dataVencimento = ?, "
                + "dataPagamento = ?, "
                + "valorRecebido = ?, "
                + "statusParcela = ?, "
                + "observacao = ?, "
                + "contasReceber_idcontasReceber = ? "
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

            if (parcela.getValorRecebido() == 0) {
                stmt.setNull(5, Types.DOUBLE);
            } else {
                stmt.setDouble(5, parcela.getValorRecebido());
            }

            stmt.setString(6, parcela.getStatusParcela());
            stmt.setString(7, parcela.getObservacao());
            stmt.setInt(8, parcela.getContasReceber_idcontasReceber());
            stmt.setInt(9, idparcelasReceber);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar parcela: "
                    + e.getMessage());
            return false;
        }
    }

    // EXCLUIR PARCELA
    public boolean excluir(int idparcelasReceber) {

        String sql = "DELETE FROM parcelasReceber "
                + "WHERE idparcelasReceber = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idparcelasReceber);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao excluir parcela: "
                    + e.getMessage());
            return false;
        }
    }

    // CONVERTER RESULTADO DO BANCO EM OBJETO
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

        if (rs.wasNull()) {
            parcela.setValorRecebido(null);
        } else {
            parcela.setValorRecebido(valorRecebido);
        }

        parcela.setStatusParcela(
                rs.getString("statusParcela"));

        parcela.setObservacao(
                rs.getString("observacao"));

        parcela.setContasReceber_idcontasReceber(
                rs.getInt("contasReceber_idcontasReceber"));

        return parcela;
    }    
}
