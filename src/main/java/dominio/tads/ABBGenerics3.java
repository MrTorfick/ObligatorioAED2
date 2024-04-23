package dominio.tads;

import java.util.Comparator;

public class ABBGenerics3<T> {

    private class NodoABBGeneric {
        private T dato;
        private NodoABBGeneric izq;
        private NodoABBGeneric der;


        public NodoABBGeneric(T dato) {
            this.dato = dato;
        }

    }

    private NodoABBGeneric raiz;
    private final Comparator<T> comparador;

    public ABBGenerics3(Comparator<T> comparador) {
        this.comparador = comparador;
    }

    public void agregar(T datoAgregar) {
        this.raiz = agregar(raiz, datoAgregar);
    }

    public boolean existe(T cascaron) {
        return existe(raiz, cascaron);
    }

    private boolean existe(NodoABBGeneric nodo, T datoBuscar) {
        if (nodo == null) return false;
        else if (datoBuscar.equals(nodo.dato)) return true;
        else if (comparador.compare(datoBuscar, nodo.dato) > 0) return existe(nodo.der, datoBuscar);
        else return existe(nodo.izq, datoBuscar);
    }

    public void imprimirOrdenado() {
        imprimirOrdenado(raiz);
    }

    private void imprimirOrdenado(NodoABBGeneric nodo) {
        if (nodo == null) return;
        imprimirOrdenado(nodo.izq);
        System.out.print(nodo.dato);
        System.out.print(",");
        imprimirOrdenado(nodo.der);
    }

    private NodoABBGeneric agregar(NodoABBGeneric nodo, T datoAgregar) {
        if (nodo == null) {
            return new NodoABBGeneric(datoAgregar);

        } else if (nodo.dato.equals(datoAgregar)) throw new RuntimeException("Duplicado");

        else if (comparador.compare(datoAgregar, nodo.dato) > 0) {
            nodo.der = agregar(nodo.der, datoAgregar);
            return nodo;
        } else {
            nodo.izq = agregar(nodo.izq, datoAgregar);
            return nodo;
        }
    }


}
