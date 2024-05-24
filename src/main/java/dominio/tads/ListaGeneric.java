package dominio.tads;


import java.util.Comparator;

public class ListaGeneric<T> {

    private Nodo<T> inicio;
    private Nodo<T> fin;
    private int cantElementos;
    private int cantMax;


    private class Nodo<T> {
        private T dato;
        private Nodo<T> siguiente;
        private Nodo<T> anterior;


        public Nodo(T dato) {
            this.dato = dato;
            siguiente = null;
            anterior = null;
        }

        public Nodo<T> getSiguiente() {
            return siguiente;
        }

        public void setSiguiente(Nodo<T> siguiente) {
            this.siguiente = siguiente;
        }

        public Nodo<T> getAnterior() {
            return anterior;
        }

        public void setAnterior(Nodo<T> anterior) {
            this.anterior = anterior;
        }

        public T getDato() {
            return dato;
        }

        public void setDato(T dato) {
            this.dato = dato;
        }
    }


    public ListaGeneric() {
        inicio = null;
        fin = null;
        cantElementos = 0;
    }

    public ListaGeneric(int tope) {
        inicio = null;
        fin = null;
        cantElementos = 0;
        cantMax = tope;
    }


    public boolean esVacia() {
        return inicio == null || fin == null;
    }

    public Nodo getInicio() {
        return inicio;
    }

    public int getTope() {
        return cantMax;
    }

    public Nodo getFin() {
        return fin;
    }

    public void setInicio(Nodo inicio) {
        this.inicio = inicio;
    }

    public void setFin(Nodo fin) {
        this.fin = fin;
    }


    public void agregarInicio(T n) {

        Nodo nuevo = new Nodo(n);
        if (esVacia()) {
            inicio = nuevo;
            fin = nuevo;
        } else {
            nuevo.setSiguiente(inicio);
            inicio.setAnterior(nuevo);
            inicio = nuevo;
        }
        cantElementos++;

    }


    public void agregarFinal(T n) {

        if (esVacia()) {
            agregarInicio(n);
        } else {

            Nodo nuevo = new Nodo(n);
            fin.setSiguiente(nuevo);
            nuevo.setAnterior(fin);
            fin = nuevo;
            cantElementos++;
        }

    }


    public T borrarInicio() {

        if (!esVacia()) {
            T dato = inicio.getDato();
            if (cantElementos == 1) {
                vaciar();
            } else {
                Nodo borrar = inicio;
                inicio = inicio.getSiguiente();
                borrar.setSiguiente(null);
                inicio.setAnterior(null);
                cantElementos--;
            }
            return dato;
        }
        return null;
    }


    public void vaciar() {
        inicio = null;
        fin = null;
        cantElementos = 0;
    }


    public int cantElementos() {
        return cantElementos;
    }

    public T obtenerMinimo(Comparator<T> comparator) {
        T menor = inicio.getDato();
        Nodo<T> aux = inicio;
        while (aux != null) {

            if (comparator.compare(aux.getDato(), menor) < 0) {
                menor = aux.getDato();
            }
            aux = aux.getSiguiente();
        }
        return menor;
    }


    public T obtenerElemento(T n, Comparator<T> comparator) {
        Nodo aux = inicio;
        while (aux != null) {
            if (comparator.compare((T) aux.getDato(), n) == 0) {
                return (T) aux.getDato();
            }
            aux = aux.getSiguiente();
        }
        return null;
    }


    public boolean existeDato(T n) {

        boolean existe = false;

        Nodo aux = inicio;
        if (esVacia()) {
            return false;
        } else {
            if (inicio.getDato().equals(n) || fin.getDato().equals(n)) {
                return true;
            } else {
                while (aux != null && !existe) {
                    if (aux.getDato().equals(n)) {
                        existe = true;
                    }
                    aux = aux.getSiguiente();
                }
            }
        }
        return existe;

    }


}
