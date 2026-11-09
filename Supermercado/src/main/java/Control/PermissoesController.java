/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control;
import Conection.Conexao;
import Model.CondicoesPagamento;
import Model.Permissoes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
/**
 *
 * @author JordanaLeite
 */


public class PermissoesController {

    // MÉTODO PARA CADASTRAR PERMISSÕES
    public void cadastrar(Permissoes permissao) {

        String sql = "INSERT INTO permissoes (tipo, telaProdutos, telaEstoque, "
                + "telaCompras, telaClientes, telaPromocoes, telaCaixa, "
                + "telaVendas, telaPDV, telaPedidos, telaEntregas, telaRh, "
                + "telaFinaceiro, telaRelatorios) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, permissao.getTipo());
            stmt.setInt(2, permissao.getTelaProdutos());
            stmt.setInt(3, permissao.getTelaEstoque());
            stmt.setInt(4, permissao.getTelaCompras());
            stmt.setInt(5, permissao.getTelaClientes());
            stmt.setInt(6, permissao.getTelaPromocoes());
            stmt.setInt(7, permissao.getTelaCaixa());
            stmt.setInt(8, permissao.getTelaVendas());
            stmt.setInt(9, permissao.getTelaPDV());
            stmt.setInt(10, permissao.getTelaPedidos());
            stmt.setInt(11, permissao.getTelaEntregas());
            stmt.setInt(12, permissao.getTelaRh());
            stmt.setInt(13, permissao.getTelaFinaceiro());
            stmt.setInt(14, permissao.getTelaRelatorios());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null,
                    "Permissões cadastradas com sucesso!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Erro ao cadastrar permissões: " + e.getMessage());
        }
    }

    // MÉTODO PARA LISTAR PERMISSÕES
    public List<Permissoes> listar() {

        List<Permissoes> lista = new ArrayList<>();

        String sql = "SELECT * FROM permissoes";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                Permissoes permissao = new Permissoes();

                permissao.setIdpermissoes(rs.getInt("idpermissoes"));
                permissao.setTipo(rs.getString("tipo"));

                permissao.setTelaProdutos(rs.getInt("telaProdutos"));
                permissao.setTelaEstoque(rs.getInt("telaEstoque"));
                permissao.setTelaCompras(rs.getInt("telaCompras"));
                permissao.setTelaClientes(rs.getInt("telaClientes"));
                permissao.setTelaPromocoes(rs.getInt("telaPromocoes"));
                permissao.setTelaCaixa(rs.getInt("telaCaixa"));
                permissao.setTelaVendas(rs.getInt("telaVendas"));
                permissao.setTelaPDV(rs.getInt("telaPDV"));
                permissao.setTelaPedidos(rs.getInt("telaPedidos"));
                permissao.setTelaEntregas(rs.getInt("telaEntregas"));
                permissao.setTelaRh(rs.getInt("telaRh"));
                permissao.setTelaFinaceiro(rs.getInt("telaFinaceiro"));
                permissao.setTelaRelatorios(rs.getInt("telaRelatorios"));

                lista.add(permissao);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Erro ao listar permissões: " + e.getMessage());
        }

        return lista;
    }

    // MÉTODO PARA ATUALIZAR PERMISSÕES
    public void atualizar(Permissoes permissao) {

        String sql = "UPDATE permissoes SET tipo = ?, telaProdutos = ?, "
                + "telaEstoque = ?, telaCompras = ?, telaClientes = ?, "
                + "telaPromocoes = ?, telaCaixa = ?, telaVendas = ?, "
                + "telaPDV = ?, telaPedidos = ?, telaEntregas = ?, "
                + "telaRh = ?, telaFinaceiro = ?, telaRelatorios = ? "
                + "WHERE idpermissoes = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, permissao.getTipo());
            stmt.setInt(2, permissao.getTelaProdutos());
            stmt.setInt(3, permissao.getTelaEstoque());
            stmt.setInt(4, permissao.getTelaCompras());
            stmt.setInt(5, permissao.getTelaClientes());
            stmt.setInt(6, permissao.getTelaPromocoes());
            stmt.setInt(7, permissao.getTelaCaixa());
            stmt.setInt(8, permissao.getTelaVendas());
            stmt.setInt(9, permissao.getTelaPDV());
            stmt.setInt(10, permissao.getTelaPedidos());
            stmt.setInt(11, permissao.getTelaEntregas());
            stmt.setInt(12, permissao.getTelaRh());
            stmt.setInt(13, permissao.getTelaFinaceiro());
            stmt.setInt(14, permissao.getTelaRelatorios());
            stmt.setInt(15, permissao.getIdpermissoes());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null,
                    "Permissões atualizadas com sucesso!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Erro ao atualizar permissões: " + e.getMessage());
        }
    }

    // MÉTODO PARA EXCLUIR PERMISSÕES
    public void excluir(int id) {

        String sql = "DELETE FROM permissoes WHERE idpermissoes = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null,
                    "Permissões excluídas com sucesso!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Erro ao excluir permissões: " + e.getMessage());
        }
    }
}