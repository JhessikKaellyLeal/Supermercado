
package Control;

import Conection.Conexao;
import Model.UnidadesMedidas;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UnidadeMedidasController {

    // CADASTRAR UNIDADE DE MEDIDA
    public boolean cadastrar(UnidadesMedidas unidade) throws SQLException {

        String sql = "INSERT INTO unidadeMedidas "
                + "(codigo, nome, status, descricao) "
                + "VALUES (?, ?, ?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, unidade.getCodigo());
            stmt.setString(2, unidade.getNome());
            stmt.setInt(3, unidade.getStatus());
            stmt.setString(4, unidade.getDescricao());

            return stmt.executeUpdate() > 0;
        }
    }

    // LISTAR TODAS AS UNIDADES DE MEDIDA
    public List<UnidadesMedidas> listarUnidadesMedidas() throws SQLException {

        List<UnidadesMedidas> lista = new ArrayList<>();

        String sql = "SELECT * FROM unidadeMedidas";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                UnidadesMedidas u = new UnidadesMedidas();

                u.setIdunidadeMedidas(
                        rs.getInt("idunidadeMedidas"));

                u.setCodigo(rs.getInt("codigo"));
                u.setNome(rs.getString("nome"));
                u.setStatus(rs.getInt("status"));
                u.setDescricao(rs.getString("descricao"));

                lista.add(u);
            }
        }

        return lista;
    }

    // LISTAR SOMENTE UNIDADES ATIVAS
    public List<UnidadesMedidas> listarUnidadesAtivas() throws SQLException {

        List<UnidadesMedidas> lista = new ArrayList<>();

        String sql = "SELECT * FROM unidadeMedidas WHERE status = 1";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                UnidadesMedidas u = new UnidadesMedidas();

                u.setIdunidadeMedidas(
                        rs.getInt("idunidadeMedidas"));

                u.setCodigo(rs.getInt("codigo"));
                u.setNome(rs.getString("nome"));
                u.setStatus(rs.getInt("status"));
                u.setDescricao(rs.getString("descricao"));

                lista.add(u);
            }
        }

        return lista;
    }

    // BUSCAR UNIDADE DE MEDIDA PELO ID
    public UnidadesMedidas buscarPorId(int id) throws SQLException {

        String sql = "SELECT * FROM unidadeMedidas "
                + "WHERE idunidadeMedidas = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {

                    UnidadesMedidas u = new UnidadesMedidas();

                    u.setIdunidadeMedidas(
                            rs.getInt("idunidadeMedidas"));

                    u.setCodigo(rs.getInt("codigo"));
                    u.setNome(rs.getString("nome"));
                    u.setStatus(rs.getInt("status"));
                    u.setDescricao(rs.getString("descricao"));

                    return u;
                }
            }
        }

        return null;
    }

    // ALTERAR UNIDADE DE MEDIDA
    public boolean alterar(UnidadesMedidas unidade) throws SQLException {

        String sql = "UPDATE unidadeMedidas SET "
                + "codigo = ?, nome = ?, status = ?, descricao = ? "
                + "WHERE idunidadeMedidas = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, unidade.getCodigo());
            stmt.setString(2, unidade.getNome());
            stmt.setInt(3, unidade.getStatus());
            stmt.setString(4, unidade.getDescricao());
            stmt.setInt(5, unidade.getIdunidadeMedidas());

            return stmt.executeUpdate() > 0;
        }
    }

    // EXCLUIR UNIDADE DE MEDIDA
    public boolean excluir(int id) throws SQLException {

        String sql = "DELETE FROM unidadeMedidas "
                + "WHERE idunidadeMedidas = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            return stmt.executeUpdate() > 0;
        }
    }
}
