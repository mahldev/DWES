import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        int num, digito;
        int aux = 0;
        String[] digitos;
        List<Integer> posiciones = new ArrayList<>();

        num = EntradaTeclado.pedirEntero("Introduzca un numero: ");
        digito = EntradaTeclado.pedirEntero("Introduzca el digito: ");


        digitos = (String.valueOf(num)).split("");

        while (aux < digitos.length) {
            if (String.valueOf(digito).equals(digitos[aux])) {
                posiciones.add(aux + 1);
            }
            aux++;
        }
        posiciones.forEach(Main::pinta);
        System.out.println();
    }

    private static void pinta(Integer n) {
        System.out.print(n + " ");
    }
}
