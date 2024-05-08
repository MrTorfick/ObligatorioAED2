package dominio.tads;

import dominio.clases.Aeropuerto;
import dominio.clases.Conexion;

public class GrafoConexion {


    private Aeropuerto[] vertices;

    private Arista[][] aristas;
    private int cantMaxVertices;
    private int largoActual;


    public GrafoConexion(int cantidadMaximaDeAerolineas) {
        vertices = new Aeropuerto[cantidadMaximaDeAerolineas];
        aristas = new Arista[cantidadMaximaDeAerolineas][cantidadMaximaDeAerolineas];
        this.cantMaxVertices = cantidadMaximaDeAerolineas;
        for (int i = 0; i < cantMaxVertices; i++) {
            for (int j = 0; j < cantMaxVertices; j++) {
                aristas[i][j] = new Arista();
            }
        }
    }


    public void registrarArista(Aeropuerto origen, Aeropuerto destino, Conexion conexion) {
        int idxOrigen = buscarIndiceVertice(origen);
        int idxDestino = buscarIndiceVertice(destino);

        aristas[idxOrigen][idxDestino].setExiste(true);
        aristas[idxOrigen][idxDestino].setDatoConexion(conexion);
    }

    private int buscarIndiceVertice(Aeropuerto aBuscarIndice) {

        for (int i = 0; i < vertices.length; i++) {
            Aeropuerto c = vertices[i];
            if (c != null && c.equals(aBuscarIndice))
                return i;

        }
        return -1;
    }


}
