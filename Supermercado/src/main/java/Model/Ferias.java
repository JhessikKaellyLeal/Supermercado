/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author AntônioVieira
 */
public class Ferias {
  private int idferias;
  private String  inicio, finalFerias, situacao;

    public Ferias() {
    }

    public int getIdferias() {
        return idferias;
    }

    public void setIdferias(int idferias) {
        this.idferias = idferias;
    }

    public String getInicio() {
        return inicio;
    }

    public void setInicio(String inicio) {
        this.inicio = inicio;
    }

    public String getFinalFerias() {
        return finalFerias;
    }

    public void setFinalFerias(String finalFerias) {
        this.finalFerias = finalFerias;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }
  
}
