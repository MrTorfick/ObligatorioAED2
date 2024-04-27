package dominio.clases;

import interfaz.Categoria;

import java.util.Objects;

public class Pasajero {

    private String cedula;
    private String nombre;
    private String telefono;
    private Categoria categoria;

    public Pasajero(String cedula) {
        this.cedula = cedula;
    }

    public Pasajero(String cedula, String nombre, String telefono, Categoria categoria) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.telefono = telefono;
        this.categoria = categoria;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public boolean validarCedula() {
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pasajero pasajero = (Pasajero) o;
        return Objects.equals(cedula, pasajero.cedula);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(cedula);
    }

    @Override
    public String toString() {
        return cedula + ";" + nombre + ";" + telefono + ";" + categoria.getTexto();
    }
}
