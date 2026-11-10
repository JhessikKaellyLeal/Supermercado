package View;

import Control.CategoriasController;
import Control.PermissoesController;
import Model.Usuario;
import java.util.ArrayList;
import Control.UsuarioController;
import Control.VendasController;
import Model.Categorias;
import Model.ContasFinanceiras;
import Model.Permissoes;
import Model.Vendas;
import control.ContasFinanceirasController;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import Control.ItensVendasController;
import model.ItensVendas;

import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author JordanaLeite
 */
public class TelaVendas extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(TelaVendas.class.getName());
    private MenuPadrao menu;
    private Usuario usuario;
    //==========================CONSTRUTOR=================================
    public TelaVendas(Usuario usuario) {
        initComponents();
        
      chamarListarCombobox();
      carregarTodasAsTabelasVendas();
      carregarTodasAsTabelasItensVenda();
      carregarVendasPorFormaPagamento();  
      carregarContasBancarias();  
      configurarPesquisas();

        
       this.usuario= usuario;
       this.menu= MenuPadrao.instalar(
       this,
        usuario,
        "vendas",
        this::navegar,
        this::dispose
    );
       this.setExtendedState(TelaVendas.MAXIMIZED_BOTH);
    }
    
    //==========================CONSTRUTOR=================================

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

// ------------------------------------------------COMBO BOX---------------------------------------------------------------------

private void preencherCombo(JComboBox<String> combo, List<String> dados) {
    combo.removeAllItems();

    for (String dado : dados) {
        combo.addItem(dado);
    }
}

private void listarCategorias() {
    try {
        CategoriasController controller = new CategoriasController();
        List<Categorias> lista = controller.listar();

        List<String> dados = new ArrayList<>();

        for (Categorias categoria : lista) {
            dados.add(categoria.getNome());
        }

        preencherCombo(cbCategoriaPagamento, dados);

    } catch (Exception e) {
        JOptionPane.showMessageDialog(this,
                "Erro ao listar categorias: " + e.getMessage());
    }
}

private void listarOperadores() {
    try {
        UsuarioController controller = new UsuarioController();
        List<Usuario> lista = controller.listar();

        List<String> dados = new ArrayList<>();

        for (Usuario usuario : lista) {
            dados.add(usuario.getNome());
        }

        preencherCombo(cbOperador, dados);

    } catch (Exception e) {
        JOptionPane.showMessageDialog(this,
                "Erro ao listar operadores: " + e.getMessage());
    }
}

private void listarNiveisAcesso() {
    try {
        PermissoesController controller = new PermissoesController();
        List<Permissoes> lista = controller.listar();

        List<String> dados = new ArrayList<>();

        for (Permissoes permissao : lista) {
            dados.add(permissao.getTipo());
        }

        preencherCombo(cbNivelAcessoOperador, dados);

    } catch (Exception e) {
        JOptionPane.showMessageDialog(this,
                "Erro ao listar níveis de acesso: " + e.getMessage());
    }
}

private void listarBancos() {
    try {
        ContasFinanceirasController controller = new ContasFinanceirasController();
        List<ContasFinanceiras> lista = controller.listar();

        Set<String> dados = new LinkedHashSet<>();

        for (ContasFinanceiras conta : lista) {
            dados.add(conta.getBanco());
        }

        List<String> bancos = new ArrayList<>(dados);

        preencherCombo(cbBancoContas, bancos);
        preencherCombo(cbBancoFiltrosContas, bancos);

    } catch (Exception e) {
        JOptionPane.showMessageDialog(this,
                "Erro ao listar bancos: " + e.getMessage());
    }
}

private void listarTiposContas() {
    try {
        ContasFinanceirasController controller = new ContasFinanceirasController();
        List<ContasFinanceiras> lista = controller.listar();

        Set<String> dados = new LinkedHashSet<>();

        for (ContasFinanceiras conta : lista) {
            dados.add(conta.getTipo());
        }

        List<String> tipos = new ArrayList<>(dados);

        preencherCombo(cbTipoCadastroContas, tipos);
        preencherCombo(cbTipoFiltroContas, tipos);

    } catch (Exception e) {
        JOptionPane.showMessageDialog(this,
                "Erro ao listar tipos de contas: " + e.getMessage());
    }
}

private void listarAndamentoVendas() {
    try {
        VendasController controller = new VendasController();
        List<Vendas> lista = controller.listar();

        Set<String> dados = new LinkedHashSet<>();

        for (Vendas venda : lista) {
            if (venda.getStatusVenda() != null) {
                dados.add(venda.getStatusVenda());
            }
        }

        List<String> status = new ArrayList<>(dados);

        preencherCombo(cbAndamentoFiltro, status);

    } catch (Exception e) {
        JOptionPane.showMessageDialog(this,
                "Erro ao listar andamento das vendas: " + e.getMessage());
    }
}

private void listarBandeiras() {
    List<String> dados = new ArrayList<>();

    dados.add("Visa");
    dados.add("Mastercard");
    dados.add("Elo");
    dados.add("American Express");
    dados.add("Hipercard");
    dados.add("Diners Club");

    preencherCombo(bBandeiraFiltrosTaxas, dados);
    preencherCombo(cbBandeiraCadastroParcelamentos, dados);
    preencherCombo(cbBandeiraCadastroTaxas, dados);
    preencherCombo(cbBandeiraParcelamentos, dados);
}

private void listarTiposBandeiras() {
    List<String> dados = new ArrayList<>();

    dados.add("Crédito");
    dados.add("Débito");

    preencherCombo(cbTipoBandeiras, dados);
    preencherCombo(cbTipoCadastroBandeiras, dados);
}

private void listarMotivosEstorno() {
    List<String> dados = new ArrayList<>();

    dados.add("Desistência da compra");
    dados.add("Pagamento duplicado");
    dados.add("Erro no valor cobrado");
    dados.add("Produto indisponível");
    dados.add("Erro na forma de pagamento");
    dados.add("Cancelamento solicitado pelo cliente");

    preencherCombo(cbMotivoEstorno, dados);
}

private void listarTaxasJuros() {
    List<String> dados = new ArrayList<>();

    dados.add("0%");
    dados.add("1%");
    dados.add("2%");
    dados.add("3%");
    dados.add("4%");
    dados.add("5%");

    preencherCombo(cbTaxaJurosParcelamentos, dados);
}

private void listarTiposTaxas() {
    List<String> dados = new ArrayList<>();

    dados.add("Percentual");
    dados.add("Valor fixo");

    preencherCombo(cbTipoTaxas, dados);
}

private void listarTiposRelatorios() {
    List<String> dados = new ArrayList<>();

    dados.add("Todas as movimentações");
    dados.add("Entradas");
    dados.add("Saídas");
    dados.add("Saldo");

    preencherCombo(cbTipoRelatorioContas, dados);
}


private void chamarListarCombobox(){
   
    listarCategorias();
    listarOperadores();
    listarNiveisAcesso();
    listarBancos();
    listarTiposContas();
   
    listarAndamentoVendas();

    listarBandeiras();
    listarTiposBandeiras();
    listarMotivosEstorno();
    listarTaxasJuros();
    listarTiposTaxas();
    listarTiposRelatorios();
}

// ------------------------------------------ LISTAR TABELAS --------------------------------------------------------------------------------

private void carregarVendas(JTable tabela) {

    DefaultTableModel modelo = new DefaultTableModel();

    modelo.addColumn("ID");
    modelo.addColumn("Data");
    modelo.addColumn("Horário");
    modelo.addColumn("Valor Total");
    modelo.addColumn("Valor Pago");
    modelo.addColumn("Troco");
    modelo.addColumn("Itens");
    modelo.addColumn("Status");
    modelo.addColumn("Cliente");
    modelo.addColumn("Usuário");
    modelo.addColumn("Caixa");
    modelo.addColumn("Cupom");

    VendasController controller = new VendasController();

    List<Vendas> lista = controller.listar();

    for (Vendas venda : lista) {

        modelo.addRow(new Object[]{
            venda.getIdvendas(),
            venda.getDataVenda(),
            venda.getHorarioVenda(),
            venda.getValorTotal(),
            venda.getValorPago(),
            venda.getTroco(),
            venda.getQuantidadeItens(),
            venda.getStatusVenda(),
            venda.getNomeCliente(),
            venda.getNomeUsuario(),
            venda.getNomeCaixa(),
            venda.getCodigoCupom()
        });
    }

    tabela.setModel(modelo);
}

private void carregarTodasAsTabelasVendas() {

    JTable[] tabelas = {
        tbVendasCadastradasConsulta,
        tbVendasFiltro,
        tbVendasOperador,
        tbVendasCadastradasProduto,
        tbVendasPagamento
    };

    for (JTable tabela : tabelas) {
        carregarVendas(tabela);
    }
}

private void carregarItensVenda(JTable tabela) {

    DefaultTableModel modelo = new DefaultTableModel();

    modelo.addColumn("ID Item");
    modelo.addColumn("Quantidade");
    modelo.addColumn("Preço Unitário");
    modelo.addColumn("Desconto");
    modelo.addColumn("Valor Total");
    modelo.addColumn("ID Venda");
    modelo.addColumn("Cliente");
    modelo.addColumn("Usuário");
    modelo.addColumn("Caixa");
    modelo.addColumn("Cupom");

    ItensVendasController controller =
            new ItensVendasController();

    List<ItensVendas> lista =
            controller.listar();

    for (ItensVendas item : lista) {

        modelo.addRow(new Object[]{
            item.getIditensVendas(),
            item.getQuantidade(),
            item.getPrecoUnitario(),
            item.getDesconto(),
            item.getValorTotal(),
            item.getVendas_idvendas(),
            item.getNomeCliente(),
            item.getNomeUsuario(),
            item.getNomeCaixa(),
            item.getCodigoCupom()
        });
    }

    tabela.setModel(modelo);
}

private void carregarTodasAsTabelasItensVenda() {

    JTable[] tabelas = {

        tbItensVendaConsulta,
        tbItensVendaCancelamento,
        tbItensVendaEstorno,
        tbItensVendaComprovante

    };

    for (JTable tabela : tabelas) {

        carregarItensVenda(tabela);

    }
}

//=========================BOTÕES============================
private void configurarPesquisas() {

    btPesquisarConsulta.addActionListener(e -> pesquisar());

    btPesquisarFiltro.addActionListener(e -> pesquisar());

    btPesquisarOperador.addActionListener(e -> pesquisar());

    btPesquisarProduto.addActionListener(e -> pesquisar());

    btPesquisarPagamento.addActionListener(e -> pesquisar());

    btPesquisarCancelamento.addActionListener(e -> pesquisar());

    btPesquisarEstorno.addActionListener(e -> pesquisar());

    btPesquisarComprovante.addActionListener(e -> pesquisar());

    btPesquisarBandeiras.addActionListener(e -> pesquisar());

    btPesquisarParcelamentos.addActionListener(e -> pesquisar());

    btPesquisarTaxas.addActionListener(e -> pesquisar());

    btPesquisarContas.addActionListener(e -> pesquisar());

}
private void pesquisar() {

    JOptionPane.showMessageDialog(
            this,
            "Pesquisa executada."
    );

}




