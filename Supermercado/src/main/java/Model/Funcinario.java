/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author AntônioVieira
 */
public class Funcinario {
  private int  idFuncionario, cargos_idcargos, setores_idsetores, turnos_idturnos; 
   private String  nome, dataNascimento, cpf, telefone, dataAdmissao;
   private boolean statusFuncionario;

    public Funcinario() {
    }

    public int getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(int idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public int getCargos_idcargos() {
        return cargos_idcargos;
    }

    public void setCargos_idcargos(int cargos_idcargos) {
        this.cargos_idcargos = cargos_idcargos;
    }

    public int getSetores_idsetores() {
        return setores_idsetores;
    }

    public void setSetores_idsetores(int setores_idsetores) {
        this.setores_idsetores = setores_idsetores;
    }

    public int getTurnos_idturnos() {
        return turnos_idturnos;
    }

    public void setTurnos_idturnos(int turnos_idturnos) {
        this.turnos_idturnos = turnos_idturnos;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getDataAdmissao() {
        return dataAdmissao;
    }

    public void setDataAdmissao(String dataAdmissao) {
        this.dataAdmissao = dataAdmissao;
    }

    public boolean isStatusFuncionario() {
        return statusFuncionario;
    }

    public void setStatusFuncionario(boolean statusFuncionario) {
        this.statusFuncionario = statusFuncionario;
    }
  
}
