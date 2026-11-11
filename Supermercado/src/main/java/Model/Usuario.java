
package Model;

public class Usuario {

    private int idusuario;
    private String nome;
    private String cpf;
    private String email;
    private String senha;
    private Integer bloqueio;
    private int Funcionario_idFuncionario;

    public Usuario() {
    }

    public Usuario(int idusuario, String nome, String cpf, String email,
                   String senha, Integer bloqueio, int Funcionario_idFuncionario) {
        this.idusuario = idusuario;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.senha = senha;
        this.bloqueio = bloqueio;
        this.Funcionario_idFuncionario = Funcionario_idFuncionario;
    }

    public int getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Integer getBloqueio() {
        return bloqueio;
    }

    public void setBloqueio(Integer bloqueio) {
        this.bloqueio = bloqueio;
    }

    public int getFuncionario_idFuncionario() {
        return Funcionario_idFuncionario;
    }

    public void setFuncionario_idFuncionario(int Funcionario_idFuncionario) {
        this.Funcionario_idFuncionario = Funcionario_idFuncionario;
    }
}
