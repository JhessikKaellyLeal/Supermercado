/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import Conection.Conexao;
import Control.EnderecoController;
import Control.EntregaController;
import Control.FuncionarioController;
import Control.TaxasEntregaController;
import Control.VeiculosController;
import Model.Funcionario;
import View.MenuPadrao;
import Model.Usuario;
import View.TelaHome;
import View.TelaPerfilUsuarios;
import View.TelaProdutos;
import View.Tela_Funcionarios_RH;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author SamaraTavares
 */
public class TelaEntregas extends javax.swing.JFrame {
    private int idVeiculoSelecionado = -1;
    private int idTaxaSelecionada;
    private int idEnderecoSelecionado = -1;
    private int idClienteSelecionado = -1;
    private int idEntregadorSelecionado = -1;
    private int idCargoSelecionado = -1;
    private int idSetorSelecionado = -1;
    private int idTurnoSelecionado = -1;
    private int idEntregaSelecionada = -1;
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(TelaEntregas.class.getName());

   private MenuPadrao menu;  
    private Usuario usuario;
    
    
    public TelaEntregas(Usuario usuario) {
        initComponents();
        this.usuario= usuario;
        this.menu=MenuPadrao.instalar(
        this,
        usuario,
        "entregas",
        this::navegar,
        this::dispose
    );
        this.setExtendedState(TelaEntregas.MAXIMIZED_BOTH);
        
       
    }

    
     // MÉTODO DE ACESSAR AS TELAS
public void navegar(String idtela) {

    switch (idtela) {

        case "inicio":
            menu.abrirTela(this,new TelaHome(usuario));
            break;

        case "produtos":
            menu.abrirTela(this,new TelaProdutos(usuario));
            break;

        case "gerenciamentoProdutos":
            menu.abrirTela(this,new TelaGerenciamentoProdutos(usuario));
            break;

        case "estoque":
            menu.abrirTela(this,new TelaEstoque(usuario));
            break;

        case "fornecedores":
            menu.abrirTela(this,new TelaFornecedoresTerceirizados(usuario));
            break;

        case "fornecedoresTerceirizados":
            menu.abrirTela(this,new TelaFornecedoresTerceirizados(usuario));
            break;

        case "clientes":
            menu.abrirTela(this,new TelasClientes(usuario));
            break;

        case "pedidos":
            menu.abrirTela(this,new TelaPedidos(usuario));
            break;

        case "entregas":
            menu.abrirTela(this,new TelaEntregas(usuario));
            break;

        case "vendas":
            menu.abrirTela(this,new TelaVendas(usuario));
            break;

        case "pdv":
            menu.abrirTela(this,new TelaPdvRealizacao(usuario));
            break;

        case "vendaPagamentos":
            menu.abrirTela(this,new TelaVendaPagamentos(usuario));
            break;

        case "caixa":
            menu.abrirTela(this,new TelaCaixa(usuario));
            break;

        case "financeiro":
            menu.abrirTela(this,new TelaFinanceiro(usuario));
            break;

        case "funcionariosRH":
            menu.abrirTela(this,new Tela_Funcionarios_RH(usuario));
            break;

        case "perfilUsuarios":
            menu.abrirTela(this,new TelaPerfilUsuarios(usuario));
            break;

        case "solicitacao":
            menu.abrirTela(this,new TelaSolicitacao(usuario));
            break;

        case "alterarSenha":
            menu.abrirTela(this,new AlterarSenha());
            break;

        case "recuperacaoSenha":
            //menu.abrirTela(this, new RecuperacaoSenha());
            break;

        case "logout":
            // Retorna à tela de login
            menu.abrirTela(this, new TelaLoginUsuarios());
            break;

        default:
            JOptionPane.showMessageDialog(
                this,
                "Tela não encontrada: " + idtela,
                "Navegação",
                JOptionPane.WARNING_MESSAGE
            );
            break;
    }
}

 

private void cadastrarEntrega() {

    try {

        // Capturar os dados dos campos
        String dataEntrega = ftfDataEntrega.getText().trim();
        String horarioEntrega = ftfHorarioEntrega.getText().trim();

        String statusEntrega = cbStatusEntrega.getSelectedItem() != null
                ? cbStatusEntrega.getSelectedItem().toString().trim()
                : "";

        // Validar campos obrigatórios
        if (dataEntrega.isEmpty()
                || horarioEntrega.isEmpty()
                || statusEntrega.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Preencha todos os campos obrigatórios!",
                    "Atenção",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        // Validar seleção do veículo
        if (cbVeiculoEntregas.getSelectedItem() == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Selecione um veículo para a entrega!",
                    "Atenção",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        // Validar seleção do entregador
        if (cbEntregadorEntregas.getSelectedItem() == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Selecione um entregador!",
                    "Atenção",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        /*
         * IMPORTANTE:
         * Antes de chamar o controller, precisamos obter os IDs
         * do cliente, veículo, taxa e funcionário, incluindo os
         * IDs de cargo, setor e turno exigidos pelo banco.
         *
         * Os nomes exibidos nos JComboBox não são necessariamente
         * os IDs armazenados no banco de dados.
         */

        // Chamar o controller de entregas
        EntregaController controller = new EntregaController();

        /*
         * A chamada ao método cadastrar será definida conforme
         * os parâmetros existentes no seu EntregasController.
         */

    } catch (Exception e) {

        JOptionPane.showMessageDialog(
                this,
                "Erro ao cadastrar entrega: " + e.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE
        );

    }
}
       
    

private void cadastrarEndereco() {

    try {

        // Capturar os dados dos campos
        String bairro = txtBairroEnderecos.getText().trim();

        String cep = txtCepEnderecos.getText()
                .replaceAll("[^0-9]", "");

        String cidade = txtCidadeEnderecos.getText().trim();

        String estado = cbEstadoEnderecos.getSelectedItem() != null
                ? cbEstadoEnderecos.getSelectedItem().toString().trim()
                : "";

        String rua = txtRuaEnderecos.getText().trim();

        String numero = txtNumeroEnderecos.getText().trim();

        // Validar campos obrigatórios
        if (bairro.isEmpty()
                || cep.isEmpty()
                || cidade.isEmpty()
                || estado.isEmpty()
                || rua.isEmpty()
                || numero.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Preencha todos os campos obrigatórios!",
                    "Atenção",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        // Validar CEP
        if (cep.length() != 8) {

            JOptionPane.showMessageDialog(
                    this,
                    "Informe um CEP válido!",
                    "Atenção",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        // Verificar se existe cliente selecionado
        if (txtClienteEnderecos.getText().trim().isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Selecione um cliente!",
                    "Atenção",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        // Chamar o controller
        EnderecoController controller = new EnderecoController();

        /*
         * A chamada cadastrar depende da assinatura
         * existente no seu EnderecoController.
         */

    } catch (Exception e) {

        JOptionPane.showMessageDialog(
                this,
                "Erro ao cadastrar endereço: " + e.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE
        );

    }
}
    


private void cadastrarVeiculo() {

    try {

        // Capturar os dados dos campos
        String tipoVeiculo = txtTipoVeiculo.getText().trim();
        String marca = txtMarcaVeiculo.getText().trim();
        String modelo = txtModeloVeiculo.getText().trim();

        String anoTexto = ftfAnoVeiculo.getText().trim();
        String cor = ""; // Substituir pelo campo de cor da JFrame

        String cargaTexto = txtCapacidadeCarga.getText().trim();

        String statusVeiculo = cbStatusVeiculo.getSelectedItem() != null
                ? cbStatusVeiculo.getSelectedItem().toString().trim()
                : "";

        String placa = txtPlacaVeiculo.getText().trim();

        String observacoes = txtObservacoesVeiculo.getText().trim();

        // Validar campos obrigatórios
        if (tipoVeiculo.isEmpty()
                || marca.isEmpty()
                || modelo.isEmpty()
                || anoTexto.isEmpty()
                || cor.isEmpty()
                || cargaTexto.isEmpty()
                || statusVeiculo.isEmpty()
                || placa.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Preencha todos os campos obrigatórios!",
                    "Atenção",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        // Converter ano e capacidade de carga
        int anoVeiculo = Integer.parseInt(anoTexto);
        double capacidadeCarga = Double.parseDouble(
                cargaTexto.replace(",", ".")
        );

        // Validar ano
        if (anoVeiculo <= 0) {

            JOptionPane.showMessageDialog(
                    this,
                    "Informe um ano válido!",
                    "Atenção",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        // Validar capacidade de carga
        if (capacidadeCarga <= 0) {

            JOptionPane.showMessageDialog(
                    this,
                    "Informe uma capacidade de carga válida!",
                    "Atenção",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        // Chamar o controller
        VeiculosController controller = new VeiculosController();

        /*
         * A chamada controller.cadastrar() será ajustada
         * conforme os parâmetros do seu VeiculosController.
         */

    } catch (NumberFormatException e) {

        JOptionPane.showMessageDialog(
                this,
                "Verifique o ano e a capacidade de carga!",
                "Atenção",
                JOptionPane.WARNING_MESSAGE
        );

    } catch (Exception e) {

        JOptionPane.showMessageDialog(
                this,
                "Erro ao cadastrar veículo: " + e.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE
        );

    }
}
   
    

private void cadastrarTaxa() {

    try {

        // Capturar os dados dos campos
        String regiao = cbRegiaoTaxas.getSelectedItem() != null
                ? cbRegiaoTaxas.getSelectedItem().toString().trim()
                : "";

        String tipoTaxa = cbTipoTaxa.getSelectedItem() != null
                ? cbTipoTaxa.getSelectedItem().toString().trim()
                : "";

        String valorTexto = txtValorTaxa.getText().trim();

        String observacao = txtObservacaoTaxa.getText().trim();

        // Validar campos obrigatórios
        if (regiao.isEmpty()
                || tipoTaxa.isEmpty()
                || valorTexto.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Preencha todos os campos obrigatórios!",
                    "Atenção",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        // Converter valor da taxa
        double valorTaxa = Double.parseDouble(
                valorTexto.replace(",", ".")
        );

        // Validar valor da taxa
        if (valorTaxa <= 0) {

            JOptionPane.showMessageDialog(
                    this,
                    "Informe um valor de taxa válido!",
                    "Atenção",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        // Chamar o controller
        TaxasEntregaController controller = new TaxasEntregaController();

        /*
         * A chamada controller.cadastrar() será ajustada
         * conforme os parâmetros do seu controller.
         */

    } catch (NumberFormatException e) {

        JOptionPane.showMessageDialog(
                this,
                "Informe um valor numérico válido para a taxa!",
                "Atenção",
                JOptionPane.WARNING_MESSAGE
        );

    } catch (Exception e) {

        JOptionPane.showMessageDialog(
                this,
                "Erro ao cadastrar taxa: " + e.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE
        );

    }
}
   
    

private void cadastrarFuncionario() {

    try {

        // Capturar os dados dos campos
        String nome = txtNome.getText().trim();

        String dataNascimento = ftfDataNascimento.getText().trim();

        String cpf = ftfCpf.getText().replaceAll("[^0-9]", "");

        String telefone = ftfTelefone.getText()
                .replaceAll("[^0-9]", "");

        // Capturar o status
        String statusTexto = cbStatusEntregadores.getSelectedItem() != null
                ? cbStatusEntregadores.getSelectedItem().toString().trim()
                : "";

        // Validar campos obrigatórios
        if (nome.isEmpty()
                || dataNascimento.isEmpty()
                || cpf.isEmpty()
                || telefone.isEmpty()
                || statusTexto.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Preencha todos os campos obrigatórios!",
                    "Atenção",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        // Validar CPF
        if (cpf.length() != 11) {

            JOptionPane.showMessageDialog(
                    this,
                    "Informe um CPF válido!",
                    "Atenção",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        // Converter status para TINYINT
        int statusFuncionario;

        if (statusTexto.equalsIgnoreCase("Ativo")) {
            statusFuncionario = 1;

        } else if (statusTexto.equalsIgnoreCase("Inativo")) {
            statusFuncionario = 0;

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Selecione um status válido!",
                    "Atenção",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        // Chamar o controller
        FuncionarioController controller = new FuncionarioController();

        /*
         * A chamada controller.cadastrar() será definida
         * conforme os parâmetros do seu controller.
         */

    } catch (Exception e) {

        JOptionPane.showMessageDialog(
                this,
                "Erro ao cadastrar funcionário: " + e.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE
        );

    }
}



public class FuncionarioController {

    public boolean cadastrar(Funcionario funcionario) {

        String sql = "INSERT INTO Funcionario "
                + "(nome, dataNascimento, cpf, telefone, "
                + "dataAdmissao, statusFuncionario, "
                + "cargos_idcargos, setores_idsetores, turnos_idturnos) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, funcionario.getNome());
            stmt.setString(2, funcionario.getDataNascimento());
            stmt.setString(3, funcionario.getCpf());
            stmt.setString(4, funcionario.getTelefone());
            stmt.setNull(5, Types.VARCHAR);
            stmt.setBoolean(6, funcionario.isStatusFuncionario());
            stmt.setNull(7, Types.INTEGER);
            stmt.setNull(8, Types.INTEGER);
            stmt.setNull(9, Types.INTEGER);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Funcionario> listar() {

        List<Funcionario> lista = new ArrayList<>();

        String sql = "SELECT * FROM Funcionario ORDER BY nome";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                Funcionario funcionario = new Funcionario();

                funcionario.setIdFuncionario(rs.getInt("idFuncionario"));
                funcionario.setNome(rs.getString("nome"));
                funcionario.setDataNascimento(rs.getString("dataNascimento"));
                funcionario.setCpf(rs.getString("cpf"));
                funcionario.setTelefone(rs.getString("telefone"));
                funcionario.setDataAdmissao(rs.getString("dataAdmissao"));
                funcionario.setStatusFuncionario(
                        rs.getBoolean("statusFuncionario"));

                funcionario.setCargos_idcargos(
                        rs.getInt("cargos_idcargos"));
                funcionario.setSetores_idsetores(
                        rs.getInt("setores_idsetores"));
                funcionario.setTurnos_idturnos(
                        rs.getInt("turnos_idturnos"));

                lista.add(funcionario);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    public Funcionario buscarPorId(int idFuncionario) {

        String sql = "SELECT * FROM Funcionario WHERE idFuncionario = ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idFuncionario);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {

                    Funcionario funcionario = new Funcionario();

                    funcionario.setIdFuncionario(rs.getInt("idFuncionario"));
                    funcionario.setNome(rs.getString("nome"));
                    funcionario.setDataNascimento(rs.getString("dataNascimento"));
                    funcionario.setCpf(rs.getString("cpf"));
                    funcionario.setTelefone(rs.getString("telefone"));
                    funcionario.setDataAdmissao(rs.getString("dataAdmissao"));
                    funcionario.setStatusFuncionario(
                            rs.getBoolean("statusFuncionario"));

                    funcionario.setCargos_idcargos(rs.getInt("cargos_idcargos"));
                    funcionario.setSetores_idsetores(rs.getInt("setores_idsetores"));
                    funcionario.setTurnos_idturnos(rs.getInt("turnos_idturnos"));

                    return funcionario;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean atualizar(Funcionario funcionario) {

        String sql = "UPDATE Funcionario SET nome = ?, "
                + "dataNascimento = ?, cpf = ?, telefone = ?, "
                + "statusFuncionario = ? WHERE idFuncionario = ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, funcionario.getNome());
            stmt.setString(2, funcionario.getDataNascimento());
            stmt.setString(3, funcionario.getCpf());
            stmt.setString(4, funcionario.getTelefone());
            stmt.setBoolean(5, funcionario.isStatusFuncionario());
            stmt.setInt(6, funcionario.getIdFuncionario());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean excluir(int idFuncionario) {

        String sql = "DELETE FROM Funcionario WHERE idFuncionario = ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idFuncionario);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    

}



private void cadastrarVeiculos() {

    try {

        String tipo = txtTipoVeiculo.getText().trim();
        String marca = txtMarcaVeiculo.getText().trim();
        String modelo = txtModeloVeiculo.getText().trim();
        int ano = Integer.parseInt(ftfAnoVeiculo.getText().trim());
        String cor = txtCorVeiculo.getText().trim();
        double capacidade = Double.parseDouble(
                txtCapacidadeCarga.getText().trim().replace(",", "."));
        String status = cbStatusVeiculo.getSelectedItem().toString();
        String placa = txtPlacaVeiculo.getText().trim();
        String observacoes = txtObservacoesVeiculo.getText().trim();

        if (tipo.isEmpty() || marca.isEmpty() || modelo.isEmpty()
                || cor.isEmpty() || placa.isEmpty()) {

            JOptionPane.showMessageDialog(this,
                    "Preencha todos os campos obrigatórios!");

            return;
        }

        VeiculosController controller = new VeiculosController();

        boolean sucesso = controller.cadastrar(
                tipo, marca, modelo, ano, cor,
                capacidade, status, placa, observacoes);

        if (sucesso) {

            JOptionPane.showMessageDialog(this,
                    "Veículo cadastrado com sucesso!");

            limparCamposVeiculos();
            carregarTabelaVeiculos();

        }

    } catch (NumberFormatException e) {

        JOptionPane.showMessageDialog(this,
                "Verifique os campos de ano e capacidade de carga!");

    } catch (SQLException e) {

        JOptionPane.showMessageDialog(this,
                "Erro ao cadastrar veículo: " + e.getMessage());

    }
}   
    
private void carregarTabelaVeiculos() {

    try {

        VeiculosController controller = new VeiculosController();

        List<Map<String, Object>> lista = controller.listar();

        DefaultTableModel modelo = (DefaultTableModel) tblVeiculos.getModel();

        modelo.setRowCount(0);

        for (Map<String, Object> veiculo : lista) {

            modelo.addRow(new Object[]{
                veiculo.get("idveiculos"),
                veiculo.get("tipoVeiculo"),
                veiculo.get("marca"),
                veiculo.get("modelo"),
                veiculo.get("anoVeiculo"),
                veiculo.get("cor"),
                veiculo.get("capacidadeCarga"),
                veiculo.get("statusVeiculo"),
                veiculo.get("placa"),
                veiculo.get("observacoes")
            });
        }

    } catch (SQLException e) {

        JOptionPane.showMessageDialog(this,
                "Erro ao carregar veículos: " + e.getMessage());

    }
}    
     
private void alterarVeiculo() {
    

    if (idVeiculoSelecionado == -1) {

        JOptionPane.showMessageDialog(this,
                "Selecione um veículo na tabela!");

        return;
    }

    try {

        String tipo = txtTipoVeiculo.getText().trim();
        String marca = txtMarcaVeiculo.getText().trim();
        String modelo = txtModeloVeiculo.getText().trim();
        int ano = Integer.parseInt(ftfAnoVeiculo.getText().trim());
        String cor = txtCorVeiculo.getText().trim();

        double capacidade = Double.parseDouble(
                txtCapacidadeCarga.getText().trim().replace(",", "."));

        String status = cbStatusVeiculo.getSelectedItem().toString();
        String placa = txtPlacaVeiculo.getText().trim();
        String observacoes = txtObservacoesVeiculo.getText().trim();

        VeiculosController controller = new VeiculosController();

        boolean sucesso = controller.atualizar(
                idVeiculoSelecionado,
                tipo, marca, modelo, ano, cor,
                capacidade, status, placa, observacoes);

        if (sucesso) {

            JOptionPane.showMessageDialog(this,
                    "Veículo alterado com sucesso!");

            idVeiculoSelecionado = -1;

            limparCamposVeiculos();
            carregarTabelaVeiculos();

        }

    } catch (NumberFormatException e) {

        JOptionPane.showMessageDialog(this,
                "Verifique os campos numéricos!");

    } catch (SQLException e) {

        JOptionPane.showMessageDialog(this,
                "Erro ao alterar veículo: " + e.getMessage());

    }
}    
     
private void excluirVeiculo() {

    if ( idVeiculoSelecionado == -1) {

        JOptionPane.showMessageDialog(this,
                "Selecione um veículo na tabela!");

        return;
    }

    int confirmacao = JOptionPane.showConfirmDialog(
            this,
            "Deseja realmente excluir este veículo?",
            "Confirmar exclusão",
            JOptionPane.YES_NO_OPTION
    );

    if (confirmacao == JOptionPane.YES_OPTION) {

        try {

            VeiculosController controller = new VeiculosController();

            boolean sucesso = controller.excluir(idVeiculoSelecionado);

            if (sucesso) {

                JOptionPane.showMessageDialog(this,
                        "Veículo excluído com sucesso!");

                idVeiculoSelecionado = -1;

                limparCamposVeiculos();
                carregarTabelaVeiculos();

            }

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(this,
                    "Erro ao excluir veículo: " + e.getMessage());

        }
    }
}    


private void limparCamposVeiculos() {

    txtTipoVeiculo.setText("");
    txtMarcaVeiculo.setText("");
    txtModeloVeiculo.setText("");
    ftfAnoVeiculo.setText("");
    txtCorVeiculo.setText("");
    txtCapacidadeCarga.setText("");
    txtPlacaVeiculo.setText("");
    txtObservacoesVeiculo.setText("");

    cbStatusVeiculo.setSelectedIndex(0);

    idVeiculoSelecionado = -1;

    tblVeiculos.clearSelection();
}



private void cadastrarTaxas() {

    try {
        String regiao = cbRegiaoTaxas.getSelectedItem().toString();
        String tipoTaxa = cbTipoTaxa.getSelectedItem().toString();

        double valorTaxa = Double.parseDouble(
            txtValorTaxa.getText().trim().replace(",", ".")
        );

        String observacao = txtObservacaoTaxa.getText().trim();

        if (regiao.isEmpty() || tipoTaxa.isEmpty()
                || txtValorTaxa.getText().trim().isEmpty()) {

            JOptionPane.showMessageDialog(this,
                "Preencha todos os campos obrigatórios!");
            return;
        }

        TaxasEntregaController controller = new TaxasEntregaController();

        boolean sucesso = controller.cadastrar(
            regiao, tipoTaxa, valorTaxa, observacao
        );

        if (sucesso) {
            JOptionPane.showMessageDialog(this,
                "Taxa cadastrada com sucesso!");

            limparCamposTaxas();
            carregarTabelaTaxas();
        }

    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this,
            "Informe um valor válido para a taxa!");

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this,
            "Erro ao cadastrar taxa: " + e.getMessage());
    }
}

private void carregarTabelaTaxas() {

    try {
        TaxasEntregaController controller = new TaxasEntregaController();

        List<Map<String, Object>> lista = controller.listar();

        DefaultTableModel modelo = (DefaultTableModel)
            tblListaTaxas.getModel();

        modelo.setRowCount(0);

        for (Map<String, Object> taxa : lista) {

            modelo.addRow(new Object[]{
                taxa.get("idtaxasEntrega"),
                taxa.get("regiao"),
                taxa.get("tipoTaxa"),
                taxa.get("valorTaxa"),
                taxa.get("observacao")
            });
        }

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this,
            "Erro ao listar taxas: " + e.getMessage());
    }
}

private void alterarTaxa() {

    if (idTaxaSelecionada == -1) {
        JOptionPane.showMessageDialog(this,
            "Selecione uma taxa na tabela!");
        return;
    }

    try {
        String regiao = cbRegiaoTaxas.getSelectedItem().toString();
        String tipoTaxa = cbTipoTaxa.getSelectedItem().toString();

        double valorTaxa = Double.parseDouble(
            txtValorTaxa.getText().trim().replace(",", ".")
        );

        String observacao = txtObservacaoTaxa.getText().trim();

        TaxasEntregaController controller = new TaxasEntregaController();

        boolean sucesso = controller.atualizar(
            idTaxaSelecionada,
            regiao,
            tipoTaxa,
            valorTaxa,
            observacao
        );

        if (sucesso) {
            JOptionPane.showMessageDialog(this,
                "Taxa alterada com sucesso!");

            limparCamposTaxas();
            carregarTabelaTaxas();
        }

    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this,
            "Informe um valor válido para a taxa!");

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this,
            "Erro ao alterar taxa: " + e.getMessage());
    }
}

private void excluirTaxa() {

    if (idTaxaSelecionada == -1) {
        JOptionPane.showMessageDialog(this,
            "Selecione uma taxa na tabela!");
        return;
    }

    int confirmacao = JOptionPane.showConfirmDialog(
        this,
        "Deseja realmente excluir esta taxa?",
        "Confirmar exclusão",
        JOptionPane.YES_NO_OPTION
    );

    if (confirmacao == JOptionPane.YES_OPTION) {

        try {
            TaxasEntregaController controller = new TaxasEntregaController();

            boolean sucesso = controller.excluir(idTaxaSelecionada);

            if (sucesso) {
                JOptionPane.showMessageDialog(this,
                    "Taxa excluída com sucesso!");

                limparCamposTaxas();
                carregarTabelaTaxas();
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this,
                "Erro ao excluir taxa: " + e.getMessage());
        }
    }
}

private void limparCamposTaxas() {

    cbRegiaoTaxas.setSelectedIndex(0);
    cbTipoTaxa.setSelectedIndex(0);

    txtValorTaxa.setText("");
    txtObservacaoTaxa.setText("");

    idTaxaSelecionada = -1;

    tblListaTaxas.clearSelection();
}

private void selecionarTaxa() {

    int linha = tblListaTaxas.getSelectedRow();

    if (linha != -1) {

        idTaxaSelecionada = Integer.parseInt(
            tblListaTaxas.getValueAt(linha, 0).toString()
        );

        cbRegiaoTaxas.setSelectedItem(
            tblListaTaxas.getValueAt(linha, 1).toString()
        );

        cbTipoTaxa.setSelectedItem(
            tblListaTaxas.getValueAt(linha, 2).toString()
        );

        txtValorTaxa.setText(
            tblListaTaxas.getValueAt(linha, 3).toString()
        );

        Object observacao = tblListaTaxas.getValueAt(linha, 4);

        txtObservacaoTaxa.setText(
            observacao == null ? "" : observacao.toString()
        );
    }
}
    
    

private void cadastrarEnderecos() {

    try {
        String cidade = txtCidadeEnderecos.getText().trim();
        String cep = txtCepEnderecos.getText().trim();
        String estado = cbEstadoEnderecos.getSelectedItem().toString();
        String rua = txtRuaEnderecos.getText().trim();
        String bairro = txtBairroEnderecos.getText().trim();
        String numero = txtNumeroEnderecos.getText().trim();

        if (cidade.isEmpty() || cep.isEmpty()
                || rua.isEmpty() || bairro.isEmpty()
                || numero.isEmpty() || idClienteSelecionado == -1) {

            JOptionPane.showMessageDialog(this,
                    "Preencha todos os campos e selecione um cliente!");
            return;
        }

        EnderecoController controller = new EnderecoController();

        boolean sucesso = controller.cadastrar(
                bairro, cep, cidade, estado,
                rua, numero, idClienteSelecionado
        );

        if (sucesso) {
            JOptionPane.showMessageDialog(this,
                    "Endereço cadastrado com sucesso!");

            limparCamposEnderecos();
            carregarTabelaEnderecos();
        }

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this,
                "Erro ao cadastrar endereço: " + e.getMessage());
    }
}

private void carregarTabelaEnderecos() {

    try {
        EnderecoController controller = new EnderecoController();

        List<Map<String, Object>> lista = controller.listar();

        DefaultTableModel modelo =
                (DefaultTableModel) tblListaEnderecos.getModel();

        modelo.setRowCount(0);

        for (Map<String, Object> endereco : lista) {

            modelo.addRow(new Object[]{
                endereco.get("idEndereco"),
                endereco.get("cliente_idcliente"),
                endereco.get("cidade"),
                endereco.get("estado"),
                endereco.get("rua"),
                endereco.get("numero"),
                endereco.get("bairro"),
                endereco.get("cep")
            });
        }

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this,
                "Erro ao listar endereços: " + e.getMessage());
    }
}

private void alterarEndereco() {

    if (idEnderecoSelecionado == -1) {
        JOptionPane.showMessageDialog(this,
                "Selecione um endereço na tabela!");
        return;
    }

    try {
        String cidade = txtCidadeEnderecos.getText().trim();
        String cep = txtCepEnderecos.getText().trim();
        String estado = cbEstadoEnderecos.getSelectedItem().toString();
        String rua = txtRuaEnderecos.getText().trim();
        String bairro = txtBairroEnderecos.getText().trim();
        String numero = txtNumeroEnderecos.getText().trim();

        if (cidade.isEmpty() || cep.isEmpty()
                || rua.isEmpty() || bairro.isEmpty()
                || numero.isEmpty() || idClienteSelecionado == -1) {

            JOptionPane.showMessageDialog(this,
                    "Preencha todos os campos e selecione um cliente!");
            return;
        }

        EnderecoController controller = new EnderecoController();

        boolean sucesso = controller.atualizar(
                idEnderecoSelecionado,
                bairro, cep, cidade, estado,
                rua, numero, idClienteSelecionado
        );

        if (sucesso) {
            JOptionPane.showMessageDialog(this,
                    "Endereço alterado com sucesso!");

            limparCamposEnderecos();
            carregarTabelaEnderecos();
        }

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this,
                "Erro ao alterar endereço: " + e.getMessage());
    }
}

private void excluirEndereco() {

    if (idEnderecoSelecionado == -1) {
        JOptionPane.showMessageDialog(this,
                "Selecione um endereço na tabela!");
        return;
    }

    int confirmacao = JOptionPane.showConfirmDialog(
            this,
            "Deseja realmente excluir este endereço?",
            "Confirmar exclusão",
            JOptionPane.YES_NO_OPTION
    );

    if (confirmacao == JOptionPane.YES_OPTION) {

        try {
            EnderecoController controller = new EnderecoController();

            boolean sucesso = controller.excluir(idEnderecoSelecionado);

            if (sucesso) {
                JOptionPane.showMessageDialog(this,
                        "Endereço excluído com sucesso!");

                limparCamposEnderecos();
                carregarTabelaEnderecos();
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this,
                    "Erro ao excluir endereço: " + e.getMessage());
        }
    }
}

private void limparCamposEnderecos() {

    txtCidadeEnderecos.setText("");
    txtClienteEnderecos.setText("");
    txtCepEnderecos.setText("");
    txtRuaEnderecos.setText("");
    txtBairroEnderecos.setText("");
    txtNumeroEnderecos.setText("");

    cbEstadoEnderecos.setSelectedIndex(0);

    idEnderecoSelecionado = -1;
    idClienteSelecionado = -1;

    tblListaEnderecos.clearSelection();
}

private void selecionarEndereco() {

    int linha = tblListaEnderecos.getSelectedRow();

    if (linha != -1) {

        idEnderecoSelecionado = Integer.parseInt(
                tblListaEnderecos.getValueAt(linha, 0).toString()
        );

        idClienteSelecionado = Integer.parseInt(
                tblListaEnderecos.getValueAt(linha, 1).toString()
        );

        txtClienteEnderecos.setText(
                "ID: " + idClienteSelecionado
        );

        txtCidadeEnderecos.setText(
                tblListaEnderecos.getValueAt(linha, 2).toString()
        );

        cbEstadoEnderecos.setSelectedItem(
                tblListaEnderecos.getValueAt(linha, 3).toString()
        );

        txtRuaEnderecos.setText(
                tblListaEnderecos.getValueAt(linha, 4).toString()
        );

        txtNumeroEnderecos.setText(
                tblListaEnderecos.getValueAt(linha, 5).toString()
        );

        txtBairroEnderecos.setText(
                tblListaEnderecos.getValueAt(linha, 6).toString()
        );

        txtCepEnderecos.setText(
                tblListaEnderecos.getValueAt(linha, 7).toString()
        );
    }
}



private void cadastrarEntregas() {

    try {
        String data = ftfDataEntrega.getText().trim();
        String horario = ftfHorarioEntrega.getText().trim();

        String status = cbStatusEntrega.getSelectedItem().toString();

        if (data.isEmpty() || horario.isEmpty()
                || idClienteSelecionado == -1
                || idVeiculoSelecionado == -1
                || idTaxaSelecionada == -1
                || idEntregadorSelecionado == -1
                || idCargoSelecionado == -1
                || idSetorSelecionado == -1
                || idTurnoSelecionado == -1) {

            JOptionPane.showMessageDialog(this,
                    "Preencha todos os campos obrigatórios!");
            return;
        }

        EntregaController controller = new EntregaController();

        boolean sucesso = controller.cadastrar(
                data,
                horario,
                status,
                idClienteSelecionado,
                idVeiculoSelecionado,
                idTaxaSelecionada,
                idEntregadorSelecionado,
                idCargoSelecionado,
                idSetorSelecionado,
                idTurnoSelecionado
        );

        if (sucesso) {
            JOptionPane.showMessageDialog(this,
                    "Entrega cadastrada com sucesso!");

            limparCamposEntregas();
            carregarTabelaEntregas();
        }

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this,
                "Erro ao cadastrar entrega: " + e.getMessage());
    }
}

private void carregarTabelaEntregas() {

    try {
        EntregaController controller = new EntregaController();

        List<Map<String, Object>> lista = controller.listar();

        DefaultTableModel modelo =
                (DefaultTableModel) tblEntregas.getModel();

        modelo.setRowCount(0);

        for (Map<String, Object> entrega : lista) {

            modelo.addRow(new Object[]{
                entrega.get("identregas"),
                entrega.get("dataEntrega"),
                entrega.get("cliente_idcliente"),
                entrega.get("Funcionario_idFuncionario"),
                entrega.get("veiculos_idveiculos"),
                entrega.get("Funcionario_setores_idsetores"),
                entrega.get("taxasEntrega_idtaxasEntrega"),
                entrega.get("statusEntrega")
            });
        }

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this,
                "Erro ao listar entregas: " + e.getMessage());
    }
}

private void alterarEntrega() {

    if (idEntregaSelecionada == -1) {
        JOptionPane.showMessageDialog(this,
                "Selecione uma entrega na tabela!");
        return;
    }

    try {
        String data = ftfDataEntrega.getText().trim();
        String horario = ftfHorarioEntrega.getText().trim();

        String status = cbStatusEntrega.getSelectedItem().toString();

        if (data.isEmpty() || horario.isEmpty()
                || idClienteSelecionado == -1
                || idVeiculoSelecionado == -1
                || idTaxaSelecionada == -1
                || idEntregadorSelecionado == -1
                || idCargoSelecionado == -1
                || idSetorSelecionado == -1
                || idTurnoSelecionado == -1) {

            JOptionPane.showMessageDialog(this,
                    "Preencha todos os campos obrigatórios!");
            return;
        }

        EntregaController controller = new EntregaController();

        boolean sucesso = controller.atualizar(
                idEntregaSelecionada,
                data,
                horario,
                status,
                idClienteSelecionado,
                idVeiculoSelecionado,
                idTaxaSelecionada,
                idEntregadorSelecionado,
                idCargoSelecionado,
                idSetorSelecionado,
                idTurnoSelecionado
        );

        if (sucesso) {
            JOptionPane.showMessageDialog(this,
                    "Entrega alterada com sucesso!");

            limparCamposEntregas();
            carregarTabelaEntregas();
        }

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this,
                "Erro ao alterar entrega: " + e.getMessage());
    }
}

private void excluirEntrega() {

    if (idEntregaSelecionada == -1) {
        JOptionPane.showMessageDialog(this,
                "Selecione uma entrega na tabela!");
        return;
    }

    int confirmacao = JOptionPane.showConfirmDialog(
            this,
            "Deseja realmente excluir esta entrega?",
            "Confirmar exclusão",
            JOptionPane.YES_NO_OPTION
    );

    if (confirmacao == JOptionPane.YES_OPTION) {

        try {
            EntregaController controller = new EntregaController();

            boolean sucesso = controller.excluir(idEntregaSelecionada);

            if (sucesso) {
                JOptionPane.showMessageDialog(this,
                        "Entrega excluída com sucesso!");

                limparCamposEntregas();
                carregarTabelaEntregas();
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this,
                    "Erro ao excluir entrega: " + e.getMessage());
        }
    }
}

private void limparCamposEntregas() {

    ftfDataEntrega.setText("");
    ftfHorarioEntrega.setText("");
    txtClienteEntregas.setText("");
    txtTaxaEntrega.setText("");

    cbStatusEntrega.setSelectedIndex(0);
    cbVeiculoEntregas.setSelectedIndex(0);
    cbEntregadorEntregas.setSelectedIndex(0);

    idEntregaSelecionada = -1;
    idClienteSelecionado = -1;
    idVeiculoSelecionado = -1;
    idTaxaSelecionada = -1;
    idEntregadorSelecionado = -1;
    idCargoSelecionado = -1;
    idSetorSelecionado = -1;
    idTurnoSelecionado = -1;

    tblEntregas.clearSelection();
}

private void selecionarEntrega() {

    int linha = tblEntregas.getSelectedRow();

    if (linha != -1) {

        idEntregaSelecionada = Integer.parseInt(
                tblEntregas.getValueAt(linha, 0).toString()
        );

        // Os demais campos devem ser preenchidos
        // a partir dos IDs e dados da entrega selecionada.
    }
}


   
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        entregadores = new javax.swing.JPanel();
        lblNome = new javax.swing.JLabel();
        lblCpf = new javax.swing.JLabel();
        lblDataNascimento = new javax.swing.JLabel();
        lblTelefone = new javax.swing.JLabel();
        lblStatusEntregadores = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        ftfCpf = new javax.swing.JFormattedTextField();
        ftfDataNascimento = new javax.swing.JFormattedTextField();
        ftfTelefone = new javax.swing.JFormattedTextField();
        tituloDadosEntregadores = new javax.swing.JLabel();
        tituloEndereco = new javax.swing.JLabel();
        lblRua = new javax.swing.JLabel();
        txtRua = new javax.swing.JTextField();
        lblNumero = new javax.swing.JLabel();
        txtNumero = new javax.swing.JTextField();
        labBairro = new javax.swing.JLabel();
        txtBairro = new javax.swing.JTextField();
        lblCidade = new javax.swing.JLabel();
        txtCidade = new javax.swing.JTextField();
        lblCep = new javax.swing.JLabel();
        txtCep = new javax.swing.JTextField();
        btnNovoEntregadores = new javax.swing.JButton();
        btnSalvaEntregadores = new javax.swing.JButton();
        btnAlterarEntregadores = new javax.swing.JButton();
        btnExcluirEntregadores = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblEntregadores = new javax.swing.JTable();
        cbStatusEntregadores = new javax.swing.JComboBox<>();
        lblEstadoEntregadores = new javax.swing.JLabel();
        cbEstadoEntregadores = new javax.swing.JComboBox<>();
        veiculos = new javax.swing.JPanel();
        tituloVeiculo = new javax.swing.JLabel();
        lblPlaca = new javax.swing.JLabel();
        txtPlacaVeiculo = new javax.swing.JTextField();
        lblTipoVeiculo = new javax.swing.JLabel();
        txtTipoVeiculo = new javax.swing.JTextField();
        lblMarca = new javax.swing.JLabel();
        txtMarcaVeiculo = new javax.swing.JTextField();
        lblModelo = new javax.swing.JLabel();
        txtModeloVeiculo = new javax.swing.JTextField();
        lblAno = new javax.swing.JLabel();
        ftfAnoVeiculo = new javax.swing.JFormattedTextField();
        lblCor = new javax.swing.JLabel();
        txtCorVeiculo = new javax.swing.JTextField();
        lblCarga = new javax.swing.JLabel();
        txtCapacidadeCarga = new javax.swing.JFormattedTextField();
        lblMotorista = new javax.swing.JLabel();
        cdMotorista = new javax.swing.JComboBox<>();
        lblDocumentacao = new javax.swing.JLabel();
        lblCnh = new javax.swing.JLabel();
        ftfCnh = new javax.swing.JFormattedTextField();
        lblVencimentoCnh = new javax.swing.JLabel();
        ftfVencimentoCnh = new javax.swing.JFormattedTextField();
        btnNovoVeiculos = new javax.swing.JButton();
        btnSalvarVeiculos = new javax.swing.JButton();
        btnAlterarVeiculos = new javax.swing.JButton();
        btnExcluirVeiculos = new javax.swing.JButton();
        lblStatusVeiculos = new javax.swing.JLabel();
        cbStatusVeiculo = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblVeiculos = new javax.swing.JTable();
        lblObservacoesVeiculos = new javax.swing.JLabel();
        jScrollPane21 = new javax.swing.JScrollPane();
        txtObservacoesVeiculo = new javax.swing.JTextArea();
        regiao = new javax.swing.JPanel();
        tituloRegiao = new javax.swing.JLabel();
        lblNomeRegiao = new javax.swing.JLabel();
        txtNomeRegiao = new javax.swing.JTextField();
        lblTipoRegiao = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        lblCodigoRegiao = new javax.swing.JLabel();
        lblValorTaxaRegiao = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        txtValorTaxaRegiao = new javax.swing.JTextField();
        lblBairroRegiao = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtaBairroRegiao = new javax.swing.JTextArea();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblRegioes = new javax.swing.JTable();
        lblObservacoesRegioes = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        txtaObservacoesRegiao = new javax.swing.JTextArea();
        btnNovoRegioes = new javax.swing.JButton();
        btnSalvarRegioes = new javax.swing.JButton();
        btnAlterarRegioes = new javax.swing.JButton();
        btnExcluirRegioes = new javax.swing.JButton();
        taxas = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lblRegiaoTaxas = new javax.swing.JLabel();
        cbRegiaoTaxas = new javax.swing.JComboBox<>();
        lblTipoTaxa = new javax.swing.JLabel();
        cbTipoTaxa = new javax.swing.JComboBox<>();
        lblValorTaxas = new javax.swing.JLabel();
        txtValorTaxa = new javax.swing.JTextField();
        lblObservacoesTaxas = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        txtObservacaoTaxa = new javax.swing.JTextArea();
        btnNovoTaxas = new javax.swing.JButton();
        btnSalvarTaxas = new javax.swing.JButton();
        btnAlterarTaxas = new javax.swing.JButton();
        btnExcluirTaxas = new javax.swing.JButton();
        jScrollPane7 = new javax.swing.JScrollPane();
        tblListaTaxas = new javax.swing.JTable();
        endereço = new javax.swing.JPanel();
        lblTituloEntrega = new javax.swing.JLabel();
        lblClienteEnderecos = new javax.swing.JLabel();
        txtCidadeEnderecos = new javax.swing.JTextField();
        txtClienteEnderecos = new javax.swing.JTextField();
        lblCepEnderecos = new javax.swing.JLabel();
        txtCepEnderecos = new javax.swing.JTextField();
        lblEstadoEnderecos = new javax.swing.JLabel();
        cbEstadoEnderecos = new javax.swing.JComboBox<>();
        btnPesquisarEnderecos = new javax.swing.JButton();
        jScrollPane8 = new javax.swing.JScrollPane();
        tblListaEnderecos = new javax.swing.JTable();
        btnNovoEnderecos = new javax.swing.JButton();
        btnAlterarEndercos = new javax.swing.JButton();
        btnExcluirEnderecos = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        lblRuaEnderecos = new javax.swing.JLabel();
        txtRuaEnderecos = new javax.swing.JTextField();
        lblBairroEnderecos = new javax.swing.JLabel();
        lblNumeroEnderecos = new javax.swing.JLabel();
        txtBairroEnderecos = new javax.swing.JTextField();
        txtNumeroEnderecos = new javax.swing.JTextField();
        btnSalvarEnderecos = new javax.swing.JButton();
        entregas = new javax.swing.JPanel();
        lbltituloEntregas = new javax.swing.JLabel();
        lblDataEntregas = new javax.swing.JLabel();
        ftfDataEntrega = new javax.swing.JFormattedTextField();
        lblTaxaEntregas = new javax.swing.JLabel();
        txtTaxaEntrega = new javax.swing.JTextField();
        lblClienteEntregas = new javax.swing.JLabel();
        txtClienteEntregas = new javax.swing.JTextField();
        lblEntregadorEntregas = new javax.swing.JLabel();
        cbEntregadorEntregas = new javax.swing.JComboBox<>();
        lblStatusEntregas = new javax.swing.JLabel();
        cbStatusEntrega = new javax.swing.JComboBox<>();
        lblVeiculoEntregas = new javax.swing.JLabel();
        cbVeiculoEntregas = new javax.swing.JComboBox<>();
        lblHoraEntregas = new javax.swing.JLabel();
        ftfHorarioEntrega = new javax.swing.JFormattedTextField();
        btnNovoEntregas = new javax.swing.JButton();
        btnSalvarEntregas = new javax.swing.JButton();
        btnAlterarEntregas = new javax.swing.JButton();
        btnExcluirEntregas = new javax.swing.JButton();
        btnPesquisarEntregas = new javax.swing.JButton();
        btnVoltarEntregas = new javax.swing.JButton();
        jScrollPane11 = new javax.swing.JScrollPane();
        tblEntregas = new javax.swing.JTable();
        dataHorario = new javax.swing.JPanel();
        lbltituloDataHorario = new javax.swing.JLabel();
        lblDataInicial = new javax.swing.JLabel();
        ftfDataInicial = new javax.swing.JFormattedTextField();
        lblDataFinal = new javax.swing.JLabel();
        ftfDataFinal = new javax.swing.JFormattedTextField();
        lblHorarioInicial = new javax.swing.JLabel();
        ftfHorarioInicial = new javax.swing.JFormattedTextField();
        lblHorarioFinal = new javax.swing.JLabel();
        txtHorarioFinal = new javax.swing.JFormattedTextField();
        lblEntregadorDataHorario = new javax.swing.JLabel();
        cbEntregadorDataHorario = new javax.swing.JComboBox<>();
        lblStatusDataHorario = new javax.swing.JLabel();
        cbStatusDataHorario = new javax.swing.JComboBox<>();
        btnPesquisarDataHorario = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        lblEntregasDiaDH = new javax.swing.JLabel();
        lblNumeroEntregasDiaDH = new javax.swing.JLabel();
        lblIconeEntregasDia = new javax.swing.JLabel();
        lblTotalEntregasDH1 = new javax.swing.JLabel();
        lblNumeroTotalDH1 = new javax.swing.JLabel();
        lblIconeTotalEntregas1 = new javax.swing.JLabel();
        lblAndamento = new javax.swing.JLabel();
        lblIconeAndamento = new javax.swing.JLabel();
        lnlNumeroAndamento = new javax.swing.JLabel();
        lblIconeConcluidas = new javax.swing.JLabel();
        lblConcluidas = new javax.swing.JLabel();
        lblNumeroConcluidas = new javax.swing.JLabel();
        jScrollPane12 = new javax.swing.JScrollPane();
        tblAgendaEntregas = new javax.swing.JTable();
        btnNovoDataHorario = new javax.swing.JButton();
        btnAlterarDataHorario = new javax.swing.JButton();
        btnExcluirDataHorario = new javax.swing.JButton();
        btnVoltarDataHorario = new javax.swing.JButton();
        agenda = new javax.swing.JPanel();
        lblTituloAgenda = new javax.swing.JLabel();
        lblDataAgenda = new javax.swing.JLabel();
        ftfDataAgenda = new javax.swing.JFormattedTextField();
        lblEntregadorAgenda = new javax.swing.JLabel();
        cbEntregadorAgenda = new javax.swing.JComboBox<>();
        lblVeiculoAgenda = new javax.swing.JLabel();
        cbVeiculoAgenda = new javax.swing.JComboBox<>();
        lblRegiãoAgenda = new javax.swing.JLabel();
        cbRegiaoAgenda = new javax.swing.JComboBox<>();
        btnPesquisarAgenda = new javax.swing.JButton();
        lblTotalEntregasAgenda = new javax.swing.JLabel();
        lblIconeEntregasAgenda = new javax.swing.JLabel();
        lblNumeroEntregasAgenda = new javax.swing.JLabel();
        lblIconeAndamentoAgenda = new javax.swing.JLabel();
        lblAndamentoAgenda = new javax.swing.JLabel();
        lblNUmeroAndamentoAgenda = new javax.swing.JLabel();
        lblIconrPendentesAgenda = new javax.swing.JLabel();
        lblPendentesAgenda = new javax.swing.JLabel();
        lblNumeroPendentesAgenda = new javax.swing.JLabel();
        lblIconeConcluidasAgenda = new javax.swing.JLabel();
        lblConcluidasAgenda = new javax.swing.JLabel();
        lblNumeroConcluidasAgenda = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        lblCalendarioAgenda = new javax.swing.JLabel();
        jScrollPane13 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btnNovoAgenda = new javax.swing.JButton();
        btnAlterarAgenda = new javax.swing.JButton();
        btnExcluirAgenda = new javax.swing.JButton();
        btnVoltarAgenda = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        distribuicao = new javax.swing.JPanel();
        lblTituloDistribuicao = new javax.swing.JLabel();
        lblPedidoDistribuicao = new javax.swing.JLabel();
        txtPedidoDistribuicao = new javax.swing.JTextField();
        lblClienteDistribuicao = new javax.swing.JLabel();
        txtClienteDistribuicao = new javax.swing.JTextField();
        lblEnderecoDistribuicao = new javax.swing.JLabel();
        jScrollPane14 = new javax.swing.JScrollPane();
        txtaEnderecoDistribuicao = new javax.swing.JTextArea();
        lblRegiaoDistribuicao = new javax.swing.JLabel();
        cbRegiaoDistribuicao = new javax.swing.JComboBox<>();
        lblEntregadorDistribuicao = new javax.swing.JLabel();
        cbEntregadorDistribuicao = new javax.swing.JComboBox<>();
        lblVeiculoDistribuicao = new javax.swing.JLabel();
        cbVeiculoDistribuicao = new javax.swing.JComboBox<>();
        lblDataDistribuicao = new javax.swing.JLabel();
        ftfDataDistribuicao = new javax.swing.JFormattedTextField();
        lblHorarioEntregaDistribuicao = new javax.swing.JLabel();
        ftfHorarioDistribuicao = new javax.swing.JFormattedTextField();
        lblStatusDistribuicao = new javax.swing.JLabel();
        cbStatusDistribuicao = new javax.swing.JComboBox<>();
        lblObservacoesDistribuicao = new javax.swing.JLabel();
        jScrollPane15 = new javax.swing.JScrollPane();
        txtaObservacoesDistribuicao = new javax.swing.JTextArea();
        btnNovoDistribuicao = new javax.swing.JButton();
        btnSalvarDistribuicao = new javax.swing.JButton();
        btnAlterarDistribuicao = new javax.swing.JButton();
        btnExcluirDistribuicao = new javax.swing.JButton();
        btnVoltarDistribuicao = new javax.swing.JButton();
        jScrollPane16 = new javax.swing.JScrollPane();
        tblListaDistribuicao = new javax.swing.JTable();
        status = new javax.swing.JPanel();
        lblTituloStatus = new javax.swing.JLabel();
        lblPeriodoStatus = new javax.swing.JLabel();
        ftfPeriodoStatus = new javax.swing.JFormattedTextField();
        lblStatus = new javax.swing.JLabel();
        cbStatus = new javax.swing.JComboBox<>();
        lblEntregadorStatus = new javax.swing.JLabel();
        cbEntregadorStatus = new javax.swing.JComboBox<>();
        lblCliente = new javax.swing.JLabel();
        txtClienteStatus = new javax.swing.JTextField();
        lblIconePagamentoStatus = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblNumeroPagamentoStatus = new javax.swing.JLabel();
        lblIconeSeparacaoStatus = new javax.swing.JLabel();
        lblSeparacaoStatus = new javax.swing.JLabel();
        lblNumeroSeparacaoStatus = new javax.swing.JLabel();
        lblIconeProntosStatus = new javax.swing.JLabel();
        lblProntosStatus = new javax.swing.JLabel();
        lblNumeroProntosStatus = new javax.swing.JLabel();
        lblIconeEnviadosStatus = new javax.swing.JLabel();
        lblEnviadosStatus = new javax.swing.JLabel();
        lblNumeroEnviadosStatus = new javax.swing.JLabel();
        lblIconeConcluídos = new javax.swing.JLabel();
        lblConcluidosStatus = new javax.swing.JLabel();
        lblNumeroConcluidosStatus = new javax.swing.JLabel();
        lblIconeCanceladosStatus = new javax.swing.JLabel();
        lblCanceladosStatus = new javax.swing.JLabel();
        lblNumeroCanceladosStatus = new javax.swing.JLabel();
        jScrollPane17 = new javax.swing.JScrollPane();
        tblEntregasStatus = new javax.swing.JTable();
        btnNovoStatus = new javax.swing.JButton();
        btnAlterarStatus = new javax.swing.JButton();
        btnExcluirStatus = new javax.swing.JButton();
        btnVoltarStatus = new javax.swing.JButton();
        tentativas = new javax.swing.JPanel();
        lblTituloTentativas = new javax.swing.JLabel();
        lblPeriodoTentativas = new javax.swing.JLabel();
        ftfPeriodoTentativas = new javax.swing.JFormattedTextField();
        lblClienteTentativas = new javax.swing.JLabel();
        txtClienteTentativas = new javax.swing.JTextField();
        lblEntregadorTentativas = new javax.swing.JLabel();
        cbEntregadorTentativas = new javax.swing.JComboBox<>();
        lblStatusTentativas = new javax.swing.JLabel();
        cbStatusTentativas = new javax.swing.JComboBox<>();
        btnPesquisarTentativas = new javax.swing.JButton();
        lblIconeTotalTentativas = new javax.swing.JLabel();
        lblTotalTentativas = new javax.swing.JLabel();
        lblNumeroTotalTentativas = new javax.swing.JLabel();
        lblIconeEntreguesTentativas = new javax.swing.JLabel();
        lblEntreguesTentativas = new javax.swing.JLabel();
        lblNumeroEntreguesTentativas = new javax.swing.JLabel();
        lblIconeAguardandoTentativas = new javax.swing.JLabel();
        lblAguardandoTentativas = new javax.swing.JLabel();
        lblNumeroAguardandoTentativas = new javax.swing.JLabel();
        lblIconeCanceladasTentativas = new javax.swing.JLabel();
        lblCanceladasTentativas = new javax.swing.JLabel();
        lblNumeroCanceladasTentativas = new javax.swing.JLabel();
        jScrollPane18 = new javax.swing.JScrollPane();
        tblTentativasEntrega = new javax.swing.JTable();
        btnNovoTentativas = new javax.swing.JButton();
        btnAlterarTentativas = new javax.swing.JButton();
        btnExcluirTentativas = new javax.swing.JButton();
        btnVoltarTentativas = new javax.swing.JButton();
        confirmacao = new javax.swing.JPanel();
        lblTituloConfirmacao = new javax.swing.JLabel();
        lblPeriodoConfirmacao = new javax.swing.JLabel();
        ftfPeriodoConfirmacao = new javax.swing.JFormattedTextField();
        lblClienteConfirmacao = new javax.swing.JLabel();
        txtClienteConfirmacao = new javax.swing.JTextField();
        lblEntregadorConfirmacao = new javax.swing.JLabel();
        cbEntregadorConfirmacao = new javax.swing.JComboBox<>();
        lblStatusConfirmacao = new javax.swing.JLabel();
        cbStatusConfirmacao = new javax.swing.JComboBox<>();
        btnPesquisarConfirmacao = new javax.swing.JButton();
        lblIconeEntregasConfirmadas = new javax.swing.JLabel();
        lblEntregasConfirmadas = new javax.swing.JLabel();
        lblNumeroEntregasConfirmadas = new javax.swing.JLabel();
        lblIconeAguargandoConfirmacao = new javax.swing.JLabel();
        lblAguardandoConfirmacao = new javax.swing.JLabel();
        lblNumeorAguardandoConfirmacao = new javax.swing.JLabel();
        lblIconeNaoConfirmada = new javax.swing.JLabel();
        lblNaoConfirmada = new javax.swing.JLabel();
        lblNumeroNaoConfirmada = new javax.swing.JLabel();
        lblIconeTotalEntregasConfirmacao = new javax.swing.JLabel();
        lblTotalEntregasConfirmacao = new javax.swing.JLabel();
        lblNumeroTotalEntregasConfirmacao = new javax.swing.JLabel();
        jScrollPane19 = new javax.swing.JScrollPane();
        tblEntregasConfirmacao = new javax.swing.JTable();
        btnNovoConfirmacao = new javax.swing.JButton();
        btnAlterarConfirmacao = new javax.swing.JButton();
        btnExcluirConfirmacao = new javax.swing.JButton();
        btnVoltarConfirmacao = new javax.swing.JButton();
        historicoRelatorio = new javax.swing.JPanel();
        lblTituloHistoricoRelatorio = new javax.swing.JLabel();
        lblPeriodoHistoricoRelatorio = new javax.swing.JLabel();
        ftfPeriodoHistoricoRelatorio = new javax.swing.JFormattedTextField();
        lblTipoRelatorio = new javax.swing.JLabel();
        cbTipoRelatorio = new javax.swing.JComboBox<>();
        lblStatusHistoricoRelatorio = new javax.swing.JLabel();
        cbStatusHistoricoRelatorio = new javax.swing.JComboBox<>();
        lblEntregadorHistoricoRelatorio = new javax.swing.JLabel();
        cbEntregadorHistoricoRelatorio = new javax.swing.JComboBox<>();
        btnPesquisarHistoricoRelatorio = new javax.swing.JButton();
        lblIconeTotalHistoricoRelatorio = new javax.swing.JLabel();
        lblTotalHistoricoRelatorio = new javax.swing.JLabel();
        lblNumeroTotalHistoricoRelatorio = new javax.swing.JLabel();
        lblIconeEntregasHistoricoRelatorio = new javax.swing.JLabel();
        lblEntregasHistoricoRelatorio = new javax.swing.JLabel();
        lblNumeroEntregasHistoricoRelatorio = new javax.swing.JLabel();
        lblIconeAndamentoHistoricoRelatorio = new javax.swing.JLabel();
        lblAndamentoHistoricoRelatorio = new javax.swing.JLabel();
        lblNumeroAndamentoHistoricoRelatorio = new javax.swing.JLabel();
        lblIconeCanceladaHistoricoRelatorio = new javax.swing.JLabel();
        lblCanceladaHistoricoRelatorio = new javax.swing.JLabel();
        lblNumeroCanceladaHistoricoRelatorio = new javax.swing.JLabel();
        lblIconeTempoHistoricoRelatorio = new javax.swing.JLabel();
        lblTempoHistoricoRelatorio = new javax.swing.JLabel();
        lblNumeroTempoHistoricoRelatorio = new javax.swing.JLabel();
        jScrollPane20 = new javax.swing.JScrollPane();
        tblHistoricoEntregasRelatorio = new javax.swing.JTable();
        btnRelatorioEntregas = new javax.swing.JButton();
        btnRelatorioEntregador = new javax.swing.JButton();
        btnRelatorioRegiao = new javax.swing.JButton();
        btnRelatorioFinanceiro = new javax.swing.JButton();
        btnRelatorioStatus = new javax.swing.JButton();
        btnExportarRelatorio = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTabbedPane1.setFont(new java.awt.Font("Arial Black", 0, 15)); // NOI18N

        entregadores.setBackground(new java.awt.Color(236, 236, 236));

        lblNome.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblNome.setText("Nome:");

        lblCpf.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblCpf.setText("CPF:");

        lblDataNascimento.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblDataNascimento.setText("Data De Nascimento:");

        lblTelefone.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblTelefone.setText("Telefone:");

        lblStatusEntregadores.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblStatusEntregadores.setText("Status");

        txtNome.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        txtNome.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        ftfCpf.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        try {
            ftfCpf.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        ftfCpf.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N

        ftfDataNascimento.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        try {
            ftfDataNascimento.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        ftfDataNascimento.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N

        ftfTelefone.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        try {
            ftfTelefone.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##)#####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        ftfTelefone.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N

        tituloDadosEntregadores.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        tituloDadosEntregadores.setForeground(new java.awt.Color(10, 4, 138));
        tituloDadosEntregadores.setText("Dados do Entregador");

        tituloEndereco.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        tituloEndereco.setForeground(new java.awt.Color(10, 4, 138));
        tituloEndereco.setText("Endereço");

        lblRua.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblRua.setText("Rua / Avenida");

        txtRua.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        txtRua.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblNumero.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblNumero.setText("Número");

        txtNumero.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        txtNumero.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        labBairro.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        labBairro.setText("Bairro");

        txtBairro.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        txtBairro.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblCidade.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblCidade.setText("Cidade");

        txtCidade.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        txtCidade.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblCep.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblCep.setText("CEP");

        txtCep.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        txtCep.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        btnNovoEntregadores.setBackground(new java.awt.Color(10, 4, 138));
        btnNovoEntregadores.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        btnNovoEntregadores.setForeground(new java.awt.Color(255, 255, 255));
        btnNovoEntregadores.setIcon(new javax.swing.ImageIcon("C:\\Users\\SamaraTavares\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\icones\\botao-mais.png")); // NOI18N
        btnNovoEntregadores.setText("Novo");
        btnNovoEntregadores.setIconTextGap(6);

        btnSalvaEntregadores.setBackground(new java.awt.Color(10, 4, 138));
        btnSalvaEntregadores.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        btnSalvaEntregadores.setForeground(new java.awt.Color(255, 255, 255));
        btnSalvaEntregadores.setIcon(new javax.swing.ImageIcon("C:\\Users\\SamaraTavares\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\icones\\Salvar.png")); // NOI18N
        btnSalvaEntregadores.setText("Salvar");

        btnAlterarEntregadores.setBackground(new java.awt.Color(10, 4, 138));
        btnAlterarEntregadores.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        btnAlterarEntregadores.setForeground(new java.awt.Color(255, 255, 255));
        btnAlterarEntregadores.setIcon(new javax.swing.ImageIcon("C:\\Users\\SamaraTavares\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\icones\\editar.png")); // NOI18N
        btnAlterarEntregadores.setText("Alterar");

        btnExcluirEntregadores.setBackground(new java.awt.Color(10, 4, 138));
        btnExcluirEntregadores.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        btnExcluirEntregadores.setForeground(new java.awt.Color(255, 255, 255));
        btnExcluirEntregadores.setIcon(new javax.swing.ImageIcon("C:\\Users\\SamaraTavares\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\icones\\excluir.png")); // NOI18N
        btnExcluirEntregadores.setText("Excluir");

        tblEntregadores.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        tblEntregadores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Código", "Nome", "CPF", "Telefone", "Veículo", "Status", "Data de Contratação"
            }
        ));
        jScrollPane1.setViewportView(tblEntregadores);

        cbStatusEntregadores.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        cbStatusEntregadores.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ativo", "Inativo", " " }));

        lblEstadoEntregadores.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblEstadoEntregadores.setText("Estado");

        cbEstadoEntregadores.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        cbEstadoEntregadores.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione o estado", "* AC", "* AL", "* AM", "* AP", "* BA", "* CE", "* DF", "* ES", "* GO", "* MA", "* MG", "* MS", "* MT", "* PA", "* PB", "* PE", "* PI", "* PR", "* RJ", "* RN", "* RO", "* RR", "* RS", "* SC", "* SE", "* SP", "* TO" }));

        javax.swing.GroupLayout entregadoresLayout = new javax.swing.GroupLayout(entregadores);
        entregadores.setLayout(entregadoresLayout);
        entregadoresLayout.setHorizontalGroup(
            entregadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(entregadoresLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(entregadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(entregadoresLayout.createSequentialGroup()
                        .addGroup(entregadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(entregadoresLayout.createSequentialGroup()
                                .addComponent(lblCpf)
                                .addGap(203, 203, 203)
                                .addComponent(lblTelefone))
                            .addComponent(tituloEndereco)
                            .addComponent(tituloDadosEntregadores)
                            .addComponent(lblNome)
                            .addGroup(entregadoresLayout.createSequentialGroup()
                                .addGroup(entregadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(ftfDataNascimento, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(ftfCpf, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblDataNascimento))
                                .addGap(24, 24, 24)
                                .addGroup(entregadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblStatusEntregadores)
                                    .addComponent(ftfTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbStatusEntregadores, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(entregadoresLayout.createSequentialGroup()
                        .addGroup(entregadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(entregadoresLayout.createSequentialGroup()
                                .addComponent(txtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(51, 51, 51)
                                .addGroup(entregadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtBairro, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(labBairro)))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 762, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 493, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(entregadoresLayout.createSequentialGroup()
                                .addComponent(txtCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(entregadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtCep, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblCep))
                                .addGap(38, 38, 38)
                                .addGroup(entregadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblEstadoEntregadores)
                                    .addComponent(cbEstadoEntregadores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(lblRua)
                            .addComponent(txtRua, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblNumero)
                            .addComponent(lblCidade)
                            .addGroup(entregadoresLayout.createSequentialGroup()
                                .addComponent(btnNovoEntregadores)
                                .addGap(18, 18, 18)
                                .addComponent(btnSalvaEntregadores)
                                .addGap(18, 18, 18)
                                .addComponent(btnAlterarEntregadores)
                                .addGap(18, 18, 18)
                                .addComponent(btnExcluirEntregadores)))
                        .addGap(0, 1242, Short.MAX_VALUE))))
        );
        entregadoresLayout.setVerticalGroup(
            entregadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(entregadoresLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(tituloDadosEntregadores)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblNome)
                .addGap(3, 3, 3)
                .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(entregadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCpf)
                    .addComponent(lblTelefone))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(entregadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ftfCpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ftfTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(entregadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDataNascimento)
                    .addComponent(lblStatusEntregadores))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(entregadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ftfDataNascimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbStatusEntregadores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(tituloEndereco)
                .addGap(18, 18, 18)
                .addGroup(entregadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblRua)
                    .addComponent(lblEstadoEntregadores))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(entregadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtRua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbEstadoEntregadores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(entregadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNumero)
                    .addComponent(labBairro))
                .addGap(9, 9, 9)
                .addGroup(entregadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(entregadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCidade)
                    .addComponent(lblCep))
                .addGap(8, 8, 8)
                .addGroup(entregadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(entregadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNovoEntregadores)
                    .addComponent(btnSalvaEntregadores)
                    .addComponent(btnAlterarEntregadores)
                    .addComponent(btnExcluirEntregadores))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(247, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Entregadores", entregadores);

        veiculos.setBackground(new java.awt.Color(236, 236, 236));

        tituloVeiculo.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        tituloVeiculo.setForeground(new java.awt.Color(10, 4, 138));
        tituloVeiculo.setText("Cadastro de Veículo");

        lblPlaca.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblPlaca.setText("Placa");

        txtPlacaVeiculo.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        txtPlacaVeiculo.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblTipoVeiculo.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblTipoVeiculo.setText("Tipo de Veículo");

        txtTipoVeiculo.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        txtTipoVeiculo.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblMarca.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblMarca.setText("Marca");

        txtMarcaVeiculo.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        txtMarcaVeiculo.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblModelo.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblModelo.setText("Modelo");

        txtModeloVeiculo.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        txtModeloVeiculo.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        txtModeloVeiculo.addActionListener(this::txtModeloVeiculoActionPerformed);

        lblAno.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblAno.setText("Ano");

        ftfAnoVeiculo.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        ftfAnoVeiculo.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N

        lblCor.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblCor.setText("Cor");

        txtCorVeiculo.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        txtCorVeiculo.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblCarga.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblCarga.setText("Capacidade de Carga");

        txtCapacidadeCarga.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        txtCapacidadeCarga.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter()));
        txtCapacidadeCarga.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N

        lblMotorista.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblMotorista.setText("Motorista");

        cdMotorista.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        cdMotorista.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblDocumentacao.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        lblDocumentacao.setForeground(new java.awt.Color(10, 4, 138));
        lblDocumentacao.setText("Documentação");

        lblCnh.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblCnh.setText("CNH");

        ftfCnh.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        ftfCnh.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N

        lblVencimentoCnh.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblVencimentoCnh.setText("Vencimento da CNH");

        ftfVencimentoCnh.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        ftfVencimentoCnh.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N

        btnNovoVeiculos.setBackground(new java.awt.Color(10, 4, 138));
        btnNovoVeiculos.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        btnNovoVeiculos.setForeground(new java.awt.Color(255, 255, 255));
        btnNovoVeiculos.setIcon(new javax.swing.ImageIcon("C:\\Users\\SamaraTavares\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\icones\\botao-mais.png")); // NOI18N
        btnNovoVeiculos.setText("Novo");

        btnSalvarVeiculos.setBackground(new java.awt.Color(10, 4, 138));
        btnSalvarVeiculos.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        btnSalvarVeiculos.setForeground(new java.awt.Color(255, 255, 255));
        btnSalvarVeiculos.setIcon(new javax.swing.ImageIcon("C:\\Users\\SamaraTavares\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\icones\\Salvar.png")); // NOI18N
        btnSalvarVeiculos.setText("Salvar");

        btnAlterarVeiculos.setBackground(new java.awt.Color(10, 4, 138));
        btnAlterarVeiculos.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        btnAlterarVeiculos.setForeground(new java.awt.Color(255, 255, 255));
        btnAlterarVeiculos.setIcon(new javax.swing.ImageIcon("C:\\Users\\SamaraTavares\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\icones\\editar.png")); // NOI18N
        btnAlterarVeiculos.setText("Alterar");

        btnExcluirVeiculos.setBackground(new java.awt.Color(10, 4, 138));
        btnExcluirVeiculos.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        btnExcluirVeiculos.setForeground(new java.awt.Color(255, 255, 255));
        btnExcluirVeiculos.setIcon(new javax.swing.ImageIcon("C:\\Users\\SamaraTavares\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\icones\\excluir.png")); // NOI18N
        btnExcluirVeiculos.setText("Excluir");

        lblStatusVeiculos.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblStatusVeiculos.setText("Status");

        cbStatusVeiculo.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        cbStatusVeiculo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ativo", "Inativo" }));

        tblVeiculos.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        tblVeiculos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Código", "Placa", "Tipo", "Marca", "Modelo", "Ano", "Cor", "Capacidade", "Motorista", "Status"
            }
        ));
        jScrollPane2.setViewportView(tblVeiculos);

        lblObservacoesVeiculos.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblObservacoesVeiculos.setText("Observações");

        txtObservacoesVeiculo.setColumns(20);
        txtObservacoesVeiculo.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        txtObservacoesVeiculo.setRows(5);
        txtObservacoesVeiculo.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jScrollPane21.setViewportView(txtObservacoesVeiculo);

        javax.swing.GroupLayout veiculosLayout = new javax.swing.GroupLayout(veiculos);
        veiculos.setLayout(veiculosLayout);
        veiculosLayout.setHorizontalGroup(
            veiculosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(veiculosLayout.createSequentialGroup()
                .addGroup(veiculosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(veiculosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(txtCapacidadeCarga, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, veiculosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(veiculosLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(txtPlacaVeiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(veiculosLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lblPlaca))
                            .addGroup(veiculosLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(ftfCnh))))
                    .addGroup(veiculosLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnNovoVeiculos)
                        .addGap(18, 18, 18)
                        .addComponent(btnSalvarVeiculos)
                        .addGap(18, 18, 18)
                        .addComponent(btnAlterarVeiculos)
                        .addGap(18, 18, 18)
                        .addComponent(btnExcluirVeiculos)))
                .addGap(0, 1492, Short.MAX_VALUE))
            .addGroup(veiculosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(veiculosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 944, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(veiculosLayout.createSequentialGroup()
                        .addGroup(veiculosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tituloVeiculo)
                            .addComponent(lblModelo)
                            .addComponent(txtModeloVeiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblCarga)
                            .addComponent(lblDocumentacao)
                            .addComponent(lblCnh))
                        .addGap(7, 7, 7)
                        .addGroup(veiculosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblVencimentoCnh)
                            .addComponent(ftfVencimentoCnh, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(veiculosLayout.createSequentialGroup()
                                .addGroup(veiculosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(veiculosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(veiculosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblTipoVeiculo)
                                            .addComponent(txtTipoVeiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(veiculosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblAno)
                                            .addComponent(ftfAnoVeiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(lblMotorista)
                                    .addComponent(cdMotorista, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(31, 31, 31)
                                .addGroup(veiculosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lblStatusVeiculos)
                                    .addComponent(lblMarca)
                                    .addComponent(txtMarcaVeiculo, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                                    .addComponent(lblCor)
                                    .addComponent(txtCorVeiculo)
                                    .addComponent(cbStatusVeiculo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(53, 53, 53)
                                .addGroup(veiculosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblObservacoesVeiculos)
                                    .addComponent(jScrollPane21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(1017, Short.MAX_VALUE))
        );
        veiculosLayout.setVerticalGroup(
            veiculosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(veiculosLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(tituloVeiculo)
                .addGap(18, 18, 18)
                .addGroup(veiculosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPlaca)
                    .addComponent(lblTipoVeiculo)
                    .addComponent(lblMarca)
                    .addComponent(lblObservacoesVeiculos))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(veiculosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(veiculosLayout.createSequentialGroup()
                        .addGroup(veiculosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtPlacaVeiculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTipoVeiculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMarcaVeiculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(veiculosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblModelo)
                            .addComponent(lblAno)
                            .addComponent(lblCor))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(veiculosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtModeloVeiculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ftfAnoVeiculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCorVeiculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(veiculosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblCarga)
                            .addComponent(lblMotorista)
                            .addComponent(lblStatusVeiculos))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(veiculosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCapacidadeCarga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cdMotorista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbStatusVeiculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane21, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addComponent(lblDocumentacao)
                .addGap(18, 18, 18)
                .addGroup(veiculosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCnh)
                    .addComponent(lblVencimentoCnh))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(veiculosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ftfCnh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ftfVencimentoCnh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(veiculosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAlterarVeiculos)
                    .addComponent(btnNovoVeiculos)
                    .addComponent(btnExcluirVeiculos)
                    .addComponent(btnSalvarVeiculos))
                .addGap(39, 39, 39)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(240, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Veículos", veiculos);

        regiao.setBackground(new java.awt.Color(236, 236, 236));

        tituloRegiao.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        tituloRegiao.setForeground(new java.awt.Color(10, 4, 138));
        tituloRegiao.setText("Cadastro de Regiões");

        lblNomeRegiao.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblNomeRegiao.setText("Nome da Região");

        txtNomeRegiao.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        txtNomeRegiao.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblTipoRegiao.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblTipoRegiao.setText("Tipo de Região");

        jComboBox1.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione o tipo", "Rural", "Urbano", " " }));
        jComboBox1.addActionListener(this::jComboBox1ActionPerformed);

        lblCodigoRegiao.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblCodigoRegiao.setText("Código da Região");

        lblValorTaxaRegiao.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblValorTaxaRegiao.setText("Valor da Taxa de Entrega");

        jComboBox2.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione o Estado", "AC - Acre", "AL - Alagoas", "AM - Amazonas", "AP - Amapá", "BA - Bahia", "CE - Ceará", "DF - Distrito Federal", "ES - Espírito Santo", "GO - Goiás", "MA - Maranhão", "MG - Minas Gerais", "MS - Mato Grosso do Sul", "MT - Mato Grosso", "PA - Pará", "PB - Paraíba", "PE - Pernambuco", "PI - Piauí", "PR - Paraná", "RJ - Rio de Janeiro", "RN - Rio Grande do Norte", "RO - Rondônia", "RR - Roraima", "RS - Rio Grande do Sul", "SC - Santa Catarina", "SE - Sergipe", "SP - São Paulo", "TO - Tocantins" }));

        txtValorTaxaRegiao.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        txtValorTaxaRegiao.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblBairroRegiao.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblBairroRegiao.setText("Bairro(s) Atendido(s)");

        txtaBairroRegiao.setColumns(20);
        txtaBairroRegiao.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        txtaBairroRegiao.setRows(5);
        txtaBairroRegiao.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jScrollPane3.setViewportView(txtaBairroRegiao);

        tblRegioes.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        tblRegioes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Código", "Nome da Região", "Bairro(s) Atendido(s)", "Tipo", "Valor da Taxa", "Observações"
            }
        ));
        jScrollPane4.setViewportView(tblRegioes);

        lblObservacoesRegioes.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblObservacoesRegioes.setText("Observações");

        txtaObservacoesRegiao.setColumns(20);
        txtaObservacoesRegiao.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        txtaObservacoesRegiao.setRows(5);
        txtaObservacoesRegiao.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jScrollPane5.setViewportView(txtaObservacoesRegiao);

        btnNovoRegioes.setBackground(new java.awt.Color(10, 4, 138));
        btnNovoRegioes.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        btnNovoRegioes.setForeground(new java.awt.Color(255, 255, 255));
        btnNovoRegioes.setIcon(new javax.swing.ImageIcon("C:\\Users\\SamaraTavares\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\icones\\botao-mais.png")); // NOI18N
        btnNovoRegioes.setText("Novo");

        btnSalvarRegioes.setBackground(new java.awt.Color(10, 4, 138));
        btnSalvarRegioes.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        btnSalvarRegioes.setForeground(new java.awt.Color(255, 255, 255));
        btnSalvarRegioes.setIcon(new javax.swing.ImageIcon("C:\\Users\\SamaraTavares\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\icones\\Salvar.png")); // NOI18N
        btnSalvarRegioes.setText("Salvar");

        btnAlterarRegioes.setBackground(new java.awt.Color(10, 4, 138));
        btnAlterarRegioes.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        btnAlterarRegioes.setForeground(new java.awt.Color(255, 255, 255));
        btnAlterarRegioes.setIcon(new javax.swing.ImageIcon("C:\\Users\\SamaraTavares\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\icones\\editar.png")); // NOI18N
        btnAlterarRegioes.setText("Alterar");

        btnExcluirRegioes.setBackground(new java.awt.Color(10, 4, 138));
        btnExcluirRegioes.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        btnExcluirRegioes.setForeground(new java.awt.Color(255, 255, 255));
        btnExcluirRegioes.setIcon(new javax.swing.ImageIcon("C:\\Users\\SamaraTavares\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\icones\\excluir.png")); // NOI18N
        btnExcluirRegioes.setText("Excluir");

        javax.swing.GroupLayout regiaoLayout = new javax.swing.GroupLayout(regiao);
        regiao.setLayout(regiaoLayout);
        regiaoLayout.setHorizontalGroup(
            regiaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(regiaoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(regiaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 921, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(regiaoLayout.createSequentialGroup()
                        .addGroup(regiaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tituloRegiao)
                            .addGroup(regiaoLayout.createSequentialGroup()
                                .addGroup(regiaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jComboBox2, javax.swing.GroupLayout.Alignment.LEADING, 0, 216, Short.MAX_VALUE)
                                    .addComponent(lblNomeRegiao, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtNomeRegiao, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblCodigoRegiao, javax.swing.GroupLayout.Alignment.LEADING))
                                .addGap(46, 46, 46)
                                .addGroup(regiaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lblValorTaxaRegiao)
                                    .addComponent(lblTipoRegiao)
                                    .addComponent(jComboBox1, 0, 187, Short.MAX_VALUE)
                                    .addComponent(txtValorTaxaRegiao)))
                            .addComponent(lblBairroRegiao)
                            .addComponent(jScrollPane3))
                        .addGap(56, 56, 56)
                        .addGroup(regiaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblObservacoesRegioes)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(regiaoLayout.createSequentialGroup()
                        .addComponent(btnNovoRegioes)
                        .addGap(18, 18, 18)
                        .addComponent(btnSalvarRegioes)
                        .addGap(18, 18, 18)
                        .addComponent(btnAlterarRegioes)
                        .addGap(18, 18, 18)
                        .addComponent(btnExcluirRegioes)))
                .addContainerGap(1083, Short.MAX_VALUE))
        );
        regiaoLayout.setVerticalGroup(
            regiaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(regiaoLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(tituloRegiao)
                .addGap(18, 18, 18)
                .addGroup(regiaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNomeRegiao)
                    .addComponent(lblTipoRegiao)
                    .addComponent(lblObservacoesRegioes))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(regiaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(regiaoLayout.createSequentialGroup()
                        .addGroup(regiaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNomeRegiao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(regiaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblCodigoRegiao)
                            .addComponent(lblValorTaxaRegiao))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(regiaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtValorTaxaRegiao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addComponent(lblBairroRegiao)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(regiaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNovoRegioes)
                    .addComponent(btnSalvarRegioes)
                    .addComponent(btnAlterarRegioes)
                    .addComponent(btnExcluirRegioes))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 402, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(217, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Regiões", regiao);

        taxas.setBackground(new java.awt.Color(236, 236, 236));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(10, 4, 138));
        jLabel1.setText("Cadastro de Taxas");

        lblRegiaoTaxas.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblRegiaoTaxas.setText("Região");

        cbRegiaoTaxas.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        cbRegiaoTaxas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione a Região", "Região Norte", "Região Nordeste", "Região Centro-Oeste", "Região Sudeste", "Região Sul" }));

        lblTipoTaxa.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblTipoTaxa.setText("Tipo de Taxa");

        cbTipoTaxa.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        cbTipoTaxa.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione o Tipo", "Fixa", "Percentual" }));

        lblValorTaxas.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblValorTaxas.setText("Valor da Taxa");

        txtValorTaxa.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        txtValorTaxa.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        txtValorTaxa.addActionListener(this::txtValorTaxaActionPerformed);

        lblObservacoesTaxas.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblObservacoesTaxas.setText("Observações");

        txtObservacaoTaxa.setColumns(20);
        txtObservacaoTaxa.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        txtObservacaoTaxa.setRows(5);
        txtObservacaoTaxa.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jScrollPane6.setViewportView(txtObservacaoTaxa);

        btnNovoTaxas.setBackground(new java.awt.Color(10, 4, 138));
        btnNovoTaxas.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        btnNovoTaxas.setForeground(new java.awt.Color(255, 255, 255));
        btnNovoTaxas.setIcon(new javax.swing.ImageIcon("C:\\Users\\SamaraTavares\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\icones\\botao-mais.png")); // NOI18N
        btnNovoTaxas.setText("Novo");

        btnSalvarTaxas.setBackground(new java.awt.Color(10, 4, 138));
        btnSalvarTaxas.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        btnSalvarTaxas.setForeground(new java.awt.Color(255, 255, 255));
        btnSalvarTaxas.setIcon(new javax.swing.ImageIcon("C:\\Users\\SamaraTavares\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\icones\\Salvar.png")); // NOI18N
        btnSalvarTaxas.setText("Salvar");

        btnAlterarTaxas.setBackground(new java.awt.Color(10, 4, 138));
        btnAlterarTaxas.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        btnAlterarTaxas.setForeground(new java.awt.Color(255, 255, 255));
        btnAlterarTaxas.setIcon(new javax.swing.ImageIcon("C:\\Users\\SamaraTavares\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\icones\\editar.png")); // NOI18N
        btnAlterarTaxas.setText("Alterar");

        btnExcluirTaxas.setBackground(new java.awt.Color(10, 4, 138));
        btnExcluirTaxas.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        btnExcluirTaxas.setForeground(new java.awt.Color(255, 255, 255));
        btnExcluirTaxas.setIcon(new javax.swing.ImageIcon("C:\\Users\\SamaraTavares\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\icones\\excluir.png")); // NOI18N
        btnExcluirTaxas.setText("Excluir");

        tblListaTaxas.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        tblListaTaxas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Código", "Região", "Faixa do Pedido", "Tipo", "Valor da Taxa ", " Valor Mínimo ", "Observações"
            }
        ));
        jScrollPane7.setViewportView(tblListaTaxas);

        javax.swing.GroupLayout taxasLayout = new javax.swing.GroupLayout(taxas);
        taxas.setLayout(taxasLayout);
        taxasLayout.setHorizontalGroup(
            taxasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(taxasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(taxasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 389, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addGroup(taxasLayout.createSequentialGroup()
                        .addGroup(taxasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblValorTaxas)
                            .addComponent(txtValorTaxa, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(39, 39, 39)
                        .addGroup(taxasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblRegiaoTaxas)
                            .addComponent(cbRegiaoTaxas, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(40, 40, 40)
                        .addGroup(taxasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTipoTaxa)
                            .addComponent(cbTipoTaxa, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(lblObservacoesTaxas)
                    .addGroup(taxasLayout.createSequentialGroup()
                        .addComponent(btnNovoTaxas)
                        .addGap(18, 18, 18)
                        .addComponent(btnSalvarTaxas)
                        .addGap(18, 18, 18)
                        .addComponent(btnAlterarTaxas)
                        .addGap(18, 18, 18)
                        .addComponent(btnExcluirTaxas))
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 865, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(1139, Short.MAX_VALUE))
        );
        taxasLayout.setVerticalGroup(
            taxasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(taxasLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(taxasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblRegiaoTaxas)
                    .addComponent(lblTipoTaxa)
                    .addComponent(lblValorTaxas))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(taxasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbRegiaoTaxas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbTipoTaxa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtValorTaxa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(60, 60, 60)
                .addComponent(lblObservacoesTaxas)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addGroup(taxasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNovoTaxas)
                    .addComponent(btnSalvarTaxas)
                    .addComponent(btnAlterarTaxas)
                    .addComponent(btnExcluirTaxas))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(249, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Taxas", taxas);

        endereço.setBackground(new java.awt.Color(236, 236, 236));

        lblTituloEntrega.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        lblTituloEntrega.setForeground(new java.awt.Color(10, 4, 138));
        lblTituloEntrega.setText("Endereços de Entrega");

        lblClienteEnderecos.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblClienteEnderecos.setText("Cliente");

        txtCidadeEnderecos.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        txtCidadeEnderecos.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        txtClienteEnderecos.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        txtClienteEnderecos.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblCepEnderecos.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblCepEnderecos.setText("CEP");

        txtCepEnderecos.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        txtCepEnderecos.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblEstadoEnderecos.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblEstadoEnderecos.setText("Estado");

        cbEstadoEnderecos.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        cbEstadoEnderecos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione o Estado", "* AC", "* AL", "* AM", "* AP", "* BA", "* CE", "* DF", "* ES", "* GO", "* MA", "* MG", "* MS", "* MT", "* PA", "* PB", "* PE", "* PI", "* PR", "* RJ", "* RN", "* RO", "* RR", "* RS", "* SC", "* SE", "* SP", "* TO" }));
        cbEstadoEnderecos.addActionListener(this::cbEstadoEnderecosActionPerformed);

        btnPesquisarEnderecos.setBackground(new java.awt.Color(10, 4, 138));
        btnPesquisarEnderecos.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        btnPesquisarEnderecos.setForeground(new java.awt.Color(255, 255, 255));
        btnPesquisarEnderecos.setIcon(new javax.swing.ImageIcon("C:\\Users\\SamaraTavares\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\icones\\lupa.png")); // NOI18N
        btnPesquisarEnderecos.setText("Pesquisar");

        tblListaEnderecos.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        tblListaEnderecos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Pedido", "Cliente", "Endereco", "Número", "Bairro", "Região", "CEP", "Status"
            }
        ));
        jScrollPane8.setViewportView(tblListaEnderecos);

        btnNovoEnderecos.setBackground(new java.awt.Color(10, 4, 138));
        btnNovoEnderecos.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        btnNovoEnderecos.setForeground(new java.awt.Color(255, 255, 255));
        btnNovoEnderecos.setIcon(new javax.swing.ImageIcon("C:\\Users\\SamaraTavares\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\icones\\botao-mais.png")); // NOI18N
        btnNovoEnderecos.setText("Novo");

        btnAlterarEndercos.setBackground(new java.awt.Color(10, 4, 138));
        btnAlterarEndercos.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        btnAlterarEndercos.setForeground(new java.awt.Color(255, 255, 255));
        btnAlterarEndercos.setIcon(new javax.swing.ImageIcon("C:\\Users\\SamaraTavares\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\icones\\editar.png")); // NOI18N
        btnAlterarEndercos.setText("Alterar");

        btnExcluirEnderecos.setBackground(new java.awt.Color(10, 4, 138));
        btnExcluirEnderecos.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        btnExcluirEnderecos.setForeground(new java.awt.Color(255, 255, 255));
        btnExcluirEnderecos.setIcon(new javax.swing.ImageIcon("C:\\Users\\SamaraTavares\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\icones\\excluir.png")); // NOI18N
        btnExcluirEnderecos.setText("Excluir");

        jLabel3.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        jLabel3.setText("Cidade");

        lblRuaEnderecos.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblRuaEnderecos.setText("Rua");
        lblRuaEnderecos.setToolTipText("");

        txtRuaEnderecos.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        txtRuaEnderecos.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblBairroEnderecos.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblBairroEnderecos.setText("Bairro");

        lblNumeroEnderecos.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblNumeroEnderecos.setText("Nº");
        lblNumeroEnderecos.setToolTipText("");

        txtBairroEnderecos.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        txtBairroEnderecos.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        txtNumeroEnderecos.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        txtNumeroEnderecos.setToolTipText("");

        btnSalvarEnderecos.setBackground(new java.awt.Color(10, 4, 138));
        btnSalvarEnderecos.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        btnSalvarEnderecos.setForeground(new java.awt.Color(255, 255, 255));
        btnSalvarEnderecos.setIcon(new javax.swing.ImageIcon("C:\\Users\\SamaraTavares\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\icones\\Salvar.png")); // NOI18N
        btnSalvarEnderecos.setText("Salvar");

        javax.swing.GroupLayout endereçoLayout = new javax.swing.GroupLayout(endereço);
        endereço.setLayout(endereçoLayout);
        endereçoLayout.setHorizontalGroup(
            endereçoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(endereçoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(endereçoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(endereçoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(lblTituloEntrega)
                        .addComponent(jScrollPane8)
                        .addGroup(endereçoLayout.createSequentialGroup()
                            .addGroup(endereçoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(endereçoLayout.createSequentialGroup()
                                    .addGroup(endereçoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtCidadeEnderecos, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel3))
                                    .addGap(18, 18, 18)
                                    .addGroup(endereçoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtClienteEnderecos, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblClienteEnderecos)
                                        .addComponent(lblBairroEnderecos)))
                                .addComponent(lblRuaEnderecos)
                                .addGroup(endereçoLayout.createSequentialGroup()
                                    .addComponent(txtRuaEnderecos, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(txtBairroEnderecos)))
                            .addGap(18, 18, 18)
                            .addGroup(endereçoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblNumeroEnderecos)
                                .addGroup(endereçoLayout.createSequentialGroup()
                                    .addGroup(endereçoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(txtNumeroEnderecos, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtCepEnderecos, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
                                        .addComponent(lblCepEnderecos, javax.swing.GroupLayout.Alignment.LEADING))
                                    .addGap(18, 18, 18)
                                    .addGroup(endereçoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lblEstadoEnderecos)
                                        .addGroup(endereçoLayout.createSequentialGroup()
                                            .addComponent(cbEstadoEnderecos, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(btnPesquisarEnderecos)))))))
                    .addGroup(endereçoLayout.createSequentialGroup()
                        .addComponent(btnNovoEnderecos)
                        .addGap(18, 18, 18)
                        .addComponent(btnAlterarEndercos)
                        .addGap(18, 18, 18)
                        .addComponent(btnExcluirEnderecos)
                        .addGap(18, 18, 18)
                        .addComponent(btnSalvarEnderecos)))
                .addContainerGap(945, Short.MAX_VALUE))
        );
        endereçoLayout.setVerticalGroup(
            endereçoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(endereçoLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(lblTituloEntrega)
                .addGap(18, 18, 18)
                .addGroup(endereçoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblEstadoEnderecos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(endereçoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblClienteEnderecos)
                        .addComponent(lblCepEnderecos)
                        .addComponent(jLabel3)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(endereçoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCidadeEnderecos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtClienteEnderecos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCepEnderecos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbEstadoEnderecos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPesquisarEnderecos))
                .addGap(18, 18, 18)
                .addGroup(endereçoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblRuaEnderecos)
                    .addComponent(lblBairroEnderecos)
                    .addComponent(lblNumeroEnderecos))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(endereçoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtRuaEnderecos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBairroEnderecos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNumeroEnderecos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(59, 59, 59)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(endereçoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNovoEnderecos)
                    .addComponent(btnAlterarEndercos)
                    .addComponent(btnExcluirEnderecos)
                    .addComponent(btnSalvarEnderecos))
                .addContainerGap(325, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Endereços", endereço);

        entregas.setBackground(new java.awt.Color(236, 236, 236));

        lbltituloEntregas.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        lbltituloEntregas.setForeground(new java.awt.Color(10, 4, 138));
        lbltituloEntregas.setText("Consulta de Entregas");

        lblDataEntregas.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblDataEntregas.setText("Data da Entrega");

        ftfDataEntrega.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        try {
            ftfDataEntrega.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        ftfDataEntrega.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N

        lblTaxaEntregas.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblTaxaEntregas.setText("Taxa de Entrega");

        txtTaxaEntrega.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        txtTaxaEntrega.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblClienteEntregas.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblClienteEntregas.setText("Cliente");

        txtClienteEntregas.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        txtClienteEntregas.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblEntregadorEntregas.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblEntregadorEntregas.setText("Entregador");

        cbEntregadorEntregas.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N

        lblStatusEntregas.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblStatusEntregas.setText("Status");

        cbStatusEntrega.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        cbStatusEntrega.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione o status", "Entregue", "Concluído", "Em Separação", "Saiu para entrega", "Enviado" }));

        lblVeiculoEntregas.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblVeiculoEntregas.setText("Veículo");

        cbVeiculoEntregas.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        cbVeiculoEntregas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione o veículo", " " }));

        lblHoraEntregas.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblHoraEntregas.setText("Hora da Entrega");

        ftfHorarioEntrega.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        try {
            ftfHorarioEntrega.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##:##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        ftfHorarioEntrega.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N

        btnNovoEntregas.setBackground(new java.awt.Color(10, 4, 138));
        btnNovoEntregas.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        btnNovoEntregas.setForeground(new java.awt.Color(255, 255, 255));
        btnNovoEntregas.setIcon(new javax.swing.ImageIcon("C:\\Users\\SamaraTavares\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\icones\\botao-mais.png")); // NOI18N
        btnNovoEntregas.setText("Novo");

        btnSalvarEntregas.setBackground(new java.awt.Color(10, 4, 138));
        btnSalvarEntregas.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        btnSalvarEntregas.setForeground(new java.awt.Color(255, 255, 255));
        btnSalvarEntregas.setIcon(new javax.swing.ImageIcon("C:\\Users\\SamaraTavares\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\icones\\Salvar.png")); // NOI18N
        btnSalvarEntregas.setText("Salvar");

        btnAlterarEntregas.setBackground(new java.awt.Color(10, 4, 138));
        btnAlterarEntregas.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        btnAlterarEntregas.setForeground(new java.awt.Color(255, 255, 255));
        btnAlterarEntregas.setIcon(new javax.swing.ImageIcon("C:\\Users\\SamaraTavares\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\icones\\editar.png")); // NOI18N
        btnAlterarEntregas.setText("Alterar");

        btnExcluirEntregas.setBackground(new java.awt.Color(10, 4, 138));
        btnExcluirEntregas.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        btnExcluirEntregas.setForeground(new java.awt.Color(255, 255, 255));
        btnExcluirEntregas.setIcon(new javax.swing.ImageIcon("C:\\Users\\SamaraTavares\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\icones\\excluir.png")); // NOI18N
        btnExcluirEntregas.setText("Excluir");

        btnPesquisarEntregas.setBackground(new java.awt.Color(10, 4, 138));
        btnPesquisarEntregas.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        btnPesquisarEntregas.setForeground(new java.awt.Color(255, 255, 255));
        btnPesquisarEntregas.setIcon(new javax.swing.ImageIcon("C:\\Users\\SamaraTavares\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\icones\\lupa.png")); // NOI18N
        btnPesquisarEntregas.setText("Pesquisar");

        btnVoltarEntregas.setBackground(new java.awt.Color(10, 4, 138));
        btnVoltarEntregas.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        btnVoltarEntregas.setForeground(new java.awt.Color(255, 255, 255));
        btnVoltarEntregas.setIcon(new javax.swing.ImageIcon("C:\\Users\\SamaraTavares\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\icones\\seta-esquerda (1).png")); // NOI18N
        btnVoltarEntregas.setText("Voltar");

        tblEntregas.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        tblEntregas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Data da Entrega", "Cliente", "Entregador", "Veículo", "Região", "Taxa ", "Status"
            }
        ));
        jScrollPane11.setViewportView(tblEntregas);

        javax.swing.GroupLayout entregasLayout = new javax.swing.GroupLayout(entregas);
        entregas.setLayout(entregasLayout);
        entregasLayout.setHorizontalGroup(
            entregasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(entregasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(entregasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(entregasLayout.createSequentialGroup()
                        .addGroup(entregasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(entregasLayout.createSequentialGroup()
                                .addComponent(ftfDataEntrega, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(35, 35, 35)
                                .addComponent(txtClienteEntregas, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(entregasLayout.createSequentialGroup()
                                .addComponent(lblDataEntregas)
                                .addGap(82, 82, 82)
                                .addComponent(lblClienteEntregas))
                            .addGroup(entregasLayout.createSequentialGroup()
                                .addGroup(entregasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblEntregadorEntregas)
                                    .addComponent(cbEntregadorEntregas, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(51, 51, 51)
                                .addGroup(entregasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbVeiculoEntregas, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblVeiculoEntregas)))
                            .addComponent(lblTaxaEntregas)
                            .addComponent(txtTaxaEntrega, javax.swing.GroupLayout.PREFERRED_SIZE, 423, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24)
                        .addGroup(entregasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblStatusEntregas)
                            .addComponent(cbStatusEntrega, 0, 172, Short.MAX_VALUE)
                            .addComponent(lblHoraEntregas)
                            .addComponent(ftfHorarioEntrega)))
                    .addComponent(lbltituloEntregas)
                    .addGroup(entregasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(entregasLayout.createSequentialGroup()
                            .addComponent(btnNovoEntregas)
                            .addGap(18, 18, 18)
                            .addComponent(btnSalvarEntregas)
                            .addGap(18, 18, 18)
                            .addComponent(btnAlterarEntregas)
                            .addGap(18, 18, 18)
                            .addComponent(btnExcluirEntregas)
                            .addGap(18, 18, 18)
                            .addComponent(btnPesquisarEntregas)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnVoltarEntregas, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 951, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(1053, Short.MAX_VALUE))
        );
        entregasLayout.setVerticalGroup(
            entregasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(entregasLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(lbltituloEntregas)
                .addGap(18, 18, 18)
                .addGroup(entregasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDataEntregas)
                    .addComponent(lblClienteEntregas)
                    .addComponent(lblStatusEntregas))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(entregasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ftfDataEntrega, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtClienteEntregas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbStatusEntrega, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(entregasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEntregadorEntregas)
                    .addComponent(lblVeiculoEntregas)
                    .addComponent(lblHoraEntregas))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(entregasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbEntregadorEntregas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbVeiculoEntregas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ftfHorarioEntrega, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(lblTaxaEntregas)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTaxaEntrega, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 97, Short.MAX_VALUE)
                .addGroup(entregasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNovoEntregas)
                    .addComponent(btnSalvarEntregas)
                    .addComponent(btnAlterarEntregas)
                    .addComponent(btnExcluirEntregas)
                    .addComponent(btnPesquisarEntregas)
                    .addComponent(btnVoltarEntregas))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 411, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(224, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Entregas", entregas);

        dataHorario.setBackground(new java.awt.Color(236, 236, 236));

        lbltituloDataHorario.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        lbltituloDataHorario.setForeground(new java.awt.Color(10, 4, 138));
        lbltituloDataHorario.setText("Filtros de Consulta");

        lblDataInicial.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblDataInicial.setText("Data Inicial");

        ftfDataInicial.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        try {
            ftfDataInicial.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        lblDataFinal.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblDataFinal.setText("Data Final");

        ftfDataFinal.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        try {
            ftfDataFinal.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        lblHorarioInicial.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblHorarioInicial.setText("Horário Inicial");

        ftfHorarioInicial.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        try {
            ftfHorarioInicial.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##:##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        ftfHorarioInicial.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N

        lblHorarioFinal.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblHorarioFinal.setText("Horário Final");

        txtHorarioFinal.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        try {
            txtHorarioFinal.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##:##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtHorarioFinal.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N

        lblEntregadorDataHorario.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblEntregadorDataHorario.setText("Entregador");

        cbEntregadorDataHorario.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        cbEntregadorDataHorario.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos", " " }));

        lblStatusDataHorario.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblStatusDataHorario.setText("Status");

        cbStatusDataHorario.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        cbStatusDataHorario.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos", " " }));

        btnPesquisarDataHorario.setBackground(new java.awt.Color(10, 4, 138));
        btnPesquisarDataHorario.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        btnPesquisarDataHorario.setForeground(new java.awt.Color(255, 255, 255));
        btnPesquisarDataHorario.setIcon(new javax.swing.ImageIcon("C:\\Users\\SamaraTavares\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\icones\\lupa.png")); // NOI18N
        btnPesquisarDataHorario.setText("Pesquisar");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 179, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        lblEntregasDiaDH.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        lblEntregasDiaDH.setForeground(new java.awt.Color(39, 34, 160));
        lblEntregasDiaDH.setText("Entregas no dia");

        lblNumeroEntregasDiaDH.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblNumeroEntregasDiaDH.setText("Nº");

        lblIconeEntregasDia.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblIconeEntregasDia.setIcon(new javax.swing.ImageIcon("C:\\Users\\SamaraTavares\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\icones\\pendente (2).png")); // NOI18N

        lblTotalEntregasDH1.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        lblTotalEntregasDH1.setForeground(new java.awt.Color(39, 34, 160));
        lblTotalEntregasDH1.setText("Total de entregas agendadas");

        lblNumeroTotalDH1.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblNumeroTotalDH1.setText("Nº");

        lblIconeTotalEntregas1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblIconeTotalEntregas1.setIcon(new javax.swing.ImageIcon("C:\\Users\\SamaraTavares\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\icones\\fast-delivery.png")); // NOI18N

        lblAndamento.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        lblAndamento.setForeground(new java.awt.Color(39, 34, 160));
        lblAndamento.setText("Em andamento");

        lblIconeAndamento.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblIconeAndamento.setIcon(new javax.swing.ImageIcon("C:\\Users\\SamaraTavares\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\icones\\entrega.png")); // NOI18N

        lnlNumeroAndamento.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lnlNumeroAndamento.setText("Nº");

        lblIconeConcluidas.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblIconeConcluidas.setIcon(new javax.swing.ImageIcon("C:\\Users\\SamaraTavares\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\icones\\concluido.png")); // NOI18N

        lblConcluidas.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        lblConcluidas.setForeground(new java.awt.Color(39, 34, 160));
        lblConcluidas.setText("Concluídas");

        lblNumeroConcluidas.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblNumeroConcluidas.setText("Nº");

        tblAgendaEntregas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Código ", "Pedido", "Cliente", "Endereço", "Entregador", "Veículo", "Data", "Horário", "Status"
            }
        ));
        jScrollPane12.setViewportView(tblAgendaEntregas);

        btnNovoDataHorario.setBackground(new java.awt.Color(10, 4, 138));
        btnNovoDataHorario.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        btnNovoDataHorario.setForeground(new java.awt.Color(255, 255, 255));
        btnNovoDataHorario.setIcon(new javax.swing.ImageIcon("C:\\Users\\SamaraTavares\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\icones\\botao-mais.png")); // NOI18N
        btnNovoDataHorario.setText("Novo");
        btnNovoDataHorario.setIconTextGap(6);

        btnAlterarDataHorario.setBackground(new java.awt.Color(10, 4, 138));
        btnAlterarDataHorario.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        btnAlterarDataHorario.setForeground(new java.awt.Color(255, 255, 255));
        btnAlterarDataHorario.setIcon(new javax.swing.ImageIcon("C:\\Users\\SamaraTavares\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\icones\\editar.png")); // NOI18N
        btnAlterarDataHorario.setText("Alterar");

        btnExcluirDataHorario.setBackground(new java.awt.Color(10, 4, 138));
        btnExcluirDataHorario.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        btnExcluirDataHorario.setForeground(new java.awt.Color(255, 255, 255));
        btnExcluirDataHorario.setIcon(new javax.swing.ImageIcon("C:\\Users\\SamaraTavares\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\icones\\excluir.png")); // NOI18N
        btnExcluirDataHorario.setText("Excluir");

        btnVoltarDataHorario.setBackground(new java.awt.Color(10, 4, 138));
        btnVoltarDataHorario.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        btnVoltarDataHorario.setForeground(new java.awt.Color(255, 255, 255));
        btnVoltarDataHorario.setIcon(new javax.swing.ImageIcon("C:\\Users\\SamaraTavares\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\icones\\seta-esquerda (1).png")); // NOI18N
        btnVoltarDataHorario.setText("Voltar");

        javax.swing.GroupLayout dataHorarioLayout = new javax.swing.GroupLayout(dataHorario);
        dataHorario.setLayout(dataHorarioLayout);
        dataHorarioLayout.setHorizontalGroup(
            dataHorarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dataHorarioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(dataHorarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbltituloDataHorario)
                    .addGroup(dataHorarioLayout.createSequentialGroup()
                        .addGroup(dataHorarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, dataHorarioLayout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(lblIconeTotalEntregas1)
                                .addGroup(dataHorarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(dataHorarioLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblTotalEntregasDH1))
                                    .addGroup(dataHorarioLayout.createSequentialGroup()
                                        .addGap(34, 34, 34)
                                        .addComponent(lblNumeroTotalDH1)))
                                .addGap(21, 21, 21)
                                .addComponent(lblIconeEntregasDia)
                                .addGroup(dataHorarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(dataHorarioLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblEntregasDiaDH))
                                    .addGroup(dataHorarioLayout.createSequentialGroup()
                                        .addGap(24, 24, 24)
                                        .addComponent(lblNumeroEntregasDiaDH))))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, dataHorarioLayout.createSequentialGroup()
                                .addGroup(dataHorarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblDataInicial)
                                    .addComponent(ftfDataInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(22, 22, 22)
                                .addGroup(dataHorarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblDataFinal)
                                    .addComponent(ftfDataFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(25, 25, 25)
                                .addGroup(dataHorarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblHorarioInicial)
                                    .addComponent(ftfHorarioInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(42, 42, 42)
                                .addGroup(dataHorarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtHorarioFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblHorarioFinal))))
                        .addGroup(dataHorarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(dataHorarioLayout.createSequentialGroup()
                                .addGap(497, 497, 497)
                                .addComponent(btnVoltarDataHorario, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(dataHorarioLayout.createSequentialGroup()
                                .addGap(33, 33, 33)
                                .addComponent(lblIconeAndamento)
                                .addGroup(dataHorarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(dataHorarioLayout.createSequentialGroup()
                                        .addGap(35, 35, 35)
                                        .addGroup(dataHorarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblStatusDataHorario)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dataHorarioLayout.createSequentialGroup()
                                                .addComponent(cbStatusDataHorario, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(117, 117, 117)
                                                .addComponent(btnPesquisarDataHorario)
                                                .addGap(47, 47, 47))))
                                    .addGroup(dataHorarioLayout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(lblAndamento))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dataHorarioLayout.createSequentialGroup()
                                        .addGap(37, 37, 37)
                                        .addComponent(lnlNumeroAndamento)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lblIconeConcluidas)
                                        .addGroup(dataHorarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(dataHorarioLayout.createSequentialGroup()
                                                .addGap(14, 14, 14)
                                                .addComponent(lblConcluidas))
                                            .addGroup(dataHorarioLayout.createSequentialGroup()
                                                .addGap(26, 26, 26)
                                                .addComponent(lblNumeroConcluidas)))
                                        .addGap(142, 142, 142))))))
                    .addGroup(dataHorarioLayout.createSequentialGroup()
                        .addGap(503, 503, 503)
                        .addGroup(dataHorarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(dataHorarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(lblEntregadorDataHorario)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(dataHorarioLayout.createSequentialGroup()
                                .addGap(108, 108, 108)
                                .addComponent(cbEntregadorDataHorario, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 1331, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(dataHorarioLayout.createSequentialGroup()
                        .addComponent(btnNovoDataHorario)
                        .addGap(18, 18, 18)
                        .addComponent(btnAlterarDataHorario)
                        .addGap(18, 18, 18)
                        .addComponent(btnExcluirDataHorario)))
                .addContainerGap(673, Short.MAX_VALUE))
        );
        dataHorarioLayout.setVerticalGroup(
            dataHorarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dataHorarioLayout.createSequentialGroup()
                .addGroup(dataHorarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(dataHorarioLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(lbltituloDataHorario)
                        .addGap(18, 18, 18)
                        .addGroup(dataHorarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblDataInicial)
                            .addComponent(lblDataFinal)
                            .addComponent(lblHorarioInicial)
                            .addComponent(lblHorarioFinal)
                            .addComponent(lblEntregadorDataHorario)
                            .addComponent(lblStatusDataHorario))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(dataHorarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ftfDataInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ftfDataFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ftfHorarioInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtHorarioFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbEntregadorDataHorario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbStatusDataHorario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnPesquisarDataHorario))
                        .addGap(26, 26, 26)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(21, 21, 21)
                        .addGroup(dataHorarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblIconeEntregasDia)
                            .addGroup(dataHorarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblTotalEntregasDH1)
                                .addComponent(lblIconeTotalEntregas1)
                                .addComponent(lblEntregasDiaDH))
                            .addComponent(lblIconeAndamento)
                            .addGroup(dataHorarioLayout.createSequentialGroup()
                                .addComponent(lblAndamento)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lnlNumeroAndamento, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblIconeConcluidas)
                            .addGroup(dataHorarioLayout.createSequentialGroup()
                                .addComponent(lblConcluidas)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblNumeroConcluidas))))
                    .addGroup(dataHorarioLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(dataHorarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblNumeroEntregasDiaDH)
                            .addComponent(lblNumeroTotalDH1))))
                .addGap(48, 48, 48)
                .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 438, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(dataHorarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(dataHorarioLayout.createSequentialGroup()
                        .addGap(136, 136, 136)
                        .addGroup(dataHorarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnNovoDataHorario)
                            .addComponent(btnAlterarDataHorario)
                            .addComponent(btnExcluirDataHorario)))
                    .addGroup(dataHorarioLayout.createSequentialGroup()
                        .addGap(150, 150, 150)
                        .addComponent(btnVoltarDataHorario)))
                .addGap(394, 394, 394))
        );

        jTabbedPane1.addTab("Data e Horário", dataHorario);

        agenda.setBackground(new java.awt.Color(236, 236, 236));

        lblTituloAgenda.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        lblTituloAgenda.setForeground(new java.awt.Color(10, 4, 138));
        lblTituloAgenda.setText("Agenda de Entregas");

        lblDataAgenda.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblDataAgenda.setText("Data");

        ftfDataAgenda.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        try {
            ftfDataAgenda.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        ftfDataAgenda.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N

        lblEntregadorAgenda.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblEntregadorAgenda.setText("Entregador");

        cbEntregadorAgenda.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        cbEntregadorAgenda.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione o entregador", " " }));

        lblVeiculoAgenda.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblVeiculoAgenda.setText("Veículo");

        cbVeiculoAgenda.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        cbVeiculoAgenda.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione o veículo", " " }));

        lblRegiãoAgenda.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblRegiãoAgenda.setText("Região");

        cbRegiaoAgenda.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        cbRegiaoAgenda.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione a região", "Região Norte", "Região Nordeste", "Região Centro-Oeste", "Região Sudeste", "Região Sul" }));

        btnPesquisarAgenda.setBackground(new java.awt.Color(10, 4, 138));
        btnPesquisarAgenda.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        btnPesquisarAgenda.setForeground(new java.awt.Color(255, 255, 255));
        btnPesquisarAgenda.setIcon(new javax.swing.ImageIcon("C:\\Users\\SamaraTavares\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\icones\\lupa.png")); // NOI18N
        btnPesquisarAgenda.setText("Pesquisar");

        lblTotalEntregasAgenda.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        lblTotalEntregasAgenda.setText("Total de Entregas");

        lblIconeEntregasAgenda.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblIconeEntregasAgenda.setIcon(new javax.swing.ImageIcon("C:\\Users\\SamaraTavares\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\icones\\fast-delivery.png")); // NOI18N

        lblNumeroEntregasAgenda.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblNumeroEntregasAgenda.setText("Nº");

        lblIconeAndamentoAgenda.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        lblAndamentoAgenda.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        lblAndamentoAgenda.setText("Em andamento");

        lblNUmeroAndamentoAgenda.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblNUmeroAndamentoAgenda.setText("Nº");

        lblIconrPendentesAgenda.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblIconrPendentesAgenda.setIcon(new javax.swing.ImageIcon("C:\\Users\\SamaraTavares\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\icones\\pendente (2).png")); // NOI18N

        lblPendentesAgenda.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        lblPendentesAgenda.setText("Pendentes");

        lblNumeroPendentesAgenda.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblNumeroPendentesAgenda.setText("Nº");

        lblIconeConcluidasAgenda.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblIconeConcluidasAgenda.setIcon(new javax.swing.ImageIcon("C:\\Users\\SamaraTavares\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\icones\\concluido.png")); // NOI18N

        lblConcluidasAgenda.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        lblConcluidasAgenda.setText("Concluídas");

        lblNumeroConcluidasAgenda.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblNumeroConcluidasAgenda.setText("Nº");

        lblCalendarioAgenda.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 24)); // NOI18N
        lblCalendarioAgenda.setForeground(new java.awt.Color(10, 4, 138));
        lblCalendarioAgenda.setText("Calendário");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(lblCalendarioAgenda)
                .addContainerGap(265, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblCalendarioAgenda)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTable1.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Horário ", "Entregador", "Veículo", "Região", "Qtd. Entregas", "Status"
            }
        ));
        jScrollPane13.setViewportView(jTable1);

        btnNovoAgenda.setBackground(new java.awt.Color(10, 4, 138));
        btnNovoAgenda.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        btnNovoAgenda.setForeground(new java.awt.Color(255, 255, 255));
        btnNovoAgenda.setIcon(new javax.swing.ImageIcon("C:\\Users\\SamaraTavares\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\icones\\botao-mais.png")); // NOI18N
        btnNovoAgenda.setText("Novo");

        btnAlterarAgenda.setBackground(new java.awt.Color(10, 4, 138));
        btnAlterarAgenda.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        btnAlterarAgenda.setForeground(new java.awt.Color(255, 255, 255));
        btnAlterarAgenda.setIcon(new javax.swing.ImageIcon("C:\\Users\\SamaraTavares\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\icones\\editar.png")); // NOI18N
        btnAlterarAgenda.setText("Alterar");

        btnExcluirAgenda.setBackground(new java.awt.Color(10, 4, 138));
        btnExcluirAgenda.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        btnExcluirAgenda.setForeground(new java.awt.Color(255, 255, 255));
        btnExcluirAgenda.setIcon(new javax.swing.ImageIcon("C:\\Users\\SamaraTavares\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\icones\\excluir.png")); // NOI18N
        btnExcluirAgenda.setText("Excluir");

        btnVoltarAgenda.setBackground(new java.awt.Color(10, 4, 138));
        btnVoltarAgenda.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        btnVoltarAgenda.setForeground(new java.awt.Color(255, 255, 255));
        btnVoltarAgenda.setIcon(new javax.swing.ImageIcon("C:\\Users\\SamaraTavares\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\icones\\seta-esquerda (1).png")); // NOI18N
        btnVoltarAgenda.setText("Voltar");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setIcon(new javax.swing.ImageIcon("C:\\Users\\SamaraTavares\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\icones\\entrega.png")); // NOI18N

        javax.swing.GroupLayout agendaLayout = new javax.swing.GroupLayout(agenda);
        agenda.setLayout(agendaLayout);
        agendaLayout.setHorizontalGroup(
            agendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(agendaLayout.createSequentialGroup()
                .addGroup(agendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(agendaLayout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane13))
                    .addGroup(agendaLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(agendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(agendaLayout.createSequentialGroup()
                                .addGroup(agendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(agendaLayout.createSequentialGroup()
                                        .addComponent(lblIconeEntregasAgenda, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(agendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblTotalEntregasAgenda)
                                            .addGroup(agendaLayout.createSequentialGroup()
                                                .addGap(29, 29, 29)
                                                .addComponent(lblNumeroEntregasAgenda)))
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel2)
                                        .addGroup(agendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(agendaLayout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(lblAndamentoAgenda))
                                            .addGroup(agendaLayout.createSequentialGroup()
                                                .addGap(30, 30, 30)
                                                .addComponent(lblNumeroPendentesAgenda)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                                        .addComponent(lblIconrPendentesAgenda)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(agendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblPendentesAgenda)
                                            .addGroup(agendaLayout.createSequentialGroup()
                                                .addComponent(lblIconeAndamentoAgenda)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(lblNUmeroAndamentoAgenda)))
                                        .addGap(55, 55, 55))
                                    .addGroup(agendaLayout.createSequentialGroup()
                                        .addGroup(agendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblTituloAgenda)
                                            .addGroup(agendaLayout.createSequentialGroup()
                                                .addComponent(lblDataAgenda)
                                                .addGap(162, 162, 162)
                                                .addComponent(lblEntregadorAgenda))
                                            .addGroup(agendaLayout.createSequentialGroup()
                                                .addComponent(ftfDataAgenda, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(50, 50, 50)
                                                .addComponent(cbEntregadorAgenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(57, 57, 57)
                                        .addGroup(agendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblVeiculoAgenda)
                                            .addComponent(cbVeiculoAgenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(31, 31, 31)))
                                .addGroup(agendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblRegiãoAgenda)
                                    .addGroup(agendaLayout.createSequentialGroup()
                                        .addComponent(cbRegiaoAgenda, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(38, 38, 38)
                                        .addComponent(btnPesquisarAgenda))))
                            .addGroup(agendaLayout.createSequentialGroup()
                                .addComponent(btnNovoAgenda)
                                .addGap(18, 18, 18)
                                .addComponent(btnAlterarAgenda)
                                .addGap(18, 18, 18)
                                .addComponent(btnExcluirAgenda)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnVoltarAgenda))
                            .addGroup(agendaLayout.createSequentialGroup()
                                .addGap(692, 692, 692)
                                .addComponent(lblIconeConcluidasAgenda)
                                .addGroup(agendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(agendaLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblConcluidasAgenda))
                                    .addGroup(agendaLayout.createSequentialGroup()
                                        .addGap(33, 33, 33)
                                        .addComponent(lblNumeroConcluidasAgenda)))))))
                .addContainerGap(919, Short.MAX_VALUE))
        );
        agendaLayout.setVerticalGroup(
            agendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(agendaLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(lblTituloAgenda)
                .addGap(18, 18, 18)
                .addGroup(agendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(agendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblDataAgenda)
                        .addComponent(lblRegiãoAgenda)
                        .addComponent(lblEntregadorAgenda))
                    .addComponent(lblVeiculoAgenda))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(agendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbVeiculoAgenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(agendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(ftfDataAgenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbEntregadorAgenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbRegiaoAgenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnPesquisarAgenda)))
                .addGroup(agendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(agendaLayout.createSequentialGroup()
                        .addGroup(agendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(agendaLayout.createSequentialGroup()
                                .addGap(45, 45, 45)
                                .addComponent(lblIconeConcluidasAgenda))
                            .addGroup(agendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(agendaLayout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(lblNumeroEntregasAgenda))
                                .addGroup(agendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(agendaLayout.createSequentialGroup()
                                        .addGap(45, 45, 45)
                                        .addComponent(jLabel2))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, agendaLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblIconeEntregasAgenda)))))
                        .addGap(46, 46, 46))
                    .addGroup(agendaLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(agendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(agendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblAndamentoAgenda)
                                .addComponent(lblPendentesAgenda))
                            .addComponent(lblTotalEntregasAgenda)
                            .addComponent(lblConcluidasAgenda))
                        .addGroup(agendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(agendaLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(agendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(agendaLayout.createSequentialGroup()
                                        .addComponent(lblIconeAndamentoAgenda, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(46, 46, 46))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, agendaLayout.createSequentialGroup()
                                        .addComponent(lblNUmeroAndamentoAgenda)
                                        .addGap(61, 61, 61))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, agendaLayout.createSequentialGroup()
                                        .addComponent(lblNumeroPendentesAgenda)
                                        .addGap(61, 61, 61))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, agendaLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblNumeroConcluidasAgenda)
                                .addGap(61, 61, 61))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, agendaLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblIconrPendentesAgenda)
                        .addGap(61, 61, 61)))
                .addGroup(agendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane13)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(agendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNovoAgenda)
                    .addComponent(btnAlterarAgenda)
                    .addComponent(btnExcluirAgenda)
                    .addComponent(btnVoltarAgenda))
                .addContainerGap(274, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Agenda", agenda);

        distribuicao.setBackground(new java.awt.Color(236, 236, 236));

        lblTituloDistribuicao.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        lblTituloDistribuicao.setForeground(new java.awt.Color(10, 4, 138));
        lblTituloDistribuicao.setText("Distribuição/ Controle de Entregas");

        lblPedidoDistribuicao.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblPedidoDistribuicao.setText("Pedido/Código");

        txtPedidoDistribuicao.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        txtPedidoDistribuicao.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblClienteDistribuicao.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblClienteDistribuicao.setText("Cliente");

        txtClienteDistribuicao.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        txtClienteDistribuicao.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblEnderecoDistribuicao.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblEnderecoDistribuicao.setText("Endereço de Entrega");

        txtaEnderecoDistribuicao.setColumns(20);
        txtaEnderecoDistribuicao.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        txtaEnderecoDistribuicao.setRows(5);
        txtaEnderecoDistribuicao.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jScrollPane14.setViewportView(txtaEnderecoDistribuicao);

        lblRegiaoDistribuicao.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblRegiaoDistribuicao.setText("Região");

        cbRegiaoDistribuicao.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        cbRegiaoDistribuicao.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione a região", "Região Norte", "Região Nordeste", "Região Centro-Oeste", "Região Sudeste", "Região Sul" }));

        lblEntregadorDistribuicao.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblEntregadorDistribuicao.setText("Entregador");

        cbEntregadorDistribuicao.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        cbEntregadorDistribuicao.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione o entregador", " " }));

        lblVeiculoDistribuicao.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblVeiculoDistribuicao.setText("Veículo");

        cbVeiculoDistribuicao.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        cbVeiculoDistribuicao.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione o veículo", " " }));

        lblDataDistribuicao.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblDataDistribuicao.setText("Data da Distribuição");

        ftfDataDistribuicao.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        try {
            ftfDataDistribuicao.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        lblHorarioEntregaDistribuicao.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblHorarioEntregaDistribuicao.setText("Horário da Entrega");

        ftfHorarioDistribuicao.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        try {
            ftfHorarioDistribuicao.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##:##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        ftfHorarioDistribuicao.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N

        lblStatusDistribuicao.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblStatusDistribuicao.setText("Status da Distribuição");

        cbStatusDistribuicao.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        cbStatusDistribuicao.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione o status", "Programada", "Em andamento", "Em rota", "Concluída" }));

        lblObservacoesDistribuicao.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblObservacoesDistribuicao.setText("Observações");

        txtaObservacoesDistribuicao.setColumns(20);
        txtaObservacoesDistribuicao.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        txtaObservacoesDistribuicao.setRows(5);
        txtaObservacoesDistribuicao.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jScrollPane15.setViewportView(txtaObservacoesDistribuicao);

        btnNovoDistribuicao.setBackground(new java.awt.Color(10, 4, 138));
        btnNovoDistribuicao.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        btnNovoDistribuicao.setForeground(new java.awt.Color(255, 255, 255));
        btnNovoDistribuicao.setIcon(new javax.swing.ImageIcon("C:\\Users\\SamaraTavares\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\icones\\botao-mais.png")); // NOI18N
        btnNovoDistribuicao.setText("Novo");

        btnSalvarDistribuicao.setBackground(new java.awt.Color(10, 4, 138));
        btnSalvarDistribuicao.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        btnSalvarDistribuicao.setForeground(new java.awt.Color(255, 255, 255));
        btnSalvarDistribuicao.setIcon(new javax.swing.ImageIcon("C:\\Users\\SamaraTavares\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\icones\\Salvar.png")); // NOI18N
        btnSalvarDistribuicao.setText("Salvar");

        btnAlterarDistribuicao.setBackground(new java.awt.Color(10, 4, 138));
        btnAlterarDistribuicao.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        btnAlterarDistribuicao.setForeground(new java.awt.Color(255, 255, 255));
        btnAlterarDistribuicao.setIcon(new javax.swing.ImageIcon("C:\\Users\\SamaraTavares\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\icones\\editar.png")); // NOI18N
        btnAlterarDistribuicao.setText("Alterar");

        btnExcluirDistribuicao.setBackground(new java.awt.Color(10, 4, 138));
        btnExcluirDistribuicao.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        btnExcluirDistribuicao.setForeground(new java.awt.Color(255, 255, 255));
        btnExcluirDistribuicao.setIcon(new javax.swing.ImageIcon("C:\\Users\\SamaraTavares\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\icones\\excluir.png")); // NOI18N
        btnExcluirDistribuicao.setText("Excluir");

        btnVoltarDistribuicao.setBackground(new java.awt.Color(10, 4, 138));
        btnVoltarDistribuicao.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        btnVoltarDistribuicao.setForeground(new java.awt.Color(255, 255, 255));
        btnVoltarDistribuicao.setIcon(new javax.swing.ImageIcon("C:\\Users\\SamaraTavares\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\icones\\seta-esquerda (1).png")); // NOI18N
        btnVoltarDistribuicao.setText("Voltar");

        tblListaDistribuicao.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        tblListaDistribuicao.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Código", "Pedido", "Cliente", "Endereço", "Região", "Entregador", "Veículo ", "Data da Distribuição", "Horário", "Status"
            }
        ));
        jScrollPane16.setViewportView(tblListaDistribuicao);

        javax.swing.GroupLayout distribuicaoLayout = new javax.swing.GroupLayout(distribuicao);
        distribuicao.setLayout(distribuicaoLayout);
        distribuicaoLayout.setHorizontalGroup(
            distribuicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(distribuicaoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(distribuicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(distribuicaoLayout.createSequentialGroup()
                        .addGroup(distribuicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTituloDistribuicao)
                            .addGroup(distribuicaoLayout.createSequentialGroup()
                                .addComponent(btnNovoDistribuicao)
                                .addGap(18, 18, 18)
                                .addComponent(btnSalvarDistribuicao)
                                .addGap(18, 18, 18)
                                .addComponent(btnAlterarDistribuicao)
                                .addGap(18, 18, 18)
                                .addComponent(btnExcluirDistribuicao))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, distribuicaoLayout.createSequentialGroup()
                                .addGroup(distribuicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(distribuicaoLayout.createSequentialGroup()
                                        .addGroup(distribuicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblPedidoDistribuicao)
                                            .addGroup(distribuicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(txtPedidoDistribuicao, javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(txtClienteDistribuicao, javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jScrollPane14, javax.swing.GroupLayout.Alignment.LEADING))
                                            .addComponent(lblClienteDistribuicao))
                                        .addGap(34, 34, 34)
                                        .addGroup(distribuicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(lblRegiaoDistribuicao)
                                            .addComponent(cbRegiaoDistribuicao, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(lblEntregadorDistribuicao)
                                            .addComponent(cbEntregadorDistribuicao, 0, 199, Short.MAX_VALUE)
                                            .addComponent(lblVeiculoDistribuicao)
                                            .addComponent(cbVeiculoDistribuicao, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(32, 32, 32)
                                        .addGroup(distribuicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(lblDataDistribuicao)
                                            .addComponent(ftfDataDistribuicao)
                                            .addComponent(lblHorarioEntregaDistribuicao)
                                            .addComponent(ftfHorarioDistribuicao)
                                            .addComponent(lblStatusDistribuicao)
                                            .addComponent(cbStatusDistribuicao, 0, 168, Short.MAX_VALUE)))
                                    .addComponent(lblEnderecoDistribuicao))
                                .addGap(43, 43, 43)
                                .addGroup(distribuicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblObservacoesDistribuicao)
                                    .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnVoltarDistribuicao, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, 1151, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(853, Short.MAX_VALUE))
        );
        distribuicaoLayout.setVerticalGroup(
            distribuicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(distribuicaoLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(lblTituloDistribuicao)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(distribuicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPedidoDistribuicao)
                    .addComponent(lblRegiaoDistribuicao)
                    .addComponent(lblDataDistribuicao)
                    .addComponent(lblObservacoesDistribuicao))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(distribuicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(distribuicaoLayout.createSequentialGroup()
                        .addGroup(distribuicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ftfDataDistribuicao, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(distribuicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtPedidoDistribuicao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cbRegiaoDistribuicao, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(distribuicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(distribuicaoLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(lblClienteDistribuicao)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtClienteDistribuicao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblEnderecoDistribuicao)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(distribuicaoLayout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addGroup(distribuicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblEntregadorDistribuicao)
                                    .addComponent(lblHorarioEntregaDistribuicao))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(distribuicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cbEntregadorDistribuicao, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(ftfHorarioDistribuicao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(distribuicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblVeiculoDistribuicao)
                                    .addComponent(lblStatusDistribuicao))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(distribuicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cbVeiculoDistribuicao, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbStatusDistribuicao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(18, 18, 18)
                        .addGroup(distribuicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnNovoDistribuicao)
                            .addComponent(btnSalvarDistribuicao)
                            .addComponent(btnAlterarDistribuicao)
                            .addComponent(btnExcluirDistribuicao)
                            .addComponent(btnVoltarDistribuicao))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(276, 276, 276))
        );

        jTabbedPane1.addTab("Distribuição", distribuicao);

        status.setBackground(new java.awt.Color(236, 236, 236));

        lblTituloStatus.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        lblTituloStatus.setForeground(new java.awt.Color(10, 4, 138));
        lblTituloStatus.setText("Status das Entregas");

        lblPeriodoStatus.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblPeriodoStatus.setText("Período");

        ftfPeriodoStatus.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        try {
            ftfPeriodoStatus.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        ftfPeriodoStatus.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N

        lblStatus.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblStatus.setText("Status");

        cbStatus.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        cbStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione o status", "Prontos", "Em separação", "Enviados", "Aguardando Pagamento", "Concluídos", "Cancelados" }));

        lblEntregadorStatus.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblEntregadorStatus.setText("Entregador");

        cbEntregadorStatus.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        cbEntregadorStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione o entregador" }));

        lblCliente.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblCliente.setText("Cliente");

        txtClienteStatus.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        txtClienteStatus.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblIconePagamentoStatus.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblIconePagamentoStatus.setIcon(new javax.swing.ImageIcon("C:\\Users\\SamaraTavares\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\icones\\pagamento-com-cartao-de-credito.png")); // NOI18N

        jLabel4.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(39, 34, 160));
        jLabel4.setText("Aguardando pagamento");

        lblNumeroPagamentoStatus.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblNumeroPagamentoStatus.setText("Nº");

        lblIconeSeparacaoStatus.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblIconeSeparacaoStatus.setIcon(new javax.swing.ImageIcon("C:\\Users\\SamaraTavares\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\icones\\separacao-em-lote (1).png")); // NOI18N

        lblSeparacaoStatus.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        lblSeparacaoStatus.setForeground(new java.awt.Color(39, 34, 160));
        lblSeparacaoStatus.setText("Em separação");

        lblNumeroSeparacaoStatus.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblNumeroSeparacaoStatus.setText("Nº");

        lblIconeProntosStatus.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblIconeProntosStatus.setIcon(new javax.swing.ImageIcon("C:\\Users\\SamaraTavares\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\icones\\armazem.png")); // NOI18N

        lblProntosStatus.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        lblProntosStatus.setForeground(new java.awt.Color(39, 34, 160));
        lblProntosStatus.setText("Prontos");

        lblNumeroProntosStatus.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblNumeroProntosStatus.setText("Nº");

        lblIconeEnviadosStatus.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblIconeEnviadosStatus.setIcon(new javax.swing.ImageIcon("C:\\Users\\SamaraTavares\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\icones\\aviao-de-papel.png")); // NOI18N

        lblEnviadosStatus.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        lblEnviadosStatus.setForeground(new java.awt.Color(39, 34, 160));
        lblEnviadosStatus.setText("Enviados");

        lblNumeroEnviadosStatus.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblNumeroEnviadosStatus.setText("Nº");

        lblIconeConcluídos.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblIconeConcluídos.setIcon(new javax.swing.ImageIcon("C:\\Users\\SamaraTavares\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\icones\\correto (2).png")); // NOI18N

        lblConcluidosStatus.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        lblConcluidosStatus.setForeground(new java.awt.Color(39, 34, 160));
        lblConcluidosStatus.setText("Concluídos");

        lblNumeroConcluidosStatus.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblNumeroConcluidosStatus.setText("Nº");

        lblIconeCanceladosStatus.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblIconeCanceladosStatus.setIcon(new javax.swing.ImageIcon("C:\\Users\\SamaraTavares\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\icones\\cancelar.png")); // NOI18N

        lblCanceladosStatus.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        lblCanceladosStatus.setForeground(new java.awt.Color(39, 34, 160));
        lblCanceladosStatus.setText("Cancelados");

        lblNumeroCanceladosStatus.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblNumeroCanceladosStatus.setText("Nº");

        tblEntregasStatus.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        tblEntregasStatus.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Pedido", "Cliente ", "Endereço", "Entregador", "Veículo", " Status", "Data e Horário"
            }
        ));
        jScrollPane17.setViewportView(tblEntregasStatus);

        btnNovoStatus.setBackground(new java.awt.Color(10, 4, 138));
        btnNovoStatus.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        btnNovoStatus.setForeground(new java.awt.Color(255, 255, 255));
        btnNovoStatus.setIcon(new javax.swing.ImageIcon("C:\\Users\\SamaraTavares\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\icones\\botao-mais.png")); // NOI18N
        btnNovoStatus.setText("Novo");

        btnAlterarStatus.setBackground(new java.awt.Color(10, 4, 138));
        btnAlterarStatus.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        btnAlterarStatus.setForeground(new java.awt.Color(255, 255, 255));
        btnAlterarStatus.setIcon(new javax.swing.ImageIcon("C:\\Users\\SamaraTavares\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\icones\\editar.png")); // NOI18N
        btnAlterarStatus.setText("Alterar");

        btnExcluirStatus.setBackground(new java.awt.Color(10, 4, 138));
        btnExcluirStatus.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        btnExcluirStatus.setForeground(new java.awt.Color(255, 255, 255));
        btnExcluirStatus.setIcon(new javax.swing.ImageIcon("C:\\Users\\SamaraTavares\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\icones\\excluir.png")); // NOI18N
        btnExcluirStatus.setText("Excluir");

        btnVoltarStatus.setBackground(new java.awt.Color(10, 4, 138));
        btnVoltarStatus.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        btnVoltarStatus.setForeground(new java.awt.Color(255, 255, 255));
        btnVoltarStatus.setIcon(new javax.swing.ImageIcon("C:\\Users\\SamaraTavares\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\icones\\seta-esquerda (1).png")); // NOI18N
        btnVoltarStatus.setText("Voltar");

        javax.swing.GroupLayout statusLayout = new javax.swing.GroupLayout(status);
        status.setLayout(statusLayout);
        statusLayout.setHorizontalGroup(
            statusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statusLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(statusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(statusLayout.createSequentialGroup()
                        .addGroup(statusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(statusLayout.createSequentialGroup()
                                .addGap(51, 51, 51)
                                .addComponent(jLabel4)
                                .addGap(82, 82, 82)
                                .addComponent(lblSeparacaoStatus)
                                .addGap(90, 90, 90)
                                .addComponent(lblProntosStatus)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblEnviadosStatus)
                                .addGap(58, 58, 58))
                            .addGroup(statusLayout.createSequentialGroup()
                                .addGroup(statusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(statusLayout.createSequentialGroup()
                                        .addGroup(statusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(lblTituloStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(lblPeriodoStatus)
                                            .addComponent(ftfPeriodoStatus))
                                        .addGap(34, 34, 34)
                                        .addGroup(statusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblStatus)
                                            .addComponent(cbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(45, 45, 45))
                                    .addGroup(statusLayout.createSequentialGroup()
                                        .addComponent(lblIconePagamentoStatus)
                                        .addGap(36, 36, 36)
                                        .addComponent(lblNumeroPagamentoStatus)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lblIconeSeparacaoStatus)
                                        .addGap(42, 42, 42)
                                        .addComponent(lblNumeroSeparacaoStatus)
                                        .addGap(57, 57, 57)))
                                .addGroup(statusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, statusLayout.createSequentialGroup()
                                        .addGroup(statusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblEntregadorStatus)
                                            .addComponent(cbEntregadorStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(30, 30, 30))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, statusLayout.createSequentialGroup()
                                        .addComponent(lblIconeProntosStatus)
                                        .addGap(18, 18, 18)
                                        .addComponent(lblNumeroProntosStatus)
                                        .addGap(91, 91, 91)))
                                .addGroup(statusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(statusLayout.createSequentialGroup()
                                        .addComponent(lblIconeEnviadosStatus)
                                        .addGap(36, 36, 36)
                                        .addComponent(lblNumeroEnviadosStatus))
                                    .addComponent(lblCliente)
                                    .addComponent(txtClienteStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(statusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(statusLayout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(lblIconeConcluídos)
                                .addGap(36, 36, 36)
                                .addComponent(lblNumeroConcluidosStatus)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblIconeCanceladosStatus)
                                .addGap(37, 37, 37)
                                .addComponent(lblNumeroCanceladosStatus)
                                .addGap(47, 47, 47))
                            .addGroup(statusLayout.createSequentialGroup()
                                .addGap(56, 56, 56)
                                .addComponent(lblConcluidosStatus)
                                .addGap(86, 86, 86)
                                .addComponent(lblCanceladosStatus)))
                        .addContainerGap(732, Short.MAX_VALUE))
                    .addGroup(statusLayout.createSequentialGroup()
                        .addGroup(statusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane17)
                            .addGroup(statusLayout.createSequentialGroup()
                                .addComponent(btnNovoStatus)
                                .addGap(18, 18, 18)
                                .addComponent(btnAlterarStatus)
                                .addGap(18, 18, 18)
                                .addComponent(btnExcluirStatus)
                                .addGap(738, 738, 738)
                                .addComponent(btnVoltarStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        statusLayout.setVerticalGroup(
            statusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statusLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(lblTituloStatus)
                .addGap(18, 18, 18)
                .addGroup(statusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPeriodoStatus)
                    .addComponent(lblStatus)
                    .addComponent(lblEntregadorStatus)
                    .addComponent(lblCliente))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(statusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ftfPeriodoStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbEntregadorStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtClienteStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(statusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(statusLayout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(statusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblProntosStatus)
                            .addComponent(lblEnviadosStatus)
                            .addComponent(lblConcluidosStatus))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(statusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(statusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblIconePagamentoStatus)
                                .addComponent(lblIconeSeparacaoStatus)
                                .addComponent(lblIconeConcluídos)
                                .addComponent(lblIconeEnviadosStatus))
                            .addComponent(lblNumeroConcluidosStatus)
                            .addComponent(lblNumeroEnviadosStatus)))
                    .addGroup(statusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(statusLayout.createSequentialGroup()
                            .addGap(35, 35, 35)
                            .addGroup(statusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel4)
                                .addComponent(lblSeparacaoStatus)
                                .addComponent(lblCanceladosStatus))
                            .addGroup(statusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, statusLayout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(statusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lblNumeroSeparacaoStatus, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(lblNumeroPagamentoStatus, javax.swing.GroupLayout.Alignment.TRAILING)))
                                .addGroup(statusLayout.createSequentialGroup()
                                    .addGroup(statusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(statusLayout.createSequentialGroup()
                                            .addGap(7, 7, 7)
                                            .addComponent(lblIconeProntosStatus))
                                        .addGroup(statusLayout.createSequentialGroup()
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addGroup(statusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(lblNumeroCanceladosStatus)
                                                .addComponent(lblIconeCanceladosStatus))))
                                    .addGap(0, 0, Short.MAX_VALUE))))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, statusLayout.createSequentialGroup()
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblNumeroProntosStatus))))
                .addGap(76, 76, 76)
                .addComponent(jScrollPane17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(statusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNovoStatus)
                    .addComponent(btnAlterarStatus)
                    .addComponent(btnExcluirStatus)
                    .addComponent(btnVoltarStatus))
                .addContainerGap(233, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Status", status);

        tentativas.setBackground(new java.awt.Color(236, 236, 236));

        lblTituloTentativas.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        lblTituloTentativas.setForeground(new java.awt.Color(10, 4, 138));
        lblTituloTentativas.setText("Tentativas de Entrega");

        lblPeriodoTentativas.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblPeriodoTentativas.setText("Período");

        ftfPeriodoTentativas.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        try {
            ftfPeriodoTentativas.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        ftfPeriodoTentativas.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N

        lblClienteTentativas.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblClienteTentativas.setText("Cliente");

        txtClienteTentativas.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        txtClienteTentativas.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblEntregadorTentativas.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblEntregadorTentativas.setText("Entregador");

        cbEntregadorTentativas.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        cbEntregadorTentativas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione o entregador" }));

        lblStatusTentativas.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblStatusTentativas.setText("Status da tentativa");

        cbStatusTentativas.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        cbStatusTentativas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione o status ", "Entregue", "Em andamento", "Cancelada", "Nova tentativa" }));

        btnPesquisarTentativas.setBackground(new java.awt.Color(10, 4, 138));
        btnPesquisarTentativas.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        btnPesquisarTentativas.setForeground(new java.awt.Color(255, 255, 255));
        btnPesquisarTentativas.setIcon(new javax.swing.ImageIcon("C:\\Users\\SamaraTavares\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\icones\\lupa.png")); // NOI18N
        btnPesquisarTentativas.setText("Pesquisar");

        lblIconeTotalTentativas.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblIconeTotalTentativas.setText("Icone");

        lblTotalTentativas.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        lblTotalTentativas.setForeground(new java.awt.Color(39, 34, 160));
        lblTotalTentativas.setText("Total de tentativas");

        lblNumeroTotalTentativas.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblNumeroTotalTentativas.setText("Nº");

        lblIconeEntreguesTentativas.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblIconeEntreguesTentativas.setText("Icone");

        lblEntreguesTentativas.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        lblEntreguesTentativas.setForeground(new java.awt.Color(39, 34, 160));
        lblEntreguesTentativas.setText("Entregues na tentativa");

        lblNumeroEntreguesTentativas.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblNumeroEntreguesTentativas.setText("Nº");

        lblIconeAguardandoTentativas.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblIconeAguardandoTentativas.setText("Icone");

        lblAguardandoTentativas.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        lblAguardandoTentativas.setForeground(new java.awt.Color(39, 34, 160));
        lblAguardandoTentativas.setText("Aguardando nova tentativa");

        lblNumeroAguardandoTentativas.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblNumeroAguardandoTentativas.setText("Nº");

        lblIconeCanceladasTentativas.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblIconeCanceladasTentativas.setText("Icone");

        lblCanceladasTentativas.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        lblCanceladasTentativas.setForeground(new java.awt.Color(39, 34, 160));
        lblCanceladasTentativas.setText("Canceladas");

        lblNumeroCanceladasTentativas.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblNumeroCanceladasTentativas.setText("Nº");

        tblTentativasEntrega.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Código da entrega", "Pedido", "Cliente", "Endereço", "Entregador", "Veículo", "Tentativas", "Data e Horário", "Status"
            }
        ));
        jScrollPane18.setViewportView(tblTentativasEntrega);

        btnNovoTentativas.setBackground(new java.awt.Color(10, 4, 138));
        btnNovoTentativas.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        btnNovoTentativas.setForeground(new java.awt.Color(255, 255, 255));
        btnNovoTentativas.setIcon(new javax.swing.ImageIcon("C:\\Users\\SamaraTavares\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\icones\\botao-mais.png")); // NOI18N
        btnNovoTentativas.setText("Novo");

        btnAlterarTentativas.setBackground(new java.awt.Color(10, 4, 138));
        btnAlterarTentativas.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        btnAlterarTentativas.setForeground(new java.awt.Color(255, 255, 255));
        btnAlterarTentativas.setIcon(new javax.swing.ImageIcon("C:\\Users\\SamaraTavares\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\icones\\editar.png")); // NOI18N
        btnAlterarTentativas.setText("Alterar");

        btnExcluirTentativas.setBackground(new java.awt.Color(10, 4, 138));
        btnExcluirTentativas.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        btnExcluirTentativas.setForeground(new java.awt.Color(255, 255, 255));
        btnExcluirTentativas.setIcon(new javax.swing.ImageIcon("C:\\Users\\SamaraTavares\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\icones\\excluir.png")); // NOI18N
        btnExcluirTentativas.setText("Excluir");

        btnVoltarTentativas.setBackground(new java.awt.Color(10, 4, 138));
        btnVoltarTentativas.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        btnVoltarTentativas.setForeground(new java.awt.Color(255, 255, 255));
        btnVoltarTentativas.setIcon(new javax.swing.ImageIcon("C:\\Users\\SamaraTavares\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\icones\\seta-esquerda (1).png")); // NOI18N
        btnVoltarTentativas.setText("Voltar");

        javax.swing.GroupLayout tentativasLayout = new javax.swing.GroupLayout(tentativas);
        tentativas.setLayout(tentativasLayout);
        tentativasLayout.setHorizontalGroup(
            tentativasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tentativasLayout.createSequentialGroup()
                .addGroup(tentativasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tentativasLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(tentativasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTituloTentativas)
                            .addGroup(tentativasLayout.createSequentialGroup()
                                .addGroup(tentativasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblPeriodoTentativas)
                                    .addComponent(ftfPeriodoTentativas, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblIconeTotalTentativas))
                                .addGroup(tentativasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(tentativasLayout.createSequentialGroup()
                                        .addGap(26, 26, 26)
                                        .addGroup(tentativasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblClienteTentativas)
                                            .addComponent(txtClienteTentativas, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(tentativasLayout.createSequentialGroup()
                                        .addGap(85, 85, 85)
                                        .addComponent(lblIconeEntreguesTentativas)))
                                .addGroup(tentativasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(tentativasLayout.createSequentialGroup()
                                        .addGap(25, 25, 25)
                                        .addGroup(tentativasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblEntregadorTentativas)
                                            .addComponent(cbEntregadorTentativas, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(28, 28, 28)
                                        .addGroup(tentativasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblStatusTentativas)
                                            .addGroup(tentativasLayout.createSequentialGroup()
                                                .addComponent(cbStatusTentativas, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(38, 38, 38)
                                                .addComponent(btnPesquisarTentativas))))
                                    .addGroup(tentativasLayout.createSequentialGroup()
                                        .addGap(172, 172, 172)
                                        .addComponent(lblIconeAguardandoTentativas)
                                        .addGap(316, 316, 316)
                                        .addComponent(lblIconeCanceladasTentativas))))))
                    .addGroup(tentativasLayout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addGroup(tentativasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTotalTentativas)
                            .addComponent(lblNumeroTotalTentativas))
                        .addGap(95, 95, 95)
                        .addGroup(tentativasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblEntreguesTentativas)
                            .addComponent(lblNumeroEntreguesTentativas))
                        .addGap(98, 98, 98)
                        .addGroup(tentativasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblAguardandoTentativas)
                            .addComponent(lblNumeroAguardandoTentativas))
                        .addGap(107, 107, 107)
                        .addGroup(tentativasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblCanceladasTentativas)
                            .addComponent(lblNumeroCanceladasTentativas)))
                    .addGroup(tentativasLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(tentativasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane18, javax.swing.GroupLayout.PREFERRED_SIZE, 1109, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(tentativasLayout.createSequentialGroup()
                                .addComponent(btnNovoTentativas)
                                .addGap(26, 26, 26)
                                .addComponent(btnAlterarTentativas)
                                .addGap(18, 18, 18)
                                .addComponent(btnExcluirTentativas)
                                .addGap(573, 573, 573)
                                .addComponent(btnVoltarTentativas, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(895, Short.MAX_VALUE))
        );
        tentativasLayout.setVerticalGroup(
            tentativasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tentativasLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblTituloTentativas)
                .addGap(18, 18, 18)
                .addGroup(tentativasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPeriodoTentativas)
                    .addComponent(lblClienteTentativas)
                    .addComponent(lblEntregadorTentativas)
                    .addComponent(lblStatusTentativas))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tentativasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ftfPeriodoTentativas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtClienteTentativas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbEntregadorTentativas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbStatusTentativas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPesquisarTentativas))
                .addGap(27, 27, 27)
                .addGroup(tentativasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTotalTentativas)
                    .addComponent(lblEntreguesTentativas)
                    .addComponent(lblAguardandoTentativas)
                    .addComponent(lblCanceladasTentativas))
                .addGap(12, 12, 12)
                .addGroup(tentativasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblIconeTotalTentativas)
                    .addComponent(lblIconeEntreguesTentativas)
                    .addComponent(lblIconeAguardandoTentativas)
                    .addComponent(lblIconeCanceladasTentativas))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tentativasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNumeroTotalTentativas)
                    .addComponent(lblNumeroEntreguesTentativas)
                    .addComponent(lblNumeroAguardandoTentativas)
                    .addComponent(lblNumeroCanceladasTentativas))
                .addGap(30, 30, 30)
                .addComponent(jScrollPane18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(tentativasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNovoTentativas)
                    .addComponent(btnAlterarTentativas)
                    .addComponent(btnExcluirTentativas)
                    .addComponent(btnVoltarTentativas))
                .addContainerGap(300, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Tentativas", tentativas);

        confirmacao.setBackground(new java.awt.Color(236, 236, 236));

        lblTituloConfirmacao.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        lblTituloConfirmacao.setForeground(new java.awt.Color(10, 4, 138));
        lblTituloConfirmacao.setText("Confirmação de Entregas");

        lblPeriodoConfirmacao.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblPeriodoConfirmacao.setText("Período");

        ftfPeriodoConfirmacao.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        try {
            ftfPeriodoConfirmacao.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        ftfPeriodoConfirmacao.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N

        lblClienteConfirmacao.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblClienteConfirmacao.setText("Cliente");

        txtClienteConfirmacao.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        txtClienteConfirmacao.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblEntregadorConfirmacao.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblEntregadorConfirmacao.setText("Entregadores");

        cbEntregadorConfirmacao.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        cbEntregadorConfirmacao.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione o entregador" }));

        lblStatusConfirmacao.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblStatusConfirmacao.setText("Status da confirmação");

        cbStatusConfirmacao.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        cbStatusConfirmacao.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione o status", "Confirmada", "Aguardando", "Não confirmada" }));

        btnPesquisarConfirmacao.setBackground(new java.awt.Color(10, 4, 138));
        btnPesquisarConfirmacao.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        btnPesquisarConfirmacao.setForeground(new java.awt.Color(255, 255, 255));
        btnPesquisarConfirmacao.setIcon(new javax.swing.ImageIcon("C:\\Users\\SamaraTavares\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\icones\\lupa.png")); // NOI18N
        btnPesquisarConfirmacao.setText("Pesquisar");

        lblIconeEntregasConfirmadas.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblIconeEntregasConfirmadas.setText("Icone");

        lblEntregasConfirmadas.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        lblEntregasConfirmadas.setForeground(new java.awt.Color(39, 34, 160));
        lblEntregasConfirmadas.setText("Entregas confirmadas");

        lblNumeroEntregasConfirmadas.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblNumeroEntregasConfirmadas.setText("Nº");

        lblIconeAguargandoConfirmacao.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblIconeAguargandoConfirmacao.setText("Icone");

        lblAguardandoConfirmacao.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        lblAguardandoConfirmacao.setForeground(new java.awt.Color(39, 34, 160));
        lblAguardandoConfirmacao.setText("Aguardando confirmação");

        lblNumeorAguardandoConfirmacao.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblNumeorAguardandoConfirmacao.setText("Nº");

        lblIconeNaoConfirmada.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblIconeNaoConfirmada.setText("Icone");

        lblNaoConfirmada.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        lblNaoConfirmada.setForeground(new java.awt.Color(39, 34, 160));
        lblNaoConfirmada.setText("Não Confirmadas");

        lblNumeroNaoConfirmada.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblNumeroNaoConfirmada.setText("Nº");

        lblIconeTotalEntregasConfirmacao.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblIconeTotalEntregasConfirmacao.setText("Icone");

        lblTotalEntregasConfirmacao.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        lblTotalEntregasConfirmacao.setForeground(new java.awt.Color(39, 34, 160));
        lblTotalEntregasConfirmacao.setText("Total de entregas");

        lblNumeroTotalEntregasConfirmacao.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblNumeroTotalEntregasConfirmacao.setText("Nº");

        tblEntregasConfirmacao.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Código da entrega", "Pedido", "Cliente", "Endereço", "Entregador", "Veículo", "Data e horário", "Status da confirmação"
            }
        ));
        jScrollPane19.setViewportView(tblEntregasConfirmacao);

        btnNovoConfirmacao.setBackground(new java.awt.Color(10, 4, 138));
        btnNovoConfirmacao.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        btnNovoConfirmacao.setForeground(new java.awt.Color(255, 255, 255));
        btnNovoConfirmacao.setIcon(new javax.swing.ImageIcon("C:\\Users\\SamaraTavares\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\icones\\botao-mais.png")); // NOI18N
        btnNovoConfirmacao.setText("Novo");

        btnAlterarConfirmacao.setBackground(new java.awt.Color(10, 4, 138));
        btnAlterarConfirmacao.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        btnAlterarConfirmacao.setForeground(new java.awt.Color(255, 255, 255));
        btnAlterarConfirmacao.setIcon(new javax.swing.ImageIcon("C:\\Users\\SamaraTavares\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\icones\\editar.png")); // NOI18N
        btnAlterarConfirmacao.setText("Alterar");

        btnExcluirConfirmacao.setBackground(new java.awt.Color(10, 4, 138));
        btnExcluirConfirmacao.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        btnExcluirConfirmacao.setForeground(new java.awt.Color(255, 255, 255));
        btnExcluirConfirmacao.setIcon(new javax.swing.ImageIcon("C:\\Users\\SamaraTavares\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\icones\\excluir.png")); // NOI18N
        btnExcluirConfirmacao.setText("Excluir");

        btnVoltarConfirmacao.setBackground(new java.awt.Color(10, 4, 138));
        btnVoltarConfirmacao.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        btnVoltarConfirmacao.setForeground(new java.awt.Color(255, 255, 255));
        btnVoltarConfirmacao.setIcon(new javax.swing.ImageIcon("C:\\Users\\SamaraTavares\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\icones\\seta-esquerda (1).png")); // NOI18N
        btnVoltarConfirmacao.setText("Voltar");

        javax.swing.GroupLayout confirmacaoLayout = new javax.swing.GroupLayout(confirmacao);
        confirmacao.setLayout(confirmacaoLayout);
        confirmacaoLayout.setHorizontalGroup(
            confirmacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(confirmacaoLayout.createSequentialGroup()
                .addGroup(confirmacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(confirmacaoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(confirmacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane19)
                            .addGroup(confirmacaoLayout.createSequentialGroup()
                                .addComponent(btnNovoConfirmacao)
                                .addGap(18, 18, 18)
                                .addComponent(btnAlterarConfirmacao)
                                .addGap(18, 18, 18)
                                .addComponent(btnExcluirConfirmacao)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnVoltarConfirmacao, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(confirmacaoLayout.createSequentialGroup()
                        .addGroup(confirmacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(confirmacaoLayout.createSequentialGroup()
                                .addGap(68, 68, 68)
                                .addGroup(confirmacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblEntregasConfirmadas)
                                    .addComponent(lblNumeroEntregasConfirmadas))
                                .addGap(84, 84, 84)
                                .addGroup(confirmacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblAguardandoConfirmacao)
                                    .addComponent(lblNumeorAguardandoConfirmacao))
                                .addGap(100, 100, 100)
                                .addGroup(confirmacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblNaoConfirmada)
                                    .addComponent(lblNumeroNaoConfirmada))
                                .addGap(96, 96, 96)
                                .addGroup(confirmacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblTotalEntregasConfirmacao)
                                    .addComponent(lblNumeroTotalEntregasConfirmacao)))
                            .addGroup(confirmacaoLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(confirmacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(confirmacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(lblTituloConfirmacao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lblPeriodoConfirmacao)
                                        .addComponent(ftfPeriodoConfirmacao))
                                    .addComponent(lblIconeEntregasConfirmadas))
                                .addGroup(confirmacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(confirmacaoLayout.createSequentialGroup()
                                        .addGap(31, 31, 31)
                                        .addGroup(confirmacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblClienteConfirmacao)
                                            .addComponent(txtClienteConfirmacao, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(confirmacaoLayout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(lblIconeAguargandoConfirmacao)))
                                .addGroup(confirmacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(confirmacaoLayout.createSequentialGroup()
                                        .addGap(32, 32, 32)
                                        .addGroup(confirmacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblEntregadorConfirmacao)
                                            .addComponent(cbEntregadorConfirmacao, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(confirmacaoLayout.createSequentialGroup()
                                        .addGap(96, 96, 96)
                                        .addComponent(lblIconeNaoConfirmada)))
                                .addGap(33, 33, 33)
                                .addGroup(confirmacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblStatusConfirmacao)
                                    .addGroup(confirmacaoLayout.createSequentialGroup()
                                        .addGroup(confirmacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(cbStatusConfirmacao, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(confirmacaoLayout.createSequentialGroup()
                                                .addGap(98, 98, 98)
                                                .addComponent(lblIconeTotalEntregasConfirmacao)))
                                        .addGap(30, 30, 30)
                                        .addComponent(btnPesquisarConfirmacao, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(63, 63, 63)))
                .addContainerGap(761, Short.MAX_VALUE))
        );
        confirmacaoLayout.setVerticalGroup(
            confirmacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(confirmacaoLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(lblTituloConfirmacao)
                .addGap(18, 18, 18)
                .addGroup(confirmacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPeriodoConfirmacao)
                    .addComponent(lblClienteConfirmacao)
                    .addComponent(lblEntregadorConfirmacao)
                    .addComponent(lblStatusConfirmacao))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(confirmacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ftfPeriodoConfirmacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtClienteConfirmacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbEntregadorConfirmacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbStatusConfirmacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPesquisarConfirmacao))
                .addGap(29, 29, 29)
                .addGroup(confirmacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEntregasConfirmadas)
                    .addComponent(lblAguardandoConfirmacao)
                    .addComponent(lblNaoConfirmada)
                    .addComponent(lblTotalEntregasConfirmacao))
                .addGap(15, 15, 15)
                .addGroup(confirmacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblIconeEntregasConfirmadas)
                    .addComponent(lblIconeAguargandoConfirmacao)
                    .addComponent(lblIconeNaoConfirmada)
                    .addComponent(lblIconeTotalEntregasConfirmacao))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(confirmacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNumeroEntregasConfirmadas)
                    .addComponent(lblNumeorAguardandoConfirmacao)
                    .addComponent(lblNumeroNaoConfirmada)
                    .addComponent(lblNumeroTotalEntregasConfirmacao))
                .addGap(32, 32, 32)
                .addComponent(jScrollPane19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(confirmacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNovoConfirmacao)
                    .addComponent(btnAlterarConfirmacao)
                    .addComponent(btnExcluirConfirmacao)
                    .addComponent(btnVoltarConfirmacao))
                .addContainerGap(288, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Confirmação", confirmacao);

        historicoRelatorio.setBackground(new java.awt.Color(236, 236, 236));

        lblTituloHistoricoRelatorio.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        lblTituloHistoricoRelatorio.setForeground(new java.awt.Color(10, 4, 138));
        lblTituloHistoricoRelatorio.setText("Histórico e Relatórios");

        lblPeriodoHistoricoRelatorio.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblPeriodoHistoricoRelatorio.setText("Período");

        ftfPeriodoHistoricoRelatorio.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        try {
            ftfPeriodoHistoricoRelatorio.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        ftfPeriodoHistoricoRelatorio.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N

        lblTipoRelatorio.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblTipoRelatorio.setText("Tipo de relatório");

        cbTipoRelatorio.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        cbTipoRelatorio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos" }));

        lblStatusHistoricoRelatorio.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblStatusHistoricoRelatorio.setText("Status");

        cbStatusHistoricoRelatorio.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        cbStatusHistoricoRelatorio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos", "Confirmada", "Em andamento", "Cancelada" }));

        lblEntregadorHistoricoRelatorio.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblEntregadorHistoricoRelatorio.setText("Entregador");

        cbEntregadorHistoricoRelatorio.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        cbEntregadorHistoricoRelatorio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos" }));

        btnPesquisarHistoricoRelatorio.setBackground(new java.awt.Color(10, 4, 138));
        btnPesquisarHistoricoRelatorio.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        btnPesquisarHistoricoRelatorio.setForeground(new java.awt.Color(255, 255, 255));
        btnPesquisarHistoricoRelatorio.setIcon(new javax.swing.ImageIcon("C:\\Users\\SamaraTavares\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\icones\\lupa.png")); // NOI18N
        btnPesquisarHistoricoRelatorio.setText("Pesquisar");

        lblIconeTotalHistoricoRelatorio.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblIconeTotalHistoricoRelatorio.setText("Icone");

        lblTotalHistoricoRelatorio.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        lblTotalHistoricoRelatorio.setForeground(new java.awt.Color(39, 34, 160));
        lblTotalHistoricoRelatorio.setText("Total de entregas");

        lblNumeroTotalHistoricoRelatorio.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblNumeroTotalHistoricoRelatorio.setText("Nº");

        lblIconeEntregasHistoricoRelatorio.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblIconeEntregasHistoricoRelatorio.setText("Icone");

        lblEntregasHistoricoRelatorio.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        lblEntregasHistoricoRelatorio.setForeground(new java.awt.Color(39, 34, 160));
        lblEntregasHistoricoRelatorio.setText("Entregas concluídas");

        lblNumeroEntregasHistoricoRelatorio.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblNumeroEntregasHistoricoRelatorio.setText("Nº");

        lblIconeAndamentoHistoricoRelatorio.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblIconeAndamentoHistoricoRelatorio.setText("Icone");

        lblAndamentoHistoricoRelatorio.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        lblAndamentoHistoricoRelatorio.setForeground(new java.awt.Color(39, 34, 160));
        lblAndamentoHistoricoRelatorio.setText("Em andamento");

        lblNumeroAndamentoHistoricoRelatorio.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblNumeroAndamentoHistoricoRelatorio.setText("Nº");

        lblIconeCanceladaHistoricoRelatorio.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblIconeCanceladaHistoricoRelatorio.setText("Icone");

        lblCanceladaHistoricoRelatorio.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        lblCanceladaHistoricoRelatorio.setForeground(new java.awt.Color(39, 34, 160));
        lblCanceladaHistoricoRelatorio.setText("Canceladas");

        lblNumeroCanceladaHistoricoRelatorio.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblNumeroCanceladaHistoricoRelatorio.setText("Nº");

        lblIconeTempoHistoricoRelatorio.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblIconeTempoHistoricoRelatorio.setText("Icone");

        lblTempoHistoricoRelatorio.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        lblTempoHistoricoRelatorio.setForeground(new java.awt.Color(39, 34, 160));
        lblTempoHistoricoRelatorio.setText("Tempo médio de entrega");

        lblNumeroTempoHistoricoRelatorio.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblNumeroTempoHistoricoRelatorio.setText("Nº");

        tblHistoricoEntregasRelatorio.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        tblHistoricoEntregasRelatorio.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Código", "Pedido", "Cliente", "Endereço", "Entregador", "Veículo", "Data e Horário", "Status"
            }
        ));
        jScrollPane20.setViewportView(tblHistoricoEntregasRelatorio);

        btnRelatorioEntregas.setBackground(new java.awt.Color(10, 4, 138));
        btnRelatorioEntregas.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        btnRelatorioEntregas.setForeground(new java.awt.Color(255, 255, 255));
        btnRelatorioEntregas.setText("Relatório de entregas");

        btnRelatorioEntregador.setBackground(new java.awt.Color(10, 4, 138));
        btnRelatorioEntregador.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        btnRelatorioEntregador.setForeground(new java.awt.Color(255, 255, 255));
        btnRelatorioEntregador.setText("Relatório por entregador");

        btnRelatorioRegiao.setBackground(new java.awt.Color(10, 4, 138));
        btnRelatorioRegiao.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        btnRelatorioRegiao.setForeground(new java.awt.Color(255, 255, 255));
        btnRelatorioRegiao.setText("Relatório por região");

        btnRelatorioFinanceiro.setBackground(new java.awt.Color(10, 4, 138));
        btnRelatorioFinanceiro.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        btnRelatorioFinanceiro.setForeground(new java.awt.Color(255, 255, 255));
        btnRelatorioFinanceiro.setText("Relatótio financeiro");

        btnRelatorioStatus.setBackground(new java.awt.Color(10, 4, 138));
        btnRelatorioStatus.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        btnRelatorioStatus.setForeground(new java.awt.Color(255, 255, 255));
        btnRelatorioStatus.setText("Relatório de status");

        btnExportarRelatorio.setBackground(new java.awt.Color(10, 4, 138));
        btnExportarRelatorio.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        btnExportarRelatorio.setForeground(new java.awt.Color(255, 255, 255));
        btnExportarRelatorio.setText("Exportar relatório");

        javax.swing.GroupLayout historicoRelatorioLayout = new javax.swing.GroupLayout(historicoRelatorio);
        historicoRelatorio.setLayout(historicoRelatorioLayout);
        historicoRelatorioLayout.setHorizontalGroup(
            historicoRelatorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(historicoRelatorioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(historicoRelatorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTituloHistoricoRelatorio)
                    .addComponent(lblIconeTotalHistoricoRelatorio)
                    .addGroup(historicoRelatorioLayout.createSequentialGroup()
                        .addGroup(historicoRelatorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblPeriodoHistoricoRelatorio)
                            .addComponent(ftfPeriodoHistoricoRelatorio, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblTotalHistoricoRelatorio)
                            .addGroup(historicoRelatorioLayout.createSequentialGroup()
                                .addGap(62, 62, 62)
                                .addComponent(lblNumeroTotalHistoricoRelatorio)))
                        .addGroup(historicoRelatorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(historicoRelatorioLayout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addGroup(historicoRelatorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblTipoRelatorio)
                                    .addComponent(cbTipoRelatorio, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblEntregasHistoricoRelatorio)
                                    .addComponent(lblIconeEntregasHistoricoRelatorio)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, historicoRelatorioLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblNumeroEntregasHistoricoRelatorio)
                                .addGap(111, 111, 111)))
                        .addGroup(historicoRelatorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(historicoRelatorioLayout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addGroup(historicoRelatorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblStatusHistoricoRelatorio)
                                    .addComponent(cbStatusHistoricoRelatorio, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(30, 30, 30)
                                .addGroup(historicoRelatorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblEntregadorHistoricoRelatorio)
                                    .addGroup(historicoRelatorioLayout.createSequentialGroup()
                                        .addComponent(cbEntregadorHistoricoRelatorio, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btnPesquisarHistoricoRelatorio))))
                            .addGroup(historicoRelatorioLayout.createSequentialGroup()
                                .addGroup(historicoRelatorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(historicoRelatorioLayout.createSequentialGroup()
                                        .addGap(53, 53, 53)
                                        .addGroup(historicoRelatorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblIconeAndamentoHistoricoRelatorio)
                                            .addComponent(lblAndamentoHistoricoRelatorio)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, historicoRelatorioLayout.createSequentialGroup()
                                        .addComponent(lblNumeroAndamentoHistoricoRelatorio)
                                        .addGap(67, 67, 67)))
                                .addGroup(historicoRelatorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(historicoRelatorioLayout.createSequentialGroup()
                                        .addGap(65, 65, 65)
                                        .addGroup(historicoRelatorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblIconeCanceladaHistoricoRelatorio)
                                            .addComponent(lblCanceladaHistoricoRelatorio))
                                        .addGap(13, 13, 13))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, historicoRelatorioLayout.createSequentialGroup()
                                        .addComponent(lblNumeroCanceladaHistoricoRelatorio)
                                        .addGap(49, 49, 49)))
                                .addGroup(historicoRelatorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(historicoRelatorioLayout.createSequentialGroup()
                                        .addGap(64, 64, 64)
                                        .addGroup(historicoRelatorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblIconeTempoHistoricoRelatorio)
                                            .addComponent(lblTempoHistoricoRelatorio)))
                                    .addGroup(historicoRelatorioLayout.createSequentialGroup()
                                        .addGap(127, 127, 127)
                                        .addComponent(lblNumeroTempoHistoricoRelatorio))))))
                    .addGroup(historicoRelatorioLayout.createSequentialGroup()
                        .addGroup(historicoRelatorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnRelatorioFinanceiro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnRelatorioStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnRelatorioEntregas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnRelatorioEntregador, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnRelatorioRegiao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnExportarRelatorio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane20, javax.swing.GroupLayout.PREFERRED_SIZE, 815, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(912, Short.MAX_VALUE))
        );
        historicoRelatorioLayout.setVerticalGroup(
            historicoRelatorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(historicoRelatorioLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(lblTituloHistoricoRelatorio)
                .addGap(18, 18, 18)
                .addGroup(historicoRelatorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPeriodoHistoricoRelatorio)
                    .addComponent(lblTipoRelatorio)
                    .addComponent(lblStatusHistoricoRelatorio)
                    .addComponent(lblEntregadorHistoricoRelatorio))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(historicoRelatorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ftfPeriodoHistoricoRelatorio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbTipoRelatorio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbStatusHistoricoRelatorio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbEntregadorHistoricoRelatorio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPesquisarHistoricoRelatorio))
                .addGap(26, 26, 26)
                .addGroup(historicoRelatorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTotalHistoricoRelatorio)
                    .addComponent(lblEntregasHistoricoRelatorio)
                    .addComponent(lblAndamentoHistoricoRelatorio)
                    .addComponent(lblCanceladaHistoricoRelatorio)
                    .addComponent(lblTempoHistoricoRelatorio))
                .addGap(16, 16, 16)
                .addGroup(historicoRelatorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(historicoRelatorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblIconeCanceladaHistoricoRelatorio)
                        .addComponent(lblIconeAndamentoHistoricoRelatorio)
                        .addComponent(lblIconeTempoHistoricoRelatorio))
                    .addGroup(historicoRelatorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblIconeTotalHistoricoRelatorio)
                        .addComponent(lblIconeEntregasHistoricoRelatorio)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(historicoRelatorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblNumeroTotalHistoricoRelatorio)
                    .addComponent(lblNumeroAndamentoHistoricoRelatorio)
                    .addComponent(lblNumeroCanceladaHistoricoRelatorio)
                    .addGroup(historicoRelatorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblNumeroTempoHistoricoRelatorio)
                        .addComponent(lblNumeroEntregasHistoricoRelatorio)))
                .addGap(27, 27, 27)
                .addGroup(historicoRelatorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(historicoRelatorioLayout.createSequentialGroup()
                        .addComponent(btnRelatorioEntregas, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnRelatorioEntregador, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnRelatorioRegiao, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnRelatorioFinanceiro, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnRelatorioStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnExportarRelatorio, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(348, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Histórico e Relatórios", historicoRelatorio);

        getContentPane().add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 2010, 1080));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void cbEstadoEnderecosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbEstadoEnderecosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbEstadoEnderecosActionPerformed

    private void txtModeloVeiculoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtModeloVeiculoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtModeloVeiculoActionPerformed

    private void txtValorTaxaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtValorTaxaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtValorTaxaActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
          Usuario usuario = new Usuario();
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
      //  Usuario usuario = new Usuario("", "", null);

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new TelaEntregas(usuario).setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel agenda;
    private javax.swing.JButton btnAlterarAgenda;
    private javax.swing.JButton btnAlterarConfirmacao;
    private javax.swing.JButton btnAlterarDataHorario;
    private javax.swing.JButton btnAlterarDistribuicao;
    private javax.swing.JButton btnAlterarEndercos;
    private javax.swing.JButton btnAlterarEntregadores;
    private javax.swing.JButton btnAlterarEntregas;
    private javax.swing.JButton btnAlterarRegioes;
    private javax.swing.JButton btnAlterarStatus;
    private javax.swing.JButton btnAlterarTaxas;
    private javax.swing.JButton btnAlterarTentativas;
    private javax.swing.JButton btnAlterarVeiculos;
    private javax.swing.JButton btnExcluirAgenda;
    private javax.swing.JButton btnExcluirConfirmacao;
    private javax.swing.JButton btnExcluirDataHorario;
    private javax.swing.JButton btnExcluirDistribuicao;
    private javax.swing.JButton btnExcluirEnderecos;
    private javax.swing.JButton btnExcluirEntregadores;
    private javax.swing.JButton btnExcluirEntregas;
    private javax.swing.JButton btnExcluirRegioes;
    private javax.swing.JButton btnExcluirStatus;
    private javax.swing.JButton btnExcluirTaxas;
    private javax.swing.JButton btnExcluirTentativas;
    private javax.swing.JButton btnExcluirVeiculos;
    private javax.swing.JButton btnExportarRelatorio;
    private javax.swing.JButton btnNovoAgenda;
    private javax.swing.JButton btnNovoConfirmacao;
    private javax.swing.JButton btnNovoDataHorario;
    private javax.swing.JButton btnNovoDistribuicao;
    private javax.swing.JButton btnNovoEnderecos;
    private javax.swing.JButton btnNovoEntregadores;
    private javax.swing.JButton btnNovoEntregas;
    private javax.swing.JButton btnNovoRegioes;
    private javax.swing.JButton btnNovoStatus;
    private javax.swing.JButton btnNovoTaxas;
    private javax.swing.JButton btnNovoTentativas;
    private javax.swing.JButton btnNovoVeiculos;
    private javax.swing.JButton btnPesquisarAgenda;
    private javax.swing.JButton btnPesquisarConfirmacao;
    private javax.swing.JButton btnPesquisarDataHorario;
    private javax.swing.JButton btnPesquisarEnderecos;
    private javax.swing.JButton btnPesquisarEntregas;
    private javax.swing.JButton btnPesquisarHistoricoRelatorio;
    private javax.swing.JButton btnPesquisarTentativas;
    private javax.swing.JButton btnRelatorioEntregador;
    private javax.swing.JButton btnRelatorioEntregas;
    private javax.swing.JButton btnRelatorioFinanceiro;
    private javax.swing.JButton btnRelatorioRegiao;
    private javax.swing.JButton btnRelatorioStatus;
    private javax.swing.JButton btnSalvaEntregadores;
    private javax.swing.JButton btnSalvarDistribuicao;
    private javax.swing.JButton btnSalvarEnderecos;
    private javax.swing.JButton btnSalvarEntregas;
    private javax.swing.JButton btnSalvarRegioes;
    private javax.swing.JButton btnSalvarTaxas;
    private javax.swing.JButton btnSalvarVeiculos;
    private javax.swing.JButton btnVoltarAgenda;
    private javax.swing.JButton btnVoltarConfirmacao;
    private javax.swing.JButton btnVoltarDataHorario;
    private javax.swing.JButton btnVoltarDistribuicao;
    private javax.swing.JButton btnVoltarEntregas;
    private javax.swing.JButton btnVoltarStatus;
    private javax.swing.JButton btnVoltarTentativas;
    private javax.swing.JComboBox<String> cbEntregadorAgenda;
    private javax.swing.JComboBox<String> cbEntregadorConfirmacao;
    private javax.swing.JComboBox<String> cbEntregadorDataHorario;
    private javax.swing.JComboBox<String> cbEntregadorDistribuicao;
    private javax.swing.JComboBox<String> cbEntregadorEntregas;
    private javax.swing.JComboBox<String> cbEntregadorHistoricoRelatorio;
    private javax.swing.JComboBox<String> cbEntregadorStatus;
    private javax.swing.JComboBox<String> cbEntregadorTentativas;
    private javax.swing.JComboBox<String> cbEstadoEnderecos;
    private javax.swing.JComboBox<String> cbEstadoEntregadores;
    private javax.swing.JComboBox<String> cbRegiaoAgenda;
    private javax.swing.JComboBox<String> cbRegiaoDistribuicao;
    private javax.swing.JComboBox<String> cbRegiaoTaxas;
    private javax.swing.JComboBox<String> cbStatus;
    private javax.swing.JComboBox<String> cbStatusConfirmacao;
    private javax.swing.JComboBox<String> cbStatusDataHorario;
    private javax.swing.JComboBox<String> cbStatusDistribuicao;
    private javax.swing.JComboBox<String> cbStatusEntrega;
    private javax.swing.JComboBox<String> cbStatusEntregadores;
    private javax.swing.JComboBox<String> cbStatusHistoricoRelatorio;
    private javax.swing.JComboBox<String> cbStatusTentativas;
    private javax.swing.JComboBox<String> cbStatusVeiculo;
    private javax.swing.JComboBox<String> cbTipoRelatorio;
    private javax.swing.JComboBox<String> cbTipoTaxa;
    private javax.swing.JComboBox<String> cbVeiculoAgenda;
    private javax.swing.JComboBox<String> cbVeiculoDistribuicao;
    private javax.swing.JComboBox<String> cbVeiculoEntregas;
    private javax.swing.JComboBox<String> cdMotorista;
    private javax.swing.JPanel confirmacao;
    private javax.swing.JPanel dataHorario;
    private javax.swing.JPanel distribuicao;
    private javax.swing.JPanel endereço;
    private javax.swing.JPanel entregadores;
    private javax.swing.JPanel entregas;
    private javax.swing.JFormattedTextField ftfAnoVeiculo;
    private javax.swing.JFormattedTextField ftfCnh;
    private javax.swing.JFormattedTextField ftfCpf;
    private javax.swing.JFormattedTextField ftfDataAgenda;
    private javax.swing.JFormattedTextField ftfDataDistribuicao;
    private javax.swing.JFormattedTextField ftfDataEntrega;
    private javax.swing.JFormattedTextField ftfDataFinal;
    private javax.swing.JFormattedTextField ftfDataInicial;
    private javax.swing.JFormattedTextField ftfDataNascimento;
    private javax.swing.JFormattedTextField ftfHorarioDistribuicao;
    private javax.swing.JFormattedTextField ftfHorarioEntrega;
    private javax.swing.JFormattedTextField ftfHorarioInicial;
    private javax.swing.JFormattedTextField ftfPeriodoConfirmacao;
    private javax.swing.JFormattedTextField ftfPeriodoHistoricoRelatorio;
    private javax.swing.JFormattedTextField ftfPeriodoStatus;
    private javax.swing.JFormattedTextField ftfPeriodoTentativas;
    private javax.swing.JFormattedTextField ftfTelefone;
    private javax.swing.JFormattedTextField ftfVencimentoCnh;
    private javax.swing.JPanel historicoRelatorio;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane17;
    private javax.swing.JScrollPane jScrollPane18;
    private javax.swing.JScrollPane jScrollPane19;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane20;
    private javax.swing.JScrollPane jScrollPane21;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel labBairro;
    private javax.swing.JLabel lblAguardandoConfirmacao;
    private javax.swing.JLabel lblAguardandoTentativas;
    private javax.swing.JLabel lblAndamento;
    private javax.swing.JLabel lblAndamentoAgenda;
    private javax.swing.JLabel lblAndamentoHistoricoRelatorio;
    private javax.swing.JLabel lblAno;
    private javax.swing.JLabel lblBairroEnderecos;
    private javax.swing.JLabel lblBairroRegiao;
    private javax.swing.JLabel lblCalendarioAgenda;
    private javax.swing.JLabel lblCanceladaHistoricoRelatorio;
    private javax.swing.JLabel lblCanceladasTentativas;
    private javax.swing.JLabel lblCanceladosStatus;
    private javax.swing.JLabel lblCarga;
    private javax.swing.JLabel lblCep;
    private javax.swing.JLabel lblCepEnderecos;
    private javax.swing.JLabel lblCidade;
    private javax.swing.JLabel lblCliente;
    private javax.swing.JLabel lblClienteConfirmacao;
    private javax.swing.JLabel lblClienteDistribuicao;
    private javax.swing.JLabel lblClienteEnderecos;
    private javax.swing.JLabel lblClienteEntregas;
    private javax.swing.JLabel lblClienteTentativas;
    private javax.swing.JLabel lblCnh;
    private javax.swing.JLabel lblCodigoRegiao;
    private javax.swing.JLabel lblConcluidas;
    private javax.swing.JLabel lblConcluidasAgenda;
    private javax.swing.JLabel lblConcluidosStatus;
    private javax.swing.JLabel lblCor;
    private javax.swing.JLabel lblCpf;
    private javax.swing.JLabel lblDataAgenda;
    private javax.swing.JLabel lblDataDistribuicao;
    private javax.swing.JLabel lblDataEntregas;
    private javax.swing.JLabel lblDataFinal;
    private javax.swing.JLabel lblDataInicial;
    private javax.swing.JLabel lblDataNascimento;
    private javax.swing.JLabel lblDocumentacao;
    private javax.swing.JLabel lblEnderecoDistribuicao;
    private javax.swing.JLabel lblEntregadorAgenda;
    private javax.swing.JLabel lblEntregadorConfirmacao;
    private javax.swing.JLabel lblEntregadorDataHorario;
    private javax.swing.JLabel lblEntregadorDistribuicao;
    private javax.swing.JLabel lblEntregadorEntregas;
    private javax.swing.JLabel lblEntregadorHistoricoRelatorio;
    private javax.swing.JLabel lblEntregadorStatus;
    private javax.swing.JLabel lblEntregadorTentativas;
    private javax.swing.JLabel lblEntregasConfirmadas;
    private javax.swing.JLabel lblEntregasDiaDH;
    private javax.swing.JLabel lblEntregasHistoricoRelatorio;
    private javax.swing.JLabel lblEntreguesTentativas;
    private javax.swing.JLabel lblEnviadosStatus;
    private javax.swing.JLabel lblEstadoEnderecos;
    private javax.swing.JLabel lblEstadoEntregadores;
    private javax.swing.JLabel lblHoraEntregas;
    private javax.swing.JLabel lblHorarioEntregaDistribuicao;
    private javax.swing.JLabel lblHorarioFinal;
    private javax.swing.JLabel lblHorarioInicial;
    private javax.swing.JLabel lblIconeAguardandoTentativas;
    private javax.swing.JLabel lblIconeAguargandoConfirmacao;
    private javax.swing.JLabel lblIconeAndamento;
    private javax.swing.JLabel lblIconeAndamentoAgenda;
    private javax.swing.JLabel lblIconeAndamentoHistoricoRelatorio;
    private javax.swing.JLabel lblIconeCanceladaHistoricoRelatorio;
    private javax.swing.JLabel lblIconeCanceladasTentativas;
    private javax.swing.JLabel lblIconeCanceladosStatus;
    private javax.swing.JLabel lblIconeConcluidas;
    private javax.swing.JLabel lblIconeConcluidasAgenda;
    private javax.swing.JLabel lblIconeConcluídos;
    private javax.swing.JLabel lblIconeEntregasAgenda;
    private javax.swing.JLabel lblIconeEntregasConfirmadas;
    private javax.swing.JLabel lblIconeEntregasDia;
    private javax.swing.JLabel lblIconeEntregasHistoricoRelatorio;
    private javax.swing.JLabel lblIconeEntreguesTentativas;
    private javax.swing.JLabel lblIconeEnviadosStatus;
    private javax.swing.JLabel lblIconeNaoConfirmada;
    private javax.swing.JLabel lblIconePagamentoStatus;
    private javax.swing.JLabel lblIconeProntosStatus;
    private javax.swing.JLabel lblIconeSeparacaoStatus;
    private javax.swing.JLabel lblIconeTempoHistoricoRelatorio;
    private javax.swing.JLabel lblIconeTotalEntregas1;
    private javax.swing.JLabel lblIconeTotalEntregasConfirmacao;
    private javax.swing.JLabel lblIconeTotalHistoricoRelatorio;
    private javax.swing.JLabel lblIconeTotalTentativas;
    private javax.swing.JLabel lblIconrPendentesAgenda;
    private javax.swing.JLabel lblMarca;
    private javax.swing.JLabel lblModelo;
    private javax.swing.JLabel lblMotorista;
    private javax.swing.JLabel lblNUmeroAndamentoAgenda;
    private javax.swing.JLabel lblNaoConfirmada;
    private javax.swing.JLabel lblNome;
    private javax.swing.JLabel lblNomeRegiao;
    private javax.swing.JLabel lblNumeorAguardandoConfirmacao;
    private javax.swing.JLabel lblNumero;
    private javax.swing.JLabel lblNumeroAguardandoTentativas;
    private javax.swing.JLabel lblNumeroAndamentoHistoricoRelatorio;
    private javax.swing.JLabel lblNumeroCanceladaHistoricoRelatorio;
    private javax.swing.JLabel lblNumeroCanceladasTentativas;
    private javax.swing.JLabel lblNumeroCanceladosStatus;
    private javax.swing.JLabel lblNumeroConcluidas;
    private javax.swing.JLabel lblNumeroConcluidasAgenda;
    private javax.swing.JLabel lblNumeroConcluidosStatus;
    private javax.swing.JLabel lblNumeroEnderecos;
    private javax.swing.JLabel lblNumeroEntregasAgenda;
    private javax.swing.JLabel lblNumeroEntregasConfirmadas;
    private javax.swing.JLabel lblNumeroEntregasDiaDH;
    private javax.swing.JLabel lblNumeroEntregasHistoricoRelatorio;
    private javax.swing.JLabel lblNumeroEntreguesTentativas;
    private javax.swing.JLabel lblNumeroEnviadosStatus;
    private javax.swing.JLabel lblNumeroNaoConfirmada;
    private javax.swing.JLabel lblNumeroPagamentoStatus;
    private javax.swing.JLabel lblNumeroPendentesAgenda;
    private javax.swing.JLabel lblNumeroProntosStatus;
    private javax.swing.JLabel lblNumeroSeparacaoStatus;
    private javax.swing.JLabel lblNumeroTempoHistoricoRelatorio;
    private javax.swing.JLabel lblNumeroTotalDH1;
    private javax.swing.JLabel lblNumeroTotalEntregasConfirmacao;
    private javax.swing.JLabel lblNumeroTotalHistoricoRelatorio;
    private javax.swing.JLabel lblNumeroTotalTentativas;
    private javax.swing.JLabel lblObservacoesDistribuicao;
    private javax.swing.JLabel lblObservacoesRegioes;
    private javax.swing.JLabel lblObservacoesTaxas;
    private javax.swing.JLabel lblObservacoesVeiculos;
    private javax.swing.JLabel lblPedidoDistribuicao;
    private javax.swing.JLabel lblPendentesAgenda;
    private javax.swing.JLabel lblPeriodoConfirmacao;
    private javax.swing.JLabel lblPeriodoHistoricoRelatorio;
    private javax.swing.JLabel lblPeriodoStatus;
    private javax.swing.JLabel lblPeriodoTentativas;
    private javax.swing.JLabel lblPlaca;
    private javax.swing.JLabel lblProntosStatus;
    private javax.swing.JLabel lblRegiaoDistribuicao;
    private javax.swing.JLabel lblRegiaoTaxas;
    private javax.swing.JLabel lblRegiãoAgenda;
    private javax.swing.JLabel lblRua;
    private javax.swing.JLabel lblRuaEnderecos;
    private javax.swing.JLabel lblSeparacaoStatus;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JLabel lblStatusConfirmacao;
    private javax.swing.JLabel lblStatusDataHorario;
    private javax.swing.JLabel lblStatusDistribuicao;
    private javax.swing.JLabel lblStatusEntregadores;
    private javax.swing.JLabel lblStatusEntregas;
    private javax.swing.JLabel lblStatusHistoricoRelatorio;
    private javax.swing.JLabel lblStatusTentativas;
    private javax.swing.JLabel lblStatusVeiculos;
    private javax.swing.JLabel lblTaxaEntregas;
    private javax.swing.JLabel lblTelefone;
    private javax.swing.JLabel lblTempoHistoricoRelatorio;
    private javax.swing.JLabel lblTipoRegiao;
    private javax.swing.JLabel lblTipoRelatorio;
    private javax.swing.JLabel lblTipoTaxa;
    private javax.swing.JLabel lblTipoVeiculo;
    private javax.swing.JLabel lblTituloAgenda;
    private javax.swing.JLabel lblTituloConfirmacao;
    private javax.swing.JLabel lblTituloDistribuicao;
    private javax.swing.JLabel lblTituloEntrega;
    private javax.swing.JLabel lblTituloHistoricoRelatorio;
    private javax.swing.JLabel lblTituloStatus;
    private javax.swing.JLabel lblTituloTentativas;
    private javax.swing.JLabel lblTotalEntregasAgenda;
    private javax.swing.JLabel lblTotalEntregasConfirmacao;
    private javax.swing.JLabel lblTotalEntregasDH1;
    private javax.swing.JLabel lblTotalHistoricoRelatorio;
    private javax.swing.JLabel lblTotalTentativas;
    private javax.swing.JLabel lblValorTaxaRegiao;
    private javax.swing.JLabel lblValorTaxas;
    private javax.swing.JLabel lblVeiculoAgenda;
    private javax.swing.JLabel lblVeiculoDistribuicao;
    private javax.swing.JLabel lblVeiculoEntregas;
    private javax.swing.JLabel lblVencimentoCnh;
    private javax.swing.JLabel lbltituloDataHorario;
    private javax.swing.JLabel lbltituloEntregas;
    private javax.swing.JLabel lnlNumeroAndamento;
    private javax.swing.JPanel regiao;
    private javax.swing.JPanel status;
    private javax.swing.JPanel taxas;
    private javax.swing.JTable tblAgendaEntregas;
    private javax.swing.JTable tblEntregadores;
    private javax.swing.JTable tblEntregas;
    private javax.swing.JTable tblEntregasConfirmacao;
    private javax.swing.JTable tblEntregasStatus;
    private javax.swing.JTable tblHistoricoEntregasRelatorio;
    private javax.swing.JTable tblListaDistribuicao;
    private javax.swing.JTable tblListaEnderecos;
    private javax.swing.JTable tblListaTaxas;
    private javax.swing.JTable tblRegioes;
    private javax.swing.JTable tblTentativasEntrega;
    private javax.swing.JTable tblVeiculos;
    private javax.swing.JPanel tentativas;
    private javax.swing.JLabel tituloDadosEntregadores;
    private javax.swing.JLabel tituloEndereco;
    private javax.swing.JLabel tituloRegiao;
    private javax.swing.JLabel tituloVeiculo;
    private javax.swing.JTextField txtBairro;
    private javax.swing.JTextField txtBairroEnderecos;
    private javax.swing.JFormattedTextField txtCapacidadeCarga;
    private javax.swing.JTextField txtCep;
    private javax.swing.JTextField txtCepEnderecos;
    private javax.swing.JTextField txtCidade;
    private javax.swing.JTextField txtCidadeEnderecos;
    private javax.swing.JTextField txtClienteConfirmacao;
    private javax.swing.JTextField txtClienteDistribuicao;
    private javax.swing.JTextField txtClienteEnderecos;
    private javax.swing.JTextField txtClienteEntregas;
    private javax.swing.JTextField txtClienteStatus;
    private javax.swing.JTextField txtClienteTentativas;
    private javax.swing.JTextField txtCorVeiculo;
    private javax.swing.JFormattedTextField txtHorarioFinal;
    private javax.swing.JTextField txtMarcaVeiculo;
    private javax.swing.JTextField txtModeloVeiculo;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtNomeRegiao;
    private javax.swing.JTextField txtNumero;
    private javax.swing.JTextField txtNumeroEnderecos;
    private javax.swing.JTextArea txtObservacaoTaxa;
    private javax.swing.JTextArea txtObservacoesVeiculo;
    private javax.swing.JTextField txtPedidoDistribuicao;
    private javax.swing.JTextField txtPlacaVeiculo;
    private javax.swing.JTextField txtRua;
    private javax.swing.JTextField txtRuaEnderecos;
    private javax.swing.JTextField txtTaxaEntrega;
    private javax.swing.JTextField txtTipoVeiculo;
    private javax.swing.JTextField txtValorTaxa;
    private javax.swing.JTextField txtValorTaxaRegiao;
    private javax.swing.JTextArea txtaBairroRegiao;
    private javax.swing.JTextArea txtaEnderecoDistribuicao;
    private javax.swing.JTextArea txtaObservacoesDistribuicao;
    private javax.swing.JTextArea txtaObservacoesRegiao;
    private javax.swing.JPanel veiculos;
    // End of variables declaration//GEN-END:variables
}
