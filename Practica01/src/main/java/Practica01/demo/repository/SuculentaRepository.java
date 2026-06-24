/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Practica01.demo.repository;

import Practica01.demo.domain.Suculenta;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio para la tabla suculenta.
 *
 * @author Nataly Scholz
 */
public interface SuculentaRepository extends JpaRepository<Suculenta, Integer> {
}
