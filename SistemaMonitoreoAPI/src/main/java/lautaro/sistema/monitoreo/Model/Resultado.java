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

    public Resultado(String fecha, String diferencia, String promedio) {
        this.fecha = fecha;
        this.diferencia = diferencia;
        this.promedio = promedio;
    }

    @Override
    public boolean equals(Object obj) {

        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Resultado)) {
            return false;
        }
        Resultado resultado = (Resultado) obj;

        boolean equal = true;
        if(!this.fecha.equalsIgnoreCase(resultado.getFecha()))
            equal=false;
        if(!this.diferencia.equalsIgnoreCase(resultado.getDiferencia()))
            equal=false;
        if(!this.promedio.equalsIgnoreCase(resultado.getPromedio()))
            equal=false;

        return equal;
    }
}
