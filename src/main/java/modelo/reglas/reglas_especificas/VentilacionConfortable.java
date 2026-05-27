package modelo.reglas.reglas_especificas;

import modelo.Sensor;
import modelo.Actuador;
import modelo.reglas.Regla;
import java.util.List;

public class VentilacionConfortable implements Regla {

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

        double tempActual = 0.0;
        Actuador ventilador = null;

        // Buscamos el sensor de temperatura y que nos devuelva la temperatura actual

        for (Sensor sensor : sensores){
            if(sensor.getID().equals("temp")){
                tempActual = sensor.getValor();
                break;
            }
        }

        // Buscamos el ventilador

        for (Actuador actuador : actuadores){
            if (actuador.getID().equals("fan")){
                ventilador = actuador;
            }
        }

        // Aplicamos las reglas

        if (ventilador!=null){

            if (tempActual >= 23.0 && tempActual < 26.0){

                System.out.println("Ventilador en fuerza BAJA. Hacen :" + tempActual + "ºC");
                ventilador.ejecutarAccion("LOW");

            } else if (tempActual >= 26.0 && tempActual < 28.0){

                System.out.println("Ventilador en fuerza MEDIA. Hacen :" + tempActual + "ºC");
                ventilador.ejecutarAccion("MEDIUM");

            } else if (tempActual >= 28.0){

                System.out.println("Ventilador en fuerza ALTA. Hacen :" + tempActual + "ºC");
                ventilador.ejecutarAccion("HIGH");

            } else {

                System.out.println("Ventilador APAGADO. Hacen :" + tempActual + "ºC");
                ventilador.ejecutarAccion("OFF");
            }
        }
    }
}
