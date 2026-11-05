package Model;

public class Categorias {

    private int idcategorias;
    private String nome;
    private String descricao;
    private boolean status;

    public Categorias() {
    }

    public Categorias(int idcategorias, String nome, String descricao, boolean status) {
        this.idcategorias = idcategorias;
        this.nome = nome;
        this.descricao = descricao;
        this.status = status;
    }

    public int getIdcategorias() {
        return idcategorias;
    }

    public void setIdcategorias(int idcategorias) {
        this.idcategorias = idcategorias;
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

    @Override
    public String toString() {
        return nome;
    }
}