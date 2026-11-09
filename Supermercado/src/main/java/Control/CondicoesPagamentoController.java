/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control;

import Conection.Conexao;
import Model.CondicoesPagamento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author JordanaLeite
 */
    // Método para cadastrar uma condição de pagamento

public class CondicoesPagamentoController{

    public void cadastrar(CondicoesPagamento condicao) {

        String sql = "INSERT INTO condicoesPagamento "
                + "(quantidadeParcelas, intervaloDias) "
                + "VALUES (?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, condicao.getQuantidadeParcelas());
            stmt.setInt(2, condicao.getIntervaloDias());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null,
                    "Condição de pagamento cadastrada com sucesso!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Erro ao cadastrar condição de pagamento: "
                    + e.getMessage());
        }
    }

    // Método para listar as condições de pagamento
    public List<CondicoesPagamento> listar() {

        List<CondicoesPagamento> lista = new ArrayList<>();

        String sql = "SELECT * FROM condicoesPagamento";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                CondicoesPagamento condicao = new CondicoesPagamento();

                condicao.setIdcondicoesPagamento(
                        rs.getInt("idcondicoesPagamento"));

                condicao.setQuantidadeParcelas(
                        rs.getInt("quantidadeParcelas"));

                condicao.setIntervaloDias(
                        rs.getInt("intervaloDias"));

                lista.add(condicao);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Erro ao listar condições de pagamento: "
                    + e.getMessage());
        }

        return lista;
    }

    // Método para atualizar uma condição de pagamento
    public void atualizar(CondicoesPagamento condicao) {

        String sql = "UPDATE condicoesPagamento SET "
                + "quantidadeParcelas = ?, intervaloDias = ? "
                + "WHERE idcondicoesPagamento = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, condicao.getQuantidadeParcelas());
            stmt.setInt(2, condicao.getIntervaloDias());
            stmt.setInt(3, condicao.getIdcondicoesPagamento());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null,
                    "Condição de pagamento atualizada com sucesso!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Erro ao atualizar condição de pagamento: "
                    + e.getMessage());
        }
    }

    // Método para excluir uma condição de pagamento
    public void excluir(int id) {

        String sql = "DELETE FROM condicoesPagamento "
                + "WHERE idcondicoesPagamento = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null,
                    "Condição de pagamento excluída com sucesso!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Erro ao excluir condição de pagamento: "
                    + e.getMessage());
        }
    }
}