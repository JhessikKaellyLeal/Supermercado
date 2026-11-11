
package Control;

import Conection.Conexao;
import Model.MovimentacoesFinanceiras;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MovimentacoesFinanceirasController {

    public void cadastrar(MovimentacoesFinanceiras movimentacao)
            throws SQLException {

        String sql = "INSERT INTO movimentacoesFinanceiras "
                + "(tipo, valor, dataHora, descricao, origem, "
                + "contasFinanceiras_idcontasFinanceiras) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, movimentacao.getTipo());
            stmt.setDouble(2, movimentacao.getValor());
            stmt.setString(3, movimentacao.getDataHora());
            stmt.setString(4, movimentacao.getDescricao());
            stmt.setString(5, movimentacao.getOrigem());
            stmt.setInt(6, movimentacao.getContasFinanceiras_idcontasFinanceiras());

            stmt.executeUpdate();
        }
    }

    public List<MovimentacoesFinanceiras> listar() throws SQLException {
        List<MovimentacoesFinanceiras> lista = new ArrayList<>();

        String sql = "SELECT * FROM movimentacoesFinanceiras "
                + "ORDER BY idmovimentacoesFinanceiras DESC";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                lista.add(mapearMovimentacao(rs));
            }
        }

        return lista;
    }

    public MovimentacoesFinanceiras buscarPorId(int id)
            throws SQLException {

        String sql = "SELECT * FROM movimentacoesFinanceiras "
                + "WHERE idmovimentacoesFinanceiras = ?";

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

    public List<MovimentacoesFinanceiras> buscarPorConta(int idConta)
            throws SQLException {

        List<MovimentacoesFinanceiras> lista = new ArrayList<>();

        String sql = "SELECT * FROM movimentacoesFinanceiras "
                + "WHERE contasFinanceiras_idcontasFinanceiras = ? "
                + "ORDER BY idmovimentacoesFinanceiras DESC";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idConta);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapearMovimentacao(rs));
                }
            }
        }

        return lista;
    }

    public List<MovimentacoesFinanceiras> buscarPorTipo(String tipo)
            throws SQLException {

        List<MovimentacoesFinanceiras> lista = new ArrayList<>();

        String sql = "SELECT * FROM movimentacoesFinanceiras "
                + "WHERE tipo = ? "
                + "ORDER BY idmovimentacoesFinanceiras DESC";

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

    public List<MovimentacoesFinanceiras> buscarPorOrigem(String origem)
            throws SQLException {

        List<MovimentacoesFinanceiras> lista = new ArrayList<>();

        String sql = "SELECT * FROM movimentacoesFinanceiras "
                + "WHERE origem LIKE ? "
                + "ORDER BY idmovimentacoesFinanceiras DESC";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + origem + "%");

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapearMovimentacao(rs));
                }
            }
        }

        return lista;
    }

    public List<MovimentacoesFinanceiras> buscarPorData(String data)
            throws SQLException {

        List<MovimentacoesFinanceiras> lista = new ArrayList<>();

        String sql = "SELECT * FROM movimentacoesFinanceiras "
                + "WHERE dataHora LIKE ? "
                + "ORDER BY idmovimentacoesFinanceiras DESC";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + data + "%");

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapearMovimentacao(rs));
                }
            }
        }

        return lista;
    }

    public void atualizar(MovimentacoesFinanceiras movimentacao)
            throws SQLException {

        String sql = "UPDATE movimentacoesFinanceiras SET "
                + "tipo = ?, valor = ?, dataHora = ?, descricao = ?, "
                + "origem = ?, contasFinanceiras_idcontasFinanceiras = ? "
                + "WHERE idmovimentacoesFinanceiras = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, movimentacao.getTipo());
            stmt.setDouble(2, movimentacao.getValor());
            stmt.setString(3, movimentacao.getDataHora());
            stmt.setString(4, movimentacao.getDescricao());
            stmt.setString(5, movimentacao.getOrigem());
            stmt.setInt(6, movimentacao.getContasFinanceiras_idcontasFinanceiras());
            stmt.setInt(7, movimentacao.getIdmovimentacoesFinanceiras());

            stmt.executeUpdate();
        }
    }

    public void excluir(int id) throws SQLException {
        String sql = "DELETE FROM movimentacoesFinanceiras "
                + "WHERE idmovimentacoesFinanceiras = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    private MovimentacoesFinanceiras mapearMovimentacao(ResultSet rs)
            throws SQLException {

        MovimentacoesFinanceiras movimentacao =
                new MovimentacoesFinanceiras();

        movimentacao.setIdmovimentacoesFinanceiras(
                rs.getInt("idmovimentacoesFinanceiras"));

        movimentacao.setTipo(rs.getString("tipo"));
        movimentacao.setValor(rs.getDouble("valor"));
        movimentacao.setDataHora(rs.getString("dataHora"));
        movimentacao.setDescricao(rs.getString("descricao"));
        movimentacao.setOrigem(rs.getString("origem"));

        movimentacao.setContasFinanceiras_idcontasFinanceiras(
                rs.getInt("contasFinanceiras_idcontasFinanceiras"));

        return movimentacao;
    }
}
