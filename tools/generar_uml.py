#!/usr/bin/env python3
"""Genera docs/casos_de_uso.svg y docs/casos_de_uso.puml. Uso: generar_uml.py [--potencia]"""
import sys
potencia = "--potencia" in sys.argv

ops = [("Sumar", "UC2"), ("Restar", "UC3"), ("Multiplicar", "UC4"), ("Dividir", "UC5")]
if potencia:
    ops.append(("Calcular potencia (a^b)", "UC9"))

# ---------- PlantUML ----------
p = ["@startuml", "left to right direction", "actor Usuario as U", "rectangle \"Calculadora de enteros\" {",
     "  usecase \"Ingresar dos enteros\" as UC1", "  usecase \"Validar que sean enteros\" as UC8"]
for n, i in ops:
    p.append(f"  usecase \"{n}\" as {i}")
p += ["  usecase \"Manejar división por cero\" as UC7", "  usecase \"Mostrar resultados y mensajes\" as UC6", "}",
      "U --> UC1"]
for n, i in ops:
    p.append(f"U --> {i}")
p += ["UC1 ..> UC8 : <<include>>", "UC7 ..> UC5 : <<extend>>"]
for n, i in ops:
    p.append(f"{i} ..> UC6 : <<include>>")
p.append("@enduml")
open("docs/casos_de_uso.puml", "w", encoding="utf-8").write("\n".join(p) + "\n")

# ---------- SVG ----------
W = 760
rows = len(ops)
H = 150 + 80 * (rows + 1)
pos = {}
svg = [f'<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 {W} {H}" font-family="Arial,Helvetica,sans-serif" font-size="13">',
       '<defs><marker id="a" markerWidth="10" markerHeight="8" refX="9" refY="4" orient="auto"><path d="M0,0 L10,4 L0,8 z" fill="#333"/></marker></defs>',
       f'<rect width="{W}" height="{H}" fill="white"/>',
       f'<rect x="170" y="40" width="{W-190}" height="{H-60}" fill="#f7f9fc" stroke="#305496" stroke-width="2"/>',
       '<text x="180" y="32" font-weight="bold">Sistema: Calculadora de enteros (consola)</text>']

shapes, lines, labels = [], [], []
RX, RY = 105, 26

def uc(key, label, cx, cy):
    pos[key] = (cx, cy)
    shapes.append(f'<ellipse cx="{cx}" cy="{cy}" rx="{RX}" ry="{RY}" fill="#fff" stroke="#305496" stroke-width="1.5"/>')
    shapes.append(f'<text x="{cx}" y="{cy+4}" text-anchor="middle">{label}</text>')

def borde(c, o):
    """Punto del borde de la elipse c en dirección a o."""
    dx, dy = o[0] - c[0], o[1] - c[1]
    t = 1 / ((dx / RX) ** 2 + (dy / RY) ** 2) ** 0.5
    return c[0] + dx * t, c[1] + dy * t

def line(a, b, dashed=False, label=None):
    pa, pb = pos[a], pos[b]
    x1, y1 = pa if a == "U" else borde(pa, pb)
    x2, y2 = borde(pb, pa)
    st = ' stroke-dasharray="6,4"' if dashed else ""
    mk = ' marker-end="url(#a)"' if dashed else ""
    lines.append(f'<line x1="{x1:.0f}" y1="{y1:.0f}" x2="{x2:.0f}" y2="{y2:.0f}" stroke="#333"{st}{mk}/>')
    if label:
        labels.append(f'<text x="{(x1+x2)/2:.0f}" y="{(y1+y2)/2-6:.0f}" text-anchor="middle" font-size="11" fill="#555">{label}</text>')

y0 = 90
uc("UC1", "Ingresar dos enteros", 320, y0)
uc("UC8", "Validar que sean enteros", 600, y0)
for k, (n, i) in enumerate(ops):
    uc(i, n, 320, y0 + 80 * (k + 1))
uc("UC7", "Manejar división por cero", 600, y0 + 80 * 4)
uc("UC6", "Mostrar resultados y mensajes", 600, y0 + 80 * 2)

# actor
ay = H // 2
pos["U"] = (90, ay)
svg.append(f'<circle cx="90" cy="{ay-45}" r="14" fill="none" stroke="#333" stroke-width="2"/>')
svg.append(f'<path d="M90,{ay-31} V{ay+10} M60,{ay-18} H120 M90,{ay+10} L65,{ay+45} M90,{ay+10} L115,{ay+45}" stroke="#333" stroke-width="2" fill="none"/>')
svg.append(f'<text x="90" y="{ay+65}" text-anchor="middle" font-weight="bold">Usuario</text>')
pos["U"] = (118, ay - 18)
line("U", "UC1")
for n, i in ops:
    line("U", i)
line("UC1", "UC8", True, "«include»")
line("UC7", "UC5", True, "«extend»")
for n, i in ops:
    line(i, "UC6", True)
svg.append(f'<text x="{W-20}" y="{H-8}" text-anchor="end" font-size="11" fill="#555">Las flechas punteadas sin etiqueta hacia “Mostrar resultados” son «include».</text>')
svg[5:5]=lines
svg += shapes + labels
svg.append("</svg>")
open("docs/casos_de_uso.svg", "w", encoding="utf-8").write("\n".join(svg) + "\n")
