package lautaro.sistema.monitoreo.Model.connection;

import lautaro.sistema.monitoreo.Model.MedicionQueue;
import lautaro.sistema.monitoreo.Model.Resultado;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Clase que implementa el patron DAO para manejar las inserciones y consultas a la Firebase de Cloud Firestore
 */
@Component
public class ResultadoDAO {

    private final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(MedicionQueue.class);

    private Connection connection;

    @Autowired
    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    /**
     * Llama al metodo insertarResultado() de la clase connection para insertar el resultado en
     * Cloud Firestore
     *
     * @param resultado Resultado a insertar en Cloud Firestore
     */
    public void insertarResultado(Resultado resultado) {
        if(resultado == null){
            LOGGER.error("Resultado a ingresar es nulo. No se realiza la insercion");
            return;
        }
        if(resultado.getFecha() == null){
            LOGGER.error("El campo fecha, en el objeto Resutado a ingresar es nulo. No se realiza la insercion");
            return;
        }
        if(resultado.getDiferencia() == null){
            LOGGER.error("El campo diferencia, en el objeto Resutado a ingresar es nulo. No se realiza la insercion");
            return;
        }
        if(resultado.getPromedio() == null){
            LOGGER.error("El campo promedio, en el objeto Resutado a ingresar es nulo. No se realiza la insercion");
            return;
        }
        connection.insertarResultado(resultado);
    }

    /**
     * Llama al metodo getResultados() de la clase connection, y retorna la lista de Resultados
     * que devuelve este metodo
     *
     * @return List<Resultado>
     */
    public List<Resultado> getAllResultados() {
        return connection.getListResultados();
    }


}
