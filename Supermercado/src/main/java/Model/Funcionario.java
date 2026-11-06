
package Model;

public class Funcionario {

    private int idFuncionario;
    private String nome;
    private String dataNascimento;
    private String cpf;
    private String telefone;
    private String dataAdmissao;
    private String statusFuncionario;

    private int cargos_idcargos;
    private int setores_idsetores;
    private int turnos_idturnos;

    public Funcionario() {
    }

    public Funcionario(int idFuncionario, String nome,
            String dataNascimento, String cpf,
            String telefone, String dataAdmissao,
            String statusFuncionario, int cargos_idcargos,
            int setores_idsetores, int turnos_idturnos) {

        this.idFuncionario = idFuncionario;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.cpf = cpf;
        this.telefone = telefone;
        this.dataAdmissao = dataAdmissao;
        this.statusFuncionario = statusFuncionario;
        this.cargos_idcargos = cargos_idcargos;
        this.setores_idsetores = setores_idsetores;
        this.turnos_idturnos = turnos_idturnos;
    }

    public int getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(int idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getDataAdmissao() {
        return dataAdmissao;
    }

    public void setDataAdmissao(String dataAdmissao) {
        this.dataAdmissao = dataAdmissao;
    }

    public String getStatusFuncionario() {
        return statusFuncionario;
    }

    public void setStatusFuncionario(String statusFuncionario) {
        this.statusFuncionario = statusFuncionario;
    }

    public int getCargos_idcargos() {
        return cargos_idcargos;
    }

    public void setCargos_idcargos(int cargos_idcargos) {
        this.cargos_idcargos = cargos_idcargos;
    }

    public int getSetores_idsetores() {
        return setores_idsetores;
    }

    public void setSetores_idsetores(int setores_idsetores) {
        this.setores_idsetores = setores_idsetores;
    }

    public int getTurnos_idturnos() {
        return turnos_idturnos;
    }

    public void setTurnos_idturnos(int turnos_idturnos) {
        this.turnos_idturnos = turnos_idturnos;
    }

    @Override
    public String toString() {
        return nome;
    }
}
