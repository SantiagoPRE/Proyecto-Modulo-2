package com.devsenior.Service;

import java.util.Scanner;

import com.devsenior.Model.Usuario;

public class SistemaGestion {

 Usuario[] usuarios;


     public SistemaGestion() {
        this.usuarios = new Usuario[50];
         crearUsuario();
    }

    public void agregarUsuario(Usuario usuario) {
        for (int i = 0; i < usuarios.length; i++) {
            if (usuarios[i] == null) {
                usuarios[i] = usuario;
                System.out.println("Usuario agregado al sistema");
                break;
            }
        }
    }

 
    public void crearUsuario() {
        Usuario usuario= new Usuario(null, 0, null, null, null);
        System.out.printf("Usuario creado: nombre: %s Id: %d", usuario.getNombreCompleto(), usuario.getId());
        agregarUsuario(usuario);
    }

    
    public boolean buscarUsuario(Usuario[] usuario,int id,String username){
    for (int i = 0; i < usuario.length; i++) {
        if (usuario[i].getId() == id || usuario[i].getUsername().equals(username)) {
            System.out.println("Usuario encontrado");
            return true;
            
        }
    }
    System.out.println("Usuario no encontrado");
    return false;
}

    public void eliminarUsuario(Usuario[] usuario, int id, String username) {
        for (int i = 0; i < usuario.length; i++) {
            if (buscarUsuario(usuario, id, username)) {
                usuario[i] = null;
                System.out.println("Usuario eliminado");
                break;
            }
        }
    }

    public void actualizarInfo(Usuario[] usuario, int id, String username,Scanner scanner) {
        for (int i = 0; i < usuario.length; i++) {
            if(buscarUsuario(usuario, id, username)){
                System.out.println("Ingrese la contraseña actual del usuario");
                if (scanner.nextLine().equals(usuario[i].getContraseña())) {
                    System.out.println("""
                            Ingrese 1 para actualizar el nombre completo
                            Ingrese 2 para actualizar la contraseña
                            """);
                    int opcion = scanner.nextInt();
                    scanner.nextLine();
                    switch (opcion) {
                        case 1:
                            System.out.println("Ingrese el nuevo nombre completo");
                            usuario[i].setNombreCompleto(scanner.nextLine());
                            break;
                        case 2:
                            System.out.println("Ingrese la nueva contraseña");
                            usuario[i].setContraseña(scanner.nextLine());
                            break;
                        default:
                            System.out.println("Opción no válida");
                            break;
                    }
                }
                else{
                    System.out.println("Contraseña incorrecta vuelva a intentarlo");
                }
            }else {
                System.out.println("Usuario no encontrado");
            }
    }


    }

    


}


