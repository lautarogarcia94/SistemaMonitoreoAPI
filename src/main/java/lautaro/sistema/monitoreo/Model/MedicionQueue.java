package lautaro.sistema.monitoreo.Model;

import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.Queue;

@Component
public class MedicionQueue {

    @Getter
    private Queue<Integer> cola;

    public void setCola(Queue<Integer> cola) {
        this.cola = cola;
    }

    public void agregarCola(int num){
        cola.add(num);
    }
}
