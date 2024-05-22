package dominio.clases;

import dominio.excepciones.DatosInvalidosException;
import interfaz.Retorno;

import java.util.Objects;

public class Vuelo {

    private String codigoAeropuertoOrigen;
    private String codigoAeropuertoDestino;
    private String codigoDeVuelo;
    private double combustible;
    private double minutos;
    private double costoEnDolares;
    private String codigoAerolinea;

    public Vuelo(String codigoAeropuertoOrigen, String codigoAeropuertoDestino, String codigoDeVuelo, double combustible, double minutos, double costoEnDolares, String codigoAerolinea) {
        this.codigoAeropuertoOrigen = codigoAeropuertoOrigen;
        this.codigoAeropuertoDestino = codigoAeropuertoDestino;
        this.codigoDeVuelo = codigoDeVuelo;
        this.combustible = combustible;
        this.minutos = minutos;
        this.costoEnDolares = costoEnDolares;
        this.codigoAerolinea = codigoAerolinea;
    }


    public Vuelo(String codigoAeropuertoDestino, String codigoAeropuertoOrigen, String codigoAerolinea) {
        this.codigoAeropuertoDestino = codigoAeropuertoDestino;
        this.codigoAeropuertoOrigen = codigoAeropuertoOrigen;
        this.codigoAerolinea = codigoAerolinea;
    }


    public void validar() throws DatosInvalidosException {
        validarDoubles();
        validarStrings();
    }

    private void validarDoubles() throws DatosInvalidosException {

        if (combustible <= 0 || minutos <= 0 || costoEnDolares <= 0) {
            throw new DatosInvalidosException("Datos invalidos", Retorno.error1("Datos invalidos"));
        }
    }


    private void validarStrings() throws DatosInvalidosException {

        if (Objects.isNull(codigoAeropuertoOrigen) || codigoAeropuertoOrigen.trim().isEmpty()
                || Objects.isNull(codigoAeropuertoDestino) || codigoAeropuertoDestino.trim().isEmpty()
                || Objects.isNull(codigoDeVuelo) || codigoDeVuelo.trim().isEmpty()
                || Objects.isNull(codigoAerolinea) || codigoAerolinea.trim().isEmpty()) {
            throw new DatosInvalidosException("Datos invalidos", Retorno.error2("Datos invalidos"));
        }


    }


    public String getCodigoAeropuertoOrigen() {
        return codigoAeropuertoOrigen;
    }

    public void setCodigoAeropuertoOrigen(String codigoAeropuertoOrigen) {
        this.codigoAeropuertoOrigen = codigoAeropuertoOrigen;
    }

    public String getCodigoAeropuertoDestino() {
        return codigoAeropuertoDestino;
    }

    public void setCodigoAeropuertoDestino(String codigoAeropuertoDestino) {
        this.codigoAeropuertoDestino = codigoAeropuertoDestino;
    }

    public String getCodigoDeVuelo() {
        return codigoDeVuelo;
    }

    public void setCodigoDeVuelo(String codigoDeVuelo) {
        this.codigoDeVuelo = codigoDeVuelo;
    }

    public double getCombustible() {
        return combustible;
    }

    public void setCombustible(double combustible) {
        this.combustible = combustible;
    }

    public double getMinutos() {
        return minutos;
    }

    public void setMinutos(double minutos) {
        this.minutos = minutos;
    }

    public double getCostoEnDolares() {
        return costoEnDolares;
    }

    public void setCostoEnDolares(double costoEnDolares) {
        this.costoEnDolares = costoEnDolares;
    }

    public String getCodigoAerolinea() {
        return codigoAerolinea;
    }

    public void setCodigoAerolinea(String codigoAerolinea) {
        this.codigoAerolinea = codigoAerolinea;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vuelo vuelo = (Vuelo) o;
        return Objects.equals(codigoAeropuertoOrigen, vuelo.codigoAeropuertoOrigen) && Objects.equals(codigoAeropuertoDestino, vuelo.codigoAeropuertoDestino) && Objects.equals(codigoDeVuelo, vuelo.codigoDeVuelo)
                || Objects.equals(codigoAeropuertoOrigen, vuelo.codigoAeropuertoDestino) && Objects.equals(codigoAeropuertoDestino, vuelo.codigoAeropuertoOrigen) && Objects.equals(codigoAerolinea, vuelo.codigoAerolinea);
    }


    @Override
    public int hashCode() {
        return Objects.hash(codigoAeropuertoOrigen, codigoAeropuertoDestino, codigoDeVuelo);
    }
}
