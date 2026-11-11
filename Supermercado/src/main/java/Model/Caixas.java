package Model;

public class Caixas {

    private int idcaixas;
    private String nome;
    private String tipo;
    private int status;
    private String observacao;

    public Caixas() {
    }

    public Caixas(int idcaixas, String nome, String tipo,
            int status, String observacao) {

        this.idcaixas = idcaixas;
        this.nome = nome;
        this.tipo = tipo;
        this.status = status;
        this.observacao = observacao;
    }

    public int getIdcaixas() {
        return idcaixas;
    }

    public void setIdcaixas(int idcaixas) {
        this.idcaixas = idcaixas;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
}