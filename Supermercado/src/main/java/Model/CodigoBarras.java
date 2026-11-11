package Model;

public class CodigoBarras {

    private int idcodigoBarras;
    private String codigo;
    private byte[] imagem;
    private String tipo;
    private int status;
    private String descricao;
    private int produtos_idprodutos;
    private int produtos_categorias_idcategorias;
    private int produtos_marcas_idmarcas;
    private int produtos_unidadeMedidas_idunidadeMedidas;

    public CodigoBarras() {
    }

    public CodigoBarras(int idcodigoBarras, String codigo, byte[] imagem,
            String tipo, int status, String descricao,
            int produtos_idprodutos,
            int produtos_categorias_idcategorias,
            int produtos_marcas_idmarcas,
            int produtos_unidadeMedidas_idunidadeMedidas) {

        this.idcodigoBarras = idcodigoBarras;
        this.codigo = codigo;
        this.imagem = imagem;
        this.tipo = tipo;
        this.status = status;
        this.descricao = descricao;
        this.produtos_idprodutos = produtos_idprodutos;
        this.produtos_categorias_idcategorias = produtos_categorias_idcategorias;
        this.produtos_marcas_idmarcas = produtos_marcas_idmarcas;
        this.produtos_unidadeMedidas_idunidadeMedidas = produtos_unidadeMedidas_idunidadeMedidas;
    }

    public int getIdcodigoBarras() {
        return idcodigoBarras;
    }

    public void setIdcodigoBarras(int idcodigoBarras) {
        this.idcodigoBarras = idcodigoBarras;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public byte[] getImagem() {
        return imagem;
    }

    public void setImagem(byte[] imagem) {
        this.imagem = imagem;
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getProdutos_idprodutos() {
        return produtos_idprodutos;
    }

    public void setProdutos_idprodutos(int produtos_idprodutos) {
        this.produtos_idprodutos = produtos_idprodutos;
    }

    public int getProdutos_categorias_idcategorias() {
        return produtos_categorias_idcategorias;
    }

    public void setProdutos_categorias_idcategorias(int produtos_categorias_idcategorias) {
        this.produtos_categorias_idcategorias = produtos_categorias_idcategorias;
    }

    public int getProdutos_marcas_idmarcas() {
        return produtos_marcas_idmarcas;
    }

    public void setProdutos_marcas_idmarcas(int produtos_marcas_idmarcas) {
        this.produtos_marcas_idmarcas = produtos_marcas_idmarcas;
    }

    public int getProdutos_unidadeMedidas_idunidadeMedidas() {
        return produtos_unidadeMedidas_idunidadeMedidas;
    }

    public void setProdutos_unidadeMedidas_idunidadeMedidas(int produtos_unidadeMedidas_idunidadeMedidas) {
        this.produtos_unidadeMedidas_idunidadeMedidas = produtos_unidadeMedidas_idunidadeMedidas;
    }
}