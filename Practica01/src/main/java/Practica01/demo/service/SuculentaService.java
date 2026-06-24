/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Practica01.demo.service;

import Practica01.demo.domain.Categoria;
import Practica01.demo.domain.Suculenta;
import Practica01.demo.firebase.FirebaseStorageService;
import Practica01.demo.repository.CategoriaRepository;
import Practica01.demo.repository.SuculentaRepository;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 * Servicio de suculentas.
 *
 * Aquí se maneja la lógica de negocio para agregar, modificar, listar
 * y eliminar suculentas.
 *
 * @author Nataly Scholz
 */
@Service
public class SuculentaService {

    @Autowired
    private SuculentaRepository suculentaRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private FirebaseStorageService firebaseStorageService;

    /**
     * Lista todas las suculentas.
     *
     * @return lista de suculentas
     */
    @Transactional(readOnly = true)
    public List<Suculenta> getSuculentas() {
        return suculentaRepository.findAll();
    }

    /**
     * Busca una suculenta por id.
     *
     * @param suculenta objeto con idSuculenta
     * @return suculenta encontrada o null
     */
    @Transactional(readOnly = true)
    public Suculenta getSuculenta(Suculenta suculenta) {
        return suculentaRepository.findById(suculenta.getIdSuculenta()).orElse(null);
    }

    /**
     * Guarda o actualiza una suculenta.
     *
     * El idCategoria viene separado desde el formulario para evitar
     * que Hibernate intente guardar una categoría temporal.
     *
     * @param suculenta datos de la suculenta
     * @param imagenFile imagen seleccionada en el formulario
     * @param idCategoria id de la categoría seleccionada
     * @throws IOException error al subir imagen
     */
    @Transactional
    public void save(Suculenta suculenta, MultipartFile imagenFile, Integer idCategoria) throws IOException {

        /*
            1. Buscar la categoría real en la base de datos.
            Esto evita el error:
            unsaved transient instance of Categoria.
        */
        Categoria categoria = null;

        if (idCategoria != null) {
            categoria = categoriaRepository.findById(idCategoria).orElse(null);
        }

        suculenta.setCategoria(categoria);

        /*
            2. Si se sube una imagen nueva, se guarda en Firebase.
            Si no se sube imagen nueva, se mantiene rutaImagen con el campo oculto.
        */
        if (imagenFile != null && !imagenFile.isEmpty()) {
            String rutaImagen = firebaseStorageService.subirImagen(imagenFile);
            suculenta.setRutaImagen(rutaImagen);
        }

        /*
            3. Guardar en MySQL.
            Si idSuculenta viene vacío, crea.
            Si idSuculenta trae valor, actualiza.
        */
        suculentaRepository.save(suculenta);
    }

    /**
     * Elimina una suculenta.
     *
     * @param suculenta suculenta a eliminar
     */
    @Transactional
    public void delete(Suculenta suculenta) {
        suculentaRepository.delete(suculenta);
    }
}