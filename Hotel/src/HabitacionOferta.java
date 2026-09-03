
public class HabitacionOferta extends Habitacion {

    private final double porcentajeDescuento; // 0 a 100

    public HabitacionOferta(String nombre, double tarifaBase, int noches, double porcentajeDescuento)
            throws DatosInvalidosException {
        super(nombre, tarifaBase, noches);
        if (porcentajeDescuento < 0 || porcentajeDescuento > 100) {
            throw new DatosInvalidosException(
                    "El descuento debe estar entre 0 y 100. Valor recibido: " + porcentajeDescuento);
        }
        this.porcentajeDescuento = porcentajeDescuento;
    }


    public HabitacionOferta(String nombre, double tarifaBase, int noches) throws DatosInvalidosException {
        this(nombre, tarifaBase, noches, 0);
    }

    @Override
    protected double factorAjuste() {
        return 1 - (porcentajeDescuento / 100.0);
    }

    @Override
    public String getTipo() {
        return "Oferta temporada baja";
    }

    @Override
    public String getDescripcionAjuste() {
        if (porcentajeDescuento == 0) {
            return "sin descuento";
        }
        return String.format("-%.0f%% descuento", porcentajeDescuento);
    }

    public double getPorcentajeDescuento() {
        return porcentajeDescuento;
    }
}
