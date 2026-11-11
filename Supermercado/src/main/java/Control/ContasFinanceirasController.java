
package Control;

import Conection.Conexao;
import Model.ContasFinanceiras;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContasFinanceirasController {

    // CADASTRAR
    public boolean cadastrar(ContasFinanceiras conta) throws SQLException {

        String sql = "INSERT INTO contasFinanceiras "
                + "(nome, tipo, agencia, numeroConta, banco, ativo) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(
                     sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, conta.getNome());
            stmt.setString(2, conta.getTipo());
            stmt.setString(3, conta.getAgencia());
            stmt.setString(4, conta.getNumeroConta());
            stmt.setString(5, conta.getBanco());
            stmt.setInt(6, conta.getAtivo());

            int linhas = stmt.executeUpdate();

            if (linhas > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        conta.setIdcontasFinanceiras(rs.getInt(1));
                    }
                }

                return true;
            }
        }

        return false;
    }

    // LISTAR TODAS
    public List<ContasFinanceiras> listar() throws SQLException {

        List<ContasFinanceiras> lista = new ArrayList<>();

        String sql = "SELECT * FROM contasFinanceiras "
                + "ORDER BY idcontasFinanceiras DESC";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                ContasFinanceiras conta = new ContasFinanceiras();

                conta.setIdcontasFinanceiras(
                        rs.getInt("idcontasFinanceiras"));
                conta.setNome(rs.getString("nome"));
                conta.setTipo(rs.getString("tipo"));
                conta.setAgencia(rs.getString("agencia"));
                conta.setNumeroConta(rs.getString("numeroConta"));
                conta.setBanco(rs.getString("banco"));
                conta.setAtivo(rs.getInt("ativo"));

                lista.add(conta);
            }
        }

        return lista;
    }

    // BUSCAR POR ID
    public ContasFinanceiras buscarPorId(int id) throws SQLException {

        String sql = "SELECT * FROM contasFinanceiras "
                + "WHERE idcontasFinanceiras = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {

                    ContasFinanceiras conta = new ContasFinanceiras();

                    conta.setIdcontasFinanceiras(
                            rs.getInt("idcontasFinanceiras"));
                    conta.setNome(rs.getString("nome"));
                    conta.setTipo(rs.getString("tipo"));
                    conta.setAgencia(rs.getString("agencia"));
                    conta.setNumeroConta(rs.getString("numeroConta"));
                    conta.setBanco(rs.getString("banco"));
                    conta.setAtivo(rs.getInt("ativo"));

                    return conta;
                }
            }
        }

        return null;
    }

    // ATUALIZAR
    public boolean atualizar(ContasFinanceiras conta) throws SQLException {

        String sql = "UPDATE contasFinanceiras SET "
                + "nome = ?, "
                + "tipo = ?, "
                + "agencia = ?, "
                + "numeroConta = ?, "
                + "banco = ?, "
                + "ativo = ? "
                + "WHERE idcontasFinanceiras = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, conta.getNome());
            stmt.setString(2, conta.getTipo());
            stmt.setString(3, conta.getAgencia());
            stmt.setString(4, conta.getNumeroConta());
            stmt.setString(5, conta.getBanco());
            stmt.setInt(6, conta.getAtivo());
            stmt.setInt(7, conta.getIdcontasFinanceiras());

            return stmt.executeUpdate() > 0;
        }
    }

    // EXCLUIR
    public boolean excluir(int id) throws SQLException {

        String sql = "DELETE FROM contasFinanceiras "
                + "WHERE idcontasFinanceiras = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            return stmt.executeUpdate() > 0;
        }
    }

    // LISTAR CONTAS ATIVAS
    public List<ContasFinanceiras> listarAtivas() throws SQLException {

        List<ContasFinanceiras> lista = new ArrayList<>();

        String sql = "SELECT * FROM contasFinanceiras "
                + "WHERE ativo = 1 ORDER BY nome";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                ContasFinanceiras conta = new ContasFinanceiras();

                conta.setIdcontasFinanceiras(
                        rs.getInt("idcontasFinanceiras"));
                conta.setNome(rs.getString("nome"));
                conta.setTipo(rs.getString("tipo"));
                conta.setAgencia(rs.getString("agencia"));
                conta.setNumeroConta(rs.getString("numeroConta"));
                conta.setBanco(rs.getString("banco"));
                conta.setAtivo(rs.getInt("ativo"));

                lista.add(conta);
            }
        }

        return lista;
    }

    // BUSCAR POR NOME
    public List<ContasFinanceiras> buscarPorNome(String nome)
            throws SQLException {

        List<ContasFinanceiras> lista = new ArrayList<>();

        String sql = "SELECT * FROM contasFinanceiras "
                + "WHERE nome LIKE ? ORDER BY nome";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + nome + "%");

            try (ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {

                    ContasFinanceiras conta = new ContasFinanceiras();

                    conta.setIdcontasFinanceiras(
                            rs.getInt("idcontasFinanceiras"));
                    conta.setNome(rs.getString("nome"));
                    conta.setTipo(rs.getString("tipo"));
                    conta.setAgencia(rs.getString("agencia"));
                    conta.setNumeroConta(rs.getString("numeroConta"));
                    conta.setBanco(rs.getString("banco"));
                    conta.setAtivo(rs.getInt("ativo"));

                    lista.add(conta);
                }
            }
        }

        return lista;
    }
}
