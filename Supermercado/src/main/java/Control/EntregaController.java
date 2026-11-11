
package Control;

import Conection.Conexao;
import Model.Entregas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EntregaController {

    // CADASTRAR
    public boolean cadastrar(Entregas entrega) throws SQLException {

        String sql = "INSERT INTO entregas "
                + "(dataEntrega, horarioEntrega, statusEntrega, "
                + "cliente_idcliente, veiculos_idveiculos, "
                + "taxasEntrega_idtaxasEntrega, "
                + "Funcionario_idFuncionario, "
                + "Funcionario_cargos_idcargos, "
                + "Funcionario_setores_idsetores, "
                + "Funcionario_turnos_idturnos) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(
                     sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, entrega.getDataEntrega());
            stmt.setString(2, entrega.getHorarioEntrega());
            stmt.setString(3, entrega.getStatusEntrega());
            stmt.setInt(4, entrega.getCliente_idcliente());
            stmt.setInt(5, entrega.getVeiculos_idveiculos());
            stmt.setInt(6, entrega.getTaxasEntrega_idtaxasEntrega());
            stmt.setInt(7, entrega.getFuncionario_idFuncionario());
            stmt.setInt(8, entrega.getFuncionario_cargos_idcargos());
            stmt.setInt(9, entrega.getFuncionario_setores_idsetores());
            stmt.setInt(10, entrega.getFuncionario_turnos_idturnos());

            int linhas = stmt.executeUpdate();

            if (linhas > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        entrega.setIdentregas(rs.getInt(1));
                    }
                }

                return true;
            }
        }

        return false;
    }

    // LISTAR TODAS
    public List<Entregas> listar() throws SQLException {

        List<Entregas> lista = new ArrayList<>();

        String sql = "SELECT * FROM entregas ORDER BY identregas DESC";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                lista.add(mapearEntrega(rs));
            }
        }

        return lista;
    }

    // BUSCAR POR ID
    public Entregas buscarPorId(int id) throws SQLException {

        String sql = "SELECT * FROM entregas WHERE identregas = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapearEntrega(rs);
                }
            }
        }

        return null;
    }

    // BUSCAR POR CLIENTE
    public List<Entregas> buscarPorCliente(int idCliente)
            throws SQLException {

        List<Entregas> lista = new ArrayList<>();

        String sql = "SELECT * FROM entregas "
                + "WHERE cliente_idcliente = ? "
                + "ORDER BY identregas DESC";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idCliente);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapearEntrega(rs));
                }
            }
        }

        return lista;
    }

    // BUSCAR POR FUNCIONÁRIO
    public List<Entregas> buscarPorFuncionario(int idFuncionario)
            throws SQLException {

        List<Entregas> lista = new ArrayList<>();

        String sql = "SELECT * FROM entregas "
                + "WHERE Funcionario_idFuncionario = ? "
                + "ORDER BY identregas DESC";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idFuncionario);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapearEntrega(rs));
                }
            }
        }

        return lista;
    }

    // BUSCAR POR STATUS
    public List<Entregas> buscarPorStatus(String status)
            throws SQLException {

        List<Entregas> lista = new ArrayList<>();

        String sql = "SELECT * FROM entregas "
                + "WHERE statusEntrega = ? "
                + "ORDER BY identregas DESC";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, status);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapearEntrega(rs));
                }
            }
        }

        return lista;
    }

    // ATUALIZAR
    public boolean atualizar(Entregas entrega) throws SQLException {

        String sql = "UPDATE entregas SET "
                + "dataEntrega = ?, "
                + "horarioEntrega = ?, "
                + "statusEntrega = ?, "
                + "cliente_idcliente = ?, "
                + "veiculos_idveiculos = ?, "
                + "taxasEntrega_idtaxasEntrega = ?, "
                + "Funcionario_idFuncionario = ?, "
                + "Funcionario_cargos_idcargos = ?, "
                + "Funcionario_setores_idsetores = ?, "
                + "Funcionario_turnos_idturnos = ? "
                + "WHERE identregas = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, entrega.getDataEntrega());
            stmt.setString(2, entrega.getHorarioEntrega());
            stmt.setString(3, entrega.getStatusEntrega());
            stmt.setInt(4, entrega.getCliente_idcliente());
            stmt.setInt(5, entrega.getVeiculos_idveiculos());
            stmt.setInt(6, entrega.getTaxasEntrega_idtaxasEntrega());
            stmt.setInt(7, entrega.getFuncionario_idFuncionario());
            stmt.setInt(8, entrega.getFuncionario_cargos_idcargos());
            stmt.setInt(9, entrega.getFuncionario_setores_idsetores());
            stmt.setInt(10, entrega.getFuncionario_turnos_idturnos());
            stmt.setInt(11, entrega.getIdentregas());

            return stmt.executeUpdate() > 0;
        }
    }

    // EXCLUIR
    public boolean excluir(int id) throws SQLException {

        String sql = "DELETE FROM entregas WHERE identregas = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            return stmt.executeUpdate() > 0;
        }
    }

    // MAPEAR RESULTADO DO BANCO PARA MODEL
    private Entregas mapearEntrega(ResultSet rs) throws SQLException {

        Entregas entrega = new Entregas();

        entrega.setIdentregas(rs.getInt("identregas"));
        entrega.setDataEntrega(rs.getString("dataEntrega"));
        entrega.setHorarioEntrega(rs.getString("horarioEntrega"));
        entrega.setStatusEntrega(rs.getString("statusEntrega"));
        entrega.setCliente_idcliente(
                rs.getInt("cliente_idcliente"));
        entrega.setVeiculos_idveiculos(
                rs.getInt("veiculos_idveiculos"));
        entrega.setTaxasEntrega_idtaxasEntrega(
                rs.getInt("taxasEntrega_idtaxasEntrega"));
        entrega.setFuncionario_idFuncionario(
                rs.getInt("Funcionario_idFuncionario"));
        entrega.setFuncionario_cargos_idcargos(
                rs.getInt("Funcionario_cargos_idcargos"));
        entrega.setFuncionario_setores_idsetores(
                rs.getInt("Funcionario_setores_idsetores"));
        entrega.setFuncionario_turnos_idturnos(
                rs.getInt("Funcionario_turnos_idturnos"));

        return entrega;
    }
    
    public int buscarIdClientePorCpf(String cpf) {
    String sql = "SELECT idcliente FROM cliente WHERE cpf = ?";

    try (Connection conexao = Conexao.conectar();
         PreparedStatement stmt = conexao.prepareStatement(sql)) {

        stmt.setString(1, cpf);

        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt("idcliente");
            }
        }

    } catch (SQLException e) {
        System.err.println("Erro ao buscar ID do cliente pelo CPF: " + e.getMessage());
    }

    return -1;
}
    
    
    
    
    
    
}
