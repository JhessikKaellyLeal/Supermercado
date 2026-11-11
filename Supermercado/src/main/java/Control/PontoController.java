
package Control;

import Conection.Conexao;
import Model.Ponto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class PontoController {

    public void cadastrar(Ponto ponto) throws SQLException {
        String sql = "INSERT INTO ponto "
                + "(data, horario, justificativa, arquivo, "
                + "Funcionario_idFuncionario, "
                + "Funcionario_cargos_idcargos, "
                + "Funcionario_setores_idsetores, "
                + "Funcionario_turnos_idturnos) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, ponto.getData());
            stmt.setString(2, ponto.getHorario());

            if (ponto.getJustificativa() == null) {
                stmt.setNull(3, Types.VARCHAR);
            } else {
                stmt.setString(3, ponto.getJustificativa());
            }

            if (ponto.getArquivo() == null) {
                stmt.setNull(4, Types.BLOB);
            } else {
                stmt.setBytes(4, ponto.getArquivo());
            }

            stmt.setInt(5, ponto.getFuncionario_idFuncionario());
            stmt.setInt(6, ponto.getFuncionario_cargos_idcargos());
            stmt.setInt(7, ponto.getFuncionario_setores_idsetores());
            stmt.setInt(8, ponto.getFuncionario_turnos_idturnos());

            stmt.executeUpdate();
        }
    }

    public List<Ponto> listar() throws SQLException {
        List<Ponto> lista = new ArrayList<>();

        String sql = "SELECT * FROM ponto ORDER BY idponto DESC";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                lista.add(mapearPonto(rs));
            }
        }

        return lista;
    }

    public Ponto buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM ponto WHERE idponto = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapearPonto(rs);
                }
            }
        }

        return null;
    }

    public List<Ponto> buscarPorFuncionario(int idFuncionario)
            throws SQLException {

        List<Ponto> lista = new ArrayList<>();

        String sql = "SELECT * FROM ponto "
                + "WHERE Funcionario_idFuncionario = ? "
                + "ORDER BY idponto DESC";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idFuncionario);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapearPonto(rs));
                }
            }
        }

        return lista;
    }

    public List<Ponto> buscarPorData(String data)
            throws SQLException {

        List<Ponto> lista = new ArrayList<>();

        String sql = "SELECT * FROM ponto "
                + "WHERE data = ? ORDER BY horario ASC";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, data);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapearPonto(rs));
                }
            }
        }

        return lista;
    }

    public List<Ponto> buscarPorFuncionarioEData(
            int idFuncionario, String data) throws SQLException {

        List<Ponto> lista = new ArrayList<>();

        String sql = "SELECT * FROM ponto "
                + "WHERE Funcionario_idFuncionario = ? AND data = ? "
                + "ORDER BY horario ASC";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idFuncionario);
            stmt.setString(2, data);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapearPonto(rs));
                }
            }
        }

        return lista;
    }

    public void atualizar(Ponto ponto) throws SQLException {
        String sql = "UPDATE ponto SET "
                + "data = ?, horario = ?, justificativa = ?, "
                + "arquivo = ?, "
                + "Funcionario_idFuncionario = ?, "
                + "Funcionario_cargos_idcargos = ?, "
                + "Funcionario_setores_idsetores = ?, "
                + "Funcionario_turnos_idturnos = ? "
                + "WHERE idponto = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, ponto.getData());
            stmt.setString(2, ponto.getHorario());

            if (ponto.getJustificativa() == null) {
                stmt.setNull(3, Types.VARCHAR);
            } else {
                stmt.setString(3, ponto.getJustificativa());
            }

            if (ponto.getArquivo() == null) {
                stmt.setNull(4, Types.BLOB);
            } else {
                stmt.setBytes(4, ponto.getArquivo());
            }

            stmt.setInt(5, ponto.getFuncionario_idFuncionario());
            stmt.setInt(6, ponto.getFuncionario_cargos_idcargos());
            stmt.setInt(7, ponto.getFuncionario_setores_idsetores());
            stmt.setInt(8, ponto.getFuncionario_turnos_idturnos());
            stmt.setInt(9, ponto.getIdponto());

            stmt.executeUpdate();
        }
    }

    public void excluir(int id) throws SQLException {
        String sql = "DELETE FROM ponto WHERE idponto = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    private Ponto mapearPonto(ResultSet rs) throws SQLException {
        Ponto ponto = new Ponto();

        ponto.setIdponto(rs.getInt("idponto"));
        ponto.setData(rs.getString("data"));
        ponto.setHorario(rs.getString("horario"));
        ponto.setJustificativa(rs.getString("justificativa"));
        ponto.setArquivo(rs.getBytes("arquivo"));

        ponto.setFuncionario_idFuncionario(
                rs.getInt("Funcionario_idFuncionario"));

        ponto.setFuncionario_cargos_idcargos(
                rs.getInt("Funcionario_cargos_idcargos"));

        ponto.setFuncionario_setores_idsetores(
                rs.getInt("Funcionario_setores_idsetores"));

        ponto.setFuncionario_turnos_idturnos(
                rs.getInt("Funcionario_turnos_idturnos"));

        return ponto;
    }
}
