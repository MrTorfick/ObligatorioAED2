package test_propios;

import dominio.clases.Pasajero;
import interfaz.Categoria;
import interfaz.Retorno;
import interfaz.Sistema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sistema.ImplementacionSistema;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test16_Personalizado {


    private Sistema sistema;
    private Retorno retorno;

    @BeforeEach
    void setUp() {
        sistema = new ImplementacionSistema();
        sistema.inicializarSistema(10, 10);

    }

    @Test
    void deberiaRegistrarVuelo() {
        sistema.registrarAeropuerto(new String("1"), new String("Aeropuerto1"));
        sistema.registrarAeropuerto(new String("2"), new String("Aeropuerto2"));
        sistema.registrarAerolinea("1", "Aerolinea 1");
        sistema.registrarConexion(new String("1"), new String("2"), 10);
        sistema.registrarVuelo(new String("1"), new String("2"), new String("1"), 10, 10, 100, new String("1"));
        retorno = sistema.registrarVuelo(new String("1"), new String("2"), new String("2"), 10, 10, 100, new String("1"));
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
    }

}
