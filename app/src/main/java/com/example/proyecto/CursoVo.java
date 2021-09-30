package com.example.proyecto;

public class CursoVo {

    private String nombreCurso;
    private String estadoActividad;
    private String tiempoCurso;
    private int fotoTrabajador;

    public CursoVo() {

    }

    public CursoVo(String nombreCurso, String estadoActividad, String tiempoCurso, int fotoTrabajador) {
        this.nombreCurso = nombreCurso;
        this.estadoActividad = estadoActividad;
        this.tiempoCurso = tiempoCurso;
        this.fotoTrabajador = fotoTrabajador;
    }

    public String getNombreCurso() {
        return nombreCurso;
    }

    public void setNombreCurso(String nombreCurso) {
        this.nombreCurso = nombreCurso;
    }

    public String getEstadoActividad() {
        return estadoActividad;
    }

    public void setEstadoActividad(String estadoActividad) {
        estadoActividad = estadoActividad;
    }

    public String getTiempoCurso() {
        return tiempoCurso;
    }

    public void setTiempoCurso() {
        tiempoCurso = tiempoCurso;
    }

    public int getFotoTrabajador() {
        return fotoTrabajador;
    }

    public void setFotoTrabajador(int fotoTrabajador) {
        this.fotoTrabajador = fotoTrabajador;
    }
}
