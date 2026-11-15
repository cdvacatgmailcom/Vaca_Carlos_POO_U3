package controlador;

import java.io.IOException;
import modelo.CantidadEquiposInvalidaException;
import modelo.Equipo;
import modelo.Partido;
import modelo.PersistenciaTorneo;
import modelo.SorteoException;
import modelo.Torneo;
import vista.VistaTorneo;

public class ControladorTorneo {
    private Torneo modelo;
    private PersistenciaTorneo persistencia;
    private VistaTorneo vista;

    public ControladorTorneo(Torneo modelo, PersistenciaTorneo persistencia, VistaTorneo vista) {
        this.modelo = modelo;
        this.persistencia = persistencia;
        this.vista = vista;
    }

    public void iniciarPrograma() {
        int opcion;
        do {
            opcion = vista.mostrarMenuPrincipal();

            switch (opcion) {
                case 1:
                    ejecutarYGuardarTorneo();
                    break;
                case 2:
                    cargarYMostrarTorneo();
                    break;
                case 3:
                    vista.mostrarMensaje("Saliendo del sistema...");
                    break;
                default:
                    vista.mostrarError("Opción no válida. Ingrese 1, 2 o 3.");
            }
        } while (opcion != 3);
    }

    private void ejecutarYGuardarTorneo() {
        try {
            int cantidad = vista.pedirCantidadEquipos();
            // Valida la cantidad directamente en el Modelo antes de pedir los nombres
            new Torneo(modelo.getNombreTorneo(), crearArregloTemporal(cantidad));

            String[] nombresIngresados = vista.pedirNombresEquipos(cantidad);
            Equipo[] equiposPersonalizados = new Equipo[cantidad];
            for (int i = 0; i < cantidad; i++) {
                equiposPersonalizados[i] = new Equipo(nombresIngresados[i]);
            }

            Torneo nuevoTorneo = new Torneo(modelo.getNombreTorneo(), equiposPersonalizados);
            nuevoTorneo.iniciar();

            this.modelo = nuevoTorneo;
            mostrarResultadosCompletos(this.modelo);

            persistencia.guardar(this.modelo);
            vista.mostrarMensaje("Resultados guardados correctamente en el archivo binario.");

        } catch (CantidadEquiposInvalidaException e) {
            vista.mostrarError("Número de equipos inválido: " + e.getMessage());
        } catch (SorteoException e) {
            vista.mostrarError("Error en el sorteo o fases del torneo: " + e.getMessage());
        } catch (IOException e) {
            vista.mostrarError("No se pudo guardar el archivo binario: " + e.getMessage());
        }
    }

    private Equipo[] crearArregloTemporal(int cantidad) {
        if (cantidad <= 0) {
            return new Equipo[0]; // Delega el lanzamiento de CantidadEquiposInvalidaException a Torneo
        }
        Equipo[] temp = new Equipo[cantidad];
        for (int i = 0; i < cantidad; i++) {
            temp[i] = new Equipo("Temp " + (i + 1));
        }
        return temp;
    }

    private void cargarYMostrarTorneo() {
        if (!persistencia.existeArchivoGuardado()) {
            vista.mostrarError("Aún no existe ningún torneo guardado. Ejecuta primero la opción 1.");
            return;
        }

        try {
            this.modelo = persistencia.cargar();
            vista.mostrarMensaje("Torneo recuperado desde el archivo binario.");
            mostrarResultadosCompletos(this.modelo);
        } catch (IOException | ClassNotFoundException e) {
            vista.mostrarError("Error al leer el archivo binario: " + e.getMessage());
        }
    }

    private void mostrarResultadosCompletos(Torneo torneoActual) {
        vista.mostrarBienvenida(torneoActual.getNombreTorneo());
        vista.mostrarOrdenSorteo(torneoActual.getEquipos());

        for (Partido[] partidosDeRonda : torneoActual.getHistorialFases()) {
            vista.mostrarFase(partidosDeRonda);
        }

        vista.mostrarCampeon(torneoActual.getCampeon());
    }
}