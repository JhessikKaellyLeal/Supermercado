
package Control;

import Conection.Conexao;
import Model.Escala;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EscalaController {

    public void cadastrar(Escala escala) throws SQLException {
        String sql = "INSERT INTO escala "
                + "(periodoInicial, periodoFinal, Funcionario_idFuncionario, "
                + "Funcionario_cargos_idcargos, Funcionario_setores_idsetores, "
                + "Funcionario_turnos_idturnos) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, escala.getPeriodoInicial());
            stmt.setString(2, escala.getPeriodoFinal());
            stmt.setInt(3, escala.getFuncionario_idFuncionario());
            stmt.setInt(4, escala.getFuncionario_cargos_idcargos());
            stmt.setInt(5, escala.getFuncionario_setores_idsetores());
            stmt.setInt(6, escala.getFuncionario_turnos_idturnos());

            stmt.executeUpdate();
        }
    }

    public List<Escala> listar() throws SQLException {
        List<Escala> lista = new ArrayList<>();

        String sql = "SELECT * FROM escala";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                lista.add(mapearEscala(rs));
            }
        }

        return lista;
    }

    public Escala buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM escala WHERE idescala = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapearEscala(rs);
                }
            }
        }

        return null;
    }

    public void atualizar(Escala escala) throws SQLException {
        String sql = "UPDATE escala SET "
                + "periodoInicial = ?, "
                + "periodoFinal = ?, "
                + "Funcionario_idFuncionario = ?, "
                + "Funcionario_cargos_idcargos = ?, "
                + "Funcionario_setores_idsetores = ?, "
                + "Funcionario_turnos_idturnos = ? "
                + "WHERE idescala = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, escala.getPeriodoInicial());
            stmt.setString(2, escala.getPeriodoFinal());
            stmt.setInt(3, escala.getFuncionario_idFuncionario());
            stmt.setInt(4, escala.getFuncionario_cargos_idcargos());
            stmt.setInt(5, escala.getFuncionario_setores_idsetores());
            stmt.setInt(6, escala.getFuncionario_turnos_idturnos());
            stmt.setInt(7, escala.getIdescala());

            stmt.executeUpdate();
        }
    }

    public void excluir(int id) throws SQLException {
        String sql = "DELETE FROM escala WHERE idescala = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public List<Escala> buscarPorFuncionario(int idFuncionario) throws SQLException {
        List<Escala> lista = new ArrayList<>();

        String sql = "SELECT * FROM escala WHERE Funcionario_idFuncionario = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idFuncionario);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapearEscala(rs));
                }
            }
        }

        return lista;
    }

    private Escala mapearEscala(ResultSet rs) throws SQLException {
        Escala escala = new Escala();

        escala.setIdescala(rs.getInt("idescala"));
        escala.setPeriodoInicial(rs.getString("periodoInicial"));
        escala.setPeriodoFinal(rs.getString("periodoFinal"));
        escala.setFuncionario_idFuncionario(rs.getInt("Funcionario_idFuncionario"));
        escala.setFuncionario_cargos_idcargos(rs.getInt("Funcionario_cargos_idcargos"));
        escala.setFuncionario_setores_idsetores(rs.getInt("Funcionario_setores_idsetores"));
        escala.setFuncionario_turnos_idturnos(rs.getInt("Funcionario_turnos_idturnos"));

        return escala;
    }
}
