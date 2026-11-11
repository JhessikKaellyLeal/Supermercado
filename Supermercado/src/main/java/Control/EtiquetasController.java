
package Control;

import Conection.Conexao;
import Model.Etiquetas;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EtiquetasController {

    public void cadastrar(Etiquetas etiqueta) throws SQLException {
        String sql = "INSERT INTO etiquetas (tipo, imagem) VALUES (?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, etiqueta.getTipo());
            stmt.setBytes(2, etiqueta.getImagem());

            stmt.executeUpdate();
        }
    }

    public List<Etiquetas> listar() throws SQLException {
        List<Etiquetas> lista = new ArrayList<>();

        String sql = "SELECT * FROM etiquetas";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                lista.add(mapearEtiqueta(rs));
            }
        }

        return lista;
    }

    public Etiquetas buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM etiquetas WHERE idetiquetas = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapearEtiqueta(rs);
                }
            }
        }

        return null;
    }

    public void atualizar(Etiquetas etiqueta) throws SQLException {
        String sql = "UPDATE etiquetas SET tipo = ?, imagem = ? "
                + "WHERE idetiquetas = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, etiqueta.getTipo());
            stmt.setBytes(2, etiqueta.getImagem());
            stmt.setInt(3, etiqueta.getIdetiquetas());

            stmt.executeUpdate();
        }
    }

    public void excluir(int id) throws SQLException {
        String sql = "DELETE FROM etiquetas WHERE idetiquetas = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public List<Etiquetas> buscarPorTipo(String tipo) throws SQLException {
        List<Etiquetas> lista = new ArrayList<>();

        String sql = "SELECT * FROM etiquetas WHERE tipo LIKE ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + tipo + "%");

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapearEtiqueta(rs));
                }
            }
        }

        return lista;
    }

    private Etiquetas mapearEtiqueta(ResultSet rs) throws SQLException {
        Etiquetas etiqueta = new Etiquetas();

        etiqueta.setIdetiquetas(rs.getInt("idetiquetas"));
        etiqueta.setTipo(rs.getString("tipo"));
        etiqueta.setImagem(rs.getBytes("imagem"));

        return etiqueta;
    }
}
