# Acortador de Enlaces
Shortly es una plataforma sencilla y ágil que permite acortar enlaces de manera eficiente, sin requerir credenciales ni restricciones tediosas.

## 🖼️ Capturas de Pantalla
Las imágenes de a continuación han sido tomados el día de publicación del proyecto, la apariencia visual de la web podría no corresponder exactamente a día de hoy

![Landing](https://i.imgur.com/MW6wUa2.png)

![Landing con items](https://i.imgur.com/pqWQapP.png)

![Descripción](https://i.imgur.com/fXqXJL9.png)



## 🚀 Características
- **Acortador de URLs**: Permite introducir una URL y obtener un enlace acortado.
- **Redirección automática**: Al ingresar el enlace acortado en el navegador, redirige automáticamente a la URL original.
- **Historial de links recientes**: Los links que acortes en el momento se almacenarán hasta que recargues la página.
- **Validación de formato de URL**: Verifica que el enlace ingresado tenga un formato válido.
- **Evita duplicados**: Si intentas acortar la misma URL sin recargar la página, se muestra un mensaje de error indicando que ya ha sido acortada.
- **Copiar al portapapeles**: Un icono de copiar permite copiar el enlace acortado con un clic.
- **Diseño responsivo**: Optimizado al 100% para dispositivos móviles y pantallas de diferentes tamaños.
- **Optimización para lighthouse**: Proyecto que cumple con los estándares de lighthouse.

## ⚙️ Tecnologías Utilizadas

- **Frontend**: Angular
- **Backend**: Spring Boot, con base de datos en **PostgreSQL**
- **CSS**: Bootstrap para la interfaz de usuario
## 💻 Instalación
Requisitos Previos
- [Node.js](https://nodejs.org/) y npm
- [Angular CLI](https://angular.io/cli)
- [Java JDK](https://www.oracle.com/java/technologies/javase-downloads.html)
- [PostgreSQL](https://www.postgresql.org/)


```bash
git clone https://github.com/tu_usuario/tu_repositorio.git
cd tu_repositorio
```
Configura el backend en Spring Boot:

En el archivo de configuración application.properties, añade tus credenciales de PostgreSQL:

```bash
spring.datasource.url=jdbc:postgresql://localhost:5432/tu_base_de_datos
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña
```
Instala las dependencias del frontend y del backend.

Inicia el backend con tu IDE preferido o usa:

```bash
./mvnw spring-boot:run
```
Inicia el frontend en el directorio del proyecto de Angular:

```bash
ng serve
```
Abre tu navegador en http://localhost:4200 para probar la aplicación.

