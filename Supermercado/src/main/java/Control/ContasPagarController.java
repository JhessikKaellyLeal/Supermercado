/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import Conection.Conexao;
import Model.ContasPagar;
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
public class ContasPagarController {
    // CADASTRAR CONTA A PAGAR
    public boolean cadastrar(ContasPagar conta) {
        String sql = "INSERT INTO contasPagar "
                + "(valor, descricao, dataVencimento, dataPagamento, "
                + "situacao, fornecedores_idfornecedores) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDouble(1, conta.getValor());
            stmt.setString(2, conta.getDecricao());
            stmt.setString(3, conta.getDataVencimento());
            stmt.setString(4, conta.getDataPagamento());
            stmt.setString(5, conta.getSituacao());
            stmt.setInt(6, conta.getFornecedores_idfornecedores());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar conta a pagar: "
                    + e.getMessage());
            return false;
        }
    }

    // LISTAR TODAS AS CONTAS A PAGAR
    public List<ContasPagar> listarTodos() {
        List<ContasPagar> contas = new ArrayList<>();

        String sql = "SELECT idcontasPaga, valor, descricao, "
                + "dataVencimento, dataPagamento, situacao, "
                + "fornecedores_idfornecedores "
                + "FROM contasPagar";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                ContasPagar conta = new ContasPagar();

                conta.setIdcontasPagar(
                        rs.getInt("idcontasPaga"));
                conta.setValor(
                        rs.getDouble("valor"));
                conta.setDecricao(
                        rs.getString("descricao"));
                conta.setDataVencimento(
                        rs.getString("dataVencimento"));
                conta.setDataPagamento(
                        rs.getString("dataPagamento"));
                conta.setSituacao(
                        rs.getString("situacao"));
                conta.setFornecedores_idfornecedores(
                        rs.getInt("fornecedores_idfornecedores"));

                contas.add(conta);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar contas a pagar: "
                    + e.getMessage());
        }

        return contas;
    }

    // BUSCAR CONTA A PAGAR PELO ID
    public ContasPagar buscarPorId(int id) {
        String sql = "SELECT idcontasPaga, valor, descricao, "
                + "dataVencimento, dataPagamento, situacao, "
                + "fornecedores_idfornecedores "
                + "FROM contasPagar "
                + "WHERE idcontasPaga = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    ContasPagar conta = new ContasPagar();

                    conta.setIdcontasPagar(
                            rs.getInt("idcontasPaga"));
                    conta.setValor(
                            rs.getDouble("valor"));
                    conta.setDecricao(
                            rs.getString("descricao"));
                    conta.setDataVencimento(
                            rs.getString("dataVencimento"));
                    conta.setDataPagamento(
                            rs.getString("dataPagamento"));
                    conta.setSituacao(
                            rs.getString("situacao"));
                    conta.setFornecedores_idfornecedores(
                            rs.getInt("fornecedores_idfornecedores"));

                    return conta;
                }
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar conta a pagar: "
                    + e.getMessage());
        }

        return null;
    }

    // ATUALIZAR CONTA A PAGAR
    public boolean atualizar(int idOriginal, ContasPagar conta) {
        String sql = "UPDATE contasPagar SET "
                + "valor = ?, descricao = ?, dataVencimento = ?, "
                + "dataPagamento = ?, situacao = ?, "
                + "fornecedores_idfornecedores = ? "
                + "WHERE idcontasPaga = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDouble(1, conta.getValor());
            stmt.setString(2, conta.getDecricao());
            stmt.setString(3, conta.getDataVencimento());
            stmt.setString(4, conta.getDataPagamento());
            stmt.setString(5, conta.getSituacao());
            stmt.setInt(6, conta.getFornecedores_idfornecedores());
            stmt.setInt(7, idOriginal);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar conta a pagar: "
                    + e.getMessage());
            return false;
        }
    }

    // EXCLUIR CONTA A PAGAR
    public boolean excluir(int id) {
        String sql = "DELETE FROM contasPagar "
                + "WHERE idcontasPaga = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao excluir conta a pagar: "
                    + e.getMessage());
            return false;
        }
    }
}
