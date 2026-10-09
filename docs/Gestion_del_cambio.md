# Gestión del cambio — Solicitud CR-001: operación potencia

Un requisito nuevo **después** de aprobar el documento de requisitos y de haber ejecutado las pruebas es una *solicitud de cambio*. No se acepta de palabra ni se programa "de una vez": se gestiona con el cliente. Pasos seguidos:

## 1. Registrar la solicitud

| Campo | Valor |
|---|---|
| ID | CR-001 |
| Solicitante | Cliente |
| Línea base afectada | Requisitos v1.0 (aprobada), código y pruebas v1.0 |
| Descripción | "Incluir una quinta operación: *a* elevado a *b*, implementada con un ciclo `for`." |
| Prioridad | La define el cliente (se asume media: no bloquea la entrega de v1.0) |

## 2. Analizar el impacto antes de comprometerse

| Área | Impacto |
|---|---|
| **Requisitos** | Nuevo requisito funcional (RF-05 potencia) y restricción de diseño: debe usar un ciclo `for`. |
| **Diseño / UML** | Nuevo caso de uso UC9 "Calcular potencia"; se regenera `casos_de_uso.svg/.puml`. |
| **Código** | Método nuevo `Calculadora.potencia` y una llamada en `Main`. **No se modifican** las 4 operaciones existentes → riesgo bajo. |
| **Pruebas** | 10 casos nuevos (TC-23…TC-32) **y regresión completa** de TC-01…TC-22. Hoja de Excel actualizada. |
| **Calidad (ISO 25010)** | Eficiencia: la potencia es O(b) por el ciclo. Fiabilidad: posible desbordamiento. Mantenibilidad: sirve de medida de la modificabilidad. |
| **Cronograma / costo** | Pequeño pero **no cero**: análisis, código, pruebas, regresión y documentación. Se estima y se comunica al cliente; si afecta fecha o precio, él lo aprueba. |
| **Riesgo** | Bajo: cambio aditivo y aislado. El mayor es dejar sin definir los casos borde (abajo). |

## 3. Aclarar con el cliente lo que el requisito no dice

| # | Pregunta | Supuesto usado mientras responde | Efecto |
|---|---|---|---|
| 1 | ¿Qué hacer con exponente **negativo** (2^-1 = 0,5 no es entero)? | Rechazarlo con mensaje de error | TC-30 |
| 2 | ¿Cuánto vale **0^0**? | 1 (convención matemática habitual) | TC-29 |
| 3 | ¿Qué hacer si el resultado **no cabe** (2^64)? | Mensaje de error, nunca un valor incorrecto | TC-31 |
| 4 | ¿Se acepta a^b con a, b enteros ingresados como las otras operaciones? | Sí, mismos dos valores a y b | — |

Estos supuestos quedan **escritos** y se piden confirmados; si el cliente decide otra cosa (p. ej. aceptar exponentes negativos devolviendo decimales), cambian código y pruebas, no el proceso.

## 4. Obtener la aprobación

Se presenta al cliente el análisis de impacto y las preguntas, y se pide su aprobación explícita del cambio, de sus supuestos y de cualquier ajuste de plazo/costo. Sin esa aprobación no se entrega nada como parte del alcance contratado.

## 5. Implementar con control de versiones

- Línea base v1.0 conservada (commit propio); el cambio va en un commit separado → v1.1, con posibilidad de volver atrás.
- `Calculadora.potencia` usa un ciclo `for` como se pidió, con `Math.multiplyExact` para detectar desbordamiento.
- Se actualizan: `pom.xml` (1.1), UML, tests, hoja de Excel y este documento.

## 6. Verificar

- Pruebas nuevas TC-23…TC-32 + **regresión** de TC-01…TC-22 (el cambio no debe romper lo ya aprobado).
- Resultado: **32 de 32 casos pasan** (`mvn test` y `docs/Pruebas_Calculadora.xlsx`).

## 7. Cerrar el cambio

Se entrega v1.1 con requisitos, UML y pruebas actualizados y se registra el cierre de CR-001 con la aprobación del cliente. Lección para el proyecto: los cambios pasan por el mismo ciclo (impacto → aprobación → implementación → regresión), nunca directo al código.
