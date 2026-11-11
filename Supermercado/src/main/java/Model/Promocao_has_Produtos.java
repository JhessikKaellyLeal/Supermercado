
package Model;

public class Promocao_has_Produtos {

    private int promocao_idpromocao;
    private int produtos_idprodutos;
    private int produtos_categorias_idcategorias;
    private int produtos_marcas_idmarcas;
    private int produtos_unidadeMedidas_idunidadeMedidas;

    public Promocao_has_Produtos() {
    }

    public Promocao_has_Produtos(int promocao_idpromocao,
            int produtos_idprodutos,
            int produtos_categorias_idcategorias,
            int produtos_marcas_idmarcas,
            int produtos_unidadeMedidas_idunidadeMedidas) {

        this.promocao_idpromocao = promocao_idpromocao;
        this.produtos_idprodutos = produtos_idprodutos;
        this.produtos_categorias_idcategorias = produtos_categorias_idcategorias;
        this.produtos_marcas_idmarcas = produtos_marcas_idmarcas;
        this.produtos_unidadeMedidas_idunidadeMedidas = produtos_unidadeMedidas_idunidadeMedidas;
    }

    public int getPromocao_idpromocao() {
        return promocao_idpromocao;
    }

    public void setPromocao_idpromocao(int promocao_idpromocao) {
        this.promocao_idpromocao = promocao_idpromocao;
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
