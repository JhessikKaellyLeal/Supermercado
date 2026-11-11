
package Control;

import Conection.Conexao;
import Model.Permissoes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;

public class PermissoesController {
    
    public void salvarPermissoesFuncionario(Permissoes permissoes, int idFuncionario)
        throws SQLException {

    String sqlUsuario = 
            "SELECT usuario_idusuario FROM funcionario WHERE idFuncionario = ?";

    String sqlPermissao =
            "INSERT INTO permissoes (tipo, telaProdutos, telaEstoque, "
            + "telaCompras, telaClientes, telaPromocoes, telaCaixa, "
            + "telaVendas, telaPDV, telaPedidos, telaEntregas, telaRh, "
            + "telaFinaceiro, telaRelatorios) "
            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    String sqlRelacionamento =
            "INSERT INTO usuario_has_permissoes "
            + "(usuario_idusuario, permissoes_idpermissoes) "
            + "VALUES (?, ?)";

    try (Connection conn = Conexao.conectar()) {

        conn.setAutoCommit(false);

        try {

            int idUsuario;

            // Localiza o usuário vinculado ao funcionário
            try (PreparedStatement stmt = conn.prepareStatement(sqlUsuario)) {

                stmt.setInt(1, idFuncionario);

                try (ResultSet rs = stmt.executeQuery()) {

                    if (!rs.next()) {
                        throw new SQLException(
                                "Não foi encontrado um usuário vinculado ao funcionário.");
                    }

                    idUsuario = rs.getInt("usuario_idusuario");
                }
            }

            // Cadastra as permissões
            int idPermissao;

            try (PreparedStatement stmt = conn.prepareStatement(
                    sqlPermissao, Statement.RETURN_GENERATED_KEYS)) {

                stmt.setString(1, permissoes.getTipo());
                stmt.setInt(2, permissoes.getTelaProdutos());
                stmt.setInt(3, permissoes.getTelaEstoque());
                stmt.setInt(4, permissoes.getTelaCompras());
                stmt.setInt(5, permissoes.getTelaClientes());
                stmt.setInt(6, permissoes.getTelaPromocoes());
                stmt.setInt(7, permissoes.getTelaCaixa());
                stmt.setInt(8, permissoes.getTelaVendas());
                stmt.setInt(9, permissoes.getTelaPDV());
                stmt.setInt(10, permissoes.getTelaPedidos());
                stmt.setInt(11, permissoes.getTelaEntregas());
                stmt.setInt(12, permissoes.getTelaRh());
                stmt.setInt(13, permissoes.getTelaFinaceiro());
                stmt.setInt(14, permissoes.getTelaRelatorios());

                stmt.executeUpdate();

                try (ResultSet rs = stmt.getGeneratedKeys()) {

                    if (!rs.next()) {
                        throw new SQLException(
                                "Não foi possível obter o ID das permissões.");
                    }

                    idPermissao = rs.getInt(1);
                }
            }

            // Relaciona as permissões ao usuário
            try (PreparedStatement stmt =
                    conn.prepareStatement(sqlRelacionamento)) {

                stmt.setInt(1, idUsuario);
                stmt.setInt(2, idPermissao);

                stmt.executeUpdate();
            }

            conn.commit();

        } catch (SQLException e) {

            conn.rollback();
            throw e;

        } finally {
            conn.setAutoCommit(true);
        }
    }
}
    
