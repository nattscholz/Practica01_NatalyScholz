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
 * Servicio de categorías.
 *
 * Maneja la lógica para listar, buscar, guardar y eliminar categorías.
 *
 * @author Nataly Scholz
 */
@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    /**
     * Obtiene categorías.
     *
     * @param activo si es true, retorna solo categorías activas.
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
     * Busca una categoría por id.
     *
     * @param idCategoria id de la categoría
     * @return categoría encontrada o null
     */
    @Transactional(readOnly = true)
    public Categoria getCategoriaPorId(Integer idCategoria) {
        if (idCategoria == null) {
            return null;
        }

        return categoriaRepository.findById(idCategoria).orElse(null);
    }

    /**
     * Busca una categoría usando un objeto categoria.
     *
     * @param categoria objeto con idCategoria
     * @return categoría encontrada o null
     */
    @Transactional(readOnly = true)
    public Categoria getCategoria(Categoria categoria) {
        return categoriaRepository.findById(categoria.getIdCategoria()).orElse(null);
    }

    /**
     * Guarda o actualiza una categoría.
     *
     * @param categoria categoría recibida del formulario
     */
    @Transactional
    public void save(Categoria categoria) {
        categoriaRepository.save(categoria);
    }

    /**
     * Elimina una categoría.
     *
     * @param categoria categoría a eliminar
     */
    @Transactional
    public void delete(Categoria categoria) {
        categoriaRepository.delete(categoria);
    }
}