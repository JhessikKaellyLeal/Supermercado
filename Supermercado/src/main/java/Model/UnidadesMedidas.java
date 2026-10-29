/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author AntônioVieira
 */
public class UnidadesMedidas {
 private int  idunidadeMedidas, codigo;
private String nome, descricao;
private boolean status;

    public UnidadesMedidas() {
    }

    public int getIdunidadeMedidas() {
        return idunidadeMedidas;
    }

    public void setIdunidadeMedidas(int idunidadeMedidas) {
        this.idunidadeMedidas = idunidadeMedidas;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

}
