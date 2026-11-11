
package Model;

public class SaldoCaixa {

    private int idsaldoCaixa;
    private int qttNotaDuzentos;
    private int qttNotaCem;
    private int qttNotaVinte;
    private int qttNotaCinquenta;
    private int qttNotaDez;
    private int qttNotaCinco;
    private int qttNotaDois;
    private int qttMoedaUm;
    private int qttMoedaCinquenta;
    private int qttMoedaVinteCinco;
    private int qttMoedaDez;
    private int qttMoedaCinco;
    private Double valorSaldo;
    private int aberturaCaixa_idaberturaCaixa;

    public SaldoCaixa() {
    }

    public SaldoCaixa(int idsaldoCaixa, int qttNotaDuzentos,
            int qttNotaCem, int qttNotaVinte, int qttNotaCinquenta,
            int qttNotaDez, int qttNotaCinco, int qttNotaDois,
            int qttMoedaUm, int qttMoedaCinquenta,
            int qttMoedaVinteCinco, int qttMoedaDez,
            int qttMoedaCinco, Double valorSaldo,
            int aberturaCaixa_idaberturaCaixa) {

        this.idsaldoCaixa = idsaldoCaixa;
        this.qttNotaDuzentos = qttNotaDuzentos;
        this.qttNotaCem = qttNotaCem;
        this.qttNotaVinte = qttNotaVinte;
        this.qttNotaCinquenta = qttNotaCinquenta;
        this.qttNotaDez = qttNotaDez;
        this.qttNotaCinco = qttNotaCinco;
        this.qttNotaDois = qttNotaDois;
        this.qttMoedaUm = qttMoedaUm;
        this.qttMoedaCinquenta = qttMoedaCinquenta;
        this.qttMoedaVinteCinco = qttMoedaVinteCinco;
        this.qttMoedaDez = qttMoedaDez;
        this.qttMoedaCinco = qttMoedaCinco;
        this.valorSaldo = valorSaldo;
        this.aberturaCaixa_idaberturaCaixa = aberturaCaixa_idaberturaCaixa;
    }

    public int getIdsaldoCaixa() {
        return idsaldoCaixa;
    }

    public void setIdsaldoCaixa(int idsaldoCaixa) {
        this.idsaldoCaixa = idsaldoCaixa;
    }

    public int getQttNotaDuzentos() {
        return qttNotaDuzentos;
    }

    public void setQttNotaDuzentos(int qttNotaDuzentos) {
        this.qttNotaDuzentos = qttNotaDuzentos;
    }

    public int getQttNotaCem() {
        return qttNotaCem;
    }

    public void setQttNotaCem(int qttNotaCem) {
        this.qttNotaCem = qttNotaCem;
    }

    public int getQttNotaVinte() {
        return qttNotaVinte;
    }

    public void setQttNotaVinte(int qttNotaVinte) {
        this.qttNotaVinte = qttNotaVinte;
    }

    public int getQttNotaCinquenta() {
        return qttNotaCinquenta;
    }

    public void setQttNotaCinquenta(int qttNotaCinquenta) {
        this.qttNotaCinquenta = qttNotaCinquenta;
    }

    public int getQttNotaDez() {
        return qttNotaDez;
    }

    public void setQttNotaDez(int qttNotaDez) {
        this.qttNotaDez = qttNotaDez;
    }

    public int getQttNotaCinco() {
        return qttNotaCinco;
    }

    public void setQttNotaCinco(int qttNotaCinco) {
        this.qttNotaCinco = qttNotaCinco;
    }

    public int getQttNotaDois() {
        return qttNotaDois;
    }

    public void setQttNotaDois(int qttNotaDois) {
        this.qttNotaDois = qttNotaDois;
    }

    public int getQttMoedaUm() {
        return qttMoedaUm;
    }

    public void setQttMoedaUm(int qttMoedaUm) {
        this.qttMoedaUm = qttMoedaUm;
    }

    public int getQttMoedaCinquenta() {
        return qttMoedaCinquenta;
    }

    public void setQttMoedaCinquenta(int qttMoedaCinquenta) {
        this.qttMoedaCinquenta = qttMoedaCinquenta;
    }

    public int getQttMoedaVinteCinco() {
        return qttMoedaVinteCinco;
    }

    public void setQttMoedaVinteCinco(int qttMoedaVinteCinco) {
        this.qttMoedaVinteCinco = qttMoedaVinteCinco;
    }

    public int getQttMoedaDez() {
        return qttMoedaDez;
    }

    public void setQttMoedaDez(int qttMoedaDez) {
        this.qttMoedaDez = qttMoedaDez;
    }

    public int getQttMoedaCinco() {
        return qttMoedaCinco;
    }

    public void setQttMoedaCinco(int qttMoedaCinco) {
        this.qttMoedaCinco = qttMoedaCinco;
    }

    public Double getValorSaldo() {
        return valorSaldo;
    }

    public void setValorSaldo(Double valorSaldo) {
        this.valorSaldo = valorSaldo;
    }

    public int getAberturaCaixa_idaberturaCaixa() {
        return aberturaCaixa_idaberturaCaixa;
    }

    public void setAberturaCaixa_idaberturaCaixa(int aberturaCaixa_idaberturaCaixa) {
        this.aberturaCaixa_idaberturaCaixa = aberturaCaixa_idaberturaCaixa;
    }
}
