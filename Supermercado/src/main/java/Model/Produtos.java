
package Model;

public class Produtos {

    private int idprodutos;
    private String nome;
    private String codigo;
    private String descricao;
    private double precoCusto;
    private double precoVenda;
    private int situacao;
    private int estoque;
    private String validade;
    private byte[] imagem;
    private int categorias_idcategorias;
    private int marcas_idmarcas;
    private int unidadeMedidas_idunidadeMedidas;

    public Produtos() {
    }

    public Produtos(int idprodutos, String nome, String codigo,
            String descricao, double precoCusto, double precoVenda,
            int situacao, int estoque, String validade, byte[] imagem,
            int categorias_idcategorias, int marcas_idmarcas,
            int unidadeMedidas_idunidadeMedidas) {

        this.idprodutos = idprodutos;
        this.nome = nome;
        this.codigo = codigo;
        this.descricao = descricao;
        this.precoCusto = precoCusto;
        this.precoVenda = precoVenda;
        this.situacao = situacao;
        this.estoque = estoque;
        this.validade = validade;
        this.imagem = imagem;
        this.categorias_idcategorias = categorias_idcategorias;
        this.marcas_idmarcas = marcas_idmarcas;
        this.unidadeMedidas_idunidadeMedidas = unidadeMedidas_idunidadeMedidas;
    }

    public int getIdprodutos() {
        return idprodutos;
    }

    public void setIdprodutos(int idprodutos) {
        this.idprodutos = idprodutos;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPrecoCusto() {
        return precoCusto;
    }

    public void setPrecoCusto(double precoCusto) {
        this.precoCusto = precoCusto;
    }

    public double getPrecoVenda() {
        return precoVenda;
    }

    public void setPrecoVenda(double precoVenda) {
        this.precoVenda = precoVenda;
    }

    public int getSituacao() {
        return situacao;
    }

    public void setSituacao(int situacao) {
        this.situacao = situacao;
    }

    public int getEstoque() {
        return estoque;
    }

    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }

    public String getValidade() {
        return validade;
    }

    public void setValidade(String validade) {
        this.validade = validade;
    }

    public byte[] getImagem() {
        return imagem;
    }

    public void setImagem(byte[] imagem) {
        this.imagem = imagem;
    }

    public int getCategorias_idcategorias() {
        return categorias_idcategorias;
    }

    public void setCategorias_idcategorias(int categorias_idcategorias) {
        this.categorias_idcategorias = categorias_idcategorias;
    }

    public int getMarcas_idmarcas() {
        return marcas_idmarcas;
    }

    public void setMarcas_idmarcas(int marcas_idmarcas) {
        this.marcas_idmarcas = marcas_idmarcas;
    }

    public int getUnidadeMedidas_idunidadeMedidas() {
        return unidadeMedidas_idunidadeMedidas;
    }

    public void setUnidadeMedidas_idunidadeMedidas(int unidadeMedidas_idunidadeMedidas) {
        this.unidadeMedidas_idunidadeMedidas = unidadeMedidas_idunidadeMedidas;
    }
}
