/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control;

/**
 *
 * @author AntônioVieira
 */
import Conection.Conexao;
import java.sql.*;
import java.util.*;
public class SaldoCaixaController {
   private final String[] campos = {
        "qttNotaDuzentos", "qttNotaCem", "qttNotaVinte",
        "qttNotaCinquenta", "qttNotaDez", "qttNotaCinco",
        "qttNotaDois", "qttMoedaUm", "qttMoedaCinquenta",
        "qttMoedaVinteCinco", "qttMoedaDez", "qttMoedaCinco",
        "valorSaldo", "aberturaCaixa_idaberturaCaixa"
    };

    public boolean cadastrar(int[] quantidades, double valorSaldo,
            int idAbertura) throws SQLException {

        if (quantidades == null || quantidades.length != 12)
            throw new IllegalArgumentException(
                "Informe as 12 quantidades de notas e moedas.");

        String sql = "INSERT INTO saldoCaixa "
            + "(qttNotaDuzentos, qttNotaCem, qttNotaVinte, "
            + "qttNotaCinquenta, qttNotaDez, qttNotaCinco, "
            + "qttNotaDois, qttMoedaUm, qttMoedaCinquenta, "
            + "qttMoedaVinteCinco, qttMoedaDez, qttMoedaCinco, "
            + "valorSaldo, aberturaCaixa_idaberturaCaixa) "
            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = Conexao.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            for (int i = 0; i < 12; i++)
                ps.setInt(i + 1, quantidades[i]);
            ps.setDouble(13, valorSaldo);
            ps.setInt(14, idAbertura);
            return ps.executeUpdate() > 0;
        }
    }

    public List<Map<String, Object>> listar() throws SQLException {
        List<Map<String, Object>> lista = new ArrayList<>();
        try (Connection con = Conexao.conectar();
             PreparedStatement ps = con.prepareStatement("SELECT * FROM saldoCaixa");
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Map<String, Object> m = new LinkedHashMap<>();
                ResultSetMetaData md = rs.getMetaData();
                for (int i = 1; i <= md.getColumnCount(); i++)
                    m.put(md.getColumnLabel(i), rs.getObject(i));
                lista.add(m);
            }
        }
        return lista;
    }

    public boolean atualizar(int id, int[] quantidades,
            double valorSaldo, int idAbertura) throws SQLException {

        if (quantidades == null || quantidades.length != 12)
            throw new IllegalArgumentException(
                "Informe as 12 quantidades de notas e moedas.");

        String sql = "UPDATE saldoCaixa SET "
            + "qttNotaDuzentos=?, qttNotaCem=?, qttNotaVinte=?, "
            + "qttNotaCinquenta=?, qttNotaDez=?, qttNotaCinco=?, "
            + "qttNotaDois=?, qttMoedaUm=?, qttMoedaCinquenta=?, "
            + "qttMoedaVinteCinco=?, qttMoedaDez=?, qttMoedaCinco=?, "
            + "valorSaldo=?, aberturaCaixa_idaberturaCaixa=? "
            + "WHERE idsaldoCaixa=?";

        try (Connection con = Conexao.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            for (int i = 0; i < 12; i++)
                ps.setInt(i + 1, quantidades[i]);
            ps.setDouble(13, valorSaldo);
            ps.setInt(14, idAbertura);
            ps.setInt(15, id);
            return ps.executeUpdate() > 0;
        }
    }

    public boolean excluir(int id) throws SQLException {
        try (Connection con = Conexao.conectar();
             PreparedStatement ps = con.prepareStatement(
                 "DELETE FROM saldoCaixa WHERE idsaldoCaixa=?")) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }   
}
