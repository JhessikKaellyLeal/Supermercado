
package Control;

import Conection.Conexao;
import Model.Promocao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PromocaoController {

    public void cadastrar(Promocao promocao) throws SQLException {
        String sql = "INSERT INTO promocao "
                + "(nome, tipo, status, dataInicio, dataFinal, "
                + "regras, desconto, qttMinima) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, promocao.getNome());
            stmt.setString(2, promocao.getTipo());
            stmt.setInt(3, promocao.getStatus());
            stmt.setString(4, promocao.getDataInicio());
            stmt.setString(5, promocao.getDataFinal());

            if (promocao.getRegras() == null) {
                stmt.setNull(6, java.sql.Types.VARCHAR);
            } else {
                stmt.setString(6, promocao.getRegras());
            }

            stmt.setInt(7, promocao.getDesconto());
            stmt.setInt(8, promocao.getQttMinima());

            stmt.executeUpdate();
        }
    }

    public List<Promocao> listar() throws SQLException {
        List<Promocao> lista = new ArrayList<>();

        String sql = "SELECT * FROM promocao ORDER BY idpromocao DESC";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                lista.add(mapearPromocao(rs));
            }
        }

        return lista;
    }

    public Promocao buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM promocao WHERE idpromocao = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapearPromocao(rs);
                }
            }
        }

        return null;
    }

    public List<Promocao> buscarPorNome(String nome)
            throws SQLException {

        List<Promocao> lista = new ArrayList<>();

        String sql = "SELECT * FROM promocao WHERE nome LIKE ? "
                + "ORDER BY nome ASC";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + nome + "%");

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapearPromocao(rs));
                }
            }
        }

        return lista;
    }

    public List<Promocao> buscarPorTipo(String tipo)
            throws SQLException {

        List<Promocao> lista = new ArrayList<>();

        String sql = "SELECT * FROM promocao WHERE tipo = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, tipo);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapearPromocao(rs));
                }
            }
        }

        return lista;
    }

    public List<Promocao> buscarPorStatus(int status)
            throws SQLException {

        List<Promocao> lista = new ArrayList<>();

        String sql = "SELECT * FROM promocao WHERE status = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, status);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapearPromocao(rs));
                }
            }
        }

        return lista;
    }

    public List<Promocao> buscarPorPeriodo(
            String dataInicio, String dataFinal) throws SQLException {

        List<Promocao> lista = new ArrayList<>();

        String sql = "SELECT * FROM promocao "
                + "WHERE dataInicio <= ? AND dataFinal >= ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, dataFinal);
            stmt.setString(2, dataInicio);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapearPromocao(rs));
                }
            }
        }

        return lista;
    }

    public List<Promocao> listarPromocoesAtivas()
            throws SQLException {

        List<Promocao> lista = new ArrayList<>();

        String sql = "SELECT * FROM promocao "
                + "WHERE status = 1 "
                + "AND dataInicio <= CURDATE() "
                + "AND dataFinal >= CURDATE() "
                + "ORDER BY dataFinal ASC";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                lista.add(mapearPromocao(rs));
            }
        }

        return lista;
    }

    public void atualizar(Promocao promocao) throws SQLException {
        String sql = "UPDATE promocao SET "
                + "nome = ?, tipo = ?, status = ?, "
                + "dataInicio = ?, dataFinal = ?, regras = ?, "
                + "desconto = ?, qttMinima = ? "
                + "WHERE idpromocao = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, promocao.getNome());
            stmt.setString(2, promocao.getTipo());
            stmt.setInt(3, promocao.getStatus());
            stmt.setString(4, promocao.getDataInicio());
            stmt.setString(5, promocao.getDataFinal());

            if (promocao.getRegras() == null) {
                stmt.setNull(6, java.sql.Types.VARCHAR);
            } else {
                stmt.setString(6, promocao.getRegras());
            }

            stmt.setInt(7, promocao.getDesconto());
            stmt.setInt(8, promocao.getQttMinima());
            stmt.setInt(9, promocao.getIdpromocao());

            stmt.executeUpdate();
        }
    }

    public void alterarStatus(int id, int status)
            throws SQLException {

        String sql = "UPDATE promocao SET status = ? "
                + "WHERE idpromocao = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, status);
            stmt.setInt(2, id);

            stmt.executeUpdate();
        }
    }

    public void excluir(int id) throws SQLException {
        String sql = "DELETE FROM promocao WHERE idpromocao = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    private Promocao mapearPromocao(ResultSet rs)
            throws SQLException {

        Promocao promocao = new Promocao();

        promocao.setIdpromocao(rs.getInt("idpromocao"));
        promocao.setNome(rs.getString("nome"));
        promocao.setTipo(rs.getString("tipo"));
        promocao.setStatus(rs.getInt("status"));
        promocao.setDataInicio(rs.getString("dataInicio"));
        promocao.setDataFinal(rs.getString("dataFinal"));
        promocao.setRegras(rs.getString("regras"));
        promocao.setDesconto(rs.getInt("desconto"));
        promocao.setQttMinima(rs.getInt("qttMinima"));

        return promocao;
    }
}
