# Casos añadidos por el cambio v1.1 (potencia). generar_excel.py los incorpora si existe este archivo.
OPS_EXTRA = {"potencia": "a ^ b"}
E1 = "a ^ b -> Error: el exponente debe ser mayor o igual a cero."
E2 = "a ^ b -> Error: el resultado de la potencia es demasiado grande."
CASOS_EXTRA = [
 ("TC-23", "potencia", "Potencia positiva", "2", "3", "8"),
 ("TC-24", "potencia", "Exponente cero", "5", "0", "1"),
 ("TC-25", "potencia", "Exponente uno", "7", "1", "7"),
 ("TC-26", "potencia", "Base negativa, exponente impar", "-2", "3", "-8"),
 ("TC-27", "potencia", "Base negativa, exponente par", "-2", "4", "16"),
 ("TC-28", "potencia", "Base cero", "0", "5", "0"),
 ("TC-29", "potencia", "Cero elevado a cero", "0", "0", "1"),
 ("TC-30", "potencia", "Exponente negativo", "2", "-1", E1),
 ("TC-31", "potencia", "Resultado demasiado grande", "2", "64", E2),
 ("TC-32", "potencia", "Mayor potencia de 2 en long", "2", "62", "4611686018427387904"),
]
