package sistema;

import dominio.clases.*;
import dominio.excepciones.*;
import dominio.tads.ABBGeneric3;
import dominio.tads.GrafoConexion;
import interfaz.*;

import java.util.Objects;

public class ImplementacionSistema implements Sistema {
    int maxAeropuertos = 0;
    int maxAerolineas = 0;

    ABBGeneric3<Pasajero> arbolPasajerosGeneral = new ABBGeneric3<>((p1, p2) -> {
        int cedula1 = Integer.parseInt(p1.getCedula().replaceAll("[^0-9]", ""));//Cualquier caracter que no este entre 0-9, eliminado
        int cedula2 = Integer.parseInt(p2.getCedula().replaceAll("[^0-9]", ""));
        return Integer.compare(cedula1, cedula2);//Si cedula1 es mayor que cedula2, devuelve 1, si es menor devuelve -1, si son iguales devuelve 0
    });
    ABBGeneric3<Pasajero> arbolPasajerosCategoriaPlatino = new ABBGeneric3<>((p1, p2) -> {
        int cedula1 = Integer.parseInt(p1.getCedula().replaceAll("[^0-9]", ""));
        int cedula2 = Integer.parseInt(p2.getCedula().replaceAll("[^0-9]", ""));
        return Integer.compare(cedula1, cedula2);
    });
    ABBGeneric3<Pasajero> arbolPasajerosCategoriaFrecuente = new ABBGeneric3<>((p1, p2) -> {
        int cedula1 = Integer.parseInt(p1.getCedula().replaceAll("[^0-9]", ""));
        int cedula2 = Integer.parseInt(p2.getCedula().replaceAll("[^0-9]", ""));
        return Integer.compare(cedula1, cedula2);
    });
    ABBGeneric3<Pasajero> arbolPasajerosCategoriaEstandar = new ABBGeneric3<>((p1, p2) -> {
        int cedula1 = Integer.parseInt(p1.getCedula().replaceAll("[^0-9]", ""));
        int cedula2 = Integer.parseInt(p2.getCedula().replaceAll("[^0-9]", ""));
        return Integer.compare(cedula1, cedula2);
    });

    ABBGeneric3<Aerolinea> arbolAerolineasGeneral = new ABBGeneric3<>((p1, p2) -> {
        String codigo1 = p1.getCodigo();
        String codigo2 = p2.getCodigo();
        return codigo1.compareTo(codigo2);
    });

    ABBGeneric3<Aeropuerto> arbolAeropuertosGeneral = new ABBGeneric3<>((p1, p2) -> {
        String codigo1 = p1.getCodigo();
        String codigo2 = p2.getCodigo();
        return codigo1.compareTo(codigo2);
    });

    GrafoConexion grafoConexionAeropuertos;

    @Override
    public Retorno inicializarSistema(int maxAeropuertos, int maxAerolineas) {
        if (maxAeropuertos <= 5)
            return Retorno.error1("La cantidad maxima de aeropuertos debe ser mayor a 5");

        if (maxAerolineas <= 3)
            return Retorno.error2("La cantidad maxima de aerolineas debe ser mayor a 3");
        this.maxAeropuertos = maxAeropuertos;
        this.maxAerolineas = maxAerolineas;
        grafoConexionAeropuertos = new GrafoConexion(maxAeropuertos);
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
        } catch (DatosInvalidosException e) {
            return Retorno.error1("La cedula es vacia o nula");
        } catch (CedulaInvalidaException e) {
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

        if (arbolAerolineasGeneral.cantNodos() == maxAerolineas)
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
        //System.out.println(arbolAerolineasGeneral.toUrl());
        String lista = arbolAerolineasGeneral.invertedInOrder();
        return Retorno.ok(lista);
    }

    @Override
    public Retorno registrarAeropuerto(String codigo, String nombre) {

        try {
            Aeropuerto a = new Aeropuerto(codigo, nombre);
            a.validar();
            grafoConexionAeropuertos.registrarVertice(a);
            return Retorno.ok();
        } catch (DatosInvalidosException e) {
            return Retorno.error2(e.getMessage());
        } catch (DatoYaExisteException e) {
            return Retorno.error3(e.getMessage());
        } catch (GrafoLlenoException e) {
            return Retorno.error1(e.getMessage());
        }


    }

    @Override
    public Retorno registrarConexion(String codigoAeropuertoOrigen, String codigoAeropuertoDestino, double kilometros) {

        if (kilometros <= 0) {
            return Retorno.error1("Los kilometros no pueden ser menores a 0");
        }


        if (Objects.isNull(codigoAeropuertoOrigen) || Objects.isNull(codigoAeropuertoDestino) ||
                codigoAeropuertoOrigen.trim().isEmpty() || codigoAeropuertoDestino.trim().isEmpty())
            return Retorno.error2("Los parametros no pueden ser vacios ni nulos");

        Aeropuerto a1 = new Aeropuerto(codigoAeropuertoOrigen);
        Aeropuerto a2 = new Aeropuerto(codigoAeropuertoDestino);

        if (grafoConexionAeropuertos.buscarIndiceVertice(a1) == -1)
            return Retorno.error3("No existe el aeropuerto de origen");

        if (grafoConexionAeropuertos.buscarIndiceVertice(a2) == -1)
            return Retorno.error4("No existe el aeropuerto de destino");


        if (grafoConexionAeropuertos.sonAdyacentes(a1, a2)) {
            return Retorno.error5("Ya existe una conexion entre el origen y el destino");
        }
        grafoConexionAeropuertos.registrarArista(a1, a2, new Conexion(kilometros));


        return Retorno.ok();
    }

