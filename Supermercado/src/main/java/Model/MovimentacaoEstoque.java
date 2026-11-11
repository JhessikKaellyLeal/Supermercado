
package Model;

public class MovimentacaoEstoque {

    private int idmovimentacaoEstoque;
    private String tipoMovimentacao;
    private int quantidade;
    private String dataMovimentacao;
    private String horaMovimentacao;
    private byte[] documentoReferencia;
    private int produtos_idprodutos;
    private int produtos_categorias_idcategorias;
    private int produtos_marcas_idmarcas;
    private int produtos_unidadeMedidas_idunidadeMedidas;

    public MovimentacaoEstoque() {
    }

    public MovimentacaoEstoque(int idmovimentacaoEstoque,
            String tipoMovimentacao, int quantidade,
            String dataMovimentacao, String horaMovimentacao,
            byte[] documentoReferencia, int produtos_idprodutos,
            int produtos_categorias_idcategorias,
            int produtos_marcas_idmarcas,
            int produtos_unidadeMedidas_idunidadeMedidas) {

        this.idmovimentacaoEstoque = idmovimentacaoEstoque;
        this.tipoMovimentacao = tipoMovimentacao;
        this.quantidade = quantidade;
        this.dataMovimentacao = dataMovimentacao;
        this.horaMovimentacao = horaMovimentacao;
        this.documentoReferencia = documentoReferencia;
        this.produtos_idprodutos = produtos_idprodutos;
        this.produtos_categorias_idcategorias = produtos_categorias_idcategorias;
        this.produtos_marcas_idmarcas = produtos_marcas_idmarcas;
        this.produtos_unidadeMedidas_idunidadeMedidas = produtos_unidadeMedidas_idunidadeMedidas;
    }

    public int getIdmovimentacaoEstoque() {
        return idmovimentacaoEstoque;
    }

    public void setIdmovimentacaoEstoque(int idmovimentacaoEstoque) {
        this.idmovimentacaoEstoque = idmovimentacaoEstoque;
    }

    public String getTipoMovimentacao() {
        return tipoMovimentacao;
    }

    public void setTipoMovimentacao(String tipoMovimentacao) {
        this.tipoMovimentacao = tipoMovimentacao;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getDataMovimentacao() {
        return dataMovimentacao;
    }

    public void setDataMovimentacao(String dataMovimentacao) {
        this.dataMovimentacao = dataMovimentacao;
    }

    public String getHoraMovimentacao() {
        return horaMovimentacao;
    }

    public void setHoraMovimentacao(String horaMovimentacao) {
        this.horaMovimentacao = horaMovimentacao;
    }

    public byte[] getDocumentoReferencia() {
        return documentoReferencia;
    }

    public void setDocumentoReferencia(byte[] documentoReferencia) {
        this.documentoReferencia = documentoReferencia;
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
