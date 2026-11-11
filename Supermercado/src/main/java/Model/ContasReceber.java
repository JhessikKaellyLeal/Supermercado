
package Model;

public class ContasReceber {

    private int idcontasReceber;
    private double valorReceber;
    private String descricao;
    private String dataVencimento;
    private String dataRecebimento;
    private String situacao;
    private int cliente_idcliente;

    public ContasReceber() {
    }

    public ContasReceber(int idcontasReceber, double valorReceber,
            String descricao, String dataVencimento, String dataRecebimento,
            String situacao, int cliente_idcliente) {
        this.idcontasReceber = idcontasReceber;
        this.valorReceber = valorReceber;
        this.descricao = descricao;
        this.dataVencimento = dataVencimento;
        this.dataRecebimento = dataRecebimento;
        this.situacao = situacao;
        this.cliente_idcliente = cliente_idcliente;
    }

    public int getIdcontasReceber() {
        return idcontasReceber;
    }

    public void setIdcontasReceber(int idcontasReceber) {
        this.idcontasReceber = idcontasReceber;
    }

    public double getValorReceber() {
        return valorReceber;
    }

    public void setValorReceber(double valorReceber) {
        this.valorReceber = valorReceber;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(String dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public String getDataRecebimento() {
        return dataRecebimento;
    }

    public void setDataRecebimento(String dataRecebimento) {
        this.dataRecebimento = dataRecebimento;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public int getCliente_idcliente() {
        return cliente_idcliente;
    }

    public void setCliente_idcliente(int cliente_idcliente) {
        this.cliente_idcliente = cliente_idcliente;
    }
}
