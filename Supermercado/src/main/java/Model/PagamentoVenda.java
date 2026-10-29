/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author AntônioVieira
 */
public class PagamentoVenda {
   private int idpagamentoVendas, vendas_idvendas, vendas_cupom_idcupom, formasPagamento_idformasPagamento, condicoesPagamento_idcondicoesPagamento, contasFinanceiras_idcontasFinanceiras;
   private String situacao, referenciaTransacao;
   private double valor,  valorRecebido, troco;

    public PagamentoVenda() {
    }

    public int getIdpagamentoVendas() {
        return idpagamentoVendas;
    }

    public void setIdpagamentoVendas(int idpagamentoVendas) {
        this.idpagamentoVendas = idpagamentoVendas;
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
   
}
