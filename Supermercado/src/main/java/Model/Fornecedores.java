
package Model;

public class Fornecedores {

    private int idfornecedores;
    private String nomeResponsavel;
    private String telefone;
    private String email;
    private String ramal;
    private String cnpj;
    private String razaoSocial;
    private String endereco;
    private String tipo;
    private String cep;
    private String inscricaoestadual;

    public Fornecedores() {
    }

    public Fornecedores(int idfornecedores, String nomeResponsavel,
            String telefone, String email, String ramal, String cnpj,
            String razaoSocial, String endereco, String tipo, String cep,
            String inscricaoestadual) {

        this.idfornecedores = idfornecedores;
        this.nomeResponsavel = nomeResponsavel;
        this.telefone = telefone;
        this.email = email;
        this.ramal = ramal;
        this.cnpj = cnpj;
        this.razaoSocial = razaoSocial;
        this.endereco = endereco;
        this.tipo = tipo;
        this.cep = cep;
        this.inscricaoestadual = inscricaoestadual;
    }

    public int getIdfornecedores() {
        return idfornecedores;
    }

    public void setIdfornecedores(int idfornecedores) {
        this.idfornecedores = idfornecedores;
    }

    public String getNomeResponsavel() {
        return nomeResponsavel;
    }

    public void setNomeResponsavel(String nomeResponsavel) {
        this.nomeResponsavel = nomeResponsavel;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRamal() {
        return ramal;
    }

    public void setRamal(String ramal) {
        this.ramal = ramal;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getInscricaoestadual() {
        return inscricaoestadual;
    }

    public void setInscricaoestadual(String inscricaoestadual) {
        this.inscricaoestadual = inscricaoestadual;
    }
}
