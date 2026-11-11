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
import Model.Endereco;
import java.sql.*;
import java.util.*;
public class EnderecoController {
    // BUSCAR ID DO CLIENTE PELO CPF
public int buscarIdClientePorCpf(String cpf) throws SQLException {

    String sql = "SELECT idcliente FROM cliente WHERE cpf = ?";

    try (Connection conn = Conexao.conectar();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, cpf);

        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt("idcliente");
            }
        }
    }

    return -1;
}
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
    
    public Endereco buscarPorCliente(int idCliente) throws SQLException {

    String sql = "SELECT * FROM endereco WHERE cliente_idcliente = ?";

    try (Connection con = Conexao.conectar();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setInt(1, idCliente);

        try (ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {

                Endereco endereco = new Endereco();

                endereco.setIdEndereco(rs.getInt("idEndereco"));
                endereco.setBairro(rs.getString("bairro"));
                endereco.setCep(rs.getString("cep"));
                endereco.setCidade(rs.getString("cidade"));
                endereco.setEstado(rs.getString("estado"));
                endereco.setRua(rs.getString("rua"));
                endereco.setNumero(rs.getString("numero"));
                endereco.setCliente_idcliente(rs.getInt("cliente_idcliente"));

                return endereco;
            }
        }
    }

    return null;
}
}
