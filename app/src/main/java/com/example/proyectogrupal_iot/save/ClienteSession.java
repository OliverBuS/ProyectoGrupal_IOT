package com.example.proyectogrupal_iot.save;

import com.example.proyectogrupal_iot.entities.Equipo;

import java.util.ArrayList;
import java.util.List;

public class ClienteSession {

    private static List<Equipo> equipoList = new ArrayList<>();
    private static List<Equipo> equipoFiltrado = new ArrayList<>();

    public static void setEquipos(List<Equipo> list){
        equipoList = list;
    }

    public static List<Equipo>  getEquipos(){
        return equipoList;
    }


}
