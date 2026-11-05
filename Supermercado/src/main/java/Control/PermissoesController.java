
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

public class PermissoesController {

    // ==========================================
    // CADASTRAR PERMISSÕES
    // ==========================================
    public boolean cadastrar(Permissoes p) {

        String sql = "INSERT INTO permissoes ("
                + "tipo, telaProdutos, telaEstoque, telaCompras, "
                + "telaClientes, telaPromocoes, telaCaixa, "
                + "telaVendas, telaPDV, telaPedidos, telaEntregas, "
                + "telaRh, telaFinaceiro, telaRelatorios"
                + ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

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

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar permissões: " + e.getMessage());
            e.printStackTrace();
            return false;
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
                    rs.getObject("telaProdutos"),
                    rs.getObject("telaEstoque"),
                    rs.getObject("telaCompras"),
                    rs.getObject("telaClientes"),
                    rs.getObject("telaPromocoes"),
                    rs.getObject("telaCaixa"),
                    rs.getObject("telaVendas"),
                    rs.getObject("telaPDV"),
                    rs.getObject("telaPedidos"),
                    rs.getObject("telaEntregas"),
                    rs.getObject("telaRh"),
                    rs.getObject("telaFinaceiro"),
                    rs.getObject("telaRelatorios")
                };

                lista.add(dados);
            }

            System.out.println("Quantidade de registros encontrados: " + lista.size());

        } catch (SQLException e) {
            System.err.println("Erro ao listar permissões com usuários: "
                    + e.getMessage());
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
}
