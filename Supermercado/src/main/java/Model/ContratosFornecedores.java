
package Model;

public class ContratosFornecedores {

    private int idcontratosFornecedores;
    private int numero;
    private String dataInicio;
    private String dataFinal;
    private double valorMensal;
    private String tipo;
    private String descricao;
    private int prazo;
    private int renovacaoAutomatica;
    private double valorTotal;
    private double multa;
    private String clausulaRecisao;
    private String observacao;
    private byte[] arquivo;
    private int fornecedores_idfornecedores;

    public ContratosFornecedores() {
    }

    public ContratosFornecedores(int idcontratosFornecedores, int numero,
            String dataInicio, String dataFinal, double valorMensal,
            String tipo, String descricao, int prazo, int renovacaoAutomatica,
            double valorTotal, double multa, String clausulaRecisao,
            String observacao, byte[] arquivo, int fornecedores_idfornecedores) {

        this.idcontratosFornecedores = idcontratosFornecedores;
        this.numero = numero;
        this.dataInicio = dataInicio;
        this.dataFinal = dataFinal;
        this.valorMensal = valorMensal;
        this.tipo = tipo;
        this.descricao = descricao;
        this.prazo = prazo;
        this.renovacaoAutomatica = renovacaoAutomatica;
        this.valorTotal = valorTotal;
        this.multa = multa;
        this.clausulaRecisao = clausulaRecisao;
        this.observacao = observacao;
        this.arquivo = arquivo;
        this.fornecedores_idfornecedores = fornecedores_idfornecedores;
    }

    public int getIdcontratosFornecedores() {
        return idcontratosFornecedores;
    }

    public void setIdcontratosFornecedores(int idcontratosFornecedores) {
        this.idcontratosFornecedores = idcontratosFornecedores;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
    }

    public String getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(String dataFinal) {
        this.dataFinal = dataFinal;
    }

    public double getValorMensal() {
        return valorMensal;
    }

    public void setValorMensal(double valorMensal) {
        this.valorMensal = valorMensal;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getPrazo() {
        return prazo;
    }

    public void setPrazo(int prazo) {
        this.prazo = prazo;
    }

    public int getRenovacaoAutomatica() {
        return renovacaoAutomatica;
    }

    public void setRenovacaoAutomatica(int renovacaoAutomatica) {
        this.renovacaoAutomatica = renovacaoAutomatica;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public double getMulta() {
        return multa;
    }

    public void setMulta(double multa) {
        this.multa = multa;
    }

    public String getClausulaRecisao() {
        return clausulaRecisao;
    }

    public void setClausulaRecisao(String clausulaRecisao) {
        this.clausulaRecisao = clausulaRecisao;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public byte[] getArquivo() {
        return arquivo;
    }

    public void setArquivo(byte[] arquivo) {
        this.arquivo = arquivo;
    }

    public int getFornecedores_idfornecedores() {
        return fornecedores_idfornecedores;
    }

    public void setFornecedores_idfornecedores(int fornecedores_idfornecedores) {
        this.fornecedores_idfornecedores = fornecedores_idfornecedores;
    }
}
