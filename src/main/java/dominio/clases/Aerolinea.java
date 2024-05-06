package dominio.clases;

import dominio.excepciones.DatosInvalidosException;

import java.util.Objects;

public class Aerolinea {

    private String codigo;
    private String nombre;

    public Aerolinea(String codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
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

    public void validar() throws DatosInvalidosException {
        validarCodigo();
        validarNombre();

    }

    private void validarNombre() throws DatosInvalidosException {
        if(Objects.isNull(nombre) || nombre.trim().isEmpty())
            throw new DatosInvalidosException("El nombre no es valido");

    }

    private void validarCodigo() throws DatosInvalidosException {
        if(Objects.isNull(codigo) || codigo.trim().isEmpty())
            throw new DatosInvalidosException("El codigo no es valido");

    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Aerolinea aerolinea = (Aerolinea) o;
        return Objects.equals(codigo, aerolinea.codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(codigo);
    }

}
