# Use a imagem base do OpenJDK 17
FROM openjdk:17-alpine

# Crie um diretório para a aplicação
WORKDIR /app

# Copie o JAR da sua aplicação para o diretório /app
COPY target/api-frete-0.0.1-SNAPSHOT.jar /app/app.jar

# Exponha a porta que sua aplicação está usando (ajuste conforme necessário)
EXPOSE 8080

# Comando para iniciar a aplicação ao iniciar o contêiner
CMD ["java", "-jar", "/app/app.jar"]
