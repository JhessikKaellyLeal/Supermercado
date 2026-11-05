
package Model;

public class Permissoes {

    private int idPermissoes;
    private String tipo;

    private int telaProdutos;
    private int telaEstoque;
    private int telaCompras;
    private int telaClientes;
    private int telaPromocoes;
    private int telaCaixa;
    private int telaVendas;
    private int telaPDV;
    private int telaPedidos;
    private int telaEntregas;
    private int telaRh;
    private int telaFinaceiro;
    private int telaRelatorios;

    // Relacionamento com Usuario
    private Usuario usuario;

    // Construtor vazio
    public Permissoes() {
    }

    // ID
    public int getIdPermissoes() {
        return idPermissoes;
    }

    public void setIdPermissoes(int idPermissoes) {
        this.idPermissoes = idPermissoes;
    }

    // TIPO
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    // TELA PRODUTOS
    public int getTelaProdutos() {
        return telaProdutos;
    }

    public void setTelaProdutos(int telaProdutos) {
        this.telaProdutos = telaProdutos;
    }

    // TELA ESTOQUE
    public int getTelaEstoque() {
        return telaEstoque;
    }

    public void setTelaEstoque(int telaEstoque) {
        this.telaEstoque = telaEstoque;
    }

    // TELA COMPRAS
    public int getTelaCompras() {
        return telaCompras;
    }

    public void setTelaCompras(int telaCompras) {
        this.telaCompras = telaCompras;
    }

    // TELA CLIENTES
    public int getTelaClientes() {
        return telaClientes;
    }

    public void setTelaClientes(int telaClientes) {
        this.telaClientes = telaClientes;
    }

    // TELA PROMOÇÕES
    public int getTelaPromocoes() {
        return telaPromocoes;
    }

    public void setTelaPromocoes(int telaPromocoes) {
        this.telaPromocoes = telaPromocoes;
    }

    // TELA CAIXA
    public int getTelaCaixa() {
        return telaCaixa;
    }

    public void setTelaCaixa(int telaCaixa) {
        this.telaCaixa = telaCaixa;
    }

    // TELA VENDAS
    public int getTelaVendas() {
        return telaVendas;
    }

    public void setTelaVendas(int telaVendas) {
        this.telaVendas = telaVendas;
    }

    // TELA PDV
    public int getTelaPDV() {
        return telaPDV;
    }

    public void setTelaPDV(int telaPDV) {
        this.telaPDV = telaPDV;
    }

    // TELA PEDIDOS
    public int getTelaPedidos() {
        return telaPedidos;
    }

    public void setTelaPedidos(int telaPedidos) {
        this.telaPedidos = telaPedidos;
    }

    // TELA ENTREGAS
    public int getTelaEntregas() {
        return telaEntregas;
    }

    public void setTelaEntregas(int telaEntregas) {
        this.telaEntregas = telaEntregas;
    }

    // TELA RH
    public int getTelaRh() {
        return telaRh;
    }

    public void setTelaRh(int telaRh) {
        this.telaRh = telaRh;
    }

    // TELA FINANCEIRO
    public int getTelaFinaceiro() {
        return telaFinaceiro;
    }

    public void setTelaFinaceiro(int telaFinaceiro) {
        this.telaFinaceiro = telaFinaceiro;
    }

    // TELA RELATÓRIOS
    public int getTelaRelatorios() {
        return telaRelatorios;
    }

    public void setTelaRelatorios(int telaRelatorios) {
        this.telaRelatorios = telaRelatorios;
    }

    // USUARIO RELACIONADO
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
