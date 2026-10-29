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
    private String nome, descriacao, horainicio, horasaida, intervaloIncio,intervaloFinal;
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

    public String getDescriacao() {
        return descriacao;
    }

    public void setDescriacao(String descriacao) {
        this.descriacao = descriacao;
    }

    public String getHorainicio() {
        return horainicio;
    }

    public void setHorainicio(String horainicio) {
        this.horainicio = horainicio;
    }

    public String getHorasaida() {
        return horasaida;
    }

    public void setHorasaida(String horasaida) {
        this.horasaida = horasaida;
    }

    public String getIntervaloIncio() {
        return intervaloIncio;
    }

    public void setIntervaloIncio(String intervaloIncio) {
        this.intervaloIncio = intervaloIncio;
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
