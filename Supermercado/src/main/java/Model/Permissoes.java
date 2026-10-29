/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author AntônioVieira
 */
public class Permissoes {
 private int idpermissoes;
 private String tipo;
 private boolean telaProdutos, telaEstoque, telaCompras, telaClientes, telaPromocoes, telaCaixa, telaVenda, telaPDV,  telaPedidos, telaEntregas, telaRh, telaFinaceiro, telaRelatorios;

    public Permissoes() {
    }

    public int getIdpermissoes() {
        return idpermissoes;
    }

    public void setIdpermissoes(int idpermissoes) {
        this.idpermissoes = idpermissoes;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public boolean isTelaProdutos() {
        return telaProdutos;
    }

    public void setTelaProdutos(boolean telaProdutos) {
        this.telaProdutos = telaProdutos;
    }

    public boolean isTelaEstoque() {
        return telaEstoque;
    }

    public void setTelaEstoque(boolean telaEstoque) {
        this.telaEstoque = telaEstoque;
    }

    public boolean isTelaCompras() {
        return telaCompras;
    }

    public void setTelaCompras(boolean telaCompras) {
        this.telaCompras = telaCompras;
    }

    public boolean isTelaClientes() {
        return telaClientes;
    }

    public void setTelaClientes(boolean telaClientes) {
        this.telaClientes = telaClientes;
    }

    public boolean isTelaPromocoes() {
        return telaPromocoes;
    }

    public void setTelaPromocoes(boolean telaPromocoes) {
        this.telaPromocoes = telaPromocoes;
    }

    public boolean isTelaCaixa() {
        return telaCaixa;
    }

    public void setTelaCaixa(boolean telaCaixa) {
        this.telaCaixa = telaCaixa;
    }

    public boolean isTelaVenda() {
        return telaVenda;
    }

    public void setTelaVenda(boolean telaVenda) {
        this.telaVenda = telaVenda;
    }

    public boolean isTelaPDV() {
        return telaPDV;
    }

    public void setTelaPDV(boolean telaPDV) {
        this.telaPDV = telaPDV;
    }

    public boolean isTelaPedidos() {
        return telaPedidos;
    }

    public void setTelaPedidos(boolean telaPedidos) {
        this.telaPedidos = telaPedidos;
    }

    public boolean isTelaEntregas() {
        return telaEntregas;
    }

    public void setTelaEntregas(boolean telaEntregas) {
        this.telaEntregas = telaEntregas;
    }

    public boolean isTelaRh() {
        return telaRh;
    }

    public void setTelaRh(boolean telaRh) {
        this.telaRh = telaRh;
    }

    public boolean isTelaFinaceiro() {
        return telaFinaceiro;
    }

    public void setTelaFinaceiro(boolean telaFinaceiro) {
        this.telaFinaceiro = telaFinaceiro;
    }

    public boolean isTelaRelatorios() {
        return telaRelatorios;
    }

    public void setTelaRelatorios(boolean telaRelatorios) {
        this.telaRelatorios = telaRelatorios;
    }
 
}
