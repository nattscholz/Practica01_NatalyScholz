/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Practica01.demo.service;

import Practica01.demo.domain.Categoria;
import Practica01.demo.repository.CategoriaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio para manejar la lógica de negocio relacionada con categorías.
 *
 * Esta clase permite obtener categorías desde la base de datos para usarlas
 * en el formulario de suculentas.
 *
 * @author Nataly Scholz
 */
@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    /**
     * Obtiene la lista de categorías.
     *
     * @param activo si es true, devuelve solo categorías activas;
     * si es false, devuelve todas las categorías.
     * @return lista de categorías
     */
    @Transactional(readOnly = true)
    public List<Categoria> getCategorias(boolean activo) {
        var categorias = categoriaRepository.findAll();

        if (activo) {
            categorias.removeIf(categoria -> !categoria.isActivo());
        }

        return categorias;
    }

    /**
     * Obtiene una categoría específica según su id.
     *
     * @param categoria objeto con el id de la categoría
     * @return categoría encontrada o null si no existe
     */
    @Transactional(readOnly = true)
    public Categoria getCategoria(Categoria categoria) {
        return categoriaRepository.findById(categoria.getIdCategoria()).orElse(null);
    }

    /**
     * Guarda o actualiza una categoría.
     *
     * @param categoria categoría que se desea guardar
     */
    @Transactional
    public void save(Categoria categoria) {
        categoriaRepository.save(categoria);
    }

    /**
     * Elimina una categoría.
     *
     * @param categoria categoría que se desea eliminar
     */
    @Transactional
    public void delete(Categoria categoria) {
        categoriaRepository.delete(categoria);
    }
}
