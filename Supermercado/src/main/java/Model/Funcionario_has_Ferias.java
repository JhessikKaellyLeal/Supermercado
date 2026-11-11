
package Model;

public class Funcionario_has_Ferias {

    private int Funcionario_idFuncionario;
    private int Funcionario_cargos_idcargos;
    private int Funcionario_setores_idsetores;
    private int Funcionario_turnos_idturnos;
    private int ferias_idferias;

    public Funcionario_has_Ferias() {
    
    }
    

    public Funcionario_has_Ferias(int Funcionario_idFuncionario,
            int Funcionario_cargos_idcargos,
            int Funcionario_setores_idsetores,
            int Funcionario_turnos_idturnos,
            int ferias_idferias) {

        this.Funcionario_idFuncionario = Funcionario_idFuncionario;
        this.Funcionario_cargos_idcargos = Funcionario_cargos_idcargos;
        this.Funcionario_setores_idsetores = Funcionario_setores_idsetores;
        this.Funcionario_turnos_idturnos = Funcionario_turnos_idturnos;
        this.ferias_idferias = ferias_idferias;
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

    public int getFerias_idferias() {
        return ferias_idferias;
    }

    public void setFerias_idferias(int ferias_idferias) {
        this.ferias_idferias = ferias_idferias;
    }
}
