/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author AntônioVieira
 */
public class MovimentacoesFinanceiras {
  private int  idmovimentacoesFinanceiras, contasFinanceiras_idcontasFinanceiras;
  private String tipo, dataHora, descricao, origem;
private double valor;

    public MovimentacoesFinanceiras() {
    }

    public int getIdmovimentacoesFinanceiras() {
        return idmovimentacoesFinanceiras;
    }

    public void setIdmovimentacoesFinanceiras(int idmovimentacoesFinanceiras) {
        this.idmovimentacoesFinanceiras = idmovimentacoesFinanceiras;
    }

    public int getContasFinanceiras_idcontasFinanceiras() {
        return contasFinanceiras_idcontasFinanceiras;
    }

    public void setContasFinanceiras_idcontasFinanceiras(int contasFinanceiras_idcontasFinanceiras) {
        this.contasFinanceiras_idcontasFinanceiras = contasFinanceiras_idcontasFinanceiras;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDataHora() {
        return dataHora;
    }

    public void setDataHora(String dataHora) {
        this.dataHora = dataHora;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

}
