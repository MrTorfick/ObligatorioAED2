package dominio.clases;

import dominio.tads.ListaGeneric;

import java.util.Comparator;

public class Conexion {

    //private String codigoAeropuertoOrigen;
    //private String codigoAeropuertoDestino;
    private double kilometros;
    private ListaGeneric<Vuelo> listaVuelos = new ListaGeneric();

    //Recorre la lista de vuelos, necesito obtener el objeto Vuelo en su recorrido

    public boolean VueloCoincidaConAerolinea(String codigoAerolinea, String codigoAeropuertoDestino, String codigoAeropuertoOrigen) {
        if (listaVuelos.cantElementos() == 0) {
            return false;
        }
        for (int i = 1; i <= listaVuelos.cantElementos(); i++) {
            Vuelo vuelo = listaVuelos.obtenerElemento(new Vuelo(codigoAeropuertoDestino, codigoAeropuertoOrigen, codigoAerolinea));
            if (vuelo == null)
                return false;
            if (!vuelo.getCodigoAerolinea().equals(codigoAerolinea)) {
                return false;
            }
        }
        return true;

    }

    public double obtenerCaminoMenosCostosoEnMinutos() {
        Vuelo v = listaVuelos.obtenerMinimo(Comparator.comparing((Vuelo vuelo) -> vuelo.getMinutos()));
        return v.getMinutos();


    }

    public ListaGeneric<Vuelo> getListaVuelos() {
        return listaVuelos;
    }

    public void setListaVuelos(ListaGeneric<Vuelo> listaVuelos) {
        this.listaVuelos = listaVuelos;
    }

    public Conexion(double kilometros) {
        //this.codigoAeropuertoOrigen = codigoAeropuertoOrigen;
        //this.codigoAeropuertoDestino = codigoAeropuertoDestino;
        this.kilometros = kilometros;
    }

    public Conexion(double kilometros, ListaGeneric<Vuelo> listaVuelos) {
        this.kilometros = kilometros;
        this.listaVuelos = listaVuelos;
    }
    /*
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
    */


    public double getKilometros() {
        return kilometros;
    }

    public void setKilometros(double kilometros) {
        this.kilometros = kilometros;
    }

    @Override
    public String toString() {
        return "Kilometros: " + kilometros + " Vuelos: " + listaVuelos.cantElementos();
    }
}