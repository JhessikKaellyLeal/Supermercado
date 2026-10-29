/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author AntônioVieira
 */
public class Entregas {
  private int  identregas, cliente_idcliente, veiculos_idveiculos, taxasEntregas_idtaxasEntregas, funcionario_idFuncionario, funcionario_cargos_idcargos, Funcionario_setores_idsetores, Funcionario_turnos_idturnos;
private String dataentrega, horarioEntrega,statusEntrega;

    public Entregas() {
    }

    public int getIdentregas() {
        return identregas;
    }

    public void setIdentregas(int identregas) {
        this.identregas = identregas;
    }

    public int getCliente_idcliente() {
        return cliente_idcliente;
    }

    public void setCliente_idcliente(int cliente_idcliente) {
        this.cliente_idcliente = cliente_idcliente;
    }

    public int getVeiculos_idveiculos() {
        return veiculos_idveiculos;
    }

    public void setVeiculos_idveiculos(int veiculos_idveiculos) {
        this.veiculos_idveiculos = veiculos_idveiculos;
    }

    public int getTaxasEntregas_idtaxasEntregas() {
        return taxasEntregas_idtaxasEntregas;
    }

    public void setTaxasEntregas_idtaxasEntregas(int taxasEntregas_idtaxasEntregas) {
        this.taxasEntregas_idtaxasEntregas = taxasEntregas_idtaxasEntregas;
    }

    public int getFuncionario_idFuncionario() {
        return funcionario_idFuncionario;
    }

    public void setFuncionario_idFuncionario(int funcionario_idFuncionario) {
        this.funcionario_idFuncionario = funcionario_idFuncionario;
    }

    public int getFuncionario_cargos_idcargos() {
        return funcionario_cargos_idcargos;
    }

    public void setFuncionario_cargos_idcargos(int funcionario_cargos_idcargos) {
        this.funcionario_cargos_idcargos = funcionario_cargos_idcargos;
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

    public String getDataentrega() {
        return dataentrega;
    }

    public void setDataentrega(String dataentrega) {
        this.dataentrega = dataentrega;
    }

    public String getHorarioEntrega() {
        return horarioEntrega;
    }

    public void setHorarioEntrega(String horarioEntrega) {
        this.horarioEntrega = horarioEntrega;
    }

    public String getStatusEntrega() {
        return statusEntrega;
    }

    public void setStatusEntrega(String statusEntrega) {
        this.statusEntrega = statusEntrega;
    }

}
