/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author AntônioVieira
 */
public class CondicoesPagamento {
  private int  idcondicoesPagamento, quantidadeParcelas, intervaloDias;

    public CondicoesPagamento() {
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
