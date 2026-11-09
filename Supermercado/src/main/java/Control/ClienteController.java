/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control;

import Conection.Conexao;
import Model.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author JhéssikLeal
 */
public class ClienteController {
    

    // CADASTRAR CLIENTE
    public boolean cadastrar(Cliente cliente) {
        String sql = "INSERT INTO cliente "
                + "(nome, cpf, rg, dataNascimento, genero, "
                + "email, telefone, status, senha) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getCpf());
            stmt.setString(3, cliente.getRh());
            stmt.setString(4, cliente.getDataNascimento());
            stmt.setString(5, cliente.getGenero());
            stmt.setString(6, cliente.getEmail());
            stmt.setString(7, cliente.getTelefone());
            stmt.setInt(8, cliente.isStatus() ? 1 : 0);
            stmt.setString(9, cliente.getSenha());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar cliente: "
                    + e.getMessage());
            return false;
        }
    }

    // LISTAR TODOS OS CLIENTES
    public List<Cliente> listarTodos() {
        List<Cliente> clientes = new ArrayList<>();

        String sql = "SELECT nome, cpf, rg, dataNascimento, genero, "
                + "email, telefone, status, senha "
                + "FROM cliente";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Cliente cliente = new Cliente();

                cliente.setNome(rs.getString("nome"));
                cliente.setCpf(rs.getString("cpf"));
                cliente.setRh(rs.getString("rg"));
                cliente.setDataNascimento(
                        rs.getString("dataNascimento"));
                cliente.setGenero(rs.getString("genero"));
                cliente.setEmail(rs.getString("email"));
                cliente.setTelefone(rs.getString("telefone"));
                cliente.setStatus(rs.getInt("status") == 1);
                cliente.setSenha(rs.getString("senha"));

                clientes.add(cliente);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar clientes: "
                    + e.getMessage());
        }

        return clientes;
    }

    // BUSCAR CLIENTE PELO CPF
    public Cliente buscarPorCpf(String cpf) {
        String sql = "SELECT nome, cpf, rg, dataNascimento, genero, "
                + "email, telefone, status, senha "
                + "FROM cliente WHERE cpf = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cpf);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Cliente cliente = new Cliente();

                    cliente.setNome(rs.getString("nome"));
                    cliente.setCpf(rs.getString("cpf"));
                    cliente.setRh(rs.getString("rg"));
                    cliente.setDataNascimento(
                            rs.getString("dataNascimento"));
                    cliente.setGenero(rs.getString("genero"));
                    cliente.setEmail(rs.getString("email"));
                    cliente.setTelefone(rs.getString("telefone"));
                    cliente.setStatus(rs.getInt("status") == 1);
                    cliente.setSenha(rs.getString("senha"));

                    return cliente;
                }
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar cliente: "
                    + e.getMessage());
        }

        return null;
    }

    // ATUALIZAR CLIENTE PELO CPF
    public boolean atualizar(String cpfOriginal, Cliente cliente) {
        String sql = "UPDATE cliente SET nome = ?, cpf = ?, rg = ?, "
                + "dataNascimento = ?, genero = ?, email = ?, "
                + "telefone = ?, status = ?, senha = ? "
                + "WHERE cpf = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getCpf());
            stmt.setString(3, cliente.getRh());
            stmt.setString(4, cliente.getDataNascimento());
            stmt.setString(5, cliente.getGenero());
            stmt.setString(6, cliente.getEmail());
            stmt.setString(7, cliente.getTelefone());
            stmt.setInt(8, cliente.isStatus() ? 1 : 0);
            stmt.setString(9, cliente.getSenha());
            stmt.setString(10, cpfOriginal);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar cliente: "
                    + e.getMessage());
            return false;
        }
    }

    // EXCLUIR CLIENTE PELO CPF
    public boolean excluir(String cpf) {
        String sql = "DELETE FROM cliente WHERE cpf = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cpf);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao excluir cliente: "
                    + e.getMessage());
            return false;
        }
    }


}
