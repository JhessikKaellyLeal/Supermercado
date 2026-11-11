
package Model;

public class PerdasEstoque {

    private int idperdasEstoque;
    private int quantidade;
    private String dataRegistro;
    private String motivo;
    private int produtos_idprodutos;
    private int produtos_categorias_idcategorias;
    private int produtos_marcas_idmarcas;
    private int produtos_unidadeMedidas_idunidadeMedidas;
    private int lotesProdutos_idlotesProdutos;

    public PerdasEstoque() {
    }

    public PerdasEstoque(int idperdasEstoque, int quantidade,
            String dataRegistro, String motivo,
            int produtos_idprodutos,
            int produtos_categorias_idcategorias,
            int produtos_marcas_idmarcas,
            int produtos_unidadeMedidas_idunidadeMedidas,
            int lotesProdutos_idlotesProdutos) {

        this.idperdasEstoque = idperdasEstoque;
        this.quantidade = quantidade;
        this.dataRegistro = dataRegistro;
        this.motivo = motivo;
        this.produtos_idprodutos = produtos_idprodutos;
        this.produtos_categorias_idcategorias = produtos_categorias_idcategorias;
        this.produtos_marcas_idmarcas = produtos_marcas_idmarcas;
        this.produtos_unidadeMedidas_idunidadeMedidas = produtos_unidadeMedidas_idunidadeMedidas;
        this.lotesProdutos_idlotesProdutos = lotesProdutos_idlotesProdutos;
    }

    public int getIdperdasEstoque() {
        return idperdasEstoque;
    }

    public void setIdperdasEstoque(int idperdasEstoque) {
        this.idperdasEstoque = idperdasEstoque;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(String dataRegistro) {
        this.dataRegistro = dataRegistro;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
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

    public int getLotesProdutos_idlotesProdutos() {
        return lotesProdutos_idlotesProdutos;
    }

    public void setLotesProdutos_idlotesProdutos(int lotesProdutos_idlotesProdutos) {
        this.lotesProdutos_idlotesProdutos = lotesProdutos_idlotesProdutos;
    }
}
