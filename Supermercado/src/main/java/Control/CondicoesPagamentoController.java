
package Control;

import Conection.Conexao;
import Model.CondicoesPagamento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CondicoesPagamentoController {

    // CADASTRAR
    public boolean cadastrar(CondicoesPagamento condicao) throws SQLException {

        String sql = "INSERT INTO condicoesPagamento "
                + "(quantidadeParcelas, intervaloDias) "
                + "VALUES (?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(
                     sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, condicao.getQuantidadeParcelas());
            stmt.setInt(2, condicao.getIntervaloDias());

            int linhas = stmt.executeUpdate();

            if (linhas > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        condicao.setIdcondicoesPagamento(rs.getInt(1));
                    }
                }

                return true;
            }
        }

        return false;
    }

    // LISTAR TODAS
    public List<CondicoesPagamento> listar() throws SQLException {

        List<CondicoesPagamento> lista = new ArrayList<>();

        String sql = "SELECT * FROM condicoesPagamento "
                + "ORDER BY idcondicoesPagamento DESC";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                CondicoesPagamento condicao = new CondicoesPagamento();

                condicao.setIdcondicoesPagamento(
                        rs.getInt("idcondicoesPagamento"));
                condicao.setQuantidadeParcelas(
                        rs.getInt("quantidadeParcelas"));
                condicao.setIntervaloDias(
                        rs.getInt("intervaloDias"));

                lista.add(condicao);
            }
        }

        return lista;
    }

    // BUSCAR POR ID
    public CondicoesPagamento buscarPorId(int id) throws SQLException {

        String sql = "SELECT * FROM condicoesPagamento "
                + "WHERE idcondicoesPagamento = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {

                    CondicoesPagamento condicao = new CondicoesPagamento();

                    condicao.setIdcondicoesPagamento(
                            rs.getInt("idcondicoesPagamento"));
                    condicao.setQuantidadeParcelas(
                            rs.getInt("quantidadeParcelas"));
                    condicao.setIntervaloDias(
                            rs.getInt("intervaloDias"));

                    return condicao;
                }
            }
        }

        return null;
    }

    // ATUALIZAR
    public boolean atualizar(CondicoesPagamento condicao) throws SQLException {

        String sql = "UPDATE condicoesPagamento SET "
                + "quantidadeParcelas = ?, "
                + "intervaloDias = ? "
                + "WHERE idcondicoesPagamento = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, condicao.getQuantidadeParcelas());
            stmt.setInt(2, condicao.getIntervaloDias());
            stmt.setInt(3, condicao.getIdcondicoesPagamento());

            return stmt.executeUpdate() > 0;
        }
    }

    // EXCLUIR
    public boolean excluir(int id) throws SQLException {

        String sql = "DELETE FROM condicoesPagamento "
                + "WHERE idcondicoesPagamento = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            return stmt.executeUpdate() > 0;
        }
    }
}
