package dominio.clases;

import dominio.tads.ListaGeneric;

public class Conexion {

    //private String codigoAeropuertoOrigen;
    //private String codigoAeropuertoDestino;
    private double kilometros;
    private ListaGeneric<Vuelo> listaVuelos = new ListaGeneric();

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


}
