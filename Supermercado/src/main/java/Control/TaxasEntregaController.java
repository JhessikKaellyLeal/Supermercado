
package Control;

import Conection.Conexao;
import Model.TaxasEntregas;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class TaxasEntregaController {

    public void cadastrar(TaxasEntregas taxa) throws SQLException {

        String sql = "INSERT INTO taxasEntrega "
                + "(regiao, tipoTaxa, valorTaxa, observacao) "
                + "VALUES (?, ?, ?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, taxa.getRegiao());
            stmt.setString(2, taxa.getTipoTaxa());
            stmt.setDouble(3, taxa.getValorTaxa());

            if (taxa.getObservacao() == null) {
                stmt.setNull(4, Types.VARCHAR);
            } else {
                stmt.setString(4, taxa.getObservacao());
            }

            stmt.executeUpdate();
        }
    }

    public List<TaxasEntregas> listar() throws SQLException {

        List<TaxasEntregas> lista = new ArrayList<>();

        String sql = "SELECT * FROM taxasEntrega "
                + "ORDER BY regiao ASC";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                lista.add(mapearTaxa(rs));
            }
        }

        return lista;
    }

    public TaxasEntregas buscarPorId(int id) throws SQLException {

        String sql = "SELECT * FROM taxasEntrega "
                + "WHERE idtaxasEntrega = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapearTaxa(rs);
                }
            }
        }

        return null;
    }

    public List<TaxasEntregas> buscarPorRegiao(String regiao)
            throws SQLException {

        List<TaxasEntregas> lista = new ArrayList<>();

        String sql = "SELECT * FROM taxasEntrega "
                + "WHERE regiao LIKE ? ORDER BY regiao ASC";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + regiao + "%");

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapearTaxa(rs));
                }
            }
        }

        return lista;
    }

    public List<TaxasEntregas> buscarPorTipo(String tipoTaxa)
            throws SQLException {

        List<TaxasEntregas> lista = new ArrayList<>();

        String sql = "SELECT * FROM taxasEntrega "
                + "WHERE tipoTaxa = ? ORDER BY regiao ASC";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, tipoTaxa);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapearTaxa(rs));
                }
            }
        }

        return lista;
    }

    public void atualizar(TaxasEntregas taxa) throws SQLException {

        String sql = "UPDATE taxasEntrega SET "
                + "regiao = ?, "
                + "tipoTaxa = ?, "
                + "valorTaxa = ?, "
                + "observacao = ? "
                + "WHERE idtaxasEntrega = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, taxa.getRegiao());
            stmt.setString(2, taxa.getTipoTaxa());
            stmt.setDouble(3, taxa.getValorTaxa());

            if (taxa.getObservacao() == null) {
                stmt.setNull(4, Types.VARCHAR);
            } else {
                stmt.setString(4, taxa.getObservacao());
            }

            stmt.setInt(5, taxa.getIdtaxasEntrega());

            stmt.executeUpdate();
        }
    }

    public void excluir(int id) throws SQLException {

        String sql = "DELETE FROM taxasEntrega "
                + "WHERE idtaxasEntrega = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    private TaxasEntregas mapearTaxa(ResultSet rs)
            throws SQLException {

        TaxasEntregas taxa = new TaxasEntregas();

        taxa.setIdtaxasEntrega(rs.getInt("idtaxasEntrega"));
        taxa.setRegiao(rs.getString("regiao"));
        taxa.setTipoTaxa(rs.getString("tipoTaxa"));
        taxa.setValorTaxa(rs.getDouble("valorTaxa"));
        taxa.setObservacao(rs.getString("observacao"));

        return taxa;
    }
}
