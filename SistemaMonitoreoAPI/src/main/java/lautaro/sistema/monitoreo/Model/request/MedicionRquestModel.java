package lautaro.sistema.monitoreo.Model.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * Clase para modelar el objeto recibido mediante un POST Request. Al generarse una instancia de
 * esta clase, se valida si el valor para la medicion es mayor o igual que 0, y menor o igual que
 * 200
 */
public class MedicionRquestModel {

    @Max(value = 200)
    @Min(value = 0)
    private int medicion;

    public int getMedicion() {
        return medicion;
    }

    public void setMedicion(int medicion) {
        this.medicion = medicion;
    }
}
