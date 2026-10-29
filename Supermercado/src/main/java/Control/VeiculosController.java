/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control;

/**
 *
 * @author AntônioVieira
 */
import Conection.Conexao;
import java.sql.*;
import java.util.*;
public class VeiculosController {
   public boolean cadastrar(String tipo, String marca,
            String modelo, int ano, String cor,
            double capacidade, String status,
            String placa, String observacoes) throws SQLException {

        String sql = "INSERT INTO veiculos "
            + "(tipoVeiculo, marca, modelo, anoVeiculo, cor, "
            + "capacidadeCarga, statusVeiculo, placa, observacoes) "
            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = Conexao.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, tipo);
            ps.setString(2, marca);
            ps.setString(3, modelo);
            ps.setInt(4, ano);
            ps.setString(5, cor);
            ps.setDouble(6, capacidade);
            ps.setString(7, status);
            ps.setString(8, placa);
            ps.setString(9, observacoes);
            return ps.executeUpdate() > 0;
        }
    }

    public List<Map<String, Object>> listar() throws SQLException {
        List<Map<String, Object>> lista = new ArrayList<>();
        try (Connection con = Conexao.conectar();
             PreparedStatement ps = con.prepareStatement("SELECT * FROM veiculos");
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Map<String, Object> m = new LinkedHashMap<>();
                ResultSetMetaData md = rs.getMetaData();
                for (int i = 1; i <= md.getColumnCount(); i++)
                    m.put(md.getColumnLabel(i), rs.getObject(i));
                lista.add(m);
            }
        }
        return lista;
    }

    public boolean atualizar(int id, String tipo, String marca,
            String modelo, int ano, String cor, double capacidade,
            String status, String placa, String observacoes) throws SQLException {

        String sql = "UPDATE veiculos SET tipoVeiculo=?, marca=?, modelo=?, "
            + "anoVeiculo=?, cor=?, capacidadeCarga=?, statusVeiculo=?, "
            + "placa=?, observacoes=? WHERE idveiculos=?";

        try (Connection con = Conexao.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, tipo);
            ps.setString(2, marca);
            ps.setString(3, modelo);
            ps.setInt(4, ano);
            ps.setString(5, cor);
            ps.setDouble(6, capacidade);
            ps.setString(7, status);
            ps.setString(8, placa);
            ps.setString(9, observacoes);
            ps.setInt(10, id);
            return ps.executeUpdate() > 0;
        }
    }

    public boolean excluir(int id) throws SQLException {
        try (Connection con = Conexao.conectar();
             PreparedStatement ps = con.prepareStatement(
                 "DELETE FROM veiculos WHERE idveiculos=?")) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    } 
}
