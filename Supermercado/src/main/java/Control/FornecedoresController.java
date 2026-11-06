
package control;

import Conection.Conexao;
import Model.Fornecedores;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FornecedoresController {

    // CADASTRAR FORNECEDOR
    public boolean cadastrar(Fornecedores fornecedor) {

        String sql = "INSERT INTO fornecedores "
                + "(nomeResponsavel, telefone, email, ramal, cnpj, "
                + "razaoSocial, endereco, tipo, cep, inscricaoestadual) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, fornecedor.getNomeResponsavel());
            stmt.setString(2, fornecedor.getTelefone());
            stmt.setString(3, fornecedor.getEmail());
            stmt.setString(4, fornecedor.getRamal());
            stmt.setString(5, fornecedor.getCnpj());
            stmt.setString(6, fornecedor.getRazaoSocial());
            stmt.setString(7, fornecedor.getEndereco());
            stmt.setString(8, fornecedor.getTipo());
            stmt.setString(9, fornecedor.getCep());
            stmt.setString(10, fornecedor.getInscricaoestadual());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar fornecedor: "
                    + e.getMessage());
            return false;
        }
    }

    // LISTAR TODOS OS FORNECEDORES
    public List<Fornecedores> listarTodos() {

        List<Fornecedores> fornecedores = new ArrayList<>();

        String sql = "SELECT idfornecedores, nomeResponsavel, telefone, "
                + "email, ramal, cnpj, razaoSocial, endereco, tipo, "
                + "cep, inscricaoestadual FROM fornecedores";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                Fornecedores fornecedor = new Fornecedores();

                fornecedor.setIdfornecedores(
                        rs.getInt("idfornecedores"));

                fornecedor.setNomeResponsavel(
                        rs.getString("nomeResponsavel"));

                fornecedor.setTelefone(rs.getString("telefone"));
                fornecedor.setEmail(rs.getString("email"));
                fornecedor.setRamal(rs.getString("ramal"));
                fornecedor.setCnpj(rs.getString("cnpj"));

                fornecedor.setRazaoSocial(
                        rs.getString("razaoSocial"));

                fornecedor.setEndereco(rs.getString("endereco"));
                fornecedor.setTipo(rs.getString("tipo"));
                fornecedor.setCep(rs.getString("cep"));

                fornecedor.setInscricaoestadual(
                        rs.getString("inscricaoestadual"));

                fornecedores.add(fornecedor);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar fornecedores: "
                    + e.getMessage());
        }

        return fornecedores;
    }

    // BUSCAR FORNECEDOR PELO CNPJ
    public Fornecedores buscarPorCnpj(String cnpj) {

        String sql = "SELECT idfornecedores, nomeResponsavel, telefone, "
                + "email, ramal, cnpj, razaoSocial, endereco, tipo, "
                + "cep, inscricaoestadual "
                + "FROM fornecedores WHERE cnpj = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cnpj);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {

                    Fornecedores fornecedor = new Fornecedores();

                    fornecedor.setIdfornecedores(
                            rs.getInt("idfornecedores"));

                    fornecedor.setNomeResponsavel(
                            rs.getString("nomeResponsavel"));

                    fornecedor.setTelefone(
                            rs.getString("telefone"));

                    fornecedor.setEmail(rs.getString("email"));
                    fornecedor.setRamal(rs.getString("ramal"));
                    fornecedor.setCnpj(rs.getString("cnpj"));

                    fornecedor.setRazaoSocial(
                            rs.getString("razaoSocial"));

                    fornecedor.setEndereco(
                            rs.getString("endereco"));

                    fornecedor.setTipo(rs.getString("tipo"));
                    fornecedor.setCep(rs.getString("cep"));

                    fornecedor.setInscricaoestadual(
                            rs.getString("inscricaoestadual"));

                    return fornecedor;
                }
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar fornecedor: "
                    + e.getMessage());
        }

        return null;
    }

    // ATUALIZAR FORNECEDOR PELO CNPJ
    public boolean atualizar(String cnpjOriginal,
            Fornecedores fornecedor) {

        String sql = "UPDATE fornecedores SET "
                + "nomeResponsavel = ?, telefone = ?, email = ?, "
                + "ramal = ?, cnpj = ?, razaoSocial = ?, "
                + "endereco = ?, tipo = ?, cep = ?, "
                + "inscricaoestadual = ? "
                + "WHERE cnpj = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, fornecedor.getNomeResponsavel());
            stmt.setString(2, fornecedor.getTelefone());
            stmt.setString(3, fornecedor.getEmail());
            stmt.setString(4, fornecedor.getRamal());
            stmt.setString(5, fornecedor.getCnpj());
            stmt.setString(6, fornecedor.getRazaoSocial());
            stmt.setString(7, fornecedor.getEndereco());
            stmt.setString(8, fornecedor.getTipo());
            stmt.setString(9, fornecedor.getCep());
            stmt.setString(10, fornecedor.getInscricaoestadual());
            stmt.setString(11, cnpjOriginal);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar fornecedor: "
                    + e.getMessage());
            return false;
        }
    }

    // EXCLUIR FORNECEDOR PELO CNPJ
    public boolean excluir(String cnpj) {

        String sql = "DELETE FROM fornecedores WHERE cnpj = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cnpj);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao excluir fornecedor: "
                    + e.getMessage());
            return false;
        }
    }
    
    
}
