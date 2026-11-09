/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import Conection.Conexao;
import Control.AberturaCaixaController;
import Control.CaixasController;
import Model.Caixas;
import Model.Usuario;
import View.AlterarSenha;
import View.MenuPadrao;
import View.RecuperacaoSenha;
import View.TelaEntregas;
import View.TelaEstoque;
import View.TelaHome;
import View.TelaLoginUsuarios;
import View.TelaPdvRealizacao;
import View.TelaPedidos;
import View.TelaPerfilUsuarios;
import View.TelaProdutos;
import View.TelaSolicitacao;
import View.TelaVendas;
import View.Tela_Funcionarios_RH;
import View.TelasClientes;
import control.MovimentacoesFinanceirasController;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author PedroLeite
 */
public class TelaCaixa extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(TelaCaixa.class.getName());

     private MenuPadrao menu;  
     private Usuario usuario;
     
     
    public TelaCaixa(Usuario usuario) {
        initComponents();
        carregarComboBoxes();
        this.usuario= usuario;
       this.menu=MenuPadrao.instalar(
        this,
        usuario,
        "entregas",
        this::navegar,
        this::dispose
    );
          this.setExtendedState(TelaCaixa.MAXIMIZED_BOTH);
       
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

// ------------------------------------------------------------------------------------------------------------------------------------------- 

// LISTAR CAIXAS NAS COMBOBOX
public void listarCaixasCombo(JComboBox<String> combo) {

    combo.removeAllItems();

    String sql = "SELECT idcaixas, nome FROM caixas ORDER BY nome";

    try (Connection con = Conexao.conectar();
         PreparedStatement stmt = con.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            combo.addItem(
                rs.getInt("idcaixas") + " - " + rs.getString("nome")
            );
        }

    } catch (SQLException e) {
        System.out.println("Erro ao listar caixas: " + e.getMessage());
    }
}

// LISTAR TIPOS DE CAIXA
public void listarTiposCaixa(JComboBox<String> combo) {

    combo.removeAllItems();

    String sql = "SELECT DISTINCT tipo FROM caixas ORDER BY tipo";

    try (Connection con = Conexao.conectar();
         PreparedStatement stmt = con.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            combo.addItem(rs.getString("tipo"));
        }

    } catch (SQLException e) {
        System.out.println("Erro ao listar tipos: " + e.getMessage());
    }
}

// LISTAR OPERADORES DE CAIXA
public void listarOperadores(JComboBox<String> combo) {

    combo.removeAllItems();

    String sql = "SELECT f.idFuncionario, f.nome "
               + "FROM Funcionario f "
               + "INNER JOIN cargos c ON f.cargos_idcargos = c.idcargos "
               + "WHERE f.statusFuncionario = 1 "
               + "AND c.nome = 'Operador de Caixa' "
               + "ORDER BY f.nome";

    try (Connection con = Conexao.conectar();
         PreparedStatement stmt = con.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            combo.addItem(
                rs.getInt("idFuncionario") + " - "
                + rs.getString("nome")
            );
        }

    } catch (SQLException e) {
        System.out.println("Erro ao listar operadores: " + e.getMessage());
    }
}

// LISTAR TODOS OS FUNCIONÁRIOS
public void listarFuncionarios(JComboBox<String> combo) {

    combo.removeAllItems();

    String sql = "SELECT idFuncionario, nome FROM Funcionario "
               + "WHERE statusFuncionario = 1 ORDER BY nome";

    try (Connection con = Conexao.conectar();
         PreparedStatement stmt = con.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            combo.addItem(
                rs.getInt("idFuncionario") + " - "
                + rs.getString("nome")
            );
        }

    } catch (SQLException e) {
        System.out.println("Erro ao listar funcionários: " + e.getMessage());
    }
}

// LISTAR TURNOS
public void listarTurnos(JComboBox<String> combo) {

    combo.removeAllItems();

    String sql = "SELECT idturnos, nome FROM turnos "
               + "WHERE status = 1 ORDER BY nome";

    try (Connection con = Conexao.conectar();
         PreparedStatement stmt = con.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            combo.addItem(
                rs.getInt("idturnos") + " - "
                + rs.getString("nome")
            );
        }

    } catch (SQLException e) {
        System.out.println("Erro ao listar turnos: " + e.getMessage());
    }
}

// LISTAR USUÁRIOS PARA AUTORIZAÇÃO
public void listarUsuarios(JComboBox<String> combo) {

    combo.removeAllItems();

    String sql = "SELECT idusuario, nome FROM usuario ORDER BY nome";

    try (Connection con = Conexao.conectar();
         PreparedStatement stmt = con.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            combo.addItem(
                rs.getInt("idusuario") + " - "
                + rs.getString("nome")
            );
        }

    } catch (SQLException e) {
        System.out.println("Erro ao listar usuários: " + e.getMessage());
    }
}

// LISTAR STATUS DO CAIXA
public void listarStatusCaixa(JComboBox<String> combo) {

    combo.removeAllItems();

    combo.addItem("1 - Ativo");
    combo.addItem("0 - Inativo");
}

// LISTAR TIPOS DE MOVIMENTAÇÃO
public void listarMovimentacoes(JComboBox<String> combo) {

    combo.removeAllItems();

    combo.addItem("Abertura");
    combo.addItem("Fechamento");
    combo.addItem("Sangria");
    combo.addItem("Suprimento");
    combo.addItem("Conferência");
}

private void carregarComboBoxes() {

   

    listarCaixasCombo(cbxCAIXA);
   listarCaixasCombo(cbxCAIXAS);
    listarCaixasCombo(cbxCaixa);
   listarCaixasCombo(cbxCaixas);

    // Tipo de caixa
   listarTiposCaixa(cbxTipodeCaixa);

    // Operadores
   listarOperadores(cbxOPERADOR);
    listarOperadores(cbxOpedaror);
    listarOperadores(cbxOperador);
    listarOperadores(cbxOperadorCadrastodecaixa);
    listarOperadores(cbxoperador);

    // Turnos
    listarTurnos(cbxTurnos);

    // Usuários para autorização
    listarUsuarios(cbxUautorizacao);

    // Status
    listarStatusCaixa(cbxStatusdeCaixa);

    // Movimentações
    listarMovimentacoes(cbxMovimentacoes);
}

private int pegarIdCombo(JComboBox<String> combo) {

    String selecionado = (String) combo.getSelectedItem();

    if (selecionado == null || selecionado.trim().isEmpty()) {
        return -1;
    }

    return Integer.parseInt(selecionado.split(" - ")[0]);
}
// --------------------------------------------------------------------------------------



