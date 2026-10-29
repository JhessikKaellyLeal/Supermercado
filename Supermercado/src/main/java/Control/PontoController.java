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
public class PontoController {

    public boolean cadastrar(
            String data,
            String horario,
            String justificativa,
            byte[] arquivo,
            int idFuncionario,
            int idCargo,
            int idSetor,
            int idTurno) throws SQLException {

        String sql = "INSERT INTO ponto "
                + "(data, horario, justificativa, arquivo, "
                + "Funcionario_idFuncionario, "
                + "Funcionario_cargos_idcargos, "
                + "Funcionario_setores_idsetores, "
                + "Funcionario_turnos_idturnos) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = Conexao.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, data);
            ps.setString(2, horario);
            ps.setString(3, justificativa);

            if (arquivo != null) {
                ps.setBytes(4, arquivo);
            } else {
                ps.setNull(4, java.sql.Types.BLOB);
            }

            ps.setInt(5, idFuncionario);
            ps.setInt(6, idCargo);
            ps.setInt(7, idSetor);
            ps.setInt(8, idTurno);

            return ps.executeUpdate() > 0;
        }
    }

    public List<String[]> listar() throws SQLException {
        List<String[]> lista = new ArrayList<>();

        String sql = "SELECT idponto, data, horario, justificativa, "
                + "Funcionario_idFuncionario "
                + "FROM ponto ORDER BY idponto";

        try (Connection con = Conexao.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(new String[]{
                    String.valueOf(rs.getInt("idponto")),
                    rs.getString("data"),
                    rs.getString("horario"),
                    rs.getString("justificativa"),
                    String.valueOf(
                        rs.getInt("Funcionario_idFuncionario"))
                });
            }
        }

        return lista;
    }

    public boolean atualizar(
            int idPonto,
            String data,
            String horario,
            String justificativa,
            byte[] arquivo,
            int idFuncionario,
            int idCargo,
            int idSetor,
            int idTurno) throws SQLException {

        String sql = "UPDATE ponto SET data=?, horario=?, "
                + "justificativa=?, arquivo=?, "
                + "Funcionario_idFuncionario=?, "
                + "Funcionario_cargos_idcargos=?, "
                + "Funcionario_setores_idsetores=?, "
                + "Funcionario_turnos_idturnos=? "
                + "WHERE idponto=?";

        try (Connection con = Conexao.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, data);
            ps.setString(2, horario);
            ps.setString(3, justificativa);

            if (arquivo != null) {
                ps.setBytes(4, arquivo);
            } else {
                ps.setNull(4, java.sql.Types.BLOB);
            }

            ps.setInt(5, idFuncionario);
            ps.setInt(6, idCargo);
            ps.setInt(7, idSetor);
            ps.setInt(8, idTurno);
            ps.setInt(9, idPonto);

            return ps.executeUpdate() > 0;
        }
    }

    public boolean excluir(int idPonto) throws SQLException {
        String sql = "DELETE FROM ponto WHERE idponto = ?";

        try (Connection con = Conexao.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idPonto);
            return ps.executeUpdate() > 0;
        }
    }
}



