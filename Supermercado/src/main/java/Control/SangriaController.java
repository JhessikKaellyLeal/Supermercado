/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control;

/**
 *
 * @author AntônioVieira
 */
import Conection.Conexao;
import java.sql.*;
import java.util.*;

public class SangriaController {
   public boolean cadastrar(String data, String motivo, String observacao,
            double valorSolicitado, double valorRetirado, String horario,
            int idAbertura, int idFuncionario, int idCargo,
            int idSetor, int idTurno) throws SQLException {

        String sql = "INSERT INTO sangria "
            + "(dataSolicitacao, motivo, observacao, valorSolicitado, "
            + "valorRetirado, horaSolicitacao, aberturaCaixa_idaberturaCaixa, "
            + "Funcionario_idFuncionario, Funcionario_cargos_idcargos, "
            + "Funcionario_setores_idsetores, Funcionario_turnos_idturnos) "
            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = Conexao.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, data);
            ps.setString(2, motivo);
            ps.setString(3, observacao);
            ps.setDouble(4, valorSolicitado);
            ps.setDouble(5, valorRetirado);
            ps.setString(6, horario);
            ps.setInt(7, idAbertura);
            ps.setInt(8, idFuncionario);
            ps.setInt(9, idCargo);
            ps.setInt(10, idSetor);
            ps.setInt(11, idTurno);
            return ps.executeUpdate() > 0;
        }
    }

    public List<Map<String, Object>> listar() throws SQLException {
        List<Map<String, Object>> lista = new ArrayList<>();
        try (Connection con = Conexao.conectar();
             PreparedStatement ps = con.prepareStatement("SELECT * FROM sangria");
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Map<String, Object> m = new LinkedHashMap<>();
                ResultSetMetaData md = rs.getMetaData();
                for (int i = 1; i <= md.getColumnCount(); i++)
                    m.put(md.getColumnLabel(i), rs.getObject(i));
                lista.add(m);
            }
        }
        return lista;
    }

    public boolean atualizar(int id, String data, String motivo,
            String observacao, double solicitado, double retirado,
            String horario, int idAbertura, int idFuncionario,
            int idCargo, int idSetor, int idTurno) throws SQLException {

        String sql = "UPDATE sangria SET dataSolicitacao=?, motivo=?, "
            + "observacao=?, valorSolicitado=?, valorRetirado=?, "
            + "horaSolicitacao=?, aberturaCaixa_idaberturaCaixa=?, "
            + "Funcionario_idFuncionario=?, Funcionario_cargos_idcargos=?, "
            + "Funcionario_setores_idsetores=?, Funcionario_turnos_idturnos=? "
            + "WHERE idsangria=?";

        try (Connection con = Conexao.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, data);
            ps.setString(2, motivo);
            ps.setString(3, observacao);
            ps.setDouble(4, solicitado);
            ps.setDouble(5, retirado);
            ps.setString(6, horario);
            ps.setInt(7, idAbertura);
            ps.setInt(8, idFuncionario);
            ps.setInt(9, idCargo);
            ps.setInt(10, idSetor);
            ps.setInt(11, idTurno);
            ps.setInt(12, id);
            return ps.executeUpdate() > 0;
        }
    }

    public boolean excluir(int id) throws SQLException {
        try (Connection con = Conexao.conectar();
             PreparedStatement ps = con.prepareStatement(
                 "DELETE FROM sangria WHERE idsangria=?")) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }  
}
