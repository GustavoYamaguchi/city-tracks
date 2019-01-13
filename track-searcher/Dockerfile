FROM openjdk:8

ADD target/track-searcher.jar /

# When running this service, you must to declare some environment variables called CLIENT_ID and CLIENT_SECRET
CMD ["java", "-jar", "track-searcher.jar", "--client.id=${CLIENT_ID}", "--client.secret=${CLIENT_SECRET}"]
