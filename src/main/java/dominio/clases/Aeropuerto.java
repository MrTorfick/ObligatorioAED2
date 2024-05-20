package dominio.clases;

import dominio.excepciones.DatosInvalidosException;

import java.util.Objects;

public class Aeropuerto {

    private String codigo;
    private String nombre;


    public Aeropuerto(String codigo) {
        this.codigo = codigo;
    }

    public Aeropuerto(String codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }


    public void validar() throws DatosInvalidosException {
        validarCodigo();
        validarNombre();

    }

    private void validarNombre() throws DatosInvalidosException {
        if(Objects.isNull(nombre) || nombre.trim().isEmpty())
            throw new DatosInvalidosException("El nombre no es valido");

    }

    public void validarCodigo() throws DatosInvalidosException {
        if(Objects.isNull(codigo) || codigo.trim().isEmpty())
            throw new DatosInvalidosException("El codigo no es valido");

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Aeropuerto that = (Aeropuerto) o;
        return Objects.equals(codigo, that.codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(codigo);
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return codigo + ";" + nombre;
    }
}
