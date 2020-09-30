package lautaro.sistema.monitoreo.Model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

    public Resultado(String diferencia, String promedio) {
        this.diferencia = diferencia;
        this.promedio = promedio;

        LocalDateTime fecha = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss");
        this.fecha = fecha.format(formatter);
    }

    public Resultado() {
        LocalDateTime fecha = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss");
        this.fecha = fecha.format(formatter);
    }
}
