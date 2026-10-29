/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author AntônioVieira
 */
public class Cupom {
  private int  idcupom, qttMaximaUso, limitePorCliente, promocao_idpromocao;
  private String codigoCupom, tipoDesconto, tipoUso, dataInicio, dataFinal, observacao;
  private double valorDesconto, valorMinimoCompra;

    public Cupom() {
    }

    public int getIdcupom() {
        return idcupom;
    }

    public void setIdcupom(int idcupom) {
        this.idcupom = idcupom;
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

    public int getPromocao_idpromocao() {
        return promocao_idpromocao;
    }

    public void setPromocao_idpromocao(int promocao_idpromocao) {
        this.promocao_idpromocao = promocao_idpromocao;
    }

    public String getCodigoCupom() {
        return codigoCupom;
    }

    public void setCodigoCupom(String codigoCupom) {
        this.codigoCupom = codigoCupom;
    }

    public String getTipoDesconto() {
        return tipoDesconto;
    }

    public void setTipoDesconto(String tipoDesconto) {
        this.tipoDesconto = tipoDesconto;
    }

    public String getTipoUso() {
        return tipoUso;
    }

    public void setTipoUso(String tipoUso) {
        this.tipoUso = tipoUso;
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
  
}
