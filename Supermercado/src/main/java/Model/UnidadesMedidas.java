
package Model;

public class UnidadesMedidas {

    private int idunidadeMedidas;
    private int codigo;
    private String nome;
    private int status;
    private String descricao;

    public UnidadesMedidas() {
    }

    public UnidadesMedidas(int idunidadeMedidas, int codigo,
            String nome, int status, String descricao) {

        this.idunidadeMedidas = idunidadeMedidas;
        this.codigo = codigo;
        this.nome = nome;
        this.status = status;
        this.descricao = descricao;
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
