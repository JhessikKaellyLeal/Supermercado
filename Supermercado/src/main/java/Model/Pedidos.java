/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author AntônioVieira
 */
public class Pedidos {
  private int idpedidos, cliente_idcliente, usuario_idusuario,entregas_identregas;
private String numeroPedidos, dataPedidos, statusPedido,observacao;

    public Pedidos() {
    }

    public int getIdpedidos() {
        return idpedidos;
    }

    public void setIdpedidos(int idpedidos) {
        this.idpedidos = idpedidos;
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

    public int getEntregas_identregas() {
        return entregas_identregas;
    }

    public void setEntregas_identregas(int entregas_identregas) {
        this.entregas_identregas = entregas_identregas;
    }

    public String getNumeroPedidos() {
        return numeroPedidos;
    }

    public void setNumeroPedidos(String numeroPedidos) {
        this.numeroPedidos = numeroPedidos;
    }

    public String getDataPedidos() {
        return dataPedidos;
    }

    public void setDataPedidos(String dataPedidos) {
        this.dataPedidos = dataPedidos;
    }

    public String getStatusPedido() {
        return statusPedido;
    }

    public void setStatusPedido(String statusPedido) {
        this.statusPedido = statusPedido;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
        
}
