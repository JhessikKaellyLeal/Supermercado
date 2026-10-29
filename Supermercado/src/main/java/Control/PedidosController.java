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
public class PedidosController {
  public boolean cadastrar(
            String numeroPedido,
            String dataPedido,
            String statusPedido,
            String observacao,
            int idCliente,
            int idUsuario,
            int idEntrega) throws SQLException {

        String sql = "INSERT INTO pedidos "
                + "(numeroPedido, datapedido, statusPedido, "
                + "observacao, cliente_idcliente, "
                + "usuario_idusuario, entregas_identregas) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = Conexao.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, numeroPedido);
            ps.setString(2, dataPedido);
            ps.setString(3, statusPedido);
            ps.setString(4, observacao);
            ps.setInt(5, idCliente);
            ps.setInt(6, idUsuario);
            ps.setInt(7, idEntrega);

            return ps.executeUpdate() > 0;
        }
    }

    public List<Map<String, Object>> listar() throws SQLException {

        List<Map<String, Object>> lista = new ArrayList<>();

        String sql = "SELECT * FROM pedidos";

        try (Connection con = Conexao.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Map<String, Object> item = new LinkedHashMap<>();

                ResultSetMetaData meta = rs.getMetaData();

                for (int i = 1; i <= meta.getColumnCount(); i++) {
                    item.put(
                        meta.getColumnLabel(i),
                        rs.getObject(i)
                    );
                }

                lista.add(item);
            }
        }

        return lista;
    }

    public boolean atualizar(
            int id,
            String numeroPedido,
            String dataPedido,
            String statusPedido,
            String observacao,
            int idCliente,
            int idUsuario,
            int idEntrega) throws SQLException {

        String sql = "UPDATE pedidos SET "
                + "numeroPedido=?, datapedido=?, statusPedido=?, "
                + "observacao=?, cliente_idcliente=?, "
                + "usuario_idusuario=?, entregas_identregas=? "
                + "WHERE idpedidos=?";

        try (Connection con = Conexao.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, numeroPedido);
            ps.setString(2, dataPedido);
            ps.setString(3, statusPedido);
            ps.setString(4, observacao);
            ps.setInt(5, idCliente);
            ps.setInt(6, idUsuario);
            ps.setInt(7, idEntrega);
            ps.setInt(8, id);

            return ps.executeUpdate() > 0;
        }
    }

    public boolean excluir(int id) throws SQLException {

        String sql = "DELETE FROM pedidos WHERE idpedidos=?";

        try (Connection con = Conexao.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            return ps.executeUpdate() > 0;
        }
    }   
}
