
package Model;

public class Entregas {

    private int identregas;
    private String dataEntrega;
    private String horarioEntrega;
    private String statusEntrega;
    private int cliente_idcliente;
    private int veiculos_idveiculos;
    private int taxasEntrega_idtaxasEntrega;
    private int Funcionario_idFuncionario;
    private int Funcionario_cargos_idcargos;
    private int Funcionario_setores_idsetores;
    private int Funcionario_turnos_idturnos;

    // CONSTRUTOR VAZIO
    public Entregas() {
    }

    // CONSTRUTOR COMPLETO
    public Entregas(int identregas, String dataEntrega,
            String horarioEntrega, String statusEntrega,
            int cliente_idcliente, int veiculos_idveiculos,
            int taxasEntrega_idtaxasEntrega,
            int Funcionario_idFuncionario,
            int Funcionario_cargos_idcargos,
            int Funcionario_setores_idsetores,
            int Funcionario_turnos_idturnos) {

        this.identregas = identregas;
        this.dataEntrega = dataEntrega;
        this.horarioEntrega = horarioEntrega;
        this.statusEntrega = statusEntrega;
        this.cliente_idcliente = cliente_idcliente;
        this.veiculos_idveiculos = veiculos_idveiculos;
        this.taxasEntrega_idtaxasEntrega = taxasEntrega_idtaxasEntrega;
        this.Funcionario_idFuncionario = Funcionario_idFuncionario;
        this.Funcionario_cargos_idcargos = Funcionario_cargos_idcargos;
        this.Funcionario_setores_idsetores = Funcionario_setores_idsetores;
        this.Funcionario_turnos_idturnos = Funcionario_turnos_idturnos;
    }

    // GETTERS E SETTERS

    public int getIdentregas() {
        return identregas;
    }

    public void setIdentregas(int identregas) {
        this.identregas = identregas;
    }

    public String getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(String dataEntrega) {
        this.dataEntrega = dataEntrega;
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

    public int getTaxasEntrega_idtaxasEntrega() {
        return taxasEntrega_idtaxasEntrega;
    }

    public void setTaxasEntrega_idtaxasEntrega(
            int taxasEntrega_idtaxasEntrega) {
        this.taxasEntrega_idtaxasEntrega = taxasEntrega_idtaxasEntrega;
    }

    public int getFuncionario_idFuncionario() {
        return Funcionario_idFuncionario;
    }

    public void setFuncionario_idFuncionario(
            int Funcionario_idFuncionario) {
        this.Funcionario_idFuncionario = Funcionario_idFuncionario;
    }

    public int getFuncionario_cargos_idcargos() {
        return Funcionario_cargos_idcargos;
    }

    public void setFuncionario_cargos_idcargos(
            int Funcionario_cargos_idcargos) {
        this.Funcionario_cargos_idcargos = Funcionario_cargos_idcargos;
    }

    public int getFuncionario_setores_idsetores() {
        return Funcionario_setores_idsetores;
    }

    public void setFuncionario_setores_idsetores(
            int Funcionario_setores_idsetores) {
        this.Funcionario_setores_idsetores = Funcionario_setores_idsetores;
    }

    public int getFuncionario_turnos_idturnos() {
        return Funcionario_turnos_idturnos;
    }

    public void setFuncionario_turnos_idturnos(
            int Funcionario_turnos_idturnos) {
        this.Funcionario_turnos_idturnos = Funcionario_turnos_idturnos;
    }
}
