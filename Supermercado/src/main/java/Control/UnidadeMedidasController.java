
package Control;

import Conection.Conexao;
import static Conection.Conexao.conectar;
import Model.UnidadesMedidas;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UnidadeMedidasController {

        try (Connection c = Conexao.conectar();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, codigo);
            ps.setString(2, nome);
            ps.setBoolean(3, status);
            ps.setString(4, descricao);
            return ps.executeUpdate() > 0;
        }
    }

   public List<UnidadesMedidas> listar() throws SQLException {

        try (Connection c = Conexao.conectar();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Map<String, Object> m = new LinkedHashMap<>();
                m.put("idunidadeMedidas",
                      rs.getInt("idunidadeMedidas"));
                m.put("codigo", rs.getInt("codigo"));
                m.put("nome", rs.getString("nome"));
                m.put("status", rs.getBoolean("status"));
                m.put("descricao", rs.getString("descricao"));
                lista.add(m);
            }
        }
    }

    return lista;
}

    public boolean editar(int id, int codigo, String nome,
                          boolean status, String descricao)
                          throws SQLException {
        String sql = "UPDATE unidadeMedidas SET codigo=?, nome=?, "
                   + "status=?, descricao=? WHERE idunidadeMedidas=?";

        try (Connection c = Conexao.conectar();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, codigo);
            ps.setString(2, nome);
            ps.setBoolean(3, status);
            ps.setString(4, descricao);
            ps.setInt(5, id);
            return ps.executeUpdate() > 0;
        }
    }

    // EXCLUIR UNIDADE DE MEDIDA
    public boolean excluir(int id) throws SQLException {

        try (Connection c = Conexao.conectar();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }
}
