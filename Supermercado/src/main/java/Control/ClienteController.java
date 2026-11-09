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
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author CleicianeGomes
 */
public class ClienteController {
   
    // ==========================================
    // CADASTRAR CLIENTE
    // ==========================================
    public boolean cadastrar(Cliente cliente) {
        String sql = "INSERT INTO cliente "
                + "(nome, cpf, rg, dataNascimento, genero, email, "
                + "telefone, status, senha) "
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
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar cliente: " + e.getMessage());
            return false;
        }
    }

    // ==========================================
    // CADASTRAR CLIENTE RECEBENDO OS PARÂMETROS
    // ==========================================
    public boolean cadastrar(String nome, String cpf, String rg,
            String dataNascimento, String genero, String email,
            String telefone, int status, String senha) {
        Cliente cliente = new Cliente();
        cliente.setNome(nome);
        cliente.setCpf(cpf);
        cliente.setRg(rg);
        cliente.setDataNascimento(dataNascimento);
        cliente.setGenero(genero);
        cliente.setEmail(email);
        cliente.setTelefone(telefone);
        cliente.setStatus(status);
        cliente.setSenha(senha);
        return cadastrar(cliente);
    }

    // ==========================================
    // LISTAR TODOS OS CLIENTES
    // ==========================================
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
    
// ==========================================
// PESQUISAR CLIENTES POR CÓDIGO, NOME OU CPF
// ==========================================
public List<Cliente> pesquisarClientes(String pesquisa, Integer status) {
    List<Cliente> lista = new ArrayList<>();
    pesquisa = pesquisa.trim();
    boolean pesquisaNumerica = pesquisa.matches("\\d+");
    String sql;
    if (pesquisaNumerica && pesquisa.length() <= 5) {
        // Números curtos: pesquisa somente pelo código exato
        sql = "SELECT * FROM cliente WHERE idcliente = ? ";
    } else if (pesquisaNumerica) {
        // Números maiores: pesquisa pelo CPF
        sql = "SELECT * FROM cliente WHERE cpf LIKE ? ";
    } else {
        // Texto: pesquisa pelo nome
        sql = "SELECT * FROM cliente WHERE nome LIKE ? ";
    }
    if (status != null) {
        sql += "AND status = ? ";
    }
    sql += "ORDER BY idcliente";
    try (Connection conn = Conexao.conectar();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        if (pesquisaNumerica && pesquisa.length() <= 5) {
            stmt.setInt(1, Integer.parseInt(pesquisa));
        } else {
            stmt.setString(1, "%" + pesquisa + "%");
        }
        if (status != null) {
            stmt.setInt(2, status);
        }
        try (ResultSet rs = stmt.executeQuery()) {
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
    } catch (SQLException e) {
        System.out.println("Erro ao pesquisar clientes: " + e.getMessage());
    }
    return lista;
}

// ==========================================
// BUSCAR CLIENTE PELO CÓDIGO
// ==========================================
public Cliente buscarPorId(int idcliente) {
    String sql = "SELECT * FROM cliente WHERE idcliente = ?";
    try (Connection conn = Conexao.conectar();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setInt(1, idcliente);
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
                return cliente;
            }
        }
    } catch (SQLException e) {
        System.out.println("Erro ao buscar detalhes: " + e.getMessage());
    }
    return null;
}

    // ==========================================
    // BUSCAR CLIENTE PELO CPF
    // ==========================================
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

    // ==========================================
    // ATUALIZAR CLIENTE
    // ==========================================
    public boolean atualizar(int idcliente, Cliente cliente) {
        String sql = "UPDATE cliente SET "
                + "nome = ?, cpf = ?, rg = ?, dataNascimento = ?, "
                + "genero = ?, email = ?, telefone = ?, status = ?, "
                + "senha = ? WHERE idcliente = ?";
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

    // ==========================================
    // DESATIVAR CLIENTE
    // =========================================
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

    // ==========================================
    // REATIVAR CLIENTE
    // ==========================================
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

    // ==========================================
    // EXCLUIR CLIENTE
    // ==========================================
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
    
    // ==========================================
// BUSCAR DADOS DO CLIENTE E SEU ENDEREÇO
// ==========================================
public Map<String, String> buscarDadosEdicao(int idcliente) {

    Map<String, String> dados = new HashMap<>();

    String sql = "SELECT "
            + "c.idcliente, c.nome, c.cpf, c.rg, "
            + "c.telefone, c.dataNascimento, c.email, "
            + "e.rua, e.numero, e.bairro, e.cidade "
            + "FROM cliente c "
            + "LEFT JOIN endereco e "
            + "ON c.idcliente = e.cliente_idcliente "
            + "WHERE c.idcliente = ?";

    try (Connection conn = Conexao.conectar();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, idcliente);

        try (ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {

                dados.put("codigo",
                        String.valueOf(rs.getInt("idcliente")));

                dados.put("nome", rs.getString("nome"));
                dados.put("cpf", rs.getString("cpf"));
                dados.put("rg", rs.getString("rg"));
                dados.put("telefone", rs.getString("telefone"));
                dados.put("dataNascimento",
                        rs.getString("dataNascimento"));
                dados.put("email", rs.getString("email"));

                // Endereço completo
                String rua = rs.getString("rua");
                String numero = rs.getString("numero");

                if (rua == null) rua = "";
                if (numero == null) numero = "";

                String endereco = rua;

                if (!numero.isEmpty()) {
                    endereco += ", " + numero;
                }

                dados.put("endereco", endereco);
                dados.put("bairro", rs.getString("bairro"));
                dados.put("cidade", rs.getString("cidade"));

                return dados;
            }

        }

    } catch (SQLException e) {
        System.out.println(
            "Erro ao buscar dados para edição: " + e.getMessage()
        );
    }

    return null;
}
}
