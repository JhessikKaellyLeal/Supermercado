package Control;

import Conection.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Model.Turnos;

public class TurnosController {

    // Cadastrar turno
    public boolean cadastrar(
            String nome,
            String descricao,
            String horaInicio,
            String horaSaida,
            String intervaloInicio,
            String intervaloFinal,
            int duracaoTurno,
            int turnoNoturno,
            int status) {

        String sql = "INSERT INTO turnos "
                + "(nome, descricao, horaInicio, horaSaida, "
                + "intervaloInicio, intervaloFinal, duracaoTurno, "
                + "turnoNoturno, status) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, nome);
            stmt.setString(2, descricao);
            stmt.setString(3, horaInicio);
            stmt.setString(4, horaSaida);
            stmt.setString(5, intervaloInicio);
            stmt.setString(6, intervaloFinal);
            stmt.setInt(7, duracaoTurno);
            stmt.setInt(8, turnoNoturno);
            stmt.setInt(9, status);

            return stmt.executeUpdate() > 0;

        } catch (SQLException erro) {
            System.out.println("Erro ao cadastrar turno: "
                    + erro.getMessage());
            return false;
        }
    }

    // Listar todos os turnos (usado nas combobox)
    public List<Turnos> listar() {

        String sql = "SELECT idturnos, nome, descricao, horaInicio, "
                + "horaSaida, intervaloInicio, intervaloFinal, "
                + "duracaoTurno, turnoNoturno, status "
                + "FROM turnos ORDER BY nome";

        return consultarLista(sql);
    }

    // Listar somente turnos ativos
    public List<Turnos> listarAtivos() {

        String sql = "SELECT idturnos, nome, descricao, horaInicio, "
                + "horaSaida, intervaloInicio, intervaloFinal, "
                + "duracaoTurno, turnoNoturno, status "
                + "FROM turnos WHERE status = 1 ORDER BY nome";

        return consultarLista(sql);
    }

    // Consultar turno pelo ID
    public Turnos consultarPorId(int idTurno) {

        String sql = "SELECT idturnos, nome, descricao, horaInicio, "
                + "horaSaida, intervaloInicio, intervaloFinal, "
                + "duracaoTurno, turnoNoturno, status "
                + "FROM turnos WHERE idturnos = ?";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, idTurno);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return montarTurno(rs);
                }
            }

        } catch (SQLException erro) {
            System.out.println("Erro ao consultar turno: "
                    + erro.getMessage());
        }

        return null;
    }

    // Pesquisar turnos pelo nome
    public List<Turnos> pesquisarPorNome(String nome) {

        String sql = "SELECT idturnos, nome, descricao, horaInicio, "
                + "horaSaida, intervaloInicio, intervaloFinal, "
                + "duracaoTurno, turnoNoturno, status "
                + "FROM turnos WHERE nome LIKE ? ORDER BY nome";

        List<Turnos> lista = new ArrayList<>();

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, "%" + nome + "%");

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    lista.add(montarTurno(rs));
                }
            }

        } catch (SQLException erro) {
            System.out.println("Erro ao pesquisar turnos: "
                    + erro.getMessage());
        }

        return lista;
    }

    // Atualizar turno
    public boolean atualizar(
            int idTurno,
            String nome,
            String descricao,
            String horaInicio,
            String horaSaida,
            String intervaloInicio,
            String intervaloFinal,
            int duracaoTurno,
            int turnoNoturno,
            int status) {

        String sql = "UPDATE turnos SET nome = ?, descricao = ?, "
                + "horaInicio = ?, horaSaida = ?, "
                + "intervaloInicio = ?, intervaloFinal = ?, "
                + "duracaoTurno = ?, turnoNoturno = ?, status = ? "
                + "WHERE idturnos = ?";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, nome);
            stmt.setString(2, descricao);
            stmt.setString(3, horaInicio);
            stmt.setString(4, horaSaida);
            stmt.setString(5, intervaloInicio);
            stmt.setString(6, intervaloFinal);
            stmt.setInt(7, duracaoTurno);
            stmt.setInt(8, turnoNoturno);
            stmt.setInt(9, status);
            stmt.setInt(10, idTurno);

            return stmt.executeUpdate() > 0;

        } catch (SQLException erro) {
            System.out.println("Erro ao atualizar turno: "
                    + erro.getMessage());
            return false;
        }
    }

    // Alterar status (1 = ativo, 0 = inativo)
    public boolean alterarStatus(int idTurno, int status) {

        String sql = "UPDATE turnos SET status = ? WHERE idturnos = ?";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, status);
            stmt.setInt(2, idTurno);

            return stmt.executeUpdate() > 0;

        } catch (SQLException erro) {
            System.out.println("Erro ao alterar status do turno: "
                    + erro.getMessage());
            return false;
        }
    }

    // Excluir turno
    public boolean excluir(int idTurno) {

        String sql = "DELETE FROM turnos WHERE idturnos = ?";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, idTurno);

            return stmt.executeUpdate() > 0;

        } catch (SQLException erro) {
            System.out.println("Erro ao excluir turno: "
                    + erro.getMessage());
            return false;
        }
    }

    // ---------- Métodos auxiliares ----------

    // Executa um SELECT sem parâmetros e devolve a lista de turnos
    private List<Turnos> consultarLista(String sql) {

        List<Turnos> lista = new ArrayList<>();

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                lista.add(montarTurno(rs));
            }

        } catch (SQLException erro) {
            System.out.println("Erro ao listar turnos: "
                    + erro.getMessage());
        }

        return lista;
    }

    // Converte a linha atual do ResultSet em um objeto Turnos
    private Turnos montarTurno(ResultSet rs) throws SQLException {

        Turnos turno = new Turnos();

        turno.setIdturnos(rs.getInt("idturnos"));
        turno.setNome(rs.getString("nome"));
        turno.setDescricao(rs.getString("descricao"));
        turno.setHoraInicio(rs.getString("horaInicio"));
        turno.setHoraSaida(rs.getString("horaSaida"));
        turno.setIntervaloInicio(rs.getString("intervaloInicio"));
        turno.setIntervaloFinal(rs.getString("intervaloFinal"));
        turno.setDuracaoTurno(rs.getInt("duracaoTurno"));
        turno.setTurnoNoturno(rs.getBoolean("turnoNoturno"));
        turno.setStatus(rs.getBoolean("status"));

        return turno;
    }
}