//===============================================================================================
//===============================================================================================
//===============================================================================================
 @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tpInicio = new javax.swing.JTabbedPane();
        jpConsulta = new javax.swing.JPanel();
        jpCabecalhoConsulta = new javax.swing.JPanel();
        btPesquisarConsulta = new javax.swing.JButton();
        tfPesquisa = new javax.swing.JTextField();
        lbConsultaVenda = new javax.swing.JLabel();
        jpDetalhesVenda = new javax.swing.JPanel();
        lbDetalheVenda = new javax.swing.JLabel();
        lbNumeroVenda = new javax.swing.JLabel();
        tfNumeroVenda = new javax.swing.JTextField();
        lbDataVenda = new javax.swing.JLabel();
        lbClienteVenda = new javax.swing.JLabel();
        tfClienteVenda = new javax.swing.JTextField();
        lbOperadorVenda = new javax.swing.JLabel();
        tfOperadorVenda = new javax.swing.JTextField();
        tfPagamentoVenda = new javax.swing.JTextField();
        lbPagamentoVenda = new javax.swing.JLabel();
        tfParcelasVenda = new javax.swing.JTextField();
        lbParcelasVenda = new javax.swing.JLabel();
        tfValorVenda = new javax.swing.JTextField();
        lbValorVenda = new javax.swing.JLabel();
        tfAndamentoVenda = new javax.swing.JTextField();
        lbAndamentoVenda = new javax.swing.JLabel();
        ftxDataVenda = new javax.swing.JFormattedTextField();
        jpItensVenda = new javax.swing.JPanel();
        lbItensVenda = new javax.swing.JLabel();
        JScrollPane = new javax.swing.JScrollPane();
        tbItensVendaConsulta = new javax.swing.JTable();
        jScrollPane7 = new javax.swing.JScrollPane();
        tbVendasCadastradasConsulta = new javax.swing.JTable();
        jpVendasCadastradas = new javax.swing.JPanel();
        lbVendasCadastradas = new javax.swing.JLabel();
        jpFiltros = new javax.swing.JPanel();
        jpCabecalhoPeriodoVendas = new javax.swing.JPanel();
        lbPeriodoVendas = new javax.swing.JLabel();
        lbDataFinalFiltros = new javax.swing.JLabel();
        lbDataInicialFiltro = new javax.swing.JLabel();
        ftfDataInicialFiltro = new javax.swing.JFormattedTextField();
        ftfDataFinalFiltro = new javax.swing.JFormattedTextField();
        cbFormaPgFiltro = new javax.swing.JComboBox<>();
        cbAndamentoFiltro = new javax.swing.JComboBox<>();
        lbFormasPgFiltro = new javax.swing.JLabel();
        lbAndamentoFiltro = new javax.swing.JLabel();
        btPesquisarFiltro = new javax.swing.JButton();
        jpTabVendas = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        tbVendasFiltro = new javax.swing.JTable();
        lbVendasFiltros = new javax.swing.JLabel();
        jpOperador = new javax.swing.JPanel();
        jpCabecalhoOperador = new javax.swing.JPanel();
        cbOperador = new javax.swing.JComboBox<>();
        lbDadosOperador = new javax.swing.JLabel();
        lbNomeOperador = new javax.swing.JLabel();
        tfNomeOperador = new javax.swing.JTextField();
        tfLoginOperador = new javax.swing.JTextField();
        lbLoginOperador = new javax.swing.JLabel();
        lbNivelAcessoOperador = new javax.swing.JLabel();
        tfQuantVendasOperador = new javax.swing.JTextField();
        lbQuantVendasOperador = new javax.swing.JLabel();
        cbNivelAcessoOperador = new javax.swing.JComboBox<>();
        lbVendasRealizadasOperador = new javax.swing.JLabel();
        lbValorTotalOperador = new javax.swing.JLabel();
        tfValorTotalOperador = new javax.swing.JTextField();
        lbOperador = new javax.swing.JLabel();
        btPesquisarOperador = new javax.swing.JButton();
        jpVendasOperador = new javax.swing.JPanel();
        lbVendasOperador = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        tbVendasOperador = new javax.swing.JTable();
        jpProduto = new javax.swing.JPanel();
        jpCabecalhoProduto = new javax.swing.JPanel();
        lbProduto = new javax.swing.JLabel();
        tfProduto = new javax.swing.JTextField();
        lbDadosProduto = new javax.swing.JLabel();
        lbCodigoProduto = new javax.swing.JLabel();
        tfCodigoProduto = new javax.swing.JTextField();
        lbNomeProduto = new javax.swing.JLabel();
        tfNomeProduto = new javax.swing.JTextField();
        lbCategoriaProduto = new javax.swing.JLabel();
        lbValorUnitarioProduto = new javax.swing.JLabel();
        tfValorUnitarioProduto = new javax.swing.JTextField();
        tfCategoriaProduto = new javax.swing.JTextField();
        lbEstoqueAtualProduto = new javax.swing.JLabel();
        tfEstoqueAtualProduto = new javax.swing.JTextField();
        lbVendasProduto = new javax.swing.JLabel();
        lbQuantVendidaProduto = new javax.swing.JLabel();
        lbValorTotalProduto = new javax.swing.JLabel();
        tfQuantVendidaProduto = new javax.swing.JTextField();
        tfValorTotalProduto = new javax.swing.JTextField();
        btPesquisarProduto = new javax.swing.JButton();
        jpVendasCadastradasProduto = new javax.swing.JPanel();
        lbVendasCadastradasProduto = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tbVendasCadastradasProduto = new javax.swing.JTable();
        jpCategoria = new javax.swing.JPanel();
        jpFiltrosPagamento = new javax.swing.JPanel();
        lbFiltrosPagamento = new javax.swing.JLabel();
        cbCategoriaPagamento = new javax.swing.JComboBox<>();
        ftfDataInicialPagamento = new javax.swing.JFormattedTextField();
        cbFormaPagamento = new javax.swing.JComboBox<>();
        ftfDataFinalPagamento = new javax.swing.JFormattedTextField();
        lbCategoriaPagamento = new javax.swing.JLabel();
        lbFormaPagamento = new javax.swing.JLabel();
        lbDataInicialPagamento = new javax.swing.JLabel();
        lbDataFinalPagamento = new javax.swing.JLabel();
        btPesquisarPagamento = new javax.swing.JButton();
        jpVendasPagamento = new javax.swing.JPanel();
        lbVendasPagamento = new javax.swing.JLabel();
        jScrollPane10 = new javax.swing.JScrollPane();
        tbVendasPagamento = new javax.swing.JTable();
        jpCancelamento = new javax.swing.JPanel();
        jpCabecalhoDadosVenda = new javax.swing.JPanel();
        lbDadosVendaCancelamento = new javax.swing.JLabel();
        lbDataHoraCancelamento = new javax.swing.JLabel();
        lbOperadorCancelamento = new javax.swing.JLabel();
        lbFormaPagamentoCancelamento = new javax.swing.JLabel();
        btPesquisarCancelamento = new javax.swing.JButton();
        tfFormaPagamentoCancelamento = new javax.swing.JTextField();
        tfOperadorCancelamento = new javax.swing.JTextField();
        tfVendaCancelamento = new javax.swing.JTextField();
        lbNVendaCancelamento = new javax.swing.JLabel();
        tfDataHoraCancelamento = new javax.swing.JTextField();
        jpTotalVendaCancelamento = new javax.swing.JPanel();
        lbTotalVendaCancelamento = new javax.swing.JLabel();
        tfTotalVendaCancelamento = new javax.swing.JTextField();
        jpItensVendaCancelamento = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tbItensVendaCancelamento = new javax.swing.JTable();
        lbItensVendaCancelamento = new javax.swing.JLabel();
        jpEstorno = new javax.swing.JPanel();
        jpDadosVendaEstorno = new javax.swing.JPanel();
        lbDadosVendaEstorno = new javax.swing.JLabel();
        lbDataHoraEstorno = new javax.swing.JLabel();
        tfNVendaEstorno = new javax.swing.JTextField();
        lbOperadorEstorno = new javax.swing.JLabel();
        lbFormaPagamentoEstorno = new javax.swing.JLabel();
        lbNVendaEstorno = new javax.swing.JLabel();
        tfFormaPagamentoEstorno = new javax.swing.JTextField();
        tfOperadorEstorno = new javax.swing.JTextField();
        tfDataHoraEstorno = new javax.swing.JTextField();
        jpTotalVendaEstorno = new javax.swing.JPanel();
        lbTotalVendaEstorno = new javax.swing.JLabel();
        tfTotalVendaEstorno = new javax.swing.JTextField();
        btPesquisarEstorno = new javax.swing.JButton();
        jpItensVendaEstorno = new javax.swing.JPanel();
        lbItensVendaEstorno = new javax.swing.JLabel();
        jScrollPane13 = new javax.swing.JScrollPane();
        tbItensVendaEstorno = new javax.swing.JTable();
        jpDadosEstorno = new javax.swing.JPanel();
        lbDadosEstorno = new javax.swing.JLabel();
        lbMotivoEstorno = new javax.swing.JLabel();
        lbObservacaoEstorno = new javax.swing.JLabel();
        cbMotivoEstorno = new javax.swing.JComboBox<>();
        tfObservacaoEstorno = new javax.swing.JTextField();
        lvValorEstorno = new javax.swing.JLabel();
        tfValorEstorno = new javax.swing.JTextField();
        btConfirmarEstorno = new javax.swing.JButton();
        jpComprovante = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        lbDadosVendaComprovante = new javax.swing.JLabel();
        lbNVendaComprovante = new javax.swing.JLabel();
        tfNVendaComprovante = new javax.swing.JTextField();
        btPesquisarComprovante = new javax.swing.JButton();
        lbFormaPagamentoComprovante = new javax.swing.JLabel();
        lbOperadorComprovante = new javax.swing.JLabel();
        lbDataHoraComprovante = new javax.swing.JLabel();
        tfDataHoraComprovante = new javax.swing.JTextField();
        tfOperadorComprovante = new javax.swing.JTextField();
        tfFormaPagamentoComprovante = new javax.swing.JTextField();
        jpTotalVendasComprovante = new javax.swing.JPanel();
        lbTotalVendaComprovante = new javax.swing.JLabel();
        tfTotalVendaComprovante = new javax.swing.JTextField();
        jpItensVendaComprovante = new javax.swing.JPanel();
        lbItensVendaComprovante = new javax.swing.JLabel();
        jScrollPane14 = new javax.swing.JScrollPane();
        tbItensVendaComprovante = new javax.swing.JTable();
        jpBandeiras = new javax.swing.JPanel();
        jpFiltrosBandeiras = new javax.swing.JPanel();
        lbFiltrosBandeiras = new javax.swing.JLabel();
        lbDescricaoBandeiras = new javax.swing.JLabel();
        tfDescricaoBandeiras = new javax.swing.JTextField();
        lbTipoBandeiras = new javax.swing.JLabel();
        cbTipoBandeiras = new javax.swing.JComboBox<>();
        jpCadastroBandeiras = new javax.swing.JPanel();
        lbCadastroBandeiras = new javax.swing.JLabel();
        lbCodigoBandeiras = new javax.swing.JLabel();
        tfCodigoBandeiras = new javax.swing.JTextField();
        lbTipoCadastroBandeiras = new javax.swing.JLabel();
        cbTipoCadastroBandeiras = new javax.swing.JComboBox<>();
        lbDescricaoCadastroBandeiras = new javax.swing.JLabel();
        jcbAtivaCadastroBandeiras = new javax.swing.JCheckBox();
        jcbDesativaCadastroBandeiras = new javax.swing.JCheckBox();
        tfDescricaoCadastroBandeiras = new javax.swing.JTextField();
        jpbtCadastroBandeiras = new javax.swing.JPanel();
        btSalvarBandeiras = new javax.swing.JButton();
        btNovoBandeiras = new javax.swing.JButton();
        btEditarBandeiras = new javax.swing.JButton();
        btExcluirBandeiras = new javax.swing.JButton();
        jpbtFiltrosBandeiras = new javax.swing.JPanel();
        btPesquisarBandeiras = new javax.swing.JButton();
        btLimparFiltrosBandeiras = new javax.swing.JButton();
        jpParcelamentos = new javax.swing.JPanel();
        jpFiltrosParcelamentos = new javax.swing.JPanel();
        lbFiltrosParcelamentos = new javax.swing.JLabel();
        lbFormasPagamentoParcelamentos = new javax.swing.JLabel();
        cbFormaPagamentoParcelamentos = new javax.swing.JComboBox<>();
        lbBandeiraParcelamentos = new javax.swing.JLabel();
        cbBandeiraParcelamentos = new javax.swing.JComboBox<>();
        jpCadastroParcelamentos = new javax.swing.JPanel();
        lbCadastroParcelamentos = new javax.swing.JLabel();
        lbCodigoParcelamentos = new javax.swing.JLabel();
        tfCodigoParcelamentos = new javax.swing.JTextField();
        lbBandeiraCadastroParcelamentos = new javax.swing.JLabel();
        cbBandeiraCadastroParcelamentos = new javax.swing.JComboBox<>();
        lbNParcelasParcelamentos = new javax.swing.JLabel();
        cbNParcelasParcelamentos = new javax.swing.JComboBox<>();
        lbTaxaJurosParcelamentos = new javax.swing.JLabel();
        cbTaxaJurosParcelamentos = new javax.swing.JComboBox<>();
        jbcAtivoCadastroParcelamentos = new javax.swing.JCheckBox();
        lbFormaPagamentos = new javax.swing.JLabel();
        cbFormaPagamentoCadastroParcelamentos = new javax.swing.JComboBox<>();
        jpbtFiltrosParcelamentos = new javax.swing.JPanel();
        btPesquisarParcelamentos = new javax.swing.JButton();
        btLimparFiltrosParcelamentos = new javax.swing.JButton();
        jpbtCadastroParcelamentos = new javax.swing.JPanel();
        btSalvarParcelamentos = new javax.swing.JButton();
        btNovoParcelamentos = new javax.swing.JButton();
        btEditarParcelamentos = new javax.swing.JButton();
        btExcluirParcelamentos = new javax.swing.JButton();
        jpTaxas = new javax.swing.JPanel();
        jpCadastroTaxas = new javax.swing.JPanel();
        lbCadastroTaxas = new javax.swing.JLabel();
        lbCodigoTaxas = new javax.swing.JLabel();
        tfCodigoTaxas = new javax.swing.JTextField();
        lbTipoTaxas = new javax.swing.JLabel();
        cbTipoTaxas = new javax.swing.JComboBox<>();
        lbFormaPagamentoCadastroTaxas = new javax.swing.JLabel();
        cbFormaPagamentoCadastroTaxas = new javax.swing.JComboBox<>();
        lbValorTaxas = new javax.swing.JLabel();
        jcbAtivoTaxas = new javax.swing.JCheckBox();
        lbBandeiraCadastroTaxas = new javax.swing.JLabel();
        cbBandeiraCadastroTaxas = new javax.swing.JComboBox<>();
        tfValorTaxas = new javax.swing.JTextField();
        jpFiltrosTaxas = new javax.swing.JPanel();
        lbFiltrosTaxas = new javax.swing.JLabel();
        lbFormaPagamentoFiltrosTaxas = new javax.swing.JLabel();
        cbFormaPagamentoFiltrosTaxas = new javax.swing.JComboBox<>();
        lbBandeiraFiltrosTaxas = new javax.swing.JLabel();
        bBandeiraFiltrosTaxas = new javax.swing.JComboBox<>();
        jpbtCadastroTaxas = new javax.swing.JPanel();
        btSalvarTaxas = new javax.swing.JButton();
        btNovoTaxas = new javax.swing.JButton();
        btEditarTaxas = new javax.swing.JButton();
        btExcluirTaxas = new javax.swing.JButton();
        jpbtFiltrosTaxas = new javax.swing.JPanel();
        btPesquisarTaxas = new javax.swing.JButton();
        btLimparFiltrosTaxas = new javax.swing.JButton();
        jpContas = new javax.swing.JPanel();
        jpRelatoriosContas = new javax.swing.JPanel();
        lbRelatoriosContas = new javax.swing.JLabel();
        lbTipoRelatorioContas = new javax.swing.JLabel();
        cbTipoRelatorioContas = new javax.swing.JComboBox<>();
        lbPeriodoContas = new javax.swing.JLabel();
        ftfPeriodoInicialContas = new javax.swing.JFormattedTextField();
        ftfPeriodoFinalContas = new javax.swing.JFormattedTextField();
        btGerarRelatorioContas = new javax.swing.JButton();
        btLimparContas = new javax.swing.JButton();
        jpCadastroContas = new javax.swing.JPanel();
        lbCadastroContas = new javax.swing.JLabel();
        lbCodigoContas = new javax.swing.JLabel();
        tfCodigoContas = new javax.swing.JTextField();
        lbTipoCadastroContas = new javax.swing.JLabel();
        cbTipoCadastroContas = new javax.swing.JComboBox<>();
        lbBancoContas = new javax.swing.JLabel();
        cbBancoContas = new javax.swing.JComboBox<>();
        jcbAtivaContas = new javax.swing.JCheckBox();
        lbAgenciaContas = new javax.swing.JLabel();
        tfAgenciaContas = new javax.swing.JTextField();
        lbCadastroContaContas = new javax.swing.JLabel();
        tfCadastroContaContas = new javax.swing.JTextField();
        jpFiltrosContas = new javax.swing.JPanel();
        lbFiltrosContas = new javax.swing.JLabel();
        lbBancoFiltrosContas = new javax.swing.JLabel();
        cbBancoFiltrosContas = new javax.swing.JComboBox<>();
        lbTipoFiltroContas = new javax.swing.JLabel();
        cbTipoFiltroContas = new javax.swing.JComboBox<>();
        jpbtCadastroContas = new javax.swing.JPanel();
        btSalvarContas = new javax.swing.JButton();
        btNovoContas = new javax.swing.JButton();
        btEditarContas = new javax.swing.JButton();
        btExcluirContas = new javax.swing.JButton();
        jpbtFiltrosContas = new javax.swing.JPanel();
        btPesquisarContas = new javax.swing.JButton();
        btLimparFiltrosContas = new javax.swing.JButton();
        jpContasBancarias = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbContasBancarias = new javax.swing.JTable();
        lbContasBancarias = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tpInicio.setBackground(new java.awt.Color(10, 4, 138));
        tpInicio.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 204), 1, true));
        tpInicio.setForeground(new java.awt.Color(255, 255, 255));
        tpInicio.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N

        jpConsulta.setBackground(new java.awt.Color(255, 255, 255));

        jpCabecalhoConsulta.setBackground(new java.awt.Color(255, 255, 255));
        jpCabecalhoConsulta.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 204), 1, true));

        btPesquisarConsulta.setBackground(new java.awt.Color(10, 4, 138));
        btPesquisarConsulta.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btPesquisarConsulta.setForeground(new java.awt.Color(255, 255, 255));
        btPesquisarConsulta.setText("Pesquisar");

        tfPesquisa.setBackground(new java.awt.Color(255, 255, 255));
        tfPesquisa.setForeground(new java.awt.Color(0, 0, 0));
        tfPesquisa.addActionListener(this::tfPesquisaActionPerformed);

        lbConsultaVenda.setBackground(new java.awt.Color(255, 255, 255));
        lbConsultaVenda.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lbConsultaVenda.setForeground(new java.awt.Color(0, 0, 0));
        lbConsultaVenda.setText("N° da venda");

        javax.swing.GroupLayout jpCabecalhoConsultaLayout = new javax.swing.GroupLayout(jpCabecalhoConsulta);
        jpCabecalhoConsulta.setLayout(jpCabecalhoConsultaLayout);
        jpCabecalhoConsultaLayout.setHorizontalGroup(
            jpCabecalhoConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpCabecalhoConsultaLayout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jpCabecalhoConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbConsultaVenda)
                    .addGroup(jpCabecalhoConsultaLayout.createSequentialGroup()
                        .addComponent(tfPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 479, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btPesquisarConsulta)))
                .addContainerGap(221, Short.MAX_VALUE))
        );
        jpCabecalhoConsultaLayout.setVerticalGroup(
            jpCabecalhoConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpCabecalhoConsultaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbConsultaVenda)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jpCabecalhoConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btPesquisarConsulta)
                    .addComponent(tfPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17))
        );

        jpDetalhesVenda.setBackground(new java.awt.Color(255, 255, 255));
        jpDetalhesVenda.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 204), 1, true));

        lbDetalheVenda.setBackground(new java.awt.Color(255, 255, 255));
        lbDetalheVenda.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lbDetalheVenda.setForeground(new java.awt.Color(0, 0, 0));
        lbDetalheVenda.setText("Detalhes da venda");
        lbDetalheVenda.setToolTipText("");

        lbNumeroVenda.setBackground(new java.awt.Color(255, 255, 255));
        lbNumeroVenda.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lbNumeroVenda.setForeground(new java.awt.Color(0, 0, 0));
        lbNumeroVenda.setText("N° da venda");

        tfNumeroVenda.setBackground(new java.awt.Color(255, 255, 255));
        tfNumeroVenda.setForeground(new java.awt.Color(0, 0, 0));

        lbDataVenda.setBackground(new java.awt.Color(255, 255, 255));
        lbDataVenda.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lbDataVenda.setForeground(new java.awt.Color(0, 0, 0));
        lbDataVenda.setText("Data/Hora");

        lbClienteVenda.setBackground(new java.awt.Color(255, 255, 255));
        lbClienteVenda.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lbClienteVenda.setForeground(new java.awt.Color(0, 0, 0));
        lbClienteVenda.setText("Cliente");

        tfClienteVenda.setBackground(new java.awt.Color(255, 255, 255));
        tfClienteVenda.setForeground(new java.awt.Color(0, 0, 0));

        lbOperadorVenda.setBackground(new java.awt.Color(255, 255, 255));
        lbOperadorVenda.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lbOperadorVenda.setForeground(new java.awt.Color(0, 0, 0));
        lbOperadorVenda.setText("Operador");

        tfOperadorVenda.setBackground(new java.awt.Color(255, 255, 255));
        tfOperadorVenda.setForeground(new java.awt.Color(0, 0, 0));

        tfPagamentoVenda.setBackground(new java.awt.Color(255, 255, 255));
        tfPagamentoVenda.setForeground(new java.awt.Color(0, 0, 0));

        lbPagamentoVenda.setBackground(new java.awt.Color(255, 255, 255));
        lbPagamentoVenda.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lbPagamentoVenda.setForeground(new java.awt.Color(0, 0, 0));
        lbPagamentoVenda.setText("Forma de pagamento");

        tfParcelasVenda.setBackground(new java.awt.Color(255, 255, 255));
        tfParcelasVenda.setForeground(new java.awt.Color(0, 0, 0));

        lbParcelasVenda.setBackground(new java.awt.Color(255, 255, 255));
        lbParcelasVenda.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lbParcelasVenda.setForeground(new java.awt.Color(0, 0, 0));
        lbParcelasVenda.setText("Parcelas");

        tfValorVenda.setBackground(new java.awt.Color(255, 255, 255));
        tfValorVenda.setForeground(new java.awt.Color(0, 0, 0));

        lbValorVenda.setBackground(new java.awt.Color(255, 255, 255));
        lbValorVenda.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lbValorVenda.setForeground(new java.awt.Color(0, 0, 0));
        lbValorVenda.setText("Valor total");

        tfAndamentoVenda.setBackground(new java.awt.Color(255, 255, 255));
        tfAndamentoVenda.setForeground(new java.awt.Color(0, 0, 0));

        lbAndamentoVenda.setBackground(new java.awt.Color(255, 255, 255));
        lbAndamentoVenda.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lbAndamentoVenda.setForeground(new java.awt.Color(0, 0, 0));
        lbAndamentoVenda.setText("Andamento");

        ftxDataVenda.setBackground(new java.awt.Color(255, 255, 255));
        ftxDataVenda.setForeground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jpDetalhesVendaLayout = new javax.swing.GroupLayout(jpDetalhesVenda);
        jpDetalhesVenda.setLayout(jpDetalhesVendaLayout);
        jpDetalhesVendaLayout.setHorizontalGroup(
            jpDetalhesVendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpDetalhesVendaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpDetalhesVendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbDetalheVenda)
                    .addGroup(jpDetalhesVendaLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jpDetalhesVendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfClienteVenda)
                            .addComponent(tfOperadorVenda)
                            .addComponent(tfNumeroVenda)
                            .addGroup(jpDetalhesVendaLayout.createSequentialGroup()
                                .addGroup(jpDetalhesVendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbDataVenda)
                                    .addComponent(lbClienteVenda)
                                    .addComponent(lbOperadorVenda)
                                    .addComponent(lbNumeroVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 74, Short.MAX_VALUE))
                            .addComponent(ftxDataVenda))
                        .addGap(18, 18, 18)
                        .addGroup(jpDetalhesVendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbPagamentoVenda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tfParcelasVenda)
                            .addComponent(tfAndamentoVenda)
                            .addComponent(tfValorVenda)
                            .addGroup(jpDetalhesVendaLayout.createSequentialGroup()
                                .addGroup(jpDetalhesVendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbAndamentoVenda)
                                    .addComponent(lbValorVenda)
                                    .addComponent(lbParcelasVenda)
                                    .addComponent(tfPagamentoVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 5, Short.MAX_VALUE)))))
                .addGap(26, 26, 26))
        );
        jpDetalhesVendaLayout.setVerticalGroup(
            jpDetalhesVendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpDetalhesVendaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbDetalheVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpDetalhesVendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpDetalhesVendaLayout.createSequentialGroup()
                        .addComponent(lbNumeroVenda)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfNumeroVenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpDetalhesVendaLayout.createSequentialGroup()
                        .addComponent(lbPagamentoVenda)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfPagamentoVenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpDetalhesVendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpDetalhesVendaLayout.createSequentialGroup()
                        .addComponent(lbDataVenda)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ftxDataVenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpDetalhesVendaLayout.createSequentialGroup()
                        .addComponent(lbParcelasVenda)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfParcelasVenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpDetalhesVendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpDetalhesVendaLayout.createSequentialGroup()
                        .addComponent(lbClienteVenda)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfClienteVenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpDetalhesVendaLayout.createSequentialGroup()
                        .addComponent(lbValorVenda)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfValorVenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpDetalhesVendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpDetalhesVendaLayout.createSequentialGroup()
                        .addComponent(lbOperadorVenda)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfOperadorVenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpDetalhesVendaLayout.createSequentialGroup()
                        .addComponent(lbAndamentoVenda)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfAndamentoVenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        jpItensVenda.setBackground(new java.awt.Color(10, 4, 138));
        jpItensVenda.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 204), 1, true));

        lbItensVenda.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lbItensVenda.setForeground(new java.awt.Color(255, 255, 255));
        lbItensVenda.setText("Itens da venda");

        tbItensVendaConsulta.setBackground(new java.awt.Color(255, 255, 255));
        tbItensVendaConsulta.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        tbItensVendaConsulta.setForeground(new java.awt.Color(0, 0, 0));
        tbItensVendaConsulta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Código", "Produto", "Quantidade", "Subtotal"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        JScrollPane.setViewportView(tbItensVendaConsulta);

        javax.swing.GroupLayout jpItensVendaLayout = new javax.swing.GroupLayout(jpItensVenda);
        jpItensVenda.setLayout(jpItensVendaLayout);
        jpItensVendaLayout.setHorizontalGroup(
            jpItensVendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpItensVendaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpItensVendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbItensVenda)
                    .addComponent(JScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 408, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        jpItensVendaLayout.setVerticalGroup(
            jpItensVendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpItensVendaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbItensVenda)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
                .addContainerGap())
        );

        tbVendasCadastradasConsulta.setBackground(new java.awt.Color(255, 255, 255));
        tbVendasCadastradasConsulta.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        tbVendasCadastradasConsulta.setForeground(new java.awt.Color(0, 0, 0));
        tbVendasCadastradasConsulta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "N° Venda", "Data/Hora", "Operador", "Total", "Forma de Pagamento"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane7.setViewportView(tbVendasCadastradasConsulta);

        jpVendasCadastradas.setBackground(new java.awt.Color(10, 4, 138));

        lbVendasCadastradas.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lbVendasCadastradas.setForeground(new java.awt.Color(255, 255, 255));
        lbVendasCadastradas.setText("Vendas cadastradas");

        javax.swing.GroupLayout jpVendasCadastradasLayout = new javax.swing.GroupLayout(jpVendasCadastradas);
        jpVendasCadastradas.setLayout(jpVendasCadastradasLayout);
        jpVendasCadastradasLayout.setHorizontalGroup(
            jpVendasCadastradasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpVendasCadastradasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbVendasCadastradas)
                .addContainerGap(638, Short.MAX_VALUE))
        );
        jpVendasCadastradasLayout.setVerticalGroup(
            jpVendasCadastradasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpVendasCadastradasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbVendasCadastradas)
                .addContainerGap(222, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jpConsultaLayout = new javax.swing.GroupLayout(jpConsulta);
        jpConsulta.setLayout(jpConsultaLayout);
        jpConsultaLayout.setHorizontalGroup(
            jpConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpConsultaLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jpConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jpCabecalhoConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jpConsultaLayout.createSequentialGroup()
                        .addComponent(jpDetalhesVenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jpItensVenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpConsultaLayout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 790, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jpVendasCadastradas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        jpConsultaLayout.setVerticalGroup(
            jpConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpConsultaLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jpCabecalhoConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(jpConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jpDetalhesVenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jpItensVenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addGroup(jpConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpConsultaLayout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jpVendasCadastradas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        tpInicio.addTab("Consulta e Detalhes de Pagamento", jpConsulta);

        jpFiltros.setBackground(new java.awt.Color(255, 255, 255));

        jpCabecalhoPeriodoVendas.setBackground(new java.awt.Color(255, 255, 255));
        jpCabecalhoPeriodoVendas.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 204), 1, true));

        lbPeriodoVendas.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lbPeriodoVendas.setForeground(new java.awt.Color(0, 0, 0));
        lbPeriodoVendas.setText("Período de vendas");

        lbDataFinalFiltros.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lbDataFinalFiltros.setForeground(new java.awt.Color(0, 0, 0));
        lbDataFinalFiltros.setText("Data final");

        lbDataInicialFiltro.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lbDataInicialFiltro.setForeground(new java.awt.Color(0, 0, 0));
        lbDataInicialFiltro.setText("Data inicial");

        ftfDataInicialFiltro.setBackground(new java.awt.Color(255, 255, 255));
        ftfDataInicialFiltro.setForeground(new java.awt.Color(0, 0, 0));

        ftfDataFinalFiltro.setBackground(new java.awt.Color(255, 255, 255));
        ftfDataFinalFiltro.setForeground(new java.awt.Color(0, 0, 0));

        cbFormaPgFiltro.setBackground(new java.awt.Color(255, 255, 255));
        cbFormaPgFiltro.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cbFormaPgFiltro.setForeground(new java.awt.Color(0, 0, 0));
        cbFormaPgFiltro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos", "Cartão de Crédito", "Cartão de Débito", "Cartão do Supermercado", "Carteiras Digitais", "Dinheiro", "PIX", "Vale Alimentação", " ", " " }));

        cbAndamentoFiltro.setBackground(new java.awt.Color(255, 255, 255));
        cbAndamentoFiltro.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cbAndamentoFiltro.setForeground(new java.awt.Color(0, 0, 0));
        cbAndamentoFiltro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos", "Cancelados", "Concluídos" }));
        cbAndamentoFiltro.addActionListener(this::cbAndamentoFiltroActionPerformed);

        lbFormasPgFiltro.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lbFormasPgFiltro.setForeground(new java.awt.Color(0, 0, 0));
        lbFormasPgFiltro.setText("Forma de pagamento");

        lbAndamentoFiltro.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lbAndamentoFiltro.setForeground(new java.awt.Color(0, 0, 0));
        lbAndamentoFiltro.setText("Andamento");

        btPesquisarFiltro.setBackground(new java.awt.Color(10, 4, 138));
        btPesquisarFiltro.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btPesquisarFiltro.setForeground(new java.awt.Color(255, 255, 255));
        btPesquisarFiltro.setText("Pesquisar");

        javax.swing.GroupLayout jpCabecalhoPeriodoVendasLayout = new javax.swing.GroupLayout(jpCabecalhoPeriodoVendas);
        jpCabecalhoPeriodoVendas.setLayout(jpCabecalhoPeriodoVendasLayout);
        jpCabecalhoPeriodoVendasLayout.setHorizontalGroup(
            jpCabecalhoPeriodoVendasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpCabecalhoPeriodoVendasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpCabecalhoPeriodoVendasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbPeriodoVendas)
                    .addGroup(jpCabecalhoPeriodoVendasLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jpCabecalhoPeriodoVendasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ftfDataInicialFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbDataInicialFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(85, 85, 85)
                        .addGroup(jpCabecalhoPeriodoVendasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbDataFinalFiltros)
                            .addComponent(ftfDataFinalFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jpCabecalhoPeriodoVendasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbFormasPgFiltro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbFormaPgFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(61, 61, 61)
                        .addGroup(jpCabecalhoPeriodoVendasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbAndamentoFiltro)
                            .addComponent(cbAndamentoFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btPesquisarFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(23, 23, 23))
        );
        jpCabecalhoPeriodoVendasLayout.setVerticalGroup(
            jpCabecalhoPeriodoVendasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpCabecalhoPeriodoVendasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbPeriodoVendas)
                .addGap(18, 18, 18)
                .addGroup(jpCabecalhoPeriodoVendasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbDataFinalFiltros)
                    .addComponent(lbDataInicialFiltro)
                    .addComponent(lbFormasPgFiltro)
                    .addComponent(lbAndamentoFiltro))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpCabecalhoPeriodoVendasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ftfDataInicialFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ftfDataFinalFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbFormaPgFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbAndamentoFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                .addComponent(btPesquisarFiltro)
                .addGap(34, 34, 34))
        );

        jpTabVendas.setBackground(new java.awt.Color(10, 4, 138));

        tbVendasFiltro.setBackground(new java.awt.Color(255, 255, 255));
        tbVendasFiltro.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tbVendasFiltro.setForeground(new java.awt.Color(0, 0, 0));
        tbVendasFiltro.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Código", "Produto", "Quantidade", "Valor Unitário", "Subtotal"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane8.setViewportView(tbVendasFiltro);

        lbVendasFiltros.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lbVendasFiltros.setForeground(new java.awt.Color(255, 255, 255));
        lbVendasFiltros.setText("Vendas cadastradas");

        javax.swing.GroupLayout jpTabVendasLayout = new javax.swing.GroupLayout(jpTabVendas);
        jpTabVendas.setLayout(jpTabVendasLayout);
        jpTabVendasLayout.setHorizontalGroup(
            jpTabVendasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpTabVendasLayout.createSequentialGroup()
                .addGroup(jpTabVendasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpTabVendasLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpTabVendasLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lbVendasFiltros)))
                .addContainerGap(70, Short.MAX_VALUE))
        );
        jpTabVendasLayout.setVerticalGroup(
            jpTabVendasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpTabVendasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbVendasFiltros)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );

        javax.swing.GroupLayout jpFiltrosLayout = new javax.swing.GroupLayout(jpFiltros);
        jpFiltros.setLayout(jpFiltrosLayout);
        jpFiltrosLayout.setHorizontalGroup(
            jpFiltrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpFiltrosLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(jpFiltrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jpCabecalhoPeriodoVendas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jpTabVendas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(42, 42, 42))
        );
        jpFiltrosLayout.setVerticalGroup(
            jpFiltrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpFiltrosLayout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addComponent(jpCabecalhoPeriodoVendas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addComponent(jpTabVendas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        tpInicio.addTab("Filtros por Período", jpFiltros);

        jpOperador.setBackground(new java.awt.Color(255, 255, 255));

        jpCabecalhoOperador.setBackground(new java.awt.Color(255, 255, 255));
        jpCabecalhoOperador.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 204), 1, true));

        cbOperador.setBackground(new java.awt.Color(255, 255, 255));
        cbOperador.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cbOperador.setForeground(new java.awt.Color(0, 0, 0));
        cbOperador.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos os operadores cadastrados" }));
        cbOperador.addActionListener(this::cbOperadorActionPerformed);

        lbDadosOperador.setBackground(new java.awt.Color(255, 255, 255));
        lbDadosOperador.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lbDadosOperador.setForeground(new java.awt.Color(0, 0, 0));
        lbDadosOperador.setText("Dados do operador");

        lbNomeOperador.setBackground(new java.awt.Color(255, 255, 255));
        lbNomeOperador.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lbNomeOperador.setForeground(new java.awt.Color(0, 0, 0));
        lbNomeOperador.setText("Nome");

        tfNomeOperador.setBackground(new java.awt.Color(255, 255, 255));
        tfNomeOperador.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tfNomeOperador.setForeground(new java.awt.Color(0, 0, 0));

        tfLoginOperador.setBackground(new java.awt.Color(255, 255, 255));
        tfLoginOperador.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tfLoginOperador.setForeground(new java.awt.Color(0, 0, 0));

        lbLoginOperador.setBackground(new java.awt.Color(255, 255, 255));
        lbLoginOperador.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lbLoginOperador.setForeground(new java.awt.Color(0, 0, 0));
        lbLoginOperador.setText("Login");

        lbNivelAcessoOperador.setBackground(new java.awt.Color(255, 255, 255));
        lbNivelAcessoOperador.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lbNivelAcessoOperador.setForeground(new java.awt.Color(0, 0, 0));
        lbNivelAcessoOperador.setText("Nível de acesso");

        tfQuantVendasOperador.setBackground(new java.awt.Color(255, 255, 255));
        tfQuantVendasOperador.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tfQuantVendasOperador.setForeground(new java.awt.Color(0, 0, 0));

        lbQuantVendasOperador.setBackground(new java.awt.Color(255, 255, 255));
        lbQuantVendasOperador.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lbQuantVendasOperador.setForeground(new java.awt.Color(0, 0, 0));
        lbQuantVendasOperador.setText("Quantidade de Vendas");

        cbNivelAcessoOperador.setBackground(new java.awt.Color(255, 255, 255));
        cbNivelAcessoOperador.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cbNivelAcessoOperador.setForeground(new java.awt.Color(0, 0, 0));
        cbNivelAcessoOperador.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Administrador", "Gerente", "Supervisor", "Operador", "Caixa" }));
        cbNivelAcessoOperador.addActionListener(this::cbNivelAcessoOperadorActionPerformed);

        lbVendasRealizadasOperador.setBackground(new java.awt.Color(255, 255, 255));
        lbVendasRealizadasOperador.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lbVendasRealizadasOperador.setForeground(new java.awt.Color(0, 0, 0));
        lbVendasRealizadasOperador.setText("Vendas realizadas pelo operador");

        lbValorTotalOperador.setBackground(new java.awt.Color(255, 255, 255));
        lbValorTotalOperador.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lbValorTotalOperador.setForeground(new java.awt.Color(0, 0, 0));
        lbValorTotalOperador.setText("Valor total");

        tfValorTotalOperador.setBackground(new java.awt.Color(255, 255, 255));
        tfValorTotalOperador.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tfValorTotalOperador.setForeground(new java.awt.Color(0, 0, 0));

        lbOperador.setBackground(new java.awt.Color(0, 0, 0));
        lbOperador.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lbOperador.setForeground(new java.awt.Color(0, 0, 0));
        lbOperador.setText("Operador");

        btPesquisarOperador.setBackground(new java.awt.Color(10, 4, 138));
        btPesquisarOperador.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btPesquisarOperador.setForeground(new java.awt.Color(255, 255, 255));
        btPesquisarOperador.setText("Pesquisar");

        javax.swing.GroupLayout jpCabecalhoOperadorLayout = new javax.swing.GroupLayout(jpCabecalhoOperador);
        jpCabecalhoOperador.setLayout(jpCabecalhoOperadorLayout);
        jpCabecalhoOperadorLayout.setHorizontalGroup(
            jpCabecalhoOperadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpCabecalhoOperadorLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpCabecalhoOperadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpCabecalhoOperadorLayout.createSequentialGroup()
                        .addComponent(lbOperador)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jpCabecalhoOperadorLayout.createSequentialGroup()
                        .addGroup(jpCabecalhoOperadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpCabecalhoOperadorLayout.createSequentialGroup()
                                .addGroup(jpCabecalhoOperadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(tfNomeOperador, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
                                    .addComponent(tfLoginOperador))
                                .addGap(254, 254, 254))
                            .addGroup(jpCabecalhoOperadorLayout.createSequentialGroup()
                                .addGroup(jpCabecalhoOperadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbDadosOperador)
                                    .addComponent(lbNomeOperador)
                                    .addComponent(lbLoginOperador)
                                    .addGroup(jpCabecalhoOperadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(cbNivelAcessoOperador, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lbNivelAcessoOperador, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(cbOperador, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(jpCabecalhoOperadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbValorTotalOperador)
                            .addGroup(jpCabecalhoOperadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(btPesquisarOperador)
                                .addGroup(jpCabecalhoOperadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(tfValorTotalOperador)
                                    .addComponent(lbQuantVendasOperador, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tfQuantVendasOperador)
                                    .addComponent(lbVendasRealizadasOperador, javax.swing.GroupLayout.Alignment.LEADING))))
                        .addGap(74, 74, 74))))
        );
        jpCabecalhoOperadorLayout.setVerticalGroup(
            jpCabecalhoOperadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpCabecalhoOperadorLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(lbOperador)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpCabecalhoOperadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jpCabecalhoOperadorLayout.createSequentialGroup()
                        .addComponent(cbOperador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbDadosOperador)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbNomeOperador)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfNomeOperador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbLoginOperador))
                    .addGroup(jpCabecalhoOperadorLayout.createSequentialGroup()
                        .addComponent(lbVendasRealizadasOperador)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbQuantVendasOperador)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfQuantVendasOperador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbValorTotalOperador)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfValorTotalOperador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfLoginOperador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(lbNivelAcessoOperador)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpCabecalhoOperadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbNivelAcessoOperador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btPesquisarOperador))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jpVendasOperador.setBackground(new java.awt.Color(10, 4, 138));

        lbVendasOperador.setBackground(new java.awt.Color(0, 0, 0));
        lbVendasOperador.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lbVendasOperador.setForeground(new java.awt.Color(255, 255, 255));
        lbVendasOperador.setText("Vendas cadastradas");

        tbVendasOperador.setBackground(new java.awt.Color(255, 255, 255));
        tbVendasOperador.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tbVendasOperador.setForeground(new java.awt.Color(0, 0, 0));
        tbVendasOperador.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "N° Venda", "Data/Hora", "Cliente", "Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane9.setViewportView(tbVendasOperador);

        javax.swing.GroupLayout jpVendasOperadorLayout = new javax.swing.GroupLayout(jpVendasOperador);
        jpVendasOperador.setLayout(jpVendasOperadorLayout);
        jpVendasOperadorLayout.setHorizontalGroup(
            jpVendasOperadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpVendasOperadorLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpVendasOperadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpVendasOperadorLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 762, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lbVendasOperador))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        jpVendasOperadorLayout.setVerticalGroup(
            jpVendasOperadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpVendasOperadorLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbVendasOperador)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jpOperadorLayout = new javax.swing.GroupLayout(jpOperador);
        jpOperador.setLayout(jpOperadorLayout);
        jpOperadorLayout.setHorizontalGroup(
            jpOperadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpOperadorLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jpCabecalhoOperador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jpOperadorLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jpVendasOperador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jpOperadorLayout.setVerticalGroup(
            jpOperadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpOperadorLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jpCabecalhoOperador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(jpVendasOperador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        tpInicio.addTab("Operador", jpOperador);

        jpProduto.setBackground(new java.awt.Color(255, 255, 255));

        jpCabecalhoProduto.setBackground(new java.awt.Color(255, 255, 255));
        jpCabecalhoProduto.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 204), 1, true));

        lbProduto.setBackground(new java.awt.Color(255, 255, 255));
        lbProduto.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lbProduto.setForeground(new java.awt.Color(0, 0, 0));
        lbProduto.setText("Produto");

        tfProduto.setBackground(new java.awt.Color(255, 255, 255));
        tfProduto.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tfProduto.setForeground(new java.awt.Color(0, 0, 0));
        tfProduto.setText("Digite o nome ou código do produto");

        lbDadosProduto.setBackground(new java.awt.Color(255, 255, 255));
        lbDadosProduto.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lbDadosProduto.setForeground(new java.awt.Color(0, 0, 0));
        lbDadosProduto.setText("Dados no produto");

        lbCodigoProduto.setForeground(new java.awt.Color(0, 0, 0));
        lbCodigoProduto.setText("Código");

        tfCodigoProduto.setBackground(new java.awt.Color(255, 255, 255));
        tfCodigoProduto.setForeground(new java.awt.Color(0, 0, 0));

        lbNomeProduto.setForeground(new java.awt.Color(0, 0, 0));
        lbNomeProduto.setText("Nome");

        tfNomeProduto.setBackground(new java.awt.Color(255, 255, 255));
        tfNomeProduto.setForeground(new java.awt.Color(0, 0, 0));

        lbCategoriaProduto.setForeground(new java.awt.Color(0, 0, 0));
        lbCategoriaProduto.setText("Categoria");

        lbValorUnitarioProduto.setForeground(new java.awt.Color(0, 0, 0));
        lbValorUnitarioProduto.setText("Valor unitário");

        tfValorUnitarioProduto.setBackground(new java.awt.Color(255, 255, 255));
        tfValorUnitarioProduto.setForeground(new java.awt.Color(0, 0, 0));

        tfCategoriaProduto.setBackground(new java.awt.Color(255, 255, 255));
        tfCategoriaProduto.setForeground(new java.awt.Color(0, 0, 0));

        lbEstoqueAtualProduto.setForeground(new java.awt.Color(0, 0, 0));
        lbEstoqueAtualProduto.setText("Estoque atual");

        tfEstoqueAtualProduto.setBackground(new java.awt.Color(255, 255, 255));
        tfEstoqueAtualProduto.setForeground(new java.awt.Color(0, 0, 0));

        lbVendasProduto.setBackground(new java.awt.Color(255, 255, 255));
        lbVendasProduto.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lbVendasProduto.setForeground(new java.awt.Color(0, 0, 0));
        lbVendasProduto.setText("Vendas do produto");

        lbQuantVendidaProduto.setForeground(new java.awt.Color(0, 0, 0));
        lbQuantVendidaProduto.setText("Quantidade vendida");

        lbValorTotalProduto.setForeground(new java.awt.Color(0, 0, 0));
        lbValorTotalProduto.setText("Valor total");

        tfQuantVendidaProduto.setBackground(new java.awt.Color(255, 255, 255));
        tfQuantVendidaProduto.setForeground(new java.awt.Color(0, 0, 0));

        tfValorTotalProduto.setBackground(new java.awt.Color(255, 255, 255));
        tfValorTotalProduto.setForeground(new java.awt.Color(0, 0, 0));

        btPesquisarProduto.setBackground(new java.awt.Color(10, 4, 138));
        btPesquisarProduto.setForeground(new java.awt.Color(255, 255, 255));
        btPesquisarProduto.setText("Pesquisar");

        javax.swing.GroupLayout jpCabecalhoProdutoLayout = new javax.swing.GroupLayout(jpCabecalhoProduto);
        jpCabecalhoProduto.setLayout(jpCabecalhoProdutoLayout);
        jpCabecalhoProdutoLayout.setHorizontalGroup(
            jpCabecalhoProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpCabecalhoProdutoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpCabecalhoProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbProduto)
                    .addGroup(jpCabecalhoProdutoLayout.createSequentialGroup()
                        .addGroup(jpCabecalhoProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jpCabecalhoProdutoLayout.createSequentialGroup()
                                .addComponent(lbCategoriaProduto)
                                .addGap(92, 92, 92))
                            .addGroup(jpCabecalhoProdutoLayout.createSequentialGroup()
                                .addComponent(tfCategoriaProduto)
                                .addGap(17, 17, 17)))
                        .addGroup(jpCabecalhoProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbValorUnitarioProduto)
                            .addComponent(tfValorUnitarioProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jpCabecalhoProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfEstoqueAtualProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbEstoqueAtualProduto)))
                    .addGroup(jpCabecalhoProdutoLayout.createSequentialGroup()
                        .addGroup(jpCabecalhoProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbDadosProduto)
                            .addGroup(jpCabecalhoProdutoLayout.createSequentialGroup()
                                .addGroup(jpCabecalhoProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbCodigoProduto)
                                    .addComponent(tfCodigoProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jpCabecalhoProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jpCabecalhoProdutoLayout.createSequentialGroup()
                                        .addGap(17, 17, 17)
                                        .addComponent(lbNomeProduto))
                                    .addGroup(jpCabecalhoProdutoLayout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(tfNomeProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(54, 54, 54)
                        .addGroup(jpCabecalhoProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbVendasProduto)
                            .addGroup(jpCabecalhoProdutoLayout.createSequentialGroup()
                                .addGroup(jpCabecalhoProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tfQuantVendidaProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbQuantVendidaProduto))
                                .addGap(18, 18, 18)
                                .addGroup(jpCabecalhoProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbValorTotalProduto)
                                    .addComponent(tfValorTotalProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jpCabecalhoProdutoLayout.createSequentialGroup()
                        .addComponent(tfProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 693, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btPesquisarProduto)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpCabecalhoProdutoLayout.setVerticalGroup(
            jpCabecalhoProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpCabecalhoProdutoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbProduto)
                .addGap(7, 7, 7)
                .addGroup(jpCabecalhoProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btPesquisarProduto))
                .addGap(18, 18, 18)
                .addGroup(jpCabecalhoProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jpCabecalhoProdutoLayout.createSequentialGroup()
                        .addGroup(jpCabecalhoProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbDadosProduto)
                            .addComponent(lbVendasProduto))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jpCabecalhoProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbCodigoProduto)
                            .addComponent(lbNomeProduto)
                            .addComponent(lbQuantVendidaProduto)
                            .addComponent(lbValorTotalProduto))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jpCabecalhoProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfCodigoProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfNomeProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfQuantVendidaProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfValorTotalProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jpCabecalhoProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbValorUnitarioProduto, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lbCategoriaProduto))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpCabecalhoProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfCategoriaProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfValorUnitarioProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jpCabecalhoProdutoLayout.createSequentialGroup()
                        .addComponent(lbEstoqueAtualProduto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfEstoqueAtualProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(42, Short.MAX_VALUE))
        );

        jpVendasCadastradasProduto.setBackground(new java.awt.Color(10, 4, 138));

        lbVendasCadastradasProduto.setBackground(new java.awt.Color(255, 255, 255));
        lbVendasCadastradasProduto.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lbVendasCadastradasProduto.setForeground(new java.awt.Color(255, 255, 255));
        lbVendasCadastradasProduto.setText("Vendas cadastradas");

        tbVendasCadastradasProduto.setBackground(new java.awt.Color(255, 255, 255));
        tbVendasCadastradasProduto.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tbVendasCadastradasProduto.setForeground(new java.awt.Color(0, 0, 0));
        tbVendasCadastradasProduto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "N° Venda", "Data/Hora", "Operador", "Title 4"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane6.setViewportView(tbVendasCadastradasProduto);

        javax.swing.GroupLayout jpVendasCadastradasProdutoLayout = new javax.swing.GroupLayout(jpVendasCadastradasProduto);
        jpVendasCadastradasProduto.setLayout(jpVendasCadastradasProdutoLayout);
        jpVendasCadastradasProdutoLayout.setHorizontalGroup(
            jpVendasCadastradasProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpVendasCadastradasProdutoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpVendasCadastradasProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpVendasCadastradasProdutoLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 784, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lbVendasCadastradasProduto))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        jpVendasCadastradasProdutoLayout.setVerticalGroup(
            jpVendasCadastradasProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpVendasCadastradasProdutoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbVendasCadastradasProduto)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jpProdutoLayout = new javax.swing.GroupLayout(jpProduto);
        jpProduto.setLayout(jpProdutoLayout);
        jpProdutoLayout.setHorizontalGroup(
            jpProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpProdutoLayout.createSequentialGroup()
                .addContainerGap(35, Short.MAX_VALUE)
                .addGroup(jpProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jpCabecalhoProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jpVendasCadastradasProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25))
        );
        jpProdutoLayout.setVerticalGroup(
            jpProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpProdutoLayout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jpCabecalhoProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jpVendasCadastradasProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        tpInicio.addTab("Produto", jpProduto);

        jpCategoria.setBackground(new java.awt.Color(255, 255, 255));

        jpFiltrosPagamento.setBackground(new java.awt.Color(255, 255, 255));
        jpFiltrosPagamento.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 51, 153), 1, true));

        lbFiltrosPagamento.setBackground(new java.awt.Color(255, 255, 255));
        lbFiltrosPagamento.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lbFiltrosPagamento.setForeground(new java.awt.Color(0, 0, 0));
        lbFiltrosPagamento.setText("Filtros");

        cbCategoriaPagamento.setBackground(new java.awt.Color(255, 255, 255));
        cbCategoriaPagamento.setForeground(new java.awt.Color(0, 0, 0));
        cbCategoriaPagamento.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mercenaria", "Bebidas", "Hortifruti", "Açougue", "Padaria", "Laticínios", "Higiene Pessoal", "Limpeza", "Congelados" }));

        ftfDataInicialPagamento.setBackground(new java.awt.Color(255, 255, 255));
        ftfDataInicialPagamento.setForeground(new java.awt.Color(0, 0, 0));

        cbFormaPagamento.setBackground(new java.awt.Color(255, 255, 255));
        cbFormaPagamento.setForeground(new java.awt.Color(0, 0, 0));
        cbFormaPagamento.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Dinheiro", "PIX", "Cartão de Crédito", "Cartão de Débito", "Vale-Alimentação", "Vale-Refeição", "Boleto", "Crediário" }));

        ftfDataFinalPagamento.setBackground(new java.awt.Color(255, 255, 255));
        ftfDataFinalPagamento.setForeground(new java.awt.Color(0, 0, 0));

        lbCategoriaPagamento.setBackground(new java.awt.Color(255, 255, 255));
        lbCategoriaPagamento.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lbCategoriaPagamento.setForeground(new java.awt.Color(0, 0, 0));
        lbCategoriaPagamento.setText("Categoria");

        lbFormaPagamento.setBackground(new java.awt.Color(255, 255, 255));
        lbFormaPagamento.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lbFormaPagamento.setForeground(new java.awt.Color(0, 0, 0));
        lbFormaPagamento.setText("Forma de pagamento");

        lbDataInicialPagamento.setBackground(new java.awt.Color(255, 255, 255));
        lbDataInicialPagamento.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lbDataInicialPagamento.setForeground(new java.awt.Color(0, 0, 0));
        lbDataInicialPagamento.setText("Data inicial");

        lbDataFinalPagamento.setBackground(new java.awt.Color(255, 255, 255));
        lbDataFinalPagamento.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lbDataFinalPagamento.setForeground(new java.awt.Color(0, 0, 0));
        lbDataFinalPagamento.setText("Data final");

        btPesquisarPagamento.setBackground(new java.awt.Color(10, 4, 138));
        btPesquisarPagamento.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btPesquisarPagamento.setForeground(new java.awt.Color(255, 255, 255));
        btPesquisarPagamento.setText("Pesquisar");

        javax.swing.GroupLayout jpFiltrosPagamentoLayout = new javax.swing.GroupLayout(jpFiltrosPagamento);
        jpFiltrosPagamento.setLayout(jpFiltrosPagamentoLayout);
        jpFiltrosPagamentoLayout.setHorizontalGroup(
            jpFiltrosPagamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpFiltrosPagamentoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpFiltrosPagamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpFiltrosPagamentoLayout.createSequentialGroup()
                        .addComponent(lbFiltrosPagamento)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jpFiltrosPagamentoLayout.createSequentialGroup()
                        .addGroup(jpFiltrosPagamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpFiltrosPagamentoLayout.createSequentialGroup()
                                .addGroup(jpFiltrosPagamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbCategoriaPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbDataInicialPagamento))
                                .addGap(269, 269, 269))
                            .addGroup(jpFiltrosPagamentoLayout.createSequentialGroup()
                                .addComponent(cbCategoriaPagamento, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(134, 134, 134)))
                        .addGroup(jpFiltrosPagamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbDataFinalPagamento)
                            .addComponent(cbFormaPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbFormaPagamento))
                        .addGap(290, 290, 290))
                    .addGroup(jpFiltrosPagamentoLayout.createSequentialGroup()
                        .addComponent(ftfDataInicialPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(134, 134, 134)
                        .addComponent(ftfDataFinalPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpFiltrosPagamentoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btPesquisarPagamento)
                .addGap(53, 53, 53))
        );
        jpFiltrosPagamentoLayout.setVerticalGroup(
            jpFiltrosPagamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpFiltrosPagamentoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpFiltrosPagamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpFiltrosPagamentoLayout.createSequentialGroup()
                        .addComponent(lbFiltrosPagamento)
                        .addGap(38, 38, 38)
                        .addGroup(jpFiltrosPagamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbCategoriaPagamento)
                            .addComponent(lbFormaPagamento))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpFiltrosPagamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbCategoriaPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbFormaPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 89, Short.MAX_VALUE))
                    .addGroup(jpFiltrosPagamentoLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jpFiltrosPagamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbDataInicialPagamento)
                            .addComponent(lbDataFinalPagamento))
                        .addGap(5, 5, 5)
                        .addGroup(jpFiltrosPagamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ftfDataInicialPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ftfDataFinalPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(17, 17, 17)
                .addComponent(btPesquisarPagamento)
                .addGap(35, 35, 35))
        );

        jpVendasPagamento.setBackground(new java.awt.Color(10, 4, 138));
        jpVendasPagamento.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 51, 153), 1, true));

        lbVendasPagamento.setBackground(new java.awt.Color(255, 255, 255));
        lbVendasPagamento.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lbVendasPagamento.setForeground(new java.awt.Color(255, 255, 255));
        lbVendasPagamento.setText("Vendas por forma de pagamento");

        tbVendasPagamento.setBackground(new java.awt.Color(255, 255, 255));
        tbVendasPagamento.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tbVendasPagamento.setForeground(new java.awt.Color(0, 0, 0));
        tbVendasPagamento.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Forma de pagamento", "Quantidade de vendas", "Valor médio", "Valor total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane10.setViewportView(tbVendasPagamento);

        javax.swing.GroupLayout jpVendasPagamentoLayout = new javax.swing.GroupLayout(jpVendasPagamento);
        jpVendasPagamento.setLayout(jpVendasPagamentoLayout);
        jpVendasPagamentoLayout.setHorizontalGroup(
            jpVendasPagamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpVendasPagamentoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpVendasPagamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpVendasPagamentoLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jScrollPane10))
                    .addGroup(jpVendasPagamentoLayout.createSequentialGroup()
                        .addComponent(lbVendasPagamento)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jpVendasPagamentoLayout.setVerticalGroup(
            jpVendasPagamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpVendasPagamentoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbVendasPagamento)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jpCategoriaLayout = new javax.swing.GroupLayout(jpCategoria);
        jpCategoria.setLayout(jpCategoriaLayout);
        jpCategoriaLayout.setHorizontalGroup(
            jpCategoriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpCategoriaLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jpCategoriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jpVendasPagamento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jpFiltrosPagamento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 19, Short.MAX_VALUE))
        );
        jpCategoriaLayout.setVerticalGroup(
            jpCategoriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpCategoriaLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jpFiltrosPagamento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jpVendasPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38))
        );

        tpInicio.addTab("Categoria e Pagamento", jpCategoria);

        jpCancelamento.setBackground(new java.awt.Color(255, 255, 255));

        jpCabecalhoDadosVenda.setBackground(new java.awt.Color(255, 255, 255));
        jpCabecalhoDadosVenda.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 51, 153), 1, true));
        jpCabecalhoDadosVenda.setForeground(new java.awt.Color(255, 255, 255));

        lbDadosVendaCancelamento.setBackground(new java.awt.Color(255, 255, 255));
        lbDadosVendaCancelamento.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lbDadosVendaCancelamento.setForeground(new java.awt.Color(0, 0, 0));
        lbDadosVendaCancelamento.setText("Dados da Venda");

        lbDataHoraCancelamento.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lbDataHoraCancelamento.setForeground(new java.awt.Color(0, 0, 0));
        lbDataHoraCancelamento.setText("Data/Hora:");

        lbOperadorCancelamento.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lbOperadorCancelamento.setForeground(new java.awt.Color(0, 0, 0));
        lbOperadorCancelamento.setText("Operador:");

        lbFormaPagamentoCancelamento.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lbFormaPagamentoCancelamento.setForeground(new java.awt.Color(0, 0, 0));
        lbFormaPagamentoCancelamento.setText("Forma de Pagamento:");

        btPesquisarCancelamento.setBackground(new java.awt.Color(10, 4, 138));
        btPesquisarCancelamento.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btPesquisarCancelamento.setForeground(new java.awt.Color(255, 255, 255));
        btPesquisarCancelamento.setText("Pesquisar");

        tfFormaPagamentoCancelamento.setBackground(new java.awt.Color(255, 255, 255));
        tfFormaPagamentoCancelamento.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tfFormaPagamentoCancelamento.setForeground(new java.awt.Color(0, 0, 0));

        tfOperadorCancelamento.setBackground(new java.awt.Color(255, 255, 255));
        tfOperadorCancelamento.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tfOperadorCancelamento.setForeground(new java.awt.Color(0, 0, 0));

        tfVendaCancelamento.setBackground(new java.awt.Color(255, 255, 255));
        tfVendaCancelamento.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tfVendaCancelamento.setForeground(new java.awt.Color(0, 0, 0));

        lbNVendaCancelamento.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lbNVendaCancelamento.setForeground(new java.awt.Color(0, 0, 0));
        lbNVendaCancelamento.setText("N° da Venda:");

        tfDataHoraCancelamento.setBackground(new java.awt.Color(255, 255, 255));
        tfDataHoraCancelamento.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tfDataHoraCancelamento.setForeground(new java.awt.Color(0, 0, 0));

        jpTotalVendaCancelamento.setBackground(new java.awt.Color(10, 4, 138));

        lbTotalVendaCancelamento.setBackground(new java.awt.Color(255, 255, 255));
        lbTotalVendaCancelamento.setForeground(new java.awt.Color(255, 255, 255));
        lbTotalVendaCancelamento.setText("Total da Venda:");

        tfTotalVendaCancelamento.setBackground(new java.awt.Color(10, 4, 138));
        tfTotalVendaCancelamento.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        tfTotalVendaCancelamento.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jpTotalVendaCancelamentoLayout = new javax.swing.GroupLayout(jpTotalVendaCancelamento);
        jpTotalVendaCancelamento.setLayout(jpTotalVendaCancelamentoLayout);
        jpTotalVendaCancelamentoLayout.setHorizontalGroup(
            jpTotalVendaCancelamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpTotalVendaCancelamentoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbTotalVendaCancelamento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(40, 40, 40))
            .addGroup(jpTotalVendaCancelamentoLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(tfTotalVendaCancelamento, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpTotalVendaCancelamentoLayout.setVerticalGroup(
            jpTotalVendaCancelamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpTotalVendaCancelamentoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbTotalVendaCancelamento)
                .addGap(18, 18, 18)
                .addComponent(tfTotalVendaCancelamento, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jpCabecalhoDadosVendaLayout = new javax.swing.GroupLayout(jpCabecalhoDadosVenda);
        jpCabecalhoDadosVenda.setLayout(jpCabecalhoDadosVendaLayout);
        jpCabecalhoDadosVendaLayout.setHorizontalGroup(
            jpCabecalhoDadosVendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpCabecalhoDadosVendaLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jpCabecalhoDadosVendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpCabecalhoDadosVendaLayout.createSequentialGroup()
                        .addGroup(jpCabecalhoDadosVendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbDadosVendaCancelamento)
                            .addGroup(jpCabecalhoDadosVendaLayout.createSequentialGroup()
                                .addGroup(jpCabecalhoDadosVendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbFormaPagamentoCancelamento, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lbOperadorCancelamento, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbDataHoraCancelamento, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jpCabecalhoDadosVendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(tfOperadorCancelamento, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                                    .addComponent(tfFormaPagamentoCancelamento))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jpTotalVendaCancelamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(jpCabecalhoDadosVendaLayout.createSequentialGroup()
                        .addComponent(lbNVendaCancelamento, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(63, 63, 63)
                        .addGroup(jpCabecalhoDadosVendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(tfDataHoraCancelamento, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                            .addComponent(tfVendaCancelamento))
                        .addGap(29, 29, 29)
                        .addComponent(btPesquisarCancelamento)
                        .addGap(262, 262, 262))))
        );
        jpCabecalhoDadosVendaLayout.setVerticalGroup(
            jpCabecalhoDadosVendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpCabecalhoDadosVendaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpCabecalhoDadosVendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jpCabecalhoDadosVendaLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jpTotalVendaCancelamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpCabecalhoDadosVendaLayout.createSequentialGroup()
                        .addComponent(lbDadosVendaCancelamento)
                        .addGap(12, 12, 12)
                        .addGroup(jpCabecalhoDadosVendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbNVendaCancelamento)
                            .addComponent(tfVendaCancelamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btPesquisarCancelamento))
                        .addGap(18, 18, 18)
                        .addGroup(jpCabecalhoDadosVendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbDataHoraCancelamento)
                            .addComponent(tfDataHoraCancelamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jpCabecalhoDadosVendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbOperadorCancelamento)
                            .addComponent(tfOperadorCancelamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jpCabecalhoDadosVendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbFormaPagamentoCancelamento)
                            .addComponent(tfFormaPagamentoCancelamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 37, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jpItensVendaCancelamento.setBackground(new java.awt.Color(10, 4, 138));
        jpItensVendaCancelamento.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 51, 153), 1, true));
        jpItensVendaCancelamento.setForeground(new java.awt.Color(255, 255, 255));

        tbItensVendaCancelamento.setBackground(new java.awt.Color(255, 255, 255));
        tbItensVendaCancelamento.setForeground(new java.awt.Color(0, 0, 0));
        tbItensVendaCancelamento.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Código", "Produto", "Quantidade", "Valor Unitário", "Subtotal"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane5.setViewportView(tbItensVendaCancelamento);

        lbItensVendaCancelamento.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lbItensVendaCancelamento.setForeground(new java.awt.Color(255, 255, 255));
        lbItensVendaCancelamento.setText("Itens da Venda");

        javax.swing.GroupLayout jpItensVendaCancelamentoLayout = new javax.swing.GroupLayout(jpItensVendaCancelamento);
        jpItensVendaCancelamento.setLayout(jpItensVendaCancelamentoLayout);
        jpItensVendaCancelamentoLayout.setHorizontalGroup(
            jpItensVendaCancelamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpItensVendaCancelamentoLayout.createSequentialGroup()
                .addContainerGap(25, Short.MAX_VALUE)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 711, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
            .addGroup(jpItensVendaCancelamentoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbItensVendaCancelamento, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpItensVendaCancelamentoLayout.setVerticalGroup(
            jpItensVendaCancelamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpItensVendaCancelamentoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbItensVendaCancelamento)
                .addGap(21, 21, 21)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jpCancelamentoLayout = new javax.swing.GroupLayout(jpCancelamento);
        jpCancelamento.setLayout(jpCancelamentoLayout);
        jpCancelamentoLayout.setHorizontalGroup(
            jpCancelamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpCancelamentoLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jpCancelamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jpCabecalhoDadosVenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jpItensVendaCancelamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        jpCancelamentoLayout.setVerticalGroup(
            jpCancelamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpCancelamentoLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jpCabecalhoDadosVenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jpItensVendaCancelamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        tpInicio.addTab("Cancelamento", jpCancelamento);

        jpEstorno.setBackground(new java.awt.Color(255, 255, 255));

        jpDadosVendaEstorno.setBackground(new java.awt.Color(255, 255, 255));
        jpDadosVendaEstorno.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(10, 4, 138), 1, true));

        lbDadosVendaEstorno.setBackground(new java.awt.Color(255, 255, 255));
        lbDadosVendaEstorno.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lbDadosVendaEstorno.setForeground(new java.awt.Color(0, 0, 0));
        lbDadosVendaEstorno.setText("Dados da Venda");

        lbDataHoraEstorno.setBackground(new java.awt.Color(255, 255, 255));
        lbDataHoraEstorno.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lbDataHoraEstorno.setForeground(new java.awt.Color(0, 0, 0));
        lbDataHoraEstorno.setText("Data/Hora:");

        tfNVendaEstorno.setBackground(new java.awt.Color(255, 255, 255));
        tfNVendaEstorno.setForeground(new java.awt.Color(0, 0, 0));

        lbOperadorEstorno.setBackground(new java.awt.Color(255, 255, 255));
        lbOperadorEstorno.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lbOperadorEstorno.setForeground(new java.awt.Color(0, 0, 0));
        lbOperadorEstorno.setText("Operador:");

        lbFormaPagamentoEstorno.setBackground(new java.awt.Color(255, 255, 255));
        lbFormaPagamentoEstorno.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lbFormaPagamentoEstorno.setForeground(new java.awt.Color(0, 0, 0));
        lbFormaPagamentoEstorno.setText("Forma de Pagamento:");

        lbNVendaEstorno.setBackground(new java.awt.Color(255, 255, 255));
        lbNVendaEstorno.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lbNVendaEstorno.setForeground(new java.awt.Color(0, 0, 0));
        lbNVendaEstorno.setText("N° da Venda:");

        tfFormaPagamentoEstorno.setBackground(new java.awt.Color(255, 255, 255));
        tfFormaPagamentoEstorno.setForeground(new java.awt.Color(0, 0, 0));

        tfOperadorEstorno.setBackground(new java.awt.Color(255, 255, 255));
        tfOperadorEstorno.setForeground(new java.awt.Color(0, 0, 0));

        tfDataHoraEstorno.setBackground(new java.awt.Color(255, 255, 255));
        tfDataHoraEstorno.setForeground(new java.awt.Color(0, 0, 0));

        jpTotalVendaEstorno.setBackground(new java.awt.Color(10, 4, 138));

        lbTotalVendaEstorno.setBackground(new java.awt.Color(255, 255, 255));
        lbTotalVendaEstorno.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lbTotalVendaEstorno.setForeground(new java.awt.Color(255, 255, 255));
        lbTotalVendaEstorno.setText("Total da Venda:");

        tfTotalVendaEstorno.setBackground(new java.awt.Color(10, 4, 138));
        tfTotalVendaEstorno.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        tfTotalVendaEstorno.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jpTotalVendaEstornoLayout = new javax.swing.GroupLayout(jpTotalVendaEstorno);
        jpTotalVendaEstorno.setLayout(jpTotalVendaEstornoLayout);
        jpTotalVendaEstornoLayout.setHorizontalGroup(
            jpTotalVendaEstornoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpTotalVendaEstornoLayout.createSequentialGroup()
                .addGroup(jpTotalVendaEstornoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpTotalVendaEstornoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lbTotalVendaEstorno))
                    .addGroup(jpTotalVendaEstornoLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(tfTotalVendaEstorno, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        jpTotalVendaEstornoLayout.setVerticalGroup(
            jpTotalVendaEstornoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpTotalVendaEstornoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbTotalVendaEstorno)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tfTotalVendaEstorno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        btPesquisarEstorno.setBackground(new java.awt.Color(10, 4, 138));
        btPesquisarEstorno.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btPesquisarEstorno.setForeground(new java.awt.Color(255, 255, 255));
        btPesquisarEstorno.setText("Pesquisar");

        javax.swing.GroupLayout jpDadosVendaEstornoLayout = new javax.swing.GroupLayout(jpDadosVendaEstorno);
        jpDadosVendaEstorno.setLayout(jpDadosVendaEstornoLayout);
        jpDadosVendaEstornoLayout.setHorizontalGroup(
            jpDadosVendaEstornoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpDadosVendaEstornoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpDadosVendaEstornoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpDadosVendaEstornoLayout.createSequentialGroup()
                        .addComponent(lbDadosVendaEstorno)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jpDadosVendaEstornoLayout.createSequentialGroup()
                        .addGroup(jpDadosVendaEstornoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpDadosVendaEstornoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jpDadosVendaEstornoLayout.createSequentialGroup()
                                    .addComponent(lbOperadorEstorno)
                                    .addGap(68, 68, 68))
                                .addComponent(lbFormaPagamentoEstorno, javax.swing.GroupLayout.Alignment.TRAILING))
                            .addComponent(lbDataHoraEstorno)
                            .addComponent(lbNVendaEstorno))
                        .addGap(32, 32, 32)
                        .addGroup(jpDadosVendaEstornoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpDadosVendaEstornoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(tfOperadorEstorno, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                                .addComponent(tfFormaPagamentoEstorno)
                                .addComponent(tfNVendaEstorno))
                            .addComponent(tfDataHoraEstorno, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 177, Short.MAX_VALUE)
                        .addGroup(jpDadosVendaEstornoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jpTotalVendaEstorno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btPesquisarEstorno))
                        .addGap(21, 21, 21))))
        );
        jpDadosVendaEstornoLayout.setVerticalGroup(
            jpDadosVendaEstornoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpDadosVendaEstornoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbDadosVendaEstorno)
                .addGap(18, 18, 18)
                .addGroup(jpDadosVendaEstornoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpDadosVendaEstornoLayout.createSequentialGroup()
                        .addComponent(lbNVendaEstorno)
                        .addGap(18, 18, 18)
                        .addComponent(lbDataHoraEstorno)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbOperadorEstorno)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbFormaPagamentoEstorno))
                    .addGroup(jpDadosVendaEstornoLayout.createSequentialGroup()
                        .addComponent(tfNVendaEstorno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfDataHoraEstorno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(tfOperadorEstorno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfFormaPagamentoEstorno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(18, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpDadosVendaEstornoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btPesquisarEstorno)
                .addGap(18, 18, 18)
                .addComponent(jpTotalVendaEstorno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jpItensVendaEstorno.setBackground(new java.awt.Color(10, 4, 138));
        jpItensVendaEstorno.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(10, 4, 138), 1, true));

        lbItensVendaEstorno.setBackground(new java.awt.Color(255, 255, 255));
        lbItensVendaEstorno.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lbItensVendaEstorno.setForeground(new java.awt.Color(255, 255, 255));
        lbItensVendaEstorno.setText("Itens da Venda");

        tbItensVendaEstorno.setAutoCreateColumnsFromModel(false);
        tbItensVendaEstorno.setAutoCreateRowSorter(true);
        tbItensVendaEstorno.setBackground(new java.awt.Color(255, 255, 255));
        tbItensVendaEstorno.setForeground(new java.awt.Color(0, 0, 0));
        tbItensVendaEstorno.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Código", "Produto", "Quantidade", "Valor Unitário", "Subtotal"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane13.setViewportView(tbItensVendaEstorno);

        javax.swing.GroupLayout jpItensVendaEstornoLayout = new javax.swing.GroupLayout(jpItensVendaEstorno);
        jpItensVendaEstorno.setLayout(jpItensVendaEstornoLayout);
        jpItensVendaEstornoLayout.setHorizontalGroup(
            jpItensVendaEstornoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpItensVendaEstornoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbItensVendaEstorno)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpItensVendaEstornoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 819, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34))
        );
        jpItensVendaEstornoLayout.setVerticalGroup(
            jpItensVendaEstornoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpItensVendaEstornoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbItensVendaEstorno)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(9, Short.MAX_VALUE))
        );

        jpDadosEstorno.setBackground(new java.awt.Color(255, 255, 255));
        jpDadosEstorno.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(10, 4, 138), 1, true));

        lbDadosEstorno.setBackground(new java.awt.Color(255, 255, 255));
        lbDadosEstorno.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lbDadosEstorno.setForeground(new java.awt.Color(0, 0, 0));
        lbDadosEstorno.setText("Dados do Estorno");

        lbMotivoEstorno.setBackground(new java.awt.Color(255, 255, 255));
        lbMotivoEstorno.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lbMotivoEstorno.setForeground(new java.awt.Color(0, 0, 0));
        lbMotivoEstorno.setText("Motivo:");

        lbObservacaoEstorno.setBackground(new java.awt.Color(255, 255, 255));
        lbObservacaoEstorno.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lbObservacaoEstorno.setForeground(new java.awt.Color(0, 0, 0));
        lbObservacaoEstorno.setText("Observação:");

        cbMotivoEstorno.setBackground(new java.awt.Color(255, 255, 255));
        cbMotivoEstorno.setForeground(new java.awt.Color(0, 0, 0));
        cbMotivoEstorno.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Desistência da compra", "Cobrança duplicada", "Valor cobrado incorretamente", "Produto cancelado", "Erro na forma de pagamento", "Problema da transação", "Outros" }));

        tfObservacaoEstorno.setBackground(new java.awt.Color(255, 255, 255));
        tfObservacaoEstorno.setForeground(new java.awt.Color(0, 0, 0));

        lvValorEstorno.setBackground(new java.awt.Color(255, 255, 255));
        lvValorEstorno.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lvValorEstorno.setForeground(new java.awt.Color(0, 0, 0));
        lvValorEstorno.setText("Valor a Estornar:");

        tfValorEstorno.setForeground(new java.awt.Color(0, 0, 0));

        btConfirmarEstorno.setBackground(new java.awt.Color(10, 4, 138));
        btConfirmarEstorno.setForeground(new java.awt.Color(255, 255, 255));
        btConfirmarEstorno.setText("Confirmar Estorno");

        javax.swing.GroupLayout jpDadosEstornoLayout = new javax.swing.GroupLayout(jpDadosEstorno);
        jpDadosEstorno.setLayout(jpDadosEstornoLayout);
        jpDadosEstornoLayout.setHorizontalGroup(
            jpDadosEstornoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpDadosEstornoLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jpDadosEstornoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpDadosEstornoLayout.createSequentialGroup()
                        .addComponent(lbDadosEstorno, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(72, 72, 72))
                    .addGroup(jpDadosEstornoLayout.createSequentialGroup()
                        .addGroup(jpDadosEstornoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbMotivoEstorno)
                            .addComponent(lbObservacaoEstorno))
                        .addGap(72, 72, 72)
                        .addGroup(jpDadosEstornoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tfObservacaoEstorno)
                            .addComponent(cbMotivoEstorno, 0, 362, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 78, Short.MAX_VALUE)
                        .addGroup(jpDadosEstornoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpDadosEstornoLayout.createSequentialGroup()
                                .addComponent(lvValorEstorno, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tfValorEstorno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(72, 72, 72))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpDadosEstornoLayout.createSequentialGroup()
                                .addComponent(btConfirmarEstorno)
                                .addGap(17, 17, 17))))))
        );
        jpDadosEstornoLayout.setVerticalGroup(
            jpDadosEstornoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpDadosEstornoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbDadosEstorno)
                .addGroup(jpDadosEstornoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpDadosEstornoLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jpDadosEstornoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbMotivoEstorno)
                            .addComponent(cbMotivoEstorno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lvValorEstorno)
                            .addComponent(tfValorEstorno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jpDadosEstornoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpDadosEstornoLayout.createSequentialGroup()
                                .addComponent(lbObservacaoEstorno)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(tfObservacaoEstorno, javax.swing.GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpDadosEstornoLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btConfirmarEstorno)
                        .addGap(23, 23, 23))))
        );

        javax.swing.GroupLayout jpEstornoLayout = new javax.swing.GroupLayout(jpEstorno);
        jpEstorno.setLayout(jpEstornoLayout);
        jpEstornoLayout.setHorizontalGroup(
            jpEstornoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpEstornoLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jpEstornoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jpDadosEstorno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jpItensVendaEstorno, javax.swing.GroupLayout.PREFERRED_SIZE, 839, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jpDadosVendaEstorno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(10, Short.MAX_VALUE))
        );
        jpEstornoLayout.setVerticalGroup(
            jpEstornoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpEstornoLayout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(jpDadosVendaEstorno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jpItensVendaEstorno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jpDadosEstorno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(34, Short.MAX_VALUE))
        );

        tpInicio.addTab("Estorno", jpEstorno);

        jpComprovante.setBackground(new java.awt.Color(255, 255, 255));

        jPanel15.setBackground(new java.awt.Color(255, 255, 255));
        jPanel15.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(10, 4, 138), 1, true));

        lbDadosVendaComprovante.setBackground(new java.awt.Color(255, 255, 255));
        lbDadosVendaComprovante.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lbDadosVendaComprovante.setForeground(new java.awt.Color(0, 0, 0));
        lbDadosVendaComprovante.setText("Dados da Venda");

        lbNVendaComprovante.setBackground(new java.awt.Color(255, 255, 255));
        lbNVendaComprovante.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lbNVendaComprovante.setForeground(new java.awt.Color(0, 0, 0));
        lbNVendaComprovante.setText("N° da Venda:");

        tfNVendaComprovante.setBackground(new java.awt.Color(255, 255, 255));
        tfNVendaComprovante.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tfNVendaComprovante.setForeground(new java.awt.Color(0, 0, 0));

        btPesquisarComprovante.setBackground(new java.awt.Color(10, 4, 138));
        btPesquisarComprovante.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btPesquisarComprovante.setForeground(new java.awt.Color(255, 255, 255));
        btPesquisarComprovante.setText("Pesquisar");

        lbFormaPagamentoComprovante.setBackground(new java.awt.Color(255, 255, 255));
        lbFormaPagamentoComprovante.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lbFormaPagamentoComprovante.setForeground(new java.awt.Color(0, 0, 0));
        lbFormaPagamentoComprovante.setText("Forma de Pagamento:");

        lbOperadorComprovante.setBackground(new java.awt.Color(255, 255, 255));
        lbOperadorComprovante.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lbOperadorComprovante.setForeground(new java.awt.Color(0, 0, 0));
        lbOperadorComprovante.setText("Operador:");

        lbDataHoraComprovante.setBackground(new java.awt.Color(255, 255, 255));
        lbDataHoraComprovante.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lbDataHoraComprovante.setForeground(new java.awt.Color(0, 0, 0));
        lbDataHoraComprovante.setText("Data/Hora:");

        tfDataHoraComprovante.setBackground(new java.awt.Color(255, 255, 255));
        tfDataHoraComprovante.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tfDataHoraComprovante.setForeground(new java.awt.Color(0, 0, 0));

        tfOperadorComprovante.setBackground(new java.awt.Color(255, 255, 255));
        tfOperadorComprovante.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tfOperadorComprovante.setForeground(new java.awt.Color(0, 0, 0));

        tfFormaPagamentoComprovante.setBackground(new java.awt.Color(255, 255, 255));
        tfFormaPagamentoComprovante.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tfFormaPagamentoComprovante.setForeground(new java.awt.Color(0, 0, 0));

        jpTotalVendasComprovante.setBackground(new java.awt.Color(10, 4, 138));

        lbTotalVendaComprovante.setBackground(new java.awt.Color(255, 255, 255));
        lbTotalVendaComprovante.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lbTotalVendaComprovante.setForeground(new java.awt.Color(255, 255, 255));
        lbTotalVendaComprovante.setText("Total da Venda");

        tfTotalVendaComprovante.setBackground(new java.awt.Color(10, 4, 138));
        tfTotalVendaComprovante.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        javax.swing.GroupLayout jpTotalVendasComprovanteLayout = new javax.swing.GroupLayout(jpTotalVendasComprovante);
        jpTotalVendasComprovante.setLayout(jpTotalVendasComprovanteLayout);
        jpTotalVendasComprovanteLayout.setHorizontalGroup(
            jpTotalVendasComprovanteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpTotalVendasComprovanteLayout.createSequentialGroup()
                .addGroup(jpTotalVendasComprovanteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpTotalVendasComprovanteLayout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(tfTotalVendaComprovante, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpTotalVendasComprovanteLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lbTotalVendaComprovante, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jpTotalVendasComprovanteLayout.setVerticalGroup(
            jpTotalVendasComprovanteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpTotalVendasComprovanteLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbTotalVendaComprovante)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tfTotalVendaComprovante, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lbDadosVendaComprovante))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel15Layout.createSequentialGroup()
                                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbDataHoraComprovante)
                                    .addComponent(lbOperadorComprovante)
                                    .addComponent(lbFormaPagamentoComprovante))
                                .addGap(30, 30, 30)
                                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(tfOperadorComprovante, javax.swing.GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
                                    .addComponent(tfDataHoraComprovante)
                                    .addComponent(tfFormaPagamentoComprovante))
                                .addGap(228, 228, 228)
                                .addComponent(jpTotalVendasComprovante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel15Layout.createSequentialGroup()
                                .addComponent(lbNVendaComprovante)
                                .addGap(18, 18, 18)
                                .addComponent(tfNVendaComprovante, javax.swing.GroupLayout.DEFAULT_SIZE, 366, Short.MAX_VALUE)
                                .addGap(27, 27, 27)
                                .addComponent(btPesquisarComprovante)
                                .addGap(179, 179, 179)))))
                .addContainerGap())
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbDadosVendaComprovante)
                .addGap(18, 18, 18)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbNVendaComprovante)
                    .addComponent(tfNVendaComprovante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btPesquisarComprovante))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbDataHoraComprovante)
                    .addComponent(tfDataHoraComprovante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbOperadorComprovante)
                    .addComponent(tfOperadorComprovante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbFormaPagamentoComprovante)
                    .addComponent(tfFormaPagamentoComprovante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jpTotalVendasComprovante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jpItensVendaComprovante.setBackground(new java.awt.Color(10, 4, 138));
        jpItensVendaComprovante.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(10, 4, 138), 1, true));

        lbItensVendaComprovante.setBackground(new java.awt.Color(255, 255, 255));
        lbItensVendaComprovante.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lbItensVendaComprovante.setForeground(new java.awt.Color(255, 255, 255));
        lbItensVendaComprovante.setText("Itens da Venda");

        tbItensVendaComprovante.setBackground(new java.awt.Color(255, 255, 255));
        tbItensVendaComprovante.setForeground(new java.awt.Color(0, 0, 0));
        tbItensVendaComprovante.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Código", "Produto", "Quantidade", "Valor Unitário", "Subtotal"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane14.setViewportView(tbItensVendaComprovante);

        javax.swing.GroupLayout jpItensVendaComprovanteLayout = new javax.swing.GroupLayout(jpItensVendaComprovante);
        jpItensVendaComprovante.setLayout(jpItensVendaComprovanteLayout);
        jpItensVendaComprovanteLayout.setHorizontalGroup(
            jpItensVendaComprovanteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpItensVendaComprovanteLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbItensVendaComprovante)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpItensVendaComprovanteLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 768, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );
        jpItensVendaComprovanteLayout.setVerticalGroup(
            jpItensVendaComprovanteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpItensVendaComprovanteLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbItensVendaComprovante)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jpComprovanteLayout = new javax.swing.GroupLayout(jpComprovante);
        jpComprovante.setLayout(jpComprovanteLayout);
        jpComprovanteLayout.setHorizontalGroup(
            jpComprovanteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpComprovanteLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jpComprovanteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jpItensVendaComprovante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(42, Short.MAX_VALUE))
        );
        jpComprovanteLayout.setVerticalGroup(
            jpComprovanteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpComprovanteLayout.createSequentialGroup()
                .addGap(86, 86, 86)
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jpItensVendaComprovante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(111, Short.MAX_VALUE))
        );

        tpInicio.addTab("Comprovante", jpComprovante);

        jpBandeiras.setBackground(new java.awt.Color(255, 255, 255));

        jpFiltrosBandeiras.setBackground(new java.awt.Color(255, 255, 255));
        jpFiltrosBandeiras.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(10, 4, 138), 1, true));

        lbFiltrosBandeiras.setBackground(new java.awt.Color(255, 255, 255));
        lbFiltrosBandeiras.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lbFiltrosBandeiras.setForeground(new java.awt.Color(0, 0, 0));
        lbFiltrosBandeiras.setText("Filtros");

        lbDescricaoBandeiras.setBackground(new java.awt.Color(255, 255, 255));
        lbDescricaoBandeiras.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lbDescricaoBandeiras.setForeground(new java.awt.Color(0, 0, 0));
        lbDescricaoBandeiras.setText("Descrição:");

        tfDescricaoBandeiras.setBackground(new java.awt.Color(255, 255, 255));
        tfDescricaoBandeiras.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tfDescricaoBandeiras.setForeground(new java.awt.Color(0, 0, 0));

        lbTipoBandeiras.setBackground(new java.awt.Color(255, 255, 255));
        lbTipoBandeiras.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lbTipoBandeiras.setForeground(new java.awt.Color(0, 0, 0));
        lbTipoBandeiras.setText("Tipo:");

        cbTipoBandeiras.setBackground(new java.awt.Color(255, 255, 255));
        cbTipoBandeiras.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cbTipoBandeiras.setForeground(new java.awt.Color(0, 0, 0));
        cbTipoBandeiras.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Crédito", "Débito", "Alimentação", "Refeição", "Frota", "Presente" }));

        javax.swing.GroupLayout jpFiltrosBandeirasLayout = new javax.swing.GroupLayout(jpFiltrosBandeiras);
        jpFiltrosBandeiras.setLayout(jpFiltrosBandeirasLayout);
        jpFiltrosBandeirasLayout.setHorizontalGroup(
            jpFiltrosBandeirasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpFiltrosBandeirasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpFiltrosBandeirasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpFiltrosBandeirasLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(lbDescricaoBandeiras)
                        .addGap(18, 18, 18)
                        .addComponent(tfDescricaoBandeiras, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(48, 48, 48)
                        .addComponent(lbTipoBandeiras)
                        .addGap(18, 18, 18)
                        .addComponent(cbTipoBandeiras, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lbFiltrosBandeiras))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpFiltrosBandeirasLayout.setVerticalGroup(
            jpFiltrosBandeirasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpFiltrosBandeirasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbFiltrosBandeiras)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jpFiltrosBandeirasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbDescricaoBandeiras)
                    .addComponent(tfDescricaoBandeiras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbTipoBandeiras)
                    .addComponent(cbTipoBandeiras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jpCadastroBandeiras.setBackground(new java.awt.Color(255, 255, 255));
        jpCadastroBandeiras.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(10, 4, 138), 1, true));

        lbCadastroBandeiras.setBackground(new java.awt.Color(255, 255, 255));
        lbCadastroBandeiras.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lbCadastroBandeiras.setForeground(new java.awt.Color(0, 0, 0));
        lbCadastroBandeiras.setText("Cadastro de Bandeiras");

        lbCodigoBandeiras.setBackground(new java.awt.Color(255, 255, 255));
        lbCodigoBandeiras.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lbCodigoBandeiras.setForeground(new java.awt.Color(0, 0, 0));
        lbCodigoBandeiras.setText("Código:");

        tfCodigoBandeiras.setBackground(new java.awt.Color(255, 255, 255));
        tfCodigoBandeiras.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tfCodigoBandeiras.setForeground(new java.awt.Color(0, 0, 0));

        lbTipoCadastroBandeiras.setBackground(new java.awt.Color(255, 255, 255));
        lbTipoCadastroBandeiras.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lbTipoCadastroBandeiras.setForeground(new java.awt.Color(0, 0, 0));
        lbTipoCadastroBandeiras.setText("Tipo:");

        cbTipoCadastroBandeiras.setBackground(new java.awt.Color(255, 255, 255));
        cbTipoCadastroBandeiras.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cbTipoCadastroBandeiras.setForeground(new java.awt.Color(0, 0, 0));
        cbTipoCadastroBandeiras.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Crédito", "Débito", "Alimentação", "Refeição", "Frota", "Presente" }));

        lbDescricaoCadastroBandeiras.setBackground(new java.awt.Color(255, 255, 255));
        lbDescricaoCadastroBandeiras.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lbDescricaoCadastroBandeiras.setForeground(new java.awt.Color(0, 0, 0));
        lbDescricaoCadastroBandeiras.setText("Descrição:");

        jcbAtivaCadastroBandeiras.setBackground(new java.awt.Color(255, 255, 255));
        jcbAtivaCadastroBandeiras.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jcbAtivaCadastroBandeiras.setForeground(new java.awt.Color(0, 0, 0));
        jcbAtivaCadastroBandeiras.setText("Ativa");

        jcbDesativaCadastroBandeiras.setBackground(new java.awt.Color(255, 255, 255));
        jcbDesativaCadastroBandeiras.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jcbDesativaCadastroBandeiras.setForeground(new java.awt.Color(0, 0, 0));
        jcbDesativaCadastroBandeiras.setText("Desativa");

        tfDescricaoCadastroBandeiras.setBackground(new java.awt.Color(255, 255, 255));
        tfDescricaoCadastroBandeiras.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tfDescricaoCadastroBandeiras.setForeground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jpCadastroBandeirasLayout = new javax.swing.GroupLayout(jpCadastroBandeiras);
        jpCadastroBandeiras.setLayout(jpCadastroBandeirasLayout);
        jpCadastroBandeirasLayout.setHorizontalGroup(
            jpCadastroBandeirasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpCadastroBandeirasLayout.createSequentialGroup()
                .addGroup(jpCadastroBandeirasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpCadastroBandeirasLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lbCadastroBandeiras))
                    .addGroup(jpCadastroBandeirasLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(jpCadastroBandeirasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbCodigoBandeiras)
                            .addComponent(lbTipoCadastroBandeiras))
                        .addGap(18, 18, 18)
                        .addGroup(jpCadastroBandeirasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tfCodigoBandeiras)
                            .addComponent(cbTipoCadastroBandeiras, 0, 126, Short.MAX_VALUE))
                        .addGap(73, 73, 73)
                        .addComponent(lbDescricaoCadastroBandeiras)
                        .addGap(18, 18, 18)
                        .addGroup(jpCadastroBandeirasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpCadastroBandeirasLayout.createSequentialGroup()
                                .addComponent(jcbAtivaCadastroBandeiras)
                                .addGap(18, 18, 18)
                                .addComponent(jcbDesativaCadastroBandeiras))
                            .addComponent(tfDescricaoCadastroBandeiras, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(45, 45, Short.MAX_VALUE))
        );
        jpCadastroBandeirasLayout.setVerticalGroup(
            jpCadastroBandeirasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpCadastroBandeirasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbCadastroBandeiras)
                .addGap(18, 18, 18)
                .addGroup(jpCadastroBandeirasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jpCadastroBandeirasLayout.createSequentialGroup()
                        .addGroup(jpCadastroBandeirasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbCodigoBandeiras)
                            .addComponent(tfCodigoBandeiras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbDescricaoCadastroBandeiras))
                        .addGap(31, 31, 31)
                        .addGroup(jpCadastroBandeirasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbTipoCadastroBandeiras)
                            .addComponent(cbTipoCadastroBandeiras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(tfDescricaoCadastroBandeiras))
                .addGap(18, 18, 18)
                .addGroup(jpCadastroBandeirasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jcbAtivaCadastroBandeiras)
                    .addComponent(jcbDesativaCadastroBandeiras))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jpbtCadastroBandeiras.setBackground(new java.awt.Color(255, 255, 255));
        jpbtCadastroBandeiras.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(10, 4, 138), 1, true));

        btSalvarBandeiras.setBackground(new java.awt.Color(10, 4, 138));
        btSalvarBandeiras.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btSalvarBandeiras.setForeground(new java.awt.Color(255, 255, 255));
        btSalvarBandeiras.setText("Salvar");

        btNovoBandeiras.setBackground(new java.awt.Color(10, 4, 138));
        btNovoBandeiras.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btNovoBandeiras.setForeground(new java.awt.Color(255, 255, 255));
        btNovoBandeiras.setText("Novo");
        btNovoBandeiras.addActionListener(this::btNovoBandeirasActionPerformed);

        btEditarBandeiras.setBackground(new java.awt.Color(10, 4, 138));
        btEditarBandeiras.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btEditarBandeiras.setForeground(new java.awt.Color(255, 255, 255));
        btEditarBandeiras.setText("Editar");

        btExcluirBandeiras.setBackground(new java.awt.Color(10, 4, 138));
        btExcluirBandeiras.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btExcluirBandeiras.setForeground(new java.awt.Color(255, 255, 255));
        btExcluirBandeiras.setText("Excluir");

        javax.swing.GroupLayout jpbtCadastroBandeirasLayout = new javax.swing.GroupLayout(jpbtCadastroBandeiras);
        jpbtCadastroBandeiras.setLayout(jpbtCadastroBandeirasLayout);
        jpbtCadastroBandeirasLayout.setHorizontalGroup(
            jpbtCadastroBandeirasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpbtCadastroBandeirasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpbtCadastroBandeirasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btExcluirBandeiras, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btEditarBandeiras, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btNovoBandeiras, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btSalvarBandeiras, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jpbtCadastroBandeirasLayout.setVerticalGroup(
            jpbtCadastroBandeirasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpbtCadastroBandeirasLayout.createSequentialGroup()
                .addContainerGap(35, Short.MAX_VALUE)
                .addComponent(btSalvarBandeiras)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btNovoBandeiras)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btEditarBandeiras)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btExcluirBandeiras)
                .addGap(31, 31, 31))
        );

        jpbtFiltrosBandeiras.setBackground(new java.awt.Color(255, 255, 255));
        jpbtFiltrosBandeiras.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(10, 4, 138), 1, true));

        btPesquisarBandeiras.setBackground(new java.awt.Color(10, 4, 138));
        btPesquisarBandeiras.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btPesquisarBandeiras.setForeground(new java.awt.Color(255, 255, 255));
        btPesquisarBandeiras.setText("Pesquisar");

        btLimparFiltrosBandeiras.setBackground(new java.awt.Color(10, 4, 138));
        btLimparFiltrosBandeiras.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btLimparFiltrosBandeiras.setForeground(new java.awt.Color(255, 255, 255));
        btLimparFiltrosBandeiras.setText("Limpar Filtros");

        javax.swing.GroupLayout jpbtFiltrosBandeirasLayout = new javax.swing.GroupLayout(jpbtFiltrosBandeiras);
        jpbtFiltrosBandeiras.setLayout(jpbtFiltrosBandeirasLayout);
        jpbtFiltrosBandeirasLayout.setHorizontalGroup(
            jpbtFiltrosBandeirasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpbtFiltrosBandeirasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpbtFiltrosBandeirasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btLimparFiltrosBandeiras, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)
                    .addGroup(jpbtFiltrosBandeirasLayout.createSequentialGroup()
                        .addComponent(btPesquisarBandeiras, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        jpbtFiltrosBandeirasLayout.setVerticalGroup(
            jpbtFiltrosBandeirasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpbtFiltrosBandeirasLayout.createSequentialGroup()
                .addContainerGap(28, Short.MAX_VALUE)
                .addComponent(btPesquisarBandeiras)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btLimparFiltrosBandeiras)
                .addGap(22, 22, 22))
        );

        javax.swing.GroupLayout jpBandeirasLayout = new javax.swing.GroupLayout(jpBandeiras);
        jpBandeiras.setLayout(jpBandeirasLayout);
        jpBandeirasLayout.setHorizontalGroup(
            jpBandeirasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpBandeirasLayout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(jpBandeirasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jpFiltrosBandeiras, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jpCadastroBandeiras, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpBandeirasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jpbtCadastroBandeiras, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jpbtFiltrosBandeiras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(60, Short.MAX_VALUE))
        );
        jpBandeirasLayout.setVerticalGroup(
            jpBandeirasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpBandeirasLayout.createSequentialGroup()
                .addContainerGap(181, Short.MAX_VALUE)
                .addGroup(jpBandeirasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jpFiltrosBandeiras, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jpbtFiltrosBandeiras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(81, 81, 81)
                .addGroup(jpBandeirasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jpCadastroBandeiras, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jpbtCadastroBandeiras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(113, 113, 113))
        );

        tpInicio.addTab("Bandeiras", jpBandeiras);

        jpParcelamentos.setBackground(new java.awt.Color(255, 255, 255));

        jpFiltrosParcelamentos.setBackground(new java.awt.Color(255, 255, 255));
        jpFiltrosParcelamentos.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(10, 4, 138), 1, true));

        lbFiltrosParcelamentos.setBackground(new java.awt.Color(255, 255, 255));
        lbFiltrosParcelamentos.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lbFiltrosParcelamentos.setForeground(new java.awt.Color(0, 0, 0));
        lbFiltrosParcelamentos.setText("Filtros");

        lbFormasPagamentoParcelamentos.setBackground(new java.awt.Color(255, 255, 255));
        lbFormasPagamentoParcelamentos.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lbFormasPagamentoParcelamentos.setForeground(new java.awt.Color(0, 0, 0));
        lbFormasPagamentoParcelamentos.setText("Forma de Pagamento:");

        cbFormaPagamentoParcelamentos.setBackground(new java.awt.Color(255, 255, 255));
        cbFormaPagamentoParcelamentos.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cbFormaPagamentoParcelamentos.setForeground(new java.awt.Color(0, 0, 0));
        cbFormaPagamentoParcelamentos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cartão de Crédito", "Cartão de Débito", "Crediário" }));

        lbBandeiraParcelamentos.setBackground(new java.awt.Color(255, 255, 255));
        lbBandeiraParcelamentos.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lbBandeiraParcelamentos.setForeground(new java.awt.Color(0, 0, 0));
        lbBandeiraParcelamentos.setText("Bandeira:");

        cbBandeiraParcelamentos.setBackground(new java.awt.Color(255, 255, 255));
        cbBandeiraParcelamentos.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cbBandeiraParcelamentos.setForeground(new java.awt.Color(0, 0, 0));
        cbBandeiraParcelamentos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mastercard", "Visa", "Elo", "American Express (Amex)", "Hipercard", "Hiper", "Cabal" }));

        javax.swing.GroupLayout jpFiltrosParcelamentosLayout = new javax.swing.GroupLayout(jpFiltrosParcelamentos);
        jpFiltrosParcelamentos.setLayout(jpFiltrosParcelamentosLayout);
        jpFiltrosParcelamentosLayout.setHorizontalGroup(
            jpFiltrosParcelamentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpFiltrosParcelamentosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpFiltrosParcelamentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbFiltrosParcelamentos)
                    .addGroup(jpFiltrosParcelamentosLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jpFiltrosParcelamentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbFormasPagamentoParcelamentos)
                            .addComponent(cbFormaPagamentoParcelamentos, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(110, 110, 110)
                        .addGroup(jpFiltrosParcelamentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbBandeiraParcelamentos)
                            .addComponent(cbBandeiraParcelamentos, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(59, Short.MAX_VALUE))
        );
        jpFiltrosParcelamentosLayout.setVerticalGroup(
            jpFiltrosParcelamentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpFiltrosParcelamentosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbFiltrosParcelamentos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jpFiltrosParcelamentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbFormasPagamentoParcelamentos)
                    .addComponent(lbBandeiraParcelamentos))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpFiltrosParcelamentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbFormaPagamentoParcelamentos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbBandeiraParcelamentos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jpCadastroParcelamentos.setBackground(new java.awt.Color(255, 255, 255));
        jpCadastroParcelamentos.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(10, 4, 138), 1, true));

        lbCadastroParcelamentos.setBackground(new java.awt.Color(255, 255, 255));
        lbCadastroParcelamentos.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lbCadastroParcelamentos.setForeground(new java.awt.Color(0, 0, 0));
        lbCadastroParcelamentos.setText("Cadastro de Parcelamento");

        lbCodigoParcelamentos.setBackground(new java.awt.Color(255, 255, 255));
        lbCodigoParcelamentos.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lbCodigoParcelamentos.setForeground(new java.awt.Color(0, 0, 0));
        lbCodigoParcelamentos.setText("Código:");

        tfCodigoParcelamentos.setBackground(new java.awt.Color(255, 255, 255));
        tfCodigoParcelamentos.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tfCodigoParcelamentos.setForeground(new java.awt.Color(0, 0, 0));

        lbBandeiraCadastroParcelamentos.setBackground(new java.awt.Color(255, 255, 255));
        lbBandeiraCadastroParcelamentos.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lbBandeiraCadastroParcelamentos.setForeground(new java.awt.Color(0, 0, 0));
        lbBandeiraCadastroParcelamentos.setText("Bandeira:");

        cbBandeiraCadastroParcelamentos.setBackground(new java.awt.Color(255, 255, 255));
        cbBandeiraCadastroParcelamentos.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cbBandeiraCadastroParcelamentos.setForeground(new java.awt.Color(0, 0, 0));
        cbBandeiraCadastroParcelamentos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mastercard", "Visa", "Elo", "American Express (Amex)", "Hipercard", "Hiper", "Cabal" }));

        lbNParcelasParcelamentos.setBackground(new java.awt.Color(255, 255, 255));
        lbNParcelasParcelamentos.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lbNParcelasParcelamentos.setForeground(new java.awt.Color(0, 0, 0));
        lbNParcelasParcelamentos.setText("N° de Parcelas:");

        cbNParcelasParcelamentos.setBackground(new java.awt.Color(255, 255, 255));
        cbNParcelasParcelamentos.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cbNParcelasParcelamentos.setForeground(new java.awt.Color(0, 0, 0));
        cbNParcelasParcelamentos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1x", "2x", "3x", "4x", "5x", "6x" }));

        lbTaxaJurosParcelamentos.setBackground(new java.awt.Color(255, 255, 255));
        lbTaxaJurosParcelamentos.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lbTaxaJurosParcelamentos.setForeground(new java.awt.Color(0, 0, 0));
        lbTaxaJurosParcelamentos.setText("Taxa de Juros (%):");

        cbTaxaJurosParcelamentos.setBackground(new java.awt.Color(255, 255, 255));
        cbTaxaJurosParcelamentos.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cbTaxaJurosParcelamentos.setForeground(new java.awt.Color(0, 0, 0));
        cbTaxaJurosParcelamentos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0%", "0,5%", "1%", "1,5%", "2%", "2,5", "3%", "3,5%", "4%" }));

        jbcAtivoCadastroParcelamentos.setBackground(new java.awt.Color(255, 255, 255));
        jbcAtivoCadastroParcelamentos.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jbcAtivoCadastroParcelamentos.setForeground(new java.awt.Color(0, 0, 0));
        jbcAtivoCadastroParcelamentos.setText("Ativo");

        lbFormaPagamentos.setBackground(new java.awt.Color(255, 255, 255));
        lbFormaPagamentos.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lbFormaPagamentos.setForeground(new java.awt.Color(0, 0, 0));
        lbFormaPagamentos.setText("Forma de Pagamento:");

        cbFormaPagamentoCadastroParcelamentos.setBackground(new java.awt.Color(255, 255, 255));
        cbFormaPagamentoCadastroParcelamentos.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cbFormaPagamentoCadastroParcelamentos.setForeground(new java.awt.Color(0, 0, 0));
        cbFormaPagamentoCadastroParcelamentos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cartão de Crédito", "Cartão de Débito", "Crediário" }));

        javax.swing.GroupLayout jpCadastroParcelamentosLayout = new javax.swing.GroupLayout(jpCadastroParcelamentos);
        jpCadastroParcelamentos.setLayout(jpCadastroParcelamentosLayout);
        jpCadastroParcelamentosLayout.setHorizontalGroup(
            jpCadastroParcelamentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpCadastroParcelamentosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpCadastroParcelamentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbCadastroParcelamentos)
                    .addGroup(jpCadastroParcelamentosLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jpCadastroParcelamentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpCadastroParcelamentosLayout.createSequentialGroup()
                                .addGroup(jpCadastroParcelamentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbCodigoParcelamentos)
                                    .addComponent(tfCodigoParcelamentos, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jpCadastroParcelamentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbFormaPagamentos)
                                    .addComponent(cbFormaPagamentoCadastroParcelamentos, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jpCadastroParcelamentosLayout.createSequentialGroup()
                                .addGroup(jpCadastroParcelamentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbBandeiraCadastroParcelamentos)
                                    .addComponent(cbBandeiraCadastroParcelamentos, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jpCadastroParcelamentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbNParcelasParcelamentos)
                                    .addComponent(cbNParcelasParcelamentos, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(28, 28, 28)
                                .addGroup(jpCadastroParcelamentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbTaxaJurosParcelamentos, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jpCadastroParcelamentosLayout.createSequentialGroup()
                                        .addComponent(lbTaxaJurosParcelamentos)
                                        .addGap(78, 78, 78)
                                        .addComponent(jbcAtivoCadastroParcelamentos)))
                                .addGap(69, 69, 69)))))
                .addGap(74, 74, 74))
        );
        jpCadastroParcelamentosLayout.setVerticalGroup(
            jpCadastroParcelamentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpCadastroParcelamentosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbCadastroParcelamentos)
                .addGap(13, 13, 13)
                .addGroup(jpCadastroParcelamentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbCodigoParcelamentos)
                    .addComponent(lbFormaPagamentos))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpCadastroParcelamentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfCodigoParcelamentos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbFormaPagamentoCadastroParcelamentos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jpCadastroParcelamentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbBandeiraCadastroParcelamentos)
                    .addComponent(lbNParcelasParcelamentos)
                    .addComponent(lbTaxaJurosParcelamentos)
                    .addComponent(jbcAtivoCadastroParcelamentos))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpCadastroParcelamentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbBandeiraCadastroParcelamentos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbNParcelasParcelamentos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbTaxaJurosParcelamentos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jpbtFiltrosParcelamentos.setBackground(new java.awt.Color(255, 255, 255));
        jpbtFiltrosParcelamentos.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(10, 4, 138), 1, true));

        btPesquisarParcelamentos.setBackground(new java.awt.Color(10, 4, 138));
        btPesquisarParcelamentos.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btPesquisarParcelamentos.setForeground(new java.awt.Color(255, 255, 255));
        btPesquisarParcelamentos.setText("Pesquisar");

        btLimparFiltrosParcelamentos.setBackground(new java.awt.Color(10, 4, 138));
        btLimparFiltrosParcelamentos.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btLimparFiltrosParcelamentos.setForeground(new java.awt.Color(255, 255, 255));
        btLimparFiltrosParcelamentos.setText("Limpar Filtros");

        javax.swing.GroupLayout jpbtFiltrosParcelamentosLayout = new javax.swing.GroupLayout(jpbtFiltrosParcelamentos);
        jpbtFiltrosParcelamentos.setLayout(jpbtFiltrosParcelamentosLayout);
        jpbtFiltrosParcelamentosLayout.setHorizontalGroup(
            jpbtFiltrosParcelamentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpbtFiltrosParcelamentosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpbtFiltrosParcelamentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btPesquisarParcelamentos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btLimparFiltrosParcelamentos, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE))
                .addContainerGap())
        );
        jpbtFiltrosParcelamentosLayout.setVerticalGroup(
            jpbtFiltrosParcelamentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpbtFiltrosParcelamentosLayout.createSequentialGroup()
                .addContainerGap(41, Short.MAX_VALUE)
                .addComponent(btPesquisarParcelamentos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btLimparFiltrosParcelamentos)
                .addGap(33, 33, 33))
        );

        jpbtCadastroParcelamentos.setBackground(new java.awt.Color(255, 255, 255));
        jpbtCadastroParcelamentos.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(10, 4, 138), 1, true));

        btSalvarParcelamentos.setBackground(new java.awt.Color(10, 4, 138));
        btSalvarParcelamentos.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btSalvarParcelamentos.setForeground(new java.awt.Color(255, 255, 255));
        btSalvarParcelamentos.setText("Salvar");

        btNovoParcelamentos.setBackground(new java.awt.Color(10, 4, 138));
        btNovoParcelamentos.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btNovoParcelamentos.setForeground(new java.awt.Color(255, 255, 255));
        btNovoParcelamentos.setText("Novo");

        btEditarParcelamentos.setBackground(new java.awt.Color(10, 4, 138));
        btEditarParcelamentos.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btEditarParcelamentos.setForeground(new java.awt.Color(255, 255, 255));
        btEditarParcelamentos.setText("Editar");

        btExcluirParcelamentos.setBackground(new java.awt.Color(10, 4, 138));
        btExcluirParcelamentos.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btExcluirParcelamentos.setForeground(new java.awt.Color(255, 255, 255));
        btExcluirParcelamentos.setText("Excluir");

        javax.swing.GroupLayout jpbtCadastroParcelamentosLayout = new javax.swing.GroupLayout(jpbtCadastroParcelamentos);
        jpbtCadastroParcelamentos.setLayout(jpbtCadastroParcelamentosLayout);
        jpbtCadastroParcelamentosLayout.setHorizontalGroup(
            jpbtCadastroParcelamentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpbtCadastroParcelamentosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpbtCadastroParcelamentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btExcluirParcelamentos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btNovoParcelamentos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btSalvarParcelamentos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btEditarParcelamentos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jpbtCadastroParcelamentosLayout.setVerticalGroup(
            jpbtCadastroParcelamentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpbtCadastroParcelamentosLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(btSalvarParcelamentos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btNovoParcelamentos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btEditarParcelamentos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btExcluirParcelamentos)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jpParcelamentosLayout = new javax.swing.GroupLayout(jpParcelamentos);
        jpParcelamentos.setLayout(jpParcelamentosLayout);
        jpParcelamentosLayout.setHorizontalGroup(
            jpParcelamentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpParcelamentosLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jpParcelamentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jpCadastroParcelamentos, javax.swing.GroupLayout.PREFERRED_SIZE, 674, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jpFiltrosParcelamentos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpParcelamentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jpbtCadastroParcelamentos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jpbtFiltrosParcelamentos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        jpParcelamentosLayout.setVerticalGroup(
            jpParcelamentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpParcelamentosLayout.createSequentialGroup()
                .addContainerGap(151, Short.MAX_VALUE)
                .addGroup(jpParcelamentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jpFiltrosParcelamentos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jpbtFiltrosParcelamentos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(100, 100, 100)
                .addGroup(jpParcelamentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jpCadastroParcelamentos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jpbtCadastroParcelamentos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(111, 111, 111))
        );

        tpInicio.addTab("Parcelamentos", jpParcelamentos);

        jpTaxas.setBackground(new java.awt.Color(255, 255, 255));

        jpCadastroTaxas.setBackground(new java.awt.Color(255, 255, 255));
        jpCadastroTaxas.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(10, 4, 138), 1, true));

        lbCadastroTaxas.setBackground(new java.awt.Color(255, 255, 255));
        lbCadastroTaxas.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lbCadastroTaxas.setForeground(new java.awt.Color(0, 0, 0));
        lbCadastroTaxas.setText("Cadastro de Taxa");

        lbCodigoTaxas.setBackground(new java.awt.Color(255, 255, 255));
        lbCodigoTaxas.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lbCodigoTaxas.setForeground(new java.awt.Color(0, 0, 0));
        lbCodigoTaxas.setText("Código:");

        tfCodigoTaxas.setBackground(new java.awt.Color(255, 255, 255));
        tfCodigoTaxas.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tfCodigoTaxas.setForeground(new java.awt.Color(0, 0, 0));

        lbTipoTaxas.setBackground(new java.awt.Color(255, 255, 255));
        lbTipoTaxas.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lbTipoTaxas.setForeground(new java.awt.Color(0, 0, 0));
        lbTipoTaxas.setText("Tipo de Taxa:");

        cbTipoTaxas.setBackground(new java.awt.Color(255, 255, 255));
        cbTipoTaxas.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cbTipoTaxas.setForeground(new java.awt.Color(0, 0, 0));
        cbTipoTaxas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Taxa percentual (%)", "Taxa fixa (R$)", "Taxa por transação", "Taxa de antecipação" }));

        lbFormaPagamentoCadastroTaxas.setBackground(new java.awt.Color(255, 255, 255));
        lbFormaPagamentoCadastroTaxas.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lbFormaPagamentoCadastroTaxas.setForeground(new java.awt.Color(0, 0, 0));
        lbFormaPagamentoCadastroTaxas.setText("Forma de Pagamento:");

        cbFormaPagamentoCadastroTaxas.setBackground(new java.awt.Color(255, 255, 255));
        cbFormaPagamentoCadastroTaxas.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cbFormaPagamentoCadastroTaxas.setForeground(new java.awt.Color(0, 0, 0));
        cbFormaPagamentoCadastroTaxas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cartão de Crédito", "Cartão de Débito", "PIX", "Vale Alimentação", "Vale Refeição" }));

        lbValorTaxas.setBackground(new java.awt.Color(255, 255, 255));
        lbValorTaxas.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lbValorTaxas.setForeground(new java.awt.Color(0, 0, 0));
        lbValorTaxas.setText("Valor da Taxa:");

        jcbAtivoTaxas.setBackground(new java.awt.Color(255, 255, 255));
        jcbAtivoTaxas.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jcbAtivoTaxas.setForeground(new java.awt.Color(0, 0, 0));
        jcbAtivoTaxas.setText("Ativo");

        lbBandeiraCadastroTaxas.setBackground(new java.awt.Color(255, 255, 255));
        lbBandeiraCadastroTaxas.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lbBandeiraCadastroTaxas.setForeground(new java.awt.Color(0, 0, 0));
        lbBandeiraCadastroTaxas.setText("Bandeira:");

        cbBandeiraCadastroTaxas.setBackground(new java.awt.Color(255, 255, 255));
        cbBandeiraCadastroTaxas.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cbBandeiraCadastroTaxas.setForeground(new java.awt.Color(0, 0, 0));
        cbBandeiraCadastroTaxas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mastercard", "Visa", "Elo", "American Express (Amex)", "Hipercard", "Hiper", "Cabal" }));

        tfValorTaxas.setBackground(new java.awt.Color(255, 255, 255));
        tfValorTaxas.setForeground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jpCadastroTaxasLayout = new javax.swing.GroupLayout(jpCadastroTaxas);
        jpCadastroTaxas.setLayout(jpCadastroTaxasLayout);
        jpCadastroTaxasLayout.setHorizontalGroup(
            jpCadastroTaxasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpCadastroTaxasLayout.createSequentialGroup()
                .addGroup(jpCadastroTaxasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpCadastroTaxasLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(jpCadastroTaxasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpCadastroTaxasLayout.createSequentialGroup()
                                .addGroup(jpCadastroTaxasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbCodigoTaxas)
                                    .addComponent(lbTipoTaxas))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jpCadastroTaxasLayout.createSequentialGroup()
                                .addGroup(jpCadastroTaxasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(cbTipoTaxas, javax.swing.GroupLayout.Alignment.LEADING, 0, 156, Short.MAX_VALUE)
                                    .addComponent(tfCodigoTaxas))
                                .addGap(55, 55, 55)))
                        .addGroup(jpCadastroTaxasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbFormaPagamentoCadastroTaxas)
                            .addComponent(cbFormaPagamentoCadastroTaxas, 0, 150, Short.MAX_VALUE)
                            .addComponent(lbValorTaxas)
                            .addComponent(tfValorTaxas))
                        .addGap(67, 67, 67)
                        .addGroup(jpCadastroTaxasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jcbAtivoTaxas)
                            .addComponent(lbBandeiraCadastroTaxas)
                            .addComponent(cbBandeiraCadastroTaxas, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jpCadastroTaxasLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lbCadastroTaxas)))
                .addGap(40, 40, 40))
        );
        jpCadastroTaxasLayout.setVerticalGroup(
            jpCadastroTaxasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpCadastroTaxasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbCadastroTaxas)
                .addGap(18, 18, 18)
                .addGroup(jpCadastroTaxasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jpCadastroTaxasLayout.createSequentialGroup()
                        .addGroup(jpCadastroTaxasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbFormaPagamentoCadastroTaxas)
                            .addComponent(lbBandeiraCadastroTaxas))
                        .addGap(2, 2, 2)
                        .addGroup(jpCadastroTaxasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbFormaPagamentoCadastroTaxas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbBandeiraCadastroTaxas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jpCadastroTaxasLayout.createSequentialGroup()
                        .addComponent(lbCodigoTaxas)
                        .addGap(2, 2, 2)
                        .addComponent(tfCodigoTaxas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jpCadastroTaxasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbValorTaxas)
                    .addComponent(lbTipoTaxas))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpCadastroTaxasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jcbAtivoTaxas)
                    .addComponent(cbTipoTaxas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfValorTaxas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jpFiltrosTaxas.setBackground(new java.awt.Color(255, 255, 255));
        jpFiltrosTaxas.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(10, 4, 138), 1, true));

        lbFiltrosTaxas.setBackground(new java.awt.Color(255, 255, 255));
        lbFiltrosTaxas.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lbFiltrosTaxas.setForeground(new java.awt.Color(0, 0, 0));
        lbFiltrosTaxas.setText("Filtros");

        lbFormaPagamentoFiltrosTaxas.setBackground(new java.awt.Color(255, 255, 255));
        lbFormaPagamentoFiltrosTaxas.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lbFormaPagamentoFiltrosTaxas.setForeground(new java.awt.Color(0, 0, 0));
        lbFormaPagamentoFiltrosTaxas.setText("Forma de Pagamento:");

        cbFormaPagamentoFiltrosTaxas.setBackground(new java.awt.Color(255, 255, 255));
        cbFormaPagamentoFiltrosTaxas.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cbFormaPagamentoFiltrosTaxas.setForeground(new java.awt.Color(0, 0, 0));
        cbFormaPagamentoFiltrosTaxas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cartão de Crédito", "Cartão de Débito", "PIX", "Vale Alimentação", "Vale Refeição" }));

        lbBandeiraFiltrosTaxas.setBackground(new java.awt.Color(255, 255, 255));
        lbBandeiraFiltrosTaxas.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lbBandeiraFiltrosTaxas.setForeground(new java.awt.Color(0, 0, 0));
        lbBandeiraFiltrosTaxas.setText("Bandeira:");

        bBandeiraFiltrosTaxas.setBackground(new java.awt.Color(255, 255, 255));
        bBandeiraFiltrosTaxas.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        bBandeiraFiltrosTaxas.setForeground(new java.awt.Color(0, 0, 0));
        bBandeiraFiltrosTaxas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mastercard", "Visa", "Elo", "American Express (Amex)", "Hipercard", "Hiper", "Cabal" }));

        javax.swing.GroupLayout jpFiltrosTaxasLayout = new javax.swing.GroupLayout(jpFiltrosTaxas);
        jpFiltrosTaxas.setLayout(jpFiltrosTaxasLayout);
        jpFiltrosTaxasLayout.setHorizontalGroup(
            jpFiltrosTaxasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpFiltrosTaxasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpFiltrosTaxasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpFiltrosTaxasLayout.createSequentialGroup()
                        .addComponent(lbFiltrosTaxas)
                        .addContainerGap(582, Short.MAX_VALUE))
                    .addGroup(jpFiltrosTaxasLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jpFiltrosTaxasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbFormaPagamentoFiltrosTaxas)
                            .addComponent(cbFormaPagamentoFiltrosTaxas, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jpFiltrosTaxasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbBandeiraFiltrosTaxas)
                            .addComponent(bBandeiraFiltrosTaxas, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(87, 87, 87))))
        );
        jpFiltrosTaxasLayout.setVerticalGroup(
            jpFiltrosTaxasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpFiltrosTaxasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbFiltrosTaxas)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpFiltrosTaxasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbFormaPagamentoFiltrosTaxas)
                    .addComponent(lbBandeiraFiltrosTaxas))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpFiltrosTaxasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbFormaPagamentoFiltrosTaxas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bBandeiraFiltrosTaxas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jpbtCadastroTaxas.setBackground(new java.awt.Color(255, 255, 255));
        jpbtCadastroTaxas.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(10, 4, 138), 1, true));

        btSalvarTaxas.setBackground(new java.awt.Color(10, 4, 138));
        btSalvarTaxas.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btSalvarTaxas.setForeground(new java.awt.Color(255, 255, 255));
        btSalvarTaxas.setText("Salvar");

        btNovoTaxas.setBackground(new java.awt.Color(10, 4, 138));
        btNovoTaxas.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btNovoTaxas.setForeground(new java.awt.Color(255, 255, 255));
        btNovoTaxas.setText("Novo");

        btEditarTaxas.setBackground(new java.awt.Color(10, 4, 138));
        btEditarTaxas.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btEditarTaxas.setForeground(new java.awt.Color(255, 255, 255));
        btEditarTaxas.setText("Editar");

        btExcluirTaxas.setBackground(new java.awt.Color(10, 4, 138));
        btExcluirTaxas.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btExcluirTaxas.setForeground(new java.awt.Color(255, 255, 255));
        btExcluirTaxas.setText("Excluir");

        javax.swing.GroupLayout jpbtCadastroTaxasLayout = new javax.swing.GroupLayout(jpbtCadastroTaxas);
        jpbtCadastroTaxas.setLayout(jpbtCadastroTaxasLayout);
        jpbtCadastroTaxasLayout.setHorizontalGroup(
            jpbtCadastroTaxasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpbtCadastroTaxasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpbtCadastroTaxasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btEditarTaxas, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
                    .addComponent(btExcluirTaxas, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btSalvarTaxas, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btNovoTaxas, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jpbtCadastroTaxasLayout.setVerticalGroup(
            jpbtCadastroTaxasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpbtCadastroTaxasLayout.createSequentialGroup()
                .addContainerGap(49, Short.MAX_VALUE)
                .addComponent(btSalvarTaxas)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btNovoTaxas)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btEditarTaxas)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btExcluirTaxas)
                .addGap(42, 42, 42))
        );

        jpbtFiltrosTaxas.setBackground(new java.awt.Color(255, 255, 255));
        jpbtFiltrosTaxas.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(10, 4, 138), 1, true));

        btPesquisarTaxas.setBackground(new java.awt.Color(10, 4, 138));
        btPesquisarTaxas.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btPesquisarTaxas.setForeground(new java.awt.Color(255, 255, 255));
        btPesquisarTaxas.setText("Pesquisar");

        btLimparFiltrosTaxas.setBackground(new java.awt.Color(10, 4, 138));
        btLimparFiltrosTaxas.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btLimparFiltrosTaxas.setForeground(new java.awt.Color(255, 255, 255));
        btLimparFiltrosTaxas.setText("Limpar Filtros");

        javax.swing.GroupLayout jpbtFiltrosTaxasLayout = new javax.swing.GroupLayout(jpbtFiltrosTaxas);
        jpbtFiltrosTaxas.setLayout(jpbtFiltrosTaxasLayout);
        jpbtFiltrosTaxasLayout.setHorizontalGroup(
            jpbtFiltrosTaxasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpbtFiltrosTaxasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpbtFiltrosTaxasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btPesquisarTaxas, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jpbtFiltrosTaxasLayout.createSequentialGroup()
                        .addComponent(btLimparFiltrosTaxas, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jpbtFiltrosTaxasLayout.setVerticalGroup(
            jpbtFiltrosTaxasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpbtFiltrosTaxasLayout.createSequentialGroup()
                .addContainerGap(43, Short.MAX_VALUE)
                .addComponent(btPesquisarTaxas)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btLimparFiltrosTaxas)
                .addGap(42, 42, 42))
        );

        javax.swing.GroupLayout jpTaxasLayout = new javax.swing.GroupLayout(jpTaxas);
        jpTaxas.setLayout(jpTaxasLayout);
        jpTaxasLayout.setHorizontalGroup(
            jpTaxasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpTaxasLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jpTaxasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jpFiltrosTaxas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jpCadastroTaxas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpTaxasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jpbtFiltrosTaxas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jpbtCadastroTaxas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(73, Short.MAX_VALUE))
        );
        jpTaxasLayout.setVerticalGroup(
            jpTaxasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpTaxasLayout.createSequentialGroup()
                .addContainerGap(120, Short.MAX_VALUE)
                .addGroup(jpTaxasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jpFiltrosTaxas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jpbtFiltrosTaxas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(82, 82, 82)
                .addGroup(jpTaxasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jpCadastroTaxas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jpbtCadastroTaxas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(113, 113, 113))
        );

        tpInicio.addTab("Taxas", jpTaxas);

        jpContas.setBackground(new java.awt.Color(255, 255, 255));

        jpRelatoriosContas.setBackground(new java.awt.Color(10, 4, 138));
        jpRelatoriosContas.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(10, 4, 138), 1, true));

        lbRelatoriosContas.setBackground(new java.awt.Color(255, 255, 255));
        lbRelatoriosContas.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lbRelatoriosContas.setForeground(new java.awt.Color(255, 255, 255));
        lbRelatoriosContas.setText("Relatórios");

        lbTipoRelatorioContas.setBackground(new java.awt.Color(255, 255, 255));
        lbTipoRelatorioContas.setForeground(new java.awt.Color(255, 255, 255));
        lbTipoRelatorioContas.setText("Tipo de Relatório:");

        cbTipoRelatorioContas.setBackground(new java.awt.Color(255, 255, 255));
        cbTipoRelatorioContas.setForeground(new java.awt.Color(0, 0, 0));
        cbTipoRelatorioContas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        lbPeriodoContas.setBackground(new java.awt.Color(255, 255, 255));
        lbPeriodoContas.setForeground(new java.awt.Color(255, 255, 255));
        lbPeriodoContas.setText("Período:");

        ftfPeriodoInicialContas.setBackground(new java.awt.Color(255, 255, 255));
        ftfPeriodoInicialContas.setForeground(new java.awt.Color(0, 0, 0));

        ftfPeriodoFinalContas.setBackground(new java.awt.Color(255, 255, 255));
        ftfPeriodoFinalContas.setForeground(new java.awt.Color(0, 0, 0));

        btGerarRelatorioContas.setBackground(new java.awt.Color(255, 255, 255));
        btGerarRelatorioContas.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btGerarRelatorioContas.setForeground(new java.awt.Color(0, 0, 0));
        btGerarRelatorioContas.setText("Gerar Relatório");

        btLimparContas.setBackground(new java.awt.Color(255, 255, 255));
        btLimparContas.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btLimparContas.setForeground(new java.awt.Color(0, 0, 0));
        btLimparContas.setText("Limpar");

        javax.swing.GroupLayout jpRelatoriosContasLayout = new javax.swing.GroupLayout(jpRelatoriosContas);
        jpRelatoriosContas.setLayout(jpRelatoriosContasLayout);
        jpRelatoriosContasLayout.setHorizontalGroup(
            jpRelatoriosContasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpRelatoriosContasLayout.createSequentialGroup()
                .addGroup(jpRelatoriosContasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpRelatoriosContasLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lbRelatoriosContas))
                    .addGroup(jpRelatoriosContasLayout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(jpRelatoriosContasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbTipoRelatorioContas)
                            .addComponent(lbPeriodoContas)
                            .addGroup(jpRelatoriosContasLayout.createSequentialGroup()
                                .addComponent(btGerarRelatorioContas)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btLimparContas))
                            .addComponent(cbTipoRelatorioContas, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jpRelatoriosContasLayout.createSequentialGroup()
                                .addComponent(ftfPeriodoInicialContas, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(ftfPeriodoFinalContas)))))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jpRelatoriosContasLayout.setVerticalGroup(
            jpRelatoriosContasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpRelatoriosContasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbRelatoriosContas)
                .addGap(18, 18, 18)
                .addComponent(lbTipoRelatorioContas)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbTipoRelatorioContas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lbPeriodoContas)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpRelatoriosContasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ftfPeriodoInicialContas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ftfPeriodoFinalContas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                .addGroup(jpRelatoriosContasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btGerarRelatorioContas)
                    .addComponent(btLimparContas))
                .addGap(34, 34, 34))
        );

        jpCadastroContas.setBackground(new java.awt.Color(255, 255, 255));
        jpCadastroContas.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(10, 4, 138), 1, true));

        lbCadastroContas.setBackground(new java.awt.Color(255, 255, 255));
        lbCadastroContas.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lbCadastroContas.setForeground(new java.awt.Color(0, 0, 0));
        lbCadastroContas.setText("Cadastro de Conta Bancária");

        lbCodigoContas.setBackground(new java.awt.Color(255, 255, 255));
        lbCodigoContas.setForeground(new java.awt.Color(0, 0, 0));
        lbCodigoContas.setText("Código:");

        tfCodigoContas.setBackground(new java.awt.Color(255, 255, 255));
        tfCodigoContas.setForeground(new java.awt.Color(0, 0, 0));

        lbTipoCadastroContas.setBackground(new java.awt.Color(255, 255, 255));
        lbTipoCadastroContas.setForeground(new java.awt.Color(0, 0, 0));
        lbTipoCadastroContas.setText("Tipo de Conta:");

        cbTipoCadastroContas.setBackground(new java.awt.Color(255, 255, 255));
        cbTipoCadastroContas.setForeground(new java.awt.Color(0, 0, 0));
        cbTipoCadastroContas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Conta Corrente", "Conta Poupança", "Conta Pagamento", "Conta Salário" }));

        lbBancoContas.setBackground(new java.awt.Color(255, 255, 255));
        lbBancoContas.setForeground(new java.awt.Color(0, 0, 0));
        lbBancoContas.setText("Banco:");

        cbBancoContas.setBackground(new java.awt.Color(255, 255, 255));
        cbBancoContas.setForeground(new java.awt.Color(0, 0, 0));
        cbBancoContas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Bando do Brasil", "Banco Inter", "C6 Bank", "Caixa Econômica Federal", "Bradesco", "Itaú", "Santander", "Nubank", "Sicredi", "Sicoob" }));

        jcbAtivaContas.setForeground(new java.awt.Color(0, 0, 0));
        jcbAtivaContas.setText("Ativa");
        jcbAtivaContas.setToolTipText("");

        lbAgenciaContas.setBackground(new java.awt.Color(255, 255, 255));
        lbAgenciaContas.setForeground(new java.awt.Color(0, 0, 0));
        lbAgenciaContas.setText("Agência:");

        tfAgenciaContas.setBackground(new java.awt.Color(255, 255, 255));
        tfAgenciaContas.setForeground(new java.awt.Color(0, 0, 0));

        lbCadastroContaContas.setBackground(new java.awt.Color(255, 255, 255));
        lbCadastroContaContas.setForeground(new java.awt.Color(0, 0, 0));
        lbCadastroContaContas.setText("Conta:");

        tfCadastroContaContas.setBackground(new java.awt.Color(255, 255, 255));
        tfCadastroContaContas.setForeground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jpCadastroContasLayout = new javax.swing.GroupLayout(jpCadastroContas);
        jpCadastroContas.setLayout(jpCadastroContasLayout);
        jpCadastroContasLayout.setHorizontalGroup(
            jpCadastroContasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpCadastroContasLayout.createSequentialGroup()
                .addGroup(jpCadastroContasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpCadastroContasLayout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(jpCadastroContasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbCodigoContas)
                            .addComponent(lbTipoCadastroContas)
                            .addComponent(tfCodigoContas)
                            .addComponent(cbTipoCadastroContas, 0, 150, Short.MAX_VALUE))
                        .addGap(29, 29, 29)
                        .addGroup(jpCadastroContasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jcbAtivaContas)
                            .addGroup(jpCadastroContasLayout.createSequentialGroup()
                                .addGroup(jpCadastroContasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbBancoContas)
                                    .addComponent(cbBancoContas, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(45, 45, 45)
                                .addGroup(jpCadastroContasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbAgenciaContas)
                                    .addComponent(tfAgenciaContas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(29, 29, 29)
                                .addGroup(jpCadastroContasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbCadastroContaContas)
                                    .addComponent(tfCadastroContaContas, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jpCadastroContasLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lbCadastroContas)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpCadastroContasLayout.setVerticalGroup(
            jpCadastroContasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpCadastroContasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbCadastroContas)
                .addGap(18, 18, 18)
                .addGroup(jpCadastroContasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jpCadastroContasLayout.createSequentialGroup()
                        .addGroup(jpCadastroContasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbCodigoContas)
                            .addComponent(lbBancoContas)
                            .addComponent(lbAgenciaContas))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpCadastroContasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfCodigoContas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbBancoContas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfAgenciaContas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jpCadastroContasLayout.createSequentialGroup()
                        .addComponent(lbCadastroContaContas)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfCadastroContaContas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(lbTipoCadastroContas)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpCadastroContasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbTipoCadastroContas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcbAtivaContas))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jpFiltrosContas.setBackground(new java.awt.Color(255, 255, 255));
        jpFiltrosContas.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(10, 4, 138), 1, true));

        lbFiltrosContas.setBackground(new java.awt.Color(255, 255, 255));
        lbFiltrosContas.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lbFiltrosContas.setForeground(new java.awt.Color(0, 0, 0));
        lbFiltrosContas.setText("Filtros");

        lbBancoFiltrosContas.setBackground(new java.awt.Color(255, 255, 255));
        lbBancoFiltrosContas.setForeground(new java.awt.Color(0, 0, 0));
        lbBancoFiltrosContas.setText("Banco:");

        cbBancoFiltrosContas.setBackground(new java.awt.Color(255, 255, 255));
        cbBancoFiltrosContas.setForeground(new java.awt.Color(0, 0, 0));
        cbBancoFiltrosContas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Bando do Brasil", "Banco Inter", "C6 Bank", "Caixa Econômica Federal", "Bradesco", "Itaú", "Santander", "Nubank", "Sicredi", "Sicoob" }));

        lbTipoFiltroContas.setBackground(new java.awt.Color(255, 255, 255));
        lbTipoFiltroContas.setForeground(new java.awt.Color(0, 0, 0));
        lbTipoFiltroContas.setText("Tipo de Conta:");

        cbTipoFiltroContas.setBackground(new java.awt.Color(255, 255, 255));
        cbTipoFiltroContas.setForeground(new java.awt.Color(0, 0, 0));
        cbTipoFiltroContas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Conta Corrente", "Conta Poupança", "Conta Pagamento", "Conta Salário" }));

        javax.swing.GroupLayout jpFiltrosContasLayout = new javax.swing.GroupLayout(jpFiltrosContas);
        jpFiltrosContas.setLayout(jpFiltrosContasLayout);
        jpFiltrosContasLayout.setHorizontalGroup(
            jpFiltrosContasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpFiltrosContasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpFiltrosContasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbFiltrosContas)
                    .addGroup(jpFiltrosContasLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jpFiltrosContasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbBancoFiltrosContas)
                            .addComponent(cbBancoFiltrosContas, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(73, 73, 73)
                        .addGroup(jpFiltrosContasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbTipoFiltroContas)
                            .addComponent(cbTipoFiltroContas, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(166, Short.MAX_VALUE))
        );
        jpFiltrosContasLayout.setVerticalGroup(
            jpFiltrosContasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpFiltrosContasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbFiltrosContas)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jpFiltrosContasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbBancoFiltrosContas)
                    .addComponent(lbTipoFiltroContas))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpFiltrosContasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbBancoFiltrosContas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbTipoFiltroContas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jpbtCadastroContas.setBackground(new java.awt.Color(255, 255, 255));
        jpbtCadastroContas.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(10, 4, 138), 1, true));

        btSalvarContas.setBackground(new java.awt.Color(10, 4, 138));
        btSalvarContas.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btSalvarContas.setForeground(new java.awt.Color(255, 255, 255));
        btSalvarContas.setText("Salvar");

        btNovoContas.setBackground(new java.awt.Color(10, 4, 138));
        btNovoContas.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btNovoContas.setForeground(new java.awt.Color(255, 255, 255));
        btNovoContas.setText("Novo");
        btNovoContas.addActionListener(this::btNovoContasActionPerformed);

        btEditarContas.setBackground(new java.awt.Color(10, 4, 138));
        btEditarContas.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btEditarContas.setForeground(new java.awt.Color(255, 255, 255));
        btEditarContas.setText("Editar");

        btExcluirContas.setBackground(new java.awt.Color(10, 4, 138));
        btExcluirContas.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btExcluirContas.setForeground(new java.awt.Color(255, 255, 255));
        btExcluirContas.setText("Excluir");

        javax.swing.GroupLayout jpbtCadastroContasLayout = new javax.swing.GroupLayout(jpbtCadastroContas);
        jpbtCadastroContas.setLayout(jpbtCadastroContasLayout);
        jpbtCadastroContasLayout.setHorizontalGroup(
            jpbtCadastroContasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpbtCadastroContasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpbtCadastroContasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btExcluirContas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btEditarContas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btSalvarContas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btNovoContas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jpbtCadastroContasLayout.setVerticalGroup(
            jpbtCadastroContasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpbtCadastroContasLayout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(btSalvarContas)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btNovoContas)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btEditarContas)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btExcluirContas)
                .addContainerGap(50, Short.MAX_VALUE))
        );

        jpbtFiltrosContas.setBackground(new java.awt.Color(255, 255, 255));
        jpbtFiltrosContas.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(10, 4, 138), 1, true));

        btPesquisarContas.setBackground(new java.awt.Color(10, 4, 138));
        btPesquisarContas.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btPesquisarContas.setForeground(new java.awt.Color(255, 255, 255));
        btPesquisarContas.setText("Pesquisar");

        btLimparFiltrosContas.setBackground(new java.awt.Color(10, 4, 138));
        btLimparFiltrosContas.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btLimparFiltrosContas.setForeground(new java.awt.Color(255, 255, 255));
        btLimparFiltrosContas.setText("Limpar Filtros");

        javax.swing.GroupLayout jpbtFiltrosContasLayout = new javax.swing.GroupLayout(jpbtFiltrosContas);
        jpbtFiltrosContas.setLayout(jpbtFiltrosContasLayout);
        jpbtFiltrosContasLayout.setHorizontalGroup(
            jpbtFiltrosContasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpbtFiltrosContasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpbtFiltrosContasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btLimparFiltrosContas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btPesquisarContas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jpbtFiltrosContasLayout.setVerticalGroup(
            jpbtFiltrosContasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpbtFiltrosContasLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(btPesquisarContas)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btLimparFiltrosContas)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jpContasBancarias.setBackground(new java.awt.Color(10, 4, 138));

        tbContasBancarias.setBackground(new java.awt.Color(255, 255, 255));
        tbContasBancarias.setForeground(new java.awt.Color(0, 0, 0));
        tbContasBancarias.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Código", "Banco", "Agência", "Conta", "Tipo", "Ativa"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(tbContasBancarias);

        lbContasBancarias.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lbContasBancarias.setForeground(new java.awt.Color(255, 255, 255));
        lbContasBancarias.setText("Contas Bancárias");

        javax.swing.GroupLayout jpContasBancariasLayout = new javax.swing.GroupLayout(jpContasBancarias);
        jpContasBancarias.setLayout(jpContasBancariasLayout);
        jpContasBancariasLayout.setHorizontalGroup(
            jpContasBancariasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpContasBancariasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpContasBancariasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpContasBancariasLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jScrollPane4))
                    .addGroup(jpContasBancariasLayout.createSequentialGroup()
                        .addComponent(lbContasBancarias)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jpContasBancariasLayout.setVerticalGroup(
            jpContasBancariasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpContasBancariasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbContasBancarias)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jpContasLayout = new javax.swing.GroupLayout(jpContas);
        jpContas.setLayout(jpContasLayout);
        jpContasLayout.setHorizontalGroup(
            jpContasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpContasLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jpContasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpContasLayout.createSequentialGroup()
                        .addComponent(jpContasBancarias, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jpRelatoriosContas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpContasLayout.createSequentialGroup()
                        .addGroup(jpContasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jpCadastroContas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jpFiltrosContas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpContasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jpbtFiltrosContas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jpbtCadastroContas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(67, Short.MAX_VALUE))
        );
        jpContasLayout.setVerticalGroup(
            jpContasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpContasLayout.createSequentialGroup()
                .addContainerGap(31, Short.MAX_VALUE)
                .addGroup(jpContasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jpbtFiltrosContas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jpFiltrosContas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jpContasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jpCadastroContas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jpbtCadastroContas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jpContasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jpRelatoriosContas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jpContasBancarias, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(27, 27, 27))
        );

        tpInicio.addTab("Contas Bancárias e Relatórios", jpContas);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tpInicio)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(tpInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 712, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 6, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tfPesquisaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfPesquisaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfPesquisaActionPerformed

    private void cbAndamentoFiltroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbAndamentoFiltroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbAndamentoFiltroActionPerformed

    private void btNovoBandeirasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btNovoBandeirasActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_btNovoBandeirasActionPerformed

    private void btNovoContasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btNovoContasActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_btNovoContasActionPerformed

    private void cbNivelAcessoOperadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbNivelAcessoOperadorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbNivelAcessoOperadorActionPerformed

    private void cbOperadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbOperadorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbOperadorActionPerformed

    private void carregarVendasPorFormaPagamento() {

    DefaultTableModel modelo =
            new DefaultTableModel();

    modelo.addColumn("Forma de Pagamento");
    modelo.addColumn("Quantidade de Vendas");
    modelo.addColumn("Valor Médio");
    modelo.addColumn("Valor Total");

    VendasController controller =
            new VendasController();

    List<Object[]> lista =
            controller.listarVendasPorFormaPagamento();

    for (Object[] linha : lista) {

        modelo.addRow(linha);

    }

    tbVendasPagamento.setModel(modelo);
}
    
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
        java.awt.EventQueue.invokeLater(() -> new TelaVendas(usuario).setVisible(true));
    }
    
    private void carregarContasBancarias() {

    DefaultTableModel modelo =
            new DefaultTableModel();

    modelo.addColumn("Código");
    modelo.addColumn("Banco");
    modelo.addColumn("Agência");
    modelo.addColumn("Conta");
    modelo.addColumn("Tipo");
    modelo.addColumn("Ativa");

    ContasFinanceirasController controller =
            new ContasFinanceirasController();

    List<ContasFinanceiras> lista =
            controller.listar();

    for (ContasFinanceiras conta : lista) {

        modelo.addRow(new Object[]{

            conta.getIdcontasFinanceiras(),
            conta.getBanco(),
            conta.getAgencia(),
            conta.getNumeroConta(),
            conta.getTipo(),

            conta.getAtivo() == 1
                    ? "Sim"
                    : "Não"

        });

    }

    tbContasBancarias.setModel(modelo);
}
    
//=======================================BOTÕES=======================================
    private void pesquisarVendas(String texto, JTable tabela) {

    DefaultTableModel modelo = new DefaultTableModel();

    modelo.addColumn("ID");
    modelo.addColumn("Data");
    modelo.addColumn("Horário");
    modelo.addColumn("Valor Total");
    modelo.addColumn("Valor Pago");
    modelo.addColumn("Troco");
    modelo.addColumn("Itens");
    modelo.addColumn("Status");
    modelo.addColumn("Cliente");
    modelo.addColumn("Usuário");
    modelo.addColumn("Caixa");
    modelo.addColumn("Cupom");

    VendasController controller = new VendasController();

    List<Vendas> lista = controller.pesquisar(texto);

    for (Vendas venda : lista) {

        modelo.addRow(new Object[]{
            venda.getIdvendas(),
            venda.getDataVenda(),
            venda.getHorarioVenda(),
            venda.getValorTotal(),
            venda.getValorPago(),
            venda.getTroco(),
            venda.getQuantidadeItens(),
            venda.getStatusVenda(),
            venda.getNomeCliente(),
            venda.getNomeUsuario(),
            venda.getNomeCaixa(),
            venda.getCodigoCupom()
        });
    }

    tabela.setModel(modelo);
}
//=======================================BOTÕES=======================================
    
    
    //====================================VARIÁVEIS==========================================
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane JScrollPane;
    private javax.swing.JComboBox<String> bBandeiraFiltrosTaxas;
    private javax.swing.JButton btConfirmarEstorno;
    private javax.swing.JButton btEditarBandeiras;
    private javax.swing.JButton btEditarContas;
    private javax.swing.JButton btEditarParcelamentos;
    private javax.swing.JButton btEditarTaxas;
    private javax.swing.JButton btExcluirBandeiras;
    private javax.swing.JButton btExcluirContas;
    private javax.swing.JButton btExcluirParcelamentos;
    private javax.swing.JButton btExcluirTaxas;
    private javax.swing.JButton btGerarRelatorioContas;
    private javax.swing.JButton btLimparContas;
    private javax.swing.JButton btLimparFiltrosBandeiras;
    private javax.swing.JButton btLimparFiltrosContas;
    private javax.swing.JButton btLimparFiltrosParcelamentos;
    private javax.swing.JButton btLimparFiltrosTaxas;
    private javax.swing.JButton btNovoBandeiras;
    private javax.swing.JButton btNovoContas;
    private javax.swing.JButton btNovoParcelamentos;
    private javax.swing.JButton btNovoTaxas;
    private javax.swing.JButton btPesquisarBandeiras;
    private javax.swing.JButton btPesquisarCancelamento;
    private javax.swing.JButton btPesquisarComprovante;
    private javax.swing.JButton btPesquisarConsulta;
    private javax.swing.JButton btPesquisarContas;
    private javax.swing.JButton btPesquisarEstorno;
    private javax.swing.JButton btPesquisarFiltro;
    private javax.swing.JButton btPesquisarOperador;
    private javax.swing.JButton btPesquisarPagamento;
    private javax.swing.JButton btPesquisarParcelamentos;
    private javax.swing.JButton btPesquisarProduto;
    private javax.swing.JButton btPesquisarTaxas;
    private javax.swing.JButton btSalvarBandeiras;
    private javax.swing.JButton btSalvarContas;
    private javax.swing.JButton btSalvarParcelamentos;
    private javax.swing.JButton btSalvarTaxas;
    private javax.swing.JComboBox<String> cbAndamentoFiltro;
    private javax.swing.JComboBox<String> cbBancoContas;
    private javax.swing.JComboBox<String> cbBancoFiltrosContas;
    private javax.swing.JComboBox<String> cbBandeiraCadastroParcelamentos;
    private javax.swing.JComboBox<String> cbBandeiraCadastroTaxas;
    private javax.swing.JComboBox<String> cbBandeiraParcelamentos;
    private javax.swing.JComboBox<String> cbCategoriaPagamento;
    private javax.swing.JComboBox<String> cbFormaPagamento;
    private javax.swing.JComboBox<String> cbFormaPagamentoCadastroParcelamentos;
    private javax.swing.JComboBox<String> cbFormaPagamentoCadastroTaxas;
    private javax.swing.JComboBox<String> cbFormaPagamentoFiltrosTaxas;
    private javax.swing.JComboBox<String> cbFormaPagamentoParcelamentos;
    private javax.swing.JComboBox<String> cbFormaPgFiltro;
    private javax.swing.JComboBox<String> cbMotivoEstorno;
    private javax.swing.JComboBox<String> cbNParcelasParcelamentos;
    private javax.swing.JComboBox<String> cbNivelAcessoOperador;
    private javax.swing.JComboBox<String> cbOperador;
    private javax.swing.JComboBox<String> cbTaxaJurosParcelamentos;
    private javax.swing.JComboBox<String> cbTipoBandeiras;
    private javax.swing.JComboBox<String> cbTipoCadastroBandeiras;
    private javax.swing.JComboBox<String> cbTipoCadastroContas;
    private javax.swing.JComboBox<String> cbTipoFiltroContas;
    private javax.swing.JComboBox<String> cbTipoRelatorioContas;
    private javax.swing.JComboBox<String> cbTipoTaxas;
    private javax.swing.JFormattedTextField ftfDataFinalFiltro;
    private javax.swing.JFormattedTextField ftfDataFinalPagamento;
    private javax.swing.JFormattedTextField ftfDataInicialFiltro;
    private javax.swing.JFormattedTextField ftfDataInicialPagamento;
    private javax.swing.JFormattedTextField ftfPeriodoFinalContas;
    private javax.swing.JFormattedTextField ftfPeriodoInicialContas;
    private javax.swing.JFormattedTextField ftxDataVenda;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JCheckBox jbcAtivoCadastroParcelamentos;
    private javax.swing.JCheckBox jcbAtivaCadastroBandeiras;
    private javax.swing.JCheckBox jcbAtivaContas;
    private javax.swing.JCheckBox jcbAtivoTaxas;
    private javax.swing.JCheckBox jcbDesativaCadastroBandeiras;
    private javax.swing.JPanel jpBandeiras;
    private javax.swing.JPanel jpCabecalhoConsulta;
    private javax.swing.JPanel jpCabecalhoDadosVenda;
    private javax.swing.JPanel jpCabecalhoOperador;
    private javax.swing.JPanel jpCabecalhoPeriodoVendas;
    private javax.swing.JPanel jpCabecalhoProduto;
    private javax.swing.JPanel jpCadastroBandeiras;
    private javax.swing.JPanel jpCadastroContas;
    private javax.swing.JPanel jpCadastroParcelamentos;
    private javax.swing.JPanel jpCadastroTaxas;
    private javax.swing.JPanel jpCancelamento;
    private javax.swing.JPanel jpCategoria;
    private javax.swing.JPanel jpComprovante;
    private javax.swing.JPanel jpConsulta;
    private javax.swing.JPanel jpContas;
    private javax.swing.JPanel jpContasBancarias;
    private javax.swing.JPanel jpDadosEstorno;
    private javax.swing.JPanel jpDadosVendaEstorno;
    private javax.swing.JPanel jpDetalhesVenda;
    private javax.swing.JPanel jpEstorno;
    private javax.swing.JPanel jpFiltros;
    private javax.swing.JPanel jpFiltrosBandeiras;
    private javax.swing.JPanel jpFiltrosContas;
    private javax.swing.JPanel jpFiltrosPagamento;
    private javax.swing.JPanel jpFiltrosParcelamentos;
    private javax.swing.JPanel jpFiltrosTaxas;
    private javax.swing.JPanel jpItensVenda;
    private javax.swing.JPanel jpItensVendaCancelamento;
    private javax.swing.JPanel jpItensVendaComprovante;
    private javax.swing.JPanel jpItensVendaEstorno;
    private javax.swing.JPanel jpOperador;
    private javax.swing.JPanel jpParcelamentos;
    private javax.swing.JPanel jpProduto;
    private javax.swing.JPanel jpRelatoriosContas;
    private javax.swing.JPanel jpTabVendas;
    private javax.swing.JPanel jpTaxas;
    private javax.swing.JPanel jpTotalVendaCancelamento;
    private javax.swing.JPanel jpTotalVendaEstorno;
    private javax.swing.JPanel jpTotalVendasComprovante;
    private javax.swing.JPanel jpVendasCadastradas;
    private javax.swing.JPanel jpVendasCadastradasProduto;
    private javax.swing.JPanel jpVendasOperador;
    private javax.swing.JPanel jpVendasPagamento;
    private javax.swing.JPanel jpbtCadastroBandeiras;
    private javax.swing.JPanel jpbtCadastroContas;
    private javax.swing.JPanel jpbtCadastroParcelamentos;
    private javax.swing.JPanel jpbtCadastroTaxas;
    private javax.swing.JPanel jpbtFiltrosBandeiras;
    private javax.swing.JPanel jpbtFiltrosContas;
    private javax.swing.JPanel jpbtFiltrosParcelamentos;
    private javax.swing.JPanel jpbtFiltrosTaxas;
    private javax.swing.JLabel lbAgenciaContas;
    private javax.swing.JLabel lbAndamentoFiltro;
    private javax.swing.JLabel lbAndamentoVenda;
    private javax.swing.JLabel lbBancoContas;
    private javax.swing.JLabel lbBancoFiltrosContas;
    private javax.swing.JLabel lbBandeiraCadastroParcelamentos;
    private javax.swing.JLabel lbBandeiraCadastroTaxas;
    private javax.swing.JLabel lbBandeiraFiltrosTaxas;
    private javax.swing.JLabel lbBandeiraParcelamentos;
    private javax.swing.JLabel lbCadastroBandeiras;
    private javax.swing.JLabel lbCadastroContaContas;
    private javax.swing.JLabel lbCadastroContas;
    private javax.swing.JLabel lbCadastroParcelamentos;
    private javax.swing.JLabel lbCadastroTaxas;
    private javax.swing.JLabel lbCategoriaPagamento;
    private javax.swing.JLabel lbCategoriaProduto;
    private javax.swing.JLabel lbClienteVenda;
    private javax.swing.JLabel lbCodigoBandeiras;
    private javax.swing.JLabel lbCodigoContas;
    private javax.swing.JLabel lbCodigoParcelamentos;
    private javax.swing.JLabel lbCodigoProduto;
    private javax.swing.JLabel lbCodigoTaxas;
    private javax.swing.JLabel lbConsultaVenda;
    private javax.swing.JLabel lbContasBancarias;
    private javax.swing.JLabel lbDadosEstorno;
    private javax.swing.JLabel lbDadosOperador;
    private javax.swing.JLabel lbDadosProduto;
    private javax.swing.JLabel lbDadosVendaCancelamento;
    private javax.swing.JLabel lbDadosVendaComprovante;
    private javax.swing.JLabel lbDadosVendaEstorno;
    private javax.swing.JLabel lbDataFinalFiltros;
    private javax.swing.JLabel lbDataFinalPagamento;
    private javax.swing.JLabel lbDataHoraCancelamento;
    private javax.swing.JLabel lbDataHoraComprovante;
    private javax.swing.JLabel lbDataHoraEstorno;
    private javax.swing.JLabel lbDataInicialFiltro;
    private javax.swing.JLabel lbDataInicialPagamento;
    private javax.swing.JLabel lbDataVenda;
    private javax.swing.JLabel lbDescricaoBandeiras;
    private javax.swing.JLabel lbDescricaoCadastroBandeiras;
    private javax.swing.JLabel lbDetalheVenda;
    private javax.swing.JLabel lbEstoqueAtualProduto;
    private javax.swing.JLabel lbFiltrosBandeiras;
    private javax.swing.JLabel lbFiltrosContas;
    private javax.swing.JLabel lbFiltrosPagamento;
    private javax.swing.JLabel lbFiltrosParcelamentos;
    private javax.swing.JLabel lbFiltrosTaxas;
    private javax.swing.JLabel lbFormaPagamento;
    private javax.swing.JLabel lbFormaPagamentoCadastroTaxas;
    private javax.swing.JLabel lbFormaPagamentoCancelamento;
    private javax.swing.JLabel lbFormaPagamentoComprovante;
    private javax.swing.JLabel lbFormaPagamentoEstorno;
    private javax.swing.JLabel lbFormaPagamentoFiltrosTaxas;
    private javax.swing.JLabel lbFormaPagamentos;
    private javax.swing.JLabel lbFormasPagamentoParcelamentos;
    private javax.swing.JLabel lbFormasPgFiltro;
    private javax.swing.JLabel lbItensVenda;
    private javax.swing.JLabel lbItensVendaCancelamento;
    private javax.swing.JLabel lbItensVendaComprovante;
    private javax.swing.JLabel lbItensVendaEstorno;
    private javax.swing.JLabel lbLoginOperador;
    private javax.swing.JLabel lbMotivoEstorno;
    private javax.swing.JLabel lbNParcelasParcelamentos;
    private javax.swing.JLabel lbNVendaCancelamento;
    private javax.swing.JLabel lbNVendaComprovante;
    private javax.swing.JLabel lbNVendaEstorno;
    private javax.swing.JLabel lbNivelAcessoOperador;
    private javax.swing.JLabel lbNomeOperador;
    private javax.swing.JLabel lbNomeProduto;
    private javax.swing.JLabel lbNumeroVenda;
    private javax.swing.JLabel lbObservacaoEstorno;
    private javax.swing.JLabel lbOperador;
    private javax.swing.JLabel lbOperadorCancelamento;
    private javax.swing.JLabel lbOperadorComprovante;
    private javax.swing.JLabel lbOperadorEstorno;
    private javax.swing.JLabel lbOperadorVenda;
    private javax.swing.JLabel lbPagamentoVenda;
    private javax.swing.JLabel lbParcelasVenda;
    private javax.swing.JLabel lbPeriodoContas;
    private javax.swing.JLabel lbPeriodoVendas;
    private javax.swing.JLabel lbProduto;
    private javax.swing.JLabel lbQuantVendasOperador;
    private javax.swing.JLabel lbQuantVendidaProduto;
    private javax.swing.JLabel lbRelatoriosContas;
    private javax.swing.JLabel lbTaxaJurosParcelamentos;
    private javax.swing.JLabel lbTipoBandeiras;
    private javax.swing.JLabel lbTipoCadastroBandeiras;
    private javax.swing.JLabel lbTipoCadastroContas;
    private javax.swing.JLabel lbTipoFiltroContas;
    private javax.swing.JLabel lbTipoRelatorioContas;
    private javax.swing.JLabel lbTipoTaxas;
    private javax.swing.JLabel lbTotalVendaCancelamento;
    private javax.swing.JLabel lbTotalVendaComprovante;
    private javax.swing.JLabel lbTotalVendaEstorno;
    private javax.swing.JLabel lbValorTaxas;
    private javax.swing.JLabel lbValorTotalOperador;
    private javax.swing.JLabel lbValorTotalProduto;
    private javax.swing.JLabel lbValorUnitarioProduto;
    private javax.swing.JLabel lbValorVenda;
    private javax.swing.JLabel lbVendasCadastradas;
    private javax.swing.JLabel lbVendasCadastradasProduto;
    private javax.swing.JLabel lbVendasFiltros;
    private javax.swing.JLabel lbVendasOperador;
    private javax.swing.JLabel lbVendasPagamento;
    private javax.swing.JLabel lbVendasProduto;
    private javax.swing.JLabel lbVendasRealizadasOperador;
    private javax.swing.JLabel lvValorEstorno;
    private javax.swing.JTable tbContasBancarias;
    private javax.swing.JTable tbItensVendaCancelamento;
    private javax.swing.JTable tbItensVendaComprovante;
    private javax.swing.JTable tbItensVendaConsulta;
    private javax.swing.JTable tbItensVendaEstorno;
    private javax.swing.JTable tbVendasCadastradasConsulta;
    private javax.swing.JTable tbVendasCadastradasProduto;
    private javax.swing.JTable tbVendasFiltro;
    private javax.swing.JTable tbVendasOperador;
    private javax.swing.JTable tbVendasPagamento;
    private javax.swing.JTextField tfAgenciaContas;
    private javax.swing.JTextField tfAndamentoVenda;
    private javax.swing.JTextField tfCadastroContaContas;
    private javax.swing.JTextField tfCategoriaProduto;
    private javax.swing.JTextField tfClienteVenda;
    private javax.swing.JTextField tfCodigoBandeiras;
    private javax.swing.JTextField tfCodigoContas;
    private javax.swing.JTextField tfCodigoParcelamentos;
    private javax.swing.JTextField tfCodigoProduto;
    private javax.swing.JTextField tfCodigoTaxas;
    private javax.swing.JTextField tfDataHoraCancelamento;
    private javax.swing.JTextField tfDataHoraComprovante;
    private javax.swing.JTextField tfDataHoraEstorno;
    private javax.swing.JTextField tfDescricaoBandeiras;
    private javax.swing.JTextField tfDescricaoCadastroBandeiras;
    private javax.swing.JTextField tfEstoqueAtualProduto;
    private javax.swing.JTextField tfFormaPagamentoCancelamento;
    private javax.swing.JTextField tfFormaPagamentoComprovante;
    private javax.swing.JTextField tfFormaPagamentoEstorno;
    private javax.swing.JTextField tfLoginOperador;
    private javax.swing.JTextField tfNVendaComprovante;
    private javax.swing.JTextField tfNVendaEstorno;
    private javax.swing.JTextField tfNomeOperador;
    private javax.swing.JTextField tfNomeProduto;
    private javax.swing.JTextField tfNumeroVenda;
    private javax.swing.JTextField tfObservacaoEstorno;
    private javax.swing.JTextField tfOperadorCancelamento;
    private javax.swing.JTextField tfOperadorComprovante;
    private javax.swing.JTextField tfOperadorEstorno;
    private javax.swing.JTextField tfOperadorVenda;
    private javax.swing.JTextField tfPagamentoVenda;
    private javax.swing.JTextField tfParcelasVenda;
    private javax.swing.JTextField tfPesquisa;
    private javax.swing.JTextField tfProduto;
    private javax.swing.JTextField tfQuantVendasOperador;
    private javax.swing.JTextField tfQuantVendidaProduto;
    private javax.swing.JTextField tfTotalVendaCancelamento;
    private javax.swing.JTextField tfTotalVendaComprovante;
    private javax.swing.JTextField tfTotalVendaEstorno;
    private javax.swing.JTextField tfValorEstorno;
    private javax.swing.JTextField tfValorTaxas;
    private javax.swing.JTextField tfValorTotalOperador;
    private javax.swing.JTextField tfValorTotalProduto;
    private javax.swing.JTextField tfValorUnitarioProduto;
    private javax.swing.JTextField tfValorVenda;
    private javax.swing.JTextField tfVendaCancelamento;
    private javax.swing.JTabbedPane tpInicio;
    // End of variables declaration//GEN-END:variables
}