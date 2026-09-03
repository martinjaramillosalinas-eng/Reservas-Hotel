import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Reserva {

    public static final int MAX_HABITACIONES = 5;


    private static int contador = 2031;

    private final int id;
    private final String huesped;
    private final List<Habitacion> habitaciones;

    public Reserva(String huesped) {
        this.id = contador++;
        this.huesped = (huesped == null || huesped.trim().isEmpty()) ? "Sin nombre" : huesped.trim();
        this.habitaciones = new ArrayList<>();
    }

    public Reserva() {
        this("Sin nombre");
    }


    public void agregarHabitacion(Habitacion habitacion)
            throws ReservaLlenaException, HabitacionDuplicadaException {

        if (habitacion == null) {
            throw new IllegalArgumentException("No se puede agregar una habitacion nula.");
        }
        if (habitaciones.size() >= MAX_HABITACIONES) {
            throw new ReservaLlenaException(
                    "La reserva #" + id + " ya tiene el maximo de " + MAX_HABITACIONES + " habitaciones.");
        }
        if (habitaciones.contains(habitacion)) {   // usa equals() de Habitacion
            throw new HabitacionDuplicadaException(
                    "La habitacion \"" + habitacion.getNombre() + "\" (" + habitacion.getTipo()
                            + ") ya esta registrada en la reserva #" + id + ".");
        }
        habitaciones.add(habitacion);
    }


    public double calcularTotal() {
        double total = 0;
        for (Habitacion h : habitaciones) {
            total += h.calcularTotal();
        }
        return total;
    }

    public int getId() {
        return id;
    }

    public String getHuesped() {
        return huesped;
    }

    public int getCantidadHabitaciones() {
        return habitaciones.size();
    }


    public List<Habitacion> getHabitaciones() {
        return Collections.unmodifiableList(habitaciones);
    }


    public String resumen() {
        StringBuilder sb = new StringBuilder();
        String linea = "-".repeat(96);

        sb.append("Reserva #").append(id)
          .append("   |   Huesped: ").append(huesped).append("\n");
        sb.append(linea).append("\n");
        sb.append(String.format("%-16s %-24s %14s %8s %18s %14s%n",
                "Habitacion", "Tipo", "Tarifa base", "Noches", "Ajustes", "Total"));
        sb.append(linea).append("\n");

        if (habitaciones.isEmpty()) {
            sb.append("(sin habitaciones registradas)\n");
        } else {
            for (Habitacion h : habitaciones) {
                sb.append(String.format("%-16s %-24s %14s %8d %18s %14s%n",
                        h.getNombre(),
                        h.getTipo(),
                        Formato.moneda(h.getTarifaBase()),
                        h.getNoches(),
                        h.getDescripcionAjuste(),
                        Formato.moneda(h.calcularTotal())));
            }
        }

        sb.append(linea).append("\n");
        sb.append(String.format("Habitaciones: %d/%d%nTotal reserva: %s%n",
                habitaciones.size(), MAX_HABITACIONES, Formato.moneda(calcularTotal())));
        return sb.toString();
    }

    public void mostrarResumen() {
        System.out.println(resumen());
    }

    @Override
    public String toString() {
        return "Reserva #" + id + " (" + habitaciones.size() + " habitaciones, total "
                + Formato.moneda(calcularTotal()) + ")";
    }
}
