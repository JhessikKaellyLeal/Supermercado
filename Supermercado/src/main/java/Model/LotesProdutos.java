/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author AntônioVieira
 */
public class LotesProdutos {
  private int  idlotesProdutos, quantidadeLote,  produtos_idprodutos, produtos_categorias_idcategorias, produtos_marcas_idmarcas, produtos_unidadeMedidas_idunidadeMedidas;
  private String  numeroLote, datafabricacao,  dataVencimento;

    public LotesProdutos() {
    }

    public int getIdlotesProdutos() {
        return idlotesProdutos;
    }

    public void setIdlotesProdutos(int idlotesProdutos) {
        this.idlotesProdutos = idlotesProdutos;
    }

    public int getQuantidadeLote() {
        return quantidadeLote;
    }

    public void setQuantidadeLote(int quantidadeLote) {
        this.quantidadeLote = quantidadeLote;
    }

    public int getProdutos_idprodutos() {
        return produtos_idprodutos;
    }

    public void setProdutos_idprodutos(int produtos_idprodutos) {
        this.produtos_idprodutos = produtos_idprodutos;
    }

    public int getProdutos_categorias_idcategorias() {
        return produtos_categorias_idcategorias;
    }

    public void setProdutos_categorias_idcategorias(int produtos_categorias_idcategorias) {
        this.produtos_categorias_idcategorias = produtos_categorias_idcategorias;
    }

    public int getProdutos_marcas_idmarcas() {
        return produtos_marcas_idmarcas;
    }

    public void setProdutos_marcas_idmarcas(int produtos_marcas_idmarcas) {
        this.produtos_marcas_idmarcas = produtos_marcas_idmarcas;
    }

    public int getProdutos_unidadeMedidas_idunidadeMedidas() {
        return produtos_unidadeMedidas_idunidadeMedidas;
    }

    public void setProdutos_unidadeMedidas_idunidadeMedidas(int produtos_unidadeMedidas_idunidadeMedidas) {
        this.produtos_unidadeMedidas_idunidadeMedidas = produtos_unidadeMedidas_idunidadeMedidas;
    }

    public String getNumeroLote() {
        return numeroLote;
    }

    public void setNumeroLote(String numeroLote) {
        this.numeroLote = numeroLote;
    }

    public String getDatafabricacao() {
        return datafabricacao;
    }

    public void setDatafabricacao(String datafabricacao) {
        this.datafabricacao = datafabricacao;
    }

    public String getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(String dataVencimento) {
        this.dataVencimento = dataVencimento;
    }
  
}
