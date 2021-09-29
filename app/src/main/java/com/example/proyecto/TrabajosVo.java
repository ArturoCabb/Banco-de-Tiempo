package com.example.proyecto;

public class TrabajosVo {

    private String nombreTrabajo;
    private String nombreTrabajador;
    private String descripcionTrabajo;
    private int fotoTrabajador;

    public TrabajosVo() {

    }

    public TrabajosVo(String nombreTrabajo, String nombreTrabajador, String descripcionTrabajo, int fotoTrabajador) {
        this.nombreTrabajo = nombreTrabajo;
        this.nombreTrabajador = nombreTrabajador;
        this.descripcionTrabajo = descripcionTrabajo;
        this.fotoTrabajador = fotoTrabajador;
    }

    public String getNombreTrabajo() {
        return nombreTrabajo;
    }

    public void setNombreTrabajo(String nombreTrabajo) {
        this.nombreTrabajo = nombreTrabajo;
    }

    public String getNombreTrabajador() {
        return nombreTrabajador;
    }

    public void setNombreTrabajador(String nombreTrabajador) {
        nombreTrabajador = nombreTrabajador;
    }

    public String getDescripcionTrabajo() {
        return descripcionTrabajo;
    }

    public void setDescripcionTrabajo() {
        descripcionTrabajo = descripcionTrabajo;
    }

    public int getFotoTrabajador() {
        return fotoTrabajador;
    }

    public void setFotoTrabajador(int fotoTrabajador) {
        this.fotoTrabajador = fotoTrabajador;
    }
}
