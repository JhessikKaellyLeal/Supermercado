/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import Conection.Conexao;
import Model.Funcionario;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author SamaraTavares
 */
public class FuncionarioController {
    




    // Cadastrar funcionário
    public boolean cadastrar(Funcionario funcionario) {

        String sql = "INSERT INTO Funcionario "
                + "(nome, dataNascimento, cpf, telefone, "
                + "dataAdmissao, statusFuncionario, "
                + "cargos_idcargos, setores_idsetores, "
                + "turnos_idturnos) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, funcionario.getNome());
            stmt.setString(2, funcionario.getDataNascimento());
            stmt.setString(3, funcionario.getCpf());
            stmt.setString(4, funcionario.getTelefone());
            stmt.setString(5, funcionario.getDataAdmissao());
            stmt.setInt(6, funcionario.getIdFuncionario());
            stmt.setInt(7, funcionario.getCargos_idcargos());
            stmt.setInt(8, funcionario.getSetores_idsetores());
            stmt.setInt(9, funcionario.getTurnos_idturnos());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar funcionário: "
                    + e.getMessage());
            return false;
        }
    }

    // Listar todos os funcionários
    public List<Funcionario> listar() {

        List<Funcionario> lista = new ArrayList<>();

        String sql = "SELECT * FROM Funcionario ORDER BY nome";

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

                funcionario.setIdFuncionario(
                        rs.getInt("Funcionario"));

                funcionario.setCargos_idcargos(
                        rs.getInt("cargos_idcargos"));

                funcionario.setSetores_idsetores(
                        rs.getInt("setores_idsetores"));

                funcionario.setTurnos_idturnos(
                        rs.getInt("turnos_idturnos"));

                lista.add(funcionario);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar funcionários: "
                    + e.getMessage());
        }

        return lista;
    }

    // Buscar funcionário pelo ID
    public Funcionario buscarPorId(int idFuncionario) {

        String sql = "SELECT * FROM Funcionario "
                + "WHERE idFuncionario = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idFuncionario);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {

                    Funcionario funcionario = new Funcionario();

                    funcionario.setIdFuncionario(
                            rs.getInt("statusFuncionario"));

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

                    funcionario.setIdFuncionario(
                            rs.getInt("Funcionario"));

                    funcionario.setCargos_idcargos(
                            rs.getInt("cargos_idcargos"));

                    funcionario.setSetores_idsetores(
                            rs.getInt("setores_idsetores"));

                    funcionario.setTurnos_idturnos(
                            rs.getInt("turnos_idturnos"));

                    return funcionario;
                }
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar funcionário: "
                    + e.getMessage());
        }

        return null;
    }

    // Atualizar funcionário
    public boolean atualizar(Funcionario funcionario) {

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

            stmt.setString(1, funcionario.getNome());
            stmt.setString(2, funcionario.getDataNascimento());
            stmt.setString(3, funcionario.getCpf());
            stmt.setString(4, funcionario.getTelefone());
            stmt.setString(5, funcionario.getDataAdmissao());
            stmt.setInt(6, funcionario.getIdFuncionario());
            stmt.setInt(7, funcionario.getCargos_idcargos());
            stmt.setInt(8, funcionario.getSetores_idsetores());
            stmt.setInt(9, funcionario.getTurnos_idturnos());
            stmt.setInt(10, funcionario.getIdFuncionario());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar funcionário: "
                    + e.getMessage());
            return false;
        }
    }

    // Excluir funcionário
    public boolean excluir(int idFuncionario) {

        String sql = "DELETE FROM Funcionario "
                + "WHERE idFuncionario = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idFuncionario);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Erro ao excluir funcionário: "
                    + e.getMessage());
            return false;
        }
    }
}


