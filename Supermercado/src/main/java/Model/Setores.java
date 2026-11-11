
package Model;

public class Setores {

    private int idsetores;
    private String nome;
    private String descricao;
    private int status;

    public Setores() {
    }

    public Setores(int idsetores, String nome, String descricao, int status) {
        this.idsetores = idsetores;
        this.nome = nome;
        this.descricao = descricao;
        this.status = status;
    }

    public int getIdsetores() {
        return idsetores;
    }

    public void setIdsetores(int idsetores) {
        this.idsetores = idsetores;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
