import java.util.ArrayList;
import java.util.List;

public class Main {
    private static List<Integer> extreaPares(List<Integer> numeros) {
        return numeros.stream()
                .filter(n -> n % 2 == 0)
                .toList();
    }

    private static List<Integer> extreaImpares(List<Integer> numeros) {
        return numeros.stream()
                .filter(n -> n % 2 != 0)
                .toList();
    }

    private static Integer reduce(List<Integer> lista) {
        return lista.stream()
                .reduce(Integer::sum)
                .orElse(0);
    }

    public static void main(String[] args) {

        int num, mediaPares, mediaImpares;
        List<Integer> numeros = new ArrayList<>();

        while ((num = EntradaTeclado.pedirEntero("Introduzca un numero: ")) >= 0) {
            numeros.add(num);
        }

        System.out.printf("Cantidad de números introducidos: %d%n", numeros.size());

        List<Integer> pares = extreaPares(numeros);
        List<Integer> impares = extreaImpares(numeros);


        System.out.printf("%nLa media de los pares es: %d%n", (reduce(pares) / pares.size()));
        System.out.printf("La media de los impares es: %d%n", (reduce(impares) / impares.size()));
    }
}
