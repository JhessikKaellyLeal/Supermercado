package Control;




import Model.Funcionario;
import Conection.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioController {

    // Cadastrar funcionário
    public boolean cadastrar(
            String nome,
            String dataNascimento,
            String cpf,
            String telefone,
            String dataAdmissao,
            int statusFuncionario,
            int cargos_idcargos,
            int setores_idsetores,
            int turnos_idturnos) {

        String sql = "INSERT INTO Funcionario "
                + "(nome, dataNascimento, cpf, telefone, "
                + "dataAdmissao, statusFuncionario, "
                + "cargos_idcargos, setores_idsetores, "
                + "turnos_idturnos) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, nome);
            stmt.setString(2, dataNascimento);
            stmt.setString(3, cpf);
            stmt.setString(4, telefone);
            stmt.setString(5, dataAdmissao);
            stmt.setInt(6, statusFuncionario);
            stmt.setInt(7, cargos_idcargos);
            stmt.setInt(8, setores_idsetores);
            stmt.setInt(9, turnos_idturnos);

            return stmt.executeUpdate() > 0;

        } catch (SQLException erro) {
            System.out.println("Erro ao cadastrar funcionário: "
                    + erro.getMessage());
            return false;
        }
    }

    // Consultar funcionário pelo CPF
    public ResultSet consultarPorCPF(String cpf) {

        String sql = "SELECT * FROM Funcionario WHERE cpf = ?";

        try {
            Connection conexao = Conexao.conectar();
            PreparedStatement stmt = conexao.prepareStatement(sql);

            stmt.setString(1, cpf);

            return stmt.executeQuery();

        } catch (SQLException erro) {
            System.out.println("Erro ao consultar funcionário: "
                    + erro.getMessage());
            return null;
        }
    }

    // Consultar funcionário pelo ID
    public ResultSet consultarPorID(int idFuncionario) {

        String sql = "SELECT * FROM Funcionario "
                + "WHERE idFuncionario = ?";

        try {
            Connection conexao = Conexao.conectar();
            PreparedStatement stmt = conexao.prepareStatement(sql);

            stmt.setInt(1, idFuncionario);

            return stmt.executeQuery();

        } catch (SQLException erro) {
            System.out.println("Erro ao consultar funcionário: "
                    + erro.getMessage());
            return null;
        }
    }

    // Listar todos os funcionários
    public ResultSet listarFuncionarios() {

        String sql = "SELECT * FROM Funcionario";

        try {
            Connection conexao = Conexao.conectar();
            PreparedStatement stmt = conexao.prepareStatement(sql);

            return stmt.executeQuery();

        } catch (SQLException erro) {
            System.out.println("Erro ao listar funcionários: "
                    + erro.getMessage());
            return null;
        }
    }

    // Atualizar funcionário
    public boolean atualizar(
            int idFuncionario,
            String nome,
            String dataNascimento,
            String cpf,
            String telefone,
            String dataAdmissao,
            int statusFuncionario,
            int cargos_idcargos,
            int setores_idsetores,
            int turnos_idturnos) {

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

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, nome);
            stmt.setString(2, dataNascimento);
            stmt.setString(3, cpf);
            stmt.setString(4, telefone);
            stmt.setString(5, dataAdmissao);
            stmt.setInt(6, statusFuncionario);
            stmt.setInt(7, cargos_idcargos);
            stmt.setInt(8, setores_idsetores);
            stmt.setInt(9, turnos_idturnos);
            stmt.setInt(10, idFuncionario);

            return stmt.executeUpdate() > 0;

        } catch (SQLException erro) {
            System.out.println("Erro ao atualizar funcionário: "
                    + erro.getMessage());
            return false;
        }
    }

    // Excluir funcionário
    public boolean excluir(int idFuncionario) {

        String sql = "DELETE FROM Funcionario "
                + "WHERE idFuncionario = ?";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, idFuncionario);

            return stmt.executeUpdate() > 0;

        } catch (SQLException erro) {
            System.out.println("Erro ao excluir funcionário: "
                    + erro.getMessage());
            return false;
        }
    }

    

public List<String[]> listar() {

    List<String[]> lista = new ArrayList<>();

    String sql = "SELECT idFuncionario, nome FROM Funcionario "
            + "ORDER BY nome";

    try (Connection conexao = Conexao.conectar();
         PreparedStatement stmt = conexao.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {

            String[] funcionario = new String[2];

            funcionario[0] = String.valueOf(
                    rs.getInt("idFuncionario"));

            funcionario[1] = rs.getString("nome");

            lista.add(funcionario);
        }

    } catch (SQLException erro) {

        System.out.println("Erro ao listar funcionários: "
                + erro.getMessage());
    }

    return lista;
}


}
