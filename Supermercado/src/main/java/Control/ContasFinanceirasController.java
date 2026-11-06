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
import javax.swing.JOptionPane;

/**
 *
 * @author IsraelSantos
 */
public class ContasFinanceirasController {

    // Método para cadastrar uma conta financeira
    public void cadastrar(ContasFinanceiras conta) {

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

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null,
                    "Conta financeira cadastrada com sucesso!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Erro ao cadastrar conta financeira: " + e.getMessage());
        }
    }

    // Método para listar as contas financeiras
    public List<ContasFinanceiras> listar() {

        List<ContasFinanceiras> lista = new ArrayList<>();

        String sql = "SELECT * FROM contasFinanceiras";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                ContasFinanceiras conta = new ContasFinanceiras();

                conta.setIdcontasFinanceiras(
                        rs.getInt("idcontasFinanceiras"));

                conta.setNome(rs.getString("nome"));
                conta.setTipo(rs.getString("tipo"));
                conta.setAgencia(rs.getString("agencia"));
                conta.setNumeroConta(rs.getString("numeroConta"));
                conta.setBanco(rs.getString("banco"));
                conta.setAtivo(rs.getBoolean("ativo"));

                lista.add(conta);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Erro ao listar contas financeiras: " + e.getMessage());
        }

        return lista;
    }

    // Método para atualizar uma conta financeira
    public void atualizar(ContasFinanceiras conta) {

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
            stmt.setInt(7, conta.getIdcontasFinanceiras());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null,
                    "Conta financeira atualizada com sucesso!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Erro ao atualizar conta financeira: " + e.getMessage());
        }
    }

    // Método para excluir uma conta financeira
    public void excluir(int id) {

        String sql = "DELETE FROM contasFinanceiras "
                + "WHERE idcontasFinanceiras = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null,
                    "Conta financeira excluída com sucesso!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Erro ao excluir conta financeira: " + e.getMessage());
        }
    }
}