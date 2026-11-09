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
import javax.swing.JOptionPane;



public class UsuarioController {

    // MÉTODO PARA CADASTRAR USUÁRIO
    public void cadastrar(Usuario usuario) {

        String sql = "INSERT INTO usuario "
                + "(nome, cpf, email, senha, bloqueio, Funcionario_idFuncionario) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getCpf());
            stmt.setString(3, usuario.getEmail());
            stmt.setString(4, usuario.getSenha());
            stmt.setInt(5, usuario.getBloqueio());
            stmt.setInt(6, usuario.getFuncionario_idFuncionario());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null,
                    "Usuário cadastrado com sucesso!");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Erro ao cadastrar usuário: " + e.getMessage());
        }
    }

    // MÉTODO PARA LISTAR USUÁRIOS
    public List<Usuario> listar() {

        List<Usuario> lista = new ArrayList<>();

        String sql = "SELECT * FROM usuario";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                Usuario usuario = new Usuario();

                usuario.setIdusuario(rs.getInt("idusuario"));
                usuario.setNome(rs.getString("nome"));
                usuario.setCpf(rs.getString("cpf"));
                usuario.setEmail(rs.getString("email"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setBloqueio(rs.getInt("bloqueio"));
                usuario.setFuncionario_idFuncionario(
                        rs.getInt("Funcionario_idFuncionario"));

                lista.add(usuario);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Erro ao listar usuários: " + e.getMessage());
        }

        return lista;
    }

    // MÉTODO PARA ATUALIZAR USUÁRIO
    public void atualizar(Usuario usuario) {

        String sql = "UPDATE usuario SET "
                + "nome = ?, cpf = ?, email = ?, senha = ?, "
                + "bloqueio = ?, Funcionario_idFuncionario = ? "
                + "WHERE idusuario = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getCpf());
            stmt.setString(3, usuario.getEmail());
            stmt.setString(4, usuario.getSenha());
            stmt.setInt(5, usuario.getBloqueio());
            stmt.setInt(6, usuario.getFuncionario_idFuncionario());
            stmt.setInt(7, usuario.getIdusuario());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null,
                    "Usuário atualizado com sucesso!");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Erro ao atualizar usuário: " + e.getMessage());
        }
    }

    // MÉTODO PARA EXCLUIR USUÁRIO
    public void excluir(int id) {

        String sql = "DELETE FROM usuario WHERE idusuario = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null,
                    "Usuário excluído com sucesso!");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Erro ao excluir usuário: " + e.getMessage());
        }
    }
    
    public Usuario login(String cpf, String senha) {

    String sql = "SELECT * FROM usuario WHERE cpf = ? AND senha = ?";

    try (Connection conn = Conexao.conectar();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, cpf);
        stmt.setString(2, senha);

        try (ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {

                Usuario usuario = new Usuario();

                usuario.setIdusuario(rs.getInt("idusuario"));
                usuario.setNome(rs.getString("nome"));
                usuario.setCpf(rs.getString("cpf"));
                usuario.setEmail(rs.getString("email"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setBloqueio(rs.getInt("bloqueio"));
                usuario.setFuncionario_idFuncionario(
                    rs.getInt("Funcionario_idFuncionario")
                );

                return usuario;
            }
        }

    } catch (SQLException e) {

        JOptionPane.showMessageDialog(
            null,
            "Erro ao realizar login: " + e.getMessage()
        );
    }

    return null;
}
    
}