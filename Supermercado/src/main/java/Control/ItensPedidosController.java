/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control;

import Conection.Conexao;
import Model.ItensPedidos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author GeovannaOliviera
 */
public class ItensPedidosController {
public boolean cadastrar(ItensPedidos item) {

        String sql = "INSERT INTO itensPedidos "
                + "(quantidade, precoUnitario, desconto, "
                + "pedidos_idpedidos, produtos_idprodutos, "
                + "produtos_categorias_idcategorias, "
                + "produtos_marcas_idmarcas, "
                + "produtos_unidadeMedidas_idunidadeMedidas) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, item.getQuantidade());
            stmt.setDouble(2, item.getPrecoUnitario());
            stmt.setDouble(3, item.getDesconto());
            stmt.setInt(4, item.getPedidos_idpedidos());
            stmt.setInt(5, item.getProdutos_idprodutos());
            stmt.setInt(6, item.getProdutos_categorias_idcategorias());
            stmt.setInt(7, item.getProdutos_marcas_idmarcas());
            stmt.setInt(8, item.getProdutos_unidadeMedidas_idunidadeMedidas());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar item do pedido: "
                    + e.getMessage());
            return false;
        }
    }

    // LISTAR TODOS OS ITENS DOS PEDIDOS
    public List<ItensPedidos> listarTodos() {

        List<ItensPedidos> lista = new ArrayList<>();

        String sql = "SELECT * FROM itensPedidos";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                ItensPedidos item = new ItensPedidos();

                item.setIditensPedidos(
                        rs.getInt("iditensPedidos"));

                item.setQuantidade(
                        rs.getInt("quantidade"));

                item.setPrecoUnitario(
                        rs.getDouble("precoUnitario"));

                item.setDesconto(
                        rs.getDouble("desconto"));

                item.setPedidos_idpedidos(
                        rs.getInt("pedidos_idpedidos"));

                item.setProdutos_idprodutos(
                        rs.getInt("produtos_idprodutos"));

                item.setProdutos_categorias_idcategorias(
                        rs.getInt("produtos_categorias_idcategorias"));

                item.setProdutos_marcas_idmarcas(
                        rs.getInt("produtos_marcas_idmarcas"));

                item.setProdutos_unidadeMedidas_idunidadeMedidas(
                        rs.getInt("produtos_unidadeMedidas_idunidadeMedidas"));

                lista.add(item);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar itens dos pedidos: "
                    + e.getMessage());
        }

        return lista;
    }

    // BUSCAR ITEM PELO ID
    public ItensPedidos buscarPorId(int iditensPedidos) {

        String sql = "SELECT * FROM itensPedidos "
                + "WHERE iditensPedidos = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, iditensPedidos);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {

                    ItensPedidos item = new ItensPedidos();

                    item.setIditensPedidos(
                            rs.getInt("iditensPedidos"));

                    item.setQuantidade(
                            rs.getInt("quantidade"));

                    item.setPrecoUnitario(
                            rs.getDouble("precoUnitario"));

                    item.setDesconto(
                            rs.getDouble("desconto"));

                    item.setPedidos_idpedidos(
                            rs.getInt("pedidos_idpedidos"));

                    item.setProdutos_idprodutos(
                            rs.getInt("produtos_idprodutos"));

                    item.setProdutos_categorias_idcategorias(
                            rs.getInt("produtos_categorias_idcategorias"));

                    item.setProdutos_marcas_idmarcas(
                            rs.getInt("produtos_marcas_idmarcas"));

                    item.setProdutos_unidadeMedidas_idunidadeMedidas(
                            rs.getInt("produtos_unidadeMedidas_idunidadeMedidas"));

                    return item;
                }
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar item do pedido: "
                    + e.getMessage());
        }

        return null;
    }

    // LISTAR ITENS DE UM PEDIDO ESPECIFICO
    public List<ItensPedidos> buscarPorPedido(int idpedidos) {

        List<ItensPedidos> lista = new ArrayList<>();

        String sql = "SELECT * FROM itensPedidos "
                + "WHERE pedidos_idpedidos = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idpedidos);

            try (ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {

                    ItensPedidos item = new ItensPedidos();

                    item.setIditensPedidos(
                            rs.getInt("iditensPedidos"));

                    item.setQuantidade(
                            rs.getInt("quantidade"));

                    item.setPrecoUnitario(
                            rs.getDouble("precoUnitario"));

                    item.setDesconto(
                            rs.getDouble("desconto"));

                    item.setPedidos_idpedidos(
                            rs.getInt("pedidos_idpedidos"));

                    item.setProdutos_idprodutos(
                            rs.getInt("produtos_idprodutos"));

                    item.setProdutos_categorias_idcategorias(
                            rs.getInt("produtos_categorias_idcategorias"));

                    item.setProdutos_marcas_idmarcas(
                            rs.getInt("produtos_marcas_idmarcas"));

                    item.setProdutos_unidadeMedidas_idunidadeMedidas(
                            rs.getInt("produtos_unidadeMedidas_idunidadeMedidas"));

                    lista.add(item);
                }
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar itens do pedido: "
                    + e.getMessage());
        }

        return lista;
    }

    // ATUALIZAR ITEM DO PEDIDO
    public boolean atualizar(int iditensPedidos, ItensPedidos item) {

        String sql = "UPDATE itensPedidos SET "
                + "quantidade = ?, "
                + "precoUnitario = ?, "
                + "desconto = ?, "
                + "pedidos_idpedidos = ?, "
                + "produtos_idprodutos = ?, "
                + "produtos_categorias_idcategorias = ?, "
                + "produtos_marcas_idmarcas = ?, "
                + "produtos_unidadeMedidas_idunidadeMedidas = ? "
                + "WHERE iditensPedidos = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, item.getQuantidade());
            stmt.setDouble(2, item.getPrecoUnitario());
            stmt.setDouble(3, item.getDesconto());
            stmt.setInt(4, item.getPedidos_idpedidos());
            stmt.setInt(5, item.getProdutos_idprodutos());
            stmt.setInt(6, item.getProdutos_categorias_idcategorias());
            stmt.setInt(7, item.getProdutos_marcas_idmarcas());
            stmt.setInt(8, item.getProdutos_unidadeMedidas_idunidadeMedidas());
            stmt.setInt(9, iditensPedidos);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar item do pedido: "
                    + e.getMessage());
            return false;
        }
    }

    // EXCLUIR ITEM DO PEDIDO
    public boolean excluir(int iditensPedidos) {

        String sql = "DELETE FROM itensPedidos "
                + "WHERE iditensPedidos = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, iditensPedidos);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao excluir item do pedido: "
                    + e.getMessage());
            return false;
        }
    }    
}
