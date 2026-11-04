package View;



import Model.Usuario;
import java.awt.*;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Consumer;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

// Componente reutilizável: menu lateral + cabeçalho + conteúdo da tela.
// Nenhuma biblioteca externa é necessária. Compatível com Java 8 ou superior.
public class MenuPadrao extends JPanel {
    public static final Color AZUL = new Color(16, 5, 139);
    private static final Color SELECIONADO = new Color(65, 53, 178);
    private final JPanel conteudo = new JPanel(new BorderLayout());
    private final JLabel nome = new JLabel();
    private final JLabel perfil = new JLabel();
    private final JLabel foto = new JLabel();
    private final Map<String, JButton> botoes = new LinkedHashMap<>();

    // O primeiro texto identifica a opção no código; o segundo aparece na tela.
   // ID da opção no código e texto exibido no menu.
private static final String[][] OPCOES = {
    {"inicio", "Início"},
    {"produtos", "Produtos"},
    {"gerenciamentoProdutos", "Gerenciamento de Produtos"},
    {"estoque", "Estoque"},
    {"fornecedores", "Fornecedores"},
    {"fornecedoresTerceirizados", "Fornecedores Terceirizados"},
    {"clientes", "Clientes"},
    {"pedidos", "Pedidos"},
    {"entregas", "Entregas"},
    {"vendas", "Vendas"},
    {"pdv", "PDV"},
    {"vendaPagamentos", "Venda e Pagamentos"},
    {"caixa", "Caixa"},
    {"financeiro", "Financeiro"},
    {"funcionariosRH", "Funcionários / RH"},
    {"perfilUsuarios", "Perfil de Usuários"},
    {"solicitacao", "Solicitações"},
    {"alterarSenha", "Alterar Senha"},
    {"recuperacaoSenha", "Recuperação de Senha"}
    
};

    public MenuPadrao() {
    }
public void abrirTela(JFrame telaAtual, JFrame proximaTela) {
    proximaTela.setLocationRelativeTo(null);
    proximaTela.setVisible(true);
    telaAtual.dispose();
}
    
    // aoNavegar recebe qual opção foi clicada. Cada projeto define suas telas.
    // aoSair define o que acontece no botão Sair.
    public MenuPadrao(Usuario usuario, Consumer<String> aoNavegar, Runnable aoSair) {
        setLayout(new BorderLayout(4, 4));
        setBackground(new Color(224, 222, 249));
        setBorder(new EmptyBorder(4, 4, 4, 4));

        JPanel lateral = new JPanel(new BorderLayout());
        lateral.setBackground(AZUL);
        lateral.setPreferredSize(new Dimension(260, 0));
        JPanel lista = new JPanel(new GridLayout(0, 1, 0, 3));
        lista.setBackground(AZUL);
        lista.setBorder(new EmptyBorder(22, 10, 12, 10));

        for (String[] opcao : OPCOES) {
            String id = opcao[0];
            JButton botao = criarBotao(opcao[1], id);
            botao.addActionListener(evt -> {
                selecionar(id);
                aoNavegar.accept(id);
            });
            botoes.put(id, botao);
            lista.add(botao);
        }
        // Rolagem para não esconder opções em monitores menores.
        JPanel areaLista = new JPanel(new BorderLayout());
        areaLista.setBackground(AZUL);
        areaLista.add(lista, BorderLayout.NORTH);
        JScrollPane rolagem = new JScrollPane(areaLista);
        rolagem.setBorder(null);
        rolagem.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        rolagem.getVerticalScrollBar().setUnitIncrement(20);
        lateral.add(rolagem, BorderLayout.CENTER);

        JButton sair = criarBotao("Sair", "sair");
        sair.addActionListener(evt -> aoSair.run());
        JPanel rodape = new JPanel(new BorderLayout());
        rodape.setBackground(AZUL);
        rodape.setBorder(new EmptyBorder(12, 10, 18, 10));
        rodape.add(sair);
        lateral.add(rodape, BorderLayout.SOUTH);
        add(lateral, BorderLayout.WEST);

        JPanel direita = new JPanel(new BorderLayout(0, 4));
        direita.setOpaque(false);
        direita.add(criarTopo(), BorderLayout.NORTH);
        conteudo.setBackground(new Color(242, 242, 242));
        direita.add(conteudo, BorderLayout.CENTER);
        add(direita, BorderLayout.CENTER);
        atualizarUsuario(usuario);
    }

