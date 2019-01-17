# City Tracks
City tracks is an application that returns playlist suggestion according to the current temperature from a specific city. Let's suppose you're in a very hot city and you decide to get tracks from our application, you will get a list of tracks for party.

## Business rules

For a given temperature (celsius):
* If above 30 degrees, we suggest songs for party;
* If it's between 15 and 30 degrees, we suggest pop songs;
* If it's between 10 and 14 degrees, we suggest rock songs;
* Otherwise, if it's less than 10 , we suggest classical songs.

## Stack

Java 8  
Spring Boot  
Eureka  
Ribbon  
PowerMock  
Mockito  

## Pre conditions

To run those applications you'll need to have Maven and Java 8 installed;
You will also need to have access to [Spotify](https://developer.spotify.com/dashboard/) and [OpenWeatherMap's](https://openweathermap.org/appid) API.

## Running

For each service implemmented here, you need to run it into your terminal.  
Open your terminal and type ```cd [directory-name]``` to access its directory, and then:  

* For directory **city-track-core** run:  
```./mvnw spring-boot:run```  

* For directory **eureka-server** run:  
```./mvnw spring-boot:run```  

* For directory **track-searcher** run:  
```./mvnw spring-boot:run -Dspring-boot.run.arguments="--server.port=8081" -Dspring-boot.run.jvmArguments="-DclientId=[CLIENT_ID] -DclientSecret=[CLIENT_SECRET]"```  

* For directory **weather-searcher** run:  
```./mvnw spring-boot:run -Dspring-boot.run.arguments="--server.port=8082" -Dspring-boot.run.jvmArguments="-DappId=[APP_ID]"```  

To run more than one weather or track searcher, remmember to change **--server.port** argument.

## Usage

To use it you'll need to access city-track-core application, to do so, you'll need its address (by default) at http://localhost:8080.  

To request for suggested tracks for a specific city (in example João Pessoa, but feel free to ask for any other city), open you terminal and type:  
```curl 'http://localhost:8080?city=joao+pessoa'```  
```curl 'http://localhost:8080?city=joao%20pessoa'```  
Note that to separate 2 words you must use **'+'** or **'%20'**.  

You can alternatively use latitude and longitude as parameters to generate a new playlist:  
```curl 'http://localhost:8080?latitude=-7.12&longitude=-34.86'```  
Note that to separate latitude and longitude we must use an ampersand **'&'**.  

## Response

An example of response:
```json
{
  "genre": "Pop",
  "temperature": 26,
  "tracks": [
    {
      "name": "You Are The Reason - Duet Version"
    },
    {
      "name": "You Say"
    },
    {
      "name": "Space for Two"
    }
  ]
}
```

## Future improvements

In the future we think that this application would be better if:
* We had good deployment to make it easy to scale, resilient and easy to deploy;
* Redis cache mechanism would be good to store Spotify's access token and playlists, and also temperatures;
* Improve tests coverage, making our code even more realible;
* Implement an API documentation, Swagger would be a great path for this;
* Add new repositories to query for weather, and also tracks repositories, like Deezer and Youtube.

With these improvements we would have a great brand new release.
