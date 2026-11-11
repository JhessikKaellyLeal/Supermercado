
package Model;

public class FechamentoCaixa {

    private int idfechamentoCaixa;
    private String dataFechamento;
    private String horarioFechamento;
    private double valorFinal;
    private int aberturaCaixa_idaberturaCaixa;
    private int Funcionario_idFuncionario;
    private int Funcionario_cargos_idcargos;
    private int Funcionario_setores_idsetores;
    private int Funcionario_turnos_idturnos;

    public FechamentoCaixa() {
    }

    public FechamentoCaixa(int idfechamentoCaixa, String dataFechamento,
            String horarioFechamento, double valorFinal,
            int aberturaCaixa_idaberturaCaixa,
            int Funcionario_idFuncionario,
            int Funcionario_cargos_idcargos,
            int Funcionario_setores_idsetores,
            int Funcionario_turnos_idturnos) {

        this.idfechamentoCaixa = idfechamentoCaixa;
        this.dataFechamento = dataFechamento;
        this.horarioFechamento = horarioFechamento;
        this.valorFinal = valorFinal;
        this.aberturaCaixa_idaberturaCaixa = aberturaCaixa_idaberturaCaixa;
        this.Funcionario_idFuncionario = Funcionario_idFuncionario;
        this.Funcionario_cargos_idcargos = Funcionario_cargos_idcargos;
        this.Funcionario_setores_idsetores = Funcionario_setores_idsetores;
        this.Funcionario_turnos_idturnos = Funcionario_turnos_idturnos;
    }

    public int getIdfechamentoCaixa() {
        return idfechamentoCaixa;
    }

    public void setIdfechamentoCaixa(int idfechamentoCaixa) {
        this.idfechamentoCaixa = idfechamentoCaixa;
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
}
