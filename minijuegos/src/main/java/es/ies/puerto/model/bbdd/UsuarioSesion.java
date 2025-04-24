package es.ies.puerto.model.bbdd;

public class UsuarioSesion {
    private static UsuarioSesion instancia;
    private Usuario usuario;

    /**
     * Contructor vacio
     */
    private UsuarioSesion() {
        usuario=new Usuario();
    }

    /**
     * * Constructor con parametros
     * @return instancia de la clase
     */
    public static UsuarioSesion getInstancia() {
        if (instancia == null) {
            instancia = new UsuarioSesion();
        }
        return instancia;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * * Metodo que cierra la sesion del usuario
     */
    public void cerrarSesion() {
        usuario = null;
    }
}