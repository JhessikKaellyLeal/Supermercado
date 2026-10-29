/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author AntônioVieira
 */
public class FechamentoCaixa {
 private int  idfechamentoCaixa,  aberturaCaixa_idaberturaCaixa, Funcionario_idFuncionario, Funcionario_cargos_idcargos, Funcionario_setores_idsetores, Funcionario_turnos_idturnos;
 private String dataFechamento,  horarioFechamento;
 private double valorFinal;

    public FechamentoCaixa() {
    }

    public int getIdfechamentoCaixa() {
        return idfechamentoCaixa;
    }

    public void setIdfechamentoCaixa(int idfechamentoCaixa) {
        this.idfechamentoCaixa = idfechamentoCaixa;
    }

    public int getAberturaCaixa_idaberturaCaixa() {
        return aberturaCaixa_idaberturaCaixa;
    }

    public void setAberturaCaixa_idaberturaCaixa(int aberturaCaixa_idaberturaCaixa) {
        this.aberturaCaixa_idaberturaCaixa = aberturaCaixa_idaberturaCaixa;
    }

    public int getFuncionario_idFuncionario() {
        return Funcionario_idFuncionario;
    }

    public void setFuncionario_idFuncionario(int Funcionario_idFuncionario) {
        this.Funcionario_idFuncionario = Funcionario_idFuncionario;
    }

    public int getFuncionario_cargos_idcargos() {
        return Funcionario_cargos_idcargos;
    }

    public void setFuncionario_cargos_idcargos(int Funcionario_cargos_idcargos) {
        this.Funcionario_cargos_idcargos = Funcionario_cargos_idcargos;
    }

    public int getFuncionario_setores_idsetores() {
        return Funcionario_setores_idsetores;
    }

    public void setFuncionario_setores_idsetores(int Funcionario_setores_idsetores) {
        this.Funcionario_setores_idsetores = Funcionario_setores_idsetores;
    }

    public int getFuncionario_turnos_idturnos() {
        return Funcionario_turnos_idturnos;
    }

    public void setFuncionario_turnos_idturnos(int Funcionario_turnos_idturnos) {
        this.Funcionario_turnos_idturnos = Funcionario_turnos_idturnos;
    }

    public String getDataFechamento() {
        return dataFechamento;
    }

    public void setDataFechamento(String dataFechamento) {
        this.dataFechamento = dataFechamento;
    }

    public String getHorarioFechamento() {
        return horarioFechamento;
    }

    public void setHorarioFechamento(String horarioFechamento) {
        this.horarioFechamento = horarioFechamento;
    }

    public double getValorFinal() {
        return valorFinal;
    }

    public void setValorFinal(double valorFinal) {
        this.valorFinal = valorFinal;
    }
 
}
