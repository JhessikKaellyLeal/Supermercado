/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import Conection.Conexao;
import Model.PerdasEstoque;
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
public class PerdasEstoqueController {
    // CADASTRAR PERDA DE ESTOQUE
    public boolean cadastrar(PerdasEstoque perda) {
        String sql = "INSERT INTO perdasEstoque "
                + "(quantidade, dataRegistro, motivo, "
                + "lotesProduto_idlotesProduto, produtos_idprodutos) "
                + "VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, perda.getQuantidade());
            stmt.setString(2, perda.getDataRegistro());
            stmt.setString(3, perda.getMotivo());
            stmt.setInt(4, perda.getLotesProdutos_idlotesProdutos());
            stmt.setInt(5, perda.getProdutos_idprodutos());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar perda de estoque: "
                    + e.getMessage());
            return false;
        }
    }

    // LISTAR TODAS AS PERDAS DE ESTOQUE
    public List<PerdasEstoque> listarTodos() {
        List<PerdasEstoque> perdas = new ArrayList<>();

        String sql = "SELECT idperdasEstoque, quantidade, dataRegistro, "
                + "motivo, lotesProduto_idlotesProduto, produtos_idprodutos "
                + "FROM perdasEstoque";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                PerdasEstoque perda = new PerdasEstoque();

                perda.setIdperdasEstoque(
                        rs.getInt("idperdasEstoque"));
                perda.setQuantidade(
                        rs.getInt("quantidade"));
                perda.setDataRegistro(
                        rs.getString("dataRegistro"));
                perda.setMotivo(
                        rs.getString("motivo"));

                perda.setLotesProdutos_idlotesProdutos(
                        rs.getInt("lotesProduto_idlotesProduto"));

                perda.setProdutos_idprodutos(
                        rs.getInt("produtos_idprodutos"));

                perdas.add(perda);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar perdas de estoque: "
                    + e.getMessage());
        }

        return perdas;
    }

    // BUSCAR PERDA DE ESTOQUE PELO ID
    public PerdasEstoque buscarPorId(int id) {
        String sql = "SELECT idperdasEstoque, quantidade, dataRegistro, "
                + "motivo, lotesProduto_idlotesProduto, produtos_idprodutos "
                + "FROM perdasEstoque "
                + "WHERE idperdasEstoque = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    PerdasEstoque perda = new PerdasEstoque();

                    perda.setIdperdasEstoque(
                            rs.getInt("idperdasEstoque"));
                    perda.setQuantidade(
                            rs.getInt("quantidade"));
                    perda.setDataRegistro(
                            rs.getString("dataRegistro"));
                    perda.setMotivo(
                            rs.getString("motivo"));

                    perda.setLotesProdutos_idlotesProdutos(
                            rs.getInt("lotesProduto_idlotesProduto"));

                    perda.setProdutos_idprodutos(
                            rs.getInt("produtos_idprodutos"));

                    return perda;
                }
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar perda de estoque: "
                    + e.getMessage());
        }

        return null;
    }

    // ATUALIZAR PERDA DE ESTOQUE
    public boolean atualizar(int idOriginal, PerdasEstoque perda) {
        String sql = "UPDATE perdasEstoque SET "
                + "quantidade = ?, dataRegistro = ?, motivo = ?, "
                + "lotesProduto_idlotesProduto = ?, produtos_idprodutos = ? "
                + "WHERE idperdasEstoque = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, perda.getQuantidade());
            stmt.setString(2, perda.getDataRegistro());
            stmt.setString(3, perda.getMotivo());
            stmt.setInt(4, perda.getLotesProdutos_idlotesProdutos());
            stmt.setInt(5, perda.getProdutos_idprodutos());
            stmt.setInt(6, idOriginal);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar perda de estoque: "
                    + e.getMessage());
            return false;
        }
    }

    // EXCLUIR PERDA DE ESTOQUE
    public boolean excluir(int id) {
        String sql = "DELETE FROM perdasEstoque "
                + "WHERE idperdasEstoque = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao excluir perda de estoque: "
                    + e.getMessage());
            return false;
        }
    }
}
