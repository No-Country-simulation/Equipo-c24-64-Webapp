# Usar la imagen oficial de OpenJDK 17 como base
FROM openjdk:17-jdk-alpine

# Establecer el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiar el archivo JAR del proyecto al contenedor
COPY target/hotel-0.0.1-SNAPSHOT.jar app.jar

# Exponer el puerto en el que corre la aplicación Spring Boot
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]