FROM openjdk:8

ADD target/track-searcher.jar /

# When running this service, you must to declare some environment variables called CLIENT_ID and CLIENT_SECRET
RUN ./mvnw spring-boot:run -Dspring-boot.run.jvmArguments="-DclientId=${CLIENT_ID},DclientSecret=${CLIENT_SECRET}"
