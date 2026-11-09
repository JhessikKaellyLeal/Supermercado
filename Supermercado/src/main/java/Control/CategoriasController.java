/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control;

import Conection.Conexao;
import Model.Categorias;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;

/**
 *
 * @author CleicianeGomes
 */

   


public class CategoriasController {

    // MÉTODO PARA CADASTRAR CATEGORIA
    public void cadastrar(Categorias categoria) {

        String sql = "INSERT INTO categorias (nome, descricao, status) "
                + "VALUES (?, ?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, categoria.getNome());
            stmt.setString(2, categoria.getDescricao());
            stmt.setInt(3, categoria.getStatus());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null,
                    "Categoria cadastrada com sucesso!");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Erro ao cadastrar categoria: " + e.getMessage());
        }
    }

    // MÉTODO PARA LISTAR CATEGORIAS
    public List<Categorias> listar() {

        List<Categorias> lista = new ArrayList<>();

        String sql = "SELECT * FROM categorias";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                Categorias categoria = new Categorias();

                categoria.setIdcategorias(rs.getInt("idcategorias"));
                categoria.setNome(rs.getString("nome"));
                categoria.setDescricao(rs.getString("descricao"));
                categoria.setStatus(rs.getInt("status"));

                lista.add(categoria);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Erro ao listar categorias: " + e.getMessage());
        }

        return lista;
    }

    // MÉTODO PARA ATUALIZAR CATEGORIA
    public void atualizar(Categorias categoria) {

        String sql = "UPDATE categorias SET nome = ?, descricao = ?, "
                + "status = ? WHERE idcategorias = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, categoria.getNome());
            stmt.setString(2, categoria.getDescricao());
            stmt.setInt(3, categoria.getStatus());
            stmt.setInt(4, categoria.getIdcategorias());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null,
                    "Categoria atualizada com sucesso!");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Erro ao atualizar categoria: " + e.getMessage());
        }
    }

    // MÉTODO PARA EXCLUIR CATEGORIA
    public void excluir(int id) {

        String sql = "DELETE FROM categorias WHERE idcategorias = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null,
                    "Categoria excluída com sucesso!");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Erro ao excluir categoria: " + e.getMessage());
        }
    }
}
