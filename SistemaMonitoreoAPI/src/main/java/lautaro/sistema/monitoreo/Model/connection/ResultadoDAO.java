package lautaro.sistema.monitoreo.Model.connection;

import lautaro.sistema.monitoreo.Model.Resultado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ResultadoDAO {


    private Connection connection;

    @Autowired
    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public void insertarResultado(Resultado resultado) {
        connection.insertarResultado(resultado);
    }
}
