
package Model;

public class DiasTrabalhados {

    private int iddiasTrabalhados;
    private String nome;

    public DiasTrabalhados() {
    }

    public DiasTrabalhados(int iddiasTrabalhados, String nome) {
        this.iddiasTrabalhados = iddiasTrabalhados;
        this.nome = nome;
    }

    public int getIddiasTrabalhados() {
        return iddiasTrabalhados;
    }

    public void setIddiasTrabalhados(int iddiasTrabalhados) {
        this.iddiasTrabalhados = iddiasTrabalhados;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
