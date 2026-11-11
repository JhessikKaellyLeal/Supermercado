
package View;

import java.awt.*;
import javax.swing.Icon;

// Ícones vetoriais para o menu do sistema Supermercado.
// Não necessita de imagens externas ou bibliotecas adicionais.
public class IconeMenu implements Icon {

    private final String tipo;
    private final int tamanho;

    public IconeMenu(String tipo, int tamanho) {
        this.tipo = tipo;
        this.tamanho = tamanho;
    }

    @Override
    public int getIconWidth() {
        return tamanho;
    }

    @Override
    public int getIconHeight() {
        return tamanho;
    }

    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {

        Graphics2D d = (Graphics2D) g.create();

        d.translate(x, y);
        d.scale(tamanho / 24.0, tamanho / 24.0);

        d.setRenderingHint(
            RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON
        );

        d.setColor(Color.WHITE);

        d.setStroke(new BasicStroke(
            1.7f,
            BasicStroke.CAP_ROUND,
            BasicStroke.JOIN_ROUND
        ));

        switch (tipo) {

            // INÍCIO
            case "inicio":

                d.drawPolyline(
                    new int[]{2, 12, 22},
                    new int[]{11, 2, 11},
                    3
                );

                d.drawRect(5, 10, 14, 11);
                d.drawRect(10, 15, 4, 6);
                break;

            // PRODUTOS
            case "gerenciamentoProdutos":
            case "produtos":

                d.drawRect(4, 3, 16, 19);
                d.drawRect(8, 2, 8, 4);

                d.drawLine(7, 10, 10, 13);
                d.drawLine(10, 13, 17, 8);

                d.drawLine(7, 17, 17, 17);
                break;

            // PROMOÇÕES
            case "promocoes":

                d.drawPolygon(
                    new int[]{3, 14, 22, 22, 14},
                    new int[]{5, 5, 10, 18, 22},
                    5
                );

                d.drawOval(7, 8, 3, 3);
                d.drawLine(13, 17, 18, 12);
                break;

            // ESTOQUE
            case "estoque":

                d.drawRect(2, 13, 9, 8);
                d.drawRect(13, 13, 9, 8);
                d.drawRect(7, 3, 10, 8);

                d.drawLine(7, 7, 17, 7);
                break;

            // FORNECEDORES
            case "fornecedoresTerceirizados":
            case "fornecedores":

                d.drawOval(3, 3, 7, 7);
                d.drawOval(14, 3, 7, 7);

                d.drawArc(1, 12, 12, 11, 0, 180);
                d.drawArc(12, 12, 12, 11, 0, 180);
                break;

            // CLIENTES
            case "clientes":

                d.drawOval(8, 2, 8, 8);
                d.drawArc(3, 11, 18, 12, 0, 180);

                d.drawLine(3, 21, 21, 21);
                break;

            // PEDIDOS
            case "pedidos":

                d.drawRect(5, 4, 14, 18);
                d.drawRect(9, 2, 6, 4);

                for (int a = 10; a < 20; a += 4) {
                    d.drawLine(8, a, 16, a);
                }
                break;

            // ENTREGAS
            case "entregas":

                d.drawRect(2, 5, 12, 12);
                d.drawRect(14, 10, 8, 7);

                d.drawLine(17, 10, 17, 14);

                d.drawOval(5, 16, 5, 5);
                d.drawOval(16, 16, 5, 5);
                break;

            // PDV
            case "pdvRealizacao":
            case "pdv":

                d.drawLine(2, 4, 6, 4);
                d.drawLine(6, 4, 9, 16);
                d.drawLine(9, 16, 20, 16);

                d.drawLine(8, 9, 21, 9);
                d.drawLine(21, 9, 18, 16);

                d.drawOval(9, 18, 3, 3);
                d.drawOval(17, 18, 3, 3);
                break;

            // VENDAS
            case "vendas":

                d.drawLine(3, 21, 3, 4);
                d.drawLine(3, 21, 22, 21);

                d.drawPolyline(
                    new int[]{5, 9, 13, 18, 22},
                    new int[]{16, 12, 15, 6, 9},
                    5
                );

                break;
                case "solicitacao":

    d.drawRect(5, 2, 14, 20);
    d.drawLine(8, 8, 16, 8);
    d.drawLine(8, 12, 16, 12);
    d.drawLine(8, 16, 14, 16);
    break;

            // CAIXA
            case "caixa":
            case "caixas":

                d.drawRect(3, 13, 18, 8);
                d.drawRect(7, 3, 11, 7);

                d.drawLine(12, 10, 12, 13);
                d.drawLine(7, 17, 17, 17);
                break;

            // FINANCEIRO
            case "financeiro":

                d.drawOval(2, 2, 20, 20);

                d.setFont(new Font(
                    "SansSerif",
                    Font.BOLD,
                    17
                ));

                d.drawString("$", 7, 18);
                break;

            // FUNCIONÁRIOS / RH
            case "funcionariosRH":
            case "rh":

                d.drawOval(8, 2, 8, 8);
                d.drawArc(3, 11, 18, 12, 0, 180);

                d.drawLine(3, 21, 21, 21);
                break;

            // PERFIL DE USUÁRIOS
            case "perfilUsuarios":

                d.drawOval(8, 2, 8, 8);
                d.drawArc(3, 11, 18, 12, 0, 180);
                break;

            // ALTERAR SENHA
            case "alterarSenha":

                d.drawRoundRect(4, 10, 16, 12, 2, 2);
                d.drawArc(7, 2, 10, 15, 0, 180);

                d.drawOval(10, 14, 4, 4);
                break;

            // SAIR
            case "sair":
            case "logout":

                d.drawRect(3, 3, 10, 18);

                d.drawLine(9, 12, 22, 12);

                d.drawPolyline(
                    new int[]{18, 22, 18},
                    new int[]{8, 12, 16},
                    3
                );
                break;

            // RELATÓRIOS
            case "relatorios":

                d.drawLine(3, 2, 3, 22);
                d.drawLine(3, 22, 22, 22);

                d.drawRect(6, 13, 3, 6);
                d.drawRect(12, 8, 3, 11);
                d.drawRect(18, 3, 3, 16);
                break;

            // CONFIGURAÇÕES
            case "configuracoes":

                d.drawOval(6, 6, 12, 12);
                d.drawOval(10, 10, 4, 4);

                for (int a = 0; a < 8; a++) {
                    d.drawLine(12, 2, 12, 5);
                    d.rotate(Math.PI / 4, 12, 12);
                }
                break;

            // ÍCONE PADRÃO
            default:

                d.drawOval(4, 4, 16, 16);
                d.drawLine(12, 8, 12, 13);
                d.fillOval(11, 16, 2, 2);
                break;
        }

        d.dispose();
    }
}
