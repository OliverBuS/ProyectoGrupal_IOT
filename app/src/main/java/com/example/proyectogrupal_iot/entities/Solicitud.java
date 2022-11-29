package com.example.proyectogrupal_iot.entities;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Solicitud {

    String key;
    String equipo;
    String motivo;
    String curso;
    int tiempo;
    String programas;
    String imagen;
    String dni;
    String otros="";
    String tipo;
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
    String fecha;
    String respuesta_motivo;


    public Solicitud() {
        this.fecha = dateFormat.format(Calendar.getInstance().getTime());
    }

    public Solicitud(String motivo, String curso, int tiempo, String programas) {
        this.motivo = motivo;
        this.curso = curso;
        this.tiempo = tiempo;
        this.programas = programas;
        this.fecha = dateFormat.format(Calendar.getInstance().getTime());
    }

    public String getFecha() {
        return fecha;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getEquipo() {
        return equipo;
    }

    public void setEquipo(String equipo) {
        this.equipo = equipo;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
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

    public String getRespuesta_motivo() {
        return respuesta_motivo;
    }

    public void setRespuesta_motivo(String respuesta_motivo) {
        this.respuesta_motivo = respuesta_motivo;
    }

}
