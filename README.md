# MATH Rest API

Math Rest API es una aplicación que permite realizar operaciones matemáticas. En esta primera versión solo está implementada la multiplicación.
La intención de esta pequeña API es mostrar algunos conceptos básicos de implementación de uns servicio REST.

## Consideraciones:

-El logout se hace automático a los 200 segundos. No esta implementado un refresh por ahora.

-El endpoint que muestra el historial de llamadas a la API, al mostrar información sensible, solo puede ser accedido por usuarios con rol ADMIN.

-Las contraseñas de las credenciales son almacenadas ofuscadas en la base de datos. Aun así el endpoint de historial de llamadas muestra los datos de las credenciales elegidas por el usuario en la request que se almacena. Se propone mejorar en el futuro esto y ofuscarlas.

-Por razones de simplicidad se permite asignar Roles pero en una aplicación productiva un usuario comun no podría asignar roles, solo crear su usuario común.

-El proyecto está organizado por funcionalidades en vez de por capas. Prefiero que a traves de ello puedan verse las funcionalidades en vez de que prime la implementacion interna.


## Pre requisitos:

1 - Tener instalado docker:
En Ubuntu Linux: sudo apt install docker.io

2- Tener instalado docker-compose: sudo apt install docker-compose

3- Tener maven instalado


## Instalacion / Ejecucion

-Clonar el proyecto a un workspace local

-Pararse dentro de la carpeta del proyecto y ejecutar:

mvn clean install

-En una consola bash, pararse dentro del proyecto y ejecutar

sudo docker-compose up

En este punto, si todo salio bien, el servicio ya esta levantado.

## Swagger UI

http://localhost:8080/math/operation/swagger-ui/index.html?configUrl=/math/operation/v3/api-docs/swagger-config#/math-operation-controller/multiply

## API Docs

http://localhost:8080/math/operation/v3/api-docs

## Testeando la API

Bajar la coleccion de Postman para probar los endpoints:

https://github.com/cesarlaurenti/math/blob/master/postman/Math-application.postman_collection.json

Con postman podras hacer lo siguiente.

Para probar la API, primero que nada es necesario registrar un usuario, para eso no es necesario estar logueado y hay una request a tal fin.

Luego hay que hacer login con un usuario registrado.

El login devuelve un access token, copiarlo.

En el resto de las request se debe pegar el access token en el parámetro access_token y ejecutar la request. El token tiene una validez de 200 segundos.

El endpoint de historial de transacciones solo podrá ser accedido por usuarios con Rol ADMIN, para lo cual tambien hay una request en la colección.

El endpint de multiplicacion tiene dos path params <a> es el operador izquierdo, y <b> es el operador derecho, ambos son de tipo Double:

http://localhost:8080/math/operation/multiply/<a\>/\<b\>?access_token=\<access-token\>


## Base de datos

La base de datos esta dockerizada y se puede acceder mediante el siguiente comando:

docker exec -it math_postgresqldb_1  psql -U postgres users_db