    public void salvarPermissoesUsuario(Permissoes permissoes, int idUsuario)
        throws SQLException {

    String sqlPermissoes = "INSERT INTO permissoes ("
            + "tipo, telaProdutos, telaEstoque, telaCompras, "
            + "telaClientes, telaPromocoes, telaCaixa, telaVendas, "
            + "telaPDV, telaPedidos, telaEntregas, telaRh, "
            + "telaFinaceiro, telaRelatorios"
            + ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    String sqlRelacionamento = "INSERT INTO usuario_has_permissoes "
            + "(usuario_idusuario, permissoes_idpermissoes) "
            + "VALUES (?, ?)";

    try (Connection conn = Conexao.conectar()) {

        conn.setAutoCommit(false);

        try {

            int idPermissao;

            // Cadastrar as permissões
            try (PreparedStatement stmt = conn.prepareStatement(
                    sqlPermissoes, Statement.RETURN_GENERATED_KEYS)) {

                stmt.setString(1, permissoes.getTipo());
                stmt.setInt(2, permissoes.getTelaProdutos());
                stmt.setInt(3, permissoes.getTelaEstoque());
                stmt.setInt(4, permissoes.getTelaCompras());
                stmt.setInt(5, permissoes.getTelaClientes());
                stmt.setInt(6, permissoes.getTelaPromocoes());
                stmt.setInt(7, permissoes.getTelaCaixa());
                stmt.setInt(8, permissoes.getTelaVendas());
                stmt.setInt(9, permissoes.getTelaPDV());
                stmt.setInt(10, permissoes.getTelaPedidos());
                stmt.setInt(11, permissoes.getTelaEntregas());
                stmt.setInt(12, permissoes.getTelaRh());
                stmt.setInt(13, permissoes.getTelaFinaceiro());
                stmt.setInt(14, permissoes.getTelaRelatorios());

                stmt.executeUpdate();

                try (ResultSet rs = stmt.getGeneratedKeys()) {

                    if (rs.next()) {
                        idPermissao = rs.getInt(1);
                    } else {
                        throw new SQLException(
                                "Não foi possível recuperar o ID da permissão."
                        );
                    }
                }
            }

            // Relacionar a permissão ao usuário
            try (PreparedStatement stmt = conn.prepareStatement(
                    sqlRelacionamento)) {

                stmt.setInt(1, idUsuario);
                stmt.setInt(2, idPermissao);

                stmt.executeUpdate();
            }

            conn.commit();

        } catch (SQLException e) {

            conn.rollback();
            throw e;

        } finally {

            conn.setAutoCommit(true);
        }
    }
}

