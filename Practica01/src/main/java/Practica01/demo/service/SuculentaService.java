/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Practica01.demo.service;

import Practica01.demo.domain.Suculenta;
import Practica01.demo.repository.SuculentaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio para manejar la lógica de negocio relacionada con suculentas.
 *
 * Esta clase pertenece a la capa de negocio del patrón MVC.
 * El controlador no accede directamente al repositorio, sino que utiliza
 * este servicio para mantener separadas las responsabilidades.
 * 
 * @author natts
 */

@Service
public class SuculentaService {

    @Autowired
    private SuculentaRepository suculentaRepository;

    /**
     * Obtiene todas las suculentas registradas en la base de datos.
     *
     * @return lista de suculentas
     */
    @Transactional(readOnly = true)
    public List<Suculenta> getSuculentas() {
        return suculentaRepository.findAll();
    }
}
