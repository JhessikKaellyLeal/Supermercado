
package Control;

import Conection.Conexao;
import Model.Pedidos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PedidosController {
    
    public int buscarIdClientePorCpf(String cpf) throws SQLException {

    String sql = "SELECT idcliente FROM cliente WHERE cpf = ?";

    try (Connection conn = Conexao.conectar();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, cpf);

        try (ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                return rs.getInt("idcliente");
            }
        }
    }

    return -1;
}
    
    public int buscarIdEntregaPorDataHora(String data, String horario)
        throws SQLException {

    String sql = "SELECT identregas FROM entregas "
            + "WHERE dataEntrega = ? AND horarioEntrega = ?";

    try (Connection conn = Conexao.conectar();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, data);
        stmt.setString(2, horario);

        try (ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                return rs.getInt("identregas");
            }
        }
    }

    return -1;
}

    public void cadastrar(Pedidos pedido) throws SQLException {
        String sql = "INSERT INTO pedidos "
                + "(numeroPedido, datapedido, statusPedido, observacao, "
                + "cliente_idcliente, usuario_idusuario, entregas_identregas) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, pedido.getNumeroPedido());
            stmt.setString(2, pedido.getDatapedido());
            stmt.setString(3, pedido.getStatusPedido());
            stmt.setString(4, pedido.getObservacao());
            stmt.setInt(5, pedido.getCliente_idcliente());
            stmt.setInt(6, pedido.getUsuario_idusuario());
            stmt.setInt(7, pedido.getEntregas_identregas());

            stmt.executeUpdate();
        }
    }

    public List<Pedidos> listar() throws SQLException {
        List<Pedidos> lista = new ArrayList<>();

        String sql = "SELECT * FROM pedidos ORDER BY idpedidos DESC";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                lista.add(mapearPedido(rs));
            }
        }

        return lista;
    }

    public Pedidos buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM pedidos WHERE idpedidos = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapearPedido(rs);
                }
            }
        }

        return null;
    }

    public Pedidos buscarPorNumero(String numero) throws SQLException {
        String sql = "SELECT * FROM pedidos WHERE numeroPedido = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, numero);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapearPedido(rs);
                }
            }
        }

        return null;
    }

    public List<Pedidos> buscarPorCliente(int idCliente)
            throws SQLException {

        List<Pedidos> lista = new ArrayList<>();

        String sql = "SELECT * FROM pedidos "
                + "WHERE cliente_idcliente = ? "
                + "ORDER BY idpedidos DESC";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idCliente);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapearPedido(rs));
                }
            }
        }

        return lista;
    }

    public List<Pedidos> buscarPorUsuario(int idUsuario)
            throws SQLException {

        List<Pedidos> lista = new ArrayList<>();

        String sql = "SELECT * FROM pedidos "
                + "WHERE usuario_idusuario = ? "
                + "ORDER BY idpedidos DESC";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idUsuario);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapearPedido(rs));
                }
            }
        }

        return lista;
    }

    public List<Pedidos> buscarPorEntrega(int idEntrega)
            throws SQLException {

        List<Pedidos> lista = new ArrayList<>();

        String sql = "SELECT * FROM pedidos "
                + "WHERE entregas_identregas = ? "
                + "ORDER BY idpedidos DESC";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idEntrega);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapearPedido(rs));
                }
            }
        }

        return lista;
    }

    public List<Pedidos> buscarPorStatus(String status)
            throws SQLException {

        List<Pedidos> lista = new ArrayList<>();

        String sql = "SELECT * FROM pedidos "
                + "WHERE statusPedido = ? "
                + "ORDER BY idpedidos DESC";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, status);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapearPedido(rs));
                }
            }
        }

        return lista;
    }

    public void atualizar(Pedidos pedido) throws SQLException {
        String sql = "UPDATE pedidos SET "
                + "numeroPedido = ?, datapedido = ?, "
                + "statusPedido = ?, observacao = ?, "
                + "cliente_idcliente = ?, usuario_idusuario = ?, "
                + "entregas_identregas = ? "
                + "WHERE idpedidos = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, pedido.getNumeroPedido());
            stmt.setString(2, pedido.getDatapedido());
            stmt.setString(3, pedido.getStatusPedido());
            stmt.setString(4, pedido.getObservacao());
            stmt.setInt(5, pedido.getCliente_idcliente());
            stmt.setInt(6, pedido.getUsuario_idusuario());
            stmt.setInt(7, pedido.getEntregas_identregas());
            stmt.setInt(8, pedido.getIdpedidos());

            stmt.executeUpdate();
        }
    }

    public void excluir(int id) throws SQLException {
        String sql = "DELETE FROM pedidos WHERE idpedidos = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    private Pedidos mapearPedido(ResultSet rs) throws SQLException {
        Pedidos pedido = new Pedidos();

        pedido.setIdpedidos(rs.getInt("idpedidos"));
        pedido.setNumeroPedido(rs.getString("numeroPedido"));
        pedido.setDatapedido(rs.getString("datapedido"));
        pedido.setStatusPedido(rs.getString("statusPedido"));
        pedido.setObservacao(rs.getString("observacao"));
        pedido.setCliente_idcliente(rs.getInt("cliente_idcliente"));
        pedido.setUsuario_idusuario(rs.getInt("usuario_idusuario"));
        pedido.setEntregas_identregas(rs.getInt("entregas_identregas"));

        return pedido;
    }
}
