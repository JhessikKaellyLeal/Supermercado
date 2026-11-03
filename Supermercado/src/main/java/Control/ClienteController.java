package Control;

import Conection.Conexao;
import Model.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteController {

    // CADASTRAR CLIENTE
    public boolean cadastrar(Cliente cliente) {

        String sql = "INSERT INTO cliente "
                + "(nome, cpf, rg, dataNascimento, genero, email, telefone, status, senha) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

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

            stmt.executeUpdate();

            return true;

        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar cliente: " + e.getMessage());
            return false;
        }
    }

    // LISTAR TODOS OS CLIENTES
    public List<Cliente> listarTodos() {

        List<Cliente> lista = new ArrayList<>();

        String sql = "SELECT * FROM cliente";

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

        } catch (SQLException e) {
            System.out.println("Erro ao listar clientes: " + e.getMessage());
        }

        return lista;
    }

    // BUSCAR CLIENTE PELO CPF
    public Cliente buscarPorCpf(String cpf) {

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

        } catch (SQLException e) {
            System.out.println("Erro ao buscar cliente: " + e.getMessage());
        }

        return null;
    }

    // ATUALIZAR CLIENTE
    public boolean atualizar(int idcliente, Cliente cliente) {

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
            stmt.setInt(10, idcliente);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar cliente: " + e.getMessage());
            return false;
        }
    }

    // DESATIVAR CLIENTE
    public boolean desativar(int idcliente) {

        String sql = "UPDATE cliente SET status = 0 WHERE idcliente = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idcliente);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Erro ao desativar cliente: " + e.getMessage());
            return false;
        }
    }

    // REATIVAR CLIENTE
    public boolean reativar(int idcliente) {

        String sql = "UPDATE cliente SET status = 1 WHERE idcliente = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idcliente);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Erro ao reativar cliente: " + e.getMessage());
            return false;
        }
    }

    // EXCLUIR CLIENTE DEFINITIVAMENTE
    public boolean excluir(int idcliente) {

        String sql = "DELETE FROM cliente WHERE idcliente = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idcliente);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Erro ao excluir cliente: " + e.getMessage());
            return false;
        }
    }
}