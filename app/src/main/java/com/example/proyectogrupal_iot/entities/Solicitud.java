package com.example.proyectogrupal_iot.entities;

public class Solicitud {

    String key;
    String motivo;
    String curso;
    int tiempo;
    String programas;
    String imagen;
    String otros="";

    public Solicitud() {
    }

    public Solicitud(String motivo, String curso, int tiempo, String programas) {
        this.motivo = motivo;
        this.curso = curso;
        this.tiempo = tiempo;
        this.programas = programas;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public int getTiempo() {
        return tiempo;
    }

    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }

    public String getProgramas() {
        return programas;
    }

    public void setProgramas(String programas) {
        this.programas = programas;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getOtros() {
        return otros;
    }

    public void setOtros(String otros) {
        this.otros = otros;
    }
}
