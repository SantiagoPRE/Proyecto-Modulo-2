package com.devsenior.ui;

import java.util.Scanner;

import com.devsenior.Model.Rol;
import com.devsenior.Model.Usuario;
import com.devsenior.Service.Historial;
import com.devsenior.Service.SistemaGestion;

public class vista {
    
    Scanner scanner= new Scanner(System.in);

    SistemaGestion sistema= new SistemaGestion();
    Historial historial= new Historial(null);

    public void iniciar() {

        Usuario admin= new Usuario("Santiago Preciado", 1, "Sa1pre","1234",Rol.ADMINISTRADOR);
        int opcion;
        Usuario actual= admin;
        System.out.println("Bienvenido al sitema de Gestion de usuarios");
        do {
        System.out.println("""
                Ingrese el numero de la accion que desea realizar:
                0.Iniciar sesion
                1.Crear un usuario}(Solo para Admins)
                2.Eliminar su informacion del sistema o si es Admin borrar a un usuario
                3.Actualizar su nombre o su contraseña o si es Admin los datos de otro usuario
                4.Ver su historial o si es Admin ver el historial de otro usuario
                5.Agregar una accion al historial de un usuario(Solo para Admins)
                6.Cerrar sesion
                7.Salir
                """);
        opcion= scanner.nextInt();
        switch (opcion) {
            case 0:
            actual=sistema.iniciarSesión(sistema.ingreseSuUsername(scanner),sistema.ingreseSuContrase(scanner));
            break;
            case 1:
            if (actual== null) {
                System.out.println("Para hacer esta accion primero debe iniciar sesion");
            }
            else{
            historial.usuarioCreado(actual);}
                break;
                case 2:
                 if (actual== null) {
                System.out.println("Para hacer esta accion primero debe iniciar sesion");
            }
            else{
            historial.usuarioEliminado(actual);}
                break;
                case 3:
                 if (actual== null) {
                System.out.println("Para hacer esta accion primero debe iniciar sesion");
            }
            else{
            historial.usuarioActualizado(actual);}
                break;
                case 4:
                 if (actual== null) {
                System.out.println("Para hacer esta accion primero debe iniciar sesion");
            }
            else{
            historial.mostrarHistorial(actual);}
                break;
                case 5:
                 if (actual== null) {
                System.out.println("Para hacer esta accion primero debe iniciar sesion");
            }
            else{
            historial.agregarAccionAUser(actual);}
                break;
                case 6:
                 if (actual== null) {
                System.out.println("Para hacer esta accion primero debe iniciar sesion");
            }
            else{
            actual= sistema.cerrarSesion(actual);}
                break;
                case 7:
            System.out.println("Saliendo del sistema....");
                break;
            default:
            System.out.println("Opcion invalida intente de nuevo");
                break;
        }
        } while (opcion==7);




    }

}
