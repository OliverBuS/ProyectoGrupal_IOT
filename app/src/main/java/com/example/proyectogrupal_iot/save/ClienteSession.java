package com.example.proyectogrupal_iot.save;

import com.example.proyectogrupal_iot.entities.Equipo;
import com.example.proyectogrupal_iot.entities.Historial;
import com.example.proyectogrupal_iot.entities.Solicitud;

import java.util.ArrayList;
import java.util.List;

public class ClienteSession {

    private static List<Equipo> equipoList = new ArrayList<>();
    private static List<Equipo> equipoFiltrado = new ArrayList<>();
    private static boolean filtro = false;
    private static List<Solicitud> solicitudes = new ArrayList<>();
    private static List<Historial> historialLista = new ArrayList<>();
    private static String marcaFiltro = "";
    private static String dispositivoFiltro = "";


    public static void setEquipos(List<Equipo> list) {
        equipoList = list;
    }

    public static List<Equipo> getEquipos() {
        return equipoList;
    }

    public static void setFiltro(boolean value) {
        filtro = value;
    }

    public static boolean checkfiltro() {
        return filtro;
    }

    public static void setSolicitudes(List<Solicitud> items) {
        solicitudes = items;
    }

    public static List<Solicitud> getSolicitudes() {
        return solicitudes;
    }

    public static List<Historial> getHistorialLista() {
        return historialLista;
    }

    public static void setHistorialLista(List<Historial> historialLista) {
        ClienteSession.historialLista = historialLista;
    }

    public static String getMarcaFiltro() {
        return marcaFiltro;
    }

    public static void setMarcaFiltro(String marcaFiltro) {
        ClienteSession.marcaFiltro = marcaFiltro;
    }

    public static String getDispositivoFiltro() {
        return dispositivoFiltro;
    }

    public static void setDispositivoFiltro(String dispositivoFiltro) {
        ClienteSession.dispositivoFiltro = dispositivoFiltro;
    }
}
