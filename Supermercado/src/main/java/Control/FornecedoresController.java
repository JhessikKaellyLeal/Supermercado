
package Control;

import Conection.Conexao;
import Model.Fornecedores;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FornecedoresController {

    public void cadastrar(Fornecedores fornecedor) throws SQLException {
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

            stmt.executeUpdate();
        }
    }

    public List<Fornecedores> listar() throws SQLException {
        List<Fornecedores> lista = new ArrayList<>();

        String sql = "SELECT * FROM fornecedores";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                lista.add(mapearFornecedor(rs));
            }
        }

        return lista;
    }

    public Fornecedores buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM fornecedores "
                + "WHERE idfornecedores = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapearFornecedor(rs);
                }
            }
        }

        return null;
    }

    public Fornecedores buscarPorCnpj(String cnpj) throws SQLException {
        String sql = "SELECT * FROM fornecedores WHERE cnpj = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cnpj);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapearFornecedor(rs);
                }
            }
        }

        return null;
    }

    public List<Fornecedores> buscarPorNome(String nome)
            throws SQLException {

        List<Fornecedores> lista = new ArrayList<>();

        String sql = "SELECT * FROM fornecedores "
                + "WHERE nomeResponsavel LIKE ? OR razaoSocial LIKE ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + nome + "%");
            stmt.setString(2, "%" + nome + "%");

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapearFornecedor(rs));
                }
            }
        }

        return lista;
    }

    public void atualizar(Fornecedores fornecedor) throws SQLException {
        String sql = "UPDATE fornecedores SET "
                + "nomeResponsavel = ?, telefone = ?, email = ?, "
                + "ramal = ?, cnpj = ?, razaoSocial = ?, endereco = ?, "
                + "tipo = ?, cep = ?, inscricaoestadual = ? "
                + "WHERE idfornecedores = ?";

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
            stmt.setInt(11, fornecedor.getIdfornecedores());

            stmt.executeUpdate();
        }
    }

    public void excluir(int id) throws SQLException {
        String sql = "DELETE FROM fornecedores "
                + "WHERE idfornecedores = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    private Fornecedores mapearFornecedor(ResultSet rs)
            throws SQLException {

        Fornecedores fornecedor = new Fornecedores();

        fornecedor.setIdfornecedores(rs.getInt("idfornecedores"));
        fornecedor.setNomeResponsavel(rs.getString("nomeResponsavel"));
        fornecedor.setTelefone(rs.getString("telefone"));
        fornecedor.setEmail(rs.getString("email"));
        fornecedor.setRamal(rs.getString("ramal"));
        fornecedor.setCnpj(rs.getString("cnpj"));
        fornecedor.setRazaoSocial(rs.getString("razaoSocial"));
        fornecedor.setEndereco(rs.getString("endereco"));
        fornecedor.setTipo(rs.getString("tipo"));
        fornecedor.setCep(rs.getString("cep"));
        fornecedor.setInscricaoestadual(rs.getString("inscricaoestadual"));

        return fornecedor;
    }
}
