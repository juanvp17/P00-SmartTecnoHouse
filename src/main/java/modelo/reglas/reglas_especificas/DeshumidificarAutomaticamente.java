package modelo.reglas.reglas_especificas;

import modelo.Sensor;
import modelo.Actuador;
import modelo.reglas.Regla;
import java.util.List;

public class DeshumidificarAutomaticamente implements Regla {

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

        double humedad = 0.0;
        Actuador deshumi = null;

        // Buscamos el sensor de humedad y que nos devuelva la humedad en el ambiente

        for (Sensor sensor: sensores){
            if(sensor.getID().equals("wet")){
                humedad = sensor.getValor();
                break;
            }
        }

        // Buscamos el deshumidificador

        for (Actuador actuador: actuadores){
            if(actuador.getID().equals("desh")){
                deshumi = actuador;
            }
        }

        // Aplicamos la regla

        if (deshumi!=null){

            if (humedad >= 55.0){

                System.out.println("Hay mucha humedad en el ambiente. Endenciendo Deshumidificador");
                deshumi.ejecutarAccion("ON");

            } else {

                System.out.println("Humedad estable. Apagando deshumidificador");
                deshumi.ejecutarAccion("OFF");
            }
        }
    }
}