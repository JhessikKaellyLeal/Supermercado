package View;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;

/**
 * Módulo Fornecedores e Terceirizados — interface Swing em um único arquivo.
 * Os dados exibidos são demonstrativos; não há conexão com banco de dados.
 */
public class Fornecedores extends JFrame {
    private final JTabbedPane abas = new JTabbedPane();
    private static final Color FUNDO = new Color(246, 248, 251);
    private static final Color BRANCO = Color.WHITE;
    private static final Color TEXTO = new Color(35, 45, 60);
    private static final Color SECUNDARIO = new Color(105, 116, 132);
    private static final Color BORDA = new Color(224, 229, 236);
    private static final Color AZUL = new Color(35, 92, 175);
    private static final Font FONTE = new Font("Segoe UI", Font.PLAIN, 13);
    private static final Font FONTE_TITULO = new Font("Segoe UI", Font.BOLD, 21);

    public Fornecedores() {
        setTitle("Fornecedores e terceirizados");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setMinimumSize(new Dimension(1100, 720));
        getContentPane().setBackground(FUNDO);
        setLayout(new BorderLayout());
        abas.setFont(new Font("Segoe UI", Font.BOLD, 13));
        abas.setBackground(BRANCO);
        abas.setForeground(TEXTO);
        abas.addTab("Contatos", criarContatos());
        abas.addTab("Contratos", criarContratos());
        abas.addTab("Documentos", criarDocumentos());
        abas.addTab("Avaliações", criarAvaliacoes());
        abas.addTab("Cond. de pagamento", criarCondicoes());
        abas.addTab("Ocorrências", criarOcorrencias());
        abas.addTab("Histórico de compras", criarHistorico());
        add(abas, BorderLayout.CENTER);
        setSize(1920, 1080);
        setLocationRelativeTo(null);
    }

    private JPanel pagina(String titulo, String subtitulo) {
        JPanel raiz = new JPanel(new BorderLayout(18, 16));
        raiz.setBackground(FUNDO);
        raiz.setBorder(new EmptyBorder(22, 26, 18, 26));
        JPanel cabecalho = new JPanel(new BorderLayout(4, 5));
        cabecalho.setOpaque(false);
        JLabel lbTitulo = new JLabel(titulo);
        lbTitulo.setFont(FONTE_TITULO); lbTitulo.setForeground(TEXTO);
        JLabel lbSubtitulo = new JLabel(subtitulo);
        lbSubtitulo.setFont(new Font("Segoe UI", Font.PLAIN, 13)); lbSubtitulo.setForeground(SECUNDARIO);
        cabecalho.add(lbTitulo, BorderLayout.NORTH); cabecalho.add(lbSubtitulo, BorderLayout.CENTER);
        raiz.add(cabecalho, BorderLayout.NORTH);
        return raiz;
    }

    private JPanel cartao(String titulo) {
        JPanel p = new JPanel(new BorderLayout(10, 12));
        p.setBackground(BRANCO);
        p.setBorder(new CompoundBorder(new LineBorder(BORDA, 1, true), new EmptyBorder(16, 17, 16, 17)));
        if (titulo != null && !titulo.isBlank()) {
            JLabel lb = new JLabel(titulo);
            lb.setFont(new Font("Segoe UI", Font.BOLD, 15)); lb.setForeground(TEXTO);
            p.add(lb, BorderLayout.NORTH);
        }
        return p;
    }

    private JPanel formulario(int colunas) {
        JPanel p = new JPanel(new GridLayout(0, colunas, 14, 12));
        p.setOpaque(false);
        return p;
    }

    private JPanel campo(String rotulo, JComponent entrada) {
        JPanel p = new JPanel(new BorderLayout(4, 6)); p.setOpaque(false);
        JLabel lb = new JLabel(rotulo); lb.setFont(FONTE); lb.setForeground(new Color(70, 80, 95));
        entrada.setFont(FONTE); entrada.setPreferredSize(new Dimension(160, 34));
        if (entrada instanceof JTextField || entrada instanceof JComboBox || entrada instanceof JFormattedTextField) {
            entrada.setBackground(Color.WHITE);
            entrada.setBorder(new CompoundBorder(new LineBorder(new Color(208, 216, 227), 1, true), new EmptyBorder(4, 8, 4, 8)));
        }
        p.add(lb, BorderLayout.NORTH); p.add(entrada, BorderLayout.CENTER); return p;
    }

