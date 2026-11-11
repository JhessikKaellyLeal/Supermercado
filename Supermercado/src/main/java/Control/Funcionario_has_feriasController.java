
package Control;

import Conection.Conexao;
import Model.Funcionario_has_Ferias;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Funcionario_has_feriasController {

    public void cadastrar(Funcionario_has_Ferias vinculo)
            throws SQLException {

        String sql = "INSERT INTO Funcionario_has_ferias "
                + "(Funcionario_idFuncionario, "
                + "Funcionario_cargos_idcargos, "
                + "Funcionario_setores_idsetores, "
                + "Funcionario_turnos_idturnos, "
                + "ferias_idferias) "
                + "VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, vinculo.getFuncionario_idFuncionario());
            stmt.setInt(2, vinculo.getFuncionario_cargos_idcargos());
            stmt.setInt(3, vinculo.getFuncionario_setores_idsetores());
            stmt.setInt(4, vinculo.getFuncionario_turnos_idturnos());
            stmt.setInt(5, vinculo.getFerias_idferias());

            stmt.executeUpdate();
        }
    }

    public List<Funcionario_has_Ferias> listar() throws SQLException {
        List<Funcionario_has_Ferias> lista = new ArrayList<>();

        String sql = "SELECT * FROM Funcionario_has_ferias";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                lista.add(mapearVinculo(rs));
            }
        }

        return lista;
    }

    public List<Funcionario_has_Ferias> buscarPorFuncionario(
            int idFuncionario) throws SQLException {

        List<Funcionario_has_Ferias> lista = new ArrayList<>();

        String sql = "SELECT * FROM Funcionario_has_ferias "
                + "WHERE Funcionario_idFuncionario = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idFuncionario);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapearVinculo(rs));
                }
            }
        }

        return lista;
    }

    public List<Funcionario_has_Ferias> buscarPorFerias(
            int idFerias) throws SQLException {

        List<Funcionario_has_Ferias> lista = new ArrayList<>();

        String sql = "SELECT * FROM Funcionario_has_ferias "
                + "WHERE ferias_idferias = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idFerias);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapearVinculo(rs));
                }
            }
        }

        return lista;
    }

    public void excluir(int idFuncionario, int idCargo, int idSetor,
            int idTurno, int idFerias) throws SQLException {

        String sql = "DELETE FROM Funcionario_has_ferias "
                + "WHERE Funcionario_idFuncionario = ? "
                + "AND Funcionario_cargos_idcargos = ? "
                + "AND Funcionario_setores_idsetores = ? "
                + "AND Funcionario_turnos_idturnos = ? "
                + "AND ferias_idferias = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idFuncionario);
            stmt.setInt(2, idCargo);
            stmt.setInt(3, idSetor);
            stmt.setInt(4, idTurno);
            stmt.setInt(5, idFerias);

            stmt.executeUpdate();
        }
    }

    private Funcionario_has_Ferias mapearVinculo(ResultSet rs)
            throws SQLException {

        Funcionario_has_Ferias vinculo = new Funcionario_has_Ferias();

        vinculo.setFuncionario_idFuncionario(
                rs.getInt("Funcionario_idFuncionario"));

        vinculo.setFuncionario_cargos_idcargos(
                rs.getInt("Funcionario_cargos_idcargos"));

        vinculo.setFuncionario_setores_idsetores(
                rs.getInt("Funcionario_setores_idsetores"));

        vinculo.setFuncionario_turnos_idturnos(
                rs.getInt("Funcionario_turnos_idturnos"));

        vinculo.setFerias_idferias(
                rs.getInt("ferias_idferias"));

        return vinculo;
    }
}
