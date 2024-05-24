package dominio.tads;

import dominio.clases.Aeropuerto;
import dominio.clases.Conexion;
import dominio.clases.Vuelo;
import dominio.excepciones.DatoYaExisteException;
import dominio.excepciones.GrafoLlenoException;
import dominio.excepciones.NoHayCaminoGrafoException;

public class GrafoConexion {


    private Aeropuerto[] vertices;

    private Arista[][] aristas;
    private int cantMaxVertices;
    private int largoActual;


    public String toUrl() {
        return VisualizadorGraphViz.grafoToUrl(vertices, aristas, a -> a.isExiste(), c -> c.toString(), a -> a.getDatoConexion().toString());
    }


    public GrafoConexion(int cantidadMaximaDeAeropuertos) {
        vertices = new Aeropuerto[cantidadMaximaDeAeropuertos];
        aristas = new Arista[cantidadMaximaDeAeropuertos][cantidadMaximaDeAeropuertos];
        this.cantMaxVertices = cantidadMaximaDeAeropuertos;
        for (int i = 0; i < cantMaxVertices; i++) {
            for (int j = 0; j < cantMaxVertices; j++) {
                aristas[i][j] = new Arista();
            }
        }
    }

    public boolean sonAdyacentes(Aeropuerto origen, Aeropuerto destino) {
        int idxOrigen = this.buscarIndiceVertice(origen);//fila
        int idxDestino = this.buscarIndiceVertice(destino);//columno
        if (idxOrigen < 0 || idxDestino < 0) {
            return false;
        }
        return sonAdyacentes(idxOrigen, idxDestino);
    }


    private boolean sonAdyacentes(int idxOrigen, int idxDestino) {
        return this.aristas[idxOrigen][idxDestino].isExiste();
    }

    public boolean existeVuelo(Aeropuerto origen, Aeropuerto destino, Vuelo vuelo) {
        int idxOrigen = this.buscarIndiceVertice(origen);//fila
        int idxDestino = this.buscarIndiceVertice(destino);//columno
        if (idxOrigen < 0 || idxDestino < 0) {
            return false;
        }
        return existeVueloBuscar(idxOrigen, idxDestino, vuelo);
    }

    private boolean existeVueloBuscar(int idxOrigen, int idxDestino, Vuelo vuelo) {
        if (aristas[idxOrigen][idxDestino].getDatoConexion().getListaVuelos().existeDato(vuelo)) {
            return true;
        }
        return false;
    }

    public void agregarVuelo(Aeropuerto origen, Aeropuerto destino, Vuelo vuelo) {
        int idxOrigen = this.buscarIndiceVertice(origen);//fila
        int idxDestino = this.buscarIndiceVertice(destino);//columno
        aristas[idxOrigen][idxDestino].getDatoConexion().getListaVuelos().agregarFinal(vuelo);
    }


    public void registrarVertice(Aeropuerto c) throws GrafoLlenoException, DatoYaExisteException {
        if (buscarIndiceVertice(c) == 1) {
            throw new DatoYaExisteException("Ya existe el vertice en el grafo");
        }

        if (largoActual == cantMaxVertices) {
            throw new GrafoLlenoException("El grafo esta lleno");
        }

        vertices[largoActual] = c;
        largoActual++;
    }


    public void registrarArista(Aeropuerto origen, Aeropuerto destino, Conexion conexion) {
        int idxOrigen = buscarIndiceVertice(origen);
        int idxDestino = buscarIndiceVertice(destino);

        aristas[idxOrigen][idxDestino].setExiste(true);
        aristas[idxOrigen][idxDestino].setDatoConexion(conexion);
    }

    public int buscarIndiceVertice(Aeropuerto aBuscarIndice) {

        for (int i = 0; i < vertices.length; i++) {
            Aeropuerto c = vertices[i];
            if (c != null && c.equals(aBuscarIndice)) return i;

        }
        return -1;
    }


    class InfoExploracion {
        int verticeExplorar;
        int cantSaltos;

        public InfoExploracion(int verticeExplorar, int cantSaltos) {
            this.verticeExplorar = verticeExplorar;
            this.cantSaltos = cantSaltos;
        }
    }


