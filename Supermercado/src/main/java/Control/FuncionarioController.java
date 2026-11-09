
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

    // MÉTODO AUXILIAR PARA CONVERTER RESULTSET EM FUNCIONÁRIO
    private Funcionario mapearFuncionario(ResultSet rs) throws SQLException {

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

    // CADASTRAR FUNCIONÁRIO
    public boolean cadastrar(
            String nome,
            String dataNascimento,
            String cpf,
            String telefone,
            String dataAdmissao,
            int status,
            int idCargo,
            int idSetor,
            int idTurno) {

        String sql = "INSERT INTO Funcionario "
                + "(nome, dataNascimento, cpf, telefone, dataAdmissao, "
                + "statusFuncionario, cargos_idcargos, setores_idsetores, "
                + "turnos_idturnos) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nome);
            stmt.setString(2, dataNascimento);
            stmt.setString(3, cpf);
            stmt.setString(4, telefone);
            stmt.setString(5, dataAdmissao);
            stmt.setInt(6, status);
            stmt.setInt(7, idCargo);
            stmt.setInt(8, idSetor);
            stmt.setInt(9, idTurno);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar funcionário:");
            e.printStackTrace();
            return false;

        } catch (RuntimeException e) {
            System.out.println("Erro inesperado ao cadastrar funcionário:");
            e.printStackTrace();
            return false;
        }
    }

    // LISTAR TODOS OS FUNCIONÁRIOS
    public List<Funcionario> listar() {

        List<Funcionario> funcionarios = new ArrayList<>();

        String sql = "SELECT idFuncionario, nome, dataNascimento, cpf, "
                + "telefone, dataAdmissao, statusFuncionario, "
                + "cargos_idcargos, setores_idsetores, turnos_idturnos "
                + "FROM Funcionario ORDER BY nome ASC";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                funcionarios.add(mapearFuncionario(rs));
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar funcionários:");
            e.printStackTrace();

        } catch (RuntimeException e) {
            System.out.println("Erro inesperado ao listar funcionários:");
            e.printStackTrace();
        }

        return funcionarios;
    }

    // BUSCAR FUNCIONÁRIO PELO ID
    public Funcionario buscarPorId(int idFuncionario) {

        String sql = "SELECT * FROM Funcionario WHERE idFuncionario = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idFuncionario);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {
                    return mapearFuncionario(rs);
                }
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar funcionário:");
            e.printStackTrace();

        } catch (RuntimeException e) {
            System.out.println("Erro inesperado ao buscar funcionário:");
            e.printStackTrace();
        }

        return null;
    }

    // ATUALIZAR FUNCIONÁRIO
    public boolean atualizar(
            int idFuncionario,
            String nome,
            String dataNascimento,
            String cpf,
            String telefone,
            String dataAdmissao,
            int status,
            int idCargo,
            int idSetor,
            int idTurno) {

        String sql = "UPDATE Funcionario SET "
                + "nome = ?, "
                + "dataNascimento = ?, "
                + "cpf = ?, "
                + "telefone = ?, "
                + "dataAdmissao = ?, "
                + "statusFuncionario = ?, "
                + "cargos_idcargos = ?, "
                + "setores_idsetores = ?, "
                + "turnos_idturnos = ? "
                + "WHERE idFuncionario = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nome);
            stmt.setString(2, dataNascimento);
            stmt.setString(3, cpf);
            stmt.setString(4, telefone);
            stmt.setString(5, dataAdmissao);
            stmt.setInt(6, status);
            stmt.setInt(7, idCargo);
            stmt.setInt(8, idSetor);
            stmt.setInt(9, idTurno);
            stmt.setInt(10, idFuncionario);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar funcionário:");
            e.printStackTrace();
            return false;

        } catch (RuntimeException e) {
            System.out.println("Erro inesperado ao atualizar funcionário:");
            e.printStackTrace();
            return false;
        }
    }

    // EXCLUIR FUNCIONÁRIO
    public boolean excluir(int idFuncionario) {

        String sql = "DELETE FROM Funcionario WHERE idFuncionario = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idFuncionario);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Erro ao excluir funcionário:");
            e.printStackTrace();
            return false;

        } catch (RuntimeException e) {
            System.out.println("Erro inesperado ao excluir funcionário:");
            e.printStackTrace();
            return false;
        }
    }

    // PESQUISAR FUNCIONÁRIO POR NOME OU CPF
    public List<Funcionario> pesquisar(String pesquisa) {

        List<Funcionario> funcionarios = new ArrayList<>();

        String sql = "SELECT * FROM Funcionario "
                + "WHERE nome LIKE ? OR cpf LIKE ? "
                + "ORDER BY nome ASC";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + pesquisa + "%");
            stmt.setString(2, "%" + pesquisa + "%");

            try (ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {
                    funcionarios.add(mapearFuncionario(rs));
                }
            }

        } catch (SQLException e) {
            System.out.println("Erro ao pesquisar funcionário:");
            e.printStackTrace();

        } catch (RuntimeException e) {
            System.out.println("Erro inesperado ao pesquisar funcionário:");
            e.printStackTrace();
        }

        return funcionarios;
    }
}
