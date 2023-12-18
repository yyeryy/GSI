# [Gestión de Sistemas de Información]()
### Grupo 3

| **Autores**                 |                                               
|-------------------------|
| **Yeray Aller** 	        |
| **Julen Huarte**    		 |
| **Luis Azcoiti**	              |
| **Iván Isusi**		     |
| **Javier Aranguren**	    | 
| **Raúl Itoiz**	             | 
| **Javier Artazcoz**	               | 
| **Ekaitz Arribillaga**	     |
| **Fermin Sola**	     |
---
## Requisitos para el uso de la aplicación:
-   Apache Netbeans con una versión de Java superior a **JDK8**.
-   [MongoDB Compass](https://www.mongodb.com/try/download/compass):       
Para conectar a MongoDB Compass (Base de datos de la aplicación Online) usar el siguiente URI en la aplicación descargada: **mongodb+srv://GSI:G3GSI2023@gsi.lvvnusj.mongodb.net/**.
---  
## Intrucciones de uso:
-   Para repoblar la base de datos, borrando todo el contenido que haya en esta usar la clase: **GSILABS PruebaMongo.java**  
(Tarda un rato en ejecutarse ya que son muchos datos a introducir)  
(Te lo damos ejecutado para que tengas la base de datos lista para su uso).
-   Para ejecutar la aplicación, usar la clase: **GSILabs.ProyectoFinal.DonaAplicacion DonaAplicacion**
-   La ejecución recomendada es (Se puede ver los cambios en la Base de datos usando **MongoDB Compass**):
    -   Registrar un Propietario.  
    (Formato de fecha: YYYY-MM-DD)  
    (Comprobar que no existe).
    -   Añadir su local.  
    (Comprobar que no existe alguno en su dirección).
    -   Añadir una donación.
    -   Cerrar sesión.
    -   Registrar Cliente
    -   Aceptar donación.  
    (Para ello es necesario que se haya creado una nueva donación y no tenga usuario asignado). 