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
public class CargosController {
 public boolean cadastrar(String nome, String descricao,
            double salarioBase, int cargaHoraria,
            double periculosidade, double insalubridade,
            int status, double adicionalNoturno) throws SQLException {

        String sql = "INSERT INTO cargos "
            + "(nome, descricao, salarioBase, cargaHoraria, "
            + "adicionalPericulosidade, adicionalInsalubridade, "
            + "statusCargo, adicionalNoturno) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = Conexao.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, nome);
            ps.setString(2, descricao);
            ps.setDouble(3, salarioBase);
            ps.setInt(4, cargaHoraria);
            ps.setDouble(5, periculosidade);
            ps.setDouble(6, insalubridade);
            ps.setInt(7, status);
            ps.setDouble(8, adicionalNoturno);
            return ps.executeUpdate() > 0;
        }
    }

    public List<Map<String, Object>> listar() throws SQLException {
        List<Map<String, Object>> lista = new ArrayList<>();
        try (Connection con = Conexao.conectar();
             PreparedStatement ps = con.prepareStatement("SELECT * FROM cargos");
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

    public boolean atualizar(int id, String nome, String descricao,
            double salarioBase, int cargaHoraria,
            double periculosidade, double insalubridade,
            int status, double adicionalNoturno) throws SQLException {

        String sql = "UPDATE cargos SET nome=?, descricao=?, salarioBase=?, "
            + "cargaHoraria=?, adicionalPericulosidade=?, "
            + "adicionalInsalubridade=?, statusCargo=?, adicionalNoturno=? "
            + "WHERE idcargos=?";

        try (Connection con = Conexao.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, nome);
            ps.setString(2, descricao);
            ps.setDouble(3, salarioBase);
            ps.setInt(4, cargaHoraria);
            ps.setDouble(5, periculosidade);
            ps.setDouble(6, insalubridade);
            ps.setInt(7, status);
            ps.setDouble(8, adicionalNoturno);
            ps.setInt(9, id);
            return ps.executeUpdate() > 0;
        }
    }

    public boolean excluir(int id) throws SQLException {
        try (Connection con = Conexao.conectar();
             PreparedStatement ps = con.prepareStatement(
                 "DELETE FROM cargos WHERE idcargos=?")) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }
    
}
