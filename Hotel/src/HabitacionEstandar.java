
public class HabitacionEstandar extends Habitacion {

    public HabitacionEstandar(String nombre, double tarifaBase, int noches) throws DatosInvalidosException {
        super(nombre, tarifaBase, noches);
    }

    @Override
    protected double factorAjuste() {
        return 1.0;
    }

    @Override
    public String getTipo() {
        return "Estandar";
    }

    @Override
    public String getDescripcionAjuste() {
        return "-";
    }
}
