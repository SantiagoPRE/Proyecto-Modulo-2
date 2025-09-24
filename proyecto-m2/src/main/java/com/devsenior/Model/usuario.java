package com.devsenior.Model;

import com.devsenior.Service.Acciones;
import com.devsenior.Service.Historial;

public class Usuario {

    private String nombreCompleto;
    private int id;
    private String username;
    private String contraseña;
    private Rol rol;
    private Acciones[] acciones;
    private Historial historial;

    public Usuario(String nombreCompleto, int id, String username, String contraseña,
            Rol rol) {
        this.nombreCompleto = nombreCompleto;
        this.id = id;
        this.username = username;
        this.contraseña = contraseña;
        this.rol = rol;
        this.acciones = new Acciones[100];
        this.historial = new Historial();

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public int getId() {
        return id;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public Acciones[] getAcciones() {
        return acciones;
    }

    public Historial getHistorial() {
        return historial;
    }

    
}