    private JTextField txt(String placeholder) { JTextField t = new JTextField(); t.setToolTipText(placeholder); return t; }
    private JComboBox<String> combo(String... itens) { return new JComboBox<>(itens); }
    private JButton botao(String texto, boolean principal) {
        JButton b = new JButton(texto); b.setFont(new Font("Segoe UI", Font.BOLD, 12));
        b.setFocusPainted(false); b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        b.setBorder(new EmptyBorder(9, 15, 9, 15));
        if (principal) { b.setBackground(AZUL); b.setForeground(Color.WHITE); b.setOpaque(true); b.setBorderPainted(false); }
        else { b.setBackground(Color.WHITE); b.setForeground(TEXTO); b.setBorder(new CompoundBorder(new LineBorder(BORDA, 1, true), new EmptyBorder(8, 14, 8, 14))); }
        return b;
    }
    private JPanel acoes(String... nomes) {
        JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0)); p.setOpaque(false);
        for (String n : nomes) p.add(botao(n, n.equalsIgnoreCase("Salvar") || n.equalsIgnoreCase("Pesquisar") || n.equalsIgnoreCase("Registrar ocorrência")));
        return p;
    }
    private JTable tabela(String[] colunas, Object[][] dados) {
        DefaultTableModel modelo = new DefaultTableModel(dados, colunas) { @Override public boolean isCellEditable(int r, int c) { return false; } };
        JTable t = new JTable(modelo); t.setFont(FONTE); t.setRowHeight(32); t.setForeground(TEXTO); t.setBackground(Color.WHITE);
        t.setGridColor(new Color(237, 240, 244)); t.setShowVerticalLines(false); t.setSelectionBackground(new Color(226, 238, 253));
        t.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12)); t.getTableHeader().setForeground(new Color(70, 80, 95));
        t.getTableHeader().setBackground(new Color(247, 249, 252)); t.getTableHeader().setPreferredSize(new Dimension(10, 36));
        return t;
    }
    private JPanel resumo(String titulo, String[][] linhas) {
        JPanel p = cartao(titulo); JPanel conteudo = new JPanel(); conteudo.setOpaque(false); conteudo.setLayout(new BoxLayout(conteudo, BoxLayout.Y_AXIS));
        for (String[] linha : linhas) {
            JPanel r = new JPanel(new BorderLayout(8, 3)); r.setOpaque(false); r.setBorder(new EmptyBorder(8, 0, 8, 0));
            JLabel a = new JLabel(linha[0]); a.setForeground(SECUNDARIO); a.setFont(FONTE);
            JLabel b = new JLabel(linha[1]); b.setForeground(TEXTO); b.setFont(new Font("Segoe UI", Font.BOLD, 12));
            r.add(a, BorderLayout.NORTH); r.add(b, BorderLayout.SOUTH); conteudo.add(r); conteudo.add(new JSeparator());
        }
        p.add(conteudo, BorderLayout.CENTER); p.setPreferredSize(new Dimension(265, 400)); return p;
    }
    private JPanel metricas(String[][] itens) {
        JPanel p = new JPanel(new GridLayout(1, itens.length, 12, 0)); p.setOpaque(false);
        for (String[] item : itens) {
            JPanel c = cartao(null); JPanel texto = new JPanel(); texto.setOpaque(false); texto.setLayout(new BoxLayout(texto, BoxLayout.Y_AXIS));
            JLabel l = new JLabel(item[0]); l.setForeground(SECUNDARIO); l.setFont(FONTE);
            JLabel v = new JLabel(item[1]); v.setForeground(TEXTO); v.setFont(new Font("Segoe UI", Font.BOLD, 22));
            JLabel d = new JLabel(item[2]); d.setForeground(new Color(40, 135, 92)); d.setFont(new Font("Segoe UI", Font.PLAIN, 11));
            texto.add(l); texto.add(Box.createVerticalStrut(7)); texto.add(v); texto.add(Box.createVerticalStrut(5)); texto.add(d); c.add(texto, BorderLayout.CENTER); p.add(c);
        }
        return p;
    }
    private JScrollPane scroll(JTable t) { JScrollPane s = new JScrollPane(t); s.setBorder(new LineBorder(BORDA, 1, true)); s.getViewport().setBackground(Color.WHITE); return s; }

    private JPanel criarContatos() {
        JPanel raiz = pagina("Cadastro de contatos", "Cadastre e consulte as pessoas responsáveis pelo relacionamento com fornecedores e terceirizados.");
        JPanel centro = new JPanel(new BorderLayout(16, 14)); centro.setOpaque(false);
        JPanel coluna = new JPanel(new BorderLayout(0, 14)); coluna.setOpaque(false);
        JPanel dados = cartao("Dados do contato"); JPanel f = formulario(4);
        f.add(campo("Fornecedor / terceirizado *", txt("Selecione o fornecedor")));
        f.add(campo("Tipo de contato *", combo("Comercial", "Financeiro", "Logística", "Suporte", "Outro")));
        f.add(campo("Nome do contato *", txt("Nome completo")));
        f.add(campo("Cargo", txt("Cargo do contato")));
        f.add(campo("Preferência de comunicação", combo("E-mail", "Telefone", "WhatsApp")));
        f.add(campo("Contato principal", combo("Sim", "Não")));
        dados.add(f, BorderLayout.CENTER);
        JPanel canais = cartao("Telefones e e-mails"); JPanel cf = formulario(4);
        cf.add(campo("Tipo de telefone *", combo("Comercial", "Celular", "Residencial", "WhatsApp")));
        cf.add(campo("DDD *", txt("Ex.: 11"))); cf.add(campo("Número *", txt("Número do telefone"))); cf.add(campo("Ramal", txt("Opcional")));
        cf.add(campo("Tipo de e-mail", combo("Comercial", "Financeiro", "Pessoal", "Outro"))); cf.add(campo("E-mail *", txt("nome@empresa.com")));
        JTextField obs = txt("Informações adicionais sobre o contato"); cf.add(campo("Observações", obs));
        canais.add(cf, BorderLayout.CENTER); canais.add(acoes("Adicionar telefone", "Adicionar e-mail"), BorderLayout.SOUTH);
        coluna.add(dados, BorderLayout.NORTH); coluna.add(canais, BorderLayout.CENTER);
        JPanel listagem = cartao("Contatos vinculados a fornecedores e terceirizados");
        JPanel filtros = formulario(4); filtros.add(campo("Fornecedor", txt("Todos os fornecedores"))); filtros.add(campo("Nome do contato", txt("Pesquisar nome"))); filtros.add(campo("Tipo", combo("Todos", "Comercial", "Financeiro", "Logística"))); filtros.add(campo("Situação", combo("Todas", "Ativo", "Inativo")));
        listagem.add(filtros, BorderLayout.NORTH);
        listagem.add(scroll(tabela(new String[]{"Código", "Fornecedor / terceirizado", "Nome do contato", "Tipo", "Cargo", "Principal", "Telefone", "E-mail", "Ações"}, new Object[][]{
            {"CT-0001", "Alimentos Boa Safra", "Mariana Costa", "Comercial", "Vendas", "Sim", "(11) 3333-2200", "mariana@boasafra.com", "Editar · Excluir"},
            {"CT-0002", "Distribuidora Central", "Paulo Mendes", "Financeiro", "Analista", "Não", "(11) 98888-1020", "paulo@central.com", "Editar · Excluir"},
            {"CT-0003", "Laticínios Serra Azul", "Renata Lima", "Logística", "Coordenadora", "Sim", "(19) 3222-1400", "renata@serraazul.com", "Editar · Excluir"}
        })), BorderLayout.CENTER);
        listagem.add(acoes("Novo", "Salvar", "Editar", "Excluir", "Limpar", "Pesquisar", "Voltar"), BorderLayout.SOUTH);
        centro.add(coluna, BorderLayout.CENTER); centro.add(resumo("Resumo do contato selecionado", new String[][]{{"Código", "CT-0001"},{"Fornecedor", "Alimentos Boa Safra"},{"Nome", "Mariana Costa"},{"Cargo", "Vendas"},{"Contato principal", "Sim"},{"Preferência", "E-mail"},{"Telefone", "(11) 3333-2200"},{"E-mail", "mariana@boasafra.com"}}), BorderLayout.EAST);
        JPanel empilhado = new JPanel(new BorderLayout(0, 14)); empilhado.setOpaque(false); empilhado.add(centro, BorderLayout.NORTH); empilhado.add(listagem, BorderLayout.CENTER);
        raiz.add(empilhado, BorderLayout.CENTER); return raiz;
    }

    private JPanel criarContratos() {
        JPanel raiz = pagina("Cadastro de contratos", "Gerencie vigência, condições comerciais, responsáveis e documentos associados a cada contrato.");
        JPanel centro = new JPanel(new BorderLayout(16, 14)); centro.setOpaque(false); JPanel esquerda = new JPanel(new BorderLayout(0, 14)); esquerda.setOpaque(false);
        JPanel form = cartao("Dados do contrato"); JPanel f = formulario(4);
        f.add(campo("Fornecedor / terceirizado *", txt("Buscar fornecedor"))); f.add(campo("Número do contrato *", txt("Ex.: CTR-2026-001")));
        f.add(campo("Tipo de contrato", combo("Fornecimento", "Prestação de serviços", "Distribuição", "Parceria", "Outro"))); f.add(campo("Situação", combo("Em análise", "Ativo", "Suspenso", "Encerrado", "Vencido")));
        f.add(campo("Data de início", txt("dd/mm/aaaa"))); f.add(campo("Data de término", txt("dd/mm/aaaa")));
        f.add(campo("Valor contratado (R$)", txt("0,00"))); f.add(campo("Responsável interno", txt("Nome do responsável")));
        f.add(campo("Renovação automática", combo("Não", "Sim"))); f.add(campo("Aviso de vencimento", combo("30 dias", "60 dias", "90 dias", "Não se aplica")));
        form.add(f, BorderLayout.CENTER); form.add(acoes("Novo", "Salvar", "Anexar documento", "Limpar"), BorderLayout.SOUTH);
        JPanel lista = cartao("Contratos cadastrados"); JPanel filtros = formulario(4); filtros.add(campo("Fornecedor", txt("Pesquisar fornecedor"))); filtros.add(campo("Número", txt("Número do contrato"))); filtros.add(campo("Situação", combo("Todas", "Ativo", "Em análise", "Vencido"))); filtros.add(campo("Vigência", combo("Todas", "Vigentes", "A vencer", "Encerrados")));
        lista.add(filtros, BorderLayout.NORTH); lista.add(scroll(tabela(new String[]{"Nº contrato", "Fornecedor", "Tipo", "Início", "Término", "Valor", "Situação", "Responsável", "Ações"}, new Object[][]{
            {"CTR-2026-001", "Alimentos Boa Safra", "Fornecimento", "01/01/2026", "31/12/2026", "R$ 85.000,00", "Ativo", "Ana Souza", "Ver · Editar"},
            {"CTR-2026-002", "Distribuidora Central", "Distribuição", "15/02/2026", "15/02/2027", "R$ 42.500,00", "Ativo", "Carlos Lima", "Ver · Editar"},
            {"CTR-2025-018", "Laticínios Serra Azul", "Fornecimento", "01/03/2025", "28/02/2026", "R$ 31.200,00", "A vencer", "Ana Souza", "Ver · Editar"}
        })), BorderLayout.CENTER); lista.add(new JLabel("Exibindo 1 a 3 registros"), BorderLayout.SOUTH);
        esquerda.add(form, BorderLayout.NORTH); esquerda.add(lista, BorderLayout.CENTER); centro.add(esquerda, BorderLayout.CENTER);
        centro.add(resumo("Resumo do contrato", new String[][]{{"Contrato", "CTR-2026-001"},{"Fornecedor", "Alimentos Boa Safra"},{"Situação", "Ativo"},{"Vigência", "01/01/2026 a 31/12/2026"},{"Valor", "R$ 85.000,00"},{"Documentos", "3 anexos"},{"Última atualização", "15/05/2026"},{"Ações rápidas", "Visualizar · Renovar"}}), BorderLayout.EAST);
        raiz.add(centro, BorderLayout.CENTER); return raiz;
    }

    private JPanel criarDocumentos() {
        JPanel raiz = pagina("Cadastro e controle de documentos", "Organize documentos legais, fiscais e contratuais e acompanhe seus vencimentos.");
        JPanel centro = new JPanel(new BorderLayout(16, 14)); centro.setOpaque(false); JPanel esquerda = new JPanel(new BorderLayout(0, 14)); esquerda.setOpaque(false);
        JPanel form = cartao("Dados do documento"); JPanel f = formulario(4);
        f.add(campo("Fornecedor / terceirizado *", txt("Buscar fornecedor"))); f.add(campo("Tipo de documento *", combo("CNPJ", "Inscrição estadual", "Certidão", "Contrato", "Licença", "Alvará", "Outro")));
        f.add(campo("Número / identificação", txt("Número do documento"))); f.add(campo("Órgão emissor", txt("Órgão emissor")));
        f.add(campo("Data de emissão", txt("dd/mm/aaaa"))); f.add(campo("Data de validade", txt("dd/mm/aaaa")));
        f.add(campo("Situação", combo("Pendente", "Válido", "A vencer", "Vencido", "Em análise"))); f.add(campo("Responsável", txt("Responsável pelo cadastro")));
        form.add(f, BorderLayout.CENTER); JPanel upload = new JPanel(new BorderLayout(12, 4)); upload.setBackground(new Color(249, 251, 254)); upload.setBorder(new CompoundBorder(new LineBorder(BORDA, 1, true), new EmptyBorder(16, 16, 16, 16)));
        JLabel lbUpload = new JLabel("⇧  Anexar arquivo do documento (PDF, JPG ou PNG)"); lbUpload.setForeground(SECUNDARIO); upload.add(lbUpload, BorderLayout.CENTER); upload.add(botao("Selecionar arquivo", false), BorderLayout.EAST); form.add(upload, BorderLayout.SOUTH);
        JPanel lista = cartao("Documentos cadastrados"); JPanel filtros = formulario(4); filtros.add(campo("Fornecedor", txt("Todos"))); filtros.add(campo("Tipo", combo("Todos", "CNPJ", "Certidão", "Contrato", "Licença"))); filtros.add(campo("Validade", combo("Todas", "Válidos", "A vencer", "Vencidos"))); filtros.add(campo("Situação", combo("Todas", "Pendente", "Válido", "Vencido")));
        lista.add(filtros, BorderLayout.NORTH); lista.add(scroll(tabela(new String[]{"Código", "Fornecedor", "Documento", "Identificação", "Emissão", "Validade", "Situação", "Arquivo", "Ações"}, new Object[][]{
            {"DOC-001", "Alimentos Boa Safra", "CNPJ", "12.345.678/0001-90", "10/01/2026", "10/01/2027", "Válido", "cnpj.pdf", "Ver · Editar"},
            {"DOC-002", "Distribuidora Central", "Certidão negativa", "CERT-2026-88", "01/06/2026", "01/12/2026", "A vencer", "certidao.pdf", "Ver · Editar"},
            {"DOC-003", "Laticínios Serra Azul", "Licença sanitária", "VISA-2230", "15/01/2025", "15/01/2026", "Vencido", "licenca.pdf", "Ver · Editar"}
        })), BorderLayout.CENTER); lista.add(acoes("Novo", "Salvar", "Editar", "Excluir", "Pesquisar", "Limpar"), BorderLayout.SOUTH);
        esquerda.add(form, BorderLayout.NORTH); esquerda.add(lista, BorderLayout.CENTER); centro.add(esquerda, BorderLayout.CENTER);
        centro.add(resumo("Resumo de documentos", new String[][]{{"Total de documentos", "18"},{"Válidos", "12"},{"A vencer em 30 dias", "3"},{"Vencidos", "2"},{"Pendentes", "1"},{"Próximo vencimento", "Certidão — 01/12/2026"},{"Fornecedor", "Distribuidora Central"}}), BorderLayout.EAST);
        raiz.add(centro, BorderLayout.CENTER); return raiz;
    }

    private JPanel criarAvaliacoes() {
        JPanel raiz = pagina("Avaliação de fornecedores", "Registre o desempenho dos fornecedores com base em critérios operacionais e comerciais.");
        JPanel centro = new JPanel(new BorderLayout(16, 14)); centro.setOpaque(false); JPanel esquerda = new JPanel(new BorderLayout(0, 14)); esquerda.setOpaque(false);
        JPanel form = cartao("Cadastro e avaliação"); JPanel f = formulario(3);
        f.add(campo("Fornecedor / terceirizado *", txt("Buscar fornecedor"))); f.add(campo("Contrato relacionado", combo("Selecione o contrato", "CTR-2026-001", "CTR-2026-002"))); f.add(campo("Data da avaliação *", txt(LocalDate.now().toString())));
        f.add(campo("Qualidade dos produtos/serviços (1–5)", combo("5 - Excelente", "4 - Bom", "3 - Regular", "2 - Ruim", "1 - Muito ruim")));
        f.add(campo("Pontualidade (1–5)", combo("5 - Excelente", "4 - Bom", "3 - Regular", "2 - Ruim", "1 - Muito ruim")));
        f.add(campo("Atendimento (1–5)", combo("5 - Excelente", "4 - Bom", "3 - Regular", "2 - Ruim", "1 - Muito ruim")));
        f.add(campo("Preço / custo-benefício (1–5)", combo("5 - Excelente", "4 - Bom", "3 - Regular", "2 - Ruim", "1 - Muito ruim")));
        f.add(campo("Conformidade (1–5)", combo("5 - Excelente", "4 - Bom", "3 - Regular", "2 - Ruim", "1 - Muito ruim")));
        f.add(campo("Resultado geral", combo("Excelente", "Bom", "Regular", "Insatisfatório")));
        form.add(f, BorderLayout.CENTER); form.add(campo("Comentários e plano de ação", new JTextArea(3, 30)), BorderLayout.SOUTH); form.add(acoes("Salvar avaliação", "Limpar"), BorderLayout.PAGE_END);
        JPanel lista = cartao("Histórico de avaliações"); lista.add(scroll(tabela(new String[]{"Data", "Fornecedor", "Contrato", "Qualidade", "Pontualidade", "Atendimento", "Nota geral", "Avaliador", "Ações"}, new Object[][]{
            {"20/05/2026", "Alimentos Boa Safra", "CTR-2026-001", "5", "4", "5", "4,7 / 5", "Ana Souza", "Visualizar"},
            {"12/04/2026", "Distribuidora Central", "CTR-2026-002", "4", "4", "3", "3,8 / 5", "Carlos Lima", "Visualizar"},
            {"28/03/2026", "Laticínios Serra Azul", "CTR-2025-018", "3", "2", "4", "3,1 / 5", "Ana Souza", "Visualizar"}
        })), BorderLayout.CENTER);
        esquerda.add(form, BorderLayout.NORTH); esquerda.add(lista, BorderLayout.CENTER); centro.add(esquerda, BorderLayout.CENTER);
        centro.add(resumo("Resumo da avaliação", new String[][]{{"Fornecedor", "Alimentos Boa Safra"},{"Nota média", "4,7 / 5"},{"Resultado", "Excelente"},{"Avaliações realizadas", "8"},{"Última avaliação", "20/05/2026"},{"Pontos fortes", "Qualidade e atendimento"},{"Ponto de atenção", "Pontualidade"}}), BorderLayout.EAST);
        raiz.add(centro, BorderLayout.CENTER); return raiz;
    }

    private JPanel criarCondicoes() {
        JPanel raiz = pagina("Condições de pagamento", "Defina prazos, formas de pagamento, parcelamento e condições comerciais padrão por fornecedor.");
        JPanel centro = new JPanel(new BorderLayout(16, 14)); centro.setOpaque(false); JPanel esquerda = new JPanel(new BorderLayout(0, 14)); esquerda.setOpaque(false);
        JPanel form = cartao("Cadastro de condição de pagamento"); JPanel f = formulario(4);
        f.add(campo("Código", txt("Gerado automaticamente"))); f.add(campo("Descrição *", txt("Ex.: 30 dias"))); f.add(campo("Fornecedor", txt("Opcional — todos se vazio"))); f.add(campo("Forma de pagamento *", combo("Boleto", "PIX", "Transferência", "Cartão", "Dinheiro", "Outro")));
        f.add(campo("Condição padrão", combo("Sim", "Não"))); f.add(campo("Número de parcelas", combo("À vista", "2", "3", "4", "5", "6", "12")));
        f.add(campo("Prazo inicial (dias)", txt("0"))); f.add(campo("Intervalo entre parcelas (dias)", txt("30")));
        f.add(campo("Desconto (%)", txt("0,00"))); f.add(campo("Juros mensal (%)", txt("0,00"))); f.add(campo("Multa por atraso (%)", txt("0,00"))); f.add(campo("Situação", combo("Ativa", "Inativa")));
        form.add(f, BorderLayout.CENTER); form.add(acoes("Nova", "Salvar", "Editar", "Excluir", "Limpar"), BorderLayout.SOUTH);
        JPanel lista = cartao("Condições cadastradas"); JPanel filtros = formulario(4); filtros.add(campo("Descrição", txt("Pesquisar"))); filtros.add(campo("Forma de pagamento", combo("Todas", "Boleto", "PIX", "Transferência"))); filtros.add(campo("Situação", combo("Todas", "Ativa", "Inativa"))); filtros.add(campo("Padrão", combo("Todas", "Sim", "Não")));
        lista.add(filtros, BorderLayout.NORTH); lista.add(scroll(tabela(new String[]{"Código", "Descrição", "Forma de pagamento", "Parcelas", "Condição padrão", "Situação", "Criado em", "Criado por", "Ações"}, new Object[][]{
            {"CP-001", "À vista", "PIX", "1", "Sim", "Ativa", "10/01/2026", "Administrador", "Editar · Excluir"},
            {"CP-002", "30 dias", "Boleto", "1", "Não", "Ativa", "12/01/2026", "Administrador", "Editar · Excluir"},
            {"CP-003", "30/60 dias", "Boleto", "2", "Não", "Ativa", "20/02/2026", "Ana Souza", "Editar · Excluir"}
        })), BorderLayout.CENTER);
        esquerda.add(form, BorderLayout.NORTH); esquerda.add(lista, BorderLayout.CENTER); centro.add(esquerda, BorderLayout.CENTER);
        centro.add(resumo("Resumo das condições", new String[][]{{"Condições cadastradas", "12"},{"Ativas", "10"},{"Inativas", "2"},{"Condição padrão", "À vista / PIX"},{"Formas disponíveis", "6"},{"Última atualização", "20/02/2026"}}), BorderLayout.EAST);
        raiz.add(centro, BorderLayout.CENTER); return raiz;
    }

    private JPanel criarOcorrencias() {
        JPanel raiz = pagina("Registro e acompanhamento de ocorrências", "Acompanhe problemas, solicitações e ações corretivas relacionados aos fornecedores.");
        JPanel centro = new JPanel(new BorderLayout(16, 14)); centro.setOpaque(false); JPanel esquerda = new JPanel(new BorderLayout(0, 14)); esquerda.setOpaque(false);
        JPanel form = cartao("Dados da ocorrência"); JPanel f = formulario(4);
        f.add(campo("Fornecedor / terceirizado *", txt("Buscar fornecedor"))); f.add(campo("Tipo de ocorrência *", combo("Atraso na entrega", "Qualidade", "Divergência de cobrança", "Documentação", "Atendimento", "Outro")));
        f.add(campo("Data da ocorrência *", txt(LocalDate.now().toString()))); f.add(campo("Prioridade", combo("Baixa", "Média", "Alta", "Crítica")));
        f.add(campo("Contrato / pedido relacionado", txt("Opcional"))); f.add(campo("Responsável", txt("Responsável interno")));
        f.add(campo("Situação", combo("Aberta", "Em andamento", "Aguardando fornecedor", "Resolvida", "Cancelada"))); f.add(campo("Prazo para resolução", txt("dd/mm/aaaa")));
        form.add(f, BorderLayout.CENTER); form.add(campo("Descrição detalhada", new JTextArea(3, 30)), BorderLayout.SOUTH); form.add(acoes("Registrar ocorrência", "Atualizar", "Limpar"), BorderLayout.PAGE_END);
        JPanel lista = cartao("Ocorrências registradas"); JPanel filtros = formulario(4); filtros.add(campo("Fornecedor", txt("Todos"))); filtros.add(campo("Tipo", combo("Todos", "Atraso", "Qualidade", "Cobrança"))); filtros.add(campo("Situação", combo("Todas", "Aberta", "Em andamento", "Resolvida"))); filtros.add(campo("Prioridade", combo("Todas", "Baixa", "Média", "Alta", "Crítica")));
        lista.add(filtros, BorderLayout.NORTH); lista.add(scroll(tabela(new String[]{"Código", "Data", "Fornecedor", "Tipo", "Descrição", "Prioridade", "Responsável", "Situação", "Ações"}, new Object[][]{
            {"OC-001", "15/06/2026", "Distribuidora Central", "Atraso na entrega", "Pedido entregue fora do prazo", "Alta", "Carlos Lima", "Em andamento", "Ver · Editar"},
            {"OC-002", "11/06/2026", "Alimentos Boa Safra", "Qualidade", "Produto fora do padrão", "Média", "Ana Souza", "Aguardando fornecedor", "Ver · Editar"},
            {"OC-003", "02/06/2026", "Laticínios Serra Azul", "Cobrança", "Divergência na nota fiscal", "Baixa", "Ana Souza", "Resolvida", "Ver · Editar"}
        })), BorderLayout.CENTER);
        esquerda.add(form, BorderLayout.NORTH); esquerda.add(lista, BorderLayout.CENTER); centro.add(esquerda, BorderLayout.CENTER);
        centro.add(resumo("Resumo de ocorrências", new String[][]{{"Total registradas", "18"},{"Abertas", "5"},{"Em andamento", "2"},{"Aguardando fornecedor", "4"},{"Resolvidas", "7"},{"Alta prioridade", "3"},{"Última ocorrência", "15/06/2026"}}), BorderLayout.EAST);
        raiz.add(centro, BorderLayout.CENTER); return raiz;
    }

    private JPanel criarHistorico() {
        JPanel raiz = pagina("Histórico de compras", "Consulte compras, valores, quantidades e desempenho comercial por fornecedor.");
        JPanel centro = new JPanel(new BorderLayout(16, 14)); centro.setOpaque(false); JPanel esquerda = new JPanel(new BorderLayout(0, 14)); esquerda.setOpaque(false);
        JPanel filtros = cartao("Filtros de consulta"); JPanel f = formulario(4);
        f.add(campo("Fornecedor", txt("Todos os fornecedores"))); f.add(campo("Período inicial", txt("dd/mm/aaaa"))); f.add(campo("Período final", txt("dd/mm/aaaa"))); f.add(campo("Status da compra", combo("Todos", "Concluída", "Pendente", "Cancelada")));
        filtros.add(f, BorderLayout.CENTER); filtros.add(acoes("Pesquisar", "Limpar", "Exportar"), BorderLayout.SOUTH);
        JPanel indicadores = metricas(new String[][]{{"Pedidos no período", "37", "+8% em relação ao anterior"},{"Itens comprados", "1.265", "Unidades registradas"},{"Valor total", "R$ 85.742,60", "Total das compras"},{"Ticket médio", "R$ 2.317,37", "Por pedido"}});
        JPanel lista = cartao("Registro de compras"); lista.add(scroll(tabela(new String[]{"Data da compra", "Fornecedor", "Pedido", "Quantidade", "Valor total", "Pedido por", "Nota fiscal", "Status", "Ações"}, new Object[][]{
            {"20/06/2026", "Alimentos Boa Safra", "PC-2026-037", "120 un.", "R$ 4.250,00", "Ana Souza", "NF-88102", "Concluída", "Ver · Imprimir"},
            {"18/06/2026", "Distribuidora Central", "PC-2026-036", "85 un.", "R$ 2.780,50", "Carlos Lima", "NF-88074", "Concluída", "Ver · Imprimir"},
            {"16/06/2026", "Laticínios Serra Azul", "PC-2026-035", "60 un.", "R$ 1.630,00", "Ana Souza", "NF-88021", "Pendente", "Ver · Imprimir"},
            {"12/06/2026", "Alimentos Boa Safra", "PC-2026-034", "150 un.", "R$ 5.120,00", "Carlos Lima", "NF-87980", "Concluída", "Ver · Imprimir"}
        })), BorderLayout.CENTER); lista.add(new JLabel("Exibindo 1 a 4 de 37 registros"), BorderLayout.SOUTH);
        esquerda.add(filtros, BorderLayout.NORTH); esquerda.add(indicadores, BorderLayout.CENTER); esquerda.add(lista, BorderLayout.SOUTH); centro.add(esquerda, BorderLayout.CENTER);
        centro.add(resumo("Resumo do fornecedor", new String[][]{{"Fornecedor", "Alimentos Boa Safra"},{"Compras no período", "12"},{"Valor total", "R$ 28.420,00"},{"Última compra", "20/06/2026"},{"Produto mais comprado", "Arroz tipo 1"},{"Prazo médio de entrega", "3 dias"},{"Ações rápidas", "Ver pedidos · Exportar"}}), BorderLayout.EAST);
        raiz.add(centro, BorderLayout.CENTER); return raiz;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); } catch (Exception ignored) { }
            new Fornecedores().setVisible(true);
        });
    }
}
