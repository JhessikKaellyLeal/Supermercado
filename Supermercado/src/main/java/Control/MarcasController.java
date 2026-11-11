
package Control;

import Conection.Conexao;
import Model.Marcas;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MarcasController {
    
    public int buscarIdPorNome(String nome) throws SQLException {

    String sql = "SELECT idmarcas FROM marcas WHERE nomeMarca = ?";

    try (Connection conn = Conexao.conectar();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, nome);

        try (ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                return rs.getInt("idmarcas");
            }
        }
    }

    return 0;
}

    public void cadastrar(Marcas marca) throws SQLException {
        String sql = "INSERT INTO marcas (nome, status, descricao) "
                + "VALUES (?, ?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, marca.getNome());
            stmt.setInt(2, marca.getStatus());
            stmt.setString(3, marca.getDescricao());

            stmt.executeUpdate();
        }
    }

    public List<Marcas> listar() throws SQLException {
        List<Marcas> lista = new ArrayList<>();

        String sql = "SELECT * FROM marcas ORDER BY nome ASC";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                lista.add(mapearMarca(rs));
            }
        }

        return lista;
    }

    public Marcas buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM marcas WHERE idmarcas = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapearMarca(rs);
                }
            }
        }

        return null;
    }

    public List<Marcas> buscarPorNome(String nome) throws SQLException {
        List<Marcas> lista = new ArrayList<>();

        String sql = "SELECT * FROM marcas WHERE nome LIKE ? "
                + "ORDER BY nome ASC";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + nome + "%");

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapearMarca(rs));
                }
            }
        }

        return lista;
    }

    public List<Marcas> listarAtivas() throws SQLException {
        List<Marcas> lista = new ArrayList<>();

        String sql = "SELECT * FROM marcas WHERE status = 1 "
                + "ORDER BY nome ASC";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                lista.add(mapearMarca(rs));
            }
        }

        return lista;
    }

    public void atualizar(Marcas marca) throws SQLException {
        String sql = "UPDATE marcas SET "
                + "nome = ?, status = ?, descricao = ? "
                + "WHERE idmarcas = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, marca.getNome());
            stmt.setInt(2, marca.getStatus());
            stmt.setString(3, marca.getDescricao());
            stmt.setInt(4, marca.getIdmarcas());

            stmt.executeUpdate();
        }
    }

    public void excluir(int id) throws SQLException {
        String sql = "DELETE FROM marcas WHERE idmarcas = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public void alterarStatus(int id, int status) throws SQLException {
        String sql = "UPDATE marcas SET status = ? "
                + "WHERE idmarcas = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, status);
            stmt.setInt(2, id);

            stmt.executeUpdate();
        }
    }

    private Marcas mapearMarca(ResultSet rs) throws SQLException {
        Marcas marca = new Marcas();

        marca.setIdmarcas(rs.getInt("idmarcas"));
        marca.setNome(rs.getString("nome"));
        marca.setStatus(rs.getInt("status"));
        marca.setDescricao(rs.getString("descricao"));

        return marca;
    }
}
