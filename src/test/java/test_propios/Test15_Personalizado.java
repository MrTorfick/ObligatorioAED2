package test_propios;

import dominio.clases.Pasajero;
import interfaz.Categoria;
import interfaz.Retorno;
import interfaz.Sistema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sistema.ImplementacionSistema;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test15_Personalizado {


    private Sistema sistema;
    private Retorno retorno;

    @BeforeEach
    void setUp() {
        sistema = new ImplementacionSistema();
        sistema.inicializarSistema(10, 10);

    }


    @Test
    void listarAscendente() {
        sistema.registrarPasajero(new String("9.136.375-3"), new String("Juliana"), new String("1234"), Categoria.ESTANDAR);
        sistema.registrarPasajero(new String("9.135.139-2"), new String("Gaston"), new String("3456"), Categoria.PLATINO);
        sistema.registrarPasajero(new String("8.888.365-4"), new String("Alejandra"), new String("5634"), Categoria.FRECUENTE);
        sistema.registrarPasajero(new String("7.447.365-1"), new String("Gustavo"), new String("23456"), Categoria.ESTANDAR);


        retorno = sistema.listarPasajerosAscendente();
        String resEsperado = "7.447.365-1;Gustavo;23456;Estándar|8.888.365-4;Alejandra;5634;Frecuente|9.135.139-2;Gaston;3456;Platino|9.136.375-3;Juliana;1234;Estándar";
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals(resEsperado, retorno.getValorString());
    }

    @Test
    public void listarPasajerosAscendente_returnsSinglePasajero_whenOnePasajeroRegistered() {
        Pasajero pasajero = new Pasajero("123.456-7", "Draven", "12345678", Categoria.ESTANDAR);
        sistema.registrarPasajero(pasajero.getCedula(), pasajero.getNombre(), pasajero.getTelefono(), pasajero.getCategoria());
        String expected = pasajero.toString();
        Retorno retorno = sistema.listarPasajerosAscendente();
        assertEquals(expected, retorno.getValorString());
    }


}
