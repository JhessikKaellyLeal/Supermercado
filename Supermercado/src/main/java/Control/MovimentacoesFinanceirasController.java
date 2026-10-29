/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import Conection.Conexao;
import Model.MovimentacoesFinanceiras;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author IsraelSantos
 */
public class MovimentacoesFinanceirasController {
    // CADASTRAR MOVIMENTAÇÃO FINANCEIRA
    public boolean cadastrar(MovimentacoesFinanceiras movimentacao) {
        String sql = "INSERT INTO movimentacoesFinanceira "
                + "(tipo, valor, dataHora, descricao, origem, "
                + "contasFinanceiras_idcontasFinanceiras) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, movimentacao.getTipo());
            stmt.setDouble(2, movimentacao.getValor());
            stmt.setString(3, movimentacao.getDataHora());
            stmt.setString(4, movimentacao.getDescricao());
            stmt.setString(5, movimentacao.getOrigem());
            stmt.setInt(6, movimentacao.getContasFinanceiras_idcontasFinanceiras());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar movimentação financeira: "
                    + e.getMessage());
            return false;
        }
    }

    // LISTAR TODAS AS MOVIMENTAÇÕES FINANCEIRAS
    public List<MovimentacoesFinanceiras> listarTodos() {
        List<MovimentacoesFinanceiras> movimentacoes = new ArrayList<>();

        String sql = "SELECT idmovimentacoesFinanceira, tipo, valor, "
                + "dataHora, descricao, origem, "
                + "contasFinanceiras_idcontasFinanceiras "
                + "FROM movimentacoesFinanceira";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                MovimentacoesFinanceiras movimentacao =
                        new MovimentacoesFinanceiras();

                movimentacao.setIdmovimentacoesFinanceiras(
                        rs.getInt("idmovimentacoesFinanceira"));
                movimentacao.setTipo(rs.getString("tipo"));
                movimentacao.setValor(rs.getDouble("valor"));
                movimentacao.setDataHora(rs.getString("dataHora"));
                movimentacao.setDescricao(rs.getString("descricao"));
                movimentacao.setOrigem(rs.getString("origem"));
                movimentacao.setContasFinanceiras_idcontasFinanceiras(
                        rs.getInt("contasFinanceiras_idcontasFinanceiras"));

                movimentacoes.add(movimentacao);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar movimentações financeiras: "
                    + e.getMessage());
        }

        return movimentacoes;
    }

    // BUSCAR MOVIMENTAÇÃO FINANCEIRA POR ID
    public MovimentacoesFinanceiras buscarPorId(int id) {
        String sql = "SELECT idmovimentacoesFinanceira, tipo, valor, "
                + "dataHora, descricao, origem, "
                + "contasFinanceiras_idcontasFinanceiras "
                + "FROM movimentacoesFinanceira "
                + "WHERE idmovimentacoesFinanceira = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    MovimentacoesFinanceiras movimentacao =
                            new MovimentacoesFinanceiras();

                    movimentacao.setIdmovimentacoesFinanceiras(
                            rs.getInt("idmovimentacoesFinanceira"));
                    movimentacao.setTipo(rs.getString("tipo"));
                    movimentacao.setValor(rs.getDouble("valor"));
                    movimentacao.setDataHora(rs.getString("dataHora"));
                    movimentacao.setDescricao(rs.getString("descricao"));
                    movimentacao.setOrigem(rs.getString("origem"));
                    movimentacao.setContasFinanceiras_idcontasFinanceiras(
                            rs.getInt("contasFinanceiras_idcontasFinanceiras"));

                    return movimentacao;
                }
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar movimentação financeira: "
                    + e.getMessage());
        }

        return null;
    }

    // ATUALIZAR MOVIMENTAÇÃO FINANCEIRA
    public boolean atualizar(int idOriginal,
            MovimentacoesFinanceiras movimentacao) {

        String sql = "UPDATE movimentacoesFinanceira SET "
                + "tipo = ?, valor = ?, dataHora = ?, descricao = ?, "
                + "origem = ?, contasFinanceiras_idcontasFinanceiras = ? "
                + "WHERE idmovimentacoesFinanceira = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, movimentacao.getTipo());
            stmt.setDouble(2, movimentacao.getValor());
            stmt.setString(3, movimentacao.getDataHora());
            stmt.setString(4, movimentacao.getDescricao());
            stmt.setString(5, movimentacao.getOrigem());
            stmt.setInt(6, movimentacao.getContasFinanceiras_idcontasFinanceiras());
            stmt.setInt(7, idOriginal);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar movimentação financeira: "
                    + e.getMessage());
            return false;
        }
    }

    // EXCLUIR MOVIMENTAÇÃO FINANCEIRA
    public boolean excluir(int id) {
        String sql = "DELETE FROM movimentacoesFinanceira "
                + "WHERE idmovimentacoesFinanceira = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao excluir movimentação financeira: "
                    + e.getMessage());
            return false;
        }
    }
}
