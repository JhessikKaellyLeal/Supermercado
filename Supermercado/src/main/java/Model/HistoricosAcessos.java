/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author AntônioVieira
 */
public class HistoricosAcessos {
  private int idhistoricoAcessos, usuario_idusuario;
  private String  dataAcesso, horarioAcesso, terminal_ip, dispositivo;

    public HistoricosAcessos() {
    }

    public int getIdhistoricoAcessos() {
        return idhistoricoAcessos;
    }

    public void setIdhistoricoAcessos(int idhistoricoAcessos) {
        this.idhistoricoAcessos = idhistoricoAcessos;
    }

    public int getUsuario_idusuario() {
        return usuario_idusuario;
    }

    public void setUsuario_idusuario(int usuario_idusuario) {
        this.usuario_idusuario = usuario_idusuario;
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
  
}
