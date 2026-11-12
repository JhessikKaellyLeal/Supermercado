/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import Conection.Conexao;
import Control.CodigoBarrasController;
import Model.Usuario;
import View.MenuPadrao;
import View.TelaHome;
import View.TelaPerfilUsuarios;

import View.Tela_Funcionarios_RH;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author KalebeMorais
 */
public class TelaPdvRealizacao extends javax.swing.JFrame {

    
private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(TelaPdvRealizacao.class.getName());
private CodigoBarrasController codigoBarrasController = new CodigoBarrasController();

    private MenuPadrao menu;  
    private Usuario usuario;

public TelaPdvRealizacao(Usuario usuario) {
    initComponents();
    
    tfCodigoBarras.addActionListener(e -> {
    adicionarProdutoPorCodigoBarras();});
    
    this.usuario= usuario;
        this.menu=MenuPadrao.instalar(
        this,
        usuario,
        "pdv",
        this::navegar,
        this::dispose
    );
        this.setExtendedState(TelaPdvRealizacao.MAXIMIZED_BOTH);
        
        
}

// MÉTODO DE ACESSAR AS TELAS
public void navegar(String idtela) {

    switch (idtela) {

        case "inicio":
            menu.abrirTela(this, new TelaHome(usuario));
            break;

        case "gerenciamentoProdutos":
            menu.abrirTela(this, new TelaGerenciamentoProdutos(usuario));
            break;

        case "promocoes":
            menu.abrirTela(this, new TelaPromocoes(usuario));
            break;

        case "estoque":
            menu.abrirTela(this, new TelaEstoque(usuario));
            break;

        case "fornecedoresTerceirizados":
            menu.abrirTela(this, new TelaFornecedoresTerceirizados(usuario));
            break;

        case "clientes":
            menu.abrirTela(this, new TelasClientes(usuario));
            break;

        case "pedidos":
            menu.abrirTela(this, new TelaPedidos(usuario));
            break;

        case "entregas":
            menu.abrirTela(this, new TelaEntregas(usuario));
            break;

        case "pdvRealizacao":
            menu.abrirTela(this, new TelaPdvRealizacao(usuario));
            break;

        case "vendas":
            menu.abrirTela(this, new TelaVendas(usuario));
            break;

        case "caixa":
            menu.abrirTela(this, new TelaCaixa(usuario));
            break;

        case "financeiro":
            menu.abrirTela(this, new TelaFinanceiro(usuario));
            break;

        case "funcionariosRH":
            menu.abrirTela(this, new Tela_Funcionarios_RH(usuario));
            break;

        case "perfilUsuarios":
            menu.abrirTela(this, new TelaPerfilUsuarios(usuario));
            break;

        case "solicitacao":
            menu.abrirTela(this, new TelaSolicitacao(usuario));
            break;

        case "alterarSenha":
            menu.abrirTela(this, new AlterarSenha(usuario));
            break;

        case "recuperacaoSenha":
            // Tela utilizada no fluxo de recuperação de senha.
            // menu.abrirTela(this, new RecuperacaoSenha());
            break;

        case "sair":
        case "logout":
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


private void adicionarProdutoPorCodigoBarras() {

    String codigo = tfCodigoBarras.getText().trim();

    if (codigo.isEmpty()) {
        JOptionPane.showMessageDialog(
            this,
            "Informe ou leia o código de barras!",
            "Atenção",
            JOptionPane.WARNING_MESSAGE
        );
        return;
    }

    String sql = "SELECT p.idprodutos, p.nome, p.precoVenda, p.estoque, cb.codigo "
            + "FROM codigoBarras cb "
            + "INNER JOIN produtos p "
            + "ON cb.produtos_idprodutos = p.idprodutos "
            + "WHERE cb.codigo = ? "
            + "AND cb.status = 1 "
            + "AND p.situacao = 1";

    try (Connection conn = Conexao.conectar();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, codigo);

        try (ResultSet rs = stmt.executeQuery()) {

            if (!rs.next()) {
                JOptionPane.showMessageDialog(
                    this,
                    "Produto não encontrado ou inativo!",
                    "Produto não encontrado",
                    JOptionPane.WARNING_MESSAGE
                );
                tfCodigoBarras.setText("");
                tfCodigoBarras.requestFocus();
                return;
            }

            int idProduto = rs.getInt("idprodutos");
            String nome = rs.getString("nome");
            double preco = rs.getDouble("precoVenda");
            int estoque = rs.getInt("estoque");

            DefaultTableModel modelo =
                (DefaultTableModel) tbItensVenda.getModel();

            // Verificar se o produto já está na tabela
            for (int i = 0; i < modelo.getRowCount(); i++) {

                int idExistente = Integer.parseInt(
                    modelo.getValueAt(i, 0).toString()
                );

                if (idExistente == idProduto) {

                    int quantidade = Integer.parseInt(
                        modelo.getValueAt(i, 3).toString()
                    );

                    if (quantidade + 1 > estoque) {
                        JOptionPane.showMessageDialog(
                            this,
                            "Estoque insuficiente!",
                            "Atenção",
                            JOptionPane.WARNING_MESSAGE
                        );
                        return;
                    }

                    quantidade++;

                    modelo.setValueAt(quantidade, i, 3);
                    modelo.setValueAt(
                        quantidade * preco,
                        i,
                        5
                    );

                    atualizarTotalVenda();
                    tfCodigoBarras.setText("");
                    tfCodigoBarras.requestFocus();
                    return;
                }
            }

            // Verificar disponibilidade no estoque
            if (estoque <= 0) {
                JOptionPane.showMessageDialog(
                    this,
                    "Este produto não possui estoque disponível!",
                    "Estoque insuficiente",
                    JOptionPane.WARNING_MESSAGE
                );
                return;
            }

            // Adicionar novo produto
            modelo.addRow(new Object[]{
                idProduto,
                codigo,
                nome,
                1,
                preco,
                preco
            });

            atualizarTotalVenda();

            tfCodigoBarras.setText("");
            tfCodigoBarras.requestFocus();
        }

    } catch (Exception e) {
        JOptionPane.showMessageDialog(
            this,
            "Erro ao adicionar produto: " + e.getMessage(),
            "Erro",
            JOptionPane.ERROR_MESSAGE
        );
    }
}

private void atualizarTotalVenda() {

    DefaultTableModel modelo =
        (DefaultTableModel) tbItensVenda.getModel();

    double total = 0;
    int quantidadeTotal = 0;

    for (int i = 0; i < modelo.getRowCount(); i++) {

        int quantidade = Integer.parseInt(
            modelo.getValueAt(i, 3).toString()
        );

        double valor = Double.parseDouble(
            modelo.getValueAt(i, 5).toString()
        );

        quantidadeTotal += quantidade;
        total += valor;
    }

    ftfTotalItens.setValue(quantidadeTotal);
    ftfValorToTal.setValue(total);
}


private void finalizarVenda() {

    DefaultTableModel modelo =
            (DefaultTableModel) tbItensVenda.getModel();

    // Verificar se existem produtos na venda
    if (modelo.getRowCount() == 0) {
        JOptionPane.showMessageDialog(
                this,
                "Adicione pelo menos um produto à venda!",
                "Atenção",
                JOptionPane.WARNING_MESSAGE
        );
        return;
    }

    Connection conn = null;

    try {

        // Capturar valores da venda
        double valorTotal = Double.parseDouble(
    ftfValorToTal.getValue().toString().replace(",", ".")
);

double valorRecebido = Double.parseDouble(
    ftfValorRecebido.getValue().toString().replace(",", ".")
);
        if (valorTotal <= 0) {
            JOptionPane.showMessageDialog(
                    this,
                    "O valor total da venda é inválido!"
            );
            return;
        }

        if (valorRecebido < valorTotal) {
            JOptionPane.showMessageDialog(
                    this,
                    "O valor recebido é insuficiente!",
                    "Pagamento",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        double troco = valorRecebido - valorTotal;

        // Capturar informações da tela
        String nomeOperador = tfOperador.getText().trim();
        String nomeCaixa = cbCaixa.getSelectedItem().toString();
       

        if (nomeOperador.isEmpty()) {
            throw new Exception("Operador não informado!");
        }

        // Calcular quantidade total de produtos
        int quantidadeItens = 0;

        for (int i = 0; i < modelo.getRowCount(); i++) {
            quantidadeItens += Integer.parseInt(
                    modelo.getValueAt(i, 3).toString()
            );
        }

        conn = Conexao.conectar();
        conn.setAutoCommit(false);

        // Buscar ID do operador
        int idUsuario;

        String sqlUsuario =
                "SELECT idusuario FROM usuario WHERE nome = ?";

        try (PreparedStatement stmt =
                conn.prepareStatement(sqlUsuario)) {

            stmt.setString(1, nomeOperador);

            try (ResultSet rs = stmt.executeQuery()) {

                if (!rs.next()) {
                    throw new Exception(
                            "Operador não encontrado no banco de dados!"
                    );
                }

                idUsuario = rs.getInt("idusuario");
            }
        }

        // Buscar ID do caixa
        int idCaixa;

        String sqlCaixa =
                "SELECT idcaixas FROM caixas WHERE nome = ?";

        try (PreparedStatement stmt =
                conn.prepareStatement(sqlCaixa)) {

            stmt.setString(1, nomeCaixa);

            try (ResultSet rs = stmt.executeQuery()) {

                if (!rs.next()) {
                    throw new Exception(
                            "Caixa não encontrado no banco de dados!"
                    );
                }

                idCaixa = rs.getInt("idcaixas");
            }
        }

        // Cadastrar a venda
        String sqlVenda =
                "INSERT INTO vendas "
                + "(dataVenda, horarioVenda, valorTotal, valorPago, "
                + "troco, quantidadeItens, statusVenda, "
                + "cliente_idcliente, usuario_idusuario, "
                + "caixas_idcaixas, cupom_idcupom) "
                + "VALUES (?, ?, ?, ?, ?, ?, 1, NULL, ?, ?, NULL)";

        int idVenda;

        try (PreparedStatement stmt = conn.prepareStatement(
                sqlVenda, Statement.RETURN_GENERATED_KEYS)) {

           
            stmt.setDouble(3, valorTotal);
            stmt.setDouble(4, valorRecebido);
            stmt.setDouble(5, troco);
            stmt.setInt(6, quantidadeItens);
            stmt.setInt(7, idUsuario);
            stmt.setInt(8, idCaixa);

            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {

                if (!rs.next()) {
                    throw new Exception(
                            "Não foi possível obter o código da venda!"
                    );
                }

                idVenda = rs.getInt(1);
            }
        }

        // Preparar cadastro dos itens
        String sqlItem =
                "INSERT INTO itensVendas "
                + "(quantidade, precoUnitario, desconto, "
                + "valorTotal, vendas_idvendas) "
                + "VALUES (?, ?, ?, ?, ?)";

        String sqlEstoque =
                "UPDATE produtos "
                + "SET estoque = estoque - ? "
                + "WHERE idprodutos = ? AND estoque >= ?";

        try (PreparedStatement stmtItem =
                    conn.prepareStatement(sqlItem);
             PreparedStatement stmtEstoque =
                    conn.prepareStatement(sqlEstoque)) {

            // Percorrer os produtos da tabela
            for (int i = 0; i < modelo.getRowCount(); i++) {

                int idProduto = Integer.parseInt(
                        modelo.getValueAt(i, 0).toString()
                );

                int quantidade = Integer.parseInt(
                        modelo.getValueAt(i, 3).toString()
                );

                double precoUnitario = Double.parseDouble(
                        modelo.getValueAt(i, 4).toString()
                );

                double totalItem = Double.parseDouble(
                        modelo.getValueAt(i, 5).toString()
                );

                // Registrar item vendido
                stmtItem.setInt(1, quantidade);
                stmtItem.setDouble(2, precoUnitario);
                stmtItem.setDouble(3, 0);
                stmtItem.setDouble(4, totalItem);
                stmtItem.setInt(5, idVenda);

                stmtItem.executeUpdate();

                // Dar baixa no estoque
                stmtEstoque.setInt(1, quantidade);
                stmtEstoque.setInt(2, idProduto);
                stmtEstoque.setInt(3, quantidade);

                int linhasAlteradas = stmtEstoque.executeUpdate();

                if (linhasAlteradas == 0) {
                    throw new Exception(
                            "Estoque insuficiente ou produto inexistente: "
                            + modelo.getValueAt(i, 2)
                    );
                }
            }
        }

        // Confirmar todas as operações
        conn.commit();

        JOptionPane.showMessageDialog(
                this,
                "Venda finalizada com sucesso!\n"
                + "Número da venda: " + idVenda
                + "\nTotal: R$ " + String.format("%.2f", valorTotal)
                + "\nTroco: R$ " + String.format("%.2f", troco),
                "Venda concluída",
                JOptionPane.INFORMATION_MESSAGE
        );

        // Limpar tabela e campos
        modelo.setRowCount(0);

        ftfValorToTal.setValue(0);
        ftfTotalItens.setValue(0);
        ftfValorRecebido.setValue(0);
        ftfValorTroco.setValue(0);

        tfCodigoBarras.setText("");
        tfCodigoBarras.requestFocus();

    } catch (Exception e) {

        try {
            if (conn != null) {
                conn.rollback();
            }
        } catch (Exception erroRollback) {
            JOptionPane.showMessageDialog(
                    this,
                    "Erro ao desfazer a venda: "
                    + erroRollback.getMessage()
            );
        }

        JOptionPane.showMessageDialog(
                this,
                "Não foi possível finalizar a venda:\n"
                + e.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE
        );

    } finally {

        try {
            if (conn != null) {
                conn.setAutoCommit(true);
                conn.close();
            }
        } catch (Exception e) {
            System.out.println(
                    "Erro ao fechar conexão: " + e.getMessage()
            );
        }
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

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        ftfValorTroco = new javax.swing.JFormattedTextField();
        tbClientes = new javax.swing.JTabbedPane();
        paginaVendas = new javax.swing.JPanel();
        pnIndentificacaoOperador = new javax.swing.JPanel();
        lbOperador = new javax.swing.JLabel();
        lbCaixa = new javax.swing.JLabel();
        lbDataHora = new javax.swing.JLabel();
        tfOperador = new javax.swing.JTextField();
        cbCaixa = new javax.swing.JComboBox<>();
        lbIdentificacaoCaixa = new javax.swing.JLabel();
        tfDataCaixa = new javax.swing.JTextField();
        pnConsultaPreco = new javax.swing.JPanel();
        PnLeituraCodigoDeBarras1 = new javax.swing.JPanel();
        lbLeituraCodigodeBarras = new javax.swing.JLabel();
        lbCodigoB = new javax.swing.JLabel();
        tfDigiteCodigoB = new javax.swing.JTextField();
        tfProduto = new javax.swing.JTextField();
        jbPesquisa = new javax.swing.JButton();
        lbConsultaPreco = new javax.swing.JLabel();
        btnPesquisaProduto = new javax.swing.JButton();
        lbPesquisaProduto = new javax.swing.JLabel();
        pnButoes = new javax.swing.JPanel();
        btnFinalizarVenda = new javax.swing.JButton();
        btnSalvar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnLimpar = new javax.swing.JButton();
        btnPesquisar = new javax.swing.JButton();
        btnVoltar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbItensVenda = new javax.swing.JTable();
        lbItensVenda = new javax.swing.JLabel();
        pnLeituraCodigoBarras = new javax.swing.JPanel();
        lbPesquisaConsultaPreco = new javax.swing.JLabel();
        tfCodigoBarras = new javax.swing.JTextField();
        btnPesquisarCodigoBarra = new javax.swing.JButton();
        pnInformacoesVenda = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        ftfCpfVendas = new javax.swing.JFormattedTextField();
        jSpinner1 = new javax.swing.JSpinner();
        tfNomeCliente = new javax.swing.JTextField();
        lbAvisoVendas = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        ftfValorRecebido = new javax.swing.JFormattedTextField();
        ftfTrocoVendas = new javax.swing.JFormattedTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        ftfTotalItens = new javax.swing.JFormattedTextField();
        ftfValorToTal = new javax.swing.JFormattedTextField();
        btnCalcularTrocoVendas = new javax.swing.JButton();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnIndentificacaoOperador.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lbOperador.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lbOperador.setText("Operador:");

        lbCaixa.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lbCaixa.setText("Caixa:");

        lbDataHora.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lbDataHora.setText("Data/Hora:");

        tfOperador.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        cbCaixa.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cbCaixa.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " Caixa 1", "Caixa 2", "Caixa 3", "Caixa 4", "Caixa 5", "Caixa 6" }));

        lbIdentificacaoCaixa.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lbIdentificacaoCaixa.setText("Identificação do operador / Caixa");

        javax.swing.GroupLayout pnIndentificacaoOperadorLayout = new javax.swing.GroupLayout(pnIndentificacaoOperador);
        pnIndentificacaoOperador.setLayout(pnIndentificacaoOperadorLayout);
        pnIndentificacaoOperadorLayout.setHorizontalGroup(
            pnIndentificacaoOperadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnIndentificacaoOperadorLayout.createSequentialGroup()
                .addGap(197, 197, 197)
                .addComponent(lbIdentificacaoCaixa, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnIndentificacaoOperadorLayout.createSequentialGroup()
                .addGroup(pnIndentificacaoOperadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnIndentificacaoOperadorLayout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(lbCaixa)
                        .addGap(18, 18, 18)
                        .addComponent(cbCaixa, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnIndentificacaoOperadorLayout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(pnIndentificacaoOperadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lbDataHora)
                            .addComponent(lbOperador))
                        .addGroup(pnIndentificacaoOperadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnIndentificacaoOperadorLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tfOperador, javax.swing.GroupLayout.PREFERRED_SIZE, 462, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnIndentificacaoOperadorLayout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(tfDataCaixa, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(0, 181, Short.MAX_VALUE))
        );
        pnIndentificacaoOperadorLayout.setVerticalGroup(
            pnIndentificacaoOperadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnIndentificacaoOperadorLayout.createSequentialGroup()
                .addComponent(lbIdentificacaoCaixa, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addGroup(pnIndentificacaoOperadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbOperador)
                    .addComponent(tfOperador, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(pnIndentificacaoOperadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbCaixa)
                    .addComponent(cbCaixa, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnIndentificacaoOperadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbDataHora)
                    .addComponent(tfDataCaixa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(39, Short.MAX_VALUE))
        );

        pnConsultaPreco.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lbLeituraCodigodeBarras.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lbLeituraCodigodeBarras.setText("Leitura de Código de Barras");

        lbCodigoB.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lbCodigoB.setText("Código de Barras:");

        tfDigiteCodigoB.setText("jTextField2");

        javax.swing.GroupLayout PnLeituraCodigoDeBarras1Layout = new javax.swing.GroupLayout(PnLeituraCodigoDeBarras1);
        PnLeituraCodigoDeBarras1.setLayout(PnLeituraCodigoDeBarras1Layout);
        PnLeituraCodigoDeBarras1Layout.setHorizontalGroup(
            PnLeituraCodigoDeBarras1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PnLeituraCodigoDeBarras1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PnLeituraCodigoDeBarras1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbCodigoB, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfDigiteCodigoB, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbLeituraCodigodeBarras, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(47, Short.MAX_VALUE))
        );
        PnLeituraCodigoDeBarras1Layout.setVerticalGroup(
            PnLeituraCodigoDeBarras1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PnLeituraCodigoDeBarras1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(lbLeituraCodigodeBarras)
                .addGap(32, 32, 32)
                .addComponent(lbCodigoB)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfDigiteCodigoB, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(148, Short.MAX_VALUE))
        );

        jbPesquisa.setText("jButton1");

        lbConsultaPreco.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lbConsultaPreco.setText("Consulta de Preços");

        btnPesquisaProduto.setText("Pesquisa");

        lbPesquisaProduto.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lbPesquisaProduto.setText("Pesquisar Produto:");

        javax.swing.GroupLayout pnConsultaPrecoLayout = new javax.swing.GroupLayout(pnConsultaPreco);
        pnConsultaPreco.setLayout(pnConsultaPrecoLayout);
        pnConsultaPrecoLayout.setHorizontalGroup(
            pnConsultaPrecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnConsultaPrecoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnConsultaPrecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnConsultaPrecoLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jbPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnConsultaPrecoLayout.createSequentialGroup()
                        .addGroup(pnConsultaPrecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbConsultaPreco, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnConsultaPrecoLayout.createSequentialGroup()
                                .addComponent(tfProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(39, 39, 39)
                                .addComponent(btnPesquisaProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lbPesquisaProduto))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(pnConsultaPrecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnConsultaPrecoLayout.createSequentialGroup()
                    .addGap(770, 770, 770)
                    .addComponent(PnLeituraCodigoDeBarras1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(649, Short.MAX_VALUE)))
        );
        pnConsultaPrecoLayout.setVerticalGroup(
            pnConsultaPrecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnConsultaPrecoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbConsultaPreco)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbPesquisaProduto)
                .addGap(16, 16, 16)
                .addGroup(pnConsultaPrecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(tfProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPesquisaProduto))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jbPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(pnConsultaPrecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnConsultaPrecoLayout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(PnLeituraCodigoDeBarras1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );

        pnButoes.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btnFinalizarVenda.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        btnFinalizarVenda.setText("Finalizar Venda");
        btnFinalizarVenda.addActionListener(this::btnFinalizarVendaActionPerformed);

        btnSalvar.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        btnSalvar.setText("Desconto/Cupom");

        btnExcluir.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        btnExcluir.setText("Remover");

        btnLimpar.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        btnLimpar.setText("Alterar Quantidade");

        btnPesquisar.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        btnPesquisar.setText("Cancelar");

        btnVoltar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnVoltar.setText("Cliente");

        javax.swing.GroupLayout pnButoesLayout = new javax.swing.GroupLayout(pnButoes);
        pnButoes.setLayout(pnButoesLayout);
        pnButoesLayout.setHorizontalGroup(
            pnButoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnButoesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnButoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnVoltar, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFinalizarVenda))
                .addGap(87, 87, 87)
                .addGroup(pnButoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(122, 122, 122)
                .addGroup(pnButoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnLimpar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSalvar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(206, Short.MAX_VALUE))
        );
        pnButoesLayout.setVerticalGroup(
            pnButoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnButoesLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(pnButoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnExcluir)
                    .addComponent(btnLimpar)
                    .addComponent(btnFinalizarVenda))
                .addGap(27, 27, 27)
                .addGroup(pnButoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnVoltar)
                    .addComponent(btnSalvar)
                    .addComponent(btnPesquisar))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        tbItensVenda.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        tbItensVenda.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                " Código", "Produto", "Quantidade", "Valor Unitário", "Desconto"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tbItensVenda);

        lbItensVenda.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lbItensVenda.setText("Itens da Venda");

        pnLeituraCodigoBarras.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lbPesquisaConsultaPreco.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lbPesquisaConsultaPreco.setText("Leitura de Código de Barras");

        tfCodigoBarras.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        btnPesquisarCodigoBarra.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnPesquisarCodigoBarra.setText("Pesquisar");
        btnPesquisarCodigoBarra.addActionListener(this::btnPesquisarCodigoBarraActionPerformed);

        javax.swing.GroupLayout pnLeituraCodigoBarrasLayout = new javax.swing.GroupLayout(pnLeituraCodigoBarras);
        pnLeituraCodigoBarras.setLayout(pnLeituraCodigoBarrasLayout);
        pnLeituraCodigoBarrasLayout.setHorizontalGroup(
            pnLeituraCodigoBarrasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnLeituraCodigoBarrasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbPesquisaConsultaPreco)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnLeituraCodigoBarrasLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(tfCodigoBarras, javax.swing.GroupLayout.PREFERRED_SIZE, 803, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPesquisarCodigoBarra)
                .addGap(12, 12, 12))
        );
        pnLeituraCodigoBarrasLayout.setVerticalGroup(
            pnLeituraCodigoBarrasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnLeituraCodigoBarrasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbPesquisaConsultaPreco)
                .addGap(37, 37, 37)
                .addGroup(pnLeituraCodigoBarrasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfCodigoBarras, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPesquisarCodigoBarra, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        pnInformacoesVenda.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel1.setText("Informaçoes da venda");

        jLabel2.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel2.setText("cliente:");

        jLabel3.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel3.setText("CPF:");

        jLabel4.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel4.setText("Quantidade (padrão):");

        try {
            ftfCpfVendas.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        tfNomeCliente.setToolTipText("");

        javax.swing.GroupLayout pnInformacoesVendaLayout = new javax.swing.GroupLayout(pnInformacoesVenda);
        pnInformacoesVenda.setLayout(pnInformacoesVendaLayout);
        pnInformacoesVendaLayout.setHorizontalGroup(
            pnInformacoesVendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnInformacoesVendaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnInformacoesVendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnInformacoesVendaLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ftfCpfVendas, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnInformacoesVendaLayout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnInformacoesVendaLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfNomeCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 371, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnInformacoesVendaLayout.setVerticalGroup(
            pnInformacoesVendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnInformacoesVendaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnInformacoesVendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfNomeCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnInformacoesVendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(ftfCpfVendas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(pnInformacoesVendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel6.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel6.setText("Resumo da Venda");

        jLabel7.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel7.setText("valor recebido:");

        jLabel8.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel8.setText("Troco:");

        try {
            ftfValorRecebido.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jLabel10.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel10.setText("Total de Itens:");

        jLabel11.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel11.setText("valor total:");

        try {
            ftfTotalItens.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("########")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        ftfTotalItens.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N

        ftfValorToTal.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("¤#,##0.00"))));
        ftfValorToTal.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ftfTotalItens, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ftfValorToTal, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(ftfTotalItens, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(ftfValorToTal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19))
        );

        btnCalcularTrocoVendas.setText("Calcular Troco");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(18, 18, 18)
                        .addComponent(ftfTrocoVendas))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ftfValorRecebido)
                        .addGap(119, 119, 119))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCalcularTrocoVendas)
                        .addGap(81, 81, 81))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(ftfValorRecebido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(ftfTrocoVendas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(71, 71, 71)
                        .addComponent(btnCalcularTrocoVendas)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout paginaVendasLayout = new javax.swing.GroupLayout(paginaVendas);
        paginaVendas.setLayout(paginaVendasLayout);
        paginaVendasLayout.setHorizontalGroup(
            paginaVendasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paginaVendasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(paginaVendasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(paginaVendasLayout.createSequentialGroup()
                        .addGroup(paginaVendasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(paginaVendasLayout.createSequentialGroup()
                                .addGap(279, 279, 279)
                                .addComponent(lbAvisoVendas, javax.swing.GroupLayout.PREFERRED_SIZE, 1050, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(paginaVendasLayout.createSequentialGroup()
                                .addComponent(pnLeituraCodigoBarras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(11, 11, 11)
                                .addComponent(pnInformacoesVenda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(7, 7, 7))
                    .addGroup(paginaVendasLayout.createSequentialGroup()
                        .addGroup(paginaVendasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 911, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pnIndentificacaoOperador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pnButoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbItensVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 583, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(paginaVendasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(paginaVendasLayout.createSequentialGroup()
                                .addComponent(pnConsultaPreco, javax.swing.GroupLayout.PREFERRED_SIZE, 544, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(22, Short.MAX_VALUE))
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
        );
        paginaVendasLayout.setVerticalGroup(
            paginaVendasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, paginaVendasLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(paginaVendasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, paginaVendasLayout.createSequentialGroup()
                        .addComponent(pnInformacoesVenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(pnConsultaPreco, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 771, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, paginaVendasLayout.createSequentialGroup()
                        .addComponent(pnLeituraCodigoBarras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbItensVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnButoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(pnIndentificacaoOperador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(529, 529, 529)))
                .addComponent(lbAvisoVendas, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        tbClientes.addTab("PDV", paginaVendas);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tbClientes)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(tbClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 1466, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnFinalizarVendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFinalizarVendaActionPerformed
        // TODO add your handling code here:
        finalizarVenda();
        
    }//GEN-LAST:event_btnFinalizarVendaActionPerformed

    private void btnPesquisarCodigoBarraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarCodigoBarraActionPerformed
        // TODO add your handling code here:
        adicionarProdutoPorCodigoBarras();
        
    }//GEN-LAST:event_btnPesquisarCodigoBarraActionPerformed
   
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

    /* Create and display the form */
    java.awt.EventQueue.invokeLater(() -> new TelaPdvRealizacao(usuario).setVisible(true));
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PnLeituraCodigoDeBarras1;
    private javax.swing.JButton btnCalcularTrocoVendas;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnFinalizarVenda;
    private javax.swing.JButton btnLimpar;
    private javax.swing.JButton btnPesquisaProduto;
    private javax.swing.JButton btnPesquisar;
    private javax.swing.JButton btnPesquisarCodigoBarra;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JButton btnVoltar;
    private javax.swing.JComboBox<String> cbCaixa;
    private javax.swing.JFormattedTextField ftfCpfVendas;
    private javax.swing.JFormattedTextField ftfTotalItens;
    private javax.swing.JFormattedTextField ftfTrocoVendas;
    private javax.swing.JFormattedTextField ftfValorRecebido;
    private javax.swing.JFormattedTextField ftfValorToTal;
    private javax.swing.JFormattedTextField ftfValorTroco;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton jbPesquisa;
    private javax.swing.JLabel lbAvisoVendas;
    private javax.swing.JLabel lbCaixa;
    private javax.swing.JLabel lbCodigoB;
    private javax.swing.JLabel lbConsultaPreco;
    private javax.swing.JLabel lbDataHora;
    private javax.swing.JLabel lbIdentificacaoCaixa;
    private javax.swing.JLabel lbItensVenda;
    private javax.swing.JLabel lbLeituraCodigodeBarras;
    private javax.swing.JLabel lbOperador;
    private javax.swing.JLabel lbPesquisaConsultaPreco;
    private javax.swing.JLabel lbPesquisaProduto;
    private javax.swing.JPanel paginaVendas;
    private javax.swing.JPanel pnButoes;
    private javax.swing.JPanel pnConsultaPreco;
    private javax.swing.JPanel pnIndentificacaoOperador;
    private javax.swing.JPanel pnInformacoesVenda;
    private javax.swing.JPanel pnLeituraCodigoBarras;
    private javax.swing.JTabbedPane tbClientes;
    private javax.swing.JTable tbItensVenda;
    private javax.swing.JTextField tfCodigoBarras;
    private javax.swing.JTextField tfDataCaixa;
    private javax.swing.JTextField tfDigiteCodigoB;
    private javax.swing.JTextField tfNomeCliente;
    private javax.swing.JTextField tfOperador;
    private javax.swing.JTextField tfProduto;
    // End of variables declaration//GEN-END:variables
}
