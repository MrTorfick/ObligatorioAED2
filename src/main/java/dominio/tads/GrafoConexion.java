package dominio.tads;

import dominio.clases.Aeropuerto;
import dominio.clases.Conexion;
import dominio.clases.Vuelo;
import dominio.excepciones.DatoYaExisteException;
import dominio.excepciones.GrafoLlenoException;

public class GrafoConexion {


    private Aeropuerto[] vertices;

    private Arista[][] aristas;
    private int cantMaxVertices;
    private int largoActual;


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
    public void agregarVuelo(Aeropuerto origen, Aeropuerto destino, Vuelo vuelo){
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
            if (c != null && c.equals(aBuscarIndice))
                return i;

        }
        return -1;
    }

}
