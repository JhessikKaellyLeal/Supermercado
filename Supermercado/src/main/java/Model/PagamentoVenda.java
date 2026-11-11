
package Model;

public class PagamentoVenda {

    private int idpagamentoVendas;
    private double valor;
    private double valorRecebido;
    private double troco;
    private String situacao;
    private String referenciaTransacao;
    private int vendas_idvendas;
    private int vendas_cupom_idcupom;
    private int formasPagamento_idformasPagamento;
    private int condicoesPagamento_idcondicoesPagamento;
    private int contasFinanceiras_idcontasFinanceiras;

    public PagamentoVenda() {
    }

    public PagamentoVenda(int idpagamentoVendas, double valor,
            double valorRecebido, double troco, String situacao,
            String referenciaTransacao, int vendas_idvendas,
            int vendas_cupom_idcupom,
            int formasPagamento_idformasPagamento,
            int condicoesPagamento_idcondicoesPagamento,
            int contasFinanceiras_idcontasFinanceiras) {

        this.idpagamentoVendas = idpagamentoVendas;
        this.valor = valor;
        this.valorRecebido = valorRecebido;
        this.troco = troco;
        this.situacao = situacao;
        this.referenciaTransacao = referenciaTransacao;
        this.vendas_idvendas = vendas_idvendas;
        this.vendas_cupom_idcupom = vendas_cupom_idcupom;
        this.formasPagamento_idformasPagamento = formasPagamento_idformasPagamento;
        this.condicoesPagamento_idcondicoesPagamento = condicoesPagamento_idcondicoesPagamento;
        this.contasFinanceiras_idcontasFinanceiras = contasFinanceiras_idcontasFinanceiras;
    }

    public int getIdpagamentoVendas() {
        return idpagamentoVendas;
    }

    public void setIdpagamentoVendas(int idpagamentoVendas) {
        this.idpagamentoVendas = idpagamentoVendas;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public double getValorRecebido() {
        return valorRecebido;
    }

    public void setValorRecebido(double valorRecebido) {
        this.valorRecebido = valorRecebido;
    }

    public double getTroco() {
        return troco;
    }

    public void setTroco(double troco) {
        this.troco = troco;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public String getReferenciaTransacao() {
        return referenciaTransacao;
    }

    public void setReferenciaTransacao(String referenciaTransacao) {
        this.referenciaTransacao = referenciaTransacao;
    }

    public int getVendas_idvendas() {
        return vendas_idvendas;
    }

    public void setVendas_idvendas(int vendas_idvendas) {
        this.vendas_idvendas = vendas_idvendas;
    }

    public int getVendas_cupom_idcupom() {
        return vendas_cupom_idcupom;
    }

    public void setVendas_cupom_idcupom(int vendas_cupom_idcupom) {
        this.vendas_cupom_idcupom = vendas_cupom_idcupom;
    }

    public int getFormasPagamento_idformasPagamento() {
        return formasPagamento_idformasPagamento;
    }

    public void setFormasPagamento_idformasPagamento(int formasPagamento_idformasPagamento) {
        this.formasPagamento_idformasPagamento = formasPagamento_idformasPagamento;
    }

    public int getCondicoesPagamento_idcondicoesPagamento() {
        return condicoesPagamento_idcondicoesPagamento;
    }

    public void setCondicoesPagamento_idcondicoesPagamento(int condicoesPagamento_idcondicoesPagamento) {
        this.condicoesPagamento_idcondicoesPagamento = condicoesPagamento_idcondicoesPagamento;
    }

    public int getContasFinanceiras_idcontasFinanceiras() {
        return contasFinanceiras_idcontasFinanceiras;
    }

    public void setContasFinanceiras_idcontasFinanceiras(int contasFinanceiras_idcontasFinanceiras) {
        this.contasFinanceiras_idcontasFinanceiras = contasFinanceiras_idcontasFinanceiras;
    }
}
