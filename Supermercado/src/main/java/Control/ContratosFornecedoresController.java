/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import Conection.Conexao;
import Model.ContratosFornecedores;
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
public class ContratosFornecedoresController {
    // CADASTRAR CONTRATO
    public boolean cadastrar(ContratosFornecedores contrato) {
        String sql = "INSERT INTO contratosFornecedores "
                + "(idfornecedores, dataInicio, dataFim, descricao) "
                + "VALUES (?, ?, ?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, contrato.getFornecedores_idfornecedores());
            stmt.setString(2, contrato.getDataInicio());
            stmt.setString(3, contrato.getDataFinal());
            stmt.setString(4, contrato.getDescricao());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar contrato: "
                    + e.getMessage());
            return false;
        }
    }

    // LISTAR TODOS OS CONTRATOS
    public List<ContratosFornecedores> listarTodos() {
        List<ContratosFornecedores> contratos = new ArrayList<>();

        String sql = "SELECT idcontratosFornecedores, idfornecedores, "
                + "dataInicio, dataFim, descricao "
                + "FROM contratosFornecedores";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                ContratosFornecedores contrato =
                        new ContratosFornecedores();

                contrato.setIdcontratosFornecedores(
                        rs.getInt("idcontratosFornecedores"));
                contrato.setFornecedores_idfornecedores(
                        rs.getInt("idfornecedores"));
                contrato.setDataInicio(rs.getString("dataInicio"));
                contrato.setDataFinal(rs.getString("dataFim"));
                contrato.setDescricao(rs.getString("descricao"));

                contratos.add(contrato);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar contratos: "
                    + e.getMessage());
        }

        return contratos;
    }

    // BUSCAR CONTRATO PELO ID
    public ContratosFornecedores buscarPorId(int id) {
        String sql = "SELECT idcontratosFornecedores, idfornecedores, "
                + "dataInicio, dataFim, descricao "
                + "FROM contratosFornecedores "
                + "WHERE idcontratosFornecedores = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    ContratosFornecedores contrato =
                            new ContratosFornecedores();

                    contrato.setIdcontratosFornecedores(
                            rs.getInt("idcontratosFornecedores"));
                    contrato.setFornecedores_idfornecedores(
                            rs.getInt("idfornecedores"));
                    contrato.setDataInicio(
                            rs.getString("dataInicio"));
                    contrato.setDataFinal(rs.getString("dataFinal"));
                    contrato.setDescricao(
                            rs.getString("descricao"));

                    return contrato;
                }
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar contrato: "
                    + e.getMessage());
        }

        return null;
    }

    // ATUALIZAR CONTRATO
    public boolean atualizar(
            int idOriginal, ContratosFornecedores contrato) {

        String sql = "UPDATE contratosFornecedores SET "
                + "idfornecedores = ?, dataInicio = ?, "
                + "dataFim = ?, descricao = ? "
                + "WHERE idcontratosFornecedores = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, contrato.getFornecedores_idfornecedores());
            stmt.setString(2, contrato.getDataInicio());
            stmt.setString(3, contrato.getDataFinal());
            stmt.setString(4, contrato.getDescricao());
            stmt.setInt(5, idOriginal);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar contrato: "
                    + e.getMessage());
            return false;
        }
    }

    // EXCLUIR CONTRATO
    public boolean excluir(int id) {
        String sql = "DELETE FROM contratosFornecedores "
                + "WHERE idcontratosFornecedores = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao excluir contrato: "
                    + e.getMessage());
            return false;
        }
    }
}
