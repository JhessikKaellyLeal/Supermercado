/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;


import Control.ClienteController;
import View.MenuPadrao;
import Model.Usuario;
import View.TelaHome;
import View.TelaPerfilUsuarios;
import View.TelaPromocoes;
import View.Tela_Funcionarios_RH;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;
import Control.ClienteController;
import Model.Cliente;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author User
 */
public class TelasClientes extends javax.swing.JFrame {
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(TelasClientes.class.getName());

     private MenuPadrao menu;  
    private Usuario usuario;

    public TelasClientes(Usuario usuario) {
        initComponents();
          this.usuario= usuario;
        this.menu=MenuPadrao.instalar(
        this,
        usuario,
        "clientes",
        this::navegar,
        this::dispose
    );
        
         this.setExtendedState(TelasClientes.MAXIMIZED_BOTH);
        
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

 private void cadastrarCliente() {

    try {

        // Capturar os dados dos campos
        String nome = txtNome.getText().trim();

        String cpf = ftfCPF.getText().replaceAll("[^0-9]", "");

        String rg = txtRGCadastro.getText().trim();

        String dataNascimento = ftData.getText().trim();

        String genero = "";

        if (rbFeminino.isSelected()) {
            genero = "Feminino";
        } else if (rbMasculino.isSelected()) {
            genero = "Masculino";
        } else if (rbOutro.isSelected()) {
            genero = "Outro";
        }

        String email = txtEmailCadastro.getText().trim();

        String telefone = ftfTelefone.getText().replaceAll("[^0-9]", "");

        String senha = new String(pfSenha.getPassword());

        // Status: 1 = Ativo
        int status = 1;

        // Validar campos obrigatórios
        if (nome.isEmpty() || cpf.isEmpty() || rg.isEmpty()
                || dataNascimento.isEmpty() || genero.isEmpty()
                || email.isEmpty() || telefone.isEmpty()
                || senha.isEmpty()) {

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

        // Validar telefone
        if (telefone.length() < 10 || telefone.length() > 11) {

            JOptionPane.showMessageDialog(
                this,
                "Informe um telefone válido!",
                "Atenção",
                JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        // Chamar o controller
        ClienteController controller = new ClienteController();

        boolean sucesso = controller.cadastrar(
            nome,
            cpf,
            rg,
            dataNascimento,
            genero,
            email,
            telefone,
            status,
            senha
        );

        if (sucesso) {

            JOptionPane.showMessageDialog(
                this,
                "Cliente cadastrado com sucesso!",
                "Sucesso",
                JOptionPane.INFORMATION_MESSAGE
            );

            limparCamposCadastro();

        } else {

            JOptionPane.showMessageDialog(
                this,
                "Não foi possível cadastrar o cliente!",
                "Erro",
                JOptionPane.ERROR_MESSAGE
            );
        }

    } catch (Exception e) {

        JOptionPane.showMessageDialog(
            this,
            "Erro ao cadastrar cliente: " + e.getMessage(),
            "Erro",
            JOptionPane.ERROR_MESSAGE
        );

        e.printStackTrace();
    }
}
 
 private void limparCamposCadastro() {

    // Limpar campos de texto
    txtNome.setText("");
    ftfCPF.setValue(null);
    txtRGCadastro.setText("");
    ftData.setValue(null);
    txtEmailCadastro.setText("");
    ftfTelefone.setValue(null);

    // Limpar senha
    pfSenha.setText("");

    // Desmarcar opções de gênero
    rbFeminino.setSelected(false);
    rbMasculino.setSelected(false);
    rbOutro.setSelected(false);

    // Retornar o cursor para o campo nome
    txtNome.requestFocus();

}

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jRadioButtonMenuItem1 = new javax.swing.JRadioButtonMenuItem();
        painelConteudo = new javax.swing.JPanel();
        abasClientes = new javax.swing.JTabbedPane();
        painelCadastro = new javax.swing.JPanel();
        painelDadosCliente = new javax.swing.JPanel();
        lblTituloCadastro = new javax.swing.JLabel();
        btnLimpar = new javax.swing.JButton();
        btnSalvar = new javax.swing.JButton();
        ftfTelefone = new javax.swing.JFormattedTextField();
        lblTelefone = new javax.swing.JLabel();
        ftfCPF = new javax.swing.JFormattedTextField();
        txtNome = new javax.swing.JTextField();
        lblCPF = new javax.swing.JLabel();
        lblDescricaoCadastro = new javax.swing.JLabel();
        lblNome = new javax.swing.JLabel();
        lbRg = new javax.swing.JLabel();
        txtRGCadastro = new javax.swing.JTextField();
        lbEmail = new javax.swing.JLabel();
        txtEmailCadastro = new javax.swing.JTextField();
        lbData = new javax.swing.JLabel();
        lbSenha = new javax.swing.JLabel();
        pfSenha = new javax.swing.JPasswordField();
        ftData = new javax.swing.JFormattedTextField();
        lbStatuscadastro = new javax.swing.JLabel();
        cbStatusCadastro = new javax.swing.JComboBox<>();
        grupoGenero = new javax.swing.JLabel();
        rbFeminino = new javax.swing.JRadioButton();
        rbMasculino = new javax.swing.JRadioButton();
        rbOutro = new javax.swing.JRadioButton();
        painelConsulta = new javax.swing.JPanel();
        painelConsultaClientes = new javax.swing.JPanel();
        btnLimparFiltros = new javax.swing.JButton();
        cbStatusConsulta = new javax.swing.JComboBox<>();
        lblStatusConsulta = new javax.swing.JLabel();
        txtPesquisaCliente = new javax.swing.JTextField();
        lblPesquisaCliente = new javax.swing.JLabel();
        lblDescricaoConsulta = new javax.swing.JLabel();
        lblTituloConsulta = new javax.swing.JLabel();
        btnPesquisarConsulta = new javax.swing.JButton();
        painelConsultaClientes1 = new javax.swing.JPanel();
        btnVisualizarDetalhes = new javax.swing.JButton();
        lblMensagemConsulta = new javax.swing.JLabel();
        lblClientesCadastrados = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaConsultaClientes = new javax.swing.JTable();
        painelDetalhesCliente = new javax.swing.JPanel();
        lbDetalhesdocliente = new javax.swing.JLabel();
        lbdadosPessoais = new javax.swing.JLabel();
        lblDetalheCodigo = new javax.swing.JLabel();
        lblDetalheNome = new javax.swing.JLabel();
        lblDetalheCPF = new javax.swing.JLabel();
        lblDetalheRG = new javax.swing.JLabel();
        lblDetalheNascimento = new javax.swing.JLabel();
        lblDetalheGenero = new javax.swing.JLabel();
        lbDatalheContato = new javax.swing.JLabel();
        lblDetalheTelefone = new javax.swing.JLabel();
        lblDetalheEmail = new javax.swing.JLabel();
        lbSituacaoCadastral = new javax.swing.JLabel();
        lblDetalheStatus = new javax.swing.JLabel();
        painelEdicao = new javax.swing.JPanel();
        painelEdicaoClientes = new javax.swing.JPanel();
        lblTituloEdicao = new javax.swing.JLabel();
        scrollTabelaEdicao = new javax.swing.JScrollPane();
        tabelaEdicaoClientes = new javax.swing.JTable();
        lblPesquisaEdicao = new javax.swing.JLabel();
        txtPesquisaEdicao = new javax.swing.JTextField();
        cbStatusEdicao = new javax.swing.JComboBox<>();
        lblStatusEdicao = new javax.swing.JLabel();
        btnPesquisarEdicao = new javax.swing.JButton();
        lblTituloDadosEdicao = new javax.swing.JLabel();
        lblDescricaoEdicao = new javax.swing.JLabel();
        chick = new javax.swing.JLabel();
        painelEdicaoClientes1 = new javax.swing.JPanel();
        lblCodigoEdicao = new javax.swing.JLabel();
        txtCodigoEdicao = new javax.swing.JTextField();
        txtCidadeEdicao = new javax.swing.JTextField();
        lblCPFCdicao = new javax.swing.JLabel();
        lblEnderecoEdicao = new javax.swing.JLabel();
        txtEmailEdicao = new javax.swing.JTextField();
        txtNomeEdicao = new javax.swing.JTextField();
        lblCidadeEdicao = new javax.swing.JLabel();
        lblNomeEdicao = new javax.swing.JLabel();
        Bairro = new javax.swing.JLabel();
        txtDataNascimentoEdicao = new javax.swing.JTextField();
        lblDataNascimentoEdicao = new javax.swing.JLabel();
        txtEnderecoEdicao = new javax.swing.JTextField();
        lblRGEdicao = new javax.swing.JLabel();
        lblEmailEdicao = new javax.swing.JLabel();
        txtBairroEdicao = new javax.swing.JTextField();
        txtCPFCdicao = new javax.swing.JTextField();
        txtRGEdicao = new javax.swing.JTextField();
        txtTelefoneEdicao = new javax.swing.JTextField();
        lblTelefoneEdicao = new javax.swing.JLabel();
        painelHistoricoClientes = new javax.swing.JPanel();
        btnLimparEdicao = new javax.swing.JButton();
        btnSalvarAlteracoes = new javax.swing.JButton();
        btnDesativarCliente = new javax.swing.JButton();
        painelHistorico = new javax.swing.JPanel();
        btnComprovante = new javax.swing.JButton();
        btnExportarHistorico = new javax.swing.JButton();
        painelhistoricoCompras = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabelaComprasHistorico = new javax.swing.JTable();
        lblTituloComprasHistorico = new javax.swing.JLabel();
        painelhistorico = new javax.swing.JPanel();
        btnPesquisarHistorico = new javax.swing.JButton();
        txtPesquisaHistorico = new javax.swing.JTextField();
        lblTituloHistorico = new javax.swing.JLabel();
        btnLimparHistorico = new javax.swing.JButton();
        lbpesquisarcliente = new javax.swing.JLabel();
        lblDescricaoHistorico = new javax.swing.JLabel();
        painelhistoricoPagamentos = new javax.swing.JPanel();
        lblTituloPagamentosHistorico = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabelaPagamentosHistorico = new javax.swing.JTable();
        lbGerarComprovante = new javax.swing.JLabel();
        painelTotalCompras = new javax.swing.JPanel();
        lbtotalcompras = new javax.swing.JLabel();
        lbValortotalCompras = new javax.swing.JLabel();
        lBSubtituloCompras = new javax.swing.JLabel();
        lbIconeTotalCompras = new javax.swing.JLabel();
        painelTotalPago = new javax.swing.JPanel();
        lBtotalPago = new javax.swing.JLabel();
        LbVALORtOTALpAGO = new javax.swing.JLabel();
        lBsubTotalPagp = new javax.swing.JLabel();
        lbTotalPagoCompras = new javax.swing.JLabel();
        lBSaldoPendente = new javax.swing.JPanel();
        lbsaldopendente1 = new javax.swing.JLabel();
        lBvalorPendente = new javax.swing.JLabel();
        lBsubValorPendente = new javax.swing.JLabel();
        lbiconeSaldoPendente = new javax.swing.JLabel();
        painelPedidosRelatorio = new javax.swing.JPanel();
        lblTituloPedidosRelatorio = new javax.swing.JLabel();
        painelpedidosTitulo = new javax.swing.JPanel();
        lblPesquisaClienteRelatorio = new javax.swing.JLabel();
        cbPeriodoRelatorio = new javax.swing.JComboBox<>();
        lblPeriodoRelatorio = new javax.swing.JLabel();
        cbStatusRelatorio = new javax.swing.JComboBox<>();
        lblStatusRelatorio = new javax.swing.JLabel();
        btnLimparFiltrosRelatorio = new javax.swing.JButton();
        btnPesquisarRelatorio = new javax.swing.JButton();
        txtPesquisaClienteRelatorio = new javax.swing.JTextField();
        painelPedidosClientes = new javax.swing.JPanel();
        tabelaPedidosClientes = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        lblTituloTabelaPedidos = new javax.swing.JLabel();
        painelTotalPedidos = new javax.swing.JPanel();
        lblQuantidadePedidosRelatorio = new javax.swing.JLabel();
        lblTotalPedidosRelatorio = new javax.swing.JLabel();
        lbIconetotalPedidos = new javax.swing.JLabel();
        painelvalorTotalPedidos = new javax.swing.JPanel();
        lblValorPedidosRelatorio = new javax.swing.JLabel();
        lblTotalValorPedidosRelatorio = new javax.swing.JLabel();
        lbIconeValorTotal = new javax.swing.JLabel();
        painelPedidospendetes = new javax.swing.JPanel();
        lblQuantidadePendentesRelatorio = new javax.swing.JLabel();
        lblPedidosPendentesRelatorio = new javax.swing.JLabel();
        lbIconePendente = new javax.swing.JLabel();
        painelGerarRelatorio = new javax.swing.JPanel();
        lblTipoRelatorio = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        btnGerarRelatorio = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        lbMesRelatorio = new javax.swing.JLabel();
        lbPeriodoRelatorio = new javax.swing.JLabel();
        lbAnoRelatorio = new javax.swing.JLabel();
        cbAnoRelatorio = new javax.swing.JComboBox<>();
        cbMesRelatorio = new javax.swing.JComboBox<>();
        cbTipoRelatorio = new javax.swing.JComboBox<>();
        lblTituloGerarRelatorio = new javax.swing.JLabel();
        lblDescricaoPedidosRelatorio = new javax.swing.JLabel();

        jRadioButtonMenuItem1.setSelected(true);
        jRadioButtonMenuItem1.setText("jRadioButtonMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(1920, 1800));
        setPreferredSize(new java.awt.Dimension(1920, 1800));

        painelConteudo.setLayout(new java.awt.BorderLayout());
        getContentPane().add(painelConteudo, java.awt.BorderLayout.CENTER);

        abasClientes.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 16)); // NOI18N
        abasClientes.setMaximumSize(null);

        painelCadastro.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        painelCadastro.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        painelCadastro.setMaximumSize(new java.awt.Dimension(1800, 1800));
        painelCadastro.setPreferredSize(new java.awt.Dimension(1800, 1800));

        painelDadosCliente.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblTituloCadastro.setFont(new java.awt.Font("Arial Black", 1, 30)); // NOI18N
        lblTituloCadastro.setForeground(new java.awt.Color(10, 4, 138));
        lblTituloCadastro.setText("Cadastro de Clientes");

        btnLimpar.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18)); // NOI18N
        btnLimpar.setIcon(new javax.swing.ImageIcon("C:\\Users\\CleicianeGomes\\Documents\\ProjetoPI_Supermercado\\GerenciamentoSupermercado\\src\\main\\resources\\assets\\replay.png")); // NOI18N
        btnLimpar.setText("Limpar ");
        btnLimpar.setBorder(null);
        btnLimpar.setPreferredSize(new java.awt.Dimension(150, 40));
        btnLimpar.addActionListener(this::btnLimparActionPerformed);

        btnSalvar.setBackground(new java.awt.Color(10, 4, 138));
        btnSalvar.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        btnSalvar.setForeground(new java.awt.Color(255, 255, 255));
        btnSalvar.setIcon(new javax.swing.ImageIcon("C:\\Users\\CleicianeGomes\\Documents\\ProjetoPI_Supermercado\\GerenciamentoSupermercado\\src\\main\\resources\\assets\\diskette (2).png")); // NOI18N
        btnSalvar.setText("Salvar cadastro");
        btnSalvar.setBorder(null);
        btnSalvar.setPreferredSize(new java.awt.Dimension(183, 40));
        btnSalvar.addActionListener(this::btnSalvarActionPerformed);

        try {
            ftfTelefone.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##)#####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        ftfTelefone.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        ftfTelefone.setPreferredSize(new java.awt.Dimension(101, 40));

        lblTelefone.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        lblTelefone.setText("Telefone *");

        try {
            ftfCPF.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        ftfCPF.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        ftfCPF.setPreferredSize(new java.awt.Dimension(96, 40));
        ftfCPF.addActionListener(this::ftfCPFActionPerformed);

        txtNome.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        txtNome.setPreferredSize(new java.awt.Dimension(68, 40));

        lblCPF.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        lblCPF.setText("CPF *");

        lblDescricaoCadastro.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 22)); // NOI18N
        lblDescricaoCadastro.setText("Preencha as informações para finalizar sua compra");

        lblNome.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        lblNome.setText("Nome Completo *");

        lbRg.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        lbRg.setText("RG");

        txtRGCadastro.setPreferredSize(new java.awt.Dimension(68, 40));

        lbEmail.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        lbEmail.setText("E-mail");

        txtEmailCadastro.setPreferredSize(new java.awt.Dimension(68, 40));

        lbData.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        lbData.setText("Data de nascimento");

        lbSenha.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        lbSenha.setText("Senha");

        pfSenha.setPreferredSize(new java.awt.Dimension(68, 40));

        try {
            ftData.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        ftData.setPreferredSize(new java.awt.Dimension(68, 40));

        lbStatuscadastro.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        lbStatuscadastro.setText("Status *");

        cbStatusCadastro.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        cbStatusCadastro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ativo", "Inativo" }));
        cbStatusCadastro.setPreferredSize(new java.awt.Dimension(83, 40));

        grupoGenero.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        grupoGenero.setText("Gênero");

        rbFeminino.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 16)); // NOI18N
        rbFeminino.setText("Feminino");

