
package Model;

public class Cupom {

    private int idcupom;
    private String codigoCupom;
    private double valorDesconto;
    private double valorMinimoCompra;
    private int qttMaximaUso;
    private int limitePorCliente;
    private String tipoUso;
    private String tipoDesconto;
    private String dataInicio;
    private String dataFinal;
    private String observacao;
    private int promocao_idpromocao;

    public Cupom() {
    }

    public Cupom(int idcupom, String codigoCupom, double valorDesconto,
            double valorMinimoCompra, int qttMaximaUso, int limitePorCliente,
            String tipoUso, String tipoDesconto, String dataInicio,
            String dataFinal, String observacao, int promocao_idpromocao) {

        this.idcupom = idcupom;
        this.codigoCupom = codigoCupom;
        this.valorDesconto = valorDesconto;
        this.valorMinimoCompra = valorMinimoCompra;
        this.qttMaximaUso = qttMaximaUso;
        this.limitePorCliente = limitePorCliente;
        this.tipoUso = tipoUso;
        this.tipoDesconto = tipoDesconto;
        this.dataInicio = dataInicio;
        this.dataFinal = dataFinal;
        this.observacao = observacao;
        this.promocao_idpromocao = promocao_idpromocao;
    }

    public int getIdcupom() {
        return idcupom;
    }

    public void setIdcupom(int idcupom) {
        this.idcupom = idcupom;
    }

    public String getCodigoCupom() {
        return codigoCupom;
    }

    public void setCodigoCupom(String codigoCupom) {
        this.codigoCupom = codigoCupom;
    }

    public double getValorDesconto() {
        return valorDesconto;
    }

    public void setValorDesconto(double valorDesconto) {
        this.valorDesconto = valorDesconto;
    }

    public double getValorMinimoCompra() {
        return valorMinimoCompra;
    }

    public void setValorMinimoCompra(double valorMinimoCompra) {
        this.valorMinimoCompra = valorMinimoCompra;
    }

    public int getQttMaximaUso() {
        return qttMaximaUso;
    }

    public void setQttMaximaUso(int qttMaximaUso) {
        this.qttMaximaUso = qttMaximaUso;
    }

    public int getLimitePorCliente() {
        return limitePorCliente;
    }

    public void setLimitePorCliente(int limitePorCliente) {
        this.limitePorCliente = limitePorCliente;
    }

    public String getTipoUso() {
        return tipoUso;
    }

    public void setTipoUso(String tipoUso) {
        this.tipoUso = tipoUso;
    }

    public String getTipoDesconto() {
        return tipoDesconto;
    }

    public void setTipoDesconto(String tipoDesconto) {
        this.tipoDesconto = tipoDesconto;
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

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public int getPromocao_idpromocao() {
        return promocao_idpromocao;
    }

    public void setPromocao_idpromocao(int promocao_idpromocao) {
        this.promocao_idpromocao = promocao_idpromocao;
    }
}
