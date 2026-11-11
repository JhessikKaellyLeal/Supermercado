
package Model;

public class Pedidos {

    private int idpedidos;
    private String numeroPedido;
    private String datapedido;
    private String statusPedido;
    private String observacao;
    private int cliente_idcliente;
    private int usuario_idusuario;
    private int entregas_identregas;

    public Pedidos() {
    }

    public Pedidos(int idpedidos, String numeroPedido, String datapedido,
            String statusPedido, String observacao,
            int cliente_idcliente, int usuario_idusuario,
            int entregas_identregas) {

        this.idpedidos = idpedidos;
        this.numeroPedido = numeroPedido;
        this.datapedido = datapedido;
        this.statusPedido = statusPedido;
        this.observacao = observacao;
        this.cliente_idcliente = cliente_idcliente;
        this.usuario_idusuario = usuario_idusuario;
        this.entregas_identregas = entregas_identregas;
    }

    public int getIdpedidos() {
        return idpedidos;
    }

    public void setIdpedidos(int idpedidos) {
        this.idpedidos = idpedidos;
    }

    public String getNumeroPedido() {
        return numeroPedido;
    }

    public void setNumeroPedido(String numeroPedido) {
        this.numeroPedido = numeroPedido;
    }

    public String getDatapedido() {
        return datapedido;
    }

    public void setDatapedido(String datapedido) {
        this.datapedido = datapedido;
    }

    public String getStatusPedido() {
        return statusPedido;
    }

    public void setStatusPedido(String statusPedido) {
        this.statusPedido = statusPedido;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
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

    public int getEntregas_identregas() {
        return entregas_identregas;
    }

    public void setEntregas_identregas(int entregas_identregas) {
        this.entregas_identregas = entregas_identregas;
    }
}
