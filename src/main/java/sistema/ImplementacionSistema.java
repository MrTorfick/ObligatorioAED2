package sistema;

import dominio.clases.Pasajero;
import dominio.tads.ABBGenerics3;
import interfaz.*;

import java.util.Objects;

public class ImplementacionSistema implements Sistema {
    int maxAeropuertos = 0;
    int maxAerolineas = 0;

    ABBGenerics3<Pasajero> arbolPasajeros = new ABBGenerics3<>((p1, p2) -> {
        int cedula1 = Integer.parseInt(p1.getCedula());
        int cedula2 = Integer.parseInt(p2.getCedula());
        return Integer.compare(cedula1, cedula2);//Si cedula1 es mayor que cedula2, devuelve 1, si es menor devuelve -1, si son iguales devuelve 0
    });

    @Override
    public Retorno inicializarSistema(int maxAeropuertos, int maxAerolineas) {
        if (maxAeropuertos <= 5)
            return Retorno.error1("La cantidad maxima de aeropuertos debe ser mayor a 5");

        if (maxAerolineas <= 3)
            return Retorno.error2("La cantidad maxima de aerolineas debe ser mayor a 3");

        return Retorno.ok();
    }

    @Override
    public Retorno registrarPasajero(String cedula, String nombre, String telefono, Categoria categoria) {

        if (Objects.isNull(cedula) || Objects.isNull(nombre) || Objects.isNull(telefono) || Objects.isNull(categoria)
                || cedula.trim().isEmpty() || nombre.trim().isEmpty() || telefono.trim().isEmpty())
            return Retorno.error1("Debe ingresar todos los datos");


        String regex = "^([1-9]{1})[.-]?(\\d{3})[.-]?(\\d{3})[.-]?(\\d{1})$";//TODO: ESTA MAL, QUEDA PENDIENTE ESTO

        if (!cedula.matches(regex))
            return Retorno.error2("La cedula no tiene un formato valido");

        Pasajero p = new Pasajero(cedula, nombre, telefono, categoria);
        if (arbolPasajeros.existe(p))
            return Retorno.error3("Ya existe un pasajero registrado con esa cedula");

        arbolPasajeros.agregar(p);//Puede tirar excepcion
        return Retorno.ok();
    }

    @Override
    public Retorno buscarPasajero(String cedula) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno listarPasajerosAscendente() {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno listarPasajerosPorCategoria(Categoria categoria) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno registrarAerolinea(String codigo, String nombre) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno listarAerolineasDescendente() {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno registrarAeropuerto(String codigo, String nombre) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno registrarConexion(String codigoAeropuertoOrigen, String codigoAeropuertoDestino, double kilometros) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno registrarVuelo(String codigoCiudadOrigen, String codigoAeropuertoDestino, String codigoDeVuelo, double combustible, double minutos, double costoEnDolares, String codigoAerolinea) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno listadoAeropuertosCantDeEscalas(String codigoAeropuertoOrigen, int cantidad, String codigoAerolinea) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno viajeCostoMinimoKilometros(String codigoCiudadOrigen, String codigoCiudadDestino) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno viajeCostoMinimoEnMinutos(String codigoAeropuertoOrigen, String codigoAeropuertoDestino) {
        return Retorno.noImplementada();
    }


}
