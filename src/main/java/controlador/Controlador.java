package controlador;

import modelo.SmartTecnoHouse;
import modelo.Sensor;
import modelo.Actuador;
import modelo.reglas.Regla;
import vista.VentanaPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controlador {

    private SmartTecnoHouse modelo;
    private VentanaPrincipal vista;

    public Controlador(SmartTecnoHouse modelo, VentanaPrincipal vista){

        this.modelo = modelo;
        this.vista = vista;

        // Leemos el JSON nada más se arranque el programa

        this.modelo.ejecutarCiclo();

        // EN cuanto arranca, actualizamos la interfaz con los datos reales

        refrescarVista();

        //Le indicamos a la vista qué hacer cuando el usuario haga click

        iniciarEventos();
    }

    private void iniciarEventos(){

        // El evento cuando el usuario pulse Ejecutar Ciclo

        vista.getBtnCicloAutomatico().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Le indicamos al modelo que lea el JSON y aplique las reglas

                modelo.ejecutarCiclo();

                // Refrescamos los textos de la ventana

                refrescarVista();

            }
        });

        // Botones para reactivar el modo automático

        vista.getBtnReactivarVent().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

               reactivarModoAutomatico("VentilacionConfortable");

            }
        });

        vista.getBtnReactivarHum().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                reactivarModoAutomatico("DeshumidificarAutomaticamente");

            }
        });

        vista.getBtnReactivarLuz().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                reactivarModoAutomatico("IluminacionAutomatica");

            }
        });

        // Botones de control manual del ventilador

        vista.getBtnVentOff().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                accionManual("VentilacionConfortable", "fan", "OFF");

            }
        });

        vista.getBtnVentLow().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                accionManual("VentilacionConfortable", "fan", "LOW");

            }
        });

        vista.getBtnVentMedium().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                accionManual("VentilacionConfortable", "fan", "MEDIUM");

            }
        });

        vista.getBtnVentHigh().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                accionManual("VentilacionConfortable", "fan", "HIGH");

            }
        });

        // Botones de control manual de la bombilla

        vista.getBtnLuzOn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                accionManual("IluminacionAutomatica", "bulb", "ON");

            }
        });

        vista.getBtnLuzOff().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                accionManual("IluminacionAutomatica", "bulb", "OFF");

            }
        });

        // Botones de control manual del deshumidificador

        vista.getBtnHumiOn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                accionManual("DeshumidificarAutomaticamente", "desh", "ON");

            }
        });

        vista.getBtnHumiOff().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                accionManual("DeshumidificarAutomaticamente", "desh", "OFF");

            }
        });

        // Evento que se ejecutará al cerrar la ventana y guardaremos el estado actual

        vista.addWindowListener(new java.awt.event.WindowAdapter(){

            @Override
            public void windowClosing (java.awt.event.WindowEvent windowEvent){

                modelo.guardarEstadoSistema();
            }
        });


    }

    // Método genérico manual que desactiva la regla inteligente y fuerza el estado de un actuador

    private void accionManual(String nombreClaseRegla, String idActuador, String nuevaAccion){

        // Buscamos la regla y la apagamos

        Regla regla = buscarRegla(nombreClaseRegla);
        if (regla!=null){
            regla.setActiva(false);
        }

        // Buscamos el actuador y cambiamos su estado

        for (Actuador a : modelo.getActuadores()){
            if (a.getID().equals(idActuador)){
                a.ejecutarAccion(nuevaAccion);
                break;
            }
        }

        // Tras los cambios, los mostramos por pantalla

        refrescarVista();
    }

    private void reactivarModoAutomatico(String nombreClaseRegla){

        // Buscamos la regla y la encendemos

        Regla regla = buscarRegla(nombreClaseRegla);
        if (regla!=null){
            regla.setActiva(true);
        }

        // Actualizamos los datos y las reglas que están en true toman el control automático

        modelo.ejecutarCiclo();
        refrescarVista();

    }

    // Buscamos en la lista la regla que coincide con el nombre de la clase creada

    private Regla buscarRegla(String nombreClase){
        for (Regla r : modelo.getReglas()){
            if(r.getClass().getSimpleName().equals(nombreClase)){

                return r;
            }
        }

        return null;
    }

     // Método para mostrar al usuario los datos

    private void refrescarVista(){

        // Actualizamos los datos de los sensores

        for (Sensor s: modelo.getSensores()){
            switch (s.getID()){
                case "temp":
                    vista.actualizarTemperatura(s.getEstadoActual());
                    break;
                case "wet":
                    vista.actualizarHumedad(s.getEstadoActual());
                    break;
                case "pir":
                    String presenciaToTexto = (s.getValor() == 1.0) ? "Sí" : "No";
                    vista.actualizarPresencia(presenciaToTexto);
                    break;
                case "luz":
                    vista.actualizarLuz(s.getEstadoActual());
                    break;
            }
        }

        // Actualizamos la visualización de las reglas

        for (Regla r: modelo.getReglas()){
            String regla = r.getClass().getSimpleName();
            if(regla.equals("VentilacionConfortable")){
                vista.actualizarReglaVent(r.isActiva());
            }
            if(regla.equals("IluminacionAutomatica")){
                vista.actualizarReglaLuz(r.isActiva());
            }
            if (regla.equals("DeshumidificarAutomaticamente")){
                vista.actualizarReglaHum(r.isActiva());
            }
        }

        // Actualizamos el color de los botones según su estado

        for (Actuador a : modelo.getActuadores()){
            if(a.getID().equals("fan")){
                vista.marcarVentilador(a.getEstadoActual());
            }

            if(a.getID().equals("bulb")){
                vista.marcarBombilla(a.getEstadoActual());
            }

            if(a.getID().equals("desh")){
                vista.marcarDeshumidificador(a.getEstadoActual());
            }
        }


    }
}

