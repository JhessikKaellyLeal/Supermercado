/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control;

import Conection.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author AntônioVieira
 */
public class PermissoesController {
 
    // CADASTRAR PERFIL
    public boolean cadastrar(String tipo, int[] permissoes) {

        String sql = "INSERT INTO permissoes "
                + "(tipo, telaProdutos, telaEstoque, telaCompras, "
                + "telaClientes, telaPromocoes, telaCaixa, telaVendas, "
                + "telaPDV, telaPedidos, telaEntregas, telaRh, "
                + "telaFinaceiro, telaRelatorios) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, tipo);

            for (int i = 0; i < permissoes.length; i++) {
                stmt.setInt(i + 2, permissoes[i]);
            }

            return stmt.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("Erro ao cadastrar perfil: " + e.getMessage());
            return false;
        }
    }

    // LISTAR OS NOMES DOS PERFIS
    public List<String> listarTipos() {

        List<String> tipos = new ArrayList<>();

        String sql = "SELECT tipo FROM permissoes ORDER BY tipo ASC";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                tipos.add(rs.getString("tipo"));
            }

        } catch (Exception e) {
            System.out.println("Erro ao listar perfis: " + e.getMessage());
        }

        return tipos;
    }

    // BUSCAR AS PERMISSÕES DE UM PERFIL
    public int[] buscarPorTipo(String tipo) {

        String sql = "SELECT telaProdutos, telaEstoque, telaCompras, "
                + "telaClientes, telaPromocoes, telaCaixa, telaVendas, "
                + "telaPDV, telaPedidos, telaEntregas, telaRh, "
                + "telaFinaceiro, telaRelatorios "
                + "FROM permissoes WHERE tipo = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, tipo);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {

                    return new int[]{
                        rs.getInt("telaProdutos"),
                        rs.getInt("telaEstoque"),
                        rs.getInt("telaCompras"),
                        rs.getInt("telaClientes"),
                        rs.getInt("telaPromocoes"),
                        rs.getInt("telaCaixa"),
                        rs.getInt("telaVendas"),
                        rs.getInt("telaPDV"),
                        rs.getInt("telaPedidos"),
                        rs.getInt("telaEntregas"),
                        rs.getInt("telaRh"),
                        rs.getInt("telaFinaceiro"),
                        rs.getInt("telaRelatorios")
                    };
                }
            }

        } catch (Exception e) {
            System.out.println("Erro ao buscar permissões: " + e.getMessage());
        }

        return null;
    }

    // ATUALIZAR PERFIL
    public boolean atualizar(String tipo, int[] permissoes) {

        String sql = "UPDATE permissoes SET "
                + "telaProdutos = ?, "
                + "telaEstoque = ?, "
                + "telaCompras = ?, "
                + "telaClientes = ?, "
                + "telaPromocoes = ?, "
                + "telaCaixa = ?, "
                + "telaVendas = ?, "
                + "telaPDV = ?, "
                + "telaPedidos = ?, "
                + "telaEntregas = ?, "
                + "telaRh = ?, "
                + "telaFinaceiro = ?, "
                + "telaRelatorios = ? "
                + "WHERE tipo = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            for (int i = 0; i < permissoes.length; i++) {
                stmt.setInt(i + 1, permissoes[i]);
            }

            stmt.setString(14, tipo);

            return stmt.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("Erro ao atualizar permissões: " + e.getMessage());
            return false;
        }
    }
}   

