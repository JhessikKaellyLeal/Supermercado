
package Control;

import Conection.Conexao;
import Model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class UsuarioController {

    // LOGIN: retorna o objeto Usuario quando as credenciais são válidas
   public Usuario login(String email, String senha) throws SQLException {

    String sql = "SELECT * FROM usuario WHERE email = ? AND senha = ?";

    try (Connection conn = Conexao.conectar();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, email);
        stmt.setString(2, senha);

        try (ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {

                Usuario usuario = new Usuario();

                usuario.setIdusuario(rs.getInt("idusuario"));
                usuario.setEmail(rs.getString("email"));
                usuario.setSenha(rs.getString("senha"));

                return usuario;
            }
        }
    }

    return null;
}

    // CADASTRAR
    public void cadastrar(Usuario usuario) throws SQLException {

        String sql = "INSERT INTO usuario "
                + "(nome, cpf, email, senha, bloqueio, "
                + "Funcionario_idFuncionario) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getCpf());
            stmt.setString(3, usuario.getEmail());
            stmt.setString(4, usuario.getSenha());

            if (usuario.getBloqueio() == null) {
                stmt.setNull(5, Types.TINYINT);
            } else {
                stmt.setInt(5, usuario.getBloqueio());
            }

            stmt.setInt(6, usuario.getFuncionario_idFuncionario());

            stmt.executeUpdate();
        }
    }

    // LISTAR
    public List<Usuario> listar() throws SQLException {

        List<Usuario> lista = new ArrayList<>();

        String sql = "SELECT * FROM usuario ORDER BY nome ASC";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                lista.add(mapearUsuario(rs));
            }
        }

        return lista;
    }

    // BUSCAR POR ID
    public Usuario buscarPorId(int id) throws SQLException {

        String sql = "SELECT * FROM usuario WHERE idusuario = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {
                    return mapearUsuario(rs);
                }
            }
        }

        return null;
    }

    // BUSCAR POR EMAIL
    public Usuario buscarPorEmail(String email)
            throws SQLException {

        String sql = "SELECT * FROM usuario WHERE email = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {
                    return mapearUsuario(rs);
                }
            }
        }

        return null;
    }

    // BUSCAR POR FUNCIONÁRIO
    public Usuario buscarPorFuncionario(int idFuncionario)
            throws SQLException {

        String sql = "SELECT * FROM usuario "
                + "WHERE Funcionario_idFuncionario = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idFuncionario);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {
                    return mapearUsuario(rs);
                }
            }
        }

        return null;
    }

    // ATUALIZAR
    public void atualizar(Usuario usuario) throws SQLException {

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

            if (usuario.getBloqueio() == null) {
                stmt.setNull(5, Types.TINYINT);
            } else {
                stmt.setInt(5, usuario.getBloqueio());
            }

            stmt.setInt(6, usuario.getFuncionario_idFuncionario());
            stmt.setInt(7, usuario.getIdusuario());

            stmt.executeUpdate();
        }
    }

    // ALTERAR BLOQUEIO
    public void alterarBloqueio(int idUsuario, Integer bloqueio)
            throws SQLException {

        String sql = "UPDATE usuario SET bloqueio = ? "
                + "WHERE idusuario = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            if (bloqueio == null) {
                stmt.setNull(1, Types.TINYINT);
            } else {
                stmt.setInt(1, bloqueio);
            }

            stmt.setInt(2, idUsuario);

            stmt.executeUpdate();
        }
    }

    // EXCLUIR
    public void excluir(int id) throws SQLException {

        String sql = "DELETE FROM usuario WHERE idusuario = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    // MAPEAR RESULTADO DO BANCO PARA OBJETO
    private Usuario mapearUsuario(ResultSet rs)
            throws SQLException {

        Usuario usuario = new Usuario();

        usuario.setIdusuario(rs.getInt("idusuario"));
        usuario.setNome(rs.getString("nome"));
        usuario.setCpf(rs.getString("cpf"));
        usuario.setEmail(rs.getString("email"));
        usuario.setSenha(rs.getString("senha"));

        int bloqueio = rs.getInt("bloqueio");
        usuario.setBloqueio(
                rs.wasNull() ? null : bloqueio
        );

        usuario.setFuncionario_idFuncionario(
                rs.getInt("Funcionario_idFuncionario")
        );

        return usuario;
    }
}
