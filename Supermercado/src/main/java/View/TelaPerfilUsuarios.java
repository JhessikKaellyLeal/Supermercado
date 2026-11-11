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

import View.TelaSolicitacao;
import View.TelaSolicitacao;

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
import Model.HistoricosAcessos;
import Model.Permissoes;
import java.util.List;
import java.util.Map;

public class TelaPerfilUsuarios extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(TelaPerfilUsuarios.class.getName());

    private MenuPadrao menu;  
    private Usuario usuario;
    private final UsuarioController usuarioController = new UsuarioController();
     private int idUsuarioSelecionado = -1; 
    private boolean carregandoComboHistorico = false;
    
    
    
    public TelaPerfilUsuarios(Usuario usuario)  {
        initComponents();
        listarUsuariosCombo();
        listarFuncionariosCombo();
        listarHistoricoAcessos();
        listarPermissoesTabela();
         listarUsuariosComboPermissoes();
        listarHistoricoAcessosTabela();
        listarUsuariosTabela();
        listarTiposPermissao();
      
      
        
        this.usuario= usuario;
        this.menu=MenuPadrao.instalar(
        this,
        usuario,
        "perfilUsuarios",
        this::navegar,
        this::dispose
    );
        
        this.setExtendedState(TelaPerfilUsuarios.MAXIMIZED_BOTH);
        
       
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

// ---------------------------------------------- LISTAR NA COMBOBOX -----------------------------------------------------------------------------
private void listarUsuariosCombo() {

    carregandoComboHistorico = true;

    try {

        cbHistoricoAcesso.removeAllItems();

        UsuarioController controller = new UsuarioController();

        List<Usuario> usuarios = controller.listar();

        for (Usuario usuario : usuarios) {
            cbHistoricoAcesso.addItem(usuario.getNome());
        }

    } catch (Exception e) {
        JOptionPane.showMessageDialog(this,
                "Erro ao carregar usuários: " + e.getMessage());
    } finally {
        carregandoComboHistorico = false;
    }
}

private void listarFuncionariosCombo() {

    try {

        cbTipoFuncionario.removeAllItems();

        FuncionarioController controller = new FuncionarioController();

        List<Funcionario> funcionarios = controller.listar();

        for (Funcionario funcionario : funcionarios) {
            cbTipoFuncionario.addItem(funcionario.getNome());
        }

    } catch (SQLException e) {

        JOptionPane.showMessageDialog(
                this,
                "Erro ao carregar funcionários: " + e.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE
        );

    }
}

private int buscarIdUsuarioSelecionado() {

    try {

        String nome = (String) cbTipoFuncionarioAreaPermissoes.getSelectedItem();

        if (nome == null || nome.trim().isEmpty()) {
            return -1;
        }

        UsuarioController controller = new UsuarioController();

        List<Usuario> usuarios = controller.listar();

        for (Usuario usuario : usuarios) {

            if (usuario.getNome() != null
                    && usuario.getNome().equalsIgnoreCase(nome.trim())) {

                return usuario.getIdusuario();
            }
        }

    } catch (SQLException e) {

        JOptionPane.showMessageDialog(
                this,
                "Erro ao buscar usuário: " + e.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE
        );
    }

    return -1;
}

private void listarUsuariosComboPermissoes() {

    cbTipoFuncionarioAreaPermissoes.removeAllItems();

    try {
        UsuarioController controller = new UsuarioController();

        List<Usuario> usuarios = controller.listar();

        for (Usuario usuario : usuarios) {
            cbTipoFuncionarioAreaPermissoes.addItem(usuario.getNome());
        }

    } catch (Exception e) {
        JOptionPane.showMessageDialog(this,
                "Erro ao listar usuários: " + e.getMessage());
        e.printStackTrace();
    }
}
// ---------------------------------------- LISTAR NA TABELA --------------------------------------------------------------------------------------

private void listarHistoricoAcessos() {

    try {

        DefaultTableModel modelo =
                (DefaultTableModel) tbHistoricoAcessos.getModel();

        modelo.setRowCount(0);

        HistoricoAcessoController controller =
                new HistoricoAcessoController();

        List<HistoricosAcessos> lista = controller.listar();

        for (HistoricosAcessos historico : lista) {

            modelo.addRow(new Object[]{
                historico.getIdhistoricoAcessos(),
                historico.getDataAcesso(),
                historico.getHorarioAcesso(),
                historico.getUsuario_idusuario(),
                historico.getTerminal_ip(),
                historico.getDispositivo()
            });
        }

        if (lista.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Nenhum registro de acesso encontrado!",
                    "Consulta",
                    JOptionPane.INFORMATION_MESSAGE
            );
        }

    } catch (SQLException e) {

        JOptionPane.showMessageDialog(
                this,
                "Erro ao listar histórico de acessos: " + e.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE
        );

        e.printStackTrace();
    }
}


