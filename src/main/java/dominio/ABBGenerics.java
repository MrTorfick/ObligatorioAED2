package dominio;

public class ABBGenerics<T extends ObjetoComparable<T>> {

    private class NodoABBGeneric {
        private T dato;
        private NodoABBGeneric izq;
        private NodoABBGeneric der;


        public NodoABBGeneric(T dato) {
            this.dato = dato;
        }

    }

    private NodoABBGeneric raiz;

    public void agregar(T datoAgregar) {
        this.raiz = agregar(raiz, datoAgregar);
    }

    public boolean existe(T cascaron) {
        return existe(raiz, cascaron);
    }

    public boolean existe(NodoABBGeneric nodo, T datoBuscar) {
        if (nodo == null) return false;
        else if (datoBuscar.esMayor(nodo.dato)) return existe(nodo.der, datoBuscar);
        else if (datoBuscar.esMenor(nodo.dato)) return existe(nodo.izq, datoBuscar);
        else return true;
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
        } else if (datoAgregar.esMayor(nodo.dato)) {
            nodo.der = agregar(nodo.der, datoAgregar);
            return nodo;
        } else if (datoAgregar.esMenor(nodo.dato)) {
            nodo.izq = agregar(nodo.izq, datoAgregar);
            return nodo;
        } else {
            throw new RuntimeException("Duplicado");
        }
    }


}
