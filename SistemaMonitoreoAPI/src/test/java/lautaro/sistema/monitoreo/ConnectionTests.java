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
import static org.junit.jupiter.api.Assertions.assertNull;
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
        Resultado resultado = new Resultado("Prueba Diferencia","Prueba Promedio");
        connection.insertarResultado(resultado);
        String fecha = resultado.getFecha();
        Resultado resultadoQuery = connection.getResultado(fecha);
        assertTrue(resultado.equals(resultadoQuery));
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

    @Test
    void _7_getResultadosNulo() {
        Resultado resultado = connection.getResultado("aaaa"); //busqueda por campo fecha con un parametro que no existe
        assertNull(resultado);
    }
}
