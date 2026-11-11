
package Control;

import Conection.Conexao;
import Model.UnidadesMedidas;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class UnidadeMedidasController {
    
    public int buscarIdPorNome(String nome) throws SQLException {

    String sql = "SELECT idunidadeMedidas FROM unidadeMedidas WHERE nomeUnidade = ?";

    try (Connection conn = Conexao.conectar();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, nome);

        try (ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                return rs.getInt("idunidadeMedidas");
            }
        }
    }

    return 0;
}

    public void cadastrar(UnidadesMedidas unidade) throws SQLException {

        String sql = "INSERT INTO unidadeMedidas "
                + "(codigo, nome, status, descricao) "
                + "VALUES (?, ?, ?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, unidade.getCodigo());
            stmt.setString(2, unidade.getNome());
            stmt.setInt(3, unidade.getStatus());

            if (unidade.getDescricao() == null) {
                stmt.setNull(4, Types.VARCHAR);
            } else {
                stmt.setString(4, unidade.getDescricao());
            }

            stmt.executeUpdate();
        }
    }

    public List<UnidadesMedidas> listar() throws SQLException {

        List<UnidadesMedidas> lista = new ArrayList<>();

        String sql = "SELECT * FROM unidadeMedidas ORDER BY nome ASC";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                lista.add(mapearUnidade(rs));
            }
        }

        return lista;
    }

    public UnidadesMedidas buscarPorId(int id) throws SQLException {

        String sql = "SELECT * FROM unidadeMedidas "
                + "WHERE idunidadeMedidas = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapearUnidade(rs);
                }
            }
        }

        return null;
    }

    public List<UnidadesMedidas> buscarPorNome(String nome)
            throws SQLException {

        List<UnidadesMedidas> lista = new ArrayList<>();

        String sql = "SELECT * FROM unidadeMedidas "
                + "WHERE nome LIKE ? ORDER BY nome ASC";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + nome + "%");

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapearUnidade(rs));
                }
            }
        }

        return lista;
    }

    public UnidadesMedidas buscarPorCodigo(int codigo)
            throws SQLException {

        String sql = "SELECT * FROM unidadeMedidas WHERE codigo = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, codigo);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapearUnidade(rs);
                }
            }
        }

        return null;
    }

    public List<UnidadesMedidas> buscarPorStatus(int status)
            throws SQLException {

        List<UnidadesMedidas> lista = new ArrayList<>();

        String sql = "SELECT * FROM unidadeMedidas "
                + "WHERE status = ? ORDER BY nome ASC";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, status);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapearUnidade(rs));
                }
            }
        }

        return lista;
    }

    public List<UnidadesMedidas> listarAtivas() throws SQLException {

        List<UnidadesMedidas> lista = new ArrayList<>();

        String sql = "SELECT * FROM unidadeMedidas "
                + "WHERE status = 1 ORDER BY nome ASC";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                lista.add(mapearUnidade(rs));
            }
        }

        return lista;
    }

    public void atualizar(UnidadesMedidas unidade) throws SQLException {

        String sql = "UPDATE unidadeMedidas SET "
                + "codigo = ?, nome = ?, status = ?, descricao = ? "
                + "WHERE idunidadeMedidas = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, unidade.getCodigo());
            stmt.setString(2, unidade.getNome());
            stmt.setInt(3, unidade.getStatus());

            if (unidade.getDescricao() == null) {
                stmt.setNull(4, Types.VARCHAR);
            } else {
                stmt.setString(4, unidade.getDescricao());
            }

            stmt.setInt(5, unidade.getIdunidadeMedidas());

            stmt.executeUpdate();
        }
    }

    public void alterarStatus(int id, int status)
            throws SQLException {

        String sql = "UPDATE unidadeMedidas SET status = ? "
                + "WHERE idunidadeMedidas = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, status);
            stmt.setInt(2, id);

            stmt.executeUpdate();
        }
    }

    public void excluir(int id) throws SQLException {

        String sql = "DELETE FROM unidadeMedidas "
                + "WHERE idunidadeMedidas = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    private UnidadesMedidas mapearUnidade(ResultSet rs)
            throws SQLException {

        UnidadesMedidas unidade = new UnidadesMedidas();

        unidade.setIdunidadeMedidas(
                rs.getInt("idunidadeMedidas")
        );
        unidade.setCodigo(rs.getInt("codigo"));
        unidade.setNome(rs.getString("nome"));
        unidade.setStatus(rs.getInt("status"));
        unidade.setDescricao(rs.getString("descricao"));

        return unidade;
    }
}
