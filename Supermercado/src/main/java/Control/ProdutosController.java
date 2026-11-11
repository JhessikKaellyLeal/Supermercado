
package Control;

import Conection.Conexao;
import Model.Produtos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class ProdutosController {

    public boolean cadastrar(
        String nome,
        String codigo,
        String descricao,
        double precoCusto,
        double precoVenda,
        int situacao,
        int estoque,
        String validade,
        byte[] imagem,
        int idCategoria,
        int idMarca,
        int idUnidade
) throws SQLException {

    String sql = "INSERT INTO produtos "
            + "(nome, codigo, descricao, precoCusto, precoVenda, "
            + "situacao, estoque, validade, imagem, "
            + "categorias_idcategorias, marcas_idmarcas, "
            + "unidadeMedidas_idunidadeMedidas) "
            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    try (Connection conn = Conexao.conectar();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, nome);
        stmt.setString(2, codigo);
        stmt.setString(3, descricao);
        stmt.setDouble(4, precoCusto);
        stmt.setDouble(5, precoVenda);
        stmt.setInt(6, situacao);
        stmt.setInt(7, estoque);
        stmt.setString(8, validade);
        stmt.setBytes(9, imagem);
        stmt.setInt(10, idCategoria);
        stmt.setInt(11, idMarca);
        stmt.setInt(12, idUnidade);

        return stmt.executeUpdate() > 0;
    }
}
    public List<Produtos> pesquisarPorNome(String nome) throws SQLException {

    List<Produtos> lista = new ArrayList<>();

    String sql = "SELECT * FROM produtos "
            + "WHERE nome LIKE ? "
            + "ORDER BY nome ASC";

    try (Connection conn = Conexao.conectar();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, "%" + nome + "%");

        try (ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                Produtos produto = new Produtos();

                produto.setIdprodutos(rs.getInt("idprodutos"));
                produto.setNome(rs.getString("nome"));
                produto.setCodigo(rs.getString("codigo"));
                produto.setDescricao(rs.getString("descricao"));
                produto.setPrecoCusto(rs.getDouble("precoCusto"));
                produto.setPrecoVenda(rs.getDouble("precoVenda"));
                produto.setSituacao(rs.getInt("situacao"));
                produto.setEstoque(rs.getInt("estoque"));
                produto.setValidade(rs.getString("validade"));
                produto.setImagem(rs.getBytes("imagem"));
                produto.setCategorias_idcategorias(
                        rs.getInt("categorias_idcategorias"));
                produto.setMarcas_idmarcas(
                        rs.getInt("marcas_idmarcas"));
                produto.setUnidadeMedidas_idunidadeMedidas(
                        rs.getInt("unidadeMedidas_idunidadeMedidas"));

                lista.add(produto);
            }
        }
    }

    return lista;
}

    public List<Produtos> listar() throws SQLException {
        List<Produtos> lista = new ArrayList<>();

        String sql = "SELECT * FROM produtos ORDER BY nome ASC";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                lista.add(mapearProduto(rs));
            }
        }

        return lista;
    }

    public Produtos buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM produtos WHERE idprodutos = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapearProduto(rs);
                }
            }
        }

        return null;
    }

    public Produtos buscarPorCodigo(String codigo) throws SQLException {
        String sql = "SELECT * FROM produtos WHERE codigo = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, codigo);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapearProduto(rs);
                }
            }
        }

        return null;
    }

    public List<Produtos> buscarPorNome(String nome) throws SQLException {
        List<Produtos> lista = new ArrayList<>();

        String sql = "SELECT * FROM produtos WHERE nome LIKE ? "
                + "ORDER BY nome ASC";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + nome + "%");

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapearProduto(rs));
                }
            }
        }

        return lista;
    }

    public List<Produtos> buscarPorCategoria(int idCategoria)
            throws SQLException {

        List<Produtos> lista = new ArrayList<>();

        String sql = "SELECT * FROM produtos "
                + "WHERE categorias_idcategorias = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idCategoria);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapearProduto(rs));
                }
            }
        }

        return lista;
    }

    public List<Produtos> buscarPorMarca(int idMarca)
            throws SQLException {

        List<Produtos> lista = new ArrayList<>();

        String sql = "SELECT * FROM produtos WHERE marcas_idmarcas = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idMarca);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapearProduto(rs));
                }
            }
        }

        return lista;
    }

    public List<Produtos> buscarPorSituacao(int situacao)
            throws SQLException {

        List<Produtos> lista = new ArrayList<>();

        String sql = "SELECT * FROM produtos WHERE situacao = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, situacao);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapearProduto(rs));
                }
            }
        }

        return lista;
    }

    public List<Produtos> listarComEstoqueBaixo(int quantidade)
            throws SQLException {

        List<Produtos> lista = new ArrayList<>();

        String sql = "SELECT * FROM produtos WHERE estoque <= ? "
                + "ORDER BY estoque ASC";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, quantidade);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapearProduto(rs));
                }
            }
        }

        return lista;
    }

    public void atualizar(Produtos produto) throws SQLException {
        String sql = "UPDATE produtos SET "
                + "nome = ?, codigo = ?, descricao = ?, "
                + "precoCusto = ?, precoVenda = ?, situacao = ?, "
                + "estoque = ?, validade = ?, imagem = ?, "
                + "categorias_idcategorias = ?, marcas_idmarcas = ?, "
                + "unidadeMedidas_idunidadeMedidas = ? "
                + "WHERE idprodutos = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, produto.getNome());
            stmt.setString(2, produto.getCodigo());

            if (produto.getDescricao() == null) {
                stmt.setNull(3, Types.VARCHAR);
            } else {
                stmt.setString(3, produto.getDescricao());
            }

            stmt.setDouble(4, produto.getPrecoCusto());
            stmt.setDouble(5, produto.getPrecoVenda());
            stmt.setInt(6, produto.getSituacao());
            stmt.setInt(7, produto.getEstoque());
            stmt.setString(8, produto.getValidade());

            if (produto.getImagem() == null) {
                stmt.setNull(9, Types.BLOB);
            } else {
                stmt.setBytes(9, produto.getImagem());
            }

            stmt.setInt(10, produto.getCategorias_idcategorias());
            stmt.setInt(11, produto.getMarcas_idmarcas());
            stmt.setInt(12, produto.getUnidadeMedidas_idunidadeMedidas());
            stmt.setInt(13, produto.getIdprodutos());

            stmt.executeUpdate();
        }
    }

    public void excluir(int id) throws SQLException {
        String sql = "DELETE FROM produtos WHERE idprodutos = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    private Produtos mapearProduto(ResultSet rs) throws SQLException {
        Produtos produto = new Produtos();

        produto.setIdprodutos(rs.getInt("idprodutos"));
        produto.setNome(rs.getString("nome"));
        produto.setCodigo(rs.getString("codigo"));
        produto.setDescricao(rs.getString("descricao"));
        produto.setPrecoCusto(rs.getDouble("precoCusto"));
        produto.setPrecoVenda(rs.getDouble("precoVenda"));
        produto.setSituacao(rs.getInt("situacao"));
        produto.setEstoque(rs.getInt("estoque"));
        produto.setValidade(rs.getString("validade"));
        produto.setImagem(rs.getBytes("imagem"));

        produto.setCategorias_idcategorias(
                rs.getInt("categorias_idcategorias"));

        produto.setMarcas_idmarcas(
                rs.getInt("marcas_idmarcas"));

        produto.setUnidadeMedidas_idunidadeMedidas(
                rs.getInt("unidadeMedidas_idunidadeMedidas"));

        return produto;
    }
}
