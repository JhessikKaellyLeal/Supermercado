
package Control;

import Conection.Conexao;
import Model.Setores;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class SetoresController {

    public void cadastrar(Setores setor) throws SQLException {

        String sql = "INSERT INTO setores (nome, descricao, status) "
                + "VALUES (?, ?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, setor.getNome());

            if (setor.getDescricao() == null) {
                stmt.setNull(2, Types.VARCHAR);
            } else {
                stmt.setString(2, setor.getDescricao());
            }

            stmt.setInt(3, setor.getStatus());

            stmt.executeUpdate();
        }
    }

    public List<Setores> listar() throws SQLException {

        List<Setores> lista = new ArrayList<>();

        String sql = "SELECT * FROM setores ORDER BY nome ASC";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                lista.add(mapearSetor(rs));
            }
        }

        return lista;
    }

    public Setores buscarPorId(int id) throws SQLException {

        String sql = "SELECT * FROM setores WHERE idsetores = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapearSetor(rs);
                }
            }
        }

        return null;
    }

    public List<Setores> buscarPorNome(String nome)
            throws SQLException {

        List<Setores> lista = new ArrayList<>();

        String sql = "SELECT * FROM setores "
                + "WHERE nome LIKE ? ORDER BY nome ASC";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + nome + "%");

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapearSetor(rs));
                }
            }
        }

        return lista;
    }

    public List<Setores> buscarPorStatus(int status)
            throws SQLException {

        List<Setores> lista = new ArrayList<>();

        String sql = "SELECT * FROM setores WHERE status = ? "
                + "ORDER BY nome ASC";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, status);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapearSetor(rs));
                }
            }
        }

        return lista;
    }

    public List<Setores> listarAtivos() throws SQLException {

        List<Setores> lista = new ArrayList<>();

        String sql = "SELECT * FROM setores "
                + "WHERE status = 1 ORDER BY nome ASC";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                lista.add(mapearSetor(rs));
            }
        }

        return lista;
    }

    public void atualizar(Setores setor) throws SQLException {

        String sql = "UPDATE setores SET "
                + "nome = ?, descricao = ?, status = ? "
                + "WHERE idsetores = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, setor.getNome());

            if (setor.getDescricao() == null) {
                stmt.setNull(2, Types.VARCHAR);
            } else {
                stmt.setString(2, setor.getDescricao());
            }

            stmt.setInt(3, setor.getStatus());
            stmt.setInt(4, setor.getIdsetores());

            stmt.executeUpdate();
        }
    }

    public void alterarStatus(int id, int status)
            throws SQLException {

        String sql = "UPDATE setores SET status = ? "
                + "WHERE idsetores = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, status);
            stmt.setInt(2, id);

            stmt.executeUpdate();
        }
    }

    public void excluir(int id) throws SQLException {

        String sql = "DELETE FROM setores WHERE idsetores = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    private Setores mapearSetor(ResultSet rs) throws SQLException {

        Setores setor = new Setores();

        setor.setIdsetores(rs.getInt("idsetores"));
        setor.setNome(rs.getString("nome"));
        setor.setDescricao(rs.getString("descricao"));
        setor.setStatus(rs.getInt("status"));

        return setor;
    }
}
