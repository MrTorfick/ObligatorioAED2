package test_propios;

import interfaz.Retorno;
import interfaz.Sistema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sistema.ImplementacionSistema;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test17_Personalizado {


    private Sistema sistema;
    private Retorno retorno;

    @BeforeEach
    void setUp() {
        sistema = new ImplementacionSistema();
        sistema.inicializarSistema(10, 10);

    }


    private void cargarSetAeropuertosyConexiones2() {

        sistema.registrarAerolinea("1", "Aerolinea1");

        sistema.registrarAeropuerto(new String("1"), new String("Aeropuerto1"));
        sistema.registrarAeropuerto(new String("2"), new String("Aeropuerto2"));
        sistema.registrarAeropuerto(new String("3"), new String("Aeropuerto3"));
        sistema.registrarAeropuerto(new String("4"), new String("Aeropuerto4"));
        sistema.registrarAeropuerto(new String("5"), new String("Aeropuerto5"));
        sistema.registrarAeropuerto(new String("6"), new String("Aeropuerto6"));
        sistema.registrarAeropuerto(new String("7"), new String("Aeropuerto7"));
        sistema.registrarAeropuerto(new String("8"), new String("Aeropuerto8"));

        sistema.registrarConexion(new String("1"), new String("2"), 10);
        sistema.registrarConexion(new String("1"), new String("3"), 10);
        sistema.registrarConexion(new String("1"), new String("6"), 10);
        sistema.registrarConexion(new String("2"), new String("4"), 10);
        sistema.registrarConexion(new String("3"), new String("4"), 10);
        sistema.registrarConexion(new String("3"), new String("6"), 10);
        sistema.registrarConexion(new String("3"), new String("5"), 10);
        sistema.registrarConexion(new String("4"), new String("8"), 10);
        sistema.registrarConexion(new String("5"), new String("8"), 10);

    }


    @Test
    void deberiaImprimir4AeropuertosAerolinea2() {
        cargarSetAeropuertosyConexiones2();
        sistema.registrarAerolinea("2", "Aerolinea2");

        sistema.registrarVuelo("1", "2", "1", 100, 10, 20, "1");
        sistema.registrarVuelo("1", "3", "2", 100, 10, 20, "1");
        sistema.registrarVuelo("3", "6", "2", 100, 10, 20, "1");
        sistema.registrarVuelo("3", "5", "3", 100, 10, 20, "1");
        sistema.registrarVuelo("5", "8", "3", 100, 10, 20, "1");

        sistema.registrarVuelo("1", "3", "20", 100, 10, 20, "2");
        sistema.registrarVuelo("1", "6", "30", 100, 10, 20, "2");
        sistema.registrarVuelo("3", "4", "10", 100, 10, 20, "2");
        sistema.registrarVuelo("4", "8", "15", 100, 10, 20, "2");

        retorno = sistema.listadoAeropuertosCantDeEscalas(new String("1"), 2, "2");

        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("1;Aeropuerto1|3;Aeropuerto3|4;Aeropuerto4|6;Aeropuerto6", retorno.getValorString());
    }

    @Test
    void deberiaImprimir5AeropuertosAerolinea1() {
        cargarSetAeropuertosyConexiones2();
        sistema.registrarAerolinea("2", "Aerolinea2");
        sistema.registrarConexion(new String("8"), new String("7"), 10);


        sistema.registrarVuelo("1", "2", "1", 100, 10, 20, "1");
        sistema.registrarVuelo("1", "3", "2", 100, 10, 20, "1");
        sistema.registrarVuelo("3", "6", "21", 100, 10, 20, "1");
        sistema.registrarVuelo("3", "5", "3", 100, 10, 20, "1");
        sistema.registrarVuelo("5", "8", "13", 100, 10, 20, "1");
        sistema.registrarVuelo("8", "7", "31", 100, 10, 20, "1");

        sistema.registrarVuelo("1", "3", "24", 100, 10, 20, "2");
        sistema.registrarVuelo("1", "6", "34", 100, 10, 20, "2");
        sistema.registrarVuelo("3", "4", "14", 100, 10, 20, "2");
        sistema.registrarVuelo("4", "5", "17", 100, 10, 20, "2");
        retorno = sistema.listadoAeropuertosCantDeEscalas(new String("1"), 2, "1");

        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("1;Aeropuerto1|2;Aeropuerto2|3;Aeropuerto3|5;Aeropuerto5|6;Aeropuerto6", retorno.getValorString());
    }

}