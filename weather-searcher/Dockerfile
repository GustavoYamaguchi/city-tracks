FROM openjdk:8

ADD target/weather-searcher.jar /

# When running this service, you must to declare an environment variable called APP_ID
CMD ["java", "-jar", "weather-searcher.jar", "--app.id=${APP_ID}"]
