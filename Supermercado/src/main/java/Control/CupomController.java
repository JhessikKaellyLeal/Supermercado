/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control;

import Conection.Conexao;
import Model.Cupom;
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
public class CupomController {

  // CADASTRAR CUPOM
    public boolean cadastrar(Cupom cupom) {
        String sql = "INSERT INTO cupom "
                + "(codigoCupom, tipoDesconto, valorDesconto, "
                + "valorMinimoCompra, qttMaximaUso, limitePorCliente, "
                + "tipoUso, dataInicio, dataFinal, observacao, "
                + "promocao_idpromocao) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cupom.getCodigoCupom());
            stmt.setString(2, cupom.getTipoDesconto());
            stmt.setDouble(3, cupom.getValorDesconto());
            stmt.setDouble(4, cupom.getValorMinimoCompra());
            stmt.setInt(5, cupom.getQttMaximaUso());
            stmt.setInt(6, cupom.getLimitePorCliente());
            stmt.setString(7, cupom.getTipoUso());
            stmt.setString(8, cupom.getDataInicio());
            stmt.setString(9, cupom.getDataFinal());
            stmt.setString(10, cupom.getObservacao());
            stmt.setInt(11, cupom.getPromocao_idpromocao());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar cupom: "
                    + e.getMessage());
            return false;
        }
    }

    // LISTAR TODOS OS CUPONS
    public List<Cupom> listarTodos() {
        List<Cupom> cupons = new ArrayList<>();

        String sql = "SELECT idcupom, codigoCupom, tipoDesconto, "
                + "valorDesconto, valorMinimoCompra, qttMaximaUso, "
                + "limitePorCliente, tipoUso, dataInicio, dataFinal, "
                + "observacao, promocao_idpromocao "
                + "FROM cupom";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Cupom cupom = new Cupom();

                cupom.setIdcupom(rs.getInt("idcupom"));
                cupom.setCodigoCupom(rs.getString("codigoCupom"));
                cupom.setTipoDesconto(rs.getString("tipoDesconto"));
                cupom.setValorDesconto(rs.getDouble("valorDesconto"));
                cupom.setValorMinimoCompra(
                        rs.getDouble("valorMinimoCompra"));
                cupom.setQttMaximaUso(rs.getInt("qttMaximaUso"));
                cupom.setLimitePorCliente(
                        rs.getInt("limitePorCliente"));
                cupom.setTipoUso(rs.getString("tipoUso"));
                cupom.setDataInicio(rs.getString("dataInicio"));
                cupom.setDataFinal(rs.getString("dataFinal"));
                cupom.setObservacao(rs.getString("observacao"));
                cupom.setPromocao_idpromocao(
                        rs.getInt("promocao_idpromocao"));

                cupons.add(cupom);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar cupons: "
                    + e.getMessage());
        }

        return cupons;
    }

    // BUSCAR CUPOM PELO ID
    public Cupom buscarPorId(int idcupom) {
        String sql = "SELECT idcupom, codigoCupom, tipoDesconto, "
                + "valorDesconto, valorMinimoCompra, qttMaximaUso, "
                + "limitePorCliente, tipoUso, dataInicio, dataFinal, "
                + "observacao, promocao_idpromocao "
                + "FROM cupom WHERE idcupom = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idcupom);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Cupom cupom = new Cupom();

                    cupom.setIdcupom(rs.getInt("idcupom"));
                    cupom.setCodigoCupom(rs.getString("codigoCupom"));
                    cupom.setTipoDesconto(rs.getString("tipoDesconto"));
                    cupom.setValorDesconto(
                            rs.getDouble("valorDesconto"));
                    cupom.setValorMinimoCompra(
                            rs.getDouble("valorMinimoCompra"));
                    cupom.setQttMaximaUso(
                            rs.getInt("qttMaximaUso"));
                    cupom.setLimitePorCliente(
                            rs.getInt("limitePorCliente"));
                    cupom.setTipoUso(rs.getString("tipoUso"));
                    cupom.setDataInicio(rs.getString("dataInicio"));
                    cupom.setDataFinal(rs.getString("dataFinal"));
                    cupom.setObservacao(rs.getString("observacao"));
                    cupom.setPromocao_idpromocao(
                            rs.getInt("promocao_idpromocao"));

                    return cupom;
                }
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar cupom: "
                    + e.getMessage());
        }

        return null;
    }

    // ATUALIZAR CUPOM PELO ID
    public boolean atualizar(int idcupom, Cupom cupom) {
        String sql = "UPDATE cupom SET codigoCupom = ?, "
                + "tipoDesconto = ?, valorDesconto = ?, "
                + "valorMinimoCompra = ?, qttMaximaUso = ?, "
                + "limitePorCliente = ?, tipoUso = ?, "
                + "dataInicio = ?, dataFinal = ?, observacao = ?, "
                + "promocao_idpromocao = ? "
                + "WHERE idcupom = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cupom.getCodigoCupom());
            stmt.setString(2, cupom.getTipoDesconto());
            stmt.setDouble(3, cupom.getValorDesconto());
            stmt.setDouble(4, cupom.getValorMinimoCompra());
            stmt.setInt(5, cupom.getQttMaximaUso());
            stmt.setInt(6, cupom.getLimitePorCliente());
            stmt.setString(7, cupom.getTipoUso());
            stmt.setString(8, cupom.getDataInicio());
            stmt.setString(9, cupom.getDataFinal());
            stmt.setString(10, cupom.getObservacao());
            stmt.setInt(11, cupom.getPromocao_idpromocao());
            stmt.setInt(12, idcupom);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar cupom: "
                    + e.getMessage());
            return false;
        }
    }

    // EXCLUIR CUPOM PELO ID
    public boolean excluir(int idcupom) {
        String sql = "DELETE FROM cupom WHERE idcupom = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idcupom);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao excluir cupom: "
                    + e.getMessage());
            return false;
        }
    }    
}
