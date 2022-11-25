package com.example.proyectogrupal_iot.entities;

import java.io.Serializable;
import java.util.List;

public class Equipo implements Serializable {
    String dispositivo;
    String marca;
    String caracteristicas;
    String incluye;
    List<String> imagenes;



    public Equipo() {
    }

    public Equipo(String dispositivo, String marca, String caracteristicas, String incluye, List<String> imagenes) {
        this.dispositivo = dispositivo;
        this.marca = marca;
        this.caracteristicas = caracteristicas;
        this.incluye = incluye;
        this.imagenes = imagenes;
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

}
