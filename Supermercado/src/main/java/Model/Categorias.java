
package Model;

public class Categorias {

    private int idcategorias;
    private String nome;
    private String descricao;
    private int status;

    public Categorias() {
    }

    public Categorias(int idcategorias, String nome,
            String descricao, int status) {

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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
