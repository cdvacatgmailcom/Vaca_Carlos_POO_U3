package modelo;

public class CantidadEquiposInvalidaException extends Exception {
    private static final long serialVersionUID = 1L;

    public CantidadEquiposInvalidaException(String mensaje) {
        super(mensaje);
    }

    public CantidadEquiposInvalidaException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}