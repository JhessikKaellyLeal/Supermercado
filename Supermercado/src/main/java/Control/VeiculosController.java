
package Control;

import Conection.Conexao;
import Model.Veiculos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class VeiculosController {

    // CADASTRAR
    public void cadastrar(Veiculos veiculo) throws SQLException {

        String sql = "INSERT INTO veiculos "
                + "(tipoVeiculo, marca, modelo, anoVeiculo, cor, "
                + "capacidadeCarga, statusVeiculo, placa, observacoes) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, veiculo.getTipoVeiculo());
            stmt.setString(2, veiculo.getMarca());
            stmt.setString(3, veiculo.getModelo());
            stmt.setInt(4, veiculo.getAnoVeiculo());
            stmt.setString(5, veiculo.getCor());
            stmt.setDouble(6, veiculo.getCapacidadeCarga());
            stmt.setString(7, veiculo.getStatusVeiculo());
            stmt.setString(8, veiculo.getPlaca());

            if (veiculo.getObservacoes() == null) {
                stmt.setNull(9, Types.VARCHAR);
            } else {
                stmt.setString(9, veiculo.getObservacoes());
            }

            stmt.executeUpdate();
        }
    }

    // LISTAR TODOS
    public List<Veiculos> listar() throws SQLException {

        List<Veiculos> lista = new ArrayList<>();

        String sql = "SELECT * FROM veiculos ORDER BY modelo ASC";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                lista.add(mapearVeiculo(rs));
            }
        }

        return lista;
    }

    // BUSCAR POR ID
    public Veiculos buscarPorId(int id) throws SQLException {

        String sql = "SELECT * FROM veiculos WHERE idveiculos = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {
                    return mapearVeiculo(rs);
                }
            }
        }

        return null;
    }

    // BUSCAR POR PLACA
    public Veiculos buscarPorPlaca(String placa) throws SQLException {

        String sql = "SELECT * FROM veiculos WHERE placa = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, placa);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {
                    return mapearVeiculo(rs);
                }
            }
        }

        return null;
    }

    // BUSCAR POR MODELO
    public List<Veiculos> buscarPorModelo(String modelo)
            throws SQLException {

        List<Veiculos> lista = new ArrayList<>();

        String sql = "SELECT * FROM veiculos WHERE modelo LIKE ? "
                + "ORDER BY modelo ASC";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + modelo + "%");

            try (ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {
                    lista.add(mapearVeiculo(rs));
                }
            }
        }

        return lista;
    }

    // BUSCAR POR MARCA
    public List<Veiculos> buscarPorMarca(String marca)
            throws SQLException {

        List<Veiculos> lista = new ArrayList<>();

        String sql = "SELECT * FROM veiculos WHERE marca LIKE ? "
                + "ORDER BY marca ASC";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + marca + "%");

            try (ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {
                    lista.add(mapearVeiculo(rs));
                }
            }
        }

        return lista;
    }

    // BUSCAR POR TIPO
    public List<Veiculos> buscarPorTipo(String tipo)
            throws SQLException {

        List<Veiculos> lista = new ArrayList<>();

        String sql = "SELECT * FROM veiculos "
                + "WHERE tipoVeiculo LIKE ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + tipo + "%");

            try (ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {
                    lista.add(mapearVeiculo(rs));
                }
            }
        }

        return lista;
    }

    // BUSCAR POR STATUS
    public List<Veiculos> buscarPorStatus(String status)
            throws SQLException {

        List<Veiculos> lista = new ArrayList<>();

        String sql = "SELECT * FROM veiculos "
                + "WHERE statusVeiculo = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, status);

            try (ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {
                    lista.add(mapearVeiculo(rs));
                }
            }
        }

        return lista;
    }

    // ATUALIZAR
    public void atualizar(Veiculos veiculo) throws SQLException {

        String sql = "UPDATE veiculos SET "
                + "tipoVeiculo = ?, marca = ?, modelo = ?, "
                + "anoVeiculo = ?, cor = ?, capacidadeCarga = ?, "
                + "statusVeiculo = ?, placa = ?, observacoes = ? "
                + "WHERE idveiculos = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, veiculo.getTipoVeiculo());
            stmt.setString(2, veiculo.getMarca());
            stmt.setString(3, veiculo.getModelo());
            stmt.setInt(4, veiculo.getAnoVeiculo());
            stmt.setString(5, veiculo.getCor());
            stmt.setDouble(6, veiculo.getCapacidadeCarga());
            stmt.setString(7, veiculo.getStatusVeiculo());
            stmt.setString(8, veiculo.getPlaca());

            if (veiculo.getObservacoes() == null) {
                stmt.setNull(9, Types.VARCHAR);
            } else {
                stmt.setString(9, veiculo.getObservacoes());
            }

            stmt.setInt(10, veiculo.getIdveiculos());

            stmt.executeUpdate();
        }
    }

    // EXCLUIR
    public void excluir(int id) throws SQLException {

        String sql = "DELETE FROM veiculos WHERE idveiculos = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    // MAPEAR RESULTADO DO BANCO PARA OBJETO
    private Veiculos mapearVeiculo(ResultSet rs)
            throws SQLException {

        Veiculos veiculo = new Veiculos();

        veiculo.setIdveiculos(rs.getInt("idveiculos"));
        veiculo.setTipoVeiculo(rs.getString("tipoVeiculo"));
        veiculo.setMarca(rs.getString("marca"));
        veiculo.setModelo(rs.getString("modelo"));
        veiculo.setAnoVeiculo(rs.getInt("anoVeiculo"));
        veiculo.setCor(rs.getString("cor"));
        veiculo.setCapacidadeCarga(rs.getDouble("capacidadeCarga"));
        veiculo.setStatusVeiculo(rs.getString("statusVeiculo"));
        veiculo.setPlaca(rs.getString("placa"));
        veiculo.setObservacoes(rs.getString("observacoes"));

        return veiculo;
    }
}
