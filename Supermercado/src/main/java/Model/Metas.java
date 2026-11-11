
package Model;

public class Metas {

    private int idmetas;
    private String tipo;
    private String periodoInicial;
    private String periodoFinal;
    private double metaPrevista;
    private Double resultadoAtual;
    private Double porcentagemAtingida;
    private String situacao;
    private int funcionario_idFuncionario;

    public Metas() {
    }

    public Metas(int idmetas, String tipo, String periodoInicial,
            String periodoFinal, double metaPrevista,
            Double resultadoAtual, Double porcentagemAtingida,
            String situacao, int funcionario_idFuncionario) {

        this.idmetas = idmetas;
        this.tipo = tipo;
        this.periodoInicial = periodoInicial;
        this.periodoFinal = periodoFinal;
        this.metaPrevista = metaPrevista;
        this.resultadoAtual = resultadoAtual;
        this.porcentagemAtingida = porcentagemAtingida;
        this.situacao = situacao;
        this.funcionario_idFuncionario = funcionario_idFuncionario;
    }

    public int getIdmetas() {
        return idmetas;
    }

    public void setIdmetas(int idmetas) {
        this.idmetas = idmetas;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getPeriodoInicial() {
        return periodoInicial;
    }

    public void setPeriodoInicial(String periodoInicial) {
        this.periodoInicial = periodoInicial;
    }

    public String getPeriodoFinal() {
        return periodoFinal;
    }

    public void setPeriodoFinal(String periodoFinal) {
        this.periodoFinal = periodoFinal;
    }

    public double getMetaPrevista() {
        return metaPrevista;
    }

    public void setMetaPrevista(double metaPrevista) {
        this.metaPrevista = metaPrevista;
    }

    public Double getResultadoAtual() {
        return resultadoAtual;
    }

    public void setResultadoAtual(Double resultadoAtual) {
        this.resultadoAtual = resultadoAtual;
    }

    public Double getPorcentagemAtingida() {
        return porcentagemAtingida;
    }

    public void setPorcentagemAtingida(Double porcentagemAtingida) {
        this.porcentagemAtingida = porcentagemAtingida;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public int getFuncionario_idFuncionario() {
        return funcionario_idFuncionario;
    }

    public void setFuncionario_idFuncionario(int funcionario_idFuncionario) {
        this.funcionario_idFuncionario = funcionario_idFuncionario;
    }
}
