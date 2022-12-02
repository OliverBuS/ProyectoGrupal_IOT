package com.example.proyectogrupal_iot.entities;

public class Cuenta {

    private String rol;
    private String correo;
    private String codigo;
    private String imgurl;

    public Cuenta() {
    }

    public Cuenta(String rol, String correo, String codigo) {
        this.rol = rol;
        this.correo = correo;
        this.codigo = codigo;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }
}
