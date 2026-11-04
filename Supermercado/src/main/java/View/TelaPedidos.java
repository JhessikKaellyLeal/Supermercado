/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import View.MenuPadrao;
import Model.Usuario;
import View.TelaHome;
import View.TelaPerfilUsuarios;
import View.TelaProdutos;
import View.Tela_Funcionarios_RH;
import javax.swing.JOptionPane;

/**
 *
 * @author SamaraTavares
 */
public class TelaPedidos extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(TelaPedidos.class.getName());

      private MenuPadrao menu;  
      private Usuario usuario;
    
    
    public TelaPedidos(Usuario usuario) {
        initComponents();
        this.usuario= usuario;
       this.menu= MenuPadrao.instalar(
        this,
        usuario,
        "pedidos",
        this::navegar,
        this::dispose
    );
        this.setExtendedState(TelaPedidos.MAXIMIZED_BOTH);
       
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

 

    
    
       
    
private void cadastrarPedido() {
    try {
        // Capturar os dados da tela
        String numeroPedido = txtNumeroPedidoCadastro.getText().trim();
        String dataPedido = ftfDataPedidoCadastro.getText().trim();
        String statusPedido = cbStatusPedidoCadastro.getSelectedItem().toString();
        String observacao = txtaObservacoesCadastro.getText().trim();

        // Validar os campos obrigatórios
        if (numeroPedido.isEmpty()
                || dataPedido.isEmpty()
                || txtClienteCadastro.getText().trim().isEmpty()) {

            javax.swing.JOptionPane.showMessageDialog(
                    this,
                    "Preencha o número, a data e o ID do cliente.",
                    "Campos obrigatórios",
                    javax.swing.JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        // Converter o ID do cliente
        int idCliente = Integer.parseInt(
                txtClienteCadastro.getText().trim()
        );

        /*
         * Substitua pelos IDs reais do usuário logado
         * e da entrega selecionada.
         */
        int idUsuario = 1;
        int idEntrega = 1;

        // Criar o Controller
        Control.PedidosController controller =
                new Control.PedidosController();

        // Cadastrar no banco
        boolean cadastrou = controller.cadastrar(
                numeroPedido,
                dataPedido,
                statusPedido,
                observacao.isEmpty() ? null : observacao,
                idCliente,
                idUsuario,
                idEntrega
        );

        if (cadastrou) {
            javax.swing.JOptionPane.showMessageDialog(
                    this,
                    "Pedido cadastrado com sucesso!",
                    "Sucesso",
                    javax.swing.JOptionPane.INFORMATION_MESSAGE
            );

            // Limpar os campos após o cadastro
            txtNumeroPedidoCadastro.setText("");
            ftfDataPedidoCadastro.setValue(null);
            txtClienteCadastro.setText("");
            txtaObservacoesCadastro.setText("");
            cbStatusPedidoCadastro.setSelectedIndex(0);

            // Atualizar a tabela, quando o método de listagem existir
            // listarPedidosNaTabela();

        } else {
            javax.swing.JOptionPane.showMessageDialog(
                    this,
                    "Não foi possível cadastrar o pedido.",
                    "Aviso",
                    javax.swing.JOptionPane.WARNING_MESSAGE
            );
        }

    } catch (NumberFormatException e) {
        javax.swing.JOptionPane.showMessageDialog(
                this,
                "O ID do cliente deve ser numérico.",
                "Erro de validação",
                javax.swing.JOptionPane.ERROR_MESSAGE
        );

    } catch (java.sql.SQLException e) {
        javax.swing.JOptionPane.showMessageDialog(
                this,
                "Erro ao cadastrar pedido: " + e.getMessage(),
                "Erro no banco de dados",
                javax.swing.JOptionPane.ERROR_MESSAGE
        );
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
        cadastro = new javax.swing.JPanel();
        lblNumeroPedidoCadastro = new javax.swing.JLabel();
        txtNumeroPedidoCadastro = new javax.swing.JTextField();
        lblDataPedidoCadastro = new javax.swing.JLabel();
        ftfDataPedidoCadastro = new javax.swing.JFormattedTextField();
        lblClienteCadastro = new javax.swing.JLabel();
        lblTipoPagamentoCadastro = new javax.swing.JLabel();
        txtClienteCadastro = new javax.swing.JTextField();
        cbTipoPagamentoCadastro = new javax.swing.JComboBox<>();
        lblStatusPedidoCadastro = new javax.swing.JLabel();
        cbStatusPedidoCadastro = new javax.swing.JComboBox<>();
        lblValorTotalCadastro = new javax.swing.JLabel();
        txtValorTotalCadastro = new javax.swing.JTextField();
        lblObservacoesCadastro = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtaObservacoesCadastro = new javax.swing.JTextArea();
        lblTituloEnderecoCadastro = new javax.swing.JLabel();
        lblRuaCadastro = new javax.swing.JLabel();
        txtRuaCadastro = new javax.swing.JTextField();
        lblCidadeCadastro = new javax.swing.JLabel();
        txtCidadeCadastro = new javax.swing.JTextField();
        lblNumeroCadastro = new javax.swing.JLabel();
        txtNumeroCadastro = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtCep = new javax.swing.JTextField();
        lblBairroCadastro = new javax.swing.JLabel();
        txtBairroCadastro = new javax.swing.JTextField();
        btnSalvarCadastro = new javax.swing.JButton();
        btnNovoCadastro = new javax.swing.JButton();
        btnEditarCadastro = new javax.swing.JButton();
        btnExcluirCadastro = new javax.swing.JButton();
        btnPesquisarCadastro = new javax.swing.JButton();
        btnVoltaCadastro = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblPedidosCadastro = new javax.swing.JTable();
        consultaDetalhes = new javax.swing.JPanel();
        lblNumeroPedidoConsulta = new javax.swing.JLabel();
        txtNumeroPedidoConsulta = new javax.swing.JTextField();
        lblDataInicialConsulta = new javax.swing.JLabel();
        ftfDataInicialConsulta = new javax.swing.JFormattedTextField();
        lblDataFinalConsulta = new javax.swing.JLabel();
        ftfDataFinalConsulta = new javax.swing.JFormattedTextField();
        lblClienteConsulta = new javax.swing.JLabel();
        lblStatusPedidoConsulta = new javax.swing.JLabel();
        txtClienteConsulta = new javax.swing.JTextField();
        cbStatusPedidoConsulta = new javax.swing.JComboBox<>();
        btnPesquisarConsulta = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblPedidosEncontradosConsulta = new javax.swing.JTable();
        lblClienteConsultaDetalhes = new javax.swing.JLabel();
        lblInformacaoClienteConsulta = new javax.swing.JLabel();
        lblDataPedidoConsulta = new javax.swing.JLabel();
        lblInformacaoDataConsulta = new javax.swing.JLabel();
        lblPagamentoConsulta = new javax.swing.JLabel();
        lblInformacaoPagamentoConsulta = new javax.swing.JLabel();
        lblStatusConsulta = new javax.swing.JLabel();
        lblInformacaoStatusConsulta = new javax.swing.JLabel();
        lblEnderecoEntregaConsulta = new javax.swing.JLabel();
        lblInformacaoEnderecoConsulta = new javax.swing.JLabel();
        jScrollPane16 = new javax.swing.JScrollPane();
        tblPedidoConsulta = new javax.swing.JTable();
        btnVoltarConsulta = new javax.swing.JButton();
        btnVisualizarConsulta = new javax.swing.JButton();
        btnImprimirConsulta = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        lblValorTotalConsulta = new javax.swing.JLabel();
        lblInformacaoValorConsulta = new javax.swing.JLabel();
        separacao = new javax.swing.JPanel();
        lblPeriodoSeparacao = new javax.swing.JLabel();
        ftfPeriodoSeparacao = new javax.swing.JFormattedTextField();
        lblStatusSeparacao = new javax.swing.JLabel();
        ftPeriodoSeparacao = new javax.swing.JFormattedTextField();
        cbStatusSeparacao = new javax.swing.JComboBox<>();
        lblClienteSeparacao = new javax.swing.JLabel();
        txtClienteSeparacao = new javax.swing.JTextField();
        lblNumeroPedidoSeparacao = new javax.swing.JLabel();
        txtNumeroPedidoSeparacao = new javax.swing.JTextField();
        btnPesquisarSeparacao = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblPedidoSeparacao = new javax.swing.JTable();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblItensPedidoConsulta = new javax.swing.JTable();
        lblTituloDetalhesSeparacao = new javax.swing.JLabel();
        lblClienteDetalhes = new javax.swing.JLabel();
        lblInformacoesNome = new javax.swing.JLabel();
        lblDataPedidoDetalhes = new javax.swing.JLabel();
        lblInformacoesData = new javax.swing.JLabel();
        lblNumeroPedidoDetalhes = new javax.swing.JLabel();
        lblInformacoesNumero = new javax.swing.JLabel();
        lblStatusSeparacaoDetalhes = new javax.swing.JLabel();
        lblInformacoesStatus = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        txtaObservacoesSeparacao = new javax.swing.JTextArea();
        lblResumoSeparacao = new javax.swing.JLabel();
        barraSeparacao = new javax.swing.JProgressBar();
        lblInformacoesResumoSeparacao = new javax.swing.JLabel();
        lblPorcentagemResumoSeparacao = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        lblNumeroSeparados = new javax.swing.JLabel();
        lblNumeroPendente = new javax.swing.JLabel();
        lblNumeroAguardando = new javax.swing.JLabel();
        btnVisualizarPedido = new javax.swing.JButton();
        btnImprimirListaSeparacao = new javax.swing.JButton();
        btnMarcarSeparado = new javax.swing.JButton();
        btnVoltar = new javax.swing.JButton();
        conferencia = new javax.swing.JPanel();
        lblNumeroPedidoConferencia = new javax.swing.JLabel();
        txtNumeroPedidoConferencia = new javax.swing.JTextField();
        lblDataPedidoConferencia = new javax.swing.JLabel();
        ftfDataPedidoConferencia = new javax.swing.JFormattedTextField();
        lblClienteConferencia = new javax.swing.JLabel();
        txtClienteConferencia = new javax.swing.JTextField();
        lblStatusConferencia = new javax.swing.JLabel();
        btnPesquisarConferencia = new javax.swing.JButton();
        jScrollPane7 = new javax.swing.JScrollPane();
        tblPedidosConferencia = new javax.swing.JTable();
        lblTituloItensConferencia = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        tblItensConferencia = new javax.swing.JTable();
        lblTituloPedidoConferencia = new javax.swing.JLabel();
        lblConferenteConferencia = new javax.swing.JLabel();
        txtConferente = new javax.swing.JTextField();
        lblDataConferencia = new javax.swing.JLabel();
        ftfDataConferencia = new javax.swing.JFormattedTextField();
        lblObservacaoConferencia = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        txtaObservacaoConferencia = new javax.swing.JTextArea();
        btnConfirmarConferencia = new javax.swing.JButton();
        btnImprimirListaConferencia = new javax.swing.JButton();
        btnVerDetalhesConferencia = new javax.swing.JButton();
        cbStatusConferencia = new javax.swing.JComboBox<>();
        alteracaoStatus = new javax.swing.JPanel();
        lblNumeroPedidoAlteracao = new javax.swing.JLabel();
        lbldataPedidoAlteracao = new javax.swing.JLabel();
        txtNumeroPedidoAlteracao = new javax.swing.JTextField();
        ftfDataPedidoAlteracao = new javax.swing.JFormattedTextField();
        lblClienteAlteracao = new javax.swing.JLabel();
        txtClienteAlteracao = new javax.swing.JTextField();
        lblStatusAlteracao = new javax.swing.JLabel();
        btnPesquisarAlteracao = new javax.swing.JButton();
        jScrollPane10 = new javax.swing.JScrollPane();
        tblStatusPedidoAlteracao = new javax.swing.JTable();
        lblTituloAlteracao = new javax.swing.JLabel();
        lblPedidoSelecionadoAlteracao = new javax.swing.JLabel();
        txtPedidoSelecionadoAlteracao = new javax.swing.JTextField();
        lblClienteAlteracaoStatus = new javax.swing.JLabel();
        txtClienteAlteracaoStatus = new javax.swing.JTextField();
        lblValorTotalAlteracao = new javax.swing.JLabel();
        txtValorTotalAlteracao = new javax.swing.JTextField();
        lblStatusAtualAlteracao = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        cbStatusAtualAlteracao = new javax.swing.JComboBox<>();
        lblNovoStatusAlteracao = new javax.swing.JLabel();
        cbNovoStatusAlteracao = new javax.swing.JComboBox<>();
        lblObservacoesAlteracao = new javax.swing.JLabel();
        jScrollPane11 = new javax.swing.JScrollPane();
        txtObservacoesAlteracao = new javax.swing.JTextArea();
        lblTituloHistoricoAlteracao = new javax.swing.JLabel();
        jScrollPane12 = new javax.swing.JScrollPane();
        tblHistoricoAlteracao = new javax.swing.JTable();
        btnSalvarAlteracao = new javax.swing.JButton();
        cbStatusAlteracao = new javax.swing.JComboBox<>();
        cancelamento = new javax.swing.JPanel();
        lblPeriodoCancelamento = new javax.swing.JLabel();
        ftfPeriodoCancelamento = new javax.swing.JFormattedTextField();
        ftfPeriodoCancelaments = new javax.swing.JFormattedTextField();
        lblClienteCancelamento = new javax.swing.JLabel();
        txtClienteCancelamento = new javax.swing.JTextField();
        lblMotivoCancelamento = new javax.swing.JLabel();
        cbMotivoCancelamento = new javax.swing.JComboBox<>();
        btnPesquisarCancelamento = new javax.swing.JButton();
        jScrollPane13 = new javax.swing.JScrollPane();
        tblPedidosCancelados = new javax.swing.JTable();
        lblTituloDetalhesCancelamento = new javax.swing.JLabel();
        lblPedidoDetalhesCancelado = new javax.swing.JLabel();
        txtPedidoDetalhesCancelado = new javax.swing.JTextField();
        lblDataPedidoCancelamento = new javax.swing.JLabel();
        ftfDataPedidoCancelamento = new javax.swing.JFormattedTextField();
        lblClienteDetalhesCancelamento = new javax.swing.JLabel();
        txtClienteDetalhesCancelamento = new javax.swing.JTextField();
        lblValorTotalCancelamento = new javax.swing.JLabel();
        txtValorTotalCancelamento = new javax.swing.JTextField();
        jScrollPane14 = new javax.swing.JScrollPane();
        tblDetalhesCancelamento = new javax.swing.JTable();
        lblTituloInformacoesCancelamento = new javax.swing.JLabel();
        lblInformacoesMotivo = new javax.swing.JLabel();
        cbInformacoesMotivo = new javax.swing.JComboBox<>();
        lblInformacoesDataCancelamento = new javax.swing.JLabel();
        ftfInformacoesDataCancelamento = new javax.swing.JFormattedTextField();
        lblObservacoesCancelamento = new javax.swing.JLabel();
        jScrollPane15 = new javax.swing.JScrollPane();
        txtaObservacoesCancelamento = new javax.swing.JTextArea();
        btnImprimirComprovanteCancelamento = new javax.swing.JButton();
        btnVoltarCancelamento = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTabbedPane1.setForeground(new java.awt.Color(10, 4, 138));
        jTabbedPane1.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N

        cadastro.setBackground(new java.awt.Color(236, 236, 236));

        lblNumeroPedidoCadastro.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblNumeroPedidoCadastro.setText("Nº do Pedido");

        txtNumeroPedidoCadastro.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        txtNumeroPedidoCadastro.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblDataPedidoCadastro.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblDataPedidoCadastro.setText("Data do Pedido");

        ftfDataPedidoCadastro.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        try {
            ftfDataPedidoCadastro.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        ftfDataPedidoCadastro.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N

        lblClienteCadastro.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblClienteCadastro.setText("Cliente");

        lblTipoPagamentoCadastro.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblTipoPagamentoCadastro.setText("Tipo de Pagamento");

        txtClienteCadastro.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        txtClienteCadastro.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        cbTipoPagamentoCadastro.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        cbTipoPagamentoCadastro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione", "Cartão de Crédito", "Cartão de Débito", "Dinheiro", "Pix" }));
        cbTipoPagamentoCadastro.addActionListener(this::cbTipoPagamentoCadastroActionPerformed);

        lblStatusPedidoCadastro.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblStatusPedidoCadastro.setText("Status do Pedido");

        cbStatusPedidoCadastro.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        cbStatusPedidoCadastro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pronto", "Em separação", "Enviado", "Aguardando pagamento", "Concluído", " " }));

        lblValorTotalCadastro.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblValorTotalCadastro.setText("Valor Total");

        txtValorTotalCadastro.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        txtValorTotalCadastro.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblObservacoesCadastro.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblObservacoesCadastro.setText("Observações");

        txtaObservacoesCadastro.setColumns(20);
        txtaObservacoesCadastro.setRows(5);
        txtaObservacoesCadastro.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jScrollPane1.setViewportView(txtaObservacoesCadastro);

        lblTituloEnderecoCadastro.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        lblTituloEnderecoCadastro.setForeground(new java.awt.Color(10, 4, 138));
        lblTituloEnderecoCadastro.setText("Endereço de Entrega");

        lblRuaCadastro.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblRuaCadastro.setText("Rua");

        txtRuaCadastro.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        txtRuaCadastro.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblCidadeCadastro.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblCidadeCadastro.setText("Cidade");

        txtCidadeCadastro.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        txtCidadeCadastro.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblNumeroCadastro.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblNumeroCadastro.setText("Nº");

        txtNumeroCadastro.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        txtNumeroCadastro.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel5.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        jLabel5.setText("CEP");

        txtCep.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        txtCep.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblBairroCadastro.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblBairroCadastro.setText("Bairro");

        txtBairroCadastro.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        txtBairroCadastro.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        btnSalvarCadastro.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        btnSalvarCadastro.setText("Salvar");

        btnNovoCadastro.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        btnNovoCadastro.setText("Novo");
        btnNovoCadastro.addActionListener(this::btnNovoCadastroActionPerformed);

        btnEditarCadastro.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        btnEditarCadastro.setText("Editar");

        btnExcluirCadastro.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        btnExcluirCadastro.setText("Excluir");

        btnPesquisarCadastro.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        btnPesquisarCadastro.setText("Pesquisar");

        btnVoltaCadastro.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        btnVoltaCadastro.setText("Voltar");

        tblPedidosCadastro.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        tblPedidosCadastro.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Nº Pedido", "Data do Pedido", "Cliente", "Valor Total", "Pagamento", "Status"
            }
        ));
        jScrollPane2.setViewportView(tblPedidosCadastro);

        javax.swing.GroupLayout cadastroLayout = new javax.swing.GroupLayout(cadastro);
        cadastro.setLayout(cadastroLayout);
        cadastroLayout.setHorizontalGroup(
            cadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cadastroLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(cadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(cadastroLayout.createSequentialGroup()
                        .addGroup(cadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(cadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txtValorTotalCadastro, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
                                .addComponent(lblNumeroPedidoCadastro, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtNumeroPedidoCadastro, javax.swing.GroupLayout.Alignment.LEADING))
                            .addComponent(lblValorTotalCadastro))
                        .addGap(35, 35, 35)
                        .addGroup(cadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(cadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(cadastroLayout.createSequentialGroup()
                                    .addGroup(cadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lblDataPedidoCadastro)
                                        .addComponent(ftfDataPedidoCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(34, 34, 34)
                                    .addGroup(cadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(cadastroLayout.createSequentialGroup()
                                            .addComponent(lblClienteCadastro)
                                            .addGap(0, 0, Short.MAX_VALUE))
                                        .addComponent(txtClienteCadastro))
                                    .addGap(18, 18, 18)
                                    .addGroup(cadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(cbTipoPagamentoCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblTipoPagamentoCadastro))
                                    .addGap(18, 18, 18)
                                    .addGroup(cadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(cbStatusPedidoCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblStatusPedidoCadastro)))
                                .addComponent(btnVoltaCadastro, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(cadastroLayout.createSequentialGroup()
                                .addGroup(cadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblObservacoesCadastro)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(25, 25, 25)
                                .addGroup(cadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lblRuaCadastro)
                                    .addComponent(lblTituloEnderecoCadastro)
                                    .addComponent(txtRuaCadastro)
                                    .addComponent(lblCidadeCadastro)
                                    .addComponent(txtCidadeCadastro))
                                .addGap(23, 23, 23)
                                .addGroup(cadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(cadastroLayout.createSequentialGroup()
                                        .addGroup(cadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblNumeroCadastro)
                                            .addComponent(jLabel5)
                                            .addComponent(txtNumeroCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(cadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(cadastroLayout.createSequentialGroup()
                                                .addComponent(lblBairroCadastro)
                                                .addGap(136, 136, 136))
                                            .addComponent(txtBairroCadastro)))
                                    .addComponent(txtCep, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(cadastroLayout.createSequentialGroup()
                        .addComponent(btnNovoCadastro)
                        .addGap(18, 18, 18)
                        .addComponent(btnSalvarCadastro)
                        .addGap(18, 18, 18)
                        .addComponent(btnEditarCadastro)
                        .addGap(18, 18, 18)
                        .addComponent(btnExcluirCadastro)
                        .addGap(18, 18, 18)
                        .addComponent(btnPesquisarCadastro))
                    .addComponent(jScrollPane2))
                .addContainerGap(949, Short.MAX_VALUE))
        );
        cadastroLayout.setVerticalGroup(
            cadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cadastroLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(cadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNumeroPedidoCadastro)
                    .addComponent(lblDataPedidoCadastro)
                    .addComponent(lblClienteCadastro)
                    .addComponent(lblTipoPagamentoCadastro)
                    .addComponent(lblStatusPedidoCadastro))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(cadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNumeroPedidoCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ftfDataPedidoCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtClienteCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbTipoPagamentoCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbStatusPedidoCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(cadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblValorTotalCadastro)
                    .addComponent(lblObservacoesCadastro)
                    .addComponent(lblTituloEnderecoCadastro))
                .addGroup(cadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(cadastroLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(cadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtValorTotalCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(cadastroLayout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(cadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblRuaCadastro)
                            .addComponent(lblNumeroCadastro)
                            .addComponent(lblBairroCadastro))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(cadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtRuaCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNumeroCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtBairroCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(cadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblCidadeCadastro)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(cadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCidadeCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(cadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnVoltaCadastro)
                    .addGroup(cadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnSalvarCadastro)
                        .addComponent(btnNovoCadastro)
                        .addComponent(btnEditarCadastro)
                        .addComponent(btnExcluirCadastro)
                        .addComponent(btnPesquisarCadastro)))
                .addGap(27, 27, 27)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(335, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Cadastro", cadastro);

        consultaDetalhes.setBackground(new java.awt.Color(236, 236, 236));

        lblNumeroPedidoConsulta.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblNumeroPedidoConsulta.setText("Nº Pedido");

        txtNumeroPedidoConsulta.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        txtNumeroPedidoConsulta.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblDataInicialConsulta.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblDataInicialConsulta.setText("Data Inicial");

        ftfDataInicialConsulta.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        try {
            ftfDataInicialConsulta.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        ftfDataInicialConsulta.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N

        lblDataFinalConsulta.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblDataFinalConsulta.setText("Data Final");

        ftfDataFinalConsulta.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        try {
            ftfDataFinalConsulta.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        ftfDataFinalConsulta.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N

        lblClienteConsulta.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblClienteConsulta.setText("Cliente");

        lblStatusPedidoConsulta.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblStatusPedidoConsulta.setText("Status do Pedido");

        txtClienteConsulta.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        txtClienteConsulta.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        cbStatusPedidoConsulta.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        cbStatusPedidoConsulta.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos", "Pronto", "Em separação", "Enviado", "Concluído", "Aguardando pagamento" }));

        btnPesquisarConsulta.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        btnPesquisarConsulta.setText("Pesquisar");

        tblPedidosEncontradosConsulta.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        tblPedidosEncontradosConsulta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Nº Pedido", "Data do Pedido", "Cliente", "Valor Total", "Pagamento", "Status"
            }
        ));
        jScrollPane3.setViewportView(tblPedidosEncontradosConsulta);

        lblClienteConsultaDetalhes.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        lblClienteConsultaDetalhes.setForeground(new java.awt.Color(39, 34, 160));
        lblClienteConsultaDetalhes.setText("Cliente");

        lblInformacaoClienteConsulta.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblInformacaoClienteConsulta.setText("Informações");

        lblDataPedidoConsulta.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        lblDataPedidoConsulta.setForeground(new java.awt.Color(39, 34, 160));
        lblDataPedidoConsulta.setText("Data do Pedido");

        lblInformacaoDataConsulta.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblInformacaoDataConsulta.setText("Informações");

        lblPagamentoConsulta.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        lblPagamentoConsulta.setForeground(new java.awt.Color(39, 34, 160));
        lblPagamentoConsulta.setText("Pagamento");

        lblInformacaoPagamentoConsulta.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblInformacaoPagamentoConsulta.setText("Informações");

        lblStatusConsulta.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        lblStatusConsulta.setForeground(new java.awt.Color(39, 34, 160));
        lblStatusConsulta.setText("Status");

        lblInformacaoStatusConsulta.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblInformacaoStatusConsulta.setText("Informações");

        lblEnderecoEntregaConsulta.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        lblEnderecoEntregaConsulta.setForeground(new java.awt.Color(39, 34, 160));
        lblEnderecoEntregaConsulta.setText("Endereço de Entrega");

        lblInformacaoEnderecoConsulta.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblInformacaoEnderecoConsulta.setText("Informações");

        tblPedidoConsulta.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        tblPedidoConsulta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Produto", "Quantidade", "Valor Unitário", "Subtotal"
            }
        ));
        jScrollPane16.setViewportView(tblPedidoConsulta);

        btnVoltarConsulta.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        btnVoltarConsulta.setText("Voltar");

        btnVisualizarConsulta.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        btnVisualizarConsulta.setText("Visualizar");

        btnImprimirConsulta.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        btnImprimirConsulta.setText("Imprimir");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblValorTotalConsulta.setFont(new java.awt.Font("Arial", 1, 28)); // NOI18N
        lblValorTotalConsulta.setForeground(new java.awt.Color(10, 4, 138));
        lblValorTotalConsulta.setText("Valor Total");

        lblInformacaoValorConsulta.setFont(new java.awt.Font("Arial Narrow", 0, 24)); // NOI18N
        lblInformacaoValorConsulta.setText("valor");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(113, 113, 113)
                        .addComponent(lblValorTotalConsulta))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(165, 165, 165)
                        .addComponent(lblInformacaoValorConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(168, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(lblValorTotalConsulta)
                .addGap(34, 34, 34)
                .addComponent(lblInformacaoValorConsulta)
                .addContainerGap(121, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout consultaDetalhesLayout = new javax.swing.GroupLayout(consultaDetalhes);
        consultaDetalhes.setLayout(consultaDetalhesLayout);
        consultaDetalhesLayout.setHorizontalGroup(
            consultaDetalhesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(consultaDetalhesLayout.createSequentialGroup()
                .addGroup(consultaDetalhesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(consultaDetalhesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, consultaDetalhesLayout.createSequentialGroup()
                            .addGap(20, 20, 20)
                            .addGroup(consultaDetalhesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblClienteConsultaDetalhes)
                                .addComponent(lblInformacaoClienteConsulta))
                            .addGap(102, 102, 102)
                            .addGroup(consultaDetalhesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblDataPedidoConsulta)
                                .addComponent(lblInformacaoDataConsulta))
                            .addGap(109, 109, 109)
                            .addGroup(consultaDetalhesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblInformacaoPagamentoConsulta)
                                .addComponent(lblPagamentoConsulta))
                            .addGap(106, 106, 106)
                            .addGroup(consultaDetalhesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblStatusConsulta)
                                .addComponent(lblInformacaoStatusConsulta))
                            .addGap(104, 104, 104)
                            .addGroup(consultaDetalhesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblEnderecoEntregaConsulta)
                                .addComponent(lblInformacaoEnderecoConsulta)))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, consultaDetalhesLayout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(btnVisualizarConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(btnImprimirConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnVoltarConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(consultaDetalhesLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(consultaDetalhesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 756, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(consultaDetalhesLayout.createSequentialGroup()
                                .addGroup(consultaDetalhesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblNumeroPedidoConsulta)
                                    .addComponent(txtNumeroPedidoConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(26, 26, 26)
                                .addGroup(consultaDetalhesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblDataInicialConsulta)
                                    .addComponent(ftfDataInicialConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(27, 27, 27)
                                .addGroup(consultaDetalhesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblDataFinalConsulta)
                                    .addComponent(ftfDataFinalConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(31, 31, 31)
                                .addGroup(consultaDetalhesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblClienteConsulta)
                                    .addComponent(txtClienteConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(33, 33, 33)
                                .addGroup(consultaDetalhesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblStatusPedidoConsulta)
                                    .addGroup(consultaDetalhesLayout.createSequentialGroup()
                                        .addComponent(cbStatusPedidoConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnPesquisarConsulta))))
                            .addGroup(consultaDetalhesLayout.createSequentialGroup()
                                .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, 631, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(43, 43, 43)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(799, Short.MAX_VALUE))
        );
        consultaDetalhesLayout.setVerticalGroup(
            consultaDetalhesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(consultaDetalhesLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(consultaDetalhesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNumeroPedidoConsulta)
                    .addComponent(lblDataInicialConsulta)
                    .addComponent(lblDataFinalConsulta)
                    .addComponent(lblClienteConsulta)
                    .addComponent(lblStatusPedidoConsulta))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(consultaDetalhesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNumeroPedidoConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ftfDataInicialConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ftfDataFinalConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtClienteConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbStatusPedidoConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPesquisarConsulta))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addGroup(consultaDetalhesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblClienteConsultaDetalhes)
                    .addComponent(lblDataPedidoConsulta)
                    .addComponent(lblPagamentoConsulta)
                    .addComponent(lblStatusConsulta)
                    .addComponent(lblEnderecoEntregaConsulta))
                .addGap(18, 18, 18)
                .addGroup(consultaDetalhesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblInformacaoClienteConsulta)
                    .addComponent(lblInformacaoDataConsulta)
                    .addComponent(lblInformacaoPagamentoConsulta)
                    .addComponent(lblInformacaoStatusConsulta)
                    .addComponent(lblInformacaoEnderecoConsulta))
                .addGap(30, 30, 30)
                .addGroup(consultaDetalhesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(consultaDetalhesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(consultaDetalhesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnVisualizarConsulta)
                        .addComponent(btnImprimirConsulta))
                    .addComponent(btnVoltarConsulta))
                .addContainerGap(298, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Consulta e Detalhes", consultaDetalhes);

        separacao.setBackground(new java.awt.Color(236, 236, 236));

        lblPeriodoSeparacao.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblPeriodoSeparacao.setText("Perído de separação");

        ftfPeriodoSeparacao.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        try {
            ftfPeriodoSeparacao.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        ftfPeriodoSeparacao.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N

        lblStatusSeparacao.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblStatusSeparacao.setText("Status da Separação");
        lblStatusSeparacao.setToolTipText("");

        ftPeriodoSeparacao.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        try {
            ftPeriodoSeparacao.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        ftPeriodoSeparacao.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N

        cbStatusSeparacao.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        cbStatusSeparacao.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Em separação", "Pendente", "Aguardando", "Pronto para entrega", " " }));

        lblClienteSeparacao.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblClienteSeparacao.setText("Cliente");

        txtClienteSeparacao.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        txtClienteSeparacao.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblNumeroPedidoSeparacao.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblNumeroPedidoSeparacao.setText("Nº do Pedido");

        txtNumeroPedidoSeparacao.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        txtNumeroPedidoSeparacao.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        btnPesquisarSeparacao.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        btnPesquisarSeparacao.setText("Pesquisar");

        tblPedidoSeparacao.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        tblPedidoSeparacao.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Nº Pedido", "Data do Pedido", "Cliente", "Valor Total", "Status da Separação"
            }
        ));
        jScrollPane4.setViewportView(tblPedidoSeparacao);

        tblItensPedidoConsulta.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        tblItensPedidoConsulta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Produto", "Quantidade", "Separado", "Pedente"
            }
        ));
        jScrollPane5.setViewportView(tblItensPedidoConsulta);

        lblTituloDetalhesSeparacao.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        lblTituloDetalhesSeparacao.setForeground(new java.awt.Color(10, 4, 138));
        lblTituloDetalhesSeparacao.setText("Detalhes para Separação");

        lblClienteDetalhes.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        lblClienteDetalhes.setForeground(new java.awt.Color(39, 34, 160));
        lblClienteDetalhes.setText("Cliente");

        lblInformacoesNome.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblInformacoesNome.setText("Nome");

        lblDataPedidoDetalhes.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        lblDataPedidoDetalhes.setForeground(new java.awt.Color(39, 34, 160));
        lblDataPedidoDetalhes.setText("Data do Pedido");

        lblInformacoesData.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblInformacoesData.setText("data");

        lblNumeroPedidoDetalhes.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        lblNumeroPedidoDetalhes.setForeground(new java.awt.Color(39, 34, 160));
        lblNumeroPedidoDetalhes.setText("Nº do Pedido");

        lblInformacoesNumero.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblInformacoesNumero.setText("numero");

        lblStatusSeparacaoDetalhes.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        lblStatusSeparacaoDetalhes.setForeground(new java.awt.Color(39, 34, 160));
        lblStatusSeparacaoDetalhes.setText("Status da Separação");

        lblInformacoesStatus.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblInformacoesStatus.setText("status");

        jLabel11.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        jLabel11.setText("Observações");

        txtaObservacoesSeparacao.setColumns(20);
        txtaObservacoesSeparacao.setRows(5);
        txtaObservacoesSeparacao.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jScrollPane6.setViewportView(txtaObservacoesSeparacao);

        lblResumoSeparacao.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        lblResumoSeparacao.setForeground(new java.awt.Color(10, 4, 138));
        lblResumoSeparacao.setText("Resumo da Separação");

        barraSeparacao.setBackground(new java.awt.Color(51, 51, 255));

        lblInformacoesResumoSeparacao.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblInformacoesResumoSeparacao.setText("Informações");

        lblPorcentagemResumoSeparacao.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lblPorcentagemResumoSeparacao.setText("porcentagem");

        jLabel48.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        jLabel48.setText("Separados");

        jLabel49.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        jLabel49.setText("Pendente");

        jLabel50.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        jLabel50.setText("Aguardando");

        lblNumeroSeparados.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lblNumeroSeparados.setText("Nº");

        lblNumeroPendente.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lblNumeroPendente.setText("Nº");

        lblNumeroAguardando.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblNumeroAguardando.setText("Nº");

        btnVisualizarPedido.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        btnVisualizarPedido.setText("Visualizar Pedido");

        btnImprimirListaSeparacao.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        btnImprimirListaSeparacao.setText("Imprimir lista");

        btnMarcarSeparado.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        btnMarcarSeparado.setText("Marcar como Separado");

        btnVoltar.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        btnVoltar.setText("Voltar");

        javax.swing.GroupLayout separacaoLayout = new javax.swing.GroupLayout(separacao);
        separacao.setLayout(separacaoLayout);
        separacaoLayout.setHorizontalGroup(
            separacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(separacaoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(separacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(separacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane4)
                        .addGroup(separacaoLayout.createSequentialGroup()
                            .addGroup(separacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblPeriodoSeparacao)
                                .addGroup(separacaoLayout.createSequentialGroup()
                                    .addComponent(ftfPeriodoSeparacao, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(ftPeriodoSeparacao, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGap(27, 27, 27)
                            .addGroup(separacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(cbStatusSeparacao, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblStatusSeparacao))
                            .addGap(18, 18, 18)
                            .addGroup(separacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblClienteSeparacao)
                                .addComponent(txtClienteSeparacao, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(23, 23, 23)
                            .addGroup(separacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblNumeroPedidoSeparacao)
                                .addGroup(separacaoLayout.createSequentialGroup()
                                    .addComponent(txtNumeroPedidoSeparacao, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(btnPesquisarSeparacao)))))
                    .addComponent(lblTituloDetalhesSeparacao)
                    .addGroup(separacaoLayout.createSequentialGroup()
                        .addGroup(separacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblClienteDetalhes)
                            .addGroup(separacaoLayout.createSequentialGroup()
                                .addComponent(lblInformacoesNome)
                                .addGap(131, 131, 131)
                                .addGroup(separacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblDataPedidoDetalhes)
                                    .addComponent(lblInformacoesData))))
                        .addGap(106, 106, 106)
                        .addGroup(separacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(separacaoLayout.createSequentialGroup()
                                .addGap(222, 222, 222)
                                .addGroup(separacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblInformacoesStatus)
                                    .addComponent(lblStatusSeparacaoDetalhes)))
                            .addComponent(lblNumeroPedidoDetalhes)
                            .addComponent(lblInformacoesNumero)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, separacaoLayout.createSequentialGroup()
                                .addGroup(separacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel11))
                                .addGap(18, 18, 18)))
                        .addGap(119, 119, 119)
                        .addGroup(separacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel50)
                            .addGroup(separacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(btnVoltar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(separacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lblResumoSeparacao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(separacaoLayout.createSequentialGroup()
                                        .addComponent(lblInformacoesResumoSeparacao)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lblPorcentagemResumoSeparacao))
                                    .addComponent(barraSeparacao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel48)
                                    .addComponent(jLabel49)))))
                    .addGroup(separacaoLayout.createSequentialGroup()
                        .addComponent(btnVisualizarPedido)
                        .addGap(18, 18, 18)
                        .addComponent(btnImprimirListaSeparacao)
                        .addGap(18, 18, 18)
                        .addComponent(btnMarcarSeparado))
                    .addGroup(separacaoLayout.createSequentialGroup()
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 553, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(627, 627, 627)
                        .addGroup(separacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(separacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(lblNumeroPendente)
                                .addComponent(lblNumeroSeparados))
                            .addComponent(lblNumeroAguardando))))
                .addContainerGap(700, Short.MAX_VALUE))
        );
        separacaoLayout.setVerticalGroup(
            separacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(separacaoLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(separacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPeriodoSeparacao)
                    .addComponent(lblStatusSeparacao)
                    .addComponent(lblClienteSeparacao)
                    .addComponent(lblNumeroPedidoSeparacao))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(separacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ftfPeriodoSeparacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ftPeriodoSeparacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbStatusSeparacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtClienteSeparacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNumeroPedidoSeparacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPesquisarSeparacao))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblTituloDetalhesSeparacao)
                .addGap(26, 26, 26)
                .addGroup(separacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblClienteDetalhes)
                    .addComponent(lblDataPedidoDetalhes)
                    .addComponent(lblNumeroPedidoDetalhes)
                    .addComponent(lblStatusSeparacaoDetalhes))
                .addGap(24, 24, 24)
                .addGroup(separacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblInformacoesData)
                    .addComponent(lblInformacoesNome)
                    .addComponent(lblInformacoesNumero)
                    .addComponent(lblInformacoesStatus)
                    .addComponent(lblResumoSeparacao))
                .addGap(26, 26, 26)
                .addGroup(separacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(separacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(separacaoLayout.createSequentialGroup()
                            .addGroup(separacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel11)
                                .addComponent(lblInformacoesResumoSeparacao)
                                .addComponent(lblPorcentagemResumoSeparacao))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jScrollPane6))
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(separacaoLayout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(barraSeparacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(separacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel48)
                            .addComponent(lblNumeroSeparados))
                        .addGap(18, 18, 18)
                        .addGroup(separacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel49)
                            .addComponent(lblNumeroPendente))
                        .addGap(18, 18, 18)
                        .addGroup(separacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblNumeroAguardando)
                            .addComponent(jLabel50))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(separacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnVisualizarPedido)
                    .addComponent(btnImprimirListaSeparacao)
                    .addComponent(btnMarcarSeparado)
                    .addComponent(btnVoltar))
                .addGap(177, 243, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Separação", separacao);

        conferencia.setBackground(new java.awt.Color(236, 236, 236));

        lblNumeroPedidoConferencia.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblNumeroPedidoConferencia.setText("Nº do Pedido");

        txtNumeroPedidoConferencia.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        txtNumeroPedidoConferencia.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblDataPedidoConferencia.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblDataPedidoConferencia.setText("Data do Pedido");

        ftfDataPedidoConferencia.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        try {
            ftfDataPedidoConferencia.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        ftfDataPedidoConferencia.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N

        lblClienteConferencia.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblClienteConferencia.setText("Cliente");

        txtClienteConferencia.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        txtClienteConferencia.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblStatusConferencia.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblStatusConferencia.setText("Status da Conferência");
        lblStatusConferencia.setToolTipText("");

        btnPesquisarConferencia.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        btnPesquisarConferencia.setText("Pesquisar");

        tblPedidosConferencia.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        tblPedidosConferencia.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Nº Pedido", "Cliente", "Valor Total", "Status da Conferência"
            }
        ));
        jScrollPane7.setViewportView(tblPedidosConferencia);

        lblTituloItensConferencia.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        lblTituloItensConferencia.setForeground(new java.awt.Color(10, 4, 138));
        lblTituloItensConferencia.setText("Itens do Pedido");

        tblItensConferencia.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        tblItensConferencia.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Produto", "Quantidade", "Valor Unitário", "Valor Total"
            }
        ));
        jScrollPane8.setViewportView(tblItensConferencia);

        lblTituloPedidoConferencia.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        lblTituloPedidoConferencia.setForeground(new java.awt.Color(10, 4, 138));
        lblTituloPedidoConferencia.setText("Conferência do Pedido");

        lblConferenteConferencia.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblConferenteConferencia.setText("Conferente");

        txtConferente.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        txtConferente.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblDataConferencia.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblDataConferencia.setText("Data da Conferência");

        ftfDataConferencia.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        try {
            ftfDataConferencia.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        ftfDataConferencia.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N

        lblObservacaoConferencia.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblObservacaoConferencia.setText("Observação");

        txtaObservacaoConferencia.setColumns(20);
        txtaObservacaoConferencia.setRows(5);
        txtaObservacaoConferencia.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jScrollPane9.setViewportView(txtaObservacaoConferencia);

        btnConfirmarConferencia.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        btnConfirmarConferencia.setText("Confirmar Conferência");

        btnImprimirListaConferencia.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        btnImprimirListaConferencia.setText("Imprimir Lista");

        btnVerDetalhesConferencia.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        btnVerDetalhesConferencia.setText("Ver Detalhes");

        cbStatusConferencia.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        cbStatusConferencia.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos", "Pendente", "Conferido" }));

        javax.swing.GroupLayout conferenciaLayout = new javax.swing.GroupLayout(conferencia);
        conferencia.setLayout(conferenciaLayout);
        conferenciaLayout.setHorizontalGroup(
            conferenciaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(conferenciaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(conferenciaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(conferenciaLayout.createSequentialGroup()
                        .addGroup(conferenciaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNumeroPedidoConferencia, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblNumeroPedidoConferencia))
                        .addGap(18, 18, 18)
                        .addGroup(conferenciaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ftfDataPedidoConferencia, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblDataPedidoConferencia))
                        .addGap(18, 18, 18)
                        .addGroup(conferenciaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtClienteConferencia, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblClienteConferencia))
                        .addGap(18, 18, 18)
                        .addGroup(conferenciaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(conferenciaLayout.createSequentialGroup()
                                .addComponent(cbStatusConferencia, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnPesquisarConferencia, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblStatusConferencia)))
                    .addGroup(conferenciaLayout.createSequentialGroup()
                        .addGroup(conferenciaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTituloItensConferencia)
                            .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 520, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(41, 41, 41)
                        .addGroup(conferenciaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(conferenciaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(lblTituloPedidoConferencia)
                                .addGroup(conferenciaLayout.createSequentialGroup()
                                    .addGroup(conferenciaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lblConferenteConferencia)
                                        .addComponent(txtConferente, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(23, 23, 23)
                                    .addGroup(conferenciaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lblDataConferencia)
                                        .addComponent(ftfDataConferencia, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addComponent(lblObservacaoConferencia)
                                .addComponent(jScrollPane9))
                            .addGroup(conferenciaLayout.createSequentialGroup()
                                .addComponent(btnConfirmarConferencia)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnImprimirListaConferencia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnVerDetalhesConferencia))))
                    .addComponent(jScrollPane7))
                .addContainerGap(811, Short.MAX_VALUE))
        );
        conferenciaLayout.setVerticalGroup(
            conferenciaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(conferenciaLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(conferenciaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNumeroPedidoConferencia)
                    .addComponent(lblDataPedidoConferencia)
                    .addComponent(lblClienteConferencia)
                    .addComponent(lblStatusConferencia))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(conferenciaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNumeroPedidoConferencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ftfDataPedidoConferencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtClienteConferencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPesquisarConferencia)
                    .addComponent(cbStatusConferencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(conferenciaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTituloItensConferencia)
                    .addComponent(lblTituloPedidoConferencia))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(conferenciaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(conferenciaLayout.createSequentialGroup()
                        .addGroup(conferenciaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblConferenteConferencia)
                            .addComponent(lblDataConferencia))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(conferenciaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtConferente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ftfDataConferencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(lblObservacaoConferencia)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(conferenciaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnConfirmarConferencia)
                            .addComponent(btnImprimirListaConferencia)
                            .addComponent(btnVerDetalhesConferencia))))
                .addGap(294, 294, 294))
        );

        jTabbedPane1.addTab("Conferência", conferencia);

        alteracaoStatus.setBackground(new java.awt.Color(236, 236, 236));

        lblNumeroPedidoAlteracao.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblNumeroPedidoAlteracao.setText("Nº do Pedido");

        lbldataPedidoAlteracao.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lbldataPedidoAlteracao.setText("Data do Pedido");

        txtNumeroPedidoAlteracao.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        txtNumeroPedidoAlteracao.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        ftfDataPedidoAlteracao.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        try {
            ftfDataPedidoAlteracao.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        ftfDataPedidoAlteracao.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N

        lblClienteAlteracao.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblClienteAlteracao.setText("Cliente ");

        txtClienteAlteracao.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        txtClienteAlteracao.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblStatusAlteracao.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblStatusAlteracao.setText("Status Atual");

        btnPesquisarAlteracao.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        btnPesquisarAlteracao.setText("Pesquisar");
        btnPesquisarAlteracao.setBorder(null);

        tblStatusPedidoAlteracao.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        tblStatusPedidoAlteracao.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Nº Pedido", "Data do Pedido", "Cliente", "Status Atual"
            }
        ));
        jScrollPane10.setViewportView(tblStatusPedidoAlteracao);

        lblTituloAlteracao.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        lblTituloAlteracao.setForeground(new java.awt.Color(10, 4, 138));
        lblTituloAlteracao.setText("Alteração de Status");

        lblPedidoSelecionadoAlteracao.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblPedidoSelecionadoAlteracao.setText("Pedido Selecionado");

        txtPedidoSelecionadoAlteracao.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        txtPedidoSelecionadoAlteracao.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblClienteAlteracaoStatus.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblClienteAlteracaoStatus.setText("Cliente");

        txtClienteAlteracaoStatus.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        txtClienteAlteracaoStatus.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblValorTotalAlteracao.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblValorTotalAlteracao.setText("Valor Total");

        txtValorTotalAlteracao.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        txtValorTotalAlteracao.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblStatusAtualAlteracao.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblStatusAtualAlteracao.setText("Status Atual");

        cbStatusAtualAlteracao.setFont(new java.awt.Font("Arial Narrow", 0, 16)); // NOI18N
        cbStatusAtualAlteracao.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Aguardando pagamento", "Em separação", " " }));

        lblNovoStatusAlteracao.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblNovoStatusAlteracao.setText("Novo Status");

        cbNovoStatusAlteracao.setFont(new java.awt.Font("Arial Narrow", 0, 16)); // NOI18N
        cbNovoStatusAlteracao.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Em Separação", "Concluído" }));

        lblObservacoesAlteracao.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblObservacoesAlteracao.setText("Observações(opcional)");

        txtObservacoesAlteracao.setColumns(20);
        txtObservacoesAlteracao.setRows(5);
        txtObservacoesAlteracao.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jScrollPane11.setViewportView(txtObservacoesAlteracao);

        lblTituloHistoricoAlteracao.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        lblTituloHistoricoAlteracao.setForeground(new java.awt.Color(10, 4, 138));
        lblTituloHistoricoAlteracao.setText("Histórico de Alterações");

        tblHistoricoAlteracao.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        tblHistoricoAlteracao.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Data e Hora", "Pedido", "Status Anterior", "Novo Status", "Usuário"
            }
        ));
        jScrollPane12.setViewportView(tblHistoricoAlteracao);

        btnSalvarAlteracao.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        btnSalvarAlteracao.setText("Salvar Alteração");

        cbStatusAlteracao.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        cbStatusAlteracao.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos", "Aguardando pagamento", "Em separação", "Concluído", " " }));

        javax.swing.GroupLayout alteracaoStatusLayout = new javax.swing.GroupLayout(alteracaoStatus);
        alteracaoStatus.setLayout(alteracaoStatusLayout);
        alteracaoStatusLayout.setHorizontalGroup(
            alteracaoStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(alteracaoStatusLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(alteracaoStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(alteracaoStatusLayout.createSequentialGroup()
                        .addGroup(alteracaoStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblNumeroPedidoAlteracao)
                            .addComponent(txtNumeroPedidoAlteracao, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addGroup(alteracaoStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbldataPedidoAlteracao)
                            .addComponent(ftfDataPedidoAlteracao, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31)
                        .addGroup(alteracaoStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtClienteAlteracao, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblClienteAlteracao))
                        .addGap(18, 18, 18)
                        .addGroup(alteracaoStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(alteracaoStatusLayout.createSequentialGroup()
                                .addComponent(cbStatusAlteracao, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnPesquisarAlteracao, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblStatusAlteracao))
                        .addContainerGap(994, Short.MAX_VALUE))
                    .addGroup(alteracaoStatusLayout.createSequentialGroup()
                        .addGroup(alteracaoStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane10, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(alteracaoStatusLayout.createSequentialGroup()
                                .addGroup(alteracaoStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblTituloAlteracao)
                                    .addGroup(alteracaoStatusLayout.createSequentialGroup()
                                        .addGroup(alteracaoStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblPedidoSelecionadoAlteracao)
                                            .addGroup(alteracaoStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(txtValorTotalAlteracao, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
                                                .addComponent(txtPedidoSelecionadoAlteracao, javax.swing.GroupLayout.Alignment.LEADING))
                                            .addComponent(lblClienteAlteracaoStatus)
                                            .addComponent(lblValorTotalAlteracao)
                                            .addComponent(txtClienteAlteracaoStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(alteracaoStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(alteracaoStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(lblObservacoesAlteracao)
                                                .addComponent(lblNovoStatusAlteracao)
                                                .addComponent(lblStatusAtualAlteracao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(alteracaoStatusLayout.createSequentialGroup()
                                                    .addComponent(cbStatusAtualAlteracao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(jLabel30, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE))
                                                .addComponent(cbNovoStatusAlteracao, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(btnSalvarAlteracao))
                                .addGap(48, 48, 48)
                                .addGroup(alteracaoStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblTituloHistoricoAlteracao)
                                    .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 626, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 817, Short.MAX_VALUE))))
        );
        alteracaoStatusLayout.setVerticalGroup(
            alteracaoStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(alteracaoStatusLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(alteracaoStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNumeroPedidoAlteracao)
                    .addComponent(lbldataPedidoAlteracao)
                    .addComponent(lblClienteAlteracao)
                    .addComponent(lblStatusAlteracao))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(alteracaoStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ftfDataPedidoAlteracao, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(alteracaoStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtNumeroPedidoAlteracao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtClienteAlteracao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnPesquisarAlteracao, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbStatusAlteracao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(alteracaoStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTituloAlteracao)
                    .addComponent(lblTituloHistoricoAlteracao))
                .addGroup(alteracaoStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(alteracaoStatusLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(alteracaoStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblPedidoSelecionadoAlteracao)
                            .addComponent(lblStatusAtualAlteracao))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(alteracaoStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtPedidoSelecionadoAlteracao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel30)
                            .addComponent(cbStatusAtualAlteracao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(alteracaoStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblClienteAlteracaoStatus)
                            .addComponent(lblNovoStatusAlteracao))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(alteracaoStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtClienteAlteracaoStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbNovoStatusAlteracao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(alteracaoStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblObservacoesAlteracao)
                            .addComponent(lblValorTotalAlteracao))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(alteracaoStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtValorTotalAlteracao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(alteracaoStatusLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(alteracaoStatusLayout.createSequentialGroup()
                        .addGap(312, 312, 312)
                        .addComponent(btnSalvarAlteracao)))
                .addGap(251, 251, 251))
        );

        jTabbedPane1.addTab("Alteração de Status", alteracaoStatus);

        cancelamento.setBackground(new java.awt.Color(236, 236, 236));

        lblPeriodoCancelamento.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblPeriodoCancelamento.setText("Período de Cancelamento");

        ftfPeriodoCancelamento.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        try {
            ftfPeriodoCancelamento.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        ftfPeriodoCancelamento.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N

        ftfPeriodoCancelaments.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        try {
            ftfPeriodoCancelaments.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        ftfPeriodoCancelaments.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N

        lblClienteCancelamento.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblClienteCancelamento.setText("Cliente");

        txtClienteCancelamento.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        txtClienteCancelamento.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblMotivoCancelamento.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblMotivoCancelamento.setText("Motivo do Cancelamento");

        cbMotivoCancelamento.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        cbMotivoCancelamento.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos", "Cliente desistiu da compra", "Produto Indisponível", "Erro no pagamento", "Cliente não autorizou a compra", "Produto com defeito" }));

        btnPesquisarCancelamento.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        btnPesquisarCancelamento.setText("Pesquisar");

        tblPedidosCancelados.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        tblPedidosCancelados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Nº Pedido", "Data do Pedido", "Cliente", "Valor Total", "Motivo do Cancelamento", "Cancelado em"
            }
        ));
        jScrollPane13.setViewportView(tblPedidosCancelados);

        lblTituloDetalhesCancelamento.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        lblTituloDetalhesCancelamento.setForeground(new java.awt.Color(10, 4, 138));
        lblTituloDetalhesCancelamento.setText("Detalhes do Pedido Cancelado");

        lblPedidoDetalhesCancelado.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblPedidoDetalhesCancelado.setText("Nº Pedido");

        txtPedidoDetalhesCancelado.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        txtPedidoDetalhesCancelado.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblDataPedidoCancelamento.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblDataPedidoCancelamento.setText("Data do Pedido");

        ftfDataPedidoCancelamento.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        try {
            ftfDataPedidoCancelamento.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        ftfDataPedidoCancelamento.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N

        lblClienteDetalhesCancelamento.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblClienteDetalhesCancelamento.setText("Cliente");

        txtClienteDetalhesCancelamento.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        txtClienteDetalhesCancelamento.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblValorTotalCancelamento.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblValorTotalCancelamento.setText("Valor Total");

        txtValorTotalCancelamento.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        txtValorTotalCancelamento.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        tblDetalhesCancelamento.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        tblDetalhesCancelamento.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Produto", "Quantidade", "Valor Unitário", "Valor Total"
            }
        ));
        jScrollPane14.setViewportView(tblDetalhesCancelamento);

        lblTituloInformacoesCancelamento.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        lblTituloInformacoesCancelamento.setForeground(new java.awt.Color(10, 4, 138));
        lblTituloInformacoesCancelamento.setText("Informações do Cancelamento");

        lblInformacoesMotivo.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblInformacoesMotivo.setText("Motivo");

        cbInformacoesMotivo.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        cbInformacoesMotivo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cliente desistiu da compra", "Produto indisponível", "Erro no pagamento", "Clinte não autorizou a compra", "Produto com defeito" }));

        lblInformacoesDataCancelamento.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblInformacoesDataCancelamento.setText("Data do Cancelamento");

        ftfInformacoesDataCancelamento.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        try {
            ftfInformacoesDataCancelamento.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        ftfInformacoesDataCancelamento.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N

        lblObservacoesCancelamento.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        lblObservacoesCancelamento.setText("Observações");

        txtaObservacoesCancelamento.setColumns(20);
        txtaObservacoesCancelamento.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        txtaObservacoesCancelamento.setRows(5);
        txtaObservacoesCancelamento.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jScrollPane15.setViewportView(txtaObservacoesCancelamento);

        btnImprimirComprovanteCancelamento.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        btnImprimirComprovanteCancelamento.setText("Imprimir Comprovante");

        btnVoltarCancelamento.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        btnVoltarCancelamento.setText("Voltar");

        javax.swing.GroupLayout cancelamentoLayout = new javax.swing.GroupLayout(cancelamento);
        cancelamento.setLayout(cancelamentoLayout);
        cancelamentoLayout.setHorizontalGroup(
            cancelamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cancelamentoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(cancelamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(cancelamentoLayout.createSequentialGroup()
                        .addGroup(cancelamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblPeriodoCancelamento)
                            .addGroup(cancelamentoLayout.createSequentialGroup()
                                .addComponent(ftfPeriodoCancelamento, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(ftfPeriodoCancelaments, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(24, 24, 24)
                        .addGroup(cancelamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblClienteCancelamento)
                            .addComponent(txtClienteCancelamento, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(23, 23, 23)
                        .addGroup(cancelamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblMotivoCancelamento)
                            .addGroup(cancelamentoLayout.createSequentialGroup()
                                .addComponent(cbMotivoCancelamento, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)
                                .addComponent(btnPesquisarCancelamento, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(cancelamentoLayout.createSequentialGroup()
                        .addComponent(btnImprimirComprovanteCancelamento)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnVoltarCancelamento, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(cancelamentoLayout.createSequentialGroup()
                        .addGroup(cancelamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(cancelamentoLayout.createSequentialGroup()
                                .addGroup(cancelamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblDataPedidoCancelamento)
                                    .addComponent(lblClienteDetalhesCancelamento)
                                    .addComponent(lblValorTotalCancelamento)
                                    .addGroup(cancelamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(txtValorTotalCancelamento, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
                                        .addComponent(txtClienteDetalhesCancelamento, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtPedidoDetalhesCancelado, javax.swing.GroupLayout.Alignment.LEADING))
                                    .addComponent(ftfDataPedidoCancelamento, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblPedidoDetalhesCancelado)
                            .addComponent(lblTituloDetalhesCancelamento))
                        .addGap(32, 32, 32)
                        .addGroup(cancelamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTituloInformacoesCancelamento)
                            .addGroup(cancelamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(cbInformacoesMotivo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(ftfInformacoesDataCancelamento)
                                .addGroup(cancelamentoLayout.createSequentialGroup()
                                    .addGroup(cancelamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lblInformacoesMotivo)
                                        .addComponent(lblInformacoesDataCancelamento)
                                        .addComponent(lblObservacoesCancelamento))
                                    .addGap(0, 0, Short.MAX_VALUE))
                                .addComponent(jScrollPane15, javax.swing.GroupLayout.DEFAULT_SIZE, 458, Short.MAX_VALUE)))))
                .addContainerGap(773, Short.MAX_VALUE))
            .addGroup(cancelamentoLayout.createSequentialGroup()
                .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 938, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        cancelamentoLayout.setVerticalGroup(
            cancelamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cancelamentoLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(cancelamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPeriodoCancelamento)
                    .addComponent(lblClienteCancelamento)
                    .addComponent(lblMotivoCancelamento))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(cancelamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ftfPeriodoCancelamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ftfPeriodoCancelaments, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtClienteCancelamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbMotivoCancelamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPesquisarCancelamento, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(cancelamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTituloDetalhesCancelamento)
                    .addComponent(lblTituloInformacoesCancelamento))
                .addGap(27, 27, 27)
                .addGroup(cancelamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPedidoDetalhesCancelado)
                    .addComponent(lblInformacoesMotivo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(cancelamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(cancelamentoLayout.createSequentialGroup()
                        .addComponent(txtPedidoDetalhesCancelado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblDataPedidoCancelamento)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ftfDataPedidoCancelamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblClienteDetalhesCancelamento)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtClienteDetalhesCancelamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblValorTotalCancelamento)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtValorTotalCancelamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(cancelamentoLayout.createSequentialGroup()
                        .addComponent(cbInformacoesMotivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblInformacoesDataCancelamento)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ftfInformacoesDataCancelamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblObservacoesCancelamento)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane15)))
                .addGap(30, 30, 30)
                .addGroup(cancelamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnImprimirComprovanteCancelamento)
                    .addComponent(btnVoltarCancelamento))
                .addContainerGap(272, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Cancelamento", cancelamento);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNovoCadastroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoCadastroActionPerformed
        // TODO add your handling code here:
       cadastrarPedido();
    }//GEN-LAST:event_btnNovoCadastroActionPerformed

    private void cbTipoPagamentoCadastroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbTipoPagamentoCadastroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbTipoPagamentoCadastroActionPerformed

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
        java.awt.EventQueue.invokeLater(() -> new TelaPedidos(usuario).setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel alteracaoStatus;
    private javax.swing.JProgressBar barraSeparacao;
    private javax.swing.JButton btnConfirmarConferencia;
    private javax.swing.JButton btnEditarCadastro;
    private javax.swing.JButton btnExcluirCadastro;
    private javax.swing.JButton btnImprimirComprovanteCancelamento;
    private javax.swing.JButton btnImprimirConsulta;
    private javax.swing.JButton btnImprimirListaConferencia;
    private javax.swing.JButton btnImprimirListaSeparacao;
    private javax.swing.JButton btnMarcarSeparado;
    private javax.swing.JButton btnNovoCadastro;
    private javax.swing.JButton btnPesquisarAlteracao;
    private javax.swing.JButton btnPesquisarCadastro;
    private javax.swing.JButton btnPesquisarCancelamento;
    private javax.swing.JButton btnPesquisarConferencia;
    private javax.swing.JButton btnPesquisarConsulta;
    private javax.swing.JButton btnPesquisarSeparacao;
    private javax.swing.JButton btnSalvarAlteracao;
    private javax.swing.JButton btnSalvarCadastro;
    private javax.swing.JButton btnVerDetalhesConferencia;
    private javax.swing.JButton btnVisualizarConsulta;
    private javax.swing.JButton btnVisualizarPedido;
    private javax.swing.JButton btnVoltaCadastro;
    private javax.swing.JButton btnVoltar;
    private javax.swing.JButton btnVoltarCancelamento;
    private javax.swing.JButton btnVoltarConsulta;
    private javax.swing.JPanel cadastro;
    private javax.swing.JPanel cancelamento;
    private javax.swing.JComboBox<String> cbInformacoesMotivo;
    private javax.swing.JComboBox<String> cbMotivoCancelamento;
    private javax.swing.JComboBox<String> cbNovoStatusAlteracao;
    private javax.swing.JComboBox<String> cbStatusAlteracao;
    private javax.swing.JComboBox<String> cbStatusAtualAlteracao;
    private javax.swing.JComboBox<String> cbStatusConferencia;
    private javax.swing.JComboBox<String> cbStatusPedidoCadastro;
    private javax.swing.JComboBox<String> cbStatusPedidoConsulta;
    private javax.swing.JComboBox<String> cbStatusSeparacao;
    private javax.swing.JComboBox<String> cbTipoPagamentoCadastro;
    private javax.swing.JPanel conferencia;
    private javax.swing.JPanel consultaDetalhes;
    private javax.swing.JFormattedTextField ftPeriodoSeparacao;
    private javax.swing.JFormattedTextField ftfDataConferencia;
    private javax.swing.JFormattedTextField ftfDataFinalConsulta;
    private javax.swing.JFormattedTextField ftfDataInicialConsulta;
    private javax.swing.JFormattedTextField ftfDataPedidoAlteracao;
    private javax.swing.JFormattedTextField ftfDataPedidoCadastro;
    private javax.swing.JFormattedTextField ftfDataPedidoCancelamento;
    private javax.swing.JFormattedTextField ftfDataPedidoConferencia;
    private javax.swing.JFormattedTextField ftfInformacoesDataCancelamento;
    private javax.swing.JFormattedTextField ftfPeriodoCancelamento;
    private javax.swing.JFormattedTextField ftfPeriodoCancelaments;
    private javax.swing.JFormattedTextField ftfPeriodoSeparacao;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblBairroCadastro;
    private javax.swing.JLabel lblCidadeCadastro;
    private javax.swing.JLabel lblClienteAlteracao;
    private javax.swing.JLabel lblClienteAlteracaoStatus;
    private javax.swing.JLabel lblClienteCadastro;
    private javax.swing.JLabel lblClienteCancelamento;
    private javax.swing.JLabel lblClienteConferencia;
    private javax.swing.JLabel lblClienteConsulta;
    private javax.swing.JLabel lblClienteConsultaDetalhes;
    private javax.swing.JLabel lblClienteDetalhes;
    private javax.swing.JLabel lblClienteDetalhesCancelamento;
    private javax.swing.JLabel lblClienteSeparacao;
    private javax.swing.JLabel lblConferenteConferencia;
    private javax.swing.JLabel lblDataConferencia;
    private javax.swing.JLabel lblDataFinalConsulta;
    private javax.swing.JLabel lblDataInicialConsulta;
    private javax.swing.JLabel lblDataPedidoCadastro;
    private javax.swing.JLabel lblDataPedidoCancelamento;
    private javax.swing.JLabel lblDataPedidoConferencia;
    private javax.swing.JLabel lblDataPedidoConsulta;
    private javax.swing.JLabel lblDataPedidoDetalhes;
    private javax.swing.JLabel lblEnderecoEntregaConsulta;
    private javax.swing.JLabel lblInformacaoClienteConsulta;
    private javax.swing.JLabel lblInformacaoDataConsulta;
    private javax.swing.JLabel lblInformacaoEnderecoConsulta;
    private javax.swing.JLabel lblInformacaoPagamentoConsulta;
    private javax.swing.JLabel lblInformacaoStatusConsulta;
    private javax.swing.JLabel lblInformacaoValorConsulta;
    private javax.swing.JLabel lblInformacoesData;
    private javax.swing.JLabel lblInformacoesDataCancelamento;
    private javax.swing.JLabel lblInformacoesMotivo;
    private javax.swing.JLabel lblInformacoesNome;
    private javax.swing.JLabel lblInformacoesNumero;
    private javax.swing.JLabel lblInformacoesResumoSeparacao;
    private javax.swing.JLabel lblInformacoesStatus;
    private javax.swing.JLabel lblMotivoCancelamento;
    private javax.swing.JLabel lblNovoStatusAlteracao;
    private javax.swing.JLabel lblNumeroAguardando;
    private javax.swing.JLabel lblNumeroCadastro;
    private javax.swing.JLabel lblNumeroPedidoAlteracao;
    private javax.swing.JLabel lblNumeroPedidoCadastro;
    private javax.swing.JLabel lblNumeroPedidoConferencia;
    private javax.swing.JLabel lblNumeroPedidoConsulta;
    private javax.swing.JLabel lblNumeroPedidoDetalhes;
    private javax.swing.JLabel lblNumeroPedidoSeparacao;
    private javax.swing.JLabel lblNumeroPendente;
    private javax.swing.JLabel lblNumeroSeparados;
    private javax.swing.JLabel lblObservacaoConferencia;
    private javax.swing.JLabel lblObservacoesAlteracao;
    private javax.swing.JLabel lblObservacoesCadastro;
    private javax.swing.JLabel lblObservacoesCancelamento;
    private javax.swing.JLabel lblPagamentoConsulta;
    private javax.swing.JLabel lblPedidoDetalhesCancelado;
    private javax.swing.JLabel lblPedidoSelecionadoAlteracao;
    private javax.swing.JLabel lblPeriodoCancelamento;
    private javax.swing.JLabel lblPeriodoSeparacao;
    private javax.swing.JLabel lblPorcentagemResumoSeparacao;
    private javax.swing.JLabel lblResumoSeparacao;
    private javax.swing.JLabel lblRuaCadastro;
    private javax.swing.JLabel lblStatusAlteracao;
    private javax.swing.JLabel lblStatusAtualAlteracao;
    private javax.swing.JLabel lblStatusConferencia;
    private javax.swing.JLabel lblStatusConsulta;
    private javax.swing.JLabel lblStatusPedidoCadastro;
    private javax.swing.JLabel lblStatusPedidoConsulta;
    private javax.swing.JLabel lblStatusSeparacao;
    private javax.swing.JLabel lblStatusSeparacaoDetalhes;
    private javax.swing.JLabel lblTipoPagamentoCadastro;
    private javax.swing.JLabel lblTituloAlteracao;
    private javax.swing.JLabel lblTituloDetalhesCancelamento;
    private javax.swing.JLabel lblTituloDetalhesSeparacao;
    private javax.swing.JLabel lblTituloEnderecoCadastro;
    private javax.swing.JLabel lblTituloHistoricoAlteracao;
    private javax.swing.JLabel lblTituloInformacoesCancelamento;
    private javax.swing.JLabel lblTituloItensConferencia;
    private javax.swing.JLabel lblTituloPedidoConferencia;
    private javax.swing.JLabel lblValorTotalAlteracao;
    private javax.swing.JLabel lblValorTotalCadastro;
    private javax.swing.JLabel lblValorTotalCancelamento;
    private javax.swing.JLabel lblValorTotalConsulta;
    private javax.swing.JLabel lbldataPedidoAlteracao;
    private javax.swing.JPanel separacao;
    private javax.swing.JTable tblDetalhesCancelamento;
    private javax.swing.JTable tblHistoricoAlteracao;
    private javax.swing.JTable tblItensConferencia;
    private javax.swing.JTable tblItensPedidoConsulta;
    private javax.swing.JTable tblPedidoConsulta;
    private javax.swing.JTable tblPedidoSeparacao;
    private javax.swing.JTable tblPedidosCadastro;
    private javax.swing.JTable tblPedidosCancelados;
    private javax.swing.JTable tblPedidosConferencia;
    private javax.swing.JTable tblPedidosEncontradosConsulta;
    private javax.swing.JTable tblStatusPedidoAlteracao;
    private javax.swing.JTextField txtBairroCadastro;
    private javax.swing.JTextField txtCep;
    private javax.swing.JTextField txtCidadeCadastro;
    private javax.swing.JTextField txtClienteAlteracao;
    private javax.swing.JTextField txtClienteAlteracaoStatus;
    private javax.swing.JTextField txtClienteCadastro;
    private javax.swing.JTextField txtClienteCancelamento;
    private javax.swing.JTextField txtClienteConferencia;
    private javax.swing.JTextField txtClienteConsulta;
    private javax.swing.JTextField txtClienteDetalhesCancelamento;
    private javax.swing.JTextField txtClienteSeparacao;
    private javax.swing.JTextField txtConferente;
    private javax.swing.JTextField txtNumeroCadastro;
    private javax.swing.JTextField txtNumeroPedidoAlteracao;
    private javax.swing.JTextField txtNumeroPedidoCadastro;
    private javax.swing.JTextField txtNumeroPedidoConferencia;
    private javax.swing.JTextField txtNumeroPedidoConsulta;
    private javax.swing.JTextField txtNumeroPedidoSeparacao;
    private javax.swing.JTextArea txtObservacoesAlteracao;
    private javax.swing.JTextField txtPedidoDetalhesCancelado;
    private javax.swing.JTextField txtPedidoSelecionadoAlteracao;
    private javax.swing.JTextField txtRuaCadastro;
    private javax.swing.JTextField txtValorTotalAlteracao;
    private javax.swing.JTextField txtValorTotalCadastro;
    private javax.swing.JTextField txtValorTotalCancelamento;
    private javax.swing.JTextArea txtaObservacaoConferencia;
    private javax.swing.JTextArea txtaObservacoesCadastro;
    private javax.swing.JTextArea txtaObservacoesCancelamento;
    private javax.swing.JTextArea txtaObservacoesSeparacao;
    // End of variables declaration//GEN-END:variables
}
