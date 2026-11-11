
package Model;

public class TaxasEntregas {

    private int idtaxasEntrega;
    private String regiao;
    private String tipoTaxa;
    private double valorTaxa;
    private String observacao;

    public TaxasEntregas() {
    }

    public TaxasEntregas(int idtaxasEntrega, String regiao,
            String tipoTaxa, double valorTaxa, String observacao) {

        this.idtaxasEntrega = idtaxasEntrega;
        this.regiao = regiao;
        this.tipoTaxa = tipoTaxa;
        this.valorTaxa = valorTaxa;
        this.observacao = observacao;
    }

    public int getIdtaxasEntrega() {
        return idtaxasEntrega;
    }

    public void setIdtaxasEntrega(int idtaxasEntrega) {
        this.idtaxasEntrega = idtaxasEntrega;
    }

    public String getRegiao() {
        return regiao;
    }

    public void setRegiao(String regiao) {
        this.regiao = regiao;
    }

    public String getTipoTaxa() {
        return tipoTaxa;
    }

    public void setTipoTaxa(String tipoTaxa) {
        this.tipoTaxa = tipoTaxa;
    }

    public double getValorTaxa() {
        return valorTaxa;
    }

    public void setValorTaxa(double valorTaxa) {
        this.valorTaxa = valorTaxa;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
}
