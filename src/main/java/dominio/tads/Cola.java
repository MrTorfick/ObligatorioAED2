package dominio.tads;

public class Cola<T> {


    private ListaGeneric<T> miLista = new ListaGeneric();


    public void encolar(T dato){
        miLista.agregarFinal(dato);
    }


    public T desencolar(){
         return miLista.borrarInicio();
    }

    public boolean esVacia(){
        return miLista.esVacia();
    }
}
