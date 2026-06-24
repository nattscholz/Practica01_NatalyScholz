/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Practica01.demo.controller;

import Practica01.demo.domain.Suculenta;
import Practica01.demo.service.CategoriaService;
import Practica01.demo.service.SuculentaService;
import jakarta.validation.Valid;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * Controlador de suculentas.
 *
 * Recibe solicitudes web y conecta las vistas con los servicios.
 *
 * @author Nataly Scholz
 */
@Controller
public class SuculentaController {

    @Autowired
    private SuculentaService suculentaService;

    @Autowired
    private CategoriaService categoriaService;

    /**
     * Muestra el listado de suculentas.
     *
     * @param model modelo para enviar datos a la vista
     * @return vista listado de suculentas
     */
    @GetMapping({"/productos", "/suculentas"})
    public String listado(Model model) {
        var suculentas = suculentaService.getSuculentas();

        model.addAttribute("suculentas", suculentas);
        model.addAttribute("totalSuculentas", suculentas.size());

        return "suculenta/listado";
    }

    /**
     * Muestra formulario para agregar una nueva suculenta.
     *
     * @param suculenta objeto vacío para el formulario
     * @param model modelo para enviar categorías
     * @return vista modifica
     */
    @GetMapping("/suculenta/nuevo")
    public String suculentaNuevo(Suculenta suculenta, Model model) {
        var categorias = categoriaService.getCategorias(true);

        model.addAttribute("categorias", categorias);

        return "suculenta/modifica";
    }

    /**
     * Guarda una suculenta nueva o modificada.
     *
     * @param suculenta objeto con datos del formulario
     * @param errors errores de validación
     * @param imagenFile imagen del formulario
     * @param idCategoria id de categoría seleccionada
     * @param model modelo para recargar categorías si hay errores
     * @return redirección o vista modifica
     * @throws IOException error al subir imagen
     */
    @PostMapping("/suculenta/guardar")
    public String suculentaGuardar(@Valid Suculenta suculenta,
            Errors errors,
            @RequestParam(value = "imagenFile", required = false) MultipartFile imagenFile,
            @RequestParam(value = "idCategoria", required = false) Integer idCategoria,
            Model model) throws IOException {

        if (errors.hasErrors()) {
            var categorias = categoriaService.getCategorias(true);
            model.addAttribute("categorias", categorias);
            return "suculenta/modifica";
        }

        suculentaService.save(suculenta, imagenFile, idCategoria);

        return "redirect:/suculentas";
    }

    /**
     * Muestra formulario para modificar una suculenta.
     *
     * @param suculenta objeto con id recibido por URL
     * @param model modelo para enviar datos
     * @return vista modifica
     */
    @GetMapping("/suculenta/modificar/{idSuculenta}")
    public String suculentaModificar(Suculenta suculenta, Model model) {
        suculenta = suculentaService.getSuculenta(suculenta);

        var categorias = categoriaService.getCategorias(true);

        model.addAttribute("suculenta", suculenta);
        model.addAttribute("categorias", categorias);

        return "suculenta/modifica";
    }

    /**
     * Elimina una suculenta.
     *
     * @param suculenta objeto con id recibido por URL
     * @return redirección al listado
     */
    @GetMapping("/suculenta/eliminar/{idSuculenta}")
    public String suculentaEliminar(Suculenta suculenta) {
        suculentaService.delete(suculenta);

        return "redirect:/suculentas";
    }
}