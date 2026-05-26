import modelo.Sensor;
import modelo.dispositivos.sensores.SensorTemperatura;
import modelo.dispositivos.sensores.SensorLuz;
import modelo.dispositivos.sensores.SensorPresencia;

public class MainPrueba {

    public static void main(String[] args) {

        System.out.println("--- INICIANDO PRUEBA DEL SENSOR ---");

        //Instanciamos los sensores. Le pasamos los ID

        Sensor temp = new SensorTemperatura("temp");
        Sensor luz = new SensorLuz("luz");
        Sensor presencia = new SensorPresencia("pir");

        // Comprobamos su estado inicial. Como el constructor le pone 0.0 por defecto, debería imprimir 0.0

        System.out.println("Estado inicial: " + temp.getEstadoActual());
        System.out.println("Estado inicial: " + luz.getEstadoActual());
        System.out.println("Estado inicial: " + presencia.getEstadoActual());

        //Ejecutamos el método que abre el archivo JSON y busca su valor

        System.out.println("Leyendo el archivo JSON...");
        temp.actualizarValor();
        luz.actualizarValor();
        presencia.actualizarValor();


        //Comprobamos el resultado. Debería imprimir el valor + tipo de unidad

        System.out.println("Estado tras leer el JSON: " + temp.getEstadoActual());
        System.out.println("Estado tras leer el JSON: " + luz.getEstadoActual());
        System.out.println("Estado tras leer el JSON: " + presencia.getEstadoActual());

        System.out.println("--- FIN DE LA PRUEBA ---");
    }
}