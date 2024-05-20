package dominio.tads;

import dominio.clases.ResultadoBusqueda;

import java.util.Comparator;

public class ABBGeneric3<T> {

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

    public ABBGeneric3(Comparator<T> comparador) {
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

    public String toUrl() {
        return VisualizadorGraphViz.arbolBinToUrl(raiz,
                n -> n.dato, n -> n.izq, n -> n.der);
    }


    public ResultadoBusqueda<T> obtener(T cascaron) {
        return obtener(raiz, cascaron, 0);
    }

    private ResultadoBusqueda<T> obtener(NodoABBGeneric nodo, T datoBuscar, int nodosVisitados) {
        if (nodo == null) {
            return new ResultadoBusqueda<>(null, nodosVisitados);
        }
        if (datoBuscar.equals(nodo.dato)) {
            return new ResultadoBusqueda<>(nodo.dato, nodosVisitados);
        } else if (comparador.compare(datoBuscar, nodo.dato) > 0) {
            ResultadoBusqueda<T> resultado = obtener(nodo.der, datoBuscar, nodosVisitados++);
            resultado.setNodosVisitados(resultado.getNodosVisitados() + nodosVisitados);
            return resultado;
        } else {
            ResultadoBusqueda<T> resultado = obtener(nodo.izq, datoBuscar, nodosVisitados++);
            resultado.setNodosVisitados(resultado.getNodosVisitados() + nodosVisitados);
            return resultado;
        }
    }

    /*
    public T obtener(T cascaron) {
        return obtener(raiz, cascaron);
    }

    private T obtener(NodoABBGeneric nodo, T datoBuscar) {
        if (nodo == null) return null;
        else if (datoBuscar.equals(nodo.dato)) return nodo.dato;
        else if (comparador.compare(datoBuscar, nodo.dato) > 0) return obtener(nodo.der, datoBuscar);
        else return obtener(nodo.izq, datoBuscar);
    }
    */

    /*
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
     */


    public int cantNodos() {
        return cantNodosRec(raiz);
    }

    private int cantNodosRec(NodoABBGeneric nodoActual) {
        if (nodoActual == null) {
            return 0;
        } else {
            int cantidadDelSubArbolIzquierdo = cantNodosRec(nodoActual.izq);
            int cantidadDelSubArbolDer = cantNodosRec(nodoActual.der);
            return cantidadDelSubArbolDer + cantidadDelSubArbolIzquierdo + 1;
        }
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





    public String inOrder(){
        StringBuilder sb = new StringBuilder();
        inOrderRec(raiz, sb);
        if(!sb.isEmpty()){
            sb.deleteCharAt(sb.length()-1);//Quitar barra final
        }
        return sb.toString();
    }

    private void inOrderRec(NodoABBGeneric nodoActual, StringBuilder sb) {
        if(nodoActual !=null){
            inOrderRec(nodoActual.izq,sb);
            sb.append(nodoActual.dato.toString()).append("|");
            //System.out.printf("%s", nodoActual.dato.);
            inOrderRec(nodoActual.der, sb);
        }
    }

    public String invertedInOrder(){
        StringBuilder sb = new StringBuilder();
        invertedInOrderRec(raiz, sb);
        if(!sb.isEmpty()){
            sb.deleteCharAt(sb.length()-1);
        }
        return sb.toString();
    }

    private void invertedInOrderRec(NodoABBGeneric nodoActual, StringBuilder sb) {
        if(nodoActual !=null){
            invertedInOrderRec(nodoActual.der,sb);
            sb.append(nodoActual.dato.toString()).append("|");
            invertedInOrderRec(nodoActual.izq, sb);
        }
    }






}
