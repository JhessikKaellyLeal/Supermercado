/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;
import Conection.Conexao;
import Control.FuncionarioController;
import Control.UsuarioController;
import Model.Usuario;
import View.AlterarSenha;
import View.AlterarSenha;
import View.MenuPadrao;
import View.MenuPadrao;
import View.MenuPadrao;
import View.RecuperacaoSenha;
import View.RecuperacaoSenha;
import View.TelaCaixa;
import View.TelaCaixa;
import View.TelaEntregas;
import View.TelaEntregas;
import View.TelaEstoque;
import View.TelaEstoque;
import View.TelaFinanceiro;
import View.TelaFinanceiro;
import View.TelaFornecedoresTerceirizados;
import View.TelaFornecedoresTerceirizados;
import View.TelaGerenciamentoProdutos;
import View.TelaGerenciamentoProdutos;
import View.TelaHome;
import View.TelaHome;
import View.TelaLoginUsuarios;
import View.TelaLoginUsuarios;
import View.TelaPdvRealizacao;
import View.TelaPdvRealizacao;
import View.TelaPedidos;
import View.TelaPedidos;
import View.TelaPedidos;
import View.TelaProdutos;
import View.TelaProdutos;
import View.TelaSolicitacao;
import View.TelaSolicitacao;
import View.TelaVendaPagamentos;
import View.TelaVendaPagamentos;
import View.TelaVendas;
import View.TelaVendas;
import View.Tela_Funcionarios_RH;
import View.Tela_Funcionarios_RH;
import View.TelasClientes;
import View.TelasClientes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import Control.HistoricoAcessoController;
import Control.PermissoesController;
import Model.Funcionario;
import Model.Permissoes;
import java.util.List;

