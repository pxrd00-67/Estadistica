package co.estadistica.calculadora;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CalculadoraTest {

    private final Calculadora c = new Calculadora();

    // ---- Suma ----
    @Test @DisplayName("TC-01 suma positivos") void tc01() { assertEquals(8, c.suma(5, 3)); }
    @Test @DisplayName("TC-02 suma negativos") void tc02() { assertEquals(-8, c.suma(-5, -3)); }
    @Test @DisplayName("TC-03 suma con cero") void tc03() { assertEquals(5, c.suma(5, 0)); }
    @Test @DisplayName("TC-04 suma en el límite de int") void tc04() { assertEquals(4294967294L, c.suma(Integer.MAX_VALUE, Integer.MAX_VALUE)); }

    // ---- Resta ----
    @Test @DisplayName("TC-05 resta positivos") void tc05() { assertEquals(2, c.resta(5, 3)); }
    @Test @DisplayName("TC-06 resta resultado negativo") void tc06() { assertEquals(-2, c.resta(3, 5)); }
    @Test @DisplayName("TC-07 resta con negativos") void tc07() { assertEquals(-2, c.resta(-5, -3)); }

    // ---- Multiplicación ----
    @Test @DisplayName("TC-08 multiplicación positivos") void tc08() { assertEquals(15, c.multiplicacion(5, 3)); }
    @Test @DisplayName("TC-09 multiplicación por cero") void tc09() { assertEquals(0, c.multiplicacion(5, 0)); }
    @Test @DisplayName("TC-10 multiplicación signos distintos") void tc10() { assertEquals(-15, c.multiplicacion(-5, 3)); }
    @Test @DisplayName("TC-11 multiplicación en el límite de int") void tc11() { assertEquals(4611686014132420609L, c.multiplicacion(Integer.MAX_VALUE, Integer.MAX_VALUE)); }

    // ---- División ----
    @Test @DisplayName("TC-12 división exacta") void tc12() { assertEquals(2.0, c.division(6, 3)); }
    @Test @DisplayName("TC-13 división con decimales") void tc13() { assertEquals(2.5, c.division(5, 2)); }
    @Test @DisplayName("TC-14 división de cero entre número") void tc14() { assertEquals(0.0, c.division(0, 7)); }
    @Test @DisplayName("TC-15 división negativa") void tc15() { assertEquals(-2.5, c.division(-5, 2)); }
    @Test @DisplayName("TC-16 división entre cero") void tc16() {
        ArithmeticException e = assertThrows(ArithmeticException.class, () -> c.division(5, 0));
        assertEquals("Error: no se puede dividir entre cero.", e.getMessage());
    }
    @Test @DisplayName("TC-17 cero entre cero") void tc17() { assertThrows(ArithmeticException.class, () -> c.division(0, 0)); }

    // ---- Validación de entrada (solo enteros) ----
    private Integer leer(String entrada) {
        return Main.leerEntero(new java.util.Scanner(entrada), "");
    }
    @Test @DisplayName("TC-18 entrada entera válida") void tc18() { assertEquals(42, leer("42\n")); }
    @Test @DisplayName("TC-19 texto no numérico se rechaza y se vuelve a pedir") void tc19() { assertEquals(7, leer("abc\n7\n")); }
    @Test @DisplayName("TC-20 decimal se rechaza y se vuelve a pedir") void tc20() { assertEquals(7, leer("3.5\n7\n")); }
    @Test @DisplayName("TC-21 fuera del rango de int se rechaza") void tc21() { assertEquals(1, leer("99999999999\n1\n")); }
    @Test @DisplayName("TC-22 entrada vacía/fin de datos devuelve null") void tc22() { assertNull(leer("")); }
}
