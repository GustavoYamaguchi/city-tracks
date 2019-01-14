FROM openjdk:8

RUN git clone https://github.com/GustavoYamaguchi/city-tracks.git

WORKDIR /city-tracks/track-searcher

# When running this service, you must to declare some environment variables called CLIENT_ID and CLIENT_SECRET
CMD ./mvnw spring-boot:run -Dspring-boot.run.jvmArguments="-DclientId=${CLIENT_ID} DclientSecret=${CLIENT_SECRET}"
