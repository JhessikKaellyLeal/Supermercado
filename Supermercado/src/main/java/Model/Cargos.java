package Model;

public class Cargos {

    private int idcargos;
    private String nome;
    private String descricao;
    private double salarioBase;
    private int cargaHoraria;
    private double adicionalPericulosidade;
    private double adicionalInsalubridade;
    private int statusCargo;
    private double adicionalNoturno;

    public Cargos() {
    }

    public Cargos(int idcargos, String nome, String descricao,
            double salarioBase, int cargaHoraria,
            double adicionalPericulosidade,
            double adicionalInsalubridade,
            int statusCargo, double adicionalNoturno) {

        this.idcargos = idcargos;
        this.nome = nome;
        this.descricao = descricao;
        this.salarioBase = salarioBase;
        this.cargaHoraria = cargaHoraria;
        this.adicionalPericulosidade = adicionalPericulosidade;
        this.adicionalInsalubridade = adicionalInsalubridade;
        this.statusCargo = statusCargo;
        this.adicionalNoturno = adicionalNoturno;
    }

    public int getIdcargos() {
        return idcargos;
    }

    public void setIdcargos(int idcargos) {
        this.idcargos = idcargos;
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

    public double getSalarioBase() {
        return salarioBase;
    }

    public void setSalarioBase(double salarioBase) {
        this.salarioBase = salarioBase;
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(int cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public double getAdicionalPericulosidade() {
        return adicionalPericulosidade;
    }

    public void setAdicionalPericulosidade(double adicionalPericulosidade) {
        this.adicionalPericulosidade = adicionalPericulosidade;
    }

    public double getAdicionalInsalubridade() {
        return adicionalInsalubridade;
    }

    public void setAdicionalInsalubridade(double adicionalInsalubridade) {
        this.adicionalInsalubridade = adicionalInsalubridade;
    }

    public int getStatusCargo() {
        return statusCargo;
    }

    public void setStatusCargo(int statusCargo) {
        this.statusCargo = statusCargo;
    }

    public double getAdicionalNoturno() {
        return adicionalNoturno;
    }

    public void setAdicionalNoturno(double adicionalNoturno) {
        this.adicionalNoturno = adicionalNoturno;
    }
}