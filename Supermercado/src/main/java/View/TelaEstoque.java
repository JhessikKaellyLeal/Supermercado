/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import Conection.Conexao;
import Control.CategoriasController;
import Control.EstoqueController;
import Control.MarcasController;
import Control.MovimentacaoEstoqueController;
import Control.ProdutosController;
import Control.UnidadeMedidasController;
import Model.Categorias;
import Model.Estoque;
import Model.Marcas;
import Model.MovimentacaoEstoque;
import Model.PerdasEstoque;
import Model.Produtos;
import Model.UnidadesMedidas;
import Model.Usuario;
import Control.PerdasEstoqueController;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author GeovannaOliviera
 */
public class TelaEstoque extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(TelaEstoque.class.getName());
    private MenuPadrao menu;
     private Usuario usuario;
    private List<Produtos> listaProdutosCE = new ArrayList<>();
    private List<Categorias> listaCategoriasCE = new ArrayList<>();
    private List<Marcas> listaMarcasCE = new ArrayList<>();
    private List<UnidadesMedidas> listaUnidadesCE = new ArrayList<>();
    


    public TelaEstoque(Usuario usuario) {
        initComponents();
        
         listarTiposMovimentacao();
    listarMotivosPerda();
    listarProdutosCE();
    listarCategoriasCE();
    listarMarcasCE();
    listarUnidadesCE();
    listarProdutosCE();
    listarCategoriasCE();
    listarMarcasCE();
    listarUnidadesCE();
    listarTodasMovimentacoes();
    listarTodosEstoque();
     listarTodasPerdas();
    
    
        this.usuario= usuario;
        this.menu=MenuPadrao.instalar(
        this,
        usuario,
        "estoque",
        this::navegar,
        this::dispose
    );     
     this.setExtendedState(TelaEstoque.MAXIMIZED_BOTH);
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

// ------------------- LISTAR NA COMBOBOX -----------------------------------------------

   private void listarProdutosCE() {

    try {

        ProdutosController controller = new ProdutosController();

        listaProdutosCE = controller.listar();

        cbProdutoCE.removeAllItems();
         cbProdutoPE.removeAllItems();
          cbProdutoME.removeAllItems();

        for (Produtos p : listaProdutosCE) {
            cbProdutoCE.addItem(p.getNome());
            cbProdutoPE.addItem(p.getNome());
            cbProdutoME.addItem(p.getNome());
        }

        cbProdutoPE.setSelectedIndex(-1);
        cbProdutoME.setSelectedIndex(-1);
        cbProdutoCE.setSelectedIndex(-1);

    } catch (SQLException e) {

        JOptionPane.showMessageDialog(this,
                "Erro ao listar produtos: " + e.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE);
    }
}
    private void listarCategoriasCE() {

    try {

        CategoriasController controller = new CategoriasController();

        listaCategoriasCE = controller.listar();
        
        cbCategoriaCE.removeAllItems();
        cbCategoriaME.removeAllItems();
        cbCategoriaPE.removeAllItems();

        for (Categorias c : listaCategoriasCE) {
            cbCategoriaCE.addItem(c.getNome());
            cbCategoriaPE.addItem(c.getNome());
            cbCategoriaME.addItem(c.getNome());
                       
        }

        cbCategoriaME.setSelectedIndex(-1);
        cbCategoriaPE.setSelectedIndex(-1);
        cbCategoriaCE.setSelectedIndex(-1);

    } catch (SQLException e) {

        JOptionPane.showMessageDialog(this,
                "Erro ao listar categorias: " + e.getMessage());
    }
}
    private void listarMarcasCE() {

    try {

        MarcasController controller = new MarcasController();

        listaMarcasCE = controller.listar();

        cbMarcaME.removeAllItems();
         cbMarcaPE.removeAllItems();
          cbMarcaCE.removeAllItems();

        for (Marcas m : listaMarcasCE) {
            cbMarcaME.addItem(m.getNome());
            cbMarcaPE.addItem(m.getNome());
            cbMarcaCE.addItem(m.getNome());
        }

        cbMarcaME.setSelectedIndex(-1);
        cbMarcaPE.setSelectedIndex(-1);
        cbMarcaCE.setSelectedIndex(-1);

    } catch (SQLException e) {

        JOptionPane.showMessageDialog(this,
                "Erro ao listar marcas: " + e.getMessage());
    }
}
    private void listarUnidadesCE() {

    try {

        UnidadeMedidasController controller = new UnidadeMedidasController();

        listaUnidadesCE = controller.listar();

        cbUnidadeMedidaME.removeAllItems();
        cbUnidadeMedidaPE.removeAllItems();
        cbUnidadeMedidaCE.removeAllItems();

        for (UnidadesMedidas u : listaUnidadesCE) {
            cbUnidadeMedidaME.addItem(u.getNome());
            cbUnidadeMedidaPE.addItem(u.getNome());
            cbUnidadeMedidaCE.addItem(u.getNome());
        }

        cbUnidadeMedidaME.setSelectedIndex(-1);
        cbUnidadeMedidaPE.setSelectedIndex(-1);
        cbUnidadeMedidaCE.setSelectedIndex(-1);

    } catch (SQLException e) {

        JOptionPane.showMessageDialog(this,
                "Erro ao listar unidades de medida: " + e.getMessage());
    }
}
    private void listarTiposMovimentacao() {

    cbTipoMovimentacaoME.removeAllItems();

    cbTipoMovimentacaoME.addItem("Entrada");
    cbTipoMovimentacaoME.addItem("Saída");
    cbTipoMovimentacaoME.addItem("Ajuste de Estoque");

    cbTipoMovimentacaoME.setSelectedIndex(-1);
} 
    private void listarMotivosPerda() {

    cbMotivoPE.removeAllItems();

    cbMotivoPE.addItem("Produto Vencido");
    cbMotivoPE.addItem("Produto Danificado");
    cbMotivoPE.addItem("Produto Quebrado");
    cbMotivoPE.addItem("Produto Estragado");
    cbMotivoPE.addItem("Contaminação");
    cbMotivoPE.addItem("Perda Operacional");

    cbMotivoPE.setSelectedIndex(-1);
}


 // ---------------------- LISTAR NAS TABELAS ----------------------------------------   
    
  
    private void listarPerdasPorProduto(String nomeProduto) {

    try {

        PerdasEstoqueController controller = new PerdasEstoqueController();

        List<PerdasEstoque> listaPerdasEstoque = controller.listarPorNomeProduto(nomeProduto);

        DefaultTableModel modelo = (DefaultTableModel) tabelaPerdasEstoque.getModel();

        modelo.setRowCount(0);

        for (PerdasEstoque perda : listaPerdasEstoque) {

            modelo.addRow(new Object[]{
                perda.getIdperdasEstoque(),
                perda.getProdutos_idprodutos(),
                perda.getQuantidade(),
                perda.getDataRegistro(),
                perda.getMotivo()
            });

        }

        if (listaPerdasEstoque.isEmpty()) {

            JOptionPane.showMessageDialog(
                this,
                "Nenhuma perda encontrada para este produto!",
                "Pesquisa",
                JOptionPane.INFORMATION_MESSAGE
            );

        }

    } catch (SQLException e) {

        JOptionPane.showMessageDialog(
            this,
            "Erro ao listar perdas: " + e.getMessage(),
            "Erro",
            JOptionPane.ERROR_MESSAGE
        );

    }
}
    
    
    
    private void listarConsultaEstoquePorProduto(String nomeProduto) {

    try {

        EstoqueController controller = new EstoqueController();

        List<Estoque> listaConsultaEstoque =
                controller.listarPorNomeProduto(nomeProduto);

        DefaultTableModel modelo =
                (DefaultTableModel) tabelaConsultaEstoque.getModel();

        modelo.setRowCount(0);

        for (Estoque estoque : listaConsultaEstoque) {

            modelo.addRow(new Object[]{
                estoque.getIdestoque(),
                estoque.getQuantidadeAtual(),
                estoque.getQuantidadeReservada(),
                estoque.getDataAtualizacao(),
                estoque.getProdutos_idprodutos(),
                estoque.getProdutos_categorias_idcategorias(),
                estoque.getProdutos_marcas_idmarcas(),
                estoque.getProdutos_unidadeMedidas_idunidadeMedidas()
            });

        }

        if (listaConsultaEstoque.isEmpty()) {

            JOptionPane.showMessageDialog(
                this,
                "Nenhum estoque encontrado para este produto!",
                "Pesquisa",
                JOptionPane.INFORMATION_MESSAGE
            );

        }

    } catch (SQLException e) {

        JOptionPane.showMessageDialog(
            this,
            "Erro ao listar estoque: " + e.getMessage(),
            "Erro",
            JOptionPane.ERROR_MESSAGE
        );

    }
}
    
    
private void listarTodasMovimentacoes() {
    try {
        MovimentacaoEstoqueController controller =
                new MovimentacaoEstoqueController();

        List<MovimentacaoEstoque> lista = controller.listar();

        DefaultTableModel modelo =
                (DefaultTableModel) tabelaMovimentacaoEstoque.getModel();

        modelo.setRowCount(0);

        for (MovimentacaoEstoque movimentacao : lista) {
            modelo.addRow(new Object[]{
                movimentacao.getIdmovimentacaoEstoque(),
                movimentacao.getTipoMovimentacao(),
                movimentacao.getQuantidade(),
                movimentacao.getDataMovimentacao(),
                movimentacao.getHoraMovimentacao(),
                movimentacao.getProdutos_idprodutos(),
                movimentacao.getProdutos_categorias_idcategorias(),
                movimentacao.getProdutos_marcas_idmarcas(),
                movimentacao.getProdutos_unidadeMedidas_idunidadeMedidas()
            });
        }

        if (lista.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Nenhuma movimentação cadastrada!",
                    "Consulta",
                    JOptionPane.INFORMATION_MESSAGE);
        }

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this,
                "Erro ao listar movimentações: " + e.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE);
    }
} 
 
 private void listarTodosEstoque() {

    try {
        EstoqueController controller = new EstoqueController();

        List<Estoque> listaEstoque = controller.listar();

        DefaultTableModel modelo =
                (DefaultTableModel) tabelaConsultaEstoque.getModel();

        modelo.setRowCount(0);

        for (Estoque estoque : listaEstoque) {

            modelo.addRow(new Object[]{
                estoque.getIdestoque(),
                estoque.getQuantidadeAtual(),
                estoque.getQuantidadeReservada(),
                estoque.getDataAtualizacao(),
                estoque.getProdutos_idprodutos(),
                estoque.getProdutos_categorias_idcategorias(),
                estoque.getProdutos_marcas_idmarcas(),
                estoque.getProdutos_unidadeMedidas_idunidadeMedidas()
            });
        }

        if (listaEstoque.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Nenhum registro de estoque encontrado!",
                    "Consulta de Estoque",
                    JOptionPane.INFORMATION_MESSAGE);
        }

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this,
                "Erro ao listar estoque: " + e.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE);
    }
}
 
 private void listarTodasPerdas() {

    try {
        PerdasEstoqueController controller =
                new PerdasEstoqueController();

        List<PerdasEstoque> lista = controller.listar();

        DefaultTableModel modelo =
                (DefaultTableModel) tabelaPerdasEstoque.getModel();

        modelo.setRowCount(0);

        for (PerdasEstoque perda : lista) {

            modelo.addRow(new Object[]{
                perda.getIdperdasEstoque(),
                perda.getProdutos_idprodutos(),
                perda.getQuantidade(),
                perda.getDataRegistro(),
                perda.getMotivo()
            });
        }

        if (lista.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Nenhuma perda de estoque cadastrada!",
                    "Consulta",
                    JOptionPane.INFORMATION_MESSAGE);
        }

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this,
                "Erro ao listar perdas de estoque: " + e.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE);
    }
}
// ------------ LIMPAR ----------------------------------------------------------------
  private void limparCamposCE() {

    // Limpar JSpinner
    spQuantidadeAtual.setValue(0);
    spQuantidadeReservada.setValue(0);

    // Limpar JFormattedTextField
    ftfDataAtualizacaoCE.setValue(null);

    // Limpar JComboBox
    cbProdutoCE.setSelectedIndex(-1);
    cbCategoriaCE.setSelectedIndex(-1);
    cbMarcaCE.setSelectedIndex(-1);
    cbUnidadeMedidaCE.setSelectedIndex(-1);

    // Limpar JTextField
    tfIDEstoque.setText("");
}  

