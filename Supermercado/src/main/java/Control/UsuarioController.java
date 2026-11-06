/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control;

import Conection.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Model.Usuario;
import java.util.ArrayList;
import java.util.List;

public class UsuarioController {

    // Cadastrar usuário
    public boolean cadastrar(
            String nome,
            String cpf,
            String email,
            String senha,
            int bloqueio,
            int idFuncionario) {

        String sql = "INSERT INTO usuario "
                + "(nome, cpf, email, senha, bloqueio, "
                + "Funcionario_idFuncionario) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, nome);
            stmt.setString(2, cpf);
            stmt.setString(3, email);
            stmt.setString(4, senha);
            stmt.setInt(5, bloqueio);
            stmt.setInt(6, idFuncionario);

            return stmt.executeUpdate() > 0;

        } catch (SQLException erro) {
            System.out.println("Erro ao cadastrar usuário: "
                    + erro.getMessage());
            return false;
        }
    }

    // Login: verifica e-mail, senha e bloqueio
   
public Usuario login(String email, String senha) {

    String sql = "SELECT idusuario, nome, cpf, email, "
            + "bloqueio, Funcionario_idFuncionario "
            + "FROM usuario "
            + "WHERE email = ? AND senha = ? "
            + "AND (bloqueio = 0 OR bloqueio IS NULL)";

    try (Connection conexao = Conexao.conectar();
         PreparedStatement stmt = conexao.prepareStatement(sql)) {

        stmt.setString(1, email);
        stmt.setString(2, senha);

        try (ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {

                Usuario usuario = new Usuario();

                usuario.setIdusuario(rs.getInt("idusuario"));
                usuario.setNome(rs.getString("nome"));
                usuario.setCpf(rs.getString("cpf"));
                usuario.setEmail(rs.getString("email"));
                usuario.setBloqueio(rs.getInt("bloqueio"));
                usuario.setFuncionario_idfuncionario(
                    rs.getInt("Funcionario_idFuncionario")
                );

                return usuario;
            }
        }

    } catch (SQLException erro) {
        System.out.println("Erro ao realizar login: "
                + erro.getMessage());
    }

    return null;
}

    // Consultar usuário pelo CPF
    public ResultSet consultarPorCPF(String cpf) {

        String sql = "SELECT * FROM usuario WHERE cpf = ?";

        try {
            Connection conexao = Conexao.conectar();
            PreparedStatement stmt = conexao.prepareStatement(sql);

            stmt.setString(1, cpf);

            return stmt.executeQuery();

        } catch (SQLException erro) {
            System.out.println("Erro ao consultar usuário: "
                    + erro.getMessage());
            return null;
        }
    }

    // Atualizar usuário
    public boolean atualizar(
            int idUsuario,
            String nome,
            String cpf,
            String email,
            String senha,
            int bloqueio,
            int idFuncionario) {

        String sql = "UPDATE usuario SET nome = ?, cpf = ?, "
                + "email = ?, senha = ?, bloqueio = ?, "
                + "Funcionario_idFuncionario = ? "
                + "WHERE idusuario = ?";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, nome);
            stmt.setString(2, cpf);
            stmt.setString(3, email);
            stmt.setString(4, senha);
            stmt.setInt(5, bloqueio);
            stmt.setInt(6, idFuncionario);
            stmt.setInt(7, idUsuario);

            return stmt.executeUpdate() > 0;

        } catch (SQLException erro) {
            System.out.println("Erro ao atualizar usuário: "
                    + erro.getMessage());
            return false;
        }
    }

    // Excluir usuário
    public boolean excluir(int idUsuario) {

        String sql = "DELETE FROM usuario WHERE idusuario = ?";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, idUsuario);

            return stmt.executeUpdate() > 0;

        } catch (SQLException erro) {
            System.out.println("Erro ao excluir usuário: "
                    + erro.getMessage());
            return false;
        }
    }
    public List<Object[]> listarFuncionariosCombo() {

    List<Object[]> lista = new ArrayList<>();

    String sql = "SELECT idusuario, nome "
            + "FROM usuario "
            + "WHERE Funcionario_idFuncionario IS NOT NULL "
            + "ORDER BY nome";

    try (Connection conn = Conexao.conectar();
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {

            Object[] dados = {
                rs.getInt("idusuario"),
                rs.getString("nome")
            };

            lista.add(dados);
        }

    } catch (SQLException e) {

        throw new RuntimeException(
            "Erro ao consultar funcionários: " + e.getMessage()
        );
    }

    return lista;
}

  

   public List<Usuario> listar()  {

    List<Usuario> lista = new ArrayList<>();

    String sql = "SELECT idusuario, nome, cpf, email, bloqueio "
               + "FROM usuario "
               + "ORDER BY idusuario DESC";

    try (Connection conn = Conexao.conectar();
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {

            Usuario usuario = new Usuario();

            usuario.setIdusuario(rs.getInt("idusuario"));
            usuario.setNome(rs.getString("nome"));
            usuario.setCpf(rs.getString("cpf"));
            usuario.setEmail(rs.getString("email"));
            usuario.setBloqueio(rs.getInt("bloqueio"));

            lista.add(usuario);
        }
    }   catch (SQLException ex) {
            System.getLogger(UsuarioController.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }

    return lista;
}

    public boolean cadastrar(Usuario usuario) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

   
}


