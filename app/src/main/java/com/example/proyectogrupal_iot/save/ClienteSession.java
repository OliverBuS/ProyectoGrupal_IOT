package com.example.proyectogrupal_iot.save;

import com.example.proyectogrupal_iot.entities.Equipo;
import com.example.proyectogrupal_iot.entities.Historial;
import com.example.proyectogrupal_iot.entities.Solicitud;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ClienteSession {

    private static List<Equipo> equipoList = new ArrayList<>();
    private static List<Equipo> equipoFiltrado = new ArrayList<>();
    private static boolean marcaFiltred = false;
    private static boolean dispositivoFiltred = false;

    private static List<Solicitud> solicitudes = new ArrayList<>();
    private static List<Historial> historialLista = new ArrayList<>();
    private static String marcaFiltro = "";
    private static String dispositivoFiltro = "";
    private static Set<String> marcas = new HashSet<>();


    public static boolean isMarcaFiltred() {
        return marcaFiltred;
    }

    public static boolean isDispositivoFiltred() {
        return dispositivoFiltred;
    }

    public static void addMarca(String marca) {
        marcas.add(marca);
    }

    public static Set<String> getMarcas() {
        return marcas;
    }

    public static void setEquipos(List<Equipo> list) {
        equipoFiltrado=new ArrayList<>();
        equipoList = list;
    }

    public static List<Equipo> getEquipos() {

        if (!marcaFiltred && !dispositivoFiltred) {
            return equipoList;
        }


        if(!equipoFiltrado.isEmpty()){
            return equipoFiltrado;
        }

        if(marcaFiltred && dispositivoFiltred){
            for(Equipo i : equipoList){
                if(i.getMarca().equals(marcaFiltro) && i.getDispositivo().equals(dispositivoFiltro)) {
                    equipoFiltrado.add(i);
                }
            }
        } else if(marcaFiltred){
            for(Equipo i : equipoList){
                if(i.getMarca().equals(marcaFiltro)) {
                    equipoFiltrado.add(i);
                }
            }
        } else{
            for(Equipo i : equipoList){
                if(i.getDispositivo().equals(dispositivoFiltro)) {
                    equipoFiltrado.add(i);
                }
            }
        }

        return equipoFiltrado;

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
        marcaFiltred = !marcaFiltro.isEmpty();
        if (!ClienteSession.marcaFiltro.equals(marcaFiltro)) {
            ClienteSession.equipoFiltrado = new ArrayList<>();
        }
        ClienteSession.marcaFiltro = marcaFiltro;
    }

    public static String getDispositivoFiltro() {
        return dispositivoFiltro;
    }

    public static void setDispositivoFiltro(String dispositivoFiltro) {
        dispositivoFiltred = !dispositivoFiltro.isEmpty();
        if (!ClienteSession.dispositivoFiltro.equals(dispositivoFiltro)) {
            ClienteSession.equipoFiltrado = new ArrayList<>();
        }
        ClienteSession.dispositivoFiltro = dispositivoFiltro;
    }

    public static void finish(){
        equipoList = new ArrayList<>();
        equipoFiltrado = new ArrayList<>();
        marcaFiltred = false;
        dispositivoFiltred = false;
        solicitudes = new ArrayList<>();
        historialLista = new ArrayList<>();
        marcaFiltro = "";
        dispositivoFiltro = "";
        marcas = new HashSet<>();
    }

}
