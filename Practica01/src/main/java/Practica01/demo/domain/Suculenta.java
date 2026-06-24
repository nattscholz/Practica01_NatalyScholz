/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Practica01.demo.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Entidad que representa la tabla suculenta de la base de datos.
 *
 * Esta clase pertenece a la capa de dominio del patrón MVC.
 * Su función es mapear los campos de la tabla suculenta a atributos Java.
 *
 * También contiene validaciones básicas para evitar registros vacíos
 * o valores numéricos negativos.
 *
 * @author Nataly Scholz
 */
@Entity
@Table(name = "suculenta")
public class Suculenta implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Llave primaria autoincremental de la tabla suculenta.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_suculenta")
    private Integer idSuculenta;

    /**
     * Nombre común de la suculenta.
     */
    @NotBlank(message = "El nombre común es obligatorio")
    @Column(name = "nombre_comun")
    private String nombreComun;

    /**
     * Nombre científico de la suculenta.
     */
    @NotBlank(message = "El nombre científico es obligatorio")
    @Column(name = "nombre_cientifico")
    private String nombreCientifico;

    /**
     * Familia botánica de la suculenta.
     */
    @NotBlank(message = "La familia es obligatoria")
    @Column(name = "familia")
    private String familia;

    /**
     * Color principal de la suculenta.
     */
    @NotBlank(message = "El color principal es obligatorio")
    @Column(name = "color_principal")
    private String colorPrincipal;

    /**
     * Altura aproximada en centímetros.
     */
    @NotNull(message = "La altura es obligatoria")
    @DecimalMin(value = "0.01", message = "La altura debe ser mayor a 0")
    @Column(name = "altura_cm")
    private BigDecimal alturaCm;

    /**
     * Precio estimado de la suculenta.
     */
    @NotNull(message = "El precio es obligatorio")
    @DecimalMin(value = "0.01", message = "El precio debe ser mayor a 0")
    @Column(name = "precio_estimado")
    private BigDecimal precioEstimado;

    /**
     * Nivel de riego recomendado.
     */
    @NotBlank(message = "El nivel de riego es obligatorio")
    @Column(name = "nivel_riego")
    private String nivelRiego;

    /**
     * URL de la imagen almacenada en la nube.
     */
    @Column(name = "ruta_imagen")
    private String rutaImagen;

    /**
     * Relación Muchos a Uno.
     *
     * Muchas suculentas pueden pertenecer a una sola categoría.
     * La columna id_categoria en la tabla suculenta funciona como llave foránea.
     */
    @ManyToOne
    @JoinColumn(name = "id_categoria")
    private Categoria categoria;

    public Suculenta() {
    }

    public Suculenta(Integer idSuculenta, String nombreComun, String nombreCientifico,
            String familia, String colorPrincipal, BigDecimal alturaCm,
            BigDecimal precioEstimado, String nivelRiego, String rutaImagen,
            Categoria categoria) {

        this.idSuculenta = idSuculenta;
        this.nombreComun = nombreComun;
        this.nombreCientifico = nombreCientifico;
        this.familia = familia;
        this.colorPrincipal = colorPrincipal;
        this.alturaCm = alturaCm;
        this.precioEstimado = precioEstimado;
        this.nivelRiego = nivelRiego;
        this.rutaImagen = rutaImagen;
        this.categoria = categoria;
    }

    public Integer getIdSuculenta() {
        return idSuculenta;
    }

    public void setIdSuculenta(Integer idSuculenta) {
        this.idSuculenta = idSuculenta;
    }

    public String getNombreComun() {
        return nombreComun;
    }

    public void setNombreComun(String nombreComun) {
        this.nombreComun = nombreComun;
    }

    public String getNombreCientifico() {
        return nombreCientifico;
    }

    public void setNombreCientifico(String nombreCientifico) {
        this.nombreCientifico = nombreCientifico;
    }

    public String getFamilia() {
        return familia;
    }

    public void setFamilia(String familia) {
        this.familia = familia;
    }

    public String getColorPrincipal() {
        return colorPrincipal;
    }

    public void setColorPrincipal(String colorPrincipal) {
        this.colorPrincipal = colorPrincipal;
    }

    public BigDecimal getAlturaCm() {
        return alturaCm;
    }

    public void setAlturaCm(BigDecimal alturaCm) {
        this.alturaCm = alturaCm;
    }

    public BigDecimal getPrecioEstimado() {
        return precioEstimado;
    }

    public void setPrecioEstimado(BigDecimal precioEstimado) {
        this.precioEstimado = precioEstimado;
    }

    public String getNivelRiego() {
        return nivelRiego;
    }

    public void setNivelRiego(String nivelRiego) {
        this.nivelRiego = nivelRiego;
    }

    public String getRutaImagen() {
        return rutaImagen;
    }

    public void setRutaImagen(String rutaImagen) {
        this.rutaImagen = rutaImagen;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}