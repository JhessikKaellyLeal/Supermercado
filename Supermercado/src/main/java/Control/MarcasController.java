
package Control;

import Conection.Conexao;
import Model.Marcas;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MarcasController {

    // CADASTRAR MARCA
    public boolean cadastrar(Marcas marca) throws SQLException {

        String sql = "INSERT INTO marcas "
                + "(nome, status, descricao) "
                + "VALUES (?, ?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, marca.getNome());
            stmt.setInt(2, marca.getStatus());
            stmt.setString(3, marca.getDescricao());

            return stmt.executeUpdate() > 0;
        }
    }

    // LISTAR TODAS AS MARCAS
    public List<Marcas> listarMarcas() throws SQLException {

        List<Marcas> lista = new ArrayList<>();

        String sql = "SELECT * FROM marcas";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                Marcas m = new Marcas();

                m.setIdmarcas(rs.getInt("idmarcas"));
                m.setNome(rs.getString("nome"));
                m.setStatus(rs.getInt("status"));
                m.setDescricao(rs.getString("descricao"));

                lista.add(m);
            }
        }

        return lista;
    }

    // BUSCAR MARCA PELO ID
    public Marcas buscarPorId(int id) throws SQLException {

        String sql = "SELECT * FROM marcas WHERE idmarcas = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {

                    Marcas m = new Marcas();

                    m.setIdmarcas(rs.getInt("idmarcas"));
                    m.setNome(rs.getString("nome"));
                    m.setStatus(rs.getInt("status"));
                    m.setDescricao(rs.getString("descricao"));

                    return m;
                }
            }
        }

        return null;
    }

    // ALTERAR MARCA
    public boolean alterar(Marcas marca) throws SQLException {

        String sql = "UPDATE marcas SET "
                + "nome = ?, status = ?, descricao = ? "
                + "WHERE idmarcas = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, marca.getNome());
            stmt.setInt(2, marca.getStatus());
            stmt.setString(3, marca.getDescricao());
            stmt.setInt(4, marca.getIdmarcas());

            return stmt.executeUpdate() > 0;
        }
    }

    // EXCLUIR MARCA
    public boolean excluir(int id) throws SQLException {

        String sql = "DELETE FROM marcas WHERE idmarcas = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            return stmt.executeUpdate() > 0;
        }
    }
}
