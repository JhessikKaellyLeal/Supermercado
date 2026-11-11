package Control;


import Conection.Conexao;
import Model.Caixas;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CaixasController {

    // CADASTRAR CAIXA
    public boolean cadastrar(Caixas caixa) {

        String sql = "INSERT INTO caixas "
                + "(nome, tipo, status, observacao) "
                + "VALUES (?, ?, ?, ?)";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, caixa.getNome());
            stmt.setString(2, caixa.getTipo());
            stmt.setInt(3, caixa.getStatus());
            stmt.setString(4, caixa.getObservacao());

            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar caixa: " + e.getMessage());
            return false;
        }
    }

    // LISTAR TODOS OS CAIXAS
    public ArrayList<Caixas> listar() {

        ArrayList<Caixas> lista = new ArrayList<>();

        String sql = "SELECT * FROM caixas ORDER BY idcaixas";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                Caixas caixa = new Caixas();

                caixa.setIdcaixas(rs.getInt("idcaixas"));
                caixa.setNome(rs.getString("nome"));
                caixa.setTipo(rs.getString("tipo"));
                caixa.setStatus(rs.getInt("status"));
                caixa.setObservacao(rs.getString("observacao"));

                lista.add(caixa);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar caixas: " + e.getMessage());
        }

        return lista;
    }

    // BUSCAR CAIXA PELO ID
    public Caixas buscarPorId(int id) {

        String sql = "SELECT * FROM caixas WHERE idcaixas = ?";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {

                    Caixas caixa = new Caixas();

                    caixa.setIdcaixas(rs.getInt("idcaixas"));
                    caixa.setNome(rs.getString("nome"));
                    caixa.setTipo(rs.getString("tipo"));
                    caixa.setStatus(rs.getInt("status"));
                    caixa.setObservacao(rs.getString("observacao"));

                    return caixa;
                }
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar caixa: " + e.getMessage());
        }

        return null;
    }

    // ATUALIZAR CAIXA
    public boolean atualizar(Caixas caixa) {

        String sql = "UPDATE caixas SET nome = ?, tipo = ?, "
                + "status = ?, observacao = ? WHERE idcaixas = ?";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, caixa.getNome());
            stmt.setString(2, caixa.getTipo());
            stmt.setInt(3, caixa.getStatus());
            stmt.setString(4, caixa.getObservacao());
            stmt.setInt(5, caixa.getIdcaixas());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar caixa: " + e.getMessage());
            return false;
        }
    }

    // EXCLUIR CAIXA
    public boolean excluir(int id) {

        String sql = "DELETE FROM caixas WHERE idcaixas = ?";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, id);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Erro ao excluir caixa: " + e.getMessage());
            return false;
        }
    }

    public boolean cadastrar(String nome, String tipo, int status, String observacao) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}




   