/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import Conection.Conexao;
import Model.ContasFinanceiras;
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
public class ContasFinanceirasController {
    // CADASTRAR CONTA FINANCEIRA
    public boolean cadastrar(ContasFinanceiras conta) {
        String sql = "INSERT INTO contasFinanceiras "
                + "(nome, tipo, agencia, numeroConta, banco, ativo) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, conta.getNome());
            stmt.setString(2, conta.getTipo());
            stmt.setString(3, conta.getAgencia());
            stmt.setString(4, conta.getNumeroConta());
            stmt.setString(5, conta.getBanco());
            stmt.setBoolean(6, conta.isAtivo());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar conta financeira: "
                    + e.getMessage());
            return false;
        }
    }

    // LISTAR TODAS AS CONTAS FINANCEIRAS
    public List<ContasFinanceiras> listarTodos() {
        List<ContasFinanceiras> contas = new ArrayList<>();

        String sql = "SELECT idcontasFinanceiras, nome, tipo, agencia, "
                + "numeroConta, banco, ativo "
                + "FROM contasFinanceiras";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                ContasFinanceiras conta = new ContasFinanceiras();

                conta.setIdcontasFinanceiras(
                        rs.getInt("idcontasFinanceiras"));
                conta.setNome(
                        rs.getString("nome"));
                conta.setTipo(
                        rs.getString("tipo"));
                conta.setAgencia(
                        rs.getString("agencia"));
                conta.setNumeroConta(
                        rs.getString("numeroConta"));
                conta.setBanco(
                        rs.getString("banco"));
                conta.setAtivo(
                        rs.getBoolean("ativo"));

                contas.add(conta);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar contas financeiras: "
                    + e.getMessage());
        }

        return contas;
    }

    // BUSCAR CONTA FINANCEIRA PELO ID
    public ContasFinanceiras buscarPorId(int id) {
        String sql = "SELECT idcontasFinanceiras, nome, tipo, agencia, "
                + "numeroConta, banco, ativo "
                + "FROM contasFinanceiras "
                + "WHERE idcontasFinanceiras = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    ContasFinanceiras conta = new ContasFinanceiras();

                    conta.setIdcontasFinanceiras(
                            rs.getInt("idcontasFinanceiras"));
                    conta.setNome(
                            rs.getString("nome"));
                    conta.setTipo(
                            rs.getString("tipo"));
                    conta.setAgencia(
                            rs.getString("agencia"));
                    conta.setNumeroConta(
                            rs.getString("numeroConta"));
                    conta.setBanco(
                            rs.getString("banco"));
                    conta.setAtivo(
                            rs.getBoolean("ativo"));

                    return conta;
                }
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar conta financeira: "
                    + e.getMessage());
        }

        return null;
    }

    // ATUALIZAR CONTA FINANCEIRA
    public boolean atualizar(int idOriginal, ContasFinanceiras conta) {
        String sql = "UPDATE contasFinanceiras SET "
                + "nome = ?, tipo = ?, agencia = ?, numeroConta = ?, "
                + "banco = ?, ativo = ? "
                + "WHERE idcontasFinanceiras = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, conta.getNome());
            stmt.setString(2, conta.getTipo());
            stmt.setString(3, conta.getAgencia());
            stmt.setString(4, conta.getNumeroConta());
            stmt.setString(5, conta.getBanco());
            stmt.setBoolean(6, conta.isAtivo());
            stmt.setInt(7, idOriginal);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar conta financeira: "
                    + e.getMessage());
            return false;
        }
    }

    // EXCLUIR CONTA FINANCEIRA
    public boolean excluir(int id) {
        String sql = "DELETE FROM contasFinanceiras "
                + "WHERE idcontasFinanceiras = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao excluir conta financeira: "
                    + e.getMessage());
            return false;
        }
    }
}
