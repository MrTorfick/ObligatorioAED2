package dominio.tads;

public class Pila<T> {
    private ListaGeneric<T> miLista = new ListaGeneric();


    public void apilar(T dato) {
        miLista.agregarInicio(dato);
    }

    public T desapilar() {
        return miLista.borrarInicio();
    }

    public boolean esVacia() {
        return miLista.esVacia();
    }
}
