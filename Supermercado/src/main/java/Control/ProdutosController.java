
package Control;

import Conection.Conexao;
import Model.Produtos;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutosController {

    // MÉTODO PARA LISTAR TODOS OS PRODUTOS
    public List<Produtos> listarProdutos() throws SQLException {

        List<Produtos> lista = new ArrayList<>();

        String sql = "SELECT idprodutos, nome FROM produtos ORDER BY nome";

        try (Connection conn = Conexao.conectar();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Produtos produto = new Produtos();

                produto.setIdprodutos(rs.getInt("idprodutos"));
                produto.setNome(rs.getString("nome"));

                lista.add(produto);
            }
        }

        return lista;
    }
}
