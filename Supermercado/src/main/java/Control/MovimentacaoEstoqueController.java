
package Control;

import Conection.Conexao;
import Model.MovimentacaoEstoque;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MovimentacaoEstoqueController {

    // LISTAR MOVIMENTAÇÕES POR NOME DO PRODUTO
public List<MovimentacaoEstoque> listarMovimentacoesPorProduto(
        String nomeProduto) throws SQLException {

    List<MovimentacaoEstoque> lista = new ArrayList<>();

    String sql = "SELECT me.* "
            + "FROM movimentacaoEstoque me "
            + "INNER JOIN produtos p "
            + "ON me.produtos_idprodutos = p.idprodutos "
            + "WHERE p.nome LIKE ? "
            + "ORDER BY me.idmovimentacaoEstoque DESC";

    try (Connection conn = Conexao.conectar();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, "%" + nomeProduto + "%");

        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                lista.add(mapearMovimentacao(rs));
            }
        }
    }

    return lista;
}
    
    
    public void cadastrar(MovimentacaoEstoque movimentacao)
            throws SQLException {

        String sql = "INSERT INTO movimentacaoEstoque "
                + "(tipoMovimentacao, quantidade, dataMovimentacao, "
                + "horaMovimentacao, documentoReferencia, "
                + "produtos_idprodutos, produtos_categorias_idcategorias, "
                + "produtos_marcas_idmarcas, "
                + "produtos_unidadeMedidas_idunidadeMedidas) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, movimentacao.getTipoMovimentacao());
            stmt.setInt(2, movimentacao.getQuantidade());
            stmt.setString(3, movimentacao.getDataMovimentacao());
            stmt.setString(4, movimentacao.getHoraMovimentacao());
            stmt.setBytes(5, movimentacao.getDocumentoReferencia());
            stmt.setInt(6, movimentacao.getProdutos_idprodutos());
            stmt.setInt(7, movimentacao.getProdutos_categorias_idcategorias());
            stmt.setInt(8, movimentacao.getProdutos_marcas_idmarcas());
            stmt.setInt(9, movimentacao.getProdutos_unidadeMedidas_idunidadeMedidas());

            stmt.executeUpdate();
        }
    }

    public List<MovimentacaoEstoque> listar() throws SQLException {
        List<MovimentacaoEstoque> lista = new ArrayList<>();

        String sql = "SELECT * FROM movimentacaoEstoque "
                + "ORDER BY idmovimentacaoEstoque DESC";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                lista.add(mapearMovimentacao(rs));
            }
        }

        return lista;
    }

    public MovimentacaoEstoque buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM movimentacaoEstoque "
                + "WHERE idmovimentacaoEstoque = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapearMovimentacao(rs);
                }
            }
        }

        return null;
    }

    public List<MovimentacaoEstoque> buscarPorProduto(int idProduto)
            throws SQLException {

        List<MovimentacaoEstoque> lista = new ArrayList<>();

        String sql = "SELECT * FROM movimentacaoEstoque "
                + "WHERE produtos_idprodutos = ? "
                + "ORDER BY idmovimentacaoEstoque DESC";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idProduto);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapearMovimentacao(rs));
                }
            }
        }

        return lista;
    }

    public List<MovimentacaoEstoque> buscarPorTipo(String tipo)
            throws SQLException {

        List<MovimentacaoEstoque> lista = new ArrayList<>();

        String sql = "SELECT * FROM movimentacaoEstoque "
                + "WHERE tipoMovimentacao = ? "
                + "ORDER BY idmovimentacaoEstoque DESC";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, tipo);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapearMovimentacao(rs));
                }
            }
        }

        return lista;
    }

    public List<MovimentacaoEstoque> buscarPorData(String data)
            throws SQLException {

        List<MovimentacaoEstoque> lista = new ArrayList<>();

        String sql = "SELECT * FROM movimentacaoEstoque "
                + "WHERE dataMovimentacao = ? "
                + "ORDER BY horaMovimentacao DESC";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, data);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapearMovimentacao(rs));
                }
            }
        }

        return lista;
    }

    public void atualizar(MovimentacaoEstoque movimentacao)
            throws SQLException {

        String sql = "UPDATE movimentacaoEstoque SET "
                + "tipoMovimentacao = ?, quantidade = ?, "
                + "dataMovimentacao = ?, horaMovimentacao = ?, "
                + "documentoReferencia = ?, produtos_idprodutos = ?, "
                + "produtos_categorias_idcategorias = ?, "
                + "produtos_marcas_idmarcas = ?, "
                + "produtos_unidadeMedidas_idunidadeMedidas = ? "
                + "WHERE idmovimentacaoEstoque = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, movimentacao.getTipoMovimentacao());
            stmt.setInt(2, movimentacao.getQuantidade());
            stmt.setString(3, movimentacao.getDataMovimentacao());
            stmt.setString(4, movimentacao.getHoraMovimentacao());
            stmt.setBytes(5, movimentacao.getDocumentoReferencia());
            stmt.setInt(6, movimentacao.getProdutos_idprodutos());
            stmt.setInt(7, movimentacao.getProdutos_categorias_idcategorias());
            stmt.setInt(8, movimentacao.getProdutos_marcas_idmarcas());
            stmt.setInt(9, movimentacao.getProdutos_unidadeMedidas_idunidadeMedidas());
            stmt.setInt(10, movimentacao.getIdmovimentacaoEstoque());

            stmt.executeUpdate();
        }
    }

    public void excluir(int id) throws SQLException {
        String sql = "DELETE FROM movimentacaoEstoque "
                + "WHERE idmovimentacaoEstoque = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    private MovimentacaoEstoque mapearMovimentacao(ResultSet rs)
            throws SQLException {

        MovimentacaoEstoque movimentacao = new MovimentacaoEstoque();

        movimentacao.setIdmovimentacaoEstoque(
                rs.getInt("idmovimentacaoEstoque"));

        movimentacao.setTipoMovimentacao(
                rs.getString("tipoMovimentacao"));

        movimentacao.setQuantidade(
                rs.getInt("quantidade"));

        movimentacao.setDataMovimentacao(
                rs.getString("dataMovimentacao"));

        movimentacao.setHoraMovimentacao(
                rs.getString("horaMovimentacao"));

        movimentacao.setDocumentoReferencia(
                rs.getBytes("documentoReferencia"));

        movimentacao.setProdutos_idprodutos(
                rs.getInt("produtos_idprodutos"));

        movimentacao.setProdutos_categorias_idcategorias(
                rs.getInt("produtos_categorias_idcategorias"));

        movimentacao.setProdutos_marcas_idmarcas(
                rs.getInt("produtos_marcas_idmarcas"));

        movimentacao.setProdutos_unidadeMedidas_idunidadeMedidas(
                rs.getInt("produtos_unidadeMedidas_idunidadeMedidas"));

        return movimentacao;
    }
    
   
}
