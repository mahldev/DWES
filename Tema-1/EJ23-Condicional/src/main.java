import java.util.HashMap;
import java.util.Map;

public class main {


    public static void main(String[] args) {

        double baseImponible;
        Double porcentIVA, promocion, precioConIVA, iva, descuento, precioConDescuento;
        String eleccion;
        boolean error = false;

        Map<String, Double> ivas = Map.of(
                "general", 0.21,
                "reducido", 0.1,
                "superreducido", 0.04
        );

        Map<String, Double> promociones = Map.of(
                "nopro", 0.0,
                "mitad", 0.5,
                "meno5", 5.0,
                "5porc", 0.05
        );

        baseImponible = EntradaTeclado.pedirDouble("Introduzca la base imponible: ");
        do {
            if (error) {
                System.out.println("No se encuentra ese tipo de iva, intenlo de nuevo");
            }
            porcentIVA = ivas.get(EntradaTeclado.pedirCadena("Introduzca el tipo de IVA (general, reducido o superreducido): ")
                    .toLowerCase());
            error = true;
        } while (porcentIVA == null);

        error = false;

        do {
            if (error) {
                System.out.println("No se encuentra esta promoción, intenlo de nuevo");
            }
            promocion = promociones.get(eleccion = EntradaTeclado.pedirCadena("Introduzca el código promocional (nopro, mitad, meno5 o 5porc): ")
                    .toLowerCase());
            error = true;
        } while (promocion == null);

        iva = calcularIVA(baseImponible, porcentIVA);
        precioConIVA = baseImponible + iva;
        descuento = calcularDescuento(precioConIVA, promocion, eleccion);
        precioConDescuento = precioConIVA - descuento;

        System.out.printf("%nBase imponible: %.2f%n", baseImponible);
        System.out.printf("IVA (%.2f%%): %.2f%n", (porcentIVA * 100), iva);
        System.out.printf("Precio con IVA: %.2f%n", precioConIVA);
        System.out.printf("Cód. promo. (%s): -%.2f%n", eleccion, descuento);
        System.out.printf("TOTAL: %.2f%n", precioConDescuento);
    }

    private static Double calcularIVA(double baseImponible, Double iva) {
        return baseImponible * iva;
    }

    private static Double calcularDescuento(double precioConIVA, Double promocion, String eleccion) {
        final Double DESC_MENO5 = 5.00;
        if (eleccion.equals("meno5")) {
            return DESC_MENO5;
        }
        return precioConIVA * promocion;
    }
}
