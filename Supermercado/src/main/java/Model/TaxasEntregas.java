/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author AntônioVieira
 */
public class TaxasEntregas {
   private int identregas,  idtaxasEntrega;
   private String regiao, tipoTaxa, observacao;
   private double valorTaxa;

    public TaxasEntregas() {
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

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public double getValorTaxa() {
        return valorTaxa;
    }

    public void setValorTaxa(double valorTaxa) {
        this.valorTaxa = valorTaxa;
    }
   
}
