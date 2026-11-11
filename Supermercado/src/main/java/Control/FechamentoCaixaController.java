
package Control;

import Conection.Conexao;
import Model.FechamentoCaixa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FechamentoCaixaController {

    public void cadastrar(FechamentoCaixa fechamento) throws SQLException {
        String sql = "INSERT INTO fechamentoCaixa "
                + "(dataFechamento, horarioFechamento, valorFinal, "
                + "funcionario_idFuncionario) VALUES (?, ?, ?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, fechamento.getDataFechamento());
            stmt.setString(2, fechamento.getHorarioFechamento());
            stmt.setDouble(3, fechamento.getValorFinal());
            stmt.setInt(4, fechamento.getFuncionario_idFuncionario());

            stmt.executeUpdate();
        }
    }

    public List<FechamentoCaixa> listar() throws SQLException {
        List<FechamentoCaixa> lista = new ArrayList<>();

        String sql = "SELECT * FROM fechamentoCaixa";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                lista.add(mapearFechamento(rs));
            }
        }

        return lista;
    }

    public FechamentoCaixa buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM fechamentoCaixa "
                + "WHERE idfechamentoCaixa = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapearFechamento(rs);
                }
            }
        }

        return null;
    }

    public void atualizar(FechamentoCaixa fechamento) throws SQLException {
        String sql = "UPDATE fechamentoCaixa SET "
                + "dataFechamento = ?, "
                + "horarioFechamento = ?, "
                + "valorFinal = ?, "
                + "funcionario_idFuncionario = ? "
                + "WHERE idfechamentoCaixa = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, fechamento.getDataFechamento());
            stmt.setString(2, fechamento.getHorarioFechamento());
            stmt.setDouble(3, fechamento.getValorFinal());
            stmt.setInt(4, fechamento.getFuncionario_idFuncionario());
            stmt.setInt(5, fechamento.getIdfechamentoCaixa());

            stmt.executeUpdate();
        }
    }

    public void excluir(int id) throws SQLException {
        String sql = "DELETE FROM fechamentoCaixa "
                + "WHERE idfechamentoCaixa = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public List<FechamentoCaixa> buscarPorFuncionario(int idFuncionario)
            throws SQLException {

        List<FechamentoCaixa> lista = new ArrayList<>();

        String sql = "SELECT * FROM fechamentoCaixa "
                + "WHERE funcionario_idFuncionario = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idFuncionario);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapearFechamento(rs));
                }
            }
        }

        return lista;
    }

    private FechamentoCaixa mapearFechamento(ResultSet rs)
            throws SQLException {

        FechamentoCaixa fechamento = new FechamentoCaixa();

        fechamento.setIdfechamentoCaixa(
                rs.getInt("idfechamentoCaixa"));

        fechamento.setDataFechamento(
                rs.getString("dataFechamento"));

        fechamento.setHorarioFechamento(
                rs.getString("horarioFechamento"));

        fechamento.setValorFinal(
                rs.getDouble("valorFinal"));

        fechamento.setFuncionario_idFuncionario(
                rs.getInt("funcionario_idFuncionario"));

        return fechamento;
    }
}
