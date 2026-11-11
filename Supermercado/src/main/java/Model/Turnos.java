
package Model;

public class Turnos {

    private int idturnos;
    private String nome;
    private String descricao;
    private String horaInicio;
    private String horaSaida;
    private String intervaloInicio;
    private String intervaloFinal;
    private int duracaoTurno;
    private Integer turnoNoturno;
    private int status;

    public Turnos() {
    }

    public Turnos(int idturnos, String nome, String descricao,
            String horaInicio, String horaSaida,
            String intervaloInicio, String intervaloFinal,
            int duracaoTurno, Integer turnoNoturno, int status) {

        this.idturnos = idturnos;
        this.nome = nome;
        this.descricao = descricao;
        this.horaInicio = horaInicio;
        this.horaSaida = horaSaida;
        this.intervaloInicio = intervaloInicio;
        this.intervaloFinal = intervaloFinal;
        this.duracaoTurno = duracaoTurno;
        this.turnoNoturno = turnoNoturno;
        this.status = status;
    }

    public int getIdturnos() {
        return idturnos;
    }

    public void setIdturnos(int idturnos) {
        this.idturnos = idturnos;
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

    public int getDuracaoTurno() {
        return duracaoTurno;
    }

    public void setDuracaoTurno(int duracaoTurno) {
        this.duracaoTurno = duracaoTurno;
    }

    public Integer getTurnoNoturno() {
        return turnoNoturno;
    }

    public void setTurnoNoturno(Integer turnoNoturno) {
        this.turnoNoturno = turnoNoturno;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
