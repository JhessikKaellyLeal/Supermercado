
package Control;

import Conection.Conexao;
import Model.Turnos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class TurnosController {

    public void cadastrar(Turnos turno) throws SQLException {

        String sql = "INSERT INTO turnos "
                + "(nome, descricao, horaInicio, horaSaida, "
                + "intervaloInicio, intervaloFinal, duracaoTurno, "
                + "turnoNoturno, status) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, turno.getNome());

            if (turno.getDescricao() == null) {
                stmt.setNull(2, Types.VARCHAR);
            } else {
                stmt.setString(2, turno.getDescricao());
            }

            stmt.setString(3, turno.getHoraInicio());
            stmt.setString(4, turno.getHoraSaida());
            stmt.setString(5, turno.getIntervaloInicio());
            stmt.setString(6, turno.getIntervaloFinal());
            stmt.setInt(7, turno.getDuracaoTurno());

            if (turno.getTurnoNoturno() == null) {
                stmt.setNull(8, Types.TINYINT);
            } else {
                stmt.setInt(8, turno.getTurnoNoturno());
            }

            stmt.setInt(9, turno.getStatus());

            stmt.executeUpdate();
        }
    }

    public List<Turnos> listar() throws SQLException {

        List<Turnos> lista = new ArrayList<>();

        String sql = "SELECT * FROM turnos ORDER BY nome ASC";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                lista.add(mapearTurno(rs));
            }
        }

        return lista;
    }

    public Turnos buscarPorId(int id) throws SQLException {

        String sql = "SELECT * FROM turnos WHERE idturnos = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapearTurno(rs);
                }
            }
        }

        return null;
    }

    public List<Turnos> buscarPorNome(String nome)
            throws SQLException {

        List<Turnos> lista = new ArrayList<>();

        String sql = "SELECT * FROM turnos "
                + "WHERE nome LIKE ? ORDER BY nome ASC";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + nome + "%");

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapearTurno(rs));
                }
            }
        }

        return lista;
    }

    public List<Turnos> buscarPorStatus(int status)
            throws SQLException {

        List<Turnos> lista = new ArrayList<>();

        String sql = "SELECT * FROM turnos "
                + "WHERE status = ? ORDER BY nome ASC";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, status);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapearTurno(rs));
                }
            }
        }

        return lista;
    }

    public List<Turnos> listarAtivos() throws SQLException {

        List<Turnos> lista = new ArrayList<>();

        String sql = "SELECT * FROM turnos "
                + "WHERE status = 1 ORDER BY nome ASC";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                lista.add(mapearTurno(rs));
            }
        }

        return lista;
    }

    public List<Turnos> buscarPorTurnoNoturno(int turnoNoturno)
            throws SQLException {

        List<Turnos> lista = new ArrayList<>();

        String sql = "SELECT * FROM turnos "
                + "WHERE turnoNoturno = ? ORDER BY nome ASC";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, turnoNoturno);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapearTurno(rs));
                }
            }
        }

        return lista;
    }

    public void atualizar(Turnos turno) throws SQLException {

        String sql = "UPDATE turnos SET "
                + "nome = ?, descricao = ?, horaInicio = ?, "
                + "horaSaida = ?, intervaloInicio = ?, "
                + "intervaloFinal = ?, duracaoTurno = ?, "
                + "turnoNoturno = ?, status = ? "
                + "WHERE idturnos = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, turno.getNome());

            if (turno.getDescricao() == null) {
                stmt.setNull(2, Types.VARCHAR);
            } else {
                stmt.setString(2, turno.getDescricao());
            }

            stmt.setString(3, turno.getHoraInicio());
            stmt.setString(4, turno.getHoraSaida());
            stmt.setString(5, turno.getIntervaloInicio());
            stmt.setString(6, turno.getIntervaloFinal());
            stmt.setInt(7, turno.getDuracaoTurno());

            if (turno.getTurnoNoturno() == null) {
                stmt.setNull(8, Types.TINYINT);
            } else {
                stmt.setInt(8, turno.getTurnoNoturno());
            }

            stmt.setInt(9, turno.getStatus());
            stmt.setInt(10, turno.getIdturnos());

            stmt.executeUpdate();
        }
    }

    public void alterarStatus(int id, int status)
            throws SQLException {

        String sql = "UPDATE turnos SET status = ? "
                + "WHERE idturnos = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, status);
            stmt.setInt(2, id);

            stmt.executeUpdate();
        }
    }

    public void excluir(int id) throws SQLException {

        String sql = "DELETE FROM turnos WHERE idturnos = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    private Turnos mapearTurno(ResultSet rs) throws SQLException {

        Turnos turno = new Turnos();

        turno.setIdturnos(rs.getInt("idturnos"));
        turno.setNome(rs.getString("nome"));
        turno.setDescricao(rs.getString("descricao"));
        turno.setHoraInicio(rs.getString("horaInicio"));
        turno.setHoraSaida(rs.getString("horaSaida"));
        turno.setIntervaloInicio(rs.getString("intervaloInicio"));
        turno.setIntervaloFinal(rs.getString("intervaloFinal"));
        turno.setDuracaoTurno(rs.getInt("duracaoTurno"));

        int turnoNoturno = rs.getInt("turnoNoturno");
        turno.setTurnoNoturno(
                rs.wasNull() ? null : turnoNoturno
        );

        turno.setStatus(rs.getInt("status"));

        return turno;
    }
}
