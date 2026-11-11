
package Model;

public class Escala {

    private int idescala;
    private String periodoInicial;
    private String periodoFinal;
    private int Funcionario_idFuncionario;
    private int Funcionario_cargos_idcargos;
    private int Funcionario_setores_idsetores;
    private int Funcionario_turnos_idturnos;

    public Escala() {
    }

    public Escala(int idescala, String periodoInicial, String periodoFinal,
            int Funcionario_idFuncionario,
            int Funcionario_cargos_idcargos,
            int Funcionario_setores_idsetores,
            int Funcionario_turnos_idturnos) {

        this.idescala = idescala;
        this.periodoInicial = periodoInicial;
        this.periodoFinal = periodoFinal;
        this.Funcionario_idFuncionario = Funcionario_idFuncionario;
        this.Funcionario_cargos_idcargos = Funcionario_cargos_idcargos;
        this.Funcionario_setores_idsetores = Funcionario_setores_idsetores;
        this.Funcionario_turnos_idturnos = Funcionario_turnos_idturnos;
    }

    public int getIdescala() {
        return idescala;
    }

    public void setIdescala(int idescala) {
        this.idescala = idescala;
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
}
