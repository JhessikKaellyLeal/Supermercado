package Model;

public class CondicoesPagamento {

    private int idcondicoesPagamento;
    private int quantidadeParcelas;
    private int intervaloDias;

    public CondicoesPagamento() {
    }

    public CondicoesPagamento(int idcondicoesPagamento,
            int quantidadeParcelas, int intervaloDias) {

        this.idcondicoesPagamento = idcondicoesPagamento;
        this.quantidadeParcelas = quantidadeParcelas;
        this.intervaloDias = intervaloDias;
    }

    public int getIdcondicoesPagamento() {
        return idcondicoesPagamento;
    }

    public void setIdcondicoesPagamento(int idcondicoesPagamento) {
        this.idcondicoesPagamento = idcondicoesPagamento;
    }

    public int getQuantidadeParcelas() {
        return quantidadeParcelas;
    }

    public void setQuantidadeParcelas(int quantidadeParcelas) {
        this.quantidadeParcelas = quantidadeParcelas;
    }

    public int getIntervaloDias() {
        return intervaloDias;
    }

    public void setIntervaloDias(int intervaloDias) {
        this.intervaloDias = intervaloDias;
    }
}