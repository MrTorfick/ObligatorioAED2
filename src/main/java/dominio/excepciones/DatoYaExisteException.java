package dominio.excepciones;

public class DatoYaExisteException extends Exception {

    public DatoYaExisteException(String message) {
        super(message);
    }
}
