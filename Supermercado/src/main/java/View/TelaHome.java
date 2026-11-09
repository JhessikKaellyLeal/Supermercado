/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import Model.Usuario;
import javax.swing.JOptionPane;


public class TelaHome extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(TelaHome.class.getName());

    private MenuPadrao menu;  
    private Usuario usuario;
    
    public TelaHome(Usuario usuario) {  
        initComponents();  
        
        this.usuario= usuario;
      
       this.menu= MenuPadrao.instalar(
        this,
        usuario,
        "inicio",
        this::navegar,
        this::dispose
    );
        
       this.setExtendedState(TelaHome.MAXIMIZED_BOTH);
        
    }// fim do construtor
    
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

 

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpBanner = new javax.swing.JPanel();
        lbOla = new javax.swing.JLabel();
        lbBemVindo = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        lbJuntosPor = new javax.swing.JLabel();
        lbDiaDia = new javax.swing.JLabel();
        lbMaisPratico = new javax.swing.JLabel();
        jpAçoesRapidas = new javax.swing.JPanel();
        lbAçoesRapidas = new javax.swing.JLabel();
        btnNovaVenda = new javax.swing.JButton();
        btnCadastrarProduto = new javax.swing.JButton();
        btnCadastrarClientes = new javax.swing.JButton();
        btnNovoPedido = new javax.swing.JButton();
        btnContasPagarReceber = new javax.swing.JButton();
        btnRelatorios = new javax.swing.JButton();
        jpResumoGeral = new javax.swing.JPanel();
        lbResumoGeral = new javax.swing.JLabel();
        jpVendasHoje = new javax.swing.JPanel();
        lbVendasHoje = new javax.swing.JLabel();
        lb4258 = new javax.swing.JLabel();
        lbemRelacaoaOntem = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jpNotificações = new javax.swing.JPanel();
        lbNotificaçoes = new javax.swing.JLabel();
        lbVerTodas = new javax.swing.JLabel();
        lbPedido4587 = new javax.swing.JLabel();
        lb10e24 = new javax.swing.JLabel();
        lbEstoqueBaixo = new javax.swing.JLabel();
        lb9e50 = new javax.swing.JLabel();
        lbContaPagarVencida = new javax.swing.JLabel();
        lb8e32 = new javax.swing.JLabel();
        VendaFinalizada = new javax.swing.JLabel();
        lbNovoClienteCadastrado = new javax.swing.JLabel();
        lb8e15 = new javax.swing.JLabel();
        lb7e48 = new javax.swing.JLabel();
        lbJoaoPereira = new javax.swing.JLabel();
        lbCaixa2 = new javax.swing.JLabel();
        lbFornecedor = new javax.swing.JLabel();
        lbReporProduto = new javax.swing.JLabel();
        lbClienteMariaSilva = new javax.swing.JLabel();
        jpAtalhos = new javax.swing.JPanel();
        lbAtalhos = new javax.swing.JLabel();
        btnNovaVenda2 = new javax.swing.JButton();
        btnCadastrarProduto2 = new javax.swing.JButton();
        btnNovoPedido2 = new javax.swing.JButton();
        btnRelatorio2 = new javax.swing.JButton();
        btnCadastrarCliente = new javax.swing.JButton();
        btnContasPagarReceber2 = new javax.swing.JButton();
        jpPedidosPendentes = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbPedidosEntregasPendentes = new javax.swing.JTable();
        lbPedidosEntregasPendentes = new javax.swing.JLabel();
        lbVerTodas2 = new javax.swing.JLabel();
        jpContasPagarReceber = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbContasPagarReceber = new javax.swing.JTable();
        lbContasPagarReceber = new javax.swing.JLabel();
        lbVerTodas3 = new javax.swing.JLabel();
        jpCentralNotificações = new javax.swing.JPanel();
        lbCentralNotificaçoes = new javax.swing.JLabel();
        lbVoçePossui3 = new javax.swing.JLabel();
        btnVerNotificaçoes = new javax.swing.JButton();
        jpHistorico = new javax.swing.JPanel();
        lbHistorico = new javax.swing.JLabel();
        lbAcesseHistoricoMovimentaçoes = new javax.swing.JLabel();
        btnVerHistorico = new javax.swing.JButton();
        jpAlertas = new javax.swing.JPanel();
        lbConfigurarAlertas = new javax.swing.JLabel();
        lbPersonalizarAlertas = new javax.swing.JLabel();
        btnConfigurar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        setMaximumSize(new java.awt.Dimension(2100000, 2100000));
        setPreferredSize(new java.awt.Dimension(720, 864));

        jpBanner.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jpBanner.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N

        lbOla.setText("Ola, Admin!");

        lbBemVindo.setText("Bem-vindo ao sistema do Supermrecado Boa Economia.");

        jLabel11.setText("Gerencie as açoes do Supermercado Por aqui.");

        lbJuntosPor.setText("Juntos Por");

        lbDiaDia.setText("Um dia a dia");

        lbMaisPratico.setText("Mais pratico!");

        javax.swing.GroupLayout jpBannerLayout = new javax.swing.GroupLayout(jpBanner);
        jpBanner.setLayout(jpBannerLayout);
        jpBannerLayout.setHorizontalGroup(
            jpBannerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpBannerLayout.createSequentialGroup()
                .addGroup(jpBannerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpBannerLayout.createSequentialGroup()
                        .addGap(100, 100, 100)
                        .addComponent(jLabel11))
                    .addGroup(jpBannerLayout.createSequentialGroup()
                        .addGap(73, 73, 73)
                        .addGroup(jpBannerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbOla)
                            .addComponent(lbBemVindo))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jpBannerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbJuntosPor)
                            .addGroup(jpBannerLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(jpBannerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jpBannerLayout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(lbMaisPratico))
                                    .addComponent(lbDiaDia))))))
                .addGap(70, 70, 70))
        );
        jpBannerLayout.setVerticalGroup(
            jpBannerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpBannerLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jpBannerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbJuntosPor)
                    .addComponent(lbOla))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpBannerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbBemVindo)
                    .addComponent(lbDiaDia))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbMaisPratico)
                .addGap(21, 21, 21)
                .addComponent(jLabel11)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jpAçoesRapidas.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jpAçoesRapidas.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N

        lbAçoesRapidas.setText("Açoes Rapidas");

        btnNovaVenda.setText("Nova Venda");

        btnCadastrarProduto.setText("Cadastar Produto");

        btnCadastrarClientes.setText("Cadastrar Clientes");

        btnNovoPedido.setText("Novo Pedido");

        btnContasPagarReceber.setText("Contas a Pagar/Receber");

        btnRelatorios.setText("Relatorios");

        javax.swing.GroupLayout jpAçoesRapidasLayout = new javax.swing.GroupLayout(jpAçoesRapidas);
        jpAçoesRapidas.setLayout(jpAçoesRapidasLayout);
        jpAçoesRapidasLayout.setHorizontalGroup(
            jpAçoesRapidasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpAçoesRapidasLayout.createSequentialGroup()
                .addContainerGap(44, Short.MAX_VALUE)
                .addComponent(btnNovaVenda)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCadastrarProduto)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCadastrarClientes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnNovoPedido)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnContasPagarReceber)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRelatorios)
                .addGap(29, 29, 29))
            .addGroup(jpAçoesRapidasLayout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(lbAçoesRapidas)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpAçoesRapidasLayout.setVerticalGroup(
            jpAçoesRapidasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpAçoesRapidasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbAçoesRapidas)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jpAçoesRapidasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNovaVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCadastrarProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCadastrarClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNovoPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnContasPagarReceber, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRelatorios, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        jpResumoGeral.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jpResumoGeral.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N

        lbResumoGeral.setText("Resumo geral");

        lbVendasHoje.setText("Vendas hoje");

        lb4258.setText("R$ 4.258,90");

        lbemRelacaoaOntem.setText("+12% em relaçao a ontem");

        javax.swing.GroupLayout jpVendasHojeLayout = new javax.swing.GroupLayout(jpVendasHoje);
        jpVendasHoje.setLayout(jpVendasHojeLayout);
        jpVendasHojeLayout.setHorizontalGroup(
            jpVendasHojeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpVendasHojeLayout.createSequentialGroup()
                .addGroup(jpVendasHojeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpVendasHojeLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(lbemRelacaoaOntem))
                    .addGroup(jpVendasHojeLayout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(lb4258))
                    .addGroup(jpVendasHojeLayout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(lbVendasHoje)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpVendasHojeLayout.setVerticalGroup(
            jpVendasHojeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpVendasHojeLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(lbVendasHoje)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lb4258)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbemRelacaoaOntem)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jLabel25.setText("Caixas abertos");

        jLabel31.setText("3");

        jLabel32.setText("De 4 caixas");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLabel25))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(jLabel32))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(72, 72, 72)
                        .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(61, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel25)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel31)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel32)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel26.setText("Pedidos Pendentes");

        jLabel33.setText("5");

        jLabel34.setText("Aguardando a entrega ");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel34)
                    .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel12Layout.createSequentialGroup()
                            .addGap(27, 27, 27)
                            .addComponent(jLabel26))
                        .addGroup(jPanel12Layout.createSequentialGroup()
                            .addGap(85, 85, 85)
                            .addComponent(jLabel33))))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel26)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel33)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel34)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel27.setText("Contas a pagar");

        jLabel35.setText("2.340,00");

        jLabel36.setText("3 Pendentes");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(jLabel35))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel36)
                            .addComponent(jLabel27))))
                .addContainerGap(36, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel27)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel35)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel36)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel14.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel14.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jLabel28.setText("Contas a receber");

        jLabel37.setText("1.870,50");

        jLabel38.setText("2 Pendentes");

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel38)
                            .addComponent(jLabel37))
                        .addGap(37, 37, 37))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                        .addComponent(jLabel28)
                        .addGap(29, 29, 29))))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel28)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel37)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel38)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jpResumoGeralLayout = new javax.swing.GroupLayout(jpResumoGeral);
        jpResumoGeral.setLayout(jpResumoGeralLayout);
        jpResumoGeralLayout.setHorizontalGroup(
            jpResumoGeralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpResumoGeralLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jpResumoGeralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpResumoGeralLayout.createSequentialGroup()
                        .addComponent(lbResumoGeral)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jpResumoGeralLayout.createSequentialGroup()
                        .addComponent(jpVendasHoje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                        .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30))))
        );
        jpResumoGeralLayout.setVerticalGroup(
            jpResumoGeralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpResumoGeralLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbResumoGeral)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jpResumoGeralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jpVendasHoje, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(38, Short.MAX_VALUE))
        );

        jpNotificações.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jpNotificações.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N

        lbNotificaçoes.setText("Notificaçoes");

        lbVerTodas.setText("Ver todas");

        lbPedido4587.setText("Pedido #4587 aguardando entrega");

        lb10e24.setText("10:24");

        lbEstoqueBaixo.setText("Estoque baixo: Arroz (5 un.)");

        lb9e50.setText("09:50");

        lbContaPagarVencida.setText("Conta a pagar vencida");

        lb8e32.setText("08:32");

        VendaFinalizada.setText("Venda finalizada");

        lbNovoClienteCadastrado.setText("Novo cliente cadastrado");

        lb8e15.setText("08:15");

        lb7e48.setText("7:48");

        lbJoaoPereira.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        lbJoaoPereira.setText("João Pereira");

        lbCaixa2.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        lbCaixa2.setText("R$ 156,90 - Caixa 2");

        lbFornecedor.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        lbFornecedor.setText("Fornecedor: Distribuidora ABC");

        lbReporProduto.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        lbReporProduto.setText("Repor o Produto");

        lbClienteMariaSilva.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        lbClienteMariaSilva.setText("Cliente: Maria Silva");

        javax.swing.GroupLayout jpNotificaçõesLayout = new javax.swing.GroupLayout(jpNotificações);
        jpNotificações.setLayout(jpNotificaçõesLayout);
        jpNotificaçõesLayout.setHorizontalGroup(
            jpNotificaçõesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpNotificaçõesLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jpNotificaçõesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpNotificaçõesLayout.createSequentialGroup()
                        .addGroup(jpNotificaçõesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpNotificaçõesLayout.createSequentialGroup()
                                .addComponent(lbNotificaçoes)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lbVerTodas))
                            .addGroup(jpNotificaçõesLayout.createSequentialGroup()
                                .addGroup(jpNotificaçõesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(jpNotificaçõesLayout.createSequentialGroup()
                                        .addComponent(lbContaPagarVencida)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lb8e32))
                                    .addGroup(jpNotificaçõesLayout.createSequentialGroup()
                                        .addGroup(jpNotificaçõesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lbPedido4587)
                                            .addComponent(lbEstoqueBaixo))
                                        .addGap(18, 18, 18)
                                        .addGroup(jpNotificaçõesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lb9e50)
                                            .addComponent(lb10e24)))
                                    .addGroup(jpNotificaçõesLayout.createSequentialGroup()
                                        .addGroup(jpNotificaçõesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(lbCaixa2)
                                            .addComponent(VendaFinalizada))
                                        .addGap(113, 113, 113)
                                        .addComponent(lb8e15))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jpNotificaçõesLayout.createSequentialGroup()
                                        .addGroup(jpNotificaçõesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(lbFornecedor)
                                            .addComponent(lbNovoClienteCadastrado))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lb7e48)
                                        .addGap(6, 6, 6)))
                                .addGap(0, 38, Short.MAX_VALUE)))
                        .addGap(16, 16, 16))
                    .addGroup(jpNotificaçõesLayout.createSequentialGroup()
                        .addGroup(jpNotificaçõesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpNotificaçõesLayout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addComponent(lbJoaoPereira))
                            .addGroup(jpNotificaçõesLayout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addComponent(lbClienteMariaSilva)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(jpNotificaçõesLayout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addComponent(lbReporProduto)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jpNotificaçõesLayout.setVerticalGroup(
            jpNotificaçõesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpNotificaçõesLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jpNotificaçõesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbNotificaçoes)
                    .addComponent(lbVerTodas))
                .addGap(18, 18, 18)
                .addGroup(jpNotificaçõesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbPedido4587)
                    .addComponent(lb10e24))
                .addGap(1, 1, 1)
                .addComponent(lbClienteMariaSilva)
                .addGap(18, 18, 18)
                .addGroup(jpNotificaçõesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbEstoqueBaixo)
                    .addComponent(lb9e50))
                .addGap(1, 1, 1)
                .addComponent(lbReporProduto)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jpNotificaçõesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbContaPagarVencida)
                    .addComponent(lb8e32))
                .addGap(4, 4, 4)
                .addComponent(lbFornecedor)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jpNotificaçõesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(VendaFinalizada)
                    .addComponent(lb8e15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbCaixa2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jpNotificaçõesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbNovoClienteCadastrado)
                    .addComponent(lb7e48))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbJoaoPereira)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jpAtalhos.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jpAtalhos.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N

        lbAtalhos.setText("Atalhos");

        btnNovaVenda2.setText("Nova Venda");
        btnNovaVenda2.addActionListener(this::btnNovaVenda2ActionPerformed);

        btnCadastrarProduto2.setText("Cadastrar Produto");
        btnCadastrarProduto2.addActionListener(this::btnCadastrarProduto2ActionPerformed);

        btnNovoPedido2.setText("Novo Pedido");

        btnRelatorio2.setText("Relatorio");

        btnCadastrarCliente.setText("Cadastrar Cliente");

        btnContasPagarReceber2.setText("Contas a Pagar/Receber");

        javax.swing.GroupLayout jpAtalhosLayout = new javax.swing.GroupLayout(jpAtalhos);
        jpAtalhos.setLayout(jpAtalhosLayout);
        jpAtalhosLayout.setHorizontalGroup(
            jpAtalhosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpAtalhosLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jpAtalhosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpAtalhosLayout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(lbAtalhos)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpAtalhosLayout.createSequentialGroup()
                        .addGroup(jpAtalhosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btnNovaVenda2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnCadastrarCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnContasPagarReceber2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                        .addGroup(jpAtalhosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btnNovoPedido2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnCadastrarProduto2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnRelatorio2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(12, 12, 12))))
        );
        jpAtalhosLayout.setVerticalGroup(
            jpAtalhosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpAtalhosLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(lbAtalhos)
                .addGap(18, 18, 18)
                .addGroup(jpAtalhosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnCadastrarProduto2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnNovaVenda2, javax.swing.GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE))
                .addGap(27, 27, 27)
                .addGroup(jpAtalhosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCadastrarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNovoPedido2, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addGroup(jpAtalhosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnContasPagarReceber2, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRelatorio2, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(70, 70, 70))
        );

        jpPedidosPendentes.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jpPedidosPendentes.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N

        tbPedidosEntregasPendentes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "N°Pedido", "Cliente", "Data", "Status", "Entrega"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tbPedidosEntregasPendentes);

        lbPedidosEntregasPendentes.setText("Pedidos e entregas Pendentes");

        lbVerTodas2.setText("Ver todas >");

        javax.swing.GroupLayout jpPedidosPendentesLayout = new javax.swing.GroupLayout(jpPedidosPendentes);
        jpPedidosPendentes.setLayout(jpPedidosPendentesLayout);
        jpPedidosPendentesLayout.setHorizontalGroup(
            jpPedidosPendentesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpPedidosPendentesLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jpPedidosPendentesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jpPedidosPendentesLayout.createSequentialGroup()
                        .addComponent(lbPedidosEntregasPendentes)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbVerTodas2))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(111, Short.MAX_VALUE))
        );
        jpPedidosPendentesLayout.setVerticalGroup(
            jpPedidosPendentesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpPedidosPendentesLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jpPedidosPendentesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbPedidosEntregasPendentes)
                    .addComponent(lbVerTodas2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42))
        );

        jpContasPagarReceber.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jpContasPagarReceber.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N

        tbContasPagarReceber.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Descriçao", "Tipo", "Valor", "Vencimento"
            }
        ));
        jScrollPane2.setViewportView(tbContasPagarReceber);

        lbContasPagarReceber.setText("Contas a pagar/Receber");

        lbVerTodas3.setText("Ver todas >");

        javax.swing.GroupLayout jpContasPagarReceberLayout = new javax.swing.GroupLayout(jpContasPagarReceber);
        jpContasPagarReceber.setLayout(jpContasPagarReceberLayout);
        jpContasPagarReceberLayout.setHorizontalGroup(
            jpContasPagarReceberLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpContasPagarReceberLayout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(47, Short.MAX_VALUE))
            .addGroup(jpContasPagarReceberLayout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(lbContasPagarReceber)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbVerTodas3)
                .addGap(28, 28, 28))
        );
        jpContasPagarReceberLayout.setVerticalGroup(
            jpContasPagarReceberLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpContasPagarReceberLayout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addGroup(jpContasPagarReceberLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbContasPagarReceber)
                    .addComponent(lbVerTodas3))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37))
        );

        jpCentralNotificações.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jpCentralNotificações.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N

        lbCentralNotificaçoes.setText("Central de Notificações");

        lbVoçePossui3.setText("Voçe possui 3 novas notificações");

        btnVerNotificaçoes.setText("Ver notificações");

        javax.swing.GroupLayout jpCentralNotificaçõesLayout = new javax.swing.GroupLayout(jpCentralNotificações);
        jpCentralNotificações.setLayout(jpCentralNotificaçõesLayout);
        jpCentralNotificaçõesLayout.setHorizontalGroup(
            jpCentralNotificaçõesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpCentralNotificaçõesLayout.createSequentialGroup()
                .addGroup(jpCentralNotificaçõesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpCentralNotificaçõesLayout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(lbVoçePossui3))
                    .addGroup(jpCentralNotificaçõesLayout.createSequentialGroup()
                        .addGap(103, 103, 103)
                        .addComponent(btnVerNotificaçoes))
                    .addGroup(jpCentralNotificaçõesLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lbCentralNotificaçoes)))
                .addContainerGap(127, Short.MAX_VALUE))
        );
        jpCentralNotificaçõesLayout.setVerticalGroup(
            jpCentralNotificaçõesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpCentralNotificaçõesLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(lbCentralNotificaçoes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbVoçePossui3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnVerNotificaçoes)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jpHistorico.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jpHistorico.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N

        lbHistorico.setText("Historico");

        lbAcesseHistoricoMovimentaçoes.setText("Acesse o historico de movimentações do sistema");

        btnVerHistorico.setText("Ver Historico");

        javax.swing.GroupLayout jpHistoricoLayout = new javax.swing.GroupLayout(jpHistorico);
        jpHistorico.setLayout(jpHistoricoLayout);
        jpHistoricoLayout.setHorizontalGroup(
            jpHistoricoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpHistoricoLayout.createSequentialGroup()
                .addGroup(jpHistoricoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpHistoricoLayout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addComponent(lbAcesseHistoricoMovimentaçoes))
                    .addGroup(jpHistoricoLayout.createSequentialGroup()
                        .addGap(110, 110, 110)
                        .addComponent(btnVerHistorico))
                    .addGroup(jpHistoricoLayout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(lbHistorico)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpHistoricoLayout.setVerticalGroup(
            jpHistoricoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpHistoricoLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(lbHistorico)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbAcesseHistoricoMovimentaçoes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnVerHistorico)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jpAlertas.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jpAlertas.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N

        lbConfigurarAlertas.setText("Configurar Alertas");

        lbPersonalizarAlertas.setText("Personalizar os alertas do sistema");

        btnConfigurar.setText("Configurar");
        btnConfigurar.addActionListener(this::btnConfigurarActionPerformed);

        javax.swing.GroupLayout jpAlertasLayout = new javax.swing.GroupLayout(jpAlertas);
        jpAlertas.setLayout(jpAlertasLayout);
        jpAlertasLayout.setHorizontalGroup(
            jpAlertasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpAlertasLayout.createSequentialGroup()
                .addGroup(jpAlertasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpAlertasLayout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(lbPersonalizarAlertas))
                    .addGroup(jpAlertasLayout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addComponent(btnConfigurar))
                    .addGroup(jpAlertasLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lbConfigurarAlertas)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpAlertasLayout.setVerticalGroup(
            jpAlertasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpAlertasLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(lbConfigurarAlertas)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbPersonalizarAlertas)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnConfigurar)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jpBanner, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jpResumoGeral, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jpAçoesRapidas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jpNotificações, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jpCentralNotificações, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jpHistorico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jpAlertas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jpPedidosPendentes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jpContasPagarReceber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jpAtalhos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jpBanner, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jpAçoesRapidas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jpResumoGeral, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jpNotificações, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jpPedidosPendentes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jpContasPagarReceber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jpAlertas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jpHistorico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jpCentralNotificações, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jpAtalhos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(108, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnConfigurarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfigurarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnConfigurarActionPerformed

    private void btnCadastrarProduto2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastrarProduto2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCadastrarProduto2ActionPerformed

    private void btnNovaVenda2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovaVenda2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnNovaVenda2ActionPerformed

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
        java.awt.EventQueue.invokeLater(() -> new TelaHome(usuario).setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel VendaFinalizada;
    private javax.swing.JButton btnCadastrarCliente;
    private javax.swing.JButton btnCadastrarClientes;
    private javax.swing.JButton btnCadastrarProduto;
    private javax.swing.JButton btnCadastrarProduto2;
    private javax.swing.JButton btnConfigurar;
    private javax.swing.JButton btnContasPagarReceber;
    private javax.swing.JButton btnContasPagarReceber2;
    private javax.swing.JButton btnNovaVenda;
    private javax.swing.JButton btnNovaVenda2;
    private javax.swing.JButton btnNovoPedido;
    private javax.swing.JButton btnNovoPedido2;
    private javax.swing.JButton btnRelatorio2;
    private javax.swing.JButton btnRelatorios;
    private javax.swing.JButton btnVerHistorico;
    private javax.swing.JButton btnVerNotificaçoes;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel jpAlertas;
    private javax.swing.JPanel jpAtalhos;
    private javax.swing.JPanel jpAçoesRapidas;
    private javax.swing.JPanel jpBanner;
    private javax.swing.JPanel jpCentralNotificações;
    private javax.swing.JPanel jpContasPagarReceber;
    private javax.swing.JPanel jpHistorico;
    private javax.swing.JPanel jpNotificações;
    private javax.swing.JPanel jpPedidosPendentes;
    private javax.swing.JPanel jpResumoGeral;
    private javax.swing.JPanel jpVendasHoje;
    private javax.swing.JLabel lb10e24;
    private javax.swing.JLabel lb4258;
    private javax.swing.JLabel lb7e48;
    private javax.swing.JLabel lb8e15;
    private javax.swing.JLabel lb8e32;
    private javax.swing.JLabel lb9e50;
    private javax.swing.JLabel lbAcesseHistoricoMovimentaçoes;
    private javax.swing.JLabel lbAtalhos;
    private javax.swing.JLabel lbAçoesRapidas;
    private javax.swing.JLabel lbBemVindo;
    private javax.swing.JLabel lbCaixa2;
    private javax.swing.JLabel lbCentralNotificaçoes;
    private javax.swing.JLabel lbClienteMariaSilva;
    private javax.swing.JLabel lbConfigurarAlertas;
    private javax.swing.JLabel lbContaPagarVencida;
    private javax.swing.JLabel lbContasPagarReceber;
    private javax.swing.JLabel lbDiaDia;
    private javax.swing.JLabel lbEstoqueBaixo;
    private javax.swing.JLabel lbFornecedor;
    private javax.swing.JLabel lbHistorico;
    private javax.swing.JLabel lbJoaoPereira;
    private javax.swing.JLabel lbJuntosPor;
    private javax.swing.JLabel lbMaisPratico;
    private javax.swing.JLabel lbNotificaçoes;
    private javax.swing.JLabel lbNovoClienteCadastrado;
    private javax.swing.JLabel lbOla;
    private javax.swing.JLabel lbPedido4587;
    private javax.swing.JLabel lbPedidosEntregasPendentes;
    private javax.swing.JLabel lbPersonalizarAlertas;
    private javax.swing.JLabel lbReporProduto;
    private javax.swing.JLabel lbResumoGeral;
    private javax.swing.JLabel lbVendasHoje;
    private javax.swing.JLabel lbVerTodas;
    private javax.swing.JLabel lbVerTodas2;
    private javax.swing.JLabel lbVerTodas3;
    private javax.swing.JLabel lbVoçePossui3;
    private javax.swing.JLabel lbemRelacaoaOntem;
    private javax.swing.JTable tbContasPagarReceber;
    private javax.swing.JTable tbPedidosEntregasPendentes;
    // End of variables declaration//GEN-END:variables
}
