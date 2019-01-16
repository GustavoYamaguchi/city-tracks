FROM openjdk:8

RUN git clone https://github.com/GustavoYamaguchi/city-tracks.git

WORKDIR /city-tracks/city-track-core

CMD ./mvnw spring-boot:run -Dspring-boot.run.arguments="--eureka.client.service-url.default-zone=${NAMING_SERVER_URL}"
