[![Build Status](https://travis-ci.org/gylopezgiles/TP2.svg?branch=develop)](https://travis-ci.org/gylopezgiles/TP2)

[![codecov](https://codecov.io/gh/gylopezgiles/TP2/branch/develop/graph/badge.svg)](https://codecov.io/gh/gylopezgiles/TP2)



# TP2 Algoritmos 3: Kahoot

Trabajo Práctico número 2 de la materia Algoritmos y Programación III de FIUBA

## Grupo 1

* **Luis Paredes** - [104851](https://github.com/LuisParedes1)
* **Micaela Villordo** - [103828](https://github.com/micaelavillordo)
* **Gisela Lopez Giles** - [104842](https://github.com/gylopezgiles)
* **Alan Goyzueta** - [102988](https://github.com/AlanCristianGoyzueta)
* **Mario Besednjak** - [103287](https://github.com/besednjak)

Corrector: **Diego Sánchez**

#### Diagrama de clases

![Modelo Juego](https://github.com/gylopezgiles/TP2/blob/develop/doc/Diagramas/Diagramas_Clase/Imagenes/Modelo%20General%20del%20Juego.png)

![Modelo Preguntas](https://github.com/gylopezgiles/TP2/blob/develop/doc/Diagramas/Diagramas_Clase/Imagenes/Modelo%20de%20Pregunta%20usando%20Simple%20Factory.png)

![Modelo Cargador Preguntas](https://github.com/gylopezgiles/TP2/blob/develop/doc/Diagramas/Diagramas_Clase/Imagenes/Modelo%20de%20CargadorPreguntas.png)

![Modelo Multiplicador](https://github.com/gylopezgiles/TP2/blob/develop/doc/Diagramas/Diagramas_Clase/Imagenes/Modelo%20de%20Multiplicador.png)

![Modelo Exclusividad](https://github.com/gylopezgiles/TP2/blob/develop/doc/Diagramas/Diagramas_Clase/Imagenes/Modelo%20Exclusividad.png)

#### Diagrama de secuencia

##### VerdaderoFalsoClasico

![dc](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/gylopezgiles/TP2/develop/doc/Diagramas/Diagramas_Secuencia/VerdaderoFalsoClasico/VerdaderoFalsoClasicoCreacion.plantuml)

![dc](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/gylopezgiles/TP2/develop/doc/Diagramas/Diagramas_Secuencia/VerdaderoFalsoClasico/VerdaderoClasicoResponder.plantuml)

![dc](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/gylopezgiles/TP2/develop/doc/Diagramas/Diagramas_Secuencia/VerdaderoFalsoClasico/VerdaderoFalsoClasicoCreacionError.plantuml)

##### VerdaderoFalsoPenalidad

![dc](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/gylopezgiles/TP2/develop/doc/Diagramas/Diagramas_Secuencia/VerdaderoFalsoPenalidad/CrearVerdaderoFalsoPenalidad.plantuml)

![dc](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/gylopezgiles/TP2/develop/doc/Diagramas/Diagramas_Secuencia/VerdaderoFalsoPenalidad/CrearVerdaderoFalsoPenalidadError.plantuml)

![dc](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/gylopezgiles/TP2/develop/doc/Diagramas/Diagramas_Secuencia/VerdaderoFalsoClasico/VerdaderoFalsoClasicoCreacionError.plantuml)

![dc](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/gylopezgiles/TP2/develop/doc/Diagramas/Diagramas_Secuencia/VerdaderoFalsoConPenalidad/alResponderCorrectamenteDebeSumarUnPunto.plantuml)

![dc](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/gylopezgiles/TP2/develop/doc/Diagramas/Diagramas_Secuencia/VerdaderoFalsoConPenalidad/alResponderIncorrectamenteDebeRestarUnPunto.plantuml)

##### MultipleChoiceClasico

![dc](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/gylopezgiles/TP2/develop/doc/Diagramas/Diagramas_Secuencia/MultipleChoiceClasico/MultipleChoiceClasicoCreacion.plantuml)

![dc](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/gylopezgiles/TP2/develop/doc/Diagramas/Diagramas_Secuencia/MultipleChoiceClasico/MultipleChoiceClasicoCreacionError.plantuml)


##### MultipleChoiceParcial

![dc](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/gylopezgiles/TP2/develop/doc/Diagramas/Diagramas_Secuencia/MultiplieChoiceParcial/MultiplieChoiceParcialCreacion.plantuml)

![dc](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/gylopezgiles/TP2/develop/doc/Diagramas/Diagramas_Secuencia/MultiplieChoiceParcial/MultipleChoiceParcialCreacionError.plantuml)

![dc](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/gylopezgiles/TP2/develop/doc/Diagramas/Diagramas_Secuencia/MultiplieChoiceParcial/MultipleChoiceParcialSumaPuntosSegunCantOpcionesCorrectas.plantuml)

![dc](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/gylopezgiles/TP2/develop/doc/Diagramas/Diagramas_Secuencia/MultiplieChoiceParcial/MultipleChoiceParcialNoSumaPuntosConOpcionIncorrectaSeleccionada.plantuml)

### Pre-requisitos

Listado de software/herramientas necesarias para el proyecto

```
java 11
maven 3.6.0
...
```

## Ejecutando las pruebas

```bash
    mvn test
```

Este comando crea el reporte de cobertura para CI y el reporte HTML que pueden abrir de la siguiente manera:

```bash
    <browser> ./target/site/jacoco/index.html
```

## Ejecutando la aplicación

Explicación de como ejecutar la aplicación

## Licencia

Este repositorio está bajo la Licencia MIT
