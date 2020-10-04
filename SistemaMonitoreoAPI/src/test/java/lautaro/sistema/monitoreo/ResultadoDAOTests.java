package lautaro.sistema.monitoreo;

import lautaro.sistema.monitoreo.Model.Resultado;
import lautaro.sistema.monitoreo.Model.connection.Connection;
import lautaro.sistema.monitoreo.Model.connection.ResultadoDAO;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@TestMethodOrder(MethodOrderer.Alphanumeric.class)
public class ResultadoDAOTests {

    @Autowired
    private ResultadoDAO resultadoDAO;

    @Autowired
    private Connection connection;

    @Test
    void _1_insertarResultado() {
        int size = connection.cantidadColecciones();
        Resultado resultado = new Resultado();
        resultadoDAO.insertarResultado(resultado);
        assertEquals(size+1,connection.cantidadColecciones());
    }


    @Test
    void _2_insertarResultado() {
        Resultado resultado = new Resultado("Prueba Diferencia","Prueba Promedio");
        resultadoDAO.insertarResultado(resultado);
        String fecha = resultado.getFecha();
        Resultado resultadoQuery = connection.getResultado(fecha);
        assertTrue(resultado.equals(resultadoQuery));
    }
}
