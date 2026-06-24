/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Practica01.demo.firebase;

import com.google.cloud.storage.Acl;
import com.google.cloud.storage.Blob;
import com.google.firebase.cloud.StorageClient;
import java.io.IOException;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * Servicio para subir imágenes de suculentas a Firebase Storage.
 *
 * Recibe un archivo desde el formulario HTML, lo guarda en el bucket
 * configurado y retorna una URL pública para almacenarla en MySQL.
 *
 * @author Nataly Scholz
 */
@Service
public class FirebaseStorageService {

    /**
     * Carpeta dentro del bucket donde se guardarán las imágenes.
     */
    @Value("${firebase.ruta-imagenes}")
    private String rutaImagenes;

    /**
     * URL base usada para construir la ruta pública de la imagen.
     */
    @Value("${firebase.url-base}")
    private String urlBase;

    /**
     * Nombre del bucket de Firebase Storage.
     */
    @Value("${firebase.bucket}")
    private String bucket;

    /**
     * Sube una imagen a Firebase Storage.
     *
     * @param archivo imagen recibida desde el formulario
     * @return URL pública de la imagen subida
     * @throws IOException si ocurre un error al leer o subir el archivo
     */
    public String subirImagen(MultipartFile archivo) throws IOException {

        if (archivo == null || archivo.isEmpty()) {
            return null;
        }

        String nombreOriginal = archivo.getOriginalFilename();
        String extension = "";

        if (nombreOriginal != null && nombreOriginal.contains(".")) {
            extension = nombreOriginal.substring(nombreOriginal.lastIndexOf("."));
        }

        String nombreArchivo = rutaImagenes + "/" + UUID.randomUUID() + extension;

        Blob blob = StorageClient.getInstance()
                .bucket()
                .create(nombreArchivo, archivo.getBytes(), archivo.getContentType());

        blob.createAcl(Acl.of(
                Acl.User.ofAllUsers(),
                Acl.Role.READER
        ));

        return urlBase + bucket + "/" + nombreArchivo;
    }
}