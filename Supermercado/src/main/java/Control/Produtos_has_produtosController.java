/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control;

import Conection.Conexao;
import Model.Promocao_has_Produtos;
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
public class Produtos_has_produtosController {
    
    
 public boolean cadastrar(Promocao_has_Produtos relacao) {

        String sql = "INSERT INTO promocao_has_produtos "
                + "(promocao_idpromocao, produtos_idprodutos, "
                + "produtos_categorias_idcategorias, "
                + "produtos_marcas_idmarcas, "
                + "produtos_unidadeMedidas_idunidadeMedidas) "
                + "VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, relacao.getPromocao_idpromocao());
            stmt.setInt(2, relacao.getProdutos_idprodutos());
            stmt.setInt(3, relacao.getProdutos_categorias_idcategorias());
            stmt.setInt(4, relacao.getProdutos_marcas_idmarcas());
            stmt.setInt(5, relacao.getProdutos_unidadeMedidas_idunidadeMedidas());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar produto na promoção: "
                    + e.getMessage());
            return false;
        }
    }

    // LISTAR TODOS OS VINCULOS
    public List<Promocao_has_Produtos> listarTodos() {

        List<Promocao_has_Produtos> lista = new ArrayList<>();

        String sql = "SELECT promocao_idpromocao, produtos_idprodutos, "
                + "produtos_categorias_idcategorias, "
                + "produtos_marcas_idmarcas, "
                + "produtos_unidadeMedidas_idunidadeMedidas "
                + "FROM promocao_has_produtos";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                Promocao_has_Produtos relacao =
                        new Promocao_has_Produtos();

                relacao.setPromocao_idpromocao(
                        rs.getInt("promocao_idpromocao"));

                relacao.setProdutos_idprodutos(
                        rs.getInt("produtos_idprodutos"));

                relacao.setProdutos_categorias_idcategorias(
                        rs.getInt("produtos_categorias_idcategorias"));

                relacao.setProdutos_marcas_idmarcas(
                        rs.getInt("produtos_marcas_idmarcas"));

                relacao.setProdutos_unidadeMedidas_idunidadeMedidas(
                        rs.getInt("produtos_unidadeMedidas_idunidadeMedidas"));

                lista.add(relacao);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar produtos das promoções: "
                    + e.getMessage());
        }

        return lista;
    }

    // BUSCAR VINCULOS DE UMA PROMOCAO
    public List<Promocao_has_Produtos> buscarPorPromocao(
            int idpromocao) {

        List<Promocao_has_Produtos> lista = new ArrayList<>();

        String sql = "SELECT promocao_idpromocao, produtos_idprodutos, "
                + "produtos_categorias_idcategorias, "
                + "produtos_marcas_idmarcas, "
                + "produtos_unidadeMedidas_idunidadeMedidas "
                + "FROM promocao_has_produtos "
                + "WHERE promocao_idpromocao = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idpromocao);

            try (ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {

                    Promocao_has_Produtos relacao =
                            new Promocao_has_Produtos();

                    relacao.setPromocao_idpromocao(
                            rs.getInt("promocao_idpromocao"));

                    relacao.setProdutos_idprodutos(
                            rs.getInt("produtos_idprodutos"));

                    relacao.setProdutos_categorias_idcategorias(
                            rs.getInt("produtos_categorias_idcategorias"));

                    relacao.setProdutos_marcas_idmarcas(
                            rs.getInt("produtos_marcas_idmarcas"));

                    relacao.setProdutos_unidadeMedidas_idunidadeMedidas(
                            rs.getInt("produtos_unidadeMedidas_idunidadeMedidas"));

                    lista.add(relacao);
                }
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar produtos da promoção: "
                    + e.getMessage());
        }

        return lista;
    }

    // BUSCAR UM VINCULO ESPECIFICO
    public Promocao_has_Produtos buscarPorChaves(
            int idpromocao,
            int idproduto,
            int idcategoria,
            int idmarca,
            int idunidadeMedida) {

        String sql = "SELECT promocao_idpromocao, produtos_idprodutos, "
                + "produtos_categorias_idcategorias, "
                + "produtos_marcas_idmarcas, "
                + "produtos_unidadeMedidas_idunidadeMedidas "
                + "FROM promocao_has_produtos "
                + "WHERE promocao_idpromocao = ? "
                + "AND produtos_idprodutos = ? "
                + "AND produtos_categorias_idcategorias = ? "
                + "AND produtos_marcas_idmarcas = ? "
                + "AND produtos_unidadeMedidas_idunidadeMedidas = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idpromocao);
            stmt.setInt(2, idproduto);
            stmt.setInt(3, idcategoria);
            stmt.setInt(4, idmarca);
            stmt.setInt(5, idunidadeMedida);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {

                    Promocao_has_Produtos relacao =
                            new Promocao_has_Produtos();

                    relacao.setPromocao_idpromocao(
                            rs.getInt("promocao_idpromocao"));

                    relacao.setProdutos_idprodutos(
                            rs.getInt("produtos_idprodutos"));

                    relacao.setProdutos_categorias_idcategorias(
                            rs.getInt("produtos_categorias_idcategorias"));

                    relacao.setProdutos_marcas_idmarcas(
                            rs.getInt("produtos_marcas_idmarcas"));

                    relacao.setProdutos_unidadeMedidas_idunidadeMedidas(
                            rs.getInt("produtos_unidadeMedidas_idunidadeMedidas"));

                    return relacao;
                }

            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar vínculo: "
                    + e.getMessage());
        }

        return null;
    }

    // EXCLUIR VINCULO ENTRE PROMOCAO E PRODUTO
    public boolean excluir(
            int idpromocao,
            int idproduto,
            int idcategoria,
            int idmarca,
            int idunidadeMedida) {

        String sql = "DELETE FROM promocao_has_produtos "
                + "WHERE promocao_idpromocao = ? "
                + "AND produtos_idprodutos = ? "
                + "AND produtos_categorias_idcategorias = ? "
                + "AND produtos_marcas_idmarcas = ? "
                + "AND produtos_unidadeMedidas_idunidadeMedidas = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idpromocao);
            stmt.setInt(2, idproduto);
            stmt.setInt(3, idcategoria);
            stmt.setInt(4, idmarca);
            stmt.setInt(5, idunidadeMedida);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao excluir vínculo: "
                    + e.getMessage());
            return false;
        }
    }    
}
