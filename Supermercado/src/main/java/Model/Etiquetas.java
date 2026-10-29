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
public class Etiquetas {
 private int  idetiquetas;
 private String tipo;
 private File imagem;

    public Etiquetas() {
    }

    public int getIdetiquetas() {
        return idetiquetas;
    }

    public void setIdetiquetas(int idetiquetas) {
        this.idetiquetas = idetiquetas;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public File getImagem() {
        return imagem;
    }

    public void setImagem(File imagem) {
        this.imagem = imagem;
    }
 
}
