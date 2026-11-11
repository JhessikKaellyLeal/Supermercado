
package Model;

public class MovimentacoesFinanceiras {

    private int idmovimentacoesFinanceiras;
    private String tipo;
    private double valor;
    private String dataHora;
    private String descricao;
    private String origem;
    private int contasFinanceiras_idcontasFinanceiras;

    public MovimentacoesFinanceiras() {
    }

    public MovimentacoesFinanceiras(int idmovimentacoesFinanceiras,
            String tipo, double valor, String dataHora,
            String descricao, String origem,
            int contasFinanceiras_idcontasFinanceiras) {

        this.idmovimentacoesFinanceiras = idmovimentacoesFinanceiras;
        this.tipo = tipo;
        this.valor = valor;
        this.dataHora = dataHora;
        this.descricao = descricao;
        this.origem = origem;
        this.contasFinanceiras_idcontasFinanceiras = contasFinanceiras_idcontasFinanceiras;
    }

    public int getIdmovimentacoesFinanceiras() {
        return idmovimentacoesFinanceiras;
    }

    public void setIdmovimentacoesFinanceiras(int idmovimentacoesFinanceiras) {
        this.idmovimentacoesFinanceiras = idmovimentacoesFinanceiras;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getDataHora() {
        return dataHora;
    }

    public void setDataHora(String dataHora) {
        this.dataHora = dataHora;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public int getContasFinanceiras_idcontasFinanceiras() {
        return contasFinanceiras_idcontasFinanceiras;
    }

    public void setContasFinanceiras_idcontasFinanceiras(int contasFinanceiras_idcontasFinanceiras) {
        this.contasFinanceiras_idcontasFinanceiras = contasFinanceiras_idcontasFinanceiras;
    }
}