    public String Bfs(Aeropuerto origen, int cantidadMaximaEscalas, String codigoAerolinea) {
        Cola<InfoExploracion> frontera = new Cola();
        boolean[] visitados = new boolean[cantMaxVertices];
        ABBGeneric3<Aeropuerto> listaAeropuertos = new ABBGeneric3<>((p1, p2) -> {
            String codigo1 = p1.getCodigo();
            String codigo2 = p2.getCodigo();
            return codigo1.compareTo(codigo2);
        });

        frontera.encolar(new InfoExploracion(this.buscarIndiceVertice(origen), 0));

        while (!frontera.esVacia()) {
            InfoExploracion infoAVisitar = frontera.desencolar();
            int idxAVisitar = infoAVisitar.verticeExplorar;
            int nivelDelVerticeAExplorar = infoAVisitar.cantSaltos;
            if (!visitados[idxAVisitar]) {
                if (nivelDelVerticeAExplorar <= cantidadMaximaEscalas) {
                    listaAeropuertos.agregar(vertices[idxAVisitar]);//esto es el lo imprimo
                    //System.out.println(vertices[idxAVisitar] + "(idxVertice:" + idxAVisitar + ", cantidadSaltos:" + nivelDelVerticeAExplorar + ")"); //TODO
                    visitados[idxAVisitar] = true;
                } else {
                    break;
                }

                for (int idxDestino = 0; idxDestino < cantMaxVertices; idxDestino++) {

                    if (sonAdyacentes(idxAVisitar, idxDestino)) {


                        Aeropuerto a1 = vertices[idxAVisitar];
                        Aeropuerto a2 = vertices[idxDestino];

                        if (aristas[idxAVisitar][idxDestino].getDatoConexion().VueloCoincidaConAerolinea(codigoAerolinea, a1.getCodigo(), a2.getCodigo())) {
                            frontera.encolar(new InfoExploracion(idxDestino, nivelDelVerticeAExplorar + 1));
                        }


                    }
                }

            }

        }
        return listaAeropuertos.inOrder();

    }

    public String[] Dijkstra(Aeropuerto origen, Aeropuerto destino, String criterio) throws NoHayCaminoGrafoException {
        int idxOrigen = buscarIndiceVertice(origen);
        int idxDestino = buscarIndiceVertice(destino);
        int[] padres = new int[cantMaxVertices];
        boolean[] visitados = new boolean[cantMaxVertices];
        double[] distancias = new double[cantMaxVertices];

        for (int i = 0; i < cantMaxVertices; i++) {
            distancias[i] = Double.MAX_VALUE;
            visitados[i] = false;
            padres[i] = -1;
        }

        distancias[idxOrigen] = 0;
        padres[idxOrigen] = idxOrigen;

        while (!estaVisitadoTodo(visitados, padres)) {
            int idxAExplorar = menorDistanciaNoVisitado(distancias, visitados);

            double distanciaVertice = distancias[idxAExplorar];
            for (int ady = 0; ady < cantMaxVertices; ady++) {
                if (sonAdyacentes(idxAExplorar, ady) && aristas[idxAExplorar][ady].getDatoConexion().getListaVuelos().cantElementos() > 0) {
                    double numArista = 0;
                    switch (criterio) {
                        case "kilometros":
                            numArista = aristas[idxAExplorar][ady].getDatoConexion().getKilometros();
                            break;
                        case "minutos":
                            numArista = aristas[idxAExplorar][ady].getDatoConexion().obtenerCaminoMenosCostosoEnMinutos();
                            break;
                    }
                    double distanciaAdyacente = distanciaVertice + numArista;
                    if (distanciaAdyacente < distancias[ady]) {
                        distancias[ady] = distanciaAdyacente;
                        padres[ady] = idxAExplorar;
                    }
                }
            }
            visitados[idxAExplorar] = true;
        }

        double caminoMinimo = distancias[idxDestino];
        String retornar = reconstruirCamino(idxOrigen, idxDestino, padres);
        return new String[]{Double.toString(caminoMinimo), retornar};
    }

    private String reconstruirCamino(int idxOrigen, int idxDestino, int[] padres) throws NoHayCaminoGrafoException {
        StringBuilder camino = new StringBuilder();
        if (padres[idxDestino] == -1) {
            throw new NoHayCaminoGrafoException("La cedula no puede ser nula o vacia");
        }
        int idxActual = idxDestino;
        while (idxActual != idxOrigen) {
            camino.insert(0, vertices[idxActual].getCodigo() + ";" + vertices[idxActual].getNombre() + "|");
            idxActual = padres[idxActual];
        }
        camino.insert(0, vertices[idxOrigen].getCodigo() + ";" + vertices[idxOrigen].getNombre() + "|");
        camino.deleteCharAt(camino.length() - 1);
        return camino.toString();
    }


    private boolean estaVisitadoTodo(boolean[] visitados, int[] padres) {
        for (int i = 0; i < visitados.length; i++) {
            if (!visitados[i] && padres[i] != -1) {
                return false;
            }
        }
        return true;
    }

    private int menorDistanciaNoVisitado(double[] distancias, boolean[] visitados) {
        double min = Double.MAX_VALUE;
        int idxMin = -1;
        for (int i = 0; i < distancias.length; i++) {
            if (distancias[i] <= min && !visitados[i]) {
                min = distancias[i];
                idxMin = i;
            }
        }
        return idxMin;
    }
}

