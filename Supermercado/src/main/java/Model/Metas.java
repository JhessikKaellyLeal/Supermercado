/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author AntônioVieira
 */
public class Metas {
   private int  idmetas, Funcionario_idFuncionario, Funcionario_cargos_idcargos, Funcionario_setores_idsetores, Funcionario_turnos_idturnos;
   private String tipo, periodoInicial, periodoFinal, situacao;
   private double metaPrevista, resultadoAtual, porcentagemAtingido;

    public Metas() {
    }

    public int getIdmetas() {
        return idmetas;
    }

    public void setIdmetas(int idmetas) {
        this.idmetas = idmetas;
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getPeriodoInicial() {
        return periodoInicial;
    }

    public void setPeriodoInicial(String periodoInicial) {
        this.periodoInicial = periodoInicial;
    }

    public String getPeriodoFinal() {
        return periodoFinal;
    }

    public void setPeriodoFinal(String periodoFinal) {
        this.periodoFinal = periodoFinal;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public double getMetaPrevista() {
        return metaPrevista;
    }

    public void setMetaPrevista(double metaPrevista) {
        this.metaPrevista = metaPrevista;
    }

    public double getResultadoAtual() {
        return resultadoAtual;
    }

    public void setResultadoAtual(double resultadoAtual) {
        this.resultadoAtual = resultadoAtual;
    }

    public double getPorcentagemAtingido() {
        return porcentagemAtingido;
    }

    public void setPorcentagemAtingido(double porcentagemAtingido) {
        this.porcentagemAtingido = porcentagemAtingido;
    }
   
}
