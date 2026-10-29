/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import Conection.Conexao;
import Model.ItensCompras;
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
public class ItensCompraController {
    // CADASTRAR ITEM DA COMPRA
    public boolean cadastrar(ItensCompras item) {
        String sql = "INSERT INTO itensCompra "
                + "(quantidade, precoUnitario, desconto, "
                + "compras_idcompras, produtos_idprodutos) "
                + "VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, item.getQuantidade());
            stmt.setDouble(2, item.getPrecoUnitario());
            stmt.setDouble(3, item.getDesconto());
            stmt.setInt(4, item.getCompras_idcompras());
            stmt.setInt(5, item.getProdutos_idprodutos());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar item da compra: "
                    + e.getMessage());
            return false;
        }
    }

    // LISTAR TODOS OS ITENS DA COMPRA
    public List<ItensCompras> listarTodos() {
        List<ItensCompras> itens = new ArrayList<>();

        String sql = "SELECT iditensCompra, quantidade, "
                + "precoUnitario, desconto, compras_idcompras, "
                + "produtos_idprodutos "
                + "FROM itensCompra";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                ItensCompras item = new ItensCompras();

                item.setIditensCompra(
                        rs.getInt("iditensCompra"));
                item.setQuantidade(
                        rs.getInt("quantidade"));
                item.setPrecoUnitario(
                        rs.getDouble("precoUnitario"));
                item.setDesconto(
                        rs.getDouble("desconto"));
                item.setCompras_idcompras(
                        rs.getInt("compras_idcompras"));
                item.setProdutos_idprodutos(
                        rs.getInt("produtos_idprodutos"));

                itens.add(item);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar itens da compra: "
                    + e.getMessage());
        }

        return itens;
    }

    // BUSCAR ITEM DA COMPRA PELO ID
    public ItensCompras buscarPorId(int id) {
        String sql = "SELECT iditensCompra, quantidade, "
                + "precoUnitario, desconto, compras_idcompras, "
                + "produtos_idprodutos "
                + "FROM itensCompra "
                + "WHERE iditensCompra = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    ItensCompras item = new ItensCompras();

                    item.setIditensCompra(
                            rs.getInt("iditensCompra"));
                    item.setQuantidade(
                            rs.getInt("quantidade"));
                    item.setPrecoUnitario(
                            rs.getDouble("precoUnitario"));
                    item.setDesconto(
                            rs.getDouble("desconto"));
                    item.setCompras_idcompras(
                            rs.getInt("compras_idcompras"));
                    item.setProdutos_idprodutos(
                            rs.getInt("produtos_idprodutos"));

                    return item;
                }
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar item da compra: "
                    + e.getMessage());
        }

        return null;
    }

    // ATUALIZAR ITEM DA COMPRA
    public boolean atualizar(int idOriginal, ItensCompras item) {
        String sql = "UPDATE itensCompra SET "
                + "quantidade = ?, precoUnitario = ?, "
                + "desconto = ?, compras_idcompras = ?, "
                + "produtos_idprodutos = ? "
                + "WHERE iditensCompra = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, item.getQuantidade());
            stmt.setDouble(2, item.getPrecoUnitario());
            stmt.setDouble(3, item.getDesconto());
            stmt.setInt(4, item.getCompras_idcompras());
            stmt.setInt(5, item.getProdutos_idprodutos());
            stmt.setInt(6, idOriginal);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar item da compra: "
                    + e.getMessage());
            return false;
        }
    }

    // EXCLUIR ITEM DA COMPRA
    public boolean excluir(int id) {
        String sql = "DELETE FROM itensCompra "
                + "WHERE iditensCompra = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao excluir item da compra: "
                    + e.getMessage());
            return false;
        }
    }
}
