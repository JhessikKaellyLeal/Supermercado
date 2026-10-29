/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author AntônioVieira
 */
public class Cargos {
   private int idcargos, cargahorario;
   private String nome, descricao;
   private double salariobase, adicionalpericulosidade, adicionalinsalubridade, adicionalnoturno;
   private boolean statuscargo;

    public Cargos() {
    }

    public int getIdcargos() {
        return idcargos;
    }

    public void setIdcargos(int idcargos) {
        this.idcargos = idcargos;
    }

    public int getCargahorario() {
        return cargahorario;
    }

    public void setCargahorario(int cargahorario) {
        this.cargahorario = cargahorario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getSalariobase() {
        return salariobase;
    }

    public void setSalariobase(double salariobase) {
        this.salariobase = salariobase;
    }

    public double getAdicionalpericulosidade() {
        return adicionalpericulosidade;
    }

    public void setAdicionalpericulosidade(double adicionalpericulosidade) {
        this.adicionalpericulosidade = adicionalpericulosidade;
    }

    public double getAdicionalinsalubridade() {
        return adicionalinsalubridade;
    }

    public void setAdicionalinsalubridade(double adicionalinsalubridade) {
        this.adicionalinsalubridade = adicionalinsalubridade;
    }

    public double getAdicionalnoturno() {
        return adicionalnoturno;
    }

    public void setAdicionalnoturno(double adicionalnoturno) {
        this.adicionalnoturno = adicionalnoturno;
    }

    public boolean isStatuscargo() {
        return statuscargo;
    }

    public void setStatuscargo(boolean statuscargo) {
        this.statuscargo = statuscargo;
    }
   
    
   
   
   
   
   
   
   
}
