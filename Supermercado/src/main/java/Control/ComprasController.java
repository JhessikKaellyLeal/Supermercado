/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import Conection.Conexao;
import Model.Compras;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Model.ItensCompras;
import java.sql.Statement;

/**
 *
 * @author IsraelSantos
 */
public class ComprasController {
    // CADASTRAR COMPRA
  public void cadastrar(
        double valorTotal,
        String situacao,
        String dataCompra,
        int idFornecedor,
        int idFuncionario,
        int idCargo,
        int idSetor,
        int idTurno
) throws SQLException {

    String sql = "INSERT INTO compras "
            + "(valorTotal, situacao, dataCompra, "
            + "fornecedores_idfornecedores, "
            + "Funcionario_idFuncionario, "
            + "Funcionario_cargos_idcargos, "
            + "Funcionario_setores_idsetores, "
            + "Funcionario_turnos_idturnos) "
            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    try (Connection conn = Conexao.conectar();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setDouble(1, valorTotal);
        stmt.setString(2, situacao);
        stmt.setString(3, dataCompra);
        stmt.setInt(4, idFornecedor);
        stmt.setInt(5, idFuncionario);
        stmt.setInt(6, idCargo);
        stmt.setInt(7, idSetor);
        stmt.setInt(8, idTurno);

        stmt.executeUpdate();
    }
}
    // LISTAR TODAS AS COMPRAS
    public List<Compras> listarTodos() {
        List<Compras> compras = new ArrayList<>();

        String sql = "SELECT idcompras, valorTotal, situacao, "
                + "dataCompra, fornecedores_idfornecedores, "
                + "Funcionario_idFuncionario "
                + "FROM compras";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Compras compra = new Compras();

                compra.setIdcompras(
                        rs.getInt("idcompras"));
                compra.setValorTotal(
                        rs.getDouble("valorTotal"));
                compra.setSituacao(
                        rs.getString("situacao"));
                compra.setDataCompra(
                        rs.getString("dataCompra"));
                compra.setFornecedores_idfornecedores(
                        rs.getInt("fornecedores_idfornecedores"));
                compra.setFuncionario_idFuncionario(
                        rs.getInt("Funcionario_idFuncionario"));

                compras.add(compra);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar compras: "
                    + e.getMessage());
        }

        return compras;
    }

    // BUSCAR COMPRA PELO ID
    public Compras buscarPorId(int id) {
        String sql = "SELECT idcompras, valorTotal, situacao, "
                + "dataCompra, fornecedores_idfornecedores, "
                + "Funcionario_idFuncionario "
                + "FROM compras WHERE idcompras = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Compras compra = new Compras();

                    compra.setIdcompras(
                            rs.getInt("idcompras"));
                    compra.setValorTotal(
                            rs.getDouble("valorTotal"));
                    compra.setSituacao(
                            rs.getString("situacao"));
                    compra.setDataCompra(
                            rs.getString("dataCompra"));
                    compra.setFornecedores_idfornecedores(
                            rs.getInt("fornecedores_idfornecedores"));
                    compra.setFuncionario_idFuncionario(
                            rs.getInt("Funcionario_idFuncionario"));

                    return compra;
                }
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar compra: "
                    + e.getMessage());
        }

        return null;
    }

    // ATUALIZAR COMPRA
    public boolean atualizar(int idOriginal, Compras compra) {
        String sql = "UPDATE compras SET "
                + "valorTotal = ?, situacao = ?, dataCompra = ?, "
                + "fornecedores_idfornecedores = ?, "
                + "Funcionario_idFuncionario = ? "
                + "WHERE idcompras = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDouble(1, compra.getValorTotal());
            stmt.setString(2, compra.getSituacao());
            stmt.setString(3, compra.getDataCompra());
            stmt.setInt(4,
                    compra.getFornecedores_idfornecedores());
            stmt.setInt(5,
                    compra.getFuncionario_idFuncionario());
            stmt.setInt(6, idOriginal);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar compra: "
                    + e.getMessage());
            return false;
        }
    }

    // EXCLUIR COMPRA
    public boolean excluir(int id) {
        String sql = "DELETE FROM compras WHERE idcompras = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao excluir compra: "
                    + e.getMessage());
            return false;
        }
    }
    
    
    public int cadastrarERetornarId(
            double valorTotal,
            String situacao,
            String dataCompra,
            int idFornecedor,
            int idFuncionario,
            int idCargo,
            int idSetor,
            int idTurno) throws SQLException {

        String sql = "INSERT INTO compras "
                + "(valorTotal, situacao, dataCompra, "
                + "fornecedores_idfornecedores, "
                + "Funcionario_idFuncionario, "
                + "Funcionario_cargos_idcargos, "
                + "Funcionario_setores_idsetores, "
                + "Funcionario_turnos_idturnos) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(
                     sql, java.sql.Statement.RETURN_GENERATED_KEYS)) {

            stmt.setDouble(1, valorTotal);
            stmt.setString(2, situacao);
            stmt.setString(3, dataCompra);
            stmt.setInt(4, idFornecedor);
            stmt.setInt(5, idFuncionario);
            stmt.setInt(6, idCargo);
            stmt.setInt(7, idSetor);
            stmt.setInt(8, idTurno);

            int linhas = stmt.executeUpdate();

            if (linhas > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        return rs.getInt(1);
                    }
                }
            }

            throw new SQLException(
                    "Não foi possível recuperar o ID da compra."
            );
        }
    }
    
    
    public int cadastrarCompraComItens(
            Compras compra,
            List<ItensCompras> itens) throws SQLException {

        if (compra == null || itens == null || itens.isEmpty()) {
            throw new IllegalArgumentException(
                    "Informe a compra e pelo menos um produto."
            );
        }

        String sqlCompra = "INSERT INTO compras "
                + "(valorTotal, situacao, dataCompra, "
                + "fornecedores_idfornecedores, "
                + "Funcionario_idFuncionario, "
                + "Funcionario_cargos_idcargos, "
                + "Funcionario_setores_idsetores, "
                + "Funcionario_turnos_idturnos) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        String sqlItem = "INSERT INTO itensCompra "
                + "(quantidade, precoUnitario, desconto, "
                + "compras_idcompras, produtos_idprodutos, "
                + "produtos_categorias_idcategorias, "
                + "produtos_marcas_idmarcas, "
                + "produtos_unidadeMedidas_idunidadeMedidas) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = Conexao.conectar()) {

            conn.setAutoCommit(false);

            try {
                int idCompra;

                // 1. Cadastrar a compra
                try (PreparedStatement ps = conn.prepareStatement(
                        sqlCompra, Statement.RETURN_GENERATED_KEYS)) {

                    ps.setDouble(1, compra.getValorTotal());
                    ps.setString(2, compra.getSituacao());
                    ps.setString(3, compra.getDataCompra());
                    ps.setInt(4, compra.getFornecedores_idfornecedores());
                    ps.setInt(5, compra.getFuncionario_idFuncionario());
                    ps.setInt(6, compra.getFuncionario_cargos_idcargos());
                    ps.setInt(7, compra.getFuncionario_setores_idsetores());
                    ps.setInt(8, compra.getFuncionario_turnos_idturnos());

                    if (ps.executeUpdate() != 1) {
                        throw new SQLException("Erro ao cadastrar compra.");
                    }

                    try (ResultSet rs = ps.getGeneratedKeys()) {
                        if (!rs.next()) {
                            throw new SQLException(
                                    "Não foi possível recuperar o ID da compra."
                            );
                        }

                        idCompra = rs.getInt(1);
                    }
                }

                // 2. Cadastrar todos os itens
                try (PreparedStatement ps = conn.prepareStatement(sqlItem)) {

                    for (ItensCompras item : itens) {

                        ps.setInt(1, item.getQuantidade());
                        ps.setDouble(2, item.getPrecoUnitario());
                        ps.setDouble(3, item.getDesconto());
                        ps.setInt(4, idCompra);
                        ps.setInt(5, item.getProdutos_idprodutos());
                        ps.setInt(6,
                                item.getProdutos_categorias_idcategorias());
                        ps.setInt(7,
                                item.getProdutos_marcas_idmarcas());
                        ps.setInt(8,
                                item.getProdutos_unidadeMedidas_idunidadeMedidas());

                        if (ps.executeUpdate() != 1) {
                            throw new SQLException(
                                    "Erro ao cadastrar item da compra."
                            );
                        }
                    }
                }

                // 3. Confirmar todas as gravações
                conn.commit();

                return idCompra;

            } catch (SQLException | RuntimeException e) {

                try {
                    conn.rollback();
                } catch (SQLException erroRollback) {
                    e.addSuppressed(erroRollback);
                }

                throw e;
            }
        }
    }
    
    public boolean atualizarCompraComItens(
            int idCompra,
            Compras compra,
            List<ItensCompras> itens) throws SQLException {

        if (idCompra <= 0 || compra == null
                || itens == null || itens.isEmpty()) {
            throw new IllegalArgumentException(
                    "Informe uma compra válida e pelo menos um item."
            );
        }

        String sqlCompra = "UPDATE compras SET "
                + "valorTotal = ?, situacao = ?, dataCompra = ?, "
                + "fornecedores_idfornecedores = ?, "
                + "Funcionario_idFuncionario = ?, "
                + "Funcionario_cargos_idcargos = ?, "
                + "Funcionario_setores_idsetores = ?, "
                + "Funcionario_turnos_idturnos = ? "
                + "WHERE idcompras = ?";

        String sqlExcluirItens = "DELETE FROM itensCompra "
                + "WHERE compras_idcompras = ?";

        String sqlInserirItem = "INSERT INTO itensCompra "
                + "(quantidade, precoUnitario, desconto, "
                + "compras_idcompras, produtos_idprodutos, "
                + "produtos_categorias_idcategorias, "
                + "produtos_marcas_idmarcas, "
                + "produtos_unidadeMedidas_idunidadeMedidas) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = Conexao.conectar()) {

            conn.setAutoCommit(false);

            try {

                // 1. Atualizar os dados da compra
                try (PreparedStatement ps =
                        conn.prepareStatement(sqlCompra)) {

                    ps.setDouble(1, compra.getValorTotal());
                    ps.setString(2, compra.getSituacao());
                    ps.setString(3, compra.getDataCompra());

                    ps.setInt(4,
                            compra.getFornecedores_idfornecedores());

                    ps.setInt(5,
                            compra.getFuncionario_idFuncionario());

                    ps.setInt(6,
                            compra.getFuncionario_cargos_idcargos());

                    ps.setInt(7,
                            compra.getFuncionario_setores_idsetores());

                    ps.setInt(8,
                            compra.getFuncionario_turnos_idturnos());

                    ps.setInt(9, idCompra);

                    if (ps.executeUpdate() != 1) {
                        throw new SQLException(
                                "Compra não encontrada para atualização."
                        );
                    }
                }

                // 2. Excluir os itens antigos da compra
                try (PreparedStatement ps =
                        conn.prepareStatement(sqlExcluirItens)) {

                    ps.setInt(1, idCompra);
                    ps.executeUpdate();
                }

                // 3. Inserir os itens atualizados
                try (PreparedStatement ps =
                        conn.prepareStatement(sqlInserirItem)) {

                    for (ItensCompras item : itens) {

                        ps.setInt(1, item.getQuantidade());
                        ps.setDouble(2, item.getPrecoUnitario());
                        ps.setDouble(3, item.getDesconto());

                        ps.setInt(4, idCompra);

                        ps.setInt(5,
                                item.getProdutos_idprodutos());

                        ps.setInt(6,
                                item.getProdutos_categorias_idcategorias());

                        ps.setInt(7,
                                item.getProdutos_marcas_idmarcas());

                        ps.setInt(8,
                                item.getProdutos_unidadeMedidas_idunidadeMedidas());

                        if (ps.executeUpdate() != 1) {
                            throw new SQLException(
                                    "Erro ao salvar item da compra."
                            );
                        }
                    }
                }

                // 4. Confirmar todas as alterações
                conn.commit();

                return true;

            } catch (SQLException | RuntimeException e) {

                // Cancelar alterações em caso de erro
                try {
                    conn.rollback();
                } catch (SQLException erroRollback) {
                    e.addSuppressed(erroRollback);
                }

                throw e;
            }
        }
    }
    
    
    public boolean excluirCompraComItens(int idCompra)
            throws SQLException {

        // Validar o ID da compra
        if (idCompra <= 0) {
            throw new IllegalArgumentException(
                    "Informe um ID de compra válido!"
            );
        }

        String sqlExcluirItens =
                "DELETE FROM itensCompra "
                + "WHERE compras_idcompras = ?";

        String sqlExcluirCompra =
                "DELETE FROM compras "
                + "WHERE idcompras = ?";

        try (Connection conn = Conexao.conectar()) {

            // Iniciar transação
            conn.setAutoCommit(false);

            try {

                // 1. Excluir os itens vinculados à compra
                try (PreparedStatement ps =
                        conn.prepareStatement(sqlExcluirItens)) {

                    ps.setInt(1, idCompra);
                    ps.executeUpdate();
                }

                // 2. Excluir a compra
                int linhasExcluidas;

                try (PreparedStatement ps =
                        conn.prepareStatement(sqlExcluirCompra)) {

                    ps.setInt(1, idCompra);

                    linhasExcluidas = ps.executeUpdate();
                }

                // 3. Verificar se a compra foi excluída
                if (linhasExcluidas != 1) {
                    throw new SQLException(
                            "Compra não encontrada para exclusão!"
                    );
                }

                // 4. Confirmar exclusão
                conn.commit();

                return true;

            } catch (SQLException | RuntimeException e) {

                // Desfazer alterações em caso de erro
                try {
                    conn.rollback();
                } catch (SQLException erroRollback) {
                    e.addSuppressed(erroRollback);
                }

                throw e;
            }
        }
    }

}
