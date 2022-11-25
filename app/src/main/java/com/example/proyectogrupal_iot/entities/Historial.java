package com.example.proyectogrupal_iot.entities;

public class Historial extends Solicitud{
    private String fecha_respuesta;
    private String estado;
    private String respuesta_motivo;

    public Historial() {
    }

    public String getFecha_respuesta() {
        return fecha_respuesta;
    }

    public void setFecha_respuesta(String fecha_respuesta) {
        this.fecha_respuesta = fecha_respuesta;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getRespuesta_motivo() {
        return respuesta_motivo;
    }

    public void setRespuesta_motivo(String respuesta_motivo) {
        this.respuesta_motivo = respuesta_motivo;
    }
}
