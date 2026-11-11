
package Control;

import Conection.Conexao;
import Model.Cupom;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CupomController {

    // CADASTRAR
    public boolean cadastrar(Cupom cupom) throws SQLException {

        String sql = "INSERT INTO cupom "
                + "(codigoCupom, valorDesconto, valorMinimoCompra, "
                + "qttMaximaUso, limitePorCliente, tipoUso, "
                + "tipoDesconto, dataInicio, dataFinal, "
                + "observacao, promocao_idpromocao) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(
                     sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, cupom.getCodigoCupom());
            stmt.setDouble(2, cupom.getValorDesconto());
            stmt.setDouble(3, cupom.getValorMinimoCompra());
            stmt.setInt(4, cupom.getQttMaximaUso());
            stmt.setInt(5, cupom.getLimitePorCliente());
            stmt.setString(6, cupom.getTipoUso());
            stmt.setString(7, cupom.getTipoDesconto());
            stmt.setString(8, cupom.getDataInicio());
            stmt.setString(9, cupom.getDataFinal());
            stmt.setString(10, cupom.getObservacao());
            stmt.setInt(11, cupom.getPromocao_idpromocao());

            int linhas = stmt.executeUpdate();

            if (linhas > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        cupom.setIdcupom(rs.getInt(1));
                    }
                }

                return true;
            }
        }

        return false;
    }

    // LISTAR TODOS
    public List<Cupom> listar() throws SQLException {

        List<Cupom> lista = new ArrayList<>();

        String sql = "SELECT * FROM cupom ORDER BY idcupom DESC";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                lista.add(mapearCupom(rs));
            }
        }

        return lista;
    }

    // BUSCAR POR ID
    public Cupom buscarPorId(int id) throws SQLException {

        String sql = "SELECT * FROM cupom WHERE idcupom = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapearCupom(rs);
                }
            }
        }

        return null;
    }

    // BUSCAR POR CÓDIGO
    public Cupom buscarPorCodigo(String codigo) throws SQLException {

        String sql = "SELECT * FROM cupom WHERE codigoCupom = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, codigo);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapearCupom(rs);
                }
            }
        }

        return null;
    }

    // ATUALIZAR
    public boolean atualizar(Cupom cupom) throws SQLException {

        String sql = "UPDATE cupom SET "
                + "codigoCupom = ?, "
                + "valorDesconto = ?, "
                + "valorMinimoCompra = ?, "
                + "qttMaximaUso = ?, "
                + "limitePorCliente = ?, "
                + "tipoUso = ?, "
                + "tipoDesconto = ?, "
                + "dataInicio = ?, "
                + "dataFinal = ?, "
                + "observacao = ?, "
                + "promocao_idpromocao = ? "
                + "WHERE idcupom = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cupom.getCodigoCupom());
            stmt.setDouble(2, cupom.getValorDesconto());
            stmt.setDouble(3, cupom.getValorMinimoCompra());
            stmt.setInt(4, cupom.getQttMaximaUso());
            stmt.setInt(5, cupom.getLimitePorCliente());
            stmt.setString(6, cupom.getTipoUso());
            stmt.setString(7, cupom.getTipoDesconto());
            stmt.setString(8, cupom.getDataInicio());
            stmt.setString(9, cupom.getDataFinal());
            stmt.setString(10, cupom.getObservacao());
            stmt.setInt(11, cupom.getPromocao_idpromocao());
            stmt.setInt(12, cupom.getIdcupom());

            return stmt.executeUpdate() > 0;
        }
    }

    // EXCLUIR
    public boolean excluir(int id) throws SQLException {

        String sql = "DELETE FROM cupom WHERE idcupom = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            return stmt.executeUpdate() > 0;
        }
    }

    // LISTAR CUPONS DE UMA PROMOÇÃO
    public List<Cupom> buscarPorPromocao(int idPromocao)
            throws SQLException {

        List<Cupom> lista = new ArrayList<>();

        String sql = "SELECT * FROM cupom "
                + "WHERE promocao_idpromocao = ? "
                + "ORDER BY idcupom DESC";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idPromocao);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapearCupom(rs));
                }
            }
        }

        return lista;
    }

    // MAPEAR RESULTADO DO BANCO PARA MODEL
    private Cupom mapearCupom(ResultSet rs) throws SQLException {

        Cupom cupom = new Cupom();

        cupom.setIdcupom(rs.getInt("idcupom"));
        cupom.setCodigoCupom(rs.getString("codigoCupom"));
        cupom.setValorDesconto(rs.getDouble("valorDesconto"));
        cupom.setValorMinimoCompra(rs.getDouble("valorMinimoCompra"));
        cupom.setQttMaximaUso(rs.getInt("qttMaximaUso"));
        cupom.setLimitePorCliente(rs.getInt("limitePorCliente"));
        cupom.setTipoUso(rs.getString("tipoUso"));
        cupom.setTipoDesconto(rs.getString("tipoDesconto"));
        cupom.setDataInicio(rs.getString("dataInicio"));
        cupom.setDataFinal(rs.getString("dataFinal"));
        cupom.setObservacao(rs.getString("observacao"));
        cupom.setPromocao_idpromocao(
                rs.getInt("promocao_idpromocao"));

        return cupom;
    }
    
    
}
