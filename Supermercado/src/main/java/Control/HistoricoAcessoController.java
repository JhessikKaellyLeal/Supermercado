
package Control;

import Conection.Conexao;
import Model.HistoricosAcessos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HistoricoAcessoController {
    
    public List<HistoricosAcessos> listarPorNomeUsuario(String nomeUsuario)
        throws SQLException {

    List<HistoricosAcessos> lista = new ArrayList<>();

    String sql = "SELECT h.* FROM historicoAcessos h "
            + "INNER JOIN usuario u ON h.usuario_idusuario = u.idusuario "
            + "WHERE u.nome LIKE ? "
            + "ORDER BY h.idhistoricoAcesso DESC";

    try (Connection conn = Conexao.conectar();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, "%" + nomeUsuario + "%");

        try (ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                lista.add(mapearHistorico(rs));
            }
        }
    }

    return lista;
}

    public void cadastrar(HistoricosAcessos historico) throws SQLException {
        String sql = "INSERT INTO historicoAcessos "
                + "(dataAcesso, horarioAcesso, terminal_ip, dispositivo, "
                + "usuario_idusuario) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, historico.getDataAcesso());
            stmt.setString(2, historico.getHorarioAcesso());
            stmt.setString(3, historico.getTerminal_ip());
            stmt.setString(4, historico.getDispositivo());
            stmt.setInt(5, historico.getUsuario_idusuario());

            stmt.executeUpdate();
        }
    }

    public List<HistoricosAcessos> listar() throws SQLException {
        List<HistoricosAcessos> lista = new ArrayList<>();

        String sql = "SELECT * FROM historicoAcessos "
                + "ORDER BY idhistoricoAcessos DESC";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                lista.add(mapearHistorico(rs));
            }
        }

        return lista;
    }

    public HistoricosAcessos buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM historicoAcessos "
                + "WHERE idhistoricoAcessos = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapearHistorico(rs);
                }
            }
        }

        return null;
    }

    public List<HistoricosAcessos> buscarPorUsuario(int idUsuario)
            throws SQLException {

        List<HistoricosAcessos> lista = new ArrayList<>();

        String sql = "SELECT * FROM historicoAcessos "
                + "WHERE usuario_idusuario = ? "
                + "ORDER BY idhistoricoAcessos DESC";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idUsuario);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapearHistorico(rs));
                }
            }
        }

        return lista;
    }

    public List<HistoricosAcessos> buscarPorData(String data)
            throws SQLException {

        List<HistoricosAcessos> lista = new ArrayList<>();

        String sql = "SELECT * FROM historicoAcessos "
                + "WHERE dataAcesso = ? "
                + "ORDER BY horarioAcesso DESC";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, data);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapearHistorico(rs));
                }
            }
        }

        return lista;
    }

    public void atualizar(HistoricosAcessos historico) throws SQLException {
        String sql = "UPDATE historicoAcessos SET "
                + "dataAcesso = ?, horarioAcesso = ?, terminal_ip = ?, "
                + "dispositivo = ?, usuario_idusuario = ? "
                + "WHERE idhistoricoAcessos = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, historico.getDataAcesso());
            stmt.setString(2, historico.getHorarioAcesso());
            stmt.setString(3, historico.getTerminal_ip());
            stmt.setString(4, historico.getDispositivo());
            stmt.setInt(5, historico.getUsuario_idusuario());
            stmt.setInt(6, historico.getIdhistoricoAcessos());

            stmt.executeUpdate();
        }
    }

    public void excluir(int id) throws SQLException {
        String sql = "DELETE FROM historicoAcessos "
                + "WHERE idhistoricoAcessos = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    private HistoricosAcessos mapearHistorico(ResultSet rs)
            throws SQLException {

        HistoricosAcessos historico = new HistoricosAcessos();

        historico.setIdhistoricoAcessos(
                rs.getInt("idhistoricoAcessos"));

        historico.setDataAcesso(rs.getString("dataAcesso"));
        historico.setHorarioAcesso(rs.getString("horarioAcesso"));
        historico.setTerminal_ip(rs.getString("terminal_ip"));
        historico.setDispositivo(rs.getString("dispositivo"));

        historico.setUsuario_idusuario(
                rs.getInt("usuario_idusuario"));

        return historico;
    }
}
