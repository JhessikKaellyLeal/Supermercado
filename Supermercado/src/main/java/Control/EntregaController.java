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
public class EntregaController {
    public boolean cadastrar(
            String dataEntrega,
            String horarioEntrega,
            String statusEntrega,
            int idCliente,
            int idVeiculo,
            int idTaxa,
            int idFuncionario,
            int idCargo,
            int idSetor,
            int idTurno) throws SQLException {

        String sql = "INSERT INTO entregas "
                + "(dataEntrega, horarioEntrega, statusEntrega, "
                + "cliente_idcliente, veiculos_idveiculos, "
                + "taxasEntrega_idtaxasEntrega, "
                + "Funcionario_idFuncionario, "
                + "Funcionario_cargos_idcargos, "
                + "Funcionario_setores_idsetores, "
                + "Funcionario_turnos_idturnos) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = Conexao.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, dataEntrega);
            ps.setString(2, horarioEntrega);
            ps.setString(3, statusEntrega);
            ps.setInt(4, idCliente);
            ps.setInt(5, idVeiculo);
            ps.setInt(6, idTaxa);
            ps.setInt(7, idFuncionario);
            ps.setInt(8, idCargo);
            ps.setInt(9, idSetor);
            ps.setInt(10, idTurno);

            return ps.executeUpdate() > 0;
        }
    }

    public List<Map<String, Object>> listar() throws SQLException {

        List<Map<String, Object>> lista = new ArrayList<>();

        String sql = "SELECT * FROM entregas";

        try (Connection con = Conexao.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Map<String, Object> item = new LinkedHashMap<>();

                ResultSetMetaData meta = rs.getMetaData();

                for (int i = 1; i <= meta.getColumnCount(); i++) {
                    item.put(
                        meta.getColumnLabel(i),
                        rs.getObject(i)
                    );
                }

                lista.add(item);
            }
        }

        return lista;
    }

    public boolean atualizar(
            int id,
            String dataEntrega,
            String horarioEntrega,
            String statusEntrega,
            int idCliente,
            int idVeiculo,
            int idTaxa,
            int idFuncionario,
            int idCargo,
            int idSetor,
            int idTurno) throws SQLException {

        String sql = "UPDATE entregas SET "
                + "dataEntrega=?, horarioEntrega=?, statusEntrega=?, "
                + "cliente_idcliente=?, veiculos_idveiculos=?, "
                + "taxasEntrega_idtaxasEntrega=?, "
                + "Funcionario_idFuncionario=?, "
                + "Funcionario_cargos_idcargos=?, "
                + "Funcionario_setores_idsetores=?, "
                + "Funcionario_turnos_idturnos=? "
                + "WHERE identregas=?";

        try (Connection con = Conexao.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, dataEntrega);
            ps.setString(2, horarioEntrega);
            ps.setString(3, statusEntrega);
            ps.setInt(4, idCliente);
            ps.setInt(5, idVeiculo);
            ps.setInt(6, idTaxa);
            ps.setInt(7, idFuncionario);
            ps.setInt(8, idCargo);
            ps.setInt(9, idSetor);
            ps.setInt(10, idTurno);
            ps.setInt(11, id);

            return ps.executeUpdate() > 0;
        }
    }

    public boolean excluir(int id) throws SQLException {

        String sql = "DELETE FROM entregas WHERE identregas=?";

        try (Connection con = Conexao.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            return ps.executeUpdate() > 0;
        }
    } 
}