private void adicionarUsuarioTabela() throws SQLException {

    DefaultTableModel modelo = new DefaultTableModel();

    modelo.addColumn("ID");
    modelo.addColumn("Nome");
    modelo.addColumn("CPF");
    modelo.addColumn("E-mail");
    modelo.addColumn("Situação");

    List<Usuario> usuarios = usuarioController.listar();

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

private void listarHistoricoAcessosTabela()  {

        try {
            DefaultTableModel modelo = new DefaultTableModel();
            
            HistoricoAcessoController controller =
                    new HistoricoAcessoController();
            
            modelo.addColumn("ID");
            modelo.addColumn("Usuário");
            modelo.addColumn("Data de Acesso");
            modelo.addColumn("Horário");
            modelo.addColumn("Terminal IP");
            modelo.addColumn("Dispositivo");
            
            List<HistoricosAcessos> historicos = controller.listar();
            for (HistoricosAcessos acesso : historicos) {
                
                modelo.addRow(new Object[]{
                    acesso.getIdhistoricoAcessos(),
                    acesso.getUsuario_idusuario(),
                    acesso.getDataAcesso(),
                    acesso.getHorarioAcesso(),
                    acesso.getTerminal_ip(),
                    acesso.getDispositivo()
                });
            }
            tbHistoricoAcessos.setModel(modelo);
            if (historicos.isEmpty()) {
                JOptionPane.showMessageDialog(
                        this,
                        "Nenhum registro de histórico encontrado!",
                        "Consulta",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }   } catch (SQLException ex) {
            System.getLogger(TelaPerfilUsuarios.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
}

private void listarUsuariosTabela()  {

        try {
            DefaultTableModel modelo = new DefaultTableModel();
            
            // Criando as colunas
            modelo.addColumn("ID");
            modelo.addColumn("Nome");
            modelo.addColumn("CPF");
            modelo.addColumn("E-mail");
            modelo.addColumn("Situação");
            
            List<Usuario> usuarios = usuarioController.listar();
            // Preenchendo a tabela
            for (Usuario usuario : usuarios) {
                
                String situacao;
                
                if (usuario.getBloqueio() == 1) {
                    situacao = "Bloqueado";
                } else {
                    situacao = "Ativo";
                }
                
                modelo.addRow(new Object[]{
                    usuario.getIdusuario(),
                    usuario.getNome(),
                    usuario.getCpf(),
                    usuario.getEmail(),
                    situacao
                });
            }
            tbUsuarios.setModel(modelo);
        } catch (SQLException ex) {
            System.getLogger(TelaPerfilUsuarios.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
}

private void listarHistoricoUsuario() {

    try {

        if (cbHistoricoAcesso.getSelectedItem() == null) {
            return;
        }

        String nomeUsuario =
                cbHistoricoAcesso.getSelectedItem().toString();

        HistoricoAcessoController controller =
                new HistoricoAcessoController();

        List<HistoricosAcessos> historicos =
                controller.listarPorNomeUsuario(nomeUsuario);

        DefaultTableModel modelo =
                (DefaultTableModel) tbHistoricoAcessos.getModel();

        modelo.setRowCount(0);

        for (HistoricosAcessos historico : historicos) {

            modelo.addRow(new Object[]{
                historico.getIdhistoricoAcessos(),
                historico.getDataAcesso(),
                historico.getHorarioAcesso(),
                historico.getTerminal_ip(),
                historico.getDispositivo(),
                historico.getUsuario_idusuario()
            });
        }

    } catch (SQLException e) {

        JOptionPane.showMessageDialog(this,
                "Erro ao listar histórico: " + e.getMessage());
    }
}

// ------------------------- CADASTRO DE DADOS ----------------------------------------------------------------------------------------------------
private int obterPermissao(javax.swing.JCheckBox checkBox) {
    return checkBox.isSelected() ? 1 : 0;
}

private void cadastrarPermissoes() {

    try {

        int idUsuario = buscarIdUsuarioSelecionado();

        if (idUsuario <= 0) {
            JOptionPane.showMessageDialog(this,
                    "Selecione um usuário válido!");
            return;
        }

        String tipo = cbTipoPermissao.getSelectedItem() == null
                ? ""
                : cbTipoPermissao.getSelectedItem().toString().trim();

        if (tipo.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Selecione o tipo de permissão!");
            return;
        }

        // Criar Model
        Permissoes permissoes = new Permissoes();

        permissoes.setTipo(tipo);
        permissoes.setTelaProdutos(chbTelaProdutos.isSelected() ? 1 : 0);
        permissoes.setTelaEstoque(chbTelaEstoque.isSelected() ? 1 : 0);
        permissoes.setTelaCompras(chbTelaCompras.isSelected() ? 1 : 0);
        permissoes.setTelaClientes(chbTelaClientes.isSelected() ? 1 : 0);
        permissoes.setTelaPromocoes(chbTelaPromocoes.isSelected() ? 1 : 0);
        permissoes.setTelaCaixa(chbTelaCaixa.isSelected() ? 1 : 0);
        permissoes.setTelaVendas(chbTelaVendas.isSelected() ? 1 : 0);
        permissoes.setTelaPDV(chbTelaPDV.isSelected() ? 1 : 0);
        permissoes.setTelaPedidos(chbTelaPedidos.isSelected() ? 1 : 0);
        permissoes.setTelaEntregas(chbTelaEntregas.isSelected() ? 1 : 0);
        permissoes.setTelaRh(chbTelaRH.isSelected() ? 1 : 0);
        permissoes.setTelaFinaceiro(chbTelaFinaceiro.isSelected() ? 1 : 0);
        permissoes.setTelaRelatorios(chbTelaRelatorios.isSelected() ? 1 : 0);

        // Salvar permissões e relacionar ao usuário
        PermissoesController controller = new PermissoesController();

        controller.salvarPermissoesUsuario(permissoes, idUsuario);

        JOptionPane.showMessageDialog(this,
                "Permissões cadastradas com sucesso!");

        listarPermissoesTabela();

    } catch (SQLException e) {

        JOptionPane.showMessageDialog(this,
                "Erro ao cadastrar permissões: " + e.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE
        );

        e.printStackTrace();
    }
}

private void listarTiposPermissao() {

    cbTipoPermissao.removeAllItems();

    cbTipoPermissao.addItem("Administrador");
    cbTipoPermissao.addItem("Gerente");
    cbTipoPermissao.addItem("Vendedor");
    cbTipoPermissao.addItem("Caixa");
    cbTipoPermissao.addItem("Estoquista");
    cbTipoPermissao.addItem("Entregador");
    cbTipoPermissao.addItem("RH");
    cbTipoPermissao.addItem("Financeiro");
}

private void atualizarPermissoes() {

    try {

        if (cbTipoFuncionario.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(this,
                    "Selecione um usuário!");
            return;
        }

        Permissoes permissoes = new Permissoes();

        permissoes.setTipo(cbTipoFuncionario.getSelectedItem().toString());

        permissoes.setTelaProdutos(chbTelaProdutos.isSelected() ? 1 : 0);
        permissoes.setTelaEstoque(chbTelaEstoque.isSelected() ? 1 : 0);
        permissoes.setTelaCompras(chbTelaCompras.isSelected() ? 1 : 0);
        permissoes.setTelaClientes(chbTelaClientes.isSelected() ? 1 : 0);
        permissoes.setTelaPromocoes(chbTelaPromocoes.isSelected() ? 1 : 0);
        permissoes.setTelaCaixa(chbTelaCaixa.isSelected() ? 1 : 0);
        permissoes.setTelaVendas(chbTelaVendas.isSelected() ? 1 : 0);
        permissoes.setTelaPDV(chbTelaPDV.isSelected() ? 1 : 0);
        permissoes.setTelaPedidos(chbTelaPedidos.isSelected() ? 1 : 0);
        permissoes.setTelaEntregas(chbTelaEntregas.isSelected() ? 1 : 0);
        permissoes.setTelaRh(chbTelaRH.isSelected() ? 1 : 0);
        permissoes.setTelaFinaceiro(chbTelaFinaceiro.isSelected() ? 1 : 0);
        permissoes.setTelaRelatorios(chbTelaRelatorios.isSelected() ? 1 : 0);

        PermissoesController controller = new PermissoesController();

        // Atualiza as permissões
        controller.atualizar(permissoes);

        JOptionPane.showMessageDialog(this,
                "Permissões atualizadas com sucesso!");

    } catch (Exception e) {

        JOptionPane.showMessageDialog(this,
                "Erro ao atualizar permissões: " + e.getMessage());

        e.printStackTrace();
    }
}

private void listarUsuarios() throws SQLException {

    adicionarUsuarioTabela();

}

private int buscarIdFuncionario(String nome) {

    try {

        FuncionarioController controller = new FuncionarioController();

        List<Funcionario> funcionarios = controller.listar();

        for (Funcionario funcionario : funcionarios) {

            if (funcionario.getNome().equalsIgnoreCase(nome.trim())) {
                return funcionario.getIdFuncionario();
            }
        }

    } catch (SQLException e) {

        JOptionPane.showMessageDialog(
                this,
                "Erro ao buscar funcionário: " + e.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE
        );
    }

    return -1;
}



private void listarPermissoesTabela() {

    DefaultTableModel modelo = new DefaultTableModel() {

        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

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

    try {

        PermissoesController controller = new PermissoesController();

        List<Object[]> lista = controller.listarPermissoesComUsuarios();

        if (lista != null && !lista.isEmpty()) {

            for (Object[] dados : lista) {

                if (dados == null || dados.length < 14) {
                    continue;
                }

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

        } else {

            System.out.println("Nenhuma permissão encontrada no banco.");
        }

    } catch (SQLException e) {

        JOptionPane.showMessageDialog(this,
                "Erro ao listar permissões: " + e.getMessage());

        e.printStackTrace();
    }

    tbPermissoes.setModel(modelo);
}


private void salvarPermissoesFuncionario() {

    try {

        if (cbTipoFuncionarioAreaPermissoes.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(this,
                    "Selecione um funcionário!");
            return;
        }

        String nomeFuncionario =
                cbTipoFuncionarioAreaPermissoes.getSelectedItem().toString();

        FuncionarioController funcionarioController =
                new FuncionarioController();

        int idFuncionario = -1;

        for (Funcionario funcionario : funcionarioController.listar()) {

            if (funcionario.getNome().equalsIgnoreCase(nomeFuncionario)) {
                idFuncionario = funcionario.getIdFuncionario();
                break;
            }
        }

        if (idFuncionario == -1) {
            JOptionPane.showMessageDialog(this,
                    "Funcionário não encontrado!");
            return;
        }

        Permissoes permissoes = obterDadosPermissoes();

        PermissoesController controller = new PermissoesController();

        controller.salvarPermissoesFuncionario(
                permissoes, idFuncionario);

        JOptionPane.showMessageDialog(this,
                "Permissões salvas com sucesso!");

    } catch (SQLException e) {

        JOptionPane.showMessageDialog(this,
                "Erro ao salvar permissões: " + e.getMessage());
    }
}



private void cadastrarUsuario() {

    try {

        String nome = tfNomecompletoPerfil.getText().trim();
        String cpf = ffCPFCadastroUsuario.getText().trim();
        String email = tfEmailPerfil.getText().trim();
        String senha = new String(pfSenhaUsuario.getPassword());

        if (nome.isEmpty() || cpf.isEmpty()
                || email.isEmpty() || senha.isEmpty()) {

            JOptionPane.showMessageDialog(this,
                    "Preencha todos os campos!");
            return;
        }

        if (cbTipoFuncionario.getSelectedItem() == null) {

            JOptionPane.showMessageDialog(this,
                    "Selecione um funcionário!");
            return;
        }

        String nomeFuncionario =
                cbTipoFuncionario.getSelectedItem().toString();

        int idFuncionario = buscarIdFuncionario(nomeFuncionario);

        if (idFuncionario == -1) {

            JOptionPane.showMessageDialog(this,
                    "Funcionário não encontrado!");
            return;
        }

        int bloqueio = chbBloqueio.isSelected() ? 1 : 0;

        // Criar objeto Usuario
        Usuario usuario = new Usuario();

        usuario.setNome(nome);
        usuario.setCpf(cpf);
        usuario.setEmail(email);
        usuario.setSenha(senha);
        usuario.setBloqueio(bloqueio);
        usuario.setFuncionario_idFuncionario(idFuncionario);

        // Cadastrar usuário
        UsuarioController controller = new UsuarioController();

        controller.cadastrar(usuario);

        JOptionPane.showMessageDialog(this,
                "Usuário cadastrado com sucesso!");

        limparCamposUsuario();
        listarUsuarios();

    } catch (SQLException e) {

        JOptionPane.showMessageDialog(this,
                "Erro ao cadastrar usuário: " + e.getMessage());

        e.printStackTrace();
    }
}



// -------------------- LISTAGEM DE DADOS ---------------------------------------------------------------------------------------------------------


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

private String converterPermissao(Object valor) {

    if (valor == null) {
        return "Não";
    }

    return Integer.parseInt(valor.toString()) == 1
            ? "Sim"
            : "Não";
}


private void carregarPermissoesFuncionario() {

    // Limpar os checkboxes antes de carregar
    limparPermissoes();

    if (cbTipoFuncionarioAreaPermissoes.getSelectedItem() == null) {
        return;
    }

    try {

        // Recuperar o nome selecionado
        String nomeFuncionario =
                cbTipoFuncionarioAreaPermissoes.getSelectedItem().toString();

        // Buscar ID do funcionário
        int idFuncionario = buscarIdFuncionario(nomeFuncionario);

        if (idFuncionario == -1) {
            JOptionPane.showMessageDialog(this,
                    "Funcionário não encontrado!");
            return;
        }

        PermissoesController controller = new PermissoesController();

        // Buscar permissões vinculadas ao funcionário
        Permissoes p = controller.buscarPorFuncionario(idFuncionario);

        if (p == null) {
            return;
        }

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

    } catch (Exception e) {

        JOptionPane.showMessageDialog(this,
                "Erro ao carregar permissões: " + e.getMessage());

        e.printStackTrace();
    }
}


private javax.swing.JLabel lblUsuarioHistorico;

private Permissoes obterDadosPermissoes() {

    Permissoes permissoes = new Permissoes();

    permissoes.setTipo(cbTipoFuncionario.getSelectedItem().toString());

    permissoes.setTelaProdutos(chbTelaProdutos.isSelected() ? 1 : 0);
    permissoes.setTelaEstoque(chbTelaEstoque.isSelected() ? 1 : 0);
    permissoes.setTelaCompras(chbTelaCompras.isSelected() ? 1 : 0);
    permissoes.setTelaClientes(chbTelaClientes.isSelected() ? 1 : 0);
    permissoes.setTelaPromocoes(chbTelaPromocoes.isSelected() ? 1 : 0);
    permissoes.setTelaCaixa(chbTelaCaixa.isSelected() ? 1 : 0);
    permissoes.setTelaVendas(chbTelaVendas.isSelected() ? 1 : 0);
    permissoes.setTelaPDV(chbTelaPDV.isSelected() ? 1 : 0);
    permissoes.setTelaPedidos(chbTelaPedidos.isSelected() ? 1 : 0);
    permissoes.setTelaEntregas(chbTelaEntregas.isSelected() ? 1 : 0);
    permissoes.setTelaRh(chbTelaRH.isSelected() ? 1 : 0);
    permissoes.setTelaFinaceiro(chbTelaFinaceiro.isSelected() ? 1 : 0);
    permissoes.setTelaRelatorios(chbTelaRelatorios.isSelected() ? 1 : 0);

    return permissoes;
}

private void limparCamposUsuario() {

   tfNomecompletoPerfil.setText("");
    ffCPFCadastroUsuario.setText("");
    tfEmailPerfil.setText("");
    pfSenhaUsuario.setText("");

    chbBloqueio.setSelected(false);

}

private void carregarDadosUsuario() {

    int linha = tbUsuarios.getSelectedRow();

    if (linha == -1) {
        return;
    }

    // Capturar ID do usuário
    idUsuarioSelecionado = Integer.parseInt(
            tbUsuarios.getValueAt(linha, 0).toString()
    );

    // Carregar informações nos campos
    tfNomecompletoPerfil.setText(
            tbUsuarios.getValueAt(linha, 1).toString()
    );

    ffCPFCadastroUsuario.setText(
            tbUsuarios.getValueAt(linha, 2).toString()
    );

    tfEmailPerfil.setText(
            tbUsuarios.getValueAt(linha, 3).toString()
    );

    // Carregar situação do usuário
    String situacao = tbUsuarios.getValueAt(linha, 4).toString();

    chbBloqueio.setSelected(situacao.equalsIgnoreCase("Bloqueado"));

    // Senha não é recuperada pela tabela
    pfSenhaUsuario.setText("");

}


private void atualizarUsuario() {

    try {

        // Verificar se existe usuário selecionado
        if (idUsuarioSelecionado == -1) {

            JOptionPane.showMessageDialog(this,
                    "Selecione um usuário na tabela!");
            return;
        }

        // Capturar dados
        String nome = tfNomecompletoPerfil.getText().trim();
        String cpf = ffCPFCadastroUsuario.getText().trim();
        String email = tfEmailPerfil.getText().trim();
        String senha = new String(pfSenhaUsuario.getPassword());

        // Validar campos
        if (nome.isEmpty() || cpf.isEmpty() || email.isEmpty()) {

            JOptionPane.showMessageDialog(this,
                    "Preencha nome, CPF e e-mail!");
            return;
        }

        // Verificar funcionário
        if (cbTipoFuncionario.getSelectedItem() == null) {

            JOptionPane.showMessageDialog(this,
                    "Selecione um funcionário!");
            return;
        }

        String nomeFuncionario =
                cbTipoFuncionario.getSelectedItem().toString();

        int idFuncionario = buscarIdFuncionario(nomeFuncionario);

        if (idFuncionario == -1) {

            JOptionPane.showMessageDialog(this,
                    "Funcionário não encontrado!");
            return;
        }

        // Situação do usuário
        int bloqueio = chbBloqueio.isSelected() ? 1 : 0;

        // Criar objeto Usuario
        Usuario usuario = new Usuario();

        usuario.setIdusuario(idUsuarioSelecionado);
        usuario.setNome(nome);
        usuario.setCpf(cpf);
        usuario.setEmail(email);

        // Manter senha atual se o campo estiver vazio
        if (!senha.isEmpty()) {
            usuario.setSenha(senha);
        } else {
            UsuarioController consulta = new UsuarioController();
            Usuario usuarioAtual = consulta.buscarPorId(idUsuarioSelecionado);

            if (usuarioAtual != null) {
                usuario.setSenha(usuarioAtual.getSenha());
            }
        }

        usuario.setBloqueio(bloqueio);
        usuario.setFuncionario_idFuncionario(idFuncionario);

        // Atualizar usuário
        UsuarioController controller = new UsuarioController();

        controller.atualizar(usuario);

        JOptionPane.showMessageDialog(this,
                "Usuário atualizado com sucesso!");

        idUsuarioSelecionado = -1;

        limparCamposUsuario();
        listarUsuarios();

    } catch (Exception e) {

        JOptionPane.showMessageDialog(this,
                "Erro ao atualizar usuário: " + e.getMessage());

        e.printStackTrace();
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
        btnSalvarPermissoes = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbPermissoes = new javax.swing.JTable();
        lbFuncionarioAreaPermissoes1 = new javax.swing.JLabel();
        cbTipoPermissao = new javax.swing.JComboBox<>();
        areaPerfilUsuario = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbUsuarios = new javax.swing.JTable();
        chbBloqueio = new javax.swing.JCheckBox();
        lbNomeCompletoPerfil = new javax.swing.JLabel();
        lbEmailPerfil = new javax.swing.JLabel();
        tfNomecompletoPerfil = new javax.swing.JTextField();
        tfEmailPerfil = new javax.swing.JTextField();
        lbSenhaPerfil = new javax.swing.JLabel();
        cbTipoFuncionario = new javax.swing.JComboBox<>();
        lbTipoFuncionario = new javax.swing.JLabel();
        btnSalvarUsuario = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        ffCPFCadastroUsuario = new javax.swing.JFormattedTextField();
        pfSenhaUsuario = new javax.swing.JPasswordField();
        btnAtualizarUsuario = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        areaPerfil.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        areaPerfil.setMaximumSize(new java.awt.Dimension(1920, 1080));
        areaPerfil.setPreferredSize(new java.awt.Dimension(1700, 800));

        areaHistoricoAcesso.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N

        cbHistoricoAcesso.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        cbHistoricoAcesso.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pesquisar Usuario", " " }));
        cbHistoricoAcesso.addActionListener(this::cbHistoricoAcessoActionPerformed);

        tbHistoricoAcessos.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tbHistoricoAcessos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Usuario", "Data Acesso", "Horario Acesso", "Terminal", "Dispositivo"
            }
        ));
        jScrollPane4.setViewportView(tbHistoricoAcessos);

        javax.swing.GroupLayout areaHistoricoAcessoLayout = new javax.swing.GroupLayout(areaHistoricoAcesso);
        areaHistoricoAcesso.setLayout(areaHistoricoAcessoLayout);
        areaHistoricoAcessoLayout.setHorizontalGroup(
            areaHistoricoAcessoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(areaHistoricoAcessoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(areaHistoricoAcessoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 1512, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbHistoricoAcesso, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(357, Short.MAX_VALUE))
        );
        areaHistoricoAcessoLayout.setVerticalGroup(
            areaHistoricoAcessoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(areaHistoricoAcessoLayout.createSequentialGroup()
                .addContainerGap(120, Short.MAX_VALUE)
                .addComponent(cbHistoricoAcesso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 630, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(74, 74, 74))
        );

        areaPerfil.addTab("Histórico de acessos", areaHistoricoAcesso);

        areaPermissoes.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N

        cbTipoFuncionarioAreaPermissoes.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        cbTipoFuncionarioAreaPermissoes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbTipoFuncionarioAreaPermissoes.addActionListener(this::cbTipoFuncionarioAreaPermissoesActionPerformed);

        chbTelaEstoque.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        chbTelaEstoque.setText("Tela Estoque");

        lbFuncionarioAreaPermissoes.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        lbFuncionarioAreaPermissoes.setText("Usuario");

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
        btnAtualizaAreaPermissoes.setText("Altualizar");
        btnAtualizaAreaPermissoes.addActionListener(this::btnAtualizaAreaPermissoesActionPerformed);

        btnSalvarPermissoes.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        btnSalvarPermissoes.setText("Salvar");
        btnSalvarPermissoes.addActionListener(this::btnSalvarPermissoesActionPerformed);

        tbPermissoes.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tbPermissoes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Usuário", "Tela Produtos", "Tela Relatorio", "Tela Financeiro", "Tela RH", "Tela Entregas", "Tela Pedidos", "Tela PDV", "Tela Venda", "Tela Caixa", "Tela Promoções", "Tela Clientes", "Tela Compras", "Tela Estoque"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tbPermissoes);

        lbFuncionarioAreaPermissoes1.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        lbFuncionarioAreaPermissoes1.setText("Habilite as telas Permitidas para este usuário");

        cbTipoPermissao.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout areaPermissoesLayout = new javax.swing.GroupLayout(areaPermissoes);
        areaPermissoes.setLayout(areaPermissoesLayout);
        areaPermissoesLayout.setHorizontalGroup(
            areaPermissoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(areaPermissoesLayout.createSequentialGroup()
                .addGap(74, 74, 74)
                .addGroup(areaPermissoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(areaPermissoesLayout.createSequentialGroup()
                        .addComponent(chbTelaProdutos)
                        .addGap(98, 98, 98)
                        .addComponent(chbTelaPDV))
                    .addGroup(areaPermissoesLayout.createSequentialGroup()
                        .addGroup(areaPermissoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(chbTelaEstoque)
                            .addComponent(chbTelaCompras)
                            .addComponent(chbTelaClientes)
                            .addComponent(chbTelaPromocoes)
                            .addComponent(chbTelaCaixa)
                            .addComponent(chbTelaVendas)
                            .addComponent(btnSalvarPermissoes, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(areaPermissoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(areaPermissoesLayout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addComponent(btnAtualizaAreaPermissoes))
                            .addGroup(areaPermissoesLayout.createSequentialGroup()
                                .addGap(80, 80, 80)
                                .addGroup(areaPermissoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(chbTelaRelatorios)
                                    .addGroup(areaPermissoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(chbTelaEntregas)
                                        .addComponent(chbTelaPedidos)
                                        .addComponent(chbTelaRH)
                                        .addComponent(chbTelaFinaceiro))))))
                    .addGroup(areaPermissoesLayout.createSequentialGroup()
                        .addComponent(lbFuncionarioAreaPermissoes, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbTipoFuncionarioAreaPermissoes, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(134, 134, 134)
                        .addComponent(cbTipoPermissao, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lbFuncionarioAreaPermissoes1, javax.swing.GroupLayout.PREFERRED_SIZE, 514, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1334, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(467, Short.MAX_VALUE))
        );
        areaPermissoesLayout.setVerticalGroup(
            areaPermissoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, areaPermissoesLayout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addGroup(areaPermissoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbTipoFuncionarioAreaPermissoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbFuncionarioAreaPermissoes, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbTipoPermissao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addComponent(lbFuncionarioAreaPermissoes1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(areaPermissoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chbTelaProdutos)
                    .addComponent(chbTelaPDV))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(areaPermissoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chbTelaEstoque)
                    .addComponent(chbTelaPedidos))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(areaPermissoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chbTelaCompras)
                    .addComponent(chbTelaEntregas))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(areaPermissoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chbTelaClientes)
                    .addComponent(chbTelaRH))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(areaPermissoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chbTelaPromocoes)
                    .addComponent(chbTelaFinaceiro))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(areaPermissoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chbTelaRelatorios)
                    .addComponent(chbTelaCaixa))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(chbTelaVendas)
                .addGap(32, 32, 32)
                .addGroup(areaPermissoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSalvarPermissoes, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAtualizaAreaPermissoes, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(138, Short.MAX_VALUE))
        );

        areaPerfil.addTab("Área Permissões", areaPermissoes);

        areaPerfilUsuario.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N

        tbUsuarios.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
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
        tbUsuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbUsuariosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbUsuarios);

        chbBloqueio.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        chbBloqueio.setText("Bloqueio");

        lbNomeCompletoPerfil.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        lbNomeCompletoPerfil.setText("Nome completo");

        lbEmailPerfil.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        lbEmailPerfil.setText("E-mail completo");

        tfNomecompletoPerfil.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N

        tfEmailPerfil.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N

        lbSenhaPerfil.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        lbSenhaPerfil.setText("Senha");

        cbTipoFuncionario.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        cbTipoFuncionario.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        lbTipoFuncionario.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        lbTipoFuncionario.setText("Tipo de Funcionário");

        btnSalvarUsuario.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        btnSalvarUsuario.setText("Salvar");
        btnSalvarUsuario.addActionListener(this::btnSalvarUsuarioActionPerformed);

        jLabel1.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel1.setText("CPF");

        ffCPFCadastroUsuario.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N

        pfSenhaUsuario.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        pfSenhaUsuario.setText("jPasswordField1");

        btnAtualizarUsuario.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        btnAtualizarUsuario.setText("Atualizar");
        btnAtualizarUsuario.addActionListener(this::btnAtualizarUsuarioActionPerformed);

        javax.swing.GroupLayout areaPerfilUsuarioLayout = new javax.swing.GroupLayout(areaPerfilUsuario);
        areaPerfilUsuario.setLayout(areaPerfilUsuarioLayout);
        areaPerfilUsuarioLayout.setHorizontalGroup(
            areaPerfilUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(areaPerfilUsuarioLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(areaPerfilUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(areaPerfilUsuarioLayout.createSequentialGroup()
                        .addGroup(areaPerfilUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(areaPerfilUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(tfNomecompletoPerfil)
                                .addComponent(lbSenhaPerfil)
                                .addComponent(lbEmailPerfil)
                                .addComponent(lbNomeCompletoPerfil)
                                .addComponent(tfEmailPerfil, javax.swing.GroupLayout.DEFAULT_SIZE, 653, Short.MAX_VALUE)
                                .addComponent(jLabel1)
                                .addComponent(ffCPFCadastroUsuario)
                                .addComponent(pfSenhaUsuario))
                            .addGroup(areaPerfilUsuarioLayout.createSequentialGroup()
                                .addComponent(cbTipoFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(131, 131, 131)
                                .addComponent(chbBloqueio, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 783, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(94, 94, 94))
                    .addGroup(areaPerfilUsuarioLayout.createSequentialGroup()
                        .addGroup(areaPerfilUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(areaPerfilUsuarioLayout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(btnSalvarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(144, 144, 144)
                                .addComponent(btnAtualizarUsuario))
                            .addComponent(lbTipoFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(1379, Short.MAX_VALUE))))
        );
        areaPerfilUsuarioLayout.setVerticalGroup(
            areaPerfilUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(areaPerfilUsuarioLayout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addComponent(lbTipoFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addGroup(areaPerfilUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chbBloqueio, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbTipoFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbNomeCompletoPerfil)
                .addGap(18, 18, 18)
                .addComponent(tfNomecompletoPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(ffCPFCadastroUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lbEmailPerfil)
                .addGap(18, 18, 18)
                .addComponent(tfEmailPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbSenhaPerfil)
                .addGap(18, 18, 18)
                .addComponent(pfSenhaUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(92, 92, 92)
                .addGroup(areaPerfilUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSalvarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAtualizarUsuario))
                .addGap(245, 245, 245))
            .addGroup(areaPerfilUsuarioLayout.createSequentialGroup()
                .addGap(117, 117, 117)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 460, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        areaPerfil.addTab("Cadastro do Usuário", areaPerfilUsuario);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(areaPerfil, javax.swing.GroupLayout.DEFAULT_SIZE, 1875, Short.MAX_VALUE)
                .addGap(19, 19, 19))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(areaPerfil, javax.swing.GroupLayout.DEFAULT_SIZE, 930, Short.MAX_VALUE)
                .addGap(24, 24, 24))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbHistoricoAcessoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbHistoricoAcessoActionPerformed
        // TODO add your handling code here:
        listarHistoricoUsuario();
      
       
       
    }//GEN-LAST:event_cbHistoricoAcessoActionPerformed

    private void chbTelaPDVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbTelaPDVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chbTelaPDVActionPerformed

    private void chbTelaVendasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbTelaVendasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chbTelaVendasActionPerformed

    private void btnSalvarPermissoesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarPermissoesActionPerformed
        // TODO add your handling code here:
         cadastrarPermissoes();
         listarPermissoesTabela();
       
    }//GEN-LAST:event_btnSalvarPermissoesActionPerformed

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

    private void tbUsuariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbUsuariosMouseClicked
        // TODO add your handling code here:
        carregarDadosUsuario();
    }//GEN-LAST:event_tbUsuariosMouseClicked

    private void btnAtualizarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtualizarUsuarioActionPerformed
        // TODO add your handling code here:
        atualizarUsuario();
    }//GEN-LAST:event_btnAtualizarUsuarioActionPerformed

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
    private javax.swing.JPanel areaHistoricoAcesso;
    private javax.swing.JTabbedPane areaPerfil;
    private javax.swing.JPanel areaPerfilUsuario;
    private javax.swing.JPanel areaPermissoes;
    private javax.swing.JButton btnAtualizaAreaPermissoes;
    private javax.swing.JButton btnAtualizarUsuario;
    private javax.swing.JButton btnSalvarPermissoes;
    private javax.swing.JButton btnSalvarUsuario;
    private javax.swing.JComboBox<String> cbHistoricoAcesso;
    private javax.swing.JComboBox<String> cbTipoFuncionario;
    private javax.swing.JComboBox<String> cbTipoFuncionarioAreaPermissoes;
    private javax.swing.JComboBox<String> cbTipoPermissao;
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
    private javax.swing.JFormattedTextField ffCPFCadastroUsuario;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel lbEmailPerfil;
    private javax.swing.JLabel lbFuncionarioAreaPermissoes;
    private javax.swing.JLabel lbFuncionarioAreaPermissoes1;
    private javax.swing.JLabel lbNomeCompletoPerfil;
    private javax.swing.JLabel lbSenhaPerfil;
    private javax.swing.JLabel lbTipoFuncionario;
    private javax.swing.JPasswordField pfSenhaUsuario;
    private javax.swing.JTable tbHistoricoAcessos;
    private javax.swing.JTable tbPermissoes;
    private javax.swing.JTable tbUsuarios;
    private javax.swing.JTextField tfEmailPerfil;
    private javax.swing.JTextField tfNomecompletoPerfil;
    // End of variables declaration//GEN-END:variables

 
 
}