    public void cadastrar(Permissoes permissao) throws SQLException {
        String sql = "INSERT INTO permissoes "
                + "(tipo, telaProdutos, telaEstoque, telaCompras, "
                + "telaClientes, telaPromocoes, telaCaixa, telaVendas, "
                + "telaPDV, telaPedidos, telaEntregas, telaRh, "
                + "telaFinaceiro, telaRelatorios) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, permissao.getTipo());
            stmt.setInt(2, permissao.getTelaProdutos());
            stmt.setInt(3, permissao.getTelaEstoque());
            stmt.setInt(4, permissao.getTelaCompras());
            stmt.setInt(5, permissao.getTelaClientes());
            stmt.setInt(6, permissao.getTelaPromocoes());
            stmt.setInt(7, permissao.getTelaCaixa());
            stmt.setInt(8, permissao.getTelaVendas());
            stmt.setInt(9, permissao.getTelaPDV());
            stmt.setInt(10, permissao.getTelaPedidos());
            stmt.setInt(11, permissao.getTelaEntregas());
            stmt.setInt(12, permissao.getTelaRh());
            stmt.setInt(13, permissao.getTelaFinaceiro());
            stmt.setInt(14, permissao.getTelaRelatorios());

            stmt.executeUpdate();
        }
    }

    public List<Permissoes> listar() throws SQLException {
        List<Permissoes> lista = new ArrayList<>();

        String sql = "SELECT * FROM permissoes ORDER BY idpermissoes";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                lista.add(mapearPermissao(rs));
            }
        }

        return lista;
    }

    public Permissoes buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM permissoes WHERE idpermissoes = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapearPermissao(rs);
                }
            }
        }

        return null;
    }

    public List<Permissoes> buscarPorTipo(String tipo)
            throws SQLException {

        List<Permissoes> lista = new ArrayList<>();

        String sql = "SELECT * FROM permissoes "
                + "WHERE tipo LIKE ? ORDER BY tipo";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + tipo + "%");

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapearPermissao(rs));
                }
            }
        }

        return lista;
    }

    public void atualizar(Permissoes permissao) throws SQLException {
        String sql = "UPDATE permissoes SET "
                + "tipo = ?, telaProdutos = ?, telaEstoque = ?, "
                + "telaCompras = ?, telaClientes = ?, "
                + "telaPromocoes = ?, telaCaixa = ?, telaVendas = ?, "
                + "telaPDV = ?, telaPedidos = ?, telaEntregas = ?, "
                + "telaRh = ?, telaFinaceiro = ?, telaRelatorios = ? "
                + "WHERE idpermissoes = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, permissao.getTipo());
            stmt.setInt(2, permissao.getTelaProdutos());
            stmt.setInt(3, permissao.getTelaEstoque());
            stmt.setInt(4, permissao.getTelaCompras());
            stmt.setInt(5, permissao.getTelaClientes());
            stmt.setInt(6, permissao.getTelaPromocoes());
            stmt.setInt(7, permissao.getTelaCaixa());
            stmt.setInt(8, permissao.getTelaVendas());
            stmt.setInt(9, permissao.getTelaPDV());
            stmt.setInt(10, permissao.getTelaPedidos());
            stmt.setInt(11, permissao.getTelaEntregas());
            stmt.setInt(12, permissao.getTelaRh());
            stmt.setInt(13, permissao.getTelaFinaceiro());
            stmt.setInt(14, permissao.getTelaRelatorios());
            stmt.setInt(15, permissao.getIdpermissoes());

            stmt.executeUpdate();
        }
    }

    public void excluir(int id) throws SQLException {
        String sql = "DELETE FROM permissoes WHERE idpermissoes = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    private Permissoes mapearPermissao(ResultSet rs)
            throws SQLException {

        Permissoes permissao = new Permissoes();

        permissao.setIdpermissoes(rs.getInt("idpermissoes"));
        permissao.setTipo(rs.getString("tipo"));
        permissao.setTelaProdutos(rs.getInt("telaProdutos"));
        permissao.setTelaEstoque(rs.getInt("telaEstoque"));
        permissao.setTelaCompras(rs.getInt("telaCompras"));
        permissao.setTelaClientes(rs.getInt("telaClientes"));
        permissao.setTelaPromocoes(rs.getInt("telaPromocoes"));
        permissao.setTelaCaixa(rs.getInt("telaCaixa"));
        permissao.setTelaVendas(rs.getInt("telaVendas"));
        permissao.setTelaPDV(rs.getInt("telaPDV"));
        permissao.setTelaPedidos(rs.getInt("telaPedidos"));
        permissao.setTelaEntregas(rs.getInt("telaEntregas"));
        permissao.setTelaRh(rs.getInt("telaRh"));
        permissao.setTelaFinaceiro(rs.getInt("telaFinaceiro"));
        permissao.setTelaRelatorios(rs.getInt("telaRelatorios"));

        return permissao;
    }
    
    public List<Object[]> listarPermissoesComUsuarios() throws SQLException {

    List<Object[]> lista = new ArrayList<>();

    String sql = "SELECT "
            + "u.nome, "
            + "p.telaProdutos, "
            + "p.telaEstoque, "
            + "p.telaCompras, "
            + "p.telaClientes, "
            + "p.telaPromocoes, "
            + "p.telaCaixa, "
            + "p.telaVendas, "
            + "p.telaPDV, "
            + "p.telaPedidos, "
            + "p.telaEntregas, "
            + "p.telaRh, "
            + "p.telaFinaceiro, "
            + "p.telaRelatorios "
            + "FROM permissoes p "
            + "INNER JOIN usuario_has_permissoes up "
            + "ON p.idpermissoes = up.permissoes_idpermissoes "
            + "INNER JOIN usuario u "
            + "ON up.usuario_idusuario = u.idusuario "
            + "ORDER BY u.nome";

    try (Connection conn = Conexao.conectar();
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {

            Object[] dados = new Object[]{
                rs.getString("nome"),
                rs.getInt("telaProdutos"),
                rs.getInt("telaEstoque"),
                rs.getInt("telaCompras"),
                rs.getInt("telaClientes"),
                rs.getInt("telaPromocoes"),
                rs.getInt("telaCaixa"),
                rs.getInt("telaVendas"),
                rs.getInt("telaPDV"),
                rs.getInt("telaPedidos"),
                rs.getInt("telaEntregas"),
                rs.getInt("telaRh"),
                rs.getInt("telaFinaceiro"),
                rs.getInt("telaRelatorios")
            };

            lista.add(dados);
        }
    }

    return lista;
}
    
    
    // BUSCAR PERMISSÕES POR FUNCIONÁRIO
public Permissoes buscarPorFuncionario(int idFuncionario)
        throws SQLException {

    String sql = "SELECT p.* "
            + "FROM permissoes p "
            + "INNER JOIN usuario_has_permissoes up "
            + "ON p.idpermissoes = up.permissoes_idpermissoes "
            + "INNER JOIN usuario u "
            + "ON up.usuario_idusuario = u.idusuario "
            + "WHERE u.Funcionario_idFuncionario = ? "
            + "LIMIT 1";

    try (Connection conn = Conexao.conectar();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, idFuncionario);

        try (ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                return mapearPermissao(rs);
            }
        }
    }

    return null;
}
    
}
