/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control;

import Conection.Conexao;
import Model.FormasPagamento;
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
public class FormasPagamentoController {

    // Método para cadastrar uma forma de pagamento
    public void cadastrar(FormasPagamento forma) {
        String sql = "INSERT INTO formasPagamento (nome) VALUES (?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, forma.getNome());
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null,
                    "Forma de pagamento cadastrada com sucesso!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Erro ao cadastrar forma de pagamento: " + e.getMessage());
        }
    }

    // Método para listar as formas de pagamento
    public List<FormasPagamento> listar() {
        List<FormasPagamento> lista = new ArrayList<>();

        String sql = "SELECT * FROM formasPagamento";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                FormasPagamento forma = new FormasPagamento();

                forma.setIdformasPagamento(rs.getInt("idformasPagamento"));
                forma.setNome(rs.getString("nome"));

                lista.add(forma);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Erro ao listar formas de pagamento: " + e.getMessage());
        }

        return lista;
    }

    // Método para atualizar uma forma de pagamento
    public void atualizar(FormasPagamento forma) {
        String sql = "UPDATE formasPagamento SET nome = ? "
                   + "WHERE idformasPagamento = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, forma.getNome());
            stmt.setInt(2, forma.getIdformasPagamento());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null,
                    "Forma de pagamento atualizada com sucesso!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Erro ao atualizar forma de pagamento: " + e.getMessage());
        }
    }

    // Método para excluir uma forma de pagamento
    public void excluir(int id) {
        String sql = "DELETE FROM formasPagamento "
                   + "WHERE idformasPagamento = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null,
                    "Forma de pagamento excluída com sucesso!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Erro ao excluir forma de pagamento: " + e.getMessage());
        }
    }
}
