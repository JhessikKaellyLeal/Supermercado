
package Model;

public class Usuario_has_Permissoes {

    private int usuario_idusuario;
    private int permissoes_idpermissoes;

    public Usuario_has_Permissoes() {
    }

    public Usuario_has_Permissoes(int usuario_idusuario, int permissoes_idpermissoes) {
        this.usuario_idusuario = usuario_idusuario;
        this.permissoes_idpermissoes = permissoes_idpermissoes;
    }

    public int getUsuario_idusuario() {
        return usuario_idusuario;
    }

    public void setUsuario_idusuario(int usuario_idusuario) {
        this.usuario_idusuario = usuario_idusuario;
    }

    public int getPermissoes_idpermissoes() {
        return permissoes_idpermissoes;
    }

    public void setPermissoes_idpermissoes(int permissoes_idpermissoes) {
        this.permissoes_idpermissoes = permissoes_idpermissoes;
    }
}
