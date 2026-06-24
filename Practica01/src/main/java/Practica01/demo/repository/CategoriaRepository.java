/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Practica01.demo.repository;

import Practica01.demo.domain.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio para la tabla categoria.
 *
 * JpaRepository permite usar findAll, findById, save y delete.
 *
 * @author Nataly Scholz
 */
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
}