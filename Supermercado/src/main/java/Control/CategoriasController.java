
package Control;

import Conection.Conexao;
import Model.Categorias;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoriasController {

    // CADASTRAR CATEGORIA
    public boolean cadastrar(Categorias categoria) throws SQLException {

        String sql = "INSERT INTO categorias "
                + "(nome, descricao, status) "
                + "VALUES (?, ?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, categoria.getNome());
            stmt.setString(2, categoria.getDescricao());
            stmt.setInt(3, categoria.getStatus());

            return stmt.executeUpdate() > 0;
        }
    }

    // LISTAR TODAS AS CATEGORIAS
    public List<Categorias> listarCategorias() throws SQLException {

        List<Categorias> lista = new ArrayList<>();

        String sql = "SELECT * FROM categorias";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                Categorias c = new Categorias();

                c.setIdcategorias(rs.getInt("idcategorias"));
                c.setNome(rs.getString("nome"));
                c.setDescricao(rs.getString("descricao"));
                c.setStatus(rs.getInt("status"));

                lista.add(c);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Erro ao listar categorias: " + e.getMessage());
        }

        return lista;
    }

    // BUSCAR CATEGORIA PELO ID
    public Categorias buscarPorId(int id) throws SQLException {

        String sql = "SELECT * FROM categorias WHERE idcategorias = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {

                    Categorias c = new Categorias();

                    c.setIdcategorias(rs.getInt("idcategorias"));
                    c.setNome(rs.getString("nome"));
                    c.setDescricao(rs.getString("descricao"));
                    c.setStatus(rs.getInt("status"));

                    return c;
                }
            }
        }

        return null;
    }

    // ALTERAR CATEGORIA
    public boolean alterar(Categorias categoria) throws SQLException {

        String sql = "UPDATE categorias SET "
                + "nome = ?, descricao = ?, status = ? "
                + "WHERE idcategorias = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, categoria.getNome());
            stmt.setString(2, categoria.getDescricao());
            stmt.setInt(3, categoria.getStatus());
            stmt.setInt(4, categoria.getIdcategorias());

            return stmt.executeUpdate() > 0;
        }
    }

    // EXCLUIR CATEGORIA
    public boolean excluir(int id) throws SQLException {

        String sql = "DELETE FROM categorias WHERE idcategorias = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            return stmt.executeUpdate() > 0;
        }
    }
}
