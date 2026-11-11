
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

    // LISTAR TODOS OS FUNCIONÁRIOS
    public List<Funcionario> listarTodos() {

        List<Funcionario> funcionarios = new ArrayList<>();

        String sql = "SELECT "
                + "idFuncionario, nome, dataNascimento, "
                + "cpf, telefone, dataAdmissao, "
                + "statusFuncionario, cargos_idcargos, "
                + "setores_idsetores, turnos_idturnos "
                + "FROM Funcionario "
                + "ORDER BY nome";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                Funcionario funcionario = new Funcionario();

                funcionario.setIdFuncionario(
                        rs.getInt("idFuncionario"));

                funcionario.setNome(
                        rs.getString("nome"));

                funcionario.setDataNascimento(
                        rs.getString("dataNascimento"));

                funcionario.setCpf(
                        rs.getString("cpf"));

                funcionario.setTelefone(
                        rs.getString("telefone"));

                funcionario.setDataAdmissao(
                        rs.getString("dataAdmissao"));

                funcionario.setStatusFuncionario(
                        rs.getString("statusFuncionario"));

                funcionario.setCargos_idcargos(
                        rs.getInt("cargos_idcargos"));

                funcionario.setSetores_idsetores(
                        rs.getInt("setores_idsetores"));

                funcionario.setTurnos_idturnos(
                        rs.getInt("turnos_idturnos"));

                funcionarios.add(funcionario);
            }

        } catch (SQLException e) {

            System.err.println(
                    "Erro ao listar funcionários: "
                    + e.getMessage()
            );
        }

        return funcionarios;
    }
}
