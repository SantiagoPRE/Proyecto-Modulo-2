package com.devsenior.Model;

import com.devsenior.Service.Historial;

public class Usuario {

    private String nombreCompleto;//Los atributos de la clase usuario
    private int id;
    private String username;
    private String contraseña;
    private Rol rol;
    private Historial historial;

    public Usuario(String nombreCompleto, int id, String username, String contraseña,//El constructor de la clase
            Rol rol) {
        this.nombreCompleto = nombreCompleto;
        this.id = id;
        this.username = username;
        this.contraseña = contraseña;
        this.rol = rol;     
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

    public Historial getHistorial() {
        return historial;
    }

    public void setHistorial(Historial historial) {
        this.historial = historial;
    }
    
//Se verifica que el usuario que llame al metodo sea administrador o estandar para poder hacer restricciones
    public boolean esAdministrador() {
        if (this.rol == Rol.ADMINISTRADOR) {
            return true;

        }
        return false;
    }

    public boolean esEstandar() {
        if (this.rol == Rol.ESTANDAR) {
            return true;

        }
        return false;
    }

}
