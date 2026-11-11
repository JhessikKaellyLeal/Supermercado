
package Control;

import Conection.Conexao;
import Model.Funcionario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioController {

    public void cadastrar(Funcionario funcionario) throws SQLException {
        String sql = "INSERT INTO Funcionario "
                + "(nome, dataNascimento, cpf, telefone, dataAdmissao, "
                + "statusFuncionario, cargos_idcargos, setores_idsetores, "
                + "turnos_idturnos) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, funcionario.getNome());
            stmt.setString(2, funcionario.getDataNascimento());
            stmt.setString(3, funcionario.getCpf());
            stmt.setString(4, funcionario.getTelefone());
            stmt.setString(5, funcionario.getDataAdmissao());
            stmt.setInt(6, funcionario.getStatusFuncionario());
            stmt.setInt(7, funcionario.getCargos_idcargos());
            stmt.setInt(8, funcionario.getSetores_idsetores());
            stmt.setInt(9, funcionario.getTurnos_idturnos());

            stmt.executeUpdate();
        }
    }

    public List<Funcionario> listar() throws SQLException {
        List<Funcionario> lista = new ArrayList<>();

        String sql = "SELECT * FROM Funcionario";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                lista.add(mapearFuncionario(rs));
            }
        }

        return lista;
    }

    public Funcionario buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM Funcionario "
                + "WHERE idFuncionario = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapearFuncionario(rs);
                }
            }
        }

        return null;
    }

    public Funcionario buscarPorCpf(String cpf) throws SQLException {
        String sql = "SELECT * FROM Funcionario WHERE cpf = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cpf);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapearFuncionario(rs);
                }
            }
        }

        return null;
    }

    public List<Funcionario> buscarPorNome(String nome)
            throws SQLException {

        List<Funcionario> lista = new ArrayList<>();

        String sql = "SELECT * FROM Funcionario WHERE nome LIKE ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + nome + "%");

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapearFuncionario(rs));
                }
            }
        }

        return lista;
    }

    public List<Funcionario> buscarPorStatus(int status)
            throws SQLException {

        List<Funcionario> lista = new ArrayList<>();

        String sql = "SELECT * FROM Funcionario "
                + "WHERE statusFuncionario = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, status);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapearFuncionario(rs));
                }
            }
        }

        return lista;
    }

    public void atualizar(Funcionario funcionario) throws SQLException {
        String sql = "UPDATE Funcionario SET "
                + "nome = ?, dataNascimento = ?, cpf = ?, telefone = ?, "
                + "dataAdmissao = ?, statusFuncionario = ?, "
                + "cargos_idcargos = ?, setores_idsetores = ?, "
                + "turnos_idturnos = ? "
                + "WHERE idFuncionario = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, funcionario.getNome());
            stmt.setString(2, funcionario.getDataNascimento());
            stmt.setString(3, funcionario.getCpf());
            stmt.setString(4, funcionario.getTelefone());
            stmt.setString(5, funcionario.getDataAdmissao());
            stmt.setInt(6, funcionario.getStatusFuncionario());
            stmt.setInt(7, funcionario.getCargos_idcargos());
            stmt.setInt(8, funcionario.getSetores_idsetores());
            stmt.setInt(9, funcionario.getTurnos_idturnos());
            stmt.setInt(10, funcionario.getIdFuncionario());

            stmt.executeUpdate();
        }
    }

    public void excluir(int id) throws SQLException {
        String sql = "DELETE FROM Funcionario "
                + "WHERE idFuncionario = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    private Funcionario mapearFuncionario(ResultSet rs)
            throws SQLException {

        Funcionario funcionario = new Funcionario();

        funcionario.setIdFuncionario(rs.getInt("idFuncionario"));
        funcionario.setNome(rs.getString("nome"));
        funcionario.setDataNascimento(rs.getString("dataNascimento"));
        funcionario.setCpf(rs.getString("cpf"));
        funcionario.setTelefone(rs.getString("telefone"));
        funcionario.setDataAdmissao(rs.getString("dataAdmissao"));
        funcionario.setStatusFuncionario(rs.getInt("statusFuncionario"));
        funcionario.setCargos_idcargos(rs.getInt("cargos_idcargos"));
        funcionario.setSetores_idsetores(rs.getInt("setores_idsetores"));
        funcionario.setTurnos_idturnos(rs.getInt("turnos_idturnos"));

        return funcionario;
    }
}
