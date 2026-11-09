/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control;

import Conection.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author AntônioVieira
 */
public class HistoricoAcessoController {
   // Cadastrar histórico de acesso
    public boolean cadastrar(
            String dataAcesso,
            String horarioAcesso,
            String terminal_ip,
            String dispositivo,
            int usuario_idusuario) {

        String sql = "INSERT INTO historicoAcessos "
                + "(dataAcesso, horarioAcesso, terminal_ip, "
                + "dispositivo, usuario_idusuario) "
                + "VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, dataAcesso);
            stmt.setString(2, horarioAcesso);
            stmt.setString(3, terminal_ip);
            stmt.setString(4, dispositivo);
            stmt.setInt(5, usuario_idusuario);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {

            System.out.println(
                "Erro ao cadastrar histórico: " + e.getMessage()
            );

            return false;
        }
    }

    // Listar todos os históricos de acesso
    public List<Object[]> listarTodos() {

        List<Object[]> lista = new ArrayList<>();

        String sql = "SELECT idhistoricoAcessos, dataAcesso, "
                + "horarioAcesso, terminal_ip, dispositivo, "
                + "usuario_idusuario "
                + "FROM historicoAcessos "
                + "ORDER BY idhistoricoAcessos DESC";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                Object[] dados = {
                    rs.getInt("idhistoricoAcessos"),
                    rs.getString("dataAcesso"),
                    rs.getString("horarioAcesso"),
                    rs.getString("terminal_ip"),
                    rs.getString("dispositivo"),
                    rs.getInt("usuario_idusuario")
                };

                lista.add(dados);
            }

        } catch (SQLException e) {

            System.out.println(
                "Erro ao listar histórico: " + e.getMessage()
            );
        }

        return lista;
    }

    public List<Object[]> listarPorFuncionario(int idFuncionario) {

    List<Object[]> historicos = new ArrayList<>();

    String sql = "SELECT h.idhistoricoAcessos, "
            + "h.dataAcesso, "
            + "h.horarioAcesso, "
            + "h.terminal_ip, "
            + "h.dispositivo, "
            + "h.usuario_idusuario "
            + "FROM historicoAcessos h "
            + "INNER JOIN usuario u "
            + "ON h.usuario_idusuario = u.idusuario "
            + "WHERE u.Funcionario_idFuncionario = ? "
            + "ORDER BY h.idhistoricoAcessos DESC";

    try (Connection conn = Conexao.conectar();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, idFuncionario);

        try (ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                Object[] dados = {
                    rs.getInt("idhistoricoAcessos"),
                    rs.getString("dataAcesso"),
                    rs.getString("horarioAcesso"),
                    rs.getString("terminal_ip"),
                    rs.getString("dispositivo"),
                    rs.getInt("usuario_idusuario")
                };

                historicos.add(dados);
            }
        }

    } catch (Exception e) {
        System.out.println("Erro ao listar histórico: " + e.getMessage());
    }

    return historicos;
}
    
    // Excluir histórico de acesso
    public boolean excluir(int idhistoricoAcessos) {

        String sql = "DELETE FROM historicoAcessos "
                + "WHERE idhistoricoAcessos = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idhistoricoAcessos);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {

            System.out.println(
                "Erro ao excluir histórico: " + e.getMessage()
            );

            return false;
        }
    }

public List<Map<String, Object>> listarHistoricoAcessos() throws SQLException {

    List<Map<String, Object>> lista = new ArrayList<>();

    String sql = "SELECT "
            + "h.idhistoricoAcessos, "
            + "h.dataAcesso, "
            + "h.horarioAcesso, "
            + "h.terminal_ip, "
            + "h.dispositivo, "
            + "u.nome AS nomeUsuario "
            + "FROM historicoAcessos h "
            + "INNER JOIN usuario u "
            + "ON h.usuario_idusuario = u.idusuario "
            + "ORDER BY h.idhistoricoAcessos DESC";

    try (Connection conn = Conexao.conectar();
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {

            Map<String, Object> acesso = new HashMap<>();

            acesso.put("id", rs.getInt("idhistoricoAcessos"));
            acesso.put("data", rs.getString("dataAcesso"));
            acesso.put("horario", rs.getString("horarioAcesso"));
            acesso.put("ip", rs.getString("terminal_ip"));
            acesso.put("dispositivo", rs.getString("dispositivo"));
            acesso.put("usuario", rs.getString("nomeUsuario"));

            lista.add(acesso);
        }
    }

    return lista;
}









}  
