
package Model;

public class Ferias {

    private int idferias;
    private String inicio;
    private String finalFerias;
    private String situacao;

    public Ferias() {
    }

    public Ferias(int idferias, String inicio, String finalFerias,
            String situacao) {
        this.idferias = idferias;
        this.inicio = inicio;
        this.finalFerias = finalFerias;
        this.situacao = situacao;
    }

    public int getIdferias() {
        return idferias;
    }

    public void setIdferias(int idferias) {
        this.idferias = idferias;
    }

    public String getInicio() {
        return inicio;
    }

    public void setInicio(String inicio) {
        this.inicio = inicio;
    }

    public String getFinalFerias() {
        return finalFerias;
    }

    public void setFinalFerias(String finalFerias) {
        this.finalFerias = finalFerias;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }
}
