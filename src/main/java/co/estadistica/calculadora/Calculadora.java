package co.estadistica.calculadora;

/**
 * Operaciones aritméticas sobre números enteros.
 * Cada operación es un método independiente.
 */
public class Calculadora {

    public long suma(int a, int b) {
        return (long) a + b;
    }

    public long resta(int a, int b) {
        return (long) a - b;
    }

    public long multiplicacion(int a, int b) {
        return (long) a * b;
    }

    /**
     * @throws ArithmeticException si b es cero.
     */
    public double division(int a, int b) {
        if (b == 0) {
            throw new ArithmeticException("Error: no se puede dividir entre cero.");
        }
        return (double) a / b;
    }
}
