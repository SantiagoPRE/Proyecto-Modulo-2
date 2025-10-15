package com.devsenior.Model;

import java.time.LocalDateTime;



public class Acciones {

    String descripcion;
    LocalDateTime time;
   

    public Acciones(String descripcion, LocalDateTime time ) {
        this.descripcion = descripcion;
        this.time = time;
        
    }


    @Override
    public String toString() {
        return "Accion: "+ descripcion + " | Fecha: " + time + "]";
    }
    
    
    
    

}



