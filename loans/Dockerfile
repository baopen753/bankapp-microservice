# in case of we haven't built JAR file, we need to add 2 images:
#   + maven image for packaging application
#   + openjdk image for running JAR file

# ==> by using multi-stage, we define 2 stages


# Stage 1
# specify the base image
FROM openjdk:23-slim

# config metadata
LABEL org.opencontainers.image.title="Bank backend application"
LABEL org.opencontainers.image.description="Loanns microservice"
LABEL org.opencontainers.image.version="1.0.0"
LABEL org.opencontainers.image.vendor="Baopen753"

WORKDIR /app

COPY target/loans-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

# execute the application
ENTRYPOINT ["java" , "-jar", "app.jar"]
