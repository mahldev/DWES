import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Main {
    public static Scanner sc = new Scanner(System.in);

    public static Double calcularMedia(List<Double> notas) {
        Double media = notas.stream()
                .reduce(Double::sum)
                .orElse(0.0);
        return media / notas.size();
    }

    public static void main(String[] args) {

        List<Double> notas = new ArrayList<>();
        double media;
        boolean apto;

        notas.add(pedirNota("primer"));
        notas.add(pedirNota("segundo"));

        media = calcularMedia(notas);


        if (media < 5) {
            apto = EntradaTeclado.pedirCadena("¿Cuál ha sido el resultado de la recuperación? (apto/no apto): ").equals("apto");
            if (apto) {
                media = 5;
            }
        }
        System.out.printf("Tu nota de Programación es %s \n", media);
    }

    private static double pedirNota(String s) {
        return EntradaTeclado.pedirDouble("Nota del " + s + " control: ");
    }
}