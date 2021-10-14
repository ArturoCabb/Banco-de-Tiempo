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
    String urlImageProfile;
    String trabajo;
    String descripcion;
    int estado;
    int imagen;

    public TrabajosModel(){

    }

    public TrabajosModel(String descripcion, int estado) {
        this.descripcion = descripcion;
        this.estado = estado;
    }


    public TrabajosModel(String trabajo, String nombre, String descripcion, int imagen) {
        this.trabajo = trabajo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.imagen = imagen;
    }


    public TrabajosModel(String trabajo, String nombre, String descripcion, String urlImageProfile) {
        this.trabajo = trabajo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.urlImageProfile = urlImageProfile;
    }


    public TrabajosModel(String correo, String hrfin, String hrinicio,
                         String localidad, String telefono, String ubicacion, String urlImageProfile,
                         String nombre) {
        this.correo = correo;
        this.hrfin = hrfin;
        this.hrinicio = hrinicio;
        this.localidad = localidad;
        this.telefono = telefono;
        this.ubicacion = ubicacion;
        this.urlImageProfile = urlImageProfile;
        this.nombre = nombre;
    }


    public TrabajosModel(String correo, String edad, String hrfin, String hrinicio,
                         String localidad, String nombre, String telefono, String ubicacion,
                         String urlImageProfile, String trabajo, String descripcion, int estado) {
        this.correo = correo;
        this.edad = edad;
        this.hrfin = hrfin;
        this.hrinicio = hrinicio;
        this.localidad = localidad;
        this.nombre = nombre;
        this.telefono = telefono;
        this.ubicacion = ubicacion;
        this.urlImageProfile = urlImageProfile;
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

    public String getUrlImageProfile() {
        return urlImageProfile;
    }

    public void setUrlImageProfile(String urlImageProfile) {
        this.urlImageProfile = urlImageProfile;
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

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
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