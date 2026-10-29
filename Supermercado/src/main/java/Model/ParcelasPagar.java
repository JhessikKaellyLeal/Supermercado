/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author AntônioVieira
 */
public class ParcelasPagar {
   private int   idparcelasPagar, numeroParcela, contasPagar_idcontasPagar;
   private String dataVencimento , dataPagamento, statusParcela, observacao;
   private double valorParcela, valorPago;

    public ParcelasPagar() {
    }

    public int getIdparcelasPagar() {
        return idparcelasPagar;
    }

    public void setIdparcelasPagar(int idparcelasPagar) {
        this.idparcelasPagar = idparcelasPagar;
    }

    public int getNumeroParcela() {
        return numeroParcela;
    }

    public void setNumeroParcela(int numeroParcela) {
        this.numeroParcela = numeroParcela;
    }

    public int getContasPagar_idcontasPagar() {
        return contasPagar_idcontasPagar;
    }

    public void setContasPagar_idcontasPagar(int contasPagar_idcontasPagar) {
        this.contasPagar_idcontasPagar = contasPagar_idcontasPagar;
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

    public double getValorParcela() {
        return valorParcela;
    }

    public void setValorParcela(double valorParcela) {
        this.valorParcela = valorParcela;
    }

    public double getValorPago() {
        return valorPago;
    }

    public void setValorPago(double valorPago) {
        this.valorPago = valorPago;
    }
   
}
