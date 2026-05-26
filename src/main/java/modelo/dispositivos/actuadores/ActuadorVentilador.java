package modelo.dispositivos.actuadores;

//Importamos la clase padre porque está en un nivel superior

import modelo.Actuador;

// Clase concreta para el Actuador del Ventilador. Aquí definimos el comportamiento de un tipo de actuador en concreto

public class ActuadorVentilador extends Actuador{

    // Añadimos un atributo propio para la velocidad del ventilador

    private String velocidad;

    public ActuadorVentilador(String id){

        // Llamamos al contructor de Actuador (id, nombre)

        super(id, "Ventilador");

        // Añadimos una velocidad por defecto al crearlo

        this.velocidad = "LOW";
    }

    // Actualizamos el método de la clase padre según la acción recibida

    @Override
    public void ejecutarAccion(String accion){

        // Aquí convertimos el texto en mayúsculas para evitar que haya un error en caso de que el texto nos venga en minúsculas

        String comando = accion.toUpperCase();

        // Crearemos un switch para evaluar las diferentes opciones que puede tener el ventilador

        switch (comando){

            case "OFF":
                this.encendido = false;
                break;
            case "LOW":
            case "MEDIUM":
            case "HIGH":
                this.encendido = true;
                this.velocidad = comando;
                break;
            // Usaremos el default para enviar un mensaje de error por pantalla, ya que las opciones disponibles son claras y limitadas
            default:
                System.out.println("Comando no reconocido :" + accion);
                break;

        }
    }

    //Actualizamos el método de la clase padre devolviendo las acciones que puede realizar

    @Override
    public String[] getAccionesPosibles(){

        // Creamos y devolvemos un array con las opciones

        return new String[]{"OFF", "LOW", "MEDIUM", "HIGH"};
    }

    //Sobreescribimos el método padre para que, en lugar de solo devolver ON u OFF, devuelva también la velocidad en caso de estar encendido

    @Override
    public String getEstadoActual(){

        if (this.encendido){

            return "ON - Velocidad: " + this.velocidad;
        } else {

            return "OFF";
        }
    }
}