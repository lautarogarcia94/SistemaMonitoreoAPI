package lautaro.sistema.monitoreo;

import lautaro.sistema.monitoreo.Model.Resultado;
import lautaro.sistema.monitoreo.Model.connection.Connection;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@TestMethodOrder(MethodOrderer.Alphanumeric.class)
public class ConnectionTests {

    @Autowired
    private Connection connection;

    @Test
    void _1_insertarResultado() {
        int size = connection.cantidadColecciones();
        Resultado resultado = new Resultado();
        connection.insertarResultado(resultado);
        assertEquals(size+1,connection.cantidadColecciones());
    }

    @Test
    void _2_insertarResultado() {
        int size = connection.cantidadColecciones();
        Resultado resultado = new Resultado("Prueba Diferencia","Prueba Promedio");
        connection.insertarResultado(resultado);
        String fecha = resultado.getFecha();
        Resultado resultadoQuery = connection.getResultado(fecha);
        String diferenciaQuery = resultadoQuery.getDiferencia();
        String promedioQuery = resultadoQuery.getPromedio();
        assertTrue(diferenciaQuery.equalsIgnoreCase("Prueba Diferencia"));
        assertTrue(promedioQuery.equalsIgnoreCase("Prueba Promedio"));
    }

    @Test
    void _3_insertarResultadoNulo() {
        Resultado resultado = null;
        int size = connection.cantidadColecciones();
        connection.insertarResultado(resultado);
        assertEquals(size,connection.cantidadColecciones());
    }

    @Test
    void _4_insertarResultadoDiferenciaNula() {
        Resultado resultado = new Resultado(null,"Prueba Promedio");
        int size = connection.cantidadColecciones();
        connection.insertarResultado(resultado);
        assertEquals(size+1,connection.cantidadColecciones());
    }

    @Test
    void _5_insertarResultadoPromedioNulo() {
        Resultado resultado = new Resultado("Prueba Diferencia",null);
        int size = connection.cantidadColecciones();
        connection.insertarResultado(resultado);
        assertEquals(size+1,connection.cantidadColecciones());
    }

    @Test
    void _6_getListaResultados() {
        List<Resultado> lista = connection.getListResultados();
        int size = connection.cantidadColecciones();
        assertEquals(size,lista.size());
    }
}
