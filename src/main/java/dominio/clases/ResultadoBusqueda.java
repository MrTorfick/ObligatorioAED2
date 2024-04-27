package dominio.clases;

public class ResultadoBusqueda<T> {

    private T dato;
    private int nodosVisitados;

    public ResultadoBusqueda(T dato, int nodosVisitados) {
        this.dato = dato;
        this.nodosVisitados = nodosVisitados;
    }

    public T getDato() {
        return dato;
    }

    public void setDato(T dato) {
        this.dato = dato;
    }

    public int getNodosVisitados() {
        return nodosVisitados;
    }

    public void setNodosVisitados(int nodosVisitados) {
        this.nodosVisitados = nodosVisitados;
    }
}
