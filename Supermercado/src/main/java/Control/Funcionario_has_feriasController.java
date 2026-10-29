/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control;

import Conection.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ArthurGiuvannucci
 */
public class Funcionario_has_feriasController {

    public boolean cadastrar(
            int idFuncionario,
            int idCargo,
            int idSetor,
            int idTurno,
            int idFerias) throws SQLException {

        String sql = "INSERT INTO Funcionario_has_ferias "
                + "(Funcionario_idFuncionario, "
                + "Funcionario_cargos_idcargos, "
                + "Funcionario_setores_idsetores, "
                + "Funcionario_turnos_idturnos, "
                + "ferias_idferias) VALUES (?, ?, ?, ?, ?)";

        try (Connection con = Conexao.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idFuncionario);
            ps.setInt(2, idCargo);
            ps.setInt(3, idSetor);
            ps.setInt(4, idTurno);
            ps.setInt(5, idFerias);

            return ps.executeUpdate() > 0;
        }
    }

    public List<String[]> listar() throws SQLException {
        List<String[]> lista = new ArrayList<>();

        String sql = "SELECT Funcionario_idFuncionario, "
                + "Funcionario_cargos_idcargos, "
                + "Funcionario_setores_idsetores, "
                + "Funcionario_turnos_idturnos, "
                + "ferias_idferias "
                + "FROM Funcionario_has_ferias "
                + "ORDER BY Funcionario_idFuncionario";

        try (Connection con = Conexao.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(new String[]{
                    String.valueOf(
                        rs.getInt("Funcionario_idFuncionario")),
                    String.valueOf(
                        rs.getInt("Funcionario_cargos_idcargos")),
                    String.valueOf(
                        rs.getInt("Funcionario_setores_idsetores")),
                    String.valueOf(
                        rs.getInt("Funcionario_turnos_idturnos")),
                    String.valueOf(rs.getInt("ferias_idferias"))
                });
            }
        }

        return lista;
    }

    public boolean excluir(
            int idFuncionario,
            int idCargo,
            int idSetor,
            int idTurno,
            int idFerias) throws SQLException {

        String sql = "DELETE FROM Funcionario_has_ferias "
                + "WHERE Funcionario_idFuncionario=? "
                + "AND Funcionario_cargos_idcargos=? "
                + "AND Funcionario_setores_idsetores=? "
                + "AND Funcionario_turnos_idturnos=? "
                + "AND ferias_idferias=?";

        try (Connection con = Conexao.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idFuncionario);
            ps.setInt(2, idCargo);
            ps.setInt(3, idSetor);
            ps.setInt(4, idTurno);
            ps.setInt(5, idFerias);

            return ps.executeUpdate() > 0;
        }
    }
}
