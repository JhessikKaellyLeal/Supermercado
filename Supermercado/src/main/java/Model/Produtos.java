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
public class Produtos {
   private int  idprodutos, estoque, categorias_idcategorias, marcas_idmarcas, unidadeMedidas_idunidadeMedidas;
private String nome, codigo, descricao, validade;
private double precoCusto,  precoVenda;
private boolean situacao;
private File imagem;

    public Produtos() {
    }

    public int getIdprodutos() {
        return idprodutos;
    }

    public void setIdprodutos(int idprodutos) {
        this.idprodutos = idprodutos;
    }

    public int getEstoque() {
        return estoque;
    }

    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }

    public int getCategorias_idcategorias() {
        return categorias_idcategorias;
    }

    public void setCategorias_idcategorias(int categorias_idcategorias) {
        this.categorias_idcategorias = categorias_idcategorias;
    }

    public int getMarcas_idmarcas() {
        return marcas_idmarcas;
    }

    public void setMarcas_idmarcas(int marcas_idmarcas) {
        this.marcas_idmarcas = marcas_idmarcas;
    }

    public int getUnidadeMedidas_idunidadeMedidas() {
        return unidadeMedidas_idunidadeMedidas;
    }

    public void setUnidadeMedidas_idunidadeMedidas(int unidadeMedidas_idunidadeMedidas) {
        this.unidadeMedidas_idunidadeMedidas = unidadeMedidas_idunidadeMedidas;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getValidade() {
        return validade;
    }

    public void setValidade(String validade) {
        this.validade = validade;
    }

    public double getPrecoCusto() {
        return precoCusto;
    }

    public void setPrecoCusto(double precoCusto) {
        this.precoCusto = precoCusto;
    }

    public double getPrecoVenda() {
        return precoVenda;
    }

    public void setPrecoVenda(double precoVenda) {
        this.precoVenda = precoVenda;
    }

    public boolean isSituacao() {
        return situacao;
    }

    public void setSituacao(boolean situacao) {
        this.situacao = situacao;
    }

    public File getImagem() {
        return imagem;
    }

    public void setImagem(File imagem) {
        this.imagem = imagem;
    }

}
