package com.example.proyecto;

public class TrabajosModel {

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

    public TrabajosModel() {}

    public TrabajosModel(String key, String correo, String edad, String hrfin, String hrinicio, String localidad, String nombre, String telefono, String ubicacion, String urlImageProfile, String trabajo, String descripcion, String quienContrata, String recibe, int estado, String yo, boolean estadoSecundario, int imagen, int totalhrs) {
        this.key = key;
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
        this.quienContrata = quienContrata;
        this.recibe = recibe;
        this.estado = estado;
        this.yo = yo;
        this.estadoSecundario = estadoSecundario;
        this.imagen = imagen;
        this.totalhrs = totalhrs;
    }

    public TrabajosModel(String key, String trabajo, String quienContrata, int estado, String yo, int totalhrs) {
        this.key = key;
        this.trabajo = trabajo;
        this.quienContrata = quienContrata;
        this.estado = estado;
        this.yo = yo;
        this.totalhrs = totalhrs;
    }

    public TrabajosModel(String key, String correo, String edad,
                         String hrfin, String hrinicio, String localidad,
                         String nombre, String telefono, String ubicacion,
                         String urlImageProfile, String trabajo, String descripcion,
                         String quienContrata, int estado, int imagen) {
        this.key = key;
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
        this.quienContrata = quienContrata;
        this.estado = estado;
        this.imagen = imagen;
    }

    public TrabajosModel(String key, String correo, String edad, String nombre, String telefono, String urlImageProfile, int estado, String yo, boolean estadoSecundario) {
        this.key = key;
        this.correo = correo;
        this.edad = edad;
        this.nombre = nombre;
        this.telefono = telefono;
        this.urlImageProfile = urlImageProfile;
        this.estado = estado;
        this.yo = yo;
        this.estadoSecundario = estadoSecundario;
    }

    public TrabajosModel(String key, String recibe, String trabajo, int estado, String correo, String edad,
                         String nombre, String telefono, String urlImageProfile, String yo,
                         boolean estadoSecundario, int totalhrs) {
        this.key = key;
        this.recibe = recibe;
        this.trabajo = trabajo;
        this.estado = estado;
        this.correo = correo;
        this.edad = edad;
        this.nombre = nombre;
        this.telefono = telefono;
        this.urlImageProfile = urlImageProfile;
        this.yo = yo;
        this.estadoSecundario = estadoSecundario;
        this. totalhrs = totalhrs;
    }

    public TrabajosModel(String key, String correo, String edad, String hrfin, String hrinicio,
                         String localidad, String nombre, String telefono, String ubicacion,
                         String urlImageProfile, String trabajo, String descripcion, int estado) {
        this.key = key;
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

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
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

    public String getQuienContrata() {
        return quienContrata;
    }

    public void setQuienContrata(String quienContrata) {
        this.quienContrata = quienContrata;
    }

    public String getRecibe() {
        return recibe;
    }

    public void setRecibe(String recibe) {
        this.recibe = recibe;
    }

    public boolean isEstadoSecundario() {
        return estadoSecundario;
    }

    public void setEstadoSecundario(boolean estadoSecundario) {
        this.estadoSecundario = estadoSecundario;
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