public class TelaPerfilUsuarios extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(TelaPerfilUsuarios.class.getName());

    private MenuPadrao menu;  
    private Usuario usuario;
  
    public TelaPerfilUsuarios(Usuario usuario) {
        initComponents();
        listarFuncionariosCombo();
        listarTiposUsuarioCombo();
        listarHistoricoAcessos();
        listarPermissoesTabela();
        cadastrarPermissoes();
        listarUsuariosTabela();
        
        this.usuario= usuario;
        this.menu=MenuPadrao.instalar(
        this,
        usuario,
        "perfilUsuarios",
        this::navegar,
        this::dispose
    );
        
        this.setExtendedState(TelaProdutos.MAXIMIZED_BOTH);
        
       
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

private void listarFuncionariosCombo() {

    cbTipoFuncionarioAreaPermissoes.removeAllItems();

    FuncionarioController controller = new FuncionarioController();

    List<Funcionario> funcionarios = controller.listar();

    for (Funcionario funcionario : funcionarios) {
        cbTipoFuncionarioAreaPermissoes.addItem(funcionario.getNome());
    }
    
        cbHistoricoAcesso.removeAllItems();

        List<Funcionario> lista = controller.listar();

        for (Funcionario funcionario : lista) {
            cbHistoricoAcesso.addItem(funcionario.getNome());
        }
}

private void listarTiposUsuarioCombo() {

    cbTipoUsuario.removeAllItems();

    cbTipoUsuario.addItem("Administrador");
    cbTipoUsuario.addItem("Gerente");
    cbTipoUsuario.addItem("Funcionário");
    cbTipoUsuario.addItem("Caixa");
    cbTipoUsuario.addItem("Estoquista");
}

private void listarHistoricoAcessos() {

   
    try {

        DefaultTableModel modelo =
                (DefaultTableModel) tbHistoricoAcessos.getModel();

        modelo.setRowCount(0);

        HistoricoAcessoController controller =
                new HistoricoAcessoController();

        for (Object[] dados : controller.listarTodos()) {

            modelo.addRow(dados);

        }

    } catch (Exception e) {

        JOptionPane.showMessageDialog(
                this,
                "Erro ao listar histórico de acessos: " + e.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE
        );

    }
}
 
private void cadastrarUsuario() {

    String nome = tfNomeUsuario.getText().trim();

    String cpf = ffCPFUsuario.getText()
            .replaceAll("[^0-9]", "");

    String email = tfEmailPerfil.getText().trim();

    String senha = new String(pfSenhaUsuario.getPassword());

    int bloqueio = chbBloqueio.isSelected() ? 1 : 0;

    // VALIDAR CAMPOS
    if (nome.isEmpty() || cpf.isEmpty()
            || email.isEmpty() || senha.isEmpty()) {

        JOptionPane.showMessageDialog(this,
                "Preencha todos os campos obrigatórios!");

        return;
    }

    if (cpf.length() != 11) {

        JOptionPane.showMessageDialog(this,
                "Informe um CPF válido!");

        return;
    }

    if (!email.matches("^[\\w.+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {

        JOptionPane.showMessageDialog(this,
                "Informe um e-mail válido!");

        return;
    }

    if (senha.length() < 6) {

        JOptionPane.showMessageDialog(this,
                "A senha deve possuir pelo menos 6 caracteres!");

        return;
    }

    // CADASTRAR NO BANCO
    boolean resultado = usuarioController.cadastrar(
            nome,
            cpf,
            email,
            senha,
            bloqueio
    );

    if (resultado) {

        JOptionPane.showMessageDialog(this,
                "Usuário cadastrado com sucesso!");

        limparCamposUsuario();

        listarUsuarios();

    } else {

        JOptionPane.showMessageDialog(this,
                "Não foi possível cadastrar o usuário!");

    }
}

private void listarUsuarios() {

    adicionarUsuarioTabela();

}



private void limparCamposUsuario() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

private void adicionarUsuarioTabela() {

    DefaultTableModel modelo = new DefaultTableModel();

    modelo.addColumn("ID");
    modelo.addColumn("Nome");
    modelo.addColumn("CPF");
    modelo.addColumn("E-mail");
    modelo.addColumn("Situação");

    Iterable<Usuario> usuarios = usuarioController.listar();

    for (Usuario usuario : usuarios) {

        Object[] linha = {
            usuario.getIdusuario(),
            usuario.getNome(),
            usuario.getCpf(),
            usuario.getEmail(),
            usuario.getBloqueio()
        };

        modelo.addRow(linha);
    }

    tbUsuarios.setModel(modelo);
}

private int obterPermissao(javax.swing.JCheckBox checkBox) {
    return checkBox.isSelected() ? 1 : 0;
}

private void cadastrarPermissoes() {

    if (cbTipoUsuario.getSelectedItem() == null) {
        JOptionPane.showMessageDialog(this, "Selecione um tipo de usuário!");
        return;
    }

    String tipo = cbTipoUsuario.getSelectedItem().toString().trim();

    if (tipo.isEmpty() || tipo.equalsIgnoreCase("Selecione")) {
        JOptionPane.showMessageDialog(this, "Selecione um tipo válido!");
        return;
    }

    Permissoes p = new Permissoes();

    p.setTipo(tipo);
    p.setTelaProdutos(obterPermissao(chbTelaProdutos));
    p.setTelaEstoque(obterPermissao(chbTelaEstoque));
    p.setTelaCompras(obterPermissao(chbTelaCompras));
    p.setTelaClientes(obterPermissao(chbTelaClientes));
    p.setTelaPromocoes(obterPermissao(chbTelaPromocoes));
    p.setTelaCaixa(obterPermissao(chbTelaCaixa));
    p.setTelaVendas(obterPermissao(chbTelaVendas));
    p.setTelaPDV(obterPermissao(chbTelaPDV));
    p.setTelaPedidos(obterPermissao(chbTelaPedidos));
    p.setTelaEntregas(obterPermissao(chbTelaEntregas));
    p.setTelaRh(obterPermissao(chbTelaRH));
    p.setTelaFinaceiro(obterPermissao(chbTelaFinaceiro));
    p.setTelaRelatorios(obterPermissao(chbTelaRelatorios));

    PermissoesController controller = new PermissoesController();

    if (controller.cadastrar(p)) {

        JOptionPane.showMessageDialog(this,
                "Permissões salvas com sucesso!");

        listarPermissoesTabela();

    } else {

        JOptionPane.showMessageDialog(this,
                "Não foi possível salvar as permissões!");
    }
}

private void atualizarPermissoes() {

    String tipo = cbTipoUsuario.getSelectedItem().toString();

    PermissoesController controller = new PermissoesController();

    Permissoes p = new Permissoes();

    p.setTipo(tipo);

    p.setTelaProdutos(obterPermissao(chbTelaProdutos));
    p.setTelaEstoque(obterPermissao(chbTelaEstoque));
    p.setTelaCompras(obterPermissao(chbTelaCompras));
    p.setTelaClientes(obterPermissao(chbTelaClientes));
    p.setTelaPromocoes(obterPermissao(chbTelaPromocoes));
    p.setTelaCaixa(obterPermissao(chbTelaCaixa));
    p.setTelaVendas(obterPermissao(chbTelaVendas));
    p.setTelaPDV(obterPermissao(chbTelaPDV));
    p.setTelaPedidos(obterPermissao(chbTelaPedidos));
    p.setTelaEntregas(obterPermissao(chbTelaEntregas));
    p.setTelaRh(obterPermissao(chbTelaRH));
    p.setTelaFinaceiro(obterPermissao(chbTelaFinaceiro));
    p.setTelaRelatorios(obterPermissao(chbTelaRelatorios));

    if (controller.atualizar(p)) {
        JOptionPane.showMessageDialog(this,
                "Permissões atualizadas com sucesso!");
    } else {
        JOptionPane.showMessageDialog(this,
                "Erro ao atualizar permissões!");
    }
}

private void carregarPermissoes(Permissoes p) {

    chbTelaProdutos.setSelected(p.getTelaProdutos() == 1);
    chbTelaEstoque.setSelected(p.getTelaEstoque() == 1);
    chbTelaCompras.setSelected(p.getTelaCompras() == 1);
    chbTelaClientes.setSelected(p.getTelaClientes() == 1);
    chbTelaPromocoes.setSelected(p.getTelaPromocoes() == 1);
    chbTelaCaixa.setSelected(p.getTelaCaixa() == 1);
    chbTelaVendas.setSelected(p.getTelaVendas() == 1);
    chbTelaPDV.setSelected(p.getTelaPDV() == 1);
    chbTelaPedidos.setSelected(p.getTelaPedidos() == 1);
    chbTelaEntregas.setSelected(p.getTelaEntregas() == 1);
    chbTelaRH.setSelected(p.getTelaRh() == 1);
    chbTelaFinaceiro.setSelected(p.getTelaFinaceiro() == 1);
    chbTelaRelatorios.setSelected(p.getTelaRelatorios() == 1);

}

private void limparPermissoes() {

    chbTelaProdutos.setSelected(false);
    chbTelaEstoque.setSelected(false);
    chbTelaCompras.setSelected(false);
    chbTelaClientes.setSelected(false);
    chbTelaPromocoes.setSelected(false);
    chbTelaCaixa.setSelected(false);
    chbTelaVendas.setSelected(false);
    chbTelaPDV.setSelected(false);
    chbTelaPedidos.setSelected(false);
    chbTelaEntregas.setSelected(false);
    chbTelaRH.setSelected(false);
    chbTelaFinaceiro.setSelected(false);
    chbTelaRelatorios.setSelected(false);
}

private void listarPermissoesTabela() {

    DefaultTableModel modelo = new DefaultTableModel() {

        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    // Criando as colunas
    modelo.addColumn("Nome do Usuário");
    modelo.addColumn("Produtos");
    modelo.addColumn("Estoque");
    modelo.addColumn("Compras");
    modelo.addColumn("Clientes");
    modelo.addColumn("Promoções");
    modelo.addColumn("Caixa");
    modelo.addColumn("Vendas");
    modelo.addColumn("PDV");
    modelo.addColumn("Pedidos");
    modelo.addColumn("Entregas");
    modelo.addColumn("RH");
    modelo.addColumn("Financeiro");
    modelo.addColumn("Relatórios");

    PermissoesController controller = new PermissoesController();

    List<Object[]> lista = controller.listarPermissoesComUsuarios();

    // Preenchendo a JTable
    for (Object[] dados : lista) {

        modelo.addRow(new Object[]{
            dados[0],
            converterPermissao(dados[1]),
            converterPermissao(dados[2]),
            converterPermissao(dados[3]),
            converterPermissao(dados[4]),
            converterPermissao(dados[5]),
            converterPermissao(dados[6]),
            converterPermissao(dados[7]),
            converterPermissao(dados[8]),
            converterPermissao(dados[9]),
            converterPermissao(dados[10]),
            converterPermissao(dados[11]),
            converterPermissao(dados[12]),
            converterPermissao(dados[13])
        });
    }

    // Aplicando o modelo na tabela existente
    tbPermissoes.setModel(modelo);

    System.out.println("Registros exibidos na tabela: " + lista.size());
}

private String converterPermissao(Object valor) {

    if (valor == null) {
        return "Não";
    }

    if (valor instanceof Number) {
        return ((Number) valor).intValue() == 1 ? "Sim" : "Não";
    }

    return "1".equals(valor.toString()) ? "Sim" : "Não";
}



private final UsuarioController usuarioController =
        new UsuarioController();
/**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        areaPerfil = new javax.swing.JTabbedPane();
        areaHistoricoAcesso = new javax.swing.JPanel();
        cbHistoricoAcesso = new javax.swing.JComboBox<>();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbHistoricoAcessos = new javax.swing.JTable();
        areaPermissoes = new javax.swing.JPanel();
        cbTipoFuncionarioAreaPermissoes = new javax.swing.JComboBox<>();
        chbTelaEstoque = new javax.swing.JCheckBox();
        lbFuncionarioAreaPermissoes = new javax.swing.JLabel();
        chbTelaCompras = new javax.swing.JCheckBox();
        chbTelaClientes = new javax.swing.JCheckBox();
        chbTelaPromocoes = new javax.swing.JCheckBox();
        chbTelaProdutos = new javax.swing.JCheckBox();
        chbTelaCaixa = new javax.swing.JCheckBox();
        chbTelaVendas = new javax.swing.JCheckBox();
        chbTelaPDV = new javax.swing.JCheckBox();
        chbTelaPedidos = new javax.swing.JCheckBox();
        chbTelaEntregas = new javax.swing.JCheckBox();
        chbTelaRH = new javax.swing.JCheckBox();
        chbTelaFinaceiro = new javax.swing.JCheckBox();
        chbTelaRelatorios = new javax.swing.JCheckBox();
        btnAtualizaAreaPermissoes = new javax.swing.JButton();
        btnSalvarAreaAcesso = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbPermissoes = new javax.swing.JTable();
        areaPerfilUsuario = new javax.swing.JPanel();
        lbNomePerfil = new javax.swing.JLabel();
        lbNomeCompletoPerfil = new javax.swing.JLabel();
        lbEmailPerfil = new javax.swing.JLabel();
        tfNomecompletoPerfil = new javax.swing.JTextField();
        tfEmailPerfil = new javax.swing.JTextField();
        lbSenhaPerfil = new javax.swing.JLabel();
        lbTelefonePerfil = new javax.swing.JLabel();
        tfCargoPerfil = new javax.swing.JTextField();
        ffTelefonePerfil = new javax.swing.JFormattedTextField();
        btnSalvarAlteracoesPerfil = new javax.swing.JButton();
        cbTipoUsuario = new javax.swing.JComboBox<>();
        lbTipoUsuario = new javax.swing.JLabel();
        areaCadastroDeUsuario = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbUsuarios = new javax.swing.JTable();
        lbNomeUsuario = new javax.swing.JLabel();
        tfNomeUsuario = new javax.swing.JTextField();
        lbCPFUsuario = new javax.swing.JLabel();
        lbEmailUsuario = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        lbSenhaUsuario = new javax.swing.JLabel();
        chbBloqueio = new javax.swing.JCheckBox();
        btnSalvarUsuario = new javax.swing.JButton();
        ffCPFUsuario = new javax.swing.JFormattedTextField();
        pfSenhaUsuario = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        areaPerfil.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        areaPerfil.setMaximumSize(new java.awt.Dimension(1920, 1080));
        areaPerfil.setPreferredSize(new java.awt.Dimension(1700, 800));

        areaHistoricoAcesso.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N

        cbHistoricoAcesso.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        cbHistoricoAcesso.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbHistoricoAcesso.addActionListener(this::cbHistoricoAcessoActionPerformed);

        tbHistoricoAcessos.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        tbHistoricoAcessos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Data Acesso", "Horario Acesso", "Terminal", "Dispositivo"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane4.setViewportView(tbHistoricoAcessos);

        javax.swing.GroupLayout areaHistoricoAcessoLayout = new javax.swing.GroupLayout(areaHistoricoAcesso);
        areaHistoricoAcesso.setLayout(areaHistoricoAcessoLayout);
        areaHistoricoAcessoLayout.setHorizontalGroup(
            areaHistoricoAcessoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(areaHistoricoAcessoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 1512, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(159, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, areaHistoricoAcessoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cbHistoricoAcesso, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(98, 98, 98))
        );
        areaHistoricoAcessoLayout.setVerticalGroup(
            areaHistoricoAcessoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(areaHistoricoAcessoLayout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addComponent(cbHistoricoAcesso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 746, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );

        areaPerfil.addTab("Histórico de acessos", areaHistoricoAcesso);

        areaPermissoes.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N

        cbTipoFuncionarioAreaPermissoes.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        cbTipoFuncionarioAreaPermissoes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbTipoFuncionarioAreaPermissoes.addActionListener(this::cbTipoFuncionarioAreaPermissoesActionPerformed);

        chbTelaEstoque.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        chbTelaEstoque.setText("Tela Estoque");

        lbFuncionarioAreaPermissoes.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        lbFuncionarioAreaPermissoes.setText("Funcionário");

        chbTelaCompras.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        chbTelaCompras.setText("Tela  Compras");

        chbTelaClientes.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        chbTelaClientes.setText("Tela Clientes");

        chbTelaPromocoes.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        chbTelaPromocoes.setText("Tela Promoções");

        chbTelaProdutos.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        chbTelaProdutos.setText("Tela Produtos");

        chbTelaCaixa.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        chbTelaCaixa.setText("Tela Caixa");

        chbTelaVendas.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        chbTelaVendas.setText("Tela Vendas");
        chbTelaVendas.addActionListener(this::chbTelaVendasActionPerformed);

        chbTelaPDV.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        chbTelaPDV.setText("Tela PDV");
        chbTelaPDV.addActionListener(this::chbTelaPDVActionPerformed);

        chbTelaPedidos.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        chbTelaPedidos.setText("Tela Pedidos");

        chbTelaEntregas.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        chbTelaEntregas.setText("Tela Entregas");

        chbTelaRH.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        chbTelaRH.setText("Tela RH");

        chbTelaFinaceiro.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        chbTelaFinaceiro.setText("Tela Finaceiro");

        chbTelaRelatorios.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        chbTelaRelatorios.setText("Tela Relatorio");

        btnAtualizaAreaPermissoes.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        btnAtualizaAreaPermissoes.setIcon(new javax.swing.ImageIcon("C:\\Users\\AntônioVieira\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\EDITAR.png")); // NOI18N
        btnAtualizaAreaPermissoes.setText("Altualizar");
        btnAtualizaAreaPermissoes.addActionListener(this::btnAtualizaAreaPermissoesActionPerformed);

        btnSalvarAreaAcesso.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        btnSalvarAreaAcesso.setIcon(new javax.swing.ImageIcon("C:\\Users\\AntônioVieira\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\salve-.png")); // NOI18N
        btnSalvarAreaAcesso.setText("Salvar");
        btnSalvarAreaAcesso.addActionListener(this::btnSalvarAreaAcessoActionPerformed);

        tbPermissoes.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        tbPermissoes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Nome", "Permissão", "Permissão"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tbPermissoes);

        javax.swing.GroupLayout areaPermissoesLayout = new javax.swing.GroupLayout(areaPermissoes);
        areaPermissoes.setLayout(areaPermissoesLayout);
        areaPermissoesLayout.setHorizontalGroup(
            areaPermissoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(areaPermissoesLayout.createSequentialGroup()
                .addGroup(areaPermissoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(areaPermissoesLayout.createSequentialGroup()
                        .addGap(139, 139, 139)
                        .addGroup(areaPermissoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(chbTelaVendas)
                            .addGroup(areaPermissoesLayout.createSequentialGroup()
                                .addGroup(areaPermissoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(chbTelaProdutos)
                                    .addComponent(chbTelaEstoque)
                                    .addComponent(chbTelaCompras)
                                    .addComponent(chbTelaClientes)
                                    .addComponent(chbTelaPromocoes)
                                    .addComponent(chbTelaCaixa))
                                .addGap(306, 306, 306)
                                .addGroup(areaPermissoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(chbTelaEntregas)
                                    .addComponent(chbTelaPedidos)
                                    .addComponent(chbTelaPDV)
                                    .addComponent(chbTelaRelatorios)
                                    .addComponent(chbTelaFinaceiro)
                                    .addComponent(chbTelaRH)))
                            .addGroup(areaPermissoesLayout.createSequentialGroup()
                                .addComponent(btnSalvarAreaAcesso, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(385, 385, 385)
                                .addComponent(btnAtualizaAreaPermissoes))))
                    .addGroup(areaPermissoesLayout.createSequentialGroup()
                        .addGap(74, 74, 74)
                        .addComponent(lbFuncionarioAreaPermissoes, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbTipoFuncionarioAreaPermissoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(areaPermissoesLayout.createSequentialGroup()
                        .addGap(74, 74, 74)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1586, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        areaPermissoesLayout.setVerticalGroup(
            areaPermissoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, areaPermissoesLayout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addGroup(areaPermissoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbTipoFuncionarioAreaPermissoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbFuncionarioAreaPermissoes, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(areaPermissoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chbTelaProdutos)
                    .addComponent(chbTelaPDV))
                .addGap(42, 42, 42)
                .addGroup(areaPermissoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chbTelaEstoque)
                    .addComponent(chbTelaPedidos))
                .addGap(46, 46, 46)
                .addGroup(areaPermissoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chbTelaCompras)
                    .addComponent(chbTelaEntregas))
                .addGap(40, 40, 40)
                .addGroup(areaPermissoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chbTelaClientes)
                    .addComponent(chbTelaRH))
                .addGap(34, 34, 34)
                .addGroup(areaPermissoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chbTelaPromocoes)
                    .addComponent(chbTelaFinaceiro))
                .addGap(39, 39, 39)
                .addGroup(areaPermissoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chbTelaCaixa)
                    .addComponent(chbTelaRelatorios))
                .addGap(40, 40, 40)
                .addGroup(areaPermissoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(areaPermissoesLayout.createSequentialGroup()
                        .addComponent(chbTelaVendas)
                        .addGap(29, 29, 29)
                        .addComponent(btnSalvarAreaAcesso, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnAtualizaAreaPermissoes, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(109, Short.MAX_VALUE))
        );

        areaPerfil.addTab("Área Permissões", areaPermissoes);

        areaPerfilUsuario.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N

        lbNomePerfil.setText("jLabel1");

        lbNomeCompletoPerfil.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        lbNomeCompletoPerfil.setText("Nome completo");

        lbEmailPerfil.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        lbEmailPerfil.setText("E-mail completo");

        tfNomecompletoPerfil.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N

        tfEmailPerfil.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N

        lbSenhaPerfil.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        lbSenhaPerfil.setText("Senha");

        lbTelefonePerfil.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        lbTelefonePerfil.setText("Telefone");

        btnSalvarAlteracoesPerfil.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        btnSalvarAlteracoesPerfil.setText("Salvar Alteracões");

        cbTipoUsuario.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        cbTipoUsuario.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        lbTipoUsuario.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        lbTipoUsuario.setText("Tipo de Usuário");

        javax.swing.GroupLayout areaPerfilUsuarioLayout = new javax.swing.GroupLayout(areaPerfilUsuario);
        areaPerfilUsuario.setLayout(areaPerfilUsuarioLayout);
        areaPerfilUsuarioLayout.setHorizontalGroup(
            areaPerfilUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(areaPerfilUsuarioLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(areaPerfilUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbNomeCompletoPerfil)
                    .addComponent(tfNomecompletoPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, 425, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(areaPerfilUsuarioLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(areaPerfilUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfEmailPerfil)
                            .addGroup(areaPerfilUsuarioLayout.createSequentialGroup()
                                .addGroup(areaPerfilUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbEmailPerfil)
                                    .addGroup(areaPerfilUsuarioLayout.createSequentialGroup()
                                        .addComponent(lbTipoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(40, 40, 40)
                                        .addComponent(cbTipoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(btnSalvarAlteracoesPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addGap(176, 176, 176)
                .addGroup(areaPerfilUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, areaPerfilUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lbTelefonePerfil)
                        .addComponent(ffTelefonePerfil, javax.swing.GroupLayout.PREFERRED_SIZE, 888, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(areaPerfilUsuarioLayout.createSequentialGroup()
                        .addComponent(lbSenhaPerfil)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(45, 45, 45))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, areaPerfilUsuarioLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(tfCargoPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, 933, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(areaPerfilUsuarioLayout.createSequentialGroup()
                .addGap(78, 78, 78)
                .addComponent(lbNomePerfil)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        areaPerfilUsuarioLayout.setVerticalGroup(
            areaPerfilUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(areaPerfilUsuarioLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(areaPerfilUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, areaPerfilUsuarioLayout.createSequentialGroup()
                        .addComponent(lbSenhaPerfil)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfCargoPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42)
                        .addComponent(lbTelefonePerfil)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ffTelefonePerfil, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, areaPerfilUsuarioLayout.createSequentialGroup()
                        .addComponent(lbNomePerfil)
                        .addGap(28, 28, 28)
                        .addComponent(lbNomeCompletoPerfil)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfNomecompletoPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(lbEmailPerfil)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfEmailPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(55, 55, 55)
                        .addGroup(areaPerfilUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbTipoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbTipoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(193, 193, 193)
                .addComponent(btnSalvarAlteracoesPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(273, Short.MAX_VALUE))
        );

        areaPerfil.addTab("Perfil do Usuário", areaPerfilUsuario);

        areaCadastroDeUsuario.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N

        tbUsuarios.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        tbUsuarios.setModel(new javax.swing.table.DefaultTableModel(
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
                "Nome", "CPF", "Email", "Senha", "Bloqueio"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tbUsuarios);

        lbNomeUsuario.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        lbNomeUsuario.setText("Nome:");

        tfNomeUsuario.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N

        lbCPFUsuario.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        lbCPFUsuario.setText("CPF:");

        lbEmailUsuario.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        lbEmailUsuario.setText("Email:");

        jTextField3.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N

        lbSenhaUsuario.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        lbSenhaUsuario.setText("Senha:");

        chbBloqueio.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        chbBloqueio.setText("Bloqueio");

        btnSalvarUsuario.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        btnSalvarUsuario.setIcon(new javax.swing.ImageIcon("C:\\Users\\AntônioVieira\\Documents\\Supermercado\\Supermercado\\src\\main\\resources\\assets\\salve-.png")); // NOI18N
        btnSalvarUsuario.setText("Salvar");
        btnSalvarUsuario.addActionListener(this::btnSalvarUsuarioActionPerformed);

        ffCPFUsuario.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N

        pfSenhaUsuario.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N

        javax.swing.GroupLayout areaCadastroDeUsuarioLayout = new javax.swing.GroupLayout(areaCadastroDeUsuario);
        areaCadastroDeUsuario.setLayout(areaCadastroDeUsuarioLayout);
        areaCadastroDeUsuarioLayout.setHorizontalGroup(
            areaCadastroDeUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(areaCadastroDeUsuarioLayout.createSequentialGroup()
                .addGroup(areaCadastroDeUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(areaCadastroDeUsuarioLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1471, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(areaCadastroDeUsuarioLayout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(areaCadastroDeUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lbNomeUsuario)
                            .addComponent(lbCPFUsuario)
                            .addComponent(lbEmailUsuario))
                        .addGap(18, 18, 18)
                        .addGroup(areaCadastroDeUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField3, javax.swing.GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE)
                            .addComponent(tfNomeUsuario, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(ffCPFUsuario))
                        .addGroup(areaCadastroDeUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(areaCadastroDeUsuarioLayout.createSequentialGroup()
                                .addGap(163, 163, 163)
                                .addComponent(lbSenhaUsuario)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(pfSenhaUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(areaCadastroDeUsuarioLayout.createSequentialGroup()
                                .addGap(158, 158, 158)
                                .addComponent(chbBloqueio))
                            .addGroup(areaCadastroDeUsuarioLayout.createSequentialGroup()
                                .addGap(149, 149, 149)
                                .addComponent(btnSalvarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(200, Short.MAX_VALUE))
        );
        areaCadastroDeUsuarioLayout.setVerticalGroup(
            areaCadastroDeUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(areaCadastroDeUsuarioLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(areaCadastroDeUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbNomeUsuario)
                    .addComponent(tfNomeUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbSenhaUsuario)
                    .addComponent(pfSenhaUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(areaCadastroDeUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbCPFUsuario)
                    .addComponent(ffCPFUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chbBloqueio))
                .addGap(37, 37, 37)
                .addGroup(areaCadastroDeUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbEmailUsuario)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSalvarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(108, 108, 108)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 506, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        areaPerfil.addTab("Cadastro de Usuário", areaCadastroDeUsuario);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(areaPerfil, javax.swing.GroupLayout.DEFAULT_SIZE, 1677, Short.MAX_VALUE)
                .addGap(19, 19, 19))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(areaPerfil, javax.swing.GroupLayout.DEFAULT_SIZE, 870, Short.MAX_VALUE)
                .addGap(24, 24, 24))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbHistoricoAcessoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbHistoricoAcessoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbHistoricoAcessoActionPerformed

    private void chbTelaPDVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbTelaPDVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chbTelaPDVActionPerformed

    private void chbTelaVendasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbTelaVendasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chbTelaVendasActionPerformed

    private void btnSalvarAreaAcessoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarAreaAcessoActionPerformed
        // TODO add your handling code here:
        cadastrarPermissoes();
        listarPermissoesTabela();
     
    }//GEN-LAST:event_btnSalvarAreaAcessoActionPerformed

    private void btnAtualizaAreaPermissoesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtualizaAreaPermissoesActionPerformed
        // TODO add your handling code here:
        atualizarPermissoes();
        listarPermissoesTabela();
    }//GEN-LAST:event_btnAtualizaAreaPermissoesActionPerformed

    private void cbTipoFuncionarioAreaPermissoesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbTipoFuncionarioAreaPermissoesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbTipoFuncionarioAreaPermissoesActionPerformed

    private void btnSalvarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarUsuarioActionPerformed
        // TODO add your handling code here:
         cadastrarUsuario();
    }//GEN-LAST:event_btnSalvarUsuarioActionPerformed

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
        java.awt.EventQueue.invokeLater(() -> new TelaPerfilUsuarios(usuario).setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel areaCadastroDeUsuario;
    private javax.swing.JPanel areaHistoricoAcesso;
    private javax.swing.JTabbedPane areaPerfil;
    private javax.swing.JPanel areaPerfilUsuario;
    private javax.swing.JPanel areaPermissoes;
    private javax.swing.JButton btnAtualizaAreaPermissoes;
    private javax.swing.JButton btnSalvarAlteracoesPerfil;
    private javax.swing.JButton btnSalvarAreaAcesso;
    private javax.swing.JButton btnSalvarUsuario;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JComboBox<String> cbHistoricoAcesso;
    private javax.swing.JComboBox<String> cbTipoFuncionarioAreaPermissoes;
    private javax.swing.JComboBox<String> cbTipoUsuario;
    private javax.swing.JCheckBox chbBloqueio;
    private javax.swing.JCheckBox chbTelaCaixa;
    private javax.swing.JCheckBox chbTelaClientes;
    private javax.swing.JCheckBox chbTelaCompras;
    private javax.swing.JCheckBox chbTelaEntregas;
    private javax.swing.JCheckBox chbTelaEstoque;
    private javax.swing.JCheckBox chbTelaFinaceiro;
    private javax.swing.JCheckBox chbTelaPDV;
    private javax.swing.JCheckBox chbTelaPedidos;
    private javax.swing.JCheckBox chbTelaProdutos;
    private javax.swing.JCheckBox chbTelaPromocoes;
    private javax.swing.JCheckBox chbTelaRH;
    private javax.swing.JCheckBox chbTelaRelatorios;
    private javax.swing.JCheckBox chbTelaVendas;
    private javax.swing.JFormattedTextField ffCPFUsuario;
    private javax.swing.JFormattedTextField ffTelefonePerfil;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JLabel lbCPFUsuario;
    private javax.swing.JLabel lbEmailPerfil;
    private javax.swing.JLabel lbEmailUsuario;
    private javax.swing.JLabel lbFuncionarioAreaPermissoes;
    private javax.swing.JLabel lbNomeCompletoPerfil;
    private javax.swing.JLabel lbNomePerfil;
    private javax.swing.JLabel lbNomeUsuario;
    private javax.swing.JLabel lbSenhaPerfil;
    private javax.swing.JLabel lbSenhaUsuario;
    private javax.swing.JLabel lbTelefonePerfil;
    private javax.swing.JLabel lbTipoUsuario;
    private javax.swing.JPasswordField pfSenhaUsuario;
    private javax.swing.JTable tbHistoricoAcessos;
    private javax.swing.JTable tbPermissoes;
    private javax.swing.JTable tbUsuarios;
    private javax.swing.JTextField tfCargoPerfil;
    private javax.swing.JTextField tfEmailPerfil;
    private javax.swing.JTextField tfNomeUsuario;
    private javax.swing.JTextField tfNomecompletoPerfil;
    // End of variables declaration//GEN-END:variables

   
    
 
}
