/**
 *
 * @author JordanaLeite
 */
package Control;
import Conection.Conexao;
import model.ItensVendas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.List;

public class ItensVendasController {

    // =====================================================
    // LISTAR ITENS
    // =====================================================

    public List<ItensVendas> listar() {

        List<ItensVendas> lista = new ArrayList<>();

        String sql =
                "SELECT "
                + "iv.iditensVendas, "
                + "iv.quantidade, "
                + "iv.precoUnitario, "
                + "iv.desconto, "
                + "iv.valorTotal, "
                + "iv.vendas_idvendas, "

                + "c.nome AS cliente, "
                + "u.nome AS usuario, "
                + "cx.nome AS caixa, "
                + "cp.codigoCupom AS cupom "

                + "FROM itensVendas iv "

                + "INNER JOIN vendas v "
                + "ON iv.vendas_idvendas = v.idvendas "

                + "INNER JOIN cliente c "
                + "ON c.idcliente = v.cliente_idcliente "

                + "INNER JOIN usuario u "
                + "ON u.idusuario = v.usuario_idusuario "

                + "INNER JOIN caixas cx "
                + "ON cx.idcaixas = v.caixas_idcaixas "

                + "INNER JOIN cupom cp "
                + "ON cp.idcupom = v.cupom_idcupom "

                + "ORDER BY iv.iditensVendas DESC";

        try {

            Connection conn = Conexao.conectar();

            PreparedStatement pst =
                    conn.prepareStatement(sql);

            ResultSet rs =
                    pst.executeQuery();

            while (rs.next()) {

                ItensVendas item = new ItensVendas();

                item.setIditensVendas(
                        rs.getInt("iditensVendas"));

                item.setQuantidade(
                        rs.getInt("quantidade"));

                item.setPrecoUnitario(
                        rs.getDouble("precoUnitario"));

                item.setDesconto(
                        rs.getDouble("desconto"));

                item.setValorTotal(
                        rs.getDouble("valorTotal"));

                item.setVendas_idvendas(
                        rs.getInt("vendas_idvendas"));

                item.setNomeCliente(
                        rs.getString("cliente"));

                item.setNomeUsuario(
                        rs.getString("usuario"));

                item.setNomeCaixa(
                        rs.getString("caixa"));

                item.setCodigoCupom(
                        rs.getString("cupom"));

                lista.add(item);
            }

            rs.close();
            pst.close();
            conn.close();

        } catch (Exception e) {

            System.out.println(
                    "Erro ao listar itens da venda: "
                    + e.getMessage());
        }

        return lista;
    }

    // =====================================================
    // SALVAR ITEM
    // =====================================================

    public boolean salvar(ItensVendas item) {

        String sql =
                "INSERT INTO itensVendas ("
                + "quantidade, "
                + "precoUnitario, "
                + "desconto, "
                + "valorTotal, "
                + "vendas_idvendas"
                + ") VALUES (?,?,?,?,?)";

        try {

            Connection conn = Conexao.conectar();

            PreparedStatement pst =
                    conn.prepareStatement(sql);

            pst.setInt(
                    1,
                    item.getQuantidade());

            pst.setDouble(
                    2,
                    item.getPrecoUnitario());

            pst.setDouble(
                    3,
                    item.getDesconto());

            pst.setDouble(
                    4,
                    item.getValorTotal());

            pst.setInt(
                    5,
                    item.getVendas_idvendas());

            pst.executeUpdate();

            pst.close();
            conn.close();

            return true;

        } catch (Exception e) {

            System.out.println(
                    "Erro ao salvar item: "
                    + e.getMessage());

            return false;
        }
    }

    // =====================================================
    // EXCLUIR ITEM
    // =====================================================

    public boolean excluir(int idItem) {

        String sql =
                "DELETE FROM itensVendas "
                + "WHERE iditensVendas=?";

        try {

            Connection conn =
                    Conexao.conectar();

            PreparedStatement pst =
                    conn.prepareStatement(sql);

            pst.setInt(1, idItem);

            pst.executeUpdate();

            pst.close();
            conn.close();

            return true;

        } catch (Exception e) {

            System.out.println(
                    "Erro ao excluir item: "
                    + e.getMessage());

            return false;
        }
    }

    // =====================================================
    // BUSCAR POR VENDA
    // =====================================================

    public List<ItensVendas> buscarPorVenda(
            int idVenda) {

        List<ItensVendas> lista =
                new ArrayList<>();

        String sql =
                "SELECT * "
                + "FROM itensVendas "
                + "WHERE vendas_idvendas=?";

        try {

            Connection conn =
                    Conexao.conectar();

            PreparedStatement pst =
                    conn.prepareStatement(sql);

            pst.setInt(1, idVenda);

            ResultSet rs =
                    pst.executeQuery();

            while (rs.next()) {

                ItensVendas item =
                        new ItensVendas();

                item.setIditensVendas(
                        rs.getInt("iditensVendas"));

                item.setQuantidade(
                        rs.getInt("quantidade"));

                item.setPrecoUnitario(
                        rs.getDouble("precoUnitario"));

                item.setDesconto(
                        rs.getDouble("desconto"));

                item.setValorTotal(
                        rs.getDouble("valorTotal"));

                item.setVendas_idvendas(
                        rs.getInt("vendas_idvendas"));

                lista.add(item);
            }

            rs.close();
            pst.close();
            conn.close();

        } catch (Exception e) {

            System.out.println(
                    "Erro ao buscar itens: "
                    + e.getMessage());
        }

        return lista;
    }
}