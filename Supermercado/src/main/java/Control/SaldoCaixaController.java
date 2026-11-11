
package Control;

import Conection.Conexao;
import Model.SaldoCaixa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SaldoCaixaController {

    public void cadastrar(SaldoCaixa saldo) throws SQLException {

        String sql = "INSERT INTO saldoCaixa "
                + "(qttNotaDuzentos, qttNotaCem, qttNotaCinquenta, "
                + "qttNotaVinte, qttNotaDez, qttCinco, "
                + "qttMoedaUm, qttMoedaCinquenta, qttMoedaVinteCinco, "
                + "qttMoedaDez, qttMoedaCinco, valorSaldo, "
                + "aberturaCaixa_idaberturaCaixa) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, saldo.getQttNotaDuzentos());
            stmt.setInt(2, saldo.getQttNotaCem());
            stmt.setInt(3, saldo.getQttNotaCinquenta());
            stmt.setInt(4, saldo.getQttNotaVinte());
            stmt.setInt(5, saldo.getQttNotaDez());
            stmt.setInt(6, saldo.getQttNotaCinco());
            stmt.setInt(7, saldo.getQttMoedaUm());
            stmt.setInt(8, saldo.getQttMoedaCinquenta());
            stmt.setInt(9, saldo.getQttMoedaVinteCinco());
            stmt.setInt(10, saldo.getQttMoedaDez());
            stmt.setInt(11, saldo.getQttMoedaCinco());

            stmt.setDouble(12, saldo.getValorSaldo());
            stmt.setInt(13, saldo.getAberturaCaixa_idaberturaCaixa());

            stmt.executeUpdate();
        }
    }

    public List<SaldoCaixa> listar() throws SQLException {

        List<SaldoCaixa> lista = new ArrayList<>();

        String sql = "SELECT * FROM saldoCaixa "
                + "ORDER BY idsaldoCaixa DESC";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                lista.add(mapearSaldo(rs));
            }
        }

        return lista;
    }

    public SaldoCaixa buscarPorId(int id) throws SQLException {

        String sql = "SELECT * FROM saldoCaixa WHERE idsaldoCaixa = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapearSaldo(rs);
                }
            }
        }

        return null;
    }

    public SaldoCaixa buscarPorAberturaCaixa(int idAbertura)
            throws SQLException {

        String sql = "SELECT * FROM saldoCaixa "
                + "WHERE aberturaCaixa_idaberturaCaixa = ? "
                + "ORDER BY idsaldoCaixa DESC LIMIT 1";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idAbertura);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapearSaldo(rs);
                }
            }
        }

        return null;
    }

    public List<SaldoCaixa> buscarTodosPorAberturaCaixa(int idAbertura)
            throws SQLException {

        List<SaldoCaixa> lista = new ArrayList<>();

        String sql = "SELECT * FROM saldoCaixa "
                + "WHERE aberturaCaixa_idaberturaCaixa = ? "
                + "ORDER BY idsaldoCaixa DESC";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idAbertura);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapearSaldo(rs));
                }
            }
        }

        return lista;
    }

    public void atualizar(SaldoCaixa saldo) throws SQLException {

        String sql = "UPDATE saldoCaixa SET "
                + "qttNotaDuzentos = ?, "
                + "qttNotaCem = ?, "
                + "qttNotaCinquenta = ?, "
                + "qttNotaVinte = ?, "
                + "qttNotaDez = ?, "
                + "qttCinco = ?, "
                + "qttMoedaUm = ?, "
                + "qttMoedaCinquenta = ?, "
                + "qttMoedaVinteCinco = ?, "
                + "qttMoedaDez = ?, "
                + "qttMoedaCinco = ?, "
                + "valorSaldo = ?, "
                + "aberturaCaixa_idaberturaCaixa = ? "
                + "WHERE idsaldoCaixa = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, saldo.getQttNotaDuzentos());
            stmt.setInt(2, saldo.getQttNotaCem());
            stmt.setInt(3, saldo.getQttNotaCinquenta());
            stmt.setInt(4, saldo.getQttNotaVinte());
            stmt.setInt(5, saldo.getQttNotaDez());
            stmt.setInt(6, saldo.getQttNotaCinco());
            stmt.setInt(7, saldo.getQttMoedaUm());
            stmt.setInt(8, saldo.getQttMoedaCinquenta());
            stmt.setInt(9, saldo.getQttMoedaVinteCinco());
            stmt.setInt(10, saldo.getQttMoedaDez());
            stmt.setInt(11, saldo.getQttMoedaCinco());

            stmt.setDouble(12, saldo.getValorSaldo());
            stmt.setInt(13, saldo.getAberturaCaixa_idaberturaCaixa());
            stmt.setInt(14, saldo.getIdsaldoCaixa());

            stmt.executeUpdate();
        }
    }

    public void excluir(int id) throws SQLException {

        String sql = "DELETE FROM saldoCaixa WHERE idsaldoCaixa = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    private SaldoCaixa mapearSaldo(ResultSet rs) throws SQLException {

        SaldoCaixa saldo = new SaldoCaixa();

        saldo.setIdsaldoCaixa(rs.getInt("idsaldoCaixa"));

        saldo.setQttNotaDuzentos(rs.getInt("qttNotaDuzentos"));
        saldo.setQttNotaCem(rs.getInt("qttNotaCem"));
        saldo.setQttNotaCinquenta(rs.getInt("qttNotaCinquenta"));
        saldo.setQttNotaVinte(rs.getInt("qttNotaVinte"));
        saldo.setQttNotaDez(rs.getInt("qttNotaDez"));
        saldo.setQttNotaCinco(rs.getInt("qttCinco"));

        saldo.setQttMoedaUm(rs.getInt("qttMoedaUm"));
        saldo.setQttMoedaCinquenta(rs.getInt("qttMoedaCinquenta"));
        saldo.setQttMoedaVinteCinco(rs.getInt("qttMoedaVinteCinco"));
        saldo.setQttMoedaDez(rs.getInt("qttMoedaDez"));
        saldo.setQttMoedaCinco(rs.getInt("qttMoedaCinco"));

        saldo.setValorSaldo(rs.getDouble("valorSaldo"));

        saldo.setAberturaCaixa_idaberturaCaixa(
                rs.getInt("aberturaCaixa_idaberturaCaixa"));

        return saldo;
    }
}
