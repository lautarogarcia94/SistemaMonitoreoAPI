package lautaro.sistema.monitoreo.Model.connection;

import lautaro.sistema.monitoreo.Model.Resultado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ResultadoDAO {


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