private void cadastrarAberturaCaixa() {

    try {
        AberturaCaixa abertura = new AberturaCaixa();

        abertura.setDataAbertura(ffDataInicial.getText().trim());

        abertura.setHorarioAbertura(
            ffHoradeAbertura.getText().trim()
        );

        abertura.setValorInicial(
            Double.parseDouble(
                txfValorInicial.getText().replace(",", ".")
            )
        );

        abertura.setCaixas_idcaixas(
            pegarIdCombo(cbxCaixa)
        );

        abertura.setFuncionario_idFuncionario(
            pegarIdCombo(cbxOperador)
        );

        AberturaCaixaController controller =
            new AberturaCaixaController();

        if (controller.cadastrar(abertura)) {
            javax.swing.JOptionPane.showMessageDialog(
                this,
                "Abertura de caixa cadastrada com sucesso!"
            );
        } else {
            javax.swing.JOptionPane.showMessageDialog(
                this,
                "Não foi possível cadastrar a abertura."
            );
        }

    } catch (NumberFormatException e) {
        javax.swing.JOptionPane.showMessageDialog(
            this,
            "Informe um valor inicial válido."
        );
    }
}















    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        buttonGroup4 = new javax.swing.ButtonGroup();
        jPanel7 = new javax.swing.JPanel();
        lbDdeCaixa = new javax.swing.JLabel();
        lbCodigoCadrastodeCaixa = new javax.swing.JLabel();
        lbPontdeACadrastodeC = new javax.swing.JLabel();
        lbSCadrastodeCaixa = new javax.swing.JLabel();
        lbOCadrastodeCaixa = new javax.swing.JLabel();
        rbAtivoCadrastodeCaixa = new javax.swing.JRadioButton();
        rbInativoCadrastodeCaixa = new javax.swing.JRadioButton();
        cbxOperadorCadrastodecaixa = new javax.swing.JComboBox<>();
        cbxPontosdeACadrastodeCaixa = new javax.swing.JComboBox<>();
        txfCcadrastoDeCaixa = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jFormattedTextField1 = new javax.swing.JFormattedTextField();
        jFormattedTextField2 = new javax.swing.JFormattedTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jFormattedTextField3 = new javax.swing.JFormattedTextField();
        jFormattedTextField4 = new javax.swing.JFormattedTextField();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextArea3 = new javax.swing.JTextArea();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        pnlMovimentacoesTelas = new javax.swing.JPanel();
        pnlMovimentacoes = new javax.swing.JPanel();
        lbDataInicialMovimentacoes = new javax.swing.JLabel();
        ffDataInicial = new javax.swing.JFormattedTextField();
        lbOperadorMovimentacoes = new javax.swing.JLabel();
        cbxOpedaror = new javax.swing.JComboBox<>();
        btncalendarioDataInicial = new javax.swing.JButton();
        lbDATAFinal = new javax.swing.JLabel();
        ffDataFinal = new javax.swing.JFormattedTextField();
        lbValorRS = new javax.swing.JLabel();
        txfValorRS = new javax.swing.JTextField();
        btnCalendarioDataFinal = new javax.swing.JButton();
        lbCaixaMovimentacoes = new javax.swing.JLabel();
        cbxCaixas = new javax.swing.JComboBox<>();
        lbMovimentacoes = new javax.swing.JLabel();
        cbxMovimentacoes = new javax.swing.JComboBox<>();
        btnPesquisa = new javax.swing.JButton();
        btnImprimi = new javax.swing.JButton();
        btnExportarMovimentacoes = new javax.swing.JButton();
        btnVizualiza = new javax.swing.JButton();
        btnVoltarMovimentacoes = new javax.swing.JButton();
        pnlRegistroMovimentacoes = new javax.swing.JPanel();
        tbResgistrosdeMovimentacoes = new javax.swing.JScrollPane();
        tbMovimentacoes = new javax.swing.JTable();
        lbResgistrodeMovimentos = new javax.swing.JLabel();
        pnlHistoricoTelas = new javax.swing.JPanel();
        pnlHistoricodeCaixa = new javax.swing.JPanel();
        lbHistoricodeCaixa = new javax.swing.JLabel();
        btnRelogio = new javax.swing.JButton();
        lbDataInicialHistorico = new javax.swing.JLabel();
        txfDataInicial = new javax.swing.JTextField();
        btnDataInicial = new javax.swing.JButton();
        lbDataFinal = new javax.swing.JLabel();
        txfDataFinal = new javax.swing.JTextField();
        btnDataFinal = new javax.swing.JButton();
        lbcaixa = new javax.swing.JLabel();
        cbxCAIXA = new javax.swing.JComboBox<>();
        lboperador = new javax.swing.JLabel();
        cbxoperador = new javax.swing.JComboBox<>();
        lbPesquisar = new javax.swing.JLabel();
        txfPesquisar = new javax.swing.JTextField();
        btnpesquisar = new javax.swing.JButton();
        lbTipodeOperacao = new javax.swing.JLabel();
        rbTodos = new javax.swing.JRadioButton();
        rbAbertura = new javax.swing.JRadioButton();
        rbFechamento = new javax.swing.JRadioButton();
        rbMovimentacoes = new javax.swing.JRadioButton();
        btnImprimir = new javax.swing.JButton();
        btnExporta = new javax.swing.JButton();
        btnVizualizar = new javax.swing.JButton();
        btnVoltar = new javax.swing.JButton();
        pnlRegistrodeHistorico = new javax.swing.JPanel();
        lbResgistrodeHistoricocaixa = new javax.swing.JLabel();
        tbResgistrosdeCaixa = new javax.swing.JScrollPane();
        tbResgistrosdoHistoricodeCaixa = new javax.swing.JTable();
        pnlDiferencadeCaixasTelas = new java.awt.Panel();
        pnlDiferenciadeCaixa = new javax.swing.JPanel();
        lbCaixa = new javax.swing.JLabel();
        cbxCaixa = new javax.swing.JComboBox<>();
        lbDadeCaixa = new javax.swing.JLabel();
        ffDatadeCaixa = new javax.swing.JFormattedTextField();
        btnCalendario = new javax.swing.JButton();
        btnOperador = new javax.swing.JLabel();
        cbxOperador = new javax.swing.JComboBox<>();
        jLabel28 = new javax.swing.JLabel();
        txfValorEsperando = new javax.swing.JTextField();
        lbValorcantado = new javax.swing.JLabel();
        txfValorContado = new javax.swing.JTextField();
        lbDiferenca = new javax.swing.JLabel();
        txfDiferenca = new javax.swing.JTextField();
        lbTipodeDiferenca = new javax.swing.JLabel();
        rbFalsa = new javax.swing.JRadioButton();
        rbSobra = new javax.swing.JRadioButton();
        lbObservacoes = new javax.swing.JLabel();
        jScrollPane12 = new javax.swing.JScrollPane();
        txaObservacoesDiferenciadeCaixa = new javax.swing.JTextArea();
        lbDiferencadeCaixa = new javax.swing.JLabel();
        btnNovo = new javax.swing.JButton();
        btnSalvar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnLimpar = new javax.swing.JButton();
        btnPesquisar = new javax.swing.JButton();
        pnlRegistrodeCaixa = new javax.swing.JPanel();
        lbRegistrodeCaixa = new javax.swing.JLabel();
        jScrollPane13 = new javax.swing.JScrollPane();
        tbResgistrodeCaixa = new javax.swing.JTable();
        pnlPontosdeAtendimentoTelas = new java.awt.Panel();
        pnlDadosdeAtendimento = new javax.swing.JPanel();
        lbDadosdeAtendimento = new javax.swing.JLabel();
        lbcodigo = new javax.swing.JLabel();
        lbDescricaodePontosdeAtendimento = new javax.swing.JLabel();
        lbTipoPontosdeAtendimento = new javax.swing.JLabel();
        txfCadigoPontosdeAtendimento = new javax.swing.JTextField();
        txfDescricaoPontosdeAtendimento = new javax.swing.JTextField();
        cbxTipoPontosdeAtendimento = new javax.swing.JComboBox<>();
        lbendereco = new javax.swing.JLabel();
        lbTELEFONE = new javax.swing.JLabel();
        lbstatus = new javax.swing.JLabel();
        tfENDERECO = new javax.swing.JTextField();
        tfTELEFONE = new javax.swing.JTextField();
        btnATIVO = new javax.swing.JRadioButton();
        btnINATIVO = new javax.swing.JRadioButton();
        lbObservacoePontosdeAtendimento = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txaObservacoesPontosdeAtendimento = new javax.swing.JTextArea();
        btnNOVO = new javax.swing.JButton();
        btnSALVAR = new javax.swing.JButton();
        btnEDITAR = new javax.swing.JButton();
        btnEXCLUIR = new javax.swing.JButton();
        btnLIMPAR = new javax.swing.JButton();
        btnPESQUISAR = new javax.swing.JButton();
        btnVOLTAR = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        tbPntosdeAtedimento = new javax.swing.JTable();
        pnlSuprimentosTelas = new java.awt.Panel();
        pnlEntradasdeNumeros = new javax.swing.JPanel();
        lbEntradasdeNumerarios = new javax.swing.JLabel();
        lbDatadeSuprimentos = new javax.swing.JLabel();
        ffDataeHORAS = new javax.swing.JFormattedTextField();
        lbCaixaDestino = new javax.swing.JLabel();
        cbxCaixaDestino = new javax.swing.JComboBox<>();
        lbMotivodoSuprimento = new javax.swing.JLabel();
        cbxMotivodoSuprimento = new javax.swing.JComboBox<>();
        lbValoraSuprir = new javax.swing.JLabel();
        lbObservacoesTX = new javax.swing.JLabel();
        jScrollPane25 = new javax.swing.JScrollPane();
        txaObservacoesNumerarios = new javax.swing.JTextArea();
        txfValorASuprir = new javax.swing.JTextField();
        pnlAutorizacao = new javax.swing.JPanel();
        lbAutorizacao = new javax.swing.JLabel();
        lbUsuarioAutorizador = new javax.swing.JLabel();
        cbxUsuarioAutorizado = new javax.swing.JComboBox<>();
        lbSenhadaAutorizacao = new javax.swing.JLabel();
        txfSenhadeAutorizacao = new javax.swing.JTextField();
        lbDeHdaAutorizacao = new javax.swing.JLabel();
        ffDeHdaAutorizacao = new javax.swing.JFormattedTextField();
        btnDeHdeAutorizacao = new javax.swing.JButton();
        pnlConferencia = new javax.swing.JPanel();
        lbConferencia = new javax.swing.JLabel();
        jScrollPane26 = new javax.swing.JScrollPane();
        tbCedulasConferencia = new javax.swing.JTable();
        jScrollPane27 = new javax.swing.JScrollPane();
        tbMoedasConferencia = new javax.swing.JTable();
        lbTotaldeCedula = new javax.swing.JLabel();
        lbTotaldeMoedas = new javax.swing.JLabel();
        lbValordeCedula = new javax.swing.JLabel();
        lbValordeMoedas = new javax.swing.JLabel();
        btnMoedasdeConferencia = new javax.swing.JButton();
        btnCedulas = new javax.swing.JButton();
        pnlAtualizacaodeSaldo = new javax.swing.JPanel();
        lbAtualizacaodeSaldo = new javax.swing.JLabel();
        lbSaldoAnteriordoCaixa = new javax.swing.JLabel();
        ffSaldoAtualizado = new javax.swing.JFormattedTextField();
        lbValorAtualizado = new javax.swing.JLabel();
        ffValordoSuprimento = new javax.swing.JFormattedTextField();
        lbNovoSaldodoCaixa = new javax.swing.JLabel();
        lbDataeHoraAtualizacao = new javax.swing.JLabel();
        lbUsuarioAtualizacao = new javax.swing.JLabel();
        pnlComprovanteSuprimentos = new javax.swing.JPanel();
        lbComprovanteAtualizado = new javax.swing.JLabel();
        btnComprovanteAtualizacao = new javax.swing.JButton();
        pnlConfirmacaodoComprovante = new javax.swing.JPanel();
        lbTotalemCEDULAS = new javax.swing.JLabel();
        lbTotalemMoedas = new javax.swing.JLabel();
        lbTotalConferido = new javax.swing.JLabel();
        ffTotalemCedulas = new javax.swing.JFormattedTextField();
        ffTotalemMoedas = new javax.swing.JFormattedTextField();
        ffTotalConferido = new javax.swing.JFormattedTextField();
        lbValordoSuprimento = new javax.swing.JLabel();
        ffValordoSuprimentos = new javax.swing.JFormattedTextField();
        lbDiferencas = new javax.swing.JLabel();
        ffDiferencaSuprimentos = new javax.swing.JFormattedTextField();
        btnEmitirComprovante = new javax.swing.JButton();
        btnVisualizarComprovante = new javax.swing.JButton();
        pnlCadastrodeCaixa = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbDadosdeCaixa = new javax.swing.JTable();
        pnlDadosDoCaixa = new javax.swing.JPanel();
        lbDadosdoCaixa = new javax.swing.JLabel();
        txfCodigo = new javax.swing.JTextField();
        lbCodigo = new javax.swing.JLabel();
        lbNome = new javax.swing.JLabel();
        cbxOPERADOR = new javax.swing.JComboBox<>();
        lbOPERADOR = new javax.swing.JLabel();
        rbAtivo = new javax.swing.JRadioButton();
        rbInativo = new javax.swing.JRadioButton();
        lbStatus = new javax.swing.JLabel();
        lbDescricao = new javax.swing.JLabel();
        lbTipodeCaixa = new javax.swing.JLabel();
        txfDescricao = new javax.swing.JTextField();
        cbxTipodeCaixa = new javax.swing.JComboBox<>();
        ffDatadeAbertura = new javax.swing.JFormattedTextField();
        btnCalendarioAberto = new javax.swing.JButton();
        lbDatadeAbetura = new javax.swing.JLabel();
        lbHorariodeAbertura = new javax.swing.JLabel();
        ffHoradeAbertura = new javax.swing.JFormattedTextField();
        btnRelogioAberto = new javax.swing.JButton();
        lbDatadeFechamento = new javax.swing.JLabel();
        ffDatadeFechamento = new javax.swing.JFormattedTextField();
        ffHoradeFechamento = new javax.swing.JFormattedTextField();
        lbHorariodeFechamento = new javax.swing.JLabel();
        btnCalendarioFechamento = new javax.swing.JButton();
        btnHorariodefechamento = new javax.swing.JButton();
        lbObservacoesdoCaixa = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txaObservacoes = new javax.swing.JTextArea();
        txfNome = new javax.swing.JTextField();
        btnSalvodeCaixa = new javax.swing.JButton();
        btnEditarCaixa = new javax.swing.JButton();
        btnLimparCaixa = new javax.swing.JButton();
        btnNovodeCaixa = new javax.swing.JButton();
        pnlConferenciaTelas = new java.awt.Panel();
        pnlContagemdeCedulas = new javax.swing.JPanel();
        lbContagemdeCedulas = new javax.swing.JLabel();
        jScrollPane15 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        lbTotalemCedulas = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        pnlMeiosdePagamentos = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();
        jScrollPane16 = new javax.swing.JScrollPane();
        tbMeiosdePagamento = new javax.swing.JTable();
        lbMeiosdePagamento = new javax.swing.JLabel();
        lbPGEsperando = new javax.swing.JLabel();
        lbPGConferido = new javax.swing.JLabel();
        lbPGDiferenca = new javax.swing.JLabel();
        pnlContagemdeMoedas = new javax.swing.JPanel();
        jScrollPane17 = new javax.swing.JScrollPane();
        tbqttdeMoedas = new javax.swing.JTable();
        lbContagemdeMoedas = new javax.swing.JLabel();
        TotalqttdeMoedas = new javax.swing.JLabel();
        lbTotalqttSubtotal = new javax.swing.JLabel();
        lbTotalqttEsperando = new javax.swing.JLabel();
        lbTotalqttDiferenca = new javax.swing.JLabel();
        pnlResulmoGeral = new javax.swing.JPanel();
        lbResumoGeralCinco = new javax.swing.JLabel();
        jScrollPane23 = new javax.swing.JScrollPane();
        tbResulmoGeral = new javax.swing.JTable();
        pnlResgidtrodeDivergencias = new javax.swing.JPanel();
        lbResgistrodeDivergenias = new javax.swing.JLabel();
        lbTipodeDivergencia = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        lbDescricaodeDivergencias = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        pnlResumodeDinheiro = new javax.swing.JPanel();
        lbResumoDinheiro = new javax.swing.JLabel();
        jScrollPane24 = new javax.swing.JScrollPane();
        tbResumodeDinheiro = new javax.swing.JTable();
        pnlChetkdeAprovacao = new javax.swing.JPanel();
        lbChecktAprovacao = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        pnlSangriasTelas = new javax.swing.JPanel();
        lbSangriasdeCaixa = new javax.swing.JLabel();
        pnlSolicitacao = new javax.swing.JPanel();
        lbSolicitacoes = new javax.swing.JLabel();
        lbNumerodaS = new javax.swing.JLabel();
        lbCAIXA = new javax.swing.JLabel();
        txfNumerodaSolicitacoes = new javax.swing.JTextField();
        cbxCaixaSangrias = new javax.swing.JComboBox<>();
        lbDatadaS = new javax.swing.JLabel();
        txfDatadaSolicitacao = new javax.swing.JTextField();
        lbOperadorS = new javax.swing.JLabel();
        txfOperadorSolicitante = new javax.swing.JTextField();
        pnlMotivodaS = new javax.swing.JPanel();
        lbMdaSangria = new javax.swing.JLabel();
        cbxMotivoSangria = new javax.swing.JComboBox<>();
        lbMotivo = new javax.swing.JLabel();
        lbObservacoesSangria = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        txpObservaçoes = new javax.swing.JTextPane();
        tbCedula = new javax.swing.JScrollPane();
        tbCEDULA = new javax.swing.JTable();
        pnlValores = new javax.swing.JPanel();
        lbValores = new javax.swing.JLabel();
        lbValorS = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        txfValorSolicitado = new javax.swing.JTextPane();
        lbValorR = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        tpValorRetirado = new javax.swing.JTextPane();
        pnlAutorizacaoSangrias = new javax.swing.JPanel();
        lbAutorizacaoSangria = new javax.swing.JLabel();
        cbxUautorizacao = new javax.swing.JComboBox<>();
        lbUsuarioautorizacao = new javax.swing.JLabel();
        lbSenha = new javax.swing.JLabel();
        txfSenha = new javax.swing.JTextField();
        pnlTotaldeM = new javax.swing.JPanel();
        lbTotaldeM = new javax.swing.JLabel();
        jScrollPane10 = new javax.swing.JScrollPane();
        tbMoedaQvalor = new javax.swing.JTable();
        pnlComprovante = new javax.swing.JPanel();
        lbComprovante = new javax.swing.JLabel();
        lbCOMPROVANTE = new javax.swing.JLabel();
        btnEcomprovante = new javax.swing.JButton();
        btnComprovante = new javax.swing.JButton();
        btnVcomprovante = new javax.swing.JButton();
        btnVizualizarC = new javax.swing.JButton();
        pnlCaixasAeFTelas = new java.awt.Panel();
        pnlFiltros = new javax.swing.JPanel();
        lbFiltros = new javax.swing.JLabel();
        lbLoja = new javax.swing.JLabel();
        cbxLoja = new javax.swing.JComboBox<>();
        lbDATA = new javax.swing.JLabel();
        ffData = new javax.swing.JFormattedTextField();
        lbcalendario = new javax.swing.JButton();
        lbStatusdeCaixa = new javax.swing.JLabel();
        cbxStatusdeCaixa = new javax.swing.JComboBox<>();
        lbPeriodo = new javax.swing.JLabel();
        cbxPeriodo = new javax.swing.JComboBox<>();
        lbTurno = new javax.swing.JLabel();
        cbxTurnos = new javax.swing.JComboBox<>();
        lbCaixas = new javax.swing.JLabel();
        cbxCAIXAS = new javax.swing.JComboBox<>();
        btnLimparFLT = new javax.swing.JButton();
        btnAplicarFLT = new javax.swing.JButton();
        pnlCaixa = new javax.swing.JPanel();
        jScrollPane20 = new javax.swing.JScrollPane();
        tbCaixas = new javax.swing.JTable();
        lbCaixasAbertosFechados = new javax.swing.JLabel();
        pnlDetalhesdoCaixa = new javax.swing.JPanel();
        lbDetalhesdoCaixa = new javax.swing.JLabel();
        lbCaixadeDetalhes = new javax.swing.JLabel();
        lbCaixasdosDetalhes = new javax.swing.JLabel();
        lbStatusDetalhes = new javax.swing.JLabel();
        lbAberto = new javax.swing.JLabel();
        lbHora = new javax.swing.JLabel();
        lbTurnosDetalhes = new javax.swing.JLabel();
        lbManha = new javax.swing.JLabel();
        lbUltimaAtualizacao = new javax.swing.JLabel();
        lbDataDetalhes = new javax.swing.JLabel();
        lbObservacoesdeDetalhes = new javax.swing.JLabel();
        lbFrenteSetor = new javax.swing.JLabel();
        lbFrentedeLoja = new javax.swing.JLabel();
        lbOperadorDetalhes = new javax.swing.JLabel();
        lbNomedoOperado = new javax.swing.JLabel();
        lbAbertura = new javax.swing.JLabel();
        lbDatadeAbertura = new javax.swing.JLabel();
        lbUltimaData = new javax.swing.JLabel();
        lbUltimodia = new javax.swing.JLabel();
        lbValordaCaixa = new javax.swing.JLabel();
        lbValordoCaixa = new javax.swing.JLabel();
        rbAberto = new javax.swing.JRadioButton();
        pnlAcoesRapida = new javax.swing.JPanel();
        lbAcoesRapidas = new javax.swing.JLabel();
        btnAbrirCaixa = new javax.swing.JButton();
        btnFecharCaixa = new javax.swing.JButton();
        btnPausarCaixa = new javax.swing.JButton();
        btnEncerrarPausar = new javax.swing.JButton();
        btnIniciarConferecia = new javax.swing.JButton();
        btnCancelarConferencia = new javax.swing.JButton();
        btnImprimirResumo = new javax.swing.JButton();
        btnTransferirOperador = new javax.swing.JButton();
        pnlHistoricodeAlteracoes = new javax.swing.JPanel();
        lbHistoricoAlteracoes = new javax.swing.JLabel();
        jScrollPane21 = new javax.swing.JScrollPane();
        tbHistoricodeAlteracoes = new javax.swing.JTable();
        pnlTabelasCaixa = new javax.swing.JPanel();
        jScrollPane22 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        lbCAIXAS = new javax.swing.JLabel();
        pnlOperadoresdeTelas = new javax.swing.JPanel();
        pnlOPERADORES = new javax.swing.JPanel();
        lbTotalaReceber = new javax.swing.JLabel();
        txfTotalaReceberOperadores = new javax.swing.JTextField();
        lbOperacaodeDinheoC = new javax.swing.JLabel();
        pnlTotalrecebido = new javax.swing.JPanel();
        lbTotalrecebido = new javax.swing.JLabel();
        txfTotalrecebido = new javax.swing.JTextField();
        pnlTroco = new javax.swing.JPanel();
        lbTroco = new javax.swing.JLabel();
        txfTrocoOperadores = new javax.swing.JTextField();
        btnImagemTroco = new javax.swing.JButton();
        pnlInformacoesdaVenda = new javax.swing.JPanel();
        lbinformacoesdaVenda = new javax.swing.JLabel();
        lbOperador = new javax.swing.JLabel();
        lbData = new javax.swing.JLabel();
        lbVenda = new javax.swing.JLabel();
        lbSubtotal = new javax.swing.JLabel();
        lbDescontos = new javax.swing.JLabel();
        lbTotaldaVenda = new javax.swing.JLabel();
        lbValorTotal = new javax.swing.JLabel();
        lbNomedoOperador = new javax.swing.JLabel();
        lbDatadaVenda = new javax.swing.JLabel();
        lbValoresdeVenda = new javax.swing.JLabel();
        lbValorSubtotal = new javax.swing.JLabel();
        lbValordoDesconto = new javax.swing.JLabel();
        pnlCedulasOperadores = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tbMoedasValor = new javax.swing.JTable();
        pnlCedulas = new javax.swing.JPanel();
        jScrollPane11 = new javax.swing.JScrollPane();
        tbValorqtt = new javax.swing.JTable();
        lbCedulas = new javax.swing.JLabel();
        pnlAcoesRapidas = new javax.swing.JPanel();
        lbAcoesRepidas = new javax.swing.JLabel();
        btnSangria = new javax.swing.JButton();
        btnCoferencia = new javax.swing.JButton();
        btnSuprimento = new javax.swing.JButton();
        btnHistorico = new javax.swing.JButton();
        pnlRecebimento = new javax.swing.JPanel();
        lbRecebimento = new javax.swing.JLabel();
        lbValorqttRecebido = new javax.swing.JLabel();
        btnImagemValorRecebido = new javax.swing.JButton();
        lbTrocoCalculado = new javax.swing.JLabel();
        lbValordoTroco = new javax.swing.JLabel();
        txfValorTotalRecebido = new javax.swing.JTextField();
        pnlResumodoRecebimento = new javax.swing.JPanel();
        lbResumodoRecebimento = new javax.swing.JLabel();
        lbTotaldeCedulas = new javax.swing.JLabel();
        lbValorTotalMoedas = new javax.swing.JLabel();
        lbTotalRecebido = new javax.swing.JLabel();
        lbTrocoRecebido = new javax.swing.JLabel();
        btnRegularTroco = new javax.swing.JButton();
        pnlMoedas = new javax.swing.JPanel();
        lbMoedas = new javax.swing.JLabel();
        tbValorqttMoedas = new javax.swing.JScrollPane();
        tbValorqttMoeda = new javax.swing.JTable();
        pnlTabelasdeCedulas = new javax.swing.JPanel();
        jScrollPane18 = new javax.swing.JScrollPane();
        tbCedulasdeOperadores = new javax.swing.JTable();
        lbCedula = new javax.swing.JLabel();

        lbDdeCaixa.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbDdeCaixa.setText("Dados de Caixa");

        lbCodigoCadrastodeCaixa.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbCodigoCadrastodeCaixa.setText("Código:");

        lbPontdeACadrastodeC.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbPontdeACadrastodeC.setText("Pontos de Atedimento:");

        lbSCadrastodeCaixa.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbSCadrastodeCaixa.setText("Status:");

        lbOCadrastodeCaixa.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbOCadrastodeCaixa.setText("Operador:");

        rbAtivoCadrastodeCaixa.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        rbAtivoCadrastodeCaixa.setText("Ativo");

        rbInativoCadrastodeCaixa.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        rbInativoCadrastodeCaixa.setText("Inativo");

        cbxPontosdeACadrastodeCaixa.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("Descrição:");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Tipo de Caixa:");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("Data de Abertura:");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("Horario de Abertura:");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pequena", "Normal", "Grande", " " }));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("Data de Fechamento:");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("Horário de Fechamento:");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setText("Observações:");

        jTextArea3.setColumns(20);
        jTextArea3.setRows(5);
        jScrollPane4.setViewportView(jTextArea3);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbPontdeACadrastodeC)
                                    .addComponent(lbCodigoCadrastodeCaixa))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txfCcadrastoDeCaixa)
                                    .addComponent(cbxPontosdeACadrastodeCaixa, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(lbDdeCaixa))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbOCadrastodeCaixa)
                            .addComponent(lbSCadrastodeCaixa))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(rbAtivoCadrastodeCaixa)
                                .addGap(45, 45, 45)
                                .addComponent(rbInativoCadrastodeCaixa))
                            .addComponent(cbxOperadorCadrastodecaixa, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)))
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel7Layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jFormattedTextField1))
                                    .addGroup(jPanel7Layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jFormattedTextField2, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(105, 105, 105)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 474, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(293, 293, 293))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel7Layout.createSequentialGroup()
                                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel7Layout.createSequentialGroup()
                                                .addComponent(jLabel7)
                                                .addGap(0, 0, Short.MAX_VALUE))
                                            .addGroup(jPanel7Layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                    .addGroup(jPanel7Layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel6)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43)
                        .addComponent(jLabel5)
                        .addContainerGap())
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addComponent(jFormattedTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(390, 390, 390))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addComponent(jFormattedTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(373, 373, 373))))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(lbDdeCaixa)
                        .addGap(69, 69, 69)
                        .addComponent(lbCodigoCadrastodeCaixa)
                        .addGap(56, 56, 56))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jFormattedTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txfCcadrastoDeCaixa, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel1)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel5))
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cbxPontosdeACadrastodeCaixa, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lbPontdeACadrastodeC)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel2)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbOCadrastodeCaixa)
                            .addComponent(cbxOperadorCadrastodecaixa, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lbSCadrastodeCaixa)
                                    .addComponent(rbInativoCadrastodeCaixa)
                                    .addComponent(rbAtivoCadrastodeCaixa))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(52, 52, 52)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3)
                                    .addComponent(jFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel7Layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jFormattedTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel4)))
                                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE))
                                .addGap(19, 19, 19))))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jFormattedTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(59, 59, 59)
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane4))))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTabbedPane1.setFont(new java.awt.Font("Segoe UI Black", 2, 12)); // NOI18N

        lbDataInicialMovimentacoes.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lbDataInicialMovimentacoes.setText("Data Inicial:");

        ffDataInicial.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("##/##/####"))));

        lbOperadorMovimentacoes.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lbOperadorMovimentacoes.setText("Operador:");

        cbxOpedaror.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));

        btncalendarioDataInicial.setIcon(new javax.swing.ImageIcon("C:\\Users\\PedroLeite\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\calendar.png")); // NOI18N

        lbDATAFinal.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lbDATAFinal.setText("Data Final:");

        ffDataFinal.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat(""))));

        lbValorRS.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lbValorRS.setText("Valor (R$):");

        btnCalendarioDataFinal.setIcon(new javax.swing.ImageIcon("C:\\Users\\PedroLeite\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\calendar.png")); // NOI18N

        lbCaixaMovimentacoes.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lbCaixaMovimentacoes.setText("Caixas:");

        lbMovimentacoes.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lbMovimentacoes.setText("Tipo de Movimentações:");

        cbxMovimentacoes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));

        btnPesquisa.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnPesquisa.setIcon(new javax.swing.ImageIcon("C:\\Users\\PedroLeite\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\search.png")); // NOI18N
        btnPesquisa.setText("Pesquisar");

        javax.swing.GroupLayout pnlMovimentacoesLayout = new javax.swing.GroupLayout(pnlMovimentacoes);
        pnlMovimentacoes.setLayout(pnlMovimentacoesLayout);
        pnlMovimentacoesLayout.setHorizontalGroup(
            pnlMovimentacoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMovimentacoesLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(pnlMovimentacoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnPesquisa)
                    .addGroup(pnlMovimentacoesLayout.createSequentialGroup()
                        .addGroup(pnlMovimentacoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbDataInicialMovimentacoes)
                            .addGroup(pnlMovimentacoesLayout.createSequentialGroup()
                                .addComponent(ffDataInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btncalendarioDataInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lbOperadorMovimentacoes)
                            .addComponent(cbxOpedaror, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(28, 28, 28)
                        .addGroup(pnlMovimentacoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(pnlMovimentacoesLayout.createSequentialGroup()
                                .addComponent(ffDataFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnCalendarioDataFinal, javax.swing.GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE))
                            .addComponent(lbDATAFinal)
                            .addComponent(lbValorRS)
                            .addComponent(txfValorRS))
                        .addGap(26, 26, 26)
                        .addGroup(pnlMovimentacoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbCaixaMovimentacoes)
                            .addComponent(cbxCaixas, 0, 300, Short.MAX_VALUE)
                            .addComponent(lbMovimentacoes)
                            .addComponent(cbxMovimentacoes, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(348, Short.MAX_VALUE))
        );
        pnlMovimentacoesLayout.setVerticalGroup(
            pnlMovimentacoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMovimentacoesLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(pnlMovimentacoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbDataInicialMovimentacoes)
                    .addComponent(lbDATAFinal)
                    .addComponent(lbCaixaMovimentacoes))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlMovimentacoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btncalendarioDataInicial, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ffDataInicial)
                    .addComponent(ffDataFinal, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnCalendarioDataFinal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbxCaixas, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGroup(pnlMovimentacoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlMovimentacoesLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(lbOperadorMovimentacoes))
                    .addGroup(pnlMovimentacoesLayout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(pnlMovimentacoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbValorRS)
                            .addComponent(lbMovimentacoes))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlMovimentacoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cbxOpedaror, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(txfValorRS)
                    .addComponent(cbxMovimentacoes))
                .addGap(18, 18, 18)
                .addComponent(btnPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        btnImprimi.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnImprimi.setIcon(new javax.swing.ImageIcon("C:\\Users\\PedroLeite\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\impressora.png")); // NOI18N
        btnImprimi.setText("Imprimir");

        btnExportarMovimentacoes.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnExportarMovimentacoes.setIcon(new javax.swing.ImageIcon("C:\\Users\\PedroLeite\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\diskette (1).png")); // NOI18N
        btnExportarMovimentacoes.setText("Exportar");

        btnVizualiza.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnVizualiza.setIcon(new javax.swing.ImageIcon("C:\\Users\\PedroLeite\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\olho (1).png")); // NOI18N
        btnVizualiza.setText("Vizualiar");
        btnVizualiza.addActionListener(this::btnVizualizaActionPerformed);

        btnVoltarMovimentacoes.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnVoltarMovimentacoes.setIcon(new javax.swing.ImageIcon("C:\\Users\\PedroLeite\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\back-arrow.png")); // NOI18N
        btnVoltarMovimentacoes.setText("Voltar");

        tbMovimentacoes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Data/Hora", "Caixa", "Operador", "Tipo", "Descrição", "Valor", "Observações"
            }
        ));
        tbResgistrosdeMovimentacoes.setViewportView(tbMovimentacoes);

        lbResgistrodeMovimentos.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lbResgistrodeMovimentos.setText("Regristro de Movimentações");

        javax.swing.GroupLayout pnlRegistroMovimentacoesLayout = new javax.swing.GroupLayout(pnlRegistroMovimentacoes);
        pnlRegistroMovimentacoes.setLayout(pnlRegistroMovimentacoesLayout);
        pnlRegistroMovimentacoesLayout.setHorizontalGroup(
            pnlRegistroMovimentacoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tbResgistrosdeMovimentacoes)
            .addGroup(pnlRegistroMovimentacoesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbResgistrodeMovimentos)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlRegistroMovimentacoesLayout.setVerticalGroup(
            pnlRegistroMovimentacoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlRegistroMovimentacoesLayout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addComponent(lbResgistrodeMovimentos)
                .addGap(18, 18, 18)
                .addComponent(tbResgistrosdeMovimentacoes, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout pnlMovimentacoesTelasLayout = new javax.swing.GroupLayout(pnlMovimentacoesTelas);
        pnlMovimentacoesTelas.setLayout(pnlMovimentacoesTelasLayout);
        pnlMovimentacoesTelasLayout.setHorizontalGroup(
            pnlMovimentacoesTelasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMovimentacoesTelasLayout.createSequentialGroup()
                .addGroup(pnlMovimentacoesTelasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(pnlRegistroMovimentacoes, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlMovimentacoesTelasLayout.createSequentialGroup()
                        .addComponent(btnImprimi, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnExportarMovimentacoes, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnVizualiza, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(340, 340, 340)
                        .addComponent(btnVoltarMovimentacoes, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 3869, Short.MAX_VALUE))
            .addGroup(pnlMovimentacoesTelasLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(pnlMovimentacoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlMovimentacoesTelasLayout.setVerticalGroup(
            pnlMovimentacoesTelasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMovimentacoesTelasLayout.createSequentialGroup()
                .addComponent(pnlMovimentacoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlMovimentacoesTelasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnImprimi, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addComponent(btnExportarMovimentacoes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnVoltarMovimentacoes, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnVizualiza, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlRegistroMovimentacoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 1529, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Movimentações", pnlMovimentacoesTelas);

        lbHistoricodeCaixa.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbHistoricodeCaixa.setText("Historico de Caixas");

        btnRelogio.setIcon(new javax.swing.ImageIcon("C:\\Users\\PedroLeite\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\clock (1).png")); // NOI18N

        lbDataInicialHistorico.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lbDataInicialHistorico.setText("Data Inicial:");
        lbDataInicialHistorico.setToolTipText("");

        btnDataInicial.setIcon(new javax.swing.ImageIcon("C:\\Users\\PedroLeite\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\calendar.png")); // NOI18N

        lbDataFinal.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lbDataFinal.setText("Data Final:");

        btnDataFinal.setIcon(new javax.swing.ImageIcon("C:\\Users\\PedroLeite\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\calendar.png")); // NOI18N

        lbcaixa.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lbcaixa.setText("Caixa:");

        cbxCAIXA.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));

        lboperador.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lboperador.setText("Operador:");

        cbxoperador.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));

        lbPesquisar.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lbPesquisar.setText("Pesquisar");

        btnpesquisar.setBackground(new java.awt.Color(0, 0, 0));
        btnpesquisar.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnpesquisar.setForeground(new java.awt.Color(255, 255, 255));
        btnpesquisar.setIcon(new javax.swing.ImageIcon("C:\\Users\\PedroLeite\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\search.png")); // NOI18N
        btnpesquisar.setText("Pesquisar");

        lbTipodeOperacao.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbTipodeOperacao.setText("Tipo de operação:");

        rbTodos.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        rbTodos.setText("Todos");

        rbAbertura.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        rbAbertura.setText("Abertura");

        rbFechamento.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        rbFechamento.setText("Fechamento");

        rbMovimentacoes.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        rbMovimentacoes.setText("Movimentação");

        javax.swing.GroupLayout pnlHistoricodeCaixaLayout = new javax.swing.GroupLayout(pnlHistoricodeCaixa);
        pnlHistoricodeCaixa.setLayout(pnlHistoricodeCaixaLayout);
        pnlHistoricodeCaixaLayout.setHorizontalGroup(
            pnlHistoricodeCaixaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHistoricodeCaixaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlHistoricodeCaixaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlHistoricodeCaixaLayout.createSequentialGroup()
                        .addComponent(btnRelogio, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbHistoricodeCaixa)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlHistoricodeCaixaLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(pnlHistoricodeCaixaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(pnlHistoricodeCaixaLayout.createSequentialGroup()
                                .addGroup(pnlHistoricodeCaixaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnlHistoricodeCaixaLayout.createSequentialGroup()
                                        .addComponent(txfDataInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnDataInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(lbDataInicialHistorico))
                                .addGap(29, 29, 29)
                                .addGroup(pnlHistoricodeCaixaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnlHistoricodeCaixaLayout.createSequentialGroup()
                                        .addComponent(txfDataFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnDataFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(lbDataFinal))
                                .addGap(38, 38, 38)
                                .addGroup(pnlHistoricodeCaixaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbxCAIXA, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbcaixa))
                                .addGap(47, 47, 47)
                                .addGroup(pnlHistoricodeCaixaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbxoperador, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lboperador, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(27, 27, 27)
                                .addGroup(pnlHistoricodeCaixaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txfPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbPesquisar))
                                .addGap(316, 316, 316))
                            .addGroup(pnlHistoricodeCaixaLayout.createSequentialGroup()
                                .addComponent(lbTipodeOperacao)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnpesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(325, 325, 325))))
                    .addGroup(pnlHistoricodeCaixaLayout.createSequentialGroup()
                        .addComponent(rbTodos)
                        .addGap(18, 18, 18)
                        .addComponent(rbAbertura)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rbFechamento)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rbMovimentacoes)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        pnlHistoricodeCaixaLayout.setVerticalGroup(
            pnlHistoricodeCaixaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHistoricodeCaixaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlHistoricodeCaixaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbHistoricodeCaixa, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRelogio, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(pnlHistoricodeCaixaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbDataInicialHistorico)
                    .addComponent(lbDataFinal)
                    .addComponent(lbcaixa)
                    .addComponent(lboperador)
                    .addComponent(lbPesquisar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlHistoricodeCaixaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnDataFinal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnDataInicial, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txfDataInicial)
                    .addComponent(txfDataFinal, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cbxCAIXA, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txfPesquisar, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cbxoperador, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(pnlHistoricodeCaixaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnpesquisar)
                    .addComponent(lbTipodeOperacao))
                .addGap(18, 18, 18)
                .addGroup(pnlHistoricodeCaixaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbTodos)
                    .addComponent(rbAbertura)
                    .addComponent(rbFechamento)
                    .addComponent(rbMovimentacoes))
                .addContainerGap(36, Short.MAX_VALUE))
        );

        btnImprimir.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnImprimir.setIcon(new javax.swing.ImageIcon("C:\\Users\\PedroLeite\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\impressora.png")); // NOI18N
        btnImprimir.setText("Imprimir");

        btnExporta.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnExporta.setIcon(new javax.swing.ImageIcon("C:\\Users\\PedroLeite\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\diskette (1).png")); // NOI18N
        btnExporta.setText("Exportar");

        btnVizualizar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnVizualizar.setIcon(new javax.swing.ImageIcon("C:\\Users\\PedroLeite\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\olho (1).png")); // NOI18N
        btnVizualizar.setText("Vizualizar");
        btnVizualizar.setToolTipText("");

        btnVoltar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnVoltar.setIcon(new javax.swing.ImageIcon("C:\\Users\\PedroLeite\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\back-arrow.png")); // NOI18N
        btnVoltar.setText("Voltar");

        lbResgistrodeHistoricocaixa.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbResgistrodeHistoricocaixa.setText("Resgistros do Historico de Caixa");

        tbResgistrosdoHistoricodeCaixa.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tbResgistrosdoHistoricodeCaixa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Data/Hora", "Tipo", "Caixa", "Operador", "Valor", "Observações"
            }
        ));
        tbResgistrosdeCaixa.setViewportView(tbResgistrosdoHistoricodeCaixa);

        javax.swing.GroupLayout pnlRegistrodeHistoricoLayout = new javax.swing.GroupLayout(pnlRegistrodeHistorico);
        pnlRegistrodeHistorico.setLayout(pnlRegistrodeHistoricoLayout);
        pnlRegistrodeHistoricoLayout.setHorizontalGroup(
            pnlRegistrodeHistoricoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRegistrodeHistoricoLayout.createSequentialGroup()
                .addComponent(tbResgistrosdeCaixa, javax.swing.GroupLayout.PREFERRED_SIZE, 1000, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(pnlRegistrodeHistoricoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbResgistrodeHistoricocaixa)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlRegistrodeHistoricoLayout.setVerticalGroup(
            pnlRegistrodeHistoricoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRegistrodeHistoricoLayout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(lbResgistrodeHistoricocaixa)
                .addGap(18, 18, 18)
                .addComponent(tbResgistrosdeCaixa, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(8, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlHistoricoTelasLayout = new javax.swing.GroupLayout(pnlHistoricoTelas);
        pnlHistoricoTelas.setLayout(pnlHistoricoTelasLayout);
        pnlHistoricoTelasLayout.setHorizontalGroup(
            pnlHistoricoTelasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHistoricoTelasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlHistoricoTelasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlHistoricoTelasLayout.createSequentialGroup()
                        .addComponent(btnImprimir)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnExporta, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnVizualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(418, 418, 418)
                        .addComponent(btnVoltar, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(pnlRegistrodeHistorico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlHistoricodeCaixa, javax.swing.GroupLayout.PREFERRED_SIZE, 1050, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(3765, Short.MAX_VALUE))
        );
        pnlHistoricoTelasLayout.setVerticalGroup(
            pnlHistoricoTelasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHistoricoTelasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlHistoricodeCaixa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlHistoricoTelasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlHistoricoTelasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnVizualizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnVoltar))
                    .addGroup(pnlHistoricoTelasLayout.createSequentialGroup()
                        .addGroup(pnlHistoricoTelasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnImprimir)
                            .addComponent(btnExporta))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlRegistrodeHistorico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1548, 1548, 1548))
        );

        jTabbedPane1.addTab("Histórico", pnlHistoricoTelas);

        lbCaixa.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbCaixa.setText("Caixa :");

        cbxCaixa.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));

        lbDadeCaixa.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbDadeCaixa.setText("Data:");

        btnCalendario.setIcon(new javax.swing.ImageIcon("C:\\Users\\PedroLeite\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\calendar.png")); // NOI18N

        btnOperador.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnOperador.setText("Operador:");

        cbxOperador.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));

        jLabel28.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel28.setText("Valor esperando (Sistema):");

        lbValorcantado.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbValorcantado.setText("Valor contado:");
        lbValorcantado.setToolTipText("");

        lbDiferenca.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbDiferenca.setText("Diferença:");

        txfDiferenca.setToolTipText("");

        lbTipodeDiferenca.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbTipodeDiferenca.setText("Tipo de Diferença");

        rbFalsa.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        rbFalsa.setText("Falsa");

        rbSobra.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        rbSobra.setText("Sobra");

        lbObservacoes.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbObservacoes.setText("Observações:");

        txaObservacoesDiferenciadeCaixa.setColumns(20);
        txaObservacoesDiferenciadeCaixa.setRows(5);
        jScrollPane12.setViewportView(txaObservacoesDiferenciadeCaixa);

        lbDiferencadeCaixa.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbDiferencadeCaixa.setText("Diferenças de Caixa");

        javax.swing.GroupLayout pnlDiferenciadeCaixaLayout = new javax.swing.GroupLayout(pnlDiferenciadeCaixa);
        pnlDiferenciadeCaixa.setLayout(pnlDiferenciadeCaixaLayout);
        pnlDiferenciadeCaixaLayout.setHorizontalGroup(
            pnlDiferenciadeCaixaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDiferenciadeCaixaLayout.createSequentialGroup()
                .addGroup(pnlDiferenciadeCaixaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlDiferenciadeCaixaLayout.createSequentialGroup()
                        .addGroup(pnlDiferenciadeCaixaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlDiferenciadeCaixaLayout.createSequentialGroup()
                                .addComponent(ffDatadeCaixa, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnCalendario, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlDiferenciadeCaixaLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(pnlDiferenciadeCaixaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnlDiferenciadeCaixaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(lbCaixa, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lbDadeCaixa, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(cbxCaixa, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(cbxOperador, javax.swing.GroupLayout.PREFERRED_SIZE, 398, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnOperador))))
                        .addGap(61, 61, 61)
                        .addGroup(pnlDiferenciadeCaixaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbDiferenca)
                            .addComponent(jLabel28)
                            .addComponent(txfValorEsperando)
                            .addComponent(lbValorcantado)
                            .addComponent(txfValorContado)
                            .addComponent(txfDiferenca, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE))
                        .addGap(35, 35, 35)
                        .addGroup(pnlDiferenciadeCaixaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbTipodeDiferenca)
                            .addGroup(pnlDiferenciadeCaixaLayout.createSequentialGroup()
                                .addComponent(rbFalsa)
                                .addGap(30, 30, 30)
                                .addComponent(rbSobra))
                            .addComponent(lbObservacoes)
                            .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 460, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnlDiferenciadeCaixaLayout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(lbDiferencadeCaixa)))
                .addContainerGap(50, Short.MAX_VALUE))
        );
        pnlDiferenciadeCaixaLayout.setVerticalGroup(
            pnlDiferenciadeCaixaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDiferenciadeCaixaLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(lbDiferencadeCaixa)
                .addGap(18, 18, 18)
                .addGroup(pnlDiferenciadeCaixaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbCaixa)
                    .addComponent(jLabel28)
                    .addComponent(lbTipodeDiferenca))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlDiferenciadeCaixaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txfValorEsperando, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlDiferenciadeCaixaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cbxCaixa, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(rbFalsa)
                        .addComponent(rbSobra)))
                .addGap(26, 26, 26)
                .addGroup(pnlDiferenciadeCaixaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbDadeCaixa)
                    .addComponent(lbValorcantado)
                    .addComponent(lbObservacoes))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlDiferenciadeCaixaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlDiferenciadeCaixaLayout.createSequentialGroup()
                        .addGroup(pnlDiferenciadeCaixaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txfValorContado, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ffDatadeCaixa, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(pnlDiferenciadeCaixaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnOperador)
                            .addComponent(lbDiferenca))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlDiferenciadeCaixaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cbxOperador, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnlDiferenciadeCaixaLayout.createSequentialGroup()
                                .addComponent(txfDiferenca, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                                .addGap(4, 4, 4)))
                        .addGap(107, 107, 107))
                    .addGroup(pnlDiferenciadeCaixaLayout.createSequentialGroup()
                        .addGroup(pnlDiferenciadeCaixaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnCalendario)
                            .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        btnNovo.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnNovo.setIcon(new javax.swing.ImageIcon("C:\\Users\\PedroLeite\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\marketing.png")); // NOI18N
        btnNovo.setText("Novo");

        btnSalvar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSalvar.setIcon(new javax.swing.ImageIcon("C:\\Users\\PedroLeite\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\diskette (1).png")); // NOI18N
        btnSalvar.setText("Salvar");

        btnEditar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnEditar.setIcon(new javax.swing.ImageIcon("C:\\Users\\PedroLeite\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\pencil (2).png")); // NOI18N
        btnEditar.setText("Editar");
        btnEditar.addActionListener(this::btnEditarActionPerformed);

        btnExcluir.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnExcluir.setIcon(new javax.swing.ImageIcon("C:\\Users\\PedroLeite\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\bin.png")); // NOI18N
        btnExcluir.setText("Excluir");

        btnLimpar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnLimpar.setIcon(new javax.swing.ImageIcon("C:\\Users\\PedroLeite\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\cleaning.png")); // NOI18N
        btnLimpar.setText("Limpar");

        btnPesquisar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnPesquisar.setIcon(new javax.swing.ImageIcon("C:\\Users\\PedroLeite\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\search.png")); // NOI18N
        btnPesquisar.setText("Pesquisar");

        lbRegistrodeCaixa.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbRegistrodeCaixa.setText("Registro de Difereças de caixa");

        tbResgistrodeCaixa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
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
                "ID", "Data", "Caixa", "Operador", "Valor Esperando", "Valor contado", "Diferença", "Tipo", "Observações"
            }
        ));
        jScrollPane13.setViewportView(tbResgistrodeCaixa);

        javax.swing.GroupLayout pnlRegistrodeCaixaLayout = new javax.swing.GroupLayout(pnlRegistrodeCaixa);
        pnlRegistrodeCaixa.setLayout(pnlRegistrodeCaixaLayout);
        pnlRegistrodeCaixaLayout.setHorizontalGroup(
            pnlRegistrodeCaixaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRegistrodeCaixaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlRegistrodeCaixaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane13, javax.swing.GroupLayout.DEFAULT_SIZE, 1298, Short.MAX_VALUE)
                    .addGroup(pnlRegistrodeCaixaLayout.createSequentialGroup()
                        .addComponent(lbRegistrodeCaixa)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        pnlRegistrodeCaixaLayout.setVerticalGroup(
            pnlRegistrodeCaixaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRegistrodeCaixaLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(lbRegistrodeCaixa)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane13, javax.swing.GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout pnlDiferencadeCaixasTelasLayout = new javax.swing.GroupLayout(pnlDiferencadeCaixasTelas);
        pnlDiferencadeCaixasTelas.setLayout(pnlDiferencadeCaixasTelasLayout);
        pnlDiferencadeCaixasTelasLayout.setHorizontalGroup(
            pnlDiferencadeCaixasTelasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDiferencadeCaixasTelasLayout.createSequentialGroup()
                .addGroup(pnlDiferencadeCaixasTelasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlDiferenciadeCaixa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlDiferencadeCaixasTelasLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 3511, Short.MAX_VALUE))
            .addGroup(pnlDiferencadeCaixasTelasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlRegistrodeCaixa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlDiferencadeCaixasTelasLayout.setVerticalGroup(
            pnlDiferencadeCaixasTelasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDiferencadeCaixasTelasLayout.createSequentialGroup()
                .addComponent(pnlDiferenciadeCaixa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlDiferencadeCaixasTelasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnLimpar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlDiferencadeCaixasTelasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnPesquisar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlRegistrodeCaixa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(1507, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Diferenças de Caixas", pnlDiferencadeCaixasTelas);

        lbDadosdeAtendimento.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbDadosdeAtendimento.setText("Dados de Pontos de Atendimento");

        lbcodigo.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbcodigo.setText("Código:");

        lbDescricaodePontosdeAtendimento.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbDescricaodePontosdeAtendimento.setText("Descrição:");

        lbTipoPontosdeAtendimento.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbTipoPontosdeAtendimento.setText("Tipo:");

        cbxTipoPontosdeAtendimento.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));

        lbendereco.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbendereco.setText("endereço:");

        lbTELEFONE.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbTELEFONE.setText("Telefone:");

        lbstatus.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbstatus.setText("Status:");

        btnATIVO.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnATIVO.setText("Ativo");

        btnINATIVO.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnINATIVO.setText("Inativo");

        lbObservacoePontosdeAtendimento.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbObservacoePontosdeAtendimento.setText("Observações:");

        txaObservacoesPontosdeAtendimento.setColumns(20);
        txaObservacoesPontosdeAtendimento.setRows(5);
        jScrollPane3.setViewportView(txaObservacoesPontosdeAtendimento);

        javax.swing.GroupLayout pnlDadosdeAtendimentoLayout = new javax.swing.GroupLayout(pnlDadosdeAtendimento);
        pnlDadosdeAtendimento.setLayout(pnlDadosdeAtendimentoLayout);
        pnlDadosdeAtendimentoLayout.setHorizontalGroup(
            pnlDadosdeAtendimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDadosdeAtendimentoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDadosdeAtendimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbDadosdeAtendimento)
                    .addGroup(pnlDadosdeAtendimentoLayout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(pnlDadosdeAtendimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbcodigo)
                            .addComponent(lbDescricaodePontosdeAtendimento)
                            .addComponent(lbTipoPontosdeAtendimento))
                        .addGap(26, 26, 26)
                        .addGroup(pnlDadosdeAtendimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txfCadigoPontosdeAtendimento)
                            .addComponent(txfDescricaoPontosdeAtendimento)
                            .addComponent(cbxTipoPontosdeAtendimento, 0, 200, Short.MAX_VALUE))
                        .addGap(32, 32, 32)
                        .addGroup(pnlDadosdeAtendimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(pnlDadosdeAtendimentoLayout.createSequentialGroup()
                                .addComponent(lbendereco)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(tfENDERECO, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlDadosdeAtendimentoLayout.createSequentialGroup()
                                .addGroup(pnlDadosdeAtendimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbTELEFONE)
                                    .addComponent(lbstatus))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(pnlDadosdeAtendimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tfTELEFONE)
                                    .addGroup(pnlDadosdeAtendimentoLayout.createSequentialGroup()
                                        .addComponent(btnATIVO)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btnINATIVO)
                                        .addGap(0, 0, Short.MAX_VALUE)))))
                        .addGap(91, 91, 91)
                        .addGroup(pnlDadosdeAtendimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbObservacoePontosdeAtendimento)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 580, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(297, Short.MAX_VALUE))
        );
        pnlDadosdeAtendimentoLayout.setVerticalGroup(
            pnlDadosdeAtendimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDadosdeAtendimentoLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(lbDadosdeAtendimento)
                .addGroup(pnlDadosdeAtendimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlDadosdeAtendimentoLayout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(pnlDadosdeAtendimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbcodigo)
                            .addComponent(txfCadigoPontosdeAtendimento, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbendereco)
                            .addComponent(tfENDERECO, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(49, 49, 49)
                        .addGroup(pnlDadosdeAtendimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbDescricaodePontosdeAtendimento)
                            .addComponent(txfDescricaoPontosdeAtendimento, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbTELEFONE)
                            .addComponent(tfTELEFONE, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(pnlDadosdeAtendimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbTipoPontosdeAtendimento)
                            .addComponent(cbxTipoPontosdeAtendimento, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbstatus)
                            .addComponent(btnATIVO)
                            .addComponent(btnINATIVO))
                        .addGap(197, 197, 197))
                    .addGroup(pnlDadosdeAtendimentoLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbObservacoePontosdeAtendimento)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(201, 201, 201))))
        );

        btnNOVO.setIcon(new javax.swing.ImageIcon("C:\\Users\\PedroLeite\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\marketing.png")); // NOI18N
        btnNOVO.setText("Novo");

        btnSALVAR.setIcon(new javax.swing.ImageIcon("C:\\Users\\PedroLeite\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\diskette (1).png")); // NOI18N
        btnSALVAR.setText("Salvar");
        btnSALVAR.addActionListener(this::btnSALVARActionPerformed);

        btnEDITAR.setIcon(new javax.swing.ImageIcon("C:\\Users\\PedroLeite\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\pencil (2).png")); // NOI18N
        btnEDITAR.setText("Editar");

        btnEXCLUIR.setIcon(new javax.swing.ImageIcon("C:\\Users\\PedroLeite\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\bin.png")); // NOI18N
        btnEXCLUIR.setText("Excluir");

        btnLIMPAR.setIcon(new javax.swing.ImageIcon("C:\\Users\\PedroLeite\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\cleaning.png")); // NOI18N
        btnLIMPAR.setText("Limpar");

        btnPESQUISAR.setIcon(new javax.swing.ImageIcon("C:\\Users\\PedroLeite\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\search.png")); // NOI18N
        btnPESQUISAR.setText("Pesquisar");
        btnPESQUISAR.addActionListener(this::btnPESQUISARActionPerformed);

        btnVOLTAR.setIcon(new javax.swing.ImageIcon("C:\\Users\\PedroLeite\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\right-arrow.png")); // NOI18N
        btnVOLTAR.setText("Voltar");

        tbPntosdeAtedimento.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
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
                "Código", "Descrição", "Tipo", "Endereço", "Telefone", "Status"
            }
        ));
        jScrollPane5.setViewportView(tbPntosdeAtedimento);

        javax.swing.GroupLayout pnlPontosdeAtendimentoTelasLayout = new javax.swing.GroupLayout(pnlPontosdeAtendimentoTelas);
        pnlPontosdeAtendimentoTelas.setLayout(pnlPontosdeAtendimentoTelasLayout);
        pnlPontosdeAtendimentoTelasLayout.setHorizontalGroup(
            pnlPontosdeAtendimentoTelasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPontosdeAtendimentoTelasLayout.createSequentialGroup()
                .addComponent(pnlDadosdeAtendimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 3248, Short.MAX_VALUE))
            .addGroup(pnlPontosdeAtendimentoTelasLayout.createSequentialGroup()
                .addGroup(pnlPontosdeAtendimentoTelasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlPontosdeAtendimentoTelasLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnNOVO, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSALVAR, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEDITAR, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEXCLUIR, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnLIMPAR, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnPESQUISAR, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnVOLTAR, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlPontosdeAtendimentoTelasLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 1316, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlPontosdeAtendimentoTelasLayout.setVerticalGroup(
            pnlPontosdeAtendimentoTelasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPontosdeAtendimentoTelasLayout.createSequentialGroup()
                .addComponent(pnlDadosdeAtendimento, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addGroup(pnlPontosdeAtendimentoTelasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNOVO, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSALVAR, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEDITAR, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEXCLUIR, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLIMPAR, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPESQUISAR, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnVOLTAR, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1546, 1546, 1546))
        );

        jTabbedPane1.addTab("Pontos de Atedimento", pnlPontosdeAtendimentoTelas);

        lbEntradasdeNumerarios.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lbEntradasdeNumerarios.setIcon(new javax.swing.ImageIcon("C:\\Users\\PedroLeite\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\healthy.png")); // NOI18N
        lbEntradasdeNumerarios.setText("1. ENTRADAS DE NUMERÁRIOS");

        lbDatadeSuprimentos.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lbDatadeSuprimentos.setText("Data e hora");

        try {
            ffDataeHORAS.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        lbCaixaDestino.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lbCaixaDestino.setText("Caixa destino");

        cbxCaixaDestino.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        cbxCaixaDestino.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));

        lbMotivodoSuprimento.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lbMotivodoSuprimento.setText("Motivo do suprimento *");

        lbValoraSuprir.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lbValoraSuprir.setText("Valor a suprir (R$)");

        lbObservacoesTX.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lbObservacoesTX.setText("Observações");

        txaObservacoesNumerarios.setColumns(20);
        txaObservacoesNumerarios.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txaObservacoesNumerarios.setRows(5);
        jScrollPane25.setViewportView(txaObservacoesNumerarios);

        javax.swing.GroupLayout pnlEntradasdeNumerosLayout = new javax.swing.GroupLayout(pnlEntradasdeNumeros);
        pnlEntradasdeNumeros.setLayout(pnlEntradasdeNumerosLayout);
        pnlEntradasdeNumerosLayout.setHorizontalGroup(
            pnlEntradasdeNumerosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEntradasdeNumerosLayout.createSequentialGroup()
                .addGroup(pnlEntradasdeNumerosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlEntradasdeNumerosLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(pnlEntradasdeNumerosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbObservacoesTX)
                            .addComponent(lbValoraSuprir)
                            .addComponent(lbMotivodoSuprimento)
                            .addComponent(lbDatadeSuprimentos)
                            .addComponent(lbEntradasdeNumerarios)
                            .addComponent(lbCaixaDestino)
                            .addGroup(pnlEntradasdeNumerosLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(pnlEntradasdeNumerosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cbxMotivodoSuprimento, 0, 195, Short.MAX_VALUE)
                                    .addComponent(cbxCaixaDestino, 0, 195, Short.MAX_VALUE)
                                    .addComponent(ffDataeHORAS, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txfValorASuprir)))))
                    .addGroup(pnlEntradasdeNumerosLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane25, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        pnlEntradasdeNumerosLayout.setVerticalGroup(
            pnlEntradasdeNumerosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEntradasdeNumerosLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(lbEntradasdeNumerarios)
                .addGap(38, 38, 38)
                .addComponent(lbDatadeSuprimentos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ffDataeHORAS, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbCaixaDestino)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cbxCaixaDestino, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbMotivodoSuprimento)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbxMotivodoSuprimento, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbValoraSuprir)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txfValorASuprir, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lbObservacoesTX)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        lbAutorizacao.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lbAutorizacao.setIcon(new javax.swing.ImageIcon("C:\\Users\\PedroLeite\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\healthy.png")); // NOI18N
        lbAutorizacao.setText("2. AUTORIZAÇÃO");

        lbUsuarioAutorizador.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lbUsuarioAutorizador.setText("Usuário autorizador *");

        lbSenhadaAutorizacao.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lbSenhadaAutorizacao.setText("Senha * ");

        txfSenhadeAutorizacao.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N

        lbDeHdaAutorizacao.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lbDeHdaAutorizacao.setText("Data e Hora da Autorização");

        try {
            ffDeHdaAutorizacao.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        btnDeHdeAutorizacao.setIcon(new javax.swing.ImageIcon("C:\\Users\\PedroLeite\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\calendar.png")); // NOI18N

        javax.swing.GroupLayout pnlAutorizacaoLayout = new javax.swing.GroupLayout(pnlAutorizacao);
        pnlAutorizacao.setLayout(pnlAutorizacaoLayout);
        pnlAutorizacaoLayout.setHorizontalGroup(
            pnlAutorizacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAutorizacaoLayout.createSequentialGroup()
                .addGroup(pnlAutorizacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlAutorizacaoLayout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(lbAutorizacao))
                    .addGroup(pnlAutorizacaoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lbSenhadaAutorizacao))
                    .addGroup(pnlAutorizacaoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lbUsuarioAutorizador))
                    .addGroup(pnlAutorizacaoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lbDeHdaAutorizacao))
                    .addGroup(pnlAutorizacaoLayout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(pnlAutorizacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(cbxUsuarioAutorizado, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txfSenhadeAutorizacao, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ffDeHdaAutorizacao, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDeHdeAutorizacao, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(38, Short.MAX_VALUE))
        );
        pnlAutorizacaoLayout.setVerticalGroup(
            pnlAutorizacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAutorizacaoLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(lbAutorizacao)
                .addGap(48, 48, 48)
                .addComponent(lbUsuarioAutorizador)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbxUsuarioAutorizado, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lbSenhadaAutorizacao)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txfSenhadeAutorizacao, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addComponent(lbDeHdaAutorizacao)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlAutorizacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnDeHdeAutorizacao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ffDeHdaAutorizacao))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lbConferencia.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lbConferencia.setIcon(new javax.swing.ImageIcon("C:\\Users\\PedroLeite\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\healthy.png")); // NOI18N
        lbConferencia.setText("CONFERÉNCIA");

        tbCedulasConferencia.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Cédulas", "QTD", "200,00", "Subtotal (R$)"
            }
        ));
        jScrollPane26.setViewportView(tbCedulasConferencia);

        tbMoedasConferencia.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Moedas", "QTD", "Valor (R$)", "Subtotal(R$)"
            }
        ));
        jScrollPane27.setViewportView(tbMoedasConferencia);

        lbTotaldeCedula.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lbTotaldeCedula.setText("TOTAL EM CEDULAS");

        lbTotaldeMoedas.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lbTotaldeMoedas.setText("TOTAL EM MOEDAS");

        lbValordeCedula.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lbValordeCedula.setForeground(new java.awt.Color(0, 204, 51));
        lbValordeCedula.setText("977,00");

        lbValordeMoedas.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lbValordeMoedas.setForeground(new java.awt.Color(0, 204, 0));
        lbValordeMoedas.setText("15,82");

        btnMoedasdeConferencia.setBackground(new java.awt.Color(0, 0, 0));
        btnMoedasdeConferencia.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        btnMoedasdeConferencia.setForeground(new java.awt.Color(0, 204, 0));
        btnMoedasdeConferencia.setText("MOEDAS");

        btnCedulas.setBackground(new java.awt.Color(0, 0, 0));
        btnCedulas.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        btnCedulas.setForeground(new java.awt.Color(0, 0, 255));
        btnCedulas.setText("CÉDULAS");

        javax.swing.GroupLayout pnlConferenciaLayout = new javax.swing.GroupLayout(pnlConferencia);
        pnlConferencia.setLayout(pnlConferenciaLayout);
        pnlConferenciaLayout.setHorizontalGroup(
            pnlConferenciaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlConferenciaLayout.createSequentialGroup()
                .addGroup(pnlConferenciaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlConferenciaLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(pnlConferenciaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane26, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                            .addComponent(btnCedulas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnlConferenciaLayout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(lbTotaldeCedula)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 158, Short.MAX_VALUE)
                        .addComponent(lbValordeCedula)
                        .addGap(37, 37, 37)))
                .addGroup(pnlConferenciaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlConferenciaLayout.createSequentialGroup()
                        .addGroup(pnlConferenciaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnMoedasdeConferencia, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlConferenciaLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jScrollPane27, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())
                    .addGroup(pnlConferenciaLayout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addComponent(lbTotaldeMoedas)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbValordeMoedas)
                        .addGap(33, 33, 33))))
            .addGroup(pnlConferenciaLayout.createSequentialGroup()
                .addGap(318, 318, 318)
                .addComponent(lbConferencia)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlConferenciaLayout.setVerticalGroup(
            pnlConferenciaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlConferenciaLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(lbConferencia)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlConferenciaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnMoedasdeConferencia)
                    .addComponent(btnCedulas))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlConferenciaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane27, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane26, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlConferenciaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbTotaldeCedula)
                    .addComponent(lbTotaldeMoedas)
                    .addComponent(lbValordeCedula)
                    .addComponent(lbValordeMoedas))
                .addContainerGap(40, Short.MAX_VALUE))
        );

        lbAtualizacaodeSaldo.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lbAtualizacaodeSaldo.setIcon(new javax.swing.ImageIcon("C:\\Users\\PedroLeite\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\healthy.png")); // NOI18N
        lbAtualizacaodeSaldo.setText("ATULIZAÇÃO DE SALDO");

        lbSaldoAnteriordoCaixa.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lbSaldoAnteriordoCaixa.setText("Saldo Anterior do Caixa (R$)");

        try {
            ffSaldoAtualizado.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        lbValorAtualizado.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lbValorAtualizado.setText("Valor do Suprimento (R$)");

        try {
            ffValordoSuprimento.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        lbNovoSaldodoCaixa.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lbNovoSaldodoCaixa.setText("Novo saldo do Caixa (R$)");

        lbDataeHoraAtualizacao.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lbDataeHoraAtualizacao.setText("Data e Hora");

        lbUsuarioAtualizacao.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lbUsuarioAtualizacao.setText("Usuarío");

        javax.swing.GroupLayout pnlAtualizacaodeSaldoLayout = new javax.swing.GroupLayout(pnlAtualizacaodeSaldo);
        pnlAtualizacaodeSaldo.setLayout(pnlAtualizacaodeSaldoLayout);
        pnlAtualizacaodeSaldoLayout.setHorizontalGroup(
            pnlAtualizacaodeSaldoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAtualizacaodeSaldoLayout.createSequentialGroup()
                .addGroup(pnlAtualizacaodeSaldoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlAtualizacaodeSaldoLayout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(lbAtualizacaodeSaldo))
                    .addGroup(pnlAtualizacaodeSaldoLayout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(pnlAtualizacaodeSaldoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbNovoSaldodoCaixa)
                            .addGroup(pnlAtualizacaodeSaldoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(ffSaldoAtualizado)
                                .addComponent(lbSaldoAnteriordoCaixa)
                                .addComponent(lbValorAtualizado)
                                .addComponent(ffValordoSuprimento, javax.swing.GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE))
                            .addComponent(lbDataeHoraAtualizacao)
                            .addComponent(lbUsuarioAtualizacao))))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        pnlAtualizacaodeSaldoLayout.setVerticalGroup(
            pnlAtualizacaodeSaldoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAtualizacaodeSaldoLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(lbAtualizacaodeSaldo)
                .addGap(51, 51, 51)
                .addComponent(lbSaldoAnteriordoCaixa)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ffSaldoAtualizado, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(lbValorAtualizado)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ffValordoSuprimento, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(57, 57, 57)
                .addComponent(lbNovoSaldodoCaixa)
                .addGap(36, 36, 36)
                .addComponent(lbDataeHoraAtualizacao)
                .addGap(34, 34, 34)
                .addComponent(lbUsuarioAtualizacao)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lbComprovanteAtualizado.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lbComprovanteAtualizado.setText("Comprovante");

        javax.swing.GroupLayout pnlComprovanteSuprimentosLayout = new javax.swing.GroupLayout(pnlComprovanteSuprimentos);
        pnlComprovanteSuprimentos.setLayout(pnlComprovanteSuprimentosLayout);
        pnlComprovanteSuprimentosLayout.setHorizontalGroup(
            pnlComprovanteSuprimentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlComprovanteSuprimentosLayout.createSequentialGroup()
                .addGroup(pnlComprovanteSuprimentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlComprovanteSuprimentosLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(lbComprovanteAtualizado))
                    .addGroup(pnlComprovanteSuprimentosLayout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(btnComprovanteAtualizacao, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(38, Short.MAX_VALUE))
        );
        pnlComprovanteSuprimentosLayout.setVerticalGroup(
            pnlComprovanteSuprimentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlComprovanteSuprimentosLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(lbComprovanteAtualizado)
                .addGap(18, 18, 18)
                .addComponent(btnComprovanteAtualizacao, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        lbTotalemCEDULAS.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lbTotalemCEDULAS.setText("Total em Cédulas");

        lbTotalemMoedas.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lbTotalemMoedas.setText("Total em Moedas");

        lbTotalConferido.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lbTotalConferido.setText("TOTAL conferido");

        ffTotalemCedulas.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,###.00"))));

        ffTotalemMoedas.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,###.00"))));

        ffTotalConferido.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,###.00"))));

        lbValordoSuprimento.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lbValordoSuprimento.setText("Valor do suprimento");

        ffValordoSuprimentos.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,###.00"))));

        lbDiferencas.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lbDiferencas.setText("Diferenças");

        ffDiferencaSuprimentos.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,###.00"))));

        btnEmitirComprovante.setBackground(new java.awt.Color(0, 0, 0));
        btnEmitirComprovante.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        btnEmitirComprovante.setForeground(new java.awt.Color(255, 255, 255));
        btnEmitirComprovante.setIcon(new javax.swing.ImageIcon("C:\\Users\\PedroLeite\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\impressora.png")); // NOI18N
        btnEmitirComprovante.setText("EMITIR COMPROVANTE");

        btnVisualizarComprovante.setBackground(new java.awt.Color(0, 0, 255));
        btnVisualizarComprovante.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnVisualizarComprovante.setForeground(new java.awt.Color(255, 255, 255));
        btnVisualizarComprovante.setIcon(new javax.swing.ImageIcon("C:\\Users\\PedroLeite\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\olho (1).png")); // NOI18N
        btnVisualizarComprovante.setText("VISUALIZAR COMPROVANTE");

        javax.swing.GroupLayout pnlConfirmacaodoComprovanteLayout = new javax.swing.GroupLayout(pnlConfirmacaodoComprovante);
        pnlConfirmacaodoComprovante.setLayout(pnlConfirmacaodoComprovanteLayout);
        pnlConfirmacaodoComprovanteLayout.setHorizontalGroup(
            pnlConfirmacaodoComprovanteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlConfirmacaodoComprovanteLayout.createSequentialGroup()
                .addGroup(pnlConfirmacaodoComprovanteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlConfirmacaodoComprovanteLayout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(lbDiferencas)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ffDiferencaSuprimentos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlConfirmacaodoComprovanteLayout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(pnlConfirmacaodoComprovanteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlConfirmacaodoComprovanteLayout.createSequentialGroup()
                                .addComponent(lbTotalemMoedas)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(ffTotalemMoedas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlConfirmacaodoComprovanteLayout.createSequentialGroup()
                                .addComponent(lbTotalemCEDULAS)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(ffTotalemCedulas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlConfirmacaodoComprovanteLayout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addGroup(pnlConfirmacaodoComprovanteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlConfirmacaodoComprovanteLayout.createSequentialGroup()
                                .addComponent(lbValordoSuprimento)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(ffValordoSuprimentos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlConfirmacaodoComprovanteLayout.createSequentialGroup()
                                .addComponent(lbTotalConferido)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(ffTotalConferido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(16, 16, 16))
            .addGroup(pnlConfirmacaodoComprovanteLayout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(pnlConfirmacaodoComprovanteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnEmitirComprovante, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnVisualizarComprovante, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(85, Short.MAX_VALUE))
        );
        pnlConfirmacaodoComprovanteLayout.setVerticalGroup(
            pnlConfirmacaodoComprovanteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlConfirmacaodoComprovanteLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(pnlConfirmacaodoComprovanteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbTotalemCEDULAS)
                    .addComponent(ffTotalemCedulas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(pnlConfirmacaodoComprovanteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbTotalemMoedas)
                    .addComponent(ffTotalemMoedas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(pnlConfirmacaodoComprovanteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbTotalConferido)
                    .addComponent(ffTotalConferido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlConfirmacaodoComprovanteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbValordoSuprimento)
                    .addComponent(ffValordoSuprimentos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlConfirmacaodoComprovanteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbDiferencas)
                    .addComponent(ffDiferencaSuprimentos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addComponent(btnEmitirComprovante)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnVisualizarComprovante)
                .addContainerGap(34, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlSuprimentosTelasLayout = new javax.swing.GroupLayout(pnlSuprimentosTelas);
        pnlSuprimentosTelas.setLayout(pnlSuprimentosTelasLayout);
        pnlSuprimentosTelasLayout.setHorizontalGroup(
            pnlSuprimentosTelasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSuprimentosTelasLayout.createSequentialGroup()
                .addGroup(pnlSuprimentosTelasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlSuprimentosTelasLayout.createSequentialGroup()
                        .addComponent(pnlEntradasdeNumeros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(pnlAutorizacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(pnlConferencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnlAtualizacaodeSaldo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlSuprimentosTelasLayout.createSequentialGroup()
                        .addComponent(pnlComprovanteSuprimentos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(182, 182, 182)
                        .addComponent(pnlConfirmacaodoComprovante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 3150, Short.MAX_VALUE))
        );
        pnlSuprimentosTelasLayout.setVerticalGroup(
            pnlSuprimentosTelasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSuprimentosTelasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlAtualizacaodeSaldo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 1837, Short.MAX_VALUE))
            .addGroup(pnlSuprimentosTelasLayout.createSequentialGroup()
                .addGroup(pnlSuprimentosTelasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlEntradasdeNumeros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlSuprimentosTelasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(pnlConferencia, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(pnlAutorizacao, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(pnlSuprimentosTelasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pnlComprovanteSuprimentos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlConfirmacaodoComprovante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(1383, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Suprimentos", pnlSuprimentosTelas);

        tbDadosdeCaixa.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tbDadosdeCaixa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"000123", "Loja Central", "Caixa01", "Normal", "Maria Souza", "Aberto", "17/06/2025", "8:00", "", null},
                {"000124", "Loja Central", "Caixa02", "Normal", "Carlos Lima", "Aberto", "17/06/2025", "8:05", null, null},
                {"000125", "Loja Central", "Caixa03", "Preferencial", "Ana Oliveira", "Fechado", "16/06/2025", "9:00", "16/06/2025", "17:30"},
                {"000126", "Loja Bairro", "Caixa01", "Normal", "Roberto Alves", "Fechado", "16/06/2025", "9:10", "16/06/2025", "18:00"},
                {"000127", "Loja Bairro", "Caixa02", "Normal", "Juliana Costa", "Aberto", "17/06/2025", "8:15", null, null}
            },
            new String [] {
                "Código", "Ponto de Atendimento", "Descrição", "Tipo de Caixa", "Operador", "Status", "Data de Abertura", "Horário de Abertura", "Data de Fechamento", "Horário de Fechamento"
            }
        ));
        jScrollPane2.setViewportView(tbDadosdeCaixa);

        lbDadosdoCaixa.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbDadosdoCaixa.setText("Dados do Caixa");

        lbCodigo.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbCodigo.setText("Código:");

        lbNome.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbNome.setText("Nome:");

        cbxOPERADOR.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));

        lbOPERADOR.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbOPERADOR.setText("Operador:");

        rbAtivo.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        rbAtivo.setText("Ativo");

        rbInativo.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        rbInativo.setText("Inativo");

        lbStatus.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbStatus.setText("Status:");

        lbDescricao.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbDescricao.setText("Descrição:");

        lbTipodeCaixa.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbTipodeCaixa.setText("Tipo de Caixa:");

        cbxTipodeCaixa.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));

        btnCalendarioAberto.setIcon(new javax.swing.ImageIcon("C:\\Users\\PedroLeite\\Documents\\ProjetoPI_Supermercado\\GerenciamentoSupermercado\\src\\main\\java\\com\\mycompany\\gerenciamentosupermercado\\calendar.png")); // NOI18N

        lbDatadeAbetura.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbDatadeAbetura.setText("Data de Abertura:");

        lbHorariodeAbertura.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbHorariodeAbertura.setText("Horário de Abertura:");

        btnRelogioAberto.setIcon(new javax.swing.ImageIcon("C:\\Users\\PedroLeite\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\clock (1).png")); // NOI18N

        lbDatadeFechamento.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbDatadeFechamento.setText("Data de Fechamento:");

        ffDatadeFechamento.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("##/##/####"))));

        ffHoradeFechamento.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat(""))));

        lbHorariodeFechamento.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbHorariodeFechamento.setText("Horário de Fechamento:");

        btnCalendarioFechamento.setIcon(new javax.swing.ImageIcon("C:\\Users\\PedroLeite\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\calendar.png")); // NOI18N

        btnHorariodefechamento.setIcon(new javax.swing.ImageIcon("C:\\Users\\PedroLeite\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\clock (1).png")); // NOI18N

        lbObservacoesdoCaixa.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbObservacoesdoCaixa.setText("Observações:");

        txaObservacoes.setColumns(20);
        txaObservacoes.setRows(5);
        jScrollPane1.setViewportView(txaObservacoes);

        javax.swing.GroupLayout pnlDadosDoCaixaLayout = new javax.swing.GroupLayout(pnlDadosDoCaixa);
        pnlDadosDoCaixa.setLayout(pnlDadosDoCaixaLayout);
        pnlDadosDoCaixaLayout.setHorizontalGroup(
            pnlDadosDoCaixaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDadosDoCaixaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDadosDoCaixaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDadosDoCaixaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lbDadosdoCaixa, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(pnlDadosDoCaixaLayout.createSequentialGroup()
                            .addGap(134, 134, 134)
                            .addComponent(txfCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnlDadosDoCaixaLayout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addGroup(pnlDadosDoCaixaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlDadosDoCaixaLayout.createSequentialGroup()
                                .addComponent(lbStatus)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(rbAtivo)
                                .addGap(35, 35, 35)
                                .addComponent(rbInativo)
                                .addGap(33, 33, 33))
                            .addGroup(pnlDadosDoCaixaLayout.createSequentialGroup()
                                .addComponent(lbOPERADOR)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cbxOPERADOR, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlDadosDoCaixaLayout.createSequentialGroup()
                                .addComponent(lbCodigo)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(pnlDadosDoCaixaLayout.createSequentialGroup()
                                .addComponent(lbNome)
                                .addGap(43, 43, 43)
                                .addComponent(txfNome)))))
                .addGap(46, 46, 46)
                .addGroup(pnlDadosDoCaixaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnlDadosDoCaixaLayout.createSequentialGroup()
                        .addGroup(pnlDadosDoCaixaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbDatadeAbetura)
                            .addComponent(lbDescricao)
                            .addComponent(lbTipodeCaixa))
                        .addGap(22, 22, 22)
                        .addGroup(pnlDadosDoCaixaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(pnlDadosDoCaixaLayout.createSequentialGroup()
                                .addGap(221, 221, 221)
                                .addComponent(btnCalendarioAberto))
                            .addComponent(txfDescricao, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(cbxTipodeCaixa, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(pnlDadosDoCaixaLayout.createSequentialGroup()
                        .addComponent(lbHorariodeAbertura)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ffHoradeAbertura, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRelogioAberto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(ffDatadeAbertura, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlDadosDoCaixaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbObservacoesdoCaixa)
                    .addGroup(pnlDadosDoCaixaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 437, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(pnlDadosDoCaixaLayout.createSequentialGroup()
                            .addGroup(pnlDadosDoCaixaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(lbHorariodeFechamento)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlDadosDoCaixaLayout.createSequentialGroup()
                                    .addGap(8, 8, 8)
                                    .addComponent(lbDatadeFechamento)))
                            .addGap(25, 25, 25)
                            .addGroup(pnlDadosDoCaixaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(ffDatadeFechamento)
                                .addComponent(ffHoradeFechamento, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE))
                            .addGap(1, 1, 1)
                            .addGroup(pnlDadosDoCaixaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(btnHorariodefechamento, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnCalendarioFechamento, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(115, Short.MAX_VALUE))
        );
        pnlDadosDoCaixaLayout.setVerticalGroup(
            pnlDadosDoCaixaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDadosDoCaixaLayout.createSequentialGroup()
                .addGroup(pnlDadosDoCaixaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlDadosDoCaixaLayout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(lbDadosdoCaixa)
                        .addGap(18, 18, 18)
                        .addGroup(pnlDadosDoCaixaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbCodigo)
                            .addComponent(txfCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbDescricao)
                            .addComponent(txfDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnlDadosDoCaixaLayout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addGroup(pnlDadosDoCaixaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ffDatadeFechamento, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbDatadeFechamento)))
                    .addGroup(pnlDadosDoCaixaLayout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addComponent(btnCalendarioFechamento, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(pnlDadosDoCaixaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnHorariodefechamento, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlDadosDoCaixaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(ffHoradeFechamento, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lbHorariodeFechamento)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlDadosDoCaixaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbNome)
                    .addComponent(lbTipodeCaixa, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxTipodeCaixa, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txfNome, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(pnlDadosDoCaixaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlDadosDoCaixaLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(pnlDadosDoCaixaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(pnlDadosDoCaixaLayout.createSequentialGroup()
                                .addGroup(pnlDadosDoCaixaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cbxOPERADOR, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbOPERADOR))
                                .addGap(29, 29, 29)
                                .addGroup(pnlDadosDoCaixaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lbStatus)
                                    .addComponent(rbAtivo)
                                    .addComponent(rbInativo))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(pnlDadosDoCaixaLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(pnlDadosDoCaixaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnlDadosDoCaixaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(ffDatadeAbertura, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lbDatadeAbetura))
                                    .addComponent(btnCalendarioAberto))
                                .addGap(24, 24, 24)
                                .addGroup(pnlDadosDoCaixaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lbHorariodeAbertura)
                                    .addComponent(btnRelogioAberto, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(ffHoradeAbertura, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(91, 91, 91))))
                    .addGroup(pnlDadosDoCaixaLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(lbObservacoesdoCaixa)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        btnSalvodeCaixa.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSalvodeCaixa.setIcon(new javax.swing.ImageIcon("C:\\Users\\PedroLeite\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\diskette (1).png")); // NOI18N
        btnSalvodeCaixa.setText("Salvo");

        btnEditarCaixa.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnEditarCaixa.setIcon(new javax.swing.ImageIcon("C:\\Users\\PedroLeite\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\pencil (2).png")); // NOI18N
        btnEditarCaixa.setText("Editar");
        btnEditarCaixa.addActionListener(this::btnEditarCaixaActionPerformed);

        btnLimparCaixa.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnLimparCaixa.setIcon(new javax.swing.ImageIcon("C:\\Users\\PedroLeite\\Documents\\ProjetoPI_Supermercado\\GerenciamentoSupermercado\\src\\main\\java\\com\\mycompany\\gerenciamentosupermercado\\cleaning.png")); // NOI18N
        btnLimparCaixa.setText("Limpar");
        btnLimparCaixa.addActionListener(this::btnLimparCaixaActionPerformed);

        btnNovodeCaixa.setIcon(new javax.swing.ImageIcon("C:\\Users\\PedroLeite\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\marketing.png")); // NOI18N
        btnNovodeCaixa.setText("Novo");
        btnNovodeCaixa.addActionListener(this::btnNovodeCaixaActionPerformed);

        javax.swing.GroupLayout pnlCadastrodeCaixaLayout = new javax.swing.GroupLayout(pnlCadastrodeCaixa);
        pnlCadastrodeCaixa.setLayout(pnlCadastrodeCaixaLayout);
        pnlCadastrodeCaixaLayout.setHorizontalGroup(
            pnlCadastrodeCaixaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCadastrodeCaixaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlCadastrodeCaixaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlCadastrodeCaixaLayout.createSequentialGroup()
                        .addComponent(btnNovodeCaixa, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSalvodeCaixa, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEditarCaixa, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnLimparCaixa, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(pnlDadosDoCaixa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1312, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1258, 1258, 1258))
        );
        pnlCadastrodeCaixaLayout.setVerticalGroup(
            pnlCadastrodeCaixaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCadastrodeCaixaLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(pnlDadosDoCaixa, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(pnlCadastrodeCaixaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlCadastrodeCaixaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(pnlCadastrodeCaixaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnEditarCaixa, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnLimparCaixa, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(btnSalvodeCaixa, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnNovodeCaixa, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(1535, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Cadastro de Caixas", pnlCadastrodeCaixa);

        lbContagemdeCedulas.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbContagemdeCedulas.setText("1.Contagem de Cédulas");

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
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
                "Cédula", "Quantidade Contada", "Valor Unitario (R$)", "Subtotal(R$)", "Esperado (R$)", "Diferença (R$)"
            }
        ));
        jScrollPane15.setViewportView(jTable2);

        lbTotalemCedulas.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbTotalemCedulas.setText("Total em Cédulas");

        jLabel35.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel35.setText("1,680,00");

        jLabel38.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel38.setText("1,630,00");

        jLabel39.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(0, 153, 0));
        jLabel39.setText("+50,00");

        javax.swing.GroupLayout pnlContagemdeCedulasLayout = new javax.swing.GroupLayout(pnlContagemdeCedulas);
        pnlContagemdeCedulas.setLayout(pnlContagemdeCedulasLayout);
        pnlContagemdeCedulasLayout.setHorizontalGroup(
            pnlContagemdeCedulasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContagemdeCedulasLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(pnlContagemdeCedulasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlContagemdeCedulasLayout.createSequentialGroup()
                        .addComponent(lbTotalemCedulas)
                        .addGap(69, 69, 69)
                        .addComponent(jLabel35)
                        .addGap(30, 30, 30)
                        .addComponent(jLabel38)
                        .addGap(27, 27, 27)
                        .addComponent(jLabel39)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnlContagemdeCedulasLayout.createSequentialGroup()
                        .addGroup(pnlContagemdeCedulasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbContagemdeCedulas)
                            .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 486, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 8, Short.MAX_VALUE))))
        );
        pnlContagemdeCedulasLayout.setVerticalGroup(
            pnlContagemdeCedulasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContagemdeCedulasLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(lbContagemdeCedulas)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnlContagemdeCedulasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel39)
                    .addGroup(pnlContagemdeCedulasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbTotalemCedulas)
                        .addComponent(jLabel35)
                        .addComponent(jLabel38)))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jLabel33.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel33.setText("4.Outros Menos de Pagamentos");

        tbMeiosdePagamento.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Meio de pagamento", "Esperando (R$)", "Conferido (R$)", "Diferença (R$)"
            }
        ));
        jScrollPane16.setViewportView(tbMeiosdePagamento);

        lbMeiosdePagamento.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbMeiosdePagamento.setText("Total Outros Meios");

        lbPGEsperando.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbPGEsperando.setText("2,483,30");

        lbPGConferido.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbPGConferido.setText("2,483,30");

        lbPGDiferenca.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbPGDiferenca.setText("0,00");

        javax.swing.GroupLayout pnlMeiosdePagamentosLayout = new javax.swing.GroupLayout(pnlMeiosdePagamentos);
        pnlMeiosdePagamentos.setLayout(pnlMeiosdePagamentosLayout);
        pnlMeiosdePagamentosLayout.setHorizontalGroup(
            pnlMeiosdePagamentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMeiosdePagamentosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlMeiosdePagamentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlMeiosdePagamentosLayout.createSequentialGroup()
                        .addGroup(pnlMeiosdePagamentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane16)
                            .addGroup(pnlMeiosdePagamentosLayout.createSequentialGroup()
                                .addComponent(jLabel33)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(pnlMeiosdePagamentosLayout.createSequentialGroup()
                        .addComponent(lbMeiosdePagamento)
                        .addGap(32, 32, 32)
                        .addComponent(lbPGEsperando)
                        .addGap(66, 66, 66)
                        .addComponent(lbPGConferido)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbPGDiferenca)
                        .addGap(70, 70, 70))))
        );
        pnlMeiosdePagamentosLayout.setVerticalGroup(
            pnlMeiosdePagamentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMeiosdePagamentosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel33)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnlMeiosdePagamentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbMeiosdePagamento)
                    .addComponent(lbPGEsperando)
                    .addComponent(lbPGConferido)
                    .addComponent(lbPGDiferenca))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        tbqttdeMoedas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
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
                "Moeda", "Quantidade Contada ", "Valor Unitario (R$)", "Subtotal (R$)", "Esperando (R$)", "Diferença (R$)"
            }
        ));
        jScrollPane17.setViewportView(tbqttdeMoedas);

        lbContagemdeMoedas.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbContagemdeMoedas.setText("2.Contagem de Moedas");

        TotalqttdeMoedas.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        TotalqttdeMoedas.setText("Total em Moedas");

        lbTotalqttSubtotal.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbTotalqttSubtotal.setText("27,20");

        lbTotalqttEsperando.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbTotalqttEsperando.setText("26,70");

        lbTotalqttDiferenca.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbTotalqttDiferenca.setForeground(new java.awt.Color(0, 153, 0));
        lbTotalqttDiferenca.setText("+0,50");

        javax.swing.GroupLayout pnlContagemdeMoedasLayout = new javax.swing.GroupLayout(pnlContagemdeMoedas);
        pnlContagemdeMoedas.setLayout(pnlContagemdeMoedasLayout);
        pnlContagemdeMoedasLayout.setHorizontalGroup(
            pnlContagemdeMoedasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContagemdeMoedasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlContagemdeMoedasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlContagemdeMoedasLayout.createSequentialGroup()
                        .addComponent(TotalqttdeMoedas)
                        .addGap(286, 286, 286)
                        .addComponent(lbTotalqttSubtotal)
                        .addGap(85, 85, 85)
                        .addComponent(lbTotalqttEsperando)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 101, Short.MAX_VALUE)
                        .addComponent(lbTotalqttDiferenca)
                        .addGap(54, 54, 54))
                    .addGroup(pnlContagemdeMoedasLayout.createSequentialGroup()
                        .addComponent(lbContagemdeMoedas)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnlContagemdeMoedasLayout.createSequentialGroup()
                        .addComponent(jScrollPane17)
                        .addContainerGap())))
        );
        pnlContagemdeMoedasLayout.setVerticalGroup(
            pnlContagemdeMoedasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContagemdeMoedasLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(lbContagemdeMoedas)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane17, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnlContagemdeMoedasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TotalqttdeMoedas)
                    .addComponent(lbTotalqttSubtotal)
                    .addComponent(lbTotalqttEsperando)
                    .addComponent(lbTotalqttDiferenca))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        lbResumoGeralCinco.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbResumoGeralCinco.setText("5. RESUMO GERAL");

        tbResulmoGeral.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Descrição", "Esperado (R$)", "Conferido (R$)", "Diferencia (R$)"
            }
        ));
        jScrollPane23.setViewportView(tbResulmoGeral);

        javax.swing.GroupLayout pnlResulmoGeralLayout = new javax.swing.GroupLayout(pnlResulmoGeral);
        pnlResulmoGeral.setLayout(pnlResulmoGeralLayout);
        pnlResulmoGeralLayout.setHorizontalGroup(
            pnlResulmoGeralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlResulmoGeralLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(lbResumoGeralCinco))
            .addGroup(pnlResulmoGeralLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane23, javax.swing.GroupLayout.PREFERRED_SIZE, 474, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pnlResulmoGeralLayout.setVerticalGroup(
            pnlResulmoGeralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlResulmoGeralLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbResumoGeralCinco)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane23, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        lbResgistrodeDivergenias.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbResgistrodeDivergenias.setText("6. RESGISTRO DE DIVERGÊNCIAS");

        lbTipodeDivergencia.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbTipodeDivergencia.setText("Tipo de Diverências *");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel11.setText("Valor da divergência (R$)");

        jLabel12.setText("jLabel12");

        lbDescricaodeDivergencias.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbDescricaodeDivergencias.setText("Descrição de Divergência *");

        jLabel13.setText("jLabel13");

        javax.swing.GroupLayout pnlResgidtrodeDivergenciasLayout = new javax.swing.GroupLayout(pnlResgidtrodeDivergencias);
        pnlResgidtrodeDivergencias.setLayout(pnlResgidtrodeDivergenciasLayout);
        pnlResgidtrodeDivergenciasLayout.setHorizontalGroup(
            pnlResgidtrodeDivergenciasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlResgidtrodeDivergenciasLayout.createSequentialGroup()
                .addGroup(pnlResgidtrodeDivergenciasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlResgidtrodeDivergenciasLayout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(pnlResgidtrodeDivergenciasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(lbDescricaodeDivergencias)
                            .addComponent(jLabel13)))
                    .addGroup(pnlResgidtrodeDivergenciasLayout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(pnlResgidtrodeDivergenciasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(lbResgistrodeDivergenias)
                            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbTipodeDivergencia))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlResgidtrodeDivergenciasLayout.setVerticalGroup(
            pnlResgidtrodeDivergenciasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlResgidtrodeDivergenciasLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(lbResgistrodeDivergenias)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addComponent(lbTipodeDivergencia)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel12)
                .addGap(18, 18, 18)
                .addComponent(lbDescricaodeDivergencias, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel13)
                .addGap(28, 28, 28))
        );

        lbResumoDinheiro.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lbResumoDinheiro.setText("3. RESUMO-DINHEIRO");

        tbResumodeDinheiro.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Descrição", "Esperado (R$)", "Conferido (R$)", "Diferença (R$)"
            }
        ));
        jScrollPane24.setViewportView(tbResumodeDinheiro);

        javax.swing.GroupLayout pnlResumodeDinheiroLayout = new javax.swing.GroupLayout(pnlResumodeDinheiro);
        pnlResumodeDinheiro.setLayout(pnlResumodeDinheiroLayout);
        pnlResumodeDinheiroLayout.setHorizontalGroup(
            pnlResumodeDinheiroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlResumodeDinheiroLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlResumodeDinheiroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlResumodeDinheiroLayout.createSequentialGroup()
                        .addComponent(lbResumoDinheiro)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane24, javax.swing.GroupLayout.DEFAULT_SIZE, 514, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlResumodeDinheiroLayout.setVerticalGroup(
            pnlResumodeDinheiroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlResumodeDinheiroLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbResumoDinheiro)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane24, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lbChecktAprovacao.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbChecktAprovacao.setText("7. CHECKTLIST DE APROVAÇÃO");

        jRadioButton1.setText("jRadioButton1");

        javax.swing.GroupLayout pnlChetkdeAprovacaoLayout = new javax.swing.GroupLayout(pnlChetkdeAprovacao);
        pnlChetkdeAprovacao.setLayout(pnlChetkdeAprovacaoLayout);
        pnlChetkdeAprovacaoLayout.setHorizontalGroup(
            pnlChetkdeAprovacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlChetkdeAprovacaoLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(pnlChetkdeAprovacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jRadioButton1)
                    .addComponent(lbChecktAprovacao))
                .addContainerGap(283, Short.MAX_VALUE))
        );
        pnlChetkdeAprovacaoLayout.setVerticalGroup(
            pnlChetkdeAprovacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlChetkdeAprovacaoLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(lbChecktAprovacao)
                .addGap(27, 27, 27)
                .addComponent(jRadioButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlConferenciaTelasLayout = new javax.swing.GroupLayout(pnlConferenciaTelas);
        pnlConferenciaTelas.setLayout(pnlConferenciaTelasLayout);
        pnlConferenciaTelasLayout.setHorizontalGroup(
            pnlConferenciaTelasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlConferenciaTelasLayout.createSequentialGroup()
                .addGroup(pnlConferenciaTelasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlConferenciaTelasLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(pnlMeiosdePagamentos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(pnlContagemdeCedulas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlConferenciaTelasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlContagemdeMoedas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlConferenciaTelasLayout.createSequentialGroup()
                        .addComponent(pnlResulmoGeral, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnlResgidtrodeDivergencias, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlConferenciaTelasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlResumodeDinheiro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlChetkdeAprovacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 3021, Short.MAX_VALUE))
        );
        pnlConferenciaTelasLayout.setVerticalGroup(
            pnlConferenciaTelasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlConferenciaTelasLayout.createSequentialGroup()
                .addGroup(pnlConferenciaTelasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlConferenciaTelasLayout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(pnlResumodeDinheiro, javax.swing.GroupLayout.PREFERRED_SIZE, 362, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnlChetkdeAprovacao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnlConferenciaTelasLayout.createSequentialGroup()
                        .addGroup(pnlConferenciaTelasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pnlContagemdeCedulas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnlConferenciaTelasLayout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addComponent(pnlContagemdeMoedas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlConferenciaTelasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlConferenciaTelasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(pnlMeiosdePagamentos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(pnlResulmoGeral, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(pnlResgidtrodeDivergencias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 1520, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Conferência", pnlConferenciaTelas);

        lbSangriasdeCaixa.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbSangriasdeCaixa.setText("Sangrias de Caixa");

        lbSolicitacoes.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbSolicitacoes.setText("Solicitação");

        lbNumerodaS.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbNumerodaS.setText("Número da solicitação");

        lbCAIXA.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbCAIXA.setText("Caixa");

        cbxCaixaSangrias.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));

        lbDatadaS.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbDatadaS.setText("Data da solicitação");

        lbOperadorS.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbOperadorS.setText("Operador solicitante");

        lbMdaSangria.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbMdaSangria.setText("Motivo da sangria");

        cbxMotivoSangria.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        lbMotivo.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbMotivo.setText("Motivo*");

        lbObservacoesSangria.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbObservacoesSangria.setText("Observações (Opcional)");
        lbObservacoesSangria.setToolTipText("");

        jScrollPane6.setViewportView(txpObservaçoes);

        tbCEDULA.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "CÉDULA", "QUANTIDADE", "VALOR(R$)"
            }
        ));
        tbCedula.setViewportView(tbCEDULA);

        javax.swing.GroupLayout pnlMotivodaSLayout = new javax.swing.GroupLayout(pnlMotivodaS);
        pnlMotivodaS.setLayout(pnlMotivodaSLayout);
        pnlMotivodaSLayout.setHorizontalGroup(
            pnlMotivodaSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMotivodaSLayout.createSequentialGroup()
                .addGroup(pnlMotivodaSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlMotivodaSLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(pnlMotivodaSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbMdaSangria)
                            .addGroup(pnlMotivodaSLayout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addComponent(lbMotivo))))
                    .addGroup(pnlMotivodaSLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(tbCedula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlMotivodaSLayout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(pnlMotivodaSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbObservacoesSangria)
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbxMotivoSangria, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(43, Short.MAX_VALUE))
        );
        pnlMotivodaSLayout.setVerticalGroup(
            pnlMotivodaSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMotivodaSLayout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addComponent(lbMdaSangria)
                .addGap(26, 26, 26)
                .addComponent(lbMotivo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbxMotivoSangria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(lbObservacoesSangria)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tbCedula, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout pnlSolicitacaoLayout = new javax.swing.GroupLayout(pnlSolicitacao);
        pnlSolicitacao.setLayout(pnlSolicitacaoLayout);
        pnlSolicitacaoLayout.setHorizontalGroup(
            pnlSolicitacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSolicitacaoLayout.createSequentialGroup()
                .addGroup(pnlSolicitacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlSolicitacaoLayout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addGroup(pnlSolicitacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbSolicitacoes)
                            .addGroup(pnlSolicitacaoLayout.createSequentialGroup()
                                .addGroup(pnlSolicitacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbCAIXA)
                                    .addGroup(pnlSolicitacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(txfNumerodaSolicitacoes, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cbxCaixaSangrias, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(lbNumerodaS))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(pnlSolicitacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lbDatadaS)
                                    .addComponent(txfDatadaSolicitacao, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
                                    .addComponent(lbOperadorS)
                                    .addComponent(txfOperadorSolicitante)))))
                    .addGroup(pnlSolicitacaoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(pnlMotivodaS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlSolicitacaoLayout.setVerticalGroup(
            pnlSolicitacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSolicitacaoLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(lbSolicitacoes)
                .addGap(18, 18, 18)
                .addGroup(pnlSolicitacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbNumerodaS)
                    .addComponent(lbDatadaS))
                .addGap(11, 11, 11)
                .addGroup(pnlSolicitacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txfNumerodaSolicitacoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txfDatadaSolicitacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlSolicitacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbCAIXA)
                    .addComponent(lbOperadorS))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlSolicitacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbxCaixaSangrias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txfOperadorSolicitante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlMotivodaS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lbValores.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbValores.setText("Valores");

        lbValorS.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbValorS.setText("Valor solicitado (R$)*");

        jScrollPane8.setViewportView(txfValorSolicitado);

        lbValorR.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbValorR.setText("Valor retirado (R$)*");

        jScrollPane9.setViewportView(tpValorRetirado);

        javax.swing.GroupLayout pnlValoresLayout = new javax.swing.GroupLayout(pnlValores);
        pnlValores.setLayout(pnlValoresLayout);
        pnlValoresLayout.setHorizontalGroup(
            pnlValoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlValoresLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlValoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbValores)
                    .addComponent(lbValorS)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
                    .addComponent(lbValorR)
                    .addComponent(jScrollPane9))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlValoresLayout.setVerticalGroup(
            pnlValoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlValoresLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(lbValores)
                .addGap(18, 18, 18)
                .addComponent(lbValorS)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbValorR)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 16, Short.MAX_VALUE)
                .addGap(9, 9, 9))
        );

        lbAutorizacaoSangria.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbAutorizacaoSangria.setText("Autorização");

        cbxUautorizacao.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        lbUsuarioautorizacao.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbUsuarioautorizacao.setText("Usuario autorizador*");

        lbSenha.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbSenha.setText("Senha*");
        lbSenha.setToolTipText("");

        javax.swing.GroupLayout pnlAutorizacaoSangriasLayout = new javax.swing.GroupLayout(pnlAutorizacaoSangrias);
        pnlAutorizacaoSangrias.setLayout(pnlAutorizacaoSangriasLayout);
        pnlAutorizacaoSangriasLayout.setHorizontalGroup(
            pnlAutorizacaoSangriasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAutorizacaoSangriasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlAutorizacaoSangriasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbAutorizacaoSangria, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbUsuarioautorizacao)
                    .addComponent(cbxUautorizacao, 0, 360, Short.MAX_VALUE)
                    .addComponent(lbSenha)
                    .addComponent(txfSenha))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlAutorizacaoSangriasLayout.setVerticalGroup(
            pnlAutorizacaoSangriasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAutorizacaoSangriasLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(lbAutorizacaoSangria)
                .addGap(22, 22, 22)
                .addComponent(lbUsuarioautorizacao)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbxUautorizacao, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbSenha)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txfSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lbTotaldeM.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbTotaldeM.setText("TOTAL DE MOEDAS");

        tbMoedaQvalor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "MOEDA", "QUANTIDADE", "VALOR (R$)"
            }
        ));
        jScrollPane10.setViewportView(tbMoedaQvalor);

        javax.swing.GroupLayout pnlTotaldeMLayout = new javax.swing.GroupLayout(pnlTotaldeM);
        pnlTotaldeM.setLayout(pnlTotaldeMLayout);
        pnlTotaldeMLayout.setHorizontalGroup(
            pnlTotaldeMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTotaldeMLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbTotaldeM)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTotaldeMLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 442, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(282, 282, 282))
        );
        pnlTotaldeMLayout.setVerticalGroup(
            pnlTotaldeMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTotaldeMLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbTotaldeM)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lbComprovante.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbComprovante.setText("Comporvante");

        lbCOMPROVANTE.setIcon(new javax.swing.ImageIcon("C:\\Users\\PedroLeite\\Documents\\ProjetoPI_Supermercado\\GerenciamentoSupermercado\\src\\main\\java\\com\\mycompany\\gerenciamentosupermercado\\recorte_supermercado_bom_preco (1).png")); // NOI18N

        btnEcomprovante.setBackground(new java.awt.Color(0, 0, 0));
        btnEcomprovante.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnEcomprovante.setForeground(new java.awt.Color(255, 255, 255));
        btnEcomprovante.setText("Emetir comprovante");

        btnComprovante.setIcon(new javax.swing.ImageIcon("C:\\Users\\PedroLeite\\Documents\\ProjetoPI_Supermercado\\GerenciamentoSupermercado\\src\\main\\java\\com\\mycompany\\gerenciamentosupermercado\\impressora.png")); // NOI18N

        btnVcomprovante.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnVcomprovante.setText("Vizualizar Comprovante");

        btnVizualizarC.setIcon(new javax.swing.ImageIcon("C:\\Users\\PedroLeite\\Documents\\ProjetoPI_Supermercado\\GerenciamentoSupermercado\\src\\main\\java\\com\\mycompany\\gerenciamentosupermercado\\olho (1).png")); // NOI18N

        javax.swing.GroupLayout pnlComprovanteLayout = new javax.swing.GroupLayout(pnlComprovante);
        pnlComprovante.setLayout(pnlComprovanteLayout);
        pnlComprovanteLayout.setHorizontalGroup(
            pnlComprovanteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlComprovanteLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbComprovante)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlComprovanteLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlComprovanteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnComprovante, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                    .addComponent(btnVizualizarC, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlComprovanteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnEcomprovante, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnVcomprovante, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(507, 507, 507))
            .addGroup(pnlComprovanteLayout.createSequentialGroup()
                .addComponent(lbCOMPROVANTE, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        pnlComprovanteLayout.setVerticalGroup(
            pnlComprovanteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlComprovanteLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(lbComprovante)
                .addGap(27, 27, 27)
                .addComponent(lbCOMPROVANTE, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlComprovanteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnEcomprovante, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnComprovante, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlComprovanteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnVcomprovante, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlComprovanteLayout.createSequentialGroup()
                        .addComponent(btnVizualizarC, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(2, 2, 2)))
                .addContainerGap(2, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlSangriasTelasLayout = new javax.swing.GroupLayout(pnlSangriasTelas);
        pnlSangriasTelas.setLayout(pnlSangriasTelasLayout);
        pnlSangriasTelasLayout.setHorizontalGroup(
            pnlSangriasTelasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSangriasTelasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlSolicitacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(pnlSangriasTelasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlSangriasTelasLayout.createSequentialGroup()
                        .addGroup(pnlSangriasTelasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlSangriasTelasLayout.createSequentialGroup()
                                .addGap(33, 33, 33)
                                .addComponent(lbSangriasdeCaixa))
                            .addGroup(pnlSangriasTelasLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(pnlValores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(pnlAutorizacaoSangrias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlSangriasTelasLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(pnlTotaldeM, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(pnlComprovante, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(3492, Short.MAX_VALUE))
        );
        pnlSangriasTelasLayout.setVerticalGroup(
            pnlSangriasTelasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSangriasTelasLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(pnlSangriasTelasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlSangriasTelasLayout.createSequentialGroup()
                        .addGroup(pnlSangriasTelasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(pnlSangriasTelasLayout.createSequentialGroup()
                                .addComponent(lbSangriasdeCaixa)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(pnlValores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(pnlAutorizacaoSangrias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(pnlSangriasTelasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pnlTotaldeM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pnlComprovante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(pnlSolicitacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(1530, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Sangrias", pnlSangriasTelas);

        lbFiltros.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbFiltros.setText("Filtros");

        lbLoja.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbLoja.setText("Loja");

        cbxLoja.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));

        lbDATA.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbDATA.setText("Data");

        try {
            ffData.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        lbcalendario.setIcon(new javax.swing.ImageIcon("C:\\Users\\PedroLeite\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\calendar.png")); // NOI18N

        lbStatusdeCaixa.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbStatusdeCaixa.setText("Status do Caixa");

        cbxStatusdeCaixa.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));
        cbxStatusdeCaixa.addActionListener(this::cbxStatusdeCaixaActionPerformed);

        lbPeriodo.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbPeriodo.setText("Periodo");

        cbxPeriodo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));
        cbxPeriodo.addActionListener(this::cbxPeriodoActionPerformed);

        lbTurno.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbTurno.setText("Turnos");

        cbxTurnos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));

        lbCaixas.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbCaixas.setText("Caixa");

        cbxCAIXAS.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));

        btnLimparFLT.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnLimparFLT.setIcon(new javax.swing.ImageIcon("C:\\Users\\PedroLeite\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\cleaning.png")); // NOI18N
        btnLimparFLT.setText("Limpar Filtros");

        btnAplicarFLT.setBackground(new java.awt.Color(0, 0, 0));
        btnAplicarFLT.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnAplicarFLT.setForeground(new java.awt.Color(255, 255, 255));
        btnAplicarFLT.setText("Aplicar Filtros");

        javax.swing.GroupLayout pnlFiltrosLayout = new javax.swing.GroupLayout(pnlFiltros);
        pnlFiltros.setLayout(pnlFiltrosLayout);
        pnlFiltrosLayout.setHorizontalGroup(
            pnlFiltrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFiltrosLayout.createSequentialGroup()
                .addGroup(pnlFiltrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cbxLoja, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlFiltrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnlFiltrosLayout.createSequentialGroup()
                            .addGap(14, 14, 14)
                            .addComponent(lbFiltros))
                        .addGroup(pnlFiltrosLayout.createSequentialGroup()
                            .addGroup(pnlFiltrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(pnlFiltrosLayout.createSequentialGroup()
                                    .addGap(24, 24, 24)
                                    .addGroup(pnlFiltrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lbLoja)
                                        .addComponent(lbDATA))
                                    .addGap(130, 130, 130))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlFiltrosLayout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(ffData, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                            .addComponent(lbcalendario, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(30, 30, 30)
                .addGroup(pnlFiltrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbStatusdeCaixa)
                    .addComponent(cbxStatusdeCaixa, 0, 150, Short.MAX_VALUE)
                    .addComponent(lbPeriodo)
                    .addComponent(cbxPeriodo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(pnlFiltrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlFiltrosLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(pnlFiltrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlFiltrosLayout.createSequentialGroup()
                                .addComponent(lbTurno)
                                .addGap(129, 129, 129)
                                .addComponent(lbCaixas))
                            .addGroup(pnlFiltrosLayout.createSequentialGroup()
                                .addComponent(cbxTurnos, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(cbxCAIXAS, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(pnlFiltrosLayout.createSequentialGroup()
                        .addGap(134, 134, 134)
                        .addComponent(btnLimparFLT)
                        .addGap(18, 18, 18)
                        .addComponent(btnAplicarFLT, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlFiltrosLayout.setVerticalGroup(
            pnlFiltrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFiltrosLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(lbFiltros)
                .addGap(18, 18, 18)
                .addGroup(pnlFiltrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbLoja)
                    .addComponent(lbStatusdeCaixa)
                    .addComponent(lbTurno)
                    .addComponent(lbCaixas))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlFiltrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cbxCAIXAS)
                    .addGroup(pnlFiltrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(cbxStatusdeCaixa, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbxTurnos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbxLoja, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlFiltrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbDATA)
                    .addComponent(lbPeriodo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlFiltrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnlFiltrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cbxPeriodo, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                        .addComponent(btnLimparFLT)
                        .addComponent(btnAplicarFLT, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lbcalendario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ffData))
                .addContainerGap(41, Short.MAX_VALUE))
        );

        tbCaixas.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        tbCaixas.setModel(new javax.swing.table.DefaultTableModel(
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
                "CAIXAS", "Frente / Setor", "Status", "Operador", "Abertura", "Duração", "Ùltima Atualização", "Ações"
            }
        ));
        jScrollPane20.setViewportView(tbCaixas);

        lbCaixasAbertosFechados.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbCaixasAbertosFechados.setText("Caixas");

        javax.swing.GroupLayout pnlCaixaLayout = new javax.swing.GroupLayout(pnlCaixa);
        pnlCaixa.setLayout(pnlCaixaLayout);
        pnlCaixaLayout.setHorizontalGroup(
            pnlCaixaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCaixaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane20, javax.swing.GroupLayout.DEFAULT_SIZE, 799, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(pnlCaixaLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(lbCaixasAbertosFechados)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlCaixaLayout.setVerticalGroup(
            pnlCaixaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCaixaLayout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(lbCaixasAbertosFechados)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane20, javax.swing.GroupLayout.DEFAULT_SIZE, 409, Short.MAX_VALUE)
                .addContainerGap())
        );

        lbDetalhesdoCaixa.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbDetalhesdoCaixa.setText("Detalhes do Caixa");

        lbCaixadeDetalhes.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbCaixadeDetalhes.setText("Caixa");

        lbCaixasdosDetalhes.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbCaixasdosDetalhes.setText("Caixas 001");

        lbStatusDetalhes.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbStatusDetalhes.setText("Status");

        lbAberto.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbAberto.setText("Abertos");

        lbHora.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbHora.setText("6h30");

        lbTurnosDetalhes.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbTurnosDetalhes.setText("Turnos");

        lbManha.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbManha.setText("Manhã");

        lbUltimaAtualizacao.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbUltimaAtualizacao.setText("Ùltima Atualização");

        lbDataDetalhes.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbDataDetalhes.setText("31/05/2025 14:30");

        lbObservacoesdeDetalhes.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbObservacoesdeDetalhes.setText("Observações");

        lbFrenteSetor.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbFrenteSetor.setText("Frente / Setor");

        lbFrentedeLoja.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbFrentedeLoja.setText("Frente de Loja");

        lbOperadorDetalhes.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbOperadorDetalhes.setText("Operador");

        lbNomedoOperado.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbNomedoOperado.setText("Maria Silva");

        lbAbertura.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbAbertura.setText("Abertura");

        lbDatadeAbertura.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbDatadeAbertura.setText("31/05/2025 08:00");

        lbUltimaData.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbUltimaData.setText("Ultima Data");

        lbUltimodia.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbUltimodia.setText("31/05/2025 14:00");

        lbValordaCaixa.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbValordaCaixa.setText("Valor em Caixa");

        lbValordoCaixa.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbValordoCaixa.setText("R$ 2.350.00");

        rbAberto.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        rbAberto.setText("Aberto");

        javax.swing.GroupLayout pnlDetalhesdoCaixaLayout = new javax.swing.GroupLayout(pnlDetalhesdoCaixa);
        pnlDetalhesdoCaixa.setLayout(pnlDetalhesdoCaixaLayout);
        pnlDetalhesdoCaixaLayout.setHorizontalGroup(
            pnlDetalhesdoCaixaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDetalhesdoCaixaLayout.createSequentialGroup()
                .addGroup(pnlDetalhesdoCaixaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlDetalhesdoCaixaLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lbDetalhesdoCaixa))
                    .addGroup(pnlDetalhesdoCaixaLayout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(pnlDetalhesdoCaixaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlDetalhesdoCaixaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lbCaixadeDetalhes)
                                .addComponent(lbStatusDetalhes)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDetalhesdoCaixaLayout.createSequentialGroup()
                                    .addComponent(lbUltimaAtualizacao)
                                    .addGap(21, 21, 21))
                                .addComponent(lbTurnosDetalhes)
                                .addComponent(lbAberto)
                                .addGroup(pnlDetalhesdoCaixaLayout.createSequentialGroup()
                                    .addGap(6, 6, 6)
                                    .addGroup(pnlDetalhesdoCaixaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(pnlDetalhesdoCaixaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lbManha)
                                            .addComponent(lbCaixasdosDetalhes, javax.swing.GroupLayout.Alignment.TRAILING))
                                        .addComponent(lbHora))))
                            .addGroup(pnlDetalhesdoCaixaLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(lbDataDetalhes))
                            .addComponent(lbObservacoesdeDetalhes)
                            .addComponent(rbAberto))
                        .addGap(66, 66, 66)
                        .addGroup(pnlDetalhesdoCaixaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbFrenteSetor)
                            .addComponent(lbOperadorDetalhes)
                            .addComponent(lbAbertura)
                            .addComponent(lbUltimaData)
                            .addComponent(lbValordaCaixa)
                            .addGroup(pnlDetalhesdoCaixaLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(pnlDetalhesdoCaixaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbValordoCaixa)
                                    .addComponent(lbUltimodia)
                                    .addComponent(lbDatadeAbertura)
                                    .addComponent(lbFrentedeLoja)
                                    .addComponent(lbNomedoOperado))))))
                .addContainerGap(230, Short.MAX_VALUE))
        );
        pnlDetalhesdoCaixaLayout.setVerticalGroup(
            pnlDetalhesdoCaixaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDetalhesdoCaixaLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(lbDetalhesdoCaixa)
                .addGap(45, 45, 45)
                .addGroup(pnlDetalhesdoCaixaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbCaixadeDetalhes)
                    .addComponent(lbFrenteSetor))
                .addGroup(pnlDetalhesdoCaixaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlDetalhesdoCaixaLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(lbCaixasdosDetalhes))
                    .addGroup(pnlDetalhesdoCaixaLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(lbFrentedeLoja)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlDetalhesdoCaixaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbStatusDetalhes)
                    .addComponent(lbOperadorDetalhes))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlDetalhesdoCaixaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbNomedoOperado)
                    .addComponent(rbAberto))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlDetalhesdoCaixaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbAberto)
                    .addComponent(lbAbertura))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlDetalhesdoCaixaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbDatadeAbertura)
                    .addComponent(lbHora))
                .addGap(18, 18, 18)
                .addGroup(pnlDetalhesdoCaixaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbTurnosDetalhes)
                    .addComponent(lbValordaCaixa))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlDetalhesdoCaixaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbManha)
                    .addComponent(lbValordoCaixa))
                .addGap(18, 18, 18)
                .addGroup(pnlDetalhesdoCaixaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbUltimaAtualizacao)
                    .addComponent(lbUltimaData))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlDetalhesdoCaixaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbUltimodia)
                    .addComponent(lbDataDetalhes))
                .addGap(18, 18, 18)
                .addComponent(lbObservacoesdeDetalhes)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        lbAcoesRapidas.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbAcoesRapidas.setText("Ações Rapidas");

        btnAbrirCaixa.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnAbrirCaixa.setText("Abrir Caixa");

        btnFecharCaixa.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnFecharCaixa.setText("Fechar caixa");

        btnPausarCaixa.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnPausarCaixa.setText("Pausar caixa");

        btnEncerrarPausar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnEncerrarPausar.setText("Encerrar pausar");

        btnIniciarConferecia.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnIniciarConferecia.setText("Iniciar conferencia");

        btnCancelarConferencia.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnCancelarConferencia.setText("Cancelar conferencia");

        btnImprimirResumo.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnImprimirResumo.setText("Imprimir resumo");

        btnTransferirOperador.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnTransferirOperador.setText("Transferir operador");

        javax.swing.GroupLayout pnlAcoesRapidaLayout = new javax.swing.GroupLayout(pnlAcoesRapida);
        pnlAcoesRapida.setLayout(pnlAcoesRapidaLayout);
        pnlAcoesRapidaLayout.setHorizontalGroup(
            pnlAcoesRapidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlAcoesRapidaLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlAcoesRapidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnTransferirOperador, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAbrirCaixa, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnIniciarConferecia, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPausarCaixa, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlAcoesRapidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnImprimirResumo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnFecharCaixa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEncerrarPausar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCancelarConferencia, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE))
                .addGap(26, 26, 26))
            .addGroup(pnlAcoesRapidaLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(lbAcoesRapidas)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlAcoesRapidaLayout.setVerticalGroup(
            pnlAcoesRapidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAcoesRapidaLayout.createSequentialGroup()
                .addGroup(pnlAcoesRapidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlAcoesRapidaLayout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addComponent(btnFecharCaixa, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                        .addGap(33, 33, 33)
                        .addComponent(btnEncerrarPausar, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                        .addGap(33, 33, 33)
                        .addComponent(btnCancelarConferencia, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(btnImprimirResumo, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlAcoesRapidaLayout.createSequentialGroup()
                        .addComponent(lbAcoesRapidas)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAbrirCaixa, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(btnPausarCaixa, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(btnIniciarConferecia, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)))
                .addComponent(btnTransferirOperador, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
                .addGap(12, 12, 12))
        );

        lbHistoricoAlteracoes.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbHistoricoAlteracoes.setText("Historíco de Alterações");

        tbHistoricodeAlteracoes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "DATA / HORA", "Usuario", "Ação", "Detalhes"
            }
        ));
        jScrollPane21.setViewportView(tbHistoricodeAlteracoes);

        javax.swing.GroupLayout pnlHistoricodeAlteracoesLayout = new javax.swing.GroupLayout(pnlHistoricodeAlteracoes);
        pnlHistoricodeAlteracoes.setLayout(pnlHistoricodeAlteracoesLayout);
        pnlHistoricodeAlteracoesLayout.setHorizontalGroup(
            pnlHistoricodeAlteracoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHistoricodeAlteracoesLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lbHistoricoAlteracoes)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlHistoricodeAlteracoesLayout.createSequentialGroup()
                .addContainerGap(13, Short.MAX_VALUE)
                .addComponent(jScrollPane21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnlHistoricodeAlteracoesLayout.setVerticalGroup(
            pnlHistoricodeAlteracoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHistoricodeAlteracoesLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(lbHistoricoAlteracoes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane21, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
                "Caixa", "Frente / Setor", "Status", "Operador", "Abertura", "Duração", "Ultima Atualização", "Ações"
            }
        ));
        jScrollPane22.setViewportView(jTable1);

        lbCAIXAS.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbCAIXAS.setText("Caixa");

        javax.swing.GroupLayout pnlTabelasCaixaLayout = new javax.swing.GroupLayout(pnlTabelasCaixa);
        pnlTabelasCaixa.setLayout(pnlTabelasCaixaLayout);
        pnlTabelasCaixaLayout.setHorizontalGroup(
            pnlTabelasCaixaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTabelasCaixaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane22)
                .addContainerGap())
            .addGroup(pnlTabelasCaixaLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(lbCAIXAS)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlTabelasCaixaLayout.setVerticalGroup(
            pnlTabelasCaixaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTabelasCaixaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbCAIXAS)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout pnlCaixasAeFTelasLayout = new javax.swing.GroupLayout(pnlCaixasAeFTelas);
        pnlCaixasAeFTelas.setLayout(pnlCaixasAeFTelasLayout);
        pnlCaixasAeFTelasLayout.setHorizontalGroup(
            pnlCaixasAeFTelasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCaixasAeFTelasLayout.createSequentialGroup()
                .addGroup(pnlCaixasAeFTelasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlFiltros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlCaixasAeFTelasLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(pnlCaixasAeFTelasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(pnlCaixasAeFTelasLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(pnlTabelasCaixa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(pnlCaixa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlCaixasAeFTelasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlDetalhesdoCaixa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlCaixasAeFTelasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(pnlHistoricodeAlteracoes, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(pnlAcoesRapida, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(3407, Short.MAX_VALUE))
        );
        pnlCaixasAeFTelasLayout.setVerticalGroup(
            pnlCaixasAeFTelasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCaixasAeFTelasLayout.createSequentialGroup()
                .addComponent(pnlDetalhesdoCaixa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlAcoesRapida, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(pnlHistoricodeAlteracoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1133, 1133, 1133))
            .addGroup(pnlCaixasAeFTelasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlFiltros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlCaixa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlTabelasCaixa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Caixa Abertos/Fechados", pnlCaixasAeFTelas);

        lbTotalaReceber.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbTotalaReceber.setText("Total a Receber:");

        lbOperacaodeDinheoC.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbOperacaodeDinheoC.setText("Operação de dinheiro-Caixa 01 Frente");

        lbTotalrecebido.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbTotalrecebido.setText("Total Recebido:");

        javax.swing.GroupLayout pnlTotalrecebidoLayout = new javax.swing.GroupLayout(pnlTotalrecebido);
        pnlTotalrecebido.setLayout(pnlTotalrecebidoLayout);
        pnlTotalrecebidoLayout.setHorizontalGroup(
            pnlTotalrecebidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTotalrecebidoLayout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(lbTotalrecebido, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txfTotalrecebido, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );
        pnlTotalrecebidoLayout.setVerticalGroup(
            pnlTotalrecebidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTotalrecebidoLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(pnlTotalrecebidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txfTotalrecebido, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbTotalrecebido))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlOPERADORESLayout = new javax.swing.GroupLayout(pnlOPERADORES);
        pnlOPERADORES.setLayout(pnlOPERADORESLayout);
        pnlOPERADORESLayout.setHorizontalGroup(
            pnlOPERADORESLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlOPERADORESLayout.createSequentialGroup()
                .addGroup(pnlOPERADORESLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlOPERADORESLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(lbTotalaReceber, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txfTotalaReceberOperadores, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lbOperacaodeDinheoC))
                .addGap(18, 18, 18)
                .addComponent(pnlTotalrecebido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        pnlOPERADORESLayout.setVerticalGroup(
            pnlOPERADORESLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlOPERADORESLayout.createSequentialGroup()
                .addComponent(lbOperacaodeDinheoC, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(pnlOPERADORESLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlOPERADORESLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txfTotalaReceberOperadores, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35))
                    .addGroup(pnlOPERADORESLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(lbTotalaReceber)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addComponent(pnlTotalrecebido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        lbTroco.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbTroco.setText("Troco:");

        btnImagemTroco.setIcon(new javax.swing.ImageIcon("C:\\Users\\PedroLeite\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\coin.png")); // NOI18N
        btnImagemTroco.setText("R$:");

        javax.swing.GroupLayout pnlTrocoLayout = new javax.swing.GroupLayout(pnlTroco);
        pnlTroco.setLayout(pnlTrocoLayout);
        pnlTrocoLayout.setHorizontalGroup(
            pnlTrocoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTrocoLayout.createSequentialGroup()
                .addGroup(pnlTrocoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlTrocoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnImagemTroco)
                        .addGap(18, 18, 18)
                        .addComponent(txfTrocoOperadores, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlTrocoLayout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(lbTroco)))
                .addContainerGap(64, Short.MAX_VALUE))
        );
        pnlTrocoLayout.setVerticalGroup(
            pnlTrocoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTrocoLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(lbTroco)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlTrocoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txfTrocoOperadores)
                    .addComponent(btnImagemTroco, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        lbinformacoesdaVenda.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbinformacoesdaVenda.setText("Informações da Venda");

        lbOperador.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbOperador.setText("Operador");

        lbData.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbData.setText("Data");

        lbVenda.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbVenda.setText("Venda");

        lbSubtotal.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbSubtotal.setText("Subtotal");

        lbDescontos.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbDescontos.setText("Descontos");

        lbTotaldaVenda.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbTotaldaVenda.setText("Total da Venda :");

        lbValorTotal.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbValorTotal.setForeground(new java.awt.Color(0, 204, 0));
        lbValorTotal.setText("R$: 145,00");

        lbNomedoOperador.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbNomedoOperador.setText("João da Silva");

        lbDatadaVenda.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbDatadaVenda.setText("26/05/2026 15:42");

        lbValoresdeVenda.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbValoresdeVenda.setText("#0001258");

        lbValorSubtotal.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbValorSubtotal.setText("R$ 150,00");
        lbValorSubtotal.setToolTipText("");

        lbValordoDesconto.setBackground(new java.awt.Color(255, 255, 255));
        lbValordoDesconto.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbValordoDesconto.setForeground(new java.awt.Color(255, 0, 0));
        lbValordoDesconto.setText("- R$ 5,00");

        tbMoedasValor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Valor", "Quantiade", "Total"
            }
        ));
        jScrollPane7.setViewportView(tbMoedasValor);

        tbValorqtt.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Valor", "Quantidade", "Total"
            }
        ));
        jScrollPane11.setViewportView(tbValorqtt);

        lbCedulas.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbCedulas.setText("Cédulas");

        lbAcoesRepidas.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbAcoesRepidas.setText("Ações Rápidas");

        btnSangria.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnSangria.setText("Sangria");

        btnCoferencia.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnCoferencia.setText("Conferência");

        btnSuprimento.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnSuprimento.setText("Suprimento");

        btnHistorico.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnHistorico.setText("Histórico");

        javax.swing.GroupLayout pnlAcoesRapidasLayout = new javax.swing.GroupLayout(pnlAcoesRapidas);
        pnlAcoesRapidas.setLayout(pnlAcoesRapidasLayout);
        pnlAcoesRapidasLayout.setHorizontalGroup(
            pnlAcoesRapidasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAcoesRapidasLayout.createSequentialGroup()
                .addGroup(pnlAcoesRapidasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlAcoesRapidasLayout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(lbAcoesRepidas))
                    .addGroup(pnlAcoesRapidasLayout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(pnlAcoesRapidasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnSangria, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnCoferencia, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlAcoesRapidasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnHistorico, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSuprimento, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlAcoesRapidasLayout.setVerticalGroup(
            pnlAcoesRapidasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlAcoesRapidasLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(lbAcoesRepidas)
                .addGap(18, 18, 18)
                .addGroup(pnlAcoesRapidasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSangria, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSuprimento, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(pnlAcoesRapidasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnHistorico, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCoferencia, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE))
                .addGap(30, 30, 30))
        );

        javax.swing.GroupLayout pnlCedulasLayout = new javax.swing.GroupLayout(pnlCedulas);
        pnlCedulas.setLayout(pnlCedulasLayout);
        pnlCedulasLayout.setHorizontalGroup(
            pnlCedulasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCedulasLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(pnlCedulasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlCedulasLayout.createSequentialGroup()
                        .addComponent(lbCedulas)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane11)
                    .addComponent(pnlAcoesRapidas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlCedulasLayout.setVerticalGroup(
            pnlCedulasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCedulasLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(pnlCedulasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlCedulasLayout.createSequentialGroup()
                        .addComponent(lbCedulas)
                        .addGap(325, 325, 325)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlAcoesRapidas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlCedulasOperadoresLayout = new javax.swing.GroupLayout(pnlCedulasOperadores);
        pnlCedulasOperadores.setLayout(pnlCedulasOperadoresLayout);
        pnlCedulasOperadoresLayout.setHorizontalGroup(
            pnlCedulasOperadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCedulasOperadoresLayout.createSequentialGroup()
                .addComponent(jScrollPane7)
                .addContainerGap())
            .addGroup(pnlCedulasOperadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlCedulasOperadoresLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(pnlCedulas, javax.swing.GroupLayout.PREFERRED_SIZE, 454, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        pnlCedulasOperadoresLayout.setVerticalGroup(
            pnlCedulasOperadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCedulasOperadoresLayout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlCedulasOperadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlCedulasOperadoresLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(pnlCedulas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout pnlInformacoesdaVendaLayout = new javax.swing.GroupLayout(pnlInformacoesdaVenda);
        pnlInformacoesdaVenda.setLayout(pnlInformacoesdaVendaLayout);
        pnlInformacoesdaVendaLayout.setHorizontalGroup(
            pnlInformacoesdaVendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInformacoesdaVendaLayout.createSequentialGroup()
                .addGroup(pnlInformacoesdaVendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlInformacoesdaVendaLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lbinformacoesdaVenda))
                    .addGroup(pnlInformacoesdaVendaLayout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(pnlInformacoesdaVendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(pnlInformacoesdaVendaLayout.createSequentialGroup()
                                .addComponent(lbVenda)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lbValoresdeVenda))
                            .addGroup(pnlInformacoesdaVendaLayout.createSequentialGroup()
                                .addComponent(lbData)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lbDatadaVenda))
                            .addGroup(pnlInformacoesdaVendaLayout.createSequentialGroup()
                                .addComponent(lbTotaldaVenda)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lbValorTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlInformacoesdaVendaLayout.createSequentialGroup()
                                .addComponent(lbOperador)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lbNomedoOperador))
                            .addGroup(pnlInformacoesdaVendaLayout.createSequentialGroup()
                                .addGroup(pnlInformacoesdaVendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbSubtotal)
                                    .addComponent(lbDescontos))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(pnlInformacoesdaVendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lbValorSubtotal)
                                    .addComponent(lbValordoDesconto))))
                        .addGap(10, 10, 10)))
                .addGap(28, 28, 28))
            .addGroup(pnlInformacoesdaVendaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlCedulasOperadores, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlInformacoesdaVendaLayout.setVerticalGroup(
            pnlInformacoesdaVendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInformacoesdaVendaLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(lbinformacoesdaVenda)
                .addGap(30, 30, 30)
                .addGroup(pnlInformacoesdaVendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbOperador)
                    .addComponent(lbNomedoOperador))
                .addGap(18, 18, 18)
                .addGroup(pnlInformacoesdaVendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbData)
                    .addComponent(lbDatadaVenda))
                .addGap(28, 28, 28)
                .addGroup(pnlInformacoesdaVendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbVenda)
                    .addComponent(lbValoresdeVenda))
                .addGap(18, 18, 18)
                .addGroup(pnlInformacoesdaVendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbSubtotal)
                    .addComponent(lbValorSubtotal))
                .addGap(30, 30, 30)
                .addGroup(pnlInformacoesdaVendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbDescontos)
                    .addComponent(lbValordoDesconto))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlInformacoesdaVendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbTotaldaVenda)
                    .addComponent(lbValorTotal))
                .addGap(18, 18, 18)
                .addComponent(pnlCedulasOperadores, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        lbRecebimento.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbRecebimento.setText("Recebimento");

        lbValorqttRecebido.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbValorqttRecebido.setText("Valor recebido");

        btnImagemValorRecebido.setIcon(new javax.swing.ImageIcon("C:\\Users\\PedroLeite\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\money-bag.png")); // NOI18N

        lbTrocoCalculado.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbTrocoCalculado.setText("Troco Calculado");

        lbValordoTroco.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbValordoTroco.setForeground(new java.awt.Color(0, 204, 0));
        lbValordoTroco.setText("R$ : 0,00");

        javax.swing.GroupLayout pnlRecebimentoLayout = new javax.swing.GroupLayout(pnlRecebimento);
        pnlRecebimento.setLayout(pnlRecebimentoLayout);
        pnlRecebimentoLayout.setHorizontalGroup(
            pnlRecebimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRecebimentoLayout.createSequentialGroup()
                .addGroup(pnlRecebimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnlRecebimentoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(pnlRecebimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lbRecebimento)
                            .addComponent(lbValorqttRecebido)))
                    .addGroup(pnlRecebimentoLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(btnImagemValorRecebido, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txfValorTotalRecebido))
                    .addGroup(pnlRecebimentoLayout.createSequentialGroup()
                        .addGap(186, 186, 186)
                        .addComponent(lbTrocoCalculado))
                    .addGroup(pnlRecebimentoLayout.createSequentialGroup()
                        .addGap(201, 201, 201)
                        .addComponent(lbValordoTroco)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlRecebimentoLayout.setVerticalGroup(
            pnlRecebimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRecebimentoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbRecebimento)
                .addGap(43, 43, 43)
                .addComponent(lbValorqttRecebido)
                .addGap(18, 18, 18)
                .addGroup(pnlRecebimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txfValorTotalRecebido)
                    .addComponent(btnImagemValorRecebido, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 71, Short.MAX_VALUE)
                .addComponent(lbTrocoCalculado)
                .addGap(18, 18, 18)
                .addComponent(lbValordoTroco)
                .addGap(33, 33, 33))
        );

        lbResumodoRecebimento.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbResumodoRecebimento.setText("Resumo do recebimento");

        lbTotaldeCedulas.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbTotaldeCedulas.setText("Total de cédulas");

        lbValorTotalMoedas.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbValorTotalMoedas.setText("Total em Moedas");

        lbTotalRecebido.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbTotalRecebido.setText("Total recebido");

        lbTrocoRecebido.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbTrocoRecebido.setText("Troco");

        btnRegularTroco.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnRegularTroco.setIcon(new javax.swing.ImageIcon("C:\\Users\\PedroLeite\\Documents\\ProjetoPI_Supermercado\\GerenciamentoSupermercado\\src\\main\\java\\com\\mycompany\\gerenciamentosupermercado\\back-arrow.png")); // NOI18N
        btnRegularTroco.setText("Recalcular Troco");

        javax.swing.GroupLayout pnlResumodoRecebimentoLayout = new javax.swing.GroupLayout(pnlResumodoRecebimento);
        pnlResumodoRecebimento.setLayout(pnlResumodoRecebimentoLayout);
        pnlResumodoRecebimentoLayout.setHorizontalGroup(
            pnlResumodoRecebimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlResumodoRecebimentoLayout.createSequentialGroup()
                .addGroup(pnlResumodoRecebimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlResumodoRecebimentoLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(pnlResumodoRecebimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbTrocoRecebido)
                            .addComponent(lbTotalRecebido)
                            .addComponent(lbValorTotalMoedas)
                            .addComponent(lbTotaldeCedulas)
                            .addComponent(lbResumodoRecebimento)))
                    .addGroup(pnlResumodoRecebimentoLayout.createSequentialGroup()
                        .addGap(131, 131, 131)
                        .addComponent(btnRegularTroco)))
                .addContainerGap(70, Short.MAX_VALUE))
        );
        pnlResumodoRecebimentoLayout.setVerticalGroup(
            pnlResumodoRecebimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlResumodoRecebimentoLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lbResumodoRecebimento)
                .addGap(31, 31, 31)
                .addComponent(lbTotaldeCedulas)
                .addGap(18, 18, 18)
                .addComponent(lbValorTotalMoedas)
                .addGap(42, 42, 42)
                .addComponent(lbTotalRecebido)
                .addGap(38, 38, 38)
                .addComponent(lbTrocoRecebido)
                .addGap(18, 18, 18)
                .addComponent(btnRegularTroco, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        lbMoedas.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbMoedas.setText("Moedas");

        tbValorqttMoeda.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Valor", "Quantidade", "Total"
            }
        ));
        tbValorqttMoedas.setViewportView(tbValorqttMoeda);

        javax.swing.GroupLayout pnlMoedasLayout = new javax.swing.GroupLayout(pnlMoedas);
        pnlMoedas.setLayout(pnlMoedasLayout);
        pnlMoedasLayout.setHorizontalGroup(
            pnlMoedasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMoedasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlMoedasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlMoedasLayout.createSequentialGroup()
                        .addComponent(lbMoedas)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(tbValorqttMoedas, javax.swing.GroupLayout.DEFAULT_SIZE, 416, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlMoedasLayout.setVerticalGroup(
            pnlMoedasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMoedasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbMoedas)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tbValorqttMoedas, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        tbCedulasdeOperadores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Valor", "Quantidade", "Total"
            }
        ));
        jScrollPane18.setViewportView(tbCedulasdeOperadores);

        lbCedula.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbCedula.setText("Cédulas");

        javax.swing.GroupLayout pnlTabelasdeCedulasLayout = new javax.swing.GroupLayout(pnlTabelasdeCedulas);
        pnlTabelasdeCedulas.setLayout(pnlTabelasdeCedulasLayout);
        pnlTabelasdeCedulasLayout.setHorizontalGroup(
            pnlTabelasdeCedulasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTabelasdeCedulasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlTabelasdeCedulasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbCedula)
                    .addComponent(jScrollPane18, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlTabelasdeCedulasLayout.setVerticalGroup(
            pnlTabelasdeCedulasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTabelasdeCedulasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbCedula)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addComponent(jScrollPane18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout pnlOperadoresdeTelasLayout = new javax.swing.GroupLayout(pnlOperadoresdeTelas);
        pnlOperadoresdeTelas.setLayout(pnlOperadoresdeTelasLayout);
        pnlOperadoresdeTelasLayout.setHorizontalGroup(
            pnlOperadoresdeTelasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlOperadoresdeTelasLayout.createSequentialGroup()
                .addGroup(pnlOperadoresdeTelasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlOperadoresdeTelasLayout.createSequentialGroup()
                        .addGroup(pnlOperadoresdeTelasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlOperadoresdeTelasLayout.createSequentialGroup()
                                .addComponent(pnlInformacoesdaVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 451, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(pnlRecebimento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlOperadoresdeTelasLayout.createSequentialGroup()
                                .addComponent(pnlMoedas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(pnlResumodoRecebimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnlTabelasdeCedulas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlOperadoresdeTelasLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(pnlOPERADORES, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnlTroco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(3602, Short.MAX_VALUE))
        );
        pnlOperadoresdeTelasLayout.setVerticalGroup(
            pnlOperadoresdeTelasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlOperadoresdeTelasLayout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addGroup(pnlOperadoresdeTelasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pnlOPERADORES, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlTroco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlOperadoresdeTelasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlOperadoresdeTelasLayout.createSequentialGroup()
                        .addGroup(pnlOperadoresdeTelasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pnlInformacoesdaVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pnlRecebimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlOperadoresdeTelasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(pnlResumodoRecebimento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(pnlMoedas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap(1439, Short.MAX_VALUE))
                    .addGroup(pnlOperadoresdeTelasLayout.createSequentialGroup()
                        .addComponent(pnlTabelasdeCedulas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        jTabbedPane1.addTab("Operadores", pnlOperadoresdeTelas);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addGap(60, 60, 60))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addGap(40, 40, 40))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnSALVARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSALVARActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSALVARActionPerformed

    private void btnPESQUISARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPESQUISARActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnPESQUISARActionPerformed

    private void btnEditarCaixaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarCaixaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEditarCaixaActionPerformed

    private void btnLimparCaixaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimparCaixaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLimparCaixaActionPerformed

    private void btnNovodeCaixaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovodeCaixaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnNovodeCaixaActionPerformed

    private void cbxPeriodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxPeriodoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxPeriodoActionPerformed

    private void cbxStatusdeCaixaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxStatusdeCaixaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxStatusdeCaixaActionPerformed

    private void btnVizualizaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVizualizaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnVizualizaActionPerformed

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
        java.awt.EventQueue.invokeLater(() -> new TelaCaixa(usuario).setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel TotalqttdeMoedas;
    private javax.swing.JRadioButton btnATIVO;
    private javax.swing.JButton btnAbrirCaixa;
    private javax.swing.JButton btnAplicarFLT;
    private javax.swing.JButton btnCalendario;
    private javax.swing.JButton btnCalendarioAberto;
    private javax.swing.JButton btnCalendarioDataFinal;
    private javax.swing.JButton btnCalendarioFechamento;
    private javax.swing.JButton btnCancelarConferencia;
    private javax.swing.JButton btnCedulas;
    private javax.swing.JButton btnCoferencia;
    private javax.swing.JButton btnComprovante;
    private javax.swing.JButton btnComprovanteAtualizacao;
    private javax.swing.JButton btnDataFinal;
    private javax.swing.JButton btnDataInicial;
    private javax.swing.JButton btnDeHdeAutorizacao;
    private javax.swing.JButton btnEDITAR;
    private javax.swing.JButton btnEXCLUIR;
    private javax.swing.JButton btnEcomprovante;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEditarCaixa;
    private javax.swing.JButton btnEmitirComprovante;
    private javax.swing.JButton btnEncerrarPausar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnExporta;
    private javax.swing.JButton btnExportarMovimentacoes;
    private javax.swing.JButton btnFecharCaixa;
    private javax.swing.JButton btnHistorico;
    private javax.swing.JButton btnHorariodefechamento;
    private javax.swing.JRadioButton btnINATIVO;
    private javax.swing.JButton btnImagemTroco;
    private javax.swing.JButton btnImagemValorRecebido;
    private javax.swing.JButton btnImprimi;
    private javax.swing.JButton btnImprimir;
    private javax.swing.JButton btnImprimirResumo;
    private javax.swing.JButton btnIniciarConferecia;
    private javax.swing.JButton btnLIMPAR;
    private javax.swing.JButton btnLimpar;
    private javax.swing.JButton btnLimparCaixa;
    private javax.swing.JButton btnLimparFLT;
    private javax.swing.JButton btnMoedasdeConferencia;
    private javax.swing.JButton btnNOVO;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnNovodeCaixa;
    private javax.swing.JLabel btnOperador;
    private javax.swing.JButton btnPESQUISAR;
    private javax.swing.JButton btnPausarCaixa;
    private javax.swing.JButton btnPesquisa;
    private javax.swing.JButton btnPesquisar;
    private javax.swing.JButton btnRegularTroco;
    private javax.swing.JButton btnRelogio;
    private javax.swing.JButton btnRelogioAberto;
    private javax.swing.JButton btnSALVAR;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JButton btnSalvodeCaixa;
    private javax.swing.JButton btnSangria;
    private javax.swing.JButton btnSuprimento;
    private javax.swing.JButton btnTransferirOperador;
    private javax.swing.JButton btnVOLTAR;
    private javax.swing.JButton btnVcomprovante;
    private javax.swing.JButton btnVisualizarComprovante;
    private javax.swing.JButton btnVizualiza;
    private javax.swing.JButton btnVizualizar;
    private javax.swing.JButton btnVizualizarC;
    private javax.swing.JButton btnVoltar;
    private javax.swing.JButton btnVoltarMovimentacoes;
    private javax.swing.JButton btncalendarioDataInicial;
    private javax.swing.JButton btnpesquisar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.ButtonGroup buttonGroup4;
    private javax.swing.JComboBox<String> cbxCAIXA;
    private javax.swing.JComboBox<String> cbxCAIXAS;
    private javax.swing.JComboBox<String> cbxCaixa;
    private javax.swing.JComboBox<String> cbxCaixaDestino;
    private javax.swing.JComboBox<String> cbxCaixaSangrias;
    private javax.swing.JComboBox<String> cbxCaixas;
    private javax.swing.JComboBox<String> cbxLoja;
    private javax.swing.JComboBox<String> cbxMotivoSangria;
    private javax.swing.JComboBox<String> cbxMotivodoSuprimento;
    private javax.swing.JComboBox<String> cbxMovimentacoes;
    private javax.swing.JComboBox<String> cbxOPERADOR;
    private javax.swing.JComboBox<String> cbxOpedaror;
    private javax.swing.JComboBox<String> cbxOperador;
    private javax.swing.JComboBox<String> cbxOperadorCadrastodecaixa;
    private javax.swing.JComboBox<String> cbxPeriodo;
    private javax.swing.JComboBox<String> cbxPontosdeACadrastodeCaixa;
    private javax.swing.JComboBox<String> cbxStatusdeCaixa;
    private javax.swing.JComboBox<String> cbxTipoPontosdeAtendimento;
    private javax.swing.JComboBox<String> cbxTipodeCaixa;
    private javax.swing.JComboBox<String> cbxTurnos;
    private javax.swing.JComboBox<String> cbxUautorizacao;
    private javax.swing.JComboBox<String> cbxUsuarioAutorizado;
    private javax.swing.JComboBox<String> cbxoperador;
    private javax.swing.JFormattedTextField ffData;
    private javax.swing.JFormattedTextField ffDataFinal;
    private javax.swing.JFormattedTextField ffDataInicial;
    private javax.swing.JFormattedTextField ffDatadeAbertura;
    private javax.swing.JFormattedTextField ffDatadeCaixa;
    private javax.swing.JFormattedTextField ffDatadeFechamento;
    private javax.swing.JFormattedTextField ffDataeHORAS;
    private javax.swing.JFormattedTextField ffDeHdaAutorizacao;
    private javax.swing.JFormattedTextField ffDiferencaSuprimentos;
    private javax.swing.JFormattedTextField ffHoradeAbertura;
    private javax.swing.JFormattedTextField ffHoradeFechamento;
    private javax.swing.JFormattedTextField ffSaldoAtualizado;
    private javax.swing.JFormattedTextField ffTotalConferido;
    private javax.swing.JFormattedTextField ffTotalemCedulas;
    private javax.swing.JFormattedTextField ffTotalemMoedas;
    private javax.swing.JFormattedTextField ffValordoSuprimento;
    private javax.swing.JFormattedTextField ffValordoSuprimentos;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JFormattedTextField jFormattedTextField1;
    private javax.swing.JFormattedTextField jFormattedTextField2;
    private javax.swing.JFormattedTextField jFormattedTextField3;
    private javax.swing.JFormattedTextField jFormattedTextField4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane17;
    private javax.swing.JScrollPane jScrollPane18;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane20;
    private javax.swing.JScrollPane jScrollPane21;
    private javax.swing.JScrollPane jScrollPane22;
    private javax.swing.JScrollPane jScrollPane23;
    private javax.swing.JScrollPane jScrollPane24;
    private javax.swing.JScrollPane jScrollPane25;
    private javax.swing.JScrollPane jScrollPane26;
    private javax.swing.JScrollPane jScrollPane27;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextArea jTextArea3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel lbAberto;
    private javax.swing.JLabel lbAbertura;
    private javax.swing.JLabel lbAcoesRapidas;
    private javax.swing.JLabel lbAcoesRepidas;
    private javax.swing.JLabel lbAtualizacaodeSaldo;
    private javax.swing.JLabel lbAutorizacao;
    private javax.swing.JLabel lbAutorizacaoSangria;
    private javax.swing.JLabel lbCAIXA;
    private javax.swing.JLabel lbCAIXAS;
    private javax.swing.JLabel lbCOMPROVANTE;
    private javax.swing.JLabel lbCaixa;
    private javax.swing.JLabel lbCaixaDestino;
    private javax.swing.JLabel lbCaixaMovimentacoes;
    private javax.swing.JLabel lbCaixadeDetalhes;
    private javax.swing.JLabel lbCaixas;
    private javax.swing.JLabel lbCaixasAbertosFechados;
    private javax.swing.JLabel lbCaixasdosDetalhes;
    private javax.swing.JLabel lbCedula;
    private javax.swing.JLabel lbCedulas;
    private javax.swing.JLabel lbChecktAprovacao;
    private javax.swing.JLabel lbCodigo;
    private javax.swing.JLabel lbCodigoCadrastodeCaixa;
    private javax.swing.JLabel lbComprovante;
    private javax.swing.JLabel lbComprovanteAtualizado;
    private javax.swing.JLabel lbConferencia;
    private javax.swing.JLabel lbContagemdeCedulas;
    private javax.swing.JLabel lbContagemdeMoedas;
    private javax.swing.JLabel lbDATA;
    private javax.swing.JLabel lbDATAFinal;
    private javax.swing.JLabel lbDadeCaixa;
    private javax.swing.JLabel lbDadosdeAtendimento;
    private javax.swing.JLabel lbDadosdoCaixa;
    private javax.swing.JLabel lbData;
    private javax.swing.JLabel lbDataDetalhes;
    private javax.swing.JLabel lbDataFinal;
    private javax.swing.JLabel lbDataInicialHistorico;
    private javax.swing.JLabel lbDataInicialMovimentacoes;
    private javax.swing.JLabel lbDatadaS;
    private javax.swing.JLabel lbDatadaVenda;
    private javax.swing.JLabel lbDatadeAbertura;
    private javax.swing.JLabel lbDatadeAbetura;
    private javax.swing.JLabel lbDatadeFechamento;
    private javax.swing.JLabel lbDatadeSuprimentos;
    private javax.swing.JLabel lbDataeHoraAtualizacao;
    private javax.swing.JLabel lbDdeCaixa;
    private javax.swing.JLabel lbDeHdaAutorizacao;
    private javax.swing.JLabel lbDescontos;
    private javax.swing.JLabel lbDescricao;
    private javax.swing.JLabel lbDescricaodeDivergencias;
    private javax.swing.JLabel lbDescricaodePontosdeAtendimento;
    private javax.swing.JLabel lbDetalhesdoCaixa;
    private javax.swing.JLabel lbDiferenca;
    private javax.swing.JLabel lbDiferencadeCaixa;
    private javax.swing.JLabel lbDiferencas;
    private javax.swing.JLabel lbEntradasdeNumerarios;
    private javax.swing.JLabel lbFiltros;
    private javax.swing.JLabel lbFrenteSetor;
    private javax.swing.JLabel lbFrentedeLoja;
    private javax.swing.JLabel lbHistoricoAlteracoes;
    private javax.swing.JLabel lbHistoricodeCaixa;
    private javax.swing.JLabel lbHora;
    private javax.swing.JLabel lbHorariodeAbertura;
    private javax.swing.JLabel lbHorariodeFechamento;
    private javax.swing.JLabel lbLoja;
    private javax.swing.JLabel lbManha;
    private javax.swing.JLabel lbMdaSangria;
    private javax.swing.JLabel lbMeiosdePagamento;
    private javax.swing.JLabel lbMoedas;
    private javax.swing.JLabel lbMotivo;
    private javax.swing.JLabel lbMotivodoSuprimento;
    private javax.swing.JLabel lbMovimentacoes;
    private javax.swing.JLabel lbNome;
    private javax.swing.JLabel lbNomedoOperado;
    private javax.swing.JLabel lbNomedoOperador;
    private javax.swing.JLabel lbNovoSaldodoCaixa;
    private javax.swing.JLabel lbNumerodaS;
    private javax.swing.JLabel lbOCadrastodeCaixa;
    private javax.swing.JLabel lbOPERADOR;
    private javax.swing.JLabel lbObservacoePontosdeAtendimento;
    private javax.swing.JLabel lbObservacoes;
    private javax.swing.JLabel lbObservacoesSangria;
    private javax.swing.JLabel lbObservacoesTX;
    private javax.swing.JLabel lbObservacoesdeDetalhes;
    private javax.swing.JLabel lbObservacoesdoCaixa;
    private javax.swing.JLabel lbOperacaodeDinheoC;
    private javax.swing.JLabel lbOperador;
    private javax.swing.JLabel lbOperadorDetalhes;
    private javax.swing.JLabel lbOperadorMovimentacoes;
    private javax.swing.JLabel lbOperadorS;
    private javax.swing.JLabel lbPGConferido;
    private javax.swing.JLabel lbPGDiferenca;
    private javax.swing.JLabel lbPGEsperando;
    private javax.swing.JLabel lbPeriodo;
    private javax.swing.JLabel lbPesquisar;
    private javax.swing.JLabel lbPontdeACadrastodeC;
    private javax.swing.JLabel lbRecebimento;
    private javax.swing.JLabel lbRegistrodeCaixa;
    private javax.swing.JLabel lbResgistrodeDivergenias;
    private javax.swing.JLabel lbResgistrodeHistoricocaixa;
    private javax.swing.JLabel lbResgistrodeMovimentos;
    private javax.swing.JLabel lbResumoDinheiro;
    private javax.swing.JLabel lbResumoGeralCinco;
    private javax.swing.JLabel lbResumodoRecebimento;
    private javax.swing.JLabel lbSCadrastodeCaixa;
    private javax.swing.JLabel lbSaldoAnteriordoCaixa;
    private javax.swing.JLabel lbSangriasdeCaixa;
    private javax.swing.JLabel lbSenha;
    private javax.swing.JLabel lbSenhadaAutorizacao;
    private javax.swing.JLabel lbSolicitacoes;
    private javax.swing.JLabel lbStatus;
    private javax.swing.JLabel lbStatusDetalhes;
    private javax.swing.JLabel lbStatusdeCaixa;
    private javax.swing.JLabel lbSubtotal;
    private javax.swing.JLabel lbTELEFONE;
    private javax.swing.JLabel lbTipoPontosdeAtendimento;
    private javax.swing.JLabel lbTipodeCaixa;
    private javax.swing.JLabel lbTipodeDiferenca;
    private javax.swing.JLabel lbTipodeDivergencia;
    private javax.swing.JLabel lbTipodeOperacao;
    private javax.swing.JLabel lbTotalConferido;
    private javax.swing.JLabel lbTotalRecebido;
    private javax.swing.JLabel lbTotalaReceber;
    private javax.swing.JLabel lbTotaldaVenda;
    private javax.swing.JLabel lbTotaldeCedula;
    private javax.swing.JLabel lbTotaldeCedulas;
    private javax.swing.JLabel lbTotaldeM;
    private javax.swing.JLabel lbTotaldeMoedas;
    private javax.swing.JLabel lbTotalemCEDULAS;
    private javax.swing.JLabel lbTotalemCedulas;
    private javax.swing.JLabel lbTotalemMoedas;
    private javax.swing.JLabel lbTotalqttDiferenca;
    private javax.swing.JLabel lbTotalqttEsperando;
    private javax.swing.JLabel lbTotalqttSubtotal;
    private javax.swing.JLabel lbTotalrecebido;
    private javax.swing.JLabel lbTroco;
    private javax.swing.JLabel lbTrocoCalculado;
    private javax.swing.JLabel lbTrocoRecebido;
    private javax.swing.JLabel lbTurno;
    private javax.swing.JLabel lbTurnosDetalhes;
    private javax.swing.JLabel lbUltimaAtualizacao;
    private javax.swing.JLabel lbUltimaData;
    private javax.swing.JLabel lbUltimodia;
    private javax.swing.JLabel lbUsuarioAtualizacao;
    private javax.swing.JLabel lbUsuarioAutorizador;
    private javax.swing.JLabel lbUsuarioautorizacao;
    private javax.swing.JLabel lbValorAtualizado;
    private javax.swing.JLabel lbValorR;
    private javax.swing.JLabel lbValorRS;
    private javax.swing.JLabel lbValorS;
    private javax.swing.JLabel lbValorSubtotal;
    private javax.swing.JLabel lbValorTotal;
    private javax.swing.JLabel lbValorTotalMoedas;
    private javax.swing.JLabel lbValoraSuprir;
    private javax.swing.JLabel lbValorcantado;
    private javax.swing.JLabel lbValordaCaixa;
    private javax.swing.JLabel lbValordeCedula;
    private javax.swing.JLabel lbValordeMoedas;
    private javax.swing.JLabel lbValordoCaixa;
    private javax.swing.JLabel lbValordoDesconto;
    private javax.swing.JLabel lbValordoSuprimento;
    private javax.swing.JLabel lbValordoTroco;
    private javax.swing.JLabel lbValores;
    private javax.swing.JLabel lbValoresdeVenda;
    private javax.swing.JLabel lbValorqttRecebido;
    private javax.swing.JLabel lbVenda;
    private javax.swing.JLabel lbcaixa;
    private javax.swing.JButton lbcalendario;
    private javax.swing.JLabel lbcodigo;
    private javax.swing.JLabel lbendereco;
    private javax.swing.JLabel lbinformacoesdaVenda;
    private javax.swing.JLabel lboperador;
    private javax.swing.JLabel lbstatus;
    private javax.swing.JPanel pnlAcoesRapida;
    private javax.swing.JPanel pnlAcoesRapidas;
    private javax.swing.JPanel pnlAtualizacaodeSaldo;
    private javax.swing.JPanel pnlAutorizacao;
    private javax.swing.JPanel pnlAutorizacaoSangrias;
    private javax.swing.JPanel pnlCadastrodeCaixa;
    private javax.swing.JPanel pnlCaixa;
    private java.awt.Panel pnlCaixasAeFTelas;
    private javax.swing.JPanel pnlCedulas;
    private javax.swing.JPanel pnlCedulasOperadores;
    private javax.swing.JPanel pnlChetkdeAprovacao;
    private javax.swing.JPanel pnlComprovante;
    private javax.swing.JPanel pnlComprovanteSuprimentos;
    private javax.swing.JPanel pnlConferencia;
    private java.awt.Panel pnlConferenciaTelas;
    private javax.swing.JPanel pnlConfirmacaodoComprovante;
    private javax.swing.JPanel pnlContagemdeCedulas;
    private javax.swing.JPanel pnlContagemdeMoedas;
    private javax.swing.JPanel pnlDadosDoCaixa;
    private javax.swing.JPanel pnlDadosdeAtendimento;
    private javax.swing.JPanel pnlDetalhesdoCaixa;
    private java.awt.Panel pnlDiferencadeCaixasTelas;
    private javax.swing.JPanel pnlDiferenciadeCaixa;
    private javax.swing.JPanel pnlEntradasdeNumeros;
    private javax.swing.JPanel pnlFiltros;
    private javax.swing.JPanel pnlHistoricoTelas;
    private javax.swing.JPanel pnlHistoricodeAlteracoes;
    private javax.swing.JPanel pnlHistoricodeCaixa;
    private javax.swing.JPanel pnlInformacoesdaVenda;
    private javax.swing.JPanel pnlMeiosdePagamentos;
    private javax.swing.JPanel pnlMoedas;
    private javax.swing.JPanel pnlMotivodaS;
    private javax.swing.JPanel pnlMovimentacoes;
    private javax.swing.JPanel pnlMovimentacoesTelas;
    private javax.swing.JPanel pnlOPERADORES;
    private javax.swing.JPanel pnlOperadoresdeTelas;
    private java.awt.Panel pnlPontosdeAtendimentoTelas;
    private javax.swing.JPanel pnlRecebimento;
    private javax.swing.JPanel pnlRegistroMovimentacoes;
    private javax.swing.JPanel pnlRegistrodeCaixa;
    private javax.swing.JPanel pnlRegistrodeHistorico;
    private javax.swing.JPanel pnlResgidtrodeDivergencias;
    private javax.swing.JPanel pnlResulmoGeral;
    private javax.swing.JPanel pnlResumodeDinheiro;
    private javax.swing.JPanel pnlResumodoRecebimento;
    private javax.swing.JPanel pnlSangriasTelas;
    private javax.swing.JPanel pnlSolicitacao;
    private java.awt.Panel pnlSuprimentosTelas;
    private javax.swing.JPanel pnlTabelasCaixa;
    private javax.swing.JPanel pnlTabelasdeCedulas;
    private javax.swing.JPanel pnlTotaldeM;
    private javax.swing.JPanel pnlTotalrecebido;
    private javax.swing.JPanel pnlTroco;
    private javax.swing.JPanel pnlValores;
    private javax.swing.JRadioButton rbAberto;
    private javax.swing.JRadioButton rbAbertura;
    private javax.swing.JRadioButton rbAtivo;
    private javax.swing.JRadioButton rbAtivoCadrastodeCaixa;
    private javax.swing.JRadioButton rbFalsa;
    private javax.swing.JRadioButton rbFechamento;
    private javax.swing.JRadioButton rbInativo;
    private javax.swing.JRadioButton rbInativoCadrastodeCaixa;
    private javax.swing.JRadioButton rbMovimentacoes;
    private javax.swing.JRadioButton rbSobra;
    private javax.swing.JRadioButton rbTodos;
    private javax.swing.JTable tbCEDULA;
    private javax.swing.JTable tbCaixas;
    private javax.swing.JScrollPane tbCedula;
    private javax.swing.JTable tbCedulasConferencia;
    private javax.swing.JTable tbCedulasdeOperadores;
    private javax.swing.JTable tbDadosdeCaixa;
    private javax.swing.JTable tbHistoricodeAlteracoes;
    private javax.swing.JTable tbMeiosdePagamento;
    private javax.swing.JTable tbMoedaQvalor;
    private javax.swing.JTable tbMoedasConferencia;
    private javax.swing.JTable tbMoedasValor;
    private javax.swing.JTable tbMovimentacoes;
    private javax.swing.JTable tbPntosdeAtedimento;
    private javax.swing.JTable tbResgistrodeCaixa;
    private javax.swing.JScrollPane tbResgistrosdeCaixa;
    private javax.swing.JScrollPane tbResgistrosdeMovimentacoes;
    private javax.swing.JTable tbResgistrosdoHistoricodeCaixa;
    private javax.swing.JTable tbResulmoGeral;
    private javax.swing.JTable tbResumodeDinheiro;
    private javax.swing.JTable tbValorqtt;
    private javax.swing.JTable tbValorqttMoeda;
    private javax.swing.JScrollPane tbValorqttMoedas;
    private javax.swing.JTable tbqttdeMoedas;
    private javax.swing.JTextField tfENDERECO;
    private javax.swing.JTextField tfTELEFONE;
    private javax.swing.JTextPane tpValorRetirado;
    private javax.swing.JTextArea txaObservacoes;
    private javax.swing.JTextArea txaObservacoesDiferenciadeCaixa;
    private javax.swing.JTextArea txaObservacoesNumerarios;
    private javax.swing.JTextArea txaObservacoesPontosdeAtendimento;
    private javax.swing.JTextField txfCadigoPontosdeAtendimento;
    private javax.swing.JTextField txfCcadrastoDeCaixa;
    private javax.swing.JTextField txfCodigo;
    private javax.swing.JTextField txfDataFinal;
    private javax.swing.JTextField txfDataInicial;
    private javax.swing.JTextField txfDatadaSolicitacao;
    private javax.swing.JTextField txfDescricao;
    private javax.swing.JTextField txfDescricaoPontosdeAtendimento;
    private javax.swing.JTextField txfDiferenca;
    private javax.swing.JTextField txfNome;
    private javax.swing.JTextField txfNumerodaSolicitacoes;
    private javax.swing.JTextField txfOperadorSolicitante;
    private javax.swing.JTextField txfPesquisar;
    private javax.swing.JTextField txfSenha;
    private javax.swing.JTextField txfSenhadeAutorizacao;
    private javax.swing.JTextField txfTotalaReceberOperadores;
    private javax.swing.JTextField txfTotalrecebido;
    private javax.swing.JTextField txfTrocoOperadores;
    private javax.swing.JTextField txfValorASuprir;
    private javax.swing.JTextField txfValorContado;
    private javax.swing.JTextField txfValorEsperando;
    private javax.swing.JTextField txfValorRS;
    private javax.swing.JTextPane txfValorSolicitado;
    private javax.swing.JTextField txfValorTotalRecebido;
    private javax.swing.JTextPane txpObservaçoes;
    // End of variables declaration//GEN-END:variables

   
   
}
