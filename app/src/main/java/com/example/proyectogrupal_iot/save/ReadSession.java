package com.example.proyectogrupal_iot.save;

public class ReadSession {
    String rol="";
    String codigo="";

    public ReadSession(){
        this.rol ="";
        this.codigo ="";
    }

    public ReadSession(String rol, String codigo) {
        this.rol = rol;
        this.codigo = codigo;
    }


    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
}
