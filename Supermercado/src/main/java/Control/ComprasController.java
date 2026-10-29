/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import Conection.Conexao;
import Model.Compras;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author IsraelSantos
 */
public class ComprasController {
    // CADASTRAR COMPRA
    public boolean cadastrar(Compras compra) {
        String sql = "INSERT INTO compras "
                + "(valorTotal, situacao, dataCompra, "
                + "fornecedores_idfornecedores, "
                + "Funcionario_idFuncionario) "
                + "VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDouble(1, compra.getValorTotal());
            stmt.setString(2, compra.getSituacao());
            stmt.setString(3, compra.getDataCompra());
            stmt.setInt(4,
                    compra.getFornecedores_idfornecedores());
            stmt.setInt(5,
                    compra.getFuncionario_idFuncionario());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar compra: "
                    + e.getMessage());
            return false;
        }
    }

    // LISTAR TODAS AS COMPRAS
    public List<Compras> listarTodos() {
        List<Compras> compras = new ArrayList<>();

        String sql = "SELECT idcompras, valorTotal, situacao, "
                + "dataCompra, fornecedores_idfornecedores, "
                + "Funcionario_idFuncionario "
                + "FROM compras";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Compras compra = new Compras();

                compra.setIdcompras(
                        rs.getInt("idcompras"));
                compra.setValorTotal(
                        rs.getDouble("valorTotal"));
                compra.setSituacao(
                        rs.getString("situacao"));
                compra.setDataCompra(
                        rs.getString("dataCompra"));
                compra.setFornecedores_idfornecedores(
                        rs.getInt("fornecedores_idfornecedores"));
                compra.setFuncionario_idFuncionario(
                        rs.getInt("Funcionario_idFuncionario"));

                compras.add(compra);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar compras: "
                    + e.getMessage());
        }

        return compras;
    }

    // BUSCAR COMPRA PELO ID
    public Compras buscarPorId(int id) {
        String sql = "SELECT idcompras, valorTotal, situacao, "
                + "dataCompra, fornecedores_idfornecedores, "
                + "Funcionario_idFuncionario "
                + "FROM compras WHERE idcompras = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Compras compra = new Compras();

                    compra.setIdcompras(
                            rs.getInt("idcompras"));
                    compra.setValorTotal(
                            rs.getDouble("valorTotal"));
                    compra.setSituacao(
                            rs.getString("situacao"));
                    compra.setDataCompra(
                            rs.getString("dataCompra"));
                    compra.setFornecedores_idfornecedores(
                            rs.getInt("fornecedores_idfornecedores"));
                    compra.setFuncionario_idFuncionario(
                            rs.getInt("Funcionario_idFuncionario"));

                    return compra;
                }
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar compra: "
                    + e.getMessage());
        }

        return null;
    }

    // ATUALIZAR COMPRA
    public boolean atualizar(int idOriginal, Compras compra) {
        String sql = "UPDATE compras SET "
                + "valorTotal = ?, situacao = ?, dataCompra = ?, "
                + "fornecedores_idfornecedores = ?, "
                + "Funcionario_idFuncionario = ? "
                + "WHERE idcompras = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDouble(1, compra.getValorTotal());
            stmt.setString(2, compra.getSituacao());
            stmt.setString(3, compra.getDataCompra());
            stmt.setInt(4,
                    compra.getFornecedores_idfornecedores());
            stmt.setInt(5,
                    compra.getFuncionario_idFuncionario());
            stmt.setInt(6, idOriginal);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar compra: "
                    + e.getMessage());
            return false;
        }
    }

    // EXCLUIR COMPRA
    public boolean excluir(int id) {
        String sql = "DELETE FROM compras WHERE idcompras = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao excluir compra: "
                    + e.getMessage());
            return false;
        }
    }
}
