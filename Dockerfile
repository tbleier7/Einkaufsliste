FROM eclipse-temurin:17-jre-alpine
LABEL org.opencontainers.image.source https://github.com/tbleier7/Essensplanung
COPY target/*.jar essensplanung.jar
ENTRYPOINT ["java", "-jar", "/essensplanung.jar"]
