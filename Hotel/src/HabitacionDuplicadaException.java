/**
 * Se lanza cuando se intenta registrar dos veces la misma habitacion
 * dentro de una misma reserva.
 */
public class HabitacionDuplicadaException extends Exception {
    public HabitacionDuplicadaException(String mensaje) {
        super(mensaje);
    }
}
