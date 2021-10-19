package com.example.proyecto;

public class TrabajosModel1 {

    private String key;
    private String correo;
    private String edad;
    private String hrfin;
    private String hrinicio;
    private String localidad;
    private String nombre;
    private String telefono;
    private String ubicacion;
    private String urlImageProfile;
    private String trabajo;
    private String descripcion;
    private String quienContrata;
    private String recibe;
    private int estado;
    private String yo;
    private boolean estadoSecundario;
    private int imagen;
    private int totalhrs;

    public TrabajosModel1() {}


    public TrabajosModel1(String key, String trabajo, String quienContrata, int estado, String yo,
                          int totalhrs) {
        this.key = key;
        this.trabajo = trabajo;
        this.quienContrata = quienContrata;
        this.estado = estado;
        this.yo = yo;
        this.totalhrs = totalhrs;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getTrabajo() {
        return trabajo;
    }

    public void setTrabajo(String trabajo) {
        this.trabajo = trabajo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getQuienContrata() {
        return quienContrata;
    }

    public void setQuienContrata(String quienContrata) {
        this.quienContrata = quienContrata;
    }

    public String getYo() {
        return yo;
    }

    public void setYo(String yo) {
        this.yo = yo;
    }

    public int getTotalhrs() {
        return totalhrs;
    }

    public void setTotalhrs(int totalhrs) {
        this.totalhrs = totalhrs;
    }

    @Override
    public String toString() {
        return "TrabajosModel{" +
                "correo='" + correo + '\'' +
                ", edad='" + edad + '\'' +
                ", hrfin='" + hrfin + '\'' +
                ", hrinicio='" + hrinicio + '\'' +
                ", localidad='" + localidad + '\'' +
                ", nombre='" + nombre + '\'' +
                ", telefono='" + telefono + '\'' +
                ", ubicacion='" + ubicacion + '\'' +
                ", urlImageProfile='" + urlImageProfile + '\'' +
                ", trabajo='" + trabajo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", estado=" + estado +
                ", imagen=" + imagen +
                '}';
    }
}