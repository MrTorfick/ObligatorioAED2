package dominio.clases;

import dominio.excepciones.CedulaInvalidaException;
import dominio.excepciones.DatosPasajeroException;
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

    private void validarCategoria() throws DatosPasajeroException {
        if(Objects.isNull(categoria))
            throw new DatosPasajeroException("La categoria no es valida");
    }

    private void validarTelefono() throws DatosPasajeroException {
        if (Objects.isNull(telefono)||telefono.trim().isEmpty())
            throw new DatosPasajeroException("El telefono no es valido");
    }

    private void validarNombre() throws DatosPasajeroException {
        if (Objects.isNull(nombre)||nombre.trim().isEmpty())
            throw new DatosPasajeroException("El nombre no es valido");

    }

    public void validarCedula() throws CedulaInvalidaException, DatosPasajeroException {

        if(Objects.isNull(cedula)||cedula.trim().isEmpty())
            throw new DatosPasajeroException("La cedula no puede ser nula o vacia");

        String primerFormato = "^([1-9])(?:\\.?\\d{3}){2}-\\d$"; // N.NNN.NNN-N
        String segundoFormato = "^[1-9][0-9]{2}\\.[0-9]{3}-[0-9]$";// NNN.NNN-N

        if (!cedula.matches(primerFormato) && !cedula.matches(segundoFormato))
            throw new CedulaInvalidaException("La cedula no tiene un formato valido");
    }

    public void validar() throws CedulaInvalidaException, DatosPasajeroException {
        validarCategoria();
        validarNombre();
        validarTelefono();
        validarCedula();
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
