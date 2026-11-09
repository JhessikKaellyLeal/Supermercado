/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author AntônioVieira
 */
public class Turnos {

    private int idturnos, duracaoTurno;
    private String nome, descricao, horaInicio, horaSaida, intervaloInicio, intervaloFinal;
    private boolean turnoNoturno, status;

    public Turnos() {
    }

    public int getIdturnos() {
        return idturnos;
    }

    public void setIdturnos(int idturnos) {
        this.idturnos = idturnos;
    }

    public int getDuracaoTurno() {
        return duracaoTurno;
    }

    public void setDuracaoTurno(int duracaoTurno) {
        this.duracaoTurno = duracaoTurno;
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

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraSaida() {
        return horaSaida;
    }

    public void setHoraSaida(String horaSaida) {
        this.horaSaida = horaSaida;
    }

    public String getIntervaloInicio() {
        return intervaloInicio;
    }

    public void setIntervaloInicio(String intervaloInicio) {
        this.intervaloInicio = intervaloInicio;
    }

    public String getIntervaloFinal() {
        return intervaloFinal;
    }

    public void setIntervaloFinal(String intervaloFinal) {
        this.intervaloFinal = intervaloFinal;
    }

    public boolean isTurnoNoturno() {
        return turnoNoturno;
    }

    public void setTurnoNoturno(boolean turnoNoturno) {
        this.turnoNoturno = turnoNoturno;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

}