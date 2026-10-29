/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author AntônioVieira
 */
public class Vendas {
 private int  idvendas, cliente_idcliente, usuario_idusuario, caixas_idcaixas, cupom_idcupom;
 private String dataVenda, horarioVenda, statusVenda;
 private double  valorTotal, valorPago, troco;

    public Vendas() {
    }

    public int getIdvendas() {
        return idvendas;
    }

    public void setIdvendas(int idvendas) {
        this.idvendas = idvendas;
    }

    public int getCliente_idcliente() {
        return cliente_idcliente;
    }

    public void setCliente_idcliente(int cliente_idcliente) {
        this.cliente_idcliente = cliente_idcliente;
    }

    public int getUsuario_idusuario() {
        return usuario_idusuario;
    }

    public void setUsuario_idusuario(int usuario_idusuario) {
        this.usuario_idusuario = usuario_idusuario;
    }

    public int getCaixas_idcaixas() {
        return caixas_idcaixas;
    }

    public void setCaixas_idcaixas(int caixas_idcaixas) {
        this.caixas_idcaixas = caixas_idcaixas;
    }

    public int getCupom_idcupom() {
        return cupom_idcupom;
    }

    public void setCupom_idcupom(int cupom_idcupom) {
        this.cupom_idcupom = cupom_idcupom;
    }

    public String getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(String dataVenda) {
        this.dataVenda = dataVenda;
    }

    public String getHorarioVenda() {
        return horarioVenda;
    }

    public void setHorarioVenda(String horarioVenda) {
        this.horarioVenda = horarioVenda;
    }

    public String getStatusVenda() {
        return statusVenda;
    }

    public void setStatusVenda(String statusVenda) {
        this.statusVenda = statusVenda;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public double getValorPago() {
        return valorPago;
    }

    public void setValorPago(double valorPago) {
        this.valorPago = valorPago;
    }

    public double getTroco() {
        return troco;
    }

    public void setTroco(double troco) {
        this.troco = troco;
    }
 
}