// ------------------ CADASTRAR ---------------------------------------------------------

private void cadastrarEstoque() {

    try {

        // Capturar os valores dos campos
        int quantidadeAtual = Integer.parseInt(
                spQuantidadeAtual.getValue().toString()
        );

        int quantidadeReservada = Integer.parseInt(
                spQuantidadeReservada.getValue().toString()
        );

        String dataAtualizacao = ftfDataAtualizacaoCE.getText().trim();

        // Capturar os IDs selecionados
        int idProduto = Integer.parseInt(
                cbProdutoCE.getSelectedItem().toString()
        );

        int idCategoria = Integer.parseInt(
                cbCategoriaCE.getSelectedItem().toString()
        );

        int idMarca = Integer.parseInt(
                cbMarcaCE.getSelectedItem().toString()
        );

        int idUnidadeMedida = Integer.parseInt(
                cbUnidadeMedidaCE.getSelectedItem().toString()
        );

        // Validar campos obrigatórios
        if (dataAtualizacao.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Preencha todos os campos obrigatórios!",
                    "Atenção",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        // Validar quantidades
        if (quantidadeAtual < 0 || quantidadeReservada < 0) {

            JOptionPane.showMessageDialog(
                    this,
                    "As quantidades não podem ser negativas!",
                    "Atenção",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        // Criar o Model
        Estoque estoque = new Estoque();

        estoque.setQuantidadeAtual(quantidadeAtual);
        estoque.setQuantidadeReservada(quantidadeReservada);
        estoque.setDataAtualizacao(dataAtualizacao);
        estoque.setProdutos_idprodutos(idProduto);
        estoque.setProdutos_categorias_idcategorias(idCategoria);
        estoque.setProdutos_marcas_idmarcas(idMarca);
        estoque.setProdutos_unidadeMedidas_idunidadeMedidas(idUnidadeMedida);

        // Chamar o Controller
        EstoqueController controller = new EstoqueController();

        controller.cadastrar(estoque);

        JOptionPane.showMessageDialog(
                this,
                "Estoque cadastrado com sucesso!",
                "Sucesso",
                JOptionPane.INFORMATION_MESSAGE
        );

        limparCamposCE();

    } catch (NumberFormatException e) {

        JOptionPane.showMessageDialog(
                this,
                "Verifique as quantidades e os IDs selecionados!",
                "Atenção",
                JOptionPane.WARNING_MESSAGE
        );

    } catch (Exception e) {

        JOptionPane.showMessageDialog(
                this,
                "Erro ao cadastrar estoque: " + e.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE
        );
    }
}

private void listarMovimentacoesPorProduto() {

    try {

        // Capturar o produto selecionado
        if (cbProdutoME.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(
                    this,
                    "Selecione um produto!",
                    "Atenção",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        String nomeProduto = cbProdutoME.getSelectedItem().toString();

        // Consultar movimentações
        MovimentacaoEstoqueController controller =
                new MovimentacaoEstoqueController();

        List<MovimentacaoEstoque> lista =
                controller.listarMovimentacoesPorProduto(nomeProduto);

        // Carregar tabela
        DefaultTableModel modelo =
                (DefaultTableModel) tabelaMovimentacaoEstoque.getModel();

        modelo.setRowCount(0);

        for (MovimentacaoEstoque movimentacao : lista) {

            modelo.addRow(new Object[]{
                movimentacao.getIdmovimentacaoEstoque(),
                movimentacao.getTipoMovimentacao(),
                movimentacao.getQuantidade(),
                movimentacao.getDataMovimentacao(),
                movimentacao.getHoraMovimentacao(),
                movimentacao.getProdutos_idprodutos(),
                movimentacao.getProdutos_categorias_idcategorias(),
                movimentacao.getProdutos_marcas_idmarcas(),
                movimentacao.getProdutos_unidadeMedidas_idunidadeMedidas()
            });
        }

        if (lista.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Nenhuma movimentação encontrada para esse produto!",
                    "Consulta",
                    JOptionPane.INFORMATION_MESSAGE
            );
        }

    } catch (SQLException e) {

        JOptionPane.showMessageDialog(
                this,
                "Erro ao consultar movimentações: " + e.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE
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

        tpnTelaEstoque = new javax.swing.JTabbedPane();
        pnConsultaEstoque = new javax.swing.JPanel();
        pnConsultaE = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jlEstoque = new javax.swing.JLabel();
        tfIDEstoque = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        cbUnidadeMedidaCE = new javax.swing.JComboBox<>();
        jlProdutoCE = new javax.swing.JLabel();
        cbProdutoCE = new javax.swing.JComboBox<>();
        jlQuantidadeAtual = new javax.swing.JLabel();
        spQuantidadeAtual = new javax.swing.JSpinner();
        jlQuantidadeReservadaCE = new javax.swing.JLabel();
        spQuantidadeReservada = new javax.swing.JSpinner();
        jlCategoriaCE = new javax.swing.JLabel();
        cbCategoriaCE = new javax.swing.JComboBox<>();
        jlMarca = new javax.swing.JLabel();
        cbMarcaCE = new javax.swing.JComboBox<>();
        jlDataAtualizacao = new javax.swing.JLabel();
        ftfDataAtualizacaoCE = new javax.swing.JFormattedTextField();
        btnPesquisarCE = new javax.swing.JButton();
        btnEditarCE = new javax.swing.JButton();
        btnExcluirCE = new javax.swing.JButton();
        btnLimparCE = new javax.swing.JButton();
        jScrollPane18 = new javax.swing.JScrollPane();
        tabelaConsultaEstoque = new javax.swing.JTable();
        jlConsultaEstoqueCE = new javax.swing.JLabel();
        btnSalvarCE = new javax.swing.JButton();
        pnMovimentacaoEstoque = new javax.swing.JPanel();
        pnMovimentacaoE = new javax.swing.JPanel();
        pnME = new javax.swing.JPanel();
        jlMovimentacao = new javax.swing.JLabel();
        tfMovimentacao = new javax.swing.JTextField();
        jlUnidadeM = new javax.swing.JLabel();
        jlTipoM = new javax.swing.JLabel();
        cbTipoMovimentacaoME = new javax.swing.JComboBox<>();
        jlDataM = new javax.swing.JLabel();
        ftfDataMovimentacao = new javax.swing.JFormattedTextField();
        jlProdutoME = new javax.swing.JLabel();
        cbProdutoME = new javax.swing.JComboBox<>();
        jlHoraMovimentacao = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cbCategoriaME = new javax.swing.JComboBox<>();
        ftfHoraMovimentacaoME = new javax.swing.JFormattedTextField();
        ftfHoraMovimentacaoATEME = new javax.swing.JFormattedTextField();
        jlMarcaME = new javax.swing.JLabel();
        cbMarcaME = new javax.swing.JComboBox<>();
        jlDocumentoReferencia = new javax.swing.JLabel();
        tfDocumentoReferenciaME = new javax.swing.JTextField();
        btnPesquisarME = new javax.swing.JButton();
        cbUnidadeMedidaME = new javax.swing.JComboBox<>();
        btnSalvarME = new javax.swing.JButton();
        btnEditarME = new javax.swing.JButton();
        btnExcluirME = new javax.swing.JButton();
        btnLimparME = new javax.swing.JButton();
        jScrollPane19 = new javax.swing.JScrollPane();
        tabelaMovimentacaoEstoque = new javax.swing.JTable();
        jlMovimentacaodeEstoque = new javax.swing.JLabel();
        pnPerdasEstoque = new javax.swing.JPanel();
        pnPerdasEs = new javax.swing.JPanel();
        jpnPerdasEstoque = new javax.swing.JPanel();
        jlPerdasPE = new javax.swing.JLabel();
        jlMarcasPE = new javax.swing.JLabel();
        tfPerdasPE = new javax.swing.JTextField();
        cbMarcaPE = new javax.swing.JComboBox<>();
        jlDataRegistroPE = new javax.swing.JLabel();
        jlUnidadeMedidaPE = new javax.swing.JLabel();
        ftfDataRegistroPE = new javax.swing.JFormattedTextField();
        cbUnidadeMedidaPE = new javax.swing.JComboBox<>();
        jlMotivoPE = new javax.swing.JLabel();
        jlProdutoPE = new javax.swing.JLabel();
        cbMotivoPE = new javax.swing.JComboBox<>();
        cbProdutoPE = new javax.swing.JComboBox<>();
        jlCategoriaPE = new javax.swing.JLabel();
        cbCategoriaPE = new javax.swing.JComboBox<>();
        btnPesquisarPE = new javax.swing.JButton();
        jlLote = new javax.swing.JLabel();
        tfLotePE = new javax.swing.JTextField();
        btnSalvarPE = new javax.swing.JButton();
        btnEditarPE = new javax.swing.JButton();
        btnExcluirPE = new javax.swing.JButton();
        btnLimparPE = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaPerdasEstoque = new javax.swing.JTable();
        jlPerdasDeEstoque = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tpnTelaEstoque.setBackground(new java.awt.Color(10, 4, 138));
        tpnTelaEstoque.setForeground(new java.awt.Color(255, 255, 255));
        tpnTelaEstoque.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N

        jlEstoque.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jlEstoque.setText("ID Estoque");

        jLabel3.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel3.setText("Unidade de Medida");

        cbUnidadeMedidaCE.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        cbUnidadeMedidaCE.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jlProdutoCE.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jlProdutoCE.setText("Produto");

        cbProdutoCE.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        cbProdutoCE.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jlQuantidadeAtual.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jlQuantidadeAtual.setText("Quantidade Atual:");

        jlQuantidadeReservadaCE.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jlQuantidadeReservadaCE.setText("Quantidade Reservada:");

        jlCategoriaCE.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jlCategoriaCE.setText("Categoria:");

        cbCategoriaCE.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        cbCategoriaCE.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jlMarca.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jlMarca.setText("Marca:");

        cbMarcaCE.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        cbMarcaCE.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbMarcaCE.addActionListener(this::cbMarcaCEActionPerformed);

        jlDataAtualizacao.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jlDataAtualizacao.setText("Data de Atualização:");

        ftfDataAtualizacaoCE.addActionListener(this::ftfDataAtualizacaoCEActionPerformed);

        btnPesquisarCE.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        btnPesquisarCE.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lupa (1).png"))); // NOI18N
        btnPesquisarCE.setText("Pesquisar");
        btnPesquisarCE.addActionListener(this::btnPesquisarCEActionPerformed);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jlEstoque)
                    .addComponent(tfIDEstoque)
                    .addComponent(jLabel3)
                    .addComponent(cbUnidadeMedidaCE, 0, 220, Short.MAX_VALUE))
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jlMarca)
                        .addGap(206, 206, 206)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlCategoriaCE)
                            .addComponent(cbCategoriaCE, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(46, 46, 46)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(cbProdutoCE, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnPesquisarCE, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jlProdutoCE, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jlQuantidadeAtual, javax.swing.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
                            .addComponent(spQuantidadeAtual))
                        .addGap(62, 62, 62)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlQuantidadeReservadaCE)
                            .addComponent(spQuantidadeReservada, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(54, 54, 54)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jlDataAtualizacao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(ftfDataAtualizacaoCE)))
                    .addComponent(cbMarcaCE, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(55, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(26, 26, 26)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jlEstoque)
                                .addComponent(jlCategoriaCE)))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jlMarca)))
                    .addComponent(jlProdutoCE, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tfIDEstoque, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbMarcaCE, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cbCategoriaCE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbProdutoCE, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnPesquisarCE)))
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlDataAtualizacao)
                    .addComponent(jlQuantidadeReservadaCE)
                    .addComponent(jlQuantidadeAtual)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cbUnidadeMedidaCE, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                    .addComponent(spQuantidadeAtual)
                    .addComponent(spQuantidadeReservada)
                    .addComponent(ftfDataAtualizacaoCE))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        btnEditarCE.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        btnEditarCE.setIcon(new javax.swing.ImageIcon(getClass().getResource("/EDITAR.png"))); // NOI18N
        btnEditarCE.setText("Editar");

        btnExcluirCE.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        btnExcluirCE.setIcon(new javax.swing.ImageIcon(getClass().getResource("/EXCLUIR.png"))); // NOI18N
        btnExcluirCE.setText("Excluir");

        btnLimparCE.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        btnLimparCE.setIcon(new javax.swing.ImageIcon(getClass().getResource("/LIMPAR.png"))); // NOI18N
        btnLimparCE.setText("Limpar");

        tabelaConsultaEstoque.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        tabelaConsultaEstoque.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
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
                "ID Estoque", "Quantidade Atual", "Quantidade Reservada", "Data Atualização", "ID Produto", "ID Categoria", "ID Marca", "ID Unidade de Medida"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane18.setViewportView(tabelaConsultaEstoque);

        jlConsultaEstoqueCE.setBackground(new java.awt.Color(10, 4, 138));
        jlConsultaEstoqueCE.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jlConsultaEstoqueCE.setText("Consulta de Estoque");

        btnSalvarCE.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        btnSalvarCE.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SALVAR.png"))); // NOI18N
        btnSalvarCE.setText("Salvar");
        btnSalvarCE.addActionListener(this::btnSalvarCEActionPerformed);

        javax.swing.GroupLayout pnConsultaELayout = new javax.swing.GroupLayout(pnConsultaE);
        pnConsultaE.setLayout(pnConsultaELayout);
        pnConsultaELayout.setHorizontalGroup(
            pnConsultaELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnConsultaELayout.createSequentialGroup()
                .addGroup(pnConsultaELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnConsultaELayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(pnConsultaELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane18, javax.swing.GroupLayout.PREFERRED_SIZE, 1107, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnConsultaELayout.createSequentialGroup()
                                .addGap(176, 176, 176)
                                .addComponent(btnSalvarCE)
                                .addGap(29, 29, 29)
                                .addComponent(btnEditarCE)
                                .addGap(67, 67, 67)
                                .addComponent(btnExcluirCE)
                                .addGap(56, 56, 56)
                                .addComponent(btnLimparCE))))
                    .addGroup(pnConsultaELayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jlConsultaEstoqueCE, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnConsultaELayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(240, 240, 240))
        );
        pnConsultaELayout.setVerticalGroup(
            pnConsultaELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnConsultaELayout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jlConsultaEstoqueCE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addGroup(pnConsultaELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEditarCE)
                    .addComponent(btnExcluirCE)
                    .addComponent(btnLimparCE)
                    .addComponent(btnSalvarCE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane18, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnConsultaEstoqueLayout = new javax.swing.GroupLayout(pnConsultaEstoque);
        pnConsultaEstoque.setLayout(pnConsultaEstoqueLayout);
        pnConsultaEstoqueLayout.setHorizontalGroup(
            pnConsultaEstoqueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnConsultaEstoqueLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(pnConsultaE, javax.swing.GroupLayout.PREFERRED_SIZE, 1404, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(228, Short.MAX_VALUE))
        );
        pnConsultaEstoqueLayout.setVerticalGroup(
            pnConsultaEstoqueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnConsultaEstoqueLayout.createSequentialGroup()
                .addComponent(pnConsultaE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 154, Short.MAX_VALUE))
        );

        tpnTelaEstoque.addTab("Consulta do Estoque", pnConsultaEstoque);

        jlMovimentacao.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jlMovimentacao.setText("ID Movimentação:");

        jlUnidadeM.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jlUnidadeM.setText("Unidade de Medida:");

        jlTipoM.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jlTipoM.setText("Tipo de Movimentação:");

        cbTipoMovimentacaoME.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        cbTipoMovimentacaoME.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbTipoMovimentacaoME.addActionListener(this::cbTipoMovimentacaoMEActionPerformed);

        jlDataM.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jlDataM.setText("Data da Movimentação:");

        jlProdutoME.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jlProdutoME.setText("Produto:");

        cbProdutoME.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        cbProdutoME.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jlHoraMovimentacao.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jlHoraMovimentacao.setText("Hora da Movimentação:");

        jLabel5.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel5.setText("Categoria:");

        cbCategoriaME.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        cbCategoriaME.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jlMarcaME.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jlMarcaME.setText("Marca:");

        cbMarcaME.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        cbMarcaME.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jlDocumentoReferencia.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jlDocumentoReferencia.setText("Documento de Referência:");

        btnPesquisarME.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        btnPesquisarME.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lupa (1).png"))); // NOI18N
        btnPesquisarME.setText("Pesquisar");
        btnPesquisarME.addActionListener(this::btnPesquisarMEActionPerformed);

        cbUnidadeMedidaME.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        cbUnidadeMedidaME.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout pnMELayout = new javax.swing.GroupLayout(pnME);
        pnME.setLayout(pnMELayout);
        pnMELayout.setHorizontalGroup(
            pnMELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnMELayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(pnMELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tfMovimentacao)
                    .addComponent(jlMovimentacao)
                    .addComponent(jlUnidadeM, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbUnidadeMedidaME, 0, 220, Short.MAX_VALUE))
                .addGap(38, 38, 38)
                .addGroup(pnMELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlTipoM)
                    .addComponent(cbTipoMovimentacaoME, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlDataM)
                    .addComponent(ftfDataMovimentacao, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(63, 63, 63)
                .addGroup(pnMELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnMELayout.createSequentialGroup()
                        .addGroup(pnMELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbMarcaME, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlMarcaME))
                        .addGap(37, 37, 37)
                        .addGroup(pnMELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(cbCategoriaME, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(pnMELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnMELayout.createSequentialGroup()
                                .addComponent(cbProdutoME, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnPesquisarME))
                            .addComponent(jlProdutoME)))
                    .addGroup(pnMELayout.createSequentialGroup()
                        .addGroup(pnMELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlHoraMovimentacao, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnMELayout.createSequentialGroup()
                                .addComponent(ftfHoraMovimentacaoME, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(48, 48, 48)
                                .addComponent(ftfHoraMovimentacaoATEME, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(90, 90, 90)
                        .addGroup(pnMELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlDocumentoReferencia)
                            .addComponent(tfDocumentoReferenciaME, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(49, Short.MAX_VALUE))
        );
        pnMELayout.setVerticalGroup(
            pnMELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnMELayout.createSequentialGroup()
                .addGroup(pnMELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnMELayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(pnMELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlMovimentacao)
                            .addComponent(jlTipoM)
                            .addComponent(jLabel5)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnMELayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(pnMELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlMarcaME, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jlProdutoME, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnMELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfMovimentacao, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbTipoMovimentacaoME, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbCategoriaME, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbMarcaME, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbProdutoME, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPesquisarME))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addGroup(pnMELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlUnidadeM)
                    .addGroup(pnMELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jlDataM)
                        .addComponent(jlHoraMovimentacao)
                        .addComponent(jlDocumentoReferencia)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnMELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(ftfDataMovimentacao, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                    .addComponent(ftfHoraMovimentacaoATEME, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ftfHoraMovimentacaoME, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfDocumentoReferenciaME, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbUnidadeMedidaME))
                .addGap(39, 39, 39))
        );

        btnSalvarME.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        btnSalvarME.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SALVAR.png"))); // NOI18N
        btnSalvarME.setText("Salvar");
        btnSalvarME.addActionListener(this::btnSalvarMEActionPerformed);

        btnEditarME.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        btnEditarME.setIcon(new javax.swing.ImageIcon(getClass().getResource("/EDITAR.png"))); // NOI18N
        btnEditarME.setText("Editar");

        btnExcluirME.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        btnExcluirME.setIcon(new javax.swing.ImageIcon(getClass().getResource("/EXCLUIR.png"))); // NOI18N
        btnExcluirME.setText("Excluir");

        btnLimparME.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        btnLimparME.setIcon(new javax.swing.ImageIcon(getClass().getResource("/LIMPAR.png"))); // NOI18N
        btnLimparME.setText("Limpar");

        tabelaMovimentacaoEstoque.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        tabelaMovimentacaoEstoque.setModel(new javax.swing.table.DefaultTableModel(
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
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID Movimentação", "Tipo de Movimentação", "Quantidade", "Data de Movimentação", "Hora Movimentação", "Documento de Referência", "ID Produto", "ID Categoria", "ID Marca", "ID Unidade de Medidas"
            }
        ));
        jScrollPane19.setViewportView(tabelaMovimentacaoEstoque);

        jlMovimentacaodeEstoque.setBackground(new java.awt.Color(10, 4, 138));
        jlMovimentacaodeEstoque.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jlMovimentacaodeEstoque.setText("Movimentação de Estoque");

        javax.swing.GroupLayout pnMovimentacaoELayout = new javax.swing.GroupLayout(pnMovimentacaoE);
        pnMovimentacaoE.setLayout(pnMovimentacaoELayout);
        pnMovimentacaoELayout.setHorizontalGroup(
            pnMovimentacaoELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnMovimentacaoELayout.createSequentialGroup()
                .addGroup(pnMovimentacaoELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnMovimentacaoELayout.createSequentialGroup()
                        .addGap(350, 350, 350)
                        .addComponent(btnSalvarME)
                        .addGap(41, 41, 41)
                        .addComponent(btnEditarME)
                        .addGap(64, 64, 64)
                        .addComponent(btnExcluirME)
                        .addGap(33, 33, 33)
                        .addComponent(btnLimparME))
                    .addGroup(pnMovimentacaoELayout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(jlMovimentacaodeEstoque))
                    .addGroup(pnMovimentacaoELayout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(pnME, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane19, javax.swing.GroupLayout.PREFERRED_SIZE, 1641, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnMovimentacaoELayout.setVerticalGroup(
            pnMovimentacaoELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnMovimentacaoELayout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addComponent(jlMovimentacaodeEstoque)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnME, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnMovimentacaoELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnExcluirME)
                    .addComponent(btnLimparME)
                    .addComponent(btnEditarME)
                    .addComponent(btnSalvarME))
                .addGap(35, 35, 35)
                .addComponent(jScrollPane19, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(167, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnMovimentacaoEstoqueLayout = new javax.swing.GroupLayout(pnMovimentacaoEstoque);
        pnMovimentacaoEstoque.setLayout(pnMovimentacaoEstoqueLayout);
        pnMovimentacaoEstoqueLayout.setHorizontalGroup(
            pnMovimentacaoEstoqueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnMovimentacaoEstoqueLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnMovimentacaoE, javax.swing.GroupLayout.PREFERRED_SIZE, 1558, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnMovimentacaoEstoqueLayout.setVerticalGroup(
            pnMovimentacaoEstoqueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnMovimentacaoEstoqueLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnMovimentacaoE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tpnTelaEstoque.addTab("Movimentação de Estoque", pnMovimentacaoEstoque);

        jlPerdasPE.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jlPerdasPE.setText("ID Perdas:");

        jlMarcasPE.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jlMarcasPE.setText("Marca:");

        tfPerdasPE.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N

        cbMarcaPE.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        cbMarcaPE.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jlDataRegistroPE.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jlDataRegistroPE.setText("Data do Registro:");

        jlUnidadeMedidaPE.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jlUnidadeMedidaPE.setText("Unidade de Medida:");

        cbUnidadeMedidaPE.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        cbUnidadeMedidaPE.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jlMotivoPE.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jlMotivoPE.setText("Motivo:");

        jlProdutoPE.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jlProdutoPE.setText("Produto:");

        cbMotivoPE.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        cbMotivoPE.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbProdutoPE.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        cbProdutoPE.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jlCategoriaPE.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jlCategoriaPE.setText("Categoria:");

        cbCategoriaPE.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        cbCategoriaPE.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnPesquisarPE.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        btnPesquisarPE.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lupa (1).png"))); // NOI18N
        btnPesquisarPE.setText("Pesquisar");
        btnPesquisarPE.addActionListener(this::btnPesquisarPEActionPerformed);

        jlLote.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jlLote.setText("Lote:");

        javax.swing.GroupLayout jpnPerdasEstoqueLayout = new javax.swing.GroupLayout(jpnPerdasEstoque);
        jpnPerdasEstoque.setLayout(jpnPerdasEstoqueLayout);
        jpnPerdasEstoqueLayout.setHorizontalGroup(
            jpnPerdasEstoqueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnPerdasEstoqueLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jpnPerdasEstoqueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jlMarcasPE)
                    .addComponent(tfPerdasPE)
                    .addComponent(jlPerdasPE, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbMarcaPE, 0, 220, Short.MAX_VALUE))
                .addGap(32, 32, 32)
                .addGroup(jpnPerdasEstoqueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jpnPerdasEstoqueLayout.createSequentialGroup()
                        .addGroup(jpnPerdasEstoqueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jpnPerdasEstoqueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jpnPerdasEstoqueLayout.createSequentialGroup()
                                    .addComponent(jlDataRegistroPE)
                                    .addGap(88, 88, 88)
                                    .addComponent(jlMotivoPE, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jpnPerdasEstoqueLayout.createSequentialGroup()
                                    .addComponent(ftfDataRegistroPE, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(109, 109, 109)
                                    .addComponent(cbMotivoPE, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jpnPerdasEstoqueLayout.createSequentialGroup()
                                .addComponent(jlUnidadeMedidaPE)
                                .addGap(329, 329, 329)))
                        .addGap(53, 53, 53)
                        .addGroup(jpnPerdasEstoqueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlProdutoPE)
                            .addComponent(cbProdutoPE, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jpnPerdasEstoqueLayout.createSequentialGroup()
                        .addComponent(cbUnidadeMedidaPE, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(54, 54, 54)
                        .addGroup(jpnPerdasEstoqueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbCategoriaPE, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlCategoriaPE))
                        .addGap(51, 51, 51)
                        .addGroup(jpnPerdasEstoqueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlLote)
                            .addComponent(tfLotePE, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnPesquisarPE)
                .addGap(87, 87, 87))
        );
        jpnPerdasEstoqueLayout.setVerticalGroup(
            jpnPerdasEstoqueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnPerdasEstoqueLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jpnPerdasEstoqueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlPerdasPE)
                    .addComponent(jlDataRegistroPE)
                    .addComponent(jlMotivoPE)
                    .addComponent(jlProdutoPE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jpnPerdasEstoqueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpnPerdasEstoqueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(tfPerdasPE, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                        .addComponent(ftfDataRegistroPE))
                    .addGroup(jpnPerdasEstoqueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cbMotivoPE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbProdutoPE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnPesquisarPE)))
                .addGroup(jpnPerdasEstoqueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpnPerdasEstoqueLayout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jlUnidadeMedidaPE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jpnPerdasEstoqueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jpnPerdasEstoqueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cbMarcaPE, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cbUnidadeMedidaPE, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(cbCategoriaPE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jpnPerdasEstoqueLayout.createSequentialGroup()
                        .addGroup(jpnPerdasEstoqueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpnPerdasEstoqueLayout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addGroup(jpnPerdasEstoqueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jlMarcasPE)
                                    .addComponent(jlCategoriaPE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnPerdasEstoqueLayout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addComponent(jlLote)
                                .addGap(2, 2, 2)))
                        .addComponent(tfLotePE, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(25, 25, 25))
        );

        btnSalvarPE.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        btnSalvarPE.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SALVAR.png"))); // NOI18N
        btnSalvarPE.setText("Salvar");

        btnEditarPE.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        btnEditarPE.setIcon(new javax.swing.ImageIcon(getClass().getResource("/EDITAR.png"))); // NOI18N
        btnEditarPE.setText("Editar");

        btnExcluirPE.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        btnExcluirPE.setIcon(new javax.swing.ImageIcon(getClass().getResource("/EXCLUIR.png"))); // NOI18N
        btnExcluirPE.setText("Excluir");

        btnLimparPE.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        btnLimparPE.setIcon(new javax.swing.ImageIcon(getClass().getResource("/LIMPAR.png"))); // NOI18N
        btnLimparPE.setText("Limpar");

        tabelaPerdasEstoque.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        tabelaPerdasEstoque.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
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
                "ID Perda", "Data do Registro", "Motivo", "Quantidade", "Produto", "Categoria", "Marca", "Unidade de Medida"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tabelaPerdasEstoque);

        jlPerdasDeEstoque.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jlPerdasDeEstoque.setText("Perdas De Estoque");

        javax.swing.GroupLayout pnPerdasEsLayout = new javax.swing.GroupLayout(pnPerdasEs);
        pnPerdasEs.setLayout(pnPerdasEsLayout);
        pnPerdasEsLayout.setHorizontalGroup(
            pnPerdasEsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnPerdasEsLayout.createSequentialGroup()
                .addGroup(pnPerdasEsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnPerdasEsLayout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jpnPerdasEstoque, javax.swing.GroupLayout.PREFERRED_SIZE, 1254, Short.MAX_VALUE))
                    .addGroup(pnPerdasEsLayout.createSequentialGroup()
                        .addGap(250, 250, 250)
                        .addComponent(btnSalvarPE)
                        .addGap(42, 42, 42)
                        .addComponent(btnEditarPE)
                        .addGap(53, 53, 53)
                        .addComponent(btnExcluirPE)
                        .addGap(52, 52, 52)
                        .addComponent(btnLimparPE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(360, 360, 360))
            .addGroup(pnPerdasEsLayout.createSequentialGroup()
                .addGroup(pnPerdasEsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnPerdasEsLayout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addComponent(jlPerdasDeEstoque))
                    .addGroup(pnPerdasEsLayout.createSequentialGroup()
                        .addGap(93, 93, 93)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 925, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnPerdasEsLayout.setVerticalGroup(
            pnPerdasEsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnPerdasEsLayout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addComponent(jlPerdasDeEstoque)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpnPerdasEstoque, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnPerdasEsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSalvarPE)
                    .addComponent(btnEditarPE)
                    .addComponent(btnExcluirPE)
                    .addComponent(btnLimparPE))
                .addGap(34, 34, 34)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(146, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnPerdasEstoqueLayout = new javax.swing.GroupLayout(pnPerdasEstoque);
        pnPerdasEstoque.setLayout(pnPerdasEstoqueLayout);
        pnPerdasEstoqueLayout.setHorizontalGroup(
            pnPerdasEstoqueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnPerdasEstoqueLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnPerdasEs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnPerdasEstoqueLayout.setVerticalGroup(
            pnPerdasEstoqueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnPerdasEstoqueLayout.createSequentialGroup()
                .addComponent(pnPerdasEs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        tpnTelaEstoque.addTab("Perdas De Estoque", pnPerdasEstoque);

        getContentPane().add(tpnTelaEstoque, new org.netbeans.lib.awtextra.AbsoluteConstraints(-5, 1, 1480, 760));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbMarcaCEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbMarcaCEActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbMarcaCEActionPerformed

    private void ftfDataAtualizacaoCEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ftfDataAtualizacaoCEActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ftfDataAtualizacaoCEActionPerformed

    private void cbTipoMovimentacaoMEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbTipoMovimentacaoMEActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbTipoMovimentacaoMEActionPerformed

    private void btnSalvarMEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarMEActionPerformed
        // TODO add your handling code here:
        cadastrarEstoque();
    }//GEN-LAST:event_btnSalvarMEActionPerformed

    private void btnPesquisarPEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarPEActionPerformed
listarPerdasPorProduto((String) cbProdutoPE.getSelectedItem());                                                  

   
    }//GEN-LAST:event_btnPesquisarPEActionPerformed

    private void btnPesquisarCEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarCEActionPerformed
   listarConsultaEstoquePorProduto(cbProdutoCE.getSelectedItem().toString());
    
        
// TODO add your handling code here:
    }//GEN-LAST:event_btnPesquisarCEActionPerformed

    private void btnSalvarCEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarCEActionPerformed
      
        

// TODO add your handling code here:
    }//GEN-LAST:event_btnSalvarCEActionPerformed

    private void btnPesquisarMEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarMEActionPerformed
      listarMovimentacoesPorProduto();
        
// TODO add your handling code here:
    }//GEN-LAST:event_btnPesquisarMEActionPerformed
  
    
          
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
        java.awt.EventQueue.invokeLater(() -> new TelaEstoque(usuario).setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEditarCE;
    private javax.swing.JButton btnEditarME;
    private javax.swing.JButton btnEditarPE;
    private javax.swing.JButton btnExcluirCE;
    private javax.swing.JButton btnExcluirME;
    private javax.swing.JButton btnExcluirPE;
    private javax.swing.JButton btnLimparCE;
    private javax.swing.JButton btnLimparME;
    private javax.swing.JButton btnLimparPE;
    private javax.swing.JButton btnPesquisarCE;
    private javax.swing.JButton btnPesquisarME;
    private javax.swing.JButton btnPesquisarPE;
    private javax.swing.JButton btnSalvarCE;
    private javax.swing.JButton btnSalvarME;
    private javax.swing.JButton btnSalvarPE;
    private javax.swing.JComboBox<String> cbCategoriaCE;
    private javax.swing.JComboBox<String> cbCategoriaME;
    private javax.swing.JComboBox<String> cbCategoriaPE;
    private javax.swing.JComboBox<String> cbMarcaCE;
    private javax.swing.JComboBox<String> cbMarcaME;
    private javax.swing.JComboBox<String> cbMarcaPE;
    private javax.swing.JComboBox<String> cbMotivoPE;
    private javax.swing.JComboBox<String> cbProdutoCE;
    private javax.swing.JComboBox<String> cbProdutoME;
    private javax.swing.JComboBox<String> cbProdutoPE;
    private javax.swing.JComboBox<String> cbTipoMovimentacaoME;
    private javax.swing.JComboBox<String> cbUnidadeMedidaCE;
    private javax.swing.JComboBox<String> cbUnidadeMedidaME;
    private javax.swing.JComboBox<String> cbUnidadeMedidaPE;
    private javax.swing.JFormattedTextField ftfDataAtualizacaoCE;
    private javax.swing.JFormattedTextField ftfDataMovimentacao;
    private javax.swing.JFormattedTextField ftfDataRegistroPE;
    private javax.swing.JFormattedTextField ftfHoraMovimentacaoATEME;
    private javax.swing.JFormattedTextField ftfHoraMovimentacaoME;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane18;
    private javax.swing.JScrollPane jScrollPane19;
    private javax.swing.JLabel jlCategoriaCE;
    private javax.swing.JLabel jlCategoriaPE;
    private javax.swing.JLabel jlConsultaEstoqueCE;
    private javax.swing.JLabel jlDataAtualizacao;
    private javax.swing.JLabel jlDataM;
    private javax.swing.JLabel jlDataRegistroPE;
    private javax.swing.JLabel jlDocumentoReferencia;
    private javax.swing.JLabel jlEstoque;
    private javax.swing.JLabel jlHoraMovimentacao;
    private javax.swing.JLabel jlLote;
    private javax.swing.JLabel jlMarca;
    private javax.swing.JLabel jlMarcaME;
    private javax.swing.JLabel jlMarcasPE;
    private javax.swing.JLabel jlMotivoPE;
    private javax.swing.JLabel jlMovimentacao;
    private javax.swing.JLabel jlMovimentacaodeEstoque;
    private javax.swing.JLabel jlPerdasDeEstoque;
    private javax.swing.JLabel jlPerdasPE;
    private javax.swing.JLabel jlProdutoCE;
    private javax.swing.JLabel jlProdutoME;
    private javax.swing.JLabel jlProdutoPE;
    private javax.swing.JLabel jlQuantidadeAtual;
    private javax.swing.JLabel jlQuantidadeReservadaCE;
    private javax.swing.JLabel jlTipoM;
    private javax.swing.JLabel jlUnidadeM;
    private javax.swing.JLabel jlUnidadeMedidaPE;
    private javax.swing.JPanel jpnPerdasEstoque;
    private javax.swing.JPanel pnConsultaE;
    private javax.swing.JPanel pnConsultaEstoque;
    private javax.swing.JPanel pnME;
    private javax.swing.JPanel pnMovimentacaoE;
    private javax.swing.JPanel pnMovimentacaoEstoque;
    private javax.swing.JPanel pnPerdasEs;
    private javax.swing.JPanel pnPerdasEstoque;
    private javax.swing.JSpinner spQuantidadeAtual;
    private javax.swing.JSpinner spQuantidadeReservada;
    private javax.swing.JTable tabelaConsultaEstoque;
    private javax.swing.JTable tabelaMovimentacaoEstoque;
    private javax.swing.JTable tabelaPerdasEstoque;
    private javax.swing.JTextField tfDocumentoReferenciaME;
    private javax.swing.JTextField tfIDEstoque;
    private javax.swing.JTextField tfLotePE;
    private javax.swing.JTextField tfMovimentacao;
    private javax.swing.JTextField tfPerdasPE;
    private javax.swing.JTabbedPane tpnTelaEstoque;
    // End of variables declaration//GEN-END:variables
}
