/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Practica01.demo.controller;
import Practica01.demo.service.SuculentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * Controlador para manejar las solicitudes web relacionadas con suculentas.
 *
 * Esta clase pertenece a la capa de presentación del patrón MVC.
 * Recibe solicitudes del navegador, solicita datos al servicio y los envía
 * al modelo para que Thymeleaf los muestre en la vista.
 * 
 * @author natts
 */

@Controller
public class SuculentaController {

    @Autowired
    private SuculentaService suculentaService;

    /**
     * Muestra el listado dinámico de suculentas.
     *
     * Rutas disponibles:
     * - /productos
     * - /suculentas
     *
     * Se usan ambas rutas para mantener el menú solicitado con "Productos",
     * pero respetando también el dominio de la práctica.
     *
     * @param model objeto que transporta datos desde el controlador hacia la vista
     * @return vista producto/listado
     */
    
    @GetMapping({"/productos", "/suculentas"})
    public String listado(Model model) {
        var listaSuculentas = suculentaService.getSuculentas();

        model.addAttribute("suculentas", listaSuculentas);
        model.addAttribute("totalSuculentas", listaSuculentas.size());

        return "producto/listado";
    }
}
    
