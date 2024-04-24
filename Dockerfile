FROM eclipse-temurin:17
WORKDIR /home
COPY ./target/ducks-service-0.0.1-SNAPSHOT.jar lab9.jar
ENTRYPOINT ["java", "-jar", "lab9.jar"]