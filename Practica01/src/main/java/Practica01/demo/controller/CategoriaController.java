/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Practica01.demo.controller;

import Practica01.demo.domain.Categoria;
import Practica01.demo.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controlador para manejar las solicitudes relacionadas con categorías.
 *
 * Permite listar, crear, modificar y eliminar categorías de suculentas.
 *
 * @author natts
 */
@Controller
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    /**
     * Muestra el listado de categorías.
     *
     * @param model objeto que permite enviar datos a la vista
     * @return vista categoria/listado
     */
    @GetMapping("/categorias")
    public String listado(Model model) {
        var categorias = categoriaService.getCategorias(false);
        model.addAttribute("categorias", categorias);
        model.addAttribute("totalCategorias", categorias.size());

        return "categoria/listado";
    }

    /**
     * Muestra el formulario para crear una nueva categoría.
     *
     * @param categoria objeto vacío para el formulario
     * @return vista categoria/modifica
     */
    @GetMapping("/categoria/nuevo")
    public String categoriaNuevo(Categoria categoria) {
        return "categoria/modifica";
    }

    /**
     * Guarda una categoría nueva o modificada.
     *
     * @param categoria objeto recibido desde el formulario
     * @return redirección al listado de categorías
     */
    @GetMapping("/categoria/guardar")
    public String categoriaGuardar(Categoria categoria) {
        categoriaService.save(categoria);
        return "redirect:/categorias";
    }

    /**
     * Muestra el formulario para modificar una categoría existente.
     *
     * @param categoria objeto con el id de la categoría
     * @param model objeto que envía la categoría encontrada
     * @return vista categoria/modifica
     */
    @GetMapping("/categoria/modificar/{idCategoria}")
    public String categoriaModificar(Categoria categoria, Model model) {
        categoria = categoriaService.getCategoria(categoria);
        model.addAttribute("categoria", categoria);
        return "categoria/modifica";
    }

    /**
     * Elimina una categoría.
     *
     * @param categoria objeto con el id de la categoría
     * @return redirección al listado
     */
    @GetMapping("/categoria/eliminar/{idCategoria}")
    public String categoriaEliminar(Categoria categoria) {
        categoriaService.delete(categoria);
        return "redirect:/categorias";
    }
}
