
package Control;

import Conection.Conexao;
import Model.Categorias;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoriasController {
    
    public int buscarIdPorNome(String nome) throws SQLException {

    String sql = "SELECT idcategorias FROM categorias WHERE nomeCategoria = ?";

    try (Connection conn = Conexao.conectar();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, nome);

        try (ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                return rs.getInt("idcategorias");
            }
        }
    }

    return 0;
}

    // CADASTRAR
    public boolean cadastrar(Categorias categoria) throws SQLException {

        String sql = "INSERT INTO categorias (nome, descricao, status) "
                   + "VALUES (?, ?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(
                     sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, categoria.getNome());
            stmt.setString(2, categoria.getDescricao());
            stmt.setInt(3, categoria.getStatus());

            int linhas = stmt.executeUpdate();

            if (linhas > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        categoria.setIdcategorias(rs.getInt(1));
                    }
                }

                return true;
            }
        }

        return false;
    }

    // LISTAR TODOS
    public List<Categorias> listar() throws SQLException {

        List<Categorias> lista = new ArrayList<>();

        String sql = "SELECT * FROM categorias ORDER BY idcategorias DESC";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                Categorias categoria = new Categorias();

                categoria.setIdcategorias(rs.getInt("idcategorias"));
                categoria.setNome(rs.getString("nome"));
                categoria.setDescricao(rs.getString("descricao"));
                categoria.setStatus(rs.getInt("status"));

                lista.add(categoria);
            }
        }

        return lista;
    }

    // BUSCAR POR ID
    public Categorias buscarPorId(int id) throws SQLException {

        String sql = "SELECT * FROM categorias WHERE idcategorias = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {

                    Categorias categoria = new Categorias();

                    categoria.setIdcategorias(rs.getInt("idcategorias"));
                    categoria.setNome(rs.getString("nome"));
                    categoria.setDescricao(rs.getString("descricao"));
                    categoria.setStatus(rs.getInt("status"));

                    return categoria;
                }
            }
        }

        return null;
    }

    // ATUALIZAR
    public boolean atualizar(Categorias categoria) throws SQLException {

        String sql = "UPDATE categorias SET "
                   + "nome = ?, "
                   + "descricao = ?, "
                   + "status = ? "
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

    // EXCLUIR
    public boolean excluir(int id) throws SQLException {

        String sql = "DELETE FROM categorias WHERE idcategorias = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            return stmt.executeUpdate() > 0;
        }
    }
}
