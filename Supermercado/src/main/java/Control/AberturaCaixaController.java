
package Control;

import Conection.Conexao;
import Model.AberturaCaixas;
import java.sql.*;
import java.util.*;

public class AberturaCaixaController {

    // ==============================
    // CADASTRAR ABERTURA DE CAIXA
    // ==============================
    public boolean cadastrar(AberturaCaixas abertura) throws SQLException {

    String sql = "INSERT INTO aberturaCaixa "
            + "(dataAbertura, horarioAbertura, valorInicial, "
            + "caixas_idcaixas, Funcionario_idFuncionario, "
            + "Funcionario_cargos_idcargos, "
            + "Funcionario_setores_idsetores, "
            + "Funcionario_turnos_idturnos) "
            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    try (Connection con = Conexao.conectar();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setString(1, abertura.getDataAbertura());
        ps.setString(2, abertura.getHorarioAbertura());
        ps.setDouble(3, abertura.getValorInicial());

        ps.setInt(4, abertura.getCaixas_idcaixas());
        ps.setInt(5, abertura.getFuncionario_idFuncionario());
        ps.setInt(6, abertura.getFuncionario_cargos_idcargos());
        ps.setInt(7, abertura.getFuncionario_setores_idsetores());
        ps.setInt(8, abertura.getFuncionario_turnos_idturnos());

        return ps.executeUpdate() > 0;
    }
}

    // ==============================
    // LISTAR ABERTURAS DE CAIXA
    // ==============================
    public List<Map<String, Object>> listar() throws SQLException {

        List<Map<String, Object>> lista = new ArrayList<>();

        String sql = "SELECT * FROM aberturaCaixa";

        try (Connection con = Conexao.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            ResultSetMetaData md = rs.getMetaData();

            while (rs.next()) {

                Map<String, Object> dados = new LinkedHashMap<>();

                for (int i = 1; i <= md.getColumnCount(); i++) {
                    dados.put(
                            md.getColumnLabel(i),
                            rs.getObject(i)
                    );
                }

                lista.add(dados);
            }
        }

        return lista;
    }

    // ==============================
    // ATUALIZAR ABERTURA DE CAIXA
    // ==============================
    public boolean atualizar(int id, String data, String horario,
            double valor, int idCaixa, int idFuncionario,
            int idCargo, int idSetor, int idTurno) throws SQLException {

        String sql = "UPDATE aberturaCaixa SET "
                + "dataAbertura=?, "
                + "horarioAbertura=?, "
                + "valorInicial=?, "
                + "caixas_idcaixas=?, "
                + "Funcionario_idFuncionario=?, "
                + "Funcionario_cargos_idcargos=?, "
                + "Funcionario_setores_idsetores=?, "
                + "Funcionario_turnos_idturnos=? "
                + "WHERE idaberturaCaixa=?";

        try (Connection con = Conexao.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, data);
            ps.setString(2, horario);
            ps.setDouble(3, valor);
            ps.setInt(4, idCaixa);
            ps.setInt(5, idFuncionario);
            ps.setInt(6, idCargo);
            ps.setInt(7, idSetor);
            ps.setInt(8, idTurno);
            ps.setInt(9, id);

            return ps.executeUpdate() > 0;
        }
    }

    // ==============================
    // EXCLUIR ABERTURA DE CAIXA
    // ==============================
    public boolean excluir(int id) throws SQLException {

        String sql = "DELETE FROM aberturaCaixa "
                + "WHERE idaberturaCaixa=?";

        try (Connection con = Conexao.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            return ps.executeUpdate() > 0;
        }
    }
}
