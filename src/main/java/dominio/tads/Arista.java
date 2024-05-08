package dominio.tads;

import dominio.clases.Conexion;

public class Arista {


    private boolean existe;
    private Conexion datoConexion;


    public Arista() {
    }

    public Arista(boolean existe, Conexion datoConexion) {
        this.existe = existe;
        this.datoConexion = datoConexion;
    }


    public boolean isExiste() {
        return existe;
    }

    public void setExiste(boolean existe) {
        this.existe = existe;
    }

    public Conexion getDatoConexion() {
        return datoConexion;
    }

    public void setDatoConexion(Conexion datoConexion) {
        this.datoConexion = datoConexion;
    }
}
