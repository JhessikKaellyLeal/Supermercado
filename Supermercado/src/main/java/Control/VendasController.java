package Control;

import Conection.Conexao;
import Model.Vendas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class VendasController {

    // =====================================================
    // SALVAR
    // =====================================================
    public boolean salvar(Vendas venda) {

        String sql = "INSERT INTO vendas ("
                + "dataVenda, horarioVenda, valorTotal, "
                + "valorPago, troco, quantidadeItens, "
                + "statusVenda, cliente_idcliente, "
                + "usuario_idusuario, caixas_idcaixas, "
                + "cupom_idcupom) "
                + "VALUES (?,?,?,?,?,?,?,?,?,?,?)";

        try {

            Connection conn = Conexao.conectar();

            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setString(1, venda.getDataVenda());
            pst.setString(2, venda.getHorarioVenda());
            pst.setDouble(3, venda.getValorTotal());
            pst.setDouble(4, venda.getValorPago());
            pst.setDouble(5, venda.getTroco());
            pst.setInt(6, venda.getQuantidadeItens());
            pst.setString(7, venda.getStatusVenda());

            pst.setInt(8, venda.getCliente_idcliente());
            pst.setInt(9, venda.getUsuario_idusuario());
            pst.setInt(10, venda.getCaixas_idcaixas());
            pst.setInt(11, venda.getCupom_idcupom());

            pst.executeUpdate();

            pst.close();
            conn.close();

            return true;

        } catch (Exception e) {

            System.out.println("Erro ao salvar venda: " + e.getMessage());

            return false;
        }
    }

    // =====================================================
    // LISTAR
    // =====================================================
    public List<Vendas> listarTodos() {

        List<Vendas> lista = new ArrayList<>();

        String sql = "SELECT * FROM vendas ORDER BY idvendas DESC";

        try {

            Connection conn = Conexao.conectar();

            PreparedStatement pst = conn.prepareStatement(sql);

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                Vendas venda = new Vendas();

                venda.setIdvendas(rs.getInt("idvendas"));
                venda.setDataVenda(rs.getString("dataVenda"));
                venda.setHorarioVenda(rs.getString("horarioVenda"));
                venda.setValorTotal(rs.getDouble("valorTotal"));
                venda.setValorPago(rs.getDouble("valorPago"));
                venda.setTroco(rs.getDouble("troco"));
                venda.setQuantidadeItens(rs.getInt("quantidadeItens"));
                venda.setStatusVenda(rs.getString("statusVenda"));

                venda.setCliente_idcliente(
                        rs.getInt("cliente_idcliente"));

                venda.setUsuario_idusuario(
                        rs.getInt("usuario_idusuario"));

                venda.setCaixas_idcaixas(
                        rs.getInt("caixas_idcaixas"));

                venda.setCupom_idcupom(
                        rs.getInt("cupom_idcupom"));

                lista.add(venda);
            }

            rs.close();
            pst.close();
            conn.close();

        } catch (Exception e) {

            System.out.println("Erro ao listar vendas: " + e.getMessage());
        }

        return lista;
    }

    // =====================================================
    // BUSCAR POR ID
    // =====================================================
    public Vendas buscarPorId(int idVenda) {

        Vendas venda = null;

        String sql = "SELECT * FROM vendas WHERE idvendas = ?";

        try {

            Connection conn = Conexao.conectar();

            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setInt(1, idVenda);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {

                venda = new Vendas();

                venda.setIdvendas(rs.getInt("idvendas"));
                venda.setDataVenda(rs.getString("dataVenda"));
                venda.setHorarioVenda(rs.getString("horarioVenda"));
                venda.setValorTotal(rs.getDouble("valorTotal"));
                venda.setValorPago(rs.getDouble("valorPago"));
                venda.setTroco(rs.getDouble("troco"));
                venda.setQuantidadeItens(rs.getInt("quantidadeItens"));
                venda.setStatusVenda(rs.getString("statusVenda"));

                venda.setCliente_idcliente(
                        rs.getInt("cliente_idcliente"));

                venda.setUsuario_idusuario(
                        rs.getInt("usuario_idusuario"));

                venda.setCaixas_idcaixas(
                        rs.getInt("caixas_idcaixas"));

                venda.setCupom_idcupom(
                        rs.getInt("cupom_idcupom"));
            }

            rs.close();
            pst.close();
            conn.close();

        } catch (Exception e) {

            System.out.println("Erro ao buscar venda: " + e.getMessage());
        }

        return venda;
    }

    // =====================================================
    // ATUALIZAR
    // =====================================================
    public boolean atualizar(Vendas venda) {

        String sql = "UPDATE vendas SET "
                + "dataVenda=?, "
                + "horarioVenda=?, "
                + "valorTotal=?, "
                + "valorPago=?, "
                + "troco=?, "
                + "quantidadeItens=?, "
                + "statusVenda=?, "
                + "cliente_idcliente=?, "
                + "usuario_idusuario=?, "
                + "caixas_idcaixas=?, "
                + "cupom_idcupom=? "
                + "WHERE idvendas=?";

        try {

            Connection conn = Conexao.conectar();

            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setString(1, venda.getDataVenda());
            pst.setString(2, venda.getHorarioVenda());
            pst.setDouble(3, venda.getValorTotal());
            pst.setDouble(4, venda.getValorPago());
            pst.setDouble(5, venda.getTroco());
            pst.setInt(6, venda.getQuantidadeItens());
            pst.setString(7, venda.getStatusVenda());

            pst.setInt(8, venda.getCliente_idcliente());
            pst.setInt(9, venda.getUsuario_idusuario());
            pst.setInt(10, venda.getCaixas_idcaixas());
            pst.setInt(11, venda.getCupom_idcupom());

            pst.setInt(12, venda.getIdvendas());

            pst.executeUpdate();

            pst.close();
            conn.close();

            return true;

        } catch (Exception e) {

            System.out.println("Erro ao atualizar venda: " + e.getMessage());

            return false;
        }
    }

    // =====================================================
    // EXCLUIR
    // =====================================================
    public boolean excluir(int idVenda) {

        String sql = "DELETE FROM vendas WHERE idvendas = ?";

        try {

            Connection conn = Conexao.conectar();

            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setInt(1, idVenda);

            pst.executeUpdate();

            pst.close();
            conn.close();

            return true;

        } catch (Exception e) {

            System.out.println("Erro ao excluir venda: " + e.getMessage());

            return false;
        }
    }

    // =====================================================
    // TOTAL DE VENDAS
    // =====================================================
    public int totalVendas() {

        String sql = "SELECT COUNT(*) AS total FROM vendas";

        try {

            Connection conn = Conexao.conectar();

            PreparedStatement pst = conn.prepareStatement(sql);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {

                return rs.getInt("total");
            }

        } catch (Exception e) {

            System.out.println("Erro ao contar vendas: " + e.getMessage());
        }

        return 0;
    }
    
    
  public List<Vendas> listar() {

    List<Vendas> lista = new ArrayList<>();

    String sql =
            "SELECT "
            + "v.idvendas, "
            + "v.dataVenda, "
            + "v.horarioVenda, "
            + "v.valorTotal, "
            + "v.valorPago, "
            + "v.troco, "
            + "v.quantidadeItens, "
            + "v.statusVenda, "
            + "c.nome AS cliente, "
            + "u.nome AS usuario, "
            + "cx.nome AS caixa, "
            + "cp.codigoCupom AS cupom "
            + "FROM vendas v "
            + "INNER JOIN cliente c "
            + "ON c.idcliente = v.cliente_idcliente "
            + "INNER JOIN usuario u "
            + "ON u.idusuario = v.usuario_idusuario "
            + "INNER JOIN caixas cx "
            + "ON cx.idcaixas = v.caixas_idcaixas "
            + "INNER JOIN cupom cp "
            + "ON cp.idcupom = v.cupom_idcupom "
            + "ORDER BY v.idvendas DESC";

    try {

        Connection conn = Conexao.conectar();

        PreparedStatement pst = conn.prepareStatement(sql);

        ResultSet rs = pst.executeQuery();

        while (rs.next()) {

            Vendas venda = new Vendas();

            venda.setIdvendas(rs.getInt("idvendas"));
            venda.setDataVenda(rs.getString("dataVenda"));
            venda.setHorarioVenda(rs.getString("horarioVenda"));
            venda.setValorTotal(rs.getDouble("valorTotal"));
            venda.setValorPago(rs.getDouble("valorPago"));
            venda.setTroco(rs.getDouble("troco"));
            venda.setQuantidadeItens(rs.getInt("quantidadeItens"));
            venda.setStatusVenda(rs.getString("statusVenda"));

            // CAMPOS DOS JOINS
            venda.setNomeCliente(rs.getString("cliente"));
            venda.setNomeUsuario(rs.getString("usuario"));
            venda.setNomeCaixa(rs.getString("caixa"));
            venda.setCodigoCupom(rs.getString("cupom"));

            lista.add(venda);
        }

        rs.close();
        pst.close();
        conn.close();

    } catch (Exception e) {

        System.out.println("Erro ao listar vendas: " + e.getMessage());
        e.printStackTrace();
    }

    return lista;
}
    
    
    
    
    
}