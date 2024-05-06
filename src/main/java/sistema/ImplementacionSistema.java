package sistema;

import dominio.clases.Aerolinea;
import dominio.clases.Pasajero;
import dominio.clases.ResultadoBusqueda;
import dominio.excepciones.CedulaInvalidaException;
import dominio.excepciones.DatosInvalidosException;
import dominio.tads.ABBGenerics3;
import interfaz.*;

public class ImplementacionSistema implements Sistema {
    int maxAeropuertos = 0;
    int maxAerolineas = 0;

    ABBGenerics3<Pasajero> arbolPasajerosGeneral = new ABBGenerics3<>((p1, p2) -> {
        int cedula1 = Integer.parseInt(p1.getCedula().replaceAll("[^0-9]", ""));//Cualquier caracter que no este entre 0-9, eliminado
        int cedula2 = Integer.parseInt(p2.getCedula().replaceAll("[^0-9]", ""));
        return Integer.compare(cedula1, cedula2);//Si cedula1 es mayor que cedula2, devuelve 1, si es menor devuelve -1, si son iguales devuelve 0
    });
    ABBGenerics3<Pasajero> arbolPasajerosCategoriaPlatino = new ABBGenerics3<>((p1, p2) -> {
        int cedula1 = Integer.parseInt(p1.getCedula().replaceAll("[^0-9]", ""));
        int cedula2 = Integer.parseInt(p2.getCedula().replaceAll("[^0-9]", ""));
        return Integer.compare(cedula1, cedula2);
    });
    ABBGenerics3<Pasajero> arbolPasajerosCategoriaFrecuente = new ABBGenerics3<>((p1, p2) -> {
        int cedula1 = Integer.parseInt(p1.getCedula().replaceAll("[^0-9]", ""));
        int cedula2 = Integer.parseInt(p2.getCedula().replaceAll("[^0-9]", ""));
        return Integer.compare(cedula1, cedula2);
    });
    ABBGenerics3<Pasajero> arbolPasajerosCategoriaEstandar = new ABBGenerics3<>((p1, p2) -> {
        int cedula1 = Integer.parseInt(p1.getCedula().replaceAll("[^0-9]", ""));
        int cedula2 = Integer.parseInt(p2.getCedula().replaceAll("[^0-9]", ""));
        return Integer.compare(cedula1, cedula2);
    });

    ABBGenerics3<Aerolinea> arbolAerolineasGeneral = new ABBGenerics3<>((p1, p2) -> {
        String codigo1 = p1.getCodigo();
        String codigo2 = p2.getCodigo();
        return codigo1.compareTo(codigo2);
    });


    @Override
    public Retorno inicializarSistema(int maxAeropuertos, int maxAerolineas) {
        if (maxAeropuertos <= 5)
            return Retorno.error1("La cantidad maxima de aeropuertos debe ser mayor a 5");

        if (maxAerolineas <= 3)
            return Retorno.error2("La cantidad maxima de aerolineas debe ser mayor a 3");
        this.maxAeropuertos=maxAeropuertos;
        this.maxAerolineas=maxAerolineas;
        return Retorno.ok();
    }

    private boolean validarCedula(String cedula) {

        String primerFormato = "^([1-9])(?:\\.?\\d{3}){2}-\\d$"; // N.NNN.NNN-N
        String segundoFormato = "^[1-9][0-9]{2}\\.[0-9]{3}-[0-9]$";// NNN.NNN-N

        if (!cedula.matches(primerFormato) && !cedula.matches(segundoFormato))
            return false;
        return true;
    }

    @Override
    public Retorno registrarPasajero(String cedula, String nombre, String telefono, Categoria categoria) {
        try {
            Pasajero p = new Pasajero(cedula, nombre, telefono, categoria);
            p.validar();

            if (arbolPasajerosGeneral.existe(p))
                return Retorno.error3("Ya existe un pasajero registrado con esa cedula");

            arbolPasajerosGeneral.agregar(p);//Puede tirar excepcion
            switch (categoria.getIndice()) {
                case 0:
                    arbolPasajerosCategoriaPlatino.agregar(p);
                    break;
                case 1:
                    arbolPasajerosCategoriaFrecuente.agregar(p);
                    break;
                case 2:
                    arbolPasajerosCategoriaEstandar.agregar(p);
                    break;
            }

            return Retorno.ok();

        } catch (CedulaInvalidaException e) {
            return Retorno.error2(e.getMessage());
        } catch (DatosInvalidosException e) {
            return Retorno.error1(e.getMessage());
        }


    }

    @Override
    public Retorno buscarPasajero(String cedula) {

        try {
            Pasajero p = new Pasajero(cedula);
            p.validarCedula();
        } catch (CedulaInvalidaException e) {
            return Retorno.error1("La cedula es vacia o nula");
        } catch (DatosInvalidosException e) {
            return Retorno.error2("La cedula no tiene un formato valido");
        }

        ResultadoBusqueda<Pasajero> retornar = arbolPasajerosGeneral.obtener(new Pasajero(cedula));
        if (retornar.getDato() == null) {
            return Retorno.error3("No se encontro un pasajero con la cedula ingresada");
        }


        return Retorno.ok(retornar.getNodosVisitados(), retornar.getDato().toString());
    }

    @Override
    public Retorno listarPasajerosAscendente() {
        String lista = arbolPasajerosGeneral.inOrder();
        return Retorno.ok(lista);
    }

    @Override
    public Retorno listarPasajerosPorCategoria(Categoria categoria) {
        String lista = "";
        switch (categoria.getIndice()) {
            case 0:
                lista = arbolPasajerosCategoriaPlatino.inOrder();
                return Retorno.ok(lista);

            case 1:
                lista = arbolPasajerosCategoriaFrecuente.inOrder();
                return Retorno.ok(lista);
            case 2:
                lista = arbolPasajerosCategoriaEstandar.inOrder();
                return Retorno.ok(lista);
        }
        return Retorno.noImplementada();
    }

    @Override
    public Retorno registrarAerolinea(String codigo, String nombre) {

        if (arbolAerolineasGeneral.cantNodos()==maxAerolineas)
            return Retorno.error1("Se alcanzo la cantidad maxima de aerolineas");


        Aerolinea a = new Aerolinea(codigo, nombre);
        try {
            a.validar();
        } catch (DatosInvalidosException e) {
            return Retorno.error2(e.getMessage());
        }

        if (arbolAerolineasGeneral.existe(a))
            return Retorno.error3("Ya existe una aerolinea con ese codigo");

        arbolAerolineasGeneral.agregar(a);

        return Retorno.ok();
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
