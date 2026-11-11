package View;

import java.util.List;
import Control.CupomController;
import Control.PromocaoController;
import Model.Cupom;
import Model.Promocao;
import Model.Usuario;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author RogerioSilva
 */
public class TelaPromocoes extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(TelaPromocoes.class.getName());

    private MenuPadrao menu;  
    private Usuario usuario;
    private int idCupomSelecionado = -1;
    
    public TelaPromocoes(Usuario usuario) {
    initComponents();
    listarCupons();
    listarPromocoes();
    

    this.usuario = usuario;

    this.menu = MenuPadrao.instalar(
        this,
        usuario,
        "produtos",
        this::navegar,
        this::dispose
    );

    this.setExtendedState(TelaPromocoes.MAXIMIZED_BOTH);
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
            menu.abrirTela(this, new AlterarSenha());
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

// ------------------------ LISTAR --------------------------------------------------------------


// MÉTODO DE LISTAR CUPONS
private void listarCupons() {
    try {
        CupomController controller = new CupomController();

        List<Cupom> lista = controller.listar();

        DefaultTableModel modelo =
                (DefaultTableModel) tbTabelaCD.getModel();

        // Garantir que a tabela tenha as 12 colunas necessárias
        modelo.setColumnIdentifiers(new Object[]{
            "ID",
            "Código",
            "Tipo de desconto",
            "Valor do desconto",
            "Valor mínimo",
            "Quantidade máxima",
            "Limite por cliente",
            "Tipo de uso",
            "Data inicial",
            "Data final",
            "Observação",
            "ID da promoção"
        });

        modelo.setRowCount(0);

        for (Cupom cupom : lista) {
            modelo.addRow(new Object[]{
                cupom.getIdcupom(),
                cupom.getCodigoCupom(),
                cupom.getTipoDesconto(),
                cupom.getValorDesconto(),
                cupom.getValorMinimoCompra(),
                cupom.getQttMaximaUso(),
                cupom.getLimitePorCliente(),
                cupom.getTipoUso(),
                cupom.getDataInicio(),
                cupom.getDataFinal(),
                cupom.getObservacao(),
                cupom.getPromocao_idpromocao()
            });
        }

    } catch (Exception e) {
        JOptionPane.showMessageDialog(
                this,
                "Erro ao listar cupons: " + e.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE
        );
    }
}
// MÉTODO DE LISTAR PROMOÇÕES
private void listarPromocoes() {
    try {
        PromocaoController controller = new PromocaoController();

        List<Promocao> lista = controller.listar();

        DefaultTableModel modelo =
                (DefaultTableModel) tbTabelaCP.getModel();

        modelo.setRowCount(0);

        // Limpar ComboBox
        cbxPromocoesVinculada.removeAllItems();

        // Opção inicial
        cbxPromocoesVinculada.addItem("Selecione uma promoção");

        for (Promocao promocao : lista) {

            // Preencher tabela
            modelo.addRow(new Object[]{
                promocao.getIdpromocao(),
                promocao.getNome(),
                promocao.getTipo(),
                promocao.getStatus(),
                promocao.getDataInicio(),
                promocao.getDataFinal(),
                promocao.getRegras(),
                promocao.getDesconto(),
                promocao.getQttMinima()
            });

            // Preencher ComboBox
            if (promocao.getStatus() == 1) {
                cbxPromocoesVinculada.addItem(promocao.getNome());
            }
        }

    } catch (Exception e) {
        JOptionPane.showMessageDialog(
                this,
                "Erro ao listar promoções: " + e.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE
        );
    }
}


// ---------------- LIMPAR -----------------------------------------------------------------

private void limparCamposCupom() {

    tfCodigoCupomCD.setText("");
    tfDataInicioCD.setText("");
    tfDataFimCD.setText("");

    cbxTipoDescontoCD.setSelectedIndex(-1);
    cbxTipoUsoCD.setSelectedIndex(-1);

    spnValorDescontoCD.setValue(0);
    spnValorMinimoCD.setValue(0);
    spnQtdMaxUsosCD.setValue(0);
    spnLimiteClienteCD.setValue(0);
}
private void limparCamposPromocao() {

    txDNdaPromocaoCP.setText("");

    cbxProdutoCategoriaCP.setSelectedIndex(-1);
    cbxTipoPromocaoCP.setSelectedIndex(-1);

    ftfDataInicioCP.setValue(null);
    ftfDataFimCP.setValue(null);

    spnDescontoCP.setValue(0);
    spnQtdeMinimaCP.setValue(0);

    rbtnStatusAtivoCP.setSelected(false);
    rbtnStatusInativoCP.setSelected(false);
}

// ------------------------------ CADASTRAR ------------------------------------------------
private int buscarIdPromocao(String nomePromocao) throws SQLException {

    PromocaoController controller = new PromocaoController();

    List<Promocao> lista = controller.listar();

    for (Promocao promocao : lista) {

        if (promocao.getNome().equalsIgnoreCase(nomePromocao)) {
            return promocao.getIdpromocao();
        }
    }

    return -1;
}

private void cadastrarCupom() {

    try {

        // Capturar os dados
        String codigoCupom = tfCodigoCupomCD.getText().trim();

        String tipoDesconto = cbxTipoDescontoCD.getSelectedItem() != null
                ? cbxTipoDescontoCD.getSelectedItem().toString()
                : "";

        String promocaoVinculada = cbxPromocoesVinculada.getSelectedItem() != null
                ? cbxPromocoesVinculada.getSelectedItem().toString()
                : "";

        double valorDesconto = Double.parseDouble(
                spnValorDescontoCD.getValue().toString());

        double valorMinimoCompra = Double.parseDouble(
                spnValorMinimoCD.getValue().toString());

        int qtdMaximaUso = Integer.parseInt(
                spnQtdMaxUsosCD.getValue().toString());

        int limitePorCliente = Integer.parseInt(
                spnLimiteClienteCD.getValue().toString());

        String tipoUso = cbxTipoUsoCD.getSelectedItem() != null
                ? cbxTipoUsoCD.getSelectedItem().toString()
                : "";

        String dataInicio = tfDataInicioCD.getText().trim();
        String dataFinal = tfDataFimCD.getText().trim();

        // Validar campos
        if (codigoCupom.isEmpty()
                || tipoDesconto.isEmpty()
                || tipoUso.isEmpty()
                || promocaoVinculada.isEmpty()
                || dataInicio.isEmpty()
                || dataFinal.isEmpty()
                || valorDesconto <= 0
                || valorMinimoCompra < 0
                || qtdMaximaUso <= 0
                || limitePorCliente <= 0) {

            JOptionPane.showMessageDialog(this,
                    "Preencha todos os campos obrigatórios corretamente!",
                    "Atenção",
                    JOptionPane.WARNING_MESSAGE);

            return;
        }

        // Buscar ID da promoção selecionada
        int idPromocao = buscarIdPromocao(promocaoVinculada);

        if (idPromocao == -1) {

            JOptionPane.showMessageDialog(this,
                    "Promoção não encontrada!");

            return;
        }

        // Criar objeto Cupom
        Cupom cupom = new Cupom();

        cupom.setCodigoCupom(codigoCupom);
        cupom.setTipoDesconto(tipoDesconto);
        cupom.setValorDesconto(valorDesconto);
        cupom.setValorMinimoCompra(valorMinimoCompra);
        cupom.setQttMaximaUso(qtdMaximaUso);
        cupom.setLimitePorCliente(limitePorCliente);
        cupom.setTipoUso(tipoUso);
        cupom.setPromocao_idpromocao(idPromocao);
        cupom.setDataInicio(dataInicio);
        cupom.setDataFinal(dataFinal);

        // Cadastrar
        CupomController controller = new CupomController();

        boolean sucesso = controller.cadastrar(cupom);

        if (sucesso) {

            JOptionPane.showMessageDialog(this,
                    "Cupom cadastrado com sucesso!",
                    "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE);

            listarCupons();
            limparCamposCupom();

        } else {

            JOptionPane.showMessageDialog(this,
                    "Não foi possível cadastrar o cupom!",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }

    } catch (NumberFormatException e) {

        JOptionPane.showMessageDialog(this,
                "Informe valores numéricos válidos!",
                "Atenção",
                JOptionPane.WARNING_MESSAGE);

    } catch (SQLException e) {

        JOptionPane.showMessageDialog(this,
                "Erro ao cadastrar cupom: " + e.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE);

        e.printStackTrace();

    } catch (Exception e) {

        JOptionPane.showMessageDialog(this,
                "Erro ao cadastrar cupom: " + e.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE);

        e.printStackTrace();
    }
}

  


private void cadastrarPromocao() {

    try {

        // Capturar os dados
        String nome = txDNdaPromocaoCP.getText().trim();

        String tipo = cbxTipoPromocaoCP.getSelectedItem() != null
                ? cbxTipoPromocaoCP.getSelectedItem().toString()
                : "";

        int status = rbtnStatusAtivoCP.isSelected() ? 1 : 0;

        String categoria = cbxProdutoCategoriaCP.getSelectedItem() != null
                ? cbxProdutoCategoriaCP.getSelectedItem().toString()
                : "";

        int qtdMinima = Integer.parseInt(
                spnQtdeMinimaCP.getValue().toString());

        int desconto = Integer.parseInt(
                spnDescontoCP.getValue().toString());

        String dataInicio = ftfDataInicioCP.getText().trim();
        String dataFinal = ftfDataFimCP.getText().trim();

        String regras = "";

        // Validar campos
        if (nome.isEmpty()
                || tipo.isEmpty()
                || categoria.isEmpty()
                || dataInicio.isEmpty()
                || dataFinal.isEmpty()
                || desconto <= 0
                || qtdMinima <= 0
                || (!rbtnStatusAtivoCP.isSelected()
                && !rbtnStatusInativoCP.isSelected())) {

            JOptionPane.showMessageDialog(this,
                    "Preencha todos os campos obrigatórios corretamente!",
                    "Atenção",
                    JOptionPane.WARNING_MESSAGE);

            return;
        }

        // Criar objeto Promocao
        Promocao promocao = new Promocao();

        promocao.setNome(nome);
        promocao.setTipo(tipo);
        promocao.setStatus(status);
        promocao.setDataInicio(dataInicio);
        promocao.setDataFinal(dataFinal);
        promocao.setRegras(regras);
        promocao.setDesconto(desconto);
        promocao.setQttMinima(qtdMinima);

        // Cadastrar
        PromocaoController controller = new PromocaoController();

        controller.cadastrar(promocao);

        JOptionPane.showMessageDialog(this,
                "Promoção cadastrada com sucesso!",
                "Sucesso",
                JOptionPane.INFORMATION_MESSAGE);

        listarPromocoes();
        limparCamposPromocao();

    } catch (NumberFormatException e) {

        JOptionPane.showMessageDialog(this,
                "Informe valores numéricos válidos!",
                "Atenção",
                JOptionPane.WARNING_MESSAGE);

    } catch (SQLException e) {

        JOptionPane.showMessageDialog(this,
                "Erro ao cadastrar promoção: " + e.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE);

        e.printStackTrace();

    } catch (Exception e) {

        JOptionPane.showMessageDialog(this,
                "Erro ao cadastrar promoção: " + e.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE);

        e.printStackTrace();
    }
}


 
//======================PREENCHER CAMPOS====================================//

private void preencherCamposPromocao() {

    int linha = tbTabelaCP.getSelectedRow();

    if (linha == -1) {
        return;
    }

    // Identificar a linha selecionada
    int linhaModelo = tbTabelaCP.convertRowIndexToModel(linha);

    // Preencher nome
    txDNdaPromocaoCP.setText(
            tbTabelaCP.getModel().getValueAt(linhaModelo, 1).toString()
    );

    // Preencher tipo
    cbxTipoPromocaoCP.setSelectedItem(
            tbTabelaCP.getModel().getValueAt(linhaModelo, 2).toString()
    );

    // Preencher status
    Object valorStatus = tbTabelaCP.getModel().getValueAt(linhaModelo, 3);

    boolean ativo = Boolean.parseBoolean(valorStatus.toString());

    rbtnStatusAtivoCP.setSelected(ativo);
    rbtnStatusInativoCP.setSelected(!ativo);

    // Preencher datas
    ftfDataInicioCP.setText(
            tbTabelaCP.getModel().getValueAt(linhaModelo, 4).toString()
    );

    ftfDataFimCP.setText(
            tbTabelaCP.getModel().getValueAt(linhaModelo, 5).toString()
    );

    // Preencher regras e observações
    txtaRegrasUsoCP.setText(
            tbTabelaCP.getModel().getValueAt(linhaModelo, 6).toString()
    );
}

private void preencherCamposCupom() {

    int linha = tbTabelaCD.getSelectedRow();

    if (linha == -1) {
        return;
    }

    int linhaModelo = tbTabelaCD.convertRowIndexToModel(linha);
    
    idCupomSelecionado = Integer.parseInt(
    tbTabelaCD.getModel().getValueAt(linhaModelo, 0).toString()
);

    // Código do cupom
    tfCodigoCupomCD.setText(
            tbTabelaCD.getModel().getValueAt(linhaModelo, 1).toString()
    );

    // Tipo de desconto
    cbxTipoDescontoCD.setSelectedItem(
            tbTabelaCD.getModel().getValueAt(linhaModelo, 2).toString()
    );

    // Valor do desconto
    spnValorDescontoCD.setValue(
            Double.parseDouble(
                    tbTabelaCD.getModel().getValueAt(linhaModelo, 3).toString()
            )
    );

    // Valor mínimo da compra
    spnValorMinimoCD.setValue(
            Double.parseDouble(
                    tbTabelaCD.getModel().getValueAt(linhaModelo, 4).toString()
            )
    );

    // Quantidade máxima de usos
    spnQtdMaxUsosCD.setValue(
            Integer.parseInt(
                    tbTabelaCD.getModel().getValueAt(linhaModelo, 5).toString()
            )
    );

    // Limite por cliente
    spnLimiteClienteCD.setValue(
            Integer.parseInt(
                    tbTabelaCD.getModel().getValueAt(linhaModelo, 6).toString()
            )
    );

    // Tipo de uso
    cbxTipoUsoCD.setSelectedItem(
            tbTabelaCD.getModel().getValueAt(linhaModelo, 7).toString()
    );

    // Data de início
    tfDataInicioCD.setText(
            tbTabelaCD.getModel().getValueAt(linhaModelo, 8).toString()
    );

    // Data final
    tfDataFimCD.setText(
            tbTabelaCD.getModel().getValueAt(linhaModelo, 9).toString()
    );

    // Promoção vinculada pelo ID
    try {

        String idPromocao = tbTabelaCD.getModel()
                .getValueAt(linhaModelo, 11).toString();

        PromocaoController controller = new PromocaoController();

        List<Promocao> promocoes = controller.listar();

        for (Promocao promocao : promocoes) {

            if (String.valueOf(promocao.getIdpromocao()).equals(idPromocao)) {

                cbxPromocoesVinculada.setSelectedItem(promocao.getNome());
                break;
            }
        }

    } catch (Exception e) {

        JOptionPane.showMessageDialog(
                this,
                "Erro ao carregar promoção vinculada: " + e.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE
        );
    }
}



private void editarCupom() {

    if (idCupomSelecionado == -1) {
        JOptionPane.showMessageDialog(this,
                "Selecione um cupom na tabela para editar.");
        return;
    }

    try {
        CupomController controller = new CupomController();

        // Buscar o cupom original
        Cupom cupomOriginal = controller.buscarPorId(
                idCupomSelecionado);

        if (cupomOriginal == null) {
            JOptionPane.showMessageDialog(this,
                    "Cupom não encontrado.");
            return;
        }

        // Criar objeto Cupom
        Cupom cupom = new Cupom();

        cupom.setIdcupom(idCupomSelecionado);

        cupom.setCodigoCupom(
                tfCodigoCupomCD.getText().trim());

        cupom.setTipoDesconto(
                cbxTipoDescontoCD.getSelectedItem().toString());

        cupom.setValorDesconto(
                Double.parseDouble(
                        spnValorDescontoCD.getValue().toString()));

        cupom.setValorMinimoCompra(
                Double.parseDouble(
                        spnValorMinimoCD.getValue().toString()));

        cupom.setQttMaximaUso(
                Integer.parseInt(
                        spnQtdMaxUsosCD.getValue().toString()));

        cupom.setLimitePorCliente(
                Integer.parseInt(
                        spnLimiteClienteCD.getValue().toString()));

        cupom.setTipoUso(
                cbxTipoUsoCD.getSelectedItem().toString());

        cupom.setDataInicio(
                tfDataInicioCD.getText().trim());

        cupom.setDataFinal(
                tfDataFimCD.getText().trim());

        // Preservar a observação original
        cupom.setObservacao(
                cupomOriginal.getObservacao());

        // Recuperar a promoção selecionada
        if (cbxPromocoesVinculada.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(this,
                    "Selecione uma promoção.");
            return;
        }

        String nomePromocao = cbxPromocoesVinculada
                .getSelectedItem().toString();

        int idPromocao = -1;

        PromocaoController promocaoController =
                new PromocaoController();

        for (Promocao promocao : promocaoController.listar()) {

            if (promocao.getNome().equals(nomePromocao)) {
                idPromocao = promocao.getIdpromocao();
                break;
            }
        }

        if (idPromocao == -1) {
            JOptionPane.showMessageDialog(this,
                    "Selecione uma promoção válida.");
            return;
        }

        cupom.setPromocao_idpromocao(idPromocao);

        // Atualizar cupom
        if (controller.atualizar(cupom)) {

            JOptionPane.showMessageDialog(this,
                    "Cupom atualizado com sucesso!");

            idCupomSelecionado = -1;

            listarCupons();
            limparCamposCupom();

        } else {

            JOptionPane.showMessageDialog(this,
                    "Não foi possível atualizar o cupom.");
        }

    } catch (NumberFormatException e) {

        JOptionPane.showMessageDialog(this,
                "Informe valores numéricos válidos.");

    } catch (SQLException e) {

        JOptionPane.showMessageDialog(this,
                "Erro ao editar cupom: " + e.getMessage());

        e.printStackTrace();

    } catch (Exception e) {

        JOptionPane.showMessageDialog(this,
                "Erro ao editar cupom: " + e.getMessage());

        e.printStackTrace();
    }
}


private void editarPromocao() {

    int linhaSelecionada = tbTabelaCP.getSelectedRow();

    if (linhaSelecionada == -1) {
        JOptionPane.showMessageDialog(this,
                "Selecione uma promoção para editar.");
        return;
    }

    try {

        // Capturar o ID da promoção selecionada
        int linhaModelo = tbTabelaCP.convertRowIndexToModel(linhaSelecionada);

        int idPromocao = Integer.parseInt(
                tbTabelaCP.getModel().getValueAt(linhaModelo, 0).toString()
        );

        // Capturar os dados dos campos
        String nome = txDNdaPromocaoCP.getText().trim();

        String tipo = cbxTipoPromocaoCP.getSelectedItem() != null
                ? cbxTipoPromocaoCP.getSelectedItem().toString()
                : "";

        int status = rbtnStatusAtivoCP.isSelected() ? 1 : 0;

        String dataInicio = ftfDataInicioCP.getText().trim();

        String dataFinal = ftfDataFimCP.getText().trim();

        String regras = txtaRegrasUsoCP.getText().trim();

        // Validar campos
        if (nome.isEmpty() || tipo.isEmpty()
                || dataInicio.isEmpty() || dataFinal.isEmpty()) {

            JOptionPane.showMessageDialog(this,
                    "Preencha todos os campos obrigatórios.");
            return;
        }

        // Buscar os dados atuais
        PromocaoController controller = new PromocaoController();

        Promocao promocao = controller.buscarPorId(idPromocao);

        if (promocao == null) {
            JOptionPane.showMessageDialog(this,
                    "Promoção não encontrada.");
            return;
        }

        // Atualizar os dados alterados
        promocao.setIdpromocao(idPromocao);
        promocao.setNome(nome);
        promocao.setTipo(tipo);
        promocao.setStatus(status);
        promocao.setDataInicio(dataInicio);
        promocao.setDataFinal(dataFinal);
        promocao.setRegras(regras);

        // Salvar no banco
        controller.atualizar(promocao);

        JOptionPane.showMessageDialog(this,
                "Promoção atualizada com sucesso!");

        listarPromocoes();

    } catch (SQLException e) {

        JOptionPane.showMessageDialog(this,
                "Erro ao editar promoção: " + e.getMessage());

        e.printStackTrace();

    } catch (Exception e) {

        JOptionPane.showMessageDialog(this,
                "Erro ao editar promoção: " + e.getMessage());

        e.printStackTrace();
    }
}



    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        pnCadastroPromocoes = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        lbDNdaPromocaoCP = new javax.swing.JLabel();
        txDNdaPromocaoCP = new javax.swing.JTextField();
        cbxTipoPromocaoCP = new javax.swing.JComboBox<>();
        lbTipoPromocaoCP = new javax.swing.JLabel();
        lbStatusCP = new javax.swing.JLabel();
        rbtnStatusAtivoCP = new javax.swing.JRadioButton();
        rbtnStatusInativoCP = new javax.swing.JRadioButton();
        jPanel9 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        cbxProdutoCategoriaCP = new javax.swing.JComboBox<>();
        btnBuscarProdutoCategoriaCP = new javax.swing.JButton();
        lbQtdeMinima = new javax.swing.JLabel();
        spnQtdeMinimaCP = new javax.swing.JSpinner();
        lbDescontoCP = new javax.swing.JLabel();
        spnDescontoCP = new javax.swing.JSpinner();
        lbDadosBasicos = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        lbVigenciaRegrasCP = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        lbDataInicioCP = new javax.swing.JLabel();
        ftfDataInicioCP = new javax.swing.JFormattedTextField();
        lbDataFimCP = new javax.swing.JLabel();
        ftfDataFimCP = new javax.swing.JFormattedTextField();
        lbRegrasObservacoesCP = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        txtaRegrasUsoCP = new javax.swing.JTextArea();
        btnNovoCP = new javax.swing.JButton();
        btnSalvarCP = new javax.swing.JButton();
        btnEditarCP = new javax.swing.JButton();
        btnExcluirCP = new javax.swing.JButton();
        btnLimparCP = new javax.swing.JButton();
        btnPesquisarCP = new javax.swing.JButton();
        btnVoltarCP = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbTabelaCP = new javax.swing.JTable();
        pnCuponsDesconto = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        lbCodigoCupomCD = new javax.swing.JLabel();
        tfCodigoCupomCD = new javax.swing.JTextField();
        cbxTipoDescontoCD = new javax.swing.JComboBox<>();
        lbTipoDescontoCD = new javax.swing.JLabel();
        lbValorDescontoCD = new javax.swing.JLabel();
        spnValorDescontoCD = new javax.swing.JSpinner();
        cbxPromocoesVinculada = new javax.swing.JComboBox<>();
        lbPromocoesVinculada = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        lbValorMinimoCD = new javax.swing.JLabel();
        lbLimiteClienteCD = new javax.swing.JLabel();
        spnLimiteClienteCD = new javax.swing.JSpinner();
        lbQtdMaxUsosCD = new javax.swing.JLabel();
        spnQtdMaxUsosCD = new javax.swing.JSpinner();
        spnValorMinimoCD = new javax.swing.JSpinner();
        lbTipoUsoCD = new javax.swing.JLabel();
        cbxTipoUsoCD = new javax.swing.JComboBox<>();
        lbDadosCD = new javax.swing.JLabel();
        lbRegrasLimitacoesCD = new javax.swing.JLabel();
        lbVigenciaRegrasCD = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        lbDataInicioCD = new javax.swing.JLabel();
        tfDataInicioCD = new javax.swing.JFormattedTextField();
        lbDataFimCD = new javax.swing.JLabel();
        tfDataFimCD = new javax.swing.JFormattedTextField();
        lbObservacoesDescricaoCD = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtaObservacoesDescricaoCD = new javax.swing.JTextArea();
        btnNovoCD = new javax.swing.JButton();
        btnSalvarCD = new javax.swing.JButton();
        btnEditarCD = new javax.swing.JButton();
        btnExcluirCD = new javax.swing.JButton();
        btnLimparCD = new javax.swing.JButton();
        btnPesquisarCD = new javax.swing.JButton();
        btnVoltarCD = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbTabelaCD = new javax.swing.JTable();
        pnHistoricoPromocoes = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        lbTipoPromocaoHP = new javax.swing.JLabel();
        cbxTipoPromocaoHP = new javax.swing.JComboBox<>();
        lbPeriodoEntreHP = new javax.swing.JLabel();
        tfPeriodoDeHP = new javax.swing.JFormattedTextField();
        tfPeriodoAteHP = new javax.swing.JFormattedTextField();
        lbAteHP = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        lbCupomHP = new javax.swing.JLabel();
        cbxTipoCupomHP = new javax.swing.JComboBox<>();
        lbStatusHistoricoHP = new javax.swing.JLabel();
        rbtnAtivasFinalizadasHP = new javax.swing.JRadioButton();
        rbtnApenasAtivasHP = new javax.swing.JRadioButton();
        rbtnApFinalizadasHP = new javax.swing.JRadioButton();
        lbFiltroPesquisaHP = new javax.swing.JLabel();
        lbDescricaoPromocaoHP = new javax.swing.JLabel();
        lbResponsavelHP = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        lbTPResponsavelHP = new javax.swing.JLabel();
        cbxResponsavelHP = new javax.swing.JComboBox<>();
        btnImprimirHP = new javax.swing.JButton();
        btnExportarCSVHP = new javax.swing.JButton();
        btnLimparHP = new javax.swing.JButton();
        btnPesquisarHP = new javax.swing.JButton();
        btnVoltarHP = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbTabelaHP = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTabbedPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jTabbedPane1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        pnCadastroPromocoes.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        pnCadastroPromocoes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnCadastroPromocoesMouseClicked(evt);
            }
        });

        jPanel8.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lbDNdaPromocaoCP.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbDNdaPromocaoCP.setText("Descrição/Nome da promoção:");

        txDNdaPromocaoCP.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txDNdaPromocaoCP.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        cbxTipoPromocaoCP.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cbxTipoPromocaoCP.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Promoção por Produto", "Desconto em Categoria", "Leve mais por menos" }));
        cbxTipoPromocaoCP.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lbTipoPromocaoCP.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbTipoPromocaoCP.setText("Tipo de Promoção");

        lbStatusCP.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbStatusCP.setText("Status");

        rbtnStatusAtivoCP.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        rbtnStatusAtivoCP.setText("Ativa");
        rbtnStatusAtivoCP.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        rbtnStatusInativoCP.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        rbtnStatusInativoCP.setText("Inativa");
        rbtnStatusInativoCP.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbDNdaPromocaoCP)
                    .addComponent(lbTipoPromocaoCP)
                    .addComponent(lbStatusCP))
                .addGap(40, 40, 40)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbxTipoPromocaoCP, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(rbtnStatusAtivoCP, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(rbtnStatusInativoCP))
                    .addComponent(txDNdaPromocaoCP, javax.swing.GroupLayout.DEFAULT_SIZE, 907, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbDNdaPromocaoCP)
                    .addComponent(txDNdaPromocaoCP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbxTipoPromocaoCP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbTipoPromocaoCP))
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbStatusCP)
                        .addContainerGap(16, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rbtnStatusAtivoCP)
                            .addComponent(rbtnStatusInativoCP))
                        .addContainerGap())))
        );

        jPanel9.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel16.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel16.setText("Produto/Categoria:");

        cbxProdutoCategoriaCP.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cbxProdutoCategoriaCP.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbxProdutoCategoriaCP.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnBuscarProdutoCategoriaCP.setBackground(new java.awt.Color(204, 204, 204));
        btnBuscarProdutoCategoriaCP.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnBuscarProdutoCategoriaCP.setIcon(new javax.swing.ImageIcon("C:\\Users\\RogerioSilva\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\PESQUISAR.png")); // NOI18N
        btnBuscarProdutoCategoriaCP.setText("Buscar");
        btnBuscarProdutoCategoriaCP.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lbQtdeMinima.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbQtdeMinima.setText("Qtde Mínima:");

        spnQtdeMinimaCP.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        spnQtdeMinimaCP.setModel(new javax.swing.SpinnerNumberModel(1, null, null, 1));

        lbDescontoCP.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbDescontoCP.setText("Desconto (% ou R$):");

        spnDescontoCP.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        spnDescontoCP.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16)
                    .addComponent(lbQtdeMinima))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(spnQtdeMinimaCP, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(272, 272, 272)
                        .addComponent(lbDescontoCP)
                        .addGap(34, 34, 34)
                        .addComponent(spnDescontoCP, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(cbxProdutoCategoriaCP, javax.swing.GroupLayout.PREFERRED_SIZE, 810, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnBuscarProdutoCategoriaCP, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(253, 253, 253))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbxProdutoCategoriaCP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16)
                    .addComponent(btnBuscarProdutoCategoriaCP, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbQtdeMinima)
                    .addComponent(spnQtdeMinimaCP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbDescontoCP)
                    .addComponent(spnDescontoCP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        lbDadosBasicos.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbDadosBasicos.setText("Dados Básicos");

        jLabel20.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel20.setText("Filtros e Regras");

        lbVigenciaRegrasCP.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbVigenciaRegrasCP.setText("Vigência e Regras");

        jPanel10.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lbDataInicioCP.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbDataInicioCP.setText("Data de Inicio:");

        ftfDataInicioCP.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        try {
            ftfDataInicioCP.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        ftfDataInicioCP.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        lbDataFimCP.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbDataFimCP.setText("Data de Fim:");

        try {
            ftfDataFimCP.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        ftfDataFimCP.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        lbRegrasObservacoesCP.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbRegrasObservacoesCP.setText("Regras de Uso/Observações:");

        txtaRegrasUsoCP.setColumns(20);
        txtaRegrasUsoCP.setRows(5);
        jScrollPane5.setViewportView(txtaRegrasUsoCP);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(lbDataInicioCP)
                        .addGap(115, 115, 115)
                        .addComponent(ftfDataInicioCP, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(76, 76, 76)
                        .addComponent(lbDataFimCP)
                        .addGap(27, 27, 27)
                        .addComponent(ftfDataFimCP, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(lbRegrasObservacoesCP)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane5)))
                .addContainerGap(504, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbDataInicioCP)
                    .addComponent(ftfDataInicioCP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbDataFimCP)
                    .addComponent(ftfDataFimCP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbRegrasObservacoesCP)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        btnNovoCP.setBackground(new java.awt.Color(204, 204, 204));
        btnNovoCP.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnNovoCP.setIcon(new javax.swing.ImageIcon("C:\\Users\\RogerioSilva\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\+.png")); // NOI18N
        btnNovoCP.setText("Novo");
        btnNovoCP.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnSalvarCP.setBackground(new java.awt.Color(204, 204, 204));
        btnSalvarCP.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnSalvarCP.setIcon(new javax.swing.ImageIcon("C:\\Users\\RogerioSilva\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\SALVAR.png")); // NOI18N
        btnSalvarCP.setText("Salvar");
        btnSalvarCP.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnSalvarCP.addActionListener(this::btnSalvarCPActionPerformed);

        btnEditarCP.setBackground(new java.awt.Color(204, 204, 204));
        btnEditarCP.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnEditarCP.setIcon(new javax.swing.ImageIcon("C:\\Users\\RogerioSilva\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\EDITAR.png")); // NOI18N
        btnEditarCP.setText("Editar");
        btnEditarCP.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnEditarCP.addActionListener(this::btnEditarCPActionPerformed);

        btnExcluirCP.setBackground(new java.awt.Color(204, 204, 204));
        btnExcluirCP.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnExcluirCP.setIcon(new javax.swing.ImageIcon("C:\\Users\\RogerioSilva\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\EXCLUIR.png")); // NOI18N
        btnExcluirCP.setText("Excluir");
        btnExcluirCP.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnExcluirCP.addActionListener(this::btnExcluirCPActionPerformed);

        btnLimparCP.setBackground(new java.awt.Color(204, 204, 204));
        btnLimparCP.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnLimparCP.setIcon(new javax.swing.ImageIcon("C:\\Users\\RogerioSilva\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\LIMPAR.png")); // NOI18N
        btnLimparCP.setText("Limpar");
        btnLimparCP.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnLimparCP.addActionListener(this::btnLimparCPActionPerformed);

        btnPesquisarCP.setBackground(new java.awt.Color(204, 204, 204));
        btnPesquisarCP.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnPesquisarCP.setIcon(new javax.swing.ImageIcon("C:\\Users\\RogerioSilva\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\PESQUISAR.png")); // NOI18N
        btnPesquisarCP.setText("Pesquisar");
        btnPesquisarCP.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnPesquisarCP.addActionListener(this::btnPesquisarCPActionPerformed);

        btnVoltarCP.setBackground(new java.awt.Color(204, 204, 204));
        btnVoltarCP.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnVoltarCP.setIcon(new javax.swing.ImageIcon("C:\\Users\\RogerioSilva\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\VOLTAR.png")); // NOI18N
        btnVoltarCP.setText("Voltar");
        btnVoltarCP.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnVoltarCP.addActionListener(this::btnVoltarCPActionPerformed);

        tbTabelaCP.setBackground(new java.awt.Color(204, 204, 204));
        tbTabelaCP.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tbTabelaCP.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        tbTabelaCP.setModel(new javax.swing.table.DefaultTableModel(
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
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Nome da Promoção", "Tipo", "Inicio", "Fim", "Regra", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbTabelaCP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbTabelaCPMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbTabelaCP);

        javax.swing.GroupLayout pnCadastroPromocoesLayout = new javax.swing.GroupLayout(pnCadastroPromocoes);
        pnCadastroPromocoes.setLayout(pnCadastroPromocoesLayout);
        pnCadastroPromocoesLayout.setHorizontalGroup(
            pnCadastroPromocoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnCadastroPromocoesLayout.createSequentialGroup()
                .addGroup(pnCadastroPromocoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnCadastroPromocoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(pnCadastroPromocoesLayout.createSequentialGroup()
                            .addGap(34, 34, 34)
                            .addComponent(btnNovoCP, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(54, 54, 54)
                            .addComponent(btnSalvarCP, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(56, 56, 56)
                            .addComponent(btnEditarCP, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(56, 56, 56)
                            .addComponent(btnExcluirCP, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(59, 59, 59)
                            .addComponent(btnLimparCP, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(53, 53, 53)
                            .addComponent(btnPesquisarCP, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(65, 65, 65)
                            .addComponent(btnVoltarCP, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(39, 39, 39)))
                    .addGroup(pnCadastroPromocoesLayout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(pnCadastroPromocoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel20)
                            .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 1157, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbDadosBasicos)
                            .addComponent(lbVigenciaRegrasCP))))
                .addContainerGap(823, Short.MAX_VALUE))
        );
        pnCadastroPromocoesLayout.setVerticalGroup(
            pnCadastroPromocoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnCadastroPromocoesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbDadosBasicos)
                .addGap(8, 8, 8)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lbVigenciaRegrasCP)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel20)
                .addGap(18, 18, 18)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnCadastroPromocoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNovoCP, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSalvarCP, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEditarCP, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExcluirCP, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLimparCP, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPesquisarCP, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnVoltarCP, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(229, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Cadastro De Pomoção", pnCadastroPromocoes);

        pnCuponsDesconto.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lbCodigoCupomCD.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbCodigoCupomCD.setText("Código do Cupom:");

        tfCodigoCupomCD.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        tfCodigoCupomCD.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        cbxTipoDescontoCD.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cbxTipoDescontoCD.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Porcentagem (%)", "Valor Fixo (R$)" }));
        cbxTipoDescontoCD.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lbTipoDescontoCD.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbTipoDescontoCD.setText("Tipo de Descoto:");

        lbValorDescontoCD.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbValorDescontoCD.setText("Valor do Desconto:");

        spnValorDescontoCD.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        spnValorDescontoCD.setModel(new javax.swing.SpinnerNumberModel(1.0d, 1.0d, null, 1.0d));
        spnValorDescontoCD.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        cbxPromocoesVinculada.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cbxPromocoesVinculada.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbxPromocoesVinculada.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        cbxPromocoesVinculada.addActionListener(this::cbxPromocoesVinculadaActionPerformed);

        lbPromocoesVinculada.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbPromocoesVinculada.setText("Promoçao Vinculada:");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbCodigoCupomCD)
                            .addComponent(lbTipoDescontoCD))
                        .addGap(129, 129, 129)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(tfCodigoCupomCD, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbxTipoDescontoCD, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(lbValorDescontoCD)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(spnValorDescontoCD, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(32, 32, 32)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbPromocoesVinculada)
                    .addComponent(cbxPromocoesVinculada, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbCodigoCupomCD)
                    .addComponent(tfCodigoCupomCD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbPromocoesVinculada))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbxTipoDescontoCD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbTipoDescontoCD)
                    .addComponent(cbxPromocoesVinculada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbValorDescontoCD)
                    .addComponent(spnValorDescontoCD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(9, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lbValorMinimoCD.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbValorMinimoCD.setText("Valor Minímo da Compra:");

        lbLimiteClienteCD.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbLimiteClienteCD.setText("Limite por Cliente:");

        spnLimiteClienteCD.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        spnLimiteClienteCD.setModel(new javax.swing.SpinnerNumberModel(1, null, null, 1));

        lbQtdMaxUsosCD.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbQtdMaxUsosCD.setText("Quantidade máxima de Usos:");

        spnQtdMaxUsosCD.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        spnQtdMaxUsosCD.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));

        spnValorMinimoCD.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        lbTipoUsoCD.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbTipoUsoCD.setText("Tipo de Uso:");

        cbxTipoUsoCD.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cbxTipoUsoCD.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Uso Único", "Múltiplos Usos" }));
        cbxTipoUsoCD.addActionListener(this::cbxTipoUsoCDActionPerformed);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbValorMinimoCD)
                    .addComponent(lbLimiteClienteCD))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(spnValorMinimoCD, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(87, 87, 87)
                        .addComponent(spnLimiteClienteCD, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 168, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbQtdMaxUsosCD)
                    .addComponent(lbTipoUsoCD))
                .addGap(82, 82, 82)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(spnQtdMaxUsosCD)
                    .addComponent(cbxTipoUsoCD, 0, 174, Short.MAX_VALUE))
                .addGap(97, 97, 97))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbValorMinimoCD)
                    .addComponent(spnValorMinimoCD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbQtdMaxUsosCD)
                    .addComponent(spnQtdMaxUsosCD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbLimiteClienteCD)
                        .addComponent(spnLimiteClienteCD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lbTipoUsoCD))
                    .addComponent(cbxTipoUsoCD, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        lbDadosCD.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbDadosCD.setText("Dados do Cupom / Desconto");

        lbRegrasLimitacoesCD.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbRegrasLimitacoesCD.setText("Regras e Limitações");

        lbVigenciaRegrasCD.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbVigenciaRegrasCD.setText("Vigência e Regras");

        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lbDataInicioCD.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbDataInicioCD.setText("Data de Inicio:");

        tfDataInicioCD.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        try {
            tfDataInicioCD.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        lbDataFimCD.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbDataFimCD.setText("Data de Fim:");

        try {
            tfDataFimCD.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfDataFimCD.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        lbObservacoesDescricaoCD.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbObservacoesDescricaoCD.setText("Observações / Descrição:");

        txtaObservacoesDescricaoCD.setColumns(20);
        txtaObservacoesDescricaoCD.setRows(5);
        jScrollPane4.setViewportView(txtaObservacoesDescricaoCD);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbDataInicioCD)
                    .addComponent(lbObservacoesDescricaoCD))
                .addGap(60, 60, 60)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(tfDataInicioCD, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44)
                        .addComponent(lbDataFimCD)
                        .addGap(51, 51, 51)
                        .addComponent(tfDataFimCD, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 689, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbDataInicioCD)
                    .addComponent(tfDataInicioCD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbDataFimCD)
                    .addComponent(tfDataFimCD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbObservacoesDescricaoCD)
                        .addContainerGap(42, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );

        btnNovoCD.setBackground(new java.awt.Color(204, 204, 204));
        btnNovoCD.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnNovoCD.setIcon(new javax.swing.ImageIcon("C:\\Users\\RogerioSilva\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\+.png")); // NOI18N
        btnNovoCD.setText("Novo");
        btnNovoCD.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnSalvarCD.setBackground(new java.awt.Color(204, 204, 204));
        btnSalvarCD.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnSalvarCD.setIcon(new javax.swing.ImageIcon("C:\\Users\\RogerioSilva\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\SALVAR.png")); // NOI18N
        btnSalvarCD.setText("Salvar");
        btnSalvarCD.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnSalvarCD.addActionListener(this::btnSalvarCDActionPerformed);

        btnEditarCD.setBackground(new java.awt.Color(204, 204, 204));
        btnEditarCD.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnEditarCD.setIcon(new javax.swing.ImageIcon("C:\\Users\\RogerioSilva\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\EDITAR.png")); // NOI18N
        btnEditarCD.setText("Editar");
        btnEditarCD.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnEditarCD.addActionListener(this::btnEditarCDActionPerformed);

        btnExcluirCD.setBackground(new java.awt.Color(204, 204, 204));
        btnExcluirCD.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnExcluirCD.setIcon(new javax.swing.ImageIcon("C:\\Users\\RogerioSilva\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\EXCLUIR.png")); // NOI18N
        btnExcluirCD.setText("Excluir");
        btnExcluirCD.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnExcluirCD.addActionListener(this::btnExcluirCDActionPerformed);

        btnLimparCD.setBackground(new java.awt.Color(204, 204, 204));
        btnLimparCD.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnLimparCD.setIcon(new javax.swing.ImageIcon("C:\\Users\\RogerioSilva\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\LIMPAR.png")); // NOI18N
        btnLimparCD.setText("Limpar");
        btnLimparCD.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnLimparCD.addActionListener(this::btnLimparCDActionPerformed);

        btnPesquisarCD.setBackground(new java.awt.Color(204, 204, 204));
        btnPesquisarCD.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnPesquisarCD.setIcon(new javax.swing.ImageIcon("C:\\Users\\RogerioSilva\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\PESQUISAR.png")); // NOI18N
        btnPesquisarCD.setText("Pesquisar");
        btnPesquisarCD.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnPesquisarCD.addActionListener(this::btnPesquisarCDActionPerformed);

        btnVoltarCD.setBackground(new java.awt.Color(204, 204, 204));
        btnVoltarCD.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnVoltarCD.setIcon(new javax.swing.ImageIcon("C:\\Users\\RogerioSilva\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\VOLTAR.png")); // NOI18N
        btnVoltarCD.setText("Voltar");
        btnVoltarCD.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnVoltarCD.addActionListener(this::btnVoltarCDActionPerformed);

        tbTabelaCD.setBackground(new java.awt.Color(204, 204, 204));
        tbTabelaCD.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tbTabelaCD.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        tbTabelaCD.setModel(new javax.swing.table.DefaultTableModel(
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
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Código", "Tipo", "Desconto", "Vigência", "Qtd. Max", "Qtd. Usados", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbTabelaCD.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbTabelaCDMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbTabelaCD);

        javax.swing.GroupLayout pnCuponsDescontoLayout = new javax.swing.GroupLayout(pnCuponsDesconto);
        pnCuponsDesconto.setLayout(pnCuponsDescontoLayout);
        pnCuponsDescontoLayout.setHorizontalGroup(
            pnCuponsDescontoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnCuponsDescontoLayout.createSequentialGroup()
                .addGroup(pnCuponsDescontoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnCuponsDescontoLayout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(btnNovoCD, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(54, 54, 54)
                        .addComponent(btnSalvarCD, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(56, 56, 56)
                        .addComponent(btnEditarCD, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(75, 75, 75)
                        .addComponent(btnExcluirCD, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnLimparCD, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(53, 53, 53)
                        .addComponent(btnPesquisarCD, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(65, 65, 65)
                        .addComponent(btnVoltarCD, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39))
                    .addGroup(pnCuponsDescontoLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(pnCuponsDescontoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(pnCuponsDescontoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(lbVigenciaRegrasCD)
                                .addComponent(lbRegrasLimitacoesCD)
                                .addComponent(lbDadosCD)
                                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1196, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(803, Short.MAX_VALUE))
        );
        pnCuponsDescontoLayout.setVerticalGroup(
            pnCuponsDescontoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnCuponsDescontoLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(lbDadosCD)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lbRegrasLimitacoesCD)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lbVigenciaRegrasCD)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnCuponsDescontoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNovoCD, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSalvarCD, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEditarCD, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExcluirCD, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLimparCD, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPesquisarCD, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnVoltarCD, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(226, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Cupons e Descontos", pnCuponsDesconto);

        pnHistoricoPromocoes.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jPanel12.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lbTipoPromocaoHP.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbTipoPromocaoHP.setText("Tipo de Promoção:");

        cbxTipoPromocaoHP.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cbxTipoPromocaoHP.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Promoção por Produto", "Desconto em Categoria", "Leve mais por menos", "Cupons" }));
        cbxTipoPromocaoHP.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lbPeriodoEntreHP.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbPeriodoEntreHP.setText("Período entre:");

        try {
            tfPeriodoDeHP.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        try {
            tfPeriodoAteHP.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        lbAteHP.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbAteHP.setText("Até");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbTipoPromocaoHP)
                    .addComponent(lbPeriodoEntreHP))
                .addGap(88, 88, 88)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(tfPeriodoDeHP, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22)
                        .addComponent(lbAteHP)
                        .addGap(18, 18, 18)
                        .addComponent(tfPeriodoAteHP, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(cbxTipoPromocaoHP, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbTipoPromocaoHP)
                    .addComponent(cbxTipoPromocaoHP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbPeriodoEntreHP)
                    .addComponent(tfPeriodoDeHP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfPeriodoAteHP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbAteHP))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel13.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lbCupomHP.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbCupomHP.setText("Cupom:");

        cbxTipoCupomHP.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cbxTipoCupomHP.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos", "Promoção por Produto", "Leve mais por Menos", "Cupons" }));
        cbxTipoCupomHP.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lbStatusHistoricoHP.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbStatusHistoricoHP.setText("Status Histórico:");

        rbtnAtivasFinalizadasHP.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        rbtnAtivasFinalizadasHP.setText("Artivas / Finalizadas");
        rbtnAtivasFinalizadasHP.addActionListener(this::rbtnAtivasFinalizadasHPActionPerformed);

        rbtnApenasAtivasHP.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        rbtnApenasAtivasHP.setText("Apenas ativas");
        rbtnApenasAtivasHP.addActionListener(this::rbtnApenasAtivasHPActionPerformed);

        rbtnApFinalizadasHP.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        rbtnApFinalizadasHP.setText("Apenas Finalizadas");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbCupomHP)
                    .addComponent(lbStatusHistoricoHP))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                        .addComponent(rbtnAtivasFinalizadasHP)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(rbtnApenasAtivasHP)
                        .addGap(41, 41, 41)
                        .addComponent(rbtnApFinalizadasHP)
                        .addGap(446, 446, 446))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                        .addComponent(cbxTipoCupomHP, javax.swing.GroupLayout.PREFERRED_SIZE, 933, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbxTipoCupomHP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbCupomHP))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbStatusHistoricoHP)
                    .addComponent(rbtnAtivasFinalizadasHP)
                    .addComponent(rbtnApenasAtivasHP)
                    .addComponent(rbtnApFinalizadasHP))
                .addContainerGap())
        );

        lbFiltroPesquisaHP.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbFiltroPesquisaHP.setText("Filtros de Pesquisa de histórico");

        lbDescricaoPromocaoHP.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbDescricaoPromocaoHP.setText("Nome / descrição da promoção ou Cupom");

        lbResponsavelHP.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbResponsavelHP.setText("Responsável");

        jPanel14.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lbTPResponsavelHP.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbTPResponsavelHP.setText("Responsável:");

        cbxResponsavelHP.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cbxResponsavelHP.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos os Usuários", "Admin", "Operador1", "Gerente de vendas" }));
        cbxResponsavelHP.addActionListener(this::cbxResponsavelHPActionPerformed);

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbTPResponsavelHP)
                .addGap(121, 121, 121)
                .addComponent(cbxResponsavelHP, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(712, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbTPResponsavelHP)
                    .addComponent(cbxResponsavelHP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(62, Short.MAX_VALUE))
        );

        btnImprimirHP.setBackground(new java.awt.Color(204, 204, 204));
        btnImprimirHP.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnImprimirHP.setIcon(new javax.swing.ImageIcon("C:\\Users\\RogerioSilva\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\impressora.png")); // NOI18N
        btnImprimirHP.setText("Imprimir");
        btnImprimirHP.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnImprimirHP.addActionListener(this::btnImprimirHPActionPerformed);

        btnExportarCSVHP.setBackground(new java.awt.Color(204, 204, 204));
        btnExportarCSVHP.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnExportarCSVHP.setText("Exportar para CSV");
        btnExportarCSVHP.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnExportarCSVHP.addActionListener(this::btnExportarCSVHPActionPerformed);

        btnLimparHP.setBackground(new java.awt.Color(204, 204, 204));
        btnLimparHP.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnLimparHP.setIcon(new javax.swing.ImageIcon("C:\\Users\\RogerioSilva\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\LIMPAR.png")); // NOI18N
        btnLimparHP.setText("Limpar Filtros");
        btnLimparHP.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnLimparHP.addActionListener(this::btnLimparHPActionPerformed);

        btnPesquisarHP.setBackground(new java.awt.Color(204, 204, 204));
        btnPesquisarHP.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnPesquisarHP.setIcon(new javax.swing.ImageIcon("C:\\Users\\RogerioSilva\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\PESQUISAR.png")); // NOI18N
        btnPesquisarHP.setText("Pesquisar");
        btnPesquisarHP.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnPesquisarHP.addActionListener(this::btnPesquisarHPActionPerformed);

        btnVoltarHP.setBackground(new java.awt.Color(204, 204, 204));
        btnVoltarHP.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnVoltarHP.setIcon(new javax.swing.ImageIcon("C:\\Users\\RogerioSilva\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\VOLTAR.png")); // NOI18N
        btnVoltarHP.setText("Voltar");
        btnVoltarHP.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnVoltarHP.addActionListener(this::btnVoltarHPActionPerformed);

        tbTabelaHP.setBackground(new java.awt.Color(204, 204, 204));
        tbTabelaHP.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tbTabelaHP.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        tbTabelaHP.setModel(new javax.swing.table.DefaultTableModel(
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
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Nome / Cupom", "Tipo", "Data Inicio", "Data Fim", "Total de Usos", "Impacto Financeiro (R$)", "Status / Situação"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tbTabelaHP);

        javax.swing.GroupLayout pnHistoricoPromocoesLayout = new javax.swing.GroupLayout(pnHistoricoPromocoes);
        pnHistoricoPromocoes.setLayout(pnHistoricoPromocoesLayout);
        pnHistoricoPromocoesLayout.setHorizontalGroup(
            pnHistoricoPromocoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnHistoricoPromocoesLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(pnHistoricoPromocoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(pnHistoricoPromocoesLayout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(btnPesquisarHP, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnLimparHP, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(66, 66, 66)
                        .addComponent(btnImprimirHP, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(68, 68, 68)
                        .addComponent(btnExportarCSVHP, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(65, 65, 65)
                        .addComponent(btnVoltarHP, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(218, 218, 218))
                    .addGroup(pnHistoricoPromocoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(pnHistoricoPromocoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1156, Short.MAX_VALUE))
                        .addComponent(lbResponsavelHP)
                        .addComponent(lbDescricaoPromocaoHP)
                        .addComponent(lbFiltroPesquisaHP)))
                .addContainerGap(842, Short.MAX_VALUE))
        );
        pnHistoricoPromocoesLayout.setVerticalGroup(
            pnHistoricoPromocoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnHistoricoPromocoesLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(lbFiltroPesquisaHP)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lbDescricaoPromocaoHP)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lbResponsavelHP)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnHistoricoPromocoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnImprimirHP, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExportarCSVHP, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLimparHP, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPesquisarHP, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnVoltarHP, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(254, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Histórico de Promoções", pnHistoricoPromocoes);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jTabbedPane1)
                .addGap(978, 978, 978))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnVoltarCDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoltarCDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnVoltarCDActionPerformed

    private void btnPesquisarCDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarCDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnPesquisarCDActionPerformed

    private void btnLimparCDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimparCDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLimparCDActionPerformed

    private void btnExcluirCDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirCDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnExcluirCDActionPerformed

    private void btnEditarCDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarCDActionPerformed
        // TODO add your handling code here:
        editarCupom();
    
    }//GEN-LAST:event_btnEditarCDActionPerformed

    private void btnEditarCPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarCPActionPerformed
        // TODO add your handling code here:
       editarPromocao();
    }//GEN-LAST:event_btnEditarCPActionPerformed

    private void btnExcluirCPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirCPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnExcluirCPActionPerformed

    private void btnLimparCPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimparCPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLimparCPActionPerformed

    private void btnPesquisarCPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarCPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnPesquisarCPActionPerformed

    private void btnVoltarCPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoltarCPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnVoltarCPActionPerformed

    private void btnImprimirHPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirHPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnImprimirHPActionPerformed

    private void btnExportarCSVHPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportarCSVHPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnExportarCSVHPActionPerformed

    private void btnLimparHPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimparHPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLimparHPActionPerformed

    private void btnPesquisarHPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarHPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnPesquisarHPActionPerformed

    private void btnVoltarHPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoltarHPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnVoltarHPActionPerformed

    private void rbtnApenasAtivasHPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtnApenasAtivasHPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rbtnApenasAtivasHPActionPerformed

    private void rbtnAtivasFinalizadasHPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtnAtivasFinalizadasHPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rbtnAtivasFinalizadasHPActionPerformed

    private void cbxResponsavelHPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxResponsavelHPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxResponsavelHPActionPerformed

    private void cbxTipoUsoCDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxTipoUsoCDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxTipoUsoCDActionPerformed

    private void btnSalvarCPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarCPActionPerformed
                // TODO add your handling code here:
        cadastrarPromocao();        
                
    }//GEN-LAST:event_btnSalvarCPActionPerformed

    private void btnSalvarCDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarCDActionPerformed
        // TODO add your handling code here:
        cadastrarCupom();
    }//GEN-LAST:event_btnSalvarCDActionPerformed

    private void cbxPromocoesVinculadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxPromocoesVinculadaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxPromocoesVinculadaActionPerformed

    private void pnCadastroPromocoesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnCadastroPromocoesMouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_pnCadastroPromocoesMouseClicked

    private void tbTabelaCPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbTabelaCPMouseClicked
        // TODO add your handling code here:
        preencherCamposPromocao();
    }//GEN-LAST:event_tbTabelaCPMouseClicked

    private void tbTabelaCDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbTabelaCDMouseClicked
        // TODO add your handling code here:
        preencherCamposCupom();
    }//GEN-LAST:event_tbTabelaCDMouseClicked

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
        java.awt.EventQueue.invokeLater(() -> new TelaPromocoes(usuario).setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscarProdutoCategoriaCP;
    private javax.swing.JButton btnEditarCD;
    private javax.swing.JButton btnEditarCP;
    private javax.swing.JButton btnExcluirCD;
    private javax.swing.JButton btnExcluirCP;
    private javax.swing.JButton btnExportarCSVHP;
    private javax.swing.JButton btnImprimirHP;
    private javax.swing.JButton btnLimparCD;
    private javax.swing.JButton btnLimparCP;
    private javax.swing.JButton btnLimparHP;
    private javax.swing.JButton btnNovoCD;
    private javax.swing.JButton btnNovoCP;
    private javax.swing.JButton btnPesquisarCD;
    private javax.swing.JButton btnPesquisarCP;
    private javax.swing.JButton btnPesquisarHP;
    private javax.swing.JButton btnSalvarCD;
    private javax.swing.JButton btnSalvarCP;
    private javax.swing.JButton btnVoltarCD;
    private javax.swing.JButton btnVoltarCP;
    private javax.swing.JButton btnVoltarHP;
    private javax.swing.JComboBox<String> cbxProdutoCategoriaCP;
    private javax.swing.JComboBox<String> cbxPromocoesVinculada;
    private javax.swing.JComboBox<String> cbxResponsavelHP;
    private javax.swing.JComboBox<String> cbxTipoCupomHP;
    private javax.swing.JComboBox<String> cbxTipoDescontoCD;
    private javax.swing.JComboBox<String> cbxTipoPromocaoCP;
    private javax.swing.JComboBox<String> cbxTipoPromocaoHP;
    private javax.swing.JComboBox<String> cbxTipoUsoCD;
    private javax.swing.JFormattedTextField ftfDataFimCP;
    private javax.swing.JFormattedTextField ftfDataInicioCP;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lbAteHP;
    private javax.swing.JLabel lbCodigoCupomCD;
    private javax.swing.JLabel lbCupomHP;
    private javax.swing.JLabel lbDNdaPromocaoCP;
    private javax.swing.JLabel lbDadosBasicos;
    private javax.swing.JLabel lbDadosCD;
    private javax.swing.JLabel lbDataFimCD;
    private javax.swing.JLabel lbDataFimCP;
    private javax.swing.JLabel lbDataInicioCD;
    private javax.swing.JLabel lbDataInicioCP;
    private javax.swing.JLabel lbDescontoCP;
    private javax.swing.JLabel lbDescricaoPromocaoHP;
    private javax.swing.JLabel lbFiltroPesquisaHP;
    private javax.swing.JLabel lbLimiteClienteCD;
    private javax.swing.JLabel lbObservacoesDescricaoCD;
    private javax.swing.JLabel lbPeriodoEntreHP;
    private javax.swing.JLabel lbPromocoesVinculada;
    private javax.swing.JLabel lbQtdMaxUsosCD;
    private javax.swing.JLabel lbQtdeMinima;
    private javax.swing.JLabel lbRegrasLimitacoesCD;
    private javax.swing.JLabel lbRegrasObservacoesCP;
    private javax.swing.JLabel lbResponsavelHP;
    private javax.swing.JLabel lbStatusCP;
    private javax.swing.JLabel lbStatusHistoricoHP;
    private javax.swing.JLabel lbTPResponsavelHP;
    private javax.swing.JLabel lbTipoDescontoCD;
    private javax.swing.JLabel lbTipoPromocaoCP;
    private javax.swing.JLabel lbTipoPromocaoHP;
    private javax.swing.JLabel lbTipoUsoCD;
    private javax.swing.JLabel lbValorDescontoCD;
    private javax.swing.JLabel lbValorMinimoCD;
    private javax.swing.JLabel lbVigenciaRegrasCD;
    private javax.swing.JLabel lbVigenciaRegrasCP;
    private javax.swing.JPanel pnCadastroPromocoes;
    private javax.swing.JPanel pnCuponsDesconto;
    private javax.swing.JPanel pnHistoricoPromocoes;
    private javax.swing.JRadioButton rbtnApFinalizadasHP;
    private javax.swing.JRadioButton rbtnApenasAtivasHP;
    private javax.swing.JRadioButton rbtnAtivasFinalizadasHP;
    private javax.swing.JRadioButton rbtnStatusAtivoCP;
    private javax.swing.JRadioButton rbtnStatusInativoCP;
    private javax.swing.JSpinner spnDescontoCP;
    private javax.swing.JSpinner spnLimiteClienteCD;
    private javax.swing.JSpinner spnQtdMaxUsosCD;
    private javax.swing.JSpinner spnQtdeMinimaCP;
    private javax.swing.JSpinner spnValorDescontoCD;
    private javax.swing.JSpinner spnValorMinimoCD;
    private javax.swing.JTable tbTabelaCD;
    private javax.swing.JTable tbTabelaCP;
    private javax.swing.JTable tbTabelaHP;
    private javax.swing.JTextField tfCodigoCupomCD;
    private javax.swing.JFormattedTextField tfDataFimCD;
    private javax.swing.JFormattedTextField tfDataInicioCD;
    private javax.swing.JFormattedTextField tfPeriodoAteHP;
    private javax.swing.JFormattedTextField tfPeriodoDeHP;
    private javax.swing.JTextField txDNdaPromocaoCP;
    private javax.swing.JTextArea txtaObservacoesDescricaoCD;
    private javax.swing.JTextArea txtaRegrasUsoCP;
    // End of variables declaration//GEN-END:variables

    private void reencherCamposPromocao() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}

   
