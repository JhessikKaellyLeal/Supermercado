/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.io.File;

/**
 *
 * @author AntônioVieira
 */
public class MovimentacaoEstoque {
  private int idmovimentacaoEstoque,  quantidade, produtos_idprodutos, produtos_categorias_idcategorias, produtos_marcas_idmarcas, produtos_unidadeMedidas_idunidadeMedidas;
  private String  tipoMovimentacao,  dataMovimentacao, horaMovimentacao;
  private File documentoReferencia;

    public MovimentacaoEstoque() {
    }

    public int getIdmovimentacaoEstoque() {
        return idmovimentacaoEstoque;
    }

    public void setIdmovimentacaoEstoque(int idmovimentacaoEstoque) {
        this.idmovimentacaoEstoque = idmovimentacaoEstoque;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
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

    public String getTipoMovimentacao() {
        return tipoMovimentacao;
    }

    public void setTipoMovimentacao(String tipoMovimentacao) {
        this.tipoMovimentacao = tipoMovimentacao;
    }

    public String getDataMovimentacao() {
        return dataMovimentacao;
    }

    public void setDataMovimentacao(String dataMovimentacao) {
        this.dataMovimentacao = dataMovimentacao;
    }

    public String getHoraMovimentacao() {
        return horaMovimentacao;
    }

    public void setHoraMovimentacao(String horaMovimentacao) {
        this.horaMovimentacao = horaMovimentacao;
    }

    public File getDocumentoReferencia() {
        return documentoReferencia;
    }

    public void setDocumentoReferencia(File documentoReferencia) {
        this.documentoReferencia = documentoReferencia;
    }
  
}
