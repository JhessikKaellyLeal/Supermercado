/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author AntônioVieira
 */
public class ParcelasReceber {
   private int idparcelasReceber;
    private int numeroParcela;
    private double valorParcela;
    private String dataVencimento;
    private String dataPagamento;
    private Double valorRecebido;
    private String statusParcela;
    private String observacao;
    private int contasReceber_idcontasReceber;

    public ParcelasReceber() {
    }

    public int getIdparcelasReceber() {
        return idparcelasReceber;
    }

    public void setIdparcelasReceber(int idparcelasReceber) {
        this.idparcelasReceber = idparcelasReceber;
    }

    public int getNumeroParcela() {
        return numeroParcela;
    }

    public void setNumeroParcela(int numeroParcela) {
        this.numeroParcela = numeroParcela;
    }

    public double getValorParcela() {
        return valorParcela;
    }

    public void setValorParcela(double valorParcela) {
        this.valorParcela = valorParcela;
    }

    public String getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(String dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public String getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(String dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public Double getValorRecebido() {
        return valorRecebido;
    }

    public void setValorRecebido(Double valorRecebido) {
        this.valorRecebido = valorRecebido;
    }

    public String getStatusParcela() {
        return statusParcela;
    }

    public void setStatusParcela(String statusParcela) {
        this.statusParcela = statusParcela;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public int getContasReceber_idcontasReceber() {
        return contasReceber_idcontasReceber;
    }

    public void setContasReceber_idcontasReceber(int contasReceber_idcontasReceber) {
        this.contasReceber_idcontasReceber = contasReceber_idcontasReceber;
    }

 
}
