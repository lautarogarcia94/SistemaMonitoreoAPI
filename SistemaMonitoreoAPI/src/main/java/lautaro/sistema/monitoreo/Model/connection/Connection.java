package lautaro.sistema.monitoreo.Model.connection;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.*;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import lautaro.sistema.monitoreo.Model.MedicionQueue;
import lautaro.sistema.monitoreo.Model.Resultado;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class Connection {


    @Value("${jsonPath}")
    private String jsonPath;

    private final String COLLECTION = "resultado";

    private Firestore firestoreDB;

    private final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(MedicionQueue.class);

    @Autowired
    public void setFirestoreDB() {
        try {
            //FileInputStream serviceAccount = new FileInputStream("C:\\Users\\Usuario\\Desktop\\GitHub\\SistemaMonitoreo\\SistemaMonitoreoAPI\\sistemamonitoreoapi.json");
            FileInputStream serviceAccount = new FileInputStream(jsonPath);

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();
            FirebaseApp.initializeApp(options);
            firestoreDB = FirestoreClient.getFirestore();
        } catch (Exception e) {
            LOGGER.error("Error en Connection constructor: " + e.getMessage());
        }
    }


    /**
     * Este metodo genera una coleccion a partir del objeto Resultado pasado por parametro,
     * y lo inserta en Cloud Firestore
     *
     * @param resultado a insertar en Cloud Firestore
     */
    public void insertarResultado(Resultado resultado) {
        DocumentReference docRef = firestoreDB.collection(COLLECTION).document(resultado.getFecha());
        Map<String, Object> data = new HashMap<>();
        data.put("diferencia", resultado.getDiferencia());
        data.put("promedio", resultado.getPromedio());
        ApiFuture<WriteResult> result = docRef.set(data);
    }

    /**
     * Este metodo retorna una lista de Resultados, a partir de los datos almacenados en
     * Cloud Firestore
     *
     * @return List<Resultado>
     */
    public List<Resultado> getResultados() {
        ApiFuture<QuerySnapshot> query = firestoreDB.collection(COLLECTION).get();

        QuerySnapshot querySnapshot = null;
        try {
            querySnapshot = query.get();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }

        List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
        return generarListaResult(documents);
    }

    /**
     * Este metodo transforma una lista de QueryDocumentSnapshot en una lista de Resultados
     *
     * @param documents List<QueryDocumentSnapshot>
     * @return List<Resultado>
     */
    private List<Resultado> generarListaResult(List<QueryDocumentSnapshot> documents) {
        String fecha, diferencia, promedio;
        List<Resultado> lista = new ArrayList<>();

        for (QueryDocumentSnapshot document : documents) {
            fecha = document.getId();
            diferencia = document.get("diferencia").toString();
            promedio = document.get("promedio").toString();
            lista.add(new Resultado(fecha, diferencia, promedio));
        }

        return lista;
    }
}
