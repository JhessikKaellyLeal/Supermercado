package Control;

import Conection.Conexao;
import static Conection.Conexao.conectar;
import Model.Categorias;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoriasController {

    public boolean cadastrar(String nome, String descricao,
                              boolean status) throws SQLException {

        try (Connection c = Conexao.conectar();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, nome);
            ps.setString(2, descricao);
            ps.setBoolean(3, status);

            return ps.executeUpdate() > 0;
        }
    }

    public List<Categorias> listar() throws SQLException {

        try (Connection c = Conexao.conectar();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Categorias categoria = new Categorias();

                categoria.setIdcategorias(rs.getInt("idcategorias"));
                categoria.setNome(rs.getString("nome"));
                categoria.setDescricao(rs.getString("descricao"));
                categoria.setStatus(rs.getBoolean("status"));

                lista.add(categoria);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Erro ao listar categorias: " + e.getMessage());
        }

        return lista;
    }

    public boolean editar(int id, String nome, String descricao,
                          boolean status) throws SQLException {

        try (Connection c = Conexao.conectar();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, nome);
            ps.setString(2, descricao);
            ps.setBoolean(3, status);
            ps.setInt(4, id);

            return ps.executeUpdate() > 0;
        }
    }

    public boolean desativar(int id) throws SQLException {

        String sql = "UPDATE categorias SET status=0 WHERE idcategorias=?";

        try (Connection c = Conexao.conectar();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, id);

            return ps.executeUpdate() > 0;
        }
    }
}