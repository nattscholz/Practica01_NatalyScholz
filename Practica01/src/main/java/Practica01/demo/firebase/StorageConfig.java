/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Practica01.demo.firebase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import jakarta.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * Clase de configuración de Firebase Storage.
 *
 * Esta clase se encarga de leer la clave privada descargada desde Firebase
 * y de inicializar la conexión con el bucket de almacenamiento.
 *
 * Se ejecuta automáticamente cuando inicia la aplicación.
 *
 * @author Nataly Scholz
 */
@Configuration
public class StorageConfig {

    /**
     * Ruta del archivo JSON de credenciales de Firebase.
     *
     * Esta ruta se configura en application.properties.
     */
    @Value("${firebase.archivo-configuracion}")
    private String archivoConfiguracion;

    /**
     * Nombre del bucket de Firebase Storage.
     *
     * Este valor se configura en application.properties.
     */
    @Value("${firebase.bucket}")
    private String bucket;

    /**
     * Inicializa Firebase una sola vez al arrancar el proyecto.
     *
     * Si Firebase ya fue inicializado, no se vuelve a crear la conexión.
     *
     * @throws IOException si el archivo JSON no existe o no se puede leer
     */
    @PostConstruct
    public void inicializarFirebase() throws IOException {

        if (FirebaseApp.getApps().isEmpty()) {

            FileInputStream serviceAccount = new FileInputStream(archivoConfiguracion);

            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setStorageBucket(bucket)
                    .build();

            FirebaseApp.initializeApp(options);
        }
    }
}

