package controlador;

import java.io.IOException;
import modelo.Partido;
import modelo.PersistenciaTorneo;
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
                    vista.mostrarError("Opción no válida.");
            }
        } while (opcion != 3);
    }

    private void ejecutarYGuardarTorneo() {
        modelo.iniciar();
        mostrarResultadosCompletos(modelo);

        try {
            persistencia.guardar(modelo);
            vista.mostrarMensaje("Resultados guardados correctamente en el archivo binario.");
        } catch (IOException e) {
            vista.mostrarError("No se pudo guardar el archivo: " + e.getMessage());
        }
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
