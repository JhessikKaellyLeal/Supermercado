
package Model;

public class ContasPagar {

    private int idcontasPagar;
    private double valor;
    private String descricao;
    private String dataVencimento;
    private String dataPagamento;
    private String situacao;
    private int fornecedores_idfornecedores;

    public ContasPagar() {
    }

    public ContasPagar(int idcontasPagar, double valor, String descricao,
            String dataVencimento, String dataPagamento, String situacao,
            int fornecedores_idfornecedores) {
        this.idcontasPagar = idcontasPagar;
        this.valor = valor;
        this.descricao = descricao;
        this.dataVencimento = dataVencimento;
        this.dataPagamento = dataPagamento;
        this.situacao = situacao;
        this.fornecedores_idfornecedores = fornecedores_idfornecedores;
    }

    public int getIdcontasPagar() {
        return idcontasPagar;
    }

    public void setIdcontasPagar(int idcontasPagar) {
        this.idcontasPagar = idcontasPagar;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
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

    public String getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(String dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public int getFornecedores_idfornecedores() {
        return fornecedores_idfornecedores;
    }

    public void setFornecedores_idfornecedores(int fornecedores_idfornecedores) {
        this.fornecedores_idfornecedores = fornecedores_idfornecedores;
    }
}
