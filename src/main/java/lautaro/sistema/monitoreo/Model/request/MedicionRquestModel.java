package lautaro.sistema.monitoreo.Model.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

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
