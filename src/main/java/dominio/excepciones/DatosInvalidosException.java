package dominio.excepciones;

import interfaz.Retorno;

public class DatosInvalidosException extends Exception {

   private Retorno r;
    public DatosInvalidosException(String message) {
        super(message);
    }

    public DatosInvalidosException(String message, Retorno r){
        super(message);
        this.r = r;
    }

    public Retorno getRetorno(){
        return r;
    }


}
