import java.util.Locale;

public final class Formato {

    private Formato() {
        // clase de utilidad: no se instancia
    }

    public static String moneda(double valor) {
        return String.format(Locale.US, "%,.0f", valor);
    }
}
