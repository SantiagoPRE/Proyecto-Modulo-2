package com.devsenior.Service;

import java.time.LocalDateTime;
import java.util.Scanner;

import com.devsenior.Model.Acciones;
import com.devsenior.Model.Usuario;

/**Esta clase se encarga de todo lo relacionado a el historial de acciones de un usuario,
 * todos los metodos de la clase funcionan apartir de llamar a los metodos de la clase SistemaGestion
 * y segun la respuesta boolean de estos metodos se realiza una accion, esta clase es la que se utiliza
 * en la clase vista para realizar y registrar las acciones de los usuarios, como se utiliza el usuario 
 * de la clase vista todos los metodos validan que el usuario no sea null antes de realizar cualquier accion
 */
public class Historial {


  
    Acciones[] acciones;
    SistemaGestion sistema;
    Scanner scanner = new Scanner(System.in);

    /**
     * Constructor de la clase Historial.
     * @param usuario Este usuario sera null y luego en la clase vista se iniciara
     * sesion y se asignara el usuario 
     * @param sistema Instancia del SistemaGestion 
     */
    public Historial(SistemaGestion sistema) {
        this.acciones = new Acciones[50];
        this.sistema = sistema;
    }

    /**
     *Este metodo sirve para agregar una accion al historial de un usuario
     *para hacerlo recorre el arreglo de acciones hasta encontrar un espacio vacio
     *y agrega la accion en ese espacio
     * @param accion La acción a agregar
     */
    public void agregarAlHistorial(Acciones accion) {
        for (int i = 0; i < acciones.length; i++) {
            if (acciones[i] == null) {
                acciones[i] = accion;
                return;
            }
        }
    }

    /**
     *Este metodo registra en el historial la operacion de crear un usuario
     *utiliza el metodo crearUsuario de la clase SistemaGestion y antes de llamarlo se valida que el
     *usuario que recibe el metodo no sea null, luego se llama a Crear usuario y segun la respuesta
     *boolean de este metodo agrega una accion al historial del usuario o muestra un mensaje de fallo
     * @param actual Usuario que se intenta crear 
     */
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

    /**
     * Este metodo registra en el historial la operación de eliminar un usuario.
     * Intenta eliminar el usuario mediante `sistema.eliminarUsuario(actual)` y
     * escribe una entrada de éxito o fracaso en su historial.
     *
     * @param actual Usuario que se va a eliminar
     */
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

    /**
     * Este metodo registra en el historial la operación de actualizar la información de un usuario.
     * Utiliza `sistema.actualizarInfo(actual)` y añade una accion al historial segun la repuesta boolean
     * de ese metodo.
     *
     * @param actual Usuario cuya información se intenta actualizar 
     */
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

    /**
     * Este metodo muestra el historial de un usuario.
     * Si el usuario actual es administrador, puede ver el historial de cualquier usuario
     * mediante su id y username. Si no es administrador, solo puede ver su propio historial.
     * @param actual Usuario que solicita ver el historial
     */
    public void mostrarHistorial(Usuario actual) {

        if (sistema.accionesPermitidas(actual)) {
            System.out.println("Ingrese el id del usuario del que quiere ver el historial");
            int id = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Ingrese el username del usuario del que quiere ver el historial");
            String username = scanner.nextLine();
            Usuario usuario1 = sistema.buscarUsuario(id, username);
            if (usuario1 == null) {
                System.out.println("Usuario no encontrado.");
                return;
            }
            System.out.println("Historial de: " + usuario1.getNombreCompleto());
            for (Acciones h : usuario1.getHistorial().acciones) {
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

    /**
     * Este metodo permite a un usuario con permisos agregar una acción al historial de otro usuario.
     * Si el usuario actual tiene permisos, se le solicita el id y username del usuario
     * al que desea agregar una acción, y luego se le presenta un menú para seleccionar la
     * acción a agregar y segun la accion que se elija se llama el metodo correspondiente a dicha accion, pero si el usuario al que
     * se le va a agregar la accion es un usuario estandar solo se le permitira al admin agregar una accion que pueda hacer un usuario estandar
     * es decir actualizar o eliminar su propia informacion o ver su propio historial y se agrega al historial del usuario seleccionado.
     * Si el usuario actual no es administrador se le muestra un mensaje.
     * @param actual Usuario que intenta agregar la acción 
     */
    public void agregarAccionAUser(Usuario actual){

        if(sistema.accionesPermitidas(actual)){
        int opcion;
        System.out.println("Ingrese el id del usuario del que desea agregar una accion a su historial");
        int id= scanner.nextInt();
        scanner.nextLine();
        System.out.println("Ingrese el username del usuario del que desea agregar una accion a su historial");
        String username= scanner.nextLine();
        Usuario usuario1 =sistema.buscarUsuario(id,username);
        if (usuario1 == null) {
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
                    Para agregar la accion eligida al historial de: """+usuario1.getNombreCompleto());
            opcion= scanner.nextInt();
            switch (opcion) {
                case 1:
                    usuarioCreado(usuario1);
                    break;
                case 2:
                    usuarioEliminado(usuario1);
                    break;
                case 3:
                    usuarioActualizado(usuario1);
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


