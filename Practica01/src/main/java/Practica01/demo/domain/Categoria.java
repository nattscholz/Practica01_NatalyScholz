/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Practica01.demo.domain;

import jakarta.persistence.*;
import java.io.Serializable;

/**
 * Entidad que representa la tabla categoria.
 *
 * Una categoría permite clasificar las suculentas.
 *
 * @author Nataly Scholz
 */
@Entity
@Table(name = "categoria")
public class Categoria implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Llave primaria de la categoría.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_categoria")
    private Integer idCategoria;

    /**
     * Nombre de la categoría.
     */
    @Column(name = "nombre")
    private String nombre;

    /**
     * Descripción de la categoría.
     */
    @Column(name = "descripcion")
    private String descripcion;

    /**
     * Estado de la categoría.
     */
    @Column(name = "activo")
    private boolean activo;

    public Categoria() {
    }

    public Categoria(Integer idCategoria, String nombre, String descripcion, boolean activo) {
        this.idCategoria = idCategoria;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.activo = activo;
    }

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}