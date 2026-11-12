/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import Model.Usuario;
import javax.swing.JOptionPane;

/**
 *
 * @author AntônioVieira
 */
public class AlterarSenha extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(AlterarSenha.class.getName());

    private MenuPadrao menu;  
    private Usuario usuario;
    public AlterarSenha(Usuario usuario) {
        initComponents();
         this.usuario= usuario;
        this.menu=MenuPadrao.instalar(
        this,
        usuario,
        "alterarSenha",
        this::navegar,
        this::dispose
    );
        this.setExtendedState(TelaEntregas.MAXIMIZED_BOTH);
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


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        areaAlterarSenha = new javax.swing.JPanel();
        spAlterarSenha = new javax.swing.JSeparator();
        lbSenhaAtual = new javax.swing.JLabel();
        txSenhaAtual = new javax.swing.JTextField();
        btnMostrarSenhaAtualAlterarSenha = new javax.swing.JButton();
        spSenhaAtual = new javax.swing.JSeparator();
        lbSenhaNovaAlterarSenha = new javax.swing.JLabel();
        tfSenhaNovaAlterarSenha = new javax.swing.JTextField();
        btnMostrarSenhaNova = new javax.swing.JButton();
        lbConfirmarSenharNovaAlterarSenha = new javax.swing.JLabel();
        tfConfirmarSenhaNovaAlterarSenha = new javax.swing.JTextField();
        btnMostrarConfirmarNovaSenha = new javax.swing.JButton();
        btnCancelarAlterarSenha = new javax.swing.JButton();
        btnSalvarSenhaNovaAlterarSenha = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbSenhaAtual.setText("Senha Atual");

        txSenhaAtual.setText("Digite sua senha atual");

        btnMostrarSenhaAtualAlterarSenha.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnMostrarSenhaAtualAlterarSenha.setText("Mostrar");

        lbSenhaNovaAlterarSenha.setText("Nova Senha");

        tfSenhaNovaAlterarSenha.setText("Minimo 8 caracteres");

        btnMostrarSenhaNova.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnMostrarSenhaNova.setText("Mostrar");

        lbConfirmarSenharNovaAlterarSenha.setText("Confirmar nova senha");

        tfConfirmarSenhaNovaAlterarSenha.setText("Repita a nova senha");

        btnMostrarConfirmarNovaSenha.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnMostrarConfirmarNovaSenha.setText("Mostrar");

        btnCancelarAlterarSenha.setBackground(new java.awt.Color(255, 51, 51));
        btnCancelarAlterarSenha.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnCancelarAlterarSenha.setForeground(new java.awt.Color(255, 255, 255));
        btnCancelarAlterarSenha.setText("Cancelar");

        btnSalvarSenhaNovaAlterarSenha.setBackground(new java.awt.Color(0, 0, 204));
        btnSalvarSenhaNovaAlterarSenha.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnSalvarSenhaNovaAlterarSenha.setForeground(new java.awt.Color(255, 255, 255));
        btnSalvarSenhaNovaAlterarSenha.setText("Salvar nova senha");

        javax.swing.GroupLayout areaAlterarSenhaLayout = new javax.swing.GroupLayout(areaAlterarSenha);
        areaAlterarSenha.setLayout(areaAlterarSenhaLayout);
        areaAlterarSenhaLayout.setHorizontalGroup(
            areaAlterarSenhaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(areaAlterarSenhaLayout.createSequentialGroup()
                .addGroup(areaAlterarSenhaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(spAlterarSenha, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(areaAlterarSenhaLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(areaAlterarSenhaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(areaAlterarSenhaLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(txSenhaAtual)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnMostrarSenhaAtualAlterarSenha))
                            .addGroup(areaAlterarSenhaLayout.createSequentialGroup()
                                .addComponent(lbSenhaAtual)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(areaAlterarSenhaLayout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(areaAlterarSenhaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbSenhaNovaAlterarSenha)
                            .addComponent(spSenhaAtual, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(areaAlterarSenhaLayout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(areaAlterarSenhaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(areaAlterarSenhaLayout.createSequentialGroup()
                                .addComponent(lbConfirmarSenharNovaAlterarSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(areaAlterarSenhaLayout.createSequentialGroup()
                                .addComponent(tfConfirmarSenhaNovaAlterarSenha)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnMostrarConfirmarNovaSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, areaAlterarSenhaLayout.createSequentialGroup()
                                .addComponent(tfSenhaNovaAlterarSenha)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnMostrarSenhaNova)))))
                .addContainerGap())
            .addGroup(areaAlterarSenhaLayout.createSequentialGroup()
                .addGap(88, 88, 88)
                .addComponent(btnSalvarSenhaNovaAlterarSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(btnCancelarAlterarSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(227, Short.MAX_VALUE))
        );
        areaAlterarSenhaLayout.setVerticalGroup(
            areaAlterarSenhaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(areaAlterarSenhaLayout.createSequentialGroup()
                .addGap(134, 134, 134)
                .addComponent(spAlterarSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbSenhaAtual)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(areaAlterarSenhaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnMostrarSenhaAtualAlterarSenha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txSenhaAtual))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(spSenhaAtual, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(lbSenhaNovaAlterarSenha)
                .addGap(19, 19, 19)
                .addGroup(areaAlterarSenhaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnMostrarSenhaNova, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfSenhaNovaAlterarSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(51, 51, 51)
                .addComponent(lbConfirmarSenharNovaAlterarSenha)
                .addGap(18, 18, 18)
                .addGroup(areaAlterarSenhaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfConfirmarSenhaNovaAlterarSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnMostrarConfirmarNovaSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addGroup(areaAlterarSenhaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSalvarSenhaNovaAlterarSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelarAlterarSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(57, Short.MAX_VALUE))
        );

        getContentPane().add(areaAlterarSenha, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 12, 800, 590));

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
        java.awt.EventQueue.invokeLater(() -> new AlterarSenha(usuario).setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel areaAlterarSenha;
    private javax.swing.JButton btnCancelarAlterarSenha;
    private javax.swing.JButton btnMostrarConfirmarNovaSenha;
    private javax.swing.JButton btnMostrarSenhaAtualAlterarSenha;
    private javax.swing.JButton btnMostrarSenhaNova;
    private javax.swing.JButton btnSalvarSenhaNovaAlterarSenha;
    private javax.swing.JLabel lbConfirmarSenharNovaAlterarSenha;
    private javax.swing.JLabel lbSenhaAtual;
    private javax.swing.JLabel lbSenhaNovaAlterarSenha;
    private javax.swing.JSeparator spAlterarSenha;
    private javax.swing.JSeparator spSenhaAtual;
    private javax.swing.JTextField tfConfirmarSenhaNovaAlterarSenha;
    private javax.swing.JTextField tfSenhaNovaAlterarSenha;
    private javax.swing.JTextField txSenhaAtual;
    // End of variables declaration//GEN-END:variables
}
