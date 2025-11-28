package com.devsenior.ui;

import java.util.Scanner;

import com.devsenior.Model.Rol;
import com.devsenior.Model.Usuario;
import com.devsenior.Service.Historial;
import com.devsenior.Service.SistemaGestion;

/**Esta es la clase que va a utilizar el usurio final y se encarga de llamar y juntar a 
 * las clases Historial y SistemaGestion para que el usuario pueda interactuar con el sistema
 * de gestion de usuarios, esta clase tiene un metodo iniciar que es el que se encarga
 * de mostrar el menu y llamar a los metodos correspondientes segun la opcion que el usuario
 * elija, esta clase tambien se encarga de manejar la sesion del usuario actual
 */
public class vista {
    
    Scanner scanner= new Scanner(System.in);
    
    SistemaGestion sistema= new SistemaGestion();
    Historial historial= new Historial(sistema);

    /**Este es el metodo principal de la clase que muestra el menu y llama a los metodos correspondientes segun la opcion que el usuario elija,
     * para esto se crea un usuario admin por defecto para poder crear otros usuarios,luego se muestra un menu con las opciones disponibles
     * pero no se pueden realizar acciones hasta que el usuario inicie sesion ya que cada metodo valida que el usuario
     * actual no sea null, por esto se inicializa el usuario actual como null para obligar el inicio de sesion,
     * una vez iniciada la sesion el usuario puede realizar las acciones disponibles segun su rol y se agregaran a su historial,
     * cuando se cierre la sesion el usuario actual se volvera null y se tendra que iniciar sesion de nuevo para realizar acciones.
     * 
     */
    public void iniciar() {

        Usuario admin= new Usuario("Santiago Preciado", 1, "Sa1pre","1234",Rol.ADMINISTRADOR);
        admin.setHistorial(new Historial(sistema));
        sistema.agregarUsuario(admin);
        int opcion;
        Usuario actual= null;
        System.out.println("Bienvenido al sitema de Gestion de usuarios");
        do {
        System.out.println("""
                Ingrese el numero de la accion que desea realizar:
                0.Iniciar sesion
                1.Crear un usuario(Solo para Admins)
                2.Eliminar su informacion del sistema o si es Admin borrar a un usuario
                3.Actualizar su nombre o su contraseña o si es Admin los datos de otro usuario
                4.Ver su historial o si es Admin ver el historial de otro usuario
                5.Agregar una accion al historial de un usuario(Solo para Admins)
                6.Cerrar sesion
                7.Salir
                """);
        opcion= scanner.nextInt();
        scanner.nextLine();
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
            historial.usuarioEliminado(actual);
            sistema.cerrarSesion(actual);
            actual=null;
            }
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
        } while (opcion!=7);




    }

}
