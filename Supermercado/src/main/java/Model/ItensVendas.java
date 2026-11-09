/**
 *
 * @author JordanaLeite
 */

package model;

public class ItensVendas {

    private int iditensVendas;
    private int quantidade;
    private double precoUnitario;
    private double desconto;
    private double valorTotal;

    private int vendas_idvendas;

    // Campos auxiliares (JOINs)

    private String nomeCliente;
    private String nomeUsuario;
    private String nomeCaixa;
    private String codigoCupom;

    public ItensVendas() {
    }

    public int getIditensVendas() {
        return iditensVendas;
    }

    public void setIditensVendas(int iditensVendas) {
        this.iditensVendas = iditensVendas;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(double precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public double getDesconto() {
        return desconto;
    }

    public void setDesconto(double desconto) {
        this.desconto = desconto;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public int getVendas_idvendas() {
        return vendas_idvendas;
    }

    public void setVendas_idvendas(int vendas_idvendas) {
        this.vendas_idvendas = vendas_idvendas;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getNomeCaixa() {
        return nomeCaixa;
    }

    public void setNomeCaixa(String nomeCaixa) {
        this.nomeCaixa = nomeCaixa;
    }

    public String getCodigoCupom() {
        return codigoCupom;
    }

    public void setCodigoCupom(String codigoCupom) {
        this.codigoCupom = codigoCupom;
    }
}