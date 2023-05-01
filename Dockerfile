FROM eclipse-temurin:17-jre-alpine
LABEL org.opencontainers.image.source https://github.com/tbleier7/Essensplanung
COPY target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
