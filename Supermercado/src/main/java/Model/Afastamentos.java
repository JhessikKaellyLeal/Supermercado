package Model;

public class Afastamentos {

    private int idafastamentos;
    private String tipo;
    private String inicio;
    private String finalAfastamento;
    private String dias;
    private String situacao;
    private String dataRegistro;
    private int Funcionario_idFuncionario;
    private int Funcionario_cargos_idcargos;
    private int Funcionario_setores_idsetores;
    private int Funcionario_turnos_idturnos;

    public Afastamentos() {
    }

    public Afastamentos(int idafastamentos, String tipo, String inicio,
            String finalAfastamento, String dias, String situacao,
            String dataRegistro, int Funcionario_idFuncionario,
            int Funcionario_cargos_idcargos,
            int Funcionario_setores_idsetores,
            int Funcionario_turnos_idturnos) {

        this.idafastamentos = idafastamentos;
        this.tipo = tipo;
        this.inicio = inicio;
        this.finalAfastamento = finalAfastamento;
        this.dias = dias;
        this.situacao = situacao;
        this.dataRegistro = dataRegistro;
        this.Funcionario_idFuncionario = Funcionario_idFuncionario;
        this.Funcionario_cargos_idcargos = Funcionario_cargos_idcargos;
        this.Funcionario_setores_idsetores = Funcionario_setores_idsetores;
        this.Funcionario_turnos_idturnos = Funcionario_turnos_idturnos;
    }

    public int getIdafastamentos() {
        return idafastamentos;
    }

    public void setIdafastamentos(int idafastamentos) {
        this.idafastamentos = idafastamentos;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getInicio() {
        return inicio;
    }

    public void setInicio(String inicio) {
        this.inicio = inicio;
    }

    public String getFinalAfastamento() {
        return finalAfastamento;
    }

    public void setFinalAfastamento(String finalAfastamento) {
        this.finalAfastamento = finalAfastamento;
    }

    public String getDias() {
        return dias;
    }

    public void setDias(String dias) {
        this.dias = dias;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public String getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(String dataRegistro) {
        this.dataRegistro = dataRegistro;
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