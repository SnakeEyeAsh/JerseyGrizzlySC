package org.example.Model;

import org.bson.types.ObjectId;

public class Enemigo {
    private ObjectId id;
    private String pais;
    private String nombre;
    private String afiliacion;

    // Constructor vacío
    public Enemigo() {
    }

    // Constructor completo
    public Enemigo(String pais, String nombre, String afiliacion) {
        this.pais = pais;
        this.nombre = nombre;
        this.afiliacion = afiliacion;
    }

    // Getters y Setters
    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getIdAsString() {
        return id != null ? id.toHexString() : null;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAfiliacion() {
        return afiliacion;
    }

    public void setAfiliacion(String afiliacion) {
        this.afiliacion = afiliacion;
    }

    @Override
    public String toString() {
        return "Enemigo{" +
                "id=" + id +
                ", pais='" + pais + '\'' +
                ", nombre='" + nombre + '\'' +
                ", afiliacion='" + afiliacion + '\'' +
                '}';
    }
}
