package modelo;

public class SorteoException extends Exception {
    private static final long serialVersionUID = 1L;

    public SorteoException(String mensaje) {
        super(mensaje);
    }

    public SorteoException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}