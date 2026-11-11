
package Model;

public class ContasFinanceiras {

    private int idcontasFinanceiras;
    private String nome;
    private String tipo;
    private String agencia;
    private String numeroConta;
    private String banco;
    private int ativo;

    public ContasFinanceiras() {
    }

    public ContasFinanceiras(int idcontasFinanceiras, String nome, String tipo,
            String agencia, String numeroConta, String banco, int ativo) {
        this.idcontasFinanceiras = idcontasFinanceiras;
        this.nome = nome;
        this.tipo = tipo;
        this.agencia = agencia;
        this.numeroConta = numeroConta;
        this.banco = banco;
        this.ativo = ativo;
    }

    public int getIdcontasFinanceiras() {
        return idcontasFinanceiras;
    }

    public void setIdcontasFinanceiras(int idcontasFinanceiras) {
        this.idcontasFinanceiras = idcontasFinanceiras;
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

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public String getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(String numeroConta) {
        this.numeroConta = numeroConta;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public int getAtivo() {
        return ativo;
    }

    public void setAtivo(int ativo) {
        this.ativo = ativo;
    }
}
