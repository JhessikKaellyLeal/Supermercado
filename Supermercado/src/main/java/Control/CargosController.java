
package Control;

import Conection.Conexao;
import Model.Cargos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CargosController {

    // CADASTRAR
    public boolean cadastrar(Cargos cargo) throws SQLException {

        String sql = "INSERT INTO cargos "
                + "(nome, descricao, salarioBase, cargaHoraria, "
                + "adicionalPericulosidade, adicionalInsalubridade, "
                + "statusCargo, adicionalNoturno) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(
                     sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, cargo.getNome());
            stmt.setString(2, cargo.getDescricao());
            stmt.setDouble(3, cargo.getSalarioBase());
            stmt.setInt(4, cargo.getCargaHoraria());
            stmt.setDouble(5, cargo.getAdicionalPericulosidade());
            stmt.setDouble(6, cargo.getAdicionalInsalubridade());
            stmt.setInt(7, cargo.getStatusCargo());
            stmt.setDouble(8, cargo.getAdicionalNoturno());

            int linhas = stmt.executeUpdate();

            if (linhas > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        cargo.setIdcargos(rs.getInt(1));
                    }
                }
                return true;
            }
        }

        return false;
    }

    // LISTAR TODOS
    public List<Cargos> listar() throws SQLException {

        List<Cargos> lista = new ArrayList<>();

        String sql = "SELECT * FROM cargos ORDER BY idcargos DESC";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                Cargos cargo = new Cargos();

                cargo.setIdcargos(rs.getInt("idcargos"));
                cargo.setNome(rs.getString("nome"));
                cargo.setDescricao(rs.getString("descricao"));
                cargo.setSalarioBase(rs.getDouble("salarioBase"));
                cargo.setCargaHoraria(rs.getInt("cargaHoraria"));
                cargo.setAdicionalPericulosidade(
                        rs.getDouble("adicionalPericulosidade"));
                cargo.setAdicionalInsalubridade(
                        rs.getDouble("adicionalInsalubridade"));
                cargo.setStatusCargo(rs.getInt("statusCargo"));
                cargo.setAdicionalNoturno(
                        rs.getDouble("adicionalNoturno"));

                lista.add(cargo);
            }
        }

        return lista;
    }

    // BUSCAR POR ID
    public Cargos buscarPorId(int id) throws SQLException {

        String sql = "SELECT * FROM cargos WHERE idcargos = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {

                    Cargos cargo = new Cargos();

                    cargo.setIdcargos(rs.getInt("idcargos"));
                    cargo.setNome(rs.getString("nome"));
                    cargo.setDescricao(rs.getString("descricao"));
                    cargo.setSalarioBase(rs.getDouble("salarioBase"));
                    cargo.setCargaHoraria(rs.getInt("cargaHoraria"));
                    cargo.setAdicionalPericulosidade(
                            rs.getDouble("adicionalPericulosidade"));
                    cargo.setAdicionalInsalubridade(
                            rs.getDouble("adicionalInsalubridade"));
                    cargo.setStatusCargo(rs.getInt("statusCargo"));
                    cargo.setAdicionalNoturno(
                            rs.getDouble("adicionalNoturno"));

                    return cargo;
                }
            }
        }

        return null;
    }

    // ATUALIZAR
    public boolean atualizar(Cargos cargo) throws SQLException {

        String sql = "UPDATE cargos SET "
                + "nome = ?, "
                + "descricao = ?, "
                + "salarioBase = ?, "
                + "cargaHoraria = ?, "
                + "adicionalPericulosidade = ?, "
                + "adicionalInsalubridade = ?, "
                + "statusCargo = ?, "
                + "adicionalNoturno = ? "
                + "WHERE idcargos = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cargo.getNome());
            stmt.setString(2, cargo.getDescricao());
            stmt.setDouble(3, cargo.getSalarioBase());
            stmt.setInt(4, cargo.getCargaHoraria());
            stmt.setDouble(5, cargo.getAdicionalPericulosidade());
            stmt.setDouble(6, cargo.getAdicionalInsalubridade());
            stmt.setInt(7, cargo.getStatusCargo());
            stmt.setDouble(8, cargo.getAdicionalNoturno());
            stmt.setInt(9, cargo.getIdcargos());

            return stmt.executeUpdate() > 0;
        }
    }

    // EXCLUIR
    public boolean excluir(int id) throws SQLException {

        String sql = "DELETE FROM cargos WHERE idcargos = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            return stmt.executeUpdate() > 0;
        }
    }
}
