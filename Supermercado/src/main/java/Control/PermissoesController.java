
package Control;

import Conection.Conexao;
import Model.Permissoes;
import Model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class PermissoesController {

    // ==========================================
    // CADASTRAR PERMISSÕES
    // ==========================================
   public void cadastrarPermissoes(Permissoes permissoes) throws SQLException {

    String sql = "INSERT INTO permissoes ("
            + "tipo, "
            + "telaProdutos, "
            + "telaEstoque, "
            + "telaCompras, "
            + "telaClientes, "
            + "telaPromocoes, "
            + "telaCaixa, "
            + "telaVendas, "
            + "telaPDV, "
            + "telaPedidos, "
            + "telaEntregas, "
            + "telaRh, "
            + "telaFinaceiro, "
            + "telaRelatorios"
            + ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    try (Connection conn = Conexao.conectar();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

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
    }
}

    // ==========================================
    // LISTAR TODAS AS PERMISSÕES
    // ==========================================
    public List<Permissoes> listar() {

        List<Permissoes> lista = new ArrayList<>();

        String sql = "SELECT * FROM permissoes ORDER BY tipo";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                lista.add(mapearPermissoes(rs));
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar permissões: " + e.getMessage());
            e.printStackTrace();
        }

        return lista;
    }

    // ==========================================
    // BUSCAR PERMISSÃO PELO ID
    // ==========================================
    public Permissoes buscarPorId(int id) {

        String sql = "SELECT * FROM permissoes WHERE idpermissoes = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapearPermissoes(rs);
                }
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar permissão: " + e.getMessage());
            e.printStackTrace();
        }

        return null;
    }

    // ==========================================
    // BUSCAR PERMISSÃO PELO TIPO
    // ==========================================
    public Permissoes buscarPorTipo(String tipo) {

        String sql = "SELECT * FROM permissoes WHERE tipo = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, tipo);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapearPermissoes(rs);
                }
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar tipo de permissão: " + e.getMessage());
            e.printStackTrace();
        }

        return null;
    }

    // ==========================================
    // ATUALIZAR PERMISSÕES
    // ==========================================
    public boolean atualizar(Permissoes p) {

        String sql = "UPDATE permissoes SET "
                + "tipo = ?, "
                + "telaProdutos = ?, "
                + "telaEstoque = ?, "
                + "telaCompras = ?, "
                + "telaClientes = ?, "
                + "telaPromocoes = ?, "
                + "telaCaixa = ?, "
                + "telaVendas = ?, "
                + "telaPDV = ?, "
                + "telaPedidos = ?, "
                + "telaEntregas = ?, "
                + "telaRh = ?, "
                + "telaFinaceiro = ?, "
                + "telaRelatorios = ? "
                + "WHERE idpermissoes = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, p.getTipo());
            stmt.setInt(2, p.getTelaProdutos());
            stmt.setInt(3, p.getTelaEstoque());
            stmt.setInt(4, p.getTelaCompras());
            stmt.setInt(5, p.getTelaClientes());
            stmt.setInt(6, p.getTelaPromocoes());
            stmt.setInt(7, p.getTelaCaixa());
            stmt.setInt(8, p.getTelaVendas());
            stmt.setInt(9, p.getTelaPDV());
            stmt.setInt(10, p.getTelaPedidos());
            stmt.setInt(11, p.getTelaEntregas());
            stmt.setInt(12, p.getTelaRh());
            stmt.setInt(13, p.getTelaFinaceiro());
            stmt.setInt(14, p.getTelaRelatorios());
            stmt.setInt(15, p.getIdPermissoes());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar permissões: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // ==========================================
    // EXCLUIR PERMISSÕES
    // ==========================================
    public boolean excluir(int id) {

        String sql = "DELETE FROM permissoes WHERE idpermissoes = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao excluir permissões: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // ==========================================
    // MAPEAR RESULTADO PARA MODEL
    // ==========================================
    private Permissoes mapearPermissoes(ResultSet rs) throws SQLException {

        Permissoes p = new Permissoes();

        p.setIdPermissoes(rs.getInt("idpermissoes"));
        p.setTipo(rs.getString("tipo"));

        p.setTelaProdutos(rs.getInt("telaProdutos"));
        p.setTelaEstoque(rs.getInt("telaEstoque"));
        p.setTelaCompras(rs.getInt("telaCompras"));
        p.setTelaClientes(rs.getInt("telaClientes"));
        p.setTelaPromocoes(rs.getInt("telaPromocoes"));
        p.setTelaCaixa(rs.getInt("telaCaixa"));
        p.setTelaVendas(rs.getInt("telaVendas"));
        p.setTelaPDV(rs.getInt("telaPDV"));
        p.setTelaPedidos(rs.getInt("telaPedidos"));
        p.setTelaEntregas(rs.getInt("telaEntregas"));
        p.setTelaRh(rs.getInt("telaRh"));
        p.setTelaFinaceiro(rs.getInt("telaFinaceiro"));
        p.setTelaRelatorios(rs.getInt("telaRelatorios"));

        return p;
    }

    // ==========================================
    // LISTAR PERMISSÕES COM NOME DOS USUÁRIOS
    // PARA A JTable tbPermissoes
    // ==========================================
    public List<Object[]> listarPermissoesComUsuarios() {

    List<Object[]> lista = new ArrayList<>();

    String sql = "SELECT "
            + "u.nome AS nomeUsuario, "
            + "COALESCE(p.telaProdutos, 0) AS telaProdutos, "
            + "COALESCE(p.telaEstoque, 0) AS telaEstoque, "
            + "COALESCE(p.telaCompras, 0) AS telaCompras, "
            + "COALESCE(p.telaClientes, 0) AS telaClientes, "
            + "COALESCE(p.telaPromocoes, 0) AS telaPromocoes, "
            + "COALESCE(p.telaCaixa, 0) AS telaCaixa, "
            + "COALESCE(p.telaVendas, 0) AS telaVendas, "
            + "COALESCE(p.telaPDV, 0) AS telaPDV, "
            + "COALESCE(p.telaPedidos, 0) AS telaPedidos, "
            + "COALESCE(p.telaEntregas, 0) AS telaEntregas, "
            + "COALESCE(p.telaRh, 0) AS telaRh, "
            + "COALESCE(p.telaFinaceiro, 0) AS telaFinaceiro, "
            + "COALESCE(p.telaRelatorios, 0) AS telaRelatorios "
            + "FROM usuario u "
            + "LEFT JOIN usuario_has_permissoes up "
            + "ON u.idusuario = up.usuario_idusuario "
            + "LEFT JOIN permissoes p "
            + "ON up.permissoes_idpermissoes = p.idpermissoes "
            + "ORDER BY u.nome";

    try (Connection conn = Conexao.conectar();
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {

            Object[] dados = {
                rs.getString("nomeUsuario"),
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

        System.out.println("Usuários encontrados: " + lista.size());

    } catch (SQLException e) {

        System.err.println("Erro ao listar permissões: " + e.getMessage());
        e.printStackTrace();
    }

    return lista;
}

    // ==========================================
    // LISTAR PERMISSÕES COM OBJETO USUARIO
    // ==========================================
    public List<Permissoes> listarPermissoes() {

        List<Permissoes> lista = new ArrayList<>();

        String sql = "SELECT p.*, "
                + "u.nome AS nomeUsuario "
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

                Permissoes permissao = mapearPermissoes(rs);

                Usuario usuario = new Usuario();
                usuario.setNome(rs.getString("nomeUsuario"));

                permissao.setUsuario(usuario);

                lista.add(permissao);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar permissões: " + e.getMessage());
            e.printStackTrace();
        }

        return lista;
    }
    
    public void salvarPermissoesFuncionario(
        Permissoes p, int idFuncionario) throws SQLException {

    String sqlUsuario = "SELECT idusuario FROM usuario "
            + "WHERE Funcionario_idFuncionario = ?";

    String sqlBuscar = "SELECT p.idpermissoes "
            + "FROM permissoes p "
            + "INNER JOIN usuario_has_permissoes up "
            + "ON p.idpermissoes = up.permissoes_idpermissoes "
            + "WHERE up.usuario_idusuario = ?";

    String sqlInsert = "INSERT INTO permissoes ("
            + "tipo, telaProdutos, telaEstoque, telaCompras, "
            + "telaClientes, telaPromocoes, telaCaixa, telaVendas, "
            + "telaPDV, telaPedidos, telaEntregas, telaRh, "
            + "telaFinaceiro, telaRelatorios"
            + ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    String sqlUpdate = "UPDATE permissoes SET "
            + "telaProdutos=?, telaEstoque=?, telaCompras=?, "
            + "telaClientes=?, telaPromocoes=?, telaCaixa=?, "
            + "telaVendas=?, telaPDV=?, telaPedidos=?, "
            + "telaEntregas=?, telaRh=?, telaFinaceiro=?, "
            + "telaRelatorios=? "
            + "WHERE idpermissoes=?";

    String sqlVincular = "INSERT INTO usuario_has_permissoes "
            + "(usuario_idusuario, permissoes_idpermissoes) "
            + "VALUES (?, ?)";

    try (Connection conn = Conexao.conectar()) {

        conn.setAutoCommit(false);

        try {

            int idUsuario;

            // 1. Localizar o usuário pelo funcionário
            try (PreparedStatement stmt = conn.prepareStatement(sqlUsuario)) {

                stmt.setInt(1, idFuncionario);

                try (ResultSet rs = stmt.executeQuery()) {

                    if (!rs.next()) {
                        throw new SQLException(
                                "Não existe usuário cadastrado para este funcionário.");
                    }

                    idUsuario = rs.getInt("idusuario");
                }
            }

            int idPermissao = -1;

            // 2. Verificar se o usuário já possui permissões
            try (PreparedStatement stmt = conn.prepareStatement(sqlBuscar)) {

                stmt.setInt(1, idUsuario);

                try (ResultSet rs = stmt.executeQuery()) {

                    if (rs.next()) {
                        idPermissao = rs.getInt("idpermissoes");
                    }
                }
            }

            if (idPermissao == -1) {

                // 3. Cadastrar novas permissões
                try (PreparedStatement stmt = conn.prepareStatement(
                        sqlInsert, java.sql.Statement.RETURN_GENERATED_KEYS)) {

                    preencherPermissoes(stmt, p);

                    stmt.executeUpdate();

                    try (ResultSet rs = stmt.getGeneratedKeys()) {

                        if (!rs.next()) {
                            throw new SQLException(
                                    "Não foi possível recuperar o ID da permissão.");
                        }

                        idPermissao = rs.getInt(1);
                    }
                }

                // 4. Vincular usuário e permissão
                try (PreparedStatement stmt = conn.prepareStatement(sqlVincular)) {

                    stmt.setInt(1, idUsuario);
                    stmt.setInt(2, idPermissao);

                    stmt.executeUpdate();
                }

            } else {

                // 5. Atualizar permissões existentes
                try (PreparedStatement stmt = conn.prepareStatement(sqlUpdate)) {

                    stmt.setInt(1, p.getTelaProdutos());
                    stmt.setInt(2, p.getTelaEstoque());
                    stmt.setInt(3, p.getTelaCompras());
                    stmt.setInt(4, p.getTelaClientes());
                    stmt.setInt(5, p.getTelaPromocoes());
                    stmt.setInt(6, p.getTelaCaixa());
                    stmt.setInt(7, p.getTelaVendas());
                    stmt.setInt(8, p.getTelaPDV());
                    stmt.setInt(9, p.getTelaPedidos());
                    stmt.setInt(10, p.getTelaEntregas());
                    stmt.setInt(11, p.getTelaRh());
                    stmt.setInt(12, p.getTelaFinaceiro());
                    stmt.setInt(13, p.getTelaRelatorios());
                    stmt.setInt(14, idPermissao);

                    stmt.executeUpdate();
                }
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

// Preencher os campos no INSERT
private void preencherPermissoes(
        PreparedStatement stmt, Permissoes p) throws SQLException {

    stmt.setString(1, "Individual");
    stmt.setInt(2, p.getTelaProdutos());
    stmt.setInt(3, p.getTelaEstoque());
    stmt.setInt(4, p.getTelaCompras());
    stmt.setInt(5, p.getTelaClientes());
    stmt.setInt(6, p.getTelaPromocoes());
    stmt.setInt(7, p.getTelaCaixa());
    stmt.setInt(8, p.getTelaVendas());
    stmt.setInt(9, p.getTelaPDV());
    stmt.setInt(10, p.getTelaPedidos());
    stmt.setInt(11, p.getTelaEntregas());
    stmt.setInt(12, p.getTelaRh());
    stmt.setInt(13, p.getTelaFinaceiro());
    stmt.setInt(14, p.getTelaRelatorios());
}

public Permissoes buscarPorFuncionario(int idFuncionario) {

    String sql = "SELECT p.* "
            + "FROM permissoes p "
            + "INNER JOIN usuario_has_permissoes up "
            + "ON p.idpermissoes = up.permissoes_idpermissoes "
            + "INNER JOIN usuario u "
            + "ON up.usuario_idusuario = u.idusuario "
            + "WHERE u.Funcionario_idFuncionario = ?";

    try (Connection conn = Conexao.conectar();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, idFuncionario);

        try (ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                return mapearPermissoes(rs);
            }
        }

    } catch (SQLException e) {

        e.printStackTrace();
    }

    return null;
}

 public void salvarPermissoesUsuario(
        Permissoes p, int idUsuario) throws SQLException {

    String sqlBuscar = "SELECT permissoes_idpermissoes "
            + "FROM usuario_has_permissoes "
            + "WHERE usuario_idusuario = ?";

    String sqlInsert = "INSERT INTO permissoes ("
            + "tipo, telaProdutos, telaEstoque, telaCompras, "
            + "telaClientes, telaPromocoes, telaCaixa, telaVendas, "
            + "telaPDV, telaPedidos, telaEntregas, telaRh, "
            + "telaFinaceiro, telaRelatorios"
            + ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    String sqlUpdate = "UPDATE permissoes SET "
            + "telaProdutos=?, telaEstoque=?, telaCompras=?, "
            + "telaClientes=?, telaPromocoes=?, telaCaixa=?, "
            + "telaVendas=?, telaPDV=?, telaPedidos=?, "
            + "telaEntregas=?, telaRh=?, telaFinaceiro=?, "
            + "telaRelatorios=? "
            + "WHERE idpermissoes=?";

    String sqlVincular = "INSERT INTO usuario_has_permissoes "
            + "(usuario_idusuario, permissoes_idpermissoes) "
            + "VALUES (?, ?)";

    try (Connection conn = Conexao.conectar()) {

        conn.setAutoCommit(false);

        try {

            int idPermissao = -1;

            // Verifica se o usuário já possui permissões
            try (PreparedStatement stmt = conn.prepareStatement(sqlBuscar)) {

                stmt.setInt(1, idUsuario);

                try (ResultSet rs = stmt.executeQuery()) {

                    if (rs.next()) {
                        idPermissao = rs.getInt("permissoes_idpermissoes");
                    }
                }
            }

            if (idPermissao == -1) {

                // Cadastra novas permissões
                try (PreparedStatement stmt = conn.prepareStatement(
                        sqlInsert,
                        java.sql.Statement.RETURN_GENERATED_KEYS)) {

                    preencherPermissoes(stmt, p);

                    stmt.executeUpdate();

                    try (ResultSet rs = stmt.getGeneratedKeys()) {

                        if (rs.next()) {
                            idPermissao = rs.getInt(1);
                        } else {
                            throw new SQLException(
                                    "Não foi possível obter o ID da permissão.");
                        }
                    }
                }

                // Associa a permissão ao usuário
                try (PreparedStatement stmt = conn.prepareStatement(sqlVincular)) {

                    stmt.setInt(1, idUsuario);
                    stmt.setInt(2, idPermissao);

                    stmt.executeUpdate();
                }

            } else {

                // Atualiza as permissões existentes
                try (PreparedStatement stmt = conn.prepareStatement(sqlUpdate)) {

                    stmt.setInt(1, p.getTelaProdutos());
                    stmt.setInt(2, p.getTelaEstoque());
                    stmt.setInt(3, p.getTelaCompras());
                    stmt.setInt(4, p.getTelaClientes());
                    stmt.setInt(5, p.getTelaPromocoes());
                    stmt.setInt(6, p.getTelaCaixa());
                    stmt.setInt(7, p.getTelaVendas());
                    stmt.setInt(8, p.getTelaPDV());
                    stmt.setInt(9, p.getTelaPedidos());
                    stmt.setInt(10, p.getTelaEntregas());
                    stmt.setInt(11, p.getTelaRh());
                    stmt.setInt(12, p.getTelaFinaceiro());
                    stmt.setInt(13, p.getTelaRelatorios());
                    stmt.setInt(14, idPermissao);

                    stmt.executeUpdate();
                }
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
   

  
}
