package dominio.tads;

public interface ObjetoComparable<T> extends Comparable<T> {

    boolean esMayor(T dato);

    boolean esMenor(T dato);


    @Override
    default int compareTo(T o) {
        if (esMayor(o)) return 1;
        else if (esMenor(o)) return -1;
        else return 0;
    }
}
