package com.example.proyectogrupal_iot.save;

import com.example.proyectogrupal_iot.entities.Equipo;
import com.example.proyectogrupal_iot.entities.Solicitud;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class TISession {

    private static List<Equipo> equipoList = new ArrayList<>();
    private static List<Solicitud> solicitudes = new ArrayList<>();
    private static Set<String> marcas = new HashSet<>();

    public static void addMarca(String marca) {
        marcas.add(marca);
    }

    public static Set<String> getMarcas() {
        return marcas;
    }

    public static void setEquipos(List<Equipo> list) {
        equipoList = list;
    }

    public static List<Equipo> getEquipos() {
        return equipoList;
    }

    public static void setSolicitudes(List<Solicitud> items) {
        solicitudes = items;
    }

    public static List<Solicitud> getSolicitudes() {
        return solicitudes;
    }
}
