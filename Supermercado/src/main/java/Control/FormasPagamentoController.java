
package Control;

import Conection.Conexao;
import Model.FormasPagamento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FormasPagamentoController {

    public void cadastrar(FormasPagamento forma) throws SQLException {
        String sql = "INSERT INTO formasPagamento (nome) VALUES (?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, forma.getNome());

            stmt.executeUpdate();
        }
    }

    public List<FormasPagamento> listar() throws SQLException {
        List<FormasPagamento> lista = new ArrayList<>();

        String sql = "SELECT * FROM formasPagamento";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                lista.add(mapearFormaPagamento(rs));
            }
        }

        return lista;
    }

    public FormasPagamento buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM formasPagamento "
                + "WHERE idformasPagamento = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapearFormaPagamento(rs);
                }
            }
        }

        return null;
    }

    public void atualizar(FormasPagamento forma) throws SQLException {
        String sql = "UPDATE formasPagamento SET nome = ? "
                + "WHERE idformasPagamento = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, forma.getNome());
            stmt.setInt(2, forma.getIdformasPagamento());

            stmt.executeUpdate();
        }
    }

    public void excluir(int id) throws SQLException {
        String sql = "DELETE FROM formasPagamento "
                + "WHERE idformasPagamento = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public List<FormasPagamento> buscarPorNome(String nome)
            throws SQLException {

        List<FormasPagamento> lista = new ArrayList<>();

        String sql = "SELECT * FROM formasPagamento WHERE nome LIKE ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + nome + "%");

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapearFormaPagamento(rs));
                }
            }
        }

        return lista;
    }

    private FormasPagamento mapearFormaPagamento(ResultSet rs)
            throws SQLException {

        FormasPagamento forma = new FormasPagamento();

        forma.setIdformasPagamento(rs.getInt("idformasPagamento"));
        forma.setNome(rs.getString("nome"));

        return forma;
    }
}
