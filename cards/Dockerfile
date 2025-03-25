# specify the base image
FROM openjdk:23-slim

# recommended standard Labels - Open Containers Initiative (OCI)
LABEL org.opencontainers.image.tile="Bank backend application"
LABEL org.opencontainers.image.description="Cards microservice"
LABEL org.opencontainers.image.version="1.0.0"
LABEL org.opencontainers.image.vendor="Baopen753"

# set working directory
WORKDIR /app

# add the applicaiton's JAR to the image (incase of we already built JAR file)
COPY target/cards-0.0.1-SNAPSHOT.jar app.jar

# explose the application port
EXPOSE 8080

# execute the application
ENTRYPOINT ["java" , "-jar", "app.jar"]