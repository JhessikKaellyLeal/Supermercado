
package Model;

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

    public ParcelasReceber(int idparcelasReceber, int numeroParcela,
            double valorParcela, String dataVencimento,
            String dataPagamento, Double valorRecebido,
            String statusParcela, String observacao,
            int contasReceber_idcontasReceber) {

        this.idparcelasReceber = idparcelasReceber;
        this.numeroParcela = numeroParcela;
        this.valorParcela = valorParcela;
        this.dataVencimento = dataVencimento;
        this.dataPagamento = dataPagamento;
        this.valorRecebido = valorRecebido;
        this.statusParcela = statusParcela;
        this.observacao = observacao;
        this.contasReceber_idcontasReceber =
                contasReceber_idcontasReceber;
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

    public void setContasReceber_idcontasReceber(
            int contasReceber_idcontasReceber) {
        this.contasReceber_idcontasReceber =
                contasReceber_idcontasReceber;
    }
}
