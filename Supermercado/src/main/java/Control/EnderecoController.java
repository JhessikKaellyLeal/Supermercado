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
public class EnderecoController {
 public boolean cadastrar(String bairro, String cep,
            String cidade, String estado, String rua,
            String numero, int idCliente) throws SQLException {

        String sql = "INSERT INTO endereco "
            + "(bairro, cep, cidade, estado, rua, numero, cliente_idcliente) "
            + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = Conexao.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, bairro);
            ps.setString(2, cep);
            ps.setString(3, cidade);
            ps.setString(4, estado);
            ps.setString(5, rua);
            ps.setString(6, numero);
            ps.setInt(7, idCliente);
            return ps.executeUpdate() > 0;
        }
    }

    public List<Map<String, Object>> listar() throws SQLException {
        List<Map<String, Object>> lista = new ArrayList<>();
        try (Connection con = Conexao.conectar();
             PreparedStatement ps = con.prepareStatement("SELECT * FROM endereco");
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

    public boolean atualizar(int id, String bairro, String cep,
            String cidade, String estado, String rua,
            String numero, int idCliente) throws SQLException {

        String sql = "UPDATE endereco SET bairro=?, cep=?, cidade=?, "
            + "estado=?, rua=?, numero=?, cliente_idcliente=? WHERE idEndereco=?";

        try (Connection con = Conexao.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, bairro);
            ps.setString(2, cep);
            ps.setString(3, cidade);
            ps.setString(4, estado);
            ps.setString(5, rua);
            ps.setString(6, numero);
            ps.setInt(7, idCliente);
            ps.setInt(8, id);
            return ps.executeUpdate() > 0;
        }
    }

    public boolean excluir(int id) throws SQLException {
        try (Connection con = Conexao.conectar();
             PreparedStatement ps = con.prepareStatement(
                 "DELETE FROM endereco WHERE idEndereco=?")) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }   
}
