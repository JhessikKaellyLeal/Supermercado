
package Model;

public class Vendas {

    private int idvendas;
    private String dataVenda;
    private String horarioVenda;
    private double valorTotal;
    private double valorPago;
    private double troco;
    private int quantidadeItens;
    private String statusVenda;
    private int cliente_idcliente;
    private int usuario_idusuario;
    private int caixas_idcaixas;
    private int cupom_idcupom;
    private String nomeCliente;
private String nomeUsuario;
private String nomeCaixa;
private String codigoCupom;

    public Vendas() {
    }

    public Vendas(int idvendas, String dataVenda, String horarioVenda,
                  double valorTotal, double valorPago, double troco,
                  int quantidadeItens, String statusVenda,
                  int cliente_idcliente, int usuario_idusuario,
                  int caixas_idcaixas, int cupom_idcupom) {
        this.idvendas = idvendas;
        this.dataVenda = dataVenda;
        this.horarioVenda = horarioVenda;
        this.valorTotal = valorTotal;
        this.valorPago = valorPago;
        this.troco = troco;
        this.quantidadeItens = quantidadeItens;
        this.statusVenda = statusVenda;
        this.cliente_idcliente = cliente_idcliente;
        this.usuario_idusuario = usuario_idusuario;
        this.caixas_idcaixas = caixas_idcaixas;
        this.cupom_idcupom = cupom_idcupom;
    }
    public String getNomeCliente() {
    return nomeCliente;
}

public void setNomeCliente(String nomeCliente) {
    this.nomeCliente = nomeCliente;
}

public String getNomeUsuario() {
    return nomeUsuario;
}

public void setNomeUsuario(String nomeUsuario) {
    this.nomeUsuario = nomeUsuario;
}

public String getNomeCaixa() {
    return nomeCaixa;
}

public void setNomeCaixa(String nomeCaixa) {
    this.nomeCaixa = nomeCaixa;
}

public String getCodigoCupom() {
    return codigoCupom;
}

public void setCodigoCupom(String codigoCupom) {
    this.codigoCupom = codigoCupom;
}

    public int getIdvendas() {
        return idvendas;
    }

    public void setIdvendas(int idvendas) {
        this.idvendas = idvendas;
    }

    public String getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(String dataVenda) {
        this.dataVenda = dataVenda;
    }

    public String getHorarioVenda() {
        return horarioVenda;
    }

    public void setHorarioVenda(String horarioVenda) {
        this.horarioVenda = horarioVenda;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public double getValorPago() {
        return valorPago;
    }

    public void setValorPago(double valorPago) {
        this.valorPago = valorPago;
    }

    public double getTroco() {
        return troco;
    }

    public void setTroco(double troco) {
        this.troco = troco;
    }

    public int getQuantidadeItens() {
        return quantidadeItens;
    }

    public void setQuantidadeItens(int quantidadeItens) {
        this.quantidadeItens = quantidadeItens;
    }

    public String getStatusVenda() {
        return statusVenda;
    }

    public void setStatusVenda(String statusVenda) {
        this.statusVenda = statusVenda;
    }

    public int getCliente_idcliente() {
        return cliente_idcliente;
    }

    public void setCliente_idcliente(int cliente_idcliente) {
        this.cliente_idcliente = cliente_idcliente;
    }

    public int getUsuario_idusuario() {
        return usuario_idusuario;
    }

    public void setUsuario_idusuario(int usuario_idusuario) {
        this.usuario_idusuario = usuario_idusuario;
    }

    public int getCaixas_idcaixas() {
        return caixas_idcaixas;
    }

    public void setCaixas_idcaixas(int caixas_idcaixas) {
        this.caixas_idcaixas = caixas_idcaixas;
    }

    public int getCupom_idcupom() {
        return cupom_idcupom;
    }

    public void setCupom_idcupom(int cupom_idcupom) {
        this.cupom_idcupom = cupom_idcupom;
    }
}
