
package Control;

import Conection.Conexao;
import Model.Sangria;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class SangriaController {

    public void cadastrar(Sangria sangria) throws SQLException {

        String sql = "INSERT INTO sangria "
                + "(dataSolicitacao, motivo, observacao, "
                + "valorSolicitado, valorRetirado, horaSolicitacao, "
                + "aberturaCaixa_idaberturaCaixa, "
                + "Funcionario_idFuncionario, "
                + "Funcionario_cargos_idcargos, "
                + "Funcionario_setores_idsetores, "
                + "Funcionario_turnos_idturnos) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, sangria.getDataSolicitacao());
            stmt.setString(2, sangria.getMotivo());

            if (sangria.getObservacao() == null) {
                stmt.setNull(3, Types.VARCHAR);
            } else {
                stmt.setString(3, sangria.getObservacao());
            }

            stmt.setDouble(4, sangria.getValorSolicitado());
            stmt.setDouble(5, sangria.getValorRetirado());
            stmt.setString(6, sangria.getHoraSolicitacao());
            stmt.setInt(7, sangria.getAberturaCaixa_idaberturaCaixa());
            stmt.setInt(8, sangria.getFuncionario_idFuncionario());
            stmt.setInt(9, sangria.getFuncionario_cargos_idcargos());
            stmt.setInt(10, sangria.getFuncionario_setores_idsetores());
            stmt.setInt(11, sangria.getFuncionario_turnos_idturnos());

            stmt.executeUpdate();
        }
    }

    public List<Sangria> listar() throws SQLException {

        List<Sangria> lista = new ArrayList<>();

        String sql = "SELECT * FROM sangria ORDER BY idsangria DESC";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                lista.add(mapearSangria(rs));
            }
        }

        return lista;
    }

    public Sangria buscarPorId(int id) throws SQLException {

        String sql = "SELECT * FROM sangria WHERE idsangria = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapearSangria(rs);
                }
            }
        }

        return null;
    }

    public List<Sangria> buscarPorAberturaCaixa(int idAbertura)
            throws SQLException {

        List<Sangria> lista = new ArrayList<>();

        String sql = "SELECT * FROM sangria "
                + "WHERE aberturaCaixa_idaberturaCaixa = ? "
                + "ORDER BY idsangria DESC";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idAbertura);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapearSangria(rs));
                }
            }
        }

        return lista;
    }

    public List<Sangria> buscarPorFuncionario(int idFuncionario)
            throws SQLException {

        List<Sangria> lista = new ArrayList<>();

        String sql = "SELECT * FROM sangria "
                + "WHERE Funcionario_idFuncionario = ? "
                + "ORDER BY idsangria DESC";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idFuncionario);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapearSangria(rs));
                }
            }
        }

        return lista;
    }

    public List<Sangria> buscarPorMotivo(String motivo)
            throws SQLException {

        List<Sangria> lista = new ArrayList<>();

        String sql = "SELECT * FROM sangria WHERE motivo LIKE ? "
                + "ORDER BY idsangria DESC";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + motivo + "%");

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapearSangria(rs));
                }
            }
        }

        return lista;
    }

    public List<Sangria> buscarPorData(String data)
            throws SQLException {

        List<Sangria> lista = new ArrayList<>();

        String sql = "SELECT * FROM sangria "
                + "WHERE dataSolicitacao = ? "
                + "ORDER BY idsangria DESC";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, data);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapearSangria(rs));
                }
            }
        }

        return lista;
    }

    public void atualizar(Sangria sangria) throws SQLException {

        String sql = "UPDATE sangria SET "
                + "dataSolicitacao = ?, "
                + "motivo = ?, "
                + "observacao = ?, "
                + "valorSolicitado = ?, "
                + "valorRetirado = ?, "
                + "horaSolicitacao = ?, "
                + "aberturaCaixa_idaberturaCaixa = ?, "
                + "Funcionario_idFuncionario = ?, "
                + "Funcionario_cargos_idcargos = ?, "
                + "Funcionario_setores_idsetores = ?, "
                + "Funcionario_turnos_idturnos = ? "
                + "WHERE idsangria = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, sangria.getDataSolicitacao());
            stmt.setString(2, sangria.getMotivo());

            if (sangria.getObservacao() == null) {
                stmt.setNull(3, Types.VARCHAR);
            } else {
                stmt.setString(3, sangria.getObservacao());
            }

            stmt.setDouble(4, sangria.getValorSolicitado());
            stmt.setDouble(5, sangria.getValorRetirado());
            stmt.setString(6, sangria.getHoraSolicitacao());
            stmt.setInt(7, sangria.getAberturaCaixa_idaberturaCaixa());
            stmt.setInt(8, sangria.getFuncionario_idFuncionario());
            stmt.setInt(9, sangria.getFuncionario_cargos_idcargos());
            stmt.setInt(10, sangria.getFuncionario_setores_idsetores());
            stmt.setInt(11, sangria.getFuncionario_turnos_idturnos());
            stmt.setInt(12, sangria.getIdsangria());

            stmt.executeUpdate();
        }
    }

    public void excluir(int id) throws SQLException {

        String sql = "DELETE FROM sangria WHERE idsangria = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    private Sangria mapearSangria(ResultSet rs) throws SQLException {

        Sangria sangria = new Sangria();

        sangria.setIdsangria(rs.getInt("idsangria"));
        sangria.setDataSolicitacao(rs.getString("dataSolicitacao"));
        sangria.setMotivo(rs.getString("motivo"));
        sangria.setObservacao(rs.getString("observacao"));
        sangria.setValorSolicitado(rs.getDouble("valorSolicitado"));
        sangria.setValorRetirado(rs.getDouble("valorRetirado"));
        sangria.setHoraSolicitacao(rs.getString("horaSolicitacao"));

        sangria.setAberturaCaixa_idaberturaCaixa(
                rs.getInt("aberturaCaixa_idaberturaCaixa"));

        sangria.setFuncionario_idFuncionario(
                rs.getInt("Funcionario_idFuncionario"));

        sangria.setFuncionario_cargos_idcargos(
                rs.getInt("Funcionario_cargos_idcargos"));

        sangria.setFuncionario_setores_idsetores(
                rs.getInt("Funcionario_setores_idsetores"));

        sangria.setFuncionario_turnos_idturnos(
                rs.getInt("Funcionario_turnos_idturnos"));

        return sangria;
    }
}
