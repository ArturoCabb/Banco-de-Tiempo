package com.example.proyecto;

public class TrabajosModel {
    String correo;
    String edad;
    String hrfin;
    String hrinicio;
    String localidad;
    String nombre;
    String telefono;
    String ubicacion;
    String urlImagen;
    String trabajo;
    String descripcion;
    int estado;

    public TrabajosModel(String descripcion, int estado) {
        this.descripcion = descripcion;
        this.estado = estado;
    }


    public TrabajosModel(String correo, String hrfin, String hrinicio,
                         String localidad, String telefono, String ubicacion, String urlImagen,
                         String nombre) {
        this.correo = correo;
        this.hrfin = hrfin;
        this.hrinicio = hrinicio;
        this.localidad = localidad;
        this.telefono = telefono;
        this.ubicacion = ubicacion;
        this.urlImagen = urlImagen;
        this.nombre = nombre;
    }


    public TrabajosModel(String correo, String edad, String hrfin, String hrinicio,
                         String localidad, String nombre, String telefono, String ubicacion,
                         String urlImagen, String trabajo, String descripcion, int estado) {
        this.correo = correo;
        this.edad = edad;
        this.hrfin = hrfin;
        this.hrinicio = hrinicio;
        this.localidad = localidad;
        this.nombre = nombre;
        this.telefono = telefono;
        this.ubicacion = ubicacion;
        this.urlImagen = urlImagen;
        this.trabajo = trabajo;
        this.descripcion = descripcion;
        this.estado = estado;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getHrfin() {
        return hrfin;
    }

    public void setHrfin(String hrfin) {
        this.hrfin = hrfin;
    }

    public String getHrinicio() {
        return hrinicio;
    }

    public void setHrinicio(String hrinicio) {
        this.hrinicio = hrinicio;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
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
}