    private JButton criarBotao(String texto, String id) {
        JButton botao = new JButton(texto, carregarIcone(id));
        botao.setFont(new Font("SansSerif", Font.BOLD, 18));
        botao.setForeground(Color.WHITE);
        botao.setBackground(AZUL);
        botao.setOpaque(true);
        botao.setContentAreaFilled(true);
        botao.setBorderPainted(false);
        botao.setFocusPainted(true);
        botao.setHorizontalAlignment(SwingConstants.LEFT);
        botao.setIconTextGap(14);
        botao.setBorder(new EmptyBorder(9, 14, 9, 6));
        botao.setPreferredSize(new Dimension(230, 43));
        botao.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return botao;
    }

    private Icon carregarIcone(String id) {
        // OPCIONAL: coloque PNGs brancos em src/main/resources/icones/.
        // Exemplo: produtos.png, clientes.png, vendas.png, sair.png.
        java.net.URL caminho = getClass().getResource("/icones/" + id + ".png");
        if (caminho != null) {
            return new ImageIcon(new ImageIcon(caminho).getImage()
                    .getScaledInstance(24, 24, Image.SCALE_SMOOTH));
        }
        // Sem PNG, o ícone já é desenhado pelo próprio Java.
        return new IconeMenu(id, 24);
    }

    private JPanel criarTopo() {
        JPanel topo = new JPanel(new BorderLayout(20, 0));
        topo.setBackground(AZUL);
        topo.setBorder(new EmptyBorder(16, 24, 16, 24));
        topo.setPreferredSize(new Dimension(0, 100));
        JLabel titulo = new JLabel("Supermercado");
        titulo.setFont(new Font("SansSerif", Font.BOLD, 30));
        titulo.setForeground(Color.WHITE);
        topo.add(titulo, BorderLayout.CENTER);

        JPanel dados = new JPanel(new GridLayout(2, 1, 0, 4));
        dados.setOpaque(false);
        nome.setForeground(Color.WHITE);
        nome.setFont(new Font("SansSerif", Font.BOLD, 16));
        perfil.setForeground(Color.WHITE);
        perfil.setFont(new Font("SansSerif", Font.PLAIN, 14));
        dados.add(nome);
        dados.add(perfil);
        foto.setPreferredSize(new Dimension(56, 56));
        foto.setHorizontalAlignment(SwingConstants.CENTER);
        JPanel usuario = new JPanel(new BorderLayout(12, 0));
        usuario.setOpaque(false);
        usuario.add(foto, BorderLayout.WEST);
        usuario.add(dados, BorderLayout.CENTER);
        topo.add(usuario, BorderLayout.EAST);
        return topo;
    }

    public void atualizarUsuario(Usuario usuario) {
        nome.setText(usuario.getNome());
        
       /* perfil.setText(usuario.getPerfil());
        ImageIcon imagem = usuario.getFoto();
        if (imagem != null && imagem.getIconWidth() > 0) {
            // Mantém a proporção da foto dentro de um espaço de 56 x 56.
            double escala = Math.min(56.0 / imagem.getIconWidth(),
                                     56.0 / imagem.getIconHeight());
            foto.setIcon(new ImageIcon(imagem.getImage().getScaledInstance(
                    Math.max(1, (int) (imagem.getIconWidth() * escala)),
                    Math.max(1, (int) (imagem.getIconHeight() * escala)),
                    Image.SCALE_SMOOTH)));
        } else {
            foto.setIcon(new IconeMenu("clientes", 48));
        }*/
        
    }

    // Troca só a parte central; os menus continuam visíveis.
    public void mostrarTela(Component tela) {
        conteudo.removeAll();
        conteudo.add(tela, BorderLayout.CENTER);
        conteudo.revalidate();
        conteudo.repaint();
    }

    public void selecionar(String id) {
        botoes.forEach((chave, botao) ->
                botao.setBackground(chave.equals(id) ? SELECIONADO : AZUL));
    }

    // Para JFrame Form do NetBeans: chamar DEPOIS de initComponents().
    // Preserva o conteúdo que o aluno já desenhou no formulário.
    public static MenuPadrao instalar(JFrame janela, Usuario usuario,
            String opcaoAtual, Consumer<String> aoNavegar, Runnable aoSair) {
        if (janela.getContentPane() instanceof MenuPadrao) {
            throw new IllegalStateException("O menu já foi instalado nesta janela.");
        }
        Container telaOriginal = janela.getContentPane();
        MenuPadrao menu = new MenuPadrao(usuario, aoNavegar, aoSair);
        menu.mostrarTela(telaOriginal);
        menu.selecionar(opcaoAtual);
        janela.setContentPane(menu);
        janela.setMinimumSize(new Dimension(1000, 600));
        janela.setSize(1250, 820);
        janela.setLocationRelativeTo(null);
        janela.revalidate();
        janela.repaint();
        return menu;
    }
}
