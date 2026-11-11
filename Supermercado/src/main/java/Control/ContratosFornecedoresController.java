
package Control;

import Conection.Conexao;
import Model.ContratosFornecedores;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContratosFornecedoresController {

    // CADASTRAR
    public boolean cadastrar(ContratosFornecedores contrato)
            throws SQLException {

        String sql = "INSERT INTO contratosFornecedores "
                + "(numero, dataInicio, dataFinal, valorMensal, tipo, "
                + "descricao, prazo, renovacaoAutomatica, valorTotal, "
                + "multa, clausulaRecisao, observacao, arquivo, "
                + "fornecedores_idfornecedores) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(
                     sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, contrato.getNumero());
            stmt.setString(2, contrato.getDataInicio());
            stmt.setString(3, contrato.getDataFinal());
            stmt.setDouble(4, contrato.getValorMensal());
            stmt.setString(5, contrato.getTipo());
            stmt.setString(6, contrato.getDescricao());
            stmt.setInt(7, contrato.getPrazo());
            stmt.setInt(8, contrato.getRenovacaoAutomatica());
            stmt.setDouble(9, contrato.getValorTotal());
            stmt.setDouble(10, contrato.getMulta());
            stmt.setString(11, contrato.getClausulaRecisao());
            stmt.setString(12, contrato.getObservacao());
            stmt.setBytes(13, contrato.getArquivo());
            stmt.setInt(14, contrato.getFornecedores_idfornecedores());

            int linhas = stmt.executeUpdate();

            if (linhas > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        contrato.setIdcontratosFornecedores(rs.getInt(1));
                    }
                }

                return true;
            }
        }

        return false;
    }

    // LISTAR TODOS
    public List<ContratosFornecedores> listar() throws SQLException {

        List<ContratosFornecedores> lista = new ArrayList<>();

        String sql = "SELECT * FROM contratosFornecedores "
                + "ORDER BY idcontratosFornecedores DESC";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                lista.add(mapearContrato(rs));
            }
        }

        return lista;
    }

    // BUSCAR POR ID
    public ContratosFornecedores buscarPorId(int id) throws SQLException {

        String sql = "SELECT * FROM contratosFornecedores "
                + "WHERE idcontratosFornecedores = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapearContrato(rs);
                }
            }
        }

        return null;
    }

    // ATUALIZAR
    public boolean atualizar(ContratosFornecedores contrato)
            throws SQLException {

        String sql = "UPDATE contratosFornecedores SET "
                + "numero = ?, "
                + "dataInicio = ?, "
                + "dataFinal = ?, "
                + "valorMensal = ?, "
                + "tipo = ?, "
                + "descricao = ?, "
                + "prazo = ?, "
                + "renovacaoAutomatica = ?, "
                + "valorTotal = ?, "
                + "multa = ?, "
                + "clausulaRecisao = ?, "
                + "observacao = ?, "
                + "arquivo = ?, "
                + "fornecedores_idfornecedores = ? "
                + "WHERE idcontratosFornecedores = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, contrato.getNumero());
            stmt.setString(2, contrato.getDataInicio());
            stmt.setString(3, contrato.getDataFinal());
            stmt.setDouble(4, contrato.getValorMensal());
            stmt.setString(5, contrato.getTipo());
            stmt.setString(6, contrato.getDescricao());
            stmt.setInt(7, contrato.getPrazo());
            stmt.setInt(8, contrato.getRenovacaoAutomatica());
            stmt.setDouble(9, contrato.getValorTotal());
            stmt.setDouble(10, contrato.getMulta());
            stmt.setString(11, contrato.getClausulaRecisao());
            stmt.setString(12, contrato.getObservacao());
            stmt.setBytes(13, contrato.getArquivo());
            stmt.setInt(14, contrato.getFornecedores_idfornecedores());
            stmt.setInt(15, contrato.getIdcontratosFornecedores());

            return stmt.executeUpdate() > 0;
        }
    }

    // EXCLUIR
    public boolean excluir(int id) throws SQLException {

        String sql = "DELETE FROM contratosFornecedores "
                + "WHERE idcontratosFornecedores = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            return stmt.executeUpdate() > 0;
        }
    }

    // BUSCAR POR FORNECEDOR
    public List<ContratosFornecedores> buscarPorFornecedor(int idFornecedor)
            throws SQLException {

        List<ContratosFornecedores> lista = new ArrayList<>();

        String sql = "SELECT * FROM contratosFornecedores "
                + "WHERE fornecedores_idfornecedores = ? "
                + "ORDER BY dataInicio DESC";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idFornecedor);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapearContrato(rs));
                }
            }
        }

        return lista;
    }

    // BUSCAR POR NÚMERO
    public ContratosFornecedores buscarPorNumero(int numero)
            throws SQLException {

        String sql = "SELECT * FROM contratosFornecedores "
                + "WHERE numero = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, numero);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapearContrato(rs);
                }
            }
        }

        return null;
    }

    // MAPEAR RESULTADO DO BANCO PARA MODEL
    private ContratosFornecedores mapearContrato(ResultSet rs)
            throws SQLException {

        ContratosFornecedores contrato = new ContratosFornecedores();

        contrato.setIdcontratosFornecedores(
                rs.getInt("idcontratosFornecedores"));
        contrato.setNumero(rs.getInt("numero"));
        contrato.setDataInicio(rs.getString("dataInicio"));
        contrato.setDataFinal(rs.getString("dataFinal"));
        contrato.setValorMensal(rs.getDouble("valorMensal"));
        contrato.setTipo(rs.getString("tipo"));
        contrato.setDescricao(rs.getString("descricao"));
        contrato.setPrazo(rs.getInt("prazo"));
        contrato.setRenovacaoAutomatica(
                rs.getInt("renovacaoAutomatica"));
        contrato.setValorTotal(rs.getDouble("valorTotal"));
        contrato.setMulta(rs.getDouble("multa"));
        contrato.setClausulaRecisao(rs.getString("clausulaRecisao"));
        contrato.setObservacao(rs.getString("observacao"));
        contrato.setArquivo(rs.getBytes("arquivo"));
        contrato.setFornecedores_idfornecedores(
                rs.getInt("fornecedores_idfornecedores"));

        return contrato;
    }
}
