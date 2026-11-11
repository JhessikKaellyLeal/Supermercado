
package Control;

import Conection.Conexao;
import Model.ItensVendas;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItensVendasController {
    
    

    public void cadastrar(ItensVendas item) throws SQLException {
        String sql = "INSERT INTO itensVendas "
                + "(quantidade, precoUnitario, desconto, valorTotal, "
                + "vendas_idvendas) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, item.getQuantidade());
            stmt.setDouble(2, item.getPrecoUnitario());
            stmt.setDouble(3, item.getDesconto());
            stmt.setDouble(4, item.getValorTotal());
            stmt.setInt(5, item.getVendas_idvendas());

            stmt.executeUpdate();
        }
    }

   
public List<ItensVendas> listar() throws SQLException {

    List<ItensVendas> lista = new ArrayList<>();

    String sql = "SELECT "
            + "iv.*, "
            + "c.nome AS nomeCliente, "
            + "u.nome AS nomeUsuario, "
            + "cx.nome AS nomeCaixa, "
            + "cp.codigoCupom AS codigoCupom "
            + "FROM itensVendas iv "
            + "INNER JOIN vendas v "
            + "ON iv.vendas_idvendas = v.idvendas "
            + "LEFT JOIN cliente c "
            + "ON v.cliente_idcliente = c.idcliente "
            + "LEFT JOIN usuario u "
            + "ON v.usuario_idusuario = u.idusuario "
            + "LEFT JOIN caixas cx "
            + "ON v.caixas_idcaixas = cx.idcaixas "
            + "LEFT JOIN cupom cp "
            + "ON v.cupom_idcupom = cp.idcupom "
            + "ORDER BY iv.iditensVendas DESC";

    try (Connection conn = Conexao.conectar();
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {

            ItensVendas item = mapearItem(rs);

            item.setNomeCliente(rs.getString("nomeCliente"));
            item.setNomeUsuario(rs.getString("nomeUsuario"));
            item.setNomeCaixa(rs.getString("nomeCaixa"));
            item.setCodigoCupom(rs.getString("codigoCupom"));

            lista.add(item);
        }
    }

    return lista;
}


    public ItensVendas buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM itensVendas "
                + "WHERE iditensVendas = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapearItem(rs);
                }
            }
        }

        return null;
    }

    public List<ItensVendas> buscarPorVenda(int idVenda)
            throws SQLException {

        List<ItensVendas> lista = new ArrayList<>();

        String sql = "SELECT * FROM itensVendas "
                + "WHERE vendas_idvendas = ? "
                + "ORDER BY iditensVendas DESC";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idVenda);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapearItem(rs));
                }
            }
        }

        return lista;
    }

    public void atualizar(ItensVendas item) throws SQLException {
        String sql = "UPDATE itensVendas SET "
                + "quantidade = ?, precoUnitario = ?, desconto = ?, "
                + "valorTotal = ?, vendas_idvendas = ? "
                + "WHERE iditensVendas = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, item.getQuantidade());
            stmt.setDouble(2, item.getPrecoUnitario());
            stmt.setDouble(3, item.getDesconto());
            stmt.setDouble(4, item.getValorTotal());
            stmt.setInt(5, item.getVendas_idvendas());
            stmt.setInt(6, item.getIditensVendas());

            stmt.executeUpdate();
        }
    }

    public void excluir(int id) throws SQLException {
        String sql = "DELETE FROM itensVendas "
                + "WHERE iditensVendas = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    private ItensVendas mapearItem(ResultSet rs) throws SQLException {
        ItensVendas item = new ItensVendas();

        item.setIditensVendas(rs.getInt("iditensVendas"));
        item.setQuantidade(rs.getInt("quantidade"));
        item.setPrecoUnitario(rs.getDouble("precoUnitario"));
        item.setDesconto(rs.getDouble("desconto"));
        item.setValorTotal(rs.getDouble("valorTotal"));
        item.setVendas_idvendas(rs.getInt("vendas_idvendas"));

        return item;
    }
    
    
public ItensVendas buscarDadosRelacionados(int idItem) throws SQLException {

    String sql = "SELECT "
            + "c.nome AS nomeCliente, "
            + "u.nome AS nomeUsuario, "
            + "cx.nome AS nomeCaixa, "
            + "cp.codigoCupom AS codigoCupom "
            + "FROM itensVendas iv "
            + "INNER JOIN vendas v "
            + "ON iv.vendas_idvendas = v.idvendas "
            + "LEFT JOIN cliente c "
            + "ON v.cliente_idcliente = c.idcliente "
            + "LEFT JOIN usuario u "
            + "ON v.usuario_idusuario = u.idusuario "
            + "LEFT JOIN caixas cx "
            + "ON v.caixas_idcaixas = cx.idcaixas "
            + "LEFT JOIN cupom cp "
            + "ON v.cupom_idcupom = cp.idcupom "
            + "WHERE iv.iditensVendas = ?";

    try (Connection conn = Conexao.conectar();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, idItem);

        try (ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {

                ItensVendas item = new ItensVendas();

                item.setNomeCliente(rs.getString("nomeCliente"));
                item.setNomeUsuario(rs.getString("nomeUsuario"));
                item.setNomeCaixa(rs.getString("nomeCaixa"));
                item.setCodigoCupom(rs.getString("codigoCupom"));

                return item;
            }
        }
    }

    return null;
}

}
