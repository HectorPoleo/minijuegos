package es.ies.puerto.model.bbdd;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import es.ies.puerto.model.abtrastas.Conexion;

public class UsuarioServiceModel extends Conexion {

    /**
     * * Constructor vacio
     */
    public UsuarioServiceModel() {
    }
    
    /**
     * * Constructor con path de conexion
     * @param unaRutaArchivoBD ruta de la bbdd
     * @throws SQLException error controlado
     */
    public UsuarioServiceModel(String unaRutaArchivoBD) throws SQLException {
        super(unaRutaArchivoBD);
    }
    
    /**
     * * Funcion que devuelve la conexion a la bbdd
     * @return ArrayList<Usuario> lista de usuarios
     * @throws SQLException error controlado
     */
    public ArrayList<Usuario> obtenerUsuarios() throws SQLException {
        String sql = "SELECT * FROM Usuario";
        return obtenerUsuario(sql);
    }

    public ArrayList<Usuario> obtenerUsuario(String sql) throws SQLException {
        ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
        try {
            PreparedStatement sentencia = getConnection().prepareStatement(sql);
            ResultSet resultado = sentencia.executeQuery();

            while (resultado.next()) {
                String nombreStr = resultado.getString("nombre_usuario");
                String contraseniaStr = resultado.getString("contrasenia");
                String emailStr = resultado.getString("email");
                Usuario usuarioModel = new Usuario(emailStr, nombreStr, contraseniaStr);
                usuarios.add(usuarioModel);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cerrar();
        }
        return usuarios;
    }

    /**
     * * Metodo que obtiene las credenciales del usuario
     * @param dato 
     * @return Usuario usuario con las credenciales
     */
    public Usuario obtenerCredencialesUsuario(String dato) {
        try {
            String sql = "SELECT * FROM usuario WHERE email = ? OR nombre_usuario = ?";
            PreparedStatement stmt = getConnection().prepareStatement(sql);
            stmt.setString(1, dato);
            stmt.setString(2, dato);
            
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Usuario usuario = new Usuario(
                    rs.getString("email"),
                    rs.getString("nombre_usuario"),
                    rs.getString("contrasenia")
                );
            
                return usuario;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                cerrar();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * * Metodo que obtiene las credenciales del usuario
     * @param usuario 
     * @return boolean true si se ha podido agregar el usuario
     * @throws SQLException error controlado
     */
    public boolean agregarUsuario(Usuario usuario) throws SQLException {
        if (usuario == null) {
            return false;
        }
        ArrayList<Usuario> usuarios = obtenerUsuarios();
        String sql = "INSERT  INTO usuario (nombre_usuario,email,contrasenia) VALUES ('" + usuario.getNombre() + "', '"
                + usuario.getEmail() + "', '" + usuario.getPassword() + "')";

        if (usuarios.contains(usuario)) {
            return false;
        }

        try {
            PreparedStatement sentencia = getConnection().prepareStatement(sql);
            sentencia.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cerrar();
        }
        return true;
    }

    /**
     * * Metodo que edita el usuario
     * @param usuario
     * @return boolean true si se ha podido editar el usuario
     * @throws SQLException error controlado
     */
    public boolean editarUsuario(Usuario usuario) throws SQLException {
        if (usuario == null) {
            return false;
        }

        String sql = "UPDATE usuario SET nombre_usuario = '" + usuario.getNombre() + 
                     "', email = '" + usuario.getEmail() + 
                     "', contrasenia = '" + usuario.getPassword() + "'";

        try {
            PreparedStatement sentencia = getConnection().prepareStatement(sql);
            sentencia.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            cerrar();
        }
    }
    
    /**
     * * Metodo que edita el usuario
     * @param usuario 
     * @param emailOriginal 
     * @return boolean true si se ha podido editar el usuario
     * @throws SQLException error controlado
     */
    public boolean editarUsuario(Usuario usuario, String emailOriginal) throws SQLException {
        if (usuario == null || emailOriginal == null || emailOriginal.isEmpty()) {
            return false;
        }

        // Actualizar el registro basado en el email original
        String sql = "UPDATE usuario SET nombre_usuario = '" + usuario.getNombre() + 
                     "', email = '" + usuario.getEmail() + 
                     "', contrasenia = '" + usuario.getPassword() + 
                     "' WHERE email = '" + emailOriginal + "'";

        try {
            PreparedStatement sentencia = getConnection().prepareStatement(sql);
            int filasActualizadas = sentencia.executeUpdate();
            return filasActualizadas > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            cerrar();
        }
    }

    /**
     * * Metodo que elimina el usuario
     * @param email
     * @return boolean true si se ha podido eliminar el usuario
     * @throws SQLException error controlado
     */
    public boolean eliminarUsuario(String email) throws SQLException {
        if (email == null || email.isEmpty()) {
            return false;
        }

        String sql = "DELETE FROM usuario WHERE email = '" + email + "'";

        try {
            PreparedStatement sentencia = getConnection().prepareStatement(sql);
            int filasEliminadas = sentencia.executeUpdate();
            return filasEliminadas > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            cerrar();
        }
    }

    /**
     * * Metodo que obtiene una palabra aleatoria de la base de datos
     * @param nivel 
     * @return String palabra aleatoria
     */
    public String obtenerPalabraAleatoria(int nivel) {
        try {
            String sql = "SELECT palabra FROM palabra WHERE id_nivel = ? ORDER BY RANDOM() LIMIT 1";
            PreparedStatement stmt = getConnection().prepareStatement(sql);
            stmt.setInt(1, nivel);
            
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString("palabra");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                cerrar();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return "casa"; 
    }
}
