
public class Suite extends Habitacion {

    private static final double RECARGO_POR_DEFECTO = 20.0;

    private final double porcentajeRecargo;

    public Suite(String nombre, double tarifaBase, int noches, double porcentajeRecargo)
            throws DatosInvalidosException {
        super(nombre, tarifaBase, noches);
        if (porcentajeRecargo < 0) {
            throw new DatosInvalidosException(
                    "El recargo no puede ser negativo. Valor recibido: " + porcentajeRecargo);
        }
        this.porcentajeRecargo = porcentajeRecargo;
    }

    public Suite(String nombre, double tarifaBase, int noches) throws DatosInvalidosException {
        this(nombre, tarifaBase, noches, RECARGO_POR_DEFECTO);
    }

    @Override
    protected double factorAjuste() {
        return 1 + (porcentajeRecargo / 100.0);
    }

    @Override
    public String getTipo() {
        return "Suite";
    }

    @Override
    public String getDescripcionAjuste() {
        return String.format("+%.0f%% recargo", porcentajeRecargo);
    }

    public double getPorcentajeRecargo() {
        return porcentajeRecargo;
    }
}
