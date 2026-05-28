package modelo.reglas.reglas_especificas;

import modelo.Sensor;
import modelo.Actuador;
import modelo.reglas.Regla;
import java.util.List;

/**
 * Regla específica para la iluminación
 * Comparará los datos del sensor de luz con la regla establecida y activará o desactivará su funcionamiento automático
 */

public class IluminacionAutomatica implements Regla {

    // variable para controlar si la regla está o no activa. Por defecto sí lo está

    private boolean activa = true;

    @Override
    public boolean isActiva(){
        return activa;
    }

    @Override
    public void setActiva(boolean activa){
        this.activa = activa;
    }

    @Override
    public void aplicar(List<Sensor> sensores, List<Actuador> actuadores){

        // Comprobamos si el usuario ha apagado la regla manualmente. Si es así, la regla no se aplica

        if (!activa){
            return;
        }

        double lux = 0.0;
        double presencia = 0.0;
        Actuador bombilla = null;

        // Buscamos el sensor de luz y presencia y que nos devuelva la luz ambiental que hay y si hay o no alguien

        for (Sensor sensor: sensores){
            if(sensor.getID().equals("luz")){
                lux = sensor.getValor();
            }
            if(sensor.getID().equals("pir")){
                presencia = sensor.getValor();
            }
        }

        // Buscamos la bombilla

        for (Actuador actuador: actuadores){
            if(actuador.getID().equals("bulb")){
                bombilla = actuador;
                break;
            }
        }

        // Aplicamos la regla

        if (bombilla!=null){

            // Comprobamos primero si hay alguien en la sala

            if (presencia == 1.0){

                if (lux < 100.0){

                    System.out.println("Hay gente y está oscuro. Encendiendo luces");
                    bombilla.ejecutarAccion("ON");

                } else {

                    System.out.println("Hay gente pero no está oscuro. Apagando luces");
                    bombilla.ejecutarAccion("OFF");
                }

            } else {

                System.out.println("No hay nadie. Apagando luces");
                bombilla.ejecutarAccion("OFF");
            }
        }
    }
}
