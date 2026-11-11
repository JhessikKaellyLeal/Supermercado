
package Model;

public class ParcelasPagar {

    private int idparcelasPagar;
    private int numeroParcelas;
    private double valorParcelas;
    private String dataVencimento;
    private String dataPagamento;
    private double valorPago;
    private String statusParcela;
    private String observacao;
    private int contasPagar_idcontasPagar;

    public ParcelasPagar() {
    }

    public ParcelasPagar(int idparcelasPagar, int numeroParcelas,
            double valorParcelas, String dataVencimento,
            String dataPagamento, double valorPago,
            String statusParcela, String observacao,
            int contasPagar_idcontasPagar) {

        this.idparcelasPagar = idparcelasPagar;
        this.numeroParcelas = numeroParcelas;
        this.valorParcelas = valorParcelas;
        this.dataVencimento = dataVencimento;
        this.dataPagamento = dataPagamento;
        this.valorPago = valorPago;
        this.statusParcela = statusParcela;
        this.observacao = observacao;
        this.contasPagar_idcontasPagar = contasPagar_idcontasPagar;
    }

    public int getIdparcelasPagar() {
        return idparcelasPagar;
    }

    public void setIdparcelasPagar(int idparcelasPagar) {
        this.idparcelasPagar = idparcelasPagar;
    }

    public int getNumeroParcelas() {
        return numeroParcelas;
    }

    public void setNumeroParcelas(int numeroParcelas) {
        this.numeroParcelas = numeroParcelas;
    }

    public double getValorParcelas() {
        return valorParcelas;
    }

    public void setValorParcelas(double valorParcelas) {
        this.valorParcelas = valorParcelas;
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

    public double getValorPago() {
        return valorPago;
    }

    public void setValorPago(double valorPago) {
        this.valorPago = valorPago;
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

    public int getContasPagar_idcontasPagar() {
        return contasPagar_idcontasPagar;
    }

    public void setContasPagar_idcontasPagar(int contasPagar_idcontasPagar) {
        this.contasPagar_idcontasPagar = contasPagar_idcontasPagar;
    }
}
