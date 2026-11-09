/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import Conection.Conexao;
import Control.CategoriasController;
import Control.EtiquetasController;
import Control.MarcasController;
import Control.ProdutosController;
import Control.UnidadeMedidasController;
import Model.Categorias;
import Model.Marcas;
import Model.Produtos;
import Model.UnidadesMedidas;
import Model.Usuario;
import View.AlterarSenha;
import View.RecuperacaoSenha;
import View.TelaEntregas;
import View.TelaEstoque;
import View.TelaHome;
import View.TelaLoginUsuarios;
import View.TelaPdvRealizacao;
import View.TelaPedidos;
import View.TelaPerfilUsuarios;
import View.TelaPromocoes;
import View.TelaSolicitacao;
import View.TelaVendas;
import View.Tela_Funcionarios_RH;
import View.TelasClientes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author EnzoLeite
 */
public class TelaGerenciamentoProdutos extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(TelaGerenciamentoProdutos.class.getName());

    private MenuPadrao menu;
    private Usuario usuario;
    private String produtos;
    
    public TelaGerenciamentoProdutos(Usuario usuario) {
        initComponents();
        
        listarMarcasCadastro();
        listarUnidadesCadastro();        
        listarCategoriasCadastro();
        this.usuario= usuario;
        listarCategoriasCadastro();
        listarProdutosComboBox();
        this.menu=MenuPadrao.instalar(
        this,
        usuario,
        "gerenciamentoProdutos",
        this::navegar,
        this::dispose
    );
        this.setExtendedState(TelaPerfilUsuarios.MAXIMIZED_BOTH);
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

 private void listarCategoriasCadastro() {

    try {

        CategoriasController controller = new CategoriasController();

        cbCategoriaCadastro.removeAllItems();

        List<Categorias> lista = controller.listar();

        for (Categorias categoria : lista) {
            cbCategoriaCadastro.addItem(categoria.getNome());
        }

        if (lista.isEmpty()) {
            System.out.println("Nenhuma categoria encontrada no banco.");
        }

    } catch (Exception e) {

        JOptionPane.showMessageDialog(
            this,
            "Erro ao carregar categorias: " + e.getMessage()
        );

        e.printStackTrace();
    }
}

 public void listarMarcasCadastro() {

    MarcasController controller = new MarcasController();

    DefaultComboBoxModel<String> modelo = new DefaultComboBoxModel<>();

    try {
        List<String[]> lista = controller.listarMarcas();

        for (String[] marca : lista) {
            modelo.addElement(marca[1]);
        }

        cbMarcaCadastro.setModel(modelo);

    } catch (Exception e) {
        javax.swing.JOptionPane.showMessageDialog(
            this,
            "Erro ao carregar marcas: " + e.getMessage()
        );
    }
}
 
private void cadastrarProduto() {

    try {

        // Capturar os dados dos campos
        String nome = ftfCodigodoprodutoCadastro.getText().trim();

        String codigo = ftfCodigodoprodutoCadastro.getText().trim();

        String descricao = taDescricaoCadastroCD.getText().trim();

        double precoCusto = Double.parseDouble(
            tfIconeValorCustoCadastro.getText().replace(",", ".")
        );

        double precoVenda = Double.parseDouble(
            tfIconePrecoVendaCadastro.getText().replace(",", ".")
        );

        int estoque = Integer.parseInt(
            tfQuantidadeEstoqueCadastro.getText().trim()
        );

        String validade = tfValidadeCadastrar
                .getText().trim();

        int situacao = cbStatusCb.getSelectedIndex();

        // Capturar os IDs dos relacionamentos
        int idCategoria = 0; // Obter o ID selecionado
        int idMarca = 0;     // Obter o ID selecionado
        int idUnidade = 0;   // Obter o ID selecionado

        // Imagem do produto
        byte[] imagem = null;

        // Validar campos obrigatórios
        if (nome.isEmpty() || codigo.isEmpty() ||
            descricao.isEmpty() || validade.isEmpty()) {

            JOptionPane.showMessageDialog(
                this,
                "Preencha todos os campos obrigatórios!",
                "Atenção",
                JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        if (precoCusto < 0 || precoVenda < 0 || estoque < 0) {

            JOptionPane.showMessageDialog(
                this,
                "Os valores não podem ser negativos!",
                "Atenção",
                JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        // Chamar o Controller
        ProdutosController controller = new ProdutosController();

        boolean sucesso = controller.cadastrar(
            nome,
            codigo,
            descricao,
            precoCusto,
            precoVenda,
            situacao,
            estoque,
            validade,
            imagem,
            idCategoria,
            idMarca,
            idUnidade
        );

        if (sucesso) {

            JOptionPane.showMessageDialog(
                this,
                "Produto cadastrado com sucesso!",
                "Sucesso",
                JOptionPane.INFORMATION_MESSAGE
            );

            limparCamposCadastro();

        } else {

            JOptionPane.showMessageDialog(
                this,
                "Não foi possível cadastrar o produto!",
                "Erro",
                JOptionPane.ERROR_MESSAGE
            );
        }

    } catch (NumberFormatException e) {

        JOptionPane.showMessageDialog(
            this,
            "Verifique os campos de preço e estoque!",
            "Atenção",
            JOptionPane.WARNING_MESSAGE
        );

    } catch (Exception e) {

        JOptionPane.showMessageDialog(
            this,
            "Erro ao cadastrar produto: " + e.getMessage(),
            "Erro",
            JOptionPane.ERROR_MESSAGE
        );
    }
}

private void pesquisarProdutoSelecionado() {

    Object selecionado = cbSelecionarCadastro.getSelectedItem();

    if (selecionado == null) {
        return;
    }

    try {

        ProdutosController controller = new ProdutosController();

        List<Produtos> lista =
                controller.pesquisarPorNome(selecionado.toString());

        atualizarTabelaProdutos(lista);

    } catch (Exception e) {

        JOptionPane.showMessageDialog(
            this,
            "Erro ao selecionar produto: " + e.getMessage()
        );
    }
}

private void limparCamposCadastro() {

    // Limpar campos de texto
    ftfCodigodoprodutoCadastro.setText("");
    ftfCodigodoprodutoCadastro.setText("");
    taDescricaoCadastroCD.setText("");
    tfIconeValorCustoCadastro.setText("");
    tfIconePrecoVendaCadastro.setText("");
    tfQuantidadeEstoqueCadastro.setText("");
    tfValidadeCadastrar.setText("");

    // Limpar ComboBoxes
    cbCategoriaCadastro.setSelectedIndex(-1);
    cbMarcaCadastro.setSelectedIndex(-1);
    cbSubcategoriaCadastro.setSelectedIndex(-1);   
    cbUnidadeMedidaCadastro.setSelectedIndex(-1);
    cbSelecionarCadastro.setSelectedIndex(-1);
    cbStatusCb.setSelectedIndex(-1);

    // Limpar imagem, caso exista um JLabel para visualização
    // Exemplo: lblImagemCadastro.setIcon(null);

}

private void listarProdutosComboBox() {

    try {

        ProdutosController controller = new ProdutosController();

        cbSelecionarCadastro.removeAllItems();

        List<Produtos> lista = controller.listar();

        for (Produtos produto : lista) {
            cbSelecionarCadastro.addItem(produto.getNome());
        }

    } catch (Exception e) {

        JOptionPane.showMessageDialog(
            this,
            "Erro ao carregar produtos: " + e.getMessage()
        );

        e.printStackTrace();
    }
    
}
 
private void cadastrarEtiqueta() {

    
    try {

        // Capturar os dados dos campos
        String tipo = cbTipoEtiquetaER.getSelectedItem().toString();

        // Capturar imagem
        byte[] imagem = null;

        // Validar campos obrigatórios
        if (tipo.isEmpty()) {

            JOptionPane.showMessageDialog(
                this,
                "Selecione o tipo de etiqueta!",
                "Atenção",
                JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        if (imagem == null) {

            JOptionPane.showMessageDialog(
                this,
                "Selecione uma imagem para a etiqueta!",
                "Atenção",
                JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        // Chamar o Controller
        EtiquetasController controller = new EtiquetasController();

        boolean sucesso = controller.cadastrar(tipo, imagem);

        if (sucesso) {

            JOptionPane.showMessageDialog(
                this,
                "Etiqueta cadastrada com sucesso!",
                "Sucesso",
                JOptionPane.INFORMATION_MESSAGE
            );

            limparCamposEtiqueta();

        } else {

            JOptionPane.showMessageDialog(
                this,
                "Não foi possível cadastrar a etiqueta!",
                "Erro",
                JOptionPane.ERROR_MESSAGE
            );
        }

    } catch (Exception e) {

        JOptionPane.showMessageDialog(
            this,
            "Erro ao cadastrar etiqueta: " + e.getMessage(),
            "Erro",
            JOptionPane.ERROR_MESSAGE
        );
    }
}

private void limparCamposEtiqueta() {

    // Limpar tipo de etiqueta
    cbTipoEtiquetaER.setSelectedIndex(-1);

    // Limpar imagem selecionada
    lbImagenPreviwEtiquetaER = null;

    // Limpar visualização da imagem
    lbImagenPreviwEtiquetaER.setIcon(null);

}

private void listarUnidadesCadastro() {

    try {

        UnidadeMedidasController controller = new UnidadeMedidasController();

        cbUnidadeMedidaCadastro.removeAllItems();

        List<UnidadesMedidas> lista = controller.listar();

        for (UnidadesMedidas unidade : lista) {
            cbUnidadeMedidaCadastro.addItem(unidade.getNome());
        }

    } catch (Exception e) {

        JOptionPane.showMessageDialog(
            this,
            "Erro ao carregar unidades de medida: " + e.getMessage()
        );

        e.printStackTrace();
    }
}

private void atualizarTabelaProdutos(List<Produtos> lista) {

    DefaultTableModel modelo =
            (DefaultTableModel) tbListaProdutosC.getModel();

    modelo.setRowCount(0);

    for (Produtos produto : lista) {

        modelo.addRow(new Object[]{
            produto.getIdprodutos(),
            produto.getNome(),
            produto.getCodigo(),
            produto.getPrecoVenda(),
            produto.getEstoque()
        });
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

        tbTelas = new javax.swing.JTabbedPane();
        jpCadastro = new javax.swing.JPanel();
        jpParteCimaCadastro = new javax.swing.JPanel();
        lbDadosProdutosCadastro = new javax.swing.JLabel();
        lbCodigoProduto = new javax.swing.JLabel();
        ftfCodigodoprodutoCadastro = new javax.swing.JFormattedTextField();
        lbNomeprodutoCadastro = new javax.swing.JLabel();
        tfNomeprodutoCadastro = new javax.swing.JTextField();
        lbDescricaoCadastro = new javax.swing.JLabel();
        taDescricaoCadastro = new javax.swing.JScrollPane();
        taDescricaoCadastroCD = new javax.swing.JTextArea();
        lbCategoriaCadastro = new javax.swing.JLabel();
        cbCategoriaCadastro = new javax.swing.JComboBox<>();
        lbMarcaCadastro = new javax.swing.JLabel();
        cbMarcaCadastro = new javax.swing.JComboBox<>();
        lbUnidadeMedidaCadastro = new javax.swing.JLabel();
        cbUnidadeMedidaCadastro = new javax.swing.JComboBox<>();
        lbValorCustoCadastro = new javax.swing.JLabel();
        bIconeValorCustoCadastro = new javax.swing.JButton();
        tfIconeValorCustoCadastro = new javax.swing.JTextField();
        blIconePrtecoVendaCadastro = new javax.swing.JButton();
        tfIconePrecoVendaCadastro = new javax.swing.JTextField();
        lbPrecoVendaCadastro = new javax.swing.JLabel();
        lbQuantidadeEstoqueCadastro = new javax.swing.JLabel();
        tfQuantidadeEstoqueCadastro = new javax.swing.JTextField();
        lbCotigoBarrasCadastro = new javax.swing.JLabel();
        tfCodigoBarrasCadastro = new javax.swing.JTextField();
        lbSituacaoProdutoCadastro = new javax.swing.JLabel();
        rbAtivoCadastro = new javax.swing.JRadioButton();
        rbDesligadoCadastro = new javax.swing.JRadioButton();
        jbSalvarCadastrar = new javax.swing.JButton();
        jbEditarCadastra = new javax.swing.JButton();
        jbExcluirCadastrar = new javax.swing.JButton();
        jbLimparCadastrar = new javax.swing.JButton();
        lbValidadeCadastrar = new javax.swing.JLabel();
        tfValidadeCadastrar = new javax.swing.JTextField();
        lbListaProdutosCadastrar = new javax.swing.JLabel();
        cbSelecionarCadastro = new javax.swing.JComboBox<>();
        jbIconePesquisaCadastro = new javax.swing.JButton();
        lbPesquisarPorCadastro = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbListaProdutosC = new javax.swing.JTable();
        jpEtiquetasRelatorios = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        lbTipoEtiquetaER = new javax.swing.JLabel();
        cbTipoEtiquetaER = new javax.swing.JComboBox<>();
        lbImpressoraER = new javax.swing.JLabel();
        cbImpressoraER = new javax.swing.JComboBox<>();
        btImprimirEtiquetaER = new javax.swing.JToggleButton();
        btVizualizarER = new javax.swing.JToggleButton();
        btLimparER = new javax.swing.JToggleButton();
        lbImagenPreviwEtiquetaER = new javax.swing.JLabel();
        lbImpresaoEtiquetaER = new javax.swing.JLabel();
        lbPreviwEtiquetaER = new javax.swing.JLabel();
        lbRelatorioER = new javax.swing.JLabel();
        lbTipoRelatorioER = new javax.swing.JLabel();
        cbTipoRelatorioER = new javax.swing.JComboBox<>();
        lbPeriodoER = new javax.swing.JLabel();
        cbPeriodoER = new javax.swing.JComboBox<>();
        lbAteER = new javax.swing.JLabel();
        cbPeriodo2ER = new javax.swing.JComboBox<>();
        lbCategoriaER = new javax.swing.JLabel();
        cbCategoriaER = new javax.swing.JComboBox<>();
        lbProduto2ER = new javax.swing.JLabel();
        tfProduto2ER = new javax.swing.JFormattedTextField();
        btProduto2ER = new javax.swing.JToggleButton();
        btGerarER = new javax.swing.JButton();
        btVisualizarER = new javax.swing.JButton();
        btExportarPDFER = new javax.swing.JButton();
        jbSalvarCadastrar5 = new javax.swing.JButton();
        btEsportarEXcelER = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        lbVendasPorPeriodoER = new javax.swing.JLabel();
        lbProdutosMaisVendidosET = new javax.swing.JLabel();
        lbProdutosPorCategoriaET = new javax.swing.JLabel();
        lbProdutosComEstoqueBaixoET = new javax.swing.JLabel();
        lbEstoqueAtualET = new javax.swing.JLabel();
        lbMovimentacaoEstoqueET = new javax.swing.JLabel();
        lbClienteCadastradosET = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        lbRelatoriosDisponiveisER = new javax.swing.JLabel();
        jpCodigoBarrasC = new javax.swing.JPanel();
        lbProdutoConsultaCB = new javax.swing.JLabel();
        lbCodigoBarrasCB = new javax.swing.JLabel();
        tfCodigoBarrasCB = new javax.swing.JTextField();
        lbTipoCodigoCB = new javax.swing.JLabel();
        cbTipoCodigoCB = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbListasCodigoBarrasCB = new javax.swing.JTable();
        lbImagemCodigoCB = new javax.swing.JLabel();
        cbStatusCb = new javax.swing.JComboBox<>();
        lbPesquisarPorCB = new javax.swing.JLabel();
        lbDescricaoCB = new javax.swing.JLabel();
        tfPesquisarPor = new javax.swing.JTextField();
        lbStatusCB = new javax.swing.JLabel();
        jpImagemCodigo = new javax.swing.JPanel();
        btSelecionarCodigo = new javax.swing.JToggleButton();
        btRemover = new javax.swing.JToggleButton();
        jbAdicionarCB = new javax.swing.JButton();
        jbSalvarCB = new javax.swing.JButton();
        jbEditarCB = new javax.swing.JButton();
        jbExcluirCB = new javax.swing.JButton();
        jbLimparCB = new javax.swing.JButton();
        jbPesquisarCB = new javax.swing.JButton();
        cbPesquisarPor = new javax.swing.JComboBox<>();
        jbIconePesquisaCB = new javax.swing.JButton();
        lbTotalCodigiBarrasCadastradoCB = new javax.swing.JLabel();
        cbProdutoCB = new javax.swing.JComboBox<>();
        jScrollPane5 = new javax.swing.JScrollPane();
        taDescricaoCB = new javax.swing.JTextArea();
        lbListaCodigoBarrasCB = new javax.swing.JLabel();
        cadastroCodigoBarras = new javax.swing.JLabel();
        lbCategoriasCB = new javax.swing.JLabel();
        tfCategoriasCB = new javax.swing.JTextField();
        lbMarcasCB = new javax.swing.JLabel();
        tfMarcasCB = new javax.swing.JTextField();
        lbUnidadeMedidaCB = new javax.swing.JLabel();
        jComboBox14 = new javax.swing.JComboBox<>();
        jpEdicao = new javax.swing.JPanel();
        lbEditarProdutoE = new javax.swing.JLabel();
        lbCodigoProdutoE = new javax.swing.JLabel();
        tfCodigoProdutoE = new javax.swing.JTextField();
        lbNomeProdutoE = new javax.swing.JLabel();
        tfNomeProdutoE = new javax.swing.JTextField();
        lbDescricaoE = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        taDescricaoE = new javax.swing.JTextArea();
        lbCodigoBarrasE = new javax.swing.JLabel();
        tfCodigoBarrasE = new javax.swing.JTextField();
        lbStatusE = new javax.swing.JLabel();
        cbCategoriasE = new javax.swing.JComboBox<>();
        rbProdutoPesavelE = new javax.swing.JRadioButton();
        jbNovoE = new javax.swing.JButton();
        jbSalvarE = new javax.swing.JButton();
        jbExcluirE = new javax.swing.JButton();
        jbLimparE = new javax.swing.JButton();
        jbPeswquisarE = new javax.swing.JButton();
        jbVoltarE = new javax.swing.JButton();
        jScrollPane9 = new javax.swing.JScrollPane();
        tbListaProdutosE = new javax.swing.JTable();
        lbListaProdutosE = new javax.swing.JLabel();
        lbPesquisarPorE = new javax.swing.JLabel();
        cbPesquisarPorE = new javax.swing.JComboBox<>();
        tfPesquisarPorE = new javax.swing.JTextField();
        jbPesquisar2E = new javax.swing.JButton();
        lbCategoriasE = new javax.swing.JLabel();
        lbSubcategoriaE = new javax.swing.JLabel();
        cbMarcaE = new javax.swing.JComboBox<>();
        lbMarcaE = new javax.swing.JLabel();
        lbUnidadeE = new javax.swing.JComboBox<>();
        jLabel36 = new javax.swing.JLabel();
        cbSubcategoriaE = new javax.swing.JComboBox<>();
        lbIconePrecoCustoE = new javax.swing.JLabel();
        tfPrecoCustoE = new javax.swing.JTextField();
        lbPrecosCustoE = new javax.swing.JLabel();
        lbIconePrecoVendaE = new javax.swing.JLabel();
        tfPrecoVendaE = new javax.swing.JTextField();
        lbEstoqueAtualE = new javax.swing.JLabel();
        tfEstoqueAtualE = new javax.swing.JTextField();
        lbEstoqueMinimoE = new javax.swing.JLabel();
        tfEstoqueMinimoE = new javax.swing.JTextField();
        jbCodigoBarras2E = new javax.swing.JLabel();
        lbImagenProdutoE = new javax.swing.JLabel();
        lbImagenProdutoIconeE = new javax.swing.JLabel();
        jbSelecionarImagenE = new javax.swing.JButton();
        jbRemoverE = new javax.swing.JButton();
        jLabel44 = new javax.swing.JLabel();
        tfCodigoBarras2E = new javax.swing.JTextField();
        jbGerarE = new javax.swing.JButton();
        lbUnidadeMedidaE = new javax.swing.JLabel();
        cbUnidadeMedidaE = new javax.swing.JComboBox<>();
        lbPrecoVendaE = new javax.swing.JLabel();
        cbStatusE = new javax.swing.JComboBox<>();
        jPanel10 = new javax.swing.JPanel();
        jLabel60 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        jTextField20 = new javax.swing.JTextField();
        jLabel62 = new javax.swing.JLabel();
        jTextField21 = new javax.swing.JTextField();
        jLabel63 = new javax.swing.JLabel();
        jScrollPane12 = new javax.swing.JScrollPane();
        jTextArea3 = new javax.swing.JTextArea();
        jLabel64 = new javax.swing.JLabel();
        jComboBox22 = new javax.swing.JComboBox<>();
        jLabel65 = new javax.swing.JLabel();
        jButton15 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        jRadioButton7 = new javax.swing.JRadioButton();
        jButton17 = new javax.swing.JButton();
        jButton18 = new javax.swing.JButton();
        jButton19 = new javax.swing.JButton();
        jButton20 = new javax.swing.JButton();
        jButton21 = new javax.swing.JButton();
        jButton22 = new javax.swing.JButton();
        jButton23 = new javax.swing.JButton();
        jLabel56 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        jComboBox25 = new javax.swing.JComboBox<>();
        jTextField18 = new javax.swing.JTextField();
        jButton24 = new javax.swing.JButton();
        jScrollPane13 = new javax.swing.JScrollPane();
        jTable6 = new javax.swing.JTable();
        jPanel11 = new javax.swing.JPanel();
        jLabel69 = new javax.swing.JLabel();
        jTextField22 = new javax.swing.JTextField();
        jLabel70 = new javax.swing.JLabel();
        jLabel71 = new javax.swing.JLabel();
        jTextField23 = new javax.swing.JTextField();
        jLabel72 = new javax.swing.JLabel();
        jScrollPane14 = new javax.swing.JScrollPane();
        jTextArea4 = new javax.swing.JTextArea();
        jLabel73 = new javax.swing.JLabel();
        jComboBox26 = new javax.swing.JComboBox<>();
        jLabel74 = new javax.swing.JLabel();
        jComboBox27 = new javax.swing.JComboBox<>();
        jLabel75 = new javax.swing.JLabel();
        jButton25 = new javax.swing.JButton();
        jButton26 = new javax.swing.JButton();
        jRadioButton8 = new javax.swing.JRadioButton();
        jButton27 = new javax.swing.JButton();
        jButton28 = new javax.swing.JButton();
        jButton29 = new javax.swing.JButton();
        jButton30 = new javax.swing.JButton();
        jButton31 = new javax.swing.JButton();
        jButton32 = new javax.swing.JButton();
        jButton33 = new javax.swing.JButton();
        jLabel76 = new javax.swing.JLabel();
        jLabel77 = new javax.swing.JLabel();
        jComboBox28 = new javax.swing.JComboBox<>();
        jTextField24 = new javax.swing.JTextField();
        jButton34 = new javax.swing.JButton();
        jScrollPane15 = new javax.swing.JScrollPane();
        jTable7 = new javax.swing.JTable();
        jPanel12 = new javax.swing.JPanel();
        jLabel78 = new javax.swing.JLabel();
        jLabel79 = new javax.swing.JLabel();
        jTextField25 = new javax.swing.JTextField();
        jLabel80 = new javax.swing.JLabel();
        jTextField26 = new javax.swing.JTextField();
        jLabel81 = new javax.swing.JLabel();
        jScrollPane16 = new javax.swing.JScrollPane();
        jTextArea5 = new javax.swing.JTextArea();
        jLabel82 = new javax.swing.JLabel();
        jComboBox29 = new javax.swing.JComboBox<>();
        jLabel83 = new javax.swing.JLabel();
        jButton35 = new javax.swing.JButton();
        jButton36 = new javax.swing.JButton();
        jRadioButton9 = new javax.swing.JRadioButton();
        jButton37 = new javax.swing.JButton();
        jButton38 = new javax.swing.JButton();
        jButton39 = new javax.swing.JButton();
        jButton40 = new javax.swing.JButton();
        jButton41 = new javax.swing.JButton();
        jButton42 = new javax.swing.JButton();
        jButton43 = new javax.swing.JButton();
        jLabel84 = new javax.swing.JLabel();
        jLabel85 = new javax.swing.JLabel();
        jComboBox30 = new javax.swing.JComboBox<>();
        jTextField27 = new javax.swing.JTextField();
        jButton44 = new javax.swing.JButton();
        jScrollPane17 = new javax.swing.JScrollPane();
        jTable8 = new javax.swing.JTable();
        jPanel14 = new javax.swing.JPanel();
        jLabel88 = new javax.swing.JLabel();
        jLabel89 = new javax.swing.JLabel();
        jTextField29 = new javax.swing.JTextField();
        jLabel90 = new javax.swing.JLabel();
        jTextField30 = new javax.swing.JTextField();
        jLabel91 = new javax.swing.JLabel();
        jScrollPane18 = new javax.swing.JScrollPane();
        jTextArea6 = new javax.swing.JTextArea();
        jLabel92 = new javax.swing.JLabel();
        jComboBox31 = new javax.swing.JComboBox<>();
        jLabel93 = new javax.swing.JLabel();
        jButton45 = new javax.swing.JButton();
        jButton46 = new javax.swing.JButton();
        jRadioButton10 = new javax.swing.JRadioButton();
        jButton47 = new javax.swing.JButton();
        jButton48 = new javax.swing.JButton();
        jButton49 = new javax.swing.JButton();
        jButton50 = new javax.swing.JButton();
        jButton51 = new javax.swing.JButton();
        jButton52 = new javax.swing.JButton();
        jButton53 = new javax.swing.JButton();
        jLabel94 = new javax.swing.JLabel();
        jLabel95 = new javax.swing.JLabel();
        jComboBox32 = new javax.swing.JComboBox<>();
        jTextField31 = new javax.swing.JTextField();
        jButton54 = new javax.swing.JButton();
        jScrollPane19 = new javax.swing.JScrollPane();
        jTable9 = new javax.swing.JTable();
        jPanel15 = new javax.swing.JPanel();
        jLabel96 = new javax.swing.JLabel();
        jLabel97 = new javax.swing.JLabel();
        jTextField32 = new javax.swing.JTextField();
        jLabel98 = new javax.swing.JLabel();
        jTextField33 = new javax.swing.JTextField();
        jLabel99 = new javax.swing.JLabel();
        jScrollPane20 = new javax.swing.JScrollPane();
        jTextArea7 = new javax.swing.JTextArea();
        jLabel100 = new javax.swing.JLabel();
        jComboBox33 = new javax.swing.JComboBox<>();
        jLabel101 = new javax.swing.JLabel();
        jButton55 = new javax.swing.JButton();
        jButton56 = new javax.swing.JButton();
        jRadioButton11 = new javax.swing.JRadioButton();
        jButton57 = new javax.swing.JButton();
        jButton58 = new javax.swing.JButton();
        jButton59 = new javax.swing.JButton();
        jButton60 = new javax.swing.JButton();
        jButton61 = new javax.swing.JButton();
        jButton62 = new javax.swing.JButton();
        jButton63 = new javax.swing.JButton();
        jLabel102 = new javax.swing.JLabel();
        jLabel103 = new javax.swing.JLabel();
        jComboBox34 = new javax.swing.JComboBox<>();
        jTextField34 = new javax.swing.JTextField();
        jButton64 = new javax.swing.JButton();
        jScrollPane21 = new javax.swing.JScrollPane();
        jTable10 = new javax.swing.JTable();
        jPanel16 = new javax.swing.JPanel();
        jLabel104 = new javax.swing.JLabel();
        jLabel105 = new javax.swing.JLabel();
        jTextField35 = new javax.swing.JTextField();
        jLabel106 = new javax.swing.JLabel();
        jTextField36 = new javax.swing.JTextField();
        jLabel107 = new javax.swing.JLabel();
        jScrollPane22 = new javax.swing.JScrollPane();
        jTextArea8 = new javax.swing.JTextArea();
        jLabel108 = new javax.swing.JLabel();
        jComboBox35 = new javax.swing.JComboBox<>();
        jLabel109 = new javax.swing.JLabel();
        jButton65 = new javax.swing.JButton();
        jButton66 = new javax.swing.JButton();
        jButton67 = new javax.swing.JButton();
        jButton68 = new javax.swing.JButton();
        jButton69 = new javax.swing.JButton();
        jButton70 = new javax.swing.JButton();
        jButton71 = new javax.swing.JButton();
        jButton72 = new javax.swing.JButton();
        jButton73 = new javax.swing.JButton();
        jLabel110 = new javax.swing.JLabel();
        jLabel111 = new javax.swing.JLabel();
        jComboBox36 = new javax.swing.JComboBox<>();
        jTextField37 = new javax.swing.JTextField();
        jButton74 = new javax.swing.JButton();
        jScrollPane23 = new javax.swing.JScrollPane();
        jTable11 = new javax.swing.JTable();
        jButton75 = new javax.swing.JButton();
        jLabel86 = new javax.swing.JLabel();
        jScrollPane24 = new javax.swing.JScrollPane();
        jTextArea9 = new javax.swing.JTextArea();
        jPanel17 = new javax.swing.JPanel();
        jLabel112 = new javax.swing.JLabel();
        jLabel113 = new javax.swing.JLabel();
        jTextField38 = new javax.swing.JTextField();
        jLabel114 = new javax.swing.JLabel();
        jTextField39 = new javax.swing.JTextField();
        jLabel115 = new javax.swing.JLabel();
        jLabel116 = new javax.swing.JLabel();
        jComboBox37 = new javax.swing.JComboBox<>();
        jLabel117 = new javax.swing.JLabel();
        jButton76 = new javax.swing.JButton();
        jButton77 = new javax.swing.JButton();
        jButton78 = new javax.swing.JButton();
        jButton79 = new javax.swing.JButton();
        jButton80 = new javax.swing.JButton();
        jButton81 = new javax.swing.JButton();
        jButton82 = new javax.swing.JButton();
        jButton83 = new javax.swing.JButton();
        jButton84 = new javax.swing.JButton();
        jLabel118 = new javax.swing.JLabel();
        jLabel119 = new javax.swing.JLabel();
        jComboBox38 = new javax.swing.JComboBox<>();
        jTextField40 = new javax.swing.JTextField();
        jButton85 = new javax.swing.JButton();
        jScrollPane26 = new javax.swing.JScrollPane();
        jTable12 = new javax.swing.JTable();
        jComboBox39 = new javax.swing.JComboBox<>();
        jLabel87 = new javax.swing.JLabel();
        jComboBox40 = new javax.swing.JComboBox<>();
        jLabel120 = new javax.swing.JLabel();
        jTextField28 = new javax.swing.JTextField();
        jLabel121 = new javax.swing.JLabel();
        jComboBox41 = new javax.swing.JComboBox<>();
        jLabel122 = new javax.swing.JLabel();
        jTextField41 = new javax.swing.JTextField();
        jLabel123 = new javax.swing.JLabel();
        jComboBox42 = new javax.swing.JComboBox<>();
        jRadioButton12 = new javax.swing.JRadioButton();
        jpConsulta = new javax.swing.JPanel();
        lbCodigoProdutoC = new javax.swing.JLabel();
        tfCodigoProdutoC = new javax.swing.JTextField();
        lbNomeProdutoC = new javax.swing.JLabel();
        tfNomeProdutoC = new javax.swing.JTextField();
        lbCategoriasC = new javax.swing.JLabel();
        cbCategoriasC = new javax.swing.JComboBox<>();
        lbMarcaC = new javax.swing.JLabel();
        cbMarcaC = new javax.swing.JComboBox<>();
        lbSubcategoriaC = new javax.swing.JLabel();
        cbSubcategoriaC = new javax.swing.JComboBox<>();
        lbUnidadeC = new javax.swing.JLabel();
        cbUnidadeC = new javax.swing.JComboBox<>();
        lbUnidadeMedidaC = new javax.swing.JLabel();
        cbUnidadeMedidaC = new javax.swing.JComboBox<>();
        lbCodigoBarrasC = new javax.swing.JLabel();
        tfCodigoBarrasC = new javax.swing.JTextField();
        lbFaixaPrecoC = new javax.swing.JLabel();
        tfFaixaPreco1C = new javax.swing.JTextField();
        lbIconeFaixaPreco1C = new javax.swing.JLabel();
        tfFaixaPreco2C = new javax.swing.JTextField();
        lbIconeFaixaPreco2C = new javax.swing.JLabel();
        lbSituacaoProdutoC = new javax.swing.JLabel();
        cbSituacaoProdutoC = new javax.swing.JComboBox<>();
        lbTipoProdutoC = new javax.swing.JLabel();
        rbTodosTipoProdutoC = new javax.swing.JRadioButton();
        rbNormalTipoProdutoC = new javax.swing.JRadioButton();
        rbPesavelTipoProdutoC = new javax.swing.JRadioButton();
        lbOutrasOpcoesC = new javax.swing.JLabel();
        rbApenasImagenC = new javax.swing.JRadioButton();
        rbApenasProdutosAtivosC = new javax.swing.JRadioButton();
        lbOrdernaPorC = new javax.swing.JLabel();
        lbOrdemC = new javax.swing.JLabel();
        jbPesquisarC = new javax.swing.JButton();
        jbLimparC = new javax.swing.JButton();
        cbOrdernaPorC = new javax.swing.JComboBox<>();
        cbOrdemC = new javax.swing.JComboBox<>();
        lbResultadoConsultaC = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tbResultadoConsultaC = new javax.swing.JTable();
        lbConsultaProdutosC = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        lbDesativacaoProdutosD = new javax.swing.JLabel();
        lbCodigoProdutoD = new javax.swing.JLabel();
        tfCodigoProdutoD = new javax.swing.JTextField();
        lbSubcategoriaD = new javax.swing.JLabel();
        tfSubcategoriaD = new javax.swing.JTextField();
        lbNomeProdutoD = new javax.swing.JLabel();
        tfNomeProdutoD = new javax.swing.JTextField();
        lbCategoriaD = new javax.swing.JLabel();
        cbMarcaD = new javax.swing.JComboBox<>();
        lbStatusD = new javax.swing.JLabel();
        cbStatusD = new javax.swing.JComboBox<>();
        lbDataCadastroD = new javax.swing.JLabel();
        cbDataCadastro1D = new javax.swing.JComboBox<>();
        lbAteD = new javax.swing.JLabel();
        cbDataCadastro2D = new javax.swing.JComboBox<>();
        jTextField19 = new javax.swing.JTextField();
        jLabel57 = new javax.swing.JLabel();
        jbPesquisar1D = new javax.swing.JButton();
        jbLimparD = new javax.swing.JButton();
        jScrollPane10 = new javax.swing.JScrollPane();
        tbDesativacaoProdutos = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        lbProdutosSeremDesativadosD = new javax.swing.JLabel();
        jComboBox21 = new javax.swing.JComboBox<>();
        jPanel6 = new javax.swing.JPanel();
        jLabel59 = new javax.swing.JLabel();
        jScrollPane11 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        lbMarcaD = new javax.swing.JLabel();
        cbCategoriaD = new javax.swing.JComboBox<>();
        lbUnidadeD = new javax.swing.JLabel();
        cbUnidadeD = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jpParteCimaCadastro.setBackground(new java.awt.Color(204, 204, 204));

        lbDadosProdutosCadastro.setBackground(new java.awt.Color(153, 153, 153));
        lbDadosProdutosCadastro.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        lbDadosProdutosCadastro.setText("Dados dos produtos:");

        lbCodigoProduto.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        lbCodigoProduto.setText("Codigo do produto:");

        ftfCodigodoprodutoCadastro.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        ftfCodigodoprodutoCadastro.addActionListener(this::ftfCodigodoprodutoCadastroActionPerformed);

        lbNomeprodutoCadastro.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        lbNomeprodutoCadastro.setText("Nome do produto:");

        tfNomeprodutoCadastro.addActionListener(this::tfNomeprodutoCadastroActionPerformed);

        lbDescricaoCadastro.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        lbDescricaoCadastro.setText("Descrição:");

        taDescricaoCadastroCD.setColumns(20);
        taDescricaoCadastroCD.setRows(5);
        taDescricaoCadastro.setViewportView(taDescricaoCadastroCD);

        lbCategoriaCadastro.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        lbCategoriaCadastro.setText("Categoria:");

        cbCategoriaCadastro.setFont(new java.awt.Font("Segoe UI Emoji", 0, 12)); // NOI18N
        cbCategoriaCadastro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione:", "Item 2", "Item 3", "Item 4" }));

        lbMarcaCadastro.setFont(new java.awt.Font("Segoe UI Black", 2, 14)); // NOI18N
        lbMarcaCadastro.setText("Marca:");

        cbMarcaCadastro.setFont(new java.awt.Font("Segoe UI Emoji", 2, 12)); // NOI18N
        cbMarcaCadastro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione:", "Item 2", "Item 3", "Item 4" }));

        lbUnidadeMedidaCadastro.setFont(new java.awt.Font("Segoe UI Black", 2, 14)); // NOI18N
        lbUnidadeMedidaCadastro.setText("Unidade de medida:");

        cbUnidadeMedidaCadastro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione:", "Item 2", "Item 3", "Item 4" }));

        lbValorCustoCadastro.setBackground(new java.awt.Color(255, 255, 255));
        lbValorCustoCadastro.setFont(new java.awt.Font("Segoe UI Black", 2, 14)); // NOI18N
        lbValorCustoCadastro.setText("Valor de custo:");

        bIconeValorCustoCadastro.setIcon(new javax.swing.ImageIcon("C:\\Users\\EnzoLeite\\Documents\\ProjetoPI_Supermercado\\GerenciamentoSupermercado\\src\\main\\resorce\\Assets\\bolsa-de-dinheiro.png")); // NOI18N

        blIconePrtecoVendaCadastro.setIcon(new javax.swing.ImageIcon("C:\\Users\\EnzoLeite\\Documents\\ProjetoPI_Supermercado\\GerenciamentoSupermercado\\src\\main\\resorce\\Assets\\bolsa-de-dinheiro.png")); // NOI18N
        blIconePrtecoVendaCadastro.addActionListener(this::blIconePrtecoVendaCadastroActionPerformed);

        lbPrecoVendaCadastro.setFont(new java.awt.Font("Segoe UI Black", 2, 14)); // NOI18N
        lbPrecoVendaCadastro.setText("Preço de venda:");

        lbQuantidadeEstoqueCadastro.setFont(new java.awt.Font("Segoe UI Black", 2, 14)); // NOI18N
        lbQuantidadeEstoqueCadastro.setText("Quantidade  em estoque:");

        lbCotigoBarrasCadastro.setFont(new java.awt.Font("Segoe UI Black", 2, 14)); // NOI18N
        lbCotigoBarrasCadastro.setText("Cotigo de barras");

        lbSituacaoProdutoCadastro.setFont(new java.awt.Font("Segoe UI Black", 2, 14)); // NOI18N
        lbSituacaoProdutoCadastro.setText("Situação do produto:");

        rbAtivoCadastro.setBackground(new java.awt.Color(51, 255, 0));
        rbAtivoCadastro.setText("Ligado");

        rbDesligadoCadastro.setBackground(new java.awt.Color(255, 0, 0));
        rbDesligadoCadastro.setText("Desligado");

        jbSalvarCadastrar.setText("Salvar");

        jbEditarCadastra.setText("Editar");

        jbExcluirCadastrar.setText("Excluir");

        jbLimparCadastrar.setText("Limpar");

        lbValidadeCadastrar.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        lbValidadeCadastrar.setText("Validade:");

        tfValidadeCadastrar.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N

        lbListaProdutosCadastrar.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        lbListaProdutosCadastrar.setText("Lista de produtos:");

        cbSelecionarCadastro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 2", "Item 3", "Item 4" }));
        cbSelecionarCadastro.addActionListener(this::cbSelecionarCadastroActionPerformed);

        jbIconePesquisaCadastro.setText("Pesquisar");
        jbIconePesquisaCadastro.addActionListener(this::jbIconePesquisaCadastroActionPerformed);

        lbPesquisarPorCadastro.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        lbPesquisarPorCadastro.setText("Pesquisar por:");

        tbListaProdutosC.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Codigo:", "Nome do produto:", "Categoria:", "Subcategoria:", "Marca:", "Unidade:", "Unidade medida:", "Preço custo:", "Preço venda:", "Estoque:", "Situação:"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tbListaProdutosC);

        javax.swing.GroupLayout jpParteCimaCadastroLayout = new javax.swing.GroupLayout(jpParteCimaCadastro);
        jpParteCimaCadastro.setLayout(jpParteCimaCadastroLayout);
        jpParteCimaCadastroLayout.setHorizontalGroup(
            jpParteCimaCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpParteCimaCadastroLayout.createSequentialGroup()
                .addGroup(jpParteCimaCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpParteCimaCadastroLayout.createSequentialGroup()
                        .addGroup(jpParteCimaCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpParteCimaCadastroLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lbNomeprodutoCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jpParteCimaCadastroLayout.createSequentialGroup()
                                .addGroup(jpParteCimaCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jpParteCimaCadastroLayout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(lbCodigoProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jpParteCimaCadastroLayout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addGroup(jpParteCimaCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(tfNomeprodutoCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(ftfCodigodoprodutoCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jpParteCimaCadastroLayout.createSequentialGroup()
                                        .addContainerGap()
                                        .addGroup(jpParteCimaCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lbDescricaoCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(taDescricaoCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGroup(jpParteCimaCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jpParteCimaCadastroLayout.createSequentialGroup()
                                        .addGap(83, 83, 83)
                                        .addGroup(jpParteCimaCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lbMarcaCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jpParteCimaCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(cbCategoriaCadastro, 0, 135, Short.MAX_VALUE)
                                                .addComponent(lbCategoriaCadastro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addGroup(jpParteCimaCadastroLayout.createSequentialGroup()
                                                .addComponent(cbMarcaCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(bIconeValorCustoCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(jpParteCimaCadastroLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(blIconePrtecoVendaCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpParteCimaCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpParteCimaCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(cbUnidadeMedidaCadastro, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lbUnidadeMedidaCadastro, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(lbPrecoVendaCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbValorCustoCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfIconeValorCustoCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfIconePrecoVendaCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jpParteCimaCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpParteCimaCadastroLayout.createSequentialGroup()
                                .addGap(81, 81, 81)
                                .addGroup(jpParteCimaCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lbQuantidadeEstoqueCadastro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(tfQuantidadeEstoqueCadastro)
                                    .addComponent(lbCotigoBarrasCadastro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lbSituacaoProdutoCadastro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(tfCodigoBarrasCadastro))
                                .addGap(64, 64, 64)
                                .addGroup(jpParteCimaCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(tfValidadeCadastrar, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
                                    .addComponent(lbValidadeCadastrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jpParteCimaCadastroLayout.createSequentialGroup()
                                .addGap(59, 59, 59)
                                .addComponent(rbAtivoCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(32, 32, 32)
                                .addComponent(rbDesligadoCadastro))))
                    .addComponent(lbDadosProdutosCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jpParteCimaCadastroLayout.createSequentialGroup()
                        .addGap(213, 213, 213)
                        .addComponent(jbSalvarCadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 75, Short.MAX_VALUE)
                        .addComponent(jbEditarCadastra, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44)
                        .addComponent(jbExcluirCadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(56, 56, 56)
                        .addComponent(jbLimparCadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(425, 425, 425)))
                .addContainerGap(265, Short.MAX_VALUE))
            .addGroup(jpParteCimaCadastroLayout.createSequentialGroup()
                .addGroup(jpParteCimaCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpParteCimaCadastroLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lbListaProdutosCadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpParteCimaCadastroLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(lbPesquisarPorCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(cbSelecionarCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbIconePesquisaCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpParteCimaCadastroLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1146, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpParteCimaCadastroLayout.setVerticalGroup(
            jpParteCimaCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpParteCimaCadastroLayout.createSequentialGroup()
                .addComponent(lbDadosProdutosCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addGroup(jpParteCimaCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbCategoriaCadastro)
                    .addComponent(lbCodigoProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbUnidadeMedidaCadastro))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpParteCimaCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpParteCimaCadastroLayout.createSequentialGroup()
                        .addGroup(jpParteCimaCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbQuantidadeEstoqueCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbValidadeCadastrar))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jpParteCimaCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpParteCimaCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(tfQuantidadeEstoqueCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(tfValidadeCadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lbNomeprodutoCadastro)))
                    .addGroup(jpParteCimaCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cbCategoriaCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbUnidadeMedidaCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(tfNomeprodutoCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jpParteCimaCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpParteCimaCadastroLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(ftfCodigodoprodutoCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21)
                        .addComponent(lbDescricaoCadastro))
                    .addGroup(jpParteCimaCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jpParteCimaCadastroLayout.createSequentialGroup()
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(tfIconeValorCustoCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jpParteCimaCadastroLayout.createSequentialGroup()
                            .addGroup(jpParteCimaCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jpParteCimaCadastroLayout.createSequentialGroup()
                                    .addGap(16, 16, 16)
                                    .addGroup(jpParteCimaCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lbCotigoBarrasCadastro)
                                        .addComponent(lbValorCustoCadastro)))
                                .addGroup(jpParteCimaCadastroLayout.createSequentialGroup()
                                    .addGap(12, 12, 12)
                                    .addComponent(lbMarcaCadastro)))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jpParteCimaCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(tfCodigoBarrasCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cbMarcaCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(bIconeValorCustoCadastro)))))
                .addGap(28, 28, 28)
                .addGroup(jpParteCimaCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpParteCimaCadastroLayout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(jpParteCimaCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpParteCimaCadastroLayout.createSequentialGroup()
                                .addGap(76, 76, 76)
                                .addGroup(jpParteCimaCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(rbAtivoCadastro)
                                    .addComponent(rbDesligadoCadastro)))
                            .addGroup(jpParteCimaCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(blIconePrtecoVendaCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jpParteCimaCadastroLayout.createSequentialGroup()
                                    .addGroup(jpParteCimaCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lbSituacaoProdutoCadastro)
                                        .addComponent(lbPrecoVendaCadastro))
                                    .addGap(27, 27, 27)
                                    .addComponent(tfIconePrecoVendaCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jpParteCimaCadastroLayout.createSequentialGroup()
                        .addComponent(taDescricaoCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(65, 65, 65)
                        .addGroup(jpParteCimaCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jbSalvarCadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jbEditarCadastra, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jbExcluirCadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jbLimparCadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                        .addComponent(lbListaProdutosCadastrar)
                        .addGap(18, 18, 18)
                        .addGroup(jpParteCimaCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbSelecionarCadastro)
                            .addComponent(jbIconePesquisaCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbPesquisarPorCadastro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jpCadastroLayout = new javax.swing.GroupLayout(jpCadastro);
        jpCadastro.setLayout(jpCadastroLayout);
        jpCadastroLayout.setHorizontalGroup(
            jpCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpCadastroLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jpParteCimaCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpCadastroLayout.setVerticalGroup(
            jpCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpCadastroLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jpParteCimaCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(1053, Short.MAX_VALUE))
        );

        tbTelas.addTab("Cadastro", jpCadastro);

        lbTipoEtiquetaER.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lbTipoEtiquetaER.setText("Tipo de Etiqueta");

        cbTipoEtiquetaER.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        cbTipoEtiquetaER.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        lbImpressoraER.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        lbImpressoraER.setText("Impressora");

        cbImpressoraER.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        cbImpressoraER.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btImprimirEtiquetaER.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        btImprimirEtiquetaER.setIcon(new javax.swing.ImageIcon("C:\\Users\\EnzoLeite\\Documents\\ProjetoPI_Supermercado\\GerenciamentoSupermercado\\src\\main\\resorce\\Assets\\impressora.png")); // NOI18N
        btImprimirEtiquetaER.setText("Imprimir etiqueta");

        btVizualizarER.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        btVizualizarER.setIcon(new javax.swing.ImageIcon("C:\\Users\\EnzoLeite\\Documents\\ProjetoPI_Supermercado\\GerenciamentoSupermercado\\src\\main\\resorce\\Assets\\eye-care.png")); // NOI18N
        btVizualizarER.setText("Visualizar");

        btLimparER.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        btLimparER.setIcon(new javax.swing.ImageIcon("C:\\Users\\EnzoLeite\\Documents\\ProjetoPI_Supermercado\\GerenciamentoSupermercado\\src\\main\\resorce\\Assets\\lixeira.png")); // NOI18N
        btLimparER.setText("Limpar");

        lbImagenPreviwEtiquetaER.setBackground(new java.awt.Color(153, 153, 153));
        lbImagenPreviwEtiquetaER.setIcon(new javax.swing.ImageIcon("C:\\Users\\EnzoLeite\\Downloads\\Imagen de teste.jpg")); // NOI18N

        lbImpresaoEtiquetaER.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        lbImpresaoEtiquetaER.setText("Impresão de etiquetas ");

        lbPreviwEtiquetaER.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        lbPreviwEtiquetaER.setText("previw da etiqueta");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbImpresaoEtiquetaER, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbTipoEtiquetaER, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbTipoEtiquetaER, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(54, 54, 54)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbImpressoraER, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbImpressoraER, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(82, 82, 82)
                        .addComponent(lbImagenPreviwEtiquetaER, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(129, 129, 129)
                        .addComponent(lbPreviwEtiquetaER, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 142, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btLimparER, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btVizualizarER)
                    .addComponent(btImprimirEtiquetaER)))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(lbImpresaoEtiquetaER, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(lbTipoEtiquetaER)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbTipoEtiquetaER, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 174, Short.MAX_VALUE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lbImpressoraER)
                        .addGap(18, 18, 18)
                        .addComponent(cbImpressoraER, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(btImprimirEtiquetaER)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btVizualizarER)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btLimparER))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(lbPreviwEtiquetaER)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lbImagenPreviwEtiquetaER, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())))
        );

        lbRelatorioER.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        lbRelatorioER.setText("Relatorios");

        lbTipoRelatorioER.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        lbTipoRelatorioER.setText("Tipo de Relatorio");

        cbTipoRelatorioER.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        cbTipoRelatorioER.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbTipoRelatorioER.addActionListener(this::cbTipoRelatorioERActionPerformed);

        lbPeriodoER.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        lbPeriodoER.setText("Periodo");

        cbPeriodoER.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        cbPeriodoER.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        lbAteER.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        lbAteER.setText("Ate");

        cbPeriodo2ER.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        cbPeriodo2ER.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        lbCategoriaER.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        lbCategoriaER.setText("Categoria");

        cbCategoriaER.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        cbCategoriaER.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        lbProduto2ER.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        lbProduto2ER.setText("Produto");

        tfProduto2ER.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N

        btProduto2ER.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        btProduto2ER.setIcon(new javax.swing.ImageIcon("C:\\Users\\EnzoLeite\\Documents\\ProjetoPI_Supermercado\\GerenciamentoSupermercado\\src\\main\\resorce\\Assets\\lupa.png")); // NOI18N
        btProduto2ER.setText("Pesquisar");

        btGerarER.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        btGerarER.setText("Gerar relatorio");

        btVisualizarER.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        btVisualizarER.setText("Visualizar");

        btExportarPDFER.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        btExportarPDFER.setText("Exportar PDF");

        jbSalvarCadastrar5.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        jbSalvarCadastrar5.setText("Limpar filtros");
        jbSalvarCadastrar5.addActionListener(this::jbSalvarCadastrar5ActionPerformed);

        btEsportarEXcelER.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        btEsportarEXcelER.setText("Exportar Excel");

        lbVendasPorPeriodoER.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        lbVendasPorPeriodoER.setText("Vendas por Periodo");

        lbProdutosMaisVendidosET.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        lbProdutosMaisVendidosET.setText("Produtos mais Vendidos");

        lbProdutosPorCategoriaET.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        lbProdutosPorCategoriaET.setText("Produtos por Categoria");

        lbProdutosComEstoqueBaixoET.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        lbProdutosComEstoqueBaixoET.setText("Produtos com Estoque baixo");

        lbEstoqueAtualET.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        lbEstoqueAtualET.setText("Estoque atual");

        lbMovimentacaoEstoqueET.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        lbMovimentacaoEstoqueET.setText("Movimentaçao de estoque ");

        lbClienteCadastradosET.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        lbClienteCadastradosET.setText("Clientes Cadastrados");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbClienteCadastradosET)
                    .addComponent(lbProdutosMaisVendidosET)
                    .addComponent(lbVendasPorPeriodoER)
                    .addComponent(lbProdutosPorCategoriaET)
                    .addComponent(lbProdutosComEstoqueBaixoET)
                    .addComponent(lbMovimentacaoEstoqueET)
                    .addComponent(lbEstoqueAtualET, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(lbVendasPorPeriodoER)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbProdutosMaisVendidosET)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbProdutosPorCategoriaET)
                .addGap(12, 12, 12)
                .addComponent(lbProdutosComEstoqueBaixoET)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbEstoqueAtualET)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbMovimentacaoEstoqueET, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbClienteCadastradosET)
                .addContainerGap(34, Short.MAX_VALUE))
        );

        jTable4.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Data/hora", "tipo de Relatorio", "Tipo", "Arquivo", "Ações"
            }
        ));
        jScrollPane4.setViewportView(jTable4);

        lbRelatoriosDisponiveisER.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        lbRelatoriosDisponiveisER.setText("Relatorios Disporniveis");

        javax.swing.GroupLayout jpEtiquetasRelatoriosLayout = new javax.swing.GroupLayout(jpEtiquetasRelatorios);
        jpEtiquetasRelatorios.setLayout(jpEtiquetasRelatoriosLayout);
        jpEtiquetasRelatoriosLayout.setHorizontalGroup(
            jpEtiquetasRelatoriosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpEtiquetasRelatoriosLayout.createSequentialGroup()
                .addGroup(jpEtiquetasRelatoriosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpEtiquetasRelatoriosLayout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addGroup(jpEtiquetasRelatoriosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbTipoRelatorioER, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jpEtiquetasRelatoriosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(lbRelatorioER, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lbTipoRelatorioER, javax.swing.GroupLayout.Alignment.LEADING))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpEtiquetasRelatoriosLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jpEtiquetasRelatoriosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btGerarER, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jpEtiquetasRelatoriosLayout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(lbRelatoriosDisponiveisER)))
                .addGroup(jpEtiquetasRelatoriosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpEtiquetasRelatoriosLayout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(btVisualizarER, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(btExportarPDFER, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(58, 58, 58)
                        .addComponent(btEsportarEXcelER, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jbSalvarCadastrar5, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jpEtiquetasRelatoriosLayout.createSequentialGroup()
                        .addGroup(jpEtiquetasRelatoriosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpEtiquetasRelatoriosLayout.createSequentialGroup()
                                .addGap(91, 91, 91)
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 585, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jpEtiquetasRelatoriosLayout.createSequentialGroup()
                                .addGap(100, 100, 100)
                                .addGroup(jpEtiquetasRelatoriosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbPeriodoER)
                                    .addGroup(jpEtiquetasRelatoriosLayout.createSequentialGroup()
                                        .addComponent(cbPeriodoER, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(lbAteER)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(cbPeriodo2ER, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(148, 148, 148)
                                .addGroup(jpEtiquetasRelatoriosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbCategoriaER)
                                    .addComponent(cbCategoriaER, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(211, 211, 211)
                                .addGroup(jpEtiquetasRelatoriosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbProduto2ER)
                                    .addGroup(jpEtiquetasRelatoriosLayout.createSequentialGroup()
                                        .addComponent(tfProduto2ER, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(btProduto2ER)))))
                        .addContainerGap(107, Short.MAX_VALUE))))
            .addGroup(jpEtiquetasRelatoriosLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpEtiquetasRelatoriosLayout.setVerticalGroup(
            jpEtiquetasRelatoriosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpEtiquetasRelatoriosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbRelatorioER)
                .addGap(19, 19, 19)
                .addGroup(jpEtiquetasRelatoriosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbTipoRelatorioER)
                    .addComponent(lbPeriodoER)
                    .addComponent(lbCategoriaER)
                    .addComponent(lbProduto2ER))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jpEtiquetasRelatoriosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbTipoRelatorioER, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbPeriodoER, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbAteER)
                    .addComponent(cbPeriodo2ER, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbCategoriaER, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfProduto2ER, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btProduto2ER))
                .addGap(34, 34, 34)
                .addGroup(jpEtiquetasRelatoriosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btGerarER)
                    .addComponent(btVisualizarER)
                    .addComponent(btExportarPDFER)
                    .addComponent(jbSalvarCadastrar5)
                    .addComponent(btEsportarEXcelER))
                .addGap(64, 64, 64)
                .addGroup(jpEtiquetasRelatoriosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpEtiquetasRelatoriosLayout.createSequentialGroup()
                        .addComponent(lbRelatoriosDisponiveisER)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(1131, Short.MAX_VALUE))
        );

        tbTelas.addTab("Etiquetas e relatorios", jpEtiquetasRelatorios);

        jpCodigoBarrasC.setBackground(new java.awt.Color(204, 204, 204));

        lbProdutoConsultaCB.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        lbProdutoConsultaCB.setText("Produto:");

        lbCodigoBarrasCB.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lbCodigoBarrasCB.setText("Codigo  de barras:");

        tfCodigoBarrasCB.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N

        lbTipoCodigoCB.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lbTipoCodigoCB.setText("Tipo de codigo:");

        cbTipoCodigoCB.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        cbTipoCodigoCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        tbListasCodigoBarrasCB.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        tbListasCodigoBarrasCB.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Codigo", "Nome da Unidade", "Descriçao", "Status", "Imagem"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tbListasCodigoBarrasCB);

        lbImagemCodigoCB.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lbImagemCodigoCB.setText("imagem do codigo:");
        lbImagemCodigoCB.setToolTipText("");

        cbStatusCb.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        cbStatusCb.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        lbPesquisarPorCB.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        lbPesquisarPorCB.setText("Pesquisar por:");

        lbDescricaoCB.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lbDescricaoCB.setText("Descrição:");

        tfPesquisarPor.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N

        lbStatusCB.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lbStatusCB.setText("Status:");

        jpImagemCodigo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btSelecionarCodigo.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        btSelecionarCodigo.setText("Selecionar Imagem");
        btSelecionarCodigo.addActionListener(this::btSelecionarCodigoActionPerformed);

        btRemover.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        btRemover.setText("Remover");

        javax.swing.GroupLayout jpImagemCodigoLayout = new javax.swing.GroupLayout(jpImagemCodigo);
        jpImagemCodigo.setLayout(jpImagemCodigoLayout);
        jpImagemCodigoLayout.setHorizontalGroup(
            jpImagemCodigoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpImagemCodigoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btSelecionarCodigo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                .addComponent(btRemover, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jpImagemCodigoLayout.setVerticalGroup(
            jpImagemCodigoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpImagemCodigoLayout.createSequentialGroup()
                .addContainerGap(189, Short.MAX_VALUE)
                .addGroup(jpImagemCodigoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btSelecionarCodigo)
                    .addComponent(btRemover))
                .addGap(14, 14, 14))
        );

        jbAdicionarCB.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        jbAdicionarCB.setIcon(new javax.swing.ImageIcon("C:\\Users\\EnzoLeite\\Documents\\ProjetoPI_Supermercado\\GerenciamentoSupermercado\\src\\main\\resorce\\Assets\\adicionar.png")); // NOI18N
        jbAdicionarCB.setText("Adicionar");

        jbSalvarCB.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        jbSalvarCB.setIcon(new javax.swing.ImageIcon("C:\\Users\\EnzoLeite\\Documents\\ProjetoPI_Supermercado\\GerenciamentoSupermercado\\src\\main\\resorce\\Assets\\salve-.png")); // NOI18N
        jbSalvarCB.setText("Salvar");

        jbEditarCB.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        jbEditarCB.setIcon(new javax.swing.ImageIcon("C:\\Users\\EnzoLeite\\Documents\\ProjetoPI_Supermercado\\GerenciamentoSupermercado\\src\\main\\resorce\\Assets\\lapis.png")); // NOI18N
        jbEditarCB.setText("Editar");

        jbExcluirCB.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        jbExcluirCB.setIcon(new javax.swing.ImageIcon("C:\\Users\\EnzoLeite\\Documents\\ProjetoPI_Supermercado\\GerenciamentoSupermercado\\src\\main\\resorce\\Assets\\lixeira.png")); // NOI18N
        jbExcluirCB.setText("Excluir");

        jbLimparCB.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        jbLimparCB.setIcon(new javax.swing.ImageIcon("C:\\Users\\EnzoLeite\\Documents\\ProjetoPI_Supermercado\\GerenciamentoSupermercado\\src\\main\\resorce\\Assets\\vassoura.png")); // NOI18N
        jbLimparCB.setText("Limpar");

        jbPesquisarCB.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        jbPesquisarCB.setIcon(new javax.swing.ImageIcon("C:\\Users\\EnzoLeite\\Documents\\ProjetoPI_Supermercado\\GerenciamentoSupermercado\\src\\main\\resorce\\Assets\\lupa.png")); // NOI18N
        jbPesquisarCB.setText("Pesquisar");

        cbPesquisarPor.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        cbPesquisarPor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jbIconePesquisaCB.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        jbIconePesquisaCB.setText("Pesquisar");

        lbTotalCodigiBarrasCadastradoCB.setText("Total de 5 codigos barras cadastradas ");

        cbProdutoCB.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        cbProdutoCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        taDescricaoCB.setColumns(20);
        taDescricaoCB.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        taDescricaoCB.setRows(5);
        jScrollPane5.setViewportView(taDescricaoCB);

        lbListaCodigoBarrasCB.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        lbListaCodigoBarrasCB.setText("Listas de codigos de barras");

        cadastroCodigoBarras.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        cadastroCodigoBarras.setText("Cadastro do codigo de barras:");

        lbCategoriasCB.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        lbCategoriasCB.setText("Categorias:");

        tfCategoriasCB.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        tfCategoriasCB.addActionListener(this::tfCategoriasCBActionPerformed);

        lbMarcasCB.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        lbMarcasCB.setText("Marcas:");

        tfMarcasCB.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N

        lbUnidadeMedidaCB.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        lbUnidadeMedidaCB.setText("Unidade de Medida");

        jComboBox14.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        jComboBox14.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jpCodigoBarrasCLayout = new javax.swing.GroupLayout(jpCodigoBarrasC);
        jpCodigoBarrasC.setLayout(jpCodigoBarrasCLayout);
        jpCodigoBarrasCLayout.setHorizontalGroup(
            jpCodigoBarrasCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpCodigoBarrasCLayout.createSequentialGroup()
                .addGroup(jpCodigoBarrasCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpCodigoBarrasCLayout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(lbTotalCodigiBarrasCadastradoCB))
                    .addGroup(jpCodigoBarrasCLayout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(cadastroCodigoBarras, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpCodigoBarrasCLayout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(lbPesquisarPorCB, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbPesquisarPor, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfPesquisarPor, javax.swing.GroupLayout.PREFERRED_SIZE, 372, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(jbIconePesquisaCB, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpCodigoBarrasCLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(jpCodigoBarrasCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jpCodigoBarrasCLayout.createSequentialGroup()
                                .addGroup(jpCodigoBarrasCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jpCodigoBarrasCLayout.createSequentialGroup()
                                        .addGroup(jpCodigoBarrasCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(jpCodigoBarrasCLayout.createSequentialGroup()
                                                .addComponent(lbTipoCodigoCB, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(4, 4, 4))
                                            .addGroup(jpCodigoBarrasCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(cbTipoCodigoCB, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(lbProdutoConsultaCB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(tfCodigoBarrasCB)
                                                .addComponent(lbCodigoBarrasCB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(cbProdutoCB, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(lbCategoriasCB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                        .addGroup(jpCodigoBarrasCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jpCodigoBarrasCLayout.createSequentialGroup()
                                                .addGap(146, 146, 146)
                                                .addGroup(jpCodigoBarrasCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(lbStatusCB)
                                                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 402, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(cbStatusCb, javax.swing.GroupLayout.PREFERRED_SIZE, 402, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(lbDescricaoCB, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addGroup(jpCodigoBarrasCLayout.createSequentialGroup()
                                                .addGap(102, 102, 102)
                                                .addGroup(jpCodigoBarrasCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(jComboBox14, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(lbUnidadeMedidaCB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                                    .addGroup(jpCodigoBarrasCLayout.createSequentialGroup()
                                        .addGap(4, 4, 4)
                                        .addGroup(jpCodigoBarrasCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(tfMarcasCB, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
                                            .addComponent(lbMarcasCB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(tfCategoriasCB))))
                                .addGap(93, 93, 93)
                                .addGroup(jpCodigoBarrasCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbImagemCodigoCB, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jpImagemCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jpCodigoBarrasCLayout.createSequentialGroup()
                                .addGroup(jpCodigoBarrasCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpCodigoBarrasCLayout.createSequentialGroup()
                                        .addComponent(jbAdicionarCB, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jbSalvarCB, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jbEditarCB, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jpCodigoBarrasCLayout.createSequentialGroup()
                                        .addGap(22, 22, 22)
                                        .addComponent(lbListaCodigoBarrasCB, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(99, 99, 99)
                                .addComponent(jbExcluirCB, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jbLimparCB, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jbPesquisarCB, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(306, 306, 306))))
                    .addGroup(jpCodigoBarrasCLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1145, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(177, Short.MAX_VALUE))
        );
        jpCodigoBarrasCLayout.setVerticalGroup(
            jpCodigoBarrasCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpCodigoBarrasCLayout.createSequentialGroup()
                .addGroup(jpCodigoBarrasCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpCodigoBarrasCLayout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(cadastroCodigoBarras)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbProdutoConsultaCB))
                    .addGroup(jpCodigoBarrasCLayout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(jpCodigoBarrasCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbImagemCodigoCB, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbDescricaoCB))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jpCodigoBarrasCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jpImagemCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jpCodigoBarrasCLayout.createSequentialGroup()
                                .addGroup(jpCodigoBarrasCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jpCodigoBarrasCLayout.createSequentialGroup()
                                        .addComponent(cbProdutoCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(27, 27, 27)
                                        .addComponent(lbCodigoBarrasCB)
                                        .addGap(9, 9, 9)
                                        .addComponent(tfCodigoBarrasCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jpCodigoBarrasCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lbTipoCodigoCB)
                                    .addComponent(lbStatusCB))
                                .addGap(8, 8, 8)
                                .addGroup(jpCodigoBarrasCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbTipoCodigoCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbStatusCb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jpCodigoBarrasCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbCategoriasCB)
                                    .addComponent(lbUnidadeMedidaCB))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jpCodigoBarrasCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tfCategoriasCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jComboBox14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGap(31, 31, 31)
                .addComponent(lbMarcasCB)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfMarcasCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(109, 109, 109)
                .addGroup(jpCodigoBarrasCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jbAdicionarCB, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jpCodigoBarrasCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jbEditarCB, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jbExcluirCB, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jbLimparCB, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jbPesquisarCB, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jbSalvarCB, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(38, 38, 38)
                .addComponent(lbListaCodigoBarrasCB)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpCodigoBarrasCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbPesquisarPor, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbPesquisarPorCB, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbIconePesquisaCB, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfPesquisarPor, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(49, 49, 49)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 840, Short.MAX_VALUE)
                .addComponent(lbTotalCodigiBarrasCadastradoCB)
                .addGap(30, 30, 30))
        );

        tbTelas.addTab("codigo de barras", jpCodigoBarrasC);

        lbEditarProdutoE.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        lbEditarProdutoE.setText("Editar produto");
        lbEditarProdutoE.setToolTipText("");

        lbCodigoProdutoE.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        lbCodigoProdutoE.setText("Codigo do produto:");

        tfCodigoProdutoE.addActionListener(this::tfCodigoProdutoEActionPerformed);

        lbNomeProdutoE.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        lbNomeProdutoE.setText("Nome do produto:");

        lbDescricaoE.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        lbDescricaoE.setText("Descrição");

        taDescricaoE.setColumns(20);
        taDescricaoE.setRows(5);
        jScrollPane8.setViewportView(taDescricaoE);

        lbCodigoBarrasE.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        lbCodigoBarrasE.setText("Codigo de barras:");

        lbStatusE.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        lbStatusE.setText("Status:");

        cbCategoriasE.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        cbCategoriasE.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        rbProdutoPesavelE.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        rbProdutoPesavelE.setText("Produto pesavel.");

        jbNovoE.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        jbNovoE.setIcon(new javax.swing.ImageIcon("C:\\Users\\EnzoLeite\\Documents\\ProjetoPI_Supermercado\\GerenciamentoSupermercado\\src\\main\\resorce\\Assets\\adicionar.png")); // NOI18N
        jbNovoE.setText("Novo");

        jbSalvarE.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        jbSalvarE.setIcon(new javax.swing.ImageIcon("C:\\Users\\EnzoLeite\\Documents\\ProjetoPI_Supermercado\\GerenciamentoSupermercado\\src\\main\\resorce\\Assets\\lapis.png")); // NOI18N
        jbSalvarE.setText("salvar");
        jbSalvarE.setToolTipText("");

        jbExcluirE.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        jbExcluirE.setIcon(new javax.swing.ImageIcon("C:\\Users\\EnzoLeite\\Documents\\ProjetoPI_Supermercado\\GerenciamentoSupermercado\\src\\main\\resorce\\Assets\\lixeira.png")); // NOI18N
        jbExcluirE.setText("Excluir");

        jbLimparE.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        jbLimparE.setIcon(new javax.swing.ImageIcon("C:\\Users\\EnzoLeite\\Documents\\ProjetoPI_Supermercado\\GerenciamentoSupermercado\\src\\main\\resorce\\Assets\\vassoura.png")); // NOI18N
        jbLimparE.setText("Limpa");

        jbPeswquisarE.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        jbPeswquisarE.setIcon(new javax.swing.ImageIcon("C:\\Users\\EnzoLeite\\Documents\\ProjetoPI_Supermercado\\GerenciamentoSupermercado\\src\\main\\resorce\\Assets\\lupa.png")); // NOI18N
        jbPeswquisarE.setText("Pesquisa");

        jbVoltarE.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        jbVoltarE.setIcon(new javax.swing.ImageIcon("C:\\Users\\EnzoLeite\\Documents\\ProjetoPI_Supermercado\\GerenciamentoSupermercado\\src\\main\\resorce\\Assets\\back.png")); // NOI18N
        jbVoltarE.setText("Voltar");

        tbListaProdutosE.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane9.setViewportView(tbListaProdutosE);

        lbListaProdutosE.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        lbListaProdutosE.setText("Lista de produtos ");

        lbPesquisarPorE.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        lbPesquisarPorE.setText("Pesquisa por:");

        cbPesquisarPorE.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        cbPesquisarPorE.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        tfPesquisarPorE.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        tfPesquisarPorE.setText("jTextField9");

        jbPesquisar2E.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        jbPesquisar2E.setIcon(new javax.swing.ImageIcon("C:\\Users\\EnzoLeite\\Documents\\ProjetoPI_Supermercado\\GerenciamentoSupermercado\\src\\main\\resorce\\Assets\\lupa.png")); // NOI18N
        jbPesquisar2E.setText("Pesquisar");

        lbCategoriasE.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        lbCategoriasE.setText("Categorias:");

        lbSubcategoriaE.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        lbSubcategoriaE.setText("Subcategoria:");

        cbMarcaE.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        cbMarcaE.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        lbMarcaE.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        lbMarcaE.setText("Marca:");

        lbUnidadeE.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        lbUnidadeE.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel36.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        jLabel36.setText("Unidade:");

        cbSubcategoriaE.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        cbSubcategoriaE.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbSubcategoriaE.addActionListener(this::cbSubcategoriaEActionPerformed);

        lbIconePrecoCustoE.setIcon(new javax.swing.ImageIcon("C:\\Users\\EnzoLeite\\Documents\\ProjetoPI_Supermercado\\GerenciamentoSupermercado\\src\\main\\resorce\\Assets\\bolsa-de-dinheiro.png")); // NOI18N

        lbPrecosCustoE.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        lbPrecosCustoE.setText("Preço de custo:");

        lbIconePrecoVendaE.setIcon(new javax.swing.ImageIcon("C:\\Users\\EnzoLeite\\Documents\\ProjetoPI_Supermercado\\GerenciamentoSupermercado\\src\\main\\resorce\\Assets\\bolsa-de-dinheiro.png")); // NOI18N

        lbEstoqueAtualE.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        lbEstoqueAtualE.setText("Estoque atual:");

        lbEstoqueMinimoE.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        lbEstoqueMinimoE.setText("Estoque minimo:");

        jbCodigoBarras2E.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        jbCodigoBarras2E.setText("Codigo de barras:");

        lbImagenProdutoE.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        lbImagenProdutoE.setText("Imagen do produto:");

        lbImagenProdutoIconeE.setBackground(new java.awt.Color(51, 255, 51));
        lbImagenProdutoIconeE.setFont(new java.awt.Font("Arial", 2, 12)); // NOI18N
        lbImagenProdutoIconeE.setIcon(new javax.swing.ImageIcon("C:\\Users\\EnzoLeite\\Downloads\\Imagen de teste.jpg")); // NOI18N

        jbSelecionarImagenE.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        jbSelecionarImagenE.setText("Selecionar imagen.");

        jbRemoverE.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        jbRemoverE.setText("Remover.");

        jbGerarE.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        jbGerarE.setText("Gerar");

        lbUnidadeMedidaE.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        lbUnidadeMedidaE.setText("Unidade de medida:");

        cbUnidadeMedidaE.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        cbUnidadeMedidaE.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        lbPrecoVendaE.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        lbPrecoVendaE.setText("Preço de venda:");

        cbStatusE.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        cbStatusE.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jpEdicaoLayout = new javax.swing.GroupLayout(jpEdicao);
        jpEdicao.setLayout(jpEdicaoLayout);
        jpEdicaoLayout.setHorizontalGroup(
            jpEdicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpEdicaoLayout.createSequentialGroup()
                .addGroup(jpEdicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jpEdicaoLayout.createSequentialGroup()
                        .addGroup(jpEdicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpEdicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lbCodigoProdutoE, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(tfCodigoProdutoE, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lbEditarProdutoE, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jpEdicaoLayout.createSequentialGroup()
                                .addGroup(jpEdicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbNomeProdutoE, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbDescricaoE, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tfNomeProdutoE, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jpEdicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpEdicaoLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lbIconePrecoVendaE, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                    .addGroup(jpEdicaoLayout.createSequentialGroup()
                                        .addGap(134, 134, 134)
                                        .addGroup(jpEdicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(cbCategoriasE, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jpEdicaoLayout.createSequentialGroup()
                                                .addComponent(lbSubcategoriaE, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(lbIconePrecoCustoE))
                                            .addComponent(lbCategoriasE, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(12, 12, 12)))
                                .addGroup(jpEdicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbPrecosCustoE, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tfPrecoVendaE, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tfPrecoCustoE, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbPrecoVendaE, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(jpEdicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpEdicaoLayout.createSequentialGroup()
                                .addGap(325, 325, 325)
                                .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpEdicaoLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jpEdicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpEdicaoLayout.createSequentialGroup()
                                        .addGroup(jpEdicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lbImagenProdutoIconeE, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jpEdicaoLayout.createSequentialGroup()
                                                .addGap(35, 35, 35)
                                                .addComponent(lbImagenProdutoE, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(9, 9, 9))
                                    .addComponent(jbCodigoBarras2E, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jpEdicaoLayout.createSequentialGroup()
                        .addGroup(jpEdicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jpEdicaoLayout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addComponent(jbNovoE)
                                .addGap(37, 37, 37)
                                .addComponent(jbSalvarE)
                                .addGap(73, 73, 73)
                                .addComponent(jbExcluirE)
                                .addGap(64, 64, 64)
                                .addComponent(jbLimparE))
                            .addComponent(lbListaProdutosE, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jpEdicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpEdicaoLayout.createSequentialGroup()
                                .addGap(62, 62, 62)
                                .addComponent(jbPeswquisarE)
                                .addGap(52, 52, 52)
                                .addComponent(jbVoltarE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpEdicaoLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jpEdicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jbRemoverE)
                                    .addComponent(jbSelecionarImagenE))
                                .addGap(40, 40, 40))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jpEdicaoLayout.createSequentialGroup()
                        .addGroup(jpEdicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbCodigoBarrasE, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jpEdicaoLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(tfCodigoBarrasE, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jpEdicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpEdicaoLayout.createSequentialGroup()
                                .addGap(44, 44, 44)
                                .addGroup(jpEdicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbSubcategoriaE, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbMarcaE, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbMarcaE, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jpEdicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(cbUnidadeMedidaE, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lbUnidadeMedidaE, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpEdicaoLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lbUnidadeE, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(jpEdicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbEstoqueAtualE, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfEstoqueAtualE, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbEstoqueMinimoE, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfEstoqueMinimoE, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(529, 529, 529))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jpEdicaoLayout.createSequentialGroup()
                        .addGroup(jpEdicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lbStatusE, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jpEdicaoLayout.createSequentialGroup()
                                .addComponent(cbStatusE, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(29, 29, 29)
                                .addComponent(rbProdutoPesavelE, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jpEdicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfCodigoBarras2E, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpEdicaoLayout.createSequentialGroup()
                                .addComponent(jbGerarE)
                                .addGap(82, 82, 82)))))
                .addGap(336, 336, 336))
            .addGroup(jpEdicaoLayout.createSequentialGroup()
                .addGroup(jpEdicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpEdicaoLayout.createSequentialGroup()
                        .addComponent(lbPesquisarPorE, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbPesquisarPorE, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(tfPesquisarPorE, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(jbPesquisar2E, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 1230, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpEdicaoLayout.setVerticalGroup(
            jpEdicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpEdicaoLayout.createSequentialGroup()
                .addGroup(jpEdicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpEdicaoLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(lbEditarProdutoE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpEdicaoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lbImagenProdutoE)))
                .addGroup(jpEdicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpEdicaoLayout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(jpEdicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jpEdicaoLayout.createSequentialGroup()
                                .addComponent(lbCodigoProdutoE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jpEdicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbPrecosCustoE, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(tfCodigoProdutoE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18))
                            .addGroup(jpEdicaoLayout.createSequentialGroup()
                                .addComponent(lbCategoriasE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbCategoriasE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(3, 3, 3)))
                        .addGroup(jpEdicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpEdicaoLayout.createSequentialGroup()
                                .addComponent(lbSubcategoriaE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jpEdicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbPrecoVendaE, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(cbSubcategoriaE, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jpEdicaoLayout.createSequentialGroup()
                                .addGroup(jpEdicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lbNomeProdutoE)
                                    .addComponent(tfPrecoCustoE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbIconePrecoCustoE, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tfNomeProdutoE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpEdicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jpEdicaoLayout.createSequentialGroup()
                                .addGroup(jpEdicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lbMarcaE)
                                    .addComponent(tfPrecoVendaE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(10, 10, 10)
                                .addGroup(jpEdicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbDescricaoE)
                                    .addComponent(cbMarcaE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jpEdicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jpEdicaoLayout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(27, 27, 27)
                                        .addComponent(lbCodigoBarrasE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(tfCodigoBarrasE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jpEdicaoLayout.createSequentialGroup()
                                        .addGap(12, 12, 12)
                                        .addComponent(jLabel36)
                                        .addGap(18, 18, 18)
                                        .addGroup(jpEdicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lbUnidadeE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jbSelecionarImagenE, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(jpEdicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jbRemoverE, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jpEdicaoLayout.createSequentialGroup()
                                                .addGap(31, 31, 31)
                                                .addComponent(cbUnidadeMedidaE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(30, 30, 30)
                                        .addComponent(jbCodigoBarras2E)))
                                .addGap(15, 15, 15))
                            .addGroup(jpEdicaoLayout.createSequentialGroup()
                                .addComponent(lbIconePrecoVendaE, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbEstoqueAtualE)
                                .addGap(18, 18, 18)
                                .addComponent(tfEstoqueAtualE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lbEstoqueMinimoE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(tfEstoqueMinimoE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(91, 91, 91))))
                    .addGroup(jpEdicaoLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbImagenProdutoIconeE, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(53, 53, 53)
                        .addComponent(jLabel44)
                        .addGap(89, 89, 89)
                        .addComponent(lbUnidadeMedidaE)
                        .addGap(22, 22, 22)))
                .addGroup(jpEdicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpEdicaoLayout.createSequentialGroup()
                        .addComponent(lbStatusE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpEdicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbStatusE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rbProdutoPesavelE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 157, Short.MAX_VALUE)
                        .addGroup(jpEdicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jbNovoE, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jbSalvarE, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jbExcluirE, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jbLimparE, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jbPeswquisarE, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jbVoltarE, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(33, 33, 33)
                        .addComponent(lbListaProdutosE)
                        .addGap(53, 53, 53)
                        .addGroup(jpEdicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbPesquisarPorE)
                            .addComponent(cbPesquisarPorE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfPesquisarPorE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jbPesquisar2E, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26))
                    .addGroup(jpEdicaoLayout.createSequentialGroup()
                        .addComponent(tfCodigoBarras2E, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jbGerarE, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(579, Short.MAX_VALUE))
        );

        tbTelas.addTab("Edição", jpEdicao);

        jLabel60.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        jLabel60.setText("Cadastro categorias:");

        jLabel61.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        jLabel61.setText("Código da categoria:");

        jTextField20.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N

        jLabel62.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        jLabel62.setText("Nome da categoria:");

        jTextField21.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N

        jLabel63.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        jLabel63.setText("Descrição:");

        jTextArea3.setColumns(20);
        jTextArea3.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        jTextArea3.setRows(5);
        jScrollPane12.setViewportView(jTextArea3);

        jLabel64.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        jLabel64.setText("Status:");

        jComboBox22.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        jComboBox22.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel65.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        jLabel65.setIcon(new javax.swing.ImageIcon("C:\\Users\\EnzoLeite\\Downloads\\Imagen de teste.jpg")); // NOI18N

        jButton15.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        jButton15.setText("Selecionar imagen");

        jButton16.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        jButton16.setText("Remover");

        jRadioButton7.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        jRadioButton7.setText("Categoria em destaque");

        jButton17.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        jButton17.setIcon(new javax.swing.ImageIcon("C:\\Users\\EnzoLeite\\Documents\\ProjetoPI_Supermercado\\GerenciamentoSupermercado\\src\\main\\resorce\\Assets\\adicionar.png")); // NOI18N
        jButton17.setText("Novo");

        jButton18.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        jButton18.setIcon(new javax.swing.ImageIcon("C:\\Users\\EnzoLeite\\Documents\\ProjetoPI_Supermercado\\GerenciamentoSupermercado\\src\\main\\resorce\\Assets\\lapis.png")); // NOI18N
        jButton18.setText("Editar");

        jButton19.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        jButton19.setIcon(new javax.swing.ImageIcon("C:\\Users\\EnzoLeite\\Documents\\ProjetoPI_Supermercado\\GerenciamentoSupermercado\\src\\main\\resorce\\Assets\\salve-.png")); // NOI18N
        jButton19.setText("Salvar");

        jButton20.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        jButton20.setIcon(new javax.swing.ImageIcon("C:\\Users\\EnzoLeite\\Documents\\ProjetoPI_Supermercado\\GerenciamentoSupermercado\\src\\main\\resorce\\Assets\\lixeira.png")); // NOI18N
        jButton20.setText("Excluir");

        jButton21.setIcon(new javax.swing.ImageIcon("C:\\Users\\EnzoLeite\\Documents\\ProjetoPI_Supermercado\\GerenciamentoSupermercado\\src\\main\\resorce\\Assets\\vassoura.png")); // NOI18N
        jButton21.setText("Limpar");

        jButton22.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        jButton22.setIcon(new javax.swing.ImageIcon("C:\\Users\\EnzoLeite\\Documents\\ProjetoPI_Supermercado\\GerenciamentoSupermercado\\src\\main\\resorce\\Assets\\lupa.png")); // NOI18N
        jButton22.setText("Pesquisar");

        jButton23.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        jButton23.setIcon(new javax.swing.ImageIcon("C:\\Users\\EnzoLeite\\Documents\\ProjetoPI_Supermercado\\GerenciamentoSupermercado\\src\\main\\resorce\\Assets\\back.png")); // NOI18N
        jButton23.setText("Voltar");

        jLabel56.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        jLabel56.setText("Lista de Categorias");

        jLabel68.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        jLabel68.setText("Pesquisar por:");

        jComboBox25.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        jComboBox25.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jTextField18.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N

        jButton24.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        jButton24.setIcon(new javax.swing.ImageIcon("C:\\Users\\EnzoLeite\\Documents\\ProjetoPI_Supermercado\\GerenciamentoSupermercado\\src\\main\\resorce\\Assets\\lupa.png")); // NOI18N
        jButton24.setText("Pesquisar");

        jTable6.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        jTable6.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane13.setViewportView(jTable6);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel62, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField21, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel63, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel10Layout.createSequentialGroup()
                                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel61, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTextField20, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(87, 87, 87)
                                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel64, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jComboBox22, 0, 208, Short.MAX_VALUE)))))
                            .addComponent(jLabel60, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(234, 234, 234)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jRadioButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel10Layout.createSequentialGroup()
                                    .addComponent(jButton15)
                                    .addGap(18, 18, 18)
                                    .addComponent(jButton16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addComponent(jLabel65, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 1245, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel10Layout.createSequentialGroup()
                                        .addComponent(jLabel68, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jComboBox25, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(jLabel56, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                                        .addComponent(jButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton19, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton18, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel10Layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton20, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton21, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton22, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton23, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel10Layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(jTextField18, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(48, 48, 48)
                                        .addComponent(jButton24, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                .addContainerGap(125, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel60)
                        .addGap(61, 61, 61)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel64)
                            .addComponent(jLabel61))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBox22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(33, 33, 33)
                        .addComponent(jLabel62)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(jLabel63)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel65, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton16, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jRadioButton7)))
                .addGap(52, 52, 52)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton19, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton18, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton20, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton21, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton22, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton23, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addComponent(jLabel56)
                .addGap(24, 24, 24)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel68, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9))
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jComboBox25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton24, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 389, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 956, Short.MAX_VALUE))
        );

        tbTelas.addTab("Categorias", jPanel10);

        jLabel69.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        jLabel69.setText("Cadastro de subcategoria");

        jTextField22.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N

        jLabel70.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        jLabel70.setText("Código da subcategoria:");
        jLabel70.setToolTipText("");

        jLabel71.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        jLabel71.setText("Nome da subcategoria:");

        jTextField23.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N

        jLabel72.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        jLabel72.setText("Descrição:");

        jTextArea4.setColumns(20);
        jTextArea4.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        jTextArea4.setRows(5);
        jScrollPane14.setViewportView(jTextArea4);

        jLabel73.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        jLabel73.setText("Categoria:");

        jComboBox26.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        jComboBox26.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel74.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        jLabel74.setText("Status:");

        jComboBox27.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        jComboBox27.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel75.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        jLabel75.setIcon(new javax.swing.ImageIcon("C:\\Users\\EnzoLeite\\Downloads\\Imagen de teste.jpg")); // NOI18N

        jButton25.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        jButton25.setText("Selecionar imagen");

        jButton26.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        jButton26.setText("Remover");

        jRadioButton8.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        jRadioButton8.setText("Categoria em destaque");

        jButton27.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        jButton27.setIcon(new javax.swing.ImageIcon("C:\\Users\\EnzoLeite\\Documents\\ProjetoPI_Supermercado\\GerenciamentoSupermercado\\src\\main\\resorce\\Assets\\adicionar.png")); // NOI18N
        jButton27.setText("Novo");

        jButton28.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        jButton28.setIcon(new javax.swing.ImageIcon("C:\\Users\\EnzoLeite\\Documents\\ProjetoPI_Supermercado\\GerenciamentoSupermercado\\src\\main\\resorce\\Assets\\salve-.png")); // NOI18N
        jButton28.setText("Salvar");

        jButton29.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        jButton29.setIcon(new javax.swing.ImageIcon("C:\\Users\\EnzoLeite\\Documents\\ProjetoPI_Supermercado\\GerenciamentoSupermercado\\src\\main\\resorce\\Assets\\lapis.png")); // NOI18N
        jButton29.setText("Editar");

        jButton30.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        jButton30.setIcon(new javax.swing.ImageIcon("C:\\Users\\EnzoLeite\\Documents\\ProjetoPI_Supermercado\\GerenciamentoSupermercado\\src\\main\\resorce\\Assets\\lixeira.png")); // NOI18N
        jButton30.setText("Excluir");

        jButton31.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        jButton31.setIcon(new javax.swing.ImageIcon("C:\\Users\\EnzoLeite\\Documents\\ProjetoPI_Supermercado\\GerenciamentoSupermercado\\src\\main\\resorce\\Assets\\vassoura.png")); // NOI18N
        jButton31.setText("Limpar");

        jButton32.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        jButton32.setIcon(new javax.swing.ImageIcon("C:\\Users\\EnzoLeite\\Documents\\ProjetoPI_Supermercado\\GerenciamentoSupermercado\\src\\main\\resorce\\Assets\\lupa.png")); // NOI18N
        jButton32.setText("Pesquisar");

        jButton33.setIcon(new javax.swing.ImageIcon("C:\\Users\\EnzoLeite\\Documents\\ProjetoPI_Supermercado\\GerenciamentoSupermercado\\src\\main\\resorce\\Assets\\back.png")); // NOI18N
        jButton33.setText("Voltar");

        jLabel76.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        jLabel76.setText("Lista de sub categoria");

        jLabel77.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        jLabel77.setText("Pesquisar por:");

        jComboBox28.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        jComboBox28.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jTextField24.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N

        jButton34.setIcon(new javax.swing.ImageIcon("C:\\Users\\EnzoLeite\\Documents\\ProjetoPI_Supermercado\\GerenciamentoSupermercado\\src\\main\\resorce\\Assets\\lupa.png")); // NOI18N
        jButton34.setText("Pesquisar");

        jTable7.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        jTable7.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane15.setViewportView(jTable7);

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel69, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jTextField22, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel70, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jLabel72, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jTextField23, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel71, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGap(152, 152, 152)
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel73, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jComboBox26, 0, 154, Short.MAX_VALUE)
                                    .addComponent(jLabel74, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jComboBox27, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel75, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel11Layout.createSequentialGroup()
                                    .addComponent(jButton25)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jButton26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(jRadioButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(318, 318, 318))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jTextField24, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel11Layout.createSequentialGroup()
                                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(jPanel11Layout.createSequentialGroup()
                                                .addComponent(jLabel77, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jComboBox28, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addComponent(jLabel76, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGroup(jPanel11Layout.createSequentialGroup()
                                                .addComponent(jButton27)
                                                .addGap(18, 18, 18)
                                                .addComponent(jButton28)
                                                .addGap(18, 18, 18)
                                                .addComponent(jButton29)))
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton30)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton31)))
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel11Layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton32)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton33))
                                    .addGroup(jPanel11Layout.createSequentialGroup()
                                        .addGap(7, 7, 7)
                                        .addComponent(jButton34, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 1234, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(136, Short.MAX_VALUE))))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel69)
                        .addGap(30, 30, 30)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel70)
                            .addComponent(jLabel73))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel71)
                            .addComponent(jLabel74))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)
                        .addComponent(jLabel72)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(136, 136, 136)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton27, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton28, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton29, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton30, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton31, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton32, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton33, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel76)
                        .addGap(28, 28, 28)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel77)
                            .addComponent(jComboBox28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton34, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel75, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton25, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton26, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jRadioButton8)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(1064, Short.MAX_VALUE))
        );

        tbTelas.addTab("Subcategoria", jPanel11);

        jLabel78.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        jLabel78.setText("Cadastro de marcas");

        jLabel79.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        jLabel79.setText("Codigo da marca:");

        jTextField25.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N

        jLabel80.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        jLabel80.setText("Nome da marca:");

        jTextField26.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N

        jLabel81.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        jLabel81.setText("Descrição:");

        jTextArea5.setColumns(20);
        jTextArea5.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        jTextArea5.setRows(5);
        jScrollPane16.setViewportView(jTextArea5);

        jLabel82.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        jLabel82.setText("Status:");

        jComboBox29.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        jComboBox29.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel83.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        jLabel83.setIcon(new javax.swing.ImageIcon("C:\\Users\\EnzoLeite\\Downloads\\Imagen de teste.jpg")); // NOI18N

        jButton35.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        jButton35.setText("Selecionar imagen");

        jButton36.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        jButton36.setText("Remover");

        jRadioButton9.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        jRadioButton9.setText("Categorias em destaque");

        jButton37.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        jButton37.setIcon(new javax.swing.ImageIcon("C:\\Users\\EnzoLeite\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\adicionar.png")); // NOI18N
        jButton37.setText("Novo");

        jButton38.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        jButton38.setIcon(new javax.swing.ImageIcon("C:\\Users\\EnzoLeite\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\salve-.png")); // NOI18N
        jButton38.setText("Salvar");

        jButton39.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        jButton39.setIcon(new javax.swing.ImageIcon("C:\\Users\\EnzoLeite\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\lapis.png")); // NOI18N
        jButton39.setText("Editar");

        jButton40.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        jButton40.setIcon(new javax.swing.ImageIcon("C:\\Users\\EnzoLeite\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\lixeira.png")); // NOI18N
        jButton40.setText("Excluir");

        jButton41.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        jButton41.setIcon(new javax.swing.ImageIcon("C:\\Users\\EnzoLeite\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\vassoura.png")); // NOI18N
        jButton41.setText("Limpar");

        jButton42.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        jButton42.setIcon(new javax.swing.ImageIcon("C:\\Users\\EnzoLeite\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\lupa.png")); // NOI18N
        jButton42.setText("Pesquisar");

        jButton43.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        jButton43.setIcon(new javax.swing.ImageIcon("C:\\Users\\EnzoLeite\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\back.png")); // NOI18N
        jButton43.setText("Voltar");
        jButton43.addActionListener(this::jButton43ActionPerformed);

        jLabel84.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        jLabel84.setText("Lista de marcas:");

        jLabel85.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        jLabel85.setText("Pesquisar por:");

        jComboBox30.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        jComboBox30.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jTextField27.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N

        jButton44.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        jButton44.setIcon(new javax.swing.ImageIcon("C:\\Users\\EnzoLeite\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\lupa.png")); // NOI18N
        jButton44.setText("Pesquisar");

        jTable8.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        jTable8.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane17.setViewportView(jTable8);

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(jButton37)
                        .addGap(18, 18, 18)
                        .addComponent(jButton38)
                        .addGap(18, 18, 18)
                        .addComponent(jButton39))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel78, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel81, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jTextField26, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
                                    .addComponent(jLabel80, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jTextField25, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel79, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(149, 149, 149)
                                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel12Layout.createSequentialGroup()
                                        .addGap(26, 26, 26)
                                        .addComponent(jButton40)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton41)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton42)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton43))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel82, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jComboBox29, 0, 148, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel83, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel12Layout.createSequentialGroup()
                                                .addComponent(jButton35)
                                                .addGap(18, 18, 18)
                                                .addComponent(jButton36))
                                            .addComponent(jRadioButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel84, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(jLabel85, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox30, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField27, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(jButton44))
                    .addComponent(jScrollPane17, javax.swing.GroupLayout.PREFERRED_SIZE, 1166, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(210, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel78)
                .addGap(8, 8, 8)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel79)
                            .addComponent(jLabel82))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31)
                        .addComponent(jLabel80)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(jLabel81))
                    .addComponent(jLabel83, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton37, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                            .addComponent(jButton38, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton39, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton40, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton41, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jButton42, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton43, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel84)
                        .addGap(28, 28, 28)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel85)
                            .addComponent(jComboBox30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton44, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton35, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton36, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioButton9)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane17, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(1083, Short.MAX_VALUE))
        );

        tbTelas.addTab("Marcas", jPanel12);

        jLabel88.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        jLabel88.setText("Cadastro de unidades");

        jLabel89.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        jLabel89.setText("Código da unidade");

        jTextField29.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N

        jLabel90.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        jLabel90.setText("Nome da unidade:");

        jTextField30.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N

        jLabel91.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        jLabel91.setText("Descrição:");

        jTextArea6.setColumns(20);
        jTextArea6.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        jTextArea6.setRows(5);
        jScrollPane18.setViewportView(jTextArea6);

        jLabel92.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        jLabel92.setText("Status:");

        jComboBox31.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        jComboBox31.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel93.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        jLabel93.setIcon(new javax.swing.ImageIcon("C:\\Users\\EnzoLeite\\Downloads\\Imagen de teste.jpg")); // NOI18N

        jButton45.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        jButton45.setText("Selecionar imagen");

        jButton46.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        jButton46.setText("Remover");

        jRadioButton10.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        jRadioButton10.setText("Categorias em destaque");

        jButton47.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        jButton47.setIcon(new javax.swing.ImageIcon("C:\\Users\\EnzoLeite\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\adicionar.png")); // NOI18N
        jButton47.setText("Novo");

        jButton48.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        jButton48.setIcon(new javax.swing.ImageIcon("C:\\Users\\EnzoLeite\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\salve-.png")); // NOI18N
        jButton48.setText("Salvar");

        jButton49.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        jButton49.setIcon(new javax.swing.ImageIcon("C:\\Users\\EnzoLeite\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\lapis.png")); // NOI18N
        jButton49.setText("Editar");

        jButton50.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        jButton50.setIcon(new javax.swing.ImageIcon("C:\\Users\\EnzoLeite\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\lixeira.png")); // NOI18N
        jButton50.setText("Excluir");

        jButton51.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        jButton51.setIcon(new javax.swing.ImageIcon("C:\\Users\\EnzoLeite\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\vassoura.png")); // NOI18N
        jButton51.setText("Limpar");

        jButton52.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        jButton52.setIcon(new javax.swing.ImageIcon("C:\\Users\\EnzoLeite\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\lupa.png")); // NOI18N
        jButton52.setText("Pesquisar");

        jButton53.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        jButton53.setIcon(new javax.swing.ImageIcon("C:\\Users\\EnzoLeite\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\back.png")); // NOI18N
        jButton53.setText("Voltar");
        jButton53.addActionListener(this::jButton53ActionPerformed);

        jLabel94.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        jLabel94.setText("Lista de marcas:");

        jLabel95.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        jLabel95.setText("Pesquisar por:");

        jComboBox32.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        jComboBox32.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jTextField31.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N

        jButton54.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        jButton54.setIcon(new javax.swing.ImageIcon("C:\\Users\\EnzoLeite\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\lupa.png")); // NOI18N
        jButton54.setText("Pesquisar");

        jTable9.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        jTable9.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane19.setViewportView(jTable9);

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel94, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(jLabel95, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox32, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField31, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(jButton54))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(jButton47)
                        .addGap(18, 18, 18)
                        .addComponent(jButton48)
                        .addGap(18, 18, 18)
                        .addComponent(jButton49))
                    .addComponent(jScrollPane19, javax.swing.GroupLayout.PREFERRED_SIZE, 1166, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel88, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel14Layout.createSequentialGroup()
                                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel91, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jTextField30, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
                                    .addComponent(jLabel90, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jTextField29, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel89, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(149, 149, 149)
                                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel14Layout.createSequentialGroup()
                                        .addGap(26, 26, 26)
                                        .addComponent(jButton50)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton51)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton52)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton53))
                                    .addGroup(jPanel14Layout.createSequentialGroup()
                                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel92, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jComboBox31, 0, 148, Short.MAX_VALUE))
                                        .addGap(320, 320, 320)
                                        .addComponent(jLabel93, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel14Layout.createSequentialGroup()
                                .addComponent(jScrollPane18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                                        .addComponent(jButton45)
                                        .addGap(32, 32, 32)
                                        .addComponent(jButton46))
                                    .addComponent(jRadioButton10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(jLabel88)
                        .addGap(32, 32, 32)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel89)
                            .addComponent(jLabel92))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31)
                        .addComponent(jLabel90)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(jLabel91)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(jLabel93, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton45, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton46, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jRadioButton10)
                        .addGap(73, 73, 73)))
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton47, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                    .addComponent(jButton48, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton49, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton50, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton51, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton52, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton53, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addComponent(jLabel94)
                .addGap(28, 28, 28)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel95)
                    .addComponent(jComboBox32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton54, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane19, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(1083, Short.MAX_VALUE))
        );

        tbTelas.addTab("Unidades", jPanel14);

        jLabel96.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        jLabel96.setText("Cadastro de unidades de medida");

        jLabel97.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        jLabel97.setText("Código da unidade de medida:");

        jTextField32.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N

        jLabel98.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        jLabel98.setText("Nome da unidade de medida:");

        jTextField33.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N

        jLabel99.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        jLabel99.setText("Descrição:");

        jTextArea7.setColumns(20);
        jTextArea7.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        jTextArea7.setRows(5);
        jScrollPane20.setViewportView(jTextArea7);

        jLabel100.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        jLabel100.setText("Status:");

        jComboBox33.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        jComboBox33.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel101.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        jLabel101.setIcon(new javax.swing.ImageIcon("C:\\Users\\EnzoLeite\\Downloads\\Imagen de teste.jpg")); // NOI18N

        jButton55.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        jButton55.setText("Selecionar imagen");

        jButton56.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        jButton56.setText("Remover");

        jRadioButton11.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        jRadioButton11.setText("Categorias em destaque");

        jButton57.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        jButton57.setIcon(new javax.swing.ImageIcon("C:\\Users\\EnzoLeite\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\adicionar.png")); // NOI18N
        jButton57.setText("Novo");

        jButton58.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        jButton58.setIcon(new javax.swing.ImageIcon("C:\\Users\\EnzoLeite\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\salve-.png")); // NOI18N
        jButton58.setText("Salvar");

        jButton59.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        jButton59.setIcon(new javax.swing.ImageIcon("C:\\Users\\EnzoLeite\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\lapis.png")); // NOI18N
        jButton59.setText("Editar");

        jButton60.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        jButton60.setIcon(new javax.swing.ImageIcon("C:\\Users\\EnzoLeite\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\lixeira.png")); // NOI18N
        jButton60.setText("Excluir");

        jButton61.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        jButton61.setIcon(new javax.swing.ImageIcon("C:\\Users\\EnzoLeite\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\vassoura.png")); // NOI18N
        jButton61.setText("Limpar");

        jButton62.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        jButton62.setIcon(new javax.swing.ImageIcon("C:\\Users\\EnzoLeite\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\lupa.png")); // NOI18N
        jButton62.setText("Pesquisar");

        jButton63.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        jButton63.setIcon(new javax.swing.ImageIcon("C:\\Users\\EnzoLeite\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\back.png")); // NOI18N
        jButton63.setText("Voltar");
        jButton63.addActionListener(this::jButton63ActionPerformed);

        jLabel102.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        jLabel102.setText("Lista de marcas:");

        jLabel103.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        jLabel103.setText("Pesquisar por:");

        jComboBox34.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        jComboBox34.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jTextField34.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N

        jButton64.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        jButton64.setIcon(new javax.swing.ImageIcon("C:\\Users\\EnzoLeite\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\lupa.png")); // NOI18N
        jButton64.setText("Pesquisar");

        jTable10.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        jTable10.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane21.setViewportView(jTable10);

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel102, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(jLabel103, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox34, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField34, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(jButton64))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(jButton57)
                        .addGap(18, 18, 18)
                        .addComponent(jButton58)
                        .addGap(18, 18, 18)
                        .addComponent(jButton59))
                    .addComponent(jScrollPane21, javax.swing.GroupLayout.PREFERRED_SIZE, 1166, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel15Layout.createSequentialGroup()
                                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel99, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jTextField33, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel98, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jTextField32, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel97, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(149, 149, 149)
                                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel15Layout.createSequentialGroup()
                                        .addGap(26, 26, 26)
                                        .addComponent(jButton60)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton61)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton62)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton63))
                                    .addGroup(jPanel15Layout.createSequentialGroup()
                                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel100, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jComboBox33, 0, 148, Short.MAX_VALUE))
                                        .addGap(320, 320, 320)
                                        .addComponent(jLabel101, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel15Layout.createSequentialGroup()
                                .addComponent(jScrollPane20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                                        .addComponent(jButton55)
                                        .addGap(32, 32, 32)
                                        .addComponent(jButton56))
                                    .addComponent(jRadioButton11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel96, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(210, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel96)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel97)
                            .addComponent(jLabel100))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox33, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31)
                        .addComponent(jLabel98)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField33, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(jLabel99)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(jLabel101, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton55, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton56, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jRadioButton11)
                        .addGap(73, 73, 73)))
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton57, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                    .addComponent(jButton58, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton59, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton60, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton61, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton62, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton63, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addComponent(jLabel102)
                .addGap(28, 28, 28)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel103)
                    .addComponent(jComboBox34, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField34, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton64, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane21, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(1061, Short.MAX_VALUE))
        );

        tbTelas.addTab("Unidades de medida", jPanel15);

        jLabel104.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        jLabel104.setText("Cadastro e gerenciamento de imagens");

        jLabel105.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        jLabel105.setText("Produto:");

        jTextField35.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N

        jLabel106.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        jLabel106.setText("Imagen:");

        jTextField36.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N

        jLabel107.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        jLabel107.setText("Descrição da imagen:");

        jTextArea8.setColumns(20);
        jTextArea8.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        jTextArea8.setRows(5);
        jScrollPane22.setViewportView(jTextArea8);

        jLabel108.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        jLabel108.setText("Data de vigencia:");

        jComboBox35.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        jComboBox35.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel109.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        jLabel109.setIcon(new javax.swing.ImageIcon("C:\\Users\\EnzoLeite\\Downloads\\Imagen de teste.jpg")); // NOI18N

        jButton65.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        jButton65.setText("Selecionar produtos");

        jButton66.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        jButton66.setIcon(new javax.swing.ImageIcon("C:\\Users\\EnzoLeite\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\lixeira.png")); // NOI18N
        jButton66.setText("Remover");

        jButton67.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        jButton67.setIcon(new javax.swing.ImageIcon("C:\\Users\\EnzoLeite\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\adicionar.png")); // NOI18N
        jButton67.setText("Novo");

        jButton68.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        jButton68.setIcon(new javax.swing.ImageIcon("C:\\Users\\EnzoLeite\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\salve-.png")); // NOI18N
        jButton68.setText("Salvar");

        jButton69.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        jButton69.setIcon(new javax.swing.ImageIcon("C:\\Users\\EnzoLeite\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\lapis.png")); // NOI18N
        jButton69.setText("Editar");

        jButton70.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        jButton70.setIcon(new javax.swing.ImageIcon("C:\\Users\\EnzoLeite\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\lixeira.png")); // NOI18N
        jButton70.setText("Excluir");

        jButton71.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        jButton71.setIcon(new javax.swing.ImageIcon("C:\\Users\\EnzoLeite\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\vassoura.png")); // NOI18N
        jButton71.setText("Limpar");

        jButton72.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        jButton72.setIcon(new javax.swing.ImageIcon("C:\\Users\\EnzoLeite\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\lupa.png")); // NOI18N
        jButton72.setText("Pesquisar");

        jButton73.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        jButton73.setIcon(new javax.swing.ImageIcon("C:\\Users\\EnzoLeite\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\back.png")); // NOI18N
        jButton73.setText("Voltar");
        jButton73.addActionListener(this::jButton73ActionPerformed);

        jLabel110.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        jLabel110.setText("Lista de imagens:");

        jLabel111.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        jLabel111.setText("Pesquisar por:");

        jComboBox36.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        jComboBox36.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jTextField37.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N

        jButton74.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        jButton74.setIcon(new javax.swing.ImageIcon("C:\\Users\\EnzoLeite\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\lupa.png")); // NOI18N
        jButton74.setText("Pesquisar");

        jTable11.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        jTable11.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane23.setViewportView(jTable11);

        jButton75.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        jButton75.setIcon(new javax.swing.ImageIcon("C:\\Users\\EnzoLeite\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\impressora.png")); // NOI18N
        jButton75.setText("Selecione arquivo");

        jLabel86.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        jLabel86.setText("Observação:");

        jTextArea9.setColumns(20);
        jTextArea9.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        jTextArea9.setRows(5);
        jScrollPane24.setViewportView(jTextArea9);

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel110, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addComponent(jLabel111, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox36, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField37, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(jButton74))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addComponent(jButton67)
                        .addGap(18, 18, 18)
                        .addComponent(jButton68)
                        .addGap(18, 18, 18)
                        .addComponent(jButton69))
                    .addComponent(jScrollPane23, javax.swing.GroupLayout.PREFERRED_SIZE, 1166, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jLabel107, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel106, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel105, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(jTextField36, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField35))
                                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel16Layout.createSequentialGroup()
                                        .addGap(75, 75, 75)
                                        .addComponent(jButton70)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton71)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton72)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton73))
                                    .addGroup(jPanel16Layout.createSequentialGroup()
                                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel16Layout.createSequentialGroup()
                                                .addGap(49, 49, 49)
                                                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(jLabel108, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(jComboBox35, 0, 148, Short.MAX_VALUE)))
                                            .addGroup(jPanel16Layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jButton75)))
                                        .addGap(320, 320, 320)
                                        .addComponent(jLabel109, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel16Layout.createSequentialGroup()
                                        .addGap(109, 109, 109)
                                        .addComponent(jLabel86, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addComponent(jScrollPane22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(47, 47, 47)
                                .addComponent(jScrollPane24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton65)
                                .addGap(32, 32, 32)
                                .addComponent(jButton66))
                            .addComponent(jLabel104))))
                .addContainerGap(210, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel104)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel105)
                            .addComponent(jLabel108))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField35, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox35, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31)
                        .addComponent(jLabel106)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField36, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton75))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel107)
                            .addComponent(jLabel86))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane24)
                            .addComponent(jScrollPane22))
                        .addGap(26, 26, 26))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addComponent(jLabel109, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton65, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton66, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(105, 105, 105)))
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton67, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                    .addComponent(jButton68, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton69, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton70, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton71, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton72, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton73, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addComponent(jLabel110)
                .addGap(28, 28, 28)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel111)
                    .addComponent(jComboBox36, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField37, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton74, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane23, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(1061, Short.MAX_VALUE))
        );

        tbTelas.addTab("Imagens", jPanel16);

        jLabel112.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        jLabel112.setText("Cadastro e gerenciamento de imagens");

        jLabel113.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        jLabel113.setText("Codigo produto:");

        jTextField38.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N

        jLabel114.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        jLabel114.setText("Nome do produto:");

        jTextField39.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N

        jLabel115.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        jLabel115.setText("Categoria:");

        jLabel116.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        jLabel116.setText("Marca:");

        jComboBox37.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        jComboBox37.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel117.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        jLabel117.setIcon(new javax.swing.ImageIcon("C:\\Users\\EnzoLeite\\Downloads\\Imagen de teste.jpg")); // NOI18N

        jButton76.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        jButton76.setText("Selecionar Imagem");

        jButton77.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        jButton77.setText("Remover");

        jButton78.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        jButton78.setIcon(new javax.swing.ImageIcon("C:\\Users\\EnzoLeite\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\adicionar.png")); // NOI18N
        jButton78.setText("Novo");

        jButton79.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        jButton79.setIcon(new javax.swing.ImageIcon("C:\\Users\\EnzoLeite\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\salve-.png")); // NOI18N
        jButton79.setText("Salvar");

        jButton80.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        jButton80.setIcon(new javax.swing.ImageIcon("C:\\Users\\EnzoLeite\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\lapis.png")); // NOI18N
        jButton80.setText("Editar");

        jButton81.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        jButton81.setIcon(new javax.swing.ImageIcon("C:\\Users\\EnzoLeite\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\lixeira.png")); // NOI18N
        jButton81.setText("Excluir");

        jButton82.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        jButton82.setIcon(new javax.swing.ImageIcon("C:\\Users\\EnzoLeite\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\vassoura.png")); // NOI18N
        jButton82.setText("Limpar");

        jButton83.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        jButton83.setIcon(new javax.swing.ImageIcon("C:\\Users\\EnzoLeite\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\lupa.png")); // NOI18N
        jButton83.setText("Pesquisar");

        jButton84.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        jButton84.setIcon(new javax.swing.ImageIcon("C:\\Users\\EnzoLeite\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\back.png")); // NOI18N
        jButton84.setText("Voltar");
        jButton84.addActionListener(this::jButton84ActionPerformed);

        jLabel118.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        jLabel118.setText("Lista de Produtos pesaveis:");

        jLabel119.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        jLabel119.setText("Pesquisar por:");

        jComboBox38.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        jComboBox38.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jTextField40.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N

        jButton85.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        jButton85.setIcon(new javax.swing.ImageIcon("C:\\Users\\EnzoLeite\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\lupa.png")); // NOI18N
        jButton85.setText("Pesquisar");

        jTable12.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        jTable12.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane26.setViewportView(jTable12);

        jComboBox39.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        jComboBox39.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel87.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        jLabel87.setText("Unidade de medida:");

        jComboBox40.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        jComboBox40.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel120.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        jLabel120.setText("Codigo de barras:");

        jTextField28.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N

        jLabel121.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        jLabel121.setText("Tipo de pesagem:");
        jLabel121.setToolTipText("");

        jComboBox41.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        jComboBox41.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel122.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        jLabel122.setText("Preço por Kg:");

        jTextField41.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N

        jLabel123.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        jLabel123.setText("Status:");

        jComboBox42.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        jComboBox42.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jRadioButton12.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        jRadioButton12.setText("Categorias em destaque:");

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel118, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addComponent(jLabel119, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox38, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField40, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(jButton85))
                    .addComponent(jScrollPane26, javax.swing.GroupLayout.PREFERRED_SIZE, 1166, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addComponent(jButton78)
                        .addGap(18, 18, 18)
                        .addComponent(jButton79)
                        .addGap(18, 18, 18)
                        .addComponent(jButton80)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton81)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton82)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel112)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jComboBox39, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel115, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel114, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel113, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField39, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
                            .addComponent(jTextField38, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE))
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel17Layout.createSequentialGroup()
                                .addGap(344, 344, 344)
                                .addComponent(jButton83)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton84))
                            .addGroup(jPanel17Layout.createSequentialGroup()
                                .addGap(49, 49, 49)
                                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel116, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
                                    .addComponent(jComboBox37, 0, 148, Short.MAX_VALUE)
                                    .addComponent(jLabel87, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jComboBox40, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel120, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jTextField28))
                                .addGap(84, 84, 84)
                                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel121, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                                    .addComponent(jComboBox41, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel122, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jTextField41)
                                    .addComponent(jLabel123, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jComboBox42, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel117, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(487, 487, 487))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel17Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jRadioButton12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel17Layout.createSequentialGroup()
                                .addComponent(jButton76)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton77)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel112)
                        .addGap(44, 44, 44)
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel113)
                            .addComponent(jLabel116)
                            .addComponent(jLabel121))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField38, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox37, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox41, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31)
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel114)
                            .addComponent(jLabel87)
                            .addComponent(jLabel122))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField39, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox40, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField41, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 35, 35)
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel115)
                            .addComponent(jLabel120)
                            .addComponent(jLabel123))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBox39, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox42, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(92, 92, 92))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                        .addComponent(jLabel117, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton76, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton77, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioButton12)
                        .addGap(78, 78, 78)))
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton78, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                    .addComponent(jButton79, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton80, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton83, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton84, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton82, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton81, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addComponent(jLabel118)
                .addGap(28, 28, 28)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel119)
                    .addComponent(jComboBox38, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField40, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton85, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane26, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(1066, Short.MAX_VALUE))
        );

        tbTelas.addTab("Produtos pesaveis", jPanel17);

        lbCodigoProdutoC.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        lbCodigoProdutoC.setText("Codigo do produto:");

        lbNomeProdutoC.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        lbNomeProdutoC.setText("Nome do produto:");

        lbCategoriasC.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        lbCategoriasC.setText("Categoria");

        cbCategoriasC.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        cbCategoriasC.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        lbMarcaC.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        lbMarcaC.setText("Marca");

        cbMarcaC.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        cbMarcaC.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        lbSubcategoriaC.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        lbSubcategoriaC.setText("Subcategoria:");

        cbSubcategoriaC.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        cbSubcategoriaC.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        lbUnidadeC.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        lbUnidadeC.setText("Unidade:");

        cbUnidadeC.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        cbUnidadeC.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        lbUnidadeMedidaC.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        lbUnidadeMedidaC.setText("Unidade de medida:");

        cbUnidadeMedidaC.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        cbUnidadeMedidaC.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        lbCodigoBarrasC.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        lbCodigoBarrasC.setText("Codigo de barras:");

        lbFaixaPrecoC.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        lbFaixaPrecoC.setText("Faixa de preço:");

        lbIconeFaixaPreco1C.setIcon(new javax.swing.ImageIcon("C:\\Users\\EnzoLeite\\Documents\\ProjetoPI_Supermercado\\GerenciamentoSupermercado\\src\\main\\resorce\\Assets\\bolsa-de-dinheiro.png")); // NOI18N

        lbIconeFaixaPreco2C.setIcon(new javax.swing.ImageIcon("C:\\Users\\EnzoLeite\\Documents\\ProjetoPI_Supermercado\\GerenciamentoSupermercado\\src\\main\\resorce\\Assets\\bolsa-de-dinheiro.png")); // NOI18N

        lbSituacaoProdutoC.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        lbSituacaoProdutoC.setText("Situação do produto:");

        cbSituacaoProdutoC.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        cbSituacaoProdutoC.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        lbTipoProdutoC.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        lbTipoProdutoC.setText("Tipo do produto:");

        rbTodosTipoProdutoC.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        rbTodosTipoProdutoC.setText("Todos");

        rbNormalTipoProdutoC.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        rbNormalTipoProdutoC.setText("Normal");

        rbPesavelTipoProdutoC.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        rbPesavelTipoProdutoC.setText("Pesavel");

        lbOutrasOpcoesC.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        lbOutrasOpcoesC.setText("Outras opções:");

        rbApenasImagenC.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        rbApenasImagenC.setText("Apenas com imagen");
        rbApenasImagenC.addActionListener(this::rbApenasImagenCActionPerformed);

        rbApenasProdutosAtivosC.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        rbApenasProdutosAtivosC.setText("Apenas produtos ativos");

        lbOrdernaPorC.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        lbOrdernaPorC.setText("Oderna por:");

        lbOrdemC.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        lbOrdemC.setText("ordem:");

        jbPesquisarC.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        jbPesquisarC.setIcon(new javax.swing.ImageIcon("C:\\Users\\EnzoLeite\\Documents\\ProjetoPI_Supermercado\\GerenciamentoSupermercado\\src\\main\\resorce\\Assets\\lupa.png")); // NOI18N
        jbPesquisarC.setText("Pesquisar");

        jbLimparC.setIcon(new javax.swing.ImageIcon("C:\\Users\\EnzoLeite\\Documents\\ProjetoPI_Supermercado\\GerenciamentoSupermercado\\src\\main\\resorce\\Assets\\vassoura.png")); // NOI18N
        jbLimparC.setText("Limpar");

        cbOrdernaPorC.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        cbOrdernaPorC.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbOrdemC.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        cbOrdemC.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        lbResultadoConsultaC.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        lbResultadoConsultaC.setText("Resultado da consulta:");

        tbResultadoConsultaC.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        tbResultadoConsultaC.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane7.setViewportView(tbResultadoConsultaC);

        lbConsultaProdutosC.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        lbConsultaProdutosC.setText("Consulta de produtos:");

        javax.swing.GroupLayout jpConsultaLayout = new javax.swing.GroupLayout(jpConsulta);
        jpConsulta.setLayout(jpConsultaLayout);
        jpConsultaLayout.setHorizontalGroup(
            jpConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpConsultaLayout.createSequentialGroup()
                .addGroup(jpConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpConsultaLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jpConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lbResultadoConsultaC, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 405, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane7, javax.swing.GroupLayout.Alignment.LEADING)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpConsultaLayout.createSequentialGroup()
                        .addGroup(jpConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpConsultaLayout.createSequentialGroup()
                                .addGap(0, 6, Short.MAX_VALUE)
                                .addGroup(jpConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jpConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(tfCodigoProdutoC)
                                        .addComponent(lbNomeProdutoC, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lbCodigoProdutoC, javax.swing.GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)
                                        .addComponent(lbCategoriasC, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(lbMarcaC, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(83, 83, 83))
                            .addGroup(jpConsultaLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jpConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tfNomeProdutoC, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbCategoriasC, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbMarcaC, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(jpConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbSubcategoriaC, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbSubcategoriaC, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbUnidadeC, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbUnidadeC, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbUnidadeMedidaC, javax.swing.GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)
                            .addComponent(cbUnidadeMedidaC, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbCodigoBarrasC, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tfCodigoBarrasC))
                        .addGroup(jpConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpConsultaLayout.createSequentialGroup()
                                .addGap(140, 140, 140)
                                .addGroup(jpConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jpConsultaLayout.createSequentialGroup()
                                        .addComponent(lbIconeFaixaPreco1C, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jpConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(jpConsultaLayout.createSequentialGroup()
                                                .addComponent(tfFaixaPreco1C, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(39, 39, 39)
                                                .addComponent(lbIconeFaixaPreco2C, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(2, 2, 2)
                                                .addComponent(tfFaixaPreco2C, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(lbFaixaPrecoC, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .addGroup(jpConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(cbSituacaoProdutoC, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lbSituacaoProdutoC, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE))
                                    .addComponent(lbTipoProdutoC, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jpConsultaLayout.createSequentialGroup()
                                        .addComponent(rbTodosTipoProdutoC, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(41, 41, 41)
                                        .addComponent(rbNormalTipoProdutoC, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(73, 73, 73)
                                .addGroup(jpConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(rbApenasProdutosAtivosC)
                                    .addComponent(lbOutrasOpcoesC, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(rbApenasImagenC)
                                    .addComponent(lbOrdernaPorC, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jpConsultaLayout.createSequentialGroup()
                                        .addGap(23, 23, 23)
                                        .addComponent(jbPesquisarC))
                                    .addComponent(cbOrdernaPorC, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(27, 27, 27)
                                .addGroup(jpConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lbOrdemC, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cbOrdemC, 0, 166, Short.MAX_VALUE)
                                    .addComponent(jbLimparC, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jpConsultaLayout.createSequentialGroup()
                                .addGap(205, 205, 205)
                                .addComponent(rbPesavelTipoProdutoC, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(139, 139, 139))
            .addGroup(jpConsultaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbConsultaProdutosC, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpConsultaLayout.setVerticalGroup(
            jpConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpConsultaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbConsultaProdutosC)
                .addGap(1, 1, 1)
                .addGroup(jpConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jpConsultaLayout.createSequentialGroup()
                        .addGroup(jpConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbSubcategoriaC)
                            .addComponent(lbFaixaPrecoC)
                            .addComponent(lbOutrasOpcoesC))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jpConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbIconeFaixaPreco2C, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jpConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cbSubcategoriaC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(tfFaixaPreco1C, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lbIconeFaixaPreco1C, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(tfFaixaPreco2C, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(rbApenasImagenC)))
                        .addGap(18, 18, 18))
                    .addGroup(jpConsultaLayout.createSequentialGroup()
                        .addComponent(lbCodigoProdutoC)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tfCodigoProdutoC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9)))
                .addGroup(jpConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbUnidadeC)
                        .addComponent(lbSituacaoProdutoC)
                        .addComponent(rbApenasProdutosAtivosC))
                    .addComponent(lbNomeProdutoC, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbUnidadeC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbSituacaoProdutoC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbOrdernaPorC)
                    .addComponent(lbOrdemC)
                    .addComponent(tfNomeProdutoC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jpConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbCategoriasC)
                    .addComponent(lbUnidadeMedidaC)
                    .addComponent(lbTipoProdutoC)
                    .addComponent(cbOrdernaPorC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbOrdemC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jpConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpConsultaLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbCategoriasC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbUnidadeMedidaC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)
                        .addGroup(jpConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpConsultaLayout.createSequentialGroup()
                                .addComponent(lbCodigoBarrasC)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jpConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jpConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jbPesquisarC, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jbLimparC, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(tfCodigoBarrasC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jpConsultaLayout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(lbMarcaC)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbMarcaC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jpConsultaLayout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(jpConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rbTodosTipoProdutoC)
                            .addComponent(rbNormalTipoProdutoC))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rbPesavelTipoProdutoC)))
                .addGap(32, 32, 32)
                .addComponent(lbResultadoConsultaC)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(1087, Short.MAX_VALUE))
        );

        tbTelas.addTab("Consulta", jpConsulta);

        lbDesativacaoProdutosD.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        lbDesativacaoProdutosD.setText("Desativação de produtos.");

        lbCodigoProdutoD.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        lbCodigoProdutoD.setText("Código do produto:");

        tfCodigoProdutoD.setFont(new java.awt.Font("Arial", 2, 12)); // NOI18N

        lbSubcategoriaD.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        lbSubcategoriaD.setText("Subcategoria:");

        tfSubcategoriaD.setFont(new java.awt.Font("Arial", 2, 12)); // NOI18N

        lbNomeProdutoD.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        lbNomeProdutoD.setText("Nome do produto:");

        tfNomeProdutoD.setFont(new java.awt.Font("Arial", 2, 12)); // NOI18N

        lbCategoriaD.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        lbCategoriaD.setText("Categoria:");

        cbMarcaD.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        cbMarcaD.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        lbStatusD.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        lbStatusD.setText("Status:");

        cbStatusD.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        cbStatusD.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        lbDataCadastroD.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        lbDataCadastroD.setText("Data de cadastro:");

        cbDataCadastro1D.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        cbDataCadastro1D.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        lbAteD.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        lbAteD.setText("Ate");

        cbDataCadastro2D.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        cbDataCadastro2D.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jTextField19.setText("jTextField19");

        jLabel57.setText("jLabel57");

        jbPesquisar1D.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        jbPesquisar1D.setIcon(new javax.swing.ImageIcon("C:\\Users\\EnzoLeite\\Documents\\ProjetoPI_Supermercado\\GerenciamentoSupermercado\\src\\main\\resorce\\Assets\\lupa.png")); // NOI18N
        jbPesquisar1D.setText("Pesquisar");

        jbLimparD.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        jbLimparD.setIcon(new javax.swing.ImageIcon("C:\\Users\\EnzoLeite\\Documents\\ProjetoPI_Supermercado\\GerenciamentoSupermercado\\src\\main\\resorce\\Assets\\vassoura.png")); // NOI18N
        jbLimparD.setText("limpar");

        tbDesativacaoProdutos.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        tbDesativacaoProdutos.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane10.setViewportView(tbDesativacaoProdutos);

        lbProdutosSeremDesativadosD.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        lbProdutosSeremDesativadosD.setText("Produtos a serem desativados:");

        jComboBox21.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        jComboBox21.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(lbProdutosSeremDesativadosD, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(52, Short.MAX_VALUE))
                    .addComponent(jComboBox21, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbProdutosSeremDesativadosD)
                .addGap(28, 28, 28)
                .addComponent(jComboBox21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(50, Short.MAX_VALUE))
        );

        jLabel59.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        jLabel59.setText("Motivo da desativação:");

        jTextArea2.setColumns(20);
        jTextArea2.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        jTextArea2.setRows(5);
        jScrollPane11.setViewportView(jTextArea2);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel59, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 12, Short.MAX_VALUE))
                    .addComponent(jScrollPane11))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel59)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        lbMarcaD.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        lbMarcaD.setText("Marca:");

        cbCategoriaD.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        cbCategoriaD.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        lbUnidadeD.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        lbUnidadeD.setText("Unidade:");

        cbUnidadeD.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        cbUnidadeD.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jbPesquisar1D, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbLimparD, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 1294, Short.MAX_VALUE))
                    .addComponent(jScrollPane10)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbDesativacaoProdutosD, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(tfSubcategoriaD, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(94, 94, 94)
                                .addComponent(cbMarcaD, 0, 149, Short.MAX_VALUE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(lbSubcategoriaD, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(tfCodigoProdutoD, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
                                    .addComponent(lbCodigoProdutoD, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(94, 94, 94)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lbNomeProdutoD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(tfNomeProdutoD, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
                                    .addComponent(lbMarcaD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(146, 146, 146)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbCategoriaD, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
                            .addComponent(cbCategoriaD, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbUnidadeD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbUnidadeD, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(151, 151, 151)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbDataCadastroD, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(cbDataCadastro1D, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(10, 10, 10)
                                        .addComponent(lbAteD, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(cbDataCadastro2D, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(170, 170, 170)
                                .addComponent(jLabel57)
                                .addGap(11, 11, 11)
                                .addComponent(jTextField19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lbStatusD, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbStatusD, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(292, 292, 292)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(lbDesativacaoProdutosD)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbCodigoProdutoD)
                    .addComponent(lbNomeProdutoD)
                    .addComponent(lbStatusD)
                    .addComponent(lbCategoriaD, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfCodigoProdutoD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfNomeProdutoD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbCategoriaD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbStatusD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(lbMarcaD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbUnidadeD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbSubcategoriaD))
                    .addComponent(lbDataCadastroD))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfSubcategoriaD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbMarcaD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbDataCadastro1D, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel57)
                    .addComponent(cbUnidadeD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbDataCadastro2D, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbAteD))
                .addGap(61, 61, 61)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbPesquisar1D, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbLimparD, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(1089, Short.MAX_VALUE))
        );

        tbTelas.addTab("Desativação", jPanel4);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tbTelas, javax.swing.GroupLayout.PREFERRED_SIZE, 1376, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tbTelas, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton84ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton84ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton84ActionPerformed

    private void jButton73ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton73ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton73ActionPerformed

    private void jButton63ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton63ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton63ActionPerformed

    private void jButton53ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton53ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton53ActionPerformed

    private void jButton43ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton43ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton43ActionPerformed

    private void cbSubcategoriaEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbSubcategoriaEActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbSubcategoriaEActionPerformed

    private void tfCodigoProdutoEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfCodigoProdutoEActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfCodigoProdutoEActionPerformed

    private void tfCategoriasCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfCategoriasCBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfCategoriasCBActionPerformed

    private void btSelecionarCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSelecionarCodigoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btSelecionarCodigoActionPerformed

    private void jbSalvarCadastrar5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSalvarCadastrar5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jbSalvarCadastrar5ActionPerformed

    private void cbTipoRelatorioERActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbTipoRelatorioERActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbTipoRelatorioERActionPerformed

    private void blIconePrtecoVendaCadastroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_blIconePrtecoVendaCadastroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_blIconePrtecoVendaCadastroActionPerformed

    private void tfNomeprodutoCadastroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfNomeprodutoCadastroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfNomeprodutoCadastroActionPerformed

    private void ftfCodigodoprodutoCadastroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ftfCodigodoprodutoCadastroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ftfCodigodoprodutoCadastroActionPerformed

    private void rbApenasImagenCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbApenasImagenCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rbApenasImagenCActionPerformed

    private void jbIconePesquisaCadastroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbIconePesquisaCadastroActionPerformed
 pesquisarProdutoSelecionado();
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jbIconePesquisaCadastroActionPerformed

    private void cbSelecionarCadastroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbSelecionarCadastroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbSelecionarCadastroActionPerformed

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
        java.awt.EventQueue.invokeLater(() -> new TelaGerenciamentoProdutos(usuario).setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bIconeValorCustoCadastro;
    private javax.swing.JButton blIconePrtecoVendaCadastro;
    private javax.swing.JButton btEsportarEXcelER;
    private javax.swing.JButton btExportarPDFER;
    private javax.swing.JButton btGerarER;
    private javax.swing.JToggleButton btImprimirEtiquetaER;
    private javax.swing.JToggleButton btLimparER;
    private javax.swing.JToggleButton btProduto2ER;
    private javax.swing.JToggleButton btRemover;
    private javax.swing.JToggleButton btSelecionarCodigo;
    private javax.swing.JButton btVisualizarER;
    private javax.swing.JToggleButton btVizualizarER;
    private javax.swing.JLabel cadastroCodigoBarras;
    private javax.swing.JComboBox<String> cbCategoriaCadastro;
    private javax.swing.JComboBox<String> cbCategoriaD;
    private javax.swing.JComboBox<String> cbCategoriaER;
    private javax.swing.JComboBox<String> cbCategoriasC;
    private javax.swing.JComboBox<String> cbCategoriasE;
    private javax.swing.JComboBox<String> cbDataCadastro1D;
    private javax.swing.JComboBox<String> cbDataCadastro2D;
    private javax.swing.JComboBox<String> cbImpressoraER;
    private javax.swing.JComboBox<String> cbMarcaC;
    private javax.swing.JComboBox<String> cbMarcaCadastro;
    private javax.swing.JComboBox<String> cbMarcaD;
    private javax.swing.JComboBox<String> cbMarcaE;
    private javax.swing.JComboBox<String> cbOrdemC;
    private javax.swing.JComboBox<String> cbOrdernaPorC;
    private javax.swing.JComboBox<String> cbPeriodo2ER;
    private javax.swing.JComboBox<String> cbPeriodoER;
    private javax.swing.JComboBox<String> cbPesquisarPor;
    private javax.swing.JComboBox<String> cbPesquisarPorE;
    private javax.swing.JComboBox<String> cbProdutoCB;
    private javax.swing.JComboBox<String> cbSelecionarCadastro;
    private javax.swing.JComboBox<String> cbSituacaoProdutoC;
    private javax.swing.JComboBox<String> cbStatusCb;
    private javax.swing.JComboBox<String> cbStatusD;
    private javax.swing.JComboBox<String> cbStatusE;
    private javax.swing.JComboBox<String> cbSubcategoriaC;
    private javax.swing.JComboBox<String> cbSubcategoriaE;
    private javax.swing.JComboBox<String> cbTipoCodigoCB;
    private javax.swing.JComboBox<String> cbTipoEtiquetaER;
    private javax.swing.JComboBox<String> cbTipoRelatorioER;
    private javax.swing.JComboBox<String> cbUnidadeC;
    private javax.swing.JComboBox<String> cbUnidadeD;
    private javax.swing.JComboBox<String> cbUnidadeMedidaC;
    private javax.swing.JComboBox<String> cbUnidadeMedidaCadastro;
    private javax.swing.JComboBox<String> cbUnidadeMedidaE;
    private javax.swing.JFormattedTextField ftfCodigodoprodutoCadastro;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton22;
    private javax.swing.JButton jButton23;
    private javax.swing.JButton jButton24;
    private javax.swing.JButton jButton25;
    private javax.swing.JButton jButton26;
    private javax.swing.JButton jButton27;
    private javax.swing.JButton jButton28;
    private javax.swing.JButton jButton29;
    private javax.swing.JButton jButton30;
    private javax.swing.JButton jButton31;
    private javax.swing.JButton jButton32;
    private javax.swing.JButton jButton33;
    private javax.swing.JButton jButton34;
    private javax.swing.JButton jButton35;
    private javax.swing.JButton jButton36;
    private javax.swing.JButton jButton37;
    private javax.swing.JButton jButton38;
    private javax.swing.JButton jButton39;
    private javax.swing.JButton jButton40;
    private javax.swing.JButton jButton41;
    private javax.swing.JButton jButton42;
    private javax.swing.JButton jButton43;
    private javax.swing.JButton jButton44;
    private javax.swing.JButton jButton45;
    private javax.swing.JButton jButton46;
    private javax.swing.JButton jButton47;
    private javax.swing.JButton jButton48;
    private javax.swing.JButton jButton49;
    private javax.swing.JButton jButton50;
    private javax.swing.JButton jButton51;
    private javax.swing.JButton jButton52;
    private javax.swing.JButton jButton53;
    private javax.swing.JButton jButton54;
    private javax.swing.JButton jButton55;
    private javax.swing.JButton jButton56;
    private javax.swing.JButton jButton57;
    private javax.swing.JButton jButton58;
    private javax.swing.JButton jButton59;
    private javax.swing.JButton jButton60;
    private javax.swing.JButton jButton61;
    private javax.swing.JButton jButton62;
    private javax.swing.JButton jButton63;
    private javax.swing.JButton jButton64;
    private javax.swing.JButton jButton65;
    private javax.swing.JButton jButton66;
    private javax.swing.JButton jButton67;
    private javax.swing.JButton jButton68;
    private javax.swing.JButton jButton69;
    private javax.swing.JButton jButton70;
    private javax.swing.JButton jButton71;
    private javax.swing.JButton jButton72;
    private javax.swing.JButton jButton73;
    private javax.swing.JButton jButton74;
    private javax.swing.JButton jButton75;
    private javax.swing.JButton jButton76;
    private javax.swing.JButton jButton77;
    private javax.swing.JButton jButton78;
    private javax.swing.JButton jButton79;
    private javax.swing.JButton jButton80;
    private javax.swing.JButton jButton81;
    private javax.swing.JButton jButton82;
    private javax.swing.JButton jButton83;
    private javax.swing.JButton jButton84;
    private javax.swing.JButton jButton85;
    private javax.swing.JComboBox<String> jComboBox14;
    private javax.swing.JComboBox<String> jComboBox21;
    private javax.swing.JComboBox<String> jComboBox22;
    private javax.swing.JComboBox<String> jComboBox25;
    private javax.swing.JComboBox<String> jComboBox26;
    private javax.swing.JComboBox<String> jComboBox27;
    private javax.swing.JComboBox<String> jComboBox28;
    private javax.swing.JComboBox<String> jComboBox29;
    private javax.swing.JComboBox<String> jComboBox30;
    private javax.swing.JComboBox<String> jComboBox31;
    private javax.swing.JComboBox<String> jComboBox32;
    private javax.swing.JComboBox<String> jComboBox33;
    private javax.swing.JComboBox<String> jComboBox34;
    private javax.swing.JComboBox<String> jComboBox35;
    private javax.swing.JComboBox<String> jComboBox36;
    private javax.swing.JComboBox<String> jComboBox37;
    private javax.swing.JComboBox<String> jComboBox38;
    private javax.swing.JComboBox<String> jComboBox39;
    private javax.swing.JComboBox<String> jComboBox40;
    private javax.swing.JComboBox<String> jComboBox41;
    private javax.swing.JComboBox<String> jComboBox42;
    private javax.swing.JLabel jLabel100;
    private javax.swing.JLabel jLabel101;
    private javax.swing.JLabel jLabel102;
    private javax.swing.JLabel jLabel103;
    private javax.swing.JLabel jLabel104;
    private javax.swing.JLabel jLabel105;
    private javax.swing.JLabel jLabel106;
    private javax.swing.JLabel jLabel107;
    private javax.swing.JLabel jLabel108;
    private javax.swing.JLabel jLabel109;
    private javax.swing.JLabel jLabel110;
    private javax.swing.JLabel jLabel111;
    private javax.swing.JLabel jLabel112;
    private javax.swing.JLabel jLabel113;
    private javax.swing.JLabel jLabel114;
    private javax.swing.JLabel jLabel115;
    private javax.swing.JLabel jLabel116;
    private javax.swing.JLabel jLabel117;
    private javax.swing.JLabel jLabel118;
    private javax.swing.JLabel jLabel119;
    private javax.swing.JLabel jLabel120;
    private javax.swing.JLabel jLabel121;
    private javax.swing.JLabel jLabel122;
    private javax.swing.JLabel jLabel123;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JLabel jLabel94;
    private javax.swing.JLabel jLabel95;
    private javax.swing.JLabel jLabel96;
    private javax.swing.JLabel jLabel97;
    private javax.swing.JLabel jLabel98;
    private javax.swing.JLabel jLabel99;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JRadioButton jRadioButton10;
    private javax.swing.JRadioButton jRadioButton11;
    private javax.swing.JRadioButton jRadioButton12;
    private javax.swing.JRadioButton jRadioButton7;
    private javax.swing.JRadioButton jRadioButton8;
    private javax.swing.JRadioButton jRadioButton9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
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
    private javax.swing.JScrollPane jScrollPane22;
    private javax.swing.JScrollPane jScrollPane23;
    private javax.swing.JScrollPane jScrollPane24;
    private javax.swing.JScrollPane jScrollPane26;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTable jTable10;
    private javax.swing.JTable jTable11;
    private javax.swing.JTable jTable12;
    private javax.swing.JTable jTable4;
    private javax.swing.JTable jTable6;
    private javax.swing.JTable jTable7;
    private javax.swing.JTable jTable8;
    private javax.swing.JTable jTable9;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextArea jTextArea3;
    private javax.swing.JTextArea jTextArea4;
    private javax.swing.JTextArea jTextArea5;
    private javax.swing.JTextArea jTextArea6;
    private javax.swing.JTextArea jTextArea7;
    private javax.swing.JTextArea jTextArea8;
    private javax.swing.JTextArea jTextArea9;
    private javax.swing.JTextField jTextField18;
    private javax.swing.JTextField jTextField19;
    private javax.swing.JTextField jTextField20;
    private javax.swing.JTextField jTextField21;
    private javax.swing.JTextField jTextField22;
    private javax.swing.JTextField jTextField23;
    private javax.swing.JTextField jTextField24;
    private javax.swing.JTextField jTextField25;
    private javax.swing.JTextField jTextField26;
    private javax.swing.JTextField jTextField27;
    private javax.swing.JTextField jTextField28;
    private javax.swing.JTextField jTextField29;
    private javax.swing.JTextField jTextField30;
    private javax.swing.JTextField jTextField31;
    private javax.swing.JTextField jTextField32;
    private javax.swing.JTextField jTextField33;
    private javax.swing.JTextField jTextField34;
    private javax.swing.JTextField jTextField35;
    private javax.swing.JTextField jTextField36;
    private javax.swing.JTextField jTextField37;
    private javax.swing.JTextField jTextField38;
    private javax.swing.JTextField jTextField39;
    private javax.swing.JTextField jTextField40;
    private javax.swing.JTextField jTextField41;
    private javax.swing.JButton jbAdicionarCB;
    private javax.swing.JLabel jbCodigoBarras2E;
    private javax.swing.JButton jbEditarCB;
    private javax.swing.JButton jbEditarCadastra;
    private javax.swing.JButton jbExcluirCB;
    private javax.swing.JButton jbExcluirCadastrar;
    private javax.swing.JButton jbExcluirE;
    private javax.swing.JButton jbGerarE;
    private javax.swing.JButton jbIconePesquisaCB;
    private javax.swing.JButton jbIconePesquisaCadastro;
    private javax.swing.JButton jbLimparC;
    private javax.swing.JButton jbLimparCB;
    private javax.swing.JButton jbLimparCadastrar;
    private javax.swing.JButton jbLimparD;
    private javax.swing.JButton jbLimparE;
    private javax.swing.JButton jbNovoE;
    private javax.swing.JButton jbPesquisar1D;
    private javax.swing.JButton jbPesquisar2E;
    private javax.swing.JButton jbPesquisarC;
    private javax.swing.JButton jbPesquisarCB;
    private javax.swing.JButton jbPeswquisarE;
    private javax.swing.JButton jbRemoverE;
    private javax.swing.JButton jbSalvarCB;
    private javax.swing.JButton jbSalvarCadastrar;
    private javax.swing.JButton jbSalvarCadastrar5;
    private javax.swing.JButton jbSalvarE;
    private javax.swing.JButton jbSelecionarImagenE;
    private javax.swing.JButton jbVoltarE;
    private javax.swing.JPanel jpCadastro;
    private javax.swing.JPanel jpCodigoBarrasC;
    private javax.swing.JPanel jpConsulta;
    private javax.swing.JPanel jpEdicao;
    private javax.swing.JPanel jpEtiquetasRelatorios;
    private javax.swing.JPanel jpImagemCodigo;
    private javax.swing.JPanel jpParteCimaCadastro;
    private javax.swing.JLabel lbAteD;
    private javax.swing.JLabel lbAteER;
    private javax.swing.JLabel lbCategoriaCadastro;
    private javax.swing.JLabel lbCategoriaD;
    private javax.swing.JLabel lbCategoriaER;
    private javax.swing.JLabel lbCategoriasC;
    private javax.swing.JLabel lbCategoriasCB;
    private javax.swing.JLabel lbCategoriasE;
    private javax.swing.JLabel lbClienteCadastradosET;
    private javax.swing.JLabel lbCodigoBarrasC;
    private javax.swing.JLabel lbCodigoBarrasCB;
    private javax.swing.JLabel lbCodigoBarrasE;
    private javax.swing.JLabel lbCodigoProduto;
    private javax.swing.JLabel lbCodigoProdutoC;
    private javax.swing.JLabel lbCodigoProdutoD;
    private javax.swing.JLabel lbCodigoProdutoE;
    private javax.swing.JLabel lbConsultaProdutosC;
    private javax.swing.JLabel lbCotigoBarrasCadastro;
    private javax.swing.JLabel lbDadosProdutosCadastro;
    private javax.swing.JLabel lbDataCadastroD;
    private javax.swing.JLabel lbDesativacaoProdutosD;
    private javax.swing.JLabel lbDescricaoCB;
    private javax.swing.JLabel lbDescricaoCadastro;
    private javax.swing.JLabel lbDescricaoE;
    private javax.swing.JLabel lbEditarProdutoE;
    private javax.swing.JLabel lbEstoqueAtualE;
    private javax.swing.JLabel lbEstoqueAtualET;
    private javax.swing.JLabel lbEstoqueMinimoE;
    private javax.swing.JLabel lbFaixaPrecoC;
    private javax.swing.JLabel lbIconeFaixaPreco1C;
    private javax.swing.JLabel lbIconeFaixaPreco2C;
    private javax.swing.JLabel lbIconePrecoCustoE;
    private javax.swing.JLabel lbIconePrecoVendaE;
    private javax.swing.JLabel lbImagemCodigoCB;
    private javax.swing.JLabel lbImagenPreviwEtiquetaER;
    private javax.swing.JLabel lbImagenProdutoE;
    private javax.swing.JLabel lbImagenProdutoIconeE;
    private javax.swing.JLabel lbImpresaoEtiquetaER;
    private javax.swing.JLabel lbImpressoraER;
    private javax.swing.JLabel lbListaCodigoBarrasCB;
    private javax.swing.JLabel lbListaProdutosCadastrar;
    private javax.swing.JLabel lbListaProdutosE;
    private javax.swing.JLabel lbMarcaC;
    private javax.swing.JLabel lbMarcaCadastro;
    private javax.swing.JLabel lbMarcaD;
    private javax.swing.JLabel lbMarcaE;
    private javax.swing.JLabel lbMarcasCB;
    private javax.swing.JLabel lbMovimentacaoEstoqueET;
    private javax.swing.JLabel lbNomeProdutoC;
    private javax.swing.JLabel lbNomeProdutoD;
    private javax.swing.JLabel lbNomeProdutoE;
    private javax.swing.JLabel lbNomeprodutoCadastro;
    private javax.swing.JLabel lbOrdemC;
    private javax.swing.JLabel lbOrdernaPorC;
    private javax.swing.JLabel lbOutrasOpcoesC;
    private javax.swing.JLabel lbPeriodoER;
    private javax.swing.JLabel lbPesquisarPorCB;
    private javax.swing.JLabel lbPesquisarPorCadastro;
    private javax.swing.JLabel lbPesquisarPorE;
    private javax.swing.JLabel lbPrecoVendaCadastro;
    private javax.swing.JLabel lbPrecoVendaE;
    private javax.swing.JLabel lbPrecosCustoE;
    private javax.swing.JLabel lbPreviwEtiquetaER;
    private javax.swing.JLabel lbProduto2ER;
    private javax.swing.JLabel lbProdutoConsultaCB;
    private javax.swing.JLabel lbProdutosComEstoqueBaixoET;
    private javax.swing.JLabel lbProdutosMaisVendidosET;
    private javax.swing.JLabel lbProdutosPorCategoriaET;
    private javax.swing.JLabel lbProdutosSeremDesativadosD;
    private javax.swing.JLabel lbQuantidadeEstoqueCadastro;
    private javax.swing.JLabel lbRelatorioER;
    private javax.swing.JLabel lbRelatoriosDisponiveisER;
    private javax.swing.JLabel lbResultadoConsultaC;
    private javax.swing.JLabel lbSituacaoProdutoC;
    private javax.swing.JLabel lbSituacaoProdutoCadastro;
    private javax.swing.JLabel lbStatusCB;
    private javax.swing.JLabel lbStatusD;
    private javax.swing.JLabel lbStatusE;
    private javax.swing.JLabel lbSubcategoriaC;
    private javax.swing.JLabel lbSubcategoriaD;
    private javax.swing.JLabel lbSubcategoriaE;
    private javax.swing.JLabel lbTipoCodigoCB;
    private javax.swing.JLabel lbTipoEtiquetaER;
    private javax.swing.JLabel lbTipoProdutoC;
    private javax.swing.JLabel lbTipoRelatorioER;
    private javax.swing.JLabel lbTotalCodigiBarrasCadastradoCB;
    private javax.swing.JLabel lbUnidadeC;
    private javax.swing.JLabel lbUnidadeD;
    private javax.swing.JComboBox<String> lbUnidadeE;
    private javax.swing.JLabel lbUnidadeMedidaC;
    private javax.swing.JLabel lbUnidadeMedidaCB;
    private javax.swing.JLabel lbUnidadeMedidaCadastro;
    private javax.swing.JLabel lbUnidadeMedidaE;
    private javax.swing.JLabel lbValidadeCadastrar;
    private javax.swing.JLabel lbValorCustoCadastro;
    private javax.swing.JLabel lbVendasPorPeriodoER;
    private javax.swing.JRadioButton rbApenasImagenC;
    private javax.swing.JRadioButton rbApenasProdutosAtivosC;
    private javax.swing.JRadioButton rbAtivoCadastro;
    private javax.swing.JRadioButton rbDesligadoCadastro;
    private javax.swing.JRadioButton rbNormalTipoProdutoC;
    private javax.swing.JRadioButton rbPesavelTipoProdutoC;
    private javax.swing.JRadioButton rbProdutoPesavelE;
    private javax.swing.JRadioButton rbTodosTipoProdutoC;
    private javax.swing.JTextArea taDescricaoCB;
    private javax.swing.JScrollPane taDescricaoCadastro;
    private javax.swing.JTextArea taDescricaoCadastroCD;
    private javax.swing.JTextArea taDescricaoE;
    private javax.swing.JTable tbDesativacaoProdutos;
    private javax.swing.JTable tbListaProdutosC;
    private javax.swing.JTable tbListaProdutosE;
    private javax.swing.JTable tbListasCodigoBarrasCB;
    private javax.swing.JTable tbResultadoConsultaC;
    private javax.swing.JTabbedPane tbTelas;
    private javax.swing.JTextField tfCategoriasCB;
    private javax.swing.JTextField tfCodigoBarras2E;
    private javax.swing.JTextField tfCodigoBarrasC;
    private javax.swing.JTextField tfCodigoBarrasCB;
    private javax.swing.JTextField tfCodigoBarrasCadastro;
    private javax.swing.JTextField tfCodigoBarrasE;
    private javax.swing.JTextField tfCodigoProdutoC;
    private javax.swing.JTextField tfCodigoProdutoD;
    private javax.swing.JTextField tfCodigoProdutoE;
    private javax.swing.JTextField tfEstoqueAtualE;
    private javax.swing.JTextField tfEstoqueMinimoE;
    private javax.swing.JTextField tfFaixaPreco1C;
    private javax.swing.JTextField tfFaixaPreco2C;
    private javax.swing.JTextField tfIconePrecoVendaCadastro;
    private javax.swing.JTextField tfIconeValorCustoCadastro;
    private javax.swing.JTextField tfMarcasCB;
    private javax.swing.JTextField tfNomeProdutoC;
    private javax.swing.JTextField tfNomeProdutoD;
    private javax.swing.JTextField tfNomeProdutoE;
    private javax.swing.JTextField tfNomeprodutoCadastro;
    private javax.swing.JTextField tfPesquisarPor;
    private javax.swing.JTextField tfPesquisarPorE;
    private javax.swing.JTextField tfPrecoCustoE;
    private javax.swing.JTextField tfPrecoVendaE;
    private javax.swing.JFormattedTextField tfProduto2ER;
    private javax.swing.JTextField tfQuantidadeEstoqueCadastro;
    private javax.swing.JTextField tfSubcategoriaD;
    private javax.swing.JTextField tfValidadeCadastrar;
    // End of variables declaration//GEN-END:variables

    
}
