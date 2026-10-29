/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.io.File;

/**
 *
 * @author AntônioVieira
 */
public class ContratosFornecedores {
 private int  idcontratosFornecedores, numero, prazo, fornecedores_idfornecedores;
private String  dataInicio, dataFinal, tipo, descricao,  clausulaRecisao,  observacao;
private double  valorMensal, valorTotal,  multa;
private File arquivo;

    public ContratosFornecedores() {
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

    public int getPrazo() {
        return prazo;
    }

    public void setPrazo(int prazo) {
        this.prazo = prazo;
    }

    public int getFornecedores_idfornecedores() {
        return fornecedores_idfornecedores;
    }

    public void setFornecedores_idfornecedores(int fornecedores_idfornecedores) {
        this.fornecedores_idfornecedores = fornecedores_idfornecedores;
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

    public double getValorMensal() {
        return valorMensal;
    }

    public void setValorMensal(double valorMensal) {
        this.valorMensal = valorMensal;
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

    public File getArquivo() {
        return arquivo;
    }

    public void setArquivo(File arquivo) {
        this.arquivo = arquivo;
    }
        
}
