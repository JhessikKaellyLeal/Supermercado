
package Control;

import Conection.Conexao;
import Model.Ferias;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FeriasController {

    public void cadastrar(Ferias ferias) throws SQLException {
        String sql = "INSERT INTO ferias (inicio, `final`, situacao) "
                + "VALUES (?, ?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, ferias.getInicio());
            stmt.setString(2, ferias.getFinalFerias());
            stmt.setString(3, ferias.getSituacao());

            stmt.executeUpdate();
        }
    }

    public List<Ferias> listar() throws SQLException {
        List<Ferias> lista = new ArrayList<>();

        String sql = "SELECT * FROM ferias";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                lista.add(mapearFerias(rs));
            }
        }

        return lista;
    }

    public Ferias buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM ferias WHERE idferias = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapearFerias(rs);
                }
            }
        }

        return null;
    }

    public void atualizar(Ferias ferias) throws SQLException {
        String sql = "UPDATE ferias SET "
                + "inicio = ?, `final` = ?, situacao = ? "
                + "WHERE idferias = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, ferias.getInicio());
            stmt.setString(2, ferias.getFinalFerias());
            stmt.setString(3, ferias.getSituacao());
            stmt.setInt(4, ferias.getIdferias());

            stmt.executeUpdate();
        }
    }

    public void excluir(int id) throws SQLException {
        String sql = "DELETE FROM ferias WHERE idferias = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public List<Ferias> buscarPorSituacao(String situacao)
            throws SQLException {

        List<Ferias> lista = new ArrayList<>();

        String sql = "SELECT * FROM ferias WHERE situacao LIKE ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + situacao + "%");

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapearFerias(rs));
                }
            }
        }

        return lista;
    }

    private Ferias mapearFerias(ResultSet rs) throws SQLException {
        Ferias ferias = new Ferias();

        ferias.setIdferias(rs.getInt("idferias"));
        ferias.setInicio(rs.getString("inicio"));
        ferias.setFinalFerias(rs.getString("final"));
        ferias.setSituacao(rs.getString("situacao"));

        return ferias;
    }
}
