package lautaro.sistema.monitoreo.Model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
 * Esta clase almacena el resultado de los calculos realizados en la clase MedicionQueue
 */
public class Resultado {

    @Setter
    @Getter
    private String fecha;

    @Setter
    @Getter
    private String diferencia;

    @Setter
    @Getter
    private String promedio;

    public Resultado() {
        this("--", "--");
    }

    public Resultado(String diferencia, String promedio) {
        this.diferencia = diferencia;
        this.promedio = promedio;

        LocalDateTime fecha = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss");
        this.fecha = fecha.format(formatter);
    }
}
