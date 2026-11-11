
package Control;

import Conection.Conexao;
import Model.Afastamentos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AfastamentosController {

    // CADASTRAR
    public boolean cadastrar(Afastamentos afastamento) throws SQLException {

        String sql = "INSERT INTO afastamentos "
                + "(tipo, inicio, final, dias, situacao, dataRegistro, "
                + "Funcionario_idFuncionario, "
                + "Funcionario_cargos_idcargos, "
                + "Funcionario_setores_idsetores, "
                + "Funcionario_turnos_idturnos) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(
                     sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, afastamento.getTipo());
            stmt.setString(2, afastamento.getInicio());
            stmt.setString(3, afastamento.getFinalAfastamento());
            stmt.setString(4, afastamento.getDias());
            stmt.setString(5, afastamento.getSituacao());
            stmt.setString(6, afastamento.getDataRegistro());
            stmt.setInt(7, afastamento.getFuncionario_idFuncionario());
            stmt.setInt(8, afastamento.getFuncionario_cargos_idcargos());
            stmt.setInt(9, afastamento.getFuncionario_setores_idsetores());
            stmt.setInt(10, afastamento.getFuncionario_turnos_idturnos());

            int linhas = stmt.executeUpdate();

            if (linhas > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        afastamento.setIdafastamentos(rs.getInt(1));
                    }
                }
                return true;
            }
        }

        return false;
    }

    // LISTAR TODOS
    public List<Afastamentos> listar() throws SQLException {

        List<Afastamentos> lista = new ArrayList<>();

        String sql = "SELECT * FROM afastamentos "
                + "ORDER BY idafastamentos DESC";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                Afastamentos afastamento = new Afastamentos();

                afastamento.setIdafastamentos(
                        rs.getInt("idafastamentos"));

                afastamento.setTipo(rs.getString("tipo"));
                afastamento.setInicio(rs.getString("inicio"));
                afastamento.setFinalAfastamento(rs.getString("final"));
                afastamento.setDias(rs.getString("dias"));
                afastamento.setSituacao(rs.getString("situacao"));
                afastamento.setDataRegistro(rs.getString("dataRegistro"));

                afastamento.setFuncionario_idFuncionario(
                        rs.getInt("Funcionario_idFuncionario"));

                afastamento.setFuncionario_cargos_idcargos(
                        rs.getInt("Funcionario_cargos_idcargos"));

                afastamento.setFuncionario_setores_idsetores(
                        rs.getInt("Funcionario_setores_idsetores"));

                afastamento.setFuncionario_turnos_idturnos(
                        rs.getInt("Funcionario_turnos_idturnos"));

                lista.add(afastamento);
            }
        }

        return lista;
    }

    // BUSCAR POR ID
    public Afastamentos buscarPorId(int id) throws SQLException {

        String sql = "SELECT * FROM afastamentos "
                + "WHERE idafastamentos = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {

                    Afastamentos afastamento = new Afastamentos();

                    afastamento.setIdafastamentos(
                            rs.getInt("idafastamentos"));

                    afastamento.setTipo(rs.getString("tipo"));
                    afastamento.setInicio(rs.getString("inicio"));
                    afastamento.setFinalAfastamento(rs.getString("final"));
                    afastamento.setDias(rs.getString("dias"));
                    afastamento.setSituacao(rs.getString("situacao"));
                    afastamento.setDataRegistro(rs.getString("dataRegistro"));

                    afastamento.setFuncionario_idFuncionario(
                            rs.getInt("Funcionario_idFuncionario"));

                    afastamento.setFuncionario_cargos_idcargos(
                            rs.getInt("Funcionario_cargos_idcargos"));

                    afastamento.setFuncionario_setores_idsetores(
                            rs.getInt("Funcionario_setores_idsetores"));

                    afastamento.setFuncionario_turnos_idturnos(
                            rs.getInt("Funcionario_turnos_idturnos"));

                    return afastamento;
                }
            }
        }

        return null;
    }

    // ATUALIZAR
    public boolean atualizar(Afastamentos afastamento) throws SQLException {

        String sql = "UPDATE afastamentos SET "
                + "tipo = ?, "
                + "inicio = ?, "
                + "final = ?, "
                + "dias = ?, "
                + "situacao = ?, "
                + "dataRegistro = ?, "
                + "Funcionario_idFuncionario = ?, "
                + "Funcionario_cargos_idcargos = ?, "
                + "Funcionario_setores_idsetores = ?, "
                + "Funcionario_turnos_idturnos = ? "
                + "WHERE idafastamentos = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, afastamento.getTipo());
            stmt.setString(2, afastamento.getInicio());
            stmt.setString(3, afastamento.getFinalAfastamento());
            stmt.setString(4, afastamento.getDias());
            stmt.setString(5, afastamento.getSituacao());
            stmt.setString(6, afastamento.getDataRegistro());
            stmt.setInt(7, afastamento.getFuncionario_idFuncionario());
            stmt.setInt(8, afastamento.getFuncionario_cargos_idcargos());
            stmt.setInt(9, afastamento.getFuncionario_setores_idsetores());
            stmt.setInt(10, afastamento.getFuncionario_turnos_idturnos());
            stmt.setInt(11, afastamento.getIdafastamentos());

            return stmt.executeUpdate() > 0;
        }
    }

    // EXCLUIR
    public boolean excluir(int id) throws SQLException {

        String sql = "DELETE FROM afastamentos "
                + "WHERE idafastamentos = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            return stmt.executeUpdate() > 0;
        }
    }
}
