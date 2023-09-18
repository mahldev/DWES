import java.util.*;

public class EntradaTeclado {

    private static Scanner teclado;

    static {
        teclado = new Scanner(System.in);
    }

    public static String pedirCadena(String cad) {
        String cadena = "";

        System.out.print(cad);
        cadena += teclado.nextLine();

        return cadena;
    }

    public static char pedirChar(String cad) {
        String cadena = " ";

        System.out.print(cad);

        do {
            cadena = teclado.nextLine();

            if (cadena.length() > 1) {
                System.err.println("\nHa habido un error. Por favor introduzca un solo caracter.");
            } else if (cadena.length() < 1) {
                System.err.println("\nHa habido un error. Por favor introduzca un caracter.");
            }

        } while (cadena.length() != 1);

        return cadena.toUpperCase().charAt(0);
    }

    public static int pedirEntero(String cadena) {
        int num = 0;
        boolean error = false;

        System.out.print(cadena);

        do {
            error = false;
            try {
                num = teclado.nextInt();
            } catch (InputMismatchException ex) {
                System.err.println(
                        "\nHa habido un error, el dato introducido no es un número. Por favor vuelva a intentarlo");
                error = true;
            } finally {
                teclado.nextLine();
            }
        } while (error == true);

        return num;
    }

    public static float pedirFloat(String cadena) {
        float num = 0;
        boolean error = false;

        System.out.print(cadena);

        do {
            error = false;
            try {
                num = teclado.nextFloat();
            } catch (InputMismatchException ex) {
                System.err.println(
                        "\nHa habido un error, el dato introducido no es un número. Por favor vuelva a intentarlo");
                error = true;
            } finally {
                teclado.nextLine();
            }
        } while (error == true);

        return num;
    }

    public static double pedirDouble(String cadena) {
        double num = 0;
        boolean error = false;

        System.out.print(cadena);

        do {
            error = false;
            try {
                num = teclado.nextDouble();
            } catch (InputMismatchException ex) {
                System.err.println(
                        "\nHa habido un error, el dato introducido no es un número. Por favor vuelva a intentarlo");
                error = true;
            } finally {
                teclado.nextLine();
            }
        } while (error == true);

        return num;
    }

    public static long pedirLong(String cadena) {
        long num = 0;
        boolean error = false;

        System.out.print(cadena);

        do {
            error = false;
            try {
                num = teclado.nextLong();
            } catch (InputMismatchException ex) {
                System.err.println(
                        "\nHa habido un error, el dato introducido no es un número. Por favor vuelva a intentarlo");
                error = true;
            } finally {
                teclado.nextLine();
            }
        } while (error == true);

        return num;
    }

    public static int pedirEdad(String cadena) {
        int edad = 0,
                anio = 0,
                mes = 0,
                dia = 0;
        boolean error = false;
        String fechanac = "";

        System.out.print(cadena);// el formato para la fecha ser� YYYY/MM/DD

        do {// controlamos que la cadena tenga una longitud de 10 y los posibles errores del
            // casteo
            error = false;
            try {
                fechanac = teclado.nextLine();
                anio = Integer.parseInt(fechanac.substring(0, 4));
                mes = Integer.parseInt(fechanac.substring(5, 7));
                dia = Integer.parseInt(fechanac.substring(8, 10));
            } catch (NumberFormatException ex) {
                System.err.println("\nHa habido un error en la entrada de datos. Por favor vuelva a intentarlo");
                error = true;
            } catch (StringIndexOutOfBoundsException ex) {
                System.err.println("\nHa habido un error en la entrada de datos. Por favor vuelva a intentarlo");
                error = true;
            }
        } while (fechanac.length() != 10 || error == true);

        // Creamos un objeto calendario con la fecha actual
        GregorianCalendar hoy = new GregorianCalendar();

        int anio2 = hoy.get(GregorianCalendar.YEAR);
        int mes2 = hoy.get(GregorianCalendar.MONTH);// 0 para enero y 11 para diciembre
        int dia2 = hoy.get(GregorianCalendar.DAY_OF_MONTH);

        // calculamos la edad
        edad = (anio2 - anio - 1);
        if (mes2 >= (mes - 1) && dia2 >= dia) {
            edad++;
        }

        return edad;
    }

    public static char continuar() {
        char resp = ' ';
        boolean error = false;

        System.out.print("\n¿Desea continuar? (S/N)");

        do {
            error = false;
            try {
                resp = teclado.nextLine().toLowerCase().charAt(0);
            } catch (StringIndexOutOfBoundsException ex) {
                error = true;
                System.err.println("\nHa habido un error. Por favor introduzca S/n");
            }
        } while (resp != 's' && resp != 'n' || error == true);

        return resp;
    }

    public static boolean pedirBoolean(String cadena) {

        System.out.print(cadena);
        String sn = EntradaTeclado.pedirCadena("");
        boolean valor = false;

        if (sn.toUpperCase().charAt(0) == 'S') {
            valor = true;
        } else {
            valor = false;
        }

        return valor;
    }

}
