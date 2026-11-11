
package Model;

public class Promocao {

    private int idpromocao;
    private String nome;
    private String tipo;
    private int status;
    private String dataInicio;
    private String dataFinal;
    private String regras;
    private int desconto;
    private int qttMinima;

    public Promocao() {
    }

    public Promocao(int idpromocao, String nome, String tipo,
            int status, String dataInicio, String dataFinal,
            String regras, int desconto, int qttMinima) {

        this.idpromocao = idpromocao;
        this.nome = nome;
        this.tipo = tipo;
        this.status = status;
        this.dataInicio = dataInicio;
        this.dataFinal = dataFinal;
        this.regras = regras;
        this.desconto = desconto;
        this.qttMinima = qttMinima;
    }

    public int getIdpromocao() {
        return idpromocao;
    }

    public void setIdpromocao(int idpromocao) {
        this.idpromocao = idpromocao;
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

    public String getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
    }

    public String getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(String dataFinal) {
        this.dataFinal = dataFinal;
    }

    public String getRegras() {
        return regras;
    }

    public void setRegras(String regras) {
        this.regras = regras;
    }

    public int getDesconto() {
        return desconto;
    }

    public void setDesconto(int desconto) {
        this.desconto = desconto;
    }

    public int getQttMinima() {
        return qttMinima;
    }

    public void setQttMinima(int qttMinima) {
        this.qttMinima = qttMinima;
    }
}
