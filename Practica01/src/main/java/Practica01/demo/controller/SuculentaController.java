/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Practica01.demo.controller;

import Practica01.demo.domain.Suculenta;
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
import Practica01.demo.service.CategoriaService;

/**
 * Controlador para manejar las solicitudes web relacionadas con suculentas.
 *
 * Pertenece a la capa de presentación del patrón MVC.
 * Recibe solicitudes del navegador, consulta el servicio y envía datos
 * al modelo para renderizarlos en vistas Thymeleaf.
 *
 * En esta etapa se agregan validaciones para evitar guardar registros
 * incompletos o incorrectos.
 *
 * @author Nataly Scholz
 */
@Controller
public class SuculentaController {

    @Autowired
    private SuculentaService suculentaService;

    /**
     * Muestra el listado dinámico de suculentas.
     *
     * @param model objeto que permite enviar datos desde Java hacia Thymeleaf
     * @return vista suculenta/listado
     */
    @GetMapping({"/productos", "/suculentas"})
    public String listado(Model model) {
        var suculentas = suculentaService.getSuculentas();

        model.addAttribute("suculentas", suculentas);
        model.addAttribute("totalSuculentas", suculentas.size());

        return "suculenta/listado";
    }

    /**
     * Muestra el formulario para agregar una nueva suculenta.
     *
     * @param suculenta objeto vacío que se utilizará en el formulario
     * @return vista suculenta/modifica
     */
    @GetMapping("/suculenta/nuevo")
    public String suculentaNuevo(Suculenta suculenta, Model model) {
        var categorias = categoriaService.getCategorias(true);
        model.addAttribute("categorias", categorias);
        return "suculenta/modifica";
    }

    /**
     * Guarda una suculenta nueva o actualizada.
     *
     * Se utiliza POST porque el formulario puede enviar una imagen.
     * También se aplica @Valid para revisar las reglas definidas
     * en la entidad Suculenta.
     *
     * @param suculenta objeto recibido desde el formulario
     * @param errors contiene los errores encontrados por las validaciones
     * @param imagenFile archivo de imagen recibido desde el formulario
     * @return redirección al listado o regreso al formulario si hay errores
     * @throws IOException si ocurre un error al subir la imagen
     */
    @PostMapping("/suculenta/guardar")
    public String suculentaGuardar(@Valid Suculenta suculenta,
            Errors errors,
            @RequestParam("imagenFile") MultipartFile imagenFile,
            Model model) throws IOException {

        if (errors.hasErrors()) {
            var categorias = categoriaService.getCategorias(true);
            model.addAttribute("categorias", categorias);
            return "suculenta/modifica";
        }

        suculentaService.save(suculenta, imagenFile);
        return "redirect:/suculentas";
    }

    /**
     * Muestra el formulario con la información de una suculenta existente.
     *
     * @param suculenta objeto con el id de la suculenta seleccionada
     * @param model objeto que envía la suculenta encontrada a la vista
     * @return vista suculenta/modifica
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
     * Elimina una suculenta de la base de datos.
     *
     * @param suculenta objeto con el id de la suculenta seleccionada
     * @return redirección al listado de suculentas
     */
    @GetMapping("/suculenta/eliminar/{idSuculenta}")
    public String suculentaEliminar(Suculenta suculenta) {
        suculentaService.delete(suculenta);
        return "redirect:/suculentas";
    }
    
    @Autowired
    private CategoriaService categoriaService;
}