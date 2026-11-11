
package Control;

import Conection.Conexao;
import Model.AberturaCaixas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AberturaCaixaController {

    // CADASTRAR
    public boolean cadastrar(AberturaCaixas abertura) throws SQLException {

        String sql = "INSERT INTO aberturaCaixa "
                + "(dataAbertura, horarioAbertura, valorInicial, "
                + "caixas_idcaixas, Funcionario_idFuncionario, "
                + "Funcionario_cargos_idcargos, "
                + "Funcionario_setores_idsetores, "
                + "Funcionario_turnos_idturnos) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(
                     sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, abertura.getDataAbertura());
            stmt.setString(2, abertura.getHorarioAbertura());
            stmt.setDouble(3, abertura.getValorInicial());
            stmt.setInt(4, abertura.getCaixas_idcaixas());
            stmt.setInt(5, abertura.getFuncionario_idFuncionario());
            stmt.setInt(6, abertura.getFuncionario_cargos_idcargos());
            stmt.setInt(7, abertura.getFuncionario_setores_idsetores());
            stmt.setInt(8, abertura.getFuncionario_turnos_idturnos());

            int linhas = stmt.executeUpdate();

            if (linhas > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        abertura.setIdaberturaCaixa(rs.getInt(1));
                    }
                }
                return true;
            }
        }

        return false;
    }

    // LISTAR TODAS AS ABERTURAS
    public List<AberturaCaixas> listar() throws SQLException {

        List<AberturaCaixas> lista = new ArrayList<>();

        String sql = "SELECT * FROM aberturaCaixa "
                + "ORDER BY idaberturaCaixa DESC";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                AberturaCaixas abertura = new AberturaCaixas();

                abertura.setIdaberturaCaixa(
                        rs.getInt("idaberturaCaixa"));

                abertura.setDataAbertura(
                        rs.getString("dataAbertura"));

                abertura.setHorarioAbertura(
                        rs.getString("horarioAbertura"));

                abertura.setValorInicial(
                        rs.getDouble("valorInicial"));

                abertura.setCaixas_idcaixas(
                        rs.getInt("caixas_idcaixas"));

                abertura.setFuncionario_idFuncionario(
                        rs.getInt("Funcionario_idFuncionario"));

                abertura.setFuncionario_cargos_idcargos(
                        rs.getInt("Funcionario_cargos_idcargos"));

                abertura.setFuncionario_setores_idsetores(
                        rs.getInt("Funcionario_setores_idsetores"));

                abertura.setFuncionario_turnos_idturnos(
                        rs.getInt("Funcionario_turnos_idturnos"));

                lista.add(abertura);
            }
        }

        return lista;
    }

    // BUSCAR POR ID
    public AberturaCaixas buscarPorId(int id) throws SQLException {

        String sql = "SELECT * FROM aberturaCaixa "
                + "WHERE idaberturaCaixa = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {

                    AberturaCaixas abertura = new AberturaCaixas();

                    abertura.setIdaberturaCaixa(
                            rs.getInt("idaberturaCaixa"));

                    abertura.setDataAbertura(
                            rs.getString("dataAbertura"));

                    abertura.setHorarioAbertura(
                            rs.getString("horarioAbertura"));

                    abertura.setValorInicial(
                            rs.getDouble("valorInicial"));

                    abertura.setCaixas_idcaixas(
                            rs.getInt("caixas_idcaixas"));

                    abertura.setFuncionario_idFuncionario(
                            rs.getInt("Funcionario_idFuncionario"));

                    abertura.setFuncionario_cargos_idcargos(
                            rs.getInt("Funcionario_cargos_idcargos"));

                    abertura.setFuncionario_setores_idsetores(
                            rs.getInt("Funcionario_setores_idsetores"));

                    abertura.setFuncionario_turnos_idturnos(
                            rs.getInt("Funcionario_turnos_idturnos"));

                    return abertura;
                }
            }
        }

        return null;
    }

    // ATUALIZAR
    public boolean atualizar(AberturaCaixas abertura) throws SQLException {

        String sql = "UPDATE aberturaCaixa SET "
                + "dataAbertura = ?, "
                + "horarioAbertura = ?, "
                + "valorInicial = ?, "
                + "caixas_idcaixas = ?, "
                + "Funcionario_idFuncionario = ?, "
                + "Funcionario_cargos_idcargos = ?, "
                + "Funcionario_setores_idsetores = ?, "
                + "Funcionario_turnos_idturnos = ? "
                + "WHERE idaberturaCaixa = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, abertura.getDataAbertura());
            stmt.setString(2, abertura.getHorarioAbertura());
            stmt.setDouble(3, abertura.getValorInicial());
            stmt.setInt(4, abertura.getCaixas_idcaixas());
            stmt.setInt(5, abertura.getFuncionario_idFuncionario());
            stmt.setInt(6, abertura.getFuncionario_cargos_idcargos());
            stmt.setInt(7, abertura.getFuncionario_setores_idsetores());
            stmt.setInt(8, abertura.getFuncionario_turnos_idturnos());
            stmt.setInt(9, abertura.getIdaberturaCaixa());

            return stmt.executeUpdate() > 0;
        }
    }

    // EXCLUIR
    public boolean excluir(int id) throws SQLException {

        String sql = "DELETE FROM aberturaCaixa "
                + "WHERE idaberturaCaixa = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            return stmt.executeUpdate() > 0;
        }
    }
}
