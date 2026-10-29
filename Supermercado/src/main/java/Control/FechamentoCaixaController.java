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
public class FechamentoCaixaController {
 public boolean cadastrar(String data, String horario, double valor,
            int idAbertura, int idFuncionario, int idCargo,
            int idSetor, int idTurno) throws SQLException {

        String sql = "INSERT INTO fechamentoCaixa "
            + "(dataFechamento, horarioFechamento, valorFinal, "
            + "aberturaCaixa_idaberturaCaixa, Funcionario_idFuncionario, "
            + "Funcionario_cargos_idcargos, Funcionario_setores_idsetores, "
            + "Funcionario_turnos_idturnos) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = Conexao.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, data);
            ps.setString(2, horario);
            ps.setDouble(3, valor);
            ps.setInt(4, idAbertura);
            ps.setInt(5, idFuncionario);
            ps.setInt(6, idCargo);
            ps.setInt(7, idSetor);
            ps.setInt(8, idTurno);
            return ps.executeUpdate() > 0;
        }
    }

    public List<Map<String, Object>> listar() throws SQLException {
        List<Map<String, Object>> lista = new ArrayList<>();
        try (Connection con = Conexao.conectar();
             PreparedStatement ps = con.prepareStatement(
                 "SELECT * FROM fechamentoCaixa");
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

    public boolean atualizar(int id, String data, String horario,
            double valor, int idAbertura, int idFuncionario,
            int idCargo, int idSetor, int idTurno) throws SQLException {

        String sql = "UPDATE fechamentoCaixa SET dataFechamento=?, "
            + "horarioFechamento=?, valorFinal=?, "
            + "aberturaCaixa_idaberturaCaixa=?, Funcionario_idFuncionario=?, "
            + "Funcionario_cargos_idcargos=?, Funcionario_setores_idsetores=?, "
            + "Funcionario_turnos_idturnos=? WHERE idfechamentoCaixa=?";

        try (Connection con = Conexao.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, data);
            ps.setString(2, horario);
            ps.setDouble(3, valor);
            ps.setInt(4, idAbertura);
            ps.setInt(5, idFuncionario);
            ps.setInt(6, idCargo);
            ps.setInt(7, idSetor);
            ps.setInt(8, idTurno);
            ps.setInt(9, id);
            return ps.executeUpdate() > 0;
        }
    }

    public boolean excluir(int id) throws SQLException {
        try (Connection con = Conexao.conectar();
             PreparedStatement ps = con.prepareStatement(
                 "DELETE FROM fechamentoCaixa WHERE idfechamentoCaixa=?")) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }
}   

