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

        String sql = "INSERT INTO categorias (nome, descricao, status) VALUES (?, ?, ?)";

        try (Connection c = Conexao.conectar();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, nome);
            ps.setString(2, descricao);
            ps.setBoolean(3, status);

            return ps.executeUpdate() > 0;
        }
    }

    public List<Categorias> listar() throws SQLException {

        List<Categorias> lista = new ArrayList<>();

        String sql = "SELECT idcategorias, nome, descricao, status "
                   + "FROM categorias ORDER BY nome";

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
        }

        return lista;
    }

    public boolean editar(int id, String nome, String descricao,
                          boolean status) throws SQLException {

        String sql = "UPDATE categorias SET nome=?, descricao=?, status=? "
                   + "WHERE idcategorias=?";

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