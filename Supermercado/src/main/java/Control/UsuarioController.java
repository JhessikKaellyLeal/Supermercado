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
                usuario.setBloqueio(rs.getBoolean("bloqueio"));
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
}


