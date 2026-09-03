import java.util.Objects;

public abstract class Habitacion {

    private final String nombre;
    private final double tarifaBase;
    private final int noches;

    public Habitacion(String nombre, double tarifaBase, int noches) throws DatosInvalidosException {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new DatosInvalidosException("El nombre/numero de la habitacion no puede estar vacio.");
        }
        if (tarifaBase < 0) {
            throw new DatosInvalidosException(
                    "La tarifa base no puede ser negativa. Valor recibido: " + tarifaBase);
        }
        if (noches <= 0) {
            throw new DatosInvalidosException(
                    "La cantidad de noches debe ser mayor a cero. Valor recibido: " + noches);
        }
        this.nombre = nombre.trim();
        this.tarifaBase = tarifaBase;
        this.noches = noches;
    }


    public double calcularSubtotal() {
        return tarifaBase * noches;
    }


    protected abstract double factorAjuste();


    public abstract String getTipo();


    public abstract String getDescripcionAjuste();


    public double calcularTotal() {
        return calcularSubtotal() * factorAjuste();
    }

    public String getNombre() {
        return nombre;
    }

    public double getTarifaBase() {
        return tarifaBase;
    }

    public int getNoches() {
        return noches;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Habitacion otra = (Habitacion) obj;
        return Double.compare(tarifaBase, otra.tarifaBase) == 0
                && nombre.equalsIgnoreCase(otra.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre.toLowerCase(), tarifaBase, getClass());
    }

    @Override
    public String toString() {
        return String.format("%s (%s) - %d noche(s) - %s - Total: %s",
                nombre, getTipo(), noches,
                getDescripcionAjuste(),
                Formato.moneda(calcularTotal()));
    }
}
