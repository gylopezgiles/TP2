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

**Informe** - [Link](https://github.com/gylopezgiles/TP2/blob/develop/doc/Informe/Informe%20TP2%20Algoritmos%20y%20Programacion%20III.pdf)

#### Diagrama de clases

![Modelo Juego](https://github.com/gylopezgiles/TP2/blob/develop/doc/Diagramas/Diagramas_Clase/Imagenes/partida.png)

![Modelo Cargador Preguntas](https://github.com/gylopezgiles/TP2/blob/develop/doc/Diagramas/Diagramas_Clase/Imagenes/cargador.png)

![Modelo Multiplicador](https://github.com/gylopezgiles/TP2/blob/develop/doc/Diagramas/Diagramas_Clase/Imagenes/modificable.png)

![Modelo Exclusividad](https://github.com/gylopezgiles/TP2/blob/develop/doc/Diagramas/Diagramas_Clase/Imagenes/exclusividad.png)

#### Diagrama de secuencia

##### Responder UI

![responder ui](https://github.com/gylopezgiles/TP2/blob/develop/doc/Diagramas/Diagramas_Secuencia/imagenes/Responder.png)

##### Responder sin aplicar Multiplicador ni Exclusividad backend

![responder sin multiplicador ni exclusividad](https://github.com/gylopezgiles/TP2/blob/develop/doc/Diagramas/Diagramas_Secuencia/imagenes/ResponderBE.png)


##### Responder aplicando Multiplicador backend

![responder aplicando multiplicador](https://github.com/gylopezgiles/TP2/blob/develop/doc/Diagramas/Diagramas_Secuencia/imagenes/ResponderConMultiplcadorBE.png)


##### Responder aplicando Exclusividad backend

![responder aplicando exclusividad](https://github.com/gylopezgiles/TP2/blob/develop/doc/Diagramas/Diagramas_Secuencia/imagenes/ResponderConExclusividadBE.png)

##### Cargar Preguntas

![cargar preguntas](https://github.com/gylopezgiles/TP2/blob/develop/doc/Diagramas/Diagramas_Secuencia/imagenes/CargadorPreguntas.png)


##### Fin de ronda

![fin ronda](https://github.com/gylopezgiles/TP2/blob/develop/doc/Diagramas/Diagramas_Secuencia/imagenes/finRonda.png)


### Pre-requisitos

Listado de software/herramientas necesarias para el proyecto

```
java 11
maven 3.6.0
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

Utilizando Maven: 
```bash
    mvn exec:java
```

Sin Maven: 
```bash
    java -jar ejecutable/tp2-1.0-SNAPSHOT.jar
```

## Licencia

Este repositorio está bajo la Licencia MIT
