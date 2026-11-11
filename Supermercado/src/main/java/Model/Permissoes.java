
package Model;

public class Permissoes {

    private int idpermissoes;
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

    public Permissoes() {
    }

    public Permissoes(int idpermissoes, String tipo,
            int telaProdutos, int telaEstoque, int telaCompras,
            int telaClientes, int telaPromocoes, int telaCaixa,
            int telaVendas, int telaPDV, int telaPedidos,
            int telaEntregas, int telaRh, int telaFinaceiro,
            int telaRelatorios) {

        this.idpermissoes = idpermissoes;
        this.tipo = tipo;
        this.telaProdutos = telaProdutos;
        this.telaEstoque = telaEstoque;
        this.telaCompras = telaCompras;
        this.telaClientes = telaClientes;
        this.telaPromocoes = telaPromocoes;
        this.telaCaixa = telaCaixa;
        this.telaVendas = telaVendas;
        this.telaPDV = telaPDV;
        this.telaPedidos = telaPedidos;
        this.telaEntregas = telaEntregas;
        this.telaRh = telaRh;
        this.telaFinaceiro = telaFinaceiro;
        this.telaRelatorios = telaRelatorios;
    }

    public int getIdpermissoes() {
        return idpermissoes;
    }

    public void setIdpermissoes(int idpermissoes) {
        this.idpermissoes = idpermissoes;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getTelaProdutos() {
        return telaProdutos;
    }

    public void setTelaProdutos(int telaProdutos) {
        this.telaProdutos = telaProdutos;
    }

    public int getTelaEstoque() {
        return telaEstoque;
    }

    public void setTelaEstoque(int telaEstoque) {
        this.telaEstoque = telaEstoque;
    }

    public int getTelaCompras() {
        return telaCompras;
    }

    public void setTelaCompras(int telaCompras) {
        this.telaCompras = telaCompras;
    }

    public int getTelaClientes() {
        return telaClientes;
    }

    public void setTelaClientes(int telaClientes) {
        this.telaClientes = telaClientes;
    }

    public int getTelaPromocoes() {
        return telaPromocoes;
    }

    public void setTelaPromocoes(int telaPromocoes) {
        this.telaPromocoes = telaPromocoes;
    }

    public int getTelaCaixa() {
        return telaCaixa;
    }

    public void setTelaCaixa(int telaCaixa) {
        this.telaCaixa = telaCaixa;
    }

    public int getTelaVendas() {
        return telaVendas;
    }

    public void setTelaVendas(int telaVendas) {
        this.telaVendas = telaVendas;
    }

    public int getTelaPDV() {
        return telaPDV;
    }

    public void setTelaPDV(int telaPDV) {
        this.telaPDV = telaPDV;
    }

    public int getTelaPedidos() {
        return telaPedidos;
    }

    public void setTelaPedidos(int telaPedidos) {
        this.telaPedidos = telaPedidos;
    }

    public int getTelaEntregas() {
        return telaEntregas;
    }

    public void setTelaEntregas(int telaEntregas) {
        this.telaEntregas = telaEntregas;
    }

    public int getTelaRh() {
        return telaRh;
    }

    public void setTelaRh(int telaRh) {
        this.telaRh = telaRh;
    }

    public int getTelaFinaceiro() {
        return telaFinaceiro;
    }

    public void setTelaFinaceiro(int telaFinaceiro) {
        this.telaFinaceiro = telaFinaceiro;
    }

    public int getTelaRelatorios() {
        return telaRelatorios;
    }

    public void setTelaRelatorios(int telaRelatorios) {
        this.telaRelatorios = telaRelatorios;
    }
}
