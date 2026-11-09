/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;


public class Usuario {
 

    private int idusuario;
    private String nome;
    private String cpf;
    private String email;
    private String senha;
    private int bloqueio;
    private int funcionario_idFuncionario;

    public Usuario() {
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

    public int getBloqueio() {
        return bloqueio;
    }

    public void setBloqueio(int bloqueio) {
        this.bloqueio = bloqueio;
    }

    public int getFuncionario_idFuncionario() {
    return funcionario_idFuncionario;
}

public void setFuncionario_idFuncionario(int funcionario_idFuncionario) {
    this.funcionario_idFuncionario = funcionario_idFuncionario;
}

    @Override
    public String toString() {
        return nome;
    }
}


   
   

   