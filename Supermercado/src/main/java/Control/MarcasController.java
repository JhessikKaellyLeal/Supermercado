
package Control;

import Conection.Conexao;
import static Conection.Conexao.conectar;
import Model.Marcas;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MarcasController {

        try (Connection c = Conexao.conectar();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, nome);
            ps.setBoolean(2, status);
            ps.setString(3, descricao);
            return ps.executeUpdate() > 0;
        }
    }
 public List<String[]> listarMarcas() throws SQLException {
    List<String[]> lista = new ArrayList<>();

    String sql = "SELECT idmarcas, nome, status, descricao "
               + "FROM marcas ORDER BY idmarcas";

    try (Connection con = Conexao.conectar();
         PreparedStatement ps = con.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        while (rs.next()) {
            lista.add(new String[]{
                String.valueOf(rs.getInt("idmarcas")),
                rs.getString("nome"),
                String.valueOf(rs.getInt("status")),
                rs.getString("descricao")
            });
        }
    }

    return lista;
}
    public List<Map<String, Object>> listar() throws SQLException {
        List<Map<String, Object>> lista = new ArrayList<>();
        String sql = "SELECT * FROM marcas ORDER BY nome";

        try (Connection c = Conexao.conectar();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
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

        try (Connection c = Conexao.conectar();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, nome);
            ps.setBoolean(2, status);
            ps.setString(3, descricao);
            ps.setInt(4, id);
            return ps.executeUpdate() > 0;
        }
    }

    // EXCLUIR MARCA
    public boolean excluir(int id) throws SQLException {

        try (Connection c = Conexao.conectar();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }

  
}
