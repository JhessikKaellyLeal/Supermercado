/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import Model.Cliente;
import View.MenuPadrao;
import Model.Usuario;
import javax.swing.JOptionPane;
import Control.ClienteController ;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
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
        
         MenuPadrao.instalar(
        this,
        usuario,
        "inicio",
         
        this :: navegar,
        this ::dispose
    );
         
         //exibe a tela inicial dentro do menu
         menu.mostrarTela(new TelasClientes(usuario));
         this.setExtendedState(TelasClientes.MAXIMIZED_BOTH);
   
        
    }
    
    // METODO DE ACESSAR AS TELAS
    public void navegar (String  idtela){
          switch (idtela){
          case "inicio":
                menu.mostrarTela( new TelaHome(usuario) );
                break;
          case "produtos":
                menu.mostrarTela( new TelaProdutos(usuario));
                break; 
           case "estoque":
             //   menu.mostrarTela();
                break;
           case "fornecedores":
               // menu.mostrarTela();
                break;
            case"clientes":
                 menu .mostrarTela(new TelasClientes(usuario));
          case"promocoes":
                 //menu .mostrarTela();
           case "caixas":
               // menu.mostrarTela();
                break;
            case "vendas":
                menu.mostrarTela(new TelaPdvRealizacao(usuario));
                break;
            case "pedidos":
                menu.mostrarTela(new TelaPedidos(usuario));
                break;
            case "entregas":
                menu.mostrarTela(new TelaEntregas(usuario));
                break;
         case "rh":
                menu.mostrarTela(new Tela_Funcionarios_RH(usuario));
                break; 
          case "financeiro":
              //  menu.mostrarTela();
                break;
          case "relatorios":
                //menu.mostrarTela();
                break;
          case "permissoes":
                //menu.mostrarTela();
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
        String email = txtEmailCadastro.getText().trim();
        String telefone = ftfTelefone.getText().replaceAll("[^0-9]", "");
        String senha = new String(pfSenha.getPassword());

        // Capturar gênero
        String genero = "";

        if (rbFeminino.isSelected()) {
            genero = "Feminino";

        } else if (rbMasculino.isSelected()) {
            genero = "Masculino";

        } else if (rbOutro.isSelected()) {
            genero = "Outro";
        }

        // Capturar status selecionado
        String statusSelecionado =
                (String) cbStatusCadastroCliente.getSelectedItem();

        // Converter status para inteiro
        int status = "Ativo".equals(statusSelecionado) ? 1 : 0;

        // Validar campos obrigatórios
        if (nome.isEmpty() || cpf.isEmpty() || rg.isEmpty()
                || dataNascimento.isEmpty() || email.isEmpty()
                || telefone.isEmpty() || senha.isEmpty()
                || genero.isEmpty()) {

            JOptionPane.showMessageDialog(this,
                    "Preencha todos os campos obrigatórios!",
                    "Atenção",
                    JOptionPane.WARNING_MESSAGE);

            return;
        }

        // Validar CPF
        if (cpf.length() != 11) {

            JOptionPane.showMessageDialog(this,
                    "Informe um CPF válido!",
                    "Atenção",
                    JOptionPane.WARNING_MESSAGE);

            return;
        }

        // Validar status
        if (statusSelecionado == null
                || (!statusSelecionado.equals("Ativo")
                && !statusSelecionado.equals("Inativo"))) {

            JOptionPane.showMessageDialog(this,
                    "Selecione um status válido!",
                    "Atenção",
                    JOptionPane.WARNING_MESSAGE);

            return;
        }

        // Criar objeto Cliente
        Cliente cliente = new Cliente();

        cliente.setNome(nome);
        cliente.setCpf(cpf);
        cliente.setRg(rg);
        cliente.setDataNascimento(dataNascimento);
        cliente.setGenero(genero);
        cliente.setEmail(email);
        cliente.setTelefone(telefone);
        cliente.setStatus(status);
        cliente.setSenha(senha);

        // Chamar Controller
        ClienteController controller = new ClienteController();

        boolean sucesso = controller.cadastrar(cliente);

        // Verificar resultado
        if (sucesso) {

            JOptionPane.showMessageDialog(this,
                    "Cliente cadastrado com sucesso!",
                    "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE);

            // Limpar campos
            txtNome.setText("");
            ftfCPF.setValue(null);
            txtRGCadastro.setText("");
            ftData.setValue(null);
            txtEmailCadastro.setText("");
            ftfTelefone.setValue(null);
            pfSenha.setText("");

            rbFeminino.setSelected(false);
            rbMasculino.setSelected(false);
            rbOutro.setSelected(false);

            cbStatusCadastroCliente.setSelectedIndex(0);

        } else {

            JOptionPane.showMessageDialog(this,
                    "Não foi possível cadastrar o cliente!",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }

    } catch (Exception e) {

        JOptionPane.showMessageDialog(this,
                "Erro ao cadastrar cliente: " + e.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE);
    }
}
   

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
        cbStatusCadastroCliente = new javax.swing.JComboBox<>();
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
        painelTotalPago = new javax.swing.JPanel();
        lBtotalPago = new javax.swing.JLabel();
        LbVALORtOTALpAGO = new javax.swing.JLabel();
        lBsubTotalPagp = new javax.swing.JLabel();
        lBSaldoPendente = new javax.swing.JPanel();
        lbsaldopendente1 = new javax.swing.JLabel();
        lBvalorPendente = new javax.swing.JLabel();
        lBsubValorPendente = new javax.swing.JLabel();
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

        painelConteudo.setLayout(new java.awt.BorderLayout());

        abasClientes.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 16)); // NOI18N

        painelCadastro.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        painelCadastro.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N

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

        cbStatusCadastroCliente.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        cbStatusCadastroCliente.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ativo", "Inativo" }));
        cbStatusCadastroCliente.setPreferredSize(new java.awt.Dimension(83, 40));

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
                                .addComponent(cbStatusCadastroCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addComponent(cbStatusCadastroCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(59, 59, 59)
                .addComponent(grupoGenero)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painelDadosClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbFeminino)
                    .addComponent(rbMasculino)
                    .addComponent(rbOutro))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 77, Short.MAX_VALUE)
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
                .addGap(144, 144, 144)
                .addComponent(painelDadosCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(2124, Short.MAX_VALUE))
        );
        painelCadastroLayout.setVerticalGroup(
            painelCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelCadastroLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(painelDadosCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(1706, Short.MAX_VALUE))
        );

        abasClientes.addTab("Cadastro", new javax.swing.ImageIcon("C:\\Users\\CleicianeGomes\\Documents\\ProjetoPI_Supermercado\\GerenciamentoSupermercado\\src\\main\\resources\\assets\\user.png"), painelCadastro); // NOI18N

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
        btnVisualizarDetalhes.setText("Visualizar detalhes");
        btnVisualizarDetalhes.setBorder(null);
        btnVisualizarDetalhes.setPreferredSize(new java.awt.Dimension(210, 40));

        lblMensagemConsulta.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lblMensagemConsulta.setText("Selecione um cliente na tabela para visualizar seus dados.");

        lblClientesCadastrados.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 20)); // NOI18N
        lblClientesCadastrados.setText("Clientes cadastrados");

        tabelaConsultaClientes.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        tabelaConsultaClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Código", "Nome", "CPF", "Telefone", "E-mail", "Status"
            }
        ));
        jScrollPane1.setViewportView(tabelaConsultaClientes);

        javax.swing.GroupLayout painelConsultaClientes1Layout = new javax.swing.GroupLayout(painelConsultaClientes1);
        painelConsultaClientes1.setLayout(painelConsultaClientes1Layout);
        painelConsultaClientes1Layout.setHorizontalGroup(
            painelConsultaClientes1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelConsultaClientes1Layout.createSequentialGroup()
                .addGap(90, 90, 90)
                .addGroup(painelConsultaClientes1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(painelConsultaClientes1Layout.createSequentialGroup()
                        .addComponent(lblMensagemConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 528, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(160, 160, 160)
                        .addComponent(btnVisualizarDetalhes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 985, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblClientesCadastrados, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addGroup(painelConsultaClientes1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnVisualizarDetalhes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMensagemConsulta))
                .addContainerGap(73, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout painelConsultaLayout = new javax.swing.GroupLayout(painelConsulta);
        painelConsulta.setLayout(painelConsultaLayout);
        painelConsultaLayout.setHorizontalGroup(
            painelConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelConsultaLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(painelConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(painelConsultaClientes1, javax.swing.GroupLayout.PREFERRED_SIZE, 1374, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(painelConsultaClientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(1653, Short.MAX_VALUE))
        );
        painelConsultaLayout.setVerticalGroup(
            painelConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelConsultaLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(painelConsultaClientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(painelConsultaClientes1, javax.swing.GroupLayout.PREFERRED_SIZE, 436, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 1861, Short.MAX_VALUE))
        );

        abasClientes.addTab("Consulta", new javax.swing.ImageIcon("C:\\Users\\CleicianeGomes\\Documents\\ProjetoPI_Supermercado\\GerenciamentoSupermercado\\src\\main\\resources\\assets\\search 24.png"), painelConsulta); // NOI18N

        painelEdicao.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        painelEdicaoClientes.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblTituloEdicao.setFont(new java.awt.Font("Arial Black", 1, 30)); // NOI18N
        lblTituloEdicao.setForeground(new java.awt.Color(10, 4, 138));
        lblTituloEdicao.setText("Edição e Desativação de Clientes");

        tabelaEdicaoClientes.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        tabelaEdicaoClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Código", "Nome", "CPF", "Telefone", "E-mail", "Status"
            }
        ));
        scrollTabelaEdicao.setViewportView(tabelaEdicaoClientes);

        lblPesquisaEdicao.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lblPesquisaEdicao.setText("Pesquisar cliente por nome, CPF ou código");

        txtPesquisaEdicao.setPreferredSize(new java.awt.Dimension(68, 40));

        cbStatusEdicao.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        cbStatusEdicao.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos", "Ativo ", "Inativo" }));
        cbStatusEdicao.setPreferredSize(new java.awt.Dimension(83, 40));

        lblStatusEdicao.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lblStatusEdicao.setText("Status do cliente");

        btnPesquisarEdicao.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18)); // NOI18N
        btnPesquisarEdicao.setText("Pesquisar");
        btnPesquisarEdicao.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(10, 4, 138)));
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
                .addGap(40, 40, 40)
                .addGroup(painelEdicaoClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(painelEdicaoClientesLayout.createSequentialGroup()
                        .addComponent(scrollTabelaEdicao, javax.swing.GroupLayout.PREFERRED_SIZE, 864, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(painelEdicaoClientesLayout.createSequentialGroup()
                        .addGroup(painelEdicaoClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(painelEdicaoClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(cbStatusEdicao, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(painelEdicaoClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lblDescricaoEdicao)
                                    .addGroup(painelEdicaoClientesLayout.createSequentialGroup()
                                        .addComponent(lblPesquisaEdicao)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lblStatusEdicao))
                                    .addComponent(lblTituloEdicao, javax.swing.GroupLayout.PREFERRED_SIZE, 698, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblTituloDadosEdicao, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(txtPesquisaEdicao, javax.swing.GroupLayout.PREFERRED_SIZE, 510, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 401, Short.MAX_VALUE)
                        .addComponent(btnPesquisarEdicao, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(235, 235, 235))))
        );
        painelEdicaoClientesLayout.setVerticalGroup(
            painelEdicaoClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelEdicaoClientesLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(lblTituloEdicao)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDescricaoEdicao)
                .addGap(38, 38, 38)
                .addGroup(painelEdicaoClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPesquisaEdicao)
                    .addComponent(lblStatusEdicao))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painelEdicaoClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(painelEdicaoClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cbStatusEdicao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnPesquisarEdicao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtPesquisaEdicao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(52, 52, 52)
                .addComponent(scrollTabelaEdicao, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTituloDadosEdicao)
                .addContainerGap(68, Short.MAX_VALUE))
        );

        painelEdicaoClientes1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

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
                .addGap(12, 12, 12)
                .addGroup(painelEdicaoClientes1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(painelEdicaoClientes1Layout.createSequentialGroup()
                        .addGroup(painelEdicaoClientes1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(painelEdicaoClientes1Layout.createSequentialGroup()
                                .addGroup(painelEdicaoClientes1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(painelEdicaoClientes1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(lblCodigoEdicao)
                                        .addComponent(txtCPFCdicao, javax.swing.GroupLayout.DEFAULT_SIZE, 294, Short.MAX_VALUE)
                                        .addComponent(txtCodigoEdicao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(lblTelefoneEdicao)
                                    .addComponent(txtTelefoneEdicao, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(199, 199, 199)
                                .addGroup(painelEdicaoClientes1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lblDataNascimentoEdicao)
                                    .addComponent(lblRGEdicao)
                                    .addComponent(lblNomeEdicao)
                                    .addComponent(txtBairroEdicao, javax.swing.GroupLayout.DEFAULT_SIZE, 332, Short.MAX_VALUE)
                                    .addComponent(txtEmailEdicao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtNomeEdicao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(painelEdicaoClientes1Layout.createSequentialGroup()
                                .addGroup(painelEdicaoClientes1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtCidadeEdicao, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblEmailEdicao)
                                    .addComponent(lblCPFCdicao))
                                .addGap(199, 199, 199)
                                .addGroup(painelEdicaoClientes1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lblEnderecoEdicao)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelEdicaoClientes1Layout.createSequentialGroup()
                                        .addComponent(lblCidadeEdicao)
                                        .addGap(275, 275, 275))
                                    .addComponent(txtRGEdicao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtDataNascimentoEdicao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(358, 757, Short.MAX_VALUE))
                    .addGroup(painelEdicaoClientes1Layout.createSequentialGroup()
                        .addGroup(painelEdicaoClientes1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtEnderecoEdicao, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Bairro, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        painelEdicaoClientes1Layout.setVerticalGroup(
            painelEdicaoClientes1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelEdicaoClientes1Layout.createSequentialGroup()
                .addGroup(painelEdicaoClientes1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(painelEdicaoClientes1Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(painelEdicaoClientes1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblCodigoEdicao)
                            .addComponent(lblNomeEdicao))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(painelEdicaoClientes1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtCodigoEdicao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtEmailEdicao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(27, 27, 27)
                        .addGroup(painelEdicaoClientes1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblCPFCdicao)
                            .addComponent(lblRGEdicao))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBairroEdicao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(painelEdicaoClientes1Layout.createSequentialGroup()
                        .addGap(157, 157, 157)
                        .addComponent(txtCPFCdicao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addGroup(painelEdicaoClientes1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblTelefoneEdicao)
                            .addComponent(lblDataNascimentoEdicao))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(painelEdicaoClientes1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCidadeEdicao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNomeEdicao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addGroup(painelEdicaoClientes1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEmailEdicao)
                    .addComponent(lblEnderecoEdicao))
                .addGap(21, 21, 21)
                .addGroup(painelEdicaoClientes1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTelefoneEdicao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtRGEdicao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addGroup(painelEdicaoClientes1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Bairro)
                    .addComponent(lblCidadeEdicao))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painelEdicaoClientes1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtEnderecoEdicao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtDataNascimentoEdicao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(33, 33, 33))
        );

        painelHistoricoClientes.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), javax.swing.BorderFactory.createMatteBorder(3, 1, 1, 1, new java.awt.Color(102, 102, 102))));

        btnLimparEdicao.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18)); // NOI18N
        btnLimparEdicao.setText("Limpar campos");
        btnLimparEdicao.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(10, 4, 138)));
        btnLimparEdicao.setPreferredSize(new java.awt.Dimension(178, 40));

        btnSalvarAlteracoes.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18)); // NOI18N
        btnSalvarAlteracoes.setText("Salvar alterações");
        btnSalvarAlteracoes.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(10, 4, 138)));
        btnSalvarAlteracoes.setPreferredSize(new java.awt.Dimension(200, 40));

        btnDesativarCliente.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18)); // NOI18N
        btnDesativarCliente.setText("Desativar cliente");
        btnDesativarCliente.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(10, 4, 138)));
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                .addContainerGap(52, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout painelEdicaoLayout = new javax.swing.GroupLayout(painelEdicao);
        painelEdicao.setLayout(painelEdicaoLayout);
        painelEdicaoLayout.setHorizontalGroup(
            painelEdicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelEdicaoLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(painelEdicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(painelEdicaoLayout.createSequentialGroup()
                        .addComponent(painelEdicaoClientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(painelEdicaoLayout.createSequentialGroup()
                        .addGroup(painelEdicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(painelHistoricoClientes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(painelEdicaoClientes1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(324, 324, 324))))
        );
        painelEdicaoLayout.setVerticalGroup(
            painelEdicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelEdicaoLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(painelEdicaoClientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(painelEdicaoClientes1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(painelHistoricoClientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(1417, Short.MAX_VALUE))
        );

        abasClientes.addTab("Edição e Desativação", new javax.swing.ImageIcon("C:\\Users\\CleicianeGomes\\Documents\\ProjetoPI_Supermercado\\GerenciamentoSupermercado\\src\\main\\resources\\assets\\pencil (1).png"), painelEdicao); // NOI18N

        painelHistorico.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        btnComprovante.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18)); // NOI18N
        btnComprovante.setText("Comprovante");
        btnComprovante.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        btnExportarHistorico.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18)); // NOI18N
        btnExportarHistorico.setText("Exportar ");
        btnExportarHistorico.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(10, 4, 138)));
        btnExportarHistorico.setPreferredSize(new java.awt.Dimension(171, 40));

        painelhistoricoCompras.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        painelhistoricoCompras.setPreferredSize(new java.awt.Dimension(300, 200));

        tabelaComprasHistorico.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
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
        jScrollPane2.setViewportView(tabelaComprasHistorico);

        lblTituloComprasHistorico.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
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
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        painelhistoricoComprasLayout.setVerticalGroup(
            painelhistoricoComprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelhistoricoComprasLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(lblTituloComprasHistorico)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(33, Short.MAX_VALUE))
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
        jScrollPane3.setViewportView(tabelaPagamentosHistorico);

        javax.swing.GroupLayout painelhistoricoPagamentosLayout = new javax.swing.GroupLayout(painelhistoricoPagamentos);
        painelhistoricoPagamentos.setLayout(painelhistoricoPagamentosLayout);
        painelhistoricoPagamentosLayout.setHorizontalGroup(
            painelhistoricoPagamentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelhistoricoPagamentosLayout.createSequentialGroup()
                .addGroup(painelhistoricoPagamentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(painelhistoricoPagamentosLayout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(lblTituloPagamentosHistorico, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(painelhistoricoPagamentosLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 1210, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        painelhistoricoPagamentosLayout.setVerticalGroup(
            painelhistoricoPagamentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelhistoricoPagamentosLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(lblTituloPagamentosHistorico)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        lbGerarComprovante.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lbGerarComprovante.setText("Gerar comprovante de uma compra ou pagamento selecionado.");

        painelTotalCompras.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lbtotalcompras.setForeground(new java.awt.Color(153, 153, 153));
        lbtotalcompras.setText("TOTAL DE COMPRAS");

        lbValortotalCompras.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        lbValortotalCompras.setText("R$ 500,00");

        lBSubtituloCompras.setText("3 compras  registradas");

        javax.swing.GroupLayout painelTotalComprasLayout = new javax.swing.GroupLayout(painelTotalCompras);
        painelTotalCompras.setLayout(painelTotalComprasLayout);
        painelTotalComprasLayout.setHorizontalGroup(
            painelTotalComprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelTotalComprasLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(painelTotalComprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lBSubtituloCompras, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(painelTotalComprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(lbValortotalCompras, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbtotalcompras, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(242, Short.MAX_VALUE))
        );
        painelTotalComprasLayout.setVerticalGroup(
            painelTotalComprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelTotalComprasLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(lbtotalcompras)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbValortotalCompras)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addComponent(lBSubtituloCompras)
                .addGap(23, 23, 23))
        );

        painelTotalPago.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        painelTotalPago.setPreferredSize(new java.awt.Dimension(146, 147));

        lBtotalPago.setForeground(new java.awt.Color(153, 153, 153));
        lBtotalPago.setText("TOTAL PAGO");

        LbVALORtOTALpAGO.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        LbVALORtOTALpAGO.setText("R$ 350,00");

        lBsubTotalPagp.setText("Pagamentos confirmados");

        javax.swing.GroupLayout painelTotalPagoLayout = new javax.swing.GroupLayout(painelTotalPago);
        painelTotalPago.setLayout(painelTotalPagoLayout);
        painelTotalPagoLayout.setHorizontalGroup(
            painelTotalPagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelTotalPagoLayout.createSequentialGroup()
                .addGap(15, 15, 15)
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
                .addGap(18, 18, 18)
                .addComponent(lBsubTotalPagp)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        lBSaldoPendente.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        lBSaldoPendente.setPreferredSize(new java.awt.Dimension(146, 104));

        lbsaldopendente1.setForeground(new java.awt.Color(153, 153, 153));
        lbsaldopendente1.setText("SALDO PENDENTE");

        lBvalorPendente.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        lBvalorPendente.setText("R$ 150,00");

        lBsubValorPendente.setText("Valor a pagar");

        javax.swing.GroupLayout lBSaldoPendenteLayout = new javax.swing.GroupLayout(lBSaldoPendente);
        lBSaldoPendente.setLayout(lBSaldoPendenteLayout);
        lBSaldoPendenteLayout.setHorizontalGroup(
            lBSaldoPendenteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lBSaldoPendenteLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(lBSaldoPendenteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(lBSaldoPendenteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(lbsaldopendente1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lBvalorPendente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(lBsubValorPendente, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(290, Short.MAX_VALUE))
        );
        lBSaldoPendenteLayout.setVerticalGroup(
            lBSaldoPendenteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lBSaldoPendenteLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(lbsaldopendente1)
                .addGap(18, 18, 18)
                .addComponent(lBvalorPendente)
                .addGap(12, 12, 12)
                .addComponent(lBsubValorPendente)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout painelHistoricoLayout = new javax.swing.GroupLayout(painelHistorico);
        painelHistorico.setLayout(painelHistoricoLayout);
        painelHistoricoLayout.setHorizontalGroup(
            painelHistoricoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelHistoricoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelHistoricoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(painelhistoricoCompras, javax.swing.GroupLayout.DEFAULT_SIZE, 1371, Short.MAX_VALUE)
                    .addComponent(painelhistoricoPagamentos, javax.swing.GroupLayout.DEFAULT_SIZE, 1371, Short.MAX_VALUE)
                    .addComponent(painelhistorico, javax.swing.GroupLayout.PREFERRED_SIZE, 1371, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(painelHistoricoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnComprovante, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbGerarComprovante)
                    .addComponent(btnExportarHistorico, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(painelHistoricoLayout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addGroup(painelHistoricoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lBSaldoPendente, javax.swing.GroupLayout.PREFERRED_SIZE, 407, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(painelTotalPago, javax.swing.GroupLayout.PREFERRED_SIZE, 407, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(painelTotalCompras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(1161, Short.MAX_VALUE))
        );
        painelHistoricoLayout.setVerticalGroup(
            painelHistoricoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelHistoricoLayout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(painelHistoricoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(painelhistoricoCompras, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(painelHistoricoLayout.createSequentialGroup()
                        .addGroup(painelHistoricoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(painelHistoricoLayout.createSequentialGroup()
                                .addComponent(painelhistorico, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(painelHistoricoLayout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addComponent(painelTotalCompras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 74, Short.MAX_VALUE)
                                .addComponent(lBSaldoPendente, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(41, 41, 41)))
                        .addComponent(painelTotalPago, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(painelHistoricoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(painelHistoricoLayout.createSequentialGroup()
                        .addGap(74, 74, 74)
                        .addComponent(lbGerarComprovante)
                        .addGap(32, 32, 32)
                        .addComponent(btnComprovante, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)
                        .addComponent(btnExportarHistorico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(painelHistoricoLayout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(painelhistoricoPagamentos, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(1759, Short.MAX_VALUE))
        );

        abasClientes.addTab("Histórico de Compras e Pagamentos", new javax.swing.ImageIcon("C:\\Users\\CleicianeGomes\\Documents\\ProjetoPI_Supermercado\\GerenciamentoSupermercado\\src\\main\\resources\\assets\\history 24.png"), painelHistorico); // NOI18N

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
                .addGap(17, 17, 17)
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
        tabelaPedidosClientes.setViewportView(jTable1);

        lblTituloTabelaPedidos.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        lblTituloTabelaPedidos.setIcon(new javax.swing.ImageIcon("C:\\Users\\CleicianeGomes\\Documents\\ProjetoPI_Supermercado\\GerenciamentoSupermercado\\src\\main\\resources\\assets\\cart.png")); // NOI18N
        lblTituloTabelaPedidos.setText("Pedidos dos Clientes");

        javax.swing.GroupLayout painelPedidosClientesLayout = new javax.swing.GroupLayout(painelPedidosClientes);
        painelPedidosClientes.setLayout(painelPedidosClientesLayout);
        painelPedidosClientesLayout.setHorizontalGroup(
            painelPedidosClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelPedidosClientesLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(painelPedidosClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tabelaPedidosClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 1230, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTituloTabelaPedidos))
                .addContainerGap(951, Short.MAX_VALUE))
        );
        painelPedidosClientesLayout.setVerticalGroup(
            painelPedidosClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelPedidosClientesLayout.createSequentialGroup()
                .addContainerGap(30, Short.MAX_VALUE)
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelGerarRelatorioLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(painelGerarRelatorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelGerarRelatorioLayout.createSequentialGroup()
                        .addGroup(painelGerarRelatorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(painelGerarRelatorioLayout.createSequentialGroup()
                                .addGroup(painelGerarRelatorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(painelGerarRelatorioLayout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(lbMesRelatorio))
                                    .addComponent(cbMesRelatorio, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(painelGerarRelatorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbAnoRelatorio, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbAnoRelatorio)))
                            .addGroup(painelGerarRelatorioLayout.createSequentialGroup()
                                .addGroup(painelGerarRelatorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(btnGerarRelatorio, javax.swing.GroupLayout.PREFERRED_SIZE, 531, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 517, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(51, 51, 51))
                    .addGroup(painelGerarRelatorioLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
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
                .addGap(40, 40, 40)
                .addComponent(lblTituloGerarRelatorio)
                .addGap(24, 24, 24)
                .addComponent(lblTipoRelatorio)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbTipoRelatorio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addComponent(lbPeriodoRelatorio)
                .addGap(18, 18, 18)
                .addGroup(painelGerarRelatorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbMesRelatorio)
                    .addComponent(lbAnoRelatorio))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painelGerarRelatorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbMesRelatorio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbAnoRelatorio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(btnGerarRelatorio, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
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
                        .addGap(24, 24, 24)
                        .addComponent(painelPedidosClientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(painelPedidosRelatorioLayout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(painelPedidosRelatorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTituloPedidosRelatorio)
                            .addComponent(lblDescricaoPedidosRelatorio)
                            .addComponent(painelpedidosTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(painelPedidosRelatorioLayout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addComponent(painelTotalPedidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(183, 183, 183)
                        .addComponent(painelPedidospendetes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(251, 251, 251)
                        .addComponent(painelvalorTotalPedidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(painelPedidosRelatorioLayout.createSequentialGroup()
                        .addGap(86, 86, 86)
                        .addComponent(painelGerarRelatorio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(820, Short.MAX_VALUE))
        );
        painelPedidosRelatorioLayout.setVerticalGroup(
            painelPedidosRelatorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelPedidosRelatorioLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(painelPedidosRelatorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(painelPedidosRelatorioLayout.createSequentialGroup()
                        .addComponent(lblTituloPedidosRelatorio, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblDescricaoPedidosRelatorio)
                        .addGap(42, 42, 42)
                        .addComponent(painelpedidosTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(67, 67, 67)
                        .addComponent(painelPedidosClientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42)
                        .addGroup(painelPedidosRelatorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(painelvalorTotalPedidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(painelPedidospendetes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(painelTotalPedidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50)
                .addComponent(painelGerarRelatorio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(1290, Short.MAX_VALUE))
        );

        abasClientes.addTab("Pedidos e Relatório", new javax.swing.ImageIcon("C:\\Users\\CleicianeGomes\\Documents\\ProjetoPI_Supermercado\\GerenciamentoSupermercado\\src\\main\\resources\\assets\\document.png"), painelPedidosRelatorio); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(2167, 2167, 2167)
                .addComponent(painelConteudo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addComponent(abasClientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(abasClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 2713, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(118, 118, 118)
                .addComponent(painelConteudo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbStatusConsultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbStatusConsultaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbStatusConsultaActionPerformed

    private void ftfCPFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ftfCPFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ftfCPFActionPerformed

    private void btnLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimparActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLimparActionPerformed

    private void txtRGEdicaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtRGEdicaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRGEdicaoActionPerformed

    private void txtEmailEdicaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmailEdicaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmailEdicaoActionPerformed

    private void cbTipoRelatorioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbTipoRelatorioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbTipoRelatorioActionPerformed

    private void cbMesRelatorioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbMesRelatorioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbMesRelatorioActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        // TODO add your handling code here:
cadastrarCliente();

      
    }//GEN-LAST:event_btnSalvarActionPerformed

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
    private javax.swing.JComboBox<String> cbStatusCadastroCliente;
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