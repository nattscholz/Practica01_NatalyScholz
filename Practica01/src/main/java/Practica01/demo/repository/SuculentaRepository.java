/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Practica01.demo.repository;

import Practica01.demo.domain.Suculenta;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio de acceso a datos para la entidad Suculenta.
 *
 * Esta interfaz pertenece a la capa de datos del patrón MVC.
 * Hereda de JpaRepository para obtener operaciones básicas como:
 * findAll, findById, save y delete.
 *
 * @author natts
 */
public interface SuculentaRepository extends JpaRepository<Suculenta, Integer> {
}
