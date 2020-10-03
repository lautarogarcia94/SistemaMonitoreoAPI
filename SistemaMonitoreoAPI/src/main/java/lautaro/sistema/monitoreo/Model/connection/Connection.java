package lautaro.sistema.monitoreo.Model.connection;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
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
import java.util.HashMap;
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


    public void insertarResultado(Resultado resultado) {
        DocumentReference docRef = firestoreDB.collection(COLLECTION).document(resultado.getFecha());
        Map<String, Object> data = new HashMap<>();
        data.put("diferencia", resultado.getDiferencia());
        data.put("promedio", resultado.getPromedio());
        ApiFuture<WriteResult> result = docRef.set(data);
        try {
            LOGGER.info("Update time : " + result.get().getUpdateTime());
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }

    }


}