        rbMasculino.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 16)); // NOI18N
        rbMasculino.setText("Masculino");

        rbOutro.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 16)); // NOI18N
        rbOutro.setText("Outro");

        javax.swing.GroupLayout painelDadosClienteLayout = new javax.swing.GroupLayout(painelDadosCliente);
        painelDadosCliente.setLayout(painelDadosClienteLayout);
        painelDadosClienteLayout.setHorizontalGroup(
            painelDadosClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelDadosClienteLayout.createSequentialGroup()
                .addGap(74, 74, 74)
                .addGroup(painelDadosClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(painelDadosClienteLayout.createSequentialGroup()
                        .addGroup(painelDadosClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTelefone)
                            .addComponent(lbEmail)
                            .addComponent(lblCPF)
                            .addComponent(ftfCPF, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(71, 71, 71)
                        .addGroup(painelDadosClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbRg)
                            .addComponent(txtRGCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(81, Short.MAX_VALUE))
                    .addGroup(painelDadosClienteLayout.createSequentialGroup()
                        .addGroup(painelDadosClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(painelDadosClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(grupoGenero)
                                .addComponent(lblDescricaoCadastro)
                                .addComponent(lblTituloCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 513, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lbStatuscadastro)
                                .addComponent(cbStatusCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtNome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblNome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelDadosClienteLayout.createSequentialGroup()
                                    .addGroup(painelDadosClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(ftfTelefone, javax.swing.GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE)
                                        .addComponent(txtEmailCadastro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGap(71, 71, 71)
                                    .addGroup(painelDadosClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(lbData)
                                        .addComponent(ftData, javax.swing.GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE)
                                        .addComponent(lbSenha)
                                        .addComponent(pfSenha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                            .addGroup(painelDadosClienteLayout.createSequentialGroup()
                                .addComponent(rbFeminino)
                                .addGap(166, 166, 166)
                                .addComponent(rbMasculino)
                                .addGap(166, 166, 166)
                                .addComponent(rbOutro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(painelDadosClienteLayout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(102, 102, 102))))
        );
        painelDadosClienteLayout.setVerticalGroup(
            painelDadosClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelDadosClienteLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(lblTituloCadastro)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDescricaoCadastro)
                .addGap(57, 57, 57)
                .addComponent(lblNome)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addGroup(painelDadosClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCPF)
                    .addComponent(lbRg))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painelDadosClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ftfCPF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtRGCadastro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(44, 44, 44)
                .addGroup(painelDadosClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTelefone)
                    .addComponent(lbData))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painelDadosClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(ftfTelefone, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ftData, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(51, 51, 51)
                .addGroup(painelDadosClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbEmail)
                    .addComponent(lbSenha))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painelDadosClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEmailCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pfSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41)
                .addComponent(lbStatuscadastro)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbStatusCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addComponent(grupoGenero)
                .addGap(10, 10, 10)
                .addGroup(painelDadosClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbFeminino)
                    .addComponent(rbMasculino)
                    .addComponent(rbOutro))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 85, Short.MAX_VALUE)
                .addGroup(painelDadosClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38))
        );

        javax.swing.GroupLayout painelCadastroLayout = new javax.swing.GroupLayout(painelCadastro);
        painelCadastro.setLayout(painelCadastroLayout);
        painelCadastroLayout.setHorizontalGroup(
            painelCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelCadastroLayout.createSequentialGroup()
                .addGap(162, 162, 162)
                .addComponent(painelDadosCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(1939, Short.MAX_VALUE))
        );
        painelCadastroLayout.setVerticalGroup(
            painelCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelCadastroLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(painelDadosCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(1672, Short.MAX_VALUE))
        );

        abasClientes.addTab("Cadastro", null, painelCadastro);

        painelConsulta.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        painelConsultaClientes.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        painelConsultaClientes.setForeground(new java.awt.Color(255, 255, 255));

        btnLimparFiltros.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        btnLimparFiltros.setIcon(new javax.swing.ImageIcon("C:\\Users\\CleicianeGomes\\Documents\\ProjetoPI_Supermercado\\GerenciamentoSupermercado\\src\\main\\resources\\assets\\replay.png")); // NOI18N
        btnLimparFiltros.setText("Limpar ");
        btnLimparFiltros.setBorder(null);
        btnLimparFiltros.setPreferredSize(new java.awt.Dimension(131, 40));

        cbStatusConsulta.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        cbStatusConsulta.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos", "Ativo", "Inativo", " " }));
        cbStatusConsulta.setPreferredSize(new java.awt.Dimension(76, 40));
        cbStatusConsulta.addActionListener(this::cbStatusConsultaActionPerformed);

        lblStatusConsulta.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lblStatusConsulta.setIcon(new javax.swing.ImageIcon("C:\\Users\\CleicianeGomes\\Documents\\ProjetoPI_Supermercado\\GerenciamentoSupermercado\\src\\main\\resources\\assets\\list-items.png")); // NOI18N
        lblStatusConsulta.setText("Status");

        txtPesquisaCliente.setToolTipText("Digite o nome, CPF ou telefone do cliente");
        txtPesquisaCliente.setPreferredSize(new java.awt.Dimension(68, 40));

        lblPesquisaCliente.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        lblPesquisaCliente.setText("Pesquisar clientes");

        lblDescricaoConsulta.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 22)); // NOI18N
        lblDescricaoConsulta.setText("Pesquise e visualize os clientes cadastrados no sistema.");

        lblTituloConsulta.setFont(new java.awt.Font("Arial Black", 1, 30)); // NOI18N
        lblTituloConsulta.setForeground(new java.awt.Color(10, 4, 138));
        lblTituloConsulta.setText("Consulta de Clientes");

        btnPesquisarConsulta.setBackground(new java.awt.Color(10, 4, 138));
        btnPesquisarConsulta.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        btnPesquisarConsulta.setForeground(new java.awt.Color(255, 255, 255));
        btnPesquisarConsulta.setIcon(new javax.swing.ImageIcon("C:\\Users\\CleicianeGomes\\Documents\\ProjetoPI_Supermercado\\GerenciamentoSupermercado\\src\\main\\resources\\assets\\search (3).png")); // NOI18N
        btnPesquisarConsulta.setText("Pesquisar");
        btnPesquisarConsulta.setBorder(null);
        btnPesquisarConsulta.setPreferredSize(new java.awt.Dimension(100, 40));

        javax.swing.GroupLayout painelConsultaClientesLayout = new javax.swing.GroupLayout(painelConsultaClientes);
        painelConsultaClientes.setLayout(painelConsultaClientesLayout);
        painelConsultaClientesLayout.setHorizontalGroup(
            painelConsultaClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelConsultaClientesLayout.createSequentialGroup()
                .addGap(84, 84, 84)
                .addGroup(painelConsultaClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDescricaoConsulta)
                    .addComponent(lblTituloConsulta)
                    .addGroup(painelConsultaClientesLayout.createSequentialGroup()
                        .addGroup(painelConsultaClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblPesquisaCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPesquisaCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 495, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(74, 74, 74)
                        .addGroup(painelConsultaClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblStatusConsulta)
                            .addComponent(cbStatusConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(painelConsultaClientesLayout.createSequentialGroup()
                .addContainerGap(793, Short.MAX_VALUE)
                .addComponent(btnPesquisarConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(98, 98, 98)
                .addComponent(btnLimparFiltros, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(109, 109, 109))
        );
        painelConsultaClientesLayout.setVerticalGroup(
            painelConsultaClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelConsultaClientesLayout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(lblTituloConsulta)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDescricaoConsulta)
                .addGap(52, 52, 52)
                .addGroup(painelConsultaClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelConsultaClientesLayout.createSequentialGroup()
                        .addComponent(lblStatusConsulta)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(painelConsultaClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(painelConsultaClientesLayout.createSequentialGroup()
                                .addComponent(cbStatusConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(100, 100, 100))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelConsultaClientesLayout.createSequentialGroup()
                                .addGroup(painelConsultaClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnPesquisarConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnLimparFiltros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(30, 30, 30))))
                    .addGroup(painelConsultaClientesLayout.createSequentialGroup()
                        .addComponent(lblPesquisaCliente)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPesquisaCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );

        painelConsultaClientes1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        painelConsultaClientes1.setPreferredSize(new java.awt.Dimension(50, 380));

        btnVisualizarDetalhes.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        btnVisualizarDetalhes.setIcon(new javax.swing.ImageIcon("C:\\Users\\CleicianeGomes\\Documents\\ProjetoPI_Supermercado\\GerenciamentoSupermercado\\src\\main\\resources\\assets\\eye (1).png")); // NOI18N
        btnVisualizarDetalhes.setText("Visualizar detalhes");
        btnVisualizarDetalhes.setBorder(null);
        btnVisualizarDetalhes.setPreferredSize(new java.awt.Dimension(210, 40));

        lblMensagemConsulta.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lblMensagemConsulta.setText("Selecione um cliente na tabela para visualizar seus dados.");

        lblClientesCadastrados.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 20)); // NOI18N
        lblClientesCadastrados.setText("Clientes cadastrados");

        jScrollPane1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        tabelaConsultaClientes.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        tabelaConsultaClientes.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        tabelaConsultaClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Código", "Nome", "CPF", "Telefone", "E-mail", "Status"
            }
        ));
        tabelaConsultaClientes.setPreferredSize(new java.awt.Dimension(450, 400));
        tabelaConsultaClientes.setRowHeight(25);
        tabelaConsultaClientes.setShowGrid(false);
        jScrollPane1.setViewportView(tabelaConsultaClientes);

        javax.swing.GroupLayout painelConsultaClientes1Layout = new javax.swing.GroupLayout(painelConsultaClientes1);
        painelConsultaClientes1.setLayout(painelConsultaClientes1Layout);
        painelConsultaClientes1Layout.setHorizontalGroup(
            painelConsultaClientes1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelConsultaClientes1Layout.createSequentialGroup()
                .addGap(90, 90, 90)
                .addGroup(painelConsultaClientes1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblClientesCadastrados, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(painelConsultaClientes1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, painelConsultaClientes1Layout.createSequentialGroup()
                            .addComponent(lblMensagemConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 528, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnVisualizarDetalhes, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 985, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(295, Short.MAX_VALUE))
        );
        painelConsultaClientes1Layout.setVerticalGroup(
            painelConsultaClientes1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelConsultaClientes1Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(lblClientesCadastrados)
                .addGap(22, 22, 22)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(painelConsultaClientes1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMensagemConsulta)
                    .addComponent(btnVisualizarDetalhes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(73, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout painelConsultaLayout = new javax.swing.GroupLayout(painelConsulta);
        painelConsulta.setLayout(painelConsultaLayout);
        painelConsultaLayout.setHorizontalGroup(
            painelConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelConsultaLayout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(painelConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(painelConsultaClientes1, javax.swing.GroupLayout.PREFERRED_SIZE, 1374, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(painelConsultaClientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(1462, Short.MAX_VALUE))
        );
        painelConsultaLayout.setVerticalGroup(
            painelConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelConsultaLayout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(painelConsultaClientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52)
                .addComponent(painelConsultaClientes1, javax.swing.GroupLayout.PREFERRED_SIZE, 436, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 1786, Short.MAX_VALUE))
        );

        abasClientes.addTab("Consulta", null, painelConsulta);

        painelEdicao.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        painelEdicaoClientes.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblTituloEdicao.setFont(new java.awt.Font("Arial Black", 1, 30)); // NOI18N
        lblTituloEdicao.setForeground(new java.awt.Color(10, 4, 138));
        lblTituloEdicao.setText("Edição e Desativação de Clientes");

        tabelaEdicaoClientes.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        tabelaEdicaoClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Código", null},
                {"Nome", null},
                {"CPF", null},
                {"Telefone", null},
                {"E-mail", null},
                {"Status", null},
                {null, null}
            },
            new String [] {
                "Campo", "Informaçoes"
            }
        ));
        scrollTabelaEdicao.setViewportView(tabelaEdicaoClientes);

        lblPesquisaEdicao.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lblPesquisaEdicao.setIcon(new javax.swing.ImageIcon("C:\\Users\\CleicianeGomes\\Documents\\ProjetoPI_Supermercado\\GerenciamentoSupermercado\\src\\main\\resources\\assets\\search (4).png")); // NOI18N
        lblPesquisaEdicao.setText("Pesquisar cliente por nome, CPF ou código");

        txtPesquisaEdicao.setPreferredSize(new java.awt.Dimension(68, 40));

        cbStatusEdicao.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        cbStatusEdicao.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos", "Ativo ", "Inativo" }));
        cbStatusEdicao.setPreferredSize(new java.awt.Dimension(83, 40));

        lblStatusEdicao.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lblStatusEdicao.setIcon(new javax.swing.ImageIcon("C:\\Users\\CleicianeGomes\\Documents\\ProjetoPI_Supermercado\\GerenciamentoSupermercado\\src\\main\\resources\\assets\\list-items.png")); // NOI18N
        lblStatusEdicao.setText("Status ");

        btnPesquisarEdicao.setBackground(new java.awt.Color(10, 4, 138));
        btnPesquisarEdicao.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        btnPesquisarEdicao.setForeground(new java.awt.Color(255, 255, 255));
        btnPesquisarEdicao.setIcon(new javax.swing.ImageIcon("C:\\Users\\CleicianeGomes\\Documents\\ProjetoPI_Supermercado\\GerenciamentoSupermercado\\src\\main\\resources\\assets\\search (3).png")); // NOI18N
        btnPesquisarEdicao.setText("Pesquisar");
        btnPesquisarEdicao.setBorder(null);
        btnPesquisarEdicao.setPreferredSize(new java.awt.Dimension(98, 40));

        lblTituloDadosEdicao.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lblTituloDadosEdicao.setText("Dados do cliente selecionado");

        lblDescricaoEdicao.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 22)); // NOI18N
        lblDescricaoEdicao.setText("Localize um cliente para consultar, alterar seus dados ou desativar seu cadastro.");

        javax.swing.GroupLayout painelEdicaoClientesLayout = new javax.swing.GroupLayout(painelEdicaoClientes);
        painelEdicaoClientes.setLayout(painelEdicaoClientesLayout);
        painelEdicaoClientesLayout.setHorizontalGroup(
            painelEdicaoClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelEdicaoClientesLayout.createSequentialGroup()
                .addGap(88, 88, 88)
                .addGroup(painelEdicaoClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(painelEdicaoClientesLayout.createSequentialGroup()
                        .addGroup(painelEdicaoClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTituloDadosEdicao, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(scrollTabelaEdicao, javax.swing.GroupLayout.PREFERRED_SIZE, 864, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(painelEdicaoClientesLayout.createSequentialGroup()
                        .addGroup(painelEdicaoClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtPesquisaEdicao, javax.swing.GroupLayout.PREFERRED_SIZE, 510, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblPesquisaEdicao))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 235, Short.MAX_VALUE)
                        .addGroup(painelEdicaoClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblStatusEdicao)
                            .addGroup(painelEdicaoClientesLayout.createSequentialGroup()
                                .addComponent(cbStatusEdicao, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(181, 181, 181)
                                .addComponent(btnPesquisarEdicao, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(299, 299, 299))
                    .addGroup(painelEdicaoClientesLayout.createSequentialGroup()
                        .addGroup(painelEdicaoClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblDescricaoEdicao)
                            .addComponent(lblTituloEdicao, javax.swing.GroupLayout.PREFERRED_SIZE, 698, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        painelEdicaoClientesLayout.setVerticalGroup(
            painelEdicaoClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelEdicaoClientesLayout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addComponent(lblTituloEdicao)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDescricaoEdicao)
                .addGap(28, 28, 28)
                .addGroup(painelEdicaoClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblPesquisaEdicao)
                    .addComponent(lblStatusEdicao))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painelEdicaoClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPesquisaEdicao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbStatusEdicao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPesquisarEdicao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addComponent(scrollTabelaEdicao, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTituloDadosEdicao)
                .addContainerGap(66, Short.MAX_VALUE))
        );

        painelEdicaoClientes1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblCodigoEdicao.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lblCodigoEdicao.setText("Código");

        txtCodigoEdicao.setPreferredSize(new java.awt.Dimension(68, 40));

        txtCidadeEdicao.setPreferredSize(new java.awt.Dimension(68, 40));

        lblCPFCdicao.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lblCPFCdicao.setText("CPF");

        lblEnderecoEdicao.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lblEnderecoEdicao.setText("Endereço");

        txtEmailEdicao.setPreferredSize(new java.awt.Dimension(68, 40));
        txtEmailEdicao.addActionListener(this::txtEmailEdicaoActionPerformed);

        txtNomeEdicao.setPreferredSize(new java.awt.Dimension(68, 40));

        lblCidadeEdicao.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lblCidadeEdicao.setText("Cidade");

        lblNomeEdicao.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lblNomeEdicao.setText("Nome completo");

        Bairro.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        Bairro.setText("Bairro");

        txtDataNascimentoEdicao.setPreferredSize(new java.awt.Dimension(68, 40));

        lblDataNascimentoEdicao.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lblDataNascimentoEdicao.setText("Data de nascimento");

        txtEnderecoEdicao.setPreferredSize(new java.awt.Dimension(68, 40));

        lblRGEdicao.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lblRGEdicao.setText("RG");

        lblEmailEdicao.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lblEmailEdicao.setText("E-mail");

        txtBairroEdicao.setPreferredSize(new java.awt.Dimension(68, 40));

        txtCPFCdicao.setPreferredSize(new java.awt.Dimension(68, 40));

        txtRGEdicao.setPreferredSize(new java.awt.Dimension(68, 40));
        txtRGEdicao.addActionListener(this::txtRGEdicaoActionPerformed);

        txtTelefoneEdicao.setPreferredSize(new java.awt.Dimension(68, 40));

        lblTelefoneEdicao.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lblTelefoneEdicao.setText("Telefone");

        javax.swing.GroupLayout painelEdicaoClientes1Layout = new javax.swing.GroupLayout(painelEdicaoClientes1);
        painelEdicaoClientes1.setLayout(painelEdicaoClientes1Layout);
        painelEdicaoClientes1Layout.setHorizontalGroup(
            painelEdicaoClientes1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelEdicaoClientes1Layout.createSequentialGroup()
                .addGroup(painelEdicaoClientes1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(painelEdicaoClientes1Layout.createSequentialGroup()
                        .addGap(100, 100, 100)
                        .addGroup(painelEdicaoClientes1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtEnderecoEdicao, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblCodigoEdicao)
                            .addComponent(txtCodigoEdicao, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblCPFCdicao)
                            .addComponent(txtCPFCdicao, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblTelefoneEdicao)
                            .addComponent(txtCidadeEdicao, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblEmailEdicao)
                            .addComponent(txtTelefoneEdicao, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Bairro, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(447, 447, 447)
                        .addGroup(painelEdicaoClientes1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblNomeEdicao)
                            .addComponent(lblRGEdicao)
                            .addComponent(txtBairroEdicao, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblDataNascimentoEdicao)
                            .addComponent(txtNomeEdicao, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtRGEdicao, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblCidadeEdicao)
                            .addComponent(txtDataNascimentoEdicao, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblEnderecoEdicao)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelEdicaoClientes1Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(txtEmailEdicao, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(421, 421, Short.MAX_VALUE))
        );
        painelEdicaoClientes1Layout.setVerticalGroup(
            painelEdicaoClientes1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelEdicaoClientes1Layout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addGroup(painelEdicaoClientes1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCodigoEdicao)
                    .addComponent(lblNomeEdicao))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painelEdicaoClientes1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCodigoEdicao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEmailEdicao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(painelEdicaoClientes1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCPFCdicao)
                    .addComponent(lblRGEdicao))
                .addGap(7, 7, 7)
                .addGroup(painelEdicaoClientes1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCPFCdicao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBairroEdicao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addGroup(painelEdicaoClientes1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTelefoneEdicao)
                    .addComponent(lblDataNascimentoEdicao))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(painelEdicaoClientes1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNomeEdicao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCidadeEdicao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50)
                .addGroup(painelEdicaoClientes1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(painelEdicaoClientes1Layout.createSequentialGroup()
                        .addComponent(lblEnderecoEdicao)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtRGEdicao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(painelEdicaoClientes1Layout.createSequentialGroup()
                        .addComponent(lblEmailEdicao)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTelefoneEdicao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(36, 36, 36)
                .addGroup(painelEdicaoClientes1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCidadeEdicao)
                    .addComponent(Bairro))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painelEdicaoClientes1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDataNascimentoEdicao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEnderecoEdicao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(85, 85, 85))
        );

        painelHistoricoClientes.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        btnLimparEdicao.setFont(new java.awt.Font("Arial Black", 1, 20)); // NOI18N
        btnLimparEdicao.setIcon(new javax.swing.ImageIcon("C:\\Users\\CleicianeGomes\\Documents\\ProjetoPI_Supermercado\\GerenciamentoSupermercado\\src\\main\\resources\\assets\\replay.png")); // NOI18N
        btnLimparEdicao.setText("Limpar campos");
        btnLimparEdicao.setBorder(null);
        btnLimparEdicao.setPreferredSize(new java.awt.Dimension(178, 40));

        btnSalvarAlteracoes.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        btnSalvarAlteracoes.setIcon(new javax.swing.ImageIcon("C:\\Users\\CleicianeGomes\\Documents\\ProjetoPI_Supermercado\\GerenciamentoSupermercado\\src\\main\\resources\\assets\\diskette (5).png")); // NOI18N
        btnSalvarAlteracoes.setText("Salvar alterações");
        btnSalvarAlteracoes.setBorder(null);
        btnSalvarAlteracoes.setPreferredSize(new java.awt.Dimension(200, 40));

        btnDesativarCliente.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        btnDesativarCliente.setIcon(new javax.swing.ImageIcon("C:\\Users\\CleicianeGomes\\Documents\\ProjetoPI_Supermercado\\GerenciamentoSupermercado\\src\\main\\resources\\assets\\user (1).png")); // NOI18N
        btnDesativarCliente.setText("Desativar cliente");
        btnDesativarCliente.setBorder(null);
        btnDesativarCliente.setPreferredSize(new java.awt.Dimension(193, 40));

        javax.swing.GroupLayout painelHistoricoClientesLayout = new javax.swing.GroupLayout(painelHistoricoClientes);
        painelHistoricoClientes.setLayout(painelHistoricoClientesLayout);
        painelHistoricoClientesLayout.setHorizontalGroup(
            painelHistoricoClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelHistoricoClientesLayout.createSequentialGroup()
                .addGap(85, 85, 85)
                .addComponent(btnLimparEdicao, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(402, 402, 402)
                .addComponent(btnSalvarAlteracoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 390, Short.MAX_VALUE)
                .addComponent(btnDesativarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(116, 116, 116))
        );
        painelHistoricoClientesLayout.setVerticalGroup(
            painelHistoricoClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelHistoricoClientesLayout.createSequentialGroup()
                .addGroup(painelHistoricoClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(painelHistoricoClientesLayout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addGroup(painelHistoricoClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnSalvarAlteracoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnDesativarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(painelHistoricoClientesLayout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addComponent(btnLimparEdicao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(55, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout painelEdicaoLayout = new javax.swing.GroupLayout(painelEdicao);
        painelEdicao.setLayout(painelEdicaoLayout);
        painelEdicaoLayout.setHorizontalGroup(
            painelEdicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelEdicaoLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(painelEdicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(painelHistoricoClientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(painelEdicaoClientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(painelEdicaoClientes1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(1243, Short.MAX_VALUE))
        );
        painelEdicaoLayout.setVerticalGroup(
            painelEdicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelEdicaoLayout.createSequentialGroup()
                .addGap(72, 72, 72)
                .addComponent(painelEdicaoClientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addComponent(painelEdicaoClientes1, javax.swing.GroupLayout.PREFERRED_SIZE, 616, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addComponent(painelHistoricoClientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(1245, Short.MAX_VALUE))
        );

        abasClientes.addTab("Edição e Desativação", null, painelEdicao);

        painelHistorico.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        btnComprovante.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18)); // NOI18N
        btnComprovante.setText("Comprovante");
        btnComprovante.setBorder(null);

        btnExportarHistorico.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18)); // NOI18N
        btnExportarHistorico.setText("Exportar ");
        btnExportarHistorico.setBorder(null);
        btnExportarHistorico.setPreferredSize(new java.awt.Dimension(171, 40));

        painelhistoricoCompras.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        painelhistoricoCompras.setPreferredSize(new java.awt.Dimension(300, 200));

        tabelaComprasHistorico.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        tabelaComprasHistorico.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        tabelaComprasHistorico.setForeground(new java.awt.Color(255, 255, 255));
        tabelaComprasHistorico.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Código da compra", "Data", "Valor total", "Forma de pagamento", "Status"
            }
        ));
        tabelaComprasHistorico.setShowVerticalLines(true);
        jScrollPane2.setViewportView(tabelaComprasHistorico);

        lblTituloComprasHistorico.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        lblTituloComprasHistorico.setIcon(new javax.swing.ImageIcon("C:\\Users\\CleicianeGomes\\Documents\\ProjetoPI_Supermercado\\GerenciamentoSupermercado\\src\\main\\resources\\assets\\cart.png")); // NOI18N
        lblTituloComprasHistorico.setText("Histórico de Compras");

        javax.swing.GroupLayout painelhistoricoComprasLayout = new javax.swing.GroupLayout(painelhistoricoCompras);
        painelhistoricoCompras.setLayout(painelhistoricoComprasLayout);
        painelhistoricoComprasLayout.setHorizontalGroup(
            painelhistoricoComprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelhistoricoComprasLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(lblTituloComprasHistorico, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(painelhistoricoComprasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1355, Short.MAX_VALUE)
                .addContainerGap())
        );
        painelhistoricoComprasLayout.setVerticalGroup(
            painelhistoricoComprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelhistoricoComprasLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(lblTituloComprasHistorico)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        painelhistorico.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        painelhistorico.setPreferredSize(new java.awt.Dimension(1200, 900));

        btnPesquisarHistorico.setBackground(new java.awt.Color(10, 4, 138));
        btnPesquisarHistorico.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        btnPesquisarHistorico.setForeground(new java.awt.Color(255, 255, 255));
        btnPesquisarHistorico.setIcon(new javax.swing.ImageIcon("C:\\Users\\CleicianeGomes\\Documents\\ProjetoPI_Supermercado\\GerenciamentoSupermercado\\src\\main\\resources\\assets\\search (3).png")); // NOI18N
        btnPesquisarHistorico.setText("Pesquisar");
        btnPesquisarHistorico.setBorder(null);
        btnPesquisarHistorico.setPreferredSize(new java.awt.Dimension(98, 40));

        txtPesquisaHistorico.setPreferredSize(new java.awt.Dimension(68, 35));

        lblTituloHistorico.setFont(new java.awt.Font("Arial Black", 1, 30)); // NOI18N
        lblTituloHistorico.setForeground(new java.awt.Color(10, 4, 138));
        lblTituloHistorico.setText("Histórico de Compras e Pagamentos");

        btnLimparHistorico.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        btnLimparHistorico.setIcon(new javax.swing.ImageIcon("C:\\Users\\CleicianeGomes\\Documents\\ProjetoPI_Supermercado\\GerenciamentoSupermercado\\src\\main\\resources\\assets\\replay.png")); // NOI18N
        btnLimparHistorico.setText("Limpar");
        btnLimparHistorico.setBorder(null);
        btnLimparHistorico.setPreferredSize(new java.awt.Dimension(176, 40));

        lbpesquisarcliente.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lbpesquisarcliente.setIcon(new javax.swing.ImageIcon("C:\\Users\\CleicianeGomes\\Documents\\ProjetoPI_Supermercado\\GerenciamentoSupermercado\\src\\main\\resources\\assets\\search (4).png")); // NOI18N
        lbpesquisarcliente.setText("Pesquisar cliente:");

        lblDescricaoHistorico.setFont(new java.awt.Font("Arial", 0, 22)); // NOI18N
        lblDescricaoHistorico.setText("Consulte as compras e os pagamentos realizados por cada cliente.");

        javax.swing.GroupLayout painelhistoricoLayout = new javax.swing.GroupLayout(painelhistorico);
        painelhistorico.setLayout(painelhistoricoLayout);
        painelhistoricoLayout.setHorizontalGroup(
            painelhistoricoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelhistoricoLayout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addGroup(painelhistoricoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtPesquisaHistorico, javax.swing.GroupLayout.PREFERRED_SIZE, 798, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbpesquisarcliente)
                    .addComponent(lblDescricaoHistorico)
                    .addComponent(lblTituloHistorico, javax.swing.GroupLayout.PREFERRED_SIZE, 907, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(403, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelhistoricoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnPesquisarHistorico, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(76, 76, 76)
                .addComponent(btnLimparHistorico, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(79, 79, 79))
        );
        painelhistoricoLayout.setVerticalGroup(
            painelhistoricoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelhistoricoLayout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(lblTituloHistorico)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDescricaoHistorico)
                .addGap(34, 34, 34)
                .addComponent(lbpesquisarcliente)
                .addGap(4, 4, 4)
                .addComponent(txtPesquisaHistorico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(painelhistoricoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnPesquisarHistorico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLimparHistorico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(35, Short.MAX_VALUE))
        );

        painelhistoricoPagamentos.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        painelhistoricoPagamentos.setPreferredSize(new java.awt.Dimension(400, 400));

        lblTituloPagamentosHistorico.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        lblTituloPagamentosHistorico.setIcon(new javax.swing.ImageIcon("C:\\Users\\CleicianeGomes\\Documents\\ProjetoPI_Supermercado\\GerenciamentoSupermercado\\src\\main\\resources\\assets\\money (2).png")); // NOI18N
        lblTituloPagamentosHistorico.setText("Histórico de Pagamentos");

        tabelaPagamentosHistorico.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        tabelaPagamentosHistorico.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Código da compra", "Data", "Valor pago", "Forma de pagamento", "Situação"
            }
        ));
        tabelaPagamentosHistorico.setShowVerticalLines(true);
        jScrollPane3.setViewportView(tabelaPagamentosHistorico);

        javax.swing.GroupLayout painelhistoricoPagamentosLayout = new javax.swing.GroupLayout(painelhistoricoPagamentos);
        painelhistoricoPagamentos.setLayout(painelhistoricoPagamentosLayout);
        painelhistoricoPagamentosLayout.setHorizontalGroup(
            painelhistoricoPagamentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelhistoricoPagamentosLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(painelhistoricoPagamentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(painelhistoricoPagamentosLayout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(lblTituloPagamentosHistorico, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(painelhistoricoPagamentosLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 1210, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(139, Short.MAX_VALUE))
        );
        painelhistoricoPagamentosLayout.setVerticalGroup(
            painelhistoricoPagamentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelhistoricoPagamentosLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(lblTituloPagamentosHistorico)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        lbGerarComprovante.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lbGerarComprovante.setText("Gerar comprovante de uma compra ou pagamento selecionado.");

        painelTotalCompras.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lbtotalcompras.setForeground(new java.awt.Color(153, 153, 153));
        lbtotalcompras.setText("TOTAL DE COMPRAS");

        lbValortotalCompras.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        lbValortotalCompras.setText("R$ 500,00");

        lBSubtituloCompras.setText("3 compras  registradas");

        lbIconeTotalCompras.setIcon(new javax.swing.ImageIcon("C:\\Users\\CleicianeGomes\\Documents\\ProjetoPI_Supermercado\\GerenciamentoSupermercado\\src\\main\\resources\\assets\\cart (4).png")); // NOI18N

        javax.swing.GroupLayout painelTotalComprasLayout = new javax.swing.GroupLayout(painelTotalCompras);
        painelTotalCompras.setLayout(painelTotalComprasLayout);
        painelTotalComprasLayout.setHorizontalGroup(
            painelTotalComprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelTotalComprasLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(lbIconeTotalCompras, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(painelTotalComprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbtotalcompras)
                    .addComponent(lbValortotalCompras, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lBSubtituloCompras, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 14, Short.MAX_VALUE))
        );
        painelTotalComprasLayout.setVerticalGroup(
            painelTotalComprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelTotalComprasLayout.createSequentialGroup()
                .addGroup(painelTotalComprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(painelTotalComprasLayout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(lbtotalcompras)
                        .addGap(18, 18, 18)
                        .addComponent(lbValortotalCompras)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                        .addComponent(lBSubtituloCompras))
                    .addGroup(painelTotalComprasLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbIconeTotalCompras, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(21, 21, 21))
        );

        painelTotalPago.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        painelTotalPago.setPreferredSize(new java.awt.Dimension(146, 147));

        lBtotalPago.setForeground(new java.awt.Color(153, 153, 153));
        lBtotalPago.setText("TOTAL PAGO");

        LbVALORtOTALpAGO.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        LbVALORtOTALpAGO.setText("R$ 350,00");

        lBsubTotalPagp.setText("Pagamentos confirmados");

        lbTotalPagoCompras.setIcon(new javax.swing.ImageIcon("C:\\Users\\CleicianeGomes\\Documents\\ProjetoPI_Supermercado\\GerenciamentoSupermercado\\src\\main\\resources\\assets\\dollar (1).png")); // NOI18N

        javax.swing.GroupLayout painelTotalPagoLayout = new javax.swing.GroupLayout(painelTotalPago);
        painelTotalPago.setLayout(painelTotalPagoLayout);
        painelTotalPagoLayout.setHorizontalGroup(
            painelTotalPagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelTotalPagoLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(lbTotalPagoCompras, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addGroup(painelTotalPagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lBsubTotalPagp)
                    .addComponent(LbVALORtOTALpAGO)
                    .addComponent(lBtotalPago))
                .addContainerGap(252, Short.MAX_VALUE))
        );
        painelTotalPagoLayout.setVerticalGroup(
            painelTotalPagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelTotalPagoLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(lBtotalPago)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(LbVALORtOTALpAGO)
                .addGap(24, 24, 24)
                .addComponent(lBsubTotalPagp)
                .addContainerGap(13, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelTotalPagoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbTotalPagoCompras, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34))
        );

        lBSaldoPendente.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        lBSaldoPendente.setPreferredSize(new java.awt.Dimension(146, 104));

        lbsaldopendente1.setForeground(new java.awt.Color(153, 153, 153));
        lbsaldopendente1.setText("SALDO PENDENTE");

        lBvalorPendente.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        lBvalorPendente.setText("R$ 150,00");

        lBsubValorPendente.setText("Valor a pagar");

        lbiconeSaldoPendente.setIcon(new javax.swing.ImageIcon("C:\\Users\\CleicianeGomes\\Documents\\ProjetoPI_Supermercado\\GerenciamentoSupermercado\\src\\main\\resources\\assets\\clock (3).png")); // NOI18N

        javax.swing.GroupLayout lBSaldoPendenteLayout = new javax.swing.GroupLayout(lBSaldoPendente);
        lBSaldoPendente.setLayout(lBSaldoPendenteLayout);
        lBSaldoPendenteLayout.setHorizontalGroup(
            lBSaldoPendenteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, lBSaldoPendenteLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(lbiconeSaldoPendente, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addGroup(lBSaldoPendenteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lBvalorPendente, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbsaldopendente1)
                    .addComponent(lBsubValorPendente, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(290, Short.MAX_VALUE))
        );
        lBSaldoPendenteLayout.setVerticalGroup(
            lBSaldoPendenteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lBSaldoPendenteLayout.createSequentialGroup()
                .addGroup(lBSaldoPendenteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(lBSaldoPendenteLayout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(lbsaldopendente1)
                        .addGap(12, 12, 12)
                        .addComponent(lBvalorPendente)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lBsubValorPendente))
                    .addGroup(lBSaldoPendenteLayout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(lbiconeSaldoPendente, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout painelHistoricoLayout = new javax.swing.GroupLayout(painelHistorico);
        painelHistorico.setLayout(painelHistoricoLayout);
        painelHistoricoLayout.setHorizontalGroup(
            painelHistoricoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelHistoricoLayout.createSequentialGroup()
                .addGap(121, 121, 121)
                .addGroup(painelHistoricoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(painelhistoricoCompras, javax.swing.GroupLayout.PREFERRED_SIZE, 1371, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(painelhistorico, javax.swing.GroupLayout.PREFERRED_SIZE, 1371, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(painelhistoricoPagamentos, javax.swing.GroupLayout.PREFERRED_SIZE, 1371, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(82, 82, 82)
                .addGroup(painelHistoricoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(painelTotalCompras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lBSaldoPendente, javax.swing.GroupLayout.PREFERRED_SIZE, 407, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(painelTotalPago, javax.swing.GroupLayout.PREFERRED_SIZE, 407, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbGerarComprovante)
                    .addComponent(btnComprovante, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExportarHistorico, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(797, Short.MAX_VALUE))
        );
        painelHistoricoLayout.setVerticalGroup(
            painelHistoricoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelHistoricoLayout.createSequentialGroup()
                .addContainerGap(46, Short.MAX_VALUE)
                .addGroup(painelHistoricoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(painelHistoricoLayout.createSequentialGroup()
                        .addComponent(painelhistorico, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42)
                        .addComponent(painelhistoricoCompras, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(painelHistoricoLayout.createSequentialGroup()
                        .addComponent(painelTotalCompras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(67, 67, 67)
                        .addComponent(lBSaldoPendente, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(painelTotalPago, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(painelHistoricoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(painelHistoricoLayout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(painelhistoricoPagamentos, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(painelHistoricoLayout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(lbGerarComprovante)
                        .addGap(18, 18, 18)
                        .addComponent(btnComprovante, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnExportarHistorico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(1745, Short.MAX_VALUE))
        );

        abasClientes.addTab("Histórico de Compras e Pagamentos", null, painelHistorico);

        painelPedidosRelatorio.setPreferredSize(new java.awt.Dimension(1200, 936));

        lblTituloPedidosRelatorio.setFont(new java.awt.Font("Arial Black", 1, 30)); // NOI18N
        lblTituloPedidosRelatorio.setText("Pedidos e Relatório de Clientes");

        painelpedidosTitulo.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblPesquisaClienteRelatorio.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        lblPesquisaClienteRelatorio.setIcon(new javax.swing.ImageIcon("C:\\Users\\CleicianeGomes\\Documents\\ProjetoPI_Supermercado\\GerenciamentoSupermercado\\src\\main\\resources\\assets\\search (4).png")); // NOI18N
        lblPesquisaClienteRelatorio.setText("Pesquisar cliente");

        cbPeriodoRelatorio.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        cbPeriodoRelatorio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos", "Últimos 7 dias", "Últimos 30 dias", "Este ano" }));
        cbPeriodoRelatorio.setPreferredSize(new java.awt.Dimension(118, 35));
        cbPeriodoRelatorio.addActionListener(this::cbPeriodoRelatorioActionPerformed);

        lblPeriodoRelatorio.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        lblPeriodoRelatorio.setIcon(new javax.swing.ImageIcon("C:\\Users\\CleicianeGomes\\Documents\\ProjetoPI_Supermercado\\GerenciamentoSupermercado\\src\\main\\resources\\assets\\date (1).png")); // NOI18N
        lblPeriodoRelatorio.setText("Período");

        cbStatusRelatorio.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        cbStatusRelatorio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos ", "Ativo", "Inativo" }));
        cbStatusRelatorio.setPreferredSize(new java.awt.Dimension(76, 35));

        lblStatusRelatorio.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        lblStatusRelatorio.setIcon(new javax.swing.ImageIcon("C:\\Users\\CleicianeGomes\\Documents\\ProjetoPI_Supermercado\\GerenciamentoSupermercado\\src\\main\\resources\\assets\\list-items.png")); // NOI18N
        lblStatusRelatorio.setText("Status");

        btnLimparFiltrosRelatorio.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        btnLimparFiltrosRelatorio.setIcon(new javax.swing.ImageIcon("C:\\Users\\CleicianeGomes\\Documents\\ProjetoPI_Supermercado\\GerenciamentoSupermercado\\src\\main\\resources\\assets\\replay.png")); // NOI18N
        btnLimparFiltrosRelatorio.setBorder(null);
        btnLimparFiltrosRelatorio.setLabel("Limpar");
        btnLimparFiltrosRelatorio.setPreferredSize(new java.awt.Dimension(138, 40));

        btnPesquisarRelatorio.setBackground(new java.awt.Color(10, 4, 138));
        btnPesquisarRelatorio.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        btnPesquisarRelatorio.setForeground(new java.awt.Color(255, 255, 255));
        btnPesquisarRelatorio.setIcon(new javax.swing.ImageIcon("C:\\Users\\CleicianeGomes\\Documents\\ProjetoPI_Supermercado\\GerenciamentoSupermercado\\src\\main\\resources\\assets\\search (3).png")); // NOI18N
        btnPesquisarRelatorio.setText("Pesquisar");
        btnPesquisarRelatorio.setBorder(null);
        btnPesquisarRelatorio.setPreferredSize(new java.awt.Dimension(75, 40));

        txtPesquisaClienteRelatorio.setPreferredSize(new java.awt.Dimension(68, 35));

        javax.swing.GroupLayout painelpedidosTituloLayout = new javax.swing.GroupLayout(painelpedidosTitulo);
        painelpedidosTitulo.setLayout(painelpedidosTituloLayout);
        painelpedidosTituloLayout.setHorizontalGroup(
            painelpedidosTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelpedidosTituloLayout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(painelpedidosTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtPesquisaClienteRelatorio, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPesquisaClienteRelatorio, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(56, 56, 56)
                .addGroup(painelpedidosTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cbStatusRelatorio, 0, 131, Short.MAX_VALUE)
                    .addComponent(lblStatusRelatorio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(66, 66, 66)
                .addGroup(painelpedidosTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtPesquisaClienteRelatorio, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPesquisaClienteRelatorio, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(56, 56, 56)
                .addGroup(painelpedidosTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cbStatusRelatorio, 0, 131, Short.MAX_VALUE)
                    .addComponent(lblStatusRelatorio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(66, 66, 66)
                .addGroup(painelpedidosTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(painelpedidosTituloLayout.createSequentialGroup()
                        .addComponent(cbPeriodoRelatorio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 129, Short.MAX_VALUE)
                        .addComponent(btnPesquisarRelatorio, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41)
                        .addComponent(btnLimparFiltrosRelatorio, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26))
                    .addGroup(painelpedidosTituloLayout.createSequentialGroup()
                        .addComponent(lblPeriodoRelatorio, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        painelpedidosTituloLayout.setVerticalGroup(
            painelpedidosTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelpedidosTituloLayout.createSequentialGroup()
                .addGroup(painelpedidosTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(painelpedidosTituloLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(painelpedidosTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblStatusRelatorio)
                            .addComponent(lblPeriodoRelatorio))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(painelpedidosTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbStatusRelatorio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbPeriodoRelatorio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnPesquisarRelatorio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnLimparFiltrosRelatorio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(painelpedidosTituloLayout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(lblPesquisaClienteRelatorio, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPesquisaClienteRelatorio, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(43, 43, 43))
        );

        painelPedidosClientes.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jTable1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTable1.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Nº do pedido", "Código do cliente", "Nome do cliente", "Data do pedido", "Valor total", "Status"
            }
        ));
        jTable1.setShowVerticalLines(true);
        tabelaPedidosClientes.setViewportView(jTable1);

        lblTituloTabelaPedidos.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        lblTituloTabelaPedidos.setIcon(new javax.swing.ImageIcon("C:\\Users\\CleicianeGomes\\Documents\\ProjetoPI_Supermercado\\GerenciamentoSupermercado\\src\\main\\resources\\assets\\cart.png")); // NOI18N
        lblTituloTabelaPedidos.setText("Pedidos dos Clientes");

        javax.swing.GroupLayout painelPedidosClientesLayout = new javax.swing.GroupLayout(painelPedidosClientes);
        painelPedidosClientes.setLayout(painelPedidosClientesLayout);
        painelPedidosClientesLayout.setHorizontalGroup(
            painelPedidosClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelPedidosClientesLayout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(painelPedidosClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tabelaPedidosClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 1230, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTituloTabelaPedidos))
                .addContainerGap(102, Short.MAX_VALUE))
        );
        painelPedidosClientesLayout.setVerticalGroup(
            painelPedidosClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelPedidosClientesLayout.createSequentialGroup()
                .addContainerGap(55, Short.MAX_VALUE)
                .addComponent(lblTituloTabelaPedidos, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tabelaPedidosClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );

        painelTotalPedidos.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblQuantidadePedidosRelatorio.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lblQuantidadePedidosRelatorio.setText("0");

        lblTotalPedidosRelatorio.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        lblTotalPedidosRelatorio.setText("Total de pedidos:");

        lbIconetotalPedidos.setIcon(new javax.swing.ImageIcon("C:\\Users\\CleicianeGomes\\Documents\\ProjetoPI_Supermercado\\GerenciamentoSupermercado\\src\\main\\resources\\assets\\cart-shopping-fast (1).png")); // NOI18N

        javax.swing.GroupLayout painelTotalPedidosLayout = new javax.swing.GroupLayout(painelTotalPedidos);
        painelTotalPedidos.setLayout(painelTotalPedidosLayout);
        painelTotalPedidosLayout.setHorizontalGroup(
            painelTotalPedidosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelTotalPedidosLayout.createSequentialGroup()
                .addGroup(painelTotalPedidosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(painelTotalPedidosLayout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(painelTotalPedidosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblQuantidadePedidosRelatorio)
                            .addComponent(lblTotalPedidosRelatorio, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(painelTotalPedidosLayout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(lbIconetotalPedidos, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(39, Short.MAX_VALUE))
        );
        painelTotalPedidosLayout.setVerticalGroup(
            painelTotalPedidosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelTotalPedidosLayout.createSequentialGroup()
                .addContainerGap(34, Short.MAX_VALUE)
                .addComponent(lbIconetotalPedidos)
                .addGap(18, 18, 18)
                .addComponent(lblTotalPedidosRelatorio)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblQuantidadePedidosRelatorio)
                .addGap(23, 23, 23))
        );

        painelvalorTotalPedidos.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblValorPedidosRelatorio.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        lblValorPedidosRelatorio.setText("Valor total dos pedidos:");

        lblTotalValorPedidosRelatorio.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lblTotalValorPedidosRelatorio.setText("R$ 0,00");

        lbIconeValorTotal.setIcon(new javax.swing.ImageIcon("C:\\Users\\CleicianeGomes\\Documents\\ProjetoPI_Supermercado\\GerenciamentoSupermercado\\src\\main\\resources\\assets\\coins.png")); // NOI18N

        javax.swing.GroupLayout painelvalorTotalPedidosLayout = new javax.swing.GroupLayout(painelvalorTotalPedidos);
        painelvalorTotalPedidos.setLayout(painelvalorTotalPedidosLayout);
        painelvalorTotalPedidosLayout.setHorizontalGroup(
            painelvalorTotalPedidosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelvalorTotalPedidosLayout.createSequentialGroup()
                .addGroup(painelvalorTotalPedidosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(painelvalorTotalPedidosLayout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(lblTotalValorPedidosRelatorio, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelvalorTotalPedidosLayout.createSequentialGroup()
                        .addGap(0, 22, Short.MAX_VALUE)
                        .addComponent(lblValorPedidosRelatorio)))
                .addContainerGap())
            .addGroup(painelvalorTotalPedidosLayout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addComponent(lbIconeValorTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        painelvalorTotalPedidosLayout.setVerticalGroup(
            painelvalorTotalPedidosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelvalorTotalPedidosLayout.createSequentialGroup()
                .addContainerGap(29, Short.MAX_VALUE)
                .addComponent(lbIconeValorTotal)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblValorPedidosRelatorio, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTotalValorPedidosRelatorio)
                .addGap(23, 23, 23))
        );

        painelPedidospendetes.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblQuantidadePendentesRelatorio.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lblQuantidadePendentesRelatorio.setText("0");

        lblPedidosPendentesRelatorio.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        lblPedidosPendentesRelatorio.setText("Pedidos pendentes:");

        lbIconePendente.setIcon(new javax.swing.ImageIcon("C:\\Users\\CleicianeGomes\\Documents\\ProjetoPI_Supermercado\\GerenciamentoSupermercado\\src\\main\\resources\\assets\\clock-three (1).png")); // NOI18N

        javax.swing.GroupLayout painelPedidospendetesLayout = new javax.swing.GroupLayout(painelPedidospendetes);
        painelPedidospendetes.setLayout(painelPedidospendetesLayout);
        painelPedidospendetesLayout.setHorizontalGroup(
            painelPedidospendetesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelPedidospendetesLayout.createSequentialGroup()
                .addGroup(painelPedidospendetesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(painelPedidospendetesLayout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addComponent(lbIconePendente))
                    .addGroup(painelPedidospendetesLayout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(painelPedidospendetesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblPedidosPendentesRelatorio)
                            .addComponent(lblQuantidadePendentesRelatorio))))
                .addContainerGap(48, Short.MAX_VALUE))
        );
        painelPedidospendetesLayout.setVerticalGroup(
            painelPedidospendetesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelPedidospendetesLayout.createSequentialGroup()
                .addContainerGap(47, Short.MAX_VALUE)
                .addComponent(lbIconePendente)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblPedidosPendentesRelatorio)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblQuantidadePendentesRelatorio)
                .addGap(31, 31, 31))
        );

        painelGerarRelatorio.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblTipoRelatorio.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        lblTipoRelatorio.setText("Tipo de relatório");

        jLabel1.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel1.setText("Formato");

        btnGerarRelatorio.setBackground(new java.awt.Color(10, 4, 138));
        btnGerarRelatorio.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        btnGerarRelatorio.setForeground(new java.awt.Color(255, 255, 255));
        btnGerarRelatorio.setText("Gerar relatório");
        btnGerarRelatorio.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnGerarRelatorio.setPreferredSize(new java.awt.Dimension(109, 40));

        jComboBox1.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "PDF ", "CSV", " " }));
        jComboBox1.setPreferredSize(new java.awt.Dimension(77, 35));

        lbMesRelatorio.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lbMesRelatorio.setText("Mês");

        lbPeriodoRelatorio.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        lbPeriodoRelatorio.setText("Período do relatório");
        lbPeriodoRelatorio.setIconTextGap(8);

        lbAnoRelatorio.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lbAnoRelatorio.setText("Ano");

        cbAnoRelatorio.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        cbAnoRelatorio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2025", "2026", "2027", " " }));
        cbAnoRelatorio.setPreferredSize(new java.awt.Dimension(76, 35));

        cbMesRelatorio.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        cbMesRelatorio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro" }));
        cbMesRelatorio.setPreferredSize(new java.awt.Dimension(111, 35));
        cbMesRelatorio.addActionListener(this::cbMesRelatorioActionPerformed);

        cbTipoRelatorio.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        cbTipoRelatorio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Clientes cadastrados ", "Histórico de compras", "Histórico de pagamentos", "Pedidos por período" }));
        cbTipoRelatorio.setPreferredSize(new java.awt.Dimension(201, 35));
        cbTipoRelatorio.addActionListener(this::cbTipoRelatorioActionPerformed);

        lblTituloGerarRelatorio.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 22)); // NOI18N
        lblTituloGerarRelatorio.setText("Gerar Relatório");

        javax.swing.GroupLayout painelGerarRelatorioLayout = new javax.swing.GroupLayout(painelGerarRelatorio);
        painelGerarRelatorio.setLayout(painelGerarRelatorioLayout);
        painelGerarRelatorioLayout.setHorizontalGroup(
            painelGerarRelatorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelGerarRelatorioLayout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addGroup(painelGerarRelatorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1)
                    .addComponent(lblTipoRelatorio, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTituloGerarRelatorio, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbTipoRelatorio, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnGerarRelatorio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbPeriodoRelatorio, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelGerarRelatorioLayout.createSequentialGroup()
                        .addGroup(painelGerarRelatorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbMesRelatorio, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbMesRelatorio))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 123, Short.MAX_VALUE)
                        .addGroup(painelGerarRelatorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbTipoRelatorio, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(painelGerarRelatorioLayout.createSequentialGroup()
                                .addGroup(painelGerarRelatorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblTituloGerarRelatorio, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbPeriodoRelatorio, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblTipoRelatorio, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(65, 65, 65))))
        );
        painelGerarRelatorioLayout.setVerticalGroup(
            painelGerarRelatorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelGerarRelatorioLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(lblTituloGerarRelatorio)
                .addGap(18, 18, 18)
                .addComponent(lblTipoRelatorio)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbTipoRelatorio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addComponent(lbPeriodoRelatorio)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(painelGerarRelatorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbMesRelatorio, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbAnoRelatorio))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painelGerarRelatorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbMesRelatorio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbAnoRelatorio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnGerarRelatorio, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblDescricaoPedidosRelatorio.setFont(new java.awt.Font("Arial", 0, 22)); // NOI18N
        lblDescricaoPedidosRelatorio.setText("Consulte os pedidos dos clientes e gere relatórios de cadastro e movimentação.");

        javax.swing.GroupLayout painelPedidosRelatorioLayout = new javax.swing.GroupLayout(painelPedidosRelatorio);
        painelPedidosRelatorio.setLayout(painelPedidosRelatorioLayout);
        painelPedidosRelatorioLayout.setHorizontalGroup(
            painelPedidosRelatorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelPedidosRelatorioLayout.createSequentialGroup()
                .addGroup(painelPedidosRelatorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(painelPedidosRelatorioLayout.createSequentialGroup()
                        .addGap(86, 86, 86)
                        .addComponent(painelGerarRelatorio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(painelPedidosRelatorioLayout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addGroup(painelPedidosRelatorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(painelPedidosRelatorioLayout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(painelTotalPedidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(197, 197, 197)
                                .addComponent(painelPedidospendetes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(591, 591, 591)
                                .addComponent(painelvalorTotalPedidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblDescricaoPedidosRelatorio)
                            .addComponent(lblTituloPedidosRelatorio)))
                    .addGroup(painelPedidosRelatorioLayout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addGroup(painelPedidosRelatorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(painelpedidosTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(painelPedidosClientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(1265, Short.MAX_VALUE))
        );
        painelPedidosRelatorioLayout.setVerticalGroup(
            painelPedidosRelatorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelPedidosRelatorioLayout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addComponent(lblTituloPedidosRelatorio, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDescricaoPedidosRelatorio)
                .addGap(32, 32, 32)
                .addComponent(painelpedidosTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(painelPedidosClientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(painelPedidosRelatorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(painelPedidosRelatorioLayout.createSequentialGroup()
                        .addGap(85, 85, 85)
                        .addComponent(painelvalorTotalPedidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(painelPedidosRelatorioLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(painelPedidosRelatorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(painelPedidospendetes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(painelTotalPedidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 35, 35)))
                .addComponent(painelGerarRelatorio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(1289, Short.MAX_VALUE))
        );

        abasClientes.addTab("Pedidos e Relatório", new javax.swing.ImageIcon("C:\\Users\\CleicianeGomes\\Documents\\ProjetoPI_Supermercado\\GerenciamentoSupermercado\\src\\main\\resources\\assets\\document.png"), painelPedidosRelatorio); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(2167, 2167, 2167)
                .addComponent(painelConteudo, javax.swing.GroupLayout.DEFAULT_SIZE, 836, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(abasClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 2882, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(abasClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 2713, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(painelConteudo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimparActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLimparActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
 cadastrarCliente();
        // TEXT FIELD
String nomeCompleto = txtNome.getText().trim();

String rg = txtRGCadastro.getText().trim();

String email = txtEmailCadastro.getText().trim();


// FORMATTED FIELD
String cpf = ftfCPF.getText().replaceAll("[^0-9]", "");

String telefone = ftfTelefone.getText().replaceAll("[^0-9]", "");

String dataNascimento = ftData.getText().trim();


// PASSWORD FIELD
String senha = new String(pfSenha.getPassword());


// GÊNERO (RADIO BUTTON)
String genero = "";

if (rbFeminino.isSelected()) {
    genero = "Feminino";
} else if (rbMasculino.isSelected()) {
    genero = "Masculino";
} else if (rbOutro.isSelected()) {
    genero = "Outro";
}
// COMBO BOX - STATUS
String statusSelecionado = cbStatusCadastro.getSelectedItem().toString();

int status = statusSelecionado.equalsIgnoreCase("Ativo") ? 1 : 0;

        //COMBO  BOX
        /*
        String nome  = nomeVariavel.getSelectedItem().toString();
        */

        //TEXT   AREA
        /*
        String nome = nomeVariavel.getText().toString();
        */
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void ftfCPFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ftfCPFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ftfCPFActionPerformed

    private void cbStatusConsultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbStatusConsultaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbStatusConsultaActionPerformed

    private void txtEmailEdicaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmailEdicaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmailEdicaoActionPerformed

    private void txtRGEdicaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtRGEdicaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRGEdicaoActionPerformed

    private void cbMesRelatorioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbMesRelatorioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbMesRelatorioActionPerformed

    private void cbTipoRelatorioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbTipoRelatorioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbTipoRelatorioActionPerformed

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
        
       //  Usuario usuario = new Usuario("Cleiciane", "administradora",null);

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new TelasClientes(usuario).setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Bairro;
    private javax.swing.JLabel LbVALORtOTALpAGO;
    private javax.swing.JTabbedPane abasClientes;
    private javax.swing.JButton btnComprovante;
    private javax.swing.JButton btnDesativarCliente;
    private javax.swing.JButton btnExportarHistorico;
    private javax.swing.JButton btnGerarRelatorio;
    private javax.swing.JButton btnLimpar;
    private javax.swing.JButton btnLimparEdicao;
    private javax.swing.JButton btnLimparFiltros;
    private javax.swing.JButton btnLimparFiltrosRelatorio;
    private javax.swing.JButton btnLimparHistorico;
    private javax.swing.JButton btnPesquisarConsulta;
    private javax.swing.JButton btnPesquisarEdicao;
    private javax.swing.JButton btnPesquisarHistorico;
    private javax.swing.JButton btnPesquisarRelatorio;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JButton btnSalvarAlteracoes;
    private javax.swing.JButton btnVisualizarDetalhes;
    private javax.swing.JComboBox<String> cbAnoRelatorio;
    private javax.swing.JComboBox<String> cbMesRelatorio;
    private javax.swing.JComboBox<String> cbPeriodoRelatorio;
    private javax.swing.JComboBox<String> cbStatusCadastro;
    private javax.swing.JComboBox<String> cbStatusConsulta;
    private javax.swing.JComboBox<String> cbStatusEdicao;
    private javax.swing.JComboBox<String> cbStatusRelatorio;
    private javax.swing.JComboBox<String> cbTipoRelatorio;
    private javax.swing.JFormattedTextField ftData;
    private javax.swing.JFormattedTextField ftfCPF;
    private javax.swing.JFormattedTextField ftfTelefone;
    private javax.swing.JLabel grupoGenero;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JPanel lBSaldoPendente;
    private javax.swing.JLabel lBSubtituloCompras;
    private javax.swing.JLabel lBsubTotalPagp;
    private javax.swing.JLabel lBsubValorPendente;
    private javax.swing.JLabel lBtotalPago;
    private javax.swing.JLabel lBvalorPendente;
    private javax.swing.JLabel lbAnoRelatorio;
    private javax.swing.JLabel lbData;
    private javax.swing.JLabel lbEmail;
    private javax.swing.JLabel lbGerarComprovante;
    private javax.swing.JLabel lbIconePendente;
    private javax.swing.JLabel lbIconeValorTotal;
    private javax.swing.JLabel lbIconetotalPedidos;
    private javax.swing.JLabel lbMesRelatorio;
    private javax.swing.JLabel lbPeriodoRelatorio;
    private javax.swing.JLabel lbRg;
    private javax.swing.JLabel lbSenha;
    private javax.swing.JLabel lbStatuscadastro;
    private javax.swing.JLabel lbValortotalCompras;
    private javax.swing.JLabel lbdadosPessoais;
    private javax.swing.JLabel lbiconeSaldoPendente;
    private javax.swing.JLabel lblCPF;
    private javax.swing.JLabel lblCPFCdicao;
    private javax.swing.JLabel lblCidadeEdicao;
    private javax.swing.JLabel lblClientesCadastrados;
    private javax.swing.JLabel lblCodigoEdicao;
    private javax.swing.JLabel lblDataNascimentoEdicao;
    private javax.swing.JLabel lblDescricaoCadastro;
    private javax.swing.JLabel lblDescricaoConsulta;
    private javax.swing.JLabel lblDescricaoEdicao;
    private javax.swing.JLabel lblDescricaoHistorico;
    private javax.swing.JLabel lblDescricaoPedidosRelatorio;
    private javax.swing.JLabel lblDetalheCPF;
    private javax.swing.JLabel lblDetalheCodigo;
    private javax.swing.JLabel lblDetalheEmail;
    private javax.swing.JLabel lblDetalheGenero;
    private javax.swing.JLabel lblDetalheNascimento;
    private javax.swing.JLabel lblDetalheNome;
    private javax.swing.JLabel lblDetalheRG;
    private javax.swing.JLabel lblDetalheStatus;
    private javax.swing.JLabel lblDetalheTelefone;
    private javax.swing.JLabel lblEmailEdicao;
    private javax.swing.JLabel lblEnderecoEdicao;
    private javax.swing.JLabel lblMensagemConsulta;
    private javax.swing.JLabel lblNome;
    private javax.swing.JLabel lblNomeEdicao;
    private javax.swing.JLabel lblPedidosPendentesRelatorio;
    private javax.swing.JLabel lblPeriodoRelatorio;
    private javax.swing.JLabel lblPesquisaCliente;
    private javax.swing.JLabel lblPesquisaClienteRelatorio;
    private javax.swing.JLabel lblPesquisaEdicao;
    private javax.swing.JLabel lblQuantidadePedidosRelatorio;
    private javax.swing.JLabel lblQuantidadePendentesRelatorio;
    private javax.swing.JLabel lblRGEdicao;
    private javax.swing.JLabel lblStatusConsulta;
    private javax.swing.JLabel lblStatusEdicao;
    private javax.swing.JLabel lblStatusRelatorio;
    private javax.swing.JLabel lblTelefone;
    private javax.swing.JLabel lblTelefoneEdicao;
    private javax.swing.JLabel lblTipoRelatorio;
    private javax.swing.JLabel lblTituloCadastro;
    private javax.swing.JLabel lblTituloComprasHistorico;
    private javax.swing.JLabel lblTituloConsulta;
    private javax.swing.JLabel lblTituloDadosEdicao;
    private javax.swing.JLabel lblTituloEdicao;
    private javax.swing.JLabel lblTituloGerarRelatorio;
    private javax.swing.JLabel lblTituloHistorico;
    private javax.swing.JLabel lblTituloPagamentosHistorico;
    private javax.swing.JLabel lblTituloPedidosRelatorio;
    private javax.swing.JLabel lblTituloTabelaPedidos;
    private javax.swing.JLabel lblTotalPedidosRelatorio;
    private javax.swing.JLabel lblTotalValorPedidosRelatorio;
    private javax.swing.JLabel lblValorPedidosRelatorio;
    private javax.swing.JLabel lbpesquisarcliente;
    private javax.swing.JLabel lbsaldopendente1;
    private javax.swing.JLabel lbtotalcompras;
    private javax.swing.JPanel painelCadastro;
    private javax.swing.JPanel painelConsulta;
    private javax.swing.JPanel painelConsultaClientes;
    private javax.swing.JPanel painelConsultaClientes1;
    private javax.swing.JPanel painelConteudo;
    private javax.swing.JPanel painelDadosCliente;
    private javax.swing.JPanel painelDetalhesCliente;
    private javax.swing.JPanel painelEdicao;
    private javax.swing.JPanel painelEdicaoClientes;
    private javax.swing.JPanel painelEdicaoClientes1;
    private javax.swing.JPanel painelGerarRelatorio;
    private javax.swing.JPanel painelHistorico;
    private javax.swing.JPanel painelHistoricoClientes;
    private javax.swing.JPanel painelPedidosClientes;
    private javax.swing.JPanel painelPedidosRelatorio;
    private javax.swing.JPanel painelPedidospendetes;
    private javax.swing.JPanel painelTotalCompras;
    private javax.swing.JPanel painelTotalPago;
    private javax.swing.JPanel painelTotalPedidos;
    private javax.swing.JPanel painelhistorico;
    private javax.swing.JPanel painelhistoricoCompras;
    private javax.swing.JPanel painelhistoricoPagamentos;
    private javax.swing.JPanel painelpedidosTitulo;
    private javax.swing.JPanel painelvalorTotalPedidos;
    private javax.swing.JPasswordField pfSenha;
    private javax.swing.JRadioButton rbFeminino;
    private javax.swing.JRadioButton rbMasculino;
    private javax.swing.JRadioButton rbOutro;
    private javax.swing.JScrollPane scrollTabelaEdicao;
    private javax.swing.JTable tabelaComprasHistorico;
    private javax.swing.JTable tabelaConsultaClientes;
    private javax.swing.JTable tabelaEdicaoClientes;
    private javax.swing.JTable tabelaPagamentosHistorico;
    private javax.swing.JScrollPane tabelaPedidosClientes;
    private javax.swing.JFormattedTextField tfRG;
    private javax.swing.JTextField txtBairroEdicao;
    private javax.swing.JTextField txtCPFCdicao;
    private javax.swing.JTextField txtCidadeEdicao;
    private javax.swing.JTextField txtCodigoEdicao;
    private javax.swing.JTextField txtDataNascimentoEdicao;
    private javax.swing.JTextField txtEmailCadastro;
    private javax.swing.JTextField txtEmailEdicao;
    private javax.swing.JTextField txtEnderecoEdicao;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtNomeEdicao;
    private javax.swing.JTextField txtPesquisaCliente;
    private javax.swing.JTextField txtPesquisaClienteRelatorio;
    private javax.swing.JTextField txtPesquisaEdicao;
    private javax.swing.JTextField txtPesquisaHistorico;
    private javax.swing.JTextField txtRGCadastro;
    private javax.swing.JTextField txtRGEdicao;
    private javax.swing.JTextField txtTelefoneEdicao;
    // End of variables declaration//GEN-END:variables
}
