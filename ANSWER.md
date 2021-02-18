# LABORATORIO 4 (ARSW) 📚

## INTEGRANTES:📋
* Johan Stiven Guerrero Pineda
* Jonathan Fabian Paez Torres

## Instrucciones 📌
1. Ingrese a la ruta del proyecto, donde se encuentra el archivo POM.xml
2. Ejecute `mvn package` en la consola cmd
![](https://github.com/Johannes26/ARSW-LAB04/blob/master/img/Compilar.PNG)
4. Para ejecutar las pruebas `mvn test`
![](https://github.com/Johannes26/ARSW-LAB04/blob/master/img/test.PNG)

## Ejercicio Introductorio 📌
2. Revise el archivo de configuración de Spring ya incluido en el proyecto (src/main/resources). El mismo indica que Spring buscará automáticamente los 'Beans' disponibles en el paquete indicado.
![](https://github.com/Johannes26/ARSW-LAB04/blob/master/img/intro2.PNG)
3. Haciendo uso de la configuración de Spring basada en anotaciones marque con las anotaciones @Autowired y @Service las dependencias que deben inyectarse, y los 'beans' candidatos a ser inyectadas -respectivamente-:
![](https://github.com/Johannes26/ARSW-LAB04/blob/master/img/intro3-b.PNG)
4. Haga un programa de prueba, donde se cree una instancia de GrammarChecker mediante Spring, y se haga uso de la misma:
![](https://github.com/Johannes26/ARSW-LAB04/blob/master/img/intro4.PNG)
5. Modifique la configuración con anotaciones para que el Bean ‘GrammarChecker‘ ahora haga uso del la clase SpanishSpellChecker (para que a GrammarChecker se le inyecte EnglishSpellChecker en lugar de SpanishSpellChecker. Verifique el nuevo resultado.
![](https://github.com/Johannes26/ARSW-LAB04/blob/master/img/intro5.PNG)

Para consultar el codigo:


## Parte I - Ejercicio Introductorio 📌


### 2. Complete los operaciones getBluePrint() y getBlueprintsByAuthor(). Implemente todo lo requerido de las capas inferiores (por ahora, el esquema de persistencia disponible 'InMemoryBlueprintPersistence') agregando las pruebas correspondientes en 'InMemoryPersistenceTest'.

![](https://https://github.com/Johannes26/ARSW-LAB04/blob/master/img/Parte1-2.PNG)

### Persistencia

![](https://github.com/Johannes26/ARSW-LAB04/blob/master/img/parte1-2Persis.PNG)

### Test

![](https://github.com/Johannes26/ARSW-LAB04/blob/master/img/part1-2test.PNG)

### 3.Haga un programa en el que cree (mediante Spring) una instancia de BlueprintServices, y rectifique la funcionalidad del mismo: registrar planos, consultar planos, registrar planos específicos, etc.

### Programa

![](https://github.com/Johannes26/ARSW-LAB04/blob/master/img/parte1-3.PNG)

### Funcionalidad

![](https://github.com/Johannes26/ARSW-LAB04/blob/master/img/parte1-3Funcionalidad.PNG)

### 4. Filtros

### Filtro A

![](https://github.com/Johannes26/ARSW-LAB04/blob/master/img/filtroA.PNG)

### Filtro B

![](https://github.com/Johannes26/ARSW-LAB04/blob/master/img/filtroB.PNG)

El consumo se debe a que multiples consumidores siempre se mantienen activos, es decir el procesos de consumir sieempre esta activo.

### 5. Pruebas 
![](https://github.com/Johannes26/ARSW-LAB04/blob/master/img/parte5-pruebas.PNG)







