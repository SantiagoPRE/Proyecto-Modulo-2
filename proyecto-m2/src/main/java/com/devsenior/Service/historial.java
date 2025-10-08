package com.devsenior.Service;

import java.time.LocalDateTime;
import java.util.Scanner;

import com.devsenior.Model.Acciones;
import com.devsenior.Model.Usuario;

public class Historial {

    Usuario usuario;
    Acciones[] acciones;

    Scanner scanner= new Scanner(System.in);
    SistemaGestion sistema= new SistemaGestion();

 
 public Historial(Usuario usuario) {
        this.usuario = usuario;
        this.acciones = new Acciones[50];
    }

    
 public void agregarAlHistorial(Acciones accion){
    for (int i = 0; i< acciones.length; i++) {
        if (acciones[i]== null) {
            acciones[i]= accion;
            return;
        }  
    }
 }

    public void usuarioCreado(Usuario actual){
        LocalDateTime ahora= LocalDateTime.now();
        if (actual== null) {
            return;
        }
        else if (sistema.crearUsuario(actual)) {
        Acciones accion= new Acciones("Se creo un usuario correctamente", ahora);
        actual.getHistorial().agregarAlHistorial(accion); 
        }
        else{
            Acciones accion= new Acciones("Se intento crear un usuario y se fallo",ahora);
        actual.getHistorial().agregarAlHistorial(accion);}
    }

    public void usuarioEliminado(Usuario actual){
       LocalDateTime ahora= LocalDateTime.now();
        if (actual== null) {
            return;
        }
        else if (sistema.eliminarUsuario(actual)) {
        Acciones accion= new Acciones("Se elimino un usuario correctamente", ahora);
        actual.getHistorial().agregarAlHistorial(accion); 
        }
        else{
            Acciones accion= new Acciones("Se intento eliminar un usuario y se fallo",ahora);
        actual.getHistorial().agregarAlHistorial(accion);}

    }

    public void usuarioActualizado(Usuario actual){
        LocalDateTime ahora= LocalDateTime.now();
        if (actual== null) {
            return;
        }
        else if (sistema.actualizarInfo(actual)) {
        Acciones accion= new Acciones("Se actualizo la informacion de un usuario correctamente", ahora);
        actual.getHistorial().agregarAlHistorial(accion); 
        }
        else{
            Acciones accion= new Acciones("Se intento actualizar la informacion de un usuario y se fallo",ahora);
        actual.getHistorial().agregarAlHistorial(accion);}

    }

    public void mostrarHistorial(Usuario usuario){
    usuario =sistema.buscarUsuario(usuario.getId(),usuario.getUsername());
        if (usuario == null) {
        System.out.println("Usuario no encontrado.");
        return;
    }
    System.out.println("Historial de: "+usuario.getNombreCompleto());
       for (Acciones h :usuario.getHistorial().acciones) {
        if (h!= null) {
            System.out.println(h);  
        }
       }
    }   

    public void agregarAccionAUser(Usuario usuario){
        int opcion;
        usuario =sistema.buscarUsuario(usuario.getId(),usuario.getUsername());
        if (usuario == null) {
        System.out.println("Usuario no encontrado.");
        return;
    }
         do{
            System.out.println("""
                    Ingrese :
                    1.Para crear un usuario
                    2.Para eliminar un usuario
                    3.Para actualizar un usuario
                    4.Para salir
                    Para agregar la accion eligida al historial de: """+usuario.getNombreCompleto());
            opcion= scanner.nextInt();
            switch (opcion) {
                case 1:
                    usuarioCreado(usuario);
                    break;
                case 2:
                    usuarioEliminado(usuario);
                    break;
                case 3:
                    usuarioActualizado(usuario);
                    break;
                default:
                    System.out.println("Opcion invalida intente nuevamente");
                    break;
            }
         }while(opcion!=4);




    }



    }


