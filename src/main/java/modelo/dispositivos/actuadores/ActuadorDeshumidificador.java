package modelo.dispositivos.actuadores;

//Importamos la clase padre porque está en un nivel superior

import modelo.Actuador;

// Clase concreta para el Actuador del Deshumidificador. Aquí definimos el comportamiento de un tipo de actuador en concreto

public class ActuadorDeshumidificador extends Actuador{

    public ActuadorDeshumidificador(String id){

        // Llamamos al contructor de Actuador (id, nombre)

        super(id, "Deshumidificador");
    }

    // Actualizamos el método de la clase padre según la acción recibida

    @Override
    public void ejecutarAccion(String accion){

        // Usamos un condicional para evaluar la acción

        if(accion.equalsIgnoreCase("ON")){

            this.encendido = true;
        } else if (accion.equalsIgnoreCase("OFF")){

            this.encendido = false;

        } else {

            // Si se manda una acción inválida, se ignora y se manda un mensaje de error

            System.out.println("Acción no reconocida: " + accion);
        }
    }

    //Actualizamos el método de la clase padre devolviendo las acciones que puede realizar

    @Override
    public String[] getAccionesPosibles(){

        // Creamos y devolvemos un array con las opciones

        return new String[]{"ON", "OFF"};
    }
}
