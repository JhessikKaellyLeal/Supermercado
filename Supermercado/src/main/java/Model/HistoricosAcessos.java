
package Model;

public class HistoricosAcessos {

    private int idhistoricoAcessos;
    private String dataAcesso;
    private String horarioAcesso;
    private String terminal_ip;
    private String dispositivo;
    private int usuario_idusuario;

    public HistoricosAcessos() {
    }

    public HistoricosAcessos(int idhistoricoAcessos, String dataAcesso,
            String horarioAcesso, String terminal_ip, String dispositivo,
            int usuario_idusuario) {

        this.idhistoricoAcessos = idhistoricoAcessos;
        this.dataAcesso = dataAcesso;
        this.horarioAcesso = horarioAcesso;
        this.terminal_ip = terminal_ip;
        this.dispositivo = dispositivo;
        this.usuario_idusuario = usuario_idusuario;
    }

    public int getIdhistoricoAcessos() {
        return idhistoricoAcessos;
    }

    public void setIdhistoricoAcessos(int idhistoricoAcessos) {
        this.idhistoricoAcessos = idhistoricoAcessos;
    }

    public String getDataAcesso() {
        return dataAcesso;
    }

    public void setDataAcesso(String dataAcesso) {
        this.dataAcesso = dataAcesso;
    }

    public String getHorarioAcesso() {
        return horarioAcesso;
    }

    public void setHorarioAcesso(String horarioAcesso) {
        this.horarioAcesso = horarioAcesso;
    }

    public String getTerminal_ip() {
        return terminal_ip;
    }

    public void setTerminal_ip(String terminal_ip) {
        this.terminal_ip = terminal_ip;
    }

    public String getDispositivo() {
        return dispositivo;
    }

    public void setDispositivo(String dispositivo) {
        this.dispositivo = dispositivo;
    }

    public int getUsuario_idusuario() {
        return usuario_idusuario;
    }

    public void setUsuario_idusuario(int usuario_idusuario) {
        this.usuario_idusuario = usuario_idusuario;
    }
}
