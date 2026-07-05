FROM eclipse-temurin:21-jdk

WORKDIR /app

COPY . .

RUN chmod +x mvnw

RUN ./mvnw clean package -DskipTests

EXPOSE 8085

ENTRYPOINT ["java","-jar","target/notification-service-0.0.1-SNAPSHOT.jar"]
