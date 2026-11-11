
package Control;

import Conection.Conexao;
import Model.Caixas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CaixasController {

    // CADASTRAR
    public boolean cadastrar(Caixas caixa) throws SQLException {

        String sql = "INSERT INTO caixas "
                + "(nome, tipo, status, observacao) "
                + "VALUES (?, ?, ?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(
                     sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, caixa.getNome());
            stmt.setString(2, caixa.getTipo());
            stmt.setInt(3, caixa.getStatus());
            stmt.setString(4, caixa.getObservacao());

            int linhas = stmt.executeUpdate();

            if (linhas > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        caixa.setIdcaixas(rs.getInt(1));
                    }
                }
                return true;
            }
        }

        return false;
    }

    // LISTAR TODOS
    public List<Caixas> listar() throws SQLException {

        List<Caixas> lista = new ArrayList<>();

        String sql = "SELECT * FROM caixas ORDER BY idcaixas DESC";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                Caixas caixa = new Caixas();

                caixa.setIdcaixas(rs.getInt("idcaixas"));
                caixa.setNome(rs.getString("nome"));
                caixa.setTipo(rs.getString("tipo"));
                caixa.setStatus(rs.getInt("status"));
                caixa.setObservacao(rs.getString("observacao"));

                lista.add(caixa);
            }
        }

        return lista;
    }

    // BUSCAR POR ID
    public Caixas buscarPorId(int id) throws SQLException {

        String sql = "SELECT * FROM caixas WHERE idcaixas = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {

                    Caixas caixa = new Caixas();

                    caixa.setIdcaixas(rs.getInt("idcaixas"));
                    caixa.setNome(rs.getString("nome"));
                    caixa.setTipo(rs.getString("tipo"));
                    caixa.setStatus(rs.getInt("status"));
                    caixa.setObservacao(rs.getString("observacao"));

                    return caixa;
                }
            }
        }

        return null;
    }

    // ATUALIZAR
    public boolean atualizar(Caixas caixa) throws SQLException {

        String sql = "UPDATE caixas SET "
                + "nome = ?, "
                + "tipo = ?, "
                + "status = ?, "
                + "observacao = ? "
                + "WHERE idcaixas = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, caixa.getNome());
            stmt.setString(2, caixa.getTipo());
            stmt.setInt(3, caixa.getStatus());
            stmt.setString(4, caixa.getObservacao());
            stmt.setInt(5, caixa.getIdcaixas());

            return stmt.executeUpdate() > 0;
        }
    }

    // EXCLUIR
    public boolean excluir(int id) throws SQLException {

        String sql = "DELETE FROM caixas WHERE idcaixas = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            return stmt.executeUpdate() > 0;
        }
    }
}