    @Override
    public Retorno registrarVuelo(String codigoCiudadOrigen, String codigoAeropuertoDestino, String codigoDeVuelo, double combustible, double minutos, double costoEnDolares, String codigoAerolinea) {

        try {
            Vuelo v = new Vuelo(codigoCiudadOrigen, codigoAeropuertoDestino, codigoDeVuelo, combustible, minutos, costoEnDolares, codigoAerolinea);
            v.validar();
            if (grafoConexionAeropuertos.buscarIndiceVertice(new Aeropuerto(codigoCiudadOrigen)) == -1) {
                return Retorno.error3("No existe el aeropuerto de origen");
            }
            if (grafoConexionAeropuertos.buscarIndiceVertice(new Aeropuerto(codigoAeropuertoDestino)) == -1) {
                return Retorno.error4("No existe el aeropuerto de destino");
            }

            if (!arbolAerolineasGeneral.existe(new Aerolinea(codigoAerolinea))) {
                return Retorno.error5("La aerolinea no existe");
            }
            if (!grafoConexionAeropuertos.sonAdyacentes(new Aeropuerto(codigoCiudadOrigen), new Aeropuerto(codigoAeropuertoDestino))) {
                return Retorno.error6("No existe una conexion entre origen y destino");
            }
            if (grafoConexionAeropuertos.existeVuelo(new Aeropuerto(codigoCiudadOrigen), new Aeropuerto(codigoAeropuertoDestino), new Vuelo(codigoCiudadOrigen, codigoAeropuertoDestino, codigoDeVuelo, combustible, minutos, costoEnDolares, codigoAerolinea))) {
                return Retorno.error7("Ya existe un vuelo con ese codigo");
            }


            grafoConexionAeropuertos.agregarVuelo(new Aeropuerto(codigoCiudadOrigen), new Aeropuerto(codigoAeropuertoDestino), v);
            return Retorno.ok();
        } catch (DatosInvalidosException e) {
            return e.getRetorno();
        }

    }

    @Override
    public Retorno listadoAeropuertosCantDeEscalas(String codigoAeropuertoOrigen, int cantidad, String codigoAerolinea) {

        if (cantidad < 0) {
            return Retorno.error1("La cantidad de escalas no puede ser menor a 0");
        }

        if (grafoConexionAeropuertos.buscarIndiceVertice(new Aeropuerto(codigoAeropuertoOrigen)) == -1) {
            return Retorno.error2("No existe el aeropuerto de origen");
        }

        if (!arbolAerolineasGeneral.existe(new Aerolinea(codigoAerolinea))) {
            return Retorno.error3("Ya existe una aerolinea con ese codigo");
        }
        String lista = grafoConexionAeropuertos.Bfs(new Aeropuerto(codigoAeropuertoOrigen), cantidad, codigoAerolinea);
        //System.out.println(lista);
        // System.out.println(grafoConexionAeropuertos.toUrl());
        return Retorno.ok(lista);
    }

    @Override
    public Retorno viajeCostoMinimoKilometros(String codigoCiudadOrigen, String codigoCiudadDestino) {

        try {

            if (Objects.isNull(codigoCiudadOrigen) || Objects.isNull(codigoCiudadDestino) || codigoCiudadDestino.trim().isEmpty() || codigoCiudadOrigen.trim().isEmpty()) {
                return Retorno.error1("Los parametros no pueden ser vacios ni nulos");
            }
            if (grafoConexionAeropuertos.buscarIndiceVertice(new Aeropuerto(codigoCiudadOrigen)) == -1) {
                return Retorno.error3("No existe el aeropuerto de origen");
            }

            if (grafoConexionAeropuertos.buscarIndiceVertice(new Aeropuerto(codigoCiudadDestino)) == -1) {
                return Retorno.error4("No existe el aeropuerto de destino");
            }


            String[] resultado = grafoConexionAeropuertos.Dijkstra(new Aeropuerto(codigoCiudadOrigen), new Aeropuerto(codigoCiudadDestino), "kilometros");
            double caminoMinimo = Double.parseDouble(resultado[0]);
            String camino = resultado[1];
            return Retorno.ok((int) caminoMinimo, camino);
        } catch (NoHayCaminoGrafoException e) {
            return Retorno.error2(e.getMessage());
        }

    }

    @Override
    public Retorno viajeCostoMinimoEnMinutos(String codigoAeropuertoOrigen, String codigoAeropuertoDestino) {

        if (codigoAeropuertoOrigen == null || codigoAeropuertoDestino == null || codigoAeropuertoOrigen.trim().isEmpty() || codigoAeropuertoDestino.trim().isEmpty()) {
            return Retorno.error1("Los parametros no pueden ser vacios ni nulos");
        }

        if (grafoConexionAeropuertos.buscarIndiceVertice(new Aeropuerto(codigoAeropuertoOrigen)) == -1) {
            return Retorno.error3("No existe el aeropuerto de origen");
        }

        if (grafoConexionAeropuertos.buscarIndiceVertice(new Aeropuerto(codigoAeropuertoDestino)) == -1) {
            return Retorno.error4("No existe el aeropuerto de destino");
        }

        try {
            String[] resultado = grafoConexionAeropuertos.Dijkstra(new Aeropuerto(codigoAeropuertoOrigen), new Aeropuerto(codigoAeropuertoDestino), "minutos");
            double caminoMinimo = Double.parseDouble(resultado[0]);
            String camino = resultado[1];
            return Retorno.ok((int) caminoMinimo, camino);


        } catch (NoHayCaminoGrafoException e) {
            return Retorno.error2(e.getMessage());
        }
    }


}
