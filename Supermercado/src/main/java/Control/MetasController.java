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
public class MetasController {

    public boolean cadastrar(
            String tipo,
            String periodoInicial,
            String periodoFinal,
            double metaPrevista,
            Double resultadoAtual,
            Double porcentagemAtingido,
            String situacao,
            int idFuncionario,
            int idCargo,
            int idSetor,
            int idTurno) throws SQLException {

        String sql = "INSERT INTO metas "
                + "(tipo, periodoInicial, periodoFinal, "
                + "metaPrevista, resultadoAtual, "
                + "porcentagemAtingido, situacao, "
                + "Funcionario_idFuncionario, "
                + "Funcionario_cargos_idcargos, "
                + "Funcionario_setores_idsetores, "
                + "Funcionario_turnos_idturnos) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = Conexao.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, tipo);
            ps.setString(2, periodoInicial);
            ps.setString(3, periodoFinal);
            ps.setDouble(4, metaPrevista);
            ps.setObject(5, resultadoAtual);
            ps.setObject(6, porcentagemAtingido);
            ps.setString(7, situacao);
            ps.setInt(8, idFuncionario);
            ps.setInt(9, idCargo);
            ps.setInt(10, idSetor);
            ps.setInt(11, idTurno);

            return ps.executeUpdate() > 0;
        }
    }

    public List<String[]> listar() throws SQLException {
        List<String[]> lista = new ArrayList<>();

        String sql = "SELECT idmetas, tipo, periodoInicial, "
                + "periodoFinal, metaPrevista, resultadoAtual, "
                + "porcentagemAtingido, situacao, "
                + "Funcionario_idFuncionario "
                + "FROM metas ORDER BY idmetas";

        try (Connection con = Conexao.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(new String[]{
                    String.valueOf(rs.getInt("idmetas")),
                    rs.getString("tipo"),
                    rs.getString("periodoInicial"),
                    rs.getString("periodoFinal"),
                    String.valueOf(rs.getDouble("metaPrevista")),
                    rs.getObject("resultadoAtual") == null
                        ? "" : String.valueOf(
                            rs.getDouble("resultadoAtual")),
                    rs.getObject("porcentagemAtingido") == null
                        ? "" : String.valueOf(
                            rs.getDouble("porcentagemAtingido")),
                    rs.getString("situacao"),
                    String.valueOf(
                        rs.getInt("Funcionario_idFuncionario"))
                });
            }
        }

        return lista;
    }

    public boolean atualizar(
            int idMeta,
            String tipo,
            String periodoInicial,
            String periodoFinal,
            double metaPrevista,
            Double resultadoAtual,
            Double porcentagemAtingido,
            String situacao,
            int idFuncionario,
            int idCargo,
            int idSetor,
            int idTurno) throws SQLException {

        String sql = "UPDATE metas SET tipo=?, periodoInicial=?, "
                + "periodoFinal=?, metaPrevista=?, resultadoAtual=?, "
                + "porcentagemAtingido=?, situacao=?, "
                + "Funcionario_idFuncionario=?, "
                + "Funcionario_cargos_idcargos=?, "
                + "Funcionario_setores_idsetores=?, "
                + "Funcionario_turnos_idturnos=? "
                + "WHERE idmetas=?";

        try (Connection con = Conexao.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, tipo);
            ps.setString(2, periodoInicial);
            ps.setString(3, periodoFinal);
            ps.setDouble(4, metaPrevista);
            ps.setObject(5, resultadoAtual);
            ps.setObject(6, porcentagemAtingido);
            ps.setString(7, situacao);
            ps.setInt(8, idFuncionario);
            ps.setInt(9, idCargo);
            ps.setInt(10, idSetor);
            ps.setInt(11, idTurno);
            ps.setInt(12, idMeta);

            return ps.executeUpdate() > 0;
        }
    }

    public boolean excluir(int idMeta) throws SQLException {
        String sql = "DELETE FROM metas WHERE idmetas = ?";

        try (Connection con = Conexao.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idMeta);
            return ps.executeUpdate() > 0;
        }
    }
}
