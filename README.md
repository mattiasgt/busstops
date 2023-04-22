This program uses the trafiklab's API to fetch data about buses in SL's public transport. It is
able to list the longest lines for buses. It also has some experimental features for trains, trams, metro, ships
and ferries, as a POC, although the stop names are not as stable in those.

Prequisites: Java 20 installed on the machine that is going to run the server.

Instructions:

* Receive and API key from trafiklab's webpage, or a secure channel and paste it after the "=" sign on
the "api.key=" line.

* Open a command prompt and navigate to the root folder of the project.

* Run the command mvnw spring-boot:run

* Wait for the server to start.

* Open a browser and go to the page: http://localhost:8080/

* Try clicking around in the system and test the features.
