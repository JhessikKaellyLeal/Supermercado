/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author AntônioVieira
 */
public class ContasPagar {
  private int idcontasPagar, fornecedores_idfornecedores;
  private String decricao, dataVencimento, dataPagamento, situacao;
  private double valor;

    public ContasPagar() {
    }

    public int getIdcontasPagar() {
        return idcontasPagar;
    }

    public void setIdcontasPagar(int idcontasPagar) {
        this.idcontasPagar = idcontasPagar;
    }

    public int getFornecedores_idfornecedores() {
        return fornecedores_idfornecedores;
    }

    public void setFornecedores_idfornecedores(int fornecedores_idfornecedores) {
        this.fornecedores_idfornecedores = fornecedores_idfornecedores;
    }

    public String getDecricao() {
        return decricao;
    }

    public void setDecricao(String decricao) {
        this.decricao = decricao;
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

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
  
}
