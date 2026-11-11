
package Control;

import Conection.Conexao;
import Model.CodigoBarras;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CodigoBarrasController {

    // CADASTRAR
    public boolean cadastrar(CodigoBarras codigoBarras) throws SQLException {

        String sql = "INSERT INTO codigoBarras "
                + "(codigo, imagem, tipo, status, descricao, "
                + "produtos_idprodutos, produtos_categorias_idcategorias, "
                + "produtos_marcas_idmarcas, produtos_unidadeMedidas_idunidadeMedidas) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(
                     sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, codigoBarras.getCodigo());
            stmt.setBytes(2, codigoBarras.getImagem());
            stmt.setString(3, codigoBarras.getTipo());
            stmt.setInt(4, codigoBarras.getStatus());
            stmt.setString(5, codigoBarras.getDescricao());
            stmt.setInt(6, codigoBarras.getProdutos_idprodutos());
            stmt.setInt(7, codigoBarras.getProdutos_categorias_idcategorias());
            stmt.setInt(8, codigoBarras.getProdutos_marcas_idmarcas());
            stmt.setInt(9, codigoBarras.getProdutos_unidadeMedidas_idunidadeMedidas());

            int linhas = stmt.executeUpdate();

            if (linhas > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        codigoBarras.setIdcodigoBarras(rs.getInt(1));
                    }
                }

                return true;
            }
        }

        return false;
    }

    // LISTAR TODOS
    public List<CodigoBarras> listar() throws SQLException {

        List<CodigoBarras> lista = new ArrayList<>();

        String sql = "SELECT * FROM codigoBarras "
                   + "ORDER BY idcodigoBarras DESC";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                CodigoBarras codigoBarras = new CodigoBarras();

                codigoBarras.setIdcodigoBarras(rs.getInt("idcodigoBarras"));
                codigoBarras.setCodigo(rs.getString("codigo"));
                codigoBarras.setImagem(rs.getBytes("imagem"));
                codigoBarras.setTipo(rs.getString("tipo"));
                codigoBarras.setStatus(rs.getInt("status"));
                codigoBarras.setDescricao(rs.getString("descricao"));
                codigoBarras.setProdutos_idprodutos(
                        rs.getInt("produtos_idprodutos"));
                codigoBarras.setProdutos_categorias_idcategorias(
                        rs.getInt("produtos_categorias_idcategorias"));
                codigoBarras.setProdutos_marcas_idmarcas(
                        rs.getInt("produtos_marcas_idmarcas"));
                codigoBarras.setProdutos_unidadeMedidas_idunidadeMedidas(
                        rs.getInt("produtos_unidadeMedidas_idunidadeMedidas"));

                lista.add(codigoBarras);
            }
        }

        return lista;
    }

    // BUSCAR POR ID
    public CodigoBarras buscarPorId(int id) throws SQLException {

        String sql = "SELECT * FROM codigoBarras "
                   + "WHERE idcodigoBarras = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {

                    CodigoBarras codigoBarras = new CodigoBarras();

                    codigoBarras.setIdcodigoBarras(rs.getInt("idcodigoBarras"));
                    codigoBarras.setCodigo(rs.getString("codigo"));
                    codigoBarras.setImagem(rs.getBytes("imagem"));
                    codigoBarras.setTipo(rs.getString("tipo"));
                    codigoBarras.setStatus(rs.getInt("status"));
                    codigoBarras.setDescricao(rs.getString("descricao"));
                    codigoBarras.setProdutos_idprodutos(
                            rs.getInt("produtos_idprodutos"));
                    codigoBarras.setProdutos_categorias_idcategorias(
                            rs.getInt("produtos_categorias_idcategorias"));
                    codigoBarras.setProdutos_marcas_idmarcas(
                            rs.getInt("produtos_marcas_idmarcas"));
                    codigoBarras.setProdutos_unidadeMedidas_idunidadeMedidas(
                            rs.getInt("produtos_unidadeMedidas_idunidadeMedidas"));

                    return codigoBarras;
                }
            }
        }

        return null;
    }

    // BUSCAR POR CÓDIGO
    public CodigoBarras buscarPorCodigo(String codigo) throws SQLException {

        String sql = "SELECT * FROM codigoBarras WHERE codigo = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, codigo);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {

                    CodigoBarras codigoBarras = new CodigoBarras();

                    codigoBarras.setIdcodigoBarras(rs.getInt("idcodigoBarras"));
                    codigoBarras.setCodigo(rs.getString("codigo"));
                    codigoBarras.setImagem(rs.getBytes("imagem"));
                    codigoBarras.setTipo(rs.getString("tipo"));
                    codigoBarras.setStatus(rs.getInt("status"));
                    codigoBarras.setDescricao(rs.getString("descricao"));
                    codigoBarras.setProdutos_idprodutos(
                            rs.getInt("produtos_idprodutos"));
                    codigoBarras.setProdutos_categorias_idcategorias(
                            rs.getInt("produtos_categorias_idcategorias"));
                    codigoBarras.setProdutos_marcas_idmarcas(
                            rs.getInt("produtos_marcas_idmarcas"));
                    codigoBarras.setProdutos_unidadeMedidas_idunidadeMedidas(
                            rs.getInt("produtos_unidadeMedidas_idunidadeMedidas"));

                    return codigoBarras;
                }
            }
        }

        return null;
    }

    // ATUALIZAR
    public boolean atualizar(CodigoBarras codigoBarras) throws SQLException {

        String sql = "UPDATE codigoBarras SET "
                + "codigo = ?, "
                + "imagem = ?, "
                + "tipo = ?, "
                + "status = ?, "
                + "descricao = ?, "
                + "produtos_idprodutos = ?, "
                + "produtos_categorias_idcategorias = ?, "
                + "produtos_marcas_idmarcas = ?, "
                + "produtos_unidadeMedidas_idunidadeMedidas = ? "
                + "WHERE idcodigoBarras = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, codigoBarras.getCodigo());
            stmt.setBytes(2, codigoBarras.getImagem());
            stmt.setString(3, codigoBarras.getTipo());
            stmt.setInt(4, codigoBarras.getStatus());
            stmt.setString(5, codigoBarras.getDescricao());
            stmt.setInt(6, codigoBarras.getProdutos_idprodutos());
            stmt.setInt(7, codigoBarras.getProdutos_categorias_idcategorias());
            stmt.setInt(8, codigoBarras.getProdutos_marcas_idmarcas());
            stmt.setInt(9, codigoBarras.getProdutos_unidadeMedidas_idunidadeMedidas());
            stmt.setInt(10, codigoBarras.getIdcodigoBarras());

            return stmt.executeUpdate() > 0;
        }
    }

    // EXCLUIR
    public boolean excluir(int id) throws SQLException {

        String sql = "DELETE FROM codigoBarras "
                   + "WHERE idcodigoBarras = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            return stmt.executeUpdate() > 0;
        }
    }
}
