
package Control;

import Conection.Conexao;
import Model.Compras;
import Model.ItensCompras;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ComprasController {

    // CADASTRAR
    public boolean cadastrar(Compras compra) throws SQLException {

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
                     sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setDouble(1, compra.getValorTotal());
            stmt.setString(2, compra.getSituacao());
            stmt.setString(3, compra.getDataCompra());
            stmt.setInt(4, compra.getFornecedores_idfornecedores());
            stmt.setInt(5, compra.getFuncionario_idFuncionario());
            stmt.setInt(6, compra.getFuncionario_cargos_idcargos());
            stmt.setInt(7, compra.getFuncionario_setores_idsetores());
            stmt.setInt(8, compra.getFuncionario_turnos_idturnos());

            int linhas = stmt.executeUpdate();

            if (linhas > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        compra.setIdcompras(rs.getInt(1));
                    }
                }

                return true;
            }
        }

        return false;
    }

    // LISTAR TODAS
    public List<Compras> listar() throws SQLException {

        List<Compras> lista = new ArrayList<>();

        String sql = "SELECT * FROM compras ORDER BY idcompras DESC";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                Compras compra = new Compras();

                compra.setIdcompras(rs.getInt("idcompras"));
                compra.setValorTotal(rs.getDouble("valorTotal"));
                compra.setSituacao(rs.getString("situacao"));
                compra.setDataCompra(rs.getString("dataCompra"));
                compra.setFornecedores_idfornecedores(
                        rs.getInt("fornecedores_idfornecedores"));
                compra.setFuncionario_idFuncionario(
                        rs.getInt("Funcionario_idFuncionario"));
                compra.setFuncionario_cargos_idcargos(
                        rs.getInt("Funcionario_cargos_idcargos"));
                compra.setFuncionario_setores_idsetores(
                        rs.getInt("Funcionario_setores_idsetores"));
                compra.setFuncionario_turnos_idturnos(
                        rs.getInt("Funcionario_turnos_idturnos"));

                lista.add(compra);
            }
        }

        return lista;
    }

    // BUSCAR POR ID
    public Compras buscarPorId(int id) throws SQLException {

        String sql = "SELECT * FROM compras WHERE idcompras = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {

                    Compras compra = new Compras();

                    compra.setIdcompras(rs.getInt("idcompras"));
                    compra.setValorTotal(rs.getDouble("valorTotal"));
                    compra.setSituacao(rs.getString("situacao"));
                    compra.setDataCompra(rs.getString("dataCompra"));
                    compra.setFornecedores_idfornecedores(
                            rs.getInt("fornecedores_idfornecedores"));
                    compra.setFuncionario_idFuncionario(
                            rs.getInt("Funcionario_idFuncionario"));
                    compra.setFuncionario_cargos_idcargos(
                            rs.getInt("Funcionario_cargos_idcargos"));
                    compra.setFuncionario_setores_idsetores(
                            rs.getInt("Funcionario_setores_idsetores"));
                    compra.setFuncionario_turnos_idturnos(
                            rs.getInt("Funcionario_turnos_idturnos"));

                    return compra;
                }
            }
        }

        return null;
    }

    // ATUALIZAR
    public boolean atualizar(Compras compra) throws SQLException {

        String sql = "UPDATE compras SET "
                + "valorTotal = ?, "
                + "situacao = ?, "
                + "dataCompra = ?, "
                + "fornecedores_idfornecedores = ?, "
                + "Funcionario_idFuncionario = ?, "
                + "Funcionario_cargos_idcargos = ?, "
                + "Funcionario_setores_idsetores = ?, "
                + "Funcionario_turnos_idturnos = ? "
                + "WHERE idcompras = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDouble(1, compra.getValorTotal());
            stmt.setString(2, compra.getSituacao());
            stmt.setString(3, compra.getDataCompra());
            stmt.setInt(4, compra.getFornecedores_idfornecedores());
            stmt.setInt(5, compra.getFuncionario_idFuncionario());
            stmt.setInt(6, compra.getFuncionario_cargos_idcargos());
            stmt.setInt(7, compra.getFuncionario_setores_idsetores());
            stmt.setInt(8, compra.getFuncionario_turnos_idturnos());
            stmt.setInt(9, compra.getIdcompras());

            return stmt.executeUpdate() > 0;
        }
    }

    // EXCLUIR
    public boolean excluir(int id) throws SQLException {

        String sql = "DELETE FROM compras WHERE idcompras = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            return stmt.executeUpdate() > 0;
        }
    }
    
    
    // ATUALIZAR COMPRA E SEUS ITENS
    public boolean atualizarCompraComItens(
            int idCompra,
            Compras compra,
            List<ItensCompras> itens) throws SQLException {

        String sqlCompra = "UPDATE compras SET "
                + "valorTotal = ?, "
                + "situacao = ?, "
                + "dataCompra = ?, "
                + "fornecedores_idfornecedores = ?, "
                + "Funcionario_idFuncionario = ?, "
                + "Funcionario_cargos_idcargos = ?, "
                + "Funcionario_setores_idsetores = ?, "
                + "Funcionario_turnos_idturnos = ? "
                + "WHERE idcompras = ?";

        String sqlExcluirItens =
                "DELETE FROM itensCompras WHERE compras_idcompras = ?";

        String sqlInserirItem = "INSERT INTO itensCompras "
                + "(quantidade, precoUnitario, desconto, "
                + "Produtos_idprodutos, "
                + "Produtos_categorias_idcategorias, "
                + "Produtos_marcas_idmarcas, "
                + "Produtos_unidadeMedidas_idunidadeMedidas, "
                + "compras_idcompras) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = Conexao.conectar()) {

            conn.setAutoCommit(false);

            try {

                // 1. Atualizar os dados da compra
                try (PreparedStatement stmt =
                        conn.prepareStatement(sqlCompra)) {

                    stmt.setDouble(1, compra.getValorTotal());
                    stmt.setString(2, compra.getSituacao());
                    stmt.setString(3, compra.getDataCompra());

                    stmt.setInt(4,
                            compra.getFornecedores_idfornecedores());

                    stmt.setInt(5,
                            compra.getFuncionario_idFuncionario());

                    stmt.setInt(6,
                            compra.getFuncionario_cargos_idcargos());

                    stmt.setInt(7,
                            compra.getFuncionario_setores_idsetores());

                    stmt.setInt(8,
                            compra.getFuncionario_turnos_idturnos());

                    stmt.setInt(9, idCompra);

                    if (stmt.executeUpdate() == 0) {
                        conn.rollback();
                        return false;
                    }
                }

                // 2. Excluir os itens antigos
                try (PreparedStatement stmt =
                        conn.prepareStatement(sqlExcluirItens)) {

                    stmt.setInt(1, idCompra);
                    stmt.executeUpdate();
                }

                // 3. Inserir os itens atualizados
                try (PreparedStatement stmt =
                        conn.prepareStatement(sqlInserirItem)) {

                    for (ItensCompras item : itens) {

                        stmt.setInt(1, item.getQuantidade());
                        stmt.setDouble(2, item.getPrecoUnitario());
                        stmt.setDouble(3, item.getDesconto());

                        stmt.setInt(4,
                                item.getProdutos_idprodutos());

                        stmt.setInt(5,
                                item.getProdutos_categorias_idcategorias());

                        stmt.setInt(6,
                                item.getProdutos_marcas_idmarcas());

                        stmt.setInt(7,
                                item.getProdutos_unidadeMedidas_idunidadeMedidas());

                        stmt.setInt(8, idCompra);

                        stmt.addBatch();
                    }

                    stmt.executeBatch();
                }

                // 4. Confirmar a transação
                conn.commit();

                return true;

            } catch (SQLException | RuntimeException e) {

                conn.rollback();
                throw e;

            } finally {

                conn.setAutoCommit(true);
            }
        }
    }

}
