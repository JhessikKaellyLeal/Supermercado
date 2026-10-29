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
public class AfastamentosController {

    public boolean cadastrar(
            String tipo,
            String inicio,
            String fim,
            String dias,
            String situacao,
            String dataRegistro,
            int idFuncionario,
            int idCargo,
            int idSetor,
            int idTurno) throws SQLException {

        String sql = "INSERT INTO afastamentos "
                + "(tipo, inicio, final, dias, situacao, dataRegistro, "
                + "Funcionario_idFuncionario, "
                + "Funcionario_cargos_idcargos, "
                + "Funcionario_setores_idsetores, "
                + "Funcionario_turnos_idturnos) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = Conexao.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, tipo);
            ps.setString(2, inicio);
            ps.setString(3, fim);
            ps.setString(4, dias);
            ps.setString(5, situacao);
            ps.setString(6, dataRegistro);
            ps.setInt(7, idFuncionario);
            ps.setInt(8, idCargo);
            ps.setInt(9, idSetor);
            ps.setInt(10, idTurno);

            return ps.executeUpdate() > 0;
        }
    }

    public List<String[]> listar() throws SQLException {

        List<String[]> lista = new ArrayList<>();

        String sql = "SELECT idafastamentos, tipo, inicio, final, "
                + "dias, situacao, dataRegistro, "
                + "Funcionario_idFuncionario "
                + "FROM afastamentos "
                + "ORDER BY idafastamentos";

        try (Connection con = Conexao.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                lista.add(new String[]{
                    String.valueOf(rs.getInt("idafastamentos")),
                    rs.getString("tipo"),
                    rs.getString("inicio"),
                    rs.getString("final"),
                    rs.getString("dias"),
                    rs.getString("situacao"),
                    rs.getString("dataRegistro"),
                    String.valueOf(
                        rs.getInt("Funcionario_idFuncionario"))
                });
            }
        }

        return lista;
    }

    public boolean atualizar(
            int idAfastamento,
            String tipo,
            String inicio,
            String fim,
            String dias,
            String situacao,
            String dataRegistro,
            int idFuncionario,
            int idCargo,
            int idSetor,
            int idTurno) throws SQLException {

        String sql = "UPDATE afastamentos SET "
                + "tipo=?, inicio=?, final=?, dias=?, situacao=?, "
                + "dataRegistro=?, "
                + "Funcionario_idFuncionario=?, "
                + "Funcionario_cargos_idcargos=?, "
                + "Funcionario_setores_idsetores=?, "
                + "Funcionario_turnos_idturnos=? "
                + "WHERE idafastamentos=?";

        try (Connection con = Conexao.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, tipo);
            ps.setString(2, inicio);
            ps.setString(3, fim);
            ps.setString(4, dias);
            ps.setString(5, situacao);
            ps.setString(6, dataRegistro);
            ps.setInt(7, idFuncionario);
            ps.setInt(8, idCargo);
            ps.setInt(9, idSetor);
            ps.setInt(10, idTurno);
            ps.setInt(11, idAfastamento);

            return ps.executeUpdate() > 0;
        }
    }

    public boolean excluir(int idAfastamento) throws SQLException {

        String sql = "DELETE FROM afastamentos "
                + "WHERE idafastamentos = ?";

        try (Connection con = Conexao.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idAfastamento);

            return ps.executeUpdate() > 0;
        }
    }
}