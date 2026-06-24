/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Practica01.demo.service;

import Practica01.demo.domain.Suculenta;
import Practica01.demo.firebase.FirebaseStorageService;
import Practica01.demo.repository.SuculentaRepository;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 * Servicio para manejar la lógica de negocio relacionada con suculentas.
 *
 * Esta clase pertenece a la capa de negocio del patrón MVC.
 * Permite listar, buscar, guardar y eliminar suculentas.
 * Además, integra Firebase Storage para subir imágenes a la nube.
 *
 * @author Nataly Scholz
 */

@Service
public class SuculentaService {

    @Autowired
    private SuculentaRepository suculentaRepository;

    @Autowired
    private FirebaseStorageService firebaseStorageService;

    /**
     * Obtiene todas las suculentas registradas en la base de datos.
     *
     * @return lista de suculentas
     */
    @Transactional(readOnly = true)
    public List<Suculenta> getSuculentas() {
        return suculentaRepository.findAll();
    }

    /**
     * Obtiene una suculenta específica según su id.
     *
     * @param suculenta objeto con el id de la suculenta
     * @return suculenta encontrada o null si no existe
     */
    @Transactional(readOnly = true)
    public Suculenta getSuculenta(Suculenta suculenta) {
        return suculentaRepository.findById(suculenta.getIdSuculenta()).orElse(null);
    }

    /**
     * Guarda o actualiza una suculenta.
     *
     * Si se recibe una imagen, se sube a Firebase Storage y se guarda
     * la URL generada dentro del atributo rutaImagen.
     *
     * @param suculenta objeto recibido desde el formulario
     * @param imagenFile archivo de imagen recibido desde el formulario
     * @throws IOException si ocurre un error al subir la imagen
     */
    @Transactional
    public void save(Suculenta suculenta, MultipartFile imagenFile) throws IOException {

        if (imagenFile != null && !imagenFile.isEmpty()) {
            String rutaImagen = firebaseStorageService.subirImagen(imagenFile);
            suculenta.setRutaImagen(rutaImagen);
        }

        suculentaRepository.save(suculenta);
    }

    /**
     * Elimina una suculenta de la base de datos.
     *
     * @param suculenta objeto suculenta que se desea eliminar
     */
    @Transactional
    public void delete(Suculenta suculenta) {
        suculentaRepository.delete(suculenta);
    }
}