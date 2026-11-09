
package Model;

public class Marcas {

    private int idmarcas;
    private String nome;
    private int status;
    private String descricao;

    public Marcas() {
    }

    public Marcas(int idmarcas, String nome, int status, String descricao) {
        this.idmarcas = idmarcas;
        this.nome = nome;
        this.status = status;
        this.descricao = descricao;
    }

    public int getIdmarcas() {
        return idmarcas;
    }

    public void setIdmarcas(int idmarcas) {
        this.idmarcas = idmarcas;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
