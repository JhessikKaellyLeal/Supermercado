
package Model;

public class Veiculos {

    private int idveiculos;
    private String tipoVeiculo;
    private String marca;
    private String modelo;
    private int anoVeiculo;
    private String cor;
    private double capacidadeCarga;
    private String statusVeiculo;
    private String placa;
    private String observacoes;

    // CONSTRUTOR VAZIO
    public Veiculos() {
    }

    // CONSTRUTOR COMPLETO
    public Veiculos(int idveiculos, String tipoVeiculo, String marca,
            String modelo, int anoVeiculo, String cor,
            double capacidadeCarga, String statusVeiculo,
            String placa, String observacoes) {

        this.idveiculos = idveiculos;
        this.tipoVeiculo = tipoVeiculo;
        this.marca = marca;
        this.modelo = modelo;
        this.anoVeiculo = anoVeiculo;
        this.cor = cor;
        this.capacidadeCarga = capacidadeCarga;
        this.statusVeiculo = statusVeiculo;
        this.placa = placa;
        this.observacoes = observacoes;
    }

    public int getIdveiculos() {
        return idveiculos;
    }

    public void setIdveiculos(int idveiculos) {
        this.idveiculos = idveiculos;
    }

    public String getTipoVeiculo() {
        return tipoVeiculo;
    }

    public void setTipoVeiculo(String tipoVeiculo) {
        this.tipoVeiculo = tipoVeiculo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getAnoVeiculo() {
        return anoVeiculo;
    }

    public void setAnoVeiculo(int anoVeiculo) {
        this.anoVeiculo = anoVeiculo;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public double getCapacidadeCarga() {
        return capacidadeCarga;
    }

    public void setCapacidadeCarga(double capacidadeCarga) {
        this.capacidadeCarga = capacidadeCarga;
    }

    public String getStatusVeiculo() {
        return statusVeiculo;
    }

    public void setStatusVeiculo(String statusVeiculo) {
        this.statusVeiculo = statusVeiculo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }
}
