
package Model;

public class Etiquetas {

    private int idetiquetas;
    private String tipo;
    private byte[] imagem;

    public Etiquetas() {
    }

    public Etiquetas(int idetiquetas, String tipo, byte[] imagem) {
        this.idetiquetas = idetiquetas;
        this.tipo = tipo;
        this.imagem = imagem;
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

    public byte[] getImagem() {
        return imagem;
    }

    public void setImagem(byte[] imagem) {
        this.imagem = imagem;
    }
}
