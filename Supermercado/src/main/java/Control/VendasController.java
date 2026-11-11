
package Control;

import Conection.Conexao;
import Model.Vendas;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VendasController {
    
    
public Vendas buscarDadosRelacionados(int idVenda) throws SQLException {

    String sql = "SELECT "
            + "c.nome AS nomeCliente, "
            + "u.nome AS nomeUsuario, "
            + "cx.nome AS nomeCaixa, "
            + "cp.codigoCupom AS codigoCupom "
            + "FROM vendas v "
            + "LEFT JOIN cliente c "
            + "ON v.cliente_idcliente = c.idcliente "
            + "LEFT JOIN usuario u "
            + "ON v.usuario_idusuario = u.idusuario "
            + "LEFT JOIN caixas cx "
            + "ON v.caixas_idcaixas = cx.idcaixas "
            + "LEFT JOIN cupom cp "
            + "ON v.cupom_idcupom = cp.idcupom "
            + "WHERE v.idvendas = ?";

    try (Connection conn = Conexao.conectar();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, idVenda);

        try (ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {

                Vendas venda = new Vendas();

                venda.setNomeCliente(rs.getString("nomeCliente"));
                venda.setNomeUsuario(rs.getString("nomeUsuario"));
                venda.setNomeCaixa(rs.getString("nomeCaixa"));
                venda.setCodigoCupom(rs.getString("codigoCupom"));

                return venda;
            }
        }
    }

    return null;
}


    // CADASTRAR VENDA
    public void cadastrar(Vendas venda) throws SQLException {

        String sql = "INSERT INTO vendas "
                + "(dataVenda, horarioVenda, valorTotal, valorPago, "
                + "troco, quantidadeItens, statusVenda, cliente_idcliente, "
                + "usuario_idusuario, caixas_idcaixas, cupom_idcupom) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, venda.getDataVenda());
            stmt.setString(2, venda.getHorarioVenda());
            stmt.setDouble(3, venda.getValorTotal());
            stmt.setDouble(4, venda.getValorPago());
            stmt.setDouble(5, venda.getTroco());
            stmt.setInt(6, venda.getQuantidadeItens());
            stmt.setString(7, venda.getStatusVenda());
            stmt.setInt(8, venda.getCliente_idcliente());
            stmt.setInt(9, venda.getUsuario_idusuario());
            stmt.setInt(10, venda.getCaixas_idcaixas());
            stmt.setInt(11, venda.getCupom_idcupom());

            stmt.executeUpdate();
        }
    }

    // LISTAR TODAS AS VENDAS
   public List<Vendas> listar() throws SQLException {

    List<Vendas> lista = new ArrayList<>();

    String sql = "SELECT "
            + "v.*, "
            + "c.nome AS nomeCliente, "
            + "u.nome AS nomeUsuario, "
            + "cx.nome AS nomeCaixa, "
            + "cp.codigoCupom AS codigoCupom "
            + "FROM vendas v "
            + "LEFT JOIN cliente c "
            + "ON v.cliente_idcliente = c.idcliente "
            + "LEFT JOIN usuario u "
            + "ON v.usuario_idusuario = u.idusuario "
            + "LEFT JOIN caixas cx "
            + "ON v.caixas_idcaixas = cx.idcaixas "
            + "LEFT JOIN cupom cp "
            + "ON v.cupom_idcupom = cp.idcupom "
            + "ORDER BY v.idvendas DESC";

    try (Connection conn = Conexao.conectar();
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {

            Vendas venda = mapearVenda(rs);

            venda.setNomeCliente(rs.getString("nomeCliente"));
            venda.setNomeUsuario(rs.getString("nomeUsuario"));
            venda.setNomeCaixa(rs.getString("nomeCaixa"));
            venda.setCodigoCupom(rs.getString("codigoCupom"));

            lista.add(venda);
        }
    }

    return lista;
}
   
   public List<Object[]> listarVendasPorFormaPagamento() throws SQLException {

    List<Object[]> lista = new ArrayList<>();

    String sql = "SELECT "
            + "fp.tipo AS formaPagamento, "
            + "COUNT(DISTINCT v.idvendas) AS quantidadeVendas, "
            + "AVG(pv.valorPago) AS valorMedio, "
            + "SUM(pv.valorPago) AS valorTotal "
            + "FROM pagamentoVenda pv "
            + "INNER JOIN formasPagamento fp "
            + "ON pv.formasPagamento_idformasPagamento = fp.idformasPagamento "
            + "INNER JOIN vendas v "
            + "ON pv.vendas_idvendas = v.idvendas "
            + "GROUP BY fp.idformasPagamento, fp.tipo "
            + "ORDER BY quantidadeVendas DESC";

    try (Connection conn = Conexao.conectar();
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {

            Object[] linha = {
                rs.getString("formaPagamento"),
                rs.getInt("quantidadeVendas"),
                rs.getDouble("valorMedio"),
                rs.getDouble("valorTotal")
            };

            lista.add(linha);
        }
    }

    return lista;
}

   
   public List<Vendas> pesquisar(String texto) throws SQLException {

    List<Vendas> lista = new ArrayList<>();

    String sql = "SELECT "
            + "v.*, "
            + "c.nome AS nomeCliente, "
            + "u.nome AS nomeUsuario, "
            + "cx.nome AS nomeCaixa, "
            + "cp.codigoCupom AS codigoCupom "
            + "FROM vendas v "
            + "LEFT JOIN cliente c ON v.cliente_idcliente = c.idcliente "
            + "LEFT JOIN usuario u ON v.usuario_idusuario = u.idusuario "
            + "LEFT JOIN caixas cx ON v.caixas_idcaixas = cx.idcaixas "
            + "LEFT JOIN cupom cp ON v.cupom_idcupom = cp.idcupom "
            + "WHERE CAST(v.idvendas AS CHAR) LIKE ? "
            + "OR c.nome LIKE ? "
            + "OR u.nome LIKE ? "
            + "OR v.statusVenda LIKE ? "
            + "OR cp.codigoCupom LIKE ? "
            + "ORDER BY v.idvendas DESC";

    try (Connection conn = Conexao.conectar();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        String busca = "%" + texto + "%";

        stmt.setString(1, busca);
        stmt.setString(2, busca);
        stmt.setString(3, busca);
        stmt.setString(4, busca);
        stmt.setString(5, busca);

        try (ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                Vendas venda = mapearVenda(rs);

                venda.setNomeCliente(rs.getString("nomeCliente"));
                venda.setNomeUsuario(rs.getString("nomeUsuario"));
                venda.setNomeCaixa(rs.getString("nomeCaixa"));
                venda.setCodigoCupom(rs.getString("codigoCupom"));

                lista.add(venda);
            }
        }
    }

    return lista;
}
    // BUSCAR POR ID
    public Vendas buscarPorId(int id) throws SQLException {

        String sql = "SELECT * FROM vendas WHERE idvendas = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {
                    return mapearVenda(rs);
                }
            }
        }

        return null;
    }

    // BUSCAR POR CLIENTE
    public List<Vendas> buscarPorCliente(int idCliente)
            throws SQLException {

        List<Vendas> lista = new ArrayList<>();

        String sql = "SELECT * FROM vendas "
                + "WHERE cliente_idcliente = ? "
                + "ORDER BY idvendas DESC";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idCliente);

            try (ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {
                    lista.add(mapearVenda(rs));
                }
            }
        }

        return lista;
    }

    // BUSCAR POR USUÁRIO
    public List<Vendas> buscarPorUsuario(int idUsuario)
            throws SQLException {

        List<Vendas> lista = new ArrayList<>();

        String sql = "SELECT * FROM vendas "
                + "WHERE usuario_idusuario = ? "
                + "ORDER BY idvendas DESC";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idUsuario);

            try (ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {
                    lista.add(mapearVenda(rs));
                }
            }
        }

        return lista;
    }

    // BUSCAR POR CAIXA
    public List<Vendas> buscarPorCaixa(int idCaixa)
            throws SQLException {

        List<Vendas> lista = new ArrayList<>();

        String sql = "SELECT * FROM vendas "
                + "WHERE caixas_idcaixas = ? "
                + "ORDER BY idvendas DESC";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idCaixa);

            try (ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {
                    lista.add(mapearVenda(rs));
                }
            }
        }

        return lista;
    }

    // BUSCAR POR CUPOM
    public List<Vendas> buscarPorCupom(int idCupom)
            throws SQLException {

        List<Vendas> lista = new ArrayList<>();

        String sql = "SELECT * FROM vendas "
                + "WHERE cupom_idcupom = ? "
                + "ORDER BY idvendas DESC";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idCupom);

            try (ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {
                    lista.add(mapearVenda(rs));
                }
            }
        }

        return lista;
    }

    // BUSCAR POR STATUS
    public List<Vendas> buscarPorStatus(String status)
            throws SQLException {

        List<Vendas> lista = new ArrayList<>();

        String sql = "SELECT * FROM vendas "
                + "WHERE statusVenda = ? "
                + "ORDER BY idvendas DESC";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, status);

            try (ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {
                    lista.add(mapearVenda(rs));
                }
            }
        }

        return lista;
    }

    // BUSCAR POR DATA
    public List<Vendas> buscarPorData(String data)
            throws SQLException {

        List<Vendas> lista = new ArrayList<>();

        String sql = "SELECT * FROM vendas "
                + "WHERE dataVenda = ? "
                + "ORDER BY horarioVenda DESC";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, data);

            try (ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {
                    lista.add(mapearVenda(rs));
                }
            }
        }

        return lista;
    }

    // ATUALIZAR VENDA
    public void atualizar(Vendas venda) throws SQLException {

        String sql = "UPDATE vendas SET "
                + "dataVenda = ?, horarioVenda = ?, valorTotal = ?, "
                + "valorPago = ?, troco = ?, quantidadeItens = ?, "
                + "statusVenda = ?, cliente_idcliente = ?, "
                + "usuario_idusuario = ?, caixas_idcaixas = ?, "
                + "cupom_idcupom = ? "
                + "WHERE idvendas = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, venda.getDataVenda());
            stmt.setString(2, venda.getHorarioVenda());
            stmt.setDouble(3, venda.getValorTotal());
            stmt.setDouble(4, venda.getValorPago());
            stmt.setDouble(5, venda.getTroco());
            stmt.setInt(6, venda.getQuantidadeItens());
            stmt.setString(7, venda.getStatusVenda());
            stmt.setInt(8, venda.getCliente_idcliente());
            stmt.setInt(9, venda.getUsuario_idusuario());
            stmt.setInt(10, venda.getCaixas_idcaixas());
            stmt.setInt(11, venda.getCupom_idcupom());
            stmt.setInt(12, venda.getIdvendas());

            stmt.executeUpdate();
        }
    }

    // EXCLUIR VENDA
    public void excluir(int id) throws SQLException {

        String sql = "DELETE FROM vendas WHERE idvendas = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    // MAPEAR RESULTADO DO BANCO PARA OBJETO
    private Vendas mapearVenda(ResultSet rs) throws SQLException {

        Vendas venda = new Vendas();

        venda.setIdvendas(rs.getInt("idvendas"));
        venda.setDataVenda(rs.getString("dataVenda"));
        venda.setHorarioVenda(rs.getString("horarioVenda"));
        venda.setValorTotal(rs.getDouble("valorTotal"));
        venda.setValorPago(rs.getDouble("valorPago"));
        venda.setTroco(rs.getDouble("troco"));
        venda.setQuantidadeItens(rs.getInt("quantidadeItens"));
        venda.setStatusVenda(rs.getString("statusVenda"));
        venda.setCliente_idcliente(rs.getInt("cliente_idcliente"));
        venda.setUsuario_idusuario(rs.getInt("usuario_idusuario"));
        venda.setCaixas_idcaixas(rs.getInt("caixas_idcaixas"));
        venda.setCupom_idcupom(rs.getInt("cupom_idcupom"));

        return venda;
    }
}
