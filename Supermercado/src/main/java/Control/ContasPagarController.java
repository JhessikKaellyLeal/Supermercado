
package Control;

import Conection.Conexao;
import Model.ContasPagar;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContasPagarController {

    // CADASTRAR
    public boolean cadastrar(ContasPagar conta) throws SQLException {

        String sql = "INSERT INTO contasPagar "
                + "(valor, descricao, dataVencimento, dataPagamento, "
                + "situacao, fornecedores_idfornecedores) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(
                     sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setDouble(1, conta.getValor());
            stmt.setString(2, conta.getDescricao());
            stmt.setString(3, conta.getDataVencimento());
            stmt.setString(4, conta.getDataPagamento());
            stmt.setString(5, conta.getSituacao());
            stmt.setInt(6, conta.getFornecedores_idfornecedores());

            int linhas = stmt.executeUpdate();

            if (linhas > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        conta.setIdcontasPagar(rs.getInt(1));
                    }
                }

                return true;
            }
        }

        return false;
    }

    // LISTAR TODAS
    public List<ContasPagar> listar() throws SQLException {

        List<ContasPagar> lista = new ArrayList<>();

        String sql = "SELECT * FROM contasPagar "
                + "ORDER BY idcontasPagar DESC";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                ContasPagar conta = new ContasPagar();

                conta.setIdcontasPagar(rs.getInt("idcontasPagar"));
                conta.setValor(rs.getDouble("valor"));
                conta.setDescricao(rs.getString("descricao"));
                conta.setDataVencimento(rs.getString("dataVencimento"));
                conta.setDataPagamento(rs.getString("dataPagamento"));
                conta.setSituacao(rs.getString("situacao"));
                conta.setFornecedores_idfornecedores(
                        rs.getInt("fornecedores_idfornecedores"));

                lista.add(conta);
            }
        }

        return lista;
    }

    // BUSCAR POR ID
    public ContasPagar buscarPorId(int id) throws SQLException {

        String sql = "SELECT * FROM contasPagar "
                + "WHERE idcontasPagar = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {

                    ContasPagar conta = new ContasPagar();

                    conta.setIdcontasPagar(rs.getInt("idcontasPagar"));
                    conta.setValor(rs.getDouble("valor"));
                    conta.setDescricao(rs.getString("descricao"));
                    conta.setDataVencimento(rs.getString("dataVencimento"));
                    conta.setDataPagamento(rs.getString("dataPagamento"));
                    conta.setSituacao(rs.getString("situacao"));
                    conta.setFornecedores_idfornecedores(
                            rs.getInt("fornecedores_idfornecedores"));

                    return conta;
                }
            }
        }

        return null;
    }

    // ATUALIZAR
    public boolean atualizar(ContasPagar conta) throws SQLException {

        String sql = "UPDATE contasPagar SET "
                + "valor = ?, "
                + "descricao = ?, "
                + "dataVencimento = ?, "
                + "dataPagamento = ?, "
                + "situacao = ?, "
                + "fornecedores_idfornecedores = ? "
                + "WHERE idcontasPagar = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDouble(1, conta.getValor());
            stmt.setString(2, conta.getDescricao());
            stmt.setString(3, conta.getDataVencimento());
            stmt.setString(4, conta.getDataPagamento());
            stmt.setString(5, conta.getSituacao());
            stmt.setInt(6, conta.getFornecedores_idfornecedores());
            stmt.setInt(7, conta.getIdcontasPagar());

            return stmt.executeUpdate() > 0;
        }
    }

    // EXCLUIR
    public boolean excluir(int id) throws SQLException {

        String sql = "DELETE FROM contasPagar "
                + "WHERE idcontasPagar = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            return stmt.executeUpdate() > 0;
        }
    }

    // BUSCAR POR SITUAÇÃO
    public List<ContasPagar> buscarPorSituacao(String situacao)
            throws SQLException {

        List<ContasPagar> lista = new ArrayList<>();

        String sql = "SELECT * FROM contasPagar "
                + "WHERE situacao = ? ORDER BY dataVencimento";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, situacao);

            try (ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {

                    ContasPagar conta = new ContasPagar();

                    conta.setIdcontasPagar(rs.getInt("idcontasPagar"));
                    conta.setValor(rs.getDouble("valor"));
                    conta.setDescricao(rs.getString("descricao"));
                    conta.setDataVencimento(rs.getString("dataVencimento"));
                    conta.setDataPagamento(rs.getString("dataPagamento"));
                    conta.setSituacao(rs.getString("situacao"));
                    conta.setFornecedores_idfornecedores(
                            rs.getInt("fornecedores_idfornecedores"));

                    lista.add(conta);
                }
            }
        }

        return lista;
    }
}
