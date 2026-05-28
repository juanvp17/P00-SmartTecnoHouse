import modelo.SmartTecnoHouse;
import vista.VentanaPrincipal;
import controlador.Controlador;

public class MainApp {

    public static void main(String[] args){

        //Obtenemos el "cerebro" de la casa
        SmartTecnoHouse miCasa = SmartTecnoHouse.getInstance();

        // Creamos la ventana gráfica

        VentanaPrincipal miVentana = new VentanaPrincipal();

        // Lo conectamos con el controlador

        Controlador miControlador = new Controlador(miCasa, miVentana);

        // Mostramos la ventada al usuario

        miVentana.setVisible(true);
    }
}
