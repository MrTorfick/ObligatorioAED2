package dominio.tads;


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


    private boolean VerificarCapacidad() {
        if (cantElementos == cantMax) {
            return true;
        } else {
            return false;
        }
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


    public void borrarFin() {

        if (!esVacia()) {
            if (cantElementos == 1) {
                this.vaciar();
            } else {
                Nodo borrar = fin;
                fin = fin.getAnterior();
                borrar.setAnterior(null);
                fin.setSiguiente(null);
                cantElementos--;
            }
        }

    }


    public void vaciar() {
        inicio = null;
        fin = null;
        cantElementos = 0;
    }


    public void mostrar() {
        Nodo aux = inicio;
        while (aux != null) {
            System.out.println(aux.getDato());
            aux = aux.getSiguiente();
        }
    }

    /*
     * Pre: No hay
     * Post: Se muestra la lista recursivamente
     * */
    public void mostrarRecursivo() {
        mostrarRecursivo(inicio);
    }

    private void mostrarRecursivo(Nodo aux) {
        if (aux != null) {
            System.out.println(aux.getDato());
            System.out.println(" ");
            mostrarRecursivo(aux.getSiguiente());
        }
    }


    public void borrarElemento(T n) {
        if (!esVacia()) {
            if (inicio.getDato().equals(n)) {
                borrarInicio();
                cantElementos--;
            } else if (fin.getDato().equals(n)) {
                borrarFin();
                cantElementos--;
            } else {
                Nodo aux = inicio;
                while (aux.getSiguiente() != null && !aux.getSiguiente().getDato().equals(n)) {
                    aux = aux.getSiguiente();
                }
                if (aux.getSiguiente() != null) {
                    Nodo aBorrar = aux.getSiguiente();
                    aux.setSiguiente(aBorrar.getSiguiente());
                    aBorrar.setSiguiente(null);
                    cantElementos--;
                }
            }
        }

    }


    public int cantElementos() {
        return cantElementos;
    }


    /*

    public Nodo obtenerElemento(T n) {

        Nodo aux = inicio;
        Nodo ret = null;
        boolean encontre = false;

        while (aux != null && !encontre) {

            if (aux.getDato().equals(n)) {
                ret = aux;
                encontre = true;
            }
            aux = aux.getSiguiente();
        }
        return ret;

    }
    */

    //Metodo que devuelve todos los elementos de la lista
    public T[] obtenerElementos() {
        T[] elementos = (T[]) new Object[cantElementos];
        Nodo aux = inicio;
        int i = 0;
        while (aux != null) {
            elementos[i] = (T) aux.getDato();
            aux = aux.getSiguiente();
            i++;
        }
        return elementos;
    }


    public T obtenerElemento(T n) {
        Nodo aux = inicio;
        while (aux != null) {
            if (aux.getDato().equals(n)) {
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

    /*
     * Pre: El elemento no puede ser nulo
     * El elemento debe ser una cola
     * Post: Agrega los elementos de la cola a la lista de forma ordenada
     * */
    /*
    public void agregarOrdenadoCola(Cola<T> cola) {
        Nodo aux = (Nodo) cola.front();
        while (aux != null) {
            this.agregarOrdenado((T) aux.getDato());
            aux = aux.getSiguiente();
        }
    }

    /*
     * Pre: El elemento no puede ser nulo
     * El elemento debe ser una lista
     * Post: Agrega los elementos de la lista a la lista de forma ordeanda
     * */

    /*
    public void agregarOrdenadoListas(Lista<T> lista) {
        Nodo aux = lista.getInicio();
        while (aux != null) {
            this.agregarOrdenado((T) aux.getDato());
            aux = aux.getSiguiente();
        }
    }


    public void agregarOrdenado(T x) {
        if (esVacia() || inicio.getDato().compareTo(x) >= 0) {
            this.agregarInicio(x);
        } else {
            Nodo aux = inicio;
            while (aux.getSiguiente() != null && aux.getSiguiente().getDato().compareTo(x) < 0) {
                aux = aux.getSiguiente();
            }
            if (aux.getSiguiente() == null) {
                this.agregarFinal(x);
            } else {
                Nodo nuevo = new Nodo(x);
                nuevo.setSiguiente(aux.getSiguiente());
                aux.setSiguiente(nuevo);
                cantElementos++;
            }
        }
    }
    */


}
