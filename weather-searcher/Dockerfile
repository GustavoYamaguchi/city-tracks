FROM openjdk:8

RUN git clone https://github.com/GustavoYamaguchi/city-tracks.git

WORKDIR /city-tracks/weather-searcher

# When running this service, you must to declare an environment variable called APP_ID
CMD ./mvnw spring-boot:run -Dspring-boot.run.jvmArguments="-DappId=${APP_ID}"
