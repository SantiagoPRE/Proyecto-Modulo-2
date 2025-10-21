package com.devsenior.Service;

import java.time.LocalDateTime;
import java.util.Scanner;

import com.devsenior.Model.Acciones;
import com.devsenior.Model.Usuario;

public class Historial {

    Usuario usuario;
    Acciones[] acciones;
    private SistemaGestion sistema;

    Scanner scanner = new Scanner(System.in);

    public Historial(Usuario usuario, SistemaGestion sistema) {
        this.usuario = usuario;
        this.acciones = new Acciones[50];
        this.sistema = sistema;
    }

    public void agregarAlHistorial(Acciones accion) {
        for (int i = 0; i < acciones.length; i++) {
            if (acciones[i] == null) {
                acciones[i] = accion;
                return;
            }
        }
    }

    public void usuarioCreado(Usuario actual) {
        LocalDateTime ahora = LocalDateTime.now();
        if (actual == null) {
            return;
        } else if (sistema.crearUsuario(actual)) {
            Acciones accion = new Acciones("Se creo un usuario correctamente", ahora);
            actual.getHistorial().agregarAlHistorial(accion);
        } else {
            Acciones accion = new Acciones("Se intento crear un usuario y se fallo", ahora);
            actual.getHistorial().agregarAlHistorial(accion);
        }
    }

    public void usuarioEliminado(Usuario actual) {
        LocalDateTime ahora = LocalDateTime.now();
        if (actual == null) {
            return;
        } else if (sistema.eliminarUsuario(actual)) {
            Acciones accion = new Acciones("Se elimino un usuario correctamente", ahora);
            actual.getHistorial().agregarAlHistorial(accion);
        } else {
            Acciones accion = new Acciones("Se intento eliminar un usuario y se fallo", ahora);
            actual.getHistorial().agregarAlHistorial(accion);
        }

    }

    public void usuarioActualizado(Usuario actual) {
        LocalDateTime ahora = LocalDateTime.now();
        if (actual == null) {
            return;
        } else if (sistema.actualizarInfo(actual)) {
            Acciones accion = new Acciones("Se actualizo la informacion de un usuario correctamente", ahora);
            actual.getHistorial().agregarAlHistorial(accion);
        } else {
            Acciones accion = new Acciones("Se intento actualizar la informacion de un usuario y se fallo", ahora);
            actual.getHistorial().agregarAlHistorial(accion);
        }

    }

    public void mostrarHistorial(Usuario actual) {

        if (sistema.accionesPermitidas(actual)) {
            System.out.println("Ingrese el id del usuario del que quiere ver el historial");
            int id = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Ingrese el username del usuario del que quiere ver el historial");
            String username = scanner.nextLine();
            usuario = sistema.buscarUsuario(id, username);
            if (usuario == null) {
                System.out.println("Usuario no encontrado.");
                return;
            }
            System.out.println("Historial de: " + usuario.getNombreCompleto());
            for (Acciones h : usuario.getHistorial().acciones) {
                if (h != null) {
                    System.out.println(h);
                }
            }
        } else {
            System.out.println("Este es su historial: " + actual.getNombreCompleto());
            for (Acciones h : actual.getHistorial().acciones) {
                if (h != null) {
                    System.out.println(h);
                }
            }
        }
    }

    public void agregarAccionAUser(Usuario actual){

        if(sistema.accionesPermitidas(actual)){
        int opcion;
        System.out.println("Ingrese el id del usuario del que desea agregar una accion a su historial");
        int id= scanner.nextInt();
        scanner.nextLine();
        System.out.println("Ingrese el username del usuario del que desea agregar una accion a su historial");
        String username= scanner.nextLine();
        usuario =sistema.buscarUsuario(id,username);
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
                    case 4:
                    System.out.println("Saliendo");
                    break;
                default:
                    System.out.println("Opcion invalida intente nuevamente");
                    break;
            }
         }while(opcion!=4);
        }
        else{System.out.println("No tiene permisos para agregarle una accion al historial de otro usuario");
    return;}

    }


}


