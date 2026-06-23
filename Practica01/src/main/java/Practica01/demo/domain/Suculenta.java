/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Practica01.demo.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Entidad que representa la tabla suculenta de la base de datos.
 *
 * Esta clase pertenece a la capa de dominio dentro del patrón MVC.
 * Su función es mapear los campos de la tabla suculenta a atributos Java.
 *
 * Tabla: suculenta
 *
 * @author natts
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
     * Ejemplo: Aloe Vera.
     */
    @Column(name = "nombre_comun")
    private String nombreComun;

    /**
     * Nombre científico de la suculenta.
     * Ejemplo: Aloe barbadensis miller.
     */
    @Column(name = "nombre_cientifico")
    private String nombreCientifico;

    /**
     * Familia botánica a la que pertenece la suculenta.
     */
    private String familia;

    /**
     * Color principal o característico de la planta.
     */
    @Column(name = "color_principal")
    private String colorPrincipal;

    /**
     * Altura aproximada en centímetros.
     */
    @Column(name = "altura_cm")
    private BigDecimal alturaCm;

    /**
     * Precio estimado de venta.
     */
    @Column(name = "precio_estimado")
    private BigDecimal precioEstimado;

    /**
     * Nivel de riego recomendado.
     * Ejemplo: Bajo, Medio o Alto.
     */
    @Column(name = "nivel_riego")
    private String nivelRiego;

    /**
     * Ruta o URL de la imagen almacenada en la nube.
     */
    @Column(name = "ruta_imagen")
    private String rutaImagen;

    public Suculenta() {
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
}
