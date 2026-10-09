package co.estadistica.calculadora;

import java.util.Scanner;

/** Aplicación de consola: lee dos enteros y muestra los resultados. */
public class Main {

    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        Calculadora calc = new Calculadora();

        Integer a = leerEntero(entrada, "Ingrese el valor de a: ");
        Integer b = leerEntero(entrada, "Ingrese el valor de b: ");
        if (a == null || b == null) {
            return;
        }

        System.out.println("a + b = " + calc.suma(a, b));
        System.out.println("a - b = " + calc.resta(a, b));
        System.out.println("a * b = " + calc.multiplicacion(a, b));
        try {
            System.out.println("a / b = " + calc.division(a, b));
        } catch (ArithmeticException e) {
            System.out.println("a / b -> " + e.getMessage());
        }
    }

    /**
     * Lee un entero. Mientras el texto no sea un entero válido muestra el error y vuelve
     * a pedir el dato. Devuelve null solo si se acaba la entrada.
     */
    static Integer leerEntero(Scanner entrada, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            if (!entrada.hasNextLine()) {
                System.out.println("Error: no se recibió ningún valor.");
                return null;
            }
            String texto = entrada.nextLine().trim();
            try {
                return Integer.parseInt(texto);
            } catch (NumberFormatException e) {
                System.out.println("Error: \"" + texto + "\" no es un número entero válido "
                        + "(solo se aceptan enteros entre " + Integer.MIN_VALUE + " y " + Integer.MAX_VALUE + ").");
            }
        }
    }
}
