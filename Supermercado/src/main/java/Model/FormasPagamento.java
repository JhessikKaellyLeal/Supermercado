
package Model;

public class FormasPagamento {

    private int idformasPagamento;
    private String nome;

    public FormasPagamento() {
    }

    public FormasPagamento(int idformasPagamento, String nome) {
        this.idformasPagamento = idformasPagamento;
        this.nome = nome;
    }

    public int getIdformasPagamento() {
        return idformasPagamento;
    }

    public void setIdformasPagamento(int idformasPagamento) {
        this.idformasPagamento = idformasPagamento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
