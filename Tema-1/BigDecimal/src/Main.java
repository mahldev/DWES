import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

public class Main {

    public static void main(String[] args) {

        BigDecimal baseImponible;
        BigDecimal porcentIVA, promocion, precioConIVA, iva, descuento, precioConDescuento;
        String eleccion;
        boolean error = false;

        Map<String, BigDecimal> ivas = Map.of(
                "general", new BigDecimal("0.21"),
                "reducido", new BigDecimal("0.1"),
                "superreducido", new BigDecimal("0.04"));

        Map<String, BigDecimal> promociones = Map.of(
                "nopro", new BigDecimal("0.0"),
                "mitad", new BigDecimal("0.5"),
                "meno5", new BigDecimal("5.0"),
                "5porc", new BigDecimal("0.05"));

        baseImponible = new BigDecimal(EntradaTeclado.pedirDouble("Introduzca la base imponible: "));
        do {
            if (error) {
                System.out.println("No se encuentra ese tipo de iva, intenlo de nuevo");
            }
            porcentIVA = ivas
                    .get(EntradaTeclado.pedirCadena("Introduzca el tipo de IVA (general, reducido o superreducido): ")
                            .toLowerCase());
            error = true;
        } while (porcentIVA == null);

        error = false;

        do {
            if (error) {
                System.out.println("No se encuentra esta promoción, intenlo de nuevo");
            }
            promocion = promociones.get(eleccion = EntradaTeclado
                    .pedirCadena("Introduzca el código promocional (nopro, mitad, meno5 o 5porc): ")
                    .toLowerCase());
            error = true;
        } while (promocion == null);

        iva = calcularIVA(baseImponible, porcentIVA);
        precioConIVA = baseImponible.add(iva).setScale(2, RoundingMode.HALF_EVEN);
        descuento = calcularDescuento(precioConIVA, promocion, eleccion);
        precioConDescuento = precioConIVA.subtract(descuento).setScale(2, RoundingMode.HALF_EVEN);

        System.out.printf("%nBase imponible: %.2f%n", baseImponible);
        System.out.printf("IVA (%.2f%%): %.2f%n",
                (porcentIVA.multiply(new BigDecimal("100").setScale(2, RoundingMode.HALF_EVEN))), iva);
        System.out.printf("Precio con IVA: %.2f%n", precioConIVA);
        System.out.printf("Cód. promo. (%s): -%.2f%n", eleccion, descuento);
        System.out.printf("TOTAL: %.2f%n", precioConDescuento);
    }

    private static BigDecimal calcularIVA(BigDecimal baseImponible, BigDecimal iva) {
        return baseImponible.multiply(iva);
    }

    private static BigDecimal calcularDescuento(BigDecimal precioConIVA, BigDecimal promocion, String eleccion) {
        if (eleccion.equals("meno5")) {
            return promocion;
        }
        return precioConIVA.multiply(promocion).setScale(2, RoundingMode.HALF_EVEN);
    }
}