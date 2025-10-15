package com.devsenior.Service;

import java.util.Scanner;

import com.devsenior.Model.Rol;
import com.devsenior.Model.Usuario;

public class SistemaGestion {

    Usuario[] usuario;
    Scanner scanner = new Scanner(System.in);

    public SistemaGestion() {
        this.usuario = new Usuario[50];
    }

    /**
     * Este metodo sirve para añadir a un usuario
     * recien creado a el sistema o mas bien al arreglo usuario
     * 
     * @param usuariox el usuario a agregar
     */
    public void agregarUsuario(Usuario usuariox) {
        for (int i = 0; i < usuario.length; i++) {
            if (usuario[i] == null) {
                usuario[i] = usuariox;
                System.out.println("Usuario agregado al sistema");
                return;
            }
        }
    }

    /**
     * Este metodo sirve para crear un usuario, primero llama al metodo accionesPermitidas que verifica si el usuario actual es administrador o estandar
     * si es administrador pide los datos del usuario a crear y lo crea
     * si es estandar no puede crear usuarios y muestra un mensaje,
     * al asignar el rol al nuevo usuario se utiliza un try-catch por si se ingresa un rol que no deberia
     * y se pide hasta que sea un rol valido
     * 
     * @return retorna true si se creo el usuario y false si no se creo para luego
     *         crear una accion en la clase historial
     */
    public boolean crearUsuario(Usuario actual) {
        if (accionesPermitidas(actual)) {
            System.out.println("Ingrese el nombre completo(se parado por un espacio) del usuario a registrar");
            String nombre = scanner.nextLine();
            System.out.println("Ingrese el Id del usuario a registrar");
            int id = scanner.nextInt();
            scanner.nextLine();
            Rol rol=null;
             while (rol == null) {  // se repite hasta que el rol sea válido
            System.out.println("Ingrese el rol del usuario (ESTANDAR o ADMINISTRADOR):");
            String rol1 = scanner.nextLine().toUpperCase();
            try {
            rol=Rol.valueOf(rol1);
        } catch (IllegalArgumentException e) {
            System.out.println("Rol inválido. Debe ser ESTANDAR o ADMINISTRADOR.");
        }}
            System.out.println("Ingrese el nombre de usuario");
            String username= scanner.nextLine();
             System.out.println("Ingrese la contraseña del usuario");
            String contraseña = scanner.nextLine();

            Usuario usuario1 = new Usuario(nombre, id, username, contraseña ,rol);
            System.out.printf("Usuario creado: nombre: %s Id: %d %n", usuario1.getNombreCompleto(), usuario1.getId());
            agregarUsuario(usuario1);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Metodo para buscar un usuario por id o username, verifica si el usuario
     * existe y luego
     * si el id o username coinciden
     * 
     * @param id
     * @param username
     * @return si encuentra el usuario lo retorna, si no retorna null
     */
    public Usuario buscarUsuario(int id, String username) {
        for (int i = 0; i < usuario.length; i++) {
            if (usuario[i] != null && (usuario[i].getId() == id || usuario[i].getUsername().equals(username))) {
                System.out.println("Usuario encontrado");
                return usuario[i];

            }
        }
        System.out.println("Usuario no encontrado");
        return null;
    }

    /**
     * Metodo para iniciar sesion, verifica si el username y contraseña coinciden
     * con algun usuario
     * sirve para saber quien es el usuario actual y con el metodo
     * accionesPermitidas
     * verificar que puede o no hacer
     * 
     * @param username   lo recibe del metodo ingreseSuUsername
     * @param contraseña lo recibe del metodo ingreseSuContrase
     * @return y retorna el usuario que inicio sesion o null si no se encontro
     */
    public Usuario iniciarSesión(String username, String contraseña) {

        for (int i = 0; i < usuario.length; i++) {
            if (username.equals(usuario[i].getUsername()) && contraseña.equals(usuario[i].getContraseña())) {
                Usuario usuarioActual = usuario[i];
                System.out.println("inicio de sesion exitoso");
                return usuarioActual;
            }
        }
        System.out.println("inicio de sesion fallido");
        return null;
    }

    public Usuario cerrarSesion(Usuario actual){
        System.out.println("Cerrando sesión de: " + actual.getUsername());
        return null;
    }

    /**
     * Este metodo sirve para eliminar un usuario del sistema, lo primero que hace
     * es llamar al metodo accionesPermitidas que verifica si el usuario actual
     * es administrador o estandar, si es administrador pide el id y username del
     * usuario a eliminar y lo busca
     * si se encuentra lo elimina, si no se encuentra muestra un mensaje,
     * si el usuario es estandar se busca y elimina a si mismo,
     * si no se encuentra muestra un mensaje
     * 
     * @return retorna true si se elimino el usuario y false si no, para luego crear
     *         una accion
     *         con el metodo usuarioEliminado en la clase historial
     */
    public boolean eliminarUsuario(Usuario actual) {
        if (accionesPermitidas(actual)) {
            System.out.println("Ingrese el id del usuario a eliminar");
            int id = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Ingrese el username del usuario a eliminar");
            String username = scanner.nextLine();
            Usuario u = buscarUsuario(id, username);
            if (u == null) {
                System.out.println("No se pudo encontrar al usuario");
                return false;
            } else {
                for (int i = 0; i < usuario.length; i++) {
                    if (usuario[i] != null && usuario[i].getId() == u.getId()) {
                        usuario[i] = null;
                        System.out.println("Se eliminaron los datos del usuario");
                        return true;
                    }
                }
                System.out.println("No se pudieron borrar los datos del usuario");
                return false;

            }
        } else {
            for (int i = 0; i < usuario.length; i++) {
                if (usuario[i] != null && usuario[i].getId() == actual.getId()) {
                    usuario[i] = null;
                    System.out.println("Se eliminaron sus datos se cerrara la sesion de su cuenta");
                    return true;
                }
            }
            System.out.println("No se pudieron borrar sus datos");
            return false;

        }
    }

    /**
     * Este metodo sirve para actualizar la informacion de un usuario, lo primero que hace es llamar al metodo accionesPermitidas que verifica si el usuario actual es
     * administrador o estandar, si es administrador pide el id y username del usuario a actualizar y lo busca
     * si se encuentra pide la contraseña del usuario y si es correcta muestra un menu sobre que va a actualizar,
     * si no se encuentra o la contraseña es incorrecta muestra un mensaje,
     * si es estandar pide su propia contraseña y si es correcta muestra un menu sobre que va a actualizar,
     * si la contraseña es incorrecta muestra un mensaje
     * @return retorna un true o false para luego crear una accion en la clase historial
     * con el metodo usuarioActualizado
     */
    public boolean actualizarInfo(Usuario actual) {
        int opcion;
        if (accionesPermitidas(actual)) {
            System.out.println("Ingrese el id del usuario a actualizar");
            int id = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Ingrese el username del usuario a actualizar");
            String username = scanner.nextLine();
            Usuario u = buscarUsuario(id, username);

            System.out.println("Ingrese la contraseña actual del usuario: " + u.getNombreCompleto());
            if (scanner.nextLine().equals(u.getContraseña())) {
                do {
                    System.out.println("""
                            Ingrese 1 para actualizar el nombre completo
                            Ingrese 2 para actualizar la contraseña
                            Ingrese 3 para salir
                            """);
                    opcion = scanner.nextInt();
                    scanner.nextLine();
                    switch (opcion) {
                        case 1:
                            System.out.println("Ingrese el nuevo nombre completo");
                            u.setNombreCompleto(scanner.nextLine());
                            System.out.println("Se cambio el nombre completo");
                            break;
                        case 2:
                            System.out.println("Ingrese la nueva contraseña");
                            u.setContraseña(scanner.nextLine());
                            System.out.println("Se cambio la contraseña");
                            break;
                            case 3:
                            System.out.println("Saliendo de actualizar informacion");
                            break;
                        default:
                            System.out.println("Opción no válida");
                            break;
                    }
                } while (opcion != 3);
                return true;
            } else {
                System.out.println("Contraseña incorrecta ");
                return false;
            }
        } else {

            System.out.println("Ingrese su contraseña actual ");
            if (scanner.nextLine().equals(actual.getContraseña())) {
                do {
                    System.out.println("""
                            Ingrese 1 para actualizar el nombre completo
                            Ingrese 2 para actualizar la contraseña
                            Ingrese 3 para salir
                            """);
                    opcion = scanner.nextInt();
                    scanner.nextLine();
                    switch (opcion) {
                        case 1:
                            System.out.println("Ingrese el nuevo nombre completo");
                            actual.setNombreCompleto(scanner.nextLine());
                            System.out.println("Se cambio su nombre completo");
                            break;
                        case 2:
                            System.out.println("Ingrese la nueva contraseña");
                            actual.setContraseña(scanner.nextLine());
                            System.out.println("Se cambio su contraseña");
                            break;
                            case 3:
                            System.out.println("Saliendo");
                            break;
                        default:
                            System.out.println("Opción no válida");
                            break;
                    }

                } while (opcion != 3);
                return true;
            } else {
                System.out.println("Contraseña incorrecta");
                return false;
            }

        }
    }

    /**
     * Este metodo sirve para verificar las acciones que puede hacer un usuario
     * 
     * @param usuario recibe el usuario del metodo iniciarSesion
     * @return devuelve true si es administrador y false si es estandar
     */
    public boolean accionesPermitidas(Usuario usuario) {
        if (usuario== null) {
            return false;
        }
        if (usuario.esAdministrador()) {
            System.out.println("El usuario es administrador y puede modificar otros usuarios");
            return true;
        } else {
            System.out.println("El usuario es estándar y no puede modificar otros usuarios");
            return false;
        }
    }

    /**
     * Este metodo complementa al metodo iniciar sesion pide el username y lo
     * devuelve para que
     * iniciar sesion o el que lo necesite lo use
     */
    public String ingreseSuUsername(Scanner scanner) {
        System.out.println("Ingrese su nombre de usuario");
        String username = scanner.nextLine();
        return username;

    }

    /**
     * Este metodo complementa al metodo iniciar sesion pide la contraseña y lo
     * devuelve para que
     * iniciar sesion o el que lo necesite lo use
     */
    public String ingreseSuContrase(Scanner scanner) {
        System.out.println("Ingrese su contraseña");
        String contraseña = scanner.nextLine();
        return contraseña;
    }
}
