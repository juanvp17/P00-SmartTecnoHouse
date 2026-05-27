import modelo.SmartTecnoHouse;
import modelo.Sensor;
import modelo.Actuador;

public class MainPrueba {

    public static void main(String[] args) {

        //Pedimos la instancia de la casa.

        SmartTecnoHouse miCasa = SmartTecnoHouse.getInstance();

        // Ejecutamos un ciclo

        miCasa.ejecutarCiclo();

        // Comprobamos si funcionan y leen los sensores y actuadores y sus valores

        for (Sensor s : miCasa.getSensores()) {
            System.out.println("Sensor -> " + s.getNombre() + ": " + s.getEstadoActual());
        }

        for (Actuador a: miCasa.getActuadores()){
            System.out.println("Actuador -> " + a.getNombre() + ": " + a.getEstadoActual());
        }
    }
}