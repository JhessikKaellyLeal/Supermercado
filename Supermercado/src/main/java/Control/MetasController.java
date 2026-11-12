
package Control;

import Conection.Conexao;
import Model.Metas;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MetasController {

    public void cadastrar(Metas meta) throws SQLException {
        String sql = "INSERT INTO metas "
                + "(tipo, periodoInicial, periodoFinal, metaPrevista, "
                + "resultadoAtual, porcentagemAtingido, situacao, "
                + "funcionario_idFuncionario) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, meta.getTipo());
            stmt.setString(2, meta.getPeriodoInicial());
            stmt.setString(3, meta.getPeriodoFinal());
            stmt.setDouble(4, meta.getMetaPrevista());

            if (meta.getResultadoAtual() == null) {
                stmt.setNull(5, java.sql.Types.DOUBLE);
            } else {
                stmt.setDouble(5, meta.getResultadoAtual());
            }

            if (meta.getPorcentagemAtingida() == null) {
                stmt.setNull(6, java.sql.Types.DOUBLE);
            } else {
                stmt.setDouble(6, meta.getPorcentagemAtingida());
            }

            stmt.setString(7, meta.getSituacao());
            stmt.setInt(8, meta.getFuncionario_idFuncionario());

            stmt.executeUpdate();
        }
    }

    public List<Metas> listar() throws SQLException {
        List<Metas> lista = new ArrayList<>();

        String sql = "SELECT * FROM metas ORDER BY idmetas DESC";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                lista.add(mapearMeta(rs));
            }
        }

        return lista;
    }

    public Metas buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM metas WHERE idmetas = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapearMeta(rs);
                }
            }
        }

        return null;
    }

    public List<Metas> buscarPorFuncionario(int idFuncionario)
            throws SQLException {

        List<Metas> lista = new ArrayList<>();

        String sql = "SELECT * FROM metas "
                + "WHERE funcionario_idFuncionario = ? "
                + "ORDER BY idmetas DESC";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idFuncionario);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapearMeta(rs));
                }
            }
        }

        return lista;
    }

    public List<Metas> buscarPorSituacao(String situacao)
            throws SQLException {

        List<Metas> lista = new ArrayList<>();

        String sql = "SELECT * FROM metas WHERE situacao = ? "
                + "ORDER BY idmetas DESC";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, situacao);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapearMeta(rs));
                }
            }
        }

        return lista;
    }

    public List<Metas> buscarPorTipo(String tipo) throws SQLException {
        List<Metas> lista = new ArrayList<>();

        String sql = "SELECT * FROM metas WHERE tipo LIKE ? "
                + "ORDER BY idmetas DESC";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + tipo + "%");

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapearMeta(rs));
                }
            }
        }

        return lista;
    }

    public void atualizar(Metas meta) throws SQLException {
        String sql = "UPDATE metas SET "
                + "tipo = ?, periodoInicial = ?, periodoFinal = ?, "
                + "metaPrevista = ?, resultadoAtual = ?, "
                + "porcentagemAtingido = ?, situacao = ?, "
                + "funcionario_idFuncionario = ? "
                + "WHERE idmetas = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, meta.getTipo());
            stmt.setString(2, meta.getPeriodoInicial());
            stmt.setString(3, meta.getPeriodoFinal());
            stmt.setDouble(4, meta.getMetaPrevista());

            if (meta.getResultadoAtual() == null) {
                stmt.setNull(5, java.sql.Types.DOUBLE);
            } else {
                stmt.setDouble(5, meta.getResultadoAtual());
            }

            if (meta.getPorcentagemAtingida() == null) {
                stmt.setNull(6, java.sql.Types.DOUBLE);
            } else {
                stmt.setDouble(6, meta.getPorcentagemAtingida());
            }

            stmt.setString(7, meta.getSituacao());
            stmt.setInt(8, meta.getFuncionario_idFuncionario());
            stmt.setInt(9, meta.getIdmetas());

            stmt.executeUpdate();
        }
    }

    public void excluir(int id) throws SQLException {
        String sql = "DELETE FROM metas WHERE idmetas = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    private Metas mapearMeta(ResultSet rs) throws SQLException {
        Metas meta = new Metas();

        meta.setIdmetas(rs.getInt("idmetas"));
        meta.setTipo(rs.getString("tipo"));
        meta.setPeriodoInicial(rs.getString("periodoInicial"));
        meta.setPeriodoFinal(rs.getString("periodoFinal"));
        meta.setMetaPrevista(rs.getDouble("metaPrevista"));

        double resultado = rs.getDouble("resultadoAtual");
        meta.setResultadoAtual(
                rs.wasNull() ? null : resultado
        );

        double porcentagem = rs.getDouble("porcentagemAtingido");
        meta.setPorcentagemAtingida(
                rs.wasNull() ? null : porcentagem
        );

        meta.setSituacao(rs.getString("situacao"));
        meta.setFuncionario_idFuncionario(
                rs.getInt("funcionario_idFuncionario"));

        return meta;
    }
}
