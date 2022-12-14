package com.example.proyectogrupal_iot.entities;

import java.io.Serializable;
import java.util.List;

public class Equipo implements Serializable {


    private String key;
    private String dispositivo;
    private String marca;
    private String caracteristicas;
    private String incluye;
    private List<String> imagenes;
    private int stock;
    private String marcaOtro;



    public Equipo() {
    }

    public Equipo(String dispositivo, String marca, String caracteristicas, String incluye, List<String> imagenes, int stock) {
        this.dispositivo = dispositivo;
        this.marca = marca;
        this.caracteristicas = caracteristicas;
        this.incluye = incluye;
        this.imagenes = imagenes;
        this.stock = stock;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getDispositivo() {
        return dispositivo;
    }

    public void setDispositivo(String dispositivo) {
        this.dispositivo = dispositivo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(String caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    public String getIncluye() {
        return incluye;
    }

    public void setIncluye(String incluye) {
        this.incluye = incluye;
    }

    public void setImagenes(List<String> imagenes) {
        this.imagenes = imagenes;
    }

    public List<String> getImagenes() {
        return imagenes;
    }

    public String getImagenPrincipal(){
        return imagenes.get(0);
    }

    public List<String> getImagenesSecundarias(){
        return  imagenes.subList(1,0);
    }

    public String getMarcaOtro() {
        return marcaOtro;
    }

    public void setMarcaOtro(String marcaOtro) {
        this.marcaOtro = marcaOtro;
    }
}
