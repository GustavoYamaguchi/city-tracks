FROM openjdk:8

ADD target/weather-searcher.jar /

# When running this service, you must to declare an environment variable called APP_ID
RUN ./mvnw spring-boot:run -Dspring-boot.run.jvmArguments="-DappId=${APP_ID}"
