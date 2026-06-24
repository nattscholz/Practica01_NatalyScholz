/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Practica01.demo.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Entidad que representa la tabla categoria de la base de datos.
 *
 * Esta clase se relaciona con Suculenta mediante una asociación
 * Uno a Muchos, ya que una categoría puede tener muchas suculentas.
 *
 * @author Nataly Scholz
 */
@Entity
@Table(name = "categoria")
public class Categoria implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Llave primaria autoincremental de la tabla categoria.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_categoria")
    private Integer idCategoria;

    /**
     * Nombre de la categoría.
     * Ejemplo: Interior, Exterior o Colección.
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
     * Permite indicar si la categoría está activa o no.
     */
    @Column(name = "activo")
    private boolean activo;

    /**
     * Relación Uno a Muchos.
     *
     * Una categoría puede tener muchas suculentas.
     * El mappedBy indica que la relación se controla desde el atributo
     * categoria dentro de la clase Suculenta.
     */
    @OneToMany(mappedBy = "categoria")
    private List<Suculenta> suculentas;

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

    public List<Suculenta> getSuculentas() {
        return suculentas;
    }

    public void setSuculentas(List<Suculenta> suculentas) {
        this.suculentas = suculentas;
    }
}