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

    /**
     * Calcula a elevado a b multiplicando a por sí mismo b veces (ciclo for).
     * Convención: cualquier número elevado a 0 es 1 (incluido 0^0).
     *
     * @throws ArithmeticException si b es negativo (el resultado no sería entero)
     *                             o si el resultado no cabe en un long.
     */
    public long potencia(int a, int b) {
        if (b < 0) {
            throw new ArithmeticException("Error: el exponente debe ser mayor o igual a cero.");
        }
        long resultado = 1;
        for (int i = 0; i < b; i++) {
            try {
                resultado = Math.multiplyExact(resultado, (long) a);
            } catch (ArithmeticException e) {
                throw new ArithmeticException("Error: el resultado de la potencia es demasiado grande.");
            }
        }
        return resultado;
    }
}
