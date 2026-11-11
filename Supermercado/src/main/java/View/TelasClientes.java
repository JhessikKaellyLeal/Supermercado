/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import Model.Usuario;
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
import Control.EnderecoController;
import Model.Cliente;
import Model.Endereco;
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

private ClienteController controller = new ClienteController();
    
private int larguraOriginal;
private int alturaOriginal;

private boolean redimensionamentoConfigurado = false;
    
     
  public TelasClientes(Usuario usuario) {

    initComponents();

    this.usuario = usuario;

    this.menu = MenuPadrao.instalar(
        this,
        usuario,
        "clientes",
        this::navegar,
        this::dispose
    );

    this.setExtendedState(TelasClientes.MAXIMIZED_BOTH);
    configurarRedimensionamento();
    carregarClientesConsulta();
    configurarPlaceholderPesquisa();
    
    
    // Retira o painel do local original
java.awt.Container pai =
        painelDetalhesCliente.getParent();

if (pai != null) {
    pai.remove(painelDetalhesCliente);
    pai.revalidate();
    pai.repaint();
}
// Cria a janela flutuante
janelaDetalhes = new javax.swing.JDialog(
        this,
        "Detalhes do cliente",
        false
);
janelaDetalhes.setDefaultCloseOperation(
        javax.swing.JDialog.HIDE_ON_CLOSE
);
// Coloca o painel dentro da janela
janelaDetalhes.setContentPane(painelDetalhesCliente);
janelaDetalhes.setSize(430, 550);
janelaDetalhes.setResizable(false);
janelaDetalhes.setLocationRelativeTo(this);
painelEnderecoFlutuante.setVisible(false);
permitirMoverPainel(painelEnderecoFlutuante);
cbStatusConsulta.setSelectedItem("Ativo");
configurarSelecaoTabelaEdicao();
}
   
  private void configurarRedimensionamento() {

    if (redimensionamentoConfigurado) {
        return;
    }

    redimensionamentoConfigurado = true;

    SwingUtilities.invokeLater(() -> {

        // Área central disponível após instalar o MenuPadrao
        Container area = abasClientes.getParent();

        if (area == null) {
            redimensionamentoConfigurado = false;
            return;
        }

        area.addComponentListener(new java.awt.event.ComponentAdapter() {

            @Override
            public void componentResized(
                    java.awt.event.ComponentEvent evt) {

                redimensionarAbas();
            }
        });

        redimensionarAbas();
    });
}
  private void redimensionarAbas() {

    int largura = abasClientes.getWidth();
    int altura = abasClientes.getHeight();

    if (largura <= 0 || altura <= 0) {
        return;
    }

    // Referência de tamanho para a área central
    double escalaX = largura / 1660.0;
    double escalaY = altura / 930.0;

    double escala = Math.min(escalaX, escalaY);

    // Evita aumentar ou diminuir exageradamente
    escala = Math.max(0.65, Math.min(1.0, escala));

    redimensionarComponentes(painelCadastro, escala);
    redimensionarComponentes(painelConsulta, escala);
    redimensionarComponentes(painelEdicao, escala);
    redimensionarComponentes(painelHistorico, escala);
    redimensionarComponentes(painelPedidosRelatorio, escala);

    abasClientes.revalidate();
    abasClientes.repaint();
}
  private void redimensionarComponentes(
        Container painel, double escala) {

    for (Component componente : painel.getComponents()) {

        Dimension tamanho = componente.getPreferredSize();

        if (tamanho.width > 0 && tamanho.height > 0) {

            Dimension novoTamanho = new Dimension(
                Math.max(1, (int) (tamanho.width * escala)),
                Math.max(1, (int) (tamanho.height * escala))
            );

            componente.setPreferredSize(novoTamanho);
        }

        Font fonte = componente.getFont();

        if (fonte != null) {
            componente.setFont(
                fonte.deriveFont(
                    Math.max(9f, fonte.getSize2D() * (float) escala)
                )
            );
        }

        if (componente instanceof Container) {
            redimensionarComponentes(
                (Container) componente, escala
            );
        }
    }
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





private void cadastrarCliente() {

    try {

        // Capturar os dados dos campos
        String nome = txtNome.getText().trim();
        String cpf = ftfCPF.getText().replaceAll("[^0-9]", "");
        String rg = tfRG.getText().trim();
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

        // Status: 1 = Ativo | 0 = Inativo
        String statusSelecionado = cbStatusCadastro.getSelectedItem() != null
                ? cbStatusCadastro.getSelectedItem().toString()
                : "";

        int status = statusSelecionado.equalsIgnoreCase("Ativo") ? 1 : 0;

        // Validar campos obrigatórios
        if (nome.isEmpty() || cpf.isEmpty() || rg.isEmpty()
                || dataNascimento.isEmpty() || genero.isEmpty()
                || email.isEmpty() || telefone.isEmpty()
                || senha.isEmpty() || statusSelecionado.isEmpty()) {

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

        // Validar telefone
        if (telefone.length() < 10 || telefone.length() > 11) {

            JOptionPane.showMessageDialog(this,
                    "Informe um telefone válido!",
                    "Atenção",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Criar Model
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

        // Cadastrar
        ClienteController controller = new ClienteController();

        controller.cadastrar(cliente);

        JOptionPane.showMessageDialog(this,
                "Cliente cadastrado com sucesso!",
                "Sucesso",
                JOptionPane.INFORMATION_MESSAGE);

        limparCamposCadastro();
        carregarClientesConsulta();

    } catch (Exception e) {

        JOptionPane.showMessageDialog(this,
                "Erro ao cadastrar cliente: " + e.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE);

        e.printStackTrace();
    }
}

 
 private void limparCamposCadastro() {

    // Limpar campos de texto
    txtNome.setText("");
    ftfCPF.setValue(null);
    tfRG.setText("");
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
 
 ////////////////////////////////////////////////
 ///TELA CONSULTA
 ////////////////////////////////////////////
 
private void carregarClientesConsulta() {

    DefaultTableModel modelo =
            (DefaultTableModel) tabelaConsultaClientes.getModel();

    // Mantém a tabela vazia
    modelo.setRowCount(0);

    lblMensagemConsulta.setText("");
}



private void pesquisarClientesConsulta() {

    try {

        String pesquisa = txtPesquisaCliente.getText().trim();

        if (pesquisa.isEmpty()
                || pesquisa.equals("Digite o nome, CPF ou código...")) {

            JOptionPane.showMessageDialog(this,
                    "Digite o nome, CPF ou código do cliente.",
                    "Pesquisa de clientes",
                    JOptionPane.INFORMATION_MESSAGE);

            txtPesquisaCliente.requestFocus();
            return;
        }

        Integer status = null;

        String textoStatus = cbStatusConsulta.getSelectedItem() != null
                ? cbStatusConsulta.getSelectedItem().toString()
                : "Todos";

        if (textoStatus.equalsIgnoreCase("Ativo")) {
            status = 1;
        } else if (textoStatus.equalsIgnoreCase("Inativo")) {
            status = 0;
        }

        // Listar clientes pelo Controller
        ClienteController controller = new ClienteController();
        List<Cliente> clientes = controller.listar();

        // Atualizar tabela
        DefaultTableModel modelo =
                (DefaultTableModel) tabelaConsultaClientes.getModel();

        modelo.setRowCount(0);

        for (Cliente cliente : clientes) {

            boolean correspondePesquisa =
                    String.valueOf(cliente.getIdcliente()).contains(pesquisa)
                    || cliente.getNome().toLowerCase().contains(pesquisa.toLowerCase())
                    || cliente.getCpf().contains(pesquisa);

            boolean correspondeStatus =
                    status == null || cliente.getStatus() == status;

            if (correspondePesquisa && correspondeStatus) {

                modelo.addRow(new Object[]{
                    cliente.getIdcliente(),
                    cliente.getNome(),
                    cliente.getCpf(),
                    cliente.getTelefone(),
                    cliente.getEmail(),
                    cliente.getStatus() == 1 ? "Ativo" : "Inativo"
                });
            }
        }

        // Mensagem de resultado
        if (modelo.getRowCount() == 0) {

            lblMensagemConsulta.setText("Nenhum cliente encontrado.");

        } else {

            lblMensagemConsulta.setText(
                    modelo.getRowCount() + " cliente(s) encontrado(s).");
        }

    } catch (Exception e) {

        JOptionPane.showMessageDialog(this,
                "Erro ao pesquisar clientes: " + e.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE);

        e.printStackTrace();
    }
}



private void configurarPlaceholderPesquisa() {
    String placeholder = "Digite o nome, CPF ou código...";
    txtPesquisaCliente.setText(placeholder);
    txtPesquisaCliente.setForeground(java.awt.Color.GRAY);
    txtPesquisaCliente.addFocusListener(new java.awt.event.FocusAdapter() {
        @Override
        public void focusGained(java.awt.event.FocusEvent e) {

            if (txtPesquisaCliente.getText().equals(placeholder)) {
                txtPesquisaCliente.setText("");
                txtPesquisaCliente.setForeground(java.awt.Color.BLACK);
            }
        }
        @Override
        public void focusLost(java.awt.event.FocusEvent e) {
            if (txtPesquisaCliente.getText().trim().isEmpty()) {
                txtPesquisaCliente.setText(placeholder);
                txtPesquisaCliente.setForeground(java.awt.Color.GRAY);
            }
        }
    });
}



private void visualizarDetalhesCliente() {

    try {

        int linha = tabelaConsultaClientes.getSelectedRow();

        if (linha == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Selecione um cliente na tabela.",
                    "Visualizar detalhes",
                    JOptionPane.INFORMATION_MESSAGE
            );
            return;
        }

        // Obter ID do cliente selecionado
        int idcliente = Integer.parseInt(
                tabelaConsultaClientes.getValueAt(linha, 0).toString()
        );

        // Buscar cliente
        ClienteController clienteController = new ClienteController();

        Cliente cliente = clienteController.buscarPorId(idcliente);

        if (cliente == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Não foi possível localizar esse cliente.",
                    "Visualizar detalhes",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        // Preencher dados pessoais
        lblDetalheCodigo.setText("Código: " + cliente.getIdcliente());
        lblDetalheNome.setText("Nome: " + cliente.getNome());
        lblDetalheCPF.setText("CPF: " + cliente.getCpf());
        lblDetalheRG.setText("RG: " + cliente.getRg());

        lblDetalheNascimento.setText(
                "Data de nascimento: "
                + formatarDataBrasileira(cliente.getDataNascimento())
        );

        lblDetalheGenero.setText("Gênero: " + cliente.getGenero());

        lblDetalheTelefone.setText(
                "Telefone: " + formatarTelefone(cliente.getTelefone())
        );

        lblDetalheEmail.setText("E-mail: " + cliente.getEmail());

        lblDetalheStatus.setText(
                "Status: " + (cliente.getStatus() == 1 ? "Ativo" : "Inativo")
        );

        // Buscar endereço pelo Model
        EnderecoController enderecoController = new EnderecoController();

        Endereco endereco = enderecoController.buscarPorCliente(idcliente);

        if (endereco != null) {

            lbCiade.setText(
                    "Cidade: " + endereco.getCidade()
            );

            lbBairro.setText(
                    "Bairro: " + endereco.getBairro()
            );

            lbEndereco.setText(
                    "Endereço: " + endereco.getRua()
                    + ", Nº " + endereco.getNumero()
            );

        } else {

            lbCiade.setText("Cidade: Não cadastrado");
            lbBairro.setText("Bairro: Não cadastrado");
            lbEndereco.setText("Endereço: Não cadastrado");
        }

        // Ajustar tamanho da label
        lblDetalheCodigo.setPreferredSize(
                new java.awt.Dimension(350, 25)
        );

        lblDetalheCodigo.setMinimumSize(
                new java.awt.Dimension(350, 25)
        );

        // Atualizar painel
        painelDetalhesCliente.revalidate();
        painelDetalhesCliente.repaint();

        // Exibir janela
        janelaDetalhes.pack();
        janelaDetalhes.setSize(430, 625);
        janelaDetalhes.setLocationRelativeTo(this);
        janelaDetalhes.setVisible(true);
        janelaDetalhes.toFront();

        lblDetalheCodigo.setVisible(true);

    } catch (Exception e) {

        JOptionPane.showMessageDialog(
                this,
                "Erro ao visualizar detalhes do cliente: " + e.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE
        );

        e.printStackTrace();
    }
}



private String formatarDataBrasileira(String data) {

    if (data == null || data.trim().isEmpty()) {
        return "";
    }

    try {
        java.time.LocalDate dataConvertida =
                java.time.LocalDate.parse(data);

        return dataConvertida.format(
                java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy")
        );

    } catch (java.time.format.DateTimeParseException e) {
        return data;
    }
}

private String formatarTelefone(String telefone) {

    if (telefone == null || telefone.trim().isEmpty()) {
        return "";
    }

    String numeros = telefone.replaceAll("\\D", "");

    if (numeros.length() == 11) {

        return "(" + numeros.substring(0, 2) + ") "
                + numeros.substring(2, 7) + "-"
                + numeros.substring(7);

    } else if (numeros.length() == 10) {

        return "(" + numeros.substring(0, 2) + ") "
                + numeros.substring(2, 6) + "-"
                + numeros.substring(6);
    }

    return telefone;
}

private javax.swing.JDialog janelaDetalhes;
/////////////////////////////////////////////////
//TELA EDIÇAO E DESATIVAÇAO
/////////////////////////////////////////////////////

private void pesquisarClientesEdicao() {

    String pesquisa = txtPesquisaEdicao.getText().trim();

    if (pesquisa.isEmpty()) {
        JOptionPane.showMessageDialog(
                this,
                "Digite o nome, CPF ou código do cliente."
        );
        return;
    }

    String statusSelecionado = cbStatusEdicao.getSelectedItem() != null
            ? cbStatusEdicao.getSelectedItem().toString().trim()
            : "";

    Integer status = null;

    if (statusSelecionado.equalsIgnoreCase("Ativo")) {
        status = 1;
    } else if (statusSelecionado.equalsIgnoreCase("Inativo")) {
        status = 0;
    }

    try {

        Control.ClienteController controller =
                new Control.ClienteController();

        java.util.List<Model.Cliente> todosClientes =
                controller.listar();

        java.util.List<Model.Cliente> clientesEncontrados =
                new java.util.ArrayList<>();

        for (Model.Cliente cliente : todosClientes) {

            boolean correspondePesquisa =
                    String.valueOf(cliente.getIdcliente()).contains(pesquisa)
                    || (cliente.getNome() != null
                    && cliente.getNome().toLowerCase().contains(pesquisa.toLowerCase()))
                    || (cliente.getCpf() != null
                    && cliente.getCpf().contains(pesquisa));

            boolean correspondeStatus =
                    status == null || cliente.getStatus() == status;

            if (correspondePesquisa && correspondeStatus) {
                clientesEncontrados.add(cliente);
            }
        }

        javax.swing.table.DefaultTableModel modelo =
                (javax.swing.table.DefaultTableModel)
                tabelaEdicaoClientes.getModel();

        modelo.setRowCount(0);

        if (clientesEncontrados.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Nenhum cliente encontrado."
            );
            return;
        }

        if (clientesEncontrados.size() > 1) {
            JOptionPane.showMessageDialog(
                    this,
                    "Foram encontrados vários clientes.\n"
                    + "Digite o código ou CPF para localizar "
                    + "um cliente específico."
            );
            return;
        }

        Model.Cliente cliente = clientesEncontrados.get(0);

        modelo.addRow(new Object[]{"Código", cliente.getIdcliente()});
        modelo.addRow(new Object[]{"Nome", cliente.getNome()});
        modelo.addRow(new Object[]{"CPF", cliente.getCpf()});
        modelo.addRow(new Object[]{"Telefone", cliente.getTelefone()});
        modelo.addRow(new Object[]{"E-mail", cliente.getEmail()});
        modelo.addRow(new Object[]{
            "Status",
            cliente.getStatus() == 1 ? "Ativo" : "Inativo"
        });

        preencherCamposEdicao(cliente.getIdcliente());

    } catch (java.sql.SQLException e) {

        JOptionPane.showMessageDialog(
                this,
                "Erro ao pesquisar clientes: " + e.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE
        );

        e.printStackTrace();
    }
}


private void configurarSelecaoTabelaEdicao() {

    tabelaEdicaoClientes.getSelectionModel()
        .addListSelectionListener(e -> {

            if (e.getValueIsAdjusting()) {
                return;
            }

            int linhaSelecionada =
                tabelaEdicaoClientes.getSelectedRow();

            if (linhaSelecionada == -1) {
                return;
            }

            int linhaModelo =
                tabelaEdicaoClientes.convertRowIndexToModel(
                    linhaSelecionada
                );

            int codigo = Integer.parseInt(
                tabelaEdicaoClientes.getModel()
                    .getValueAt(linhaModelo, 0).toString()
            );

            preencherCamposEdicao(codigo);
        });
}


private void preencherCamposEdicao(int idcliente) {

    try {

        // Buscar dados do cliente
        Control.ClienteController clienteController =
                new Control.ClienteController();

        Model.Cliente cliente =
                clienteController.buscarPorId(idcliente);

        if (cliente == null) {
            JOptionPane.showMessageDialog(
                    this,
                    "Não foi possível carregar os dados do cliente."
            );
            return;
        }

        // Preencher dados pessoais
        txtCodigoEdicao.setText(
                String.valueOf(cliente.getIdcliente())
        );

        txtNomeEdicao.setText(
                textoSeguro(cliente.getNome())
        );

        txtCPFCdicao.setText(
                formatarCPFEdicao(textoSeguro(cliente.getCpf()))
        );

        txtRGEdicao.setText(
                formatarRG(textoSeguro(cliente.getRg()))
        );

        txtTelefoneEdicao.setText(
                formatarTelefoneEdicao(textoSeguro(cliente.getTelefone()))
        );

        txtDataNascimentoEdicao.setText(
                formatarDataEdicao(textoSeguro(cliente.getDataNascimento()))
        );

        txtEmailEdicao.setText(
                textoSeguro(cliente.getEmail())
        );

        // Buscar endereço do cliente
        Control.EnderecoController enderecoController =
                new Control.EnderecoController();

        Model.Endereco endereco =
                enderecoController.buscarPorCliente(idcliente);

        if (endereco != null) {

            txtEnderecoEdicao.setText(
                    textoSeguro(endereco.getRua())
                    + ", Nº " + textoSeguro(endereco.getNumero())
            );

            txtBairroEdicao.setText(
                    textoSeguro(endereco.getBairro())
            );

            txtCidadeEdicao.setText(
                    textoSeguro(endereco.getCidade())
            );

        } else {

            txtEnderecoEdicao.setText("");
            txtBairroEdicao.setText("");
            txtCidadeEdicao.setText("");
        }

    } catch (java.sql.SQLException e) {

        JOptionPane.showMessageDialog(
                this,
                "Erro ao carregar dados do cliente: " + e.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE
        );

        e.printStackTrace();
    }
}


private String textoSeguro(String valor) {
    return valor == null ? "" : valor;
}

private String formatarCPFEdicao(String cpf) {
    String n = cpf.replaceAll("\\D", "");

    if (n.length() != 11) return cpf;

    return n.substring(0, 3) + "."
         + n.substring(3, 6) + "."
         + n.substring(6, 9) + "-"
         + n.substring(9, 11);
}

private String formatarRG(String rg) {
    String n = rg.replaceAll("\\D", "");

    if (n.length() != 7) return rg;

    return n.substring(0, 1) + "."
         + n.substring(1, 4) + "."
         + n.substring(4, 7);
}

private String formatarTelefoneEdicao(String telefone) {
    String n = telefone.replaceAll("\\D", "");

    if (n.length() == 11) {
        return "(" + n.substring(0, 2) + ") "
             + n.substring(2, 7) + "-"
             + n.substring(7, 11);
    }

    if (n.length() == 10) {
        return "(" + n.substring(0, 2) + ") "
             + n.substring(2, 6) + "-"
             + n.substring(6, 10);
    }

    return telefone;
}

private String formatarDataEdicao(String data) {
    if (data == null || data.trim().isEmpty()) {
        return "";
    }

    if (data.matches("\\d{4}-\\d{2}-\\d{2}")) {
        String[] partes = data.split("-");
        return partes[2] + "/" + partes[1] + "/" + partes[0];
    }

    return data;
}

private void limparCamposEdicao() {
    txtCodigoEdicao.setText("");
    txtNomeEdicao.setText("");
    txtCPFCdicao.setText("");
    txtRGEdicao.setText("");
    txtTelefoneEdicao.setText("");
    txtDataNascimentoEdicao.setText("");
    txtEmailEdicao.setText("");

    txtEnderecoEdicao.setText("");
    txtBairroEdicao.setText("");
    txtCidadeEdicao.setText("");
    javax.swing.table.DefaultTableModel modelo =
            (javax.swing.table.DefaultTableModel) tabelaEdicaoClientes.getModel();
    modelo.setRowCount(0);
}

private void permitirMoverPainel(JPanel painel) {

    final java.awt.Point posicaoInicial = new java.awt.Point();

    painel.addMouseListener(new java.awt.event.MouseAdapter() {

        @Override
        public void mousePressed(java.awt.event.MouseEvent evt) {
            posicaoInicial.setLocation(evt.getPoint());
        }
    });

    painel.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {

        @Override
        public void mouseDragged(java.awt.event.MouseEvent evt) {

            int novoX = painel.getX() + evt.getX() - posicaoInicial.x;
            int novoY = painel.getY() + evt.getY() - posicaoInicial.y;

            painel.setLocation(novoX, novoY);
        }
    });
}

//TELA HISTORICO DE COMPRAS E PAGAMENTOS


private void pesquisarHistoricoCompras() {

    String pesquisa = txtPesquisaHistorico.getText().trim();

    if (pesquisa.isEmpty()) {
        JOptionPane.showMessageDialog(
                this,
                "Digite o nome, CPF ou código do cliente."
        );
        return;
    }

    try {

        Control.ClienteController controller =
                new Control.ClienteController();

        java.util.List<Model.Cliente> todosClientes =
                controller.listar();

        java.util.List<Model.Cliente> clientes =
                new java.util.ArrayList<>();

        for (Model.Cliente cliente : todosClientes) {

            boolean encontrado =
                    String.valueOf(cliente.getIdcliente()).contains(pesquisa)
                    || (cliente.getNome() != null
                    && cliente.getNome().toLowerCase().contains(
                            pesquisa.toLowerCase()))
                    || (cliente.getCpf() != null
                    && cliente.getCpf().contains(pesquisa));

            if (encontrado) {
                clientes.add(cliente);
            }
        }

        javax.swing.table.DefaultTableModel modelo =
                (javax.swing.table.DefaultTableModel)
                tabelaComprasHistorico.getModel();

        modelo.setRowCount(0);

        if (clientes.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Nenhum cliente encontrado."
            );
            return;
        }

        if (clientes.size() > 1) {
            JOptionPane.showMessageDialog(
                    this,
                    "Foram encontrados vários clientes. "
                    + "Digite o CPF ou código para localizar "
                    + "um cliente específico."
            );
            return;
        }

        int idcliente = clientes.get(0).getIdcliente();

        // Buscar histórico de compras pelo ID do cliente.
        // Aqui será utilizada a lista tipada do Model Compras.

    } catch (java.sql.SQLException e) {

        JOptionPane.showMessageDialog(
                this,
                "Erro ao pesquisar histórico: " + e.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE
        );

        e.printStackTrace();
    }
}






private String formatarDataHistorico(String data) {

    try {
        java.time.LocalDate dataConvertida =
                java.time.LocalDate.parse(data);

        return dataConvertida.format(
                java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy"));

    } catch (Exception e) {
        return data;
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
        tfRG = new javax.swing.JFormattedTextField();
        painelEnderecoFlutuante = new javax.swing.JPanel();
        lbCidadeCadastro = new javax.swing.JLabel();
        tfCidadeCadastro = new javax.swing.JTextField();
        lbBairroCadastro = new javax.swing.JLabel();
        tfBairrocadastro = new javax.swing.JTextField();
        lbEnderecocadastro = new javax.swing.JLabel();
        tfEnderecoCadastro = new javax.swing.JTextField();
        btnFinalizarCadastro = new javax.swing.JButton();
        lbTituloEnderecocadastro = new javax.swing.JLabel();
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
        lbenderecoflutuante = new javax.swing.JLabel();
        lbCiade = new javax.swing.JLabel();
        lbBairro = new javax.swing.JLabel();
        lbEndereco = new javax.swing.JLabel();
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
        painelpedidosTitulo = new javax.swing.JPanel();
        lblTituloPedidosRelatorio = new javax.swing.JLabel();
        lblDescricaoPedidosRelatorio = new javax.swing.JLabel();
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
        tbPedidosClientes = new javax.swing.JTable();
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

        jRadioButtonMenuItem1.setSelected(true);
        jRadioButtonMenuItem1.setText("jRadioButtonMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

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

        btnLimpar.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        btnLimpar.setIcon(new javax.swing.ImageIcon("C:\\Users\\CleicianeGomes\\Documents\\ProjetoPI_Supermercado\\GerenciamentoSupermercado\\src\\main\\resources\\assets\\replay.png")); // NOI18N
        btnLimpar.setText("Limpar ");
        btnLimpar.setBorder(null);
        btnLimpar.setIconTextGap(8);
        btnLimpar.setPreferredSize(new java.awt.Dimension(150, 45));
        btnLimpar.addActionListener(this::btnLimparActionPerformed);

        btnSalvar.setBackground(new java.awt.Color(10, 4, 138));
        btnSalvar.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        btnSalvar.setForeground(new java.awt.Color(255, 255, 255));
        btnSalvar.setIcon(new javax.swing.ImageIcon("C:\\Users\\CleicianeGomes\\Documents\\ProjetoPI_Supermercado\\GerenciamentoSupermercado\\src\\main\\resources\\assets\\diskette (6).png")); // NOI18N
        btnSalvar.setText("Salvar cadastro");
        btnSalvar.setBorder(null);
        btnSalvar.setIconTextGap(8);
        btnSalvar.setPreferredSize(new java.awt.Dimension(183, 45));
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
        lbRg.setText("RG *");

        lbEmail.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        lbEmail.setText("E-mail *");

        txtEmailCadastro.setPreferredSize(new java.awt.Dimension(68, 40));

        lbData.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        lbData.setText("Data de nascimento *");

        lbSenha.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        lbSenha.setText("Senha *");

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
        grupoGenero.setText("Gênero *");

        rbFeminino.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 16)); // NOI18N
        rbFeminino.setText("Feminino");

        rbMasculino.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 16)); // NOI18N
        rbMasculino.setText("Masculino");

        rbOutro.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 16)); // NOI18N
        rbOutro.setText("Outro");

        try {
            tfRG.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#.###.###")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfRG.setPreferredSize(new java.awt.Dimension(130, 40));

        javax.swing.GroupLayout painelDadosClienteLayout = new javax.swing.GroupLayout(painelDadosCliente);
        painelDadosCliente.setLayout(painelDadosClienteLayout);
        painelDadosClienteLayout.setHorizontalGroup(
            painelDadosClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelDadosClienteLayout.createSequentialGroup()
                .addGap(74, 74, 74)
                .addGroup(painelDadosClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                                    .addComponent(txtEmailCadastro, javax.swing.GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE)
                                    .addGap(71, 71, 71)
                                    .addComponent(pfSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(painelDadosClienteLayout.createSequentialGroup()
                                .addComponent(rbFeminino)
                                .addGap(161, 161, 161)
                                .addComponent(rbMasculino)
                                .addGap(158, 158, 158)
                                .addComponent(rbOutro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(19, 19, 19))
                    .addGroup(painelDadosClienteLayout.createSequentialGroup()
                        .addGroup(painelDadosClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTelefone)
                            .addComponent(lbEmail)
                            .addComponent(ftfCPF, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblCPF)
                            .addComponent(ftfTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(71, 71, 71)
                        .addGroup(painelDadosClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(painelDadosClienteLayout.createSequentialGroup()
                                .addComponent(lbRg)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(painelDadosClienteLayout.createSequentialGroup()
                                .addGroup(painelDadosClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tfRG, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbData)
                                    .addComponent(ftData, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbSenha))
                                .addContainerGap(111, Short.MAX_VALUE))))))
            .addGroup(painelDadosClienteLayout.createSequentialGroup()
                .addGap(98, 98, 98)
                .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(114, 114, 114))
        );
        painelDadosClienteLayout.setVerticalGroup(
            painelDadosClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelDadosClienteLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(painelDadosClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(painelDadosClienteLayout.createSequentialGroup()
                        .addComponent(lblTituloCadastro)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblDescricaoCadastro)
                        .addGap(40, 40, 40)
                        .addComponent(lblNome)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addGroup(painelDadosClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblCPF)
                            .addComponent(lbRg))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfRG, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(ftfCPF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(painelDadosClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTelefone)
                    .addComponent(lbData))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painelDadosClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ftData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ftfTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
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
                .addGap(37, 37, 37)
                .addComponent(grupoGenero)
                .addGroup(painelDadosClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(painelDadosClienteLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(painelDadosClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rbFeminino)
                            .addComponent(rbMasculino)
                            .addComponent(rbOutro))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelDadosClienteLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 98, Short.MAX_VALUE)
                        .addGroup(painelDadosClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(160, 160, 160))))
        );

        painelEnderecoFlutuante.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lbCidadeCadastro.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        lbCidadeCadastro.setText("Cidade");

        tfCidadeCadastro.setPreferredSize(new java.awt.Dimension(68, 40));

        lbBairroCadastro.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        lbBairroCadastro.setText("Bairro");

        tfBairrocadastro.setPreferredSize(new java.awt.Dimension(68, 40));

        lbEnderecocadastro.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        lbEnderecocadastro.setText("Endereço");

        tfEnderecoCadastro.setPreferredSize(new java.awt.Dimension(68, 45));

        btnFinalizarCadastro.setBackground(new java.awt.Color(10, 4, 138));
        btnFinalizarCadastro.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        btnFinalizarCadastro.setForeground(new java.awt.Color(255, 255, 255));
        btnFinalizarCadastro.setIcon(new javax.swing.ImageIcon("C:\\Users\\CleicianeGomes\\Documents\\ProjetoPI_Supermercado\\GerenciamentoSupermercado\\src\\main\\resources\\assets\\diskette (6).png")); // NOI18N
        btnFinalizarCadastro.setText("Finalizar cadastro");
        btnFinalizarCadastro.setBorder(null);
        btnFinalizarCadastro.setIconTextGap(8);
        btnFinalizarCadastro.setPreferredSize(new java.awt.Dimension(221, 45));
        btnFinalizarCadastro.addActionListener(this::btnFinalizarCadastroActionPerformed);

        lbTituloEnderecocadastro.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 20)); // NOI18N
        lbTituloEnderecocadastro.setText("Cadastrar endereço");

        javax.swing.GroupLayout painelEnderecoFlutuanteLayout = new javax.swing.GroupLayout(painelEnderecoFlutuante);
        painelEnderecoFlutuante.setLayout(painelEnderecoFlutuanteLayout);
        painelEnderecoFlutuanteLayout.setHorizontalGroup(
            painelEnderecoFlutuanteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelEnderecoFlutuanteLayout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addGroup(painelEnderecoFlutuanteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbTituloEnderecocadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbCidadeCadastro)
                    .addComponent(lbEnderecocadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbBairroCadastro)
                    .addComponent(btnFinalizarCadastro, javax.swing.GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)
                    .addComponent(tfEnderecoCadastro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tfBairrocadastro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tfCidadeCadastro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(52, Short.MAX_VALUE))
        );
        painelEnderecoFlutuanteLayout.setVerticalGroup(
            painelEnderecoFlutuanteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelEnderecoFlutuanteLayout.createSequentialGroup()
                .addContainerGap(36, Short.MAX_VALUE)
                .addComponent(lbTituloEnderecocadastro)
                .addGap(24, 24, 24)
                .addComponent(lbCidadeCadastro)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfCidadeCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(lbBairroCadastro)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfBairrocadastro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(lbEnderecocadastro)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfEnderecoCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addComponent(btnFinalizarCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48))
        );

        javax.swing.GroupLayout painelCadastroLayout = new javax.swing.GroupLayout(painelCadastro);
        painelCadastro.setLayout(painelCadastroLayout);
        painelCadastroLayout.setHorizontalGroup(
            painelCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelCadastroLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(painelDadosCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(119, 119, 119)
                .addComponent(painelEnderecoFlutuante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(1572, Short.MAX_VALUE))
        );
        painelCadastroLayout.setVerticalGroup(
            painelCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelCadastroLayout.createSequentialGroup()
                .addGroup(painelCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(painelCadastroLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(painelDadosCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(painelCadastroLayout.createSequentialGroup()
                        .addGap(158, 158, 158)
                        .addComponent(painelEnderecoFlutuante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(1799, Short.MAX_VALUE))
        );

        abasClientes.addTab("Cadastro", new javax.swing.ImageIcon("C:\\Users\\CleicianeGomes\\Documents\\ProjetoPI_Supermercado\\GerenciamentoSupermercado\\src\\main\\resources\\assets\\user.png"), painelCadastro); // NOI18N

        painelConsulta.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        painelConsultaClientes.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        painelConsultaClientes.setForeground(new java.awt.Color(255, 255, 255));

        btnLimparFiltros.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        btnLimparFiltros.setIcon(new javax.swing.ImageIcon("C:\\Users\\CleicianeGomes\\Documents\\ProjetoPI_Supermercado\\GerenciamentoSupermercado\\src\\main\\resources\\assets\\replay.png")); // NOI18N
        btnLimparFiltros.setText("Limpar ");
        btnLimparFiltros.setBorder(null);
        btnLimparFiltros.setIconTextGap(8);
        btnLimparFiltros.setPreferredSize(new java.awt.Dimension(131, 45));
        btnLimparFiltros.addActionListener(this::btnLimparFiltrosActionPerformed);

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
        lblPesquisaCliente.setIcon(new javax.swing.ImageIcon("C:\\Users\\CleicianeGomes\\Documents\\ProjetoPI_Supermercado\\GerenciamentoSupermercado\\src\\main\\resources\\assets\\search (4).png")); // NOI18N
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
        btnPesquisarConsulta.setIconTextGap(8);
        btnPesquisarConsulta.setPreferredSize(new java.awt.Dimension(100, 45));
        btnPesquisarConsulta.addActionListener(this::btnPesquisarConsultaActionPerformed);

        javax.swing.GroupLayout painelConsultaClientesLayout = new javax.swing.GroupLayout(painelConsultaClientes);
        painelConsultaClientes.setLayout(painelConsultaClientesLayout);
        painelConsultaClientesLayout.setHorizontalGroup(
            painelConsultaClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelConsultaClientesLayout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addGroup(painelConsultaClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDescricaoConsulta)
                    .addComponent(lblTituloConsulta)
                    .addGroup(painelConsultaClientesLayout.createSequentialGroup()
                        .addGroup(painelConsultaClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnPesquisarConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(painelConsultaClientesLayout.createSequentialGroup()
                                .addGroup(painelConsultaClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblPesquisaCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtPesquisaCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 495, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(48, 48, 48)
                                .addGroup(painelConsultaClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblStatusConsulta)
                                    .addComponent(cbStatusConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(94, 94, 94)
                        .addComponent(btnLimparFiltros, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(63, Short.MAX_VALUE))
        );
        painelConsultaClientesLayout.setVerticalGroup(
            painelConsultaClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelConsultaClientesLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(lblTituloConsulta)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDescricaoConsulta)
                .addGap(64, 64, 64)
                .addGroup(painelConsultaClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelConsultaClientesLayout.createSequentialGroup()
                        .addComponent(lblStatusConsulta)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbStatusConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25)
                        .addGroup(painelConsultaClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnPesquisarConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnLimparFiltros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30))
                    .addGroup(painelConsultaClientesLayout.createSequentialGroup()
                        .addComponent(lblPesquisaCliente)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPesquisaCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        painelConsultaClientes1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        painelConsultaClientes1.setPreferredSize(new java.awt.Dimension(50, 380));

        btnVisualizarDetalhes.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        btnVisualizarDetalhes.setIcon(new javax.swing.ImageIcon("C:\\Users\\CleicianeGomes\\Documents\\ProjetoPI_Supermercado\\GerenciamentoSupermercado\\src\\main\\resources\\assets\\eye (1).png")); // NOI18N
        btnVisualizarDetalhes.setText("Visualizar detalhes");
        btnVisualizarDetalhes.setBorder(null);
        btnVisualizarDetalhes.setIconTextGap(8);
        btnVisualizarDetalhes.setPreferredSize(new java.awt.Dimension(210, 45));
        btnVisualizarDetalhes.addActionListener(this::btnVisualizarDetalhesActionPerformed);

        lblMensagemConsulta.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lblMensagemConsulta.setText("Selecione um cliente na tabela para visualizar seus dados.");

        lblClientesCadastrados.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 20)); // NOI18N
        lblClientesCadastrados.setIcon(new javax.swing.ImageIcon("C:\\Users\\CleicianeGomes\\Documents\\ProjetoPI_Supermercado\\GerenciamentoSupermercado\\src\\main\\resources\\assets\\persona.png")); // NOI18N
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
                .addGap(54, 54, 54)
                .addGroup(painelConsultaClientes1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblClientesCadastrados, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(painelConsultaClientes1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(painelConsultaClientes1Layout.createSequentialGroup()
                            .addComponent(lblMensagemConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 528, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnVisualizarDetalhes, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 985, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        painelConsultaClientes1Layout.setVerticalGroup(
            painelConsultaClientes1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelConsultaClientes1Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(lblClientesCadastrados)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addGroup(painelConsultaClientes1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnVisualizarDetalhes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMensagemConsulta))
                .addContainerGap(60, Short.MAX_VALUE))
        );

        painelDetalhesCliente.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lbDetalhesdocliente.setFont(new java.awt.Font("Arial Black", 0, 20)); // NOI18N
        lbDetalhesdocliente.setText("Detalhes do cliente");

        lbdadosPessoais.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lbdadosPessoais.setForeground(new java.awt.Color(102, 102, 102));
        lbdadosPessoais.setText("DADOS PESSOAIS");

        lblDetalheCodigo.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 16)); // NOI18N
        lblDetalheCodigo.setText("Código:");

        lblDetalheNome.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 16)); // NOI18N
        lblDetalheNome.setText("Nome:");

        lblDetalheCPF.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 16)); // NOI18N
        lblDetalheCPF.setText("CPF:");

        lblDetalheRG.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 16)); // NOI18N
        lblDetalheRG.setText("RG:");

        lblDetalheNascimento.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 16)); // NOI18N
        lblDetalheNascimento.setText("Data de nascimento:");

        lblDetalheGenero.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 16)); // NOI18N
        lblDetalheGenero.setText("Gênero:");

        lbDatalheContato.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lbDatalheContato.setForeground(new java.awt.Color(102, 102, 102));
        lbDatalheContato.setText("CONTATO");

        lblDetalheTelefone.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 16)); // NOI18N
        lblDetalheTelefone.setText("Telefone:");

        lblDetalheEmail.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 16)); // NOI18N
        lblDetalheEmail.setText("E-mail:");

        lbSituacaoCadastral.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lbSituacaoCadastral.setForeground(new java.awt.Color(102, 102, 102));
        lbSituacaoCadastral.setText("SITUAÇÃO CADASTRAL");

        lblDetalheStatus.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 16)); // NOI18N
        lblDetalheStatus.setText("Status:");

        lbenderecoflutuante.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lbenderecoflutuante.setForeground(new java.awt.Color(102, 102, 102));
        lbenderecoflutuante.setText("ENDEREÇO");

        lbCiade.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 16)); // NOI18N
        lbCiade.setText("Cidade:");

        lbBairro.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 16)); // NOI18N
        lbBairro.setText("Bairro:");

        lbEndereco.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 16)); // NOI18N
        lbEndereco.setText("Endereço:");

        javax.swing.GroupLayout painelDetalhesClienteLayout = new javax.swing.GroupLayout(painelDetalhesCliente);
        painelDetalhesCliente.setLayout(painelDetalhesClienteLayout);
        painelDetalhesClienteLayout.setHorizontalGroup(
            painelDetalhesClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelDetalhesClienteLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(painelDetalhesClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(painelDetalhesClienteLayout.createSequentialGroup()
                        .addGroup(painelDetalhesClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbDetalhesdocliente)
                            .addComponent(lbdadosPessoais, javax.swing.GroupLayout.DEFAULT_SIZE, 283, Short.MAX_VALUE))
                        .addContainerGap(79, Short.MAX_VALUE))
                    .addGroup(painelDetalhesClienteLayout.createSequentialGroup()
                        .addComponent(lblDetalheNome)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(painelDetalhesClienteLayout.createSequentialGroup()
                        .addGroup(painelDetalhesClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbSituacaoCadastral)
                            .addComponent(lblDetalheStatus)
                            .addComponent(lblDetalheGenero)
                            .addComponent(lblDetalheNascimento)
                            .addComponent(lblDetalheRG)
                            .addComponent(lblDetalheCPF)
                            .addComponent(lblDetalheCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbDatalheContato)
                            .addGroup(painelDetalhesClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(lblDetalheEmail, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblDetalheTelefone, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lbenderecoflutuante, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(lbEndereco, javax.swing.GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE)
                            .addComponent(lbBairro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbCiade, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        painelDetalhesClienteLayout.setVerticalGroup(
            painelDetalhesClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelDetalhesClienteLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(lbDetalhesdocliente)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbdadosPessoais)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblDetalheCodigo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDetalheNome)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDetalheCPF)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDetalheRG)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDetalheNascimento)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDetalheGenero)
                .addGap(18, 18, 18)
                .addComponent(lbDatalheContato)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblDetalheTelefone)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDetalheEmail)
                .addGap(18, 18, 18)
                .addComponent(lbenderecoflutuante)
                .addGap(18, 18, 18)
                .addComponent(lbCiade)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbBairro)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbEndereco)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addComponent(lbSituacaoCadastral)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDetalheStatus)
                .addGap(33, 33, 33))
        );

        javax.swing.GroupLayout painelConsultaLayout = new javax.swing.GroupLayout(painelConsulta);
        painelConsulta.setLayout(painelConsultaLayout);
        painelConsultaLayout.setHorizontalGroup(
            painelConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelConsultaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(painelConsultaClientes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(painelConsultaClientes1, javax.swing.GroupLayout.DEFAULT_SIZE, 1133, Short.MAX_VALUE))
                .addGap(70, 70, 70)
                .addComponent(painelDetalhesCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(1286, Short.MAX_VALUE))
        );
        painelConsultaLayout.setVerticalGroup(
            painelConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelConsultaLayout.createSequentialGroup()
                .addGroup(painelConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(painelConsultaLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(painelConsultaClientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(painelConsultaClientes1, javax.swing.GroupLayout.PREFERRED_SIZE, 436, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(painelConsultaLayout.createSequentialGroup()
                        .addGap(296, 296, 296)
                        .addComponent(painelDetalhesCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 1916, Short.MAX_VALUE))
        );

        abasClientes.addTab("Consulta", new javax.swing.ImageIcon("C:\\Users\\CleicianeGomes\\Documents\\ProjetoPI_Supermercado\\GerenciamentoSupermercado\\src\\main\\resources\\assets\\search 24.png"), painelConsulta); // NOI18N

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
        tabelaEdicaoClientes.setShowHorizontalLines(true);
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
        btnPesquisarEdicao.setIconTextGap(8);
        btnPesquisarEdicao.setPreferredSize(new java.awt.Dimension(98, 45));
        btnPesquisarEdicao.addActionListener(this::btnPesquisarEdicaoActionPerformed);

        lblTituloDadosEdicao.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lblTituloDadosEdicao.setText("Dados do cliente selecionado");

        lblDescricaoEdicao.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 22)); // NOI18N
        lblDescricaoEdicao.setText("Localize um cliente para consultar, alterar seus dados ou \ndesativar seu cadastro.");

        chick.setIcon(new javax.swing.ImageIcon("C:\\Users\\CleicianeGomes\\Documents\\ProjetoPI_Supermercado\\GerenciamentoSupermercado\\src\\main\\resources\\assets\\chick (2).png")); // NOI18N

        javax.swing.GroupLayout painelEdicaoClientesLayout = new javax.swing.GroupLayout(painelEdicaoClientes);
        painelEdicaoClientes.setLayout(painelEdicaoClientesLayout);
        painelEdicaoClientesLayout.setHorizontalGroup(
            painelEdicaoClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelEdicaoClientesLayout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addGroup(painelEdicaoClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(painelEdicaoClientesLayout.createSequentialGroup()
                        .addGroup(painelEdicaoClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(painelEdicaoClientesLayout.createSequentialGroup()
                                .addGroup(painelEdicaoClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblPesquisaEdicao)
                                    .addComponent(txtPesquisaEdicao, javax.swing.GroupLayout.PREFERRED_SIZE, 525, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(34, 34, 34)
                                .addGroup(painelEdicaoClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblStatusEdicao)
                                    .addComponent(cbStatusEdicao, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(lblTituloEdicao, javax.swing.GroupLayout.PREFERRED_SIZE, 698, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblDescricaoEdicao)
                            .addComponent(scrollTabelaEdicao, javax.swing.GroupLayout.PREFERRED_SIZE, 842, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(40, Short.MAX_VALUE))
                    .addGroup(painelEdicaoClientesLayout.createSequentialGroup()
                        .addComponent(lblTituloDadosEdicao, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnPesquisarEdicao, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(121, 121, 121))))
            .addGroup(painelEdicaoClientesLayout.createSequentialGroup()
                .addGap(138, 138, 138)
                .addComponent(chick)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        painelEdicaoClientesLayout.setVerticalGroup(
            painelEdicaoClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelEdicaoClientesLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(lblTituloEdicao)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDescricaoEdicao)
                .addGap(69, 69, 69)
                .addGroup(painelEdicaoClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPesquisaEdicao)
                    .addComponent(lblStatusEdicao))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painelEdicaoClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPesquisaEdicao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbStatusEdicao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(scrollTabelaEdicao, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(painelEdicaoClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTituloDadosEdicao)
                    .addComponent(btnPesquisarEdicao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(108, 108, 108)
                .addComponent(chick))
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
        txtBairroEdicao.addActionListener(this::txtBairroEdicaoActionPerformed);

        txtCPFCdicao.setPreferredSize(new java.awt.Dimension(68, 40));

        txtRGEdicao.setPreferredSize(new java.awt.Dimension(68, 40));
        txtRGEdicao.addActionListener(this::txtRGEdicaoActionPerformed);

        txtTelefoneEdicao.setPreferredSize(new java.awt.Dimension(68, 40));
        txtTelefoneEdicao.addActionListener(this::txtTelefoneEdicaoActionPerformed);

        lblTelefoneEdicao.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lblTelefoneEdicao.setText("Telefone");

        javax.swing.GroupLayout painelEdicaoClientes1Layout = new javax.swing.GroupLayout(painelEdicaoClientes1);
        painelEdicaoClientes1.setLayout(painelEdicaoClientes1Layout);
        painelEdicaoClientes1Layout.setHorizontalGroup(
            painelEdicaoClientes1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelEdicaoClientes1Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(painelEdicaoClientes1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblTelefoneEdicao)
                    .addComponent(txtBairroEdicao, javax.swing.GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE)
                    .addComponent(lblCodigoEdicao)
                    .addComponent(txtCPFCdicao, javax.swing.GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE)
                    .addComponent(txtCodigoEdicao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblCPFCdicao)
                    .addComponent(lblEmailEdicao)
                    .addComponent(Bairro, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEmailEdicao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtTelefoneEdicao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(painelEdicaoClientes1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtEnderecoEdicao, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDataNascimentoEdicao, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCidadeEdicao)
                    .addComponent(lblEnderecoEdicao)
                    .addComponent(lblDataNascimentoEdicao)
                    .addComponent(lblRGEdicao)
                    .addComponent(lblNomeEdicao)
                    .addComponent(txtRGEdicao, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCidadeEdicao, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNomeEdicao, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        painelEdicaoClientes1Layout.setVerticalGroup(
            painelEdicaoClientes1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelEdicaoClientes1Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(painelEdicaoClientes1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(painelEdicaoClientes1Layout.createSequentialGroup()
                        .addComponent(lblNomeEdicao)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNomeEdicao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(painelEdicaoClientes1Layout.createSequentialGroup()
                        .addComponent(lblCodigoEdicao)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCodigoEdicao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(34, 34, 34)
                .addGroup(painelEdicaoClientes1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(painelEdicaoClientes1Layout.createSequentialGroup()
                        .addComponent(lblRGEdicao)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtRGEdicao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(painelEdicaoClientes1Layout.createSequentialGroup()
                        .addComponent(lblCPFCdicao)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCPFCdicao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(39, 39, 39)
                .addGroup(painelEdicaoClientes1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTelefoneEdicao)
                    .addComponent(lblDataNascimentoEdicao))
                .addGroup(painelEdicaoClientes1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(painelEdicaoClientes1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDataNascimentoEdicao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(painelEdicaoClientes1Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(txtTelefoneEdicao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(38, 38, 38)
                .addGroup(painelEdicaoClientes1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEnderecoEdicao)
                    .addComponent(lblEmailEdicao))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painelEdicaoClientes1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEnderecoEdicao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEmailEdicao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addGroup(painelEdicaoClientes1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Bairro)
                    .addComponent(lblCidadeEdicao))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painelEdicaoClientes1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBairroEdicao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCidadeEdicao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        painelHistoricoClientes.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        btnLimparEdicao.setFont(new java.awt.Font("Arial Black", 1, 20)); // NOI18N
        btnLimparEdicao.setIcon(new javax.swing.ImageIcon("C:\\Users\\CleicianeGomes\\Documents\\ProjetoPI_Supermercado\\GerenciamentoSupermercado\\src\\main\\resources\\assets\\replay.png")); // NOI18N
        btnLimparEdicao.setText("Limpar campos");
        btnLimparEdicao.setBorder(null);
        btnLimparEdicao.setIconTextGap(8);
        btnLimparEdicao.setPreferredSize(new java.awt.Dimension(178, 45));
        btnLimparEdicao.addActionListener(this::btnLimparEdicaoActionPerformed);

        btnSalvarAlteracoes.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        btnSalvarAlteracoes.setIcon(new javax.swing.ImageIcon("C:\\Users\\CleicianeGomes\\Documents\\ProjetoPI_Supermercado\\GerenciamentoSupermercado\\src\\main\\resources\\assets\\diskette (5).png")); // NOI18N
        btnSalvarAlteracoes.setText("Salvar alterações");
        btnSalvarAlteracoes.setBorder(null);
        btnSalvarAlteracoes.setIconTextGap(8);
        btnSalvarAlteracoes.setPreferredSize(new java.awt.Dimension(200, 45));
        btnSalvarAlteracoes.addActionListener(this::btnSalvarAlteracoesActionPerformed);

        btnDesativarCliente.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        btnDesativarCliente.setIcon(new javax.swing.ImageIcon("C:\\Users\\CleicianeGomes\\Documents\\ProjetoPI_Supermercado\\GerenciamentoSupermercado\\src\\main\\resources\\assets\\user (1).png")); // NOI18N
        btnDesativarCliente.setText("Desativar cliente");
        btnDesativarCliente.setBorder(null);
        btnDesativarCliente.setPreferredSize(new java.awt.Dimension(193, 45));
        btnDesativarCliente.addActionListener(this::btnDesativarClienteActionPerformed);

        javax.swing.GroupLayout painelHistoricoClientesLayout = new javax.swing.GroupLayout(painelHistoricoClientes);
        painelHistoricoClientes.setLayout(painelHistoricoClientesLayout);
        painelHistoricoClientesLayout.setHorizontalGroup(
            painelHistoricoClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelHistoricoClientesLayout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(btnLimparEdicao, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(376, 376, 376)
                .addComponent(btnSalvarAlteracoes, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 362, Short.MAX_VALUE)
                .addComponent(btnDesativarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(69, 69, 69))
        );
        painelHistoricoClientesLayout.setVerticalGroup(
            painelHistoricoClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelHistoricoClientesLayout.createSequentialGroup()
                .addGroup(painelHistoricoClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(painelHistoricoClientesLayout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(painelHistoricoClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnLimparEdicao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSalvarAlteracoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(painelHistoricoClientesLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(btnDesativarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(32, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout painelEdicaoLayout = new javax.swing.GroupLayout(painelEdicao);
        painelEdicao.setLayout(painelEdicaoLayout);
        painelEdicaoLayout.setHorizontalGroup(
            painelEdicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelEdicaoLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(painelEdicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(painelHistoricoClientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(painelEdicaoLayout.createSequentialGroup()
                        .addComponent(painelEdicaoClientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(painelEdicaoClientes1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(1268, Short.MAX_VALUE))
        );
        painelEdicaoLayout.setVerticalGroup(
            painelEdicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelEdicaoLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(painelEdicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(painelEdicaoClientes1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(painelEdicaoClientes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(36, 36, 36)
                .addComponent(painelHistoricoClientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(1998, Short.MAX_VALUE))
        );

        abasClientes.addTab("Edição e Desativação", new javax.swing.ImageIcon("C:\\Users\\CleicianeGomes\\Documents\\ProjetoPI_Supermercado\\GerenciamentoSupermercado\\src\\main\\resources\\assets\\pencil (1).png"), painelEdicao); // NOI18N

        painelHistorico.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        btnComprovante.setBackground(new java.awt.Color(10, 4, 138));
        btnComprovante.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18)); // NOI18N
        btnComprovante.setForeground(new java.awt.Color(255, 255, 255));
        btnComprovante.setIcon(new javax.swing.ImageIcon("C:\\Users\\CleicianeGomes\\Documents\\ProjetoPI_Supermercado\\GerenciamentoSupermercado\\src\\main\\resources\\assets\\bill (1).png")); // NOI18N
        btnComprovante.setText("Comprovante");
        btnComprovante.setBorder(null);

        btnExportarHistorico.setBackground(new java.awt.Color(10, 40, 138));
        btnExportarHistorico.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18)); // NOI18N
        btnExportarHistorico.setForeground(new java.awt.Color(255, 255, 255));
        btnExportarHistorico.setIcon(new javax.swing.ImageIcon("C:\\Users\\CleicianeGomes\\Documents\\ProjetoPI_Supermercado\\GerenciamentoSupermercado\\src\\main\\resources\\assets\\share.png")); // NOI18N
        btnExportarHistorico.setText("Exportar ");
        btnExportarHistorico.setBorder(null);
        btnExportarHistorico.setPreferredSize(new java.awt.Dimension(171, 40));

        painelhistoricoCompras.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        painelhistoricoCompras.setPreferredSize(new java.awt.Dimension(300, 200));

        tabelaComprasHistorico.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        tabelaComprasHistorico.setFont(new java.awt.Font("Arial Narrow", 0, 16)); // NOI18N
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
                .addGap(53, 53, 53)
                .addGroup(painelhistoricoComprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 750, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTituloComprasHistorico, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(69, Short.MAX_VALUE))
        );
        painelhistoricoComprasLayout.setVerticalGroup(
            painelhistoricoComprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelhistoricoComprasLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(lblTituloComprasHistorico)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        painelhistorico.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        painelhistorico.setPreferredSize(new java.awt.Dimension(1200, 900));

        btnPesquisarHistorico.setBackground(new java.awt.Color(10, 4, 138));
        btnPesquisarHistorico.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        btnPesquisarHistorico.setForeground(new java.awt.Color(255, 255, 255));
        btnPesquisarHistorico.setIcon(new javax.swing.ImageIcon("C:\\Users\\CleicianeGomes\\Documents\\ProjetoPI_Supermercado\\GerenciamentoSupermercado\\src\\main\\resources\\assets\\search (3).png")); // NOI18N
        btnPesquisarHistorico.setText("Pesquisar");
        btnPesquisarHistorico.setBorder(null);
        btnPesquisarHistorico.setIconTextGap(8);
        btnPesquisarHistorico.setPreferredSize(new java.awt.Dimension(98, 45));
        btnPesquisarHistorico.addActionListener(this::btnPesquisarHistoricoActionPerformed);

        txtPesquisaHistorico.setPreferredSize(new java.awt.Dimension(68, 35));

        lblTituloHistorico.setFont(new java.awt.Font("Arial Black", 1, 30)); // NOI18N
        lblTituloHistorico.setForeground(new java.awt.Color(10, 4, 138));
        lblTituloHistorico.setText("Histórico de Compras e Pagamentos");

        btnLimparHistorico.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        btnLimparHistorico.setIcon(new javax.swing.ImageIcon("C:\\Users\\CleicianeGomes\\Documents\\ProjetoPI_Supermercado\\GerenciamentoSupermercado\\src\\main\\resources\\assets\\replay.png")); // NOI18N
        btnLimparHistorico.setText("Limpar");
        btnLimparHistorico.setBorder(null);
        btnLimparHistorico.setIconTextGap(8);
        btnLimparHistorico.setPreferredSize(new java.awt.Dimension(176, 45));

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
                .addGap(56, 56, 56)
                .addGroup(painelhistoricoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTituloHistorico)
                    .addComponent(txtPesquisaHistorico, javax.swing.GroupLayout.PREFERRED_SIZE, 645, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(painelhistoricoLayout.createSequentialGroup()
                        .addComponent(btnPesquisarHistorico, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(186, 186, 186)
                        .addComponent(btnLimparHistorico, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lbpesquisarcliente)
                    .addComponent(lblDescricaoHistorico))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        painelhistoricoLayout.setVerticalGroup(
            painelhistoricoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelhistoricoLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(lblTituloHistorico)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDescricaoHistorico)
                .addGap(28, 28, 28)
                .addComponent(lbpesquisarcliente)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtPesquisaHistorico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addGroup(painelhistoricoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnPesquisarHistorico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLimparHistorico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(36, Short.MAX_VALUE))
        );

        painelhistoricoPagamentos.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        painelhistoricoPagamentos.setPreferredSize(new java.awt.Dimension(400, 400));

        lblTituloPagamentosHistorico.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        lblTituloPagamentosHistorico.setIcon(new javax.swing.ImageIcon("C:\\Users\\CleicianeGomes\\Documents\\ProjetoPI_Supermercado\\GerenciamentoSupermercado\\src\\main\\resources\\assets\\money (2).png")); // NOI18N
        lblTituloPagamentosHistorico.setText("Histórico de Pagamentos");

        tabelaPagamentosHistorico.setFont(new java.awt.Font("Arial Narrow", 0, 16)); // NOI18N
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
                    .addComponent(lblTituloPagamentosHistorico, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 758, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(74, Short.MAX_VALUE))
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
                .addGap(0, 17, Short.MAX_VALUE))
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
                .addGap(19, 19, 19)
                .addComponent(lbiconeSaldoPendente, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addGroup(lBSaldoPendenteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lBvalorPendente, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbsaldopendente1)
                    .addComponent(lBsubValorPendente, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(50, Short.MAX_VALUE))
        );
        lBSaldoPendenteLayout.setVerticalGroup(
            lBSaldoPendenteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lBSaldoPendenteLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(lBSaldoPendenteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbiconeSaldoPendente, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(lBSaldoPendenteLayout.createSequentialGroup()
                        .addComponent(lbsaldopendente1)
                        .addGap(12, 12, 12)
                        .addComponent(lBvalorPendente)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lBsubValorPendente)))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout painelHistoricoLayout = new javax.swing.GroupLayout(painelHistorico);
        painelHistorico.setLayout(painelHistoricoLayout);
        painelHistoricoLayout.setHorizontalGroup(
            painelHistoricoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelHistoricoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(painelHistoricoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(painelhistoricoCompras, javax.swing.GroupLayout.DEFAULT_SIZE, 876, Short.MAX_VALUE)
                    .addComponent(painelhistoricoPagamentos, javax.swing.GroupLayout.DEFAULT_SIZE, 876, Short.MAX_VALUE)
                    .addComponent(painelhistorico, javax.swing.GroupLayout.DEFAULT_SIZE, 876, Short.MAX_VALUE))
                .addGap(42, 42, 42)
                .addGroup(painelHistoricoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbGerarComprovante)
                    .addGroup(painelHistoricoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(painelTotalPago, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(painelHistoricoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(painelTotalCompras, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lBSaldoPendente, javax.swing.GroupLayout.DEFAULT_SIZE, 293, Short.MAX_VALUE)))
                    .addGroup(painelHistoricoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(btnExportarHistorico, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
                        .addComponent(btnComprovante, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(1461, Short.MAX_VALUE))
        );
        painelHistoricoLayout.setVerticalGroup(
            painelHistoricoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelHistoricoLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(painelHistoricoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(painelHistoricoLayout.createSequentialGroup()
                        .addComponent(painelTotalCompras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lBSaldoPendente, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(painelTotalPago, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                        .addComponent(lbGerarComprovante)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 157, Short.MAX_VALUE)
                        .addComponent(btnComprovante, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(btnExportarHistorico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1997, 1997, 1997))
                    .addGroup(painelHistoricoLayout.createSequentialGroup()
                        .addComponent(painelhistorico, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(painelhistoricoCompras, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(painelhistoricoPagamentos, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        abasClientes.addTab("Histórico de Compras e Pagamentos", new javax.swing.ImageIcon("C:\\Users\\CleicianeGomes\\Documents\\ProjetoPI_Supermercado\\GerenciamentoSupermercado\\src\\main\\resources\\assets\\history 24.png"), painelHistorico); // NOI18N

        painelPedidosRelatorio.setPreferredSize(new java.awt.Dimension(1200, 936));

        painelpedidosTitulo.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblTituloPedidosRelatorio.setFont(new java.awt.Font("Arial Black", 1, 30)); // NOI18N
        lblTituloPedidosRelatorio.setForeground(new java.awt.Color(10, 4, 138));
        lblTituloPedidosRelatorio.setText("Pedidos e Relatório de Clientes");

        lblDescricaoPedidosRelatorio.setFont(new java.awt.Font("Arial", 0, 22)); // NOI18N
        lblDescricaoPedidosRelatorio.setText("Consulte os pedidos dos clientes e gere relatórios de cadastro e movimentação.");

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
        btnLimparFiltrosRelatorio.setIconTextGap(8);
        btnLimparFiltrosRelatorio.setLabel("Limpar");
        btnLimparFiltrosRelatorio.setPreferredSize(new java.awt.Dimension(138, 40));
        btnLimparFiltrosRelatorio.addActionListener(this::btnLimparFiltrosRelatorioActionPerformed);

        btnPesquisarRelatorio.setBackground(new java.awt.Color(10, 4, 138));
        btnPesquisarRelatorio.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        btnPesquisarRelatorio.setForeground(new java.awt.Color(255, 255, 255));
        btnPesquisarRelatorio.setIcon(new javax.swing.ImageIcon("C:\\Users\\CleicianeGomes\\Documents\\ProjetoPI_Supermercado\\GerenciamentoSupermercado\\src\\main\\resources\\assets\\search (3).png")); // NOI18N
        btnPesquisarRelatorio.setText("Pesquisar");
        btnPesquisarRelatorio.setBorder(null);
        btnPesquisarRelatorio.setIconTextGap(6);
        btnPesquisarRelatorio.setPreferredSize(new java.awt.Dimension(75, 40));

        txtPesquisaClienteRelatorio.setPreferredSize(new java.awt.Dimension(68, 35));

        javax.swing.GroupLayout painelpedidosTituloLayout = new javax.swing.GroupLayout(painelpedidosTitulo);
        painelpedidosTitulo.setLayout(painelpedidosTituloLayout);
        painelpedidosTituloLayout.setHorizontalGroup(
            painelpedidosTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelpedidosTituloLayout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(painelpedidosTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(painelpedidosTituloLayout.createSequentialGroup()
                        .addGroup(painelpedidosTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(painelpedidosTituloLayout.createSequentialGroup()
                                .addComponent(txtPesquisaClienteRelatorio, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cbStatusRelatorio, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(painelpedidosTituloLayout.createSequentialGroup()
                                .addComponent(lblPesquisaClienteRelatorio, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblStatusRelatorio, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(68, 68, 68)
                        .addGroup(painelpedidosTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblPeriodoRelatorio, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(painelpedidosTituloLayout.createSequentialGroup()
                                .addComponent(cbPeriodoRelatorio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(57, 57, 57)
                                .addComponent(btnPesquisarRelatorio, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnLimparFiltrosRelatorio, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(lblTituloPedidosRelatorio)
                    .addComponent(lblDescricaoPedidosRelatorio))
                .addGap(23, 23, 23))
        );
        painelpedidosTituloLayout.setVerticalGroup(
            painelpedidosTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelpedidosTituloLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTituloPedidosRelatorio, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDescricaoPedidosRelatorio)
                .addGap(18, 18, 18)
                .addGroup(painelpedidosTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(painelpedidosTituloLayout.createSequentialGroup()
                        .addGroup(painelpedidosTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblPesquisaClienteRelatorio, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblStatusRelatorio))
                        .addGap(1, 1, 1)
                        .addGroup(painelpedidosTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtPesquisaClienteRelatorio, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbStatusRelatorio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(painelpedidosTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(painelpedidosTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnPesquisarRelatorio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnLimparFiltrosRelatorio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(painelpedidosTituloLayout.createSequentialGroup()
                            .addComponent(lblPeriodoRelatorio)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(cbPeriodoRelatorio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(118, 118, 118))
        );

        painelPedidosClientes.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        tbPedidosClientes.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tbPedidosClientes.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        tbPedidosClientes.setModel(new javax.swing.table.DefaultTableModel(
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
        tbPedidosClientes.setShowVerticalLines(true);
        tabelaPedidosClientes.setViewportView(tbPedidosClientes);

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
                    .addComponent(lblTituloTabelaPedidos)
                    .addComponent(tabelaPedidosClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 1014, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        painelPedidosClientesLayout.setVerticalGroup(
            painelPedidosClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelPedidosClientesLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(lblTituloTabelaPedidos, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tabelaPedidosClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        painelTotalPedidos.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblQuantidadePedidosRelatorio.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lblQuantidadePedidosRelatorio.setText("0");

        lblTotalPedidosRelatorio.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        lblTotalPedidosRelatorio.setText("Total de pedidos:");

        lbIconetotalPedidos.setIcon(new javax.swing.ImageIcon("C:\\Users\\CleicianeGomes\\Documents\\ProjetoPI_Supermercado\\GerenciamentoSupermercado\\src\\main\\resources\\assets\\cart (4).png")); // NOI18N

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
                        .addGap(39, 39, 39)
                        .addComponent(lbIconetotalPedidos, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(55, Short.MAX_VALUE))
        );
        painelTotalPedidosLayout.setVerticalGroup(
            painelTotalPedidosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelTotalPedidosLayout.createSequentialGroup()
                .addContainerGap(38, Short.MAX_VALUE)
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

        lbIconeValorTotal.setIcon(new javax.swing.ImageIcon("C:\\Users\\CleicianeGomes\\Documents\\ProjetoPI_Supermercado\\GerenciamentoSupermercado\\src\\main\\resources\\assets\\dollar (1).png")); // NOI18N

        javax.swing.GroupLayout painelvalorTotalPedidosLayout = new javax.swing.GroupLayout(painelvalorTotalPedidos);
        painelvalorTotalPedidos.setLayout(painelvalorTotalPedidosLayout);
        painelvalorTotalPedidosLayout.setHorizontalGroup(
            painelvalorTotalPedidosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelvalorTotalPedidosLayout.createSequentialGroup()
                .addGroup(painelvalorTotalPedidosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(painelvalorTotalPedidosLayout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(painelvalorTotalPedidosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblValorPedidosRelatorio)
                            .addComponent(lblTotalValorPedidosRelatorio, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(painelvalorTotalPedidosLayout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(lbIconeValorTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        painelvalorTotalPedidosLayout.setVerticalGroup(
            painelvalorTotalPedidosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelvalorTotalPedidosLayout.createSequentialGroup()
                .addContainerGap(38, Short.MAX_VALUE)
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

        lbIconePendente.setIcon(new javax.swing.ImageIcon("C:\\Users\\CleicianeGomes\\Documents\\ProjetoPI_Supermercado\\GerenciamentoSupermercado\\src\\main\\resources\\assets\\clock (3).png")); // NOI18N

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
                .addContainerGap(36, Short.MAX_VALUE)
                .addComponent(lbIconePendente)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblPedidosPendentesRelatorio)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblQuantidadePendentesRelatorio)
                .addGap(31, 31, 31))
        );

        painelGerarRelatorio.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblTipoRelatorio.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        lblTipoRelatorio.setText("Tipo de relatório");

        jLabel1.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon("C:\\Users\\CleicianeGomes\\Documents\\ProjetoPI_Supermercado\\GerenciamentoSupermercado\\src\\main\\resources\\assets\\documents (1).png")); // NOI18N
        jLabel1.setText("Formato");

        btnGerarRelatorio.setBackground(new java.awt.Color(10, 4, 138));
        btnGerarRelatorio.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        btnGerarRelatorio.setForeground(new java.awt.Color(255, 255, 255));
        btnGerarRelatorio.setIcon(new javax.swing.ImageIcon("C:\\Users\\CleicianeGomes\\Documents\\ProjetoPI_Supermercado\\GerenciamentoSupermercado\\src\\main\\resources\\assets\\report (1).png")); // NOI18N
        btnGerarRelatorio.setText("Gerar relatório");
        btnGerarRelatorio.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnGerarRelatorio.setPreferredSize(new java.awt.Dimension(109, 40));

        jComboBox1.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "PDF ", "CSV", " " }));
        jComboBox1.setPreferredSize(new java.awt.Dimension(77, 35));

        lbMesRelatorio.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lbMesRelatorio.setText("Mês");

        lbPeriodoRelatorio.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        lbPeriodoRelatorio.setIcon(new javax.swing.ImageIcon("C:\\Users\\CleicianeGomes\\Documents\\ProjetoPI_Supermercado\\GerenciamentoSupermercado\\src\\main\\resources\\assets\\calendar.png")); // NOI18N
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
        lblTituloGerarRelatorio.setIcon(new javax.swing.ImageIcon("C:\\Users\\CleicianeGomes\\Documents\\ProjetoPI_Supermercado\\GerenciamentoSupermercado\\src\\main\\resources\\assets\\newspaper (3).png")); // NOI18N
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
                            .addComponent(lbAnoRelatorio)
                            .addComponent(cbAnoRelatorio, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(lblTituloGerarRelatorio, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(47, Short.MAX_VALUE))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
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

        javax.swing.GroupLayout painelPedidosRelatorioLayout = new javax.swing.GroupLayout(painelPedidosRelatorio);
        painelPedidosRelatorio.setLayout(painelPedidosRelatorioLayout);
        painelPedidosRelatorioLayout.setHorizontalGroup(
            painelPedidosRelatorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelPedidosRelatorioLayout.createSequentialGroup()
                .addGroup(painelPedidosRelatorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(painelpedidosTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(painelPedidosRelatorioLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(painelGerarRelatorio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(painelPedidosRelatorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(painelvalorTotalPedidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(painelTotalPedidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(23, 73, Short.MAX_VALUE)
                        .addComponent(painelPedidospendetes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(painelPedidosClientes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(1683, 1683, 1683))
        );
        painelPedidosRelatorioLayout.setVerticalGroup(
            painelPedidosRelatorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelPedidosRelatorioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(painelpedidosTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(painelPedidosClientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(painelPedidosRelatorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(painelPedidosRelatorioLayout.createSequentialGroup()
                        .addGroup(painelPedidosRelatorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(painelTotalPedidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(painelPedidospendetes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(painelvalorTotalPedidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(painelGerarRelatorio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(1935, Short.MAX_VALUE))
        );

        abasClientes.addTab("Pedidos e Relatório", new javax.swing.ImageIcon("C:\\Users\\CleicianeGomes\\Documents\\ProjetoPI_Supermercado\\GerenciamentoSupermercado\\src\\main\\resources\\assets\\document.png"), painelPedidosRelatorio); // NOI18N

        getContentPane().add(abasClientes, java.awt.BorderLayout.PAGE_START);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimparActionPerformed
        // TODO add your handling code here:
         limparCamposCadastro() ;
    }//GEN-LAST:event_btnLimparActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
// TEXT FIELD
    // Verifica se os dados pessoais estão preenchidos
    if (txtNome.getText().trim().isEmpty()
            || tfRG.getText().trim().isEmpty()
            || txtEmailCadastro.getText().trim().isEmpty()
            || ftfCPF.getText().replaceAll("[^0-9]", "").isEmpty()
            || ftfTelefone.getText().replaceAll("[^0-9]", "").isEmpty()
            || ftData.getText().trim().isEmpty()
            || pfSenha.getPassword().length == 0) {
        JOptionPane.showMessageDialog(this,
                "Preencha todos os dados pessoais antes de continuar.");
        return;
    }
    // Abre o painel de endereço
    painelEnderecoFlutuante.setVisible(true);

    painelEnderecoFlutuante.getParent().setComponentZOrder(
            painelEnderecoFlutuante, 0
    );
    painelEnderecoFlutuante.getParent().repaint();
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

    private void cbPeriodoRelatorioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbPeriodoRelatorioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbPeriodoRelatorioActionPerformed

    private void btnPesquisarConsultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarConsultaActionPerformed
        // TODO add your handling code here:
           pesquisarClientesConsulta();

    }//GEN-LAST:event_btnPesquisarConsultaActionPerformed

    private void btnLimparFiltrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimparFiltrosActionPerformed
        // TODO add your handling code here:
// Limpa o campo de pesquisa
   txtPesquisaCliente.setText(
            "Digite o nome, CPF ou código..."
    );

    txtPesquisaCliente.setForeground(java.awt.Color.GRAY);

    // Limpa a tabela, sem carregar os clientes
    DefaultTableModel modelo =
            (DefaultTableModel) tabelaConsultaClientes.getModel();
    modelo.setRowCount(0);
    lblMensagemConsulta.setText(""); 
    // Esconde o painel de detalhes
janelaDetalhes.setVisible(false);
// Atualiza a organização visual
painelConsulta.revalidate();
painelConsulta.repaint();
// Limpa os campos de detalhes
lblDetalheCodigo.setText("");
lblDetalheNome.setText("");
lblDetalheCPF.setText("");
lblDetalheRG.setText("");
lblDetalheNascimento.setText("");
lblDetalheGenero.setText("");
lblDetalheTelefone.setText("");
lblDetalheEmail.setText("");
lblDetalheStatus.setText("");
    }//GEN-LAST:event_btnLimparFiltrosActionPerformed

    private void btnVisualizarDetalhesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVisualizarDetalhesActionPerformed
        // TODO add your handling code here:
         visualizarDetalhesCliente();
         
    }//GEN-LAST:event_btnVisualizarDetalhesActionPerformed

    private void btnLimparEdicaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimparEdicaoActionPerformed
        // TODO add your handling code here:
    txtCodigoEdicao.setText("");
    txtNomeEdicao.setText("");
    txtCPFCdicao.setText("");
    txtRGEdicao.setText("");
    txtTelefoneEdicao.setText("");
    txtDataNascimentoEdicao.setText("");
    txtEmailEdicao.setText("");

    txtEnderecoEdicao.setText("");
    txtBairroEdicao.setText("");
    txtCidadeEdicao.setText("");

    // Limpar a tabela de detalhes
    javax.swing.table.DefaultTableModel modelo =
            (javax.swing.table.DefaultTableModel) tabelaEdicaoClientes.getModel();

    modelo.setRowCount(0);
    }//GEN-LAST:event_btnLimparEdicaoActionPerformed

    private void btnLimparFiltrosRelatorioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimparFiltrosRelatorioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLimparFiltrosRelatorioActionPerformed

    private void btnPesquisarEdicaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarEdicaoActionPerformed
        // TODO add your handling code here:
        pesquisarClientesEdicao();
    }//GEN-LAST:event_btnPesquisarEdicaoActionPerformed

    private void txtBairroEdicaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBairroEdicaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBairroEdicaoActionPerformed

    private void txtTelefoneEdicaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTelefoneEdicaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTelefoneEdicaoActionPerformed

    private void btnDesativarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDesativarClienteActionPerformed
        // TODO add your handling code here:
      desativarCliente();
    }//GEN-LAST:event_btnDesativarClienteActionPerformed

    
private void desativarCliente() {

    String codigo = txtCodigoEdicao.getText().trim();

    if (codigo.isEmpty()) {
        JOptionPane.showMessageDialog(
                this,
                "Pesquise e selecione um cliente antes de desativá-lo.",
                "Atenção",
                JOptionPane.WARNING_MESSAGE
        );
        return;
    }

    try {

        int idcliente = Integer.parseInt(codigo);

        int confirmacao = JOptionPane.showConfirmDialog(
                this,
                "Tem certeza de que deseja desativar este cliente?",
                "Confirmar desativação",
                JOptionPane.YES_NO_OPTION
        );

        if (confirmacao == JOptionPane.YES_OPTION) {

            Control.ClienteController controller =
                    new Control.ClienteController();

            boolean sucesso = controller.desativar(idcliente);

            if (sucesso) {

                JOptionPane.showMessageDialog(
                        this,
                        "Cliente desativado com sucesso!",
                        "Sucesso",
                        JOptionPane.INFORMATION_MESSAGE
                );

                limparCamposEdicao();
                carregarClientesConsulta();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Não foi possível desativar o cliente.",
                        "Erro",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        }

    } catch (NumberFormatException e) {

        JOptionPane.showMessageDialog(
                this,
                "Código do cliente inválido.",
                "Erro",
                JOptionPane.ERROR_MESSAGE
        );

    } catch (java.sql.SQLException e) {

        JOptionPane.showMessageDialog(
                this,
                "Erro ao desativar cliente: " + e.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE
        );

        e.printStackTrace();
    }
}

    
    private void btnFinalizarCadastroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFinalizarCadastroActionPerformed
        // TODO add your handling code here:
    

    String cidade = tfCidadeCadastro.getText().trim();
    String bairro = tfBairrocadastro.getText().trim();
    String rua = tfEnderecoCadastro.getText().trim();

    if (cidade.isEmpty() || bairro.isEmpty() || rua.isEmpty()) {
        JOptionPane.showMessageDialog(
                this,
                "Preencha todos os campos do endereço."
        );
        return;
    }

    String nome = txtNome.getText().trim();
    String rg = tfRG.getText().trim();
    String email = txtEmailCadastro.getText().trim();
    String cpf = ftfCPF.getText().replaceAll("[^0-9]", "");
    String telefone = ftfTelefone.getText().replaceAll("[^0-9]", "");
    String dataNascimento = ftData.getText().trim();
    String senha = new String(pfSenha.getPassword());

    String genero = "";

    if (rbFeminino.isSelected()) {
        genero = "Feminino";
    } else if (rbMasculino.isSelected()) {
        genero = "Masculino";
    } else if (rbOutro.isSelected()) {
        genero = "Outro";
    }

    String statusSelecionado =
            cbStatusCadastro.getSelectedItem().toString();

    int status = statusSelecionado.equalsIgnoreCase("Ativo") ? 1 : 0;

    try {

        // Criar Model Cliente
        Model.Cliente cliente = new Model.Cliente();

        cliente.setNome(nome);
        cliente.setCpf(cpf);
        cliente.setRg(rg);
        cliente.setDataNascimento(dataNascimento);
        cliente.setGenero(genero);
        cliente.setEmail(email);
        cliente.setTelefone(telefone);
        cliente.setStatus(status);
        cliente.setSenha(senha);

        // Cadastrar cliente
        Control.ClienteController clienteController =
                new Control.ClienteController();

        boolean sucesso = clienteController.cadastrar(cliente);

        if (!sucesso) {
            JOptionPane.showMessageDialog(
                    this,
                    "Não foi possível cadastrar o cliente."
            );
            return;
        }

        // Criar Model Endereco
        Model.Endereco endereco = new Model.Endereco();

        endereco.setBairro(bairro);
        endereco.setCep("");
        endereco.setCidade(cidade);
        endereco.setEstado("");
        endereco.setRua(rua);
        endereco.setNumero("");
        endereco.setCliente_idcliente(cliente.getIdcliente());

        // Cadastrar endereço
        Control.EnderecoController enderecoController =
                new Control.EnderecoController();

        boolean enderecoCadastrado = enderecoController.cadastrar(
                endereco.getBairro(),
                endereco.getCep(),
                endereco.getCidade(),
                endereco.getEstado(),
                endereco.getRua(),
                endereco.getNumero(),
                endereco.getCliente_idcliente()
        );

        if (enderecoCadastrado) {

            JOptionPane.showMessageDialog(
                    this,
                    "Cliente cadastrado com sucesso!"
            );

            painelEnderecoFlutuante.setVisible(false);

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Cliente cadastrado, mas houve um erro ao cadastrar o endereço."
            );
        }

    } catch (java.sql.SQLException e) {

        JOptionPane.showMessageDialog(
                this,
                "Erro ao cadastrar cliente: " + e.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE
        );

        e.printStackTrace();
    }


    }//GEN-LAST:event_btnFinalizarCadastroActionPerformed

    private void btnSalvarAlteracoesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarAlteracoesActionPerformed
     

    if (txtCodigoEdicao.getText().trim().isEmpty()) {
        JOptionPane.showMessageDialog(
                this,
                "Selecione um cliente na tabela."
        );
        return;
    }

    try {

        int idcliente = Integer.parseInt(
                txtCodigoEdicao.getText().trim()
        );

        String nome = txtNomeEdicao.getText().trim();
        String cpf = txtCPFCdicao.getText().replaceAll("[^0-9]", "");
        String rg = txtRGEdicao.getText().trim();
        String telefone = txtTelefoneEdicao.getText().replaceAll("[^0-9]", "");
        String dataNascimento = txtDataNascimentoEdicao.getText().trim();
        String email = txtEmailEdicao.getText().trim();

        if (nome.isEmpty() || cpf.isEmpty() || telefone.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Preencha o nome, CPF e telefone."
            );
            return;
        }

        Control.ClienteController controller =
                new Control.ClienteController();

        // Recuperar cliente cadastrado
        Model.Cliente cliente =
                controller.buscarPorId(idcliente);

        if (cliente == null) {
            JOptionPane.showMessageDialog(
                    this,
                    "Cliente não encontrado."
            );
            return;
        }

        // Atualizar dados do Model
        cliente.setNome(nome);
        cliente.setCpf(cpf);
        cliente.setRg(rg);
        cliente.setTelefone(telefone);
        cliente.setDataNascimento(dataNascimento);
        cliente.setEmail(email);

        // Salvar alterações
        boolean atualizado = controller.atualizar(cliente);

        if (atualizado) {

            JOptionPane.showMessageDialog(
                    this,
                    "Alterações salvas com sucesso!"
            );

            pesquisarClientesEdicao();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Não foi possível salvar as alterações."
            );
        }

    } catch (NumberFormatException e) {

        JOptionPane.showMessageDialog(
                this,
                "Código do cliente inválido."
        );

    } catch (java.sql.SQLException e) {

        JOptionPane.showMessageDialog(
                this,
                "Erro ao salvar alterações: " + e.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE
        );

        e.printStackTrace();
    }

    }//GEN-LAST:event_btnSalvarAlteracoesActionPerformed

    
private void pesquisarHistoricoCliente() {

    String pesquisa = txtPesquisaHistorico.getText().trim();

    if (pesquisa.isEmpty()) {
        JOptionPane.showMessageDialog(
                this,
                "Digite o nome, CPF ou código do cliente."
        );
        return;
    }

    try {

        Control.ClienteController controller =
                new Control.ClienteController();

        java.util.List<Model.Cliente> todosClientes =
                controller.listar();

        java.util.List<Model.Cliente> clientesEncontrados =
                new java.util.ArrayList<>();

        for (Model.Cliente cliente : todosClientes) {

            boolean correspondePesquisa =
                    String.valueOf(cliente.getIdcliente()).contains(pesquisa)
                    || (cliente.getNome() != null
                    && cliente.getNome().toLowerCase().contains(
                            pesquisa.toLowerCase()))
                    || (cliente.getCpf() != null
                    && cliente.getCpf().contains(pesquisa));

            if (correspondePesquisa) {
                clientesEncontrados.add(cliente);
            }
        }

        javax.swing.table.DefaultTableModel modelo =
                (javax.swing.table.DefaultTableModel)
                tabelaComprasHistorico.getModel();

        modelo.setRowCount(0);

        if (clientesEncontrados.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Nenhum cliente encontrado."
            );
            return;
        }

        if (clientesEncontrados.size() > 1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Foram encontrados vários clientes. "
                    + "Digite o CPF ou código para localizar "
                    + "um cliente específico."
            );
            return;
        }

        // Cliente localizado
        Model.Cliente cliente = clientesEncontrados.get(0);

        int idcliente = cliente.getIdcliente();

        // Aqui será carregado o histórico de compras
        // utilizando o Controller e o Model de compras.

        JOptionPane.showMessageDialog(
                this,
                "Cliente localizado: " + cliente.getNome()
                + "\nCódigo: " + idcliente
                + "\nCPF: " + cliente.getCpf()
        );

    } catch (java.sql.SQLException e) {

        JOptionPane.showMessageDialog(
                this,
                "Erro ao pesquisar cliente: " + e.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE
        );

        e.printStackTrace();
    }
}



    
    private void btnPesquisarHistoricoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarHistoricoActionPerformed
        // TODO add your handling code here:
      pesquisarHistoricoCliente();
      pesquisarHistoricoCompras();
    }//GEN-LAST:event_btnPesquisarHistoricoActionPerformed

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
    private javax.swing.JButton btnFinalizarCadastro;
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
    private javax.swing.JLabel chick;
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
    private javax.swing.JPanel lBSaldoPendente;
    private javax.swing.JLabel lBSubtituloCompras;
    private javax.swing.JLabel lBsubTotalPagp;
    private javax.swing.JLabel lBsubValorPendente;
    private javax.swing.JLabel lBtotalPago;
    private javax.swing.JLabel lBvalorPendente;
    private javax.swing.JLabel lbAnoRelatorio;
    private javax.swing.JLabel lbBairro;
    private javax.swing.JLabel lbBairroCadastro;
    private javax.swing.JLabel lbCiade;
    private javax.swing.JLabel lbCidadeCadastro;
    private javax.swing.JLabel lbData;
    private javax.swing.JLabel lbDatalheContato;
    private javax.swing.JLabel lbDetalhesdocliente;
    private javax.swing.JLabel lbEmail;
    private javax.swing.JLabel lbEndereco;
    private javax.swing.JLabel lbEnderecocadastro;
    private javax.swing.JLabel lbGerarComprovante;
    private javax.swing.JLabel lbIconePendente;
    private javax.swing.JLabel lbIconeTotalCompras;
    private javax.swing.JLabel lbIconeValorTotal;
    private javax.swing.JLabel lbIconetotalPedidos;
    private javax.swing.JLabel lbMesRelatorio;
    private javax.swing.JLabel lbPeriodoRelatorio;
    private javax.swing.JLabel lbRg;
    private javax.swing.JLabel lbSenha;
    private javax.swing.JLabel lbSituacaoCadastral;
    private javax.swing.JLabel lbStatuscadastro;
    private javax.swing.JLabel lbTituloEnderecocadastro;
    private javax.swing.JLabel lbTotalPagoCompras;
    private javax.swing.JLabel lbValortotalCompras;
    private javax.swing.JLabel lbdadosPessoais;
    private javax.swing.JLabel lbenderecoflutuante;
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
    private javax.swing.JPanel painelEnderecoFlutuante;
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
    private javax.swing.JTable tbPedidosClientes;
    private javax.swing.JTextField tfBairrocadastro;
    private javax.swing.JTextField tfCidadeCadastro;
    private javax.swing.JTextField tfEnderecoCadastro;
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
    private javax.swing.JTextField txtRGEdicao;
    private javax.swing.JTextField txtTelefoneEdicao;
    // End of variables declaration//GEN-END:variables
}
