
package Model;

public class ItensPedidos {

    private int iditensPedidos;
    private int quantidade;
    private double precoUnitario;
    private double desconto;
    private int pedidos_idpedidos;
    private int produtos_idprodutos;
    private int produtos_categorias_idcategorias;
    private int produtos_marcas_idmarcas;
    private int produtos_unidadeMedidas_idunidadeMedidas;

    public ItensPedidos() {
    }

    public ItensPedidos(int iditensPedidos, int quantidade,
            double precoUnitario, double desconto,
            int pedidos_idpedidos, int produtos_idprodutos,
            int produtos_categorias_idcategorias,
            int produtos_marcas_idmarcas,
            int produtos_unidadeMedidas_idunidadeMedidas) {

        this.iditensPedidos = iditensPedidos;
        this.quantidade = quantidade;
        this.precoUnitario = precoUnitario;
        this.desconto = desconto;
        this.pedidos_idpedidos = pedidos_idpedidos;
        this.produtos_idprodutos = produtos_idprodutos;
        this.produtos_categorias_idcategorias = produtos_categorias_idcategorias;
        this.produtos_marcas_idmarcas = produtos_marcas_idmarcas;
        this.produtos_unidadeMedidas_idunidadeMedidas = produtos_unidadeMedidas_idunidadeMedidas;
    }

    public int getIditensPedidos() {
        return iditensPedidos;
    }

    public void setIditensPedidos(int iditensPedidos) {
        this.iditensPedidos = iditensPedidos;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(double precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public double getDesconto() {
        return desconto;
    }

    public void setDesconto(double desconto) {
        this.desconto = desconto;
    }

    public int getPedidos_idpedidos() {
        return pedidos_idpedidos;
    }

    public void setPedidos_idpedidos(int pedidos_idpedidos) {
        this.pedidos_idpedidos = pedidos_idpedidos;
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
