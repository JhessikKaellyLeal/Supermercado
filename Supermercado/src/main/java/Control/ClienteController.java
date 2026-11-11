
package Control;

import Conection.Conexao;
import Model.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteController {
    
    
    

    // CADASTRAR
    public boolean cadastrar(Cliente cliente) throws SQLException {

        String sql = "INSERT INTO cliente "
                + "(nome, cpf, rg, dataNascimento, genero, email, "
                + "telefone, status, senha) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(
                     sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getCpf());
            stmt.setString(3, cliente.getRg());
            stmt.setString(4, cliente.getDataNascimento());
            stmt.setString(5, cliente.getGenero());
            stmt.setString(6, cliente.getEmail());
            stmt.setString(7, cliente.getTelefone());
            stmt.setInt(8, cliente.getStatus());
            stmt.setString(9, cliente.getSenha());

            int linhas = stmt.executeUpdate();

            if (linhas > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        cliente.setIdcliente(rs.getInt(1));
                    }
                }

                return true;
            }
        }

        return false;
    }

    // LISTAR TODOS
    public List<Cliente> listar() throws SQLException {

        List<Cliente> lista = new ArrayList<>();

        String sql = "SELECT * FROM cliente ORDER BY idcliente DESC";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                Cliente cliente = new Cliente();

                cliente.setIdcliente(rs.getInt("idcliente"));
                cliente.setNome(rs.getString("nome"));
                cliente.setCpf(rs.getString("cpf"));
                cliente.setRg(rs.getString("rg"));
                cliente.setDataNascimento(rs.getString("dataNascimento"));
                cliente.setGenero(rs.getString("genero"));
                cliente.setEmail(rs.getString("email"));
                cliente.setTelefone(rs.getString("telefone"));
                cliente.setStatus(rs.getInt("status"));
                cliente.setSenha(rs.getString("senha"));

                lista.add(cliente);
            }
        }

        return lista;
    }

    // BUSCAR POR ID
    public Cliente buscarPorId(int id) throws SQLException {

        String sql = "SELECT * FROM cliente WHERE idcliente = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {

                    Cliente cliente = new Cliente();

                    cliente.setIdcliente(rs.getInt("idcliente"));
                    cliente.setNome(rs.getString("nome"));
                    cliente.setCpf(rs.getString("cpf"));
                    cliente.setRg(rs.getString("rg"));
                    cliente.setDataNascimento(rs.getString("dataNascimento"));
                    cliente.setGenero(rs.getString("genero"));
                    cliente.setEmail(rs.getString("email"));
                    cliente.setTelefone(rs.getString("telefone"));
                    cliente.setStatus(rs.getInt("status"));
                    cliente.setSenha(rs.getString("senha"));

                    return cliente;
                }
            }
        }

        return null;
    }

    // BUSCAR POR CPF
    public Cliente buscarPorCpf(String cpf) throws SQLException {

        String sql = "SELECT * FROM cliente WHERE cpf = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cpf);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {

                    Cliente cliente = new Cliente();

                    cliente.setIdcliente(rs.getInt("idcliente"));
                    cliente.setNome(rs.getString("nome"));
                    cliente.setCpf(rs.getString("cpf"));
                    cliente.setRg(rs.getString("rg"));
                    cliente.setDataNascimento(rs.getString("dataNascimento"));
                    cliente.setGenero(rs.getString("genero"));
                    cliente.setEmail(rs.getString("email"));
                    cliente.setTelefone(rs.getString("telefone"));
                    cliente.setStatus(rs.getInt("status"));
                    cliente.setSenha(rs.getString("senha"));

                    return cliente;
                }
            }
        }

        return null;
    }

    // ATUALIZAR
    public boolean atualizar(Cliente cliente) throws SQLException {

        String sql = "UPDATE cliente SET "
                + "nome = ?, "
                + "cpf = ?, "
                + "rg = ?, "
                + "dataNascimento = ?, "
                + "genero = ?, "
                + "email = ?, "
                + "telefone = ?, "
                + "status = ?, "
                + "senha = ? "
                + "WHERE idcliente = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getCpf());
            stmt.setString(3, cliente.getRg());
            stmt.setString(4, cliente.getDataNascimento());
            stmt.setString(5, cliente.getGenero());
            stmt.setString(6, cliente.getEmail());
            stmt.setString(7, cliente.getTelefone());
            stmt.setInt(8, cliente.getStatus());
            stmt.setString(9, cliente.getSenha());
            stmt.setInt(10, cliente.getIdcliente());

            return stmt.executeUpdate() > 0;
        }
    }

    // EXCLUIR
    public boolean excluir(int id) throws SQLException {

        String sql = "DELETE FROM cliente WHERE idcliente = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            return stmt.executeUpdate() > 0;
        }
    }
    
    // DESATIVAR CLIENTE
public boolean desativar(int idcliente) throws SQLException {

    String sql = "UPDATE cliente SET status = 0 WHERE idcliente = ?";

    try (Connection conn = Conexao.conectar();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, idcliente);

        return stmt.executeUpdate() > 0;
    }
}
}
