package es.ies.puerto.model.bbdd;

import java.util.Objects;

/**
 * @author hectorpoleo
 * @version 1.0.0
 */
public class Usuario{
    private String email;
    private Integer id;
    private String nombre;
    private String Password;
    private int nivelActual;
    private int victoriasTotales;
    private int derrotasTotales;
    private int victoriasNivel;
    private int derrotasConsecutivas;
    private int mayorRacha;
    private int rachaActual;

    /**
     * Constructor vacio
     */
    public Usuario() {
    }

    public Usuario(String Password, String nombre) {
        this.Password = Password;
        this.nombre = nombre;
    }

    /**
     * Constructor con parametros
     * @param email
     * @param nombre
     * @param Password
     */
    public Usuario(Integer id, String email, String nombre, String Password) {
        this.id = id;
        this.email = email;
        this.nombre = nombre;
        this.Password = Password;
        this.nivelActual = 1;
        this.victoriasTotales = 0;
        this.derrotasTotales = 0;
        this.victoriasNivel = 0;
        this.derrotasConsecutivas = 0;
        this.mayorRacha = 0;
        this.rachaActual = 0;
    }

    public Usuario(String email, String nombre, String Password) {
        this.id = id;
        this.email = email;
        this.nombre = nombre;
        this.Password = Password;
        this.nivelActual = 1;
        this.victoriasTotales = 0;
        this.derrotasTotales = 0;
        this.victoriasNivel = 0;
        this.derrotasConsecutivas = 0;
        this.mayorRacha = 0;
        this.rachaActual = 0;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return this.Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public int getNivelActual() {
        return nivelActual;
    }

    public void setNivelActual(int nivelActual) {
        this.nivelActual = nivelActual;
    }

    public int getVictoriasTotales() {
        return victoriasTotales;
    }

    public void setVictoriasTotales(int victoriasTotales) {
        this.victoriasTotales = victoriasTotales;
    }

    public int getDerrotasTotales() {
        return derrotasTotales;
    }

    public void setDerrotasTotales(int derrotasTotales) {
        this.derrotasTotales = derrotasTotales;
    }

    public int getVictoriasNivel() {
        return victoriasNivel;
    }

    public void setVictoriasNivel(int victoriasNivel) {
        this.victoriasNivel = victoriasNivel;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getDerrotasConsecutivas() {
        return derrotasConsecutivas;
    }

    public void setDerrotasConsecutivas(int derrotasConsecutivas) {
        this.derrotasConsecutivas = derrotasConsecutivas;
    }

    public int getMayorRacha() {
        return mayorRacha;
    }

    public void setMayorRacha(int mayorRacha) {
        this.mayorRacha = mayorRacha;
    }

    public int getRachaActual() {
        return rachaActual;
    }

    public void setRachaActual(int rachaActual) {
        this.rachaActual = rachaActual;
    }

    /**
     * * Metodo que actualiza las estadisticas del usuario
     * * @param usuario 
     * @param victoria 
     */
    public void actualizarEstadisticas(boolean victoria) {
        if (victoria) {
            victoriasTotales++;
            victoriasNivel++;
            derrotasConsecutivas = 0;
            rachaActual++;
            if (rachaActual > mayorRacha) {
                mayorRacha = rachaActual;
            }
            
            if (victoriasNivel >= 3 && nivelActual < 3) {
                nivelActual++;
                victoriasNivel = 0;
            }
        } else {
            derrotasTotales++;
            derrotasConsecutivas++;
            rachaActual = 0;
            
            if (derrotasConsecutivas >= 2) {
                victoriasNivel = Math.max(0, victoriasNivel - 1);
                derrotasConsecutivas = 0;
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Usuario)) {
            return false;
        }
        Usuario usuarioEntity = (Usuario) o;
        return Objects.equals(email, usuarioEntity.email) && Objects.equals(nombre, usuarioEntity.nombre);
    }

    @Override
    public String toString() {
        return
             getEmail() +
            "," + getNombre() +
            "," + getPassword() + ","
             + getId();
    }

}
