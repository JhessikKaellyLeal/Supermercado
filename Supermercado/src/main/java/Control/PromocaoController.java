/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control;

import Conection.Conexao;
import Model.Promocao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author GeovannaOliviera
 */
public class PromocaoController {
    
    // CADASTRAR PROMOÇÃO
    public boolean cadastrar(Promocao promocao) {
        String sql = "INSERT INTO promocao "
                + "(nome, tipo, status, dataInicio, dataFinal, "
                + "regras, desconto, qttMinima) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, promocao.getNome());
            stmt.setString(2, promocao.getTipo());
            stmt.setInt(3, promocao.isStatus() ? 1 : 0);
            stmt.setString(4, promocao.getDataInicio());
            stmt.setString(5, promocao.getDataFinal());
            stmt.setString(6, promocao.getRegras());
            stmt.setInt(7, promocao.getDesconto());
            stmt.setInt(8, promocao.getQttMinima());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar promoção: "
                    + e.getMessage());
            return false;
        }
    }

    // LISTAR TODAS AS PROMOÇÕES
    public List<Promocao> listarTodos() {
        List<Promocao> promocoes = new ArrayList<>();

        String sql = "SELECT idpromocao, nome, tipo, status, "
                + "dataInicio, dataFinal, regras, desconto, qttMinima "
                + "FROM promocao";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Promocao promocao = new Promocao();

                promocao.setIdpromocao(rs.getInt("idpromocao"));
                promocao.setNome(rs.getString("nome"));
                promocao.setTipo(rs.getString("tipo"));
                promocao.setStatus(rs.getInt("status") == 1);
                promocao.setDataInicio(rs.getString("dataInicio"));
                promocao.setDataFinal(rs.getString("dataFinal"));
                promocao.setRegras(rs.getString("regras"));
                promocao.setDesconto(rs.getInt("desconto"));
                promocao.setQttMinima(rs.getInt("qttMinima"));

                promocoes.add(promocao);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar promoções: "
                    + e.getMessage());
        }

        return promocoes;
    }

    // BUSCAR PROMOÇÃO PELO ID
    public Promocao buscarPorId(int idpromocao) {
        String sql = "SELECT idpromocao, nome, tipo, status, "
                + "dataInicio, dataFinal, regras, desconto, qttMinima "
                + "FROM promocao WHERE idpromocao = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idpromocao);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Promocao promocao = new Promocao();

                    promocao.setIdpromocao(rs.getInt("idpromocao"));
                    promocao.setNome(rs.getString("nome"));
                    promocao.setTipo(rs.getString("tipo"));
                    promocao.setStatus(rs.getInt("status") == 1);
                    promocao.setDataInicio(rs.getString("dataInicio"));
                    promocao.setDataFinal(rs.getString("dataFinal"));
                    promocao.setRegras(rs.getString("regras"));
                    promocao.setDesconto(rs.getInt("desconto"));
                    promocao.setQttMinima(rs.getInt("qttMinima"));

                    return promocao;
                }
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar promoção: "
                    + e.getMessage());
        }

        return null;
    }

    // ATUALIZAR PROMOÇÃO PELO ID
    public boolean atualizar(int idpromocao, Promocao promocao) {
        String sql = "UPDATE promocao SET nome = ?, tipo = ?, "
                + "status = ?, dataInicio = ?, dataFinal = ?, "
                + "regras = ?, desconto = ?, qttMinima = ? "
                + "WHERE idpromocao = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, promocao.getNome());
            stmt.setString(2, promocao.getTipo());
            stmt.setInt(3, promocao.isStatus() ? 1 : 0);
            stmt.setString(4, promocao.getDataInicio());
            stmt.setString(5, promocao.getDataFinal());
            stmt.setString(6, promocao.getRegras());
            stmt.setInt(7, promocao.getDesconto());
            stmt.setInt(8, promocao.getQttMinima());
            stmt.setInt(9, idpromocao);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar promoção: "
                    + e.getMessage());
            return false;
        }
    }

    // EXCLUIR PROMOÇÃO PELO ID
    public boolean excluir(int idpromocao) {
        String sql = "DELETE FROM promocao WHERE idpromocao = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idpromocao);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao excluir promoção: "
                    + e.getMessage());
            return false;
        }
    }
}
