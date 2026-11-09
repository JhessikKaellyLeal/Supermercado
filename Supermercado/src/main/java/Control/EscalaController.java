/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control;

/**
 *
 * @author ArthurGiuvannucci
 */


import Conection.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EscalaController {

    // Cadastrar escala
    public boolean cadastrar(
            String periodoInicial,
            String periodoFinal,
            int idFuncionario,
            int idCargo,
            int idSetor,
            int idTurno) {

        String sql = "INSERT INTO escala "
                + "(periodoInicial, periodoFinal, "
                + "Funcionario_idFuncionario, "
                + "Funcionario_cargos_idcargos, "
                + "Funcionario_setores_idsetores, "
                + "Funcionario_turnos_idturnos) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, periodoInicial);
            stmt.setString(2, periodoFinal);
            stmt.setInt(3, idFuncionario);
            stmt.setInt(4, idCargo);
            stmt.setInt(5, idSetor);
            stmt.setInt(6, idTurno);

            return stmt.executeUpdate() > 0;

        } catch (SQLException erro) {

            System.out.println("Erro ao cadastrar escala: "
                    + erro.getMessage());

            return false;
        }
    }

    // Consultar escala pelo ID
    public ResultSet consultarPorID(int idEscala) {

        String sql = "SELECT * FROM escala WHERE idescala = ?";

        try {
            Connection conexao = Conexao.conectar();
            PreparedStatement stmt = conexao.prepareStatement(sql);

            stmt.setInt(1, idEscala);

            return stmt.executeQuery();

        } catch (SQLException erro) {

            System.out.println("Erro ao consultar escala: "
                    + erro.getMessage());

            return null;
        }
    }

    // Listar todas as escalas
    public ResultSet listarEscalas() {

        String sql = "SELECT * FROM escala";

        try {
            Connection conexao = Conexao.conectar();
            PreparedStatement stmt = conexao.prepareStatement(sql);

            return stmt.executeQuery();

        } catch (SQLException erro) {

            System.out.println("Erro ao listar escalas: "
                    + erro.getMessage());

            return null;
        }
    }

    // Excluir escala
    public boolean excluir(int idEscala) {

        String sql = "DELETE FROM escala WHERE idescala = ?";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, idEscala);

            return stmt.executeUpdate() > 0;

        } catch (SQLException erro) {

            System.out.println("Erro ao excluir escala: "
                    + erro.getMessage());

            return false;
        }
    }

    // Atualizar escala
    public boolean atualizar(
            int idEscala,
            String periodoInicial,
            String periodoFinal,
            int idFuncionario,
            int idCargo,
            int idSetor,
            int idTurno) {

        String sql = "UPDATE escala SET "
                + "periodoInicial = ?, "
                + "periodoFinal = ?, "
                + "Funcionario_idFuncionario = ?, "
                + "Funcionario_cargos_idcargos = ?, "
                + "Funcionario_setores_idsetores = ?, "
                + "Funcionario_turnos_idturnos = ? "
                + "WHERE idescala = ?";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, periodoInicial);
            stmt.setString(2, periodoFinal);
            stmt.setInt(3, idFuncionario);
            stmt.setInt(4, idCargo);
            stmt.setInt(5, idSetor);
            stmt.setInt(6, idTurno);
            stmt.setInt(7, idEscala);

            return stmt.executeUpdate() > 0;

        } catch (SQLException erro) {

            System.out.println("Erro ao atualizar escala: "
                    + erro.getMessage());

            return false;
        }
    }